package com.qcwireless.qcwatch.ui.base.repository.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncDataEntity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/SyncDataEntity;", "", "uid", "", "action", "", "lastSyncTime", "(JLjava/lang/String;J)V", "getAction", "()Ljava/lang/String;", "getLastSyncTime", "()J", "getUid", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SyncDataEntity {
    private final String action;
    private final long lastSyncTime;
    private final long uid;

    public SyncDataEntity(long j, String action, long j2) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.uid = j;
        this.action = action;
        this.lastSyncTime = j2;
    }

    public final long getUid() {
        return this.uid;
    }

    public final String getAction() {
        return this.action;
    }

    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }
}
