package com.king.zxing.analyze;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.king.zxing.DecodeConfig;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class BarcodeFormatAnalyzer extends AreaRectAnalyzer {
    private Reader mReader;

    public abstract Reader createReader();

    public BarcodeFormatAnalyzer(Map<DecodeHintType, Object> map) {
        this(new DecodeConfig().setHints(map));
    }

    public BarcodeFormatAnalyzer(DecodeConfig decodeConfig) {
        super(decodeConfig);
        initReader();
    }

    private void initReader() {
        this.mReader = createReader();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0094 A[PHI: r16
      0x0094: PHI (r16v5 com.google.zxing.Result) = (r16v1 com.google.zxing.Result), (r16v1 com.google.zxing.Result), (r16v3 com.google.zxing.Result) binds: [B:5:0x002f, B:7:0x0033, B:19:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
            com.google.zxing.Reader r2 = r1.mReader
            if (r2 == 0) goto Lc5
            long r14 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbe
            com.google.zxing.PlanarYUVLuminanceSource r10 = new com.google.zxing.PlanarYUVLuminanceSource     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbe
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
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbe
            boolean r2 = r1.isMultiDecode     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbe
            com.google.zxing.Result r16 = r1.decodeInternal(r13, r2)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbe
            if (r16 != 0) goto L94
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            if (r2 == 0) goto L94
            if (r16 != 0) goto L79
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            boolean r2 = r2.isSupportVerticalCode()     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            if (r2 == 0) goto L79
            int r2 = r0.length     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            byte[] r3 = new byte[r2]     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            r2 = 0
            r4 = 0
        L44:
            if (r4 >= r12) goto L5c
            r5 = 0
        L47:
            if (r5 >= r11) goto L59
            int r6 = r5 * r12
            int r6 = r6 + r12
            int r6 = r6 - r4
            int r6 = r6 + (-1)
            int r7 = r4 * r11
            int r7 = r7 + r5
            r7 = r0[r7]     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            r3[r6] = r7     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            int r5 = r5 + 1
            goto L47
        L59:
            int r4 = r4 + 1
            goto L44
        L5c:
            com.google.zxing.PlanarYUVLuminanceSource r0 = new com.google.zxing.PlanarYUVLuminanceSource     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            r10 = 0
            r2 = r0
            r4 = r20
            r5 = r19
            r6 = r22
            r7 = r21
            r8 = r24
            r9 = r23
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            boolean r2 = r2.isSupportVerticalCodeMultiDecode()     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            com.google.zxing.Result r16 = r1.decodeInternal(r0, r2)     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
        L79:
            com.king.zxing.DecodeConfig r0 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            boolean r0 = r0.isSupportLuminanceInvert()     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            if (r0 == 0) goto L94
            com.google.zxing.LuminanceSource r0 = r13.invert()     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            com.king.zxing.DecodeConfig r2 = r1.mDecodeConfig     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            boolean r2 = r2.isSupportLuminanceInvertMultiDecode()     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            com.google.zxing.Result r0 = r1.decodeInternal(r0, r2)     // Catch: java.lang.Exception -> L91 java.lang.Throwable -> Lb7
            r13 = r0
            goto L96
        L91:
            r13 = r16
            goto Lbf
        L94:
            r13 = r16
        L96:
            if (r13 == 0) goto Lbf
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            r0.<init>()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            java.lang.String r4 = "Found barcode in "
            r0.append(r4)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            long r2 = r2 - r14
            r0.append(r2)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            java.lang.String r2 = " ms"
            r0.append(r2)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            com.king.zxing.util.LogUtils.d(r0)     // Catch: java.lang.Throwable -> Lb7 java.lang.Exception -> Lbf
            goto Lbf
        Lb7:
            r0 = move-exception
            com.google.zxing.Reader r2 = r1.mReader
            r2.reset()
            throw r0
        Lbe:
            r13 = 0
        Lbf:
            com.google.zxing.Reader r0 = r1.mReader
            r0.reset()
            goto Lc6
        Lc5:
            r13 = 0
        Lc6:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.king.zxing.analyze.BarcodeFormatAnalyzer.analyze(byte[], int, int, int, int, int, int):com.google.zxing.Result");
    }

    private Result decodeInternal(LuminanceSource luminanceSource, boolean z) {
        Result resultDecode;
        try {
            resultDecode = this.mReader.decode(new BinaryBitmap(new HybridBinarizer(luminanceSource)), this.mHints);
        } catch (Exception unused) {
            resultDecode = null;
        }
        if (!z || resultDecode != null) {
            return resultDecode;
        }
        try {
            return this.mReader.decode(new BinaryBitmap(new GlobalHistogramBinarizer(luminanceSource)), this.mHints);
        } catch (Exception unused2) {
            return resultDecode;
        }
    }
}
