import 'dart:developer';

class SleepParser {
  final List<int> _data;
  final int currentIndex;

  SleepParser(this._data, {required this.currentIndex});

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
