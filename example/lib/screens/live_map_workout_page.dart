import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:geolocator/geolocator.dart';
import 'package:latlong2/latlong.dart' as ll;

import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';

class LiveMapWorkoutPage extends StatefulWidget {
  final BluetoothCharacteristic? vendorNotify;
  final BluetoothCharacteristic? vendorWrite;
  final BluetoothCharacteristic? standardNotify;
  final BluetoothCharacteristic? standardWrite;
  final int? initialSportType;

  const LiveMapWorkoutPage({
    super.key,
    required this.vendorNotify,
    required this.vendorWrite,
    required this.standardNotify,
    required this.standardWrite,
    this.initialSportType,
  });

  @override
  State<LiveMapWorkoutPage> createState() => _LiveMapWorkoutPageState();
}

class _LiveMapWorkoutPageState extends State<LiveMapWorkoutPage> {
  final MapController _map = MapController();
  final List<ll.LatLng> _route = [];
  ll.LatLng? _initialCenter;
  StreamSubscription<Position>? _gpsSub;
  StreamSubscription<List<int>>? _vendorNotifySub;
  StreamSubscription<List<int>>? _stdNotifySub;

  int _sportType = 7; // default Run
  bool _tracking = false;
  bool _paused = false;
  String _status = '';

  // Live metrics from band
  int _hr = 0;
  int _steps = 0;
  int _durationSec = 0;
  int _distanceMetersLive = 0;

  // Post-run summary
  Map<String, dynamic>? _summary;
  List<int> _hrSeries = const [];
  int _sampleSecond = 0;

  @override
  void initState() {
    super.initState();
    _sportType = widget.initialSportType ?? 7;
    // Live 0x78 listener
    QCBandSDK.setLiveSportListener((live) {
      setState(() {
        _hr = (live['heartRate'] ?? 0) as int;
        _steps = (live['steps'] ?? 0) as int;
        _durationSec = (live['durationSec'] ?? 0) as int;
        _distanceMetersLive = (live['distance'] ?? 0) as int;
      });
    });
    // Pipe vendor notify to assembler (Sport+)
    final v = widget.vendorNotify?.value;
    if (v != null) {
      _vendorNotifySub = v.listen((data) {
        QCBandSDK.ingestVendorNotification(data);
      });
    }
    // Optional: standard notify through generic parser if desired
    final s = widget.standardNotify?.value;
    if (s != null) {
      _stdNotifySub = s.listen((data) {
        // Ignore empty payloads to avoid RangeError inside decoders
        if (data.isNotEmpty) {
          QCBandSDK.ingestStandardNotification(data);
        }
      });
    }

    // Center map on user's current location at startup (best-effort)
    () async {
      try {
        final pos = await _ensureLocation();
        if (!mounted || pos == null) return;
        final here = ll.LatLng(pos.latitude, pos.longitude);
        setState(() {
          _initialCenter = here;
        });
        // move after first frame to ensure controller is ready
        WidgetsBinding.instance.addPostFrameCallback((_) {
          _map.move(here, 16.0);
        });
      } catch (_) {
        if (!mounted) return;
        setState(() {
          _status = 'Location permission missing; rebuild app after manifest change';
        });
      }
    }();
  }

  @override
  void dispose() {
    _gpsSub?.cancel();
    _vendorNotifySub?.cancel();
    _stdNotifySub?.cancel();
    QCBandSDK.setLiveSportListener(null);
    super.dispose();
  }

