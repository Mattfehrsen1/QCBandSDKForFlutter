import 'dart:async';
import 'dart:convert'; // Required for JSON encoding
import 'dart:developer';
import 'dart:typed_data'; // Required for Uint8List and ByteData
import 'dart:core'; // Required for Endian
import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:qc_band_sdk_for_flutter/bean/models/blood_pressure.dart';
import 'package:qc_band_sdk_for_flutter/utils/qc_band_sdk_const.dart';
import 'package:qc_band_sdk_for_flutter_example/utils/utils.dart';

import '../widgets/service_tile.dart';
import '../widgets/characteristic_tile.dart';
import '../widgets/descriptor_tile.dart';
import '../utils/snackbar.dart';
import '../utils/extra.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/bean/models/blood_oxygen_entity.dart';
import 'package:qc_band_sdk_for_flutter/bean/models/sleepModel.dart';
import 'package:qc_band_sdk_for_flutter/bean/models/alarm.dart';
import 'package:qc_band_sdk_for_flutter/utils/resolve_util.dart';
import 'sports_monitor_page.dart';

class DeviceScreen extends StatefulWidget {
  final BluetoothDevice device;

  const DeviceScreen({Key? key, required this.device}) : super(key: key);

  @override
  State<DeviceScreen> createState() => _DeviceScreenState();
}

class _DeviceScreenState extends State<DeviceScreen> {
  int? _rssi;
  int? _mtuSize;
  BluetoothConnectionState _connectionState =
      BluetoothConnectionState.disconnected;
  List<BluetoothService> _services = [];
  bool _isDiscoveringServices = false;
  bool _isConnecting = false;
  bool _isDisconnecting = false;

  late StreamSubscription<BluetoothConnectionState>
      _connectionStateSubscription;
  late StreamSubscription<bool> _isConnectingSubscription;
  late StreamSubscription<bool> _isDisconnectingSubscription;
  late StreamSubscription<int> _mtuSubscription;
  late BluetoothCharacteristic _bluetoothCharacteristicNotification;
  late BluetoothCharacteristic _bluetoothCharacteristicWrite;
  late BluetoothCharacteristic _secondbluetoothCharacteristicNotification;
  // de5bf72a-d711-4e47-af26-65e3012a5dc7
  late BluetoothCharacteristic _secondbluetoothCharacteristicWrite;
  BluetoothCharacteristic? _hrMeasurementChar; // 2A37
  BluetoothCharacteristic? _rscMeasurementChar; // 2A53
  StreamSubscription<List<int>>? _heartRateNotificationSubscription;
  Timer? _heartRateRepeatingTimer;
  StreamSubscription<List<int>>? _liveWorkoutSubscription;
  Timer? _hrKeepAliveTimer;
  Timer? _sportHeartbeatTimer;
  int _workoutElapsedSec = 0;

  // Live workout state
  int _selectedSportType = 7; // default Run
  int _liveDurationSec = 0;
  int _liveHr = 0;
  int _liveSteps = 0;
  int _liveDistance = 0; // meters
  int _liveCalories = 0; // calories
  final List<int> _hrSamples = <int>[]; // accumulating HR samples during workout
  String _vendorStatus = '';

  Future<void> _discoverServicesAndCharacteristics(
      BluetoothDevice device) async {
    try {
      final services = await device.discoverServices();
// I/flutter (25374): These are the services 1801
// I/flutter (25374): These are the services 1800
// I/flutter (25374): These are the services 6e40fff0-b5a3-f393-e0a9-e50e24dcca9e
// I/flutter (25374): These are the services de5bf728-d711-4e47-af26-65e3012a5dc7
// I/flutter (25374): These are the services 180a
// I/flutter (25374): These are the services fee7

      // Look for your specific service UUID
      for (final service in services) {
        print("These are the services ${service.uuid.str}");
        // TODO: Replace "fff0" with your actual service UUID if different
        if (service.uuid.str
            .toString()
            .toLowerCase()
            .contains("6e40fff0-b5a3-f393-e0a9-e50e24dcca9e")) {
          print('Found required service: ${service.uuid}');

          for (final characteristic in service.characteristics) {
            // Find write characteristic (fff6)
            if (characteristic.uuid.str.toString().toLowerCase() ==
                "6e400002-b5a3-f393-e0a9-e50e24dcca9e") {
              // print('Found write characteristic: ${characteristic.uuid.str}');
              // characteristic.write(QCBandSDK.GetDeviceBatteryLevel());
              setState(() {
                _bluetoothCharacteristicWrite = characteristic;
              });

              // FFAppState().DeviceCharactertics = characteristic;
            }

            // Find notification characteristic (fff7)
            if (characteristic.uuid.str.toString().toLowerCase() ==
                "00002902-0000-1000-8000-00805f9b34fb") {
              print(
                  'Found notification characteristic: ${characteristic.uuid.str}');

              if (characteristic.properties.notify) {
                try {
                  // Enable notifications
                  await characteristic.setNotifyValue(true);
                  setState(() {
                    _bluetoothCharacteristicWrite = characteristic;
                  });
                  print('Notifications enabled');
                  // FFAppState().ListenValueCharactertics = characteristic;
                } catch (e) {
                  print('Error enabling notifications: $e');
                }
              } else {
                print('Characteristic does not support notifications');
              }
            }
            if (characteristic.uuid.str.toString().toLowerCase() ==
                "6e400003-b5a3-f393-e0a9-e50e24dcca9e") {
              print(
                  'Found notification characteristic: ${characteristic.uuid.str}');

              if (characteristic.properties.notify) {
                try {
                  // Enable notifications
                  await characteristic.setNotifyValue(true);
                  setState(() {
                    _bluetoothCharacteristicNotification = characteristic;
                  });
                  print('Notifications enabled');
                  _ensureLiveWorkoutListener();
                  // FFAppState().ListenValueCharactertics = characteristic;
                } catch (e) {
                  print('Error enabling notifications: $e');
                }
              } else {
                print('Characteristic does not support notifications');
              }
            }
          }
        }
        if (service.uuid.str
            .toString()
            .toLowerCase()
            .contains(QcBandSdkConst.serialPortService)) {
          print('Found required service 2 : ${service.uuid}');

          for (final characteristic in service.characteristics) {
            print('Found required Charactertics 2 : ${characteristic.uuid}');
            // Find write characteristic (fff6)
            if (characteristic.uuid.str.toString().toLowerCase() ==
                QcBandSdkConst.serialPortWrite) {
              setState(() {
                _secondbluetoothCharacteristicWrite = characteristic;
                print(
                    'Updated _secondbluetoothCharacteristicWrite  : ${characteristic.uuid}');
              });
            }

            // Find notification characteristic (fff7)
            if (characteristic.uuid.str.toString().toLowerCase() ==
                QcBandSdkConst.serialPortNotify) {
              print(
                  'Found notification characteristic: ${characteristic.uuid.str}');

              if (characteristic.properties.notify) {
                try {
                  // Enable notifications de5bf729-d711-4e47-af26-65e3012a5dc7
                  await characteristic.setNotifyValue(true);
                  setState(() {
                    _secondbluetoothCharacteristicNotification = characteristic;
                  });
                  print(
                      'Notifications enabled  de5bf729-d711-4e47-af26-65e3012a5dc7 ');
                  _ensureLiveWorkoutListener();
                  // FFAppState().ListenValueCharactertics = characteristic;
                } catch (e) {
                  // print('Error enabling notifications: $e');
                }
              } else {
                print('Characteristic does not support notifications');
              }
            }
            // if (characteristic.uuid.str.toString().toLowerCase() ==
            //     QcBandSdkConst.serialPortNotify) {
            //   print(
            //       'Found notification characteristic: ${characteristic.uuid.str}');

            //   if (characteristic.properties.notify) {
            //     try {
            //       // Enable notifications
            //       await characteristic.setNotifyValue(true);
            //       setState(() {
            //         _bluetoothCharacteristicNotification = characteristic;
            //       });
            //       print('Notifications enabled');
            //       // FFAppState().ListenValueCharactertics = characteristic;
            //     } catch (e) {
            //       print('Error enabling notifications: $e');
            //     }
            //   } else {
            //     print('Characteristic does not support notifications');
            //   }
            // }
          }
        }
        // Heart Rate service 180D
        if (service.uuid.str.toString().toLowerCase().contains("0000180d-0000-1000-8000-00805f9b34fb") ||
            service.uuid.str.toString().toLowerCase().contains("180d")) {
          for (final characteristic in service.characteristics) {
            final id = characteristic.uuid.str.toString().toLowerCase();
            if (id == "00002a37-0000-1000-8000-00805f9b34fb" || id.endsWith("2a37")) {
              try {
                await characteristic.setNotifyValue(true);
                setState(() { _hrMeasurementChar = characteristic; });
                print('HR Measurement (2A37) notifications enabled');
              } catch (e) {
                print('Error enabling HR notify: $e');
              }
            }
          }
        }
        // Running Speed and Cadence service 1814
        if (service.uuid.str.toString().toLowerCase().contains("00001814-0000-1000-8000-00805f9b34fb") ||
            service.uuid.str.toString().toLowerCase().contains("1814")) {
          for (final characteristic in service.characteristics) {
            final id = characteristic.uuid.str.toString().toLowerCase();
            if (id == "00002a53-0000-1000-8000-00805f9b34fb" || id.endsWith("2a53")) {
              try {
                await characteristic.setNotifyValue(true);
                setState(() { _rscMeasurementChar = characteristic; });
                print('RSC Measurement (2A53) notifications enabled');
              } catch (e) {
                print('Error enabling RSC notify: $e');
              }
            }
          }
        }
      }
    } catch (e) {
      print('Error discovering services: $e');
    }
  }

  // ======== Alarm UI state ========
  int _alarmIndex = 0;
  TimeOfDay _alarmTime = const TimeOfDay(hour: 7, minute: 30);
  List<bool> _alarmRepeat = [false, true, true, true, true, true, false]; // Su..Sa

  Future<void> _pickAlarmTime() async {
    final picked = await showTimePicker(context: context, initialTime: _alarmTime);
    if (picked != null) {
      setState(() => _alarmTime = picked);
    }
  }

