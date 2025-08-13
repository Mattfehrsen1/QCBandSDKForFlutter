package com.liulishuo.okdownload.kotlin;

import com.google.android.gms.fitness.FitnessActivities;
import com.liulishuo.okdownload.core.cause.EndCause;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/liulishuo/okdownload/kotlin/DownloadResult;", "", "cause", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "(Lcom/liulishuo/okdownload/core/cause/EndCause;)V", "getCause", "()Lcom/liulishuo/okdownload/core/cause/EndCause;", "becauseOfCompleted", "", "becauseOfRepeatedTask", "component1", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "kotlin_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final /* data */ class DownloadResult {
    private final EndCause cause;

    public static /* synthetic */ DownloadResult copy$default(DownloadResult downloadResult, EndCause endCause, int i, Object obj) {
        if ((i & 1) != 0) {
            endCause = downloadResult.cause;
        }
        return downloadResult.copy(endCause);
    }

    /* renamed from: component1, reason: from getter */
    public final EndCause getCause() {
        return this.cause;
    }

    public final DownloadResult copy(EndCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        return new DownloadResult(cause);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof DownloadResult) && Intrinsics.areEqual(this.cause, ((DownloadResult) other).cause);
        }
        return true;
    }

    public int hashCode() {
        EndCause endCause = this.cause;
        if (endCause != null) {
            return endCause.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "DownloadResult(cause=" + this.cause + ")";
    }

    public DownloadResult(EndCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        this.cause = cause;
    }

    public final EndCause getCause() {
        return this.cause;
    }

    public final boolean becauseOfCompleted() {
        return this.cause == EndCause.COMPLETED;
    }

    public final boolean becauseOfRepeatedTask() {
        return this.cause == EndCause.SAME_TASK_BUSY || this.cause == EndCause.FILE_BUSY;
    }
}
