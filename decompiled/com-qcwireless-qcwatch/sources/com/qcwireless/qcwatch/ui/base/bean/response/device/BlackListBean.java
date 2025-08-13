package com.qcwireless.qcwatch.ui.base.bean.response.device;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlackListBean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/BlackListBean;", "", "address", "", NotificationCompat.CATEGORY_STATUS, "", "(Ljava/lang/String;I)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getStatus", "()I", "setStatus", "(I)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BlackListBean {
    private String address;
    private int status;

    public static /* synthetic */ BlackListBean copy$default(BlackListBean blackListBean, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = blackListBean.address;
        }
        if ((i2 & 2) != 0) {
            i = blackListBean.status;
        }
        return blackListBean.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component2, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final BlackListBean copy(String address, int status) {
        Intrinsics.checkNotNullParameter(address, "address");
        return new BlackListBean(address, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlackListBean)) {
            return false;
        }
        BlackListBean blackListBean = (BlackListBean) other;
        return Intrinsics.areEqual(this.address, blackListBean.address) && this.status == blackListBean.status;
    }

    public int hashCode() {
        return (this.address.hashCode() * 31) + this.status;
    }

    public String toString() {
        return "BlackListBean(address=" + this.address + ", status=" + this.status + ')';
    }

    public BlackListBean(String address, int i) {
        Intrinsics.checkNotNullParameter(address, "address");
        this.address = address;
        this.status = i;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.address = str;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }
}
