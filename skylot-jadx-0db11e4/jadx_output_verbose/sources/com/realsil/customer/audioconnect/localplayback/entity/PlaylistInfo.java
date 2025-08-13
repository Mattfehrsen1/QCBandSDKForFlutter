package com.realsil.customer.audioconnect.localplayback.entity;

import java.util.List;

/* loaded from: qc_sdk_20250409.aar:libs/rtk-audioconnect-localplayback-1.0.9.jar:com/realsil/customer/audioconnect/localplayback/entity/PlaylistInfo.class */
public class PlaylistInfo {
    public int a;
    public String b;
    public List<SongInfo> c;

    public String getPlaylistName() {
        return this.b;
    }

    public void setPlaylistName(String str) {
        this.b = str;
    }

    public int getContainSongNum() {
        List<SongInfo> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public List<SongInfo> getContainSongList() {
        return this.c;
    }

    public void setContainSongList(List<SongInfo> list) {
        this.c = list;
    }

    public int getPlaylistNo() {
        return this.a;
    }

    public void setPlaylistNo(int i) {
        this.a = i;
    }
}
