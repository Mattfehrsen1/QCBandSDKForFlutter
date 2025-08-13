package com.oudmon.ble.base.bluetooth.spp;

import android.os.Bundle;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/spp/MyLocalPlaybackModelCallback.class */
public interface MyLocalPlaybackModelCallback {
    void onGetFileHeaderReport(int i, long j);

    void onGetFileContentReport(int i, byte[] bArr);

    void onGetFileFooterReport(int i, byte[] bArr);

    void onAddOrDeleteSongToPlaylistReport(int i);

    void onDeleteSingleSongReport(int i);

    void onDeleteAllSongReport(int i);

    void onGetDeviceInfoReport(Bundle bundle);

    void onEnterSongTransferModeReport(boolean z);

    void onExitSongTransferModeReport(boolean z);

    void onCancelTransferReport(boolean z);

    void onWriteSuccessReport(int i);

    void onWriteFailedReport();

    void onTransferWasValidReport(int i);
}
