package com.qcwireless.qcwatch.ui.base.repository.watchface;

import com.androidnetworking.common.ANConstants;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListenerBean.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/watchface/ListenerBean;", "", ANConstants.SUCCESS, "", "name", "", "progress", "", "(ZLjava/lang/String;I)V", "getName", "()Ljava/lang/String;", "getProgress", "()I", "getSuccess", "()Z", "component1", "component2", "component3", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ListenerBean {
    private final String name;
    private final int progress;
    private final boolean success;

    public static /* synthetic */ ListenerBean copy$default(ListenerBean listenerBean, boolean z, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = listenerBean.success;
        }
        if ((i2 & 2) != 0) {
            str = listenerBean.name;
        }
        if ((i2 & 4) != 0) {
            i = listenerBean.progress;
        }
        return listenerBean.copy(z, str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final int getProgress() {
        return this.progress;
    }

    public final ListenerBean copy(boolean success, String name, int progress) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ListenerBean(success, name, progress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListenerBean)) {
            return false;
        }
        ListenerBean listenerBean = (ListenerBean) other;
        return this.success == listenerBean.success && Intrinsics.areEqual(this.name, listenerBean.name) && this.progress == listenerBean.progress;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.success;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((r0 * 31) + this.name.hashCode()) * 31) + this.progress;
    }

    public String toString() {
        return "ListenerBean(success=" + this.success + ", name=" + this.name + ", progress=" + this.progress + ')';
    }

    public ListenerBean(boolean z, String name, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.success = z;
        this.name = name;
        this.progress = i;
    }

    public final String getName() {
        return this.name;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final boolean getSuccess() {
        return this.success;
    }
}
