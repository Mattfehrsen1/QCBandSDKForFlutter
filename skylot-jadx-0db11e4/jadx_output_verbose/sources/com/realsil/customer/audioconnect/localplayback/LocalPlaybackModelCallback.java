package com.realsil.customer.audioconnect.localplayback;

import android.os.Bundle;
import com.realsil.customer.bbpro.internal.ModelClientCallback;

/* loaded from: qc_sdk_20250409.aar:libs/rtk-audioconnect-localplayback-1.0.9.jar:com/realsil/customer/audioconnect/localplayback/LocalPlaybackModelCallback.class */
public abstract class LocalPlaybackModelCallback extends ModelClientCallback {
    public void onGetFileHeaderReport(int i, long j) {
    }

    public void onGetFileContentReport(int i, byte[] bArr) {
    }

    public void onGetFileFooterReport(int i, byte[] bArr) {
    }

    public void onAddOrDeleteSongToPlaylistReport(int i) {
    }

    public void onDeleteSingleSongReport(int i) {
    }

    public void onDeleteAllSongReport(int i) {
    }

    public void onGetDeviceInfoReport(Bundle bundle) {
    }

    public void onEnterSongTransferModeReport(boolean z) {
    }

    public void onExitSongTransferModeReport(boolean z) {
    }

    public void onCancelTransferReport(boolean z) {
    }

    public void onWriteSuccessReport(int i) {
    }

    public void onWriteFailedReport() {
    }

    public void onTransferWasValidReport(int i) {
    }
}
