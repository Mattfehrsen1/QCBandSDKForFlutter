package com.qcwireless.qcwatch.ui.device.push.bean;

import android.graphics.drawable.Drawable;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SoftwarePush.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/bean/SoftwarePush;", "", "name", "", "packageName", "switch", "", "icon", "Landroid/graphics/drawable/Drawable;", "(Ljava/lang/String;Ljava/lang/String;ZLandroid/graphics/drawable/Drawable;)V", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "getName", "()Ljava/lang/String;", "getPackageName", "getSwitch", "()Z", "setSwitch", "(Z)V", "component1", "component2", "component3", "component4", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SoftwarePush {
    private Drawable icon;
    private final String name;
    private final String packageName;
    private boolean switch;

    public static /* synthetic */ SoftwarePush copy$default(SoftwarePush softwarePush, String str, String str2, boolean z, Drawable drawable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = softwarePush.name;
        }
        if ((i & 2) != 0) {
            str2 = softwarePush.packageName;
        }
        if ((i & 4) != 0) {
            z = softwarePush.switch;
        }
        if ((i & 8) != 0) {
            drawable = softwarePush.icon;
        }
        return softwarePush.copy(str, str2, z, drawable);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getSwitch() {
        return this.switch;
    }

    /* renamed from: component4, reason: from getter */
    public final Drawable getIcon() {
        return this.icon;
    }

    public final SoftwarePush copy(String name, String packageName, boolean z, Drawable icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(icon, "icon");
        return new SoftwarePush(name, packageName, z, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SoftwarePush)) {
            return false;
        }
        SoftwarePush softwarePush = (SoftwarePush) other;
        return Intrinsics.areEqual(this.name, softwarePush.name) && Intrinsics.areEqual(this.packageName, softwarePush.packageName) && this.switch == softwarePush.switch && Intrinsics.areEqual(this.icon, softwarePush.icon);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.packageName.hashCode()) * 31;
        boolean z = this.switch;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + this.icon.hashCode();
    }

    public String toString() {
        return "SoftwarePush(name=" + this.name + ", packageName=" + this.packageName + ", switch=" + this.switch + ", icon=" + this.icon + ')';
    }

    public SoftwarePush(String name, String packageName, boolean z, Drawable icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.name = name;
        this.packageName = packageName;
        this.switch = z;
        this.icon = icon;
    }

    public /* synthetic */ SoftwarePush(String str, String str2, boolean z, Drawable drawable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z, drawable);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean getSwitch() {
        return this.switch;
    }

    public final void setSwitch(boolean z) {
        this.switch = z;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final void setIcon(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "<set-?>");
        this.icon = drawable;
    }
}