  @override
  void initState() {
    super.initState();

    _connectionStateSubscription =
        widget.device.connectionState.listen((state) async {
      _connectionState = state;
      if (state == BluetoothConnectionState.connected) {
        _services = []; // must rediscover services
        _discoverServicesAndCharacteristics(widget.device);
      }
      if (state != BluetoothConnectionState.connected) {
        onConnectPressed();
        _discoverServicesAndCharacteristics(widget.device);
      }
      if (state == BluetoothConnectionState.connected && _rssi == null) {
        _rssi = await widget.device.readRssi();
      }
      if (mounted) {
        setState(() {});
      }
    });

    _mtuSubscription = widget.device.mtu.listen((value) {
      _mtuSize = value;
      if (mounted) {
        setState(() {});
      }
    });

    _isConnectingSubscription = widget.device.isConnecting.listen((value) {
      _isConnecting = value;
      if (mounted) {
        setState(() {});
      }
    });

    _isDisconnectingSubscription =
        widget.device.isDisconnecting.listen((value) {
      _isDisconnecting = value;
      if (mounted) {
        setState(() {});
      }
    });
  }

  @override
  void dispose() {
    _connectionStateSubscription.cancel();
    _mtuSubscription.cancel();
    _isConnectingSubscription.cancel();
    _isDisconnectingSubscription.cancel();
    super.dispose();
  }

  bool get isConnected {
    return _connectionState == BluetoothConnectionState.connected;
  }

  Future onConnectPressed() async {
    try {
      await widget.device.connectAndUpdateStream();
      Snackbar.show(ABC.c, "Connect: Success", success: true);
    } catch (e) {
      if (e is FlutterBluePlusException &&
          e.code == FbpErrorCode.connectionCanceled.index) {
        // ignore connections canceled by the user
      } else {
        Snackbar.show(ABC.c, prettyException("Connect Error:", e),
            success: false);
        print(e);
      }
    }
  }

  Future onCancelPressed() async {
    try {
      await widget.device.disconnectAndUpdateStream(queue: false);
      Snackbar.show(ABC.c, "Cancel: Success", success: true);
    } catch (e) {
      Snackbar.show(ABC.c, prettyException("Cancel Error:", e), success: false);
      print(e);
    }
  }

  Future onDisconnectPressed() async {
    try {
      await widget.device.disconnectAndUpdateStream();
      Snackbar.show(ABC.c, "Disconnect: Success", success: true);
    } catch (e) {
      Snackbar.show(ABC.c, prettyException("Disconnect Error:", e),
          success: false);
      print(e);
    }
  }

  Future onDiscoverServicesPressed() async {
    if (mounted) {
      setState(() {
        _isDiscoveringServices = true;
      });
    }
    try {
      _services = await widget.device.discoverServices();
      Snackbar.show(ABC.c, "Discover Services: Success", success: true);
    } catch (e) {
      Snackbar.show(ABC.c, prettyException("Discover Services Error:", e),
          success: false);
      print(e);
    }
    if (mounted) {
      setState(() {
        _isDiscoveringServices = false;
      });
    }
  }

  Future onRequestMtuPressed() async {
    try {
      await widget.device.requestMtu(223, predelay: 0);
      Snackbar.show(ABC.c, "Request Mtu: Success", success: true);
    } catch (e) {
      Snackbar.show(ABC.c, prettyException("Change Mtu Error:", e),
          success: false);
      print(e);
    }
  }

  List<Widget> _buildServiceTiles(BuildContext context, BluetoothDevice d) {
    return _services
        .map(
          (s) => ServiceTile(
            service: s,
            characteristicTiles: s.characteristics
                .map((c) => _buildCharacteristicTile(c))
                .toList(),
          ),
        )
        .toList();
  }

  CharacteristicTile _buildCharacteristicTile(BluetoothCharacteristic c) {
    return CharacteristicTile(
      characteristic: c,
      descriptorTiles:
          c.descriptors.map((d) => DescriptorTile(descriptor: d)).toList(),
    );
  }

  Widget buildSpinner(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(14.0),
      child: AspectRatio(
        aspectRatio: 1.0,
        child: CircularProgressIndicator(
          backgroundColor: Colors.black12,
          color: Colors.black26,
        ),
      ),
    );
  }

  Widget buildRemoteId(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Text('${widget.device.remoteId}'),
    );
  }

  Widget buildRssiTile(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        isConnected
            ? const Icon(Icons.bluetooth_connected)
            : const Icon(Icons.bluetooth_disabled),
        Text(((isConnected && _rssi != null) ? '${_rssi!} dBm' : ''),
            style: Theme.of(context).textTheme.bodySmall)
      ],
    );
  }

  Widget buildGetServices(BuildContext context) {
    return IndexedStack(
      index: (_isDiscoveringServices) ? 1 : 0,
      children: <Widget>[
        TextButton(
          child: const Text("Get Services"),
          onPressed: onDiscoverServicesPressed,
        ),
        const IconButton(
          icon: SizedBox(
            child: CircularProgressIndicator(
              valueColor: AlwaysStoppedAnimation(Colors.grey),
            ),
            width: 18.0,
            height: 18.0,
          ),
          onPressed: null,
        )
      ],
    );
  }

  Widget buildMtuTile(BuildContext context) {
    return ListTile(
        title: const Text('MTU Size'),
        subtitle: Text('$_mtuSize bytes'),
        trailing: IconButton(
          icon: const Icon(Icons.edit),
          onPressed: onRequestMtuPressed,
        ));
  }

  Widget buildConnectButton(BuildContext context) {
    return Row(children: [
      if (_isConnecting || _isDisconnecting) buildSpinner(context),
      TextButton(
          onPressed: _isConnecting
              ? onCancelPressed
              : (isConnected ? onDisconnectPressed : onConnectPressed),
          child: Text(
            _isConnecting ? "CANCEL" : (isConnected ? "DISCONNECT" : "CONNECT"),
            style: Theme.of(context)
                .primaryTextTheme
                .labelLarge
                ?.copyWith(color: Colors.white),
          ))
    ]);
  }

  getDeviceBattery() async {
    await _bluetoothCharacteristicWrite
        .write(QCBandSDK.GetDeviceBatteryLevel());
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');

      if (value.isNotEmpty) {
        var recievedBattery = QCBandSDK.DataParsingWithData(value);
        log('Battery Data: $recievedBattery');
      }
    });
  }

  // Global or class-level variables to store accumulated HR data and track state
  Map<String, int> _accumulatedHrData = {};
  int _lastParsedPacketIndex = -1;
  int _nextExpectedDataPointMinute =
      0; // Tracks the start time for the *next* expected HR data point

  getHRData() async {
    // Reset state variables at the beginning of a new HR data retrieval session
    _accumulatedHrData = {};
    _lastParsedPacketIndex = -1;
    _nextExpectedDataPointMinute = 0;

    int currentUnixTimestamp =
        DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
    Uint8List heartRateCommandPacket =
        QCBandSDK.buildReadHeartRateCommand(currentUnixTimestamp);

    print('Generated command packet: $heartRateCommandPacket');
    await _bluetoothCharacteristicWrite.write(heartRateCommandPacket);

    _bluetoothCharacteristicNotification.value.listen((value) {
      if (value.isNotEmpty) {
        if (value[0] == 21 && value[1] >= 1) {
          log('Received HR data packet: $value');

          int packetIndex = value[1];
          int totalPackets = value[
              2]; // Assuming value[2] is the total number of packets expected

          if (packetIndex <= _lastParsedPacketIndex &&
              _lastParsedPacketIndex != -1) {
            log('Skipping already processed or out-of-order packet: $packetIndex (last processed: $_lastParsedPacketIndex)');
            return;
          }

          int dataStartIndex;
          int dataPointsCount;

          if (packetIndex == 1) {
            dataStartIndex = 6;
            dataPointsCount = 9;
            log('Parsing FIRST HR packet (index $packetIndex): Data starts at index $dataStartIndex, count $dataPointsCount.');
          } else {
            dataStartIndex = 2; // Data points start from values[2]
            dataPointsCount = 13; // Each packet contains 13 data points
            log('Parsing SUBSEQUENT HR packet (index $packetIndex): Data starts at index $dataStartIndex, count $dataPointsCount.');
          }

          if (value.length < dataStartIndex + dataPointsCount) {
            log('Error: Packet with index $packetIndex is too short for its declared data points. Expected at least ${dataStartIndex + dataPointsCount} bytes, got ${value.length}.');
            return;
          }

          parseAndAccumulateHrData(
            values: value,
            dataStartIndex: dataStartIndex,
            dataPointsCount: dataPointsCount,
          );

          _lastParsedPacketIndex = packetIndex;

          // --- Outputting Accumulated Data After Each Packet ---
          log('Accumulated HR data after packet $packetIndex: $_accumulatedHrData');

          // Check if this is the last expected packet and log the final data.
          if (packetIndex == totalPackets) {
            log('--- All HR data packets received. Final Accumulated Data ---');
            log('$_accumulatedHrData'); // This will show the complete map
            log('-------------------------------------------------------');
            // You might want to pass _accumulatedHrData to a callback or update a UI here.
          }
        }
      }
    });
  }

  /// Parses a heart rate data packet and accumulates the data.
  parseAndAccumulateHrData({
    required List<int> values,
    required int dataStartIndex,
    required int dataPointsCount,
  }) {
    int lastDataPointMinuteInPacket = _nextExpectedDataPointMinute;

    for (int i = 0; i < dataPointsCount; i++) {
      int hrValue = values[dataStartIndex + i];

      int currentDataPointTotalMinutes = _nextExpectedDataPointMinute + (i * 5);

      int hour = (currentDataPointTotalMinutes ~/ 60) % 24;
      int minute = currentDataPointTotalMinutes % 60;

      String timeKey =
          '${hour.toString().padLeft(2, '0')}:${minute.toString().padLeft(2, '0')}';
      _accumulatedHrData[timeKey] = hrValue;

      lastDataPointMinuteInPacket = currentDataPointTotalMinutes;
    }

    _nextExpectedDataPointMinute = lastDataPointMinuteInPacket + 5;

    log('Parsed HR data for this packet. Last data point at: ${lastDataPointMinuteInPacket} mins. Next expected start: ${_nextExpectedDataPointMinute} mins.');
  }

// Function to convert an integer Unix timestamp to a 4-byte little-endian array

// Example Usage:
// int currentUnixTimestamp = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
// Uint8List heartRateCommandPacket = buildReadHeartRateCommand(currentUnixTimestamp);
//
// print('Generated command packet: $heartRateCommandPacket');
//
// // To verify the timestamp interpretation on the receiving end (similar to Java's byteArrayToInt):
// int reconstructedTimestamp = ByteData.view(heartRateCommandPacket.buffer).getInt32(1, Endian.little);
// print('Reconstructed timestamp: $reconstructedTimestamp');

// Find equipment
  findDevice() async {
    await _bluetoothCharacteristicWrite
        .write(QCBandSDK.buildFindDeviceCommand());
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      // if (value.isNotEmpty) {
      //   var recievedHrData = QCBandSDK.DataParsingWithData(value);
      //   print(recievedHrData);
      // }
    });
  }

  // Device Callibaration
