package com.oudmon.ble.base.bluetooth.spp;

import android.os.Bundle;

/* loaded from: classes3.dex */
public interface MyLocalPlaybackModelCallback {
    void onAddOrDeleteSongToPlaylistReport(int i);

    void onCancelTransferReport(boolean z);

    void onDeleteAllSongReport(int i);

    void onDeleteSingleSongReport(int i);

    void onEnterSongTransferModeReport(boolean z);

    void onExitSongTransferModeReport(boolean z);

    void onGetDeviceInfoReport(Bundle bundle);

    void onGetFileContentReport(int i, byte[] bArr);

    void onGetFileFooterReport(int i, byte[] bArr);

    void onGetFileHeaderReport(int i, long j);

    void onTransferWasValidReport(int i);

    void onWriteFailedReport();

    void onWriteSuccessReport(int i);
}
