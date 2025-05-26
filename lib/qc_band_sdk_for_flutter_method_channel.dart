import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'qc_band_sdk_for_flutter_platform_interface.dart';

/// An implementation of [QcBandSdkForFlutterPlatform] that uses method channels.
class MethodChannelQcBandSdkForFlutter extends QcBandSdkForFlutterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('qc_band_sdk_for_flutter');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
