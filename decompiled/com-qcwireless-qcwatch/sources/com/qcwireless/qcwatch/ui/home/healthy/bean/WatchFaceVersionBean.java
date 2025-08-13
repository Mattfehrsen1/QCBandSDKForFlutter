package com.qcwireless.qcwatch.ui.home.healthy.bean;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceVersionBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/bean/WatchFaceVersionBean;", "", "hardwareVersion", "", "version", "(Ljava/lang/String;Ljava/lang/String;)V", "getHardwareVersion", "()Ljava/lang/String;", "setHardwareVersion", "(Ljava/lang/String;)V", "getVersion", "setVersion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFaceVersionBean {
    private String hardwareVersion;
    private String version;

    public WatchFaceVersionBean(String hardwareVersion, String version) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(version, "version");
        this.hardwareVersion = "";
        this.version = "0";
        this.hardwareVersion = hardwareVersion;
        this.version = version;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version = str;
    }
}
