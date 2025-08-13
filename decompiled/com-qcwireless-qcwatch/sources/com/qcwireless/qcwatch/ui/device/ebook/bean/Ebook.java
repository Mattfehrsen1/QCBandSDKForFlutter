package com.qcwireless.qcwatch.ui.device.ebook.bean;

import com.google.android.gms.fitness.FitnessActivities;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Ebook.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0018\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/bean/Ebook;", "", "()V", "editEbook", "", "getEditEbook", "()Z", "setEditEbook", "(Z)V", "firstName", "", "getFirstName", "()Ljava/lang/String;", "setFirstName", "(Ljava/lang/String;)V", "name", "getName", "setName", "path", "getPath", "setPath", "select", "getSelect", "setSelect", "equals", FitnessActivities.OTHER, "hashCode", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Ebook {
    private boolean editEbook;
    private boolean select;
    private String name = "";
    private String path = "";
    private String firstName = "";

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.path = str;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.firstName = str;
    }

    public final boolean getEditEbook() {
        return this.editEbook;
    }

    public final void setEditEbook(boolean z) {
        this.editEbook = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Objects.requireNonNull(other, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.device.ebook.bean.Ebook");
        Ebook ebook = (Ebook) other;
        return Intrinsics.areEqual(this.name, ebook.name) && Intrinsics.areEqual(this.path, ebook.path);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.path.hashCode();
    }
}
