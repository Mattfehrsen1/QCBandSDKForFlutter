package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchThemeResp.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003JO\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\tHÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f¨\u0006+"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchThemeResp;", "", "binUrl", "", "hardwareVersion", "themeName", "preImageUrl", "themeDesc", "themeType", "", "appHomeIndex", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getAppHomeIndex", "()I", "setAppHomeIndex", "(I)V", "getBinUrl", "()Ljava/lang/String;", "setBinUrl", "(Ljava/lang/String;)V", "getHardwareVersion", "setHardwareVersion", "getPreImageUrl", "setPreImageUrl", "getThemeDesc", "setThemeDesc", "getThemeName", "setThemeName", "getThemeType", "setThemeType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchThemeResp {
    private int appHomeIndex;
    private String binUrl;
    private String hardwareVersion;
    private String preImageUrl;
    private String themeDesc;
    private String themeName;
    private int themeType;

    public static /* synthetic */ WatchThemeResp copy$default(WatchThemeResp watchThemeResp, String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = watchThemeResp.binUrl;
        }
        if ((i3 & 2) != 0) {
            str2 = watchThemeResp.hardwareVersion;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            str3 = watchThemeResp.themeName;
        }
        String str7 = str3;
        if ((i3 & 8) != 0) {
            str4 = watchThemeResp.preImageUrl;
        }
        String str8 = str4;
        if ((i3 & 16) != 0) {
            str5 = watchThemeResp.themeDesc;
        }
        String str9 = str5;
        if ((i3 & 32) != 0) {
            i = watchThemeResp.themeType;
        }
        int i4 = i;
        if ((i3 & 64) != 0) {
            i2 = watchThemeResp.appHomeIndex;
        }
        return watchThemeResp.copy(str, str6, str7, str8, str9, i4, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getBinUrl() {
        return this.binUrl;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getThemeName() {
        return this.themeName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final String getThemeDesc() {
        return this.themeDesc;
    }

    /* renamed from: component6, reason: from getter */
    public final int getThemeType() {
        return this.themeType;
    }

    /* renamed from: component7, reason: from getter */
    public final int getAppHomeIndex() {
        return this.appHomeIndex;
    }

    public final WatchThemeResp copy(String binUrl, String hardwareVersion, String themeName, String preImageUrl, String themeDesc, int themeType, int appHomeIndex) {
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(themeName, "themeName");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(themeDesc, "themeDesc");
        return new WatchThemeResp(binUrl, hardwareVersion, themeName, preImageUrl, themeDesc, themeType, appHomeIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchThemeResp)) {
            return false;
        }
        WatchThemeResp watchThemeResp = (WatchThemeResp) other;
        return Intrinsics.areEqual(this.binUrl, watchThemeResp.binUrl) && Intrinsics.areEqual(this.hardwareVersion, watchThemeResp.hardwareVersion) && Intrinsics.areEqual(this.themeName, watchThemeResp.themeName) && Intrinsics.areEqual(this.preImageUrl, watchThemeResp.preImageUrl) && Intrinsics.areEqual(this.themeDesc, watchThemeResp.themeDesc) && this.themeType == watchThemeResp.themeType && this.appHomeIndex == watchThemeResp.appHomeIndex;
    }

    public int hashCode() {
        return (((((((((((this.binUrl.hashCode() * 31) + this.hardwareVersion.hashCode()) * 31) + this.themeName.hashCode()) * 31) + this.preImageUrl.hashCode()) * 31) + this.themeDesc.hashCode()) * 31) + this.themeType) * 31) + this.appHomeIndex;
    }

    public String toString() {
        return "WatchThemeResp(binUrl=" + this.binUrl + ", hardwareVersion=" + this.hardwareVersion + ", themeName=" + this.themeName + ", preImageUrl=" + this.preImageUrl + ", themeDesc=" + this.themeDesc + ", themeType=" + this.themeType + ", appHomeIndex=" + this.appHomeIndex + ')';
    }

    public WatchThemeResp(String binUrl, String hardwareVersion, String themeName, String preImageUrl, String themeDesc, int i, int i2) {
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(themeName, "themeName");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(themeDesc, "themeDesc");
        this.binUrl = binUrl;
        this.hardwareVersion = hardwareVersion;
        this.themeName = themeName;
        this.preImageUrl = preImageUrl;
        this.themeDesc = themeDesc;
        this.themeType = i;
        this.appHomeIndex = i2;
    }

    public final String getBinUrl() {
        return this.binUrl;
    }

    public final void setBinUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.binUrl = str;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getThemeName() {
        return this.themeName;
    }

    public final void setThemeName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.themeName = str;
    }

    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    public final void setPreImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.preImageUrl = str;
    }

    public final String getThemeDesc() {
        return this.themeDesc;
    }

    public final void setThemeDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.themeDesc = str;
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
