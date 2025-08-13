package com.realsil.customer.bbpro.vendor;

import androidx.annotation.NonNull;
import com.realsil.customer.bbpro.internal.ModelClientCallback;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.bbpro.model.KeyMmiSettings;
import com.realsil.customer.bbpro.multilink.MultilinkInfo;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vendor/VendorModelCallback.class */
public abstract class VendorModelCallback extends ModelClientCallback {
    public void onDeviceInfoChanged(int i, @NonNull DeviceInfo deviceInfo) {
    }

    public void onCreateConnectionResponse(byte b) {
    }

    public void onDisconnectResponse(byte b) {
    }

    public void onProfileConnected(byte b, byte[] bArr) {
    }

    public void onProfileDisconnected(byte b, byte[] bArr) {
    }

    public void onPlayRingtongResponse(byte b) {
    }

    public void onPlayRingtongResult(byte b) {
    }

    public void onKeyeventReported(int i) {
    }

    public void onKeyMapSettingsReported(List<KeyMmiSettings> list) {
    }

    public void onRwsKeyMapSettingsReported(List<KeyMmiSettings> list) {
    }

    public void onListeningModeCycleReported(int i) {
    }

    public void onMultilinkInfoChanged(MultilinkInfo multilinkInfo) {
    }
}
