package com.qcwireless.qcwatch.ui.base.bean.request.device;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LastOtaRequest.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003JY\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006+"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/device/LastOtaRequest;", "", "appId", "", "uid", "", "hardwareVersion", "", "romVersion", "os", "mac", "country", "dev", "(IJLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;I)V", "getAppId", "()I", "getCountry", "()Ljava/lang/String;", "getDev", "setDev", "(I)V", "getHardwareVersion", "getMac", "getOs", "getRomVersion", "setRomVersion", "(Ljava/lang/String;)V", "getUid", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class LastOtaRequest {
    private final int appId;
    private final String country;
    private int dev;
    private final String hardwareVersion;
    private final String mac;
    private final int os;
    private String romVersion;
    private final long uid;

    /* renamed from: component1, reason: from getter */
    public final int getAppId() {
        return this.appId;
    }

    /* renamed from: component2, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRomVersion() {
        return this.romVersion;
    }

    /* renamed from: component5, reason: from getter */
    public final int getOs() {
        return this.os;
    }

    /* renamed from: component6, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component7, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* renamed from: component8, reason: from getter */
    public final int getDev() {
        return this.dev;
    }

    public final LastOtaRequest copy(int appId, long uid, String hardwareVersion, String romVersion, int os, String mac, String country, int dev) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(romVersion, "romVersion");
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(country, "country");
        return new LastOtaRequest(appId, uid, hardwareVersion, romVersion, os, mac, country, dev);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LastOtaRequest)) {
            return false;
        }
        LastOtaRequest lastOtaRequest = (LastOtaRequest) other;
        return this.appId == lastOtaRequest.appId && this.uid == lastOtaRequest.uid && Intrinsics.areEqual(this.hardwareVersion, lastOtaRequest.hardwareVersion) && Intrinsics.areEqual(this.romVersion, lastOtaRequest.romVersion) && this.os == lastOtaRequest.os && Intrinsics.areEqual(this.mac, lastOtaRequest.mac) && Intrinsics.areEqual(this.country, lastOtaRequest.country) && this.dev == lastOtaRequest.dev;
    }

    public int hashCode() {
        return (((((((((((((this.appId * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.uid)) * 31) + this.hardwareVersion.hashCode()) * 31) + this.romVersion.hashCode()) * 31) + this.os) * 31) + this.mac.hashCode()) * 31) + this.country.hashCode()) * 31) + this.dev;
    }

    public String toString() {
        return "LastOtaRequest(appId=" + this.appId + ", uid=" + this.uid + ", hardwareVersion=" + this.hardwareVersion + ", romVersion=" + this.romVersion + ", os=" + this.os + ", mac=" + this.mac + ", country=" + this.country + ", dev=" + this.dev + ')';
    }

    public LastOtaRequest(int i, long j, String hardwareVersion, String romVersion, int i2, String mac, String country, int i3) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(romVersion, "romVersion");
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(country, "country");
        this.appId = i;
        this.uid = j;
        this.hardwareVersion = hardwareVersion;
        this.romVersion = romVersion;
        this.os = i2;
        this.mac = mac;
        this.country = country;
        this.dev = i3;
    }

    public /* synthetic */ LastOtaRequest(int i, long j, String str, String str2, int i2, String str3, String str4, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, j, str, str2, (i4 & 16) != 0 ? 1 : i2, str3, str4, (i4 & 128) != 0 ? 2 : i3);
    }

    public final int getAppId() {
        return this.appId;
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final String getRomVersion() {
        return this.romVersion;
    }

    public final void setRomVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.romVersion = str;
    }

    public final int getOs() {
        return this.os;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getCountry() {
        return this.country;
    }

    public final int getDev() {
        return this.dev;
    }

    public final void setDev(int i) {
        this.dev = i;
    }
}
