class FeatureSupport {
  // Bit positions for features in a hypothetical device support bitmask.
  // These can be adjusted once the actual mapping is confirmed.
  static const int featureBloodSugarBit = 1 << 7; // 0x80
  static const int featureBodyTemperatureBit = 1 << 8; // 0x100

  static bool supportsBloodSugar(int supportMask) {
    return (supportMask & featureBloodSugarBit) != 0;
  }

  static bool supportsBodyTemperature(int supportMask) {
    return (supportMask & featureBodyTemperatureBit) != 0;
  }
}


