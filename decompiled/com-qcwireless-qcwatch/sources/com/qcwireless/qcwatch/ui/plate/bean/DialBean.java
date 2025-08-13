package com.qcwireless.qcwatch.ui.plate.bean;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DialBean.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\bHÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u000bHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u000bHÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011¨\u00062"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/bean/DialBean;", "", "name", "", "preImageUrl", "localImageUrl", "binUrl", "price", "", "strPrice", NotificationCompat.CATEGORY_STATUS, "", "nameDesc", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/String;ILjava/lang/String;)V", "getBinUrl", "()Ljava/lang/String;", "setBinUrl", "(Ljava/lang/String;)V", "getLocalImageUrl", "setLocalImageUrl", "getName", "setName", "getNameDesc", "setNameDesc", "getPreImageUrl", "setPreImageUrl", "getPrice", "()F", "setPrice", "(F)V", "getStatus", "()I", "setStatus", "(I)V", "getStrPrice", "setStrPrice", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DialBean {
    private String binUrl;
    private String localImageUrl;
    private String name;
    private String nameDesc;
    private String preImageUrl;
    private float price;
    private int status;
    private String strPrice;

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPreImageUrl() {
        return this.preImageUrl;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLocalImageUrl() {
        return this.localImageUrl;
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
    public final String getStrPrice() {
        return this.strPrice;
    }

    /* renamed from: component7, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* renamed from: component8, reason: from getter */
    public final String getNameDesc() {
        return this.nameDesc;
    }

    public final DialBean copy(String name, String preImageUrl, String localImageUrl, String binUrl, float price, String strPrice, int status, String nameDesc) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(strPrice, "strPrice");
        Intrinsics.checkNotNullParameter(nameDesc, "nameDesc");
        return new DialBean(name, preImageUrl, localImageUrl, binUrl, price, strPrice, status, nameDesc);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DialBean)) {
            return false;
        }
        DialBean dialBean = (DialBean) other;
        return Intrinsics.areEqual(this.name, dialBean.name) && Intrinsics.areEqual(this.preImageUrl, dialBean.preImageUrl) && Intrinsics.areEqual(this.localImageUrl, dialBean.localImageUrl) && Intrinsics.areEqual(this.binUrl, dialBean.binUrl) && Intrinsics.areEqual((Object) Float.valueOf(this.price), (Object) Float.valueOf(dialBean.price)) && Intrinsics.areEqual(this.strPrice, dialBean.strPrice) && this.status == dialBean.status && Intrinsics.areEqual(this.nameDesc, dialBean.nameDesc);
    }

    public int hashCode() {
        return (((((((((((((this.name.hashCode() * 31) + this.preImageUrl.hashCode()) * 31) + this.localImageUrl.hashCode()) * 31) + this.binUrl.hashCode()) * 31) + Float.floatToIntBits(this.price)) * 31) + this.strPrice.hashCode()) * 31) + this.status) * 31) + this.nameDesc.hashCode();
    }

    public String toString() {
        return "DialBean(name=" + this.name + ", preImageUrl=" + this.preImageUrl + ", localImageUrl=" + this.localImageUrl + ", binUrl=" + this.binUrl + ", price=" + this.price + ", strPrice=" + this.strPrice + ", status=" + this.status + ", nameDesc=" + this.nameDesc + ')';
    }

    public DialBean(String name, String preImageUrl, String localImageUrl, String binUrl, float f, String strPrice, int i, String nameDesc) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(preImageUrl, "preImageUrl");
        Intrinsics.checkNotNullParameter(localImageUrl, "localImageUrl");
        Intrinsics.checkNotNullParameter(binUrl, "binUrl");
        Intrinsics.checkNotNullParameter(strPrice, "strPrice");
        Intrinsics.checkNotNullParameter(nameDesc, "nameDesc");
        this.name = name;
        this.preImageUrl = preImageUrl;
        this.localImageUrl = localImageUrl;
        this.binUrl = binUrl;
        this.price = f;
        this.strPrice = strPrice;
        this.status = i;
        this.nameDesc = nameDesc;
    }

    public /* synthetic */ DialBean(String str, String str2, String str3, String str4, float f, String str5, int i, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, f, str5, i, (i2 & 128) != 0 ? "" : str6);
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

    public final String getLocalImageUrl() {
        return this.localImageUrl;
    }

    public final void setLocalImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localImageUrl = str;
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

    public final String getStrPrice() {
        return this.strPrice;
    }

    public final void setStrPrice(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.strPrice = str;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    public final String getNameDesc() {
        return this.nameDesc;
    }

    public final void setNameDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nameDesc = str;
    }
}
