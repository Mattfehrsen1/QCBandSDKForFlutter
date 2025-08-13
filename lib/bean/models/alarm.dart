class Alarm {
  final int index; // 0..4
  final bool enabled;
  final int hour; // 0..23
  final int minute; // 0..59
  // Sun..Sat, length 7
  final List<bool> repeatDays;

  // Optional fields for future vendor path
  final int? type;
  final String? name;

  Alarm({
    required this.index,
    required this.enabled,
    required this.hour,
    required this.minute,
    required this.repeatDays,
    this.type,
    this.name,
  }) : assert(index >= 0 && index <= 7),
       assert(hour >= 0 && hour <= 23),
       assert(minute >= 0 && minute <= 59),
       assert(repeatDays.length == 7);

  Alarm copyWith({
    int? index,
    bool? enabled,
    int? hour,
    int? minute,
    List<bool>? repeatDays,
    int? type,
    String? name,
  }) {
    return Alarm(
      index: index ?? this.index,
      enabled: enabled ?? this.enabled,
      hour: hour ?? this.hour,
      minute: minute ?? this.minute,
      repeatDays: repeatDays ?? this.repeatDays,
      type: type ?? this.type,
      name: name ?? this.name,
    );
  }
}


