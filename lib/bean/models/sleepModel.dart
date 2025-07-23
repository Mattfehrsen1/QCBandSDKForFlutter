import 'dart:developer';

class SleepParser {
  /// A model class to parse and separate data from a list of integers.
  /// It separates the first 13 elements from the rest of the data.
  final List<int> _data;
  final int currentIndex;

  /// Initializes the SleepParser with the input data list.
  ///
  /// Throws [ArgumentError] if the input data is not a list of integers.
  // SleepParser(this._data ) {
  //   if (_data == null || _data.any((element) => element is! int)) {
  //     throw ArgumentError("Input data must be a list of integers.");
  //   }
  // }
  SleepParser(this._data, {required this.currentIndex});

  /// Returns the first 13 elements of the data list.
  ///
  /// Returns:
  ///   A [List<int>] containing the first 13 integers.
  List<int> getFirstThirteenElements() {
    // Ensure we don't try to access beyond the list's length
    final int endIndex = _data.length < 13 ? _data.length : 13;
    return _data.sublist(0, endIndex);
  }

  /// Returns all elements of the data list after the first 13,
  /// grouped into pairs of two. If there's an odd number of remaining
  /// elements, the last "pair" will contain a single element.
  ///
  /// Returns:
  ///   A [List<List<int>>] where each inner list is a pair of integers.
  List<List<int>> getRemainingElements() {
    // If the list has 13 or fewer elements, there are no remaining elements
    if (_data.length <= 13) {
      return [];
    }

    final List<int> remaining = _data.sublist(13);
    final List<List<int>> pairs = [];

    for (int i = 0; i < remaining.length; i += 2) {
      if (i + 1 < remaining.length) {
        // If there are at least two elements left, form a pair
        pairs.add([remaining[i], remaining[i + 1]]);
      } else {
        // If there's only one element left, add it as a single-element list
        // This case should not be processed by sumSecondValuesOfPairsStartingWithTwo
        // as it requires a second value.
        pairs.add([remaining[i]]);
      }
    }
    return pairs;
  }

  /// Calculates the total sum of the second values in pairs
  /// where the first value of the pair is 2 (Light Sleep).
  ///
  /// Only considers pairs that have exactly two elements.
  ///
  /// Returns:
  ///   An [int] representing the sum of the second values.
  int sumLightSleep() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;

