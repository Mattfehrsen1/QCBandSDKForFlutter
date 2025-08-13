package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceOrderParams.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrderParams;", "", "uid", "", "orderNumber", "", "paymentStatus", "", "(JLjava/lang/String;I)V", "getOrderNumber", "()Ljava/lang/String;", "getPaymentStatus", "()I", "getUid", "()J", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchFaceOrderParams {
    private final String orderNumber;
    private final int paymentStatus;
    private final long uid;

    public static /* synthetic */ WatchFaceOrderParams copy$default(WatchFaceOrderParams watchFaceOrderParams, long j, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = watchFaceOrderParams.uid;
        }
        if ((i2 & 2) != 0) {
            str = watchFaceOrderParams.orderNumber;
        }
        if ((i2 & 4) != 0) {
            i = watchFaceOrderParams.paymentStatus;
        }
        return watchFaceOrderParams.copy(j, str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getOrderNumber() {
        return this.orderNumber;
    }

    /* renamed from: component3, reason: from getter */
    public final int getPaymentStatus() {
        return this.paymentStatus;
    }

    public final WatchFaceOrderParams copy(long uid, String orderNumber, int paymentStatus) {
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        return new WatchFaceOrderParams(uid, orderNumber, paymentStatus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchFaceOrderParams)) {
            return false;
        }
        WatchFaceOrderParams watchFaceOrderParams = (WatchFaceOrderParams) other;
        return this.uid == watchFaceOrderParams.uid && Intrinsics.areEqual(this.orderNumber, watchFaceOrderParams.orderNumber) && this.paymentStatus == watchFaceOrderParams.paymentStatus;
    }

    public int hashCode() {
        return (((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.orderNumber.hashCode()) * 31) + this.paymentStatus;
    }

    public String toString() {
        return "WatchFaceOrderParams(uid=" + this.uid + ", orderNumber=" + this.orderNumber + ", paymentStatus=" + this.paymentStatus + ')';
    }

    public WatchFaceOrderParams(long j, String orderNumber, int i) {
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        this.uid = j;
        this.orderNumber = orderNumber;
        this.paymentStatus = i;
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getOrderNumber() {
        return this.orderNumber;
    }

    public final int getPaymentStatus() {
        return this.paymentStatus;
    }
}
