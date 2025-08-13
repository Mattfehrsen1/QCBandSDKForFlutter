package com.qcwireless.qcwatch.ui.device.push.bean;

import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes3.dex */
public class MusicBean implements Serializable {
    public String album;
    public String artistName;
    public Long duration;
    public Boolean playing;
    public Long position;
    public String track;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MusicBean musicBean = (MusicBean) o;
        return this.artistName.equals(musicBean.artistName) && this.album.equals(musicBean.album) && this.track.equals(musicBean.track) && this.playing.equals(musicBean.playing);
    }

    public int hashCode() {
        return Objects.hash(this.artistName, this.album, this.track, this.playing);
    }

    public String toString() {
        return "MusicBean{artistName='" + this.artistName + "', album='" + this.album + "', track='" + this.track + "', playing=" + this.playing + ", duration=" + this.duration + ", position=" + this.position + '}';
    }
}
