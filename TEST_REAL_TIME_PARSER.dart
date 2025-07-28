import 'lib/utils/real_time_sport_parser.dart';

void main() {
  print('🧪 Testing Real-Time Sport Parser');
  print('===================================\n');

  // Test 1: Perfect real-time packet
  print('🔬 Test 1: Perfect real-time packet (5-minute run)');
  List<int> perfectPacket = RealTimeSportParser.createTestPacket(
    sportType: 7,    // Running
    errorStatus: 0,  // Normal
    duration: 300,   // 5 minutes
    heartRate: 140,  // 140 bpm
    steps: 600,      // 600 steps
    distance: 500,   // 500 meters
    calories: 35,    // 35 calories
  );
  
  print('Generated packet: ${perfectPacket.map((b) => b.toRadixString(16).padLeft(2, '0')).join(' ')}');
  var result1 = RealTimeSportParser.parseRealTimePacket(perfectPacket);
  print('Result: ${result1}\n');

  // Test 2: Walking workout
  print('🔬 Test 2: Walking workout (10 minutes)');
  List<int> walkingPacket = RealTimeSportParser.createTestPacket(
    sportType: 4,    // Walking
    errorStatus: 0,  // Normal
    duration: 600,   // 10 minutes
    heartRate: 90,   // 90 bpm
    steps: 800,      // 800 steps
    distance: 600,   // 600 meters
    calories: 25,    // 25 calories
  );
  
  var result2 = RealTimeSportParser.parseRealTimePacket(walkingPacket);
  print('Result: ${result2}\n');

  // Test 3: Not wearing device
  print('🔬 Test 3: Device not properly worn');
  List<int> notWearingPacket = RealTimeSportParser.createTestPacket(
    sportType: 7,    // Running
    errorStatus: 1,  // Not wearing
    duration: 120,   // 2 minutes
    heartRate: 0,    // No heart rate
    steps: 0,        // No steps
    distance: 0,     // No distance
    calories: 0,     // No calories
  );
  
  var result3 = RealTimeSportParser.parseRealTimePacket(notWearingPacket);
  print('Result: ${result3}\n');

  // Test 4: Malformed packet (too short)
  print('🔬 Test 4: Malformed packet (too short)');
  List<int> shortPacket = [0x07, 0x00, 0x2C, 0x01]; // Only 4 bytes
  
  var result4 = RealTimeSportParser.parseRealTimePacket(shortPacket);
  print('Result: ${result4}\n');

  // Test 5: Edge case values
  print('🔬 Test 5: Edge case values');
  List<int> edgePacket = RealTimeSportParser.createTestPacket(
    sportType: 0,      // Minimum sport type
    errorStatus: 0,    // Normal
    duration: 0,       // Just started
    heartRate: 255,    // Max heart rate
    steps: 0xFFFFFF,   // Max 24-bit steps
    distance: 0xFFFFFF, // Max 24-bit distance
    calories: 0xFFFFFF, // Max 24-bit calories
  );
  
  var result5 = RealTimeSportParser.parseRealTimePacket(edgePacket);
  print('Result: ${result5}\n');

  // Test 6: Packet format detection
  print('🔬 Test 6: Packet format detection');
  print('Perfect packet detected as real-time: ${RealTimeSportParser.isRealTimeSportPacket(perfectPacket)}');
  print('Short packet detected as real-time: ${RealTimeSportParser.isRealTimeSportPacket(shortPacket)}');
  print('Random data detected as real-time: ${RealTimeSportParser.isRealTimeSportPacket([0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF])}');

  print('\n✅ Real-Time Sport Parser Testing Complete!');
} 