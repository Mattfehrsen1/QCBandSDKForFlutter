package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GenerateOrderNumberResp.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006 "}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/GenerateOrderNumberResp;", "", "uid", "", "hardwareName", "", "watchFaceName", "orderNumber", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHardwareName", "()Ljava/lang/String;", "setHardwareName", "(Ljava/lang/String;)V", "getOrderNumber", "setOrderNumber", "getUid", "()J", "setUid", "(J)V", "getWatchFaceName", "setWatchFaceName", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class GenerateOrderNumberResp {
    private String hardwareName;
    private String orderNumber;
    private long uid;
    private String watchFaceName;

    public static /* synthetic */ GenerateOrderNumberResp copy$default(GenerateOrderNumberResp generateOrderNumberResp, long j, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = generateOrderNumberResp.uid;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = generateOrderNumberResp.hardwareName;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = generateOrderNumberResp.watchFaceName;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = generateOrderNumberResp.orderNumber;
        }
        return generateOrderNumberResp.copy(j2, str4, str5, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardwareName() {
        return this.hardwareName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getWatchFaceName() {
        return this.watchFaceName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getOrderNumber() {
        return this.orderNumber;
    }

    public final GenerateOrderNumberResp copy(long uid, String hardwareName, String watchFaceName, String orderNumber) {
        Intrinsics.checkNotNullParameter(hardwareName, "hardwareName");
        Intrinsics.checkNotNullParameter(watchFaceName, "watchFaceName");
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        return new GenerateOrderNumberResp(uid, hardwareName, watchFaceName, orderNumber);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GenerateOrderNumberResp)) {
            return false;
        }
        GenerateOrderNumberResp generateOrderNumberResp = (GenerateOrderNumberResp) other;
        return this.uid == generateOrderNumberResp.uid && Intrinsics.areEqual(this.hardwareName, generateOrderNumberResp.hardwareName) && Intrinsics.areEqual(this.watchFaceName, generateOrderNumberResp.watchFaceName) && Intrinsics.areEqual(this.orderNumber, generateOrderNumberResp.orderNumber);
    }

    public int hashCode() {
        return (((((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.hardwareName.hashCode()) * 31) + this.watchFaceName.hashCode()) * 31) + this.orderNumber.hashCode();
    }

    public String toString() {
        return "GenerateOrderNumberResp(uid=" + this.uid + ", hardwareName=" + this.hardwareName + ", watchFaceName=" + this.watchFaceName + ", orderNumber=" + this.orderNumber + ')';
    }

    public GenerateOrderNumberResp(long j, String hardwareName, String watchFaceName, String orderNumber) {
        Intrinsics.checkNotNullParameter(hardwareName, "hardwareName");
        Intrinsics.checkNotNullParameter(watchFaceName, "watchFaceName");
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        this.uid = j;
        this.hardwareName = hardwareName;
        this.watchFaceName = watchFaceName;
        this.orderNumber = orderNumber;
    }

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final String getHardwareName() {
        return this.hardwareName;
    }

    public final void setHardwareName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareName = str;
    }

    public final String getWatchFaceName() {
        return this.watchFaceName;
    }

    public final void setWatchFaceName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.watchFaceName = str;
    }

    public final String getOrderNumber() {
        return this.orderNumber;
    }

    public final void setOrderNumber(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.orderNumber = str;
    }
}
