class StressData {
  /// The date for which the stress data was recorded.
  final DateTime date;

  /// A list of stress values. The guide suggests dividing the raw value by 10.
  final List<int> stressArray;

  /// The interval in minutes at which stress was tested.
  final int range;

  StressData({
    required this.date,
    required this.stressArray,
    required this.range,
  });

  @override
  String toString() {
    return 'StressData(date: $date, range: $range, values: ${stressArray.length})';
  }
}
