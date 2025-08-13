package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PayWatchFace.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\nHÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\nHÖ\u0001J\t\u00101\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001e\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u00062"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/PayWatchFace;", "", "uid", "", "name", "", "hardwareVersion", "preImageUrl", "binUrl", "payStatus", "", "orderNumber", "googleOrderId", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getBinUrl", "()Ljava/lang/String;", "setBinUrl", "(Ljava/lang/String;)V", "getGoogleOrderId", "setGoogleOrderId", "getHardwareVersion", "setHardwareVersion", "getName", "setName", "getOrderNumber", "setOrderNumber", "getPayStatus", "()I", "setPayStatus", "(I)V", "getPreImageUrl", "setPreImageUrl", "getUid", "()J", "setUid", "(J)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PayWatchFace {
    private String binUrl;
    private String googleOrderId;
    private String hardwareVersion;
    private String name;
    private String orderNumber;
    private int payStatus;
    private String preImageUrl;
    private long uid;

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final String getBinUrl() {
        return this.binUrl;
    }

    /* renamed from: component6, reason: from getter */
    public final int getPayStatus() {
        return this.payStatus;
    }

    /* renamed from: component7, reason: from getter */
    public final String getOrderNumber() {
        return this.orderNumber;
    }

    /* renamed from: component8, reason: from getter */
    public final String getGoogleOrderId() {
        return this.googleOrderId;
    }

    public final PayWatchFace copy(long uid, String name, String hardwareVersion, String preImageUrl, String binUrl, int payStatus, String orderNumber, String googleOrderId) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        Intrinsics.checkNotNullParameter(googleOrderId, "googleOrderId");
        return new PayWatchFace(uid, name, hardwareVersion, preImageUrl, binUrl, payStatus, orderNumber, googleOrderId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PayWatchFace)) {
            return false;
        }
        PayWatchFace payWatchFace = (PayWatchFace) other;
        return this.uid == payWatchFace.uid && Intrinsics.areEqual(this.name, payWatchFace.name) && Intrinsics.areEqual(this.hardwareVersion, payWatchFace.hardwareVersion) && Intrinsics.areEqual(this.preImageUrl, payWatchFace.preImageUrl) && Intrinsics.areEqual(this.binUrl, payWatchFace.binUrl) && this.payStatus == payWatchFace.payStatus && Intrinsics.areEqual(this.orderNumber, payWatchFace.orderNumber) && Intrinsics.areEqual(this.googleOrderId, payWatchFace.googleOrderId);
    }

    public int hashCode() {
        return (((((((((((((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.name.hashCode()) * 31) + this.hardwareVersion.hashCode()) * 31) + this.preImageUrl.hashCode()) * 31) + this.binUrl.hashCode()) * 31) + this.payStatus) * 31) + this.orderNumber.hashCode()) * 31) + this.googleOrderId.hashCode();
    }

    public String toString() {
        return "PayWatchFace(uid=" + this.uid + ", name=" + this.name + ", hardwareVersion=" + this.hardwareVersion + ", preImageUrl=" + this.preImageUrl + ", binUrl=" + this.binUrl + ", payStatus=" + this.payStatus + ", orderNumber=" + this.orderNumber + ", googleOrderId=" + this.googleOrderId + ')';
    }

    public PayWatchFace(long j, String name, String hardwareVersion, String preImageUrl, String binUrl, int i, String orderNumber, String googleOrderId) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(orderNumber, "orderNumber");
        Intrinsics.checkNotNullParameter(googleOrderId, "googleOrderId");
        this.uid = j;
        this.name = name;
        this.hardwareVersion = hardwareVersion;
        this.preImageUrl = preImageUrl;
        this.binUrl = binUrl;
        this.payStatus = i;
        this.orderNumber = orderNumber;
        this.googleOrderId = googleOrderId;
    }

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    public final void setPreImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.preImageUrl = str;
    }

    public final String getBinUrl() {
        return this.binUrl;
    }

    public final void setBinUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.binUrl = str;
    }

    public final int getPayStatus() {
        return this.payStatus;
    }

    public final void setPayStatus(int i) {
        this.payStatus = i;
    }

    public final String getOrderNumber() {
        return this.orderNumber;
    }

    public final void setOrderNumber(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.orderNumber = str;
    }

    public final String getGoogleOrderId() {
        return this.googleOrderId;
    }

    public final void setGoogleOrderId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.googleOrderId = str;
    }
}
