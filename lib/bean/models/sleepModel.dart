import 'dart:developer';
import '../../utils/sleep_separator_parser.dart';

class SleepParser {
  final List<int> _data;
  final int currentIndex;
  final int? actualDayNumber;
  final Map<int, int> _separators;

  SleepParser(this._data, {required this.currentIndex, this.actualDayNumber}) 
    : _separators = SleepSeparatorParser.extractDaySeparatorsWithValidation(_data);

  /// Create a parser that auto-detects all available days
  factory SleepParser.multiDay(List<int> data) {
    return SleepParser(
      data,
      currentIndex: 0, // Default to today
      actualDayNumber: null, // Will be determined when accessing specific days
    );
  }

  /// Get available days in this data
  List<int> getAvailableDays() {
    return SleepSeparatorParser.getAvailableDays(_separators);
  }

  /// Get data for a specific day (new method)
  List<int> getDayData(int dayNumber) {
    // FORCE DEBUG LOGGING - Use print instead of log to ensure it appears
    print("🔥 getDayData($dayNumber) called with ${_data.length} bytes data and separators: $_separators");
    final result = SleepSeparatorParser.extractDayData(_data, dayNumber, _separators);
    print("🔥 getDayData($dayNumber) returning ${result.length} bytes: ${result.take(10).toList()}${result.length > 10 ? '...' : ''}");
    return result;
  }

  /// Check if data exists for a specific day
  bool hasDayData(int dayNumber) {
    return _separators.containsKey(dayNumber);
  }

  /// Get sleep summary for a specific day
  Map<String, int> getSleepSummaryForDay(int dayNumber) {
    // FORCE DEBUG LOGGING - Use print instead of log to ensure it appears
    print("🔍 getSleepSummaryForDay($dayNumber) called");
    
    if (!hasDayData(dayNumber)) {
      print("❌ No data available for day $dayNumber - separators: $_separators");
      return {
        'totalDuration': 0,
        'lightSleep': 0,
        'deepSleep': 0,
        'rapidEyeMovement': 0,
        'awake': 0,
      };
    }

    print("✅ Day $dayNumber found in separators, extracting data...");
    
    // EMERGENCY DEBUG: Let's force debug output here
    print("🚨 EMERGENCY DEBUG: About to call getDayData($dayNumber)");
    print("🚨 Separators available: ${_separators.keys.toList()}");
    print("🚨 Data length: ${_data.length} bytes");
    
    final dayData = getDayData(dayNumber);
    print("📊 Extracted ${dayData.length} bytes for day $dayNumber: ${dayData.take(10).toList()}${dayData.length > 10 ? '...' : ''}");
    
    if (dayData.isEmpty) {
      print("❌ Day $dayNumber data is empty after extraction");
      return {
        'totalDuration': 0,
        'lightSleep': 0,
        'deepSleep': 0,
        'rapidEyeMovement': 0,
        'awake': 0,
      };
    }

    // Process the day-specific data with robust parsing
    log("🔧 Processing ${dayData.length} bytes of day data: ${dayData.take(20).toList()}${dayData.length > 20 ? '...' : ''}");
    
    int lightSleep = 0;
    int deepSleep = 0;
    int rapidEyeMovement = 0;
    int awake = 0;
    int validPairs = 0;
    int skippedBytes = 0;

    // More robust parsing: scan for valid sleep type + duration pairs
    for (int i = 0; i < dayData.length - 1; i++) {
      final type = dayData[i];
      final duration = dayData[i + 1];
      
      // Check if this looks like a valid sleep data pair
      if (type >= 2 && type <= 5 && duration > 0 && duration <= 120) {
        switch (type) {
          case 2: 
            lightSleep += duration; 
            log("  ✅ Light sleep: +${duration}min");
            break;
          case 3: 
            deepSleep += duration; 
            log("  ✅ Deep sleep: +${duration}min");
            break;
          case 4: 
            rapidEyeMovement += duration; 
            log("  ✅ REM sleep: +${duration}min");
            break;
          case 5: 
            awake += duration; 
            log("  ✅ Awake: +${duration}min");
            break;
        }
        validPairs++;
        i++; // Skip the duration byte since we just processed it
      } else {
        log("  ⚠️  Skipping invalid pair [${type}, ${duration}] at position $i");
        skippedBytes++;
      }
    }
    
    log("🔧 Parsed $validPairs valid pairs, skipped $skippedBytes bytes");

    final int totalDuration = lightSleep + deepSleep + rapidEyeMovement + awake;

    return {
      'totalDuration': totalDuration,
      'lightSleep': lightSleep,
      'deepSleep': deepSleep,
      'rapidEyeMovement': rapidEyeMovement,
      'awake': awake,
    };
  }

  List<int> getFirstThirteenElements() {
    final int endIndex = _data.length < 13 ? _data.length : 13;
    return _data.sublist(0, endIndex);
  }

  List<List<int>> getRemainingElements() {
    if (_data.length <= 13) {
      return [];
    }
    final List<int> remaining = _data.sublist(13);
    final List<List<int>> pairs = [];
    for (int i = 0; i < remaining.length; i += 2) {
      if (i + 1 < remaining.length) {
        pairs.add([remaining[i], remaining[i + 1]]);
      } else {
        pairs.add([remaining[i]]); // Handle odd number of elements gracefully
      }
    }
    return pairs;
  }

