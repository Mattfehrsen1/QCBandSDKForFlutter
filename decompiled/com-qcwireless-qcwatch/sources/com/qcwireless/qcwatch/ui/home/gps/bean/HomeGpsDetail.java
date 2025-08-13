package com.qcwireless.qcwatch.ui.home.gps.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeGpsDetail.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/bean/HomeGpsDetail;", "", "distance", "", "dateTime", "", "(Ljava/lang/String;J)V", "getDateTime", "()J", "getDistance", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class HomeGpsDetail {
    private final long dateTime;
    private final String distance;

    public static /* synthetic */ HomeGpsDetail copy$default(HomeGpsDetail homeGpsDetail, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = homeGpsDetail.distance;
        }
        if ((i & 2) != 0) {
            j = homeGpsDetail.dateTime;
        }
        return homeGpsDetail.copy(str, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDistance() {
        return this.distance;
    }

    /* renamed from: component2, reason: from getter */
    public final long getDateTime() {
        return this.dateTime;
    }

    public final HomeGpsDetail copy(String distance, long dateTime) {
        Intrinsics.checkNotNullParameter(distance, "distance");
        return new HomeGpsDetail(distance, dateTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeGpsDetail)) {
            return false;
        }
        HomeGpsDetail homeGpsDetail = (HomeGpsDetail) other;
        return Intrinsics.areEqual(this.distance, homeGpsDetail.distance) && this.dateTime == homeGpsDetail.dateTime;
    }

    public int hashCode() {
        return (this.distance.hashCode() * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.dateTime);
    }

    public String toString() {
        return "HomeGpsDetail(distance=" + this.distance + ", dateTime=" + this.dateTime + ')';
    }

    public HomeGpsDetail(String distance, long j) {
        Intrinsics.checkNotNullParameter(distance, "distance");
        this.distance = distance;
        this.dateTime = j;
    }

    public final String getDistance() {
        return this.distance;
    }

    public final long getDateTime() {
        return this.dateTime;
    }
}
