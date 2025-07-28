# Guide: Replicating the Multi-Day Sleep Sync Functionality

This document outlines how to correctly implement the multi-day sleep data synchronization feature in a new project by referencing the corrected implementation in `example/lib/screens/device_screen.dart`.

The core of the solution is to fetch data sequentially, one day at a time, and to correctly use the paired data responses as required by the device's specific BLE protocol.

## Key Implementation Steps

A developer should model their implementation on the `sleepDetailData` function. Here are the critical components to replicate:

### 1. The Main Orchestration Function: `sleepDetailData()`

This is the main entry point and contains the complete logic. It should be an `async` function.

**Core Logic:**
- Use a `for` loop to iterate from `day = 0` (Today) to `day = 6` (6 days ago).
- Inside the loop, `await` the result of fetching data for each day before proceeding to the next. This is the most important step to prevent race conditions.
- Maintain a variable outside the loop (e.g., `dayNPlus1Data`) to hold the data from the *previous* iteration, as it's needed for parsing the current day's historical data.

```dart
// Reference: sleepDetailData in device_screen.dart

Future<void> sleepDetailData() async {
  List<int> dayNPlus1Data = []; // Holds data from the previous day's response (i.e., day N+1)

  for (int day = 0; day < 7; day++) {
    try {
      // 1. Fetch the raw data for the current day in the loop
      final List<int> dayNData = await fetchSingleDayResponse(day);

      // 2. Parse the data based on the day
      if (day == 0) {
        // Today's data is self-contained
        final summary = SleepParser.getSleepSummaryToday(dayNData);
        print('Sleep Summary for day 0 (Today): $summary');
      } else {
        // Historical data requires the previous day's response for context
        final summary = SleepParser.getSleepSummaryYesterday(dayNData, dayNPlus1Data);
        print('Sleep Summary for day $day: $summary');
      }

      // 3. Store the current response to be used in the next iteration
      dayNPlus1Data = dayNData;

    } catch (e) {
      print('Error fetching sleep data for day $day: $e');
      // Handle error, maybe break the loop or log it
    }
  }
}
```

### 2. The BLE Response Handler: `fetchSingleDayResponse(int day)`

This helper function is a robust pattern for sending a BLE command and waiting for a single, specific, asynchronous response.

**Key Features:**
- **`Completer`:** Bridges the command (write) and the response (notification).
- **`timeout`:** Prevents the app from hanging if the device fails to respond.
- **Stream Subscription Management:** The listener is created just before the command is sent and is cancelled immediately in the `whenComplete` block to prevent leaks and stop listening for stale data.
- **Command Generation:** Uses `QCBandSDK.instance.getSleepData(day: day)` to create the correct command payload.

```dart
// Reference: fetchSingleDayResponse in device_screen.dart

Future<List<int>> fetchSingleDayResponse(int day) async {
  final completer = Completer<List<int>>();
  StreamSubscription? subscription;

  // Listen for the next valid sleep data packet
  subscription = _secondbluetoothCharacteristicNotification.value.listen((value) {
    if (value.isNotEmpty && value[1] == QcBandSdkConst.getSleepData && !completer.isCompleted) {
      completer.complete(value);
    }
  });

  // Clean up the listener when the future completes or times out
  completer.future.whenComplete(() {
    subscription?.cancel();
  });

  // Send the command to the device
  await QCBandSDK.instance.getSleepData(day: day);
  print('Command for sleep data for day $day requested successfully.');

  // Wait for the response, with a timeout
  return completer.future.timeout(const Duration(seconds: 5));
}
```

### 3. The Parsing Logic: Understanding the Protocol

This is the most critical piece of domain knowledge to transfer. The developer *must* understand how the `SleepParser` is used.

- **`SleepParser.getSleepSummaryToday(day0Data)`:** Only used for **Day 0**. It parses the data from a single response.
- **`SleepParser.getSleepSummaryYesterday(dayNData, dayNPlus1Data)`:** Used for **all other days (1-6)**. It requires two arguments:
    1.  `dayNData`: The raw byte response from the request for the current day (e.g., Day 2).
    2.  `dayNPlus1Data`: The raw byte response from the request for the *next* day (e.g., Day 1). The end of Day 2's data is marked within the data for Day 1.

By following this structure, a developer can successfully integrate the multi-day sleep sync feature into any application using this plugin.
