package com.qcwireless.qcwatch.ui.device.more.ecard.bean;

import android.graphics.drawable.Drawable;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SoftwareCard.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareCard;", "", "type", "", "name", "", "icon", "Landroid/graphics/drawable/Drawable;", "(ILjava/lang/String;Landroid/graphics/drawable/Drawable;)V", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "getName", "()Ljava/lang/String;", "getType", "()I", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SoftwareCard {
    private Drawable icon;
    private final String name;
    private final int type;

    public static /* synthetic */ SoftwareCard copy$default(SoftwareCard softwareCard, int i, String str, Drawable drawable, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = softwareCard.type;
        }
        if ((i2 & 2) != 0) {
            str = softwareCard.name;
        }
        if ((i2 & 4) != 0) {
            drawable = softwareCard.icon;
        }
        return softwareCard.copy(i, str, drawable);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final Drawable getIcon() {
        return this.icon;
    }

    public final SoftwareCard copy(int type, String name, Drawable icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        return new SoftwareCard(type, name, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwareCard)) {
            return false;
        }
        SoftwareCard softwareCard = (SoftwareCard) other;
        return this.type == softwareCard.type && Intrinsics.areEqual(this.name, softwareCard.name) && Intrinsics.areEqual(this.icon, softwareCard.icon);
    }

    public int hashCode() {
        return (((this.type * 31) + this.name.hashCode()) * 31) + this.icon.hashCode();
    }

    public String toString() {
        return "SoftwareCard(type=" + this.type + ", name=" + this.name + ", icon=" + this.icon + ')';
    }

    public SoftwareCard(int i, String name, Drawable icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.type = i;
        this.name = name;
        this.icon = icon;
    }

    public final int getType() {
        return this.type;
    }

    public final String getName() {
        return this.name;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final void setIcon(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "<set-?>");
        this.icon = drawable;
    }
}
