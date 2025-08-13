package com.qcwireless.qcwatch.ui.base.repository.watchface;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QueueDiyImageListener.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J0\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/watchface/QueueDiyImageListener;", "Lcom/liulishuo/okdownload/core/listener/DownloadListener1;", "()V", "connected", "", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "blockCount", "", "currentOffset", "", "totalLength", "progress", "retry", "cause", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "taskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "realCause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "model", "Lcom/liulishuo/okdownload/core/listener/assist/Listener1Assist$Listener1Model;", "taskStart", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QueueDiyImageListener extends DownloadListener1 {
    private static final String TAG = "QueueListener";

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
        Intrinsics.checkNotNullParameter(task, "task");
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void progress(DownloadTask task, long currentOffset, long totalLength) {
        Intrinsics.checkNotNullParameter(task, "task");
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void retry(DownloadTask task, ResumeFailedCause cause) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(cause, "cause");
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void taskEnd(DownloadTask task, EndCause cause, Exception realCause, Listener1Assist.Listener1Model model) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(cause, "cause");
        Intrinsics.checkNotNullParameter(model, "model");
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void taskStart(DownloadTask task, Listener1Assist.Listener1Model model) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(model, "model");
    }
}
