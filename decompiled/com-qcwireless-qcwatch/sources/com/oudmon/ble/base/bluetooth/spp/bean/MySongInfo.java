package com.oudmon.ble.base.bluetooth.spp.bean;

import java.util.List;

/* loaded from: classes3.dex */
public class MySongInfo {
    public boolean checked;
    public boolean deleted;
    public List<Integer> relatePlayList;
    public int relatePlayListIndex;
    public int songIndexInFileList;
    public String songName;
    public byte[] songNameBuffer;
    public int songNameLength;
    public int songNameOffset;
    public String songNameWithoutSuffix;

    public int getSongNameOffset() {
        return this.songNameOffset;
    }

    public void setSongNameOffset(int i) {
        this.songNameOffset = i;
    }

    public int getSongNameLength() {
        return this.songNameLength;
    }

    public void setSongNameLength(int i) {
        this.songNameLength = i;
    }

    public int getSongIndexInFileList() {
        return this.songIndexInFileList;
    }

    public void setSongIndexInFileList(int i) {
        this.songIndexInFileList = i;
    }

    public int getRelatePlayListIndex() {
        return this.relatePlayListIndex;
    }

    public void setRelatePlayListIndex(int i) {
        this.relatePlayListIndex = i;
    }

    public String getSongName() {
        return this.songName;
    }

    public void setSongName(String str) {
        this.songName = str;
    }

    public String getSongNameWithoutSuffix() {
        return this.songNameWithoutSuffix;
    }

    public void setSongNameWithoutSuffix(String str) {
        this.songNameWithoutSuffix = str;
    }

    public byte[] getSongNameBuffer() {
        return this.songNameBuffer;
    }

    public void setSongNameBuffer(byte[] bArr) {
        this.songNameBuffer = bArr;
    }

    public List<Integer> getRelatePlayList() {
        return this.relatePlayList;
    }

    public void setRelatePlayList(List<Integer> list) {
        this.relatePlayList = list;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean z) {
        this.deleted = z;
    }
}
