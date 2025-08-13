package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserEntity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\bJ\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\n\u0012\u0006\u0010\u0012\u001a\u00020\n\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\u0014\u001a\u00020\n\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\b¢\u0006\u0002\u0010\u0017J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\bHÆ\u0003J\t\u0010E\u001a\u00020\nHÆ\u0003J\t\u0010F\u001a\u00020\nHÆ\u0003J\t\u0010G\u001a\u00020\nHÆ\u0003J\t\u0010H\u001a\u00020\nHÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\bHÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\bHÆ\u0003J\t\u0010N\u001a\u00020\nHÆ\u0003J\t\u0010O\u001a\u00020\bHÆ\u0003J\t\u0010P\u001a\u00020\nHÆ\u0003J\t\u0010Q\u001a\u00020\u0005HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J³\u0001\u0010S\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\bHÆ\u0001J\u0013\u0010T\u001a\u00020U2\b\u0010V\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010W\u001a\u00020\bHÖ\u0001J\t\u0010X\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\r\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001e\u0010\u0011\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010\u0012\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010%\"\u0004\b)\u0010'R\u001e\u0010\u0014\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010%\"\u0004\b+\u0010'R\u001e\u0010\u0013\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010%\"\u0004\b-\u0010'R\u001e\u0010\u0010\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R\u001e\u0010\f\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010%\"\u0004\b1\u0010'R\u001e\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0019\"\u0004\b3\u0010\u001bR\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0019\"\u0004\b5\u0010\u001bR\u001e\u0010\u0015\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0019\"\u0004\b7\u0010\u001bR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010\u0016\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010!\"\u0004\b=\u0010#R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010%\"\u0004\b?\u0010'R\u001e\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010!\"\u0004\bA\u0010#¨\u0006Y"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "", "title", "", "email", "", "nickName", "gender", "", "weight", "", "weightLbs", "height", "birthday", "avatarUrl", "localAvatarUrl", "goalSteps", "goalCalorie", "goalDistance", "goalSportTime", "goalSleepTime", "registerDate", "update", "(JLjava/lang/String;Ljava/lang/String;IFIFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IFFFFLjava/lang/String;I)V", "getAvatarUrl", "()Ljava/lang/String;", "setAvatarUrl", "(Ljava/lang/String;)V", "getBirthday", "setBirthday", "getEmail", "setEmail", "getGender", "()I", "setGender", "(I)V", "getGoalCalorie", "()F", "setGoalCalorie", "(F)V", "getGoalDistance", "setGoalDistance", "getGoalSleepTime", "setGoalSleepTime", "getGoalSportTime", "setGoalSportTime", "getGoalSteps", "setGoalSteps", "getHeight", "setHeight", "getLocalAvatarUrl", "setLocalAvatarUrl", "getNickName", "setNickName", "getRegisterDate", "setRegisterDate", "getTitle", "()J", "setTitle", "(J)V", "getUpdate", "setUpdate", "getWeight", "setWeight", "getWeightLbs", "setWeightLbs", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class UserEntity {
    private String avatarUrl;
    private String birthday;
    private String email;
    private int gender;
    private float goalCalorie;
    private float goalDistance;
    private float goalSleepTime;
    private float goalSportTime;
    private int goalSteps;
    private float height;
    private String localAvatarUrl;
    private String nickName;
    private String registerDate;
    private long title;
    private int update;
    private float weight;
    private int weightLbs;

    /* renamed from: component1, reason: from getter */
    public final long getTitle() {
        return this.title;
    }

    /* renamed from: component10, reason: from getter */
    public final String getLocalAvatarUrl() {
        return this.localAvatarUrl;
    }

    /* renamed from: component11, reason: from getter */
    public final int getGoalSteps() {
        return this.goalSteps;
    }

    /* renamed from: component12, reason: from getter */
    public final float getGoalCalorie() {
        return this.goalCalorie;
    }

    /* renamed from: component13, reason: from getter */
    public final float getGoalDistance() {
        return this.goalDistance;
    }

    /* renamed from: component14, reason: from getter */
    public final float getGoalSportTime() {
        return this.goalSportTime;
    }

    /* renamed from: component15, reason: from getter */
    public final float getGoalSleepTime() {
        return this.goalSleepTime;
    }

    /* renamed from: component16, reason: from getter */
    public final String getRegisterDate() {
        return this.registerDate;
    }

    /* renamed from: component17, reason: from getter */
    public final int getUpdate() {
        return this.update;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component3, reason: from getter */
    public final String getNickName() {
        return this.nickName;
    }

    /* renamed from: component4, reason: from getter */
    public final int getGender() {
        return this.gender;
    }

    /* renamed from: component5, reason: from getter */
    public final float getWeight() {
        return this.weight;
    }

    /* renamed from: component6, reason: from getter */
    public final int getWeightLbs() {
        return this.weightLbs;
    }

    /* renamed from: component7, reason: from getter */
    public final float getHeight() {
        return this.height;
    }

    /* renamed from: component8, reason: from getter */
    public final String getBirthday() {
        return this.birthday;
    }

    /* renamed from: component9, reason: from getter */
    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final UserEntity copy(long title, String email, String nickName, int gender, float weight, int weightLbs, float height, String birthday, String avatarUrl, String localAvatarUrl, int goalSteps, float goalCalorie, float goalDistance, float goalSportTime, float goalSleepTime, String registerDate, int update) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(nickName, "nickName");
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(avatarUrl, "avatarUrl");
        Intrinsics.checkNotNullParameter(localAvatarUrl, "localAvatarUrl");
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        return new UserEntity(title, email, nickName, gender, weight, weightLbs, height, birthday, avatarUrl, localAvatarUrl, goalSteps, goalCalorie, goalDistance, goalSportTime, goalSleepTime, registerDate, update);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserEntity)) {
            return false;
        }
        UserEntity userEntity = (UserEntity) other;
        return this.title == userEntity.title && Intrinsics.areEqual(this.email, userEntity.email) && Intrinsics.areEqual(this.nickName, userEntity.nickName) && this.gender == userEntity.gender && Intrinsics.areEqual((Object) Float.valueOf(this.weight), (Object) Float.valueOf(userEntity.weight)) && this.weightLbs == userEntity.weightLbs && Intrinsics.areEqual((Object) Float.valueOf(this.height), (Object) Float.valueOf(userEntity.height)) && Intrinsics.areEqual(this.birthday, userEntity.birthday) && Intrinsics.areEqual(this.avatarUrl, userEntity.avatarUrl) && Intrinsics.areEqual(this.localAvatarUrl, userEntity.localAvatarUrl) && this.goalSteps == userEntity.goalSteps && Intrinsics.areEqual((Object) Float.valueOf(this.goalCalorie), (Object) Float.valueOf(userEntity.goalCalorie)) && Intrinsics.areEqual((Object) Float.valueOf(this.goalDistance), (Object) Float.valueOf(userEntity.goalDistance)) && Intrinsics.areEqual((Object) Float.valueOf(this.goalSportTime), (Object) Float.valueOf(userEntity.goalSportTime)) && Intrinsics.areEqual((Object) Float.valueOf(this.goalSleepTime), (Object) Float.valueOf(userEntity.goalSleepTime)) && Intrinsics.areEqual(this.registerDate, userEntity.registerDate) && this.update == userEntity.update;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((CollectionRequest$$ExternalSyntheticBackport0.m(this.title) * 31) + this.email.hashCode()) * 31) + this.nickName.hashCode()) * 31) + this.gender) * 31) + Float.floatToIntBits(this.weight)) * 31) + this.weightLbs) * 31) + Float.floatToIntBits(this.height)) * 31) + this.birthday.hashCode()) * 31) + this.avatarUrl.hashCode()) * 31) + this.localAvatarUrl.hashCode()) * 31) + this.goalSteps) * 31) + Float.floatToIntBits(this.goalCalorie)) * 31) + Float.floatToIntBits(this.goalDistance)) * 31) + Float.floatToIntBits(this.goalSportTime)) * 31) + Float.floatToIntBits(this.goalSleepTime)) * 31) + this.registerDate.hashCode()) * 31) + this.update;
    }

    public String toString() {
        return "UserEntity(title=" + this.title + ", email=" + this.email + ", nickName=" + this.nickName + ", gender=" + this.gender + ", weight=" + this.weight + ", weightLbs=" + this.weightLbs + ", height=" + this.height + ", birthday=" + this.birthday + ", avatarUrl=" + this.avatarUrl + ", localAvatarUrl=" + this.localAvatarUrl + ", goalSteps=" + this.goalSteps + ", goalCalorie=" + this.goalCalorie + ", goalDistance=" + this.goalDistance + ", goalSportTime=" + this.goalSportTime + ", goalSleepTime=" + this.goalSleepTime + ", registerDate=" + this.registerDate + ", update=" + this.update + ')';
    }

    public UserEntity(long j, String email, String nickName, int i, float f, int i2, float f2, String birthday, String avatarUrl, String localAvatarUrl, int i3, float f3, float f4, float f5, float f6, String registerDate, int i4) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(nickName, "nickName");
        Intrinsics.checkNotNullParameter(birthday, "birthday");
        Intrinsics.checkNotNullParameter(avatarUrl, "avatarUrl");
        Intrinsics.checkNotNullParameter(localAvatarUrl, "localAvatarUrl");
        Intrinsics.checkNotNullParameter(registerDate, "registerDate");
        this.title = j;
        this.email = email;
        this.nickName = nickName;
        this.gender = i;
        this.weight = f;
        this.weightLbs = i2;
        this.height = f2;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
        this.localAvatarUrl = localAvatarUrl;
        this.goalSteps = i3;
        this.goalCalorie = f3;
        this.goalDistance = f4;
        this.goalSportTime = f5;
        this.goalSleepTime = f6;
        this.registerDate = registerDate;
        this.update = i4;
    }

    public final long getTitle() {
        return this.title;
    }

    public final void setTitle(long j) {
        this.title = j;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.email = str;
    }

    public final String getNickName() {
        return this.nickName;
    }

    public final void setNickName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.nickName = str;
    }

    public final int getGender() {
        return this.gender;
    }

    public final void setGender(int i) {
        this.gender = i;
    }

    public final float getWeight() {
        return this.weight;
    }

    public final void setWeight(float f) {
        this.weight = f;
    }

    public final int getWeightLbs() {
        return this.weightLbs;
    }

    public final void setWeightLbs(int i) {
        this.weightLbs = i;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final void setBirthday(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.birthday = str;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final void setAvatarUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.avatarUrl = str;
    }

    public final String getLocalAvatarUrl() {
        return this.localAvatarUrl;
    }

    public final void setLocalAvatarUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localAvatarUrl = str;
    }

    public final int getGoalSteps() {
        return this.goalSteps;
    }

    public final void setGoalSteps(int i) {
        this.goalSteps = i;
    }

    public final float getGoalCalorie() {
        return this.goalCalorie;
    }

    public final void setGoalCalorie(float f) {
        this.goalCalorie = f;
    }

    public final float getGoalDistance() {
        return this.goalDistance;
    }

    public final void setGoalDistance(float f) {
        this.goalDistance = f;
    }

    public final float getGoalSportTime() {
        return this.goalSportTime;
    }

    public final void setGoalSportTime(float f) {
        this.goalSportTime = f;
    }

    public final float getGoalSleepTime() {
        return this.goalSleepTime;
    }

    public final void setGoalSleepTime(float f) {
        this.goalSleepTime = f;
    }

    public final String getRegisterDate() {
        return this.registerDate;
    }

    public final void setRegisterDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.registerDate = str;
    }

    public final int getUpdate() {
        return this.update;
    }

    public final void setUpdate(int i) {
        this.update = i;
    }
}
