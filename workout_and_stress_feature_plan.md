# Next Steps: Workout and Stress Feature Implementation

This document outlines the current progress and the next actionable steps for implementing the workout (sport mode) and stress monitoring features in the Flutter SDK.

---

## 1. Workout Feature (Sport Mode)

### Current Status: In Progress

**What we have achieved so far:**
- **SDK Core:**
    - Command constants for starting/stopping workouts have been added.
    - A `SportData` model has been created to structure workout data.
    - SDK methods to build and parse workout-related commands are in place.
- **Example App UI:**
    - "Start Workout" and "End Workout" buttons have been added to the device screen.
    - Handler functions (`startWorkout`, `endWorkout`) that send the commands to the device have been implemented.

**What we are working on now:**
- The final step is to listen for, receive, and display the workout data sent back from the device.

### Actionable Tasks

1.  **Implement Bluetooth Notification Listener:**
    - **File:** `example/lib/screens/device_screen.dart`
    - **Task:** Add a listener to the `_bluetoothCharacteristicNotification` stream within the `initState` method. This listener will be responsible for handling all incoming data for this feature.

2.  **Filter and Parse Workout Data:**
    - **File:** `example/lib/screens/device_screen.dart` (inside the new listener)
    - **Task:**
        - Check if the incoming data packet is a sport mode response (e.g., by checking `value[1] == QcBandSdkConst.getSportMode`).
        - If it is, pass the raw data to `QCBandSDK.DataParsingWithData` to get a `SportData` object.

3.  **Display Parsed Data:**
    - **File:** `example/lib/screens/device_screen.dart`
    - **Task:** Display the fields of the parsed `SportData` object in the UI (e.g., using a Snackbar or updating a text widget) to confirm the data is being received correctly.

4.  **End-to-End Testing:**
    - **Task:** Run the app, connect to the device, and test the full sequence:
        - Tap "Start Workout".
        - Verify the command is sent and the device responds.
        - Check that the app receives and displays the workout data.
        - Tap "End Workout" and verify the corresponding response.

---

## 2. Stress Monitoring Feature (Not Started)

### Actionable Tasks

1.  **Analysis:** Review the `android_sdk_guide.md` to identify the command constants, request format, and response structure for stress monitoring.
2.  **Constants:** Add the necessary stress-related command constants to `lib/utils/qc_band_sdk_const.dart`.
3.  **Data Model:** Create a `StressData` model class in the `lib/bean/models/` directory to represent the stress data.
4.  **SDK Command Function:** Add a function to `lib/qc_band_sdk_for_flutter.dart` to construct and send the stress data request command.
5.  **Parsing Logic:** Update `lib/utils/resolve_util.dart` and the `DataParsingWithData` switch in the main SDK file to handle and parse the stress data response.
6.  **UI Integration:** Add a button and handler function to `example/lib/screens/device_screen.dart` to trigger the stress data request and display the results.
