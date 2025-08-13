package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* compiled from: BpDownRequest.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BpDownRequest;", "", "uid", "", "lastSyncId", "size", "", "(JJI)V", "getLastSyncId", "()J", "getSize", "()I", "getUid", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BpDownRequest {
    private final long lastSyncId;
    private final int size;
    private final long uid;

    public static /* synthetic */ BpDownRequest copy$default(BpDownRequest bpDownRequest, long j, long j2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = bpDownRequest.uid;
        }
        long j3 = j;
        if ((i2 & 2) != 0) {
            j2 = bpDownRequest.lastSyncId;
        }
        long j4 = j2;
        if ((i2 & 4) != 0) {
            i = bpDownRequest.size;
        }
        return bpDownRequest.copy(j3, j4, i);
    }

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final long getLastSyncId() {
        return this.lastSyncId;
    }

    /* renamed from: component3, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    public final BpDownRequest copy(long uid, long lastSyncId, int size) {
        return new BpDownRequest(uid, lastSyncId, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BpDownRequest)) {
            return false;
        }
        BpDownRequest bpDownRequest = (BpDownRequest) other;
        return this.uid == bpDownRequest.uid && this.lastSyncId == bpDownRequest.lastSyncId && this.size == bpDownRequest.size;
    }

    public int hashCode() {
        return (((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncId)) * 31) + this.size;
    }

    public String toString() {
        return "BpDownRequest(uid=" + this.uid + ", lastSyncId=" + this.lastSyncId + ", size=" + this.size + ')';
    }

    public BpDownRequest(long j, long j2, int i) {
        this.uid = j;
        this.lastSyncId = j2;
        this.size = i;
    }

    public final long getLastSyncId() {
        return this.lastSyncId;
    }

    public final int getSize() {
        return this.size;
    }

    public final long getUid() {
        return this.uid;
    }
}