//   runDeviceCalibaration() async {
// //     Type	Meaning
// // 6	Start calibration
// // 2	Stop calibration
//     await _bluetoothCharacteristicWrite
//         .write(QCBandSDK.runDeviceCallibration(6));
//     _bluetoothCharacteristicNotification.value.listen((value) {
//       // Handle the received value (List<int>)
//       print('Received notification: $value');
//       if (value.isNotEmpty) {
//         var recievedData = QCBandSDK.DataParsingWithData(value);
//         print(recievedData);
//       }
//     });
//   }

  // Step Data of Today
  stepData() async {
    await _bluetoothCharacteristicWrite.write(QCBandSDK.GetStepOfToday());
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      if (value.isNotEmpty) {
        var recievedBattery = QCBandSDK.DataParsingWithData(value);
        print(recievedBattery);
      }
    });
  }

  // Step Data of Today Details
  deviceDetailStep() async {
    var jsonData = [];
    await _bluetoothCharacteristicWrite
        .write(QCBandSDK.generateReadStepDetailsCommand(0, 0, 95));
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      // print('Received notification: $value');
      if (value.isNotEmpty) {
        if (value[1] != 240 && value[0] == 67) {
          print('This is the Second Value ${value[1]}');
          jsonData.add(parseDetailStepData(value));
        }
        if (value[5] == value[6] - 1) {
          log("Accumated Data $jsonData");
          jsonData = [];
        }
        // var recievedBattery = QCBandSDK.DataParsingWithData(value);
        // print(recievedBattery);
      }
    });
  }

  parseDetailStepData(List<int> value) {
    Map stepDetail = {};
    // Seperate the Year , Month , Day , Time Index , Calorie , Walk Steps , Distance first
    // Combine the all data in one.
    int year = bcdToDecimal(value[1]) + 2000;
    int month = bcdToDecimal(value[2]);
    int day = bcdToDecimal(value[3]);
    double timeInhr = (value[4] * 15) / 60;
    int calorie = bytes2Int([value[8], value[7]]);
    int walkSteps = bytes2Int([value[10], value[9]]);
    int distance = bytes2Int([value[12], value[11]]);

    stepDetail = {
      "year": year,
      "month": month,
      "day": day,
      "time": timeInhr,
      "calorie": calorie,
      "walkSteps": walkSteps,
      "distance": distance,
    };
    log('This is the Date Parsed $stepDetail');
    // int month = bcdToDecimal(value[2]);
    return stepDetail;
  }

  // Heart Data of Today
  hrData() async {
    await _bluetoothCharacteristicWrite
        .write(QCBandSDK.getHRate(DateTime(2025, 6, 6).toString()));
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      if (value.isNotEmpty) {
        print('Received notification: $value');

        // var recievedBattery = QCBandSDK.DataParsingWithData(value);
        // print(recievedBattery);
      }
    });
  }

  // Step Data of Today
  // hrvDetails() async {
  //   // Today
  //   await _bluetoothCharacteristicWrite.write(
  //     QCBandSDK.getHRV(0),
  //   );
  //   _bluetoothCharacteristicNotification.value.listen((value) {
  //     // Handle the received value (List<int>)
  //     // print('Received notification: $value');
  //     if (value.isNotEmpty && value[0] == QcBandSdkConst.cmdHrv) {
  //       // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
  //       // // print(recievedHRVData);
  //       parseHRVData(value);
  //       log('Received HRV: $value');
  //     }
  //   });
  // }
// Global variable for accumulated data
  final Map<String, int> _accumulatedHrvData = LinkedHashMap<String, int>();

  Future<void> hrvDetails() async {
    // Clear any previously accumulated data before starting a new request.
    _accumulatedHrvData.clear();

    // Request HRV data from the device.
    await _bluetoothCharacteristicWrite.write(
      QCBandSDK.getHRV(1), // Assuming '1' requests today's data
    );

    // Listen for incoming characteristic notifications from the BLE device.
    _bluetoothCharacteristicNotification.value.listen((value) {
      log('Received raw notification: $value'); // Log every incoming packet.

      // Check if the packet is an HRV command response (value[0] == 57).
      if (value.isNotEmpty && value[0] == QcBandSdkConst.cmdHrv) {
        log('Identified as HRV type packet: $value');

        // Check if value[1] is a packet index that we expect to contain HRV data points.
        // Assuming packet indices 1, 2, 3, 4 contain sequential HRV data.
        if (value.length > 1 && value[1] >= 1 && value[1] <= 4) {
          // Adjust range if more packets exist
          log('Processing HRV data packet with index: ${value[1]}');

          // Call the highly specific parsing function.
          Map<String, int> parsedHrvSegment = parseHRVData(value);

          // Accumulate the parsed data segment into the overall map.
          _accumulatedHrvData.addAll(parsedHrvSegment);

          log('Parsed HRV segment: $parsedHrvSegment');
        } else {
          // Log packets that are HRV-related but not data segments (e.g., value[1] == 0).
          log('Received non-data HRV packet (value[1]=${value.length > 1 ? value[1] : 'N/A'}): $value');
        }
      }
    }, onDone: () {
      // This callback executes when the stream of notifications is finished.
      log('\n--- All HRV packets processed. Final Accumulated HRV Data ---');
      if (_accumulatedHrvData.isNotEmpty) {
        // Sort the accumulated data by time for consistent display.
        final sortedKeys = _accumulatedHrvData.keys.toList()
          ..sort((a, b) => _compareTimeStrings(a, b));

        for (var key in sortedKeys) {
          print('   Final Accumulated - $key: ${_accumulatedHrvData[key]}');
        }
      } else {
        print('   No HRV data with a valid data packet index was accumulated.');
      }
      log('------------------------------------------------------------');

      log('-------------Accumulated----------------------------------$_accumulatedHrvData');
    }, onError: (error) {
      log('Error during HRV data stream: $error');
    });
  }

// Helper function to compare time strings for sorting.
  int _compareTimeStrings(String time1, String time2) {
    final parts1 = time1.split(':');
    final parts2 = time2.split(':');
    final h1 = int.parse(parts1[0]);
    final m1 = int.parse(parts1[1]);
    final h2 = int.parse(parts2[0]);
    final m2 = int.parse(parts2[1]);

    if (h1 != h2) {
      return h1.compareTo(h2);
    }
    return m1.compareTo(m2);
  }

// --- The highly specific parseHRVData function ---
  /// Parses a list of integer values representing a segment of HRV data
  /// from a single BLE notification packet, applying specific byte mappings
  /// based on the packet index to match the user's desired output.
  ///
  /// [value]: The complete List<int> received from the BLE characteristic notification.
  /// Returns a [Map<String, int>] where keys are time strings ("HH:MM") and
  /// values are the corresponding HRV data points for this packet segment.
  Map<String, int> parseHRVData(List<int> value) {
    final Map<String, int> hrvDataSegment = LinkedHashMap<String, int>();
    final int packetIndex = value[1];

    log("Parsing packet with index: $packetIndex, length: ${value.length}");

    switch (packetIndex) {
      case 0:
        // Packet 0 is typically metadata, not time-series HRV data.
        log("Note: Packet index 0 detected. Skipping data extraction for time-series HRV.");
        break;

      case 1: // Data for 00:00 - 05:30 (12 data points)
        // Original logic, seems consistent with previous requests.
        const int startIndex = 3;
        if (value.length >= startIndex + 12) {
          for (int i = 0; i < 12; i++) {
            final int totalMinutes = (0 * 12 * 30) + (i * 30);
            final int hours = totalMinutes ~/ 60;
            final int minutes = totalMinutes % 60;
            final String timeKey =
                '${hours.toString().padLeft(2, '0')}:${minutes.toString().padLeft(2, '0')}';
            hrvDataSegment[timeKey] = value[startIndex + i];
          }
        } else {
          log("Error: Packet 1 is too short. Expected at least ${startIndex + 12} elements, got ${value.length}.");
        }
        break;

      case 2: // Data for 06:00 - 12:30 (14 data points as per new interpretation)
        // This packet contains data from 06:00 to 11:30 AND 12:00 to 12:30.
        // 06:00 to 11:30 (12 points) starts at value[2]
        const int segment1StartIndex = 2; // For 06:00 to 11:30
        const int segment1Count = 12; // Number of points for 06:00 to 11:30

        if (value.length >= segment1StartIndex + segment1Count) {
          // Parse 06:00 to 11:30
          for (int i = 0; i < segment1Count; i++) {
            final int totalMinutes = (1 * 12 * 30) +
                (i * 30); // Packet 2 starts at 360 minutes (06:00)
            final int hours = totalMinutes ~/ 60;
            final int minutes = totalMinutes % 60;
            final String timeKey =
                '${hours.toString().padLeft(2, '0')}:${minutes.toString().padLeft(2, '0')}';
            hrvDataSegment[timeKey] = value[segment1StartIndex + i];
          }

          // Parse 12:00 and 12:30 from the end of this packet
          // According to user's latest, 12:00 is 32 (value[14]), and 12:30 is 0 (value[15] which is 74 in raw data)
          if (value.length >= 16) {
            // Need value[15] for 12:30
            hrvDataSegment['12:00'] = value[14];
            // For 12:30, if value[15] is 74, we set it to 0 as per user's latest instruction.
            hrvDataSegment['12:30'] = (value[15] == 74) ? 0 : value[15];
          } else {
            log("Warning: Packet 2 is too short to contain 12:00 and 12:30 data. Expected at least 16 elements, got ${value.length}.");
          }
        } else {
          log("Error: Packet 2 is too short for 06:00-11:30 data. Expected at least ${segment1StartIndex + segment1Count} elements, got ${value.length}.");
        }
        break;

      case 3: // Data for 13:00 - 18:30 (12 data points)
        // This packet appears to carry data from 13:00 to 18:30.
        // Based on user's latest input, 13:00 is 38 which is value[3] of this packet.
        // So, data starts effectively from index 3 and takes 12 values.
        const int startIndex = 3;
        if (value.length >= startIndex + 12) {
          for (int i = 0; i < 12; i++) {
            // Calculate time based on effective start 13:00 (780 minutes)
            final int totalMinutes = 780 + (i * 30); // 780 minutes = 13:00
            final int hours = totalMinutes ~/ 60;
            final int minutes = totalMinutes % 60;
            final String timeKey =
                '${hours.toString().padLeft(2, '0')}:${minutes.toString().padLeft(2, '0')}';
            hrvDataSegment[timeKey] = value[startIndex + i];
          }
        } else {
          log("Error: Packet 3 is too short. Expected at least ${startIndex + 12} elements, got ${value.length}.");
        }
        break;

      case 4: // Data for 19:00 - 23:30 (10 data points)
        // This packet appears to carry data from 19:00 to 23:30.
        // Based on user's input, 19:00 is 37 which is value[2] of this packet.
        const int startIndex = 2; // Data starts at value[2]
        const int dataCount =
            10; // Number of data points (19:00 to 23:30 = 10 half-hour intervals)

        if (value.length >= startIndex + dataCount) {
          for (int i = 0; i < dataCount; i++) {
            // Calculate time based on effective start 19:00 (1140 minutes)
            final int totalMinutes = 1140 + (i * 30); // 1140 minutes = 19:00
            final int hours = totalMinutes ~/ 60;
            final int minutes = totalMinutes % 60;
            final String timeKey =
                '${hours.toString().padLeft(2, '0')}:${minutes.toString().padLeft(2, '0')}';
            hrvDataSegment[timeKey] = value[startIndex + i];
          }
        } else {
          log("Error: Packet 4 is too short. Expected at least ${startIndex + dataCount} elements, got ${value.length}.");
        }
        break;

      default:
        log("Warning: Unknown or unhandled packetIndex $packetIndex. Skipping parsing.");
        break;
    }

    return hrvDataSegment;
  }

  // ================= Pretty log helpers =================
  String _repeatDaysToShort(List<bool> days) {
    const labels = ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'];
    final on = <String>[];
    for (int i = 0; i < 7 && i < days.length; i++) {
      if (days[i]) on.add(labels[i]);
    }
    return on.isEmpty ? 'none' : on.join(',');
  }

  String _formatAlarmParsed(Map<String, dynamic> parsed) {
    final data = parsed['data'] as Map?;
    if (data == null) return 'Alarm: <no data>';
    final idx = data['index'];
    final en = data['enabled'];
    final h = data['hour'];
    final m = data['minute'];
    final days = (data['days'] is List) ? List<bool>.from(data['days']) : List<bool>.filled(7, false);
    final time = '${h.toString().padLeft(2, '0')}:${m.toString().padLeft(2, '0')}';
    return 'Alarm[$idx] enabled=$en time=$time repeat=${_repeatDaysToShort(days)}';
  }

  // Step Data of Today
  liveHeartRate() {
    // Today
    // 1. Set up the notification listener ONLY ONCE.
    // This listens for incoming data from the Bluetooth characteristic.
    _setupHeartRateNotificationListener();

    // 2. Start the periodic task to write data to the characteristic.
    // This write operation typically requests the band to send heart rate data.
    // The returned Timer object is stored in _heartRateRepeatingTimer so it can be cancelled later.
    _heartRateRepeatingTimer = _startHeartRatePeriodicWrite();
  }

  void _ensureLiveWorkoutListener() {
    if (_liveWorkoutSubscription != null) return;
    Stream<List<int>>? stream;
    try {
      if (_secondbluetoothCharacteristicNotification.properties.notify) {
        stream = _secondbluetoothCharacteristicNotification.value;
      }
    } catch (_) {}
    if (stream == null) {
      try {
        stream = _bluetoothCharacteristicNotification.value;
      } catch (_) {}
    }
    if (stream == null) return;
    _liveWorkoutSubscription = stream.listen((value) async {
      if (value.isEmpty) return;
      // Delegate vendor 0xBC messages (header and continuation) to SDK
      QCBandSDK.ingestVendorNotification(value);
        // Log vendor ACKs for operate (0x40)
        try {
          if (value.length >= 2 && value[0] == 0xBC && (value[1] & 0xFF) == 0x40) {
            final hex = value.map((e) => e.toRadixString(16).padLeft(2, '0')).join(' ');
            print('Vendor ACK 0x40 ← $hex');
          }
        } catch (_) {}
      final parsed = QCBandSDK.DataParsingWithData(value);
      final dataType = parsed['dataType'];
      if (dataType == 'LiveSportNotify') {
        final data = parsed['dicData'] ?? parsed['data'] ?? parsed['Data'];
        if (data is Map) {
          // Debug: ensure we see updates
          print(
              'Live 0x78 → type=${data['sportType']} dur=${data['durationSec']}s hr=${data['heartRate']} steps=${data['steps']} dist=${data['distance']}m cal=${data['calorie']}');
          setState(() {
            _liveDurationSec = (data['durationSec'] ?? _liveDurationSec) as int;
            _liveHr = (data['heartRate'] ?? _liveHr) as int;
            _liveSteps = (data['steps'] ?? _liveSteps) as int;
            _liveDistance = (data['distance'] ?? _liveDistance) as int;
            _liveCalories = (data['calorie'] ?? _liveCalories) as int;
            if (_liveHr > 0) _hrSamples.add(_liveHr);
          });
        }
      } else if (value[0] == 120 && value.length >= 14) {
        // Fallback: parse 0x78 live frame directly (little-endian for multi-byte fields)
        final sportType = value[1];
        final durationSec = value[2] | (value[3] << 8);
        final hr = value[4];
        final steps = value[5] | (value[6] << 8) | (value[7] << 16);
        final distance = value[8] | (value[9] << 8) | (value[10] << 16);
        final calories = value[11] | (value[12] << 8) | (value[13] << 16);
        print(
            'Live 0x78(raw) → type=$sportType dur=${durationSec}s hr=$hr steps=$steps dist=${distance}m cal=$calories');
        setState(() {
          _selectedSportType = sportType;
          _liveDurationSec = durationSec;
          _liveHr = hr;
          _liveSteps = steps;
          _liveDistance = distance;
          _liveCalories = calories;
          if (hr > 0) _hrSamples.add(hr);
        });
      }

      if (value[0] == QcBandSdkConst.cmdGetRealTimeHeartRate && value.length > 1) {
        final hr = value[1];
        setState(() {
          _liveHr = hr;
          _hrSamples.add(hr);
        });
      }
    });
  }

  // ============ On-device sport controls (vendor serial, 0xBC) ============
  Future<void> _vendorWrite(Uint8List data) async {
    try {
      if (_secondbluetoothCharacteristicWrite != null) {
        await _secondbluetoothCharacteristicWrite.write(data);
      } else if (_bluetoothCharacteristicWrite != null) {
        await _bluetoothCharacteristicWrite!.write(data);
      }
    } catch (e) {
      print('Vendor write error: $e');
    }
  }

  Future<void> _startOnDeviceSport() async {
    final pkt = QCBandSDK.startOnDeviceSport(_selectedSportType);
    setState(() { _vendorStatus = 'Sent START (type=$_selectedSportType)'; });
    await _vendorWrite(pkt);
  }

  Future<void> _pauseOnDeviceSport() async {
    final pkt = QCBandSDK.pauseOnDeviceSport(_selectedSportType);
    setState(() { _vendorStatus = 'Sent PAUSE (type=$_selectedSportType)'; });
    await _vendorWrite(pkt);
  }

  Future<void> _continueOnDeviceSport() async {
    final pkt = QCBandSDK.continueOnDeviceSport(_selectedSportType);
    setState(() { _vendorStatus = 'Sent CONTINUE (type=$_selectedSportType)'; });
    await _vendorWrite(pkt);
  }

  Future<void> _stopOnDeviceSport() async {
    final pkt = QCBandSDK.stopOnDeviceSport(_selectedSportType);
    setState(() { _vendorStatus = 'Sent STOP (type=$_selectedSportType)'; });
    await _vendorWrite(pkt);
  }

  // ============ Sport+ sync (summary + details) ============
  int _lastSpSyncTs = 0;
  List<Map<String, dynamic>> _lastSpSummaries = [];
  List<String> _spLog = [];
  // Render helper: format seconds to hh:mm:ss
  String _fmtSeconds(int s) {
    final h = s ~/ 3600;
    final m = (s % 3600) ~/ 60;
    final sec = s % 60;
    if (h > 0) return '${h}h ${m}m ${sec}s';
    if (m > 0) return '${m}m ${sec}s';
    return '${sec}s';
  }
  String _fmtUnix(int ts) {
    if (ts <= 0) return '-';
    final dt = DateTime.fromMillisecondsSinceEpoch(ts * 1000, isUtc: true).toLocal();
    return '${dt.year}-${dt.month.toString().padLeft(2,'0')}-${dt.day.toString().padLeft(2,'0')} ${dt.hour.toString().padLeft(2,'0')}:${dt.minute.toString().padLeft(2,'0')}';
  }

  void _appendSpLog(String s) {
    setState(() {
      _spLog.insert(0, s);
      if (_spLog.length > 200) _spLog.removeLast();
    });
  }

  Future<void> _syncSportPlus() async {
    // Register callbacks and write summary request
    QCBandSDK.getSportPlusSummaryFromTimestamp(_lastSpSyncTs, (summaries) async {
      setState(() {
        _vendorStatus = 'SP+ summary received: ${summaries.length} sessions';
        _lastSpSummaries = List<Map<String, dynamic>>.from(summaries);
      });
      if (summaries.isEmpty) return;
      final latest = summaries.last;
      // register details callback then request details
      QCBandSDK.getSportPlusDetailsFor(
        (latest['sportType'] ?? 0) as int,
        (latest['startTime'] ?? 0) as int,
        (summary, hrSeries, sampleSecond) {
          _appendSpLog('SP+ details: samples=${hrSeries.length} sampleSecond=$sampleSecond');
          setState(() {
            _vendorStatus = 'SP+ details received: ${hrSeries.length} samples';
          });
        },
      );
      await _vendorWrite(QCBandSDK.buildSportPlusDetailsReq(latest['sportType'] ?? 0, latest['startTime'] ?? 0));
    });
    await _vendorWrite(QCBandSDK.buildSportPlusSummaryReq(_lastSpSyncTs));
  }

  void _setupHeartRateNotificationListener() {
    // If there's an existing subscription, cancel it to prevent multiple listeners
    if (_heartRateNotificationSubscription != null) {
      _heartRateNotificationSubscription!.cancel();
      _heartRateNotificationSubscription = null;
      print('DEBUG: Cancelled previous heart rate notification subscription.');
    }

    print('DEBUG: Setting up heart rate notification listener...');
    _heartRateNotificationSubscription =
        _bluetoothCharacteristicNotification.value.listen(
      (value) {
        // Handle the received value (List<int>)
        // print('Received notification: $value'); // Uncomment for detailed debug
        if (value.isNotEmpty &&
            value[0] == QcBandSdkConst.cmdGetRealTimeHeartRate) {
          log('Received HRV: ${value[1]}'); // Assuming value[1] is the actual HR value
        }
      },
      onError: (error) {
        print('ERROR: Heart rate notification stream error: $error');
      },
      onDone: () {
        print('DEBUG: Heart rate notification stream completed.');
        _heartRateNotificationSubscription =
            null; // Clear reference when stream closes
      },
    );
  }

  Widget _buildOnDeviceSportControls() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const SizedBox(height: 8),
        if (_vendorStatus.isNotEmpty)
          Padding(
            padding: const EdgeInsets.only(bottom: 8.0),
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 8),
              decoration: BoxDecoration(
                color: Colors.amberAccent,
                borderRadius: BorderRadius.circular(8),
                border: Border.all(color: Colors.amber.shade700, width: 1),
              ),
              child: Row(
                children: [
                  Icon(Icons.info_outline, size: 18, color: Colors.black.withOpacity(0.85)),
                  const SizedBox(width: 8),
                  Expanded(
                    child: Text(
                      _vendorStatus,
                      style: const TextStyle(fontSize: 14, fontWeight: FontWeight.w600, color: Colors.black),
                    ),
                  ),
                ],
              ),
            ),
          ),
        Wrap(
          spacing: 8,
          runSpacing: 8,
          children: [
            ElevatedButton(
              onPressed: _startOnDeviceSport,
              child: const Text('Start On-Device Sport'),
            ),
            ElevatedButton(
              onPressed: _pauseOnDeviceSport,
              child: const Text('Pause On-Device Sport'),
            ),
            ElevatedButton(
              onPressed: _continueOnDeviceSport,
              child: const Text('Continue On-Device Sport'),
            ),
            ElevatedButton(
              onPressed: _stopOnDeviceSport,
              child: const Text('Stop On-Device Sport'),
            ),
          ],
        ),
        const SizedBox(height: 8),
        ElevatedButton(
          onPressed: _syncSportPlus,
          child: const Text('Sync Workouts (Sport+)'),
        ),
        const SizedBox(height: 8),
        Wrap(
          spacing: 8,
          runSpacing: 8,
          children: [
            ElevatedButton(
              onPressed: () => _runVariantSweep(topOnly: true),
              child: const Text('Run On-device Sport Tester (Top Variants)'),
            ),
            ElevatedButton(
              onPressed: () => _runVariantSweep(topOnly: false),
              child: const Text('Run Full Variant Sweep'),
            ),
            ElevatedButton(
              onPressed: () => _runVariantSweep(topOnly: false),
              child: const Text('Run Remaining Variants'),
            ),
            ElevatedButton(
              onPressed: () => _runSpecificVariants(const [6, 7, 8, 9]),
              child: const Text('Run Timestamp/Padding Variants (C/D)'),
            ),
            ElevatedButton(
              onPressed: () => _runSpecificVariantsWithType(const [2, 3, 0, 1, 4, 5], 12),
              child: const Text('Run Top Variants (APP_RUN=12)'),
            ),
            ElevatedButton(
              onPressed: _runZeroBasedTop,
              child: const Text('Run Top Variants (0-based states)'),
            ),
            ElevatedButton(
              onPressed: () => _runB1WithFinalize(runSeconds: 190),
              child: const Text('Run B1 with finalize (3 min)'),
            ),
          ],
        ),
        const SizedBox(height: 8),
        if (_lastSpSummaries.isNotEmpty)
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
                Text(
                  'Sport+ Sessions (${_lastSpSummaries.length})',
                  style: TextStyle(
                    fontWeight: FontWeight.w700,
                    fontSize: 14,
                    color: Colors.blue.shade900,
                  ),
                ),
                const SizedBox(height: 6),
                ..._lastSpSummaries.take(10).map((s) {
                  final type = s['sportType'] ?? 0;
                  final start = _fmtUnix((s['startTime'] ?? 0) as int);
                  final dur = _fmtSeconds((s['duration'] ?? 0) as int);
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
                    child: Text(
                      '• type=$type  start=$start  dur=$dur  dist=${dist}m  cal=$cal$hrStr',
                      style: const TextStyle(fontSize: 13, color: Colors.black),
                    ),
                  );
                }).toList(),
              ],
            ),
          ),
        const SizedBox(height: 8),
        if (_spLog.isNotEmpty)
          Container(
            padding: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              color: const Color(0xFFF7F7F7),
              borderRadius: BorderRadius.circular(8),
            ),
            height: 140,
            child: ListView.builder(
              itemCount: _spLog.length,
              itemBuilder: (_, i) => Text(_spLog[i], style: const TextStyle(fontSize: 12)),
            ),
          ),
      ],
    );
  }

  /// This function starts the periodic write operation to the Bluetooth characteristic.
  /// It returns the Timer object, which is crucial for stopping it later.
  Timer _startHeartRatePeriodicWrite() {
    const intervalDuration = Duration(seconds: 10);

    print(
        'Scheduling heart rate data write every ${intervalDuration.inSeconds} seconds...');

    // Timer.periodic creates a repeating timer.
    // The 'await' call for writing data is now inside this periodic callback,
    // ensuring it happens every 20 seconds.
    return Timer.periodic(intervalDuration, (Timer timer) async {
      print('---');
      print('Performing periodic heart rate data write at ${DateTime.now()}');
      await _bluetoothCharacteristicWrite.write(
        QCBandSDK.liveHeartData(1), // This sends the command to the band
      );
      print('---');
    });
  }

  /// This function stops both the repeating write task and the notification listener.
  void stopHeartHeartRepeating() {
    // Stop the periodic timer if it's active
    if (_heartRateRepeatingTimer != null &&
        _heartRateRepeatingTimer!.isActive) {
      _heartRateRepeatingTimer!.cancel();
      _heartRateRepeatingTimer = null; // Clear the reference after cancelling
      print('Repeating heart rate data write stopped at ${DateTime.now()}');
    } else {
      print('No active repeating heart rate data write to stop.');
    }

    // Also, stop the notification listener to clean up resources
    if (_heartRateNotificationSubscription != null) {
      _heartRateNotificationSubscription!.cancel();
      _heartRateNotificationSubscription = null; // Clear the reference
      print('Heart rate notification listener stopped.');
    } else {
      print('No active heart rate notification listener to stop.');
    }
  }

  // Clean method to get sleep data for all days with timestamps
  Future<void> getSleepDataForAllDays() async {
    print("🌙 Fetching sleep data for all available days...");
    
    // Fetch sleep data for the last 7 days (0 = today, 6 = 6 days ago)
    for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
      await _fetchAndDisplaySleepData(dayIndex);
      
      // Small delay between requests to avoid overwhelming the device
      await Future.delayed(const Duration(milliseconds: 300));
    }
    
    print("✅ Sleep data collection complete!");
  }

  // Fetch and display sleep data for a specific day
  Future<void> _fetchAndDisplaySleepData(int dayIndex) async {
    try {
      List<int> sleepData = await _requestSleepDataFromDevice(dayIndex);
      
      if (sleepData.isNotEmpty && sleepData.length >= 13) {
        final parser = SleepParser(sleepData, currentIndex: dayIndex);
        final summary = parser.getSleepSummaryWithTimestamps();
        
        _displaySleepSummary(dayIndex, summary);
      } else {
        print("Day $dayIndex: No sleep data available");
      }
    } catch (e) {
      print("Error fetching sleep data for day $dayIndex: $e");
    }
  }

  // Display clean sleep summary with timestamps
  void _displaySleepSummary(int dayIndex, SleepSummary summary) {
    String dayLabel = dayIndex == 0 ? "Today" : "${dayIndex} day${dayIndex > 1 ? 's' : ''} ago";
    
    print("\n📊 Sleep Summary - $dayLabel (Day $dayIndex):");
    
    if (summary.bedTime != null && summary.wakeTime != null) {
      print("  🛏️  Bed Time: ${_formatTime(summary.bedTime!)}");
      print("  🌅 Wake Time: ${_formatTime(summary.wakeTime!)}");
      print("  ⏱️  Total Sleep: ${summary.durations['totalDuration']} minutes");
      
      print("\n  📈 Sleep Stage Breakdown:");
      print("    💤 Deep Sleep: ${summary.durations['deepSleep']} min");
      print("    😴 Light Sleep: ${summary.durations['lightSleep']} min");
      print("    👁️  REM Sleep: ${summary.durations['rapidEyeMovement']} min");
      print("    😵 Awake: ${summary.durations['awake']} min");
      
      print("  🔄 Total Segments: ${summary.segments.length}");
      
      // Show first few segments as example
      if (summary.segments.isNotEmpty) {
        print("\n  📊 Sleep Timeline (first 5 segments):");
        int maxSegments = summary.segments.length > 5 ? 5 : summary.segments.length;
        
        for (int i = 0; i < maxSegments; i++) {
          final segment = summary.segments[i];
          String stageType = _getSleepStageDisplayName(segment.stageType);
          int duration = segment.segmentEnd.difference(segment.segmentStart).inMinutes;
          
          print("    ${i + 1}. ${_formatTime(segment.segmentStart)} → ${_formatTime(segment.segmentEnd)} ($duration min) - $stageType");
        }
        
        if (summary.segments.length > 5) {
          print("    ... and ${summary.segments.length - 5} more segments");
        }
      }
    } else {
      print("  ❌ No valid sleep times available");
    }
    
    print("  ${'-' * 50}");
  }

  // Helper to format time consistently
  String _formatTime(DateTime time) {
    return "${time.hour.toString().padLeft(2, '0')}:${time.minute.toString().padLeft(2, '0')}";
  }

  // Helper to get display name for sleep stages
  String _getSleepStageDisplayName(int stageType) {
    switch (stageType) {
      case 1: return "Deep Sleep";
      case 2: return "Light Sleep"; 
      case 3: return "Awake";
      case 4: return "REM Sleep";
      case 5: return "Unknown";
      default: return "Unknown";
    }
  }

  // Request sleep data from the device for a specific day
  Future<List<int>> _requestSleepDataFromDevice(int dayIndex) async {
    final completer = Completer<List<int>>();
    List<int>? receivedData;
    Timer? timeoutTimer;

    // Set up listener for the response
    final subscription = _secondbluetoothCharacteristicNotification.value.listen((value) {
      if (value.isNotEmpty && value[1] == QcBandSdkConst.getSleepData) {
        // Valid sleep data response received
        if (_isValidSleepDataPacket(value)) {
          receivedData = List<int>.from(value);
          
          // Use a short delay to allow for any additional packets
          timeoutTimer?.cancel();
          timeoutTimer = Timer(const Duration(milliseconds: 200), () {
            if (!completer.isCompleted && receivedData != null) {
              completer.complete(receivedData!);
            }
          });
        }
      }
    });

    // Send the command to request sleep data
    await _secondbluetoothCharacteristicWrite.write(QCBandSDK.getSleepData(dayIndex));

    // Wait for response with timeout
    final response = await completer.future.timeout(
      const Duration(seconds: 5),
      onTimeout: () {
        timeoutTimer?.cancel();
        return receivedData ?? [];
      },
    );

    subscription.cancel();
    timeoutTimer?.cancel();

    return response;
  }

  // Basic validation for sleep data packets
  bool _isValidSleepDataPacket(List<int> data) {
    // Basic checks: minimum length and correct command header
    return data.length >= 10 && 
           data[0] == 188 && 
           (data[1] == 39 || data[1] == 68);
  }

  // Legacy method - keeping for backward compatibility but simplified
  sleepDetailData() async {
    print("⚠️  sleepDetailData() is deprecated. Use getSleepDataForAllDays() instead.");
    await getSleepDataForAllDays();
  }

  historicalSleepData() async {
    print("historicalSleepData is deprecated and its logic is now in getSleepDataForAllDays.");
  }

  deviceTimeSet() async {
    // Tested it as document in the response we should know whether some functionality are supported or not.
    await _bluetoothCharacteristicWrite.write(
      QCBandSDK.setDeviceTime(1),
    );
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      if (value.isNotEmpty) {
        // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
        // print(recievedHRVData);
        // log('Received Sleep: $value');
      }
    });
  }

