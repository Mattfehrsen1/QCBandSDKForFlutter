import 'dart:async';
import 'dart:typed_data';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/utils/qc_band_sdk_const.dart';

class SportsMonitorPage extends StatefulWidget {
  final BluetoothCharacteristic? vendorNotify;
  final BluetoothCharacteristic? standardNotify;
  final BluetoothCharacteristic? standardWrite;
  final BluetoothCharacteristic? vendorWrite;
  final BluetoothCharacteristic? hrNotify; // 2A37 Heart Rate Measurement
  final BluetoothCharacteristic? rscNotify; // 2A53 RSC Measurement
  final int initialSportType;

  const SportsMonitorPage({
    super.key,
    required this.vendorNotify,
    required this.vendorWrite,
    required this.standardNotify,
    required this.standardWrite,
    required this.hrNotify,
    required this.rscNotify,
    required this.initialSportType,
  });

  @override
  State<SportsMonitorPage> createState() => _SportsMonitorPageState();
}

class _SportsMonitorPageState extends State<SportsMonitorPage> {
  StreamSubscription<List<int>>? _sub;
  StreamSubscription<List<int>>? _subStd;
  StreamSubscription<List<int>>? _subHr;
  StreamSubscription<List<int>>? _subRsc;
  String _status = '';
  int _sportType = 7;
  int _liveHr = 0;
  int _liveDur = 0;
  int _liveSteps = 0;
  int _liveDist = 0;
  double _liveCal = 0.0;
  List<Map<String, dynamic>> _summaries = [];
  Map<String, dynamic>? _lastDetailSummary;
  int _lastDetailHrSamples = 0;
  int _lastDetailSampleSecond = 0;
  String? _lastWorkoutJson;
  Timer? _durTimer;
  List<int>? _last73;
  final List<List<int>> _last73List = <List<int>>[];
  bool _hrHeuristic = false;
  int? _hrIdx;
  bool _useStrideDistance = false;
  double _strideMeters = 0.70;
  // Baseline anchoring for 0x73 so a new Start shows deltas
  bool _resetAnchorOnNext73 = false;
  int _base73Steps = 0;
  double _base73Kcal = 0.0;

  @override
  void initState() {
    super.initState();
    _sportType = widget.initialSportType;
    _attachNotify();
  }

  @override
  void dispose() {
    _sub?.cancel();
    _subStd?.cancel();
    _subHr?.cancel();
    _subRsc?.cancel();
    super.dispose();
  }

