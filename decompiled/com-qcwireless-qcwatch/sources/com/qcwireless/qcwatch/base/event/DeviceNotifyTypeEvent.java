package com.qcwireless.qcwatch.base.event;

import kotlin.Metadata;

/* compiled from: DeviceNotifyTypeEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/base/event/DeviceNotifyTypeEvent;", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "type", "", "(I)V", "getType", "()I", "setType", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class DeviceNotifyTypeEvent extends MessageEvent {
    private int type;

    public DeviceNotifyTypeEvent(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
