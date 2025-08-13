package com.qcwireless.qcwatch.ui.base.bean.request.collection;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CollectionRequest.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u000eHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003Jw\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006/"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/collection/CollectionRequest;", "", "deviceName", "", "deviceAddress", "country", "phoneModel", "phoneBrand", "osVersion", "appVersion", "hdVersion", "fwVersion", "userEmail", "uid", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getAppVersion", "()Ljava/lang/String;", "getCountry", "getDeviceAddress", "getDeviceName", "getFwVersion", "getHdVersion", "getOsVersion", "getPhoneBrand", "getPhoneModel", "getUid", "()J", "getUserEmail", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class CollectionRequest {
    private final String appVersion;
    private final String country;
    private final String deviceAddress;
    private final String deviceName;
    private final String fwVersion;
    private final String hdVersion;
    private final String osVersion;
    private final String phoneBrand;
    private final String phoneModel;
    private final long uid;
    private final String userEmail;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: component10, reason: from getter */
    public final String getUserEmail() {
        return this.userEmail;
    }

    /* renamed from: component11, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPhoneModel() {
        return this.phoneModel;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPhoneBrand() {
        return this.phoneBrand;
    }

    /* renamed from: component6, reason: from getter */
    public final String getOsVersion() {
        return this.osVersion;
    }

    /* renamed from: component7, reason: from getter */
    public final String getAppVersion() {
        return this.appVersion;
    }

    /* renamed from: component8, reason: from getter */
    public final String getHdVersion() {
        return this.hdVersion;
    }

    /* renamed from: component9, reason: from getter */
    public final String getFwVersion() {
        return this.fwVersion;
    }

    public final CollectionRequest copy(String deviceName, String deviceAddress, String country, String phoneModel, String phoneBrand, String osVersion, String appVersion, String hdVersion, String fwVersion, String userEmail, long uid) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(phoneModel, "phoneModel");
        Intrinsics.checkNotNullParameter(phoneBrand, "phoneBrand");
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(hdVersion, "hdVersion");
        Intrinsics.checkNotNullParameter(fwVersion, "fwVersion");
        Intrinsics.checkNotNullParameter(userEmail, "userEmail");
        return new CollectionRequest(deviceName, deviceAddress, country, phoneModel, phoneBrand, osVersion, appVersion, hdVersion, fwVersion, userEmail, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CollectionRequest)) {
            return false;
        }
        CollectionRequest collectionRequest = (CollectionRequest) other;
        return Intrinsics.areEqual(this.deviceName, collectionRequest.deviceName) && Intrinsics.areEqual(this.deviceAddress, collectionRequest.deviceAddress) && Intrinsics.areEqual(this.country, collectionRequest.country) && Intrinsics.areEqual(this.phoneModel, collectionRequest.phoneModel) && Intrinsics.areEqual(this.phoneBrand, collectionRequest.phoneBrand) && Intrinsics.areEqual(this.osVersion, collectionRequest.osVersion) && Intrinsics.areEqual(this.appVersion, collectionRequest.appVersion) && Intrinsics.areEqual(this.hdVersion, collectionRequest.hdVersion) && Intrinsics.areEqual(this.fwVersion, collectionRequest.fwVersion) && Intrinsics.areEqual(this.userEmail, collectionRequest.userEmail) && this.uid == collectionRequest.uid;
    }

    public int hashCode() {
        return (((((((((((((((((((this.deviceName.hashCode() * 31) + this.deviceAddress.hashCode()) * 31) + this.country.hashCode()) * 31) + this.phoneModel.hashCode()) * 31) + this.phoneBrand.hashCode()) * 31) + this.osVersion.hashCode()) * 31) + this.appVersion.hashCode()) * 31) + this.hdVersion.hashCode()) * 31) + this.fwVersion.hashCode()) * 31) + this.userEmail.hashCode()) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.uid);
    }

    public String toString() {
        return "CollectionRequest(deviceName=" + this.deviceName + ", deviceAddress=" + this.deviceAddress + ", country=" + this.country + ", phoneModel=" + this.phoneModel + ", phoneBrand=" + this.phoneBrand + ", osVersion=" + this.osVersion + ", appVersion=" + this.appVersion + ", hdVersion=" + this.hdVersion + ", fwVersion=" + this.fwVersion + ", userEmail=" + this.userEmail + ", uid=" + this.uid + ')';
    }

    public CollectionRequest(String deviceName, String deviceAddress, String country, String phoneModel, String phoneBrand, String osVersion, String appVersion, String hdVersion, String fwVersion, String userEmail, long j) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(phoneModel, "phoneModel");
        Intrinsics.checkNotNullParameter(phoneBrand, "phoneBrand");
        Intrinsics.checkNotNullParameter(osVersion, "osVersion");
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(hdVersion, "hdVersion");
        Intrinsics.checkNotNullParameter(fwVersion, "fwVersion");
        Intrinsics.checkNotNullParameter(userEmail, "userEmail");
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
        this.country = country;
        this.phoneModel = phoneModel;
        this.phoneBrand = phoneBrand;
        this.osVersion = osVersion;
        this.appVersion = appVersion;
        this.hdVersion = hdVersion;
        this.fwVersion = fwVersion;
        this.userEmail = userEmail;
        this.uid = j;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final String getFwVersion() {
        return this.fwVersion;
    }

    public final String getHdVersion() {
        return this.hdVersion;
    }

    public final String getOsVersion() {
        return this.osVersion;
    }

    public final String getPhoneBrand() {
        return this.phoneBrand;
    }

    public final String getPhoneModel() {
        return this.phoneModel;
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getUserEmail() {
        return this.userEmail;
    }
}
