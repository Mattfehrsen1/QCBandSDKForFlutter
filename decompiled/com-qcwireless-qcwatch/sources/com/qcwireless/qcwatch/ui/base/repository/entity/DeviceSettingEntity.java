package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceSettingEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;", "", "mac", "", "settingAction", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getMac", "getSettingAction", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DeviceSettingEntity {
    private final String content;
    private final String mac;
    private final String settingAction;

    public static /* synthetic */ DeviceSettingEntity copy$default(DeviceSettingEntity deviceSettingEntity, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceSettingEntity.mac;
        }
        if ((i & 2) != 0) {
            str2 = deviceSettingEntity.settingAction;
        }
        if ((i & 4) != 0) {
            str3 = deviceSettingEntity.content;
        }
        return deviceSettingEntity.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSettingAction() {
        return this.settingAction;
    }

    /* renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    public final DeviceSettingEntity copy(String mac, String settingAction, String content) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(settingAction, "settingAction");
        Intrinsics.checkNotNullParameter(content, "content");
        return new DeviceSettingEntity(mac, settingAction, content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceSettingEntity)) {
            return false;
        }
        DeviceSettingEntity deviceSettingEntity = (DeviceSettingEntity) other;
        return Intrinsics.areEqual(this.mac, deviceSettingEntity.mac) && Intrinsics.areEqual(this.settingAction, deviceSettingEntity.settingAction) && Intrinsics.areEqual(this.content, deviceSettingEntity.content);
    }

    public int hashCode() {
        return (((this.mac.hashCode() * 31) + this.settingAction.hashCode()) * 31) + this.content.hashCode();
    }

    public String toString() {
        return "DeviceSettingEntity(mac=" + this.mac + ", settingAction=" + this.settingAction + ", content=" + this.content + ')';
    }

    public DeviceSettingEntity(String mac, String settingAction, String content) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(settingAction, "settingAction");
        Intrinsics.checkNotNullParameter(content, "content");
        this.mac = mac;
        this.settingAction = settingAction;
        this.content = content;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getSettingAction() {
        return this.settingAction;
    }

    public final String getContent() {
        return this.content;
    }
}
