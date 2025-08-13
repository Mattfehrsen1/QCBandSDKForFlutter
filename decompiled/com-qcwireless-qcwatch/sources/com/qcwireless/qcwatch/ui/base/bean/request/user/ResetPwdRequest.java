package com.qcwireless.qcwatch.ui.base.bean.request.user;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ResetPwdRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/user/ResetPwdRequest;", "", "code", "", "email", "password", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getEmail", "getPassword", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ResetPwdRequest {
    private final String code;
    private final String email;
    private final String password;

    public static /* synthetic */ ResetPwdRequest copy$default(ResetPwdRequest resetPwdRequest, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = resetPwdRequest.code;
        }
        if ((i & 2) != 0) {
            str2 = resetPwdRequest.email;
        }
        if ((i & 4) != 0) {
            str3 = resetPwdRequest.password;
        }
        return resetPwdRequest.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    public final ResetPwdRequest copy(String code, String email, String password) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        return new ResetPwdRequest(code, email, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResetPwdRequest)) {
            return false;
        }
        ResetPwdRequest resetPwdRequest = (ResetPwdRequest) other;
        return Intrinsics.areEqual(this.code, resetPwdRequest.code) && Intrinsics.areEqual(this.email, resetPwdRequest.email) && Intrinsics.areEqual(this.password, resetPwdRequest.password);
    }

    public int hashCode() {
        return (((this.code.hashCode() * 31) + this.email.hashCode()) * 31) + this.password.hashCode();
    }

    public String toString() {
        return "ResetPwdRequest(code=" + this.code + ", email=" + this.email + ", password=" + this.password + ')';
    }

    public ResetPwdRequest(String code, String email, String password) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        this.code = code;
        this.email = email;
        this.password = password;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getPassword() {
        return this.password;
    }
}