  void _attachNotify() {
    final stream = widget.vendorNotify?.value;
    if (stream == null) return;
    _sub = stream.listen((value) {
      if (value.isEmpty) return;
      // Feed vendor data to SDK assembler
      QCBandSDK.ingestVendorNotification(value);
      // Also handle 0x78 live frames for UI
      if (value[0] == 120 && value.length >= 14) {
        final t = value[1];
        final dur = value[2] | (value[3] << 8);
        final hr = value[4];
        final steps = value[5] | (value[6] << 8) | (value[7] << 16);
        final dist = value[8] | (value[9] << 8) | (value[10] << 16);
        final cal = value[11] | (value[12] << 8) | (value[13] << 16);
        setState(() {
          _sportType = t;
          _liveDur = dur;
          _liveHr = hr;
          _liveSteps = steps;
          _liveDist = dist;
          _liveCal = cal.toDouble();
        });
        _ensureTickerRunning();
        return;
      }
      // Fallback: device notify (0x73) with HR at index 10 observed in logs
      if (value[0] == 115) {
        _handleDeviceNotify73(value);
      }
    });

    // Subscribe to standard notify (0x78 often comes here)
    final stdStream = widget.standardNotify?.value;
    if (stdStream != null) {
      _subStd = stdStream.listen((value) {
        if (value.isEmpty) return;
        final parsed = QCBandSDK.DataParsingWithData(value);
        final dataType = parsed['dataType'] ?? parsed['DataType'] ?? parsed['datatype'];
        if (dataType == 'LiveSportNotify') {
          final m = parsed['dicData'] ?? parsed['data'] ?? parsed['Data'] ?? {};
          setState(() {
            _sportType = (m['sportType'] ?? _sportType) as int;
            _liveDur = (m['durationSec'] ?? _liveDur) as int;
            _liveHr = (m['heartRate'] ?? _liveHr) as int;
            _liveSteps = (m['steps'] ?? _liveSteps) as int;
            _liveDist = (m['distance'] ?? _liveDist) as int;
            _liveCal = ((m['calorie'] ?? _liveCal) as num).toDouble();
          });
          _ensureTickerRunning();
          return;
        }
        if (value[0] == 120 && value.length >= 14) {
          final t = value[1];
          final dur = value[2] | (value[3] << 8);
          final hr = value[4];
          final steps = value[5] | (value[6] << 8) | (value[7] << 16);
          final dist = value[8] | (value[9] << 8) | (value[10] << 16);
          final cal = value[11] | (value[12] << 8) | (value[13] << 16);
          setState(() {
            _sportType = t;
            _liveDur = dur;
            _liveHr = hr;
            _liveSteps = steps;
            _liveDist = dist;
            _liveCal = cal.toDouble();
          });
          _ensureTickerRunning();
          return;
        }
        if (value[0] == 115) {
          _handleDeviceNotify73(value);
        }
      });
    }

    // Subscribe to Heart Rate Measurement (2A37) if available
    final hrStream = widget.hrNotify?.value;
    if (hrStream != null) {
      _subHr = hrStream.listen((value) {
        if (value.isEmpty) return;
        // 2A37 format: Flags (bit0: HR 8/16-bit), HR value next
        final flags = value[0] & 0xFF;
        int idx = 1;
        int hr;
        if ((flags & 0x01) == 0x01) {
          // 16-bit
          if (value.length >= 3) {
            hr = value[1] | (value[2] << 8);
            idx = 3;
          } else {
            return;
          }
        } else {
          // 8-bit
          if (value.length >= 2) {
            hr = value[1];
            idx = 2;
          } else {
            return;
          }
        }
        setState(() { _liveHr = hr; });
      });
    }

    // Subscribe to RSC Measurement (2A53) if available
    final rscStream = widget.rscNotify?.value;
    if (rscStream != null) {
      _subRsc = rscStream.listen((value) {
        if (value.isEmpty) return;
        // 2A53 format: Flags, Instantaneous Speed (uint16, 1/256 m/s), Instantaneous Cadence (uint8),
        // optional: Instantaneous Stride Length (uint16), Total Distance (uint32, 1/16 m)
        int index = 0;
        final flags = value[index++] & 0xFF;
        if (value.length < index + 3) return;
        final speed256 = value[index] | (value[index + 1] << 8); index += 2; // not used yet
        final cadence = value[index++] & 0xFF; // not used yet
        bool hasStrideLen = (flags & 0x01) != 0; // bit0
        bool hasTotalDist = (flags & 0x02) != 0; // bit1
        if (hasStrideLen) {
          if (value.length < index + 2) return;
          final strideLen = value[index] | (value[index + 1] << 8);
          index += 2;
        }
        if (hasTotalDist) {
          if (value.length < index + 4) return;
          final dist16 = (value[index] | (value[index + 1] << 8) | (value[index + 2] << 16) | (value[index + 3] << 24));
          // Total Distance is in units of 1/16 meter
          final meters = (dist16 / 16.0);
          setState(() { _liveDist = meters.toInt(); });
        }
      });
    }
  }

