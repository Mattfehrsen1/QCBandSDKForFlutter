class SportData {
  int mSportType; // Movement type index
  int mStartTime; // Start time seconds
  int mDuration; // Movement duration seconds
  int mDistance; // Movement mileage meters
  int mCalorie; // Calorie consumption card
  int mStep; // Number of steps
  double mSpeed; // Speed km/h
  int mPace; // Pace
  int mHeartRate; // Average heart rate
  List<int> mHeartRateArr; // Heart rate array

  SportData({
    required this.mSportType,
    required this.mStartTime,
    required this.mDuration,
    required this.mDistance,
    required this.mCalorie,
    required this.mStep,
    required this.mSpeed,
    required this.mPace,
    required this.mHeartRate,
    required this.mHeartRateArr,
  });

  @override
  String toString() {
    return 'SportData{\n'+
        '  mSportType: $mSportType,\n'+
        '  mStartTime: $mStartTime,\n'+
        '  mDuration: $mDuration,\n'+
        '  mDistance: $mDistance,\n'+
        '  mCalorie: $mCalorie,\n'+
        '  mStep: $mStep,\n'+
        '  mSpeed: $mSpeed,\n'+
        '  mPace: $mPace,\n'+
        '  mHeartRate: $mHeartRate,\n'+
        '  mHeartRateArr: $mHeartRateArr\n'+
        '}';
  }
}
