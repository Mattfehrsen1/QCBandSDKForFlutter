package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.bbpro.internal.ModelClientCallback;
import com.realsil.sdk.bbpro.model.DspParams;

/* loaded from: classes3.dex */
public abstract class EqModelCallback extends ModelClientCallback {
    public void onAudioEqBasicInfoReport(byte b, int i, EqInfo eqInfo) {
    }

    public void onAudioEqEntryIndexReport(byte b, EqEntryIndex eqEntryIndex) {
    }

    public void onAudioEqEntryNumberReport(byte b, int i, int i2) {
    }

    public void onAudioEqIndexParamsReport(byte b, EqIndex eqIndex) {
    }

    public void onAudioEqIndexReport(byte b, int i, int i2) {
    }

    public void onAudioEqStateReport(byte b, byte b2) {
    }

    public void onClearAudioEqResponse(byte b) {
    }

    public void onDisableAudioEqResponse(byte b) {
    }

    public void onDspAudioEqReport(byte b, byte b2, byte[] bArr) {
    }

    public void onDspAudioEqReport(byte b, AudioEq audioEq) {
    }

    public void onDspParamsChanged(DspParams dspParams) {
    }

    public void onDspStatusChanged(byte b) {
    }

    public void onEnableAudioEqResponse(byte b) {
    }

    public void onGamingModeStatusChanged(boolean z) {
    }

    public void onMicAudioEqBasicInfoReport(byte b, int i, EqInfo eqInfo) {
    }

    public void onResetAudioEqIndexResponse(byte b) {
    }

    public void onSetAudioEqIndexParamsResponse(byte b, int i, int i2, int i3) {
    }

    public void onSetAudioEqIndexResponse(byte b, int i) {
    }
}
