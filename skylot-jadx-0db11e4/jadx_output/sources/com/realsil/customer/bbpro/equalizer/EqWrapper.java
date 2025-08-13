package com.realsil.customer.bbpro.equalizer;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqWrapper.class */
public class EqWrapper {
    public byte sampleRate;
    public byte[] eqData;

    public EqWrapper(byte b, byte[] bArr) {
        this.sampleRate = b;
        this.eqData = bArr;
    }
}
