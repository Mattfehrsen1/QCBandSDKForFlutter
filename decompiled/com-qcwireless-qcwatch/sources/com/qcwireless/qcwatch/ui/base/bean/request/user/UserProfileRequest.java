package com.qcwireless.qcwatch.ui.base.bean.request.user;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserProfileRequest.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001J\t\u0010 \u001a\u00020\tHÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/user/UserProfileRequest;", "", "uid", "", "sex", "", "weight", "height", "birthday", "", "nickname", "(JIIILjava/lang/String;Ljava/lang/String;)V", "getBirthday", "()Ljava/lang/String;", "getHeight", "()I", "getNickname", "getSex", "getUid", "()J", "getWeight", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class UserProfileRequest {
    private final String birthday;
    private final int height;
    private final String nickname;
    private final int sex;
    private final long uid;
    private final int weight;

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSex() {
        return this.sex;
    }

    /* renamed from: component3, reason: from getter */
    public final int getWeight() {
        return this.weight;
    }

    /* renamed from: component4, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: component5, reason: from getter */
    public final String getBirthday() {
        return this.birthday;
    }

    /* renamed from: component6, reason: from getter */
    public final String getNickname() {
        return this.nickname;
    }

    public final UserProfileRequest copy(long uid, int sex, int weight, int height, String birthday, String nickname) {
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        return new UserProfileRequest(uid, sex, weight, height, birthday, nickname);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserProfileRequest)) {
            return false;
        }
        UserProfileRequest userProfileRequest = (UserProfileRequest) other;
        return this.uid == userProfileRequest.uid && this.sex == userProfileRequest.sex && this.weight == userProfileRequest.weight && this.height == userProfileRequest.height && Intrinsics.areEqual(this.birthday, userProfileRequest.birthday) && Intrinsics.areEqual(this.nickname, userProfileRequest.nickname);
    }

    public int hashCode() {
        return (((((((((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.sex) * 31) + this.weight) * 31) + this.height) * 31) + this.birthday.hashCode()) * 31) + this.nickname.hashCode();
    }

    public String toString() {
        return "UserProfileRequest(uid=" + this.uid + ", sex=" + this.sex + ", weight=" + this.weight + ", height=" + this.height + ", birthday=" + this.birthday + ", nickname=" + this.nickname + ')';
    }

    public UserProfileRequest(long j, int i, int i2, int i3, String birthday, String nickname) {
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        this.uid = j;
        this.sex = i;
        this.weight = i2;
        this.height = i3;
        this.birthday = birthday;
        this.nickname = nickname;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final int getSex() {
        return this.sex;
    }

    public final long getUid() {
        return this.uid;
    }

    public final int getWeight() {
        return this.weight;
    }
}
