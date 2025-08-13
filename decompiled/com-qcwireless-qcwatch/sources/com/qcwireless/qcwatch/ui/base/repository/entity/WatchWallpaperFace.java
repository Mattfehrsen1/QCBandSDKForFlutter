package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchWallpaperFace.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\tHÆ\u0003J\t\u00102\u001a\u00020\tHÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003Jw\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\tHÖ\u0001J\t\u0010:\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001e\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001e\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0011\"\u0004\b\u001d\u0010\u0013R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0011\"\u0004\b!\u0010\u0013R\u001e\u0010\r\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0019\"\u0004\b#\u0010\u001bR\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\u001bR\u001e\u0010\u000e\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0011\"\u0004\b'\u0010\u0013R\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0011\"\u0004\b)\u0010\u0013¨\u0006;"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchWallpaperFace;", "", "wallpaperName", "", "hardwareVersion", "wallpaperDesc", "wallpaperUrl", "wallpaperFileUrl", "wallpaperTypeAppIndex", "", "wallpaperAppIndex", "localImageUrl", "localBinUrl", "wallpaperType", "wallpaperTypeName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getHardwareVersion", "()Ljava/lang/String;", "setHardwareVersion", "(Ljava/lang/String;)V", "getLocalBinUrl", "setLocalBinUrl", "getLocalImageUrl", "setLocalImageUrl", "getWallpaperAppIndex", "()I", "setWallpaperAppIndex", "(I)V", "getWallpaperDesc", "setWallpaperDesc", "getWallpaperFileUrl", "setWallpaperFileUrl", "getWallpaperName", "setWallpaperName", "getWallpaperType", "setWallpaperType", "getWallpaperTypeAppIndex", "setWallpaperTypeAppIndex", "getWallpaperTypeName", "setWallpaperTypeName", "getWallpaperUrl", "setWallpaperUrl", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchWallpaperFace {
    private String hardwareVersion;
    private String localBinUrl;
    private String localImageUrl;
    private int wallpaperAppIndex;
    private String wallpaperDesc;
    private String wallpaperFileUrl;
    private String wallpaperName;
    private int wallpaperType;
    private int wallpaperTypeAppIndex;
    private String wallpaperTypeName;
    private String wallpaperUrl;

    /* renamed from: component1, reason: from getter */
    public final String getWallpaperName() {
        return this.wallpaperName;
    }

    /* renamed from: component10, reason: from getter */
    public final int getWallpaperType() {
        return this.wallpaperType;
    }

    /* renamed from: component11, reason: from getter */
    public final String getWallpaperTypeName() {
        return this.wallpaperTypeName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getWallpaperDesc() {
        return this.wallpaperDesc;
    }

    /* renamed from: component4, reason: from getter */
    public final String getWallpaperUrl() {
        return this.wallpaperUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final String getWallpaperFileUrl() {
        return this.wallpaperFileUrl;
    }

    /* renamed from: component6, reason: from getter */
    public final int getWallpaperTypeAppIndex() {
        return this.wallpaperTypeAppIndex;
    }

    /* renamed from: component7, reason: from getter */
    public final int getWallpaperAppIndex() {
        return this.wallpaperAppIndex;
    }

    /* renamed from: component8, reason: from getter */
    public final String getLocalImageUrl() {
        return this.localImageUrl;
    }

    /* renamed from: component9, reason: from getter */
    public final String getLocalBinUrl() {
        return this.localBinUrl;
    }

    public final WatchWallpaperFace copy(String wallpaperName, String hardwareVersion, String wallpaperDesc, String wallpaperUrl, String wallpaperFileUrl, int wallpaperTypeAppIndex, int wallpaperAppIndex, String localImageUrl, String localBinUrl, int wallpaperType, String wallpaperTypeName) {
        Intrinsics.checkNotNullParameter(wallpaperName, "wallpaperName");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(wallpaperDesc, "wallpaperDesc");
        Intrinsics.checkNotNullParameter(wallpaperUrl, "wallpaperUrl");
        Intrinsics.checkNotNullParameter(wallpaperFileUrl, "wallpaperFileUrl");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(localBinUrl, "localBinUrl");
        Intrinsics.checkNotNullParameter(wallpaperTypeName, "wallpaperTypeName");
        return new WatchWallpaperFace(wallpaperName, hardwareVersion, wallpaperDesc, wallpaperUrl, wallpaperFileUrl, wallpaperTypeAppIndex, wallpaperAppIndex, localImageUrl, localBinUrl, wallpaperType, wallpaperTypeName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchWallpaperFace)) {
            return false;
        }
        WatchWallpaperFace watchWallpaperFace = (WatchWallpaperFace) other;
        return Intrinsics.areEqual(this.wallpaperName, watchWallpaperFace.wallpaperName) && Intrinsics.areEqual(this.hardwareVersion, watchWallpaperFace.hardwareVersion) && Intrinsics.areEqual(this.wallpaperDesc, watchWallpaperFace.wallpaperDesc) && Intrinsics.areEqual(this.wallpaperUrl, watchWallpaperFace.wallpaperUrl) && Intrinsics.areEqual(this.wallpaperFileUrl, watchWallpaperFace.wallpaperFileUrl) && this.wallpaperTypeAppIndex == watchWallpaperFace.wallpaperTypeAppIndex && this.wallpaperAppIndex == watchWallpaperFace.wallpaperAppIndex && Intrinsics.areEqual(this.localImageUrl, watchWallpaperFace.localImageUrl) && Intrinsics.areEqual(this.localBinUrl, watchWallpaperFace.localBinUrl) && this.wallpaperType == watchWallpaperFace.wallpaperType && Intrinsics.areEqual(this.wallpaperTypeName, watchWallpaperFace.wallpaperTypeName);
    }

    public int hashCode() {
        return (((((((((((((((((((this.wallpaperName.hashCode() * 31) + this.hardwareVersion.hashCode()) * 31) + this.wallpaperDesc.hashCode()) * 31) + this.wallpaperUrl.hashCode()) * 31) + this.wallpaperFileUrl.hashCode()) * 31) + this.wallpaperTypeAppIndex) * 31) + this.wallpaperAppIndex) * 31) + this.localImageUrl.hashCode()) * 31) + this.localBinUrl.hashCode()) * 31) + this.wallpaperType) * 31) + this.wallpaperTypeName.hashCode();
    }

    public String toString() {
        return "WatchWallpaperFace(wallpaperName=" + this.wallpaperName + ", hardwareVersion=" + this.hardwareVersion + ", wallpaperDesc=" + this.wallpaperDesc + ", wallpaperUrl=" + this.wallpaperUrl + ", wallpaperFileUrl=" + this.wallpaperFileUrl + ", wallpaperTypeAppIndex=" + this.wallpaperTypeAppIndex + ", wallpaperAppIndex=" + this.wallpaperAppIndex + ", localImageUrl=" + this.localImageUrl + ", localBinUrl=" + this.localBinUrl + ", wallpaperType=" + this.wallpaperType + ", wallpaperTypeName=" + this.wallpaperTypeName + ')';
    }

    public WatchWallpaperFace(String wallpaperName, String hardwareVersion, String wallpaperDesc, String wallpaperUrl, String wallpaperFileUrl, int i, int i2, String localImageUrl, String localBinUrl, int i3, String wallpaperTypeName) {
        Intrinsics.checkNotNullParameter(wallpaperName, "wallpaperName");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(wallpaperDesc, "wallpaperDesc");
        Intrinsics.checkNotNullParameter(wallpaperUrl, "wallpaperUrl");
        Intrinsics.checkNotNullParameter(wallpaperFileUrl, "wallpaperFileUrl");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(localBinUrl, "localBinUrl");
        Intrinsics.checkNotNullParameter(wallpaperTypeName, "wallpaperTypeName");
        this.wallpaperName = wallpaperName;
        this.hardwareVersion = hardwareVersion;
        this.wallpaperDesc = wallpaperDesc;
        this.wallpaperUrl = wallpaperUrl;
        this.wallpaperFileUrl = wallpaperFileUrl;
        this.wallpaperTypeAppIndex = i;
        this.wallpaperAppIndex = i2;
        this.localImageUrl = localImageUrl;
        this.localBinUrl = localBinUrl;
        this.wallpaperType = i3;
        this.wallpaperTypeName = wallpaperTypeName;
    }

    public final String getWallpaperName() {
        return this.wallpaperName;
    }

    public final void setWallpaperName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wallpaperName = str;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getWallpaperDesc() {
        return this.wallpaperDesc;
    }

    public final void setWallpaperDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wallpaperDesc = str;
    }

    public final String getWallpaperUrl() {
        return this.wallpaperUrl;
    }

    public final void setWallpaperUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wallpaperUrl = str;
    }

    public final String getWallpaperFileUrl() {
        return this.wallpaperFileUrl;
    }

    public final void setWallpaperFileUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wallpaperFileUrl = str;
    }

    public final int getWallpaperTypeAppIndex() {
        return this.wallpaperTypeAppIndex;
    }

    public final void setWallpaperTypeAppIndex(int i) {
        this.wallpaperTypeAppIndex = i;
    }

    public final int getWallpaperAppIndex() {
        return this.wallpaperAppIndex;
    }

    public final void setWallpaperAppIndex(int i) {
        this.wallpaperAppIndex = i;
    }

    public final String getLocalImageUrl() {
        return this.localImageUrl;
    }

    public final void setLocalImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localImageUrl = str;
    }

    public final String getLocalBinUrl() {
        return this.localBinUrl;
    }

    public final void setLocalBinUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localBinUrl = str;
    }

    public final int getWallpaperType() {
        return this.wallpaperType;
    }

    public final void setWallpaperType(int i) {
        this.wallpaperType = i;
    }

    public final String getWallpaperTypeName() {
        return this.wallpaperTypeName;
    }

    public final void setWallpaperTypeName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wallpaperTypeName = str;
    }
}
