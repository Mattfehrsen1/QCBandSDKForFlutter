package com.qcwireless.qcwatch.ui.base.repository.watchface;

import com.elvishew.xlog.XLog;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcWatchWallpaperDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QueueWallpaperImageListener.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J0\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/watchface/QueueWallpaperImageListener;", "Lcom/liulishuo/okdownload/core/listener/DownloadListener1;", "()V", "watchWallpaperDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcWatchWallpaperDao;", "connected", "", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "blockCount", "", "currentOffset", "", "totalLength", "progress", "retry", "cause", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "taskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "realCause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "model", "Lcom/liulishuo/okdownload/core/listener/assist/Listener1Assist$Listener1Model;", "taskStart", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QueueWallpaperImageListener extends DownloadListener1 {
    private static final String TAG = "QueueListener";
    private final QcWatchWallpaperDao watchWallpaperDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcWatchWallpaperDao();

    /* compiled from: QueueWallpaperImageListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EndCause.values().length];
            iArr[EndCause.COMPLETED.ordinal()] = 1;
            iArr[EndCause.ERROR.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

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
        XLog.i("---retry");
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
        Intrinsics.checkNotNullParameter(task, "task");
        QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(true);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void progress(DownloadTask task, long currentOffset, long totalLength) {
        Intrinsics.checkNotNullParameter(task, "task");
        if (currentOffset > 0) {
            QCApplication.INSTANCE.getGetInstance().setDownloadWatchFace(true);
        }
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void taskEnd(final DownloadTask task, EndCause cause, Exception realCause, Listener1Assist.Listener1Model model) throws Throwable {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(cause, "cause");
        Intrinsics.checkNotNullParameter(model, "model");
        int i = WhenMappings.$EnumSwitchMapping$0[cause.ordinal()];
        if (i == 1) {
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<QueueWallpaperImageListener, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.repository.watchface.QueueWallpaperImageListener.taskEnd.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(QueueWallpaperImageListener queueWallpaperImageListener) {
                    invoke2(queueWallpaperImageListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(QueueWallpaperImageListener ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    WatchWallpaperFace watchWallpaperFaceQueryWatchWallpaperByName = ktxRunOnBgSingle.watchWallpaperDao.queryWatchWallpaperByName(task.getTag().toString(), UserConfig.INSTANCE.getInstance().getHwVersion());
                    if (watchWallpaperFaceQueryWatchWallpaperByName != null) {
                        File file = task.getFile();
                        watchWallpaperFaceQueryWatchWallpaperByName.setLocalImageUrl(String.valueOf(file != null ? file.getAbsolutePath() : null));
                        ktxRunOnBgSingle.watchWallpaperDao.insert(watchWallpaperFaceQueryWatchWallpaperByName);
                    }
                }
            });
            return;
        }
        if (i == 2) {
            try {
                FileUtils fileUtils = FileUtils.INSTANCE;
                File file = task.getFile();
                fileUtils.deleteFile(String.valueOf(file != null ? file.getAbsolutePath() : null));
            } catch (Exception unused) {
            }
            XLog.i(cause.toString());
            return;
        }
        String string = task.getTag().toString();
        LogToFile.getInstance().wtf(string + ':' + cause.name());
    }
}
