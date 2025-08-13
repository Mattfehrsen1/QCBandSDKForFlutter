package com.oudmon.ble.base.bluetooth.spp;

import com.realsil.customer.audioconnect.localplayback.entity.PlaylistInfo;
import com.realsil.customer.audioconnect.localplayback.entity.SongInfo;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/spp/LocalPlaybackDataPool.class */
public class LocalPlaybackDataPool {
    private boolean mWhetherNeedToUpdatePlaylist = true;
    private List<SongInfo> mSongInfoList;
    private List<PlaylistInfo> mPlaylistInfoList;
    private static volatile LocalPlaybackDataPool sInstance = null;

    private LocalPlaybackDataPool() {
    }

    public static LocalPlaybackDataPool getInstance() {
        if (sInstance == null) {
            synchronized (LocalPlaybackDataPool.class) {
                if (sInstance == null) {
                    sInstance = new LocalPlaybackDataPool();
                }
            }
        }
        return sInstance;
    }

    public synchronized void setSongInfoList(List<SongInfo> songInfoList) {
        this.mSongInfoList = songInfoList;
    }

    public List<SongInfo> getSongInfoList() {
        return this.mSongInfoList;
    }

    public List<PlaylistInfo> getPlaylistInfoList() {
        return this.mPlaylistInfoList;
    }

    public synchronized void setPlaylistInfoList(List<PlaylistInfo> playlistInfoList) {
        this.mPlaylistInfoList = playlistInfoList;
    }

    public boolean isWhetherNeedToUpdatePlaylist() {
        return this.mWhetherNeedToUpdatePlaylist;
    }

    public synchronized void setWhetherNeedToUpdatePlaylist(boolean updatePlaylist) {
        this.mWhetherNeedToUpdatePlaylist = updatePlaylist;
    }

    public synchronized void clear() {
        if (this.mSongInfoList != null) {
            this.mSongInfoList.clear();
            this.mSongInfoList = null;
        }
        if (this.mPlaylistInfoList != null) {
            this.mPlaylistInfoList.clear();
            this.mPlaylistInfoList = null;
        }
    }
}