    for (final pair in pairs) {
      // Ensure the pair has at least two elements to access the second value
      if (pair.length == 2 && pair[0] == 2) {
        sum += pair[1];
      }
    }
    return sum;
  }

  /// Calculates the total sum of the second values in pairs
  /// where the first value of the pair is 3 (Deep Sleep).
  ///
  /// Only considers pairs that have exactly two elements.
  ///
  /// Returns:
  ///   An [int] representing the sum of the second values.
  int sumDeepSleep() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;

    for (final pair in pairs) {
      // Ensure the pair has exactly two elements and the first element is 3
      if (pair.length == 2 && pair[0] == 3) {
        sum += pair[1];
      }
    }
    return sum;
  }

  /// Calculates the total sum of the second values in pairs
  /// where the first value of the pair is 4 (Rapid Eye Movement).
  ///
  /// Only considers pairs that have exactly two elements.
  ///
  /// Returns:
  ///   An [int] representing the sum of the second values.
  int sumRapidEyeMoment() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;

    for (final pair in pairs) {
      // Ensure the pair has exactly two elements and the first element is 4
      if (pair.length == 2 && pair[0] == 4) {
        sum += pair[1];
      }
    }
    return sum;
  }

  /// Calculates the total sum of the second values in pairs
  /// where the first value of the pair is 5 (Awake).
  ///
  /// Only considers pairs that have exactly two elements.
  ///
  /// Returns:
  ///   An [int] representing the sum of the second values.
  int sumAwake() {
    final List<List<int>> pairs = getRemainingElements();
    int sum = 0;

    for (final pair in pairs) {
      // Ensure the pair has exactly two elements and the first element is 5
      if (pair.length == 2 && pair[0] == 5) {
        sum += pair[1];
      }
    }
    return sum;
  }

  /// Calculates the total sleep duration and individual sleep stage durations.
  ///
  /// Returns:
  ///   A [Map<String, int>] containing the total duration and separate
  ///   durations for Light Sleep, Deep Sleep, Rapid Eye Movement, and Awake.
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

  Map<String, int> getSleepSummaryYesterday(
      {required List<int> yesterdayList, required List<int> todayList}) {
// Steps to follow
// 1. Remove the first 7 elements from the todayList
// 2. then Seperate the next 6 element in some markerYesterday.
// 3. Remove the first 13 elements from the yesterdayList
// 4. then Seperate the newYesterday List by using List of 6 element stored in markerYesterday. and then return the List.

// 1. Remove the first 7 elements from the todayList
    if (todayList.length >= 7) {
      todayList = todayList.sublist(7);
    } else {
      // Handle cases where todayList has fewer than 7 elements
      print(
          "Warning: todayList has less than 7 elements. No elements removed from the start.");
      todayList = []; // Or handle as per your logic
    }

    // 2. then Seperate the next 6 element in some markerYesterday.
    List<int> markerYesterday = [];
    if (todayList.length >= 6) {
      markerYesterday = todayList.sublist(0, 5);
      todayList =
          todayList.sublist(6); // Remove these 6 elements after separating
    } else {
      print("Warning: todayList has less than 6 elements for markerYesterday.");
      // markerYesterday will be what's available
      markerYesterday = List.from(todayList);
      todayList = [];
    }

    // 3. Remove the first 13 elements from the yesterdayList
    if (yesterdayList.length >= 13) {
      yesterdayList = yesterdayList.sublist(13);
    } else {
      print(
          "Warning: yesterdayList has less than 13 elements. No elements removed from the start.");
      yesterdayList = []; // Or handle as per your logic
    }

    List<List<int>> result = [];
    List<int> currentChunk = [];
    int listIndex = 0;

    while (listIndex < yesterdayList.length) {
      bool markerFound = true;
      // Check if the marker sequence is present starting from listIndex
      if (listIndex + markerYesterday.length <= yesterdayList.length) {
        for (int i = 0; i < markerYesterday.length; i++) {
          if (yesterdayList[listIndex + i] != markerYesterday[i]) {
            markerFound = false;
            break;
          }
        }
      } else {
        markerFound = false; // Not enough elements left for the marker
      }

      if (markerFound) {
        if (currentChunk.isNotEmpty) {
          result.add(currentChunk);
        }
        currentChunk = []; // Reset for the next chunk
        listIndex += markerYesterday.length; // Move past the marker
      } else {
        currentChunk.add(yesterdayList[listIndex]);
        listIndex++;
      }
    }

    // Add the last chunk if it's not empty
    if (currentChunk.isNotEmpty) {
      result.add(currentChunk);
    }

    List<List<int>> pairedList = [];

    for (int i = 0; i < result[0].length; i += 2) {
      if (i + 1 < result[0].length) {
        // Ensure there's a second element for the pair
        pairedList.add([result[0][i], result[0][i + 1]]);
      } else {
        // Handle the case if the list has an odd number of elements
        // For example, you could add the last element as a single-element list
        // pairedList.add([result[0][i]]);
        print(
            "Warning: Odd number of elements. Last element ${result[0][i]} will be ignored or handled separately.");
      }
    }
    int lightSum = 0;
    int deepSum = 0;
    int remSum = 0;
    int awakeSum = 0;

    for (final pair in pairedList) {
      // Ensure the pair has at least two elements to access the second value
      if (pair.length == 2 && pair[0] == 2) {
        lightSum += pair[1];
      }
      if (pair.length == 2 && pair[0] == 3) {
        deepSum += pair[1];
      }
      if (pair.length == 2 && pair[0] == 4) {
        remSum += pair[1];
      }
      if (pair.length == 2 && pair[0] == 5) {
        // Handle the case for Awake sleep
        awakeSum += pair[1];
      }
    }

    final int totalDuration = lightSum + deepSum + remSum + awakeSum;
    log('This is the List of $todayList}');
    log('This is the List of Yesterday ${result[0]}');
    return {
      'totalDuration': totalDuration,
      'lightSleep': lightSum,
      'deepSleep': deepSum,
      'rapidEyeMovement': remSum,
      'awake': awakeSum,
    };
  }
}
