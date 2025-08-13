package com.king.zxing.analyze;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.king.zxing.DecodeConfig;
import java.util.Map;

/* loaded from: classes3.dex */
public class MultiFormatAnalyzer extends AreaRectAnalyzer {
    MultiFormatReader mReader;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MultiFormatAnalyzer() {
        this((DecodeConfig) null);
    }

    public MultiFormatAnalyzer(Map<DecodeHintType, Object> map) {
        this(new DecodeConfig().setHints(map));
    }

    public MultiFormatAnalyzer(DecodeConfig decodeConfig) {
        super(decodeConfig);
        initReader();
    }

    private void initReader() {
        this.mReader = new MultiFormatReader();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x009b A[PHI: r16
      0x009b: PHI (r16v5 com.google.zxing.Result) = 
      (r16v1 com.google.zxing.Result)
      (r16v1 com.google.zxing.Result)
      (r16v3 com.google.zxing.Result)
      (r16v3 com.google.zxing.Result)
     binds: [B:4:0x0032, B:6:0x0036, B:17:0x007e, B:19:0x0086] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.king.zxing.analyze.AreaRectAnalyzer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.Result analyze(byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r11 = r19
            r12 = r20
            long r14 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            com.google.zxing.MultiFormatReader r2 = r1.mReader     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            java.util.Map<com.google.zxing.DecodeHintType, ?> r3 = r1.mHints     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            r2.setHints(r3)     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            com.google.zxing.PlanarYUVLuminanceSource r10 = new com.google.zxing.PlanarYUVLuminanceSource     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            r16 = 0
            r2 = r10
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r13 = r10
            r10 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            boolean r2 = r1.isMultiDecode     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            com.google.zxing.Result r16 = r1.decodeInternal(r13, r2)     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc5
            if (r16 != 0) goto L9b
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            if (r2 == 0) goto L9b
            if (r16 != 0) goto L7e
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            boolean r2 = r2.isSupportVerticalCode()     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            if (r2 == 0) goto L7e
            int r2 = r0.length     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            byte[] r3 = new byte[r2]     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            r2 = 0
            r4 = 0
        L47:
            if (r4 >= r12) goto L5f
            r5 = 0
        L4a:
            if (r5 >= r11) goto L5c
            int r6 = r5 * r12
            int r6 = r6 + r12
            int r6 = r6 - r4
            int r6 = r6 + (-1)
            int r7 = r4 * r11
            int r7 = r7 + r5
            r7 = r0[r7]     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            r3[r6] = r7     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            int r5 = r5 + 1
            goto L4a
        L5c:
            int r4 = r4 + 1
            goto L47
        L5f:
            com.google.zxing.PlanarYUVLuminanceSource r0 = new com.google.zxing.PlanarYUVLuminanceSource     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            r10 = 0
            r2 = r0
            r4 = r20
            r5 = r19
            r6 = r22
            r7 = r21
            r8 = r24
            r9 = r23
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            boolean r2 = r2.isSupportVerticalCodeMultiDecode()     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            com.google.zxing.Result r0 = r1.decodeInternal(r0, r2)     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            r16 = r0
        L7e:
            if (r16 != 0) goto L9b
            com.king.zxing.DecodeConfig r0 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            boolean r0 = r0.isSupportLuminanceInvert()     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            if (r0 == 0) goto L9b
            com.google.zxing.LuminanceSource r0 = r13.invert()     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            boolean r2 = r2.isSupportLuminanceInvertMultiDecode()     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            com.google.zxing.Result r0 = r1.decodeInternal(r0, r2)     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> Lbe
            r13 = r0
            goto L9d
        L98:
            r13 = r16
            goto Lc6
        L9b:
            r13 = r16
        L9d:
            if (r13 == 0) goto Lc6
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            r0.<init>()     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            java.lang.String r4 = "Found barcode in "
            r0.append(r4)     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            long r2 = r2 - r14
            r0.append(r2)     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            java.lang.String r2 = " ms"
            r0.append(r2)     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            com.king.zxing.util.LogUtils.d(r0)     // Catch: java.lang.Throwable -> Lbe java.lang.Exception -> Lc6
            goto Lc6
        Lbe:
            r0 = move-exception
            com.google.zxing.MultiFormatReader r2 = r1.mReader
            r2.reset()
            throw r0
        Lc5:
            r13 = 0
        Lc6:
            com.google.zxing.MultiFormatReader r0 = r1.mReader
            r0.reset()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.king.zxing.analyze.MultiFormatAnalyzer.analyze(byte[], int, int, int, int, int, int):com.google.zxing.Result");
    }

    private Result decodeInternal(LuminanceSource luminanceSource, boolean z) {
        Result resultDecodeWithState;
        try {
            resultDecodeWithState = this.mReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(luminanceSource)));
        } catch (Exception unused) {
            resultDecodeWithState = null;
        }
        if (!z || resultDecodeWithState != null) {
            return resultDecodeWithState;
        }
        try {
            return this.mReader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(luminanceSource)));
        } catch (Exception unused2) {
            return resultDecodeWithState;
        }
    }
}
