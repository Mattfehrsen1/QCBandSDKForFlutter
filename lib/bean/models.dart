import 'dart:typed_data';

// Import the models that are used in this file
import 'models/sport_model.dart';

// Assuming these are external dependencies or part of your core setup
// You might need to import flutter_blue_plus here if you use its types directly.
// import 'package:flutter_blue_plus/flutter_blue_plus.dart';

// Export all models
export 'models/blood_oxygen_entity.dart';
export 'models/read_heart_rate_response.dart';
export 'models/sleepModel.dart';
export 'models/sport_model.dart';
export 'models/stress_model.dart';

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

// Type-safe BLE packet interfaces and enums

/// Enum for sport/workout types with type safety
enum SportType {
  walking(1, 'Walking'),
  running(2, 'Running'),
  cycling(3, 'Cycling'),
  swimming(4, 'Swimming'),
  yoga(5, 'Yoga'),
  basketball(6, 'Basketball'),
  football(7, 'Football'),
  tennis(8, 'Tennis');

  const SportType(this.id, this.displayName);
  
  final int id;
  final String displayName;
  
  static SportType? fromId(int id) {
    for (SportType type in SportType.values) {
      if (type.id == id) return type;
    }
    return null;
  }
}

/// Enum for BLE command IDs with type safety
enum BleCommandId {
  // Device commands
  deviceBattery(3, 'Device Battery'),
  stepDataToday(72, 'Step Data Today'),
  stepDataDetails(67, 'Step Data Details'),
  
  // Workout commands
  sportMode(0x5A, 'Sport Mode Control'),
  getSportMode(95, 'Get Sport Mode'),
  sportData(18, 'Sport Data'),
  workoutStatusStart(238, 'Workout Status Start'),
  workoutStatusEnd(239, 'Workout Status End'),
  
  // Heart rate commands
  heartRateData(21, 'Heart Rate Data'),
  realTimeHeartRate(30, 'Real-time Heart Rate'),
  
  // HRV and stress
  hrv(57, 'HRV Data'),
  stressData(56, 'Stress Data');

  const BleCommandId(this.id, this.description);
  
  final int id;
  final String description;
  
  static BleCommandId? fromId(int id) {
    for (BleCommandId cmd in BleCommandId.values) {
      if (cmd.id == id) return cmd;
    }
    return null;
  }
}

/// Base class for all BLE packet responses
abstract class BlePacketResponse {
  final BleCommandId commandId;
  final DateTime timestamp;
  final bool isValid;
  final String? errorMessage;
  
  const BlePacketResponse({
    required this.commandId,
    required this.timestamp,
    required this.isValid,
    this.errorMessage,
  });
  
  /// Validates the response data
  bool validate();
  
  /// Converts to a Map for JSON serialization
  Map<String, dynamic> toMap();
}

/// Type-safe workout packet response
class WorkoutPacketResponse extends BlePacketResponse {
  final SportData? data;
  final SportType? sportType;
  
  const WorkoutPacketResponse({
    required super.commandId,
    required super.timestamp,
    required super.isValid,
    super.errorMessage,
    this.data,
    this.sportType,
  });
  
  @override
  bool validate() {
    if (!isValid) return false;
    if (data == null) return false;
    
    // Validate sport data
    if (data!.mSportType < 0 || data!.mSportType > 100) return false;
    if (data!.mDuration < 0) return false;
    if (data!.mDistance < 0) return false;
    if (data!.mCalorie < 0) return false;
    if (data!.mStep < 0) return false;
    
    return true;
  }
  
  @override
  Map<String, dynamic> toMap() {
    return {
      'commandId': commandId.id,
      'timestamp': timestamp.toIso8601String(),
      'isValid': isValid,
      'errorMessage': errorMessage,
      'sportType': sportType?.displayName,
      'data': data?.toMap(),
    };
  }
}

/// Type-safe status packet response
class StatusPacketResponse extends BlePacketResponse {
  final bool isWorkoutActive;
  final String statusMessage;
  
  const StatusPacketResponse({
    required super.commandId,
    required super.timestamp,
    required super.isValid,
    super.errorMessage,
    required this.isWorkoutActive,
    required this.statusMessage,
  });
  
  @override
  bool validate() {
    return isValid && statusMessage.isNotEmpty;
  }
  
  @override
  Map<String, dynamic> toMap() {
    return {
      'commandId': commandId.id,
      'timestamp': timestamp.toIso8601String(),
      'isValid': isValid,
      'errorMessage': errorMessage,
      'isWorkoutActive': isWorkoutActive,
      'statusMessage': statusMessage,
    };
  }
}

/// Extension for SportData to add type safety
extension SportDataExtension on SportData {
  SportType? get sportType => SportType.fromId(mSportType);
  
  Map<String, dynamic> toMap() {
    return {
      'sportType': mSportType,
      'startTime': mStartTime,
      'duration': mDuration,
      'distance': mDistance,
      'calorie': mCalorie,
      'step': mStep,
      'speed': mSpeed,
      'pace': mPace,
      'heartRate': mHeartRate,
      'heartRateArr': mHeartRateArr,
    };
  }
  
  bool get isValidData {
    return mSportType >= 0 && 
           mDuration >= 0 && 
           mDistance >= 0 && 
           mCalorie >= 0 && 
           mStep >= 0;
  }
}