package com.qcwireless.qcwatch.ui.base.repository.entity;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MusicToDeviceEntity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0007HÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0007HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0007HÆ\u0003Jc\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0007HÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u00020\tHÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u000b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001e\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001e\u0010\r\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0014\"\u0004\b&\u0010\u0016¨\u00066"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/MusicToDeviceEntity;", "", "deviceAddress", "", "musicName", "singer", "size", "", TypedValues.TransitionType.S_DURATION, "", "path", "albumId", "songMenuName", "songMenuId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JILjava/lang/String;JLjava/lang/String;J)V", "getAlbumId", "()J", "setAlbumId", "(J)V", "getDeviceAddress", "()Ljava/lang/String;", "setDeviceAddress", "(Ljava/lang/String;)V", "getDuration", "()I", "setDuration", "(I)V", "getMusicName", "setMusicName", "getPath", "setPath", "getSinger", "setSinger", "getSize", "setSize", "getSongMenuId", "setSongMenuId", "getSongMenuName", "setSongMenuName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class MusicToDeviceEntity {
    private long albumId;
    private String deviceAddress;
    private int duration;
    private String musicName;
    private String path;
    private String singer;
    private long size;
    private long songMenuId;
    private String songMenuName;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMusicName() {
        return this.musicName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSinger() {
        return this.singer;
    }

    /* renamed from: component4, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    /* renamed from: component5, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component7, reason: from getter */
    public final long getAlbumId() {
        return this.albumId;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSongMenuName() {
        return this.songMenuName;
    }

    /* renamed from: component9, reason: from getter */
    public final long getSongMenuId() {
        return this.songMenuId;
    }

    public final MusicToDeviceEntity copy(String deviceAddress, String musicName, String singer, long size, int duration, String path, long albumId, String songMenuName, long songMenuId) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(musicName, "musicName");
        Intrinsics.checkNotNullParameter(singer, "singer");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(songMenuName, "songMenuName");
        return new MusicToDeviceEntity(deviceAddress, musicName, singer, size, duration, path, albumId, songMenuName, songMenuId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MusicToDeviceEntity)) {
            return false;
        }
        MusicToDeviceEntity musicToDeviceEntity = (MusicToDeviceEntity) other;
        return Intrinsics.areEqual(this.deviceAddress, musicToDeviceEntity.deviceAddress) && Intrinsics.areEqual(this.musicName, musicToDeviceEntity.musicName) && Intrinsics.areEqual(this.singer, musicToDeviceEntity.singer) && this.size == musicToDeviceEntity.size && this.duration == musicToDeviceEntity.duration && Intrinsics.areEqual(this.path, musicToDeviceEntity.path) && this.albumId == musicToDeviceEntity.albumId && Intrinsics.areEqual(this.songMenuName, musicToDeviceEntity.songMenuName) && this.songMenuId == musicToDeviceEntity.songMenuId;
    }

    public int hashCode() {
        return (((((((((((((((this.deviceAddress.hashCode() * 31) + this.musicName.hashCode()) * 31) + this.singer.hashCode()) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.size)) * 31) + this.duration) * 31) + this.path.hashCode()) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.albumId)) * 31) + this.songMenuName.hashCode()) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.songMenuId);
    }

    public String toString() {
        return "MusicToDeviceEntity(deviceAddress=" + this.deviceAddress + ", musicName=" + this.musicName + ", singer=" + this.singer + ", size=" + this.size + ", duration=" + this.duration + ", path=" + this.path + ", albumId=" + this.albumId + ", songMenuName=" + this.songMenuName + ", songMenuId=" + this.songMenuId + ')';
    }

    public MusicToDeviceEntity(String deviceAddress, String musicName, String singer, long j, int i, String path, long j2, String songMenuName, long j3) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(musicName, "musicName");
        Intrinsics.checkNotNullParameter(singer, "singer");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(songMenuName, "songMenuName");
        this.deviceAddress = deviceAddress;
        this.musicName = musicName;
        this.singer = singer;
        this.size = j;
        this.duration = i;
        this.path = path;
        this.albumId = j2;
        this.songMenuName = songMenuName;
        this.songMenuId = j3;
    }

    public /* synthetic */ MusicToDeviceEntity(String str, String str2, String str3, long j, int i, String str4, long j2, String str5, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, j, i, str4, j2, str5, (i2 & 256) != 0 ? 0L : j3);
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final void setDeviceAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceAddress = str;
    }

    public final String getMusicName() {
        return this.musicName;
    }

    public final void setMusicName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.musicName = str;
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

    public final String getSongMenuName() {
        return this.songMenuName;
    }

    public final void setSongMenuName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.songMenuName = str;
    }

    public final long getSongMenuId() {
        return this.songMenuId;
    }

    public final void setSongMenuId(long j) {
        this.songMenuId = j;
    }
}
