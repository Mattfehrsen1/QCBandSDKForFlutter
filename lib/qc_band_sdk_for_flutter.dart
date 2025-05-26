
import 'qc_band_sdk_for_flutter_platform_interface.dart';

class QcBandSdkForFlutter {
  Future<String?> getPlatformVersion() {
    return QcBandSdkForFlutterPlatform.instance.getPlatformVersion();
  }
}