  void _handleDeviceNotify73(List<int> value) {
    setState(() {
      _last73 = List<int>.from(value);
      _last73List.insert(0, List<int>.from(value));
      if (_last73List.length > 10) _last73List.removeLast();
    });
    if (value.length >= 12) {
      // Hypothesis from captured frames:
      // steps: LE uint16 at [4..5], distance: BE uint16 at [6..7], hr: [10]
      final stepsRaw = (value[4] & 0xFF) | ((value[5] & 0xFF) << 8);
      final calRaw = ((value[6] & 0xFF) << 8) | (value[7] & 0xFF);
      final kcalRawApprox = calRaw / 1000.0; // observed scale ~1000 → ~xx.x kcal
      if (_resetAnchorOnNext73) {
        _base73Steps = stepsRaw;
        _base73Kcal = kcalRawApprox;
        _resetAnchorOnNext73 = false;
      }
      final stepsDelta = stepsRaw - _base73Steps;
      final kcalDelta = kcalRawApprox - _base73Kcal;
      int nextHr = _liveHr;
      if (_hrHeuristic) {
        _hrIdx ??= _detectHrIndex();
        if (_hrIdx != null && _hrIdx! >= 0 && _hrIdx! < value.length) {
          final v = value[_hrIdx!] & 0xFF;
          if (v >= 40 && v <= 220) nextHr = v;
        }
      }
      int nextDist = _liveDist;
      if (_useStrideDistance) {
        final stepsForDist = stepsDelta >= 0 ? stepsDelta : 0;
        nextDist = (stepsForDist * _strideMeters).toInt();
      }
      setState(() {
        _liveSteps = stepsDelta >= 0 ? stepsDelta : 0;
        _liveCal = kcalDelta >= 0 ? kcalDelta : 0.0;
        _liveHr = nextHr;
        _liveDist = nextDist;
      });
      print('Device 0x73 → stepsRaw=$stepsRaw stepsΔ=${_liveSteps} kcalRaw≈${kcalRawApprox.toStringAsFixed(1)} kcalΔ≈${_liveCal.toStringAsFixed(1)}');
    }
    _ensureTickerRunning();
  }

  String _hrDisplay() {
    if (_liveHr > 0) {
      return _hrHeuristic ? '$_liveHr (heuristic)' : '$_liveHr';
    }
    return _hrHeuristic ? '— (heuristic)' : '—';
  }

  int? _detectHrIndex() {
    if (_last73List.length < 3) return null;
    for (int idx = 8; idx <= 13; idx++) {
      final vals = _last73List.take(5).map((p) => p.length > idx ? (p[idx] & 0xFF) : 0).toList();
      if (vals.any((v) => v < 40 || v > 220)) continue;
      bool strictlyMonotonic = true;
      for (int i = 1; i < vals.length; i++) {
        if (vals[i] == vals[i - 1]) { strictlyMonotonic = false; break; }
      }
      if (strictlyMonotonic) continue;
      return idx;
    }
    return null;
  }

  void _ensureTickerRunning() {
    _durTimer ??= Timer.periodic(const Duration(seconds: 1), (_) {
      setState(() { _liveDur += 1; });
    });
  }

  Future<void> _write(Uint8List data) async {
    try {
      if (widget.vendorWrite != null) {
        await widget.vendorWrite!.write(data);
      }
    } catch (_) {}
  }

  Future<void> _writeStd(Uint8List data) async {
    try {
      if (widget.standardWrite != null) {
        await widget.standardWrite!.write(data);
      }
    } catch (_) {}
  }

  String _fmtDur(int s) {
    final h = s ~/ 3600;
    final m = (s % 3600) ~/ 60;
    final sec = s % 60;
    if (h > 0) return '${h}h ${m}m ${sec}s';
    if (m > 0) return '${m}m ${sec}s';
    return '${sec}s';
  }

  String _fmtTs(int ts) {
    if (ts <= 0) return '-';
    final dt = DateTime.fromMillisecondsSinceEpoch(ts * 1000, isUtc: true).toLocal();
    return '${dt.year}-${dt.month.toString().padLeft(2, '0')}-${dt.day.toString().padLeft(2, '0')} ${dt.hour.toString().padLeft(2, '0')}:${dt.minute.toString().padLeft(2, '0')}';
  }

  void _appendSummaries(List<Map<String, dynamic>> list) {
    setState(() {
      _summaries = List<Map<String, dynamic>>.from(list);
    });
  }

