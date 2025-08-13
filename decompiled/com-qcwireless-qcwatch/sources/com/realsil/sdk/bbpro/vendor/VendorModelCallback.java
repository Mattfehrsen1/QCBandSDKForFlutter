package com.realsil.sdk.bbpro.vendor;

import com.realsil.sdk.bbpro.internal.ModelClientCallback;
import com.realsil.sdk.bbpro.model.DeviceInfo;
import com.realsil.sdk.bbpro.model.KeyMmiSettings;
import com.realsil.sdk.bbpro.multilink.MultilinkInfo;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class VendorModelCallback extends ModelClientCallback {
    public void onCreateConnectionResponse(byte b) {
    }

    public void onDeviceInfoChanged(int i, DeviceInfo deviceInfo) {
    }

    public void onDisconnectResponse(byte b) {
    }

    public void onKeyMapSettingsReported(List<KeyMmiSettings> list) {
    }

    public void onKeyeventReported(int i) {
    }

    public void onListeningModeCycleReported(int i) {
    }

    public void onMultilinkInfoChanged(MultilinkInfo multilinkInfo) {
    }

    public void onPlayRingtongResponse(byte b) {
    }

    public void onPlayRingtongResult(byte b) {
    }

    public void onProfileConnected(byte b, byte[] bArr) {
    }

    public void onProfileDisconnected(byte b, byte[] bArr) {
    }

    public void onRwsKeyMapSettingsReported(List<KeyMmiSettings> list) {
    }
}
