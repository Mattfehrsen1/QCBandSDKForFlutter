package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchWallpaperResp.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\bHÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\bHÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003Jc\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bHÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u00020\bHÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011¨\u00063"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchWallpaperResp;", "", "hardwareVersion", "", "wallpaperName", "wallpaperDesc", "wallpaperUrl", "wallpaperType", "", "wallpaperTypeName", "wallpaperFileUrl", "wallpaperTypeAppIndex", "wallpaperAppIndex", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;II)V", "getHardwareVersion", "()Ljava/lang/String;", "setHardwareVersion", "(Ljava/lang/String;)V", "getWallpaperAppIndex", "()I", "setWallpaperAppIndex", "(I)V", "getWallpaperDesc", "setWallpaperDesc", "getWallpaperFileUrl", "setWallpaperFileUrl", "getWallpaperName", "setWallpaperName", "getWallpaperType", "setWallpaperType", "getWallpaperTypeAppIndex", "setWallpaperTypeAppIndex", "getWallpaperTypeName", "setWallpaperTypeName", "getWallpaperUrl", "setWallpaperUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchWallpaperResp {
    private String hardwareVersion;
    private int wallpaperAppIndex;
    private String wallpaperDesc;
    private String wallpaperFileUrl;
    private String wallpaperName;
    private int wallpaperType;
    private int wallpaperTypeAppIndex;
    private String wallpaperTypeName;
    private String wallpaperUrl;

    /* renamed from: component1, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final String getWallpaperName() {
        return this.wallpaperName;
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
    public final int getWallpaperType() {
        return this.wallpaperType;
    }

    /* renamed from: component6, reason: from getter */
    public final String getWallpaperTypeName() {
        return this.wallpaperTypeName;
    }

    /* renamed from: component7, reason: from getter */
    public final String getWallpaperFileUrl() {
        return this.wallpaperFileUrl;
    }

    /* renamed from: component8, reason: from getter */
    public final int getWallpaperTypeAppIndex() {
        return this.wallpaperTypeAppIndex;
    }

    /* renamed from: component9, reason: from getter */
    public final int getWallpaperAppIndex() {
        return this.wallpaperAppIndex;
    }

    public final WatchWallpaperResp copy(String hardwareVersion, String wallpaperName, String wallpaperDesc, String wallpaperUrl, int wallpaperType, String wallpaperTypeName, String wallpaperFileUrl, int wallpaperTypeAppIndex, int wallpaperAppIndex) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(wallpaperName, "wallpaperName");
        Intrinsics.checkNotNullParameter(wallpaperDesc, "wallpaperDesc");
        Intrinsics.checkNotNullParameter(wallpaperUrl, "wallpaperUrl");
        Intrinsics.checkNotNullParameter(wallpaperTypeName, "wallpaperTypeName");
        Intrinsics.checkNotNullParameter(wallpaperFileUrl, "wallpaperFileUrl");
        return new WatchWallpaperResp(hardwareVersion, wallpaperName, wallpaperDesc, wallpaperUrl, wallpaperType, wallpaperTypeName, wallpaperFileUrl, wallpaperTypeAppIndex, wallpaperAppIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchWallpaperResp)) {
            return false;
        }
        WatchWallpaperResp watchWallpaperResp = (WatchWallpaperResp) other;
        return Intrinsics.areEqual(this.hardwareVersion, watchWallpaperResp.hardwareVersion) && Intrinsics.areEqual(this.wallpaperName, watchWallpaperResp.wallpaperName) && Intrinsics.areEqual(this.wallpaperDesc, watchWallpaperResp.wallpaperDesc) && Intrinsics.areEqual(this.wallpaperUrl, watchWallpaperResp.wallpaperUrl) && this.wallpaperType == watchWallpaperResp.wallpaperType && Intrinsics.areEqual(this.wallpaperTypeName, watchWallpaperResp.wallpaperTypeName) && Intrinsics.areEqual(this.wallpaperFileUrl, watchWallpaperResp.wallpaperFileUrl) && this.wallpaperTypeAppIndex == watchWallpaperResp.wallpaperTypeAppIndex && this.wallpaperAppIndex == watchWallpaperResp.wallpaperAppIndex;
    }

    public int hashCode() {
        return (((((((((((((((this.hardwareVersion.hashCode() * 31) + this.wallpaperName.hashCode()) * 31) + this.wallpaperDesc.hashCode()) * 31) + this.wallpaperUrl.hashCode()) * 31) + this.wallpaperType) * 31) + this.wallpaperTypeName.hashCode()) * 31) + this.wallpaperFileUrl.hashCode()) * 31) + this.wallpaperTypeAppIndex) * 31) + this.wallpaperAppIndex;
    }

    public String toString() {
        return "WatchWallpaperResp(hardwareVersion=" + this.hardwareVersion + ", wallpaperName=" + this.wallpaperName + ", wallpaperDesc=" + this.wallpaperDesc + ", wallpaperUrl=" + this.wallpaperUrl + ", wallpaperType=" + this.wallpaperType + ", wallpaperTypeName=" + this.wallpaperTypeName + ", wallpaperFileUrl=" + this.wallpaperFileUrl + ", wallpaperTypeAppIndex=" + this.wallpaperTypeAppIndex + ", wallpaperAppIndex=" + this.wallpaperAppIndex + ')';
    }

    public WatchWallpaperResp(String hardwareVersion, String wallpaperName, String wallpaperDesc, String wallpaperUrl, int i, String wallpaperTypeName, String wallpaperFileUrl, int i2, int i3) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(wallpaperName, "wallpaperName");
        Intrinsics.checkNotNullParameter(wallpaperDesc, "wallpaperDesc");
        Intrinsics.checkNotNullParameter(wallpaperUrl, "wallpaperUrl");
        Intrinsics.checkNotNullParameter(wallpaperTypeName, "wallpaperTypeName");
        Intrinsics.checkNotNullParameter(wallpaperFileUrl, "wallpaperFileUrl");
        this.hardwareVersion = hardwareVersion;
        this.wallpaperName = wallpaperName;
        this.wallpaperDesc = wallpaperDesc;
        this.wallpaperUrl = wallpaperUrl;
        this.wallpaperType = i;
        this.wallpaperTypeName = wallpaperTypeName;
        this.wallpaperFileUrl = wallpaperFileUrl;
        this.wallpaperTypeAppIndex = i2;
        this.wallpaperAppIndex = i3;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getWallpaperName() {
        return this.wallpaperName;
    }

    public final void setWallpaperName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wallpaperName = str;
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
}
