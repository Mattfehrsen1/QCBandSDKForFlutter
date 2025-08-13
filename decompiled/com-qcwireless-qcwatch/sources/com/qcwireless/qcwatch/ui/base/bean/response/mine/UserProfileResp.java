package com.qcwireless.qcwatch.ui.base.bean.response.mine;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserProfileResp.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b1\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0007¢\u0006\u0002\u0010\u0011J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\u0005HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0005HÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u000bHÆ\u0003J\t\u00108\u001a\u00020\u000bHÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\u0007HÆ\u0003Jw\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u0007HÆ\u0001J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010?\u001a\u00020\u0007HÖ\u0001J\t\u0010@\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u000e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0010\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0013\"\u0004\b%\u0010\u0015R\u001a\u0010\r\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010\u0019R\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001f\"\u0004\b/\u0010!¨\u0006A"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserProfileResp;", "", "uid", "", "nickname", "", "gender", "", "birthday", "portraitUrl", "height", "", "weight", "sbpFrom", "dbpFrom", "sbpTo", "dbpTo", "(JLjava/lang/String;ILjava/lang/String;Ljava/lang/String;FFIIII)V", "getBirthday", "()Ljava/lang/String;", "setBirthday", "(Ljava/lang/String;)V", "getDbpFrom", "()I", "setDbpFrom", "(I)V", "getDbpTo", "setDbpTo", "getGender", "setGender", "getHeight", "()F", "setHeight", "(F)V", "getNickname", "setNickname", "getPortraitUrl", "setPortraitUrl", "getSbpFrom", "setSbpFrom", "getSbpTo", "setSbpTo", "getUid", "()J", "setUid", "(J)V", "getWeight", "setWeight", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class UserProfileResp {
    private String birthday;
    private int dbpFrom;
    private int dbpTo;
    private int gender;
    private float height;
    private String nickname;
    private String portraitUrl;
    private int sbpFrom;
    private int sbpTo;
    private long uid;
    private float weight;

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component10, reason: from getter */
    public final int getSbpTo() {
        return this.sbpTo;
    }

    /* renamed from: component11, reason: from getter */
    public final int getDbpTo() {
        return this.dbpTo;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNickname() {
        return this.nickname;
    }

    /* renamed from: component3, reason: from getter */
    public final int getGender() {
        return this.gender;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBirthday() {
        return this.birthday;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPortraitUrl() {
        return this.portraitUrl;
    }

    /* renamed from: component6, reason: from getter */
    public final float getHeight() {
        return this.height;
    }

    /* renamed from: component7, reason: from getter */
    public final float getWeight() {
        return this.weight;
    }

    /* renamed from: component8, reason: from getter */
    public final int getSbpFrom() {
        return this.sbpFrom;
    }

    /* renamed from: component9, reason: from getter */
    public final int getDbpFrom() {
        return this.dbpFrom;
    }

    public final UserProfileResp copy(long uid, String nickname, int gender, String birthday, String portraitUrl, float height, float weight, int sbpFrom, int dbpFrom, int sbpTo, int dbpTo) {
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(portraitUrl, "portraitUrl");
        return new UserProfileResp(uid, nickname, gender, birthday, portraitUrl, height, weight, sbpFrom, dbpFrom, sbpTo, dbpTo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserProfileResp)) {
            return false;
        }
        UserProfileResp userProfileResp = (UserProfileResp) other;
        return this.uid == userProfileResp.uid && Intrinsics.areEqual(this.nickname, userProfileResp.nickname) && this.gender == userProfileResp.gender && Intrinsics.areEqual(this.birthday, userProfileResp.birthday) && Intrinsics.areEqual(this.portraitUrl, userProfileResp.portraitUrl) && Intrinsics.areEqual((Object) Float.valueOf(this.height), (Object) Float.valueOf(userProfileResp.height)) && Intrinsics.areEqual((Object) Float.valueOf(this.weight), (Object) Float.valueOf(userProfileResp.weight)) && this.sbpFrom == userProfileResp.sbpFrom && this.dbpFrom == userProfileResp.dbpFrom && this.sbpTo == userProfileResp.sbpTo && this.dbpTo == userProfileResp.dbpTo;
    }

    public int hashCode() {
        return (((((((((((((((((((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.nickname.hashCode()) * 31) + this.gender) * 31) + this.birthday.hashCode()) * 31) + this.portraitUrl.hashCode()) * 31) + Float.floatToIntBits(this.height)) * 31) + Float.floatToIntBits(this.weight)) * 31) + this.sbpFrom) * 31) + this.dbpFrom) * 31) + this.sbpTo) * 31) + this.dbpTo;
    }

    public String toString() {
        return "UserProfileResp(uid=" + this.uid + ", nickname=" + this.nickname + ", gender=" + this.gender + ", birthday=" + this.birthday + ", portraitUrl=" + this.portraitUrl + ", height=" + this.height + ", weight=" + this.weight + ", sbpFrom=" + this.sbpFrom + ", dbpFrom=" + this.dbpFrom + ", sbpTo=" + this.sbpTo + ", dbpTo=" + this.dbpTo + ')';
    }

    public UserProfileResp(long j, String nickname, int i, String birthday, String portraitUrl, float f, float f2, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(nickname, "nickname");
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(portraitUrl, "portraitUrl");
        this.uid = j;
        this.nickname = nickname;
        this.gender = i;
        this.birthday = birthday;
        this.portraitUrl = portraitUrl;
        this.height = f;
        this.weight = f2;
        this.sbpFrom = i2;
        this.dbpFrom = i3;
        this.sbpTo = i4;
        this.dbpTo = i5;
    }

    public /* synthetic */ UserProfileResp(long j, String str, int i, String str2, String str3, float f, float f2, int i2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i6 & 2) != 0 ? "" : str, i, (i6 & 8) != 0 ? "" : str2, (i6 & 16) != 0 ? "" : str3, f, f2, i2, i3, i4, i5);
    }

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final void setNickname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nickname = str;
    }

    public final int getGender() {
        return this.gender;
    }

    public final void setGender(int i) {
        this.gender = i;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final void setBirthday(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.birthday = str;
    }

    public final String getPortraitUrl() {
        return this.portraitUrl;
    }

    public final void setPortraitUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.portraitUrl = str;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    public final float getWeight() {
        return this.weight;
    }

    public final void setWeight(float f) {
        this.weight = f;
    }

    public final int getSbpFrom() {
        return this.sbpFrom;
    }

    public final void setSbpFrom(int i) {
        this.sbpFrom = i;
    }

    public final int getDbpFrom() {
        return this.dbpFrom;
    }

    public final void setDbpFrom(int i) {
        this.dbpFrom = i;
    }

    public final int getSbpTo() {
        return this.sbpTo;
    }

    public final void setSbpTo(int i) {
        this.sbpTo = i;
    }

    public final int getDbpTo() {
        return this.dbpTo;
    }

    public final void setDbpTo(int i) {
        this.dbpTo = i;
    }
}
