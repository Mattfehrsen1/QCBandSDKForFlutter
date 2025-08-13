package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceOrder.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\tHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrder;", "", "uid", "", "hardwareName", "", "watchFaceName", "orderNumber", "osSystem", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getHardwareName", "()Ljava/lang/String;", "getOrderNumber", "getOsSystem", "()I", "getUid", "()J", "getWatchFaceName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchFaceOrder {
    private final String hardwareName;
    private final String orderNumber;
    private final int osSystem;
    private final long uid;
    private final String watchFaceName;

    public static /* synthetic */ WatchFaceOrder copy$default(WatchFaceOrder watchFaceOrder, long j, String str, String str2, String str3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = watchFaceOrder.uid;
        }
        long j2 = j;
        if ((i2 & 2) != 0) {
            str = watchFaceOrder.hardwareName;
        }
        String str4 = str;
        if ((i2 & 4) != 0) {
            str2 = watchFaceOrder.watchFaceName;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = watchFaceOrder.orderNumber;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            i = watchFaceOrder.osSystem;
        }
        return watchFaceOrder.copy(j2, str4, str5, str6, i);
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

    /* renamed from: component5, reason: from getter */
    public final int getOsSystem() {
        return this.osSystem;
    }

    public final WatchFaceOrder copy(long uid, String hardwareName, String watchFaceName, String orderNumber, int osSystem) {
        Intrinsics.checkNotNullParameter(hardwareName, "hardwareName");
        Intrinsics.checkNotNullParameter(watchFaceName, "watchFaceName");
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        return new WatchFaceOrder(uid, hardwareName, watchFaceName, orderNumber, osSystem);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchFaceOrder)) {
            return false;
        }
        WatchFaceOrder watchFaceOrder = (WatchFaceOrder) other;
        return this.uid == watchFaceOrder.uid && Intrinsics.areEqual(this.hardwareName, watchFaceOrder.hardwareName) && Intrinsics.areEqual(this.watchFaceName, watchFaceOrder.watchFaceName) && Intrinsics.areEqual(this.orderNumber, watchFaceOrder.orderNumber) && this.osSystem == watchFaceOrder.osSystem;
    }

    public int hashCode() {
        return (((((((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.hardwareName.hashCode()) * 31) + this.watchFaceName.hashCode()) * 31) + this.orderNumber.hashCode()) * 31) + this.osSystem;
    }

    public String toString() {
        return "WatchFaceOrder(uid=" + this.uid + ", hardwareName=" + this.hardwareName + ", watchFaceName=" + this.watchFaceName + ", orderNumber=" + this.orderNumber + ", osSystem=" + this.osSystem + ')';
    }

    public WatchFaceOrder(long j, String hardwareName, String watchFaceName, String orderNumber, int i) {
        Intrinsics.checkNotNullParameter(hardwareName, "hardwareName");
        Intrinsics.checkNotNullParameter(watchFaceName, "watchFaceName");
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        this.uid = j;
        this.hardwareName = hardwareName;
        this.watchFaceName = watchFaceName;
        this.orderNumber = orderNumber;
        this.osSystem = i;
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getHardwareName() {
        return this.hardwareName;
    }

    public final String getWatchFaceName() {
        return this.watchFaceName;
    }

    public final String getOrderNumber() {
        return this.orderNumber;
    }

    public final int getOsSystem() {
        return this.osSystem;
    }
}
