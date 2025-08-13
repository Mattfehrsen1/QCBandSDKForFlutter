package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceRanking.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceRanking;", "", "watchfaceName", "", "hdName", "(Ljava/lang/String;Ljava/lang/String;)V", "getHdName", "()Ljava/lang/String;", "getWatchfaceName", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchFaceRanking {
    private final String hdName;
    private final String watchfaceName;

    public static /* synthetic */ WatchFaceRanking copy$default(WatchFaceRanking watchFaceRanking, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = watchFaceRanking.watchfaceName;
        }
        if ((i & 2) != 0) {
            str2 = watchFaceRanking.hdName;
        }
        return watchFaceRanking.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getWatchfaceName() {
        return this.watchfaceName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHdName() {
        return this.hdName;
    }

    public final WatchFaceRanking copy(String watchfaceName, String hdName) {
        Intrinsics.checkNotNullParameter(watchfaceName, "watchfaceName");
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        return new WatchFaceRanking(watchfaceName, hdName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchFaceRanking)) {
            return false;
        }
        WatchFaceRanking watchFaceRanking = (WatchFaceRanking) other;
        return Intrinsics.areEqual(this.watchfaceName, watchFaceRanking.watchfaceName) && Intrinsics.areEqual(this.hdName, watchFaceRanking.hdName);
    }

    public int hashCode() {
        return (this.watchfaceName.hashCode() * 31) + this.hdName.hashCode();
    }

    public String toString() {
        return "WatchFaceRanking(watchfaceName=" + this.watchfaceName + ", hdName=" + this.hdName + ')';
    }

    public WatchFaceRanking(String watchfaceName, String hdName) {
        Intrinsics.checkNotNullParameter(watchfaceName, "watchfaceName");
        Intrinsics.checkNotNullParameter(hdName, "hdName");
        this.watchfaceName = watchfaceName;
        this.hdName = hdName;
    }

    public final String getWatchfaceName() {
        return this.watchfaceName;
    }

    public final String getHdName() {
        return this.hdName;
    }
}
