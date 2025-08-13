package com.qcwireless.qcwatch.ui.home.sport.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeSportDetail.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/bean/HomeSportDetail;", "", "sportType", "", "distance", "", "calorie", "dateTime", "", "(IFFLjava/lang/String;)V", "getCalorie", "()F", "getDateTime", "()Ljava/lang/String;", "getDistance", "getSportType", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class HomeSportDetail {
    private final float calorie;
    private final String dateTime;
    private final float distance;
    private final int sportType;

    public static /* synthetic */ HomeSportDetail copy$default(HomeSportDetail homeSportDetail, int i, float f, float f2, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = homeSportDetail.sportType;
        }
        if ((i2 & 2) != 0) {
            f = homeSportDetail.distance;
        }
        if ((i2 & 4) != 0) {
            f2 = homeSportDetail.calorie;
        }
        if ((i2 & 8) != 0) {
            str = homeSportDetail.dateTime;
        }
        return homeSportDetail.copy(i, f, f2, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getSportType() {
        return this.sportType;
    }

    /* renamed from: component2, reason: from getter */
    public final float getDistance() {
        return this.distance;
    }

    /* renamed from: component3, reason: from getter */
    public final float getCalorie() {
        return this.calorie;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDateTime() {
        return this.dateTime;
    }

    public final HomeSportDetail copy(int sportType, float distance, float calorie, String dateTime) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        return new HomeSportDetail(sportType, distance, calorie, dateTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeSportDetail)) {
            return false;
        }
        HomeSportDetail homeSportDetail = (HomeSportDetail) other;
        return this.sportType == homeSportDetail.sportType && Intrinsics.areEqual((Object) Float.valueOf(this.distance), (Object) Float.valueOf(homeSportDetail.distance)) && Intrinsics.areEqual((Object) Float.valueOf(this.calorie), (Object) Float.valueOf(homeSportDetail.calorie)) && Intrinsics.areEqual(this.dateTime, homeSportDetail.dateTime);
    }

    public int hashCode() {
        return (((((this.sportType * 31) + Float.floatToIntBits(this.distance)) * 31) + Float.floatToIntBits(this.calorie)) * 31) + this.dateTime.hashCode();
    }

    public String toString() {
        return "HomeSportDetail(sportType=" + this.sportType + ", distance=" + this.distance + ", calorie=" + this.calorie + ", dateTime=" + this.dateTime + ')';
    }

    public HomeSportDetail(int i, float f, float f2, String dateTime) {
        Intrinsics.checkNotNullParameter(dateTime, "dateTime");
        this.sportType = i;
        this.distance = f;
        this.calorie = f2;
        this.dateTime = dateTime;
    }

    public final int getSportType() {
        return this.sportType;
    }

    public final float getDistance() {
        return this.distance;
    }

    public final float getCalorie() {
        return this.calorie;
    }

    public final String getDateTime() {
        return this.dateTime;
    }
}
