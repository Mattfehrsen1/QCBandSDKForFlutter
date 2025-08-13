package com.qcwireless.qcwatch.ui.device.contact.helper;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ItemEntity.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J3\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/helper/ItemEntity;", "", "text", "", "isChecked", "", "modelType", "", "showOrNot", "(Ljava/lang/String;ZIZ)V", "()Z", "setChecked", "(Z)V", "getModelType", "()I", "setModelType", "(I)V", "getShowOrNot", "setShowOrNot", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ItemEntity {
    private boolean isChecked;
    private int modelType;
    private boolean showOrNot;
    private final String text;

    public static /* synthetic */ ItemEntity copy$default(ItemEntity itemEntity, String str, boolean z, int i, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = itemEntity.text;
        }
        if ((i2 & 2) != 0) {
            z = itemEntity.isChecked;
        }
        if ((i2 & 4) != 0) {
            i = itemEntity.modelType;
        }
        if ((i2 & 8) != 0) {
            z2 = itemEntity.showOrNot;
        }
        return itemEntity.copy(str, z, i, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsChecked() {
        return this.isChecked;
    }

    /* renamed from: component3, reason: from getter */
    public final int getModelType() {
        return this.modelType;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getShowOrNot() {
        return this.showOrNot;
    }

    public final ItemEntity copy(String text, boolean isChecked, int modelType, boolean showOrNot) {
        return new ItemEntity(text, isChecked, modelType, showOrNot);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ItemEntity)) {
            return false;
        }
        ItemEntity itemEntity = (ItemEntity) other;
        return Intrinsics.areEqual(this.text, itemEntity.text) && this.isChecked == itemEntity.isChecked && this.modelType == itemEntity.modelType && this.showOrNot == itemEntity.showOrNot;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.text;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        boolean z = this.isChecked;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (((iHashCode + i) * 31) + this.modelType) * 31;
        boolean z2 = this.showOrNot;
        return i2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "ItemEntity(text=" + this.text + ", isChecked=" + this.isChecked + ", modelType=" + this.modelType + ", showOrNot=" + this.showOrNot + ')';
    }

    public ItemEntity(String str, boolean z, int i, boolean z2) {
        this.text = str;
        this.isChecked = z;
        this.modelType = i;
        this.showOrNot = z2;
    }

    public /* synthetic */ ItemEntity(String str, boolean z, int i, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, i, (i2 & 8) != 0 ? true : z2);
    }

    public final String getText() {
        return this.text;
    }

    public final boolean isChecked() {
        return this.isChecked;
    }

    public final void setChecked(boolean z) {
        this.isChecked = z;
    }

    public final int getModelType() {
        return this.modelType;
    }

    public final void setModelType(int i) {
        this.modelType = i;
    }

    public final boolean getShowOrNot() {
        return this.showOrNot;
    }

    public final void setShowOrNot(boolean z) {
        this.showOrNot = z;
    }
}