  Future<void> _fetchDetailsFor(Map<String, dynamic> summary) async {
    final int sportType = (summary['sportType'] ?? 0) as int;
    final int startTime = (summary['startTime'] ?? 0) as int;
    setState(() { _status = 'Requesting SP+ details for start=$startTime...'; });
    QCBandSDK.getSportPlusDetailsFor(sportType, startTime, (sum, hrSeries, sampleSecond) {
      setState(() {
        _lastDetailSummary = summary;
        _lastDetailHrSamples = hrSeries.length;
        _lastDetailSampleSecond = sampleSecond;
        // Build workout JSON for easy export
        final workout = QCBandSDK.buildWorkoutJson(summary, hrSeries, sampleSecond);
        _lastWorkoutJson = jsonEncode(workout);
        _status = 'SP+ details received: samples=${hrSeries.length} sampleSecond=$sampleSecond (JSON ready)';
      });
    });
    await _write(QCBandSDK.buildSportPlusDetailsReq(sportType, startTime));
  }

  Future<void> _syncSummaries() async {
    setState(() => _status = 'Requesting SP+ summary...');
    QCBandSDK.getSportPlusSummaryFromTimestamp(0, (list) async {
      _appendSummaries(list);
      setState(() => _status = 'SP+ summary received: ${list.length} sessions');
    });
    await _write(QCBandSDK.buildSportPlusSummaryReq(0));
  }

  Future<void> _syncSummariesLast24h() async {
    final nowSec = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
    final ts = nowSec - 24 * 3600;
    setState(() => _status = 'Requesting SP+ summary (last 24h)...');
    QCBandSDK.getSportPlusSummaryFromTimestamp(ts, (list) async {
      _appendSummaries(list);
      setState(() => _status = 'SP+ summary (24h) received: ${list.length} sessions');
    });
    await _write(QCBandSDK.buildSportPlusSummaryReq(ts));
  }

  Future<void> _setDeviceClock() async {
    setState(() => _status = 'Setting device time to phone time...');
    await _writeStd(QCBandSDK.setDeviceTime(0));
    setState(() => _status = 'Device time set');
  }

  void _showLastDiagnostics() {
    final List<List<int>> sample = _last73List.take(3).toList();
    final lines = sample
        .map((pkt) => pkt.map((b) => b.toRadixString(16).padLeft(2, '0')).join(' '))
        .toList();
    final text = lines.join('\n');
    // Also print to console for easy sharing
    if (lines.isEmpty) {
      print('DIAG_73: (none yet)');
    } else {
      print('DIAG_73_BEGIN');
      for (final l in lines) {
        print('DIAG_73: $l');
      }
      print('DIAG_73_END');
    }
    showDialog(
      context: context,
      builder: (_) => AlertDialog(
        title: const Text('Last 3 device-notify (0x73) packets'),
        content: SingleChildScrollView(child: SelectableText(text.isEmpty ? '(none yet)' : text)),
        actions: [
          TextButton(onPressed: () => Navigator.of(context).pop(), child: const Text('Close')),
        ],
      ),
    );
  }