  Future<Position?> _ensureLocation() async {
    bool serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      setState(() => _status = 'Enable Location Services');
      return null;
    }
    LocationPermission permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
    }
    if (permission == LocationPermission.denied || permission == LocationPermission.deniedForever) {
      setState(() => _status = 'Location permission denied');
      return null;
    }
    try {
      return await Geolocator.getCurrentPosition(desiredAccuracy: LocationAccuracy.best);
    } catch (_) {
      return null;
    }
  }

  Future<void> _start() async {
    final current = await _ensureLocation();
    setState(() {
      _status = 'Starting...';
      _summary = null;
      _hrSeries = const [];
      _sampleSecond = 0;
    });
    // Send phone-sport start (0x77)
    await _writeStd(QCBandSDK.phoneSportStart(_sportType));
    // Start GPS stream
    _gpsSub?.cancel();
    _gpsSub = Geolocator.getPositionStream(
      locationSettings: const LocationSettings(accuracy: LocationAccuracy.best, distanceFilter: 1),
    ).listen((pos) {
      final p = ll.LatLng(pos.latitude, pos.longitude);
      if (_route.isEmpty || _route.last != p) {
        setState(() {
          _route.add(p);
        });
      }
    });
    if (current != null) {
      final first = ll.LatLng(current.latitude, current.longitude);
      setState(() {
        _route.add(first);
      });
      _map.move(first, 16.0);
    }
    setState(() {
      _tracking = true;
      _paused = false;
      _status = 'Running';
    });
  }

  Future<void> _pause() async {
    if (!_tracking || _paused) return;
    await _writeStd(QCBandSDK.phoneSportPause(_sportType));
    await _gpsSub?.cancel();
    setState(() {
      _paused = true;
      _status = 'Paused';
    });
  }

  Future<void> _resume() async {
    if (!_tracking || !_paused) return;
    await _writeStd(QCBandSDK.phoneSportContinue(_sportType));
    _gpsSub = Geolocator.getPositionStream(
      locationSettings: const LocationSettings(accuracy: LocationAccuracy.best, distanceFilter: 1),
    ).listen((pos) {
      final p = ll.LatLng(pos.latitude, pos.longitude);
      if (_route.isEmpty || _route.last != p) {
        setState(() {
          _route.add(p);
        });
      }
    });
    setState(() {
      _paused = false;
      _status = 'Running';
    });
  }

  Future<void> _stop() async {
    if (!_tracking) return;
    // Pre-stop then stop
    for (final pkt in QCBandSDK.phoneSportStopSequence(_sportType)) {
      await _writeStd(pkt);
      await Future.delayed(const Duration(milliseconds: 120));
    }
    await _gpsSub?.cancel();
    setState(() {
      _tracking = false;
      _paused = false;
      _status = 'Stopped – syncing...';
    });
    // Fetch Sport+ summaries since last 24h
    final cutoffTs = DateTime.now().toUtc().subtract(const Duration(hours: 24)).millisecondsSinceEpoch ~/ 1000;
    QCBandSDK.getSportPlusSummaryFromTimestamp(cutoffTs, (summaries) async {
      // pick the latest matching sportType
      summaries.sort((a, b) => ((b['startTime'] ?? 0) as int).compareTo((a['startTime'] ?? 0) as int));
      final latest = summaries.firstWhere(
        (s) => (s['sportType'] ?? _sportType) == _sportType,
        orElse: () => summaries.isNotEmpty ? summaries.first : <String, dynamic>{},
      );
      if (latest.isNotEmpty) {
        setState(() => _summary = latest);
        // Request details (HR series)
        QCBandSDK.getSportPlusDetailsFor((latest['sportType'] ?? _sportType) as int, (latest['startTime'] ?? 0) as int,
            (sum, hrSeries, sampleSecond) {
          setState(() {
            _hrSeries = hrSeries;
            _sampleSecond = sampleSecond;
            _status = 'Synced';
          });
        });
        await _writeVendor(QCBandSDK.buildSportPlusDetailsReq(
            (latest['sportType'] ?? _sportType) as int, (latest['startTime'] ?? 0) as int));
      } else {
        setState(() => _status = 'No SP+ summary found');
      }
    });
    await _writeVendor(QCBandSDK.buildSportPlusSummaryReq(cutoffTs));
  }

  Future<void> _writeVendor(Uint8List data) async {
    if (widget.vendorWrite != null) {
      await widget.vendorWrite!.write(data);
    }
  }

  Future<void> _writeStd(Uint8List data) async {
    if (widget.standardWrite != null) {
      await widget.standardWrite!.write(data);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Live Map Workout')),
      body: Column(
        children: [
          // Map
          Expanded(
            child: FlutterMap(
              mapController: _map,
              options: MapOptions(
                initialCenter: _initialCenter ?? (_route.isNotEmpty ? _route.last : const ll.LatLng(0, 0)),
                initialZoom: _initialCenter != null ? 15 : 3,
              ),
              children: [
                TileLayer(
                  urlTemplate: 'https://{s}.basemaps.cartocdn.com/rastertiles/light_all/{z}/{x}/{y}{r}.png',
                  subdomains: const ['a', 'b', 'c', 'd'],
                  retinaMode: RetinaMode.isHighDensity(context),
                  userAgentPackageName: 'com.penng.ai.qc_band_sdk_for_flutter_example',
                ),
                PolylineLayer(
                  polylines: [
                    Polyline(points: _route, color: Colors.blueAccent, strokeWidth: 4),
                  ],
                ),
              ],
            ),
          ),
          // Live HUD
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: SingleChildScrollView(
              scrollDirection: Axis.horizontal,
              child: Row(
                children: [
                  _chip('HR', '$_hr bpm'),
                  const SizedBox(width: 8),
                  _chip('Steps', '$_steps'),
                  const SizedBox(width: 8),
                  _chip('Dist', '${(_distanceMetersLive / 1000).toStringAsFixed(2)} km'),
                  const SizedBox(width: 8),
                  _chip('Time', _fmtTime(_durationSec)),
                ],
              ),
            ),
          ),
          // Controls
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
            child: Wrap(
              spacing: 8,
              children: [
                ElevatedButton(onPressed: _tracking ? null : _start, child: const Text('Start')),
                ElevatedButton(onPressed: _tracking && !_paused ? _pause : null, child: const Text('Pause')),
                ElevatedButton(onPressed: _tracking && _paused ? _resume : null, child: const Text('Continue')),
                ElevatedButton(onPressed: _tracking ? _stop : null, child: const Text('Stop')),
              ],
            ),
          ),
          if (_status.isNotEmpty)
            Padding(
              padding: const EdgeInsets.only(bottom: 8),
              child: Text(_status, style: const TextStyle(fontWeight: FontWeight.w600)),
            ),
          if (_summary != null)
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 6),
              child: _buildSummaryCard(),
            ),
        ],
      ),
    );
  }

  Widget _chip(String k, String v) {
    return Chip(label: Text('$k: $v'));
  }

  String _fmtTime(int s) {
    final h = s ~/ 3600;
    final m = (s % 3600) ~/ 60;
    final ss = s % 60;
    if (h > 0) return '${h}h ${m.toString().padLeft(2, '0')}m';
    return '${m}m ${ss.toString().padLeft(2, '0')}s';
  }

  Widget _buildSummaryCard() {
    final s = _summary!;
    final dur = (s['duration'] ?? 0) as int;
    final dist = (s['distance'] ?? 0) as int;
    final cal = (s['calories'] ?? 0) as int;
    final hrA = (s['hrAvg'] ?? 0) as int;
    final hrN = _hrSeries.length;
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text('Sport+ Summary', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 6),
            Text('Duration: ${_fmtTime(dur)}'),
            Text('Distance: ${(dist / 1000).toStringAsFixed(2)} km'),
            Text('Calories: ${cal / 1000.0} kcal (raw: $cal)'),
            Text('Avg HR: $hrA bpm • Samples: $hrN @ ${_sampleSecond}s'),
          ],
        ),
      ),
    );
  }
}


