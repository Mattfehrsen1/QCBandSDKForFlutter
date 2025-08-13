package com.qcwireless.qcwatch.ui.base.bean.response.device;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DevicePictureResp.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DevicePictureResp;", "", "hardVersion", "", "pictureUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getHardVersion", "()Ljava/lang/String;", "getPictureUrl", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DevicePictureResp {
    private final String hardVersion;
    private final String pictureUrl;

    public static /* synthetic */ DevicePictureResp copy$default(DevicePictureResp devicePictureResp, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = devicePictureResp.hardVersion;
        }
        if ((i & 2) != 0) {
            str2 = devicePictureResp.pictureUrl;
        }
        return devicePictureResp.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getHardVersion() {
        return this.hardVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPictureUrl() {
        return this.pictureUrl;
    }

    public final DevicePictureResp copy(String hardVersion, String pictureUrl) {
        Intrinsics.checkNotNullParameter(hardVersion, "hardVersion");
        Intrinsics.checkNotNullParameter(pictureUrl, "pictureUrl");
        return new DevicePictureResp(hardVersion, pictureUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DevicePictureResp)) {
            return false;
        }
        DevicePictureResp devicePictureResp = (DevicePictureResp) other;
        return Intrinsics.areEqual(this.hardVersion, devicePictureResp.hardVersion) && Intrinsics.areEqual(this.pictureUrl, devicePictureResp.pictureUrl);
    }

    public int hashCode() {
        return (this.hardVersion.hashCode() * 31) + this.pictureUrl.hashCode();
    }

    public String toString() {
        return "DevicePictureResp(hardVersion=" + this.hardVersion + ", pictureUrl=" + this.pictureUrl + ')';
    }

    public DevicePictureResp(String hardVersion, String pictureUrl) {
        Intrinsics.checkNotNullParameter(hardVersion, "hardVersion");
        Intrinsics.checkNotNullParameter(pictureUrl, "pictureUrl");
        this.hardVersion = hardVersion;
        this.pictureUrl = pictureUrl;
    }

    public final String getHardVersion() {
        return this.hardVersion;
    }

    public final String getPictureUrl() {
        return this.pictureUrl;
    }
}
