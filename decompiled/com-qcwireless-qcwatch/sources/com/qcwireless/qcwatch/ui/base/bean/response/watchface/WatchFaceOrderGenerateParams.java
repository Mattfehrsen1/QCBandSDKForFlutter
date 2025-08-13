package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceOrderGenerateParams.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceOrderGenerateParams;", "", "uid", "", "hdName", "", "watchfaceName", "(JLjava/lang/String;Ljava/lang/String;)V", "getHdName", "()Ljava/lang/String;", "getUid", "()J", "getWatchfaceName", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchFaceOrderGenerateParams {
    private final String hdName;
    private final long uid;
    private final String watchfaceName;

    public static /* synthetic */ WatchFaceOrderGenerateParams copy$default(WatchFaceOrderGenerateParams watchFaceOrderGenerateParams, long j, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = watchFaceOrderGenerateParams.uid;
        }
        if ((i & 2) != 0) {
            str = watchFaceOrderGenerateParams.hdName;
        }
        if ((i & 4) != 0) {
            str2 = watchFaceOrderGenerateParams.watchfaceName;
        }
        return watchFaceOrderGenerateParams.copy(j, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHdName() {
        return this.hdName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getWatchfaceName() {
        return this.watchfaceName;
    }

    public final WatchFaceOrderGenerateParams copy(long uid, String hdName, String watchfaceName) {
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        Intrinsics.checkNotNullParameter(watchfaceName, "watchfaceName");
        return new WatchFaceOrderGenerateParams(uid, hdName, watchfaceName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchFaceOrderGenerateParams)) {
            return false;
        }
        WatchFaceOrderGenerateParams watchFaceOrderGenerateParams = (WatchFaceOrderGenerateParams) other;
        return this.uid == watchFaceOrderGenerateParams.uid && Intrinsics.areEqual(this.hdName, watchFaceOrderGenerateParams.hdName) && Intrinsics.areEqual(this.watchfaceName, watchFaceOrderGenerateParams.watchfaceName);
    }

    public int hashCode() {
        return (((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.hdName.hashCode()) * 31) + this.watchfaceName.hashCode();
    }

    public String toString() {
        return "WatchFaceOrderGenerateParams(uid=" + this.uid + ", hdName=" + this.hdName + ", watchfaceName=" + this.watchfaceName + ')';
    }

    public WatchFaceOrderGenerateParams(long j, String hdName, String watchfaceName) {
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        Intrinsics.checkNotNullParameter(watchfaceName, "watchfaceName");
        this.uid = j;
        this.hdName = hdName;
        this.watchfaceName = watchfaceName;
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getHdName() {
        return this.hdName;
    }

    public final String getWatchfaceName() {
        return this.watchfaceName;
    }
}
