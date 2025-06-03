import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'ble_manager.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: 'BLE App', home: BleScreen());
  }
}

class BleScreen extends StatefulWidget {
  @override
  State<BleScreen> createState() => _BleScreenState();
}

class _BleScreenState extends State<BleScreen> {
  BleManager? bleManager;
  String battery = "--";

  @override
  void initState() {
    super.initState();
    scanAndConnect();
  }

  Future<void> scanAndConnect() async {
    FlutterBluePlus.startScan(timeout: const Duration(seconds: 5));
    FlutterBluePlus.scanResults.listen((results) async {
      for (ScanResult r in results) {
        if (r.device.name.isNotEmpty) {
          print('Found ${r.device.name == 'H59_2300'}');
          FlutterBluePlus.stopScan();
          bleManager = BleManager(r.device);
          await bleManager!.connect();
          bleManager!.onBatteryResponse().listen((value) {
            setState(() {
              battery = '$value%';
            });
          });
          break;
        }
      }
    });
  }

  void getBattery() {
    bleManager?.getBattery();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("BLE Battery Reader")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("Battery: $battery", style: const TextStyle(fontSize: 24)),
            ElevatedButton(
              onPressed: getBattery,
              child: const Text("Get Battery"),
            ),
          ],
        ),
      ),
    );
  }
}
