import 'dart:typed_data';
import 'package:intl/intl.dart';

// Helper functions to replicate the Java utility classes
class DataParseUtilsBlePressure {
  static int byteArrayToInt(Uint8List b) {
    int s0 = b[0] & 0xFF;
    int s1 = b[1] & 0xFF;
    int s2 = b[2] & 0xFF;
    int s3 = b[3] & 0xFF;
    int s = s0 | (s1 << 8) | (s2 << 16) | (s3 << 24);
    return s;
  }
}

class DataTransferUtilsBlePressure {
  static String getHexString(Uint8List data) {
    if (data == null) {
      return '';
    }
    StringBuffer s = StringBuffer();
    for (int i = 0; i < data.length; i++) {
      String hex = data[i].toRadixString(16);
      if (hex.length == 1) {
        s.write('0');
      }
      s.write(hex);
    }
    return s.toString();
  }
}

// Replicates the BlePressure Java class
// class BlePressure {
//   late int time;
//   late int dbp;
//   late int sbp;

//   BlePressure(this.time, this.sbp, this.dbp);

//   // Optional: A method to get a human-readable time from the timestamp
//   DateTime get dateTime => DateTime.fromMillisecondsSinceEpoch(time * 1000);

//   // Optional: For easy printing of the object
//   @override
//   String toString() {
//     final formatter = DateFormat('yyyy-MM-dd HH:mm:ss');
//     return 'BlePressure(time: ${formatter.format(dateTime)}, sbp: $sbp, dbp: $dbp)';
//   }
// }

// Replicates the ReadBlePressureRsp Java class
// Note: Assuming `BaseRspCmd` is a base class for responses, we'll
// make a simple equivalent for this example.
abstract class BaseRspCmdBlePressure {
  bool acceptData(Uint8List data);
}

class ReadBlePressureRsp extends BaseRspCmdBlePressure {
  int _mCount = 0;
  List<int> _mValueList = [];

  @override
  bool acceptData(Uint8List data) {
    // The provided data is in a different format than the class expects.
    // The Java class expects a headerless byte array.
    // We will simulate the expected data format by slicing the input.
    // The user's provided input is: [13, 0, 25, 7, 24, 30, ...]
    // The Java class expects: [ 0, 25, 7, 24, 30, ...]
    if (data.isEmpty) {
      return false;
    }

    // Simulate the slicing based on the user's expected data format
    Uint8List dataToParse = data.sublist(1);

    Uint8List timeBytes = dataToParse.sublist(0, 4);
    if (DataTransferUtilsBlePressure.getHexString(timeBytes) == 'ffffffff') {
      _mCount = 0;
      return false;
    }

    _mCount++;
    int timeStamp = DataParseUtilsBlePressure.byteArrayToInt(timeBytes);

    // The original Java code adjusts for timezone, which we can replicate.
    // Here we'll just use UTC for simplicity to match the `fromMillisecondsSinceEpoch` behavior
    // that assumes UTC unless specified. The original Java code used a Calendar object
    // to get the timezone offset, which is more complex to replicate exactly.
    // For now, we'll omit the offset calculation.
    // long timeOffset = (this.mCalendar.get(15) / 1000);
    // timeStamp -= timeOffset;

    int dbp = dataToParse[4] & 0xFF;

    _mValueList.add(dbp);

    if (_mCount >= 50) {
      _mCount = 0;
      return false;
    }

    return true;
  }

  List<int> getValueList() {
    return _mValueList;
  }
}
