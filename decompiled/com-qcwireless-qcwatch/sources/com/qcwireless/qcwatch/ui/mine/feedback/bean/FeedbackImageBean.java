package com.qcwireless.qcwatch.ui.mine.feedback.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeedbackImageBean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/feedback/bean/FeedbackImageBean;", "", "path", "", "add", "", "(Ljava/lang/String;Z)V", "getAdd", "()Z", "getPath", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class FeedbackImageBean {
    private final boolean add;
    private final String path;

    public static /* synthetic */ FeedbackImageBean copy$default(FeedbackImageBean feedbackImageBean, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = feedbackImageBean.path;
        }
        if ((i & 2) != 0) {
            z = feedbackImageBean.add;
        }
        return feedbackImageBean.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getAdd() {
        return this.add;
    }

    public final FeedbackImageBean copy(String path, boolean add) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new FeedbackImageBean(path, add);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeedbackImageBean)) {
            return false;
        }
        FeedbackImageBean feedbackImageBean = (FeedbackImageBean) other;
        return Intrinsics.areEqual(this.path, feedbackImageBean.path) && this.add == feedbackImageBean.add;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = this.path.hashCode() * 31;
        boolean z = this.add;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "FeedbackImageBean(path=" + this.path + ", add=" + this.add + ')';
    }

    public FeedbackImageBean(String path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.path = path;
        this.add = z;
    }

    public final boolean getAdd() {
        return this.add;
    }

    public final String getPath() {
        return this.path;
    }
}
