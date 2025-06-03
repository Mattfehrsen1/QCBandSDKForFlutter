import 'dart:typed_data';

// CRC Calculation (like addCRC in Java)
int calculateCRC(Uint8List data) {
  int crc = 0;
  for (int i = 0; i < data.length - 1; i++) {
    crc += data[i];
  }
  return crc & 0xFF;
}

// Build a CMD_GET_DEVICE_ELECTRICITY_VALUE (battery) command
Uint8List buildBatteryRequest() {
  const CMD_GET_DEVICE_ELECTRICITY_VALUE = 3;
  const CMD_DATA_LENGTH = 16;

  final bytes = Uint8List(CMD_DATA_LENGTH);
  bytes[0] = CMD_GET_DEVICE_ELECTRICITY_VALUE;
  bytes[CMD_DATA_LENGTH - 1] = calculateCRC(bytes);
  return bytes;
}

// Parse response (like BatteryRsp.acceptData)
int parseBatteryResponse(Uint8List data) {
  return data[0];
}
