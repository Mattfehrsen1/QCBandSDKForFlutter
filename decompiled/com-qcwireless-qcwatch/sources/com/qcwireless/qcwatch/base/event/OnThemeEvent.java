package com.qcwireless.qcwatch.base.event;

import kotlin.Metadata;

/* compiled from: OnThemeEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/base/event/OnThemeEvent;", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "skinType", "", "(I)V", "getSkinType", "()I", "setSkinType", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OnThemeEvent extends MessageEvent {
    private int skinType;

    public OnThemeEvent(int i) {
        this.skinType = i;
    }

    public final int getSkinType() {
        return this.skinType;
    }

    public final void setSkinType(int i) {
        this.skinType = i;
    }
}
