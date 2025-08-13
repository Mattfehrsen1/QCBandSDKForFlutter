package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SongMenuEntity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/SongMenuEntity;", "", "deviceAddress", "", "menuId", "", "menuName", "(Ljava/lang/String;JLjava/lang/String;)V", "getDeviceAddress", "()Ljava/lang/String;", "setDeviceAddress", "(Ljava/lang/String;)V", "getMenuId", "()J", "setMenuId", "(J)V", "getMenuName", "setMenuName", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SongMenuEntity {
    private String deviceAddress;
    private long menuId;
    private String menuName;

    public static /* synthetic */ SongMenuEntity copy$default(SongMenuEntity songMenuEntity, String str, long j, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = songMenuEntity.deviceAddress;
        }
        if ((i & 2) != 0) {
            j = songMenuEntity.menuId;
        }
        if ((i & 4) != 0) {
            str2 = songMenuEntity.menuName;
        }
        return songMenuEntity.copy(str, j, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final long getMenuId() {
        return this.menuId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMenuName() {
        return this.menuName;
    }

    public final SongMenuEntity copy(String deviceAddress, long menuId, String menuName) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        return new SongMenuEntity(deviceAddress, menuId, menuName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SongMenuEntity)) {
            return false;
        }
        SongMenuEntity songMenuEntity = (SongMenuEntity) other;
        return Intrinsics.areEqual(this.deviceAddress, songMenuEntity.deviceAddress) && this.menuId == songMenuEntity.menuId && Intrinsics.areEqual(this.menuName, songMenuEntity.menuName);
    }

    public int hashCode() {
        return (((this.deviceAddress.hashCode() * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.menuId)) * 31) + this.menuName.hashCode();
    }

    public String toString() {
        return "SongMenuEntity(deviceAddress=" + this.deviceAddress + ", menuId=" + this.menuId + ", menuName=" + this.menuName + ')';
    }

    public SongMenuEntity(String deviceAddress, long j, String menuName) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        this.deviceAddress = deviceAddress;
        this.menuId = j;
        this.menuName = menuName;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final void setDeviceAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceAddress = str;
    }

    public final long getMenuId() {
        return this.menuId;
    }

    public final void setMenuId(long j) {
        this.menuId = j;
    }

    public final String getMenuName() {
        return this.menuName;
    }

    public final void setMenuName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.menuName = str;
    }
}
