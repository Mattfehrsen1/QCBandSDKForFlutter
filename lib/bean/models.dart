import 'dart:typed_data';
import 'dart:async'; // Required for StreamController if used elsewhere

// Assuming these are external dependencies or part of your core setup
// You might need to import flutter_blue_plus here if you use its types directly.
// import 'package:flutter_blue_plus/flutter_blue_plus.dart';

// Helper to convert 4 little-endian bytes to int (Unix timestamp)
// This should be accessible to ReadHeartRateResponse
int bytesToLittleEndianInt(Uint8List bytes, int offset) {
  final byteData = ByteData.view(bytes.buffer, offset, 4);
  return byteData.getInt32(0, Endian.little);
}

// Represents the parsed heart rate response for a full day
class ReadHeartRateResponse {
  int? mUtcTime; // Unix timestamp for the day's heart rate data
  List<int> mHeartRateArray = List.filled(288, 0); // Array to store all 288 heart rate values
  int currentHrArrayFilledSize = 0; // Track filled size for progress/completion

  // Method to process each incoming 16-byte data packet
  // Returns true if the entire day's heart rate data has been received and parsed, false otherwise.
  bool acceptData(Uint8List data) {
    if (data.length != 16 || data[0] != 21) {
      print('Received invalid HR response packet: $data');
      return false; // Not a valid HR response packet
    }

    final packetIndex = data[1]; // The second byte indicates the packet sequence/type

    if (packetIndex == 0) {
      // This is the header/metadata packet [21, 0, ...]
      print('Received HR header packet: $data');
      return false; // Response not complete yet
    } else if (packetIndex == 1) {
      // This is the first data packet [21, 1, ..., 9 HR values]
      print('Received first HR data packet: $data');
      mUtcTime = bytesToLittleEndianInt(data, 2); // Parse timestamp
      final hrValues = data.sublist(6, 15); // Extract 9 HR values
      for (int i = 0; i < hrValues.length; i++) {
        if (i < mHeartRateArray.length) {
          mHeartRateArray[i] = hrValues[i];
        }
      }
      currentHrArrayFilledSize += hrValues.length;
    } else if (packetIndex > 1) {
      // These are subsequent data packets [21, N, ..., 14 HR values]
      print('Received subsequent HR data packet (index $packetIndex): $data');
      final hrValues = data.sublist(2, 16); // Extract 14 HR values
      // Calculate the starting index in mHeartRateArray for these values
      final arrayStartIndex = 9 + (packetIndex - 2) * 14;
      for (int i = 0; i < hrValues.length; i++) {
        if (arrayStartIndex + i < mHeartRateArray.length) {
          mHeartRateArray[arrayStartIndex + i] = hrValues[i];
        }
      }
      currentHrArrayFilledSize += hrValues.length;
    }

    // Determine if the entire response is complete.
    // Assuming completeness when `currentHrArrayFilledSize` fills the array.
    // In a real scenario, this might also be based on a specific `endFlag` byte
    // or a total count from a header packet.
    return currentHrArrayFilledSize >= mHeartRateArray.length;
  }
}