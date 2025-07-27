import 'dart:math';
import 'dart:typed_data';
import 'package:intl/intl.dart';

// Helper functions to replicate the Java utility classes
class BloodPressureBle {
  parseData(List<int> bloodPressureBleHeart,userAge) {
    List<Map> jsonDataParse = [];
    // Remove the 0 in the list
    bloodPressureBleHeart.removeWhere((item) => item == 0);
    print(
        'This is Length of the BloodPressureBle Heart $bloodPressureBleHeart');
    for (var i = 0; i < bloodPressureBleHeart.length; i++) {
      int heartRate = bloodPressureBleHeart[i];
     
      int sbp1 = CalcBloodPressureByHeart.cal_sbp(heartRate, userAge);
      int dbp1 = CalcBloodPressureByHeart.cal_dbp(sbp1);
      jsonDataParse.add({
        "systolic blood Pressure": sbp1,
        "diastolic blood pressure": dbp1,
      });
    }
    return jsonDataParse;
  }
}


class CalcBloodPressureByHeart {
  static const int MIN_BP_DIFF = 37;
  static const int MAX_BP_DIFF = 43;
  static const int MAX_SBP = 120;
  static const int MIN_SBP = 100;
  static const int HR_UPPER = 85;
  static const int HR_LOWER = 65;
  static const double HR_BP_RATE = 0.45;
  static const int HR_DEFAULT_VALUE = 80;
  static const int AGE_DEFAULT = 25;

  static const List<int> AGE = [20, 30, 40, 50, 60];
  static const List<int> AGE_BP_COF = [-10, 5, 15, 20, 25, 30];

  static int g_reserve_sbp = 0;
  static int g_last_sbp = 0;
  static int g_last_hr = 0;
  static int g_reserve_age = -1;
  static int last_sbp = 0;
  static int last_dbp = 0;

  static int cal_sbp(int hr, int age) {
    int sbp;
    final random = Random();

    if (g_reserve_sbp > 0 && age == g_reserve_age) {
      sbp = g_last_sbp;
      if (hr > g_last_hr) {
        sbp = (sbp + (hr - g_last_hr) * HR_BP_RATE).toInt();
        g_last_sbp = ((g_reserve_sbp + sbp + g_last_sbp)/ 3).toInt();
        sbp -= (random.nextDouble() * 4.0).toInt();
      }
      g_last_hr = hr;
    } else {
      int index = 0;
      for (int i = 0; i < AGE.length; i++) {
        if (age < AGE[i]) {
          index = i;
          break;
        }
      }
      sbp = MIN_SBP + (random.nextDouble() * (MAX_SBP - MIN_SBP + 1)).toInt();
      sbp += AGE_BP_COF[index];
      g_reserve_sbp = sbp;
      if (hr < HR_LOWER) {
        sbp = (sbp - (HR_LOWER - hr) * HR_BP_RATE).toInt();
      } else {
        sbp = (sbp + (hr - HR_LOWER) * HR_BP_RATE).toInt();
      }
      sbp = ((g_reserve_sbp + sbp)/ 2).toInt();
      g_last_sbp = sbp;
      g_last_hr = hr;
      g_reserve_age = age;
    }
    return sbp;
  }

  static int cal_dbp(int sbp) {
    final random = Random();
    return sbp - MIN_BP_DIFF +
        (random.nextDouble() * (MAX_BP_DIFF - MIN_BP_DIFF)).toInt();
  }
}