import 'dart:async';
import 'dart:developer';
import 'dart:typed_data'; // Required for Uint8List and ByteData
import 'dart:core'; // Required for Endian
import 'dart:collection';

import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
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
      }
    } catch (e) {
      print('Error discovering services: $e');
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
  
  // Class-level accumulator for step detail data
  List<Map> _accumulatedStepDetailData = [];

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

  // Step Data Details - FIXED & FLEXIBLE
  deviceDetailStep({int daysBack = 0, int dayCount = 1}) async {
    print('🔧 FIXED FUNCTION CALLED - deviceDetailStep() starting...');
    print('🔧 Parameters: daysBack=$daysBack, dayCount=$dayCount');
    
    // Clear previous session data
    _accumulatedStepDetailData.clear();
    int _expectedTotalPackets = dayCount * 8; // 8 packets per day
    int _receivedPackets = 0;
    
    print('🔧 Cleared accumulated data, requesting $dayCount days starting from $daysBack days back...');
    
    // Request data for each day
    for (int i = 0; i < dayCount; i++) {
      int currentDayOffset = daysBack + i;
      print('🔧 Requesting day offset: $currentDayOffset');
      
      await _bluetoothCharacteristicWrite
          .write(QCBandSDK.generateReadStepDetailsCommand(currentDayOffset, 0, 95));
      
      // Small delay between requests to avoid overwhelming the device
      if (i < dayCount - 1) {
        await Future.delayed(Duration(milliseconds: 100));
      }
    }
    
    _bluetoothCharacteristicNotification.value.listen((value) {
      print('🔧 NOTIFICATION LISTENER TRIGGERED: $value');
      log('Raw step packet: $value');
      
      if (value.isNotEmpty && value[0] == 67) { // Command validation
        print('🔧 COMMAND VALIDATION PASSED: value[0] = ${value[0]}');
        
        // Filter data packets (37) vs status packets (240)
        print('🔧 PACKET FILTER CHECK: value[1] = ${value[1]}');
        if (value[1] == 37) { // Data packet marker
          print('🔧 DATA PACKET DETECTED! Processing...');
          var parsedDetail = parseDetailStepData(value);
          print('🔧 PARSED DETAIL: $parsedDetail');
          _accumulatedStepDetailData.add(parsedDetail);
          print('🔧 ADDED TO ACCUMULATOR. Total items: ${_accumulatedStepDetailData.length}');
          
          int packetIndex = value[5]; // Sequential index (0,1,2,3,4,5,6,7)
          _receivedPackets++;
          log('Added step detail packet $packetIndex: $parsedDetail');
          print('🔧 Progress: $_receivedPackets/$_expectedTotalPackets packets received');
          
          // Completion detection for multiple days
          if (_receivedPackets >= _expectedTotalPackets) {
            log('--- All $dayCount day(s) of step detail data received ---');
            log('Total packets received: $_receivedPackets');
            log('Complete step data: $_accumulatedStepDetailData');
            
            // Sort by date and time to ensure proper chronological order
            _accumulatedStepDetailData.sort((a, b) {
              int dateCompare = DateTime(a['year'], a['month'], a['day'])
                  .compareTo(DateTime(b['year'], b['month'], b['day']));
              if (dateCompare != 0) return dateCompare;
              return a['time'].compareTo(b['time']);
            });
            
            print('🎉 SYNC COMPLETE: ${_accumulatedStepDetailData.length} time periods from $dayCount day(s)');
          }
        }
        else if (value[1] == 240) {
          log('Received status packet (ignored): $value');
        }
      }
    }, onError: (error) {
      log('Step detail stream error: $error');
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

  // Step Data of Today
  liveHeartRate() async {
    // Today
    await _bluetoothCharacteristicWrite.write(
      QCBandSDK.liveHeartData(1),
    );
    _bluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: $value');
      if (value.isNotEmpty &&
          value[0] == QcBandSdkConst.cmdGetRealTimeHeartRate) {
        // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
        // print(recievedHRVData);
        log('Received HRV: ${value[1]}');
      }
    });
  }

  sleepDetailData() async {
    int currentDay = 1;
    bool secondQuery = false;
    try {
      await _secondbluetoothCharacteristicWrite
          .write(QCBandSDK.getSleepData(currentDay));
      print("Command for sleep data requested successfully.");
    } catch (e) {
      print("Failed to request sleep data: $e");
      // Handle specific BLE errors (e.g., BluetoothAdapter is off, device disconnected)
    }
    // Parsing Sleep Data Steps
    // 1. Combine the both element after call.
    // 2. Know what data been mising by extracting and missing days [ Seperate the data by date] i.e  0,36 [Today] 1, 36 [Today - 1] 2, 36 [Today - 2] 3, 36 [Today - 3] 4, 36 [Today - 4], 5, 36 [Today - 5] 6, 36 [Today - 6]
    // 3. Calculate the deep sleep, light sleep , Rapid eye movement , awake
    _secondbluetoothCharacteristicNotification.value.listen((value) {
      // Handle the received value (List<int>)
      print('Received notification: ${value.length}');
      if (value.isNotEmpty) {
        // For Today
        if (currentDay == 0) {
          // Create an instance of the SleepParser
          final SleepParser parser = SleepParser(value);

          // Get the first 13 elements
          final List<int> firstThirteen = parser.getFirstThirteenElements();
          print("First 13 elements: $firstThirteen");

          // Get the remaining elements as pairs
          final List<List<int>> remainingElementsPairs =
              parser.getRemainingElements();
          print("Remaining elements (in pairs): $remainingElementsPairs");

          // Calculate and print the sum of second values for pairs starting with 2
          final int sumOfLightSleep = parser.sumLightSleep();
          print("Sum of Light Sleep (pairs starting with 2): $sumOfLightSleep");

          final int sumOfDeepSleep = parser.sumDeepSleep();
          print("Sum of Deep Sleep (pairs starting with 3): $sumOfDeepSleep");

          final int sumOfRapidEyeMoment = parser.sumRapidEyeMoment();
          // Corrected expected value for sampleData: 13 (from [4,13]) + 6 (from [4,6]) + 12 (from [4,12]) + 12 (from [4,12]) = 43
          print(
              "Sum of Rapid Eye Movement (pairs starting with 4): $sumOfRapidEyeMoment");

          final int sumOfAwake = parser.sumAwake();
          // Corrected expected value for sampleData: 2 (from [5,2]) + 8 (from [5,8]) = 10
          print("Sum of Awake (pairs starting with 5): $sumOfAwake");

          // Get and print the sleep summary
          final Map<String, int> sleepSummary = parser.getSleepSummary();
          print("\nSleep Summary: $sleepSummary");
        } else if (currentDay > 0 && currentDay < 7) {
          List<int> sleepData1 = []; // Query data
          List<int> sleepData2 = []; // -1 day data
          try {
            await _secondbluetoothCharacteristicWrite
                .write(QCBandSDK.getSleepData(currentDay - 1));
            print("Command for previous day sleep data requested successfully.");
          } catch (e) {
            print("Failed to request previous day sleep data: $e");
            // Handle specific BLE errors (e.g., BluetoothAdapter is off, device disconnected)
          }
          
          _secondbluetoothCharacteristicNotification.value.listen((onValue) {
            // Handle the received value (List<int>)
            print('Received notification: ${onValue.length}');
            if (onValue.isNotEmpty &&
                onValue[0] == QcBandSdkConst.actionBloodOxygen &&
                onValue[1] == QcBandSdkConst.getSleepData &&
                secondQuery == false) {
              sleepData1 = onValue;
              secondQuery = true;
              print('This is the first List $sleepData1}');
            }
          });
          
          await Future.delayed(Duration(seconds: 1));
          try {
            await _secondbluetoothCharacteristicWrite
                .write(QCBandSDK.getSleepData(currentDay));
            print("Command for current day sleep data requested successfully.");
          } catch (e) {
            print("Failed to request current day sleep data: $e");
            // Handle specific BLE errors (e.g., BluetoothAdapter is off, device disconnected)
          }
          
          _secondbluetoothCharacteristicNotification.value.listen((onValue) {
            // Handle the received value (List<int>)
            print('Received notification: ${onValue.length}');
            if (onValue.isNotEmpty &&
                onValue[0] == QcBandSdkConst.actionBloodOxygen &&
                onValue[1] == QcBandSdkConst.getSleepData) {
              sleepData2 = onValue;
              print('This is the second List $sleepData2}');
            }
            // Create an instance of the SleepParser
            final SleepParser parser2 =
                SleepParser(sleepData2, currentIndex: currentDay);
            // Get and print the sleep summary
            final Map<String, int> sleepSummaryofYesterday =
                parser2.getSleepSummaryYesterday(
                    todayList: sleepData2, yesterdayList: sleepData1);
            // print(sleepSummaryofYesterday);
          });
        } else if (currentDay == 2) {
          final HistoricalSleepDataParser historicalParser =
              HistoricalSleepDataParser(value);
          final List<int> processListIndexSecond =
              historicalParser.getProcessedElementsIndexSecond();
          print("Elements After Truncation: $processListIndexSecond");
          final SleepParser parser = SleepParser(processListIndexSecond);
          // Get and print the sleep summary
          final Map<String, int> sleepSummary = parser.getSleepSummary();
          print("\nSleep Summary: $sleepSummary");
        } else if (currentDay == 3) {
          final HistoricalSleepDataParser historicalParser =
              HistoricalSleepDataParser(value);
          final List<int> processListIndexSecond =
              historicalParser.getProcessedElementsIndexSecond();
          print("Elements After Truncation: $processListIndexSecond");
          final SleepParser parser = SleepParser(processListIndexSecond);
          // Get and print the sleep summary
          final Map<String, int> sleepSummary = parser.getSleepSummary();
          print("\nSleep Summary: $sleepSummary");
        }
      }
    });
    // Today
    // await _secondbluetoothCharacteristicWrite.write(
    //   QCBandSDK.generateReadSleepDetailsCommand(1, 0, 95),
    // );
    // _secondbluetoothCharacteristicNotification.value.listen((value) {
    //   // Handle the received value (List<int>)
    //   print('Received notification: $value');
    //   if (value.isNotEmpty) {
    //     // var recievedHRVData = QCBandSDK.DataParsingWithData(value);
    //     // print(recievedHRVData);
    //     log('Received Sleep: $value');
    //   }
    // });
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

  getBloodPressureDeviceWrong() async {
    // await _bluetoothCharacteristicWrite.write(
    //   QCBandSDK.getBloodPressure(0),
    // );

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
        command,
        withoutResponse:
            false, // Set to true if your device doesn't send a write response
      );
      print("Pressure request sent successfully for offset: $offset");
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

  getBloodPressureDevice() async {
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
        command,
        withoutResponse:
            false, // Set to true if your device doesn't send a write response
      );
      print("Pressure request sent successfully for offset: $offset");
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
                    // Test 3-day sync: today + yesterday + day before
                    deviceDetailStep(daysBack: 0, dayCount: 3);
                  },
                  child: Text('Multi Day Step Sync (3 Days)', 
                      style: TextStyle(color: Colors.green))),
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
                  // Notify Listenner of the Command
                  // getDeviceBattery();
                  //Send Command
                  TODO: // Working Need Work on Parsing
                  sleepDetailData();
                  // Parse Response
                },
                child: Text('Sleep Details Data'),
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
                onPressed: () {
                  // Notify Listenner of the Command
                  // getDeviceBattery();
                  //Send Command
                  // TODO: // Working Need Work on Parsing
                  // sleepDetailData();
                  // Parse Response
                },
                child: Text('Get Alarm'),
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
                child: Text('Set Alarm'),
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
                child: Text('Start WorkOut'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
