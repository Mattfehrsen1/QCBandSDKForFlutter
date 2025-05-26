import 'package:flutter_test/flutter_test.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter_platform_interface.dart';
import 'package:qc_band_sdk_for_flutter/qc_band_sdk_for_flutter_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockQcBandSdkForFlutterPlatform
    with MockPlatformInterfaceMixin
    implements QcBandSdkForFlutterPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final QcBandSdkForFlutterPlatform initialPlatform = QcBandSdkForFlutterPlatform.instance;

  test('$MethodChannelQcBandSdkForFlutter is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelQcBandSdkForFlutter>());
  });

  test('getPlatformVersion', () async {
    QcBandSdkForFlutter qcBandSdkForFlutterPlugin = QcBandSdkForFlutter();
    MockQcBandSdkForFlutterPlatform fakePlatform = MockQcBandSdkForFlutterPlatform();
    QcBandSdkForFlutterPlatform.instance = fakePlatform;

    expect(await qcBandSdkForFlutterPlugin.getPlatformVersion(), '42');
  });
}
