package com.oudmon.ble.base.bluetooth.spp;

import android.app.Application;
import android.os.Bundle;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.spp.bean.MySongInfo;
import com.realsil.sdk.audioconnect.localplayback.LocalPlaybackConfiguration;
import com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback;
import com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelClient;
import com.realsil.sdk.audioconnect.localplayback.LocalPlaybackTransferEngine;
import com.realsil.sdk.audioconnect.localplayback.LocalPlaybackTransferEngineCallback;
import com.realsil.sdk.audioconnect.localplayback.LocalPlaybackUtil;
import com.realsil.sdk.audioconnect.localplayback.entity.SongInfo;
import com.realsil.sdk.bbpro.core.BeeError;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class RtkMusicSpp {
    private static RtkMusicSpp rtkMusicSpp;
    LocalPlaybackModelCallback mLocalPlaybackModelCallback = new LocalPlaybackModelCallback() { // from class: com.oudmon.ble.base.bluetooth.spp.RtkMusicSpp.2
        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetFileHeaderReport(int i, long j) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetFileHeaderReport(i, j);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetFileContentReport(int i, byte[] bArr) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetFileContentReport(i, bArr);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetFileFooterReport(int i, byte[] bArr) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetFileFooterReport(i, bArr);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onAddOrDeleteSongToPlaylistReport(int i) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onAddOrDeleteSongToPlaylistReport(i);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onDeleteSingleSongReport(int i) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onDeleteSingleSongReport(i);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onDeleteAllSongReport(int i) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onDeleteAllSongReport(i);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetDeviceInfoReport(Bundle bundle) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetDeviceInfoReport(bundle);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onEnterSongTransferModeReport(boolean z) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onEnterSongTransferModeReport(z);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onExitSongTransferModeReport(boolean z) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onExitSongTransferModeReport(z);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onCancelTransferReport(boolean z) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onCancelTransferReport(z);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onWriteSuccessReport(int i) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onWriteSuccessReport(i);
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onWriteFailedReport() {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onWriteFailedReport();
            }
        }

        @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onTransferWasValidReport(int i) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onTransferWasValidReport(i);
            }
        }
    };
    private LocalPlaybackModelClient mLocalPlaybackModelClient;
    private MyLocalPlaybackModelCallback myLocalPlaybackModelCallback;

    public static RtkMusicSpp getInstance() {
        if (rtkMusicSpp == null) {
            synchronized (RtkMusicSpp.class) {
                if (rtkMusicSpp == null) {
                    rtkMusicSpp = new RtkMusicSpp();
                }
            }
        }
        return rtkMusicSpp;
    }

    public void initLocalPlaybackModelClient(Application application) {
        LocalPlaybackModelClient.initialize(application);
    }

    public void initModelClient() {
        if (this.mLocalPlaybackModelClient == null) {
            this.mLocalPlaybackModelClient = LocalPlaybackModelClient.getInstance();
            registerModelCallback();
        }
    }

    private void registerModelCallback() {
        this.mLocalPlaybackModelClient.registerCallback(this.mLocalPlaybackModelCallback);
    }

    private void unRegisterModelCallback() {
        LocalPlaybackModelClient localPlaybackModelClient = this.mLocalPlaybackModelClient;
        if (localPlaybackModelClient != null) {
            localPlaybackModelClient.unregisterCallback(this.mLocalPlaybackModelCallback);
        }
    }

    public void registerMyMusicCallback(MyLocalPlaybackModelCallback myLocalPlaybackModelCallback) {
        this.myLocalPlaybackModelCallback = myLocalPlaybackModelCallback;
    }

    public Integer getFileListData(int i) {
        if (this.mLocalPlaybackModelClient == null) {
            XLog.i("spp 连接失败");
            return -1;
        }
        if (i == RtkSppConstants.FILE_TYPE_HEADER_BIN) {
            BeeError fileListData = this.mLocalPlaybackModelClient.getFileListData((byte) 0);
            XLog.i(Integer.valueOf(fileListData.code));
            return Integer.valueOf(fileListData.code);
        }
        if (i == RtkSppConstants.FILE_TYPE_NAME_BIN) {
            BeeError fileListData2 = this.mLocalPlaybackModelClient.getFileListData((byte) 1);
            XLog.i(Integer.valueOf(fileListData2.code));
            return Integer.valueOf(fileListData2.code);
        }
        return 0;
    }

    public boolean getConnectState() {
        LocalPlaybackModelClient localPlaybackModelClient = this.mLocalPlaybackModelClient;
        if (localPlaybackModelClient == null) {
            XLog.i("没有初始化");
            return false;
        }
        int connectState = localPlaybackModelClient.getVendorClient().getConnectState();
        XLog.i(Integer.valueOf(connectState));
        return connectState >= 263;
    }

    public String getCacheDirPath() {
        return LocalPlaybackConfiguration.getInstance().getCacheDirPath();
    }

    public void setCacheDirPath(String str) {
        LocalPlaybackConfiguration.getInstance().setCacheDirPath(str);
    }

    public File createNewHeaderFile(String str) {
        return LocalPlaybackUtil.createNewFile(str, "header.bin");
    }

    public File createNewNameFile(String str) {
        return LocalPlaybackUtil.createNewFile(str, "name.bin");
    }

    public void writeContentToFile(File file, byte[] bArr) throws IOException {
        LocalPlaybackUtil.writeContentToFile(file, bArr);
    }

    public int deleteSingleSongFromDevice(int i, byte[] bArr) {
        return this.mLocalPlaybackModelClient.deleteSingleSongFromDevice(i, bArr).code;
    }

    public int deleteAllSongFromDevice() {
        return this.mLocalPlaybackModelClient.deleteAllSongFromDevice().code;
    }

    public void updateTransferState(int i) throws InterruptedException {
        LocalPlaybackTransferEngine.getInstance().updateTransferState(i);
    }

    public int queryDeviceInfo() {
        return LocalPlaybackModelClient.getInstance().queryDeviceInfo().code;
    }

    public int enterTransferMode(File file) {
        return LocalPlaybackModelClient.getInstance().enterSongTransferMode(file).code;
    }

    public void startTransferInit(int i, int i2) {
        LocalPlaybackTransferEngine.getInstance().init(i, i2);
    }

    public void startTransfer(File file, final MyLocalPlaybackTransferEngineCallback myLocalPlaybackTransferEngineCallback) {
        LocalPlaybackTransferEngine.getInstance().startTransfer(file);
        LocalPlaybackTransferEngine.getInstance().setTransferCallback(new LocalPlaybackTransferEngineCallback() { // from class: com.oudmon.ble.base.bluetooth.spp.RtkMusicSpp.1
            @Override // com.realsil.sdk.audioconnect.localplayback.LocalPlaybackTransferEngineCallback
            public void onTransferProgressChanged(int i) {
                MyLocalPlaybackTransferEngineCallback myLocalPlaybackTransferEngineCallback2 = myLocalPlaybackTransferEngineCallback;
                if (myLocalPlaybackTransferEngineCallback2 != null) {
                    myLocalPlaybackTransferEngineCallback2.onTransferProgressChanged(i);
                }
            }
        });
    }

    public int stopTransfer() {
        return LocalPlaybackModelClient.getInstance().cancelTransfer().code;
    }

    public int exitSongTransferMode() {
        return LocalPlaybackModelClient.getInstance().exitSongTransferMode().code;
    }

    public List<MySongInfo> getSongInfoList(File file, File file2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        List<SongInfo> songInfoList = LocalPlaybackUtil.getSongInfoList(file, file2);
        LocalPlaybackDataPool.getInstance().clear();
        LocalPlaybackDataPool.getInstance().setSongInfoList(songInfoList);
        for (SongInfo songInfo : LocalPlaybackDataPool.getInstance().getSongInfoList()) {
            MySongInfo mySongInfo = new MySongInfo();
            mySongInfo.setSongNameLength(songInfo.getSongNameLength());
            mySongInfo.setSongName(songInfo.getSongName());
            mySongInfo.setChecked(songInfo.isChecked());
            mySongInfo.setSongIndexInFileList(songInfo.getSongIndexInFileList());
            mySongInfo.setSongNameBuffer(songInfo.getSongNameBuffer());
            mySongInfo.setDeleted(songInfo.isDeleted());
            mySongInfo.setRelatePlayListIndex(songInfo.getRelatePlayListIndex());
            mySongInfo.setSongNameWithoutSuffix(songInfo.getSongNameWithoutSuffix());
            mySongInfo.setSongNameOffset(songInfo.getSongNameOffset());
            arrayList.add(mySongInfo);
        }
        return arrayList;
    }
}
