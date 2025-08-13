package com.qcwireless.qcwatch.ui.base.bean.response.cs;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SupportCsResp.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/cs/SupportCsResp;", "", "hardVersion", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "getHardVersion", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SupportCsResp {
    private final String deviceName;
    private final String hardVersion;

    public static /* synthetic */ SupportCsResp copy$default(SupportCsResp supportCsResp, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = supportCsResp.hardVersion;
        }
        if ((i & 2) != 0) {
            str2 = supportCsResp.deviceName;
        }
        return supportCsResp.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getHardVersion() {
        return this.hardVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    public final SupportCsResp copy(String hardVersion, String deviceName) {
        Intrinsics.checkNotNullParameter(hardVersion, "hardVersion");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        return new SupportCsResp(hardVersion, deviceName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SupportCsResp)) {
            return false;
        }
        SupportCsResp supportCsResp = (SupportCsResp) other;
        return Intrinsics.areEqual(this.hardVersion, supportCsResp.hardVersion) && Intrinsics.areEqual(this.deviceName, supportCsResp.deviceName);
    }

    public int hashCode() {
        return (this.hardVersion.hashCode() * 31) + this.deviceName.hashCode();
    }

    public String toString() {
        return "SupportCsResp(hardVersion=" + this.hardVersion + ", deviceName=" + this.deviceName + ')';
    }

    public SupportCsResp(String hardVersion, String deviceName) {
        Intrinsics.checkNotNullParameter(hardVersion, "hardVersion");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        this.hardVersion = hardVersion;
        this.deviceName = deviceName;
    }

    public final String getHardVersion() {
        return this.hardVersion;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }
}
