package com.qcwireless.qcwatch.ui.base.api;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QcNoDataResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/api/QcNoDataResponse;", "", "retCode", "", "message", "", "(ILjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getRetCode", "()I", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class QcNoDataResponse {
    private final String message;
    private final int retCode;

    public static /* synthetic */ QcNoDataResponse copy$default(QcNoDataResponse qcNoDataResponse, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = qcNoDataResponse.retCode;
        }
        if ((i2 & 2) != 0) {
            str = qcNoDataResponse.message;
        }
        return qcNoDataResponse.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getRetCode() {
        return this.retCode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final QcNoDataResponse copy(int retCode, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new QcNoDataResponse(retCode, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QcNoDataResponse)) {
            return false;
        }
        QcNoDataResponse qcNoDataResponse = (QcNoDataResponse) other;
        return this.retCode == qcNoDataResponse.retCode && Intrinsics.areEqual(this.message, qcNoDataResponse.message);
    }

    public int hashCode() {
        return (this.retCode * 31) + this.message.hashCode();
    }

    public String toString() {
        return "QcNoDataResponse(retCode=" + this.retCode + ", message=" + this.message + ')';
    }

    public QcNoDataResponse(int i, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.retCode = i;
        this.message = message;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getRetCode() {
        return this.retCode;
    }
}
