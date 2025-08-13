package com.oudmon.ble.base.bluetooth.spp;

import android.app.Application;
import android.os.Bundle;
import com.oudmon.ble.base.bluetooth.spp.bean.MySongInfo;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackConfiguration;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelClient;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngineCallback;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackUtil;
import com.realsil.customer.audioconnect.localplayback.entity.SongInfo;
import com.realsil.customer.bbpro.core.BeeError;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/spp/RtkMusicSpp.class */
public class RtkMusicSpp {
    private static RtkMusicSpp rtkMusicSpp;
    private LocalPlaybackModelClient mLocalPlaybackModelClient;
    private MyLocalPlaybackModelCallback myLocalPlaybackModelCallback;
    LocalPlaybackModelCallback mLocalPlaybackModelCallback = new LocalPlaybackModelCallback() { // from class: com.oudmon.ble.base.bluetooth.spp.RtkMusicSpp.2
        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetFileHeaderReport(int var1, long var2) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetFileHeaderReport(var1, var2);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetFileContentReport(int var1, byte[] var2) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetFileContentReport(var1, var2);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetFileFooterReport(int var1, byte[] var2) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetFileFooterReport(var1, var2);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onAddOrDeleteSongToPlaylistReport(int var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onAddOrDeleteSongToPlaylistReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onDeleteSingleSongReport(int var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onDeleteSingleSongReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onDeleteAllSongReport(int var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onDeleteAllSongReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onGetDeviceInfoReport(Bundle var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onGetDeviceInfoReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onEnterSongTransferModeReport(boolean var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onEnterSongTransferModeReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onExitSongTransferModeReport(boolean var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onExitSongTransferModeReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onCancelTransferReport(boolean var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onCancelTransferReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onWriteSuccessReport(int var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onWriteSuccessReport(var1);
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onWriteFailedReport() {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onWriteFailedReport();
            }
        }

        @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelCallback
        public void onTransferWasValidReport(int var1) {
            if (RtkMusicSpp.this.myLocalPlaybackModelCallback != null) {
                RtkMusicSpp.this.myLocalPlaybackModelCallback.onTransferWasValidReport(var1);
            }
        }
    };

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
        if (this.mLocalPlaybackModelClient != null) {
            this.mLocalPlaybackModelClient.unregisterCallback(this.mLocalPlaybackModelCallback);
        }
    }

    public void registerMyMusicCallback(MyLocalPlaybackModelCallback myLocalPlaybackModelCallback) {
        this.myLocalPlaybackModelCallback = myLocalPlaybackModelCallback;
    }

    public Integer getFileListData(int type) {
        if (this.mLocalPlaybackModelClient == null) {
            return -1;
        }
        if (type == RtkSppConstants.FILE_TYPE_HEADER_BIN) {
            BeeError error = this.mLocalPlaybackModelClient.getFileListData((byte) 0);
            return Integer.valueOf(error.code);
        }
        if (type == RtkSppConstants.FILE_TYPE_NAME_BIN) {
            BeeError error2 = this.mLocalPlaybackModelClient.getFileListData((byte) 1);
            return Integer.valueOf(error2.code);
        }
        return 0;
    }

    public boolean getConnectState() {
        if (this.mLocalPlaybackModelClient == null) {
            return false;
        }
        int errorCode = this.mLocalPlaybackModelClient.getVendorClient().getConnectState();
        if (errorCode >= 263) {
            return true;
        }
        return false;
    }

    public String getCacheDirPath() {
        return LocalPlaybackConfiguration.getInstance().getCacheDirPath();
    }

    public void setCacheDirPath(String path) {
        LocalPlaybackConfiguration.getInstance().setCacheDirPath(path);
    }

    public File createNewHeaderFile(String path) {
        return LocalPlaybackUtil.createNewFile(path, "header.bin");
    }

    public File createNewNameFile(String path) {
        return LocalPlaybackUtil.createNewFile(path, "name.bin");
    }

    public void writeContentToFile(File file, byte[] content) throws IOException {
        LocalPlaybackUtil.writeContentToFile(file, content);
    }

    public int deleteSingleSongFromDevice(int fileIndex, byte[] songName) {
        BeeError ret = this.mLocalPlaybackModelClient.deleteSingleSongFromDevice(fileIndex, songName);
        return ret.code;
    }

    public int deleteAllSongFromDevice() {
        BeeError ret = this.mLocalPlaybackModelClient.deleteAllSongFromDevice();
        return ret.code;
    }

    public void updateTransferState(int bufferFlag) throws InterruptedException {
        LocalPlaybackTransferEngine.getInstance().updateTransferState(bufferFlag);
    }

    public int queryDeviceInfo() {
        BeeError ret = LocalPlaybackModelClient.getInstance().queryDeviceInfo();
        return ret.code;
    }

    public int enterTransferMode(File songFile) {
        BeeError ret = LocalPlaybackModelClient.getInstance().enterSongTransferMode(songFile);
        return ret.code;
    }

    public void startTransferInit(int mSendPacketSize, int mBufferCheckSize) {
        LocalPlaybackTransferEngine.getInstance().init(mSendPacketSize, mBufferCheckSize);
    }

    public void startTransfer(File mTransferFile, final MyLocalPlaybackTransferEngineCallback callback) throws InterruptedException {
        LocalPlaybackTransferEngine.getInstance().startTransfer(mTransferFile);
        LocalPlaybackTransferEngine.getInstance().setTransferCallback(new LocalPlaybackTransferEngineCallback() { // from class: com.oudmon.ble.base.bluetooth.spp.RtkMusicSpp.1
            @Override // com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngineCallback
            public void onTransferProgressChanged(int progress) {
                if (callback != null) {
                    callback.onTransferProgressChanged(progress);
                }
            }
        });
    }

    public int stopTransfer() {
        BeeError ret = LocalPlaybackModelClient.getInstance().cancelTransfer();
        return ret.code;
    }

    public int exitSongTransferMode() {
        BeeError ret = LocalPlaybackModelClient.getInstance().exitSongTransferMode();
        return ret.code;
    }

    public List<MySongInfo> getSongInfoList(File mHeaderBin, File mNameBin) {
        List<MySongInfo> mySongList = new ArrayList<>();
        List<SongInfo> songInfoList = LocalPlaybackUtil.getSongInfoList(mHeaderBin, mNameBin);
        LocalPlaybackDataPool.getInstance().clear();
        LocalPlaybackDataPool.getInstance().setSongInfoList(songInfoList);
        List<SongInfo> list = LocalPlaybackDataPool.getInstance().getSongInfoList();
        for (SongInfo deviceSong : list) {
            MySongInfo song = new MySongInfo();
            song.setSongNameLength(deviceSong.getSongNameLength());
            song.setSongName(deviceSong.getSongName());
            song.setChecked(deviceSong.isChecked());
            song.setSongIndexInFileList(deviceSong.getSongIndexInFileList());
            song.setSongNameBuffer(deviceSong.getSongNameBuffer());
            song.setDeleted(deviceSong.isDeleted());
            song.setRelatePlayListIndex(deviceSong.getRelatePlayListIndex());
            song.setSongNameWithoutSuffix(deviceSong.getSongNameWithoutSuffix());
            song.setSongNameOffset(deviceSong.getSongNameOffset());
            mySongList.add(song);
        }
        return mySongList;
    }
}
