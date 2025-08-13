package com.app.watch.keeping.workmanager;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.app.watch.keeping.entity.CactusConfig;
import com.app.watch.keeping.ext.CactusExtKt;
import com.app.watch.keeping.ext.ConfigExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CactusWorker.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/app/watch/keeping/workmanager/CactusWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "mIsStop", "", "doWork", "Landroidx/work/ListenableWorker$Result;", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CactusWorker extends Worker {
    private final Context context;
    private boolean mIsStop;

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CactusWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.context = context;
        CactusExtKt.registerStopReceiver(context, new Function0<Unit>() { // from class: com.app.watch.keeping.workmanager.CactusWorker.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CactusWorker.this.mIsStop = true;
            }
        });
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Context context = this.context;
        CactusConfig config = ConfigExtKt.getConfig(context);
        CactusExtKt.log(this + "-doWork");
        if (!CactusExtKt.isCactusRunning(context) && !this.mIsStop && !isStopped()) {
            CactusExtKt.register(context, config);
        }
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(resultSuccess, "success()");
        return resultSuccess;
    }
}
