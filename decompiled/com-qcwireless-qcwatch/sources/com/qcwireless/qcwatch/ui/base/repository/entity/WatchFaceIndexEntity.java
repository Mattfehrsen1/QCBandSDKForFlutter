package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceIndexEntity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\bHÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\fHÆ\u0003J\t\u0010/\u001a\u00020\fHÆ\u0003Jc\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u00020\fHÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001e\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001e\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001c\"\u0004\b&\u0010\u001e¨\u00066"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchFaceIndexEntity;", "", "name", "", "hardwareVersion", "preImageUrl", "binUrl", "price", "", "localImageUrl", "localBinUrl", "typeId", "", "position", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/String;Ljava/lang/String;II)V", "getBinUrl", "()Ljava/lang/String;", "setBinUrl", "(Ljava/lang/String;)V", "getHardwareVersion", "setHardwareVersion", "getLocalBinUrl", "setLocalBinUrl", "getLocalImageUrl", "setLocalImageUrl", "getName", "setName", "getPosition", "()I", "setPosition", "(I)V", "getPreImageUrl", "setPreImageUrl", "getPrice", "()F", "setPrice", "(F)V", "getTypeId", "setTypeId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchFaceIndexEntity {
    private String binUrl;
    private String hardwareVersion;
    private String localBinUrl;
    private String localImageUrl;
    private String name;
    private int position;
    private String preImageUrl;
    private float price;
    private int typeId;

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBinUrl() {
        return this.binUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final float getPrice() {
        return this.price;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLocalImageUrl() {
        return this.localImageUrl;
    }

    /* renamed from: component7, reason: from getter */
    public final String getLocalBinUrl() {
        return this.localBinUrl;
    }

    /* renamed from: component8, reason: from getter */
    public final int getTypeId() {
        return this.typeId;
    }

    /* renamed from: component9, reason: from getter */
    public final int getPosition() {
        return this.position;
    }

    public final WatchFaceIndexEntity copy(String name, String hardwareVersion, String preImageUrl, String binUrl, float price, String localImageUrl, String localBinUrl, int typeId, int position) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(localBinUrl, "localBinUrl");
        return new WatchFaceIndexEntity(name, hardwareVersion, preImageUrl, binUrl, price, localImageUrl, localBinUrl, typeId, position);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchFaceIndexEntity)) {
            return false;
        }
        WatchFaceIndexEntity watchFaceIndexEntity = (WatchFaceIndexEntity) other;
        return Intrinsics.areEqual(this.name, watchFaceIndexEntity.name) && Intrinsics.areEqual(this.hardwareVersion, watchFaceIndexEntity.hardwareVersion) && Intrinsics.areEqual(this.preImageUrl, watchFaceIndexEntity.preImageUrl) && Intrinsics.areEqual(this.binUrl, watchFaceIndexEntity.binUrl) && Intrinsics.areEqual((Object) Float.valueOf(this.price), (Object) Float.valueOf(watchFaceIndexEntity.price)) && Intrinsics.areEqual(this.localImageUrl, watchFaceIndexEntity.localImageUrl) && Intrinsics.areEqual(this.localBinUrl, watchFaceIndexEntity.localBinUrl) && this.typeId == watchFaceIndexEntity.typeId && this.position == watchFaceIndexEntity.position;
    }

    public int hashCode() {
        return (((((((((((((((this.name.hashCode() * 31) + this.hardwareVersion.hashCode()) * 31) + this.preImageUrl.hashCode()) * 31) + this.binUrl.hashCode()) * 31) + Float.floatToIntBits(this.price)) * 31) + this.localImageUrl.hashCode()) * 31) + this.localBinUrl.hashCode()) * 31) + this.typeId) * 31) + this.position;
    }

    public String toString() {
        return "WatchFaceIndexEntity(name=" + this.name + ", hardwareVersion=" + this.hardwareVersion + ", preImageUrl=" + this.preImageUrl + ", binUrl=" + this.binUrl + ", price=" + this.price + ", localImageUrl=" + this.localImageUrl + ", localBinUrl=" + this.localBinUrl + ", typeId=" + this.typeId + ", position=" + this.position + ')';
    }

    public WatchFaceIndexEntity(String name, String hardwareVersion, String preImageUrl, String binUrl, float f, String localImageUrl, String localBinUrl, int i, int i2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(localBinUrl, "localBinUrl");
        this.name = name;
        this.hardwareVersion = hardwareVersion;
        this.preImageUrl = preImageUrl;
        this.binUrl = binUrl;
        this.price = f;
        this.localImageUrl = localImageUrl;
        this.localBinUrl = localBinUrl;
        this.typeId = i;
        this.position = i2;
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

    public final float getPrice() {
        return this.price;
    }

    public final void setPrice(float f) {
        this.price = f;
    }

    public final String getLocalImageUrl() {
        return this.localImageUrl;
    }

    public final void setLocalImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localImageUrl = str;
    }

    public final String getLocalBinUrl() {
        return this.localBinUrl;
    }

    public final void setLocalBinUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localBinUrl = str;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }
}
