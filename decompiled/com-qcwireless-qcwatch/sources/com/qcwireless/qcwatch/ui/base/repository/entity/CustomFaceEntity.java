package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomFaceEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003JO\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0006HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001e\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001e\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019¨\u0006+"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;", "", "address", "", "hdVersion", "type", "", "x", "y", "imageUrl", "localUrl", "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getHdVersion", "setHdVersion", "getImageUrl", "setImageUrl", "getLocalUrl", "setLocalUrl", "getType", "()I", "setType", "(I)V", "getX", "setX", "getY", "setY", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class CustomFaceEntity {
    private String address;
    private String hdVersion;
    private String imageUrl;
    private String localUrl;
    private int type;
    private int x;
    private int y;

    public static /* synthetic */ CustomFaceEntity copy$default(CustomFaceEntity customFaceEntity, String str, String str2, int i, int i2, int i3, String str3, String str4, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = customFaceEntity.address;
        }
        if ((i4 & 2) != 0) {
            str2 = customFaceEntity.hdVersion;
        }
        String str5 = str2;
        if ((i4 & 4) != 0) {
            i = customFaceEntity.type;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = customFaceEntity.x;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = customFaceEntity.y;
        }
        int i7 = i3;
        if ((i4 & 32) != 0) {
            str3 = customFaceEntity.imageUrl;
        }
        String str6 = str3;
        if ((i4 & 64) != 0) {
            str4 = customFaceEntity.localUrl;
        }
        return customFaceEntity.copy(str, str5, i5, i6, i7, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component2, reason: from getter */
    public final String getHdVersion() {
        return this.hdVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final int getX() {
        return this.x;
    }

    /* renamed from: component5, reason: from getter */
    public final int getY() {
        return this.y;
    }

    /* renamed from: component6, reason: from getter */
    public final String getImageUrl() {
        return this.imageUrl;
    }

    /* renamed from: component7, reason: from getter */
    public final String getLocalUrl() {
        return this.localUrl;
    }

    public final CustomFaceEntity copy(String address, String hdVersion, int type, int x, int y, String imageUrl, String localUrl) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(hdVersion, "hdVersion");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(localUrl, "localUrl");
        return new CustomFaceEntity(address, hdVersion, type, x, y, imageUrl, localUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomFaceEntity)) {
            return false;
        }
        CustomFaceEntity customFaceEntity = (CustomFaceEntity) other;
        return Intrinsics.areEqual(this.address, customFaceEntity.address) && Intrinsics.areEqual(this.hdVersion, customFaceEntity.hdVersion) && this.type == customFaceEntity.type && this.x == customFaceEntity.x && this.y == customFaceEntity.y && Intrinsics.areEqual(this.imageUrl, customFaceEntity.imageUrl) && Intrinsics.areEqual(this.localUrl, customFaceEntity.localUrl);
    }

    public int hashCode() {
        return (((((((((((this.address.hashCode() * 31) + this.hdVersion.hashCode()) * 31) + this.type) * 31) + this.x) * 31) + this.y) * 31) + this.imageUrl.hashCode()) * 31) + this.localUrl.hashCode();
    }

    public String toString() {
        return "CustomFaceEntity(address=" + this.address + ", hdVersion=" + this.hdVersion + ", type=" + this.type + ", x=" + this.x + ", y=" + this.y + ", imageUrl=" + this.imageUrl + ", localUrl=" + this.localUrl + ')';
    }

    public CustomFaceEntity(String address, String hdVersion, int i, int i2, int i3, String imageUrl, String localUrl) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(hdVersion, "hdVersion");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(localUrl, "localUrl");
        this.address = address;
        this.hdVersion = hdVersion;
        this.type = i;
        this.x = i2;
        this.y = i3;
        this.imageUrl = imageUrl;
        this.localUrl = localUrl;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.address = str;
    }

    public final String getHdVersion() {
        return this.hdVersion;
    }

    public final void setHdVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hdVersion = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final int getX() {
        return this.x;
    }

    public final void setX(int i) {
        this.x = i;
    }

    public final int getY() {
        return this.y;
    }

    public final void setY(int i) {
        this.y = i;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.imageUrl = str;
    }

    public final String getLocalUrl() {
        return this.localUrl;
    }

    public final void setLocalUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localUrl = str;
    }
}
