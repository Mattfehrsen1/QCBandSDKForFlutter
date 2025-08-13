package com.liulishuo.okdownload.kotlin.listener;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadListener2Extension.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0082\u0001\u0010\u0000\u001a\u00020\u00012'\b\u0002\u0010\u0002\u001a!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t2Q\u0010\n\u001aM\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\u000bj\u0002`\u0010¨\u0006\u0011"}, d2 = {"createListener2", "Lcom/liulishuo/okdownload/core/listener/DownloadListener2;", "onTaskStart", "Lkotlin/Function1;", "Lcom/liulishuo/okdownload/DownloadTask;", "Lkotlin/ParameterName;", "name", "task", "", "Lcom/liulishuo/okdownload/kotlin/listener/onTaskStart;", "onTaskEnd", "Lkotlin/Function3;", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "cause", "Ljava/lang/Exception;", "realCause", "Lcom/liulishuo/okdownload/kotlin/listener/onTaskEnd;", "kotlin_release"}, k = 2, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class DownloadListener2ExtensionKt {
    public static /* synthetic */ DownloadListener2 createListener2$default(Function1 function1, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<DownloadTask, Unit>() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListener2ExtensionKt.createListener2.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DownloadTask downloadTask) {
                    invoke2(downloadTask);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DownloadTask it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            };
        }
        return createListener2(function1, function3);
    }

    public static final DownloadListener2 createListener2(final Function1<? super DownloadTask, Unit> onTaskStart, final Function3<? super DownloadTask, ? super EndCause, ? super Exception, Unit> onTaskEnd) {
        Intrinsics.checkParameterIsNotNull(onTaskStart, "onTaskStart");
        Intrinsics.checkParameterIsNotNull(onTaskEnd, "onTaskEnd");
        return new DownloadListener2() { // from class: com.liulishuo.okdownload.kotlin.listener.DownloadListener2ExtensionKt.createListener2.2
            @Override // com.liulishuo.okdownload.DownloadListener
            public void taskStart(DownloadTask task) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                onTaskStart.invoke(task);
            }

            @Override // com.liulishuo.okdownload.DownloadListener
            public void taskEnd(DownloadTask task, EndCause cause, Exception realCause) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                onTaskEnd.invoke(task, cause, realCause);
            }
        };
    }
}
