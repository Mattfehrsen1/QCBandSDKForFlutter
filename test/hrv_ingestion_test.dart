import 'dart:typed_data';

import 'package:flutter_test/flutter_test.dart';
import 'package:qc_band_sdk_for_flutter/utils/qc_band_sdk_const.dart';
import 'package:qc_band_sdk_for_flutter/utils/resolve_util.dart';

void main() {
  group('HRV ingestion (cmd 57) assembler', () {
    test('assembles frames, reports progress, and emits normalized 288-point result', () {
      // Header: size=3 frames total (0=header, 1=first, 2=last), range=30
      final header = Uint8List.fromList([
        QcBandSdkConst.cmdHrv, 0x00, 0x03, 0x1E, // 57, 0, size=3, range=30
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0x00, // placeholder bytes
        0x92, // crc (ignored)
      ]);
      final h = ResolveUtil.handleIncomingDataHrv(header);
      expect(h['dataType'], equals('HRVData'));
      expect(h['end'], isFalse);
      final hData = (h['data'] as Map);
      expect(hData['progress'], equals(0));
      expect(hData['totalExpected'], equals(3 * 13)); // buffer = size * 13

      // First data: offsetDays=1, payload 12 bytes (values 1..12)
      final first = Uint8List.fromList([
        QcBandSdkConst.cmdHrv, 0x01, 0x01,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
        0x00, // crc (ignored)
      ]);
      final f1 = ResolveUtil.handleIncomingDataHrv(first);
      expect(f1['end'], isFalse);
      final f1Data = (f1['data'] as Map);
      expect(f1Data['progress'], equals(12));

      // Duplicate first data should not advance progress
      final f1dup = ResolveUtil.handleIncomingDataHrv(first);
      expect((f1dup['data'] as Map)['progress'], equals(12));

      // Next (and last) frame: sub=2 (since size=3 ⇒ last index is 2), payload 13 bytes (values 13..25)
      final next = Uint8List.fromList([
        QcBandSdkConst.cmdHrv, 0x02,
        13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
        0x00,
      ]);
      final last = ResolveUtil.handleIncomingDataHrv(next);
      expect(last['end'], isTrue);
      final lastData = (last['data'] as Map);

      // Verify metadata
      expect(lastData['range'], equals(30));
      expect(lastData['totalValues'], equals(25)); // 12 + 13
      expect(lastData['hrvArray'], isA<List>());
      final arr = (lastData['hrvArray'] as List).cast<int>();
      expect(arr.length, equals(288));

      // Placement: for range=30, samples map every 6 slots
      // arr[0] = 1, arr[6] = 2, ..., arr[24*6] = 25 (if within bounds)
      expect(arr[0], equals(1));
      expect(arr[6], equals(2));
      expect(arr[12], equals(3));
      expect(arr[18], equals(4));
      expect(arr[24], equals(5));
      // spot check a later one
      expect(arr[6 * 10], equals(11));
    });
  });
}


