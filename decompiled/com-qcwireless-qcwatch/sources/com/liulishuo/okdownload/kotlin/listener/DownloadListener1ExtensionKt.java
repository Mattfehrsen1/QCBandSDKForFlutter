package com.liulishuo.okdownload.kotlin.listener;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadListener1Extension.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aµ\u0003\u0010\u0000\u001a\u00020\u00012@\b\u0002\u0010\u0002\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u000b2@\b\u0002\u0010\f\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u000f2j\b\u0002\u0010\u0010\u001ad\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u00172U\b\u0002\u0010\u0018\u001aO\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001a2f\u0010\u001b\u001ab\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0011j\u0002`\u001f*¾\u0001\u0010 \"\\\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\n0\u00112\\\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\n0\u0011*\u0094\u0001\u0010!\"G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\n0\u00192G\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\n0\u0019*j\u0010\"\"2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\n0\u000322\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\n0\u0003*Â\u0001\u0010#\"^\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00112^\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0011*j\u0010$\"2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u000322\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003¨\u0006%"}, d2 = {"createListener1", "Lcom/liulishuo/okdownload/core/listener/DownloadListener1;", "taskStart", "Lkotlin/Function2;", "Lcom/liulishuo/okdownload/DownloadTask;", "Lkotlin/ParameterName;", "name", "task", "Lcom/liulishuo/okdownload/core/listener/assist/Listener1Assist$Listener1Model;", "model", "", "Lcom/liulishuo/okdownload/kotlin/listener/onTaskStartWithModel;", "retry", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "cause", "Lcom/liulishuo/okdownload/kotlin/listener/onRetry;", "connected", "Lkotlin/Function4;", "", "blockCount", "", "currentOffset", "totalLength", "Lcom/liulishuo/okdownload/kotlin/listener/onConnected;", "progress", "Lkotlin/Function3;", "Lcom/liulishuo/okdownload/kotlin/listener/onProgress;", "taskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "Ljava/lang/Exception;", "realCause", "Lcom/liulishuo/okdownload/kotlin/listener/onTaskEndWithModel;", "onConnected", "onProgress", "onRetry", "onTaskEndWithModel", "onTaskStartWithModel", "kotlin_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class DownloadListener1ExtensionKt {
    public static /* synthetic */ DownloadListener1 createListener1$default(Function2 function2, Function2 function22, Function4 function4, Function3 function3, Function4 function42, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = null;
        }
        if ((i & 2) != 0) {
            function22 = null;
        }
        if ((i & 4) != 0) {
            function4 = null;
        }
        if ((i & 8) != 0) {
            function3 = null;
        }
        return createListener1(function2, function22, function4, function3, function42);
    }

    public static final DownloadListener1 createListener1(final Function2<? super DownloadTask, ? super Listener1Assist.Listener1Model, Unit> function2, final Function2<? super DownloadTask, ? super ResumeFailedCause, Unit> function22, final Function4<? super DownloadTask, ? super Integer, ? super Long, ? super Long, Unit> function4, final Function3<? super DownloadTask, ? super Long, ? super Long, Unit> function3, final Function4<? super DownloadTask, ? super EndCause, ? super Exception, ? super Listener1Assist.Listener1Model, Unit> taskEnd) {
        Intrinsics.checkParameterIsNotNull(taskEnd, "taskEnd");
        return new DownloadListener1() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListener1ExtensionKt.createListener1.1
            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void taskStart(DownloadTask task, Listener1Assist.Listener1Model model) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(model, "model");
                Function2 function23 = function2;
                if (function23 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void retry(DownloadTask task, ResumeFailedCause cause) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                Function2 function23 = function22;
                if (function23 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Function4 function42 = function4;
                if (function42 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void progress(DownloadTask task, long currentOffset, long totalLength) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Function3 function32 = function3;
                if (function32 != null) {
                }
            }

            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void taskEnd(DownloadTask task, EndCause cause, Exception realCause, Listener1Assist.Listener1Model model) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                Intrinsics.checkParameterIsNotNull(model, "model");
                taskEnd.invoke(task, cause, realCause, model);
            }
        };
    }
}
