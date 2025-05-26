import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'qc_band_sdk_for_flutter_method_channel.dart';

abstract class QcBandSdkForFlutterPlatform extends PlatformInterface {
  /// Constructs a QcBandSdkForFlutterPlatform.
  QcBandSdkForFlutterPlatform() : super(token: _token);

  static final Object _token = Object();

  static QcBandSdkForFlutterPlatform _instance = MethodChannelQcBandSdkForFlutter();

  /// The default instance of [QcBandSdkForFlutterPlatform] to use.
  ///
  /// Defaults to [MethodChannelQcBandSdkForFlutter].
  static QcBandSdkForFlutterPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [QcBandSdkForFlutterPlatform] when
  /// they register themselves.
  static set instance(QcBandSdkForFlutterPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
