package com.realsil.sdk.audioconnect.localplayback;

import android.os.Bundle;
import com.realsil.sdk.bbpro.internal.ModelClientCallback;

/* loaded from: classes3.dex */
public abstract class LocalPlaybackModelCallback extends ModelClientCallback {
    public void onAddOrDeleteSongToPlaylistReport(int i) {
    }

    public void onCancelTransferReport(boolean z) {
    }

    public void onDeleteAllSongReport(int i) {
    }

    public void onDeleteSingleSongReport(int i) {
    }

    public void onEnterSongTransferModeReport(boolean z) {
    }

    public void onExitSongTransferModeReport(boolean z) {
    }

    public void onGetDeviceInfoReport(Bundle bundle) {
    }

    public void onGetFileContentReport(int i, byte[] bArr) {
    }

    public void onGetFileFooterReport(int i, byte[] bArr) {
    }

    public void onGetFileHeaderReport(int i, long j) {
    }

    public void onTransferWasValidReport(int i) {
    }

    public void onWriteFailedReport() {
    }

    public void onWriteSuccessReport(int i) {
    }
}
