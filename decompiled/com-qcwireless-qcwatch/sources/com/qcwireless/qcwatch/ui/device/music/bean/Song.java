package com.qcwireless.qcwatch.ui.device.music.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Song.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u0012\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u00103\u001a\u00020\u00102\b\u00104\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00105\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001a\u0010!\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R\u001a\u0010$\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\f\"\u0004\b,\u0010\u000eR\u001a\u0010-\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u00066"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "", "()V", "albumId", "", "getAlbumId", "()J", "setAlbumId", "(J)V", TypedValues.TransitionType.S_DURATION, "", "getDuration", "()I", "setDuration", "(I)V", "editMusic", "", "getEditMusic", "()Z", "setEditMusic", "(Z)V", "firstName", "", "getFirstName", "()Ljava/lang/String;", "setFirstName", "(Ljava/lang/String;)V", "name", "getName", "setName", "path", "getPath", "setPath", "select", "getSelect", "setSelect", "singer", "getSinger", "setSinger", "size", "getSize", "setSize", "songIndexInFileList", "getSongIndexInFileList", "setSongIndexInFileList", "songNameBuffer", "", "getSongNameBuffer", "()[B", "setSongNameBuffer", "([B)V", "equals", FitnessActivities.OTHER, "hashCode", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Song {
    private long albumId;
    private int duration;
    private boolean editMusic;
    private boolean select;
    private long size;
    private int songIndexInFileList;
    private String name = "";
    private String singer = "";
    private String path = "";
    private String firstName = "";
    private byte[] songNameBuffer = new byte[0];

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getSinger() {
        return this.singer;
    }

    public final void setSinger(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.singer = str;
    }

    public final long getSize() {
        return this.size;
    }

    public final void setSize(long j) {
        this.size = j;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }

    public final long getAlbumId() {
        return this.albumId;
    }

    public final void setAlbumId(long j) {
        this.albumId = j;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.firstName = str;
    }

    public final boolean getEditMusic() {
        return this.editMusic;
    }

    public final void setEditMusic(boolean z) {
        this.editMusic = z;
    }

    public final int getSongIndexInFileList() {
        return this.songIndexInFileList;
    }

    public final void setSongIndexInFileList(int i) {
        this.songIndexInFileList = i;
    }

    public final byte[] getSongNameBuffer() {
        return this.songNameBuffer;
    }

    public final void setSongNameBuffer(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.songNameBuffer = bArr;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Objects.requireNonNull(other, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.device.music.bean.Song");
        Song song = (Song) other;
        return Intrinsics.areEqual(this.name, song.name) && Intrinsics.areEqual(this.singer, song.singer) && this.size == song.size && this.duration == song.duration && Intrinsics.areEqual(this.path, song.path) && this.albumId == song.albumId && Intrinsics.areEqual(this.firstName, song.firstName);
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.singer;
        int iHashCode2 = (((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.size)) * 31) + this.duration) * 31;
        String str3 = this.path;
        return ((((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.albumId)) * 31) + this.firstName.hashCode();
    }
}
