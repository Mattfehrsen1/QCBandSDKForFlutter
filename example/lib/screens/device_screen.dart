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
  StreamSubscription<List<int>>? _heartRateNotificationSubscription;
  Timer? _heartRateRepeatingTimer;

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

  sleepDetailData() async {
    // CRITICAL: First debug to verify function is called
    // Fetch sleep data for multiple days
    log("🚨🚨🚨 sleepDetailData() FUNCTION CALLED! 🚨🚨🚨");

    // Run timestamp calculation test first
    print("Running timestamp calculation test...");
    // SleepParser.testTimestampCalculation();

    // 🎯 REAL DATA TEST - Manually decode the 51-byte packet we saw for Day 1
    print("\n🎯 TESTING REAL DEVICE DATA FROM LOGS 🎯");
    // List<int> realDeviceData = [
    //   188,
    //   39,
    //   31,
    //   0,
    //   124,
    //   229,
    //   1,
    //   2,
    //   28,
    //   1,
    //   1,
    //   232,
    //   1,
    //   2,
    //   21,
    //   5,
    //   8,
    //   3,
    //   39,
    //   2,
    //   11,
    //   5,
    //   3,
    //   3,
    //   21,
    //   5,
    //   3,
    //   4,
    //   19,
    //   2,
    //   31,
    //   3,
    //   38,
    //   4,
    //   18,
    //   2,
    //   19
    // ];

    // print("Real device packet length: ${realDeviceData.length} bytes");
    // print("Real device packet: ${realDeviceData.sublist(0, 20)}...");

    // // Manual decoding to verify structure
    // int startMinutes = realDeviceData[4] | (realDeviceData[5] << 8);
    // int endMinutes = realDeviceData[6] | (realDeviceData[7] << 8);

    // print(
    //     "Manual decode - Start: $startMinutes minutes (${(startMinutes ~/ 60).toString().padLeft(2, '0')}:${(startMinutes % 60).toString().padLeft(2, '0')})");
    // print(
    //     "Manual decode - End: $endMinutes minutes (${(endMinutes ~/ 60).toString().padLeft(2, '0')}:${(endMinutes % 60).toString().padLeft(2, '0')})");

    // // Test with SleepParser
    // SleepParser realParser = SleepParser(realDeviceData, currentIndex: 3);
    // SleepSummary realSummary = realParser.getSleepSummaryWithTimestamps();

    // print("🌙 REAL DEVICE DATA RESULTS:");
    // print("  Bed Time: ${realSummary.bedTime}");
    // print("  Wake Time: ${realSummary.wakeTime}");
    // if (realSummary.bedTime != null && realSummary.wakeTime != null) {
    //   print(
    //       "  📍 Bed Time (local): ${realSummary.bedTime!.hour.toString().padLeft(2, '0')}:${realSummary.bedTime!.minute.toString().padLeft(2, '0')}");
    //   print(
    //       "  📍 Wake Time (local): ${realSummary.wakeTime!.hour.toString().padLeft(2, '0')}:${realSummary.wakeTime!.minute.toString().padLeft(2, '0')}");
    // }
    // print("  Segments: ${realSummary.segments.length}");

    // Multi-level packet validation functions

    // Large Data Protocol validation (for multi-day packets)
    bool _validateLargeDataPacket(List<int> data, int expectedDay) {
      // Check 3: Valid packet length field (little-endian)
      // NOTE: BLE adds 6-byte wrapper, so actual length = declared + 6
      int declaredLength = data[2] | (data[3] << 8);
      int expectedActualLength = declaredLength + 6;
      if (data.length != expectedActualLength &&
          data.length != declaredLength) {
        // Length validation failed
        return false;
      }
      // Length validation passed

      // Check 4: Valid multi-day structure
      if (data.length < 9) {
        // Packet too short
        return false;
      }

      int totalDays = data[6] & 0xFF;
      if (totalDays == 0) {
        // Zero days detected
        return false;
      }

      if (totalDays > 7) {
        // Too many days
        return false;
      }

      // Check 5: Size-based validation - large packets are true multi-day
      if (data.length >= 100) {
        // Large Data Protocol validation passed
        return true;
      }

      // NEW: 59-byte packets are single-day Standard Protocol (misnamed but valid)
      if (data.length == 59 && totalDays == 1) {
        print(
            "✅ VALIDATION: 59-byte packet detected as Standard Protocol (single day, ${data.length} bytes)");
        return false; // Return false to route to Standard Protocol parser
      }

      // Invalid packet size
      return false;
    }

    // Standard Protocol validation (for 59-byte single-day packets)
    bool _validateStandardPacket(List<int> data, int expectedDay) {
      // Accept 59-byte packets specifically (our main target)
      if (data.length == 59) {
        // Validate length consistency
        int declaredLength = data[2] | (data[3] << 8);
        int expectedActualLength = declaredLength + 6;
        if (data.length != expectedActualLength) {
          // Length mismatch
          return false;
        }

        // Should have totalDays = 1 for single-day data
        if (data.length >= 7 && data[6] != 1) {
          // Invalid totalDays for Standard Protocol
          return false;
        }

        // Standard Protocol validation passed
        return true;
      }

      // Accept other reasonable sizes for Standard Protocol
      if (data.length >= 20 && data.length <= 100) {
        // Standard Protocol validation passed
        return true;
      }

      // Invalid Standard Protocol size
      return false;
    }

    bool containsExpectedDay(List<int> data, int expectedDay) {
      if (data.length < 9) return false;

      int totalDays = data[6] & 0xFF;
      int firstDayId = data[7] & 0xFF;

      // Check if our expected day is in this packet's range
      for (int i = 0; i < totalDays; i++) {
        if (firstDayId - i == expectedDay) {
          // Day found in packet
          return true;
        }
      }

      // Day not found in packet
      return false;
    }

    // Main validation function that routes to protocol-specific validators
    bool isValidMultiDayPacket(List<int> data, int expectedDay) {
      // Check 1: Must be at least header size + some data
      if (data.length < 10) {
        // Packet too short
        return false;
      }

      // Check 2: Correct command bytes (accept both protocols)
      if (data[0] != 188) {
        // Invalid command header
        return false;
      }

      if (data[1] != 39 && data[1] != 68) {
        // Invalid sub-command
        return false;
      }

      // NEW: Special routing logic for 59-byte packets
      if (data.length == 59 && data[1] == 39) {
        // Routing to Standard Protocol parser
        return _validateStandardPacket(data, expectedDay);
      }

      // Protocol-specific validation for other packets
      if (data[1] == 39) {
        return _validateLargeDataPacket(data, expectedDay);
      } else {
        return _validateStandardPacket(data, expectedDay);
      }
    }

    // Helper function to get stage display name
    String _getStageDisplayName(int stageType) {
      switch (stageType) {
        case 1:
          return "deep";
        case 2:
          return "light";
        case 3:
          return "awake";
        case 4:
          return "rem";
        case 5:
          return "unknown";
        default:
          return "unknown";
      }
    }

    // Helper function to request data and collect all packets for a day
    Future<List<int>> fetchSingleDayResponse(int day) async {
      // Fetch sleep data for day $day from BLE device

      final completer = Completer<List<int>>();
      List<int>? bestValidPacket;
      Timer? timeoutTimer;

      // Multi-packet assembly state (from LargeDataParser.java)
      List<int>? tempPacketData;
      int expectedDataLength = 0;

      // CRITICAL DEBUG: Test if the characteristic is working
      print("🔍 DEBUGGING CHARACTERISTIC for day $day");
      print(
          "🔍 _secondbluetoothCharacteristicNotification exists: ${_secondbluetoothCharacteristicNotification != null}");
      if (_secondbluetoothCharacteristicNotification != null) {
        print(
            "🔍 Characteristic UUID: ${_secondbluetoothCharacteristicNotification!.uuid}");
      } else {
        print("🔍 Characteristic is NULL - this is the problem!");
      }

      // CRITICAL: Set up listener BEFORE sending the write command to avoid race condition
      final subscription =
          _secondbluetoothCharacteristicNotification.value.listen((value) {
        // Check if this is a sleep data response

        if (value.isNotEmpty && value[1] == QcBandSdkConst.getSleepData) {
          // Process multi-packet assembly for day $day

          // Official Logic from LargeDataParser.java
          if (value.length >= 6 && (value[0] & 255) == 188) {
            // First packet: Extract total data length
            expectedDataLength = (value[2] & 255) | ((value[3] & 255) << 8);
            // Extract declared length from first packet

            if (value.length - 6 >= expectedDataLength) {
              // Complete packet in one go
              // Complete packet received
              List<int> completePacket = List<int>.from(value);

              if (isValidMultiDayPacket(completePacket, day) &&
                  containsExpectedDay(completePacket, day)) {
                // Valid complete packet accepted
                bestValidPacket = completePacket;

                timeoutTimer?.cancel();
                timeoutTimer = Timer(Duration(milliseconds: 200), () {
                  if (!completer.isCompleted && bestValidPacket != null) {
                    completer.complete(bestValidPacket!);
                  }
                });
              } else {
                // Complete packet rejected
              }

              // End packet validation
              return;
            }

            // Start of multi-packet: Store first chunk
            print(
                "🔄 MULTI-PACKET START: Storing first chunk (${value.length} bytes)");
            tempPacketData = List<int>.from(value);
            print("=== END PACKET VALIDATION ===");
            return;
          }

          // Continuation packets: Accumulate data
          if (tempPacketData != null) {
            print(
                "🔄 CONTINUATION PACKET: Adding ${value.length} bytes to accumulated data");
            tempPacketData!.addAll(value);
            print(
                "📊 ACCUMULATED: ${tempPacketData!.length} bytes, Expected: ${expectedDataLength + 6}");

            if (tempPacketData!.length - 6 == expectedDataLength) {
              // Complete assembly: Process accumulated data
              print(
                  "✅ ASSEMBLY COMPLETE: Processing ${tempPacketData!.length} bytes");
              List<int> assembledPacket = List<int>.from(tempPacketData!);

              if (isValidMultiDayPacket(assembledPacket, day) &&
                  containsExpectedDay(assembledPacket, day)) {
                print(
                    "✅ ASSEMBLED PACKET ACCEPTED: Valid multi-packet for day $day");
                bestValidPacket = assembledPacket;

                timeoutTimer?.cancel();
                timeoutTimer = Timer(Duration(milliseconds: 200), () {
                  if (!completer.isCompleted && bestValidPacket != null) {
                    // Using assembled packet
                    completer.complete(bestValidPacket!);
                  }
                });
              } else {
                // Assembled packet rejected
              }

              // Reset assembly state
              tempPacketData = null;
              expectedDataLength = 0;
            } else if (tempPacketData!.length - 6 > expectedDataLength) {
              print(
                  "❌ ASSEMBLY ERROR: Too much data received (${tempPacketData!.length - 6} > $expectedDataLength)");
              tempPacketData = null;
              expectedDataLength = 0;
            }

            print("=== END PACKET VALIDATION ===");
            return;
          }

          // Fallback: Unknown packet type - not a valid sleep data packet
        }
      });

      // Add a small delay to ensure listener is ready
      await Future.delayed(Duration(milliseconds: 50));

      await _secondbluetoothCharacteristicWrite
          .write(QCBandSDK.getSleepData(day));
      print("Command for sleep data for day $day requested successfully.");

      // Wait for the response, with a timeout
      final response = await completer.future
          .timeout(const Duration(seconds: 10), onTimeout: () {
        // Timeout: no valid packets received
        timeoutTimer?.cancel();
        // Return best packet we found, or empty if none
        return bestValidPacket ?? [];
      });

      subscription.cancel();
      timeoutTimer?.cancel();

      // Sleep data fetch complete
      return response;
    }

    // --- Main Logic ---

    // 1. Fetch and process Today's data (Day 0) separately
    List<int> todayData = await fetchSingleDayResponse(0);
    if (todayData.isNotEmpty && todayData.length >= 13) {
      final parser = SleepParser(todayData, currentIndex: 0);

      // Get traditional summary
      final summary = parser.getSleepSummary();
      print("\nSleep Summary for day 0 (Today): $summary");

      // Get enhanced summary with timestamps (date extracted from data)
      final enhancedSummary = parser.getSleepSummaryWithTimestamps();
      print("Enhanced Sleep Summary for Today:");
      print("  Bed Time: ${enhancedSummary.bedTime}");
      print("  Wake Time: ${enhancedSummary.wakeTime}");
      print("  Duration Breakdown: ${enhancedSummary.durations}");
      print("  Total Segments: ${enhancedSummary.segments.length}");

      // Show first few segments for debugging
      if (enhancedSummary.segments.isNotEmpty) {
        print("  First few segments:");
        for (int i = 0; i < 5 && i < enhancedSummary.segments.length; i++) {
          final seg = enhancedSummary.segments[i];
          print(
              "    ${seg.segmentStart} - ${seg.segmentEnd} (stage: ${seg.stageType}, quality: ${seg.originalQuality})");
        }
      }
    } else if (todayData.isNotEmpty) {
      print(
          "\nDay 0 (Today): Response too short (${todayData.length} bytes) - no sleep data available");
    } else {
      print("\nDay 0 (Today): No response received");
    }

    // 🎯 SPECIAL PROCESSING FOR DAY 1 (August 4th-5th) - Your newest sleep data!
    print(
        "\n🎯 PROCESSING DAY 1 (August 4th-5th) - Your Latest Sleep Data! 🎯");
    List<int> day1Data = await fetchSingleDayResponse(1);
    if (day1Data.isNotEmpty && day1Data.length >= 13) {
      // Day 1 data received

      final day1Parser = SleepParser(day1Data, currentIndex: 1);
      final day1Summary = day1Parser.getSleepSummaryWithTimestamps();

      print("🌙 DAY 1 (August 4th-5th) SLEEP ANALYSIS:");
      print("  Bed Time: ${day1Summary.bedTime}");
      print("  Wake Time: ${day1Summary.wakeTime}");

      if (day1Summary.bedTime != null && day1Summary.wakeTime != null) {
        print(
            "  📍 Bed Time (local): ${day1Summary.bedTime!.hour.toString().padLeft(2, '0')}:${day1Summary.bedTime!.minute.toString().padLeft(2, '0')}");
        print(
            "  📍 Wake Time (local): ${day1Summary.wakeTime!.hour.toString().padLeft(2, '0')}:${day1Summary.wakeTime!.minute.toString().padLeft(2, '0')}");
      }

      print("  Duration Breakdown: ${day1Summary.durations}");
      print("  Total Segments: ${day1Summary.segments.length}");
    } else {
      // Day 1 data insufficient
    }

    // Test specific day data
    List<int> day4Data = await fetchSingleDayResponse(4);
    if (day4Data.isNotEmpty && day4Data.length >= 13) {
      // Day 4 data received
      final day4Parser = SleepParser(day4Data, currentIndex: 4);
      final day4Summary = day4Parser.getSleepSummaryWithTimestamps();
      // Day 4 processed successfully
    } else {
      // Day 4 data insufficient
    }

    // Keep track of the previous day's data for parsing
    List<int> previousDayData = todayData;

    // 2. Fetch and process historical data (Day 1 to 6)
    for (int i = 1; i < 7; i++) {
      // The device sends data for day `i` and `i-1` together when we ask for day `i`.
      // The `getSleepSummaryYesterday` method internally splits this combined data.
      List<int> combinedResponse = await fetchSingleDayResponse(i);

      if (combinedResponse.isNotEmpty && combinedResponse.length >= 13) {
        final parser = SleepParser(combinedResponse, currentIndex: i);

        // For historical data, use direct parsing instead of yesterday parsing
        // since the data format seems to contain the sleep data directly

        // Get enhanced summary with timestamps (date extracted from data)
        final enhancedSummary = parser.getSleepSummaryWithTimestamps();

        // Create clean JSON output with sleep stage breakdown
        Map<String, dynamic> sleepDataJson = {
          "day_$i": {
            "status": "available",
            "bed_time": enhancedSummary.bedTime?.toIso8601String(),
            "wake_time": enhancedSummary.wakeTime?.toIso8601String(),
            "duration_minutes": enhancedSummary.durations['totalDuration'],
            "stage_breakdown": {
              "light_sleep_minutes": enhancedSummary.durations['lightSleep'],
              "deep_sleep_minutes": enhancedSummary.durations['deepSleep'],
              "rem_sleep_minutes":
                  enhancedSummary.durations['rapidEyeMovement'],
              "awake_minutes": enhancedSummary.durations['awake'],
            },
            "segments": enhancedSummary.segments
                .map((seg) => {
                      "start": seg.segmentStart.toIso8601String(),
                      "end": seg.segmentEnd.toIso8601String(),
                      "stage": _getStageDisplayName(seg.stageType),
                      "duration_minutes":
                          seg.segmentEnd.difference(seg.segmentStart).inMinutes,
                    })
                .toList(),
            "total_segments": enhancedSummary.segments.length,
          }
        };

        // Production sleep data summary
        print("\nSleep Summary - Day $i:");
        print(
            "  ${enhancedSummary.bedTime?.hour.toString().padLeft(2, '0')}:${enhancedSummary.bedTime?.minute.toString().padLeft(2, '0')} → ${enhancedSummary.wakeTime?.hour.toString().padLeft(2, '0')}:${enhancedSummary.wakeTime?.minute.toString().padLeft(2, '0')} (${enhancedSummary.durations['totalDuration']} min)");
        print(
            "  Deep: ${enhancedSummary.durations['deepSleep']}min | Light: ${enhancedSummary.durations['lightSleep']}min | REM: ${enhancedSummary.durations['rapidEyeMovement']}min | Awake: ${enhancedSummary.durations['awake']}min");
        print("  ${enhancedSummary.segments.length} segments");

        // Show segment details
        print("\nSegment Timeline (${enhancedSummary.segments.length} total):");
        for (int segIdx = 0;
            segIdx < enhancedSummary.segments.length && segIdx < 10;
            segIdx++) {
          final seg = enhancedSummary.segments[segIdx];
          final startTime =
              "${seg.segmentStart.hour.toString().padLeft(2, '0')}:${seg.segmentStart.minute.toString().padLeft(2, '0')}";
          final endTime =
              "${seg.segmentEnd.hour.toString().padLeft(2, '0')}:${seg.segmentEnd.minute.toString().padLeft(2, '0')}";
          final duration =
              seg.segmentEnd.difference(seg.segmentStart).inMinutes;
          final stageName = _getStageDisplayName(seg.stageType);
          print(
              "  ${segIdx + 1}. $startTime → $endTime ($duration min) - $stageName");
        }
        if (enhancedSummary.segments.length > 10) {
          print(
              "  ... and ${enhancedSummary.segments.length - 10} more segments");
        }

        print("\nComplete JSON available via getSleepDataJson() method");
      } else if (combinedResponse.isNotEmpty) {
        print(
            "\nℹ️ Day $i: No sleep data available (response too short: ${combinedResponse.length} bytes)");
      } else {
        print(
            "\nℹ️ Day $i: No sleep data available (device not worn or no sleep recorded)");
      }
      // Update previousDayData for the next iteration
      previousDayData = combinedResponse;

      // Add a small delay between requests to be safe
      await Future.delayed(const Duration(milliseconds: 200));
    }
  }

  historicalSleepData() async {
    // This function is deprecated as its logic has been integrated into sleepDetailData.
    print(
        "historicalSleepData is deprecated and its logic is now in sleepDetailData.");
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
    final List<int> command = QCBandSDK.startWorkOut();
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
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
    final List<int> command = QCBandSDK.pauseWorkOut();
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
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

  continueWorkOut() async {
    final List<int> command = QCBandSDK.continueWorkOut();
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
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

  stopWorkOut() async {
    final List<int> command = QCBandSDK.stopWorkOut();
    try {
      await _bluetoothCharacteristicWrite!.write(
        command,
      );
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
                onPressed: startWorkOut,
                child: Text('Start WorkOut'),
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
            ],
          ),
        ),
      ),
    );
  }

  // PRODUCTION SLEEP DATA EXAMPLE
  // Use the existing fetchSingleDayResponse and SleepParser for production integration
  // Example:
  // List<int> response = await fetchSingleDayResponse(daysAgo);
  // SleepParser parser = SleepParser(response, currentIndex: daysAgo);
  // SleepSummary summary = parser.getSleepSummaryWithTimestamps();
}
