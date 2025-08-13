package com.qcwireless.qcwatch.ui.base.bean.request.app;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppVersionRequest.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/app/AppVersionRequest;", "", "appName", "", "appVersionCode", "", "appVersionName", "(Ljava/lang/String;ILjava/lang/String;)V", "getAppName", "()Ljava/lang/String;", "getAppVersionCode", "()I", "getAppVersionName", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class AppVersionRequest {
    private final String appName;
    private final int appVersionCode;
    private final String appVersionName;

    public static /* synthetic */ AppVersionRequest copy$default(AppVersionRequest appVersionRequest, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = appVersionRequest.appName;
        }
        if ((i2 & 2) != 0) {
            i = appVersionRequest.appVersionCode;
        }
        if ((i2 & 4) != 0) {
            str2 = appVersionRequest.appVersionName;
        }
        return appVersionRequest.copy(str, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAppName() {
        return this.appName;
    }

    /* renamed from: component2, reason: from getter */
    public final int getAppVersionCode() {
        return this.appVersionCode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAppVersionName() {
        return this.appVersionName;
    }

    public final AppVersionRequest copy(String appName, int appVersionCode, String appVersionName) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(appVersionName, "appVersionName");
        return new AppVersionRequest(appName, appVersionCode, appVersionName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppVersionRequest)) {
            return false;
        }
        AppVersionRequest appVersionRequest = (AppVersionRequest) other;
        return Intrinsics.areEqual(this.appName, appVersionRequest.appName) && this.appVersionCode == appVersionRequest.appVersionCode && Intrinsics.areEqual(this.appVersionName, appVersionRequest.appVersionName);
    }

    public int hashCode() {
        return (((this.appName.hashCode() * 31) + this.appVersionCode) * 31) + this.appVersionName.hashCode();
    }

    public String toString() {
        return "AppVersionRequest(appName=" + this.appName + ", appVersionCode=" + this.appVersionCode + ", appVersionName=" + this.appVersionName + ')';
    }

    public AppVersionRequest(String appName, int i, String appVersionName) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(appVersionName, "appVersionName");
        this.appName = appName;
        this.appVersionCode = i;
        this.appVersionName = appVersionName;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final int getAppVersionCode() {
        return this.appVersionCode;
    }

    public final String getAppVersionName() {
        return this.appVersionName;
    }
}
