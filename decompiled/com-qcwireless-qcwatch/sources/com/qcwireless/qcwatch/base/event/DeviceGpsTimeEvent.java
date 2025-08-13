package com.qcwireless.qcwatch.base.event;

import kotlin.Metadata;

/* compiled from: DeviceGpsTimeEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/base/event/DeviceGpsTimeEvent;", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "time", "", "(J)V", "getTime", "()J", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class DeviceGpsTimeEvent extends MessageEvent {
    private final long time;

    public DeviceGpsTimeEvent(long j) {
        this.time = j;
    }

    public final long getTime() {
        return this.time;
    }
}
