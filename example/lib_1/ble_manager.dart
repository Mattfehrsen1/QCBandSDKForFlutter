import 'dart:typed_data';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'commands.dart';

class BleManager {
  final BluetoothDevice device;
  BluetoothCharacteristic? writeChar;
  BluetoothCharacteristic? notifyChar;

  BleManager(this.device);

  Future<void> connect() async {
    await device.connect();
    List<BluetoothService> services = await device.discoverServices();
    print("Discovered services:");
    for (var s in services) {
      print(s.uuid.toString());
    }

    final service = services.firstWhere(
        (s) => s.uuid.toString() == '6e40fff0-b5a3-f393-e0a9-e50e24dcca9e');
    writeChar = service.characteristics.firstWhere(
        (c) => c.uuid.toString() == '6e400002-b5a3-f393-e0a9-e50e24dcca9e');
    notifyChar = service.characteristics.firstWhere(
        (c) => c.uuid.toString() == 'de5bf72a-d711-4e47-af26-65e3012a5dc7');

    await notifyChar!.setNotifyValue(true);
  }

  Stream<int> onBatteryResponse() {
    return notifyChar!.onValueReceived.map((data) {
      // Convert List<int> to Uint8List before parsing
      final uint8data = Uint8List.fromList(data);
      return parseBatteryResponse(uint8data);
    });
  }

  Future<void> getBattery() async {
    final cmd = buildBatteryRequest();
    await writeChar!.write(cmd, withoutResponse: false);
  }

  void dispose() {
    device.disconnect();
  }
}
