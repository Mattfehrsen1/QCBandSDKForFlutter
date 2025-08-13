package com.qcwireless.qcwatch.ui.base.bean.response.device;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FirmwareOtaResp.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u000fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0014\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0006R\u0014\u0010\u001a\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0006¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/FirmwareOtaResp;", "", "()V", "downloadUrl", "", "getDownloadUrl", "()Ljava/lang/String;", "enforceUpdateFrom", "getEnforceUpdateFrom", "enforceUpdateTo", "getEnforceUpdateTo", "hardwareVersion", "getHardwareVersion", "isEnforceUpdate", "openOrNot", "", "getOpenOrNot", "()I", "os", "getOs", "updateDesc", "getUpdateDesc", "setUpdateDesc", "(Ljava/lang/String;)V", "uploadDate", "getUploadDate", "version", "getVersion", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FirmwareOtaResp {
    private final int openOrNot;
    private final String hardwareVersion = "";
    private final String version = "";
    private final String enforceUpdateFrom = "";
    private final String enforceUpdateTo = "";
    private final String isEnforceUpdate = "";
    private final String downloadUrl = "";
    private final String uploadDate = "";
    private final int os = 1;
    private String updateDesc = "";

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final String getVersion() {
        return this.version;
    }

    public final String getEnforceUpdateFrom() {
        return this.enforceUpdateFrom;
    }

    public final String getEnforceUpdateTo() {
        return this.enforceUpdateTo;
    }

    /* renamed from: isEnforceUpdate, reason: from getter */
    public final String getIsEnforceUpdate() {
        return this.isEnforceUpdate;
    }

    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    public final int getOpenOrNot() {
        return this.openOrNot;
    }

    public final String getUploadDate() {
        return this.uploadDate;
    }

    public final int getOs() {
        return this.os;
    }

    public final String getUpdateDesc() {
        return this.updateDesc;
    }

    public final void setUpdateDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.updateDesc = str;
    }

    public String toString() {
        return "FirmwareOtaResp(hardwareVersion='" + this.hardwareVersion + "', version='" + this.version + "', enforceUpdateFrom='" + this.enforceUpdateFrom + "', enforceUpdateTo='" + this.enforceUpdateTo + "', isEnforceUpdate='" + this.isEnforceUpdate + "', downloadUrl='" + this.downloadUrl + "', openOrNot=" + this.openOrNot + ", uploadDate='" + this.uploadDate + "', os=" + this.os + ", updateDesc='" + this.updateDesc + "')";
    }
}
