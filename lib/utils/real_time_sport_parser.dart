import 'dart:typed_data';
import '../bean/models/sport_model.dart';

/// Real-time sport data parser for QC Band live workout data
/// Based on Android SDK documentation for real-time sport data packets
class RealTimeSportParser {
  
  /// Parse real-time sport data packet
  /// 
  /// Expected packet structure (14 bytes):
  /// byte[0]: Sport type
  /// byte[1]: Error status (0=normal, 1=not wearing, etc.)
  /// byte[2-3]: Duration (seconds) - 16-bit little-endian
  /// byte[4]: Heart rate (bpm)
  /// byte[5-7]: Steps - 24-bit little-endian
  /// byte[8-10]: Distance (meters) - 24-bit little-endian
  /// byte[11-13]: Calories - 24-bit little-endian
  static Map<String, dynamic> parseRealTimePacket(List<int> value) {
    try {
      // Validate packet length
      if (value.length < 14) {
        print('🚨 Real-time sport packet too short. Expected 14 bytes, got ${value.length}');
        return {
          "method": "parseRealTimePacket",
          "error": "Invalid packet length: ${value.length}",
          "data": null,
        };
      }

      // Debug logging
      print('🔍 REAL-TIME SPORT PACKET ANALYSIS:');
      print('   Raw bytes: $value');
      print('   Hex: ${value.map((b) => b.toRadixString(16).padLeft(2, '0')).join(' ')}');
      
      // Extract fields according to real-time structure
      int sportType = value[0];
      int errorStatus = value[1];
      
      // Duration: 16-bit little-endian at bytes 2-3
      int duration = parseUint16LE(value, 2);
      
      // Heart rate: single byte at position 4
      int heartRate = value[4];
      
      // Steps: 24-bit little-endian at bytes 5-7
      int steps = parseUint24LE(value, 5);
      
      // Distance: 24-bit little-endian at bytes 8-10
      int distance = parseUint24LE(value, 8);
      
      // Calories: 24-bit little-endian at bytes 11-13
      int calories = parseUint24LE(value, 11);

      print('📊 PARSED REAL-TIME DATA:');
      print('   Sport Type: $sportType');
      print('   Error Status: $errorStatus (0=normal, 1=not wearing)');
      print('   Duration: ${duration}s');
      print('   Heart Rate: ${heartRate} bpm');
      print('   Steps: $steps');
      print('   Distance: ${distance}m');
      print('   Calories: $calories');

      // Validate data ranges
      String? validationError = _validateRealTimeData(
        sportType, errorStatus, duration, heartRate, steps, distance, calories
      );
      
      if (validationError != null) {
        print('⚠️ Real-time data validation warning: $validationError');
      }

      // Create SportData object with real-time values
      SportData sportData = SportData(
        mSportType: sportType,
        mStartTime: DateTime.now().millisecondsSinceEpoch ~/ 1000, // Current timestamp
        mDuration: duration,
        mDistance: distance,
        mCalorie: calories,
        mStep: steps,
        mSpeed: _calculateSpeed(distance, duration),
        mPace: _calculatePace(distance, duration),
        mHeartRate: heartRate,
        mHeartRateArr: [], // Real-time data doesn't include HR array
      );

      return {
        "method": "parseRealTimePacket",
        "data": sportData,
        "errorStatus": errorStatus,
        "isWearing": errorStatus == 0,
        "timestamp": DateTime.now().millisecondsSinceEpoch,
      };

    } catch (e) {
      print('💥 Error parsing real-time sport packet: $e');
      return {
        "method": "parseRealTimePacket",
        "error": "Parse error: $e",
        "data": null,
      };
    }
  }

  /// Parse 16-bit unsigned integer in little-endian format
  static int parseUint16LE(List<int> data, int offset) {
    if (offset + 1 >= data.length) return 0;
    return data[offset] | (data[offset + 1] << 8);
  }

  /// Parse 24-bit unsigned integer in little-endian format
  static int parseUint24LE(List<int> data, int offset) {
    if (offset + 2 >= data.length) return 0;
    return data[offset] | (data[offset + 1] << 8) | (data[offset + 2] << 16);
  }

  /// Parse 32-bit unsigned integer in little-endian format
  static int parseUint32LE(List<int> data, int offset) {
    if (offset + 3 >= data.length) return 0;
    return data[offset] | 
           (data[offset + 1] << 8) | 
           (data[offset + 2] << 16) | 
           (data[offset + 3] << 24);
  }

  /// Validate real-time data ranges
  static String? _validateRealTimeData(
    int sportType, int errorStatus, int duration, int heartRate, 
    int steps, int distance, int calories
  ) {
    List<String> issues = [];

    if (sportType < 0 || sportType > 50) {
      issues.add('Sport type out of range: $sportType');
    }

    if (errorStatus < 0 || errorStatus > 10) {
      issues.add('Error status out of range: $errorStatus');
    }

    if (duration < 0 || duration > 86400) { // Max 24 hours
      issues.add('Duration out of range: $duration seconds');
    }

    if (heartRate < 0 || heartRate > 250) {
      issues.add('Heart rate out of range: $heartRate bpm');
    }

    if (steps < 0 || steps > 1000000) {
      issues.add('Steps out of range: $steps');
    }

    if (distance < 0 || distance > 100000) { // Max 100km
      issues.add('Distance out of range: $distance meters');
    }

    if (calories < 0 || calories > 10000) {
      issues.add('Calories out of range: $calories');
    }

    return issues.isEmpty ? null : issues.join(', ');
  }

  /// Calculate speed in km/h from distance (meters) and duration (seconds)
  static double _calculateSpeed(int distanceMeters, int durationSeconds) {
    if (durationSeconds <= 0) return 0.0;
    double speedMs = distanceMeters / durationSeconds; // m/s
    return speedMs * 3.6; // Convert to km/h
  }

  /// Calculate pace in seconds per km
  static int _calculatePace(int distanceMeters, int durationSeconds) {
    if (distanceMeters <= 0) return 0;
    double paceSecondsPerMeter = durationSeconds / distanceMeters;
    return (paceSecondsPerMeter * 1000).round(); // seconds per km
  }

  /// Check if this looks like a real-time sport data packet
  static bool isRealTimeSportPacket(List<int> value) {
    if (value.length < 14) return false;
    
    // Basic heuristics for real-time packets
    int sportType = value[0];
    int errorStatus = value[1];
    
    // Sport type should be in reasonable range
    if (sportType < 0 || sportType > 50) return false;
    
    // Error status should be in known range
    if (errorStatus < 0 || errorStatus > 10) return false;
    
    return true;
  }

  /// Create test packet for development/testing
  static List<int> createTestPacket({
    int sportType = 7, // Running
    int errorStatus = 0, // Normal
    int duration = 300, // 5 minutes
    int heartRate = 120, // 120 bpm
    int steps = 500, // 500 steps
    int distance = 400, // 400 meters
    int calories = 25, // 25 calories
  }) {
    List<int> packet = List.filled(14, 0);
    
    packet[0] = sportType;
    packet[1] = errorStatus;
    
    // Duration - 16-bit LE
    packet[2] = duration & 0xFF;
    packet[3] = (duration >> 8) & 0xFF;
    
    // Heart rate
    packet[4] = heartRate;
    
    // Steps - 24-bit LE
    packet[5] = steps & 0xFF;
    packet[6] = (steps >> 8) & 0xFF;
    packet[7] = (steps >> 16) & 0xFF;
    
    // Distance - 24-bit LE
    packet[8] = distance & 0xFF;
    packet[9] = (distance >> 8) & 0xFF;
    packet[10] = (distance >> 16) & 0xFF;
    
    // Calories - 24-bit LE
    packet[11] = calories & 0xFF;
    packet[12] = (calories >> 8) & 0xFF;
    packet[13] = (calories >> 16) & 0xFF;
    
    return packet;
  }
} 