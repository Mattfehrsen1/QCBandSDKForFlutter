import 'dart:typed_data';

class BloodOxygenEntity {
  String dateStr; // Data date (e.g., "2025-07-05")
  List<int> minArray; // Data minimum value array (24 values, one per hour)
  List<int> maxArray; // Data maximum value array (24 values, one per hour)
  int unixTime; // Data value at 0:00 on a certain day (Unix timestamp in seconds)

  BloodOxygenEntity({
    required this.dateStr,
    required this.minArray,
    required this.maxArray,
    required this.unixTime,
  });

  @override
  String toString() {
    return 'BloodOxygenEntity(\n'
        '  dateStr: $dateStr,\n'
        '  unixTime: $unixTime (${DateTime.fromMillisecondsSinceEpoch(unixTime * 1000)}),\n'
        '  minArray: ${minArray.map((e) => e.toString().padLeft(2, '0')).join(', ')}\n'
        '  maxArray: ${maxArray.map((e) => e.toString().padLeft(2, '0')).join(', ')}\n'
        ')';
  }
}


// Helper function to convert 2 bytes to a short (int in Dart), little-endian
int bytesToShortOxygen(List<int> bytes) {
  return (bytes[1] << 8) | bytes[0];
}

List<BloodOxygenEntity> parseBloodOxygenData(Uint8List receivedData) {
  List<BloodOxygenEntity> bloodOxygenRecords = [];

  // 1. Validate Header and Command ID
  // The first byte (0) should be 188 (0xBC)
  if (receivedData.isEmpty || receivedData[0] != 188) {
    print("Error: Invalid header byte. Expected 188 (0xBC). Got ${receivedData[0]}.");
    return [];
  }
  // The second byte (1) should be 42 (ACTION_Blood_Oxygen)
  if (receivedData[1] != 42) {
    print("Error: Invalid Command ID. Expected 42. Got ${receivedData[1]}.");
    return [];
  }

  // 2. Extract Data Length (Bytes 2 and 3)
  // This is the total length of the data payload that follows (from index 6 onwards).
  int totalDataPayloadLength = bytesToShortOxygen(receivedData.sublist(2, 4)); // In your example: [147, 0] -> 147

  // 3. Extract CRC (Bytes 4 and 5)
  // int receivedCrc = bytesToShortOxygen(receivedData.sublist(4, 6)); // In your example: [152, 188] -> 48312
  // You might want to re-calculate CRC of the data payload and compare it with receivedCrc for data integrity.

  // 4. Isolate the Raw Data Payload
  // The actual blood oxygen data starts from index 6.
  // The length of this payload is `totalDataPayloadLength`.
  Uint8List rawDataPayload = receivedData.sublist(6, 6 + totalDataPayloadLength);

  // 5. Calculate Number of BloodOxygenEntity Records
  // Each BloodOxygenEntity record is 49 bytes long.
  if (rawDataPayload.length % 49 != 0) {
    print("Warning: Raw data payload length (${rawDataPayload.length}) is not a multiple of 49. Data might be incomplete or malformed.");
    return [];
  }
  int numberOfRecords = rawDataPayload.length ~/ 49;

  // 6. Iterate and Parse Each Record (49 bytes per record)
  DateTime now = DateTime.now();
  // Get today's date at midnight (00:00:00) for calculating timestamps
  DateTime todayMidnight = DateTime(now.year, now.month, now.day);

  for (int i = 0; i < numberOfRecords; i++) {
    int offset = i * 49; // Starting byte for the current record

    // Extract minArray (bytes 0 to 23 within the 49-byte chunk)
    List<int> minArray = rawDataPayload.sublist(offset, offset + 24).toList();

    // Extract maxArray (bytes 24 to 47 within the 49-byte chunk)
    List<int> maxArray = rawDataPayload.sublist(offset + 24, offset + 48).toList();

    // Extract date offset (byte 48 within the 49-byte chunk)
    int dateOffsetDays = rawDataPayload[offset + 48];

    // Derive dateStr and unixTime
    // The dateOffsetDays tells us how many days before 'today' the data was recorded.
    DateTime recordDate = todayMidnight.subtract(Duration(days: dateOffsetDays));

    String dateStr = "${recordDate.year.toString()}-"
                     "${recordDate.month.toString().padLeft(2, '0')}-"
                     "${recordDate.day.toString().padLeft(2, '0')}";

    int unixTime = recordDate.millisecondsSinceEpoch ~/ 1000; // Unix timestamp in seconds

    bloodOxygenRecords.add(BloodOxygenEntity(
      dateStr: dateStr,
      minArray: minArray,
      maxArray: maxArray,
      unixTime: unixTime,
    ));
  }

  return bloodOxygenRecords;
}

// Example Usage:
/*
void main() {
  Uint8List receivedBytes = Uint8List.fromList([
    188, 42, 147, 0, 152, 188, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 98, 98, 96, 96, 99, 99, 98, 98, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 96, 96, 97, 97, 98, 98, 99, 99, 98, 98, 96, 96, 98, 98, 0, 0, 99, 99, 99, 99, 96, 96, 98, 98, 96, 96, 0, 0, 0, 0, 97, 97, 98, 98, 98, 98, 97, 97, 99, 99, 98, 98, 0, 0, 96, 96, 97, 97, 0, 98, 98, 99, 99, 97, 97, 99, 99, 99, 99, 98, 98, 99, 99, 99, 99, 99, 99, 99, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
  ]);

  List<BloodOxygenEntity> parsedData = parseBloodOxygenData(receivedBytes);
  parsedData.forEach((entity) => print(entity));
}
*/