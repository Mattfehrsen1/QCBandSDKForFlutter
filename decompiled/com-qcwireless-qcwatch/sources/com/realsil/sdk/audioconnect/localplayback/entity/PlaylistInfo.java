package com.realsil.sdk.audioconnect.localplayback.entity;

import java.util.List;

/* loaded from: classes3.dex */
public class PlaylistInfo {
    public int a;
    public String b;
    public List<SongInfo> c;

    public List<SongInfo> getContainSongList() {
        return this.c;
    }

    public int getContainSongNum() {
        List<SongInfo> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public String getPlaylistName() {
        return this.b;
    }

    public int getPlaylistNo() {
        return this.a;
    }

    public void setContainSongList(List<SongInfo> list) {
        this.c = list;
    }

    public void setPlaylistName(String str) {
        this.b = str;
    }

    public void setPlaylistNo(int i) {
        this.a = i;
    }
}
