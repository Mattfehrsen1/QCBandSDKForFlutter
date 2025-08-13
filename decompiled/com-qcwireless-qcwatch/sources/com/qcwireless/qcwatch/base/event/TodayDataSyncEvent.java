package com.qcwireless.qcwatch.base.event;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* compiled from: TodayDataSyncEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/base/event/TodayDataSyncEvent;", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", NotificationCompat.CATEGORY_STATUS, "", "(Z)V", "getStatus", "()Z", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class TodayDataSyncEvent extends MessageEvent {
    private final boolean status;

    public TodayDataSyncEvent(boolean z) {
        this.status = z;
    }

    public final boolean getStatus() {
        return this.status;
    }
}