  void _setStride() {
    final controller = TextEditingController(text: _strideMeters.toStringAsFixed(2));
    showDialog(
      context: context,
      builder: (_) => AlertDialog(
        title: const Text('Set stride (meters)'),
        content: TextField(
          controller: controller,
          keyboardType: const TextInputType.numberWithOptions(decimal: true),
          decoration: const InputDecoration(hintText: 'e.g., 0.70'),
        ),
        actions: [
          TextButton(onPressed: () => Navigator.of(context).pop(), child: const Text('Cancel')),
          TextButton(
            onPressed: () {
              final v = double.tryParse(controller.text.trim());
              if (v != null && v > 0 && v < 2.0) {
                setState(() { _strideMeters = v; });
              }
              Navigator.of(context).pop();
            },
            child: const Text('Save'),
          )
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Sports Monitor')),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(12),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(10),
              decoration: BoxDecoration(
                color: Colors.greenAccent,
                borderRadius: BorderRadius.circular(8),
                border: Border.all(color: Colors.green.shade700, width: 1),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Live 0x78', style: TextStyle(fontWeight: FontWeight.w700, color: Colors.green.shade900)),
                  const SizedBox(height: 4),
                  Text('type=$_sportType  dur=${_fmtDur(_liveDur)}  hr=${_hrDisplay()}'),
                  const SizedBox(height: 2),
                  Text('steps=$_liveSteps  dist≈${_useStrideDistance ? _liveDist.toString() : 'off'} m  cal≈${_liveCal.toStringAsFixed(1)} kcal'),
                ],
              ),
            ),
            const SizedBox(height: 12),
            if (_status.isNotEmpty)
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(10),
                decoration: BoxDecoration(
                  color: Colors.amberAccent,
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(color: Colors.amber.shade700, width: 1),
                ),
                child: Text(_status, style: const TextStyle(fontWeight: FontWeight.w600)),
              ),
            const SizedBox(height: 12),
            Wrap(
              spacing: 8,
              runSpacing: 8,
              children: [
                ElevatedButton(
                  onPressed: () async {
                    setState(() => _status = 'START sent (0x77)');
                    // Send phone-controlled start on Nordic UART
                    await _writeStd(QCBandSDK.phoneSportStart(_sportType));
                    // Reset live counters so a new start doesn't look like continuation
                    setState(() {
                      _liveHr = 0;
                      _liveDur = 0;
                      _liveSteps = 0;
                      _liveDist = 0;
                      _liveCal = 0.0;
                      _last73 = null;
                      _last73List.clear();
                      _hrIdx = null;
                      _resetAnchorOnNext73 = true; // re-anchor deltas on next 0x73 frame
                    });
                    _durTimer?.cancel();
                    _durTimer = null;
                    _ensureTickerRunning();
                  },
                  child: const Text('Start'),
                ),
                ElevatedButton(
                  onPressed: () async {
                    setState(() => _status = 'PAUSE sent (0x77)');
                    await _writeStd(QCBandSDK.phoneSportPause(_sportType));
                    _durTimer?.cancel();
                    _durTimer = null;
                  },
                  child: const Text('Pause'),
                ),
                ElevatedButton(
                  onPressed: () async {
                    setState(() => _status = 'CONTINUE sent (0x77)');
                    await _writeStd(QCBandSDK.phoneSportContinue(_sportType));
                    _ensureTickerRunning();
                  },
                  child: const Text('Continue'),
                ),
                ElevatedButton(
                  onPressed: () async {
                    setState(() => _status = 'STOP sent (0x77)');
                    // Some flows send pre-stop (6) then stop (4)
                    await _writeStd(QCBandSDK.phoneSportPreStop(_sportType));
                    await Future.delayed(const Duration(milliseconds: 100));
                    await _writeStd(QCBandSDK.phoneSportStop(_sportType));
                    _durTimer?.cancel();
                    _durTimer = null;
                    // After stop, trigger Sport+ summary fetch to surface the saved session
                    await _syncSummariesLast24h();
                  },
                  child: const Text('Stop'),
                ),
                ElevatedButton(
                  onPressed: _syncSummaries,
                  child: const Text('Sync SP+ Summaries'),
                ),
                ElevatedButton(
                  onPressed: _syncSummariesLast24h,
                  child: const Text('Sync SP+ (last 24h)'),
                ),
                ElevatedButton(
                  onPressed: _setDeviceClock,
                  child: const Text('Set device time'),
                ),
                ElevatedButton(
                  onPressed: _showLastDiagnostics,
                  child: const Text('Show last 3 diag (0x73)'),
                ),
                SwitchListTile(
                  value: _hrHeuristic,
                  onChanged: (v) => setState(() { _hrHeuristic = v; _hrIdx = null; }),
                  title: const Text('Heuristic HR (0x73)'),
                  contentPadding: EdgeInsets.zero,
                ),
                SwitchListTile(
                  value: _useStrideDistance,
                  onChanged: (v) => setState(() { _useStrideDistance = v; }),
                  title: const Text('Estimate distance by stride'),
                  contentPadding: EdgeInsets.zero,
                ),
                Row(
                  children: [
                    Text('Stride: ${_strideMeters.toStringAsFixed(2)} m'),
                    const SizedBox(width: 8),
                    ElevatedButton(
                      onPressed: _setStride,
                      child: const Text('Set stride'),
                    ),
                  ],
                ),
              ],
            ),
            const SizedBox(height: 12),
            if (_summaries.isNotEmpty)
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(10),
                decoration: BoxDecoration(
                  color: Colors.blue.shade50,
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(color: Colors.blue.shade400, width: 1),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Sport+ Sessions (${_summaries.length})',
                        style: TextStyle(fontWeight: FontWeight.w700, color: Colors.blue.shade900)),
                    const SizedBox(height: 6),
                    ..._summaries.take(10).map((s) {
                      final type = s['sportType'] ?? 0;
                      final start = _fmtTs((s['startTime'] ?? 0) as int);
                      final dur = _fmtDur((s['duration'] ?? 0) as int);
                      final dist = s['distance'] ?? 0;
                      final cal = s['calories'] ?? 0;
                      final hrMin = s['hrMin'];
                      final hrAvg = s['hrAvg'];
                      final hrMax = s['hrMax'];
                      final hrStr = (hrMin != null && hrAvg != null && hrMax != null)
                          ? ' HR[$hrMin/$hrAvg/$hrMax]'
                          : '';
                      return Padding(
                        padding: const EdgeInsets.symmetric(vertical: 3.0),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Expanded(child: Text('• type=$type  start=$start  dur=$dur  dist=${dist}m  cal=$cal$hrStr')),
                            TextButton(
                              onPressed: () => _fetchDetailsFor(s),
                              child: const Text('Details'),
                            )
                          ],
                        ),
                      );
                    }).toList(),
                    if (_lastDetailSummary != null) ...[
                      const SizedBox(height: 8),
                      Text('Last details: samples=$_lastDetailHrSamples  sampleSecond=$_lastDetailSampleSecond',
                          style: TextStyle(color: Colors.blue.shade900, fontWeight: FontWeight.w600)),
                      const SizedBox(height: 6),
                      Row(
                        children: [
                          ElevatedButton(
                            onPressed: (_lastWorkoutJson == null)
                                ? null
                                : () async {
                                    await Clipboard.setData(ClipboardData(text: _lastWorkoutJson!));
                                    if (mounted) {
                                      ScaffoldMessenger.of(context).showSnackBar(
                                        const SnackBar(content: Text('Workout JSON copied')),
                                      );
                                    }
                                  },
                            child: const Text('Copy last workout JSON'),
                          ),
                          const SizedBox(width: 8),
                          if (_lastWorkoutJson != null)
                            Expanded(
                              child: Text(
                                _lastWorkoutJson!,
                                maxLines: 2,
                                overflow: TextOverflow.ellipsis,
                                style: const TextStyle(fontSize: 12, color: Colors.black87),
                              ),
                            ),
                        ],
                      ),
                    ]
                  ],
                ),
              ),
            const SizedBox(height: 12),
            if (_last73 != null)
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(10),
                decoration: BoxDecoration(
                  color: Colors.deepPurple.shade50,
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(color: Colors.deepPurple.shade200, width: 1),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Last 0x73 notify (diagnostic)',
                        style: TextStyle(fontWeight: FontWeight.w700, color: Colors.deepPurple.shade800)),
                    const SizedBox(height: 6),
                    Text(_last73!.map((b) => b.toRadixString(16).padLeft(2, '0')).join(' ')),
                    if (_last73!.length >= 11) ...[
                      const SizedBox(height: 4),
                      Text('idx4=${_last73![4]}  idx6_7_LE=${(_last73![6] | (_last73![7] << 8))}  idx10=${_last73![10]}'),
                    ]
                  ],
                ),
              ),
          ],
        ),
      ),
    );
  }
}


