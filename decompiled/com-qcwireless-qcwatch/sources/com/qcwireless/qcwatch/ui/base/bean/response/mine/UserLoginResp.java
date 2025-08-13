package com.qcwireless.qcwatch.ui.base.bean.response.mine;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserLoginResp.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;", "", "retCode", "", "uid", "", "token", "", "registerDate", "(IJLjava/lang/String;Ljava/lang/String;)V", "getRegisterDate", "()Ljava/lang/String;", "getRetCode", "()I", "getToken", "getUid", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class UserLoginResp {
    private final String registerDate;
    private final int retCode;
    private final String token;
    private final long uid;

    public static /* synthetic */ UserLoginResp copy$default(UserLoginResp userLoginResp, int i, long j, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = userLoginResp.retCode;
        }
        if ((i2 & 2) != 0) {
            j = userLoginResp.uid;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            str = userLoginResp.token;
        }
        String str3 = str;
        if ((i2 & 8) != 0) {
            str2 = userLoginResp.registerDate;
        }
        return userLoginResp.copy(i, j2, str3, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getRetCode() {
        return this.retCode;
    }

    /* renamed from: component2, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRegisterDate() {
        return this.registerDate;
    }

    public final UserLoginResp copy(int retCode, long uid, String token, String registerDate) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        return new UserLoginResp(retCode, uid, token, registerDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserLoginResp)) {
            return false;
        }
        UserLoginResp userLoginResp = (UserLoginResp) other;
        return this.retCode == userLoginResp.retCode && this.uid == userLoginResp.uid && Intrinsics.areEqual(this.token, userLoginResp.token) && Intrinsics.areEqual(this.registerDate, userLoginResp.registerDate);
    }

    public int hashCode() {
        return (((((this.retCode * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.uid)) * 31) + this.token.hashCode()) * 31) + this.registerDate.hashCode();
    }

    public String toString() {
        return "UserLoginResp(retCode=" + this.retCode + ", uid=" + this.uid + ", token=" + this.token + ", registerDate=" + this.registerDate + ')';
    }

    public UserLoginResp(int i, long j, String token, String registerDate) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        this.retCode = i;
        this.uid = j;
        this.token = token;
        this.registerDate = registerDate;
    }

    public final int getRetCode() {
        return this.retCode;
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getRegisterDate() {
        return this.registerDate;
    }
}
