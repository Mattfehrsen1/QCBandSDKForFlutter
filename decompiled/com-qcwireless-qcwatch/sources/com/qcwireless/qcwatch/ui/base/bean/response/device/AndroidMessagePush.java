package com.qcwireless.qcwatch.ui.base.bean.response.device;

import kotlin.Metadata;

/* compiled from: AndroidMessagePush.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/AndroidMessagePush;", "", "()V", "appName", "", "getAppName", "()Ljava/lang/String;", "setAppName", "(Ljava/lang/String;)V", "iconLink", "getIconLink", "setIconLink", "packageName", "getPackageName", "setPackageName", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidMessagePush {
    private String appName;
    private String iconLink;
    private String packageName;

    public final String getPackageName() {
        return this.packageName;
    }

    public final void setPackageName(String str) {
        this.packageName = str;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final void setAppName(String str) {
        this.appName = str;
    }

    public final String getIconLink() {
        return this.iconLink;
    }

    public final void setIconLink(String str) {
        this.iconLink = str;
    }
}
