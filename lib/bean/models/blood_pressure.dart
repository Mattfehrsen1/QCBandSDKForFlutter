import 'dart:math';
import 'dart:typed_data';
import 'package:intl/intl.dart';

// Helper functions to replicate the Java utility classes
class BloodPressureBle {
  parseData(List<int> bloodPressureBleHeart) {
    return {};
  }
}


class CalcBloodPressureByHeart {
  static const int minBpDiff = 37;
  static const int maxBpDiff = 43;
  static const int maxSbp = 120;
  static const int minSbp = 100;
  static const int hrUpper = 85;
  static const int hrLower = 65;
  static const double hrBpRate = 0.45;
  static const int hrDefaultValue = 80;
  static const int ageDefault = 25;
  static const List<int> age = [20, 30, 40, 50, 60];
  static const List<int> ageBpCof = [-10, 5, 15, 20, 25, 30];

  static int gReserveSbp = 0;
  static int gLastSbp = 0;
  static int gLastHr = 0;
  static int gReserveAge = -1;
  static int lastSbp = 0;
  static int lastDbp = 0;
  
  static final Random _random = Random();

  static int calSbp(int hr, int ageInput) {
    int sbp;
    // The following variables were not used but are here for completeness
    // int age = ageInput;
    // int hr = hrInput;
    if (gReserveSbp > 0 && ageInput == gReserveAge) {
      sbp = gLastSbp;
      if (hr > gLastHr) {
        sbp = (sbp + (hr - gLastHr) * hrBpRate).toInt();
        gLastSbp = (gReserveSbp + sbp + gLastSbp) ~/ 3;
        sbp -= _random.nextInt(4);
      }
      gLastHr = hr;
    } else {
      int index = 0;
      for (int i = 0; i < CalcBloodPressureByHeart.age.length; i++) {
        if (ageInput < CalcBloodPressureByHeart.age[i]) {
          index = i;
          break;
        }
      }
      sbp = minSbp + _random.nextInt(maxSbp - minSbp + 1);
      sbp += ageBpCof[index];
      gReserveSbp = sbp;
      if (hr < hrLower) {
        sbp = (sbp - (hrLower - hr) * hrBpRate).toInt();
      } else {
        sbp = (sbp + (hr - hrLower) * hrBpRate).toInt();
      }
      sbp = (gReserveSbp + sbp) ~/ 2;
      gLastSbp = sbp;
      gLastHr = hr;
      gReserveAge = ageInput;
    }
    return sbp;
  }

  static int calDbp(int sbp) {
    return sbp - minBpDiff + _random.nextInt(maxBpDiff - minBpDiff);
  }
}