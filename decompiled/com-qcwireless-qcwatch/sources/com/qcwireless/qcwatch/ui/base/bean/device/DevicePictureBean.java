package com.qcwireless.qcwatch.ui.base.bean.device;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DevicePictureBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/device/DevicePictureBean;", "", "hardVersion", "", "pictureUrl", "localUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHardVersion", "()Ljava/lang/String;", "getLocalUrl", "getPictureUrl", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DevicePictureBean {
    private final String hardVersion;
    private final String localUrl;
    private final String pictureUrl;

    public static /* synthetic */ DevicePictureBean copy$default(DevicePictureBean devicePictureBean, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = devicePictureBean.hardVersion;
        }
        if ((i & 2) != 0) {
            str2 = devicePictureBean.pictureUrl;
        }
        if ((i & 4) != 0) {
            str3 = devicePictureBean.localUrl;
        }
        return devicePictureBean.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getHardVersion() {
        return this.hardVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPictureUrl() {
        return this.pictureUrl;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLocalUrl() {
        return this.localUrl;
    }

    public final DevicePictureBean copy(String hardVersion, String pictureUrl, String localUrl) {
        Intrinsics.checkNotNullParameter(hardVersion, "hardVersion");
        Intrinsics.checkNotNullParameter(pictureUrl, "pictureUrl");
        Intrinsics.checkNotNullParameter(localUrl, "localUrl");
        return new DevicePictureBean(hardVersion, pictureUrl, localUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DevicePictureBean)) {
            return false;
        }
        DevicePictureBean devicePictureBean = (DevicePictureBean) other;
        return Intrinsics.areEqual(this.hardVersion, devicePictureBean.hardVersion) && Intrinsics.areEqual(this.pictureUrl, devicePictureBean.pictureUrl) && Intrinsics.areEqual(this.localUrl, devicePictureBean.localUrl);
    }

    public int hashCode() {
        return (((this.hardVersion.hashCode() * 31) + this.pictureUrl.hashCode()) * 31) + this.localUrl.hashCode();
    }

    public String toString() {
        return "DevicePictureBean(hardVersion=" + this.hardVersion + ", pictureUrl=" + this.pictureUrl + ", localUrl=" + this.localUrl + ')';
    }

    public DevicePictureBean(String hardVersion, String pictureUrl, String localUrl) {
        Intrinsics.checkNotNullParameter(hardVersion, "hardVersion");
        Intrinsics.checkNotNullParameter(pictureUrl, "pictureUrl");
        Intrinsics.checkNotNullParameter(localUrl, "localUrl");
        this.hardVersion = hardVersion;
        this.pictureUrl = pictureUrl;
        this.localUrl = localUrl;
    }

    public final String getHardVersion() {
        return this.hardVersion;
    }

    public final String getLocalUrl() {
        return this.localUrl;
    }

    public final String getPictureUrl() {
        return this.pictureUrl;
    }
}
