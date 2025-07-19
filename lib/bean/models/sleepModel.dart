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

  /// Returns all elements of the data list after the first 13.
  ///
  /// Returns:
  ///   A [List<int>] containing the remaining integers.
  List<int> getRemainingElements() {
    // If the list has 13 or fewer elements, there are no remaining elements
    if (_data.length <= 13) {
      return [];
    }
    return _data.sublist(13);
  }
}
