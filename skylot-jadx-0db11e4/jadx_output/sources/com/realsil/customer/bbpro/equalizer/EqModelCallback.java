package com.realsil.customer.bbpro.equalizer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.realsil.customer.bbpro.internal.ModelClientCallback;
import com.realsil.customer.bbpro.model.DspParams;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/equalizer/EqModelCallback.class */
public abstract class EqModelCallback extends ModelClientCallback {
    public void onDspStatusChanged(byte b) {
    }

    public void onDspParamsChanged(DspParams dspParams) {
    }

    public void onDspAudioEqReport(byte b, byte b2, byte[] bArr) {
    }

    public void onDspAudioEqReport(byte b, AudioEq audioEq) {
    }

    public void onClearAudioEqResponse(byte b) {
    }

    public void onAudioEqIndexReport(byte b, int i, int i2) {
    }

    public void onAudioEqBasicInfoReport(byte b, int i, @NonNull EqInfo eqInfo) {
    }

    public void onMicAudioEqBasicInfoReport(byte b, int i, @NonNull EqInfo eqInfo) {
    }

    public void onAudioEqEntryIndexReport(byte b, @NonNull EqEntryIndex eqEntryIndex) {
    }

    public void onGamingModeStatusChanged(boolean z) {
    }

    public void onAudioEqStateReport(byte b, byte b2) {
    }

    public void onAudioEqEntryNumberReport(byte b, int i, int i2) {
    }

    public void onAudioEqIndexParamsReport(byte b, @Nullable EqIndex eqIndex) {
    }

    public void onSetAudioEqIndexResponse(byte b, int i) {
    }

    public void onSetAudioEqIndexParamsResponse(byte b, int i, int i2, int i3) {
    }

    public void onEnableAudioEqResponse(byte b) {
    }

    public void onDisableAudioEqResponse(byte b) {
    }

    public void onResetAudioEqIndexResponse(byte b) {
    }
}