// --- START Helper Functions ---

// Function to construct the full BLE packet

  getBloodOxygenDevice() async {
    // Step 1. Construct the Data Payload [ Done ] [Moved to qc_band_sdk_for_flutter]
    // a . The data payload for this command is a single byte with value -1.
    // b. / Construct the full, correctly formatted command packet
    // This packet will be: [0xBC, 0x2A, 0x01, 0x00, <CRC_LSB>, <CRC_MSB>, 0xFF]

    // Send the constructed packet to the write characteristic
    await _secondbluetoothCharacteristicWrite.write(
      QCBandSDK.getBloodOxygen(),
    );
    print(
        'Sent Blood Oxygen request: ${QCBandSDK.getBloodOxygen().map((e) => e.toRadixString(16).padLeft(2, '0')).join(' ')}');

    _secondbluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print(
          'Received notification: ${QcBandSdkConst.serialPortNotify} \n ${value.length}');
      if (value.isNotEmpty) {
        List<BloodOxygenEntity> parsedData =
            parseBloodOxygenData(Uint8List.fromList(value));
        parsedData.forEach((entity) {
          print(entity.toString());
        });
        // print(recievedHRVData);
        // log('Received Sleep: $value  ');
      }
    });
  }

  setBloodPressureDeviceTo30Min() async {
    // Confirmation needed is 24 is the day
    List<int> command = [12, 2, 1, 0, 0, 24, 0, 30, 0, 0, 0, 0, 0, 0, 0, 69];
    try {
      await _bluetoothCharacteristicWrite!.write(
        command, // Set to true if your device doesn't send a write response
      );
      print('Command Runned Successfully -------');
    } catch (e) {
      print("Error sending pressure request: $e");
    }
  }

  getBloodPressureDevice() async {
    // Example for Parsing the data
    int userAge = 29;
    // List<int> encodedBloodPressure = [77];
    // BloodPressureBle parser = BloodPressureBle();
    // var result = parser.parseData(encodedBloodPressure, userAge);
    // log('This is the Response of the Blood Pressure $result');

    // Instantiate your response parser class
    // final ReadBlePressureRsp pressureResponseParser = ReadBlePressureRsp();

// Create a list to hold the parsed pressure readings

    List<int> encodedBloodPressure = [];

    int offset = 0;
    if (_bluetoothCharacteristicWrite == null) {
      print("Write characteristic not initialized.");
      return;
    }

    // Ensure offset is within the valid range (0-6)
    if (offset < 0 || offset > 6) {
      print("Invalid offset. Must be between 0 and 6.");
      return;
    }

    final command = QCBandSDK.getBloodPressure(offset);
    print(
        "Sending command: ${command.map((e) => e.toRadixString(16).padLeft(2, '0')).join(' ')}");

    try {
      await _bluetoothCharacteristicWrite!.write(
        command, // Set to true if your device doesn't send a write response
      );
      print("Pressure request sent successfully for offset: $offset");
    } catch (e) {
      print("Error sending pressure request: $e");
    }
    _bluetoothCharacteristicNotification.value.listen(
      (value) {
        // Handle the received value (List<int>
        log('Received notification: $value');
        if (value.isNotEmpty && value[0] == 13 && value[1] == 0) {
          log('Command Header Log $value}');
        } else if (value.isNotEmpty && value[0] == 13 && value[1] == 1) {
          log('Command Body Log $value}');
          for (var i = 0; i < value.length - 1; i++) {
            if (i > 1) {
              encodedBloodPressure.add(value[i]);
            }
          }
          log('Encoded Blood Pressure: $encodedBloodPressure');
          BloodPressureBle parser = BloodPressureBle();
          var result = parser.parseData(encodedBloodPressure, userAge);
          log('This is the Response of the Blood Pressure $result');
          encodedBloodPressure = [];
        } else if (value.isNotEmpty) {
          log('No Blood Pressure Record Found all are synchronized');
        }
      },
    );
  }

  startWorkOut() async {
    final List<int> command =
        QCBandSDK.startWorkOutWithType(sportType: _selectedSportType);
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
      // Start real-time HR stream for richer HR samples
      await _bluetoothCharacteristicWrite
          .write(QCBandSDK.liveHeartData(QcBandSdkConst.ACTION_START));
      // Note: Do NOT send 0x5A heartbeat in phone-sport mode; device handles session autonomously
    } catch (e) {
      print("Error sending pressure request: $e");
    }
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      if (value.isNotEmpty) {
        // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
        // print(recievedHRVData);
        log('Received notification After isNotEmpty Check : $value}');
      }
    });
  }

  pauseWorkOut() async {
    final List<int> command =
        QCBandSDK.pauseWorkOutWithType(sportType: _selectedSportType);
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
    } catch (e) {
      print("Error sending pressure request: $e");
    }
    // Nothing extra for pause
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      if (value.isNotEmpty) {
        // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
        // print(recievedHRVData);
        log('Received notification After isNotEmpty Check : $value}');
      }
    });
  }

  continueWorkOut() async {
    final List<int> command =
        QCBandSDK.continueWorkOutWithType(sportType: _selectedSportType);
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
    } catch (e) {
      print("Error sending pressure request: $e");
    }
    // Nothing extra for continue
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      if (value.isNotEmpty) {
        // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
        // print(recievedHRVData);
        log('Received notification After isNotEmpty Check : $value}');
      }
    });
  }

  stopWorkOut() async {
    final List<int> command =
        QCBandSDK.stopWorkOutWithType(sportType: _selectedSportType);
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
      // Stop real-time HR stream
      await _bluetoothCharacteristicWrite
          .write(QCBandSDK.liveHeartData(QcBandSdkConst.ACTION_STOP));
      _hrKeepAliveTimer?.cancel();
      _hrKeepAliveTimer = null;
      // No sport heartbeat in phone-sport mode
    } catch (e) {
      print("Error sending pressure request: $e");
    }
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      if (value.isNotEmpty) {
        // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
        // print(recievedHRVData);
        log('Received notification After isNotEmpty Check : $value}');
      }
    });
  }

  Future<Map<String, dynamic>> _fetchAlarmByIndex(int index) async {
    final completer = Completer<Map<String, dynamic>>();
    late final StreamSubscription<List<int>> sub;
    sub = _bluetoothCharacteristicNotification.value.listen((value) {
      if (value.isNotEmpty && value[0] == QcBandSdkConst.cmdGetAlarmClockInt && value.length > 1 && value[1] == index) {
        try {
          final parsed = QCBandSDK.DataParsingWithData(value);
          completer.complete(Map<String, dynamic>.from(parsed));
        } catch (e) {
          completer.complete({});
        } finally {
          sub.cancel();
        }
      }
    });
    try {
      final cmd = QCBandSDK.buildGetAlarmClassic(index);
      log('[ALARM] → READ idx=$index  cmd=${cmd.map((e)=>e.toRadixString(16).padLeft(2,'0')).join(' ')}');
      await _bluetoothCharacteristicWrite.write(cmd);
    } catch (e) {
      sub.cancel();
      rethrow;
    }
    return completer.future.timeout(const Duration(seconds: 3), onTimeout: () {
      sub.cancel();
      return {};
    });
  }

  getAlarms() async {
    final List<Map<String, dynamic>> results = [];
    for (int i = 0; i <= 4; i++) {
      try {
        final r = await _fetchAlarmByIndex(i);
        if (r.isNotEmpty) results.add(r);
      } catch (_) {}
      await Future.delayed(const Duration(milliseconds: 150));
    }
    if (results.isEmpty) {
      log('[ALARM] No alarms returned');
    } else {
      for (final r in results) {
        log('[ALARM] ← ${_formatAlarmParsed(r)}');
      }
    }
  }

  setSampleAlarm() async {
    final alarm = Alarm(
      index: 0,
      enabled: true,
      hour: 7,
      minute: 30,
      repeatDays: const [false, true, true, true, true, true, false], // Mon-Fri
    );
    final cmd = QCBandSDK.buildSetAlarmClassic(alarm);
    log('[ALARM] → SET ${alarm.index} ${alarm.hour.toString().padLeft(2,'0')}:${alarm.minute.toString().padLeft(2,'0')} repeat=${_repeatDaysToShort(alarm.repeatDays)}  cmd=${cmd.map((e)=>e.toRadixString(16).padLeft(2,'0')).join(' ')}');
    try {
      await _bluetoothCharacteristicWrite.write(cmd);
      Snackbar.show(ABC.c, "Set alarm sent", success: true);
    } catch (e) {
      Snackbar.show(ABC.c, prettyException("Set alarm error:", e), success: false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return ScaffoldMessenger(
      key: Snackbar.snackBarKeyC,
      child: Scaffold(
        appBar: AppBar(
          title: Text(widget.device.platformName),
          actions: [buildConnectButton(context)],
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              buildRemoteId(context),
              ListTile(
                leading: buildRssiTile(context),
                title: Text(
                    'Device is ${_connectionState.toString().split('.')[1]}.'),
                trailing: buildGetServices(context),
              ),
              buildMtuTile(context),
              ..._buildServiceTiles(context, widget.device),
              TextButton(
                  onPressed: () {
                    // Notify Listenner of the Command
                    getDeviceBattery();
                    //Send Command

                    // Parse Response
                    print('Battery Button Pressed');
                  },
                  child: Text('Battery Data')),
              TextButton(
                  onPressed: () {
                    // Notify Listenner of the Command
                    // getDeviceBattery();
                    //Send Command
                    getHRData();
                    // Parse Response
                  },
                  child: Text('Heart Rate Setting')),
              TextButton(
                  onPressed: () {
                    // Notify Listenner of the Command
                    // getDeviceBattery();
                    //Send Command
                    findDevice();
                    // Parse Response
                  },
                  child: Text('Find Device ')),
              // Quick link to Sports Monitor page
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 12.0, vertical: 6.0),
                child: ElevatedButton(
                  onPressed: () {
                    Navigator.of(context).push(MaterialPageRoute(
                      builder: (_) => SportsMonitorPage(
                        vendorNotify: _secondbluetoothCharacteristicNotification,
                        vendorWrite: _secondbluetoothCharacteristicWrite,
                        standardNotify: _bluetoothCharacteristicNotification,
                        standardWrite: _bluetoothCharacteristicWrite,
                        hrNotify: _hrMeasurementChar,
                        rscNotify: _rscMeasurementChar,
                        initialSportType: _selectedSportType,
                      ),
                    ));
                  },
                  child: const Text('Open Sports Monitor'),
                ),
              ),
              // On-device sport and Sport+ controls
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 12.0, vertical: 8.0),
                child: _buildOnDeviceSportControls(),
              ),
              // TextButton(
              //     onPressed: () {
              //       // Notify Listenner of the Command
              //       // getDeviceBattery();
              //       //Send Command
              //       runDeviceCalibaration();
              //       // Parse Response
              //     },
              //     child: Text('Device Callibaration')),
              TextButton(
                onPressed: () {
                  // Notify Listenner of the Command
                  // getDeviceBattery();
                  //Send Command
                  stepData();
                  // Parse Response
                },
                child: Text('Device Step Data'),
              ),
              TextButton(
                  onPressed: () {
                    // Notify Listenner of the Command
                    // getDeviceBattery();
                    //Send Command
                    deviceDetailStep();
                    // Parse Response
                  },
                  child: Text('Device Details Step Data')),
              TextButton(
                  onPressed: () {
                    // Notify Listenner of the Command
                    // getDeviceBattery();

                    //Send Command

                    getHRData();
                    // Parse Response
                  },
                  child: Text('heart rate data')),
              TextButton(
                onPressed: () {
                  // Notify Listenner of the Command
                  // getDeviceBattery();
                  //Send Command
                  hrvDetails();
                  // Parse Response
                },
                child: Text('HRV Details'),
              ),
              TextButton(
                onPressed: () {
                  // Notify Listenner of the Command
                  // getDeviceBattery();
                  //Send Command
                  TODO: // Working Need Work on Parsing
                  liveHeartRate();
                  // Parse Response
                },
                child: Text('Live Heart Rate'),
              ),
              TextButton(
                onPressed: () {
                  stopHeartHeartRepeating();
                  // Parse Response
                },
                child: Text('Stop Heart Rate'),
              ),
              TextButton(
                onPressed: () {
                  getSleepDataForAllDays();
                },
                child: Text('Get Sleep Data (All Days)'),
              ),
              TextButton(
                onPressed: () {
                  // TODO: // Get Blood Oxygen

                  getBloodOxygenDevice();
                  // Steps to Complete
                  // 1. BCD Conversion Helper [Done] Implemented on the resolve_util.dart
                  // 2. Language Mapping [Done] Implemented on the resolve_util.dart
                  // 3. Constructing the Payload [Done] Implemented in qc_band_sdk_for_flutter
                  // 4. Equivalent Write Operation in flutter_blue_plus
                },
                child: Text('Get BloodOxygen History'),
              ),
              TextButton(
                onPressed: () {
                  // Steps to Complete
                  // 1. Same as liveHeartData Function
                  setBloodPressureDeviceTo30Min();
                },
                child: Text('Set BloodPressure'),
              ),
              TextButton(
                onPressed: () {
                  // Steps to Complete
                  // 1. Same as liveHeartData Function
                  getBloodPressureDevice();
                },
                child: Text('Get BloodPressure History'),
              ),
              TextButton(
                onPressed: () {
                  // Notify Listenner of the Command
                  // getDeviceBattery();
                  //Send Command
                  // TODO: // Working Need Work on Parsing
                  // sleepDetailData();
                  // Parse Response
                },
                child: Text('Get Device Details'),
              ),
              TextButton(
                onPressed: () {
                  // Steps to Complete
                  // 1. BCD Conversion Helper [Done] Implemented on the resolve_util.dart
                  // 2. Language Mapping [Done] Implemented on the resolve_util.dart
                  // 3. Constructing the Payload [Done] Implemented in qc_band_sdk_for_flutter
                  // 4. Equivalent Write Operation in flutter_blue_plus
                  deviceTimeSet();
                },
                child: Text('Set Time'),
              ),
              TextButton(
                onPressed: getAlarms,
                child: Text('Get Alarm'),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 12.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(children: [
                      const Text('Alarm Index:'),
                      const SizedBox(width: 8),
                      DropdownButton<int>(
                        value: _alarmIndex,
                        items: const [
                          DropdownMenuItem(value: 0, child: Text('0')),
                          DropdownMenuItem(value: 1, child: Text('1')),
                          DropdownMenuItem(value: 2, child: Text('2')),
                          DropdownMenuItem(value: 3, child: Text('3')),
                          DropdownMenuItem(value: 4, child: Text('4')),
                        ],
                        onChanged: (v) => setState(() => _alarmIndex = v ?? 0),
                      ),
                      const SizedBox(width: 16),
                      Text('Time: ${_alarmTime.format(context)}'),
                      const SizedBox(width: 8),
                      TextButton(onPressed: _pickAlarmTime, child: const Text('Pick Time')),
                    ]),
                    const SizedBox(height: 8),
                    Wrap(
                      spacing: 8,
                      children: List.generate(7, (i) {
                        const labels = ['Su','Mo','Tu','We','Th','Fr','Sa'];
                        return FilterChip(
                          label: Text(labels[i]),
                          selected: _alarmRepeat[i],
                          onSelected: (sel) => setState(() => _alarmRepeat[i] = sel),
                        );
                      }),
                    ),
                    const SizedBox(height: 8),
                    ElevatedButton(
                      onPressed: () async {
                        final alarm = Alarm(
                          index: _alarmIndex,
                          enabled: true,
                          hour: _alarmTime.hour,
                          minute: _alarmTime.minute,
                          repeatDays: List<bool>.from(_alarmRepeat),
                        );
                        final cmd = QCBandSDK.buildSetAlarmClassic(alarm);
                        log('[ALARM] → SET ${alarm.index} ${alarm.hour.toString().padLeft(2,'0')}:${alarm.minute.toString().padLeft(2,'0')} repeat=${_repeatDaysToShort(alarm.repeatDays)}  cmd=${cmd.map((e)=>e.toRadixString(16).padLeft(2,'0')).join(' ')}');
                        try {
                          await _bluetoothCharacteristicWrite.write(cmd);
                          Snackbar.show(ABC.c, 'Set alarm sent', success: true);
                        } catch (e) {
                          Snackbar.show(ABC.c, prettyException('Set alarm error:', e), success: false);
                        }
                      },
                      child: const Text('Set Alarm'),
                    ),
                  ],
                ),
              ),
              TextButton(
                onPressed: startWorkOut,
                child: Text('Start WorkOut'),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 12.0),
                child: Row(
                  children: [
                    const Text('Sport Type:'),
                    const SizedBox(width: 8),
                    DropdownButton<int>(
                      value: _selectedSportType,
                      items: const [
                        DropdownMenuItem(value: 4, child: Text('Walk')),
                        DropdownMenuItem(value: 7, child: Text('Run')),
                        DropdownMenuItem(value: 8, child: Text('Hike')),
                        DropdownMenuItem(value: 9, child: Text('Cycling')),
                        DropdownMenuItem(value: 10, child: Text('Other')),
                      ],
                      onChanged: (v) => setState(() => _selectedSportType = v ?? 7),
                    ),
                  ],
                ),
              ),
              TextButton(
                onPressed: pauseWorkOut,
                child: Text('Pause WorkOut'),
              ),
              TextButton(
                onPressed: continueWorkOut,
                child: Text('Continue WorkOut'),
              ),
              TextButton(
                onPressed: stopWorkOut,
                child: Text('Stop WorkOut'),
              ),
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('Live Duration: ${_liveDurationSec}s'),
                    Text('HR: $_liveHr bpm  Steps: $_liveSteps  Dist: ${_liveDistance}m  Cal: $_liveCalories'),
                    Text('HR samples captured: ${_hrSamples.length}')
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  // ================= Variant sweep (on-device sport 0x40) =================
  Uint8List _buildOp40Packet(int state, int sportType, int variantIndex) {
    final nowSec = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000;
    Uint8List ts4() {
      final b = ByteData(4)..setUint32(0, nowSec, Endian.little);
      return b.buffer.asUint8List();
    }
    List<int> p;
    switch (variantIndex) {
      case 0: // A1: [state, type]
        p = [state & 0xFF, sportType & 0xFF];
        break;
      case 1: // A2: [type, state]
        p = [sportType & 0xFF, state & 0xFF];
        break;
      case 2: // B1: [state, type, 0x01]
        p = [state & 0xFF, sportType & 0xFF, 0x01];
        break;
      case 3: // B2: [state, type, 0x00]
        p = [state & 0xFF, sportType & 0xFF, 0x00];
        break;
      case 4: // B3: [type, state, 0x01]
        p = [sportType & 0xFF, state & 0xFF, 0x01];
        break;
      case 5: // B4: [type, state, 0x00]
        p = [sportType & 0xFF, state & 0xFF, 0x00];
        break;
      case 6: // C1: [state, type, tsLE(4), 0x01]
        p = [state & 0xFF, sportType & 0xFF, ...ts4(), 0x01];
        break;
      case 7: // C2: [state, type, 0x01, tsLE(4)]
        p = [state & 0xFF, sportType & 0xFF, 0x01, ...ts4()];
        break;
      case 8: // D1: [state, type, 0x01, 0x00, 0x00, 0x00]
        p = [state & 0xFF, sportType & 0xFF, 0x01, 0x00, 0x00, 0x00];
        break;
      case 9: // D2: [type, state, 0x01, 0x00, 0x00, 0x00]
        p = [sportType & 0xFF, state & 0xFF, 0x01, 0x00, 0x00, 0x00];
        break;
      default:
        p = [state & 0xFF, sportType & 0xFF, 0x01];
        break;
    }
    final payload = Uint8List.fromList(p);
    final pkt = QCBandSDK.buildVendorPacket(0x40, payload);
    final tag = _variantLabel(variantIndex);
    print('op40 $tag → ${pkt.map((e) => e.toRadixString(16).padLeft(2, '0')).join(' ')}');
    return pkt;
  }

  String _variantLabel(int idx) {
    const labels = [
      'A1[state,type]',
      'A2[type,state]',
      'B1[state,type,01]',
      'B2[state,type,00]',
      'B3[type,state,01]',
      'B4[type,state,00]',
      'C1[state,type,ts,01]',
      'C2[state,type,01,ts]',
      'D1[state,type,01,00,00,00]',
      'D2[type,state,01,00,00,00]',
    ];
    if (idx >= 0 && idx < labels.length) return labels[idx];
    return 'var$idx';
  }

  Future<Set<String>> _getSummaryIdsSince(int cutoffTs) async {
    final comp = Completer<Set<String>>();
    void onResult(List<Map<String, dynamic>> summaries) {
      final ids = <String>{};
      for (final s in summaries) {
        final st = (s['startTime'] ?? 0) as int;
        final ty = (s['sportType'] ?? 0) as int;
        if (st >= cutoffTs) {
          ids.add('$ty-$st');
        }
      }
      if (!comp.isCompleted) comp.complete(ids);
    }
    QCBandSDK.getSportPlusSummaryFromTimestamp(cutoffTs, onResult);
    await _vendorWrite(QCBandSDK.buildSportPlusSummaryReq(cutoffTs));
    try {
      return await comp.future.timeout(const Duration(seconds: 6));
    } catch (_) {
      return <String>{};
    }
  }

  Future<bool> _testSingleVariant(int variantIndex, {int runSeconds = 75, int? overrideSportType}) async {
    final type = overrideSportType ?? _selectedSportType;
    final cutoff = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000 - 1800; // 30 min window
    final before = await _getSummaryIdsSince(cutoff);

    setState(() { _vendorStatus = 'Variant ${_variantLabel(variantIndex)} → START (type=$type)'; });
    await _vendorWrite(_buildOp40Packet(QcBandSdkConst.ACTION_START, type, variantIndex));
    await Future.delayed(Duration(seconds: runSeconds));
    setState(() { _vendorStatus = 'Variant ${_variantLabel(variantIndex)} → STOP (type=$type)'; });
    await _vendorWrite(_buildOp40Packet(QcBandSdkConst.ACTION_STOP, type, variantIndex));

    // Give device a moment to finalize, then fetch summaries
    await Future.delayed(const Duration(seconds: 12));
    final after = await _getSummaryIdsSince(cutoff);
    final diff = after.difference(before);
    final ok = diff.isNotEmpty;
    print('Variant ${_variantLabel(variantIndex)} result → ${ok ? 'SUCCESS' : 'NO RECORD'} (new=${diff.length})');
    setState(() { _vendorStatus = 'Variant ${_variantLabel(variantIndex)} → ${ok ? 'SUCCESS' : 'No record'}'; });
    return ok;
  }

  Future<void> _runVariantSweep({required bool topOnly}) async {
    if (!isConnected) {
      Snackbar.show(ABC.c, 'Not connected', success: false);
      return;
    }
    // Ensure no phone-controlled mode keepalives
    _hrKeepAliveTimer?.cancel();
    _sportHeartbeatTimer?.cancel();

    // Track tested variants in-memory per app run
    _testedVariants ??= <int>{};
    final baseOrderTop = <int>[2, 3, 0, 1, 4, 5];
    final baseOrderFull = <int>[...baseOrderTop, 6, 7, 8, 9];
    final base = topOnly ? baseOrderTop : baseOrderFull;
    // If user asked for remaining but we have evidence of earlier B1 run in this session, mark them as tested.
    // Heuristic: if we ever saw an ACK for 0x40 in logs already, assume early variants may have been tried.
    // Safer approach: pre-mark B1..B4 as tested when running non-top sweeps.
    if (!topOnly) {
      _testedVariants!.addAll({2, 3, 0, 1, 4, 5});
    }
    final variants = base.where((v) => !_testedVariants!.contains(v)).toList();
    if (variants.isEmpty) {
      Snackbar.show(ABC.c, topOnly ? 'Top variants already tested' : 'All variants already tested', success: false);
      return;
    }

    for (final v in variants) {
      _testedVariants!.add(v);
      final ok = await _testSingleVariant(v, runSeconds: 75);
      if (ok) {
        Snackbar.show(ABC.c, 'Found working variant: ${_variantLabel(v)}', success: true);
        break;
      }
      // Small rest between attempts
      await Future.delayed(const Duration(seconds: 8));
    }
  }

  Set<int>? _testedVariants;

  Future<void> _runSpecificVariants(List<int> order) async {
    if (!isConnected) {
      Snackbar.show(ABC.c, 'Not connected', success: false);
      return;
    }
    _hrKeepAliveTimer?.cancel();
    _sportHeartbeatTimer?.cancel();
    _testedVariants ??= <int>{};
    final variants = order.where((v) => !_testedVariants!.contains(v)).toList();
    if (variants.isEmpty) {
      Snackbar.show(ABC.c, 'Selected variants already tested', success: false);
      return;
    }
    for (final v in variants) {
      _testedVariants!.add(v);
      final ok = await _testSingleVariant(v, runSeconds: 75);
      if (ok) {
        Snackbar.show(ABC.c, 'Found working variant: ${_variantLabel(v)}', success: true);
        break;
      }
      await Future.delayed(const Duration(seconds: 8));
    }
  }

  Future<void> _runSpecificVariantsWithType(List<int> order, int sportType) async {
    if (!isConnected) {
      Snackbar.show(ABC.c, 'Not connected', success: false);
      return;
    }
    _hrKeepAliveTimer?.cancel();
    _sportHeartbeatTimer?.cancel();
    _testedVariants ??= <int>{};
    final variants = order.where((v) => !_testedVariants!.contains(v)).toList();
    if (variants.isEmpty) {
      Snackbar.show(ABC.c, 'Selected variants already tested', success: false);
      return;
    }
    for (final v in variants) {
      _testedVariants!.add(v);
      final ok = await _testSingleVariant(v, runSeconds: 75, overrideSportType: sportType);
      if (ok) {
        Snackbar.show(ABC.c, 'Found working variant: ${_variantLabel(v)} (type=$sportType)', success: true);
        break;
      }
      await Future.delayed(const Duration(seconds: 8));
    }
  }

  Future<void> _runZeroBasedTop() async {
    if (!isConnected) {
      Snackbar.show(ABC.c, 'Not connected', success: false);
      return;
    }
    _hrKeepAliveTimer?.cancel();
    _sportHeartbeatTimer?.cancel();
    final variants = <int>[2, 3, 0, 1, 4, 5].where((v) => !(_testedVariants ?? {}).contains(v)).toList();
    if (variants.isEmpty) {
      Snackbar.show(ABC.c, 'Top variants already tested', success: false);
      return;
    }
    for (final v in variants) {
      final ok = await _testSingleVariantZeroBased(v, runSeconds: 75);
      if (ok) {
        Snackbar.show(ABC.c, 'Found working (0-based states): ${_variantLabel(v)}', success: true);
        break;
      }
      await Future.delayed(const Duration(seconds: 8));
    }
  }

  Future<bool> _testSingleVariantZeroBased(int variantIndex, {int runSeconds = 75}) async {
    final type = _selectedSportType;
    final cutoff = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000 - 1800;
    final before = await _getSummaryIdsSince(cutoff);
    // 0-based mapping: start=0, pause=1, continue=2, stop=3
    const stStart = 0, stStop = 3;
    setState(() { _vendorStatus = '0-based ${_variantLabel(variantIndex)} → START (type=$type)'; });
    await _vendorWrite(_buildOp40Packet(stStart, type, variantIndex));
    await Future.delayed(Duration(seconds: runSeconds));
    setState(() { _vendorStatus = '0-based ${_variantLabel(variantIndex)} → STOP (type=$type)'; });
    await _vendorWrite(_buildOp40Packet(stStop, type, variantIndex));
    await Future.delayed(const Duration(seconds: 12));
    final after = await _getSummaryIdsSince(cutoff);
    final ok = after.difference(before).isNotEmpty;
    print('0-based ${_variantLabel(variantIndex)} result → ${ok ? 'SUCCESS' : 'NO RECORD'}');
    return ok;
  }

  Future<void> _runB1WithFinalize({required int runSeconds, int? overrideSportType}) async {
    if (!isConnected) {
      Snackbar.show(ABC.c, 'Not connected', success: false);
      return;
    }
    // B1 = variantIndex 2
    final type = overrideSportType ?? _selectedSportType;
    final cutoff = DateTime.now().toUtc().millisecondsSinceEpoch ~/ 1000 - 1800;
    final before = await _getSummaryIdsSince(cutoff);

    // Optional pre-enable (hypothesis: enable on-device sport source)
    await _vendorWrite(QCBandSDK.buildVendorPacket(0x47, Uint8List.fromList([0x01])));
    await Future.delayed(const Duration(milliseconds: 300));

    setState(() { _vendorStatus = 'B1+finalize → START (type=$type)'; });
    await _vendorWrite(_buildOp40Packet(QcBandSdkConst.ACTION_START, type, 2));
    await Future.delayed(Duration(seconds: runSeconds));
    setState(() { _vendorStatus = 'B1+finalize → STOP (type=$type)'; });
    await _vendorWrite(_buildOp40Packet(QcBandSdkConst.ACTION_STOP, type, 2));

    // Finalize attempts
    await Future.delayed(const Duration(seconds: 1));
    final List<Uint8List> finishSeq = [
      QCBandSDK.buildVendorPacket(0x4F, null),
      QCBandSDK.buildVendorPacket(0x46, Uint8List.fromList([0x00])),
      QCBandSDK.buildVendorPacket(0x46, Uint8List.fromList([0x01])),
    ];
    for (final pkt in finishSeq) {
      await _vendorWrite(pkt);
      await Future.delayed(const Duration(milliseconds: 250));
    }
    // Optional post-disable
    await _vendorWrite(QCBandSDK.buildVendorPacket(0x47, Uint8List.fromList([0x00])));

    // Fetch summaries
    await Future.delayed(const Duration(seconds: 12));
    final after = await _getSummaryIdsSince(cutoff);
    final diff = after.difference(before);
    final ok = diff.isNotEmpty;
    print('B1+finalize result → ${ok ? 'SUCCESS' : 'NO RECORD'} (new=${diff.length})');
    setState(() { _vendorStatus = 'B1+finalize → ${ok ? 'SUCCESS' : 'No record'}'; });
  }

  // PRODUCTION SLEEP DATA EXAMPLE
  // Use the existing fetchSingleDayResponse and SleepParser for production integration
  // Example:
  // List<int> response = await fetchSingleDayResponse(daysAgo);
  // SleepParser parser = SleepParser(response, currentIndex: daysAgo);
  // SleepSummary summary = parser.getSleepSummaryWithTimestamps();
}
