class SleepParser {
  /// A model class to parse and separate data from a list of integers.
  /// It separates the first 13 elements from the rest of the data.
  final List<int> _data;

  /// Initializes the SleepParser with the input data list.
  ///
  /// Throws [ArgumentError] if the input data is not a list of integers.
  SleepParser(this._data) {
    if (_data == null || _data.any((element) => element is! int)) {
      throw ArgumentError("Input data must be a list of integers.");
    }
  }

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
}
