package com.oudmon.ble.base.bluetooth.spp;

import com.realsil.sdk.audioconnect.localplayback.entity.PlaylistInfo;
import com.realsil.sdk.audioconnect.localplayback.entity.SongInfo;
import java.util.List;

/* loaded from: classes3.dex */
public class LocalPlaybackDataPool {
    private static volatile LocalPlaybackDataPool sInstance;
    private List<PlaylistInfo> mPlaylistInfoList;
    private List<SongInfo> mSongInfoList;
    private boolean mWhetherNeedToUpdatePlaylist = true;

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

    public synchronized void setSongInfoList(List<SongInfo> list) {
        this.mSongInfoList = list;
    }

    public List<SongInfo> getSongInfoList() {
        return this.mSongInfoList;
    }

    public List<PlaylistInfo> getPlaylistInfoList() {
        return this.mPlaylistInfoList;
    }

    public synchronized void setPlaylistInfoList(List<PlaylistInfo> list) {
        this.mPlaylistInfoList = list;
    }

    public boolean isWhetherNeedToUpdatePlaylist() {
        return this.mWhetherNeedToUpdatePlaylist;
    }

    public synchronized void setWhetherNeedToUpdatePlaylist(boolean z) {
        this.mWhetherNeedToUpdatePlaylist = z;
    }

    public synchronized void clear() {
        List<SongInfo> list = this.mSongInfoList;
        if (list != null) {
            list.clear();
            this.mSongInfoList = null;
        }
        List<PlaylistInfo> list2 = this.mPlaylistInfoList;
        if (list2 != null) {
            list2.clear();
            this.mPlaylistInfoList = null;
        }
    }
}
