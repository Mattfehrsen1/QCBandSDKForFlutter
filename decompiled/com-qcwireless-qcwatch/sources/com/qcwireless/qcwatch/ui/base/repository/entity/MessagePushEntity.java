package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessagePushEntity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/MessagePushEntity;", "", "packageName", "", "open", "", "(Ljava/lang/String;I)V", "getOpen", "()I", "getPackageName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class MessagePushEntity {
    private final int open;
    private final String packageName;

    public static /* synthetic */ MessagePushEntity copy$default(MessagePushEntity messagePushEntity, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = messagePushEntity.packageName;
        }
        if ((i2 & 2) != 0) {
            i = messagePushEntity.open;
        }
        return messagePushEntity.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* renamed from: component2, reason: from getter */
    public final int getOpen() {
        return this.open;
    }

    public final MessagePushEntity copy(String packageName, int open) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new MessagePushEntity(packageName, open);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessagePushEntity)) {
            return false;
        }
        MessagePushEntity messagePushEntity = (MessagePushEntity) other;
        return Intrinsics.areEqual(this.packageName, messagePushEntity.packageName) && this.open == messagePushEntity.open;
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + this.open;
    }

    public String toString() {
        return "MessagePushEntity(packageName=" + this.packageName + ", open=" + this.open + ')';
    }

    public MessagePushEntity(String packageName, int i) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.packageName = packageName;
        this.open = i;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final int getOpen() {
        return this.open;
    }
}
