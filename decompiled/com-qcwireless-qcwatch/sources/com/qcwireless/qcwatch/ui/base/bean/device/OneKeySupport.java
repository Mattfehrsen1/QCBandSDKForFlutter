package com.qcwireless.qcwatch.ui.base.bean.device;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OneKeySupport.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003Jm\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u00102\u001a\u00020\u00052\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\u001a\u0010\f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0014\"\u0004\b&\u0010\u0016¨\u00067"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/device/OneKeySupport;", "", "mac", "", "supportBloodOxygen", "", "supportBloodPressure", "supportFeature", "supportTemp", "supportManualHeart", "supportECard", "supportLocation", "supportPressure", "supportHrv", "(Ljava/lang/String;ZZZZZZZZZ)V", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "getSupportBloodOxygen", "()Z", "setSupportBloodOxygen", "(Z)V", "getSupportBloodPressure", "setSupportBloodPressure", "getSupportECard", "setSupportECard", "getSupportFeature", "setSupportFeature", "getSupportHrv", "setSupportHrv", "getSupportLocation", "setSupportLocation", "getSupportManualHeart", "setSupportManualHeart", "getSupportPressure", "setSupportPressure", "getSupportTemp", "setSupportTemp", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class OneKeySupport {
    private String mac;
    private boolean supportBloodOxygen;
    private boolean supportBloodPressure;
    private boolean supportECard;
    private boolean supportFeature;
    private boolean supportHrv;
    private boolean supportLocation;
    private boolean supportManualHeart;
    private boolean supportPressure;
    private boolean supportTemp;

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getSupportHrv() {
        return this.supportHrv;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getSupportBloodOxygen() {
        return this.supportBloodOxygen;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getSupportBloodPressure() {
        return this.supportBloodPressure;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getSupportFeature() {
        return this.supportFeature;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getSupportTemp() {
        return this.supportTemp;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getSupportManualHeart() {
        return this.supportManualHeart;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getSupportECard() {
        return this.supportECard;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getSupportLocation() {
        return this.supportLocation;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getSupportPressure() {
        return this.supportPressure;
    }

    public final OneKeySupport copy(String mac, boolean supportBloodOxygen, boolean supportBloodPressure, boolean supportFeature, boolean supportTemp, boolean supportManualHeart, boolean supportECard, boolean supportLocation, boolean supportPressure, boolean supportHrv) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        return new OneKeySupport(mac, supportBloodOxygen, supportBloodPressure, supportFeature, supportTemp, supportManualHeart, supportECard, supportLocation, supportPressure, supportHrv);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OneKeySupport)) {
            return false;
        }
        OneKeySupport oneKeySupport = (OneKeySupport) other;
        return Intrinsics.areEqual(this.mac, oneKeySupport.mac) && this.supportBloodOxygen == oneKeySupport.supportBloodOxygen && this.supportBloodPressure == oneKeySupport.supportBloodPressure && this.supportFeature == oneKeySupport.supportFeature && this.supportTemp == oneKeySupport.supportTemp && this.supportManualHeart == oneKeySupport.supportManualHeart && this.supportECard == oneKeySupport.supportECard && this.supportLocation == oneKeySupport.supportLocation && this.supportPressure == oneKeySupport.supportPressure && this.supportHrv == oneKeySupport.supportHrv;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = this.mac.hashCode() * 31;
        boolean z = this.supportBloodOxygen;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.supportBloodPressure;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        boolean z3 = this.supportFeature;
        int i5 = z3;
        if (z3 != 0) {
            i5 = 1;
        }
        int i6 = (i4 + i5) * 31;
        boolean z4 = this.supportTemp;
        int i7 = z4;
        if (z4 != 0) {
            i7 = 1;
        }
        int i8 = (i6 + i7) * 31;
        boolean z5 = this.supportManualHeart;
        int i9 = z5;
        if (z5 != 0) {
            i9 = 1;
        }
        int i10 = (i8 + i9) * 31;
        boolean z6 = this.supportECard;
        int i11 = z6;
        if (z6 != 0) {
            i11 = 1;
        }
        int i12 = (i10 + i11) * 31;
        boolean z7 = this.supportLocation;
        int i13 = z7;
        if (z7 != 0) {
            i13 = 1;
        }
        int i14 = (i12 + i13) * 31;
        boolean z8 = this.supportPressure;
        int i15 = z8;
        if (z8 != 0) {
            i15 = 1;
        }
        int i16 = (i14 + i15) * 31;
        boolean z9 = this.supportHrv;
        return i16 + (z9 ? 1 : z9 ? 1 : 0);
    }

    public String toString() {
        return "OneKeySupport(mac=" + this.mac + ", supportBloodOxygen=" + this.supportBloodOxygen + ", supportBloodPressure=" + this.supportBloodPressure + ", supportFeature=" + this.supportFeature + ", supportTemp=" + this.supportTemp + ", supportManualHeart=" + this.supportManualHeart + ", supportECard=" + this.supportECard + ", supportLocation=" + this.supportLocation + ", supportPressure=" + this.supportPressure + ", supportHrv=" + this.supportHrv + ')';
    }

    public OneKeySupport(String mac, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        this.mac = mac;
        this.supportBloodOxygen = z;
        this.supportBloodPressure = z2;
        this.supportFeature = z3;
        this.supportTemp = z4;
        this.supportManualHeart = z5;
        this.supportECard = z6;
        this.supportLocation = z7;
        this.supportPressure = z8;
        this.supportHrv = z9;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mac = str;
    }

    public final boolean getSupportBloodOxygen() {
        return this.supportBloodOxygen;
    }

    public final void setSupportBloodOxygen(boolean z) {
        this.supportBloodOxygen = z;
    }

    public final boolean getSupportBloodPressure() {
        return this.supportBloodPressure;
    }

    public final void setSupportBloodPressure(boolean z) {
        this.supportBloodPressure = z;
    }

    public final boolean getSupportFeature() {
        return this.supportFeature;
    }

    public final void setSupportFeature(boolean z) {
        this.supportFeature = z;
    }

    public final boolean getSupportTemp() {
        return this.supportTemp;
    }

    public final void setSupportTemp(boolean z) {
        this.supportTemp = z;
    }

    public final boolean getSupportManualHeart() {
        return this.supportManualHeart;
    }

    public final void setSupportManualHeart(boolean z) {
        this.supportManualHeart = z;
    }

    public final boolean getSupportECard() {
        return this.supportECard;
    }

    public final void setSupportECard(boolean z) {
        this.supportECard = z;
    }

    public final boolean getSupportLocation() {
        return this.supportLocation;
    }

    public final void setSupportLocation(boolean z) {
        this.supportLocation = z;
    }

    public final boolean getSupportPressure() {
        return this.supportPressure;
    }

    public final void setSupportPressure(boolean z) {
        this.supportPressure = z;
    }

    public final boolean getSupportHrv() {
        return this.supportHrv;
    }

    public final void setSupportHrv(boolean z) {
        this.supportHrv = z;
    }
}
