package com.qcwireless.qcwatch.ui.base.repository.watchface;

import com.blankj.utilcode.util.FileUtils;
import com.elvishew.xlog.XLog;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchFaceDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: QueueBinListener.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J0\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/watchface/QueueBinListener;", "Lcom/liulishuo/okdownload/core/listener/DownloadListener1;", "()V", "watchFaceDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchFaceDao;", "connected", "", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "blockCount", "", "currentOffset", "", "totalLength", "progress", "retry", "cause", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "taskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "realCause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "model", "Lcom/liulishuo/okdownload/core/listener/assist/Listener1Assist$Listener1Model;", "taskStart", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QueueBinListener extends DownloadListener1 {
    private static final String TAG = "QueueListener";
    private final QcWatchFaceDao watchFaceDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchFaceDao();

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void taskStart(DownloadTask task, Listener1Assist.Listener1Model model) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(model, "model");
        QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(true);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void retry(DownloadTask task, ResumeFailedCause cause) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(cause, "cause");
        XLog.i(task.getTag() + "---" + cause);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
        Intrinsics.checkNotNullParameter(task, "task");
        QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(true);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void progress(DownloadTask task, long currentOffset, long totalLength) {
        Intrinsics.checkNotNullParameter(task, "task");
        QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(true);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [T, java.lang.String] */
    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void taskEnd(final DownloadTask task, EndCause cause, Exception realCause, Listener1Assist.Listener1Model model) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(cause, "cause");
        Intrinsics.checkNotNullParameter(model, "model");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = task.getTag().toString();
        if (cause == EndCause.COMPLETED) {
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<QueueBinListener, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.QueueBinListener.taskEnd.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(QueueBinListener queueBinListener) {
                    invoke2(queueBinListener);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.String] */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(QueueBinListener ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    Ref.ObjectRef<String> objectRef2 = objectRef;
                    objectRef2.element = StringsKt.substringAfter$default(objectRef2.element, "temp_", (String) null, 2, (Object) null);
                    FileUtils.rename(com.qcwireless.qcwatch.base.utils.FileUtils.INSTANCE.getWatchFaceDirFile().getAbsolutePath() + '/' + task.getTag(), objectRef.element);
                    WatchFace watchFaceQueryWatchFaceByName = ktxRunOnBgSingle.watchFaceDao.queryWatchFaceByName(objectRef.element, UserConfig.INSTANCE.getInstance().getHwVersion());
                    if (watchFaceQueryWatchFaceByName != null) {
                        watchFaceQueryWatchFaceByName.setLocalBinUrl(com.qcwireless.qcwatch.base.utils.FileUtils.INSTANCE.getWatchFaceDirFile().getAbsolutePath() + '/' + objectRef.element);
                        ktxRunOnBgSingle.watchFaceDao.insert(watchFaceQueryWatchFaceByName);
                    }
                }
            });
        } else if (cause == EndCause.ERROR) {
            try {
                com.qcwireless.qcwatch.base.utils.FileUtils fileUtils = com.qcwireless.qcwatch.base.utils.FileUtils.INSTANCE;
                File file = task.getFile();
                fileUtils.deleteFile(String.valueOf(file != null ? file.getAbsolutePath() : null));
            } catch (Exception unused) {
            }
        }
    }
}