  int sumLightSleep() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 2) {
        sum += pair[1];
      }
    }
    return sum;
  }

  int sumDeepSleep() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 3) {
        sum += pair[1];
      }
    }
    return sum;
  }

  int sumRapidEyeMoment() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 4) {
        sum += pair[1];
      }
    }
    return sum;
  }

  int sumAwake() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;
    for (final pair in pairs) {
      if (pair.length == 2 && pair[0] == 5) {
        sum += pair[1];
      }
    }
    return sum;
  }

  Map<String, int> getSleepSummary() {
    final int lightSleep = sumLightSleep();
    final int deepSleep = sumDeepSleep();
    final int rapidEyeMoment = sumRapidEyeMoment();
    final int awake = sumAwake();

    final int totalDuration = lightSleep + deepSleep + rapidEyeMoment + awake;

    return {
      'totalDuration': totalDuration,
      'lightSleep': lightSleep,
      'deepSleep': deepSleep,
      'rapidEyeMovement': rapidEyeMoment,
      'awake': awake,
    };
  }

  /// Debug method to print detected separators
  void debugSeparators() {
    SleepSeparatorParser.debugPrintSeparators(_data);
  }

  Map<String, int> getSleepSummaryYesterday({
    required List<int> yesterdayList,
    required List<int> todayList,
  }) {
    // 1. Extract the marker from todayList (next 6 elements after first 7)
    List<int> markerYesterday = [];
    if (todayList.length >= 7 + 6) {
      // Ensure enough elements for header + marker
      markerYesterday = todayList.sublist(7, 7 + 6);
    } else {
      log("Error: todayList does not contain enough elements for the 6-byte marker after the first 7 bytes.");
      return {}; // Return empty or handle error appropriately
    }

    // 2. Remove the first 13 elements from yesterdayList
    List<int> processableYesterdayList = [];
    if (yesterdayList.length >= 13) {
      processableYesterdayList = yesterdayList.sublist(13);
    } else {
      log("Error: yesterdayList has fewer than 13 elements. Cannot extract sleep data.");
      return {}; // Return empty or handle error appropriately
    }

    // 3. Split the newYesterdayList by using the 6-element marker
    List<List<int>> splitYesterdayData =
        _splitListByMarker(processableYesterdayList, markerYesterday);

    // Assuming the actual sleep data for yesterday is the first chunk after the marker.
    // Adjust this logic if the structure of splitYesterdayData is different.
    if (splitYesterdayData.isEmpty) {
      log("No sleep data found in yesterday's list after splitting by marker.");
      return {};
    }

    List<int> actualYesterdaySleepData =
        splitYesterdayData[0]; // Assuming the first chunk is the relevant one

    // 4. Pair up the actualYesterdaySleepData for summation
    List<List<int>> pairedList = [];
    for (int i = 0; i < actualYesterdaySleepData.length; i += 2) {
      if (i + 1 < actualYesterdaySleepData.length) {
        pairedList.add(
            [actualYesterdaySleepData[i], actualYesterdaySleepData[i + 1]]);
      } else {
        // Handle odd number of elements if necessary, or simply ignore the last one
        log("Warning: Odd number of elements in yesterday's sleep data. Last element ${actualYesterdaySleepData[i]} might be ignored.");
      }
    }

    int lightSum = 0;
    int deepSum = 0;
    int remSum = 0;
    int awakeSum = 0;

    for (final pair in pairedList) {
      if (pair.length == 2) {
        if (pair[0] == 2) {
          lightSum += pair[1];
        } else if (pair[0] == 3) {
          deepSum += pair[1];
        } else if (pair[0] == 4) {
          remSum += pair[1];
        } else if (pair[0] == 5) {
          awakeSum += pair[1];
        }
      }
    }

    final int totalDuration = lightSum + deepSum + remSum + awakeSum;

    log('Sleep data for today (first 13 removed): ${todayList.sublist(7 + 6)}'); // For debugging, showing actual sleep data part of today
    log('Marker for yesterday: $markerYesterday');
    log('Sleep data for yesterday (after split and pairing): $actualYesterdaySleepData');

    return {
      'totalDuration': totalDuration,
      'lightSleep': lightSum,
      'deepSleep': deepSum,
      'rapidEyeMovement': remSum,
      'awake': awakeSum,
    };
  }

  // Helper method to split a list by a given marker
  List<List<int>> _splitListByMarker(List<int> data, List<int> marker) {
    List<List<int>> result = [];
    int start = 0;
    while (start < data.length) {
      int markerIndex = -1;
      for (int i = start; i <= data.length - marker.length; i++) {
        bool found = true;
        for (int j = 0; j < marker.length; j++) {
          if (data[i + j] != marker[j]) {
            found = false;
            break;
          }
        }
        if (found) {
          markerIndex = i;
          break;
        }
      }

      if (markerIndex != -1) {
        result.add(data.sublist(start, markerIndex));
        start = markerIndex + marker.length;
      } else {
        result.add(data.sublist(start));
        break;
      }
    }
    return result;
  }
}