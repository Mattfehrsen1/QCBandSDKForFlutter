package com.king.zxing.analyze;

import com.google.zxing.DecodeHintType;
import com.google.zxing.Reader;
import com.google.zxing.qrcode.QRCodeReader;
import com.king.zxing.DecodeConfig;
import java.util.Map;

/* loaded from: classes3.dex */
public class QRCodeAnalyzer extends BarcodeFormatAnalyzer {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCodeAnalyzer() {
        this((DecodeConfig) null);
    }

    public QRCodeAnalyzer(Map<DecodeHintType, Object> map) {
        this(new DecodeConfig().setHints(map));
    }

    public QRCodeAnalyzer(DecodeConfig decodeConfig) {
        super(decodeConfig);
    }

    @Override // com.king.zxing.analyze.BarcodeFormatAnalyzer
    public Reader createReader() {
        return new QRCodeReader();
    }
}
