package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceIndex.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0005HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006%"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceIndex;", "", "hardwareVersion", "", "typeId", "", "typeInfo", "watchfaceName", "price", "preImageUrl", "binUrl", "indexPosition", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;I)V", "getBinUrl", "()Ljava/lang/String;", "getHardwareVersion", "getIndexPosition", "()I", "getPreImageUrl", "getPrice", "getTypeId", "getTypeInfo", "getWatchfaceName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchFaceIndex {
    private final String binUrl;
    private final String hardwareVersion;
    private final int indexPosition;
    private final String preImageUrl;
    private final int price;
    private final int typeId;
    private final String typeInfo;
    private final String watchfaceName;

    /* renamed from: component1, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTypeId() {
        return this.typeId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTypeInfo() {
        return this.typeInfo;
    }

    /* renamed from: component4, reason: from getter */
    public final String getWatchfaceName() {
        return this.watchfaceName;
    }

    /* renamed from: component5, reason: from getter */
    public final int getPrice() {
        return this.price;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBinUrl() {
        return this.binUrl;
    }

    /* renamed from: component8, reason: from getter */
    public final int getIndexPosition() {
        return this.indexPosition;
    }

    public final WatchFaceIndex copy(String hardwareVersion, int typeId, String typeInfo, String watchfaceName, int price, String preImageUrl, String binUrl, int indexPosition) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(typeInfo, "typeInfo");
        Intrinsics.checkNotNullParameter(watchfaceName, "watchfaceName");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        return new WatchFaceIndex(hardwareVersion, typeId, typeInfo, watchfaceName, price, preImageUrl, binUrl, indexPosition);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchFaceIndex)) {
            return false;
        }
        WatchFaceIndex watchFaceIndex = (WatchFaceIndex) other;
        return Intrinsics.areEqual(this.hardwareVersion, watchFaceIndex.hardwareVersion) && this.typeId == watchFaceIndex.typeId && Intrinsics.areEqual(this.typeInfo, watchFaceIndex.typeInfo) && Intrinsics.areEqual(this.watchfaceName, watchFaceIndex.watchfaceName) && this.price == watchFaceIndex.price && Intrinsics.areEqual(this.preImageUrl, watchFaceIndex.preImageUrl) && Intrinsics.areEqual(this.binUrl, watchFaceIndex.binUrl) && this.indexPosition == watchFaceIndex.indexPosition;
    }

    public int hashCode() {
        return (((((((((((((this.hardwareVersion.hashCode() * 31) + this.typeId) * 31) + this.typeInfo.hashCode()) * 31) + this.watchfaceName.hashCode()) * 31) + this.price) * 31) + this.preImageUrl.hashCode()) * 31) + this.binUrl.hashCode()) * 31) + this.indexPosition;
    }

    public String toString() {
        return "WatchFaceIndex(hardwareVersion=" + this.hardwareVersion + ", typeId=" + this.typeId + ", typeInfo=" + this.typeInfo + ", watchfaceName=" + this.watchfaceName + ", price=" + this.price + ", preImageUrl=" + this.preImageUrl + ", binUrl=" + this.binUrl + ", indexPosition=" + this.indexPosition + ')';
    }

    public WatchFaceIndex(String hardwareVersion, int i, String typeInfo, String watchfaceName, int i2, String preImageUrl, String binUrl, int i3) {
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(typeInfo, "typeInfo");
        Intrinsics.checkNotNullParameter(watchfaceName, "watchfaceName");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        this.hardwareVersion = hardwareVersion;
        this.typeId = i;
        this.typeInfo = typeInfo;
        this.watchfaceName = watchfaceName;
        this.price = i2;
        this.preImageUrl = preImageUrl;
        this.binUrl = binUrl;
        this.indexPosition = i3;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final String getTypeInfo() {
        return this.typeInfo;
    }

    public final String getWatchfaceName() {
        return this.watchfaceName;
    }

    public final int getPrice() {
        return this.price;
    }

    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    public final String getBinUrl() {
        return this.binUrl;
    }

    public final int getIndexPosition() {
        return this.indexPosition;
    }
}
