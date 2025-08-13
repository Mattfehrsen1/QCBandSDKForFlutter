package com.qcwireless.qcwatch.ui.base.bean.request.user;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindPwdRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/user/FindPwdRequest;", "", "email", "", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class FindPwdRequest {
    private final String email;

    public static /* synthetic */ FindPwdRequest copy$default(FindPwdRequest findPwdRequest, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = findPwdRequest.email;
        }
        return findPwdRequest.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    public final FindPwdRequest copy(String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        return new FindPwdRequest(email);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FindPwdRequest) && Intrinsics.areEqual(this.email, ((FindPwdRequest) other).email);
    }

    public int hashCode() {
        return this.email.hashCode();
    }

    public String toString() {
        return "FindPwdRequest(email=" + this.email + ')';
    }

    public FindPwdRequest(String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        this.email = email;
    }

    public final String getEmail() {
        return this.email;
    }
}
