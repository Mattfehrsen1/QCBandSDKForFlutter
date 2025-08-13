package com.qcwireless.qcwatch.ui.base.bean.request.user;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/user/LoginRequest;", "", "account", "", "password", "type", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getAccount", "()Ljava/lang/String;", "setAccount", "(Ljava/lang/String;)V", "getPassword", "setPassword", "getType", "()I", "setType", "(I)V", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class LoginRequest {
    private String account;
    private String password;
    private int type;

    public static /* synthetic */ LoginRequest copy$default(LoginRequest loginRequest, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = loginRequest.account;
        }
        if ((i2 & 2) != 0) {
            str2 = loginRequest.password;
        }
        if ((i2 & 4) != 0) {
            i = loginRequest.type;
        }
        return loginRequest.copy(str, str2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAccount() {
        return this.account;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* renamed from: component3, reason: from getter */
    public final int getType() {
        return this.type;
    }

    public final LoginRequest copy(String account, String password, int type) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        return new LoginRequest(account, password, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginRequest)) {
            return false;
        }
        LoginRequest loginRequest = (LoginRequest) other;
        return Intrinsics.areEqual(this.account, loginRequest.account) && Intrinsics.areEqual(this.password, loginRequest.password) && this.type == loginRequest.type;
    }

    public int hashCode() {
        return (((this.account.hashCode() * 31) + this.password.hashCode()) * 31) + this.type;
    }

    public String toString() {
        return "LoginRequest(account=" + this.account + ", password=" + this.password + ", type=" + this.type + ')';
    }

    public LoginRequest(String account, String password, int i) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        this.account = account;
        this.password = password;
        this.type = i;
    }

    public final String getAccount() {
        return this.account;
    }

    public final void setAccount(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.account = str;
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.password = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
