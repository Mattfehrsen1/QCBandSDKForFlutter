package com.qcwireless.qcwatch.ui.device.more.ecard.bean;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SoftwareLinks.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareLinks;", "", "()V", "facebookUrl", "", "getFacebookUrl", "()Ljava/lang/String;", "setFacebookUrl", "(Ljava/lang/String;)V", "qqUrl", "getQqUrl", "setQqUrl", "twitterUrl", "getTwitterUrl", "setTwitterUrl", "wechatUrl", "getWechatUrl", "setWechatUrl", "whatsappUrl", "getWhatsappUrl", "setWhatsappUrl", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SoftwareLinks {
    private String qqUrl = "";
    private String wechatUrl = "";
    private String facebookUrl = "";
    private String whatsappUrl = "";
    private String twitterUrl = "";

    public final String getQqUrl() {
        return this.qqUrl;
    }

    public final void setQqUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.qqUrl = str;
    }

    public final String getWechatUrl() {
        return this.wechatUrl;
    }

    public final void setWechatUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.wechatUrl = str;
    }

    public final String getFacebookUrl() {
        return this.facebookUrl;
    }

    public final void setFacebookUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.facebookUrl = str;
    }

    public final String getWhatsappUrl() {
        return this.whatsappUrl;
    }

    public final void setWhatsappUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.whatsappUrl = str;
    }

    public final String getTwitterUrl() {
        return this.twitterUrl;
    }

    public final void setTwitterUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.twitterUrl = str;
    }
}
