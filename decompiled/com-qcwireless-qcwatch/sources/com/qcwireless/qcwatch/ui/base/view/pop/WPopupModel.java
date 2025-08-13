package com.qcwireless.qcwatch.ui.base.view.pop;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WPopupModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupModel;", "", "text", "", "imgRes", "", "(Ljava/lang/String;I)V", "getImgRes", "()I", "setImgRes", "(I)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WPopupModel {
    private int imgRes;
    private String text;

    public static /* synthetic */ WPopupModel copy$default(WPopupModel wPopupModel, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = wPopupModel.text;
        }
        if ((i2 & 2) != 0) {
            i = wPopupModel.imgRes;
        }
        return wPopupModel.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component2, reason: from getter */
    public final int getImgRes() {
        return this.imgRes;
    }

    public final WPopupModel copy(String text, int imgRes) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new WPopupModel(text, imgRes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WPopupModel)) {
            return false;
        }
        WPopupModel wPopupModel = (WPopupModel) other;
        return Intrinsics.areEqual(this.text, wPopupModel.text) && this.imgRes == wPopupModel.imgRes;
    }

    public int hashCode() {
        return (this.text.hashCode() * 31) + this.imgRes;
    }

    public String toString() {
        return "WPopupModel(text=" + this.text + ", imgRes=" + this.imgRes + ')';
    }

    public WPopupModel(String text, int i) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.imgRes = i;
    }

    public /* synthetic */ WPopupModel(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? -1 : i);
    }

    public final int getImgRes() {
        return this.imgRes;
    }

    public final String getText() {
        return this.text;
    }

    public final void setImgRes(int i) {
        this.imgRes = i;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }
}
