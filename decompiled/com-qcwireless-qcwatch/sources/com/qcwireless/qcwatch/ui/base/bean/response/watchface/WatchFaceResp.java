package com.qcwireless.qcwatch.ui.base.bean.response.watchface;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WatchFaceResp.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\bHÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u000bHÆ\u0003J\t\u0010+\u001a\u00020\u000bHÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u000bHÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011¨\u00062"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/watchface/WatchFaceResp;", "", "binUrl", "", "hardwareVersion", "name", "preImageUrl", "price", "", "watchDesc", "typeId", "", "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/String;II)V", "getBinUrl", "()Ljava/lang/String;", "setBinUrl", "(Ljava/lang/String;)V", "getHardwareVersion", "setHardwareVersion", "getName", "setName", "getPreImageUrl", "setPreImageUrl", "getPrice", "()F", "setPrice", "(F)V", "getTypeId", "()I", "setTypeId", "(I)V", "getVersion", "setVersion", "getWatchDesc", "setWatchDesc", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WatchFaceResp {
    private String binUrl;
    private String hardwareVersion;
    private String name;
    private String preImageUrl;
    private float price;
    private int typeId;
    private int version;
    private String watchDesc;

    /* renamed from: component1, reason: from getter */
    public final String getBinUrl() {
        return this.binUrl;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    /* renamed from: component5, reason: from getter */
    public final float getPrice() {
        return this.price;
    }

    /* renamed from: component6, reason: from getter */
    public final String getWatchDesc() {
        return this.watchDesc;
    }

    /* renamed from: component7, reason: from getter */
    public final int getTypeId() {
        return this.typeId;
    }

    /* renamed from: component8, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final WatchFaceResp copy(String binUrl, String hardwareVersion, String name, String preImageUrl, float price, String watchDesc, int typeId, int version) {
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(watchDesc, "watchDesc");
        return new WatchFaceResp(binUrl, hardwareVersion, name, preImageUrl, price, watchDesc, typeId, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchFaceResp)) {
            return false;
        }
        WatchFaceResp watchFaceResp = (WatchFaceResp) other;
        return Intrinsics.areEqual(this.binUrl, watchFaceResp.binUrl) && Intrinsics.areEqual(this.hardwareVersion, watchFaceResp.hardwareVersion) && Intrinsics.areEqual(this.name, watchFaceResp.name) && Intrinsics.areEqual(this.preImageUrl, watchFaceResp.preImageUrl) && Intrinsics.areEqual((Object) Float.valueOf(this.price), (Object) Float.valueOf(watchFaceResp.price)) && Intrinsics.areEqual(this.watchDesc, watchFaceResp.watchDesc) && this.typeId == watchFaceResp.typeId && this.version == watchFaceResp.version;
    }

    public int hashCode() {
        return (((((((((((((this.binUrl.hashCode() * 31) + this.hardwareVersion.hashCode()) * 31) + this.name.hashCode()) * 31) + this.preImageUrl.hashCode()) * 31) + Float.floatToIntBits(this.price)) * 31) + this.watchDesc.hashCode()) * 31) + this.typeId) * 31) + this.version;
    }

    public String toString() {
        return "WatchFaceResp(binUrl=" + this.binUrl + ", hardwareVersion=" + this.hardwareVersion + ", name=" + this.name + ", preImageUrl=" + this.preImageUrl + ", price=" + this.price + ", watchDesc=" + this.watchDesc + ", typeId=" + this.typeId + ", version=" + this.version + ')';
    }

    public WatchFaceResp(String binUrl, String hardwareVersion, String name, String preImageUrl, float f, String watchDesc, int i, int i2) {
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(hardwareVersion, "hardwareVersion");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(watchDesc, "watchDesc");
        this.binUrl = binUrl;
        this.hardwareVersion = hardwareVersion;
        this.name = name;
        this.preImageUrl = preImageUrl;
        this.price = f;
        this.watchDesc = watchDesc;
        this.typeId = i;
        this.version = i2;
    }

    public final String getBinUrl() {
        return this.binUrl;
    }

    public final void setBinUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.binUrl = str;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hardwareVersion = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    public final void setPreImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.preImageUrl = str;
    }

    public final float getPrice() {
        return this.price;
    }

    public final void setPrice(float f) {
        this.price = f;
    }

    public final String getWatchDesc() {
        return this.watchDesc;
    }

    public final void setWatchDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.watchDesc = str;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    public final int getVersion() {
        return this.version;
    }

    public final void setVersion(int i) {
        this.version = i;
    }
}
