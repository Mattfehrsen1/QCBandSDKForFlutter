import 'dart:typed_data';
import 'package:intl/intl.dart'; // For date formatting

// 1. Define the BloodOxygenEntity Class in Dart (remains the same)
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
        '  Date: $dateStr (Unix: $unixTime (${DateTime.fromMillisecondsSinceEpoch(unixTime * 1000)})),\n'
        '  Min Array (24 hours): ${minArray.map((e) => e.toString().padLeft(2, '0')).join(', ')}\n'
        '  Max Array (24 hours): ${maxArray.map((e) => e.toString().padLeft(2, '0')).join(', ')}\n'
        ')';
  }
}

// Helper function to convert 2 bytes to a short (int in Dart), little-endian
int bytesToShort(List<int> bytes) {
  return (bytes[1] << 8) | bytes[0];
}

// 2. Updated Parsing Logic with Interleaved Min/Max
List<BloodOxygenEntity> parseBloodOxygenData(Uint8List receivedData) {
  List<BloodOxygenEntity> bloodOxygenRecords = [];

  // --- Header Validation (Optional but good practice) ---
  if (receivedData.isEmpty || receivedData[0] != 188 || receivedData[1] != 42) {
    print("Error: Invalid header or Command ID. Not a blood oxygen packet.");
    return [];
  }

  // Extract total data payload length from bytes 2 and 3
  int totalDataPayloadLength = bytesToShort(receivedData.sublist(2, 4));

  // Isolate the raw data payload (after the 6-byte header)
  // Ensure we don't go out of bounds if totalDataPayloadLength is larger than actual receivedData.length - 6
  Uint8List rawDataPayload = receivedData.sublist(6, (6 + totalDataPayloadLength).clamp(0, receivedData.length));

  // Each BloodOxygenEntity record is 49 bytes long
  if (rawDataPayload.length % 49 != 0) {
    print("Warning: Raw data payload length (${rawDataPayload.length}) is not a multiple of 49. Data might be incomplete or malformed.");
    return [];
  }

  int numberOfRecords = rawDataPayload.length ~/ 49;
  DateTime now = DateTime.now();
  // Get today's date at midnight (00:00:00) for calculating timestamps
  DateTime todayMidnight = DateTime(now.year, now.month, now.day);

  for (int i = 0; i < numberOfRecords; i++) {
    int recordStartOffset = i * 49; // Starting byte for the current 49-byte record chunk

    // --- Parsing the 49-byte record chunk ---

    // Byte 0 of the 49-byte chunk: dateOffsetDays
    // This is at `rawDataPayload[recordStartOffset]`
    int dateOffsetDays = rawDataPayload[recordStartOffset];

    // Bytes 1-48 of the 49-byte chunk: Interleaved Min/Max values (48 bytes total)
    List<int> currentMinArray = [];
    List<int> currentMaxArray = [];

    // Loop 24 times for 24 hours
    for (int j = 0; j < 24; j++) {
      // Calculate index within the rawDataPayload for the current hour's min/max pair
      int minByteIndex = recordStartOffset + 1 + (j * 2);
      int maxByteIndex = recordStartOffset + 1 + (j * 2) + 1;

      currentMinArray.add(rawDataPayload[minByteIndex]);
      currentMaxArray.add(rawDataPayload[maxByteIndex]);
    }

    // --- Derive Date and Unix Timestamp ---
    DateTime recordDate = todayMidnight.subtract(Duration(days: dateOffsetDays));
    String dateStr = DateFormat('yyyy-MM-dd').format(recordDate); // Format as YYYY-MM-DD
    int unixTime = recordDate.millisecondsSinceEpoch ~/ 1000; // Unix timestamp in seconds

    bloodOxygenRecords.add(BloodOxygenEntity(
      dateStr: dateStr,
      minArray: currentMinArray,
      maxArray: currentMaxArray,
      unixTime: unixTime,
    ));
  }

  return bloodOxygenRecords;
}

// Example Usage:
// void main() {
//   Uint8List receivedBytes = Uint8List.fromList([
//     188, 42, 147, 0, 152, 188, // Header (6 bytes)
//     // --- Record 1 (49 bytes) - Example from earlier (mostly zeros, dateOffsetDays = 2) ---
//     2, // dateOffsetDays = 2 (today - 2 days)
//     0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // Interleaved min/max (24 pairs of 0s)
//     0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // (continuation of 24 pairs of 0s)
//     // --- Record 2 (49 bytes) - Data where [96, 96] is min-max for 00:00 (dateOffsetDays = 1) ---
//     1, // dateOffsetDays = 1 (today - 1 day)
//     96, 96, // 00:00 - 01:00 min:96, max:96
//     97, 97, // 01:00 - 02:00 min:97, max:97
//     98, 98, // 02:00 - 03:00 min:98, max:98
//     99, 99, // 03:00 - 04:00 min:99, max:99
//     98, 98, // 04:00 - 05:00 min:98, max:98
//     96, 96, 98, 98, 0, 0, 99, 99, 99, 99, 96, 96, 98, 98, 96, 96, 0, 0, 0, 0, 97, 97, 98, 98, 98, 98, 97, 97, 99, 99, 98, 98, 0, 0, 96, 96, 97, 97, // Remaining 22 pairs
//     // --- Record 3 (49 bytes) - Data where [98, 98] is min-max for 00:00 (dateOffsetDays = 0) ---
//     0, // dateOffsetDays = 0 (today)
//     98, 98, // 00:00 - 01:00 min:98, max:98
//     99, 99, // 01:00 - 02:00 min:99, max:99
//     97, 97, 99, 99, 99, 99, 98, 98, 99, 99, 99, 99, 99, 99, 99, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 // Remaining 22 pairs
//   ]);

//   List<BloodOxygenEntity> parsedData = parseBloodOxygenData(receivedBytes);
//   print("\n--- Parsed Blood Oxygen Records ---");
//   parsedData.forEach((entity) => print(entity));
// }
// Example Usage:
/*
void main() {
 Uint8List receivedBytes = Uint8List.fromList([
    188, 42, 147, 0, 152, 188, 
    2,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 98, 98, 96, 96, 99, 99, 98, 98, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
    1,96, 96, 97, 97, 98, 98, 99, 99, 98, 98, 96, 96, 98, 98, 0, 0, 99, 99, 99, 99, 96, 96, 98, 98, 96, 96, 0, 0, 0, 0, 97, 97, 98, 98, 98, 98, 97, 97, 99, 99, 98, 98, 0, 0, 96, 96, 97, 97, 
    0, 98, 98, 99, 99, 97, 97, 99, 99, 99, 99, 98, 98, 99, 99, 99, 99, 99, 99, 99, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
  ]);
  List<BloodOxygenEntity> parsedData = parseBloodOxygenData(receivedBytes);
  parsedData.forEach((entity) => print(entity));
}
*/
//  Total Received bytes are 153
// According to Documentation Each individual blood oxygen record is 49 bytes long and 3 day data being stored. [49 * 3 = 147 ]
// Confusion If 1 hour gap data being stored then why it is 49 entries not 24 for a day 
// Answer Because it is storing the data of 1 hour and each hour has 2 points . [24 *2 = 48] and Asummation the 1 point will be the seperator
// 153 - 147 = 6 
