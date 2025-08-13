package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchThemeFace.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u000bHÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003Jc\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u00020\u000bHÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011¨\u00063"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchThemeFace;", "", "themeName", "", "hardwareVersion", "preImageUrl", "binUrl", "themeDesc", "localImageUrl", "localBinUrl", "themeType", "", "appHomeIndex", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getAppHomeIndex", "()I", "setAppHomeIndex", "(I)V", "getBinUrl", "()Ljava/lang/String;", "setBinUrl", "(Ljava/lang/String;)V", "getHardwareVersion", "setHardwareVersion", "getLocalBinUrl", "setLocalBinUrl", "getLocalImageUrl", "setLocalImageUrl", "getPreImageUrl", "setPreImageUrl", "getThemeDesc", "setThemeDesc", "getThemeName", "setThemeName", "getThemeType", "setThemeType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchThemeFace {
    private int appHomeIndex;
    private String binUrl;
    private String hardwareVersion;
    private String localBinUrl;
    private String localImageUrl;
    private String preImageUrl;
    private String themeDesc;
    private String themeName;
    private int themeType;

    /* renamed from: component1, reason: from getter */
    public final String getThemeName() {
        return this.themeName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBinUrl() {
        return this.binUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final String getThemeDesc() {
        return this.themeDesc;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLocalImageUrl() {
        return this.localImageUrl;
    }

    /* renamed from: component7, reason: from getter */
    public final String getLocalBinUrl() {
        return this.localBinUrl;
    }

    /* renamed from: component8, reason: from getter */
    public final int getThemeType() {
        return this.themeType;
    }

    /* renamed from: component9, reason: from getter */
    public final int getAppHomeIndex() {
        return this.appHomeIndex;
    }

    public final WatchThemeFace copy(String themeName, String hardwareVersion, String preImageUrl, String binUrl, String themeDesc, String localImageUrl, String localBinUrl, int themeType, int appHomeIndex) {
        Intrinsics.checkNotNullParameter(themeName, "themeName");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(themeDesc, "themeDesc");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(localBinUrl, "localBinUrl");
        return new WatchThemeFace(themeName, hardwareVersion, preImageUrl, binUrl, themeDesc, localImageUrl, localBinUrl, themeType, appHomeIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchThemeFace)) {
            return false;
        }
        WatchThemeFace watchThemeFace = (WatchThemeFace) other;
        return Intrinsics.areEqual(this.themeName, watchThemeFace.themeName) && Intrinsics.areEqual(this.hardwareVersion, watchThemeFace.hardwareVersion) && Intrinsics.areEqual(this.preImageUrl, watchThemeFace.preImageUrl) && Intrinsics.areEqual(this.binUrl, watchThemeFace.binUrl) && Intrinsics.areEqual(this.themeDesc, watchThemeFace.themeDesc) && Intrinsics.areEqual(this.localImageUrl, watchThemeFace.localImageUrl) && Intrinsics.areEqual(this.localBinUrl, watchThemeFace.localBinUrl) && this.themeType == watchThemeFace.themeType && this.appHomeIndex == watchThemeFace.appHomeIndex;
    }

    public int hashCode() {
        return (((((((((((((((this.themeName.hashCode() * 31) + this.hardwareVersion.hashCode()) * 31) + this.preImageUrl.hashCode()) * 31) + this.binUrl.hashCode()) * 31) + this.themeDesc.hashCode()) * 31) + this.localImageUrl.hashCode()) * 31) + this.localBinUrl.hashCode()) * 31) + this.themeType) * 31) + this.appHomeIndex;
    }

    public String toString() {
        return "WatchThemeFace(themeName=" + this.themeName + ", hardwareVersion=" + this.hardwareVersion + ", preImageUrl=" + this.preImageUrl + ", binUrl=" + this.binUrl + ", themeDesc=" + this.themeDesc + ", localImageUrl=" + this.localImageUrl + ", localBinUrl=" + this.localBinUrl + ", themeType=" + this.themeType + ", appHomeIndex=" + this.appHomeIndex + ')';
    }

    public WatchThemeFace(String themeName, String hardwareVersion, String preImageUrl, String binUrl, String themeDesc, String localImageUrl, String localBinUrl, int i, int i2) {
        Intrinsics.checkNotNullParameter(themeName, "themeName");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(themeDesc, "themeDesc");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(localBinUrl, "localBinUrl");
        this.themeName = themeName;
        this.hardwareVersion = hardwareVersion;
        this.preImageUrl = preImageUrl;
        this.binUrl = binUrl;
        this.themeDesc = themeDesc;
        this.localImageUrl = localImageUrl;
        this.localBinUrl = localBinUrl;
        this.themeType = i;
        this.appHomeIndex = i2;
    }

    public final String getThemeName() {
        return this.themeName;
    }

    public final void setThemeName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.themeName = str;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    public final void setPreImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.preImageUrl = str;
    }

    public final String getBinUrl() {
        return this.binUrl;
    }

    public final void setBinUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.binUrl = str;
    }

    public final String getThemeDesc() {
        return this.themeDesc;
    }

    public final void setThemeDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.themeDesc = str;
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

    public final int getThemeType() {
        return this.themeType;
    }

    public final void setThemeType(int i) {
        this.themeType = i;
    }

    public final int getAppHomeIndex() {
        return this.appHomeIndex;
    }

    public final void setAppHomeIndex(int i) {
        this.appHomeIndex = i;
    }
}
