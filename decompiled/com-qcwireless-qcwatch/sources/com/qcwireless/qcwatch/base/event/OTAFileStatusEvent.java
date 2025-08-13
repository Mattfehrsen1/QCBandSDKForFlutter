package com.qcwireless.qcwatch.base.event;

import com.androidnetworking.common.ANConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OTAFileStatusEvent.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/base/event/OTAFileStatusEvent;", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "type", "", "progressBar", ANConstants.SUCCESS, "", "fileName", "", "(IIZLjava/lang/String;)V", "getFileName", "()Ljava/lang/String;", "getProgressBar", "()I", "getSuccess", "()Z", "getType", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class OTAFileStatusEvent extends MessageEvent {
    private final String fileName;
    private final int progressBar;
    private final boolean success;
    private final int type;

    public OTAFileStatusEvent(int i, int i2, boolean z, String fileName) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.type = i;
        this.progressBar = i2;
        this.success = z;
        this.fileName = fileName;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final int getProgressBar() {
        return this.progressBar;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final int getType() {
        return this.type;
    }
}
