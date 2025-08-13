import 'package:flutter_test/flutter_test.dart';
import 'package:qc_band_sdk_for_flutter/utils/feature_support.dart';

void main() {
  group('Device feature compatibility', () {
    test('Blood sugar support detection', () {
      // Mask with blood sugar bit set
      final int maskWithBloodSugar = FeatureSupport.featureBloodSugarBit;
      expect(FeatureSupport.supportsBloodSugar(maskWithBloodSugar), isTrue);

      // Mask without blood sugar bit set
      final int maskWithoutBloodSugar = 0x0;
      expect(FeatureSupport.supportsBloodSugar(maskWithoutBloodSugar), isFalse);
    });

    test('Body temperature support detection', () {
      // Mask with body temperature bit set
      final int maskWithBodyTemp = FeatureSupport.featureBodyTemperatureBit;
      expect(FeatureSupport.supportsBodyTemperature(maskWithBodyTemp), isTrue);

      // Mask without body temperature bit set
      final int maskWithoutBodyTemp = 0x0;
      expect(FeatureSupport.supportsBodyTemperature(maskWithoutBodyTemp), isFalse);
    });
  });
}


