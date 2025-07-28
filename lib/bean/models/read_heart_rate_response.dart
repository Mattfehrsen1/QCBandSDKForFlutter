class ReadHeartRateResponse {
  bool isComplete = false;
  int mUtcTime = 0;
  List<int> mHeartRateArray = [];
  int currentHrArrayFilledSize = 0;

  ReadHeartRateResponse();

  // Placeholder method to fix build error.
  // The actual implementation will depend on how the device sends heart rate data packets.
  bool acceptData(List<int> data) {
    // Assuming the data packet contains heart rate values.
    // This is a simplified placeholder logic.
    if (data.length > 5) {
      // Example: extract HR value and add to array
      int hrValue = data[5];
      mHeartRateArray.add(hrValue);
      currentHrArrayFilledSize++;
    }

    // Placeholder: Assume the data transfer is complete if we receive a small packet
    // or a specific end marker. The actual logic will be more complex.
    if (data.length < 20) {
      isComplete = true;
    }

    return isComplete;
  }
}
