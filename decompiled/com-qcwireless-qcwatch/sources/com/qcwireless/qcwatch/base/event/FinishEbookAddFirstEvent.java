package com.qcwireless.qcwatch.base.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FinishEbookAddFirstEvent.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/base/event/FinishEbookAddFirstEvent;", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "name", "", "path", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPath", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FinishEbookAddFirstEvent extends MessageEvent {
    private final String name;
    private final String path;

    public FinishEbookAddFirstEvent(String name, String path) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(path, "path");
        this.name = name;
        this.path = path;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }
}
