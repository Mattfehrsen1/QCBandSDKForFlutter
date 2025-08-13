package com.qcwireless.qcwatch.ui.base.bean.response.device;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceMissFileResp.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/DeviceMissFileResp;", "", "fileName", "", "desc", "hardwareVersion", "firmwareVersion", "downloadUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getDownloadUrl", "getFileName", "getFirmwareVersion", "getHardwareVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DeviceMissFileResp {
    private final String desc;
    private final String downloadUrl;
    private final String fileName;
    private final String firmwareVersion;
    private final String hardwareVersion;

    public DeviceMissFileResp() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ DeviceMissFileResp copy$default(DeviceMissFileResp deviceMissFileResp, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceMissFileResp.fileName;
        }
        if ((i & 2) != 0) {
            str2 = deviceMissFileResp.desc;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = deviceMissFileResp.hardwareVersion;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = deviceMissFileResp.firmwareVersion;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = deviceMissFileResp.downloadUrl;
        }
        return deviceMissFileResp.copy(str, str6, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* renamed from: component3, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    public final DeviceMissFileResp copy(String fileName, String desc, String hardwareVersion, String firmwareVersion, String downloadUrl) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(firmwareVersion, "firmwareVersion");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        return new DeviceMissFileResp(fileName, desc, hardwareVersion, firmwareVersion, downloadUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceMissFileResp)) {
            return false;
        }
        DeviceMissFileResp deviceMissFileResp = (DeviceMissFileResp) other;
        return Intrinsics.areEqual(this.fileName, deviceMissFileResp.fileName) && Intrinsics.areEqual(this.desc, deviceMissFileResp.desc) && Intrinsics.areEqual(this.hardwareVersion, deviceMissFileResp.hardwareVersion) && Intrinsics.areEqual(this.firmwareVersion, deviceMissFileResp.firmwareVersion) && Intrinsics.areEqual(this.downloadUrl, deviceMissFileResp.downloadUrl);
    }

    public int hashCode() {
        return (((((((this.fileName.hashCode() * 31) + this.desc.hashCode()) * 31) + this.hardwareVersion.hashCode()) * 31) + this.firmwareVersion.hashCode()) * 31) + this.downloadUrl.hashCode();
    }

    public String toString() {
        return "DeviceMissFileResp(fileName=" + this.fileName + ", desc=" + this.desc + ", hardwareVersion=" + this.hardwareVersion + ", firmwareVersion=" + this.firmwareVersion + ", downloadUrl=" + this.downloadUrl + ')';
    }

    public DeviceMissFileResp(String fileName, String desc, String hardwareVersion, String firmwareVersion, String downloadUrl) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(firmwareVersion, "firmwareVersion");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        this.fileName = fileName;
        this.desc = desc;
        this.hardwareVersion = hardwareVersion;
        this.firmwareVersion = firmwareVersion;
        this.downloadUrl = downloadUrl;
    }

    public /* synthetic */ DeviceMissFileResp(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5);
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public final String getDownloadUrl() {
        return this.downloadUrl;
    }
}
