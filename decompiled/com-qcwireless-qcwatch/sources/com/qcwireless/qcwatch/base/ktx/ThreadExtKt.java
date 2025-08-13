package com.qcwireless.qcwatch.base.ktx;

import android.os.Handler;
import android.os.Looper;
import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;

/* compiled from: ThreadExt.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\u001a.\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012\u001a.\u0010\u0013\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012\u001a.\u0010\u0014\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012\u001a.\u0010\u0015\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012\u001a.\u0010\u0016\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012\u001a.\u0010\u0017\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012\u001a.\u0010\u0018\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u0012\u001a6\u0010\u0019\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\u0002H\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\r0\u0010¢\u0006\u0002\b\u0011¢\u0006\u0002\u0010\u001c\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"cache", "Ljava/util/concurrent/ExecutorService;", "coreSize", "", "fix", "handler", "Landroid/os/Handler;", "scheduled", "single", "singleAnother", "singleBle", "singleNetWork", "ktxRunOnBgCache", "", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "ktxRunOnBgFix", "ktxRunOnBgSingle", "ktxRunOnBgSingleAnother", "ktxRunOnBgSingleBle", "ktxRunOnBgSingleNetWork", "ktxRunOnUi", "ktxRunOnUiDelay", "delayMillis", "", "(Ljava/lang/Object;JLkotlin/jvm/functions/Function1;)V", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThreadExtKt {
    private static final ExecutorService cache;
    private static final int coreSize;
    private static final ExecutorService fix;
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static final ExecutorService scheduled;
    private static final ExecutorService single;
    private static final ExecutorService singleAnother;
    private static final ExecutorService singleBle;
    private static final ExecutorService singleNetWork;

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors() + 1;
        coreSize = iAvailableProcessors;
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(iAvailableProcessors);
        Intrinsics.checkNotNullExpressionValue(executorServiceNewFixedThreadPool, "newFixedThreadPool(coreSize)");
        fix = executorServiceNewFixedThreadPool;
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewCachedThreadPool, "newCachedThreadPool()");
        cache = executorServiceNewCachedThreadPool;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        single = executorServiceNewSingleThreadExecutor;
        ExecutorService executorServiceNewSingleThreadExecutor2 = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor2, "newSingleThreadExecutor()");
        singleAnother = executorServiceNewSingleThreadExecutor2;
        ExecutorService executorServiceNewSingleThreadExecutor3 = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor3, "newSingleThreadExecutor()");
        singleBle = executorServiceNewSingleThreadExecutor3;
        ExecutorService executorServiceNewSingleThreadExecutor4 = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor4, "newSingleThreadExecutor()");
        singleNetWork = executorServiceNewSingleThreadExecutor4;
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(iAvailableProcessors);
        Intrinsics.checkNotNullExpressionValue(scheduledExecutorServiceNewScheduledThreadPool, "newScheduledThreadPool(coreSize)");
        scheduled = scheduledExecutorServiceNewScheduledThreadPool;
    }

    public static final <T> void ktxRunOnUi(final T t, final Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        handler.post(new Runnable() { // from class: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ThreadExtKt.m249ktxRunOnUi$lambda0(block, t);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ktxRunOnUi$lambda-0, reason: not valid java name */
    public static final void m249ktxRunOnUi$lambda0(Function1 block, Object obj) {
        Intrinsics.checkNotNullParameter(block, "$block");
        block.invoke(obj);
    }

    public static final <T> void ktxRunOnUiDelay(final T t, long j, final Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ThreadExtKt.m250ktxRunOnUiDelay$lambda1(block, t);
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ktxRunOnUiDelay$lambda-1, reason: not valid java name */
    public static final void m250ktxRunOnUiDelay$lambda1(Function1 block, Object obj) {
        Intrinsics.checkNotNullParameter(block, "$block");
        block.invoke(obj);
    }

    public static final <T> void ktxRunOnBgSingle(T t, Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executorServiceNewSingleThreadExecutor)), null, null, new ThreadExtKt$ktxRunOnBgSingle$job$1(block, t, null), 3, null), null), 1, null);
    }

    /* compiled from: ThreadExt.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.base.ktx.ThreadExtKt$ktxRunOnBgSingle$1", f = "ThreadExt.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$ktxRunOnBgSingle$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $job;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Job job, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$job = job;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$job, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.$job.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final <T> void ktxRunOnBgSingleAnother(T t, Function1<? super T, Unit> block) throws InterruptedException {
        Intrinsics.checkNotNullParameter(block, "block");
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        BuildersKt__BuildersKt.runBlocking$default(null, new C02851(BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executorServiceNewSingleThreadExecutor)), null, null, new ThreadExtKt$ktxRunOnBgSingleAnother$job$1(block, t, null), 3, null), null), 1, null);
    }

    /* compiled from: ThreadExt.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.base.ktx.ThreadExtKt$ktxRunOnBgSingleAnother$1", f = "ThreadExt.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$ktxRunOnBgSingleAnother$1, reason: invalid class name and case insensitive filesystem */
    static final class C02851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $job;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02851(Job job, Continuation<? super C02851> continuation) {
            super(2, continuation);
            this.$job = job;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02851(this.$job, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.$job.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final <T> void ktxRunOnBgSingleBle(final T t, final Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        singleBle.execute(new Runnable() { // from class: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ThreadExtKt.m248ktxRunOnBgSingleBle$lambda2(block, t);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ktxRunOnBgSingleBle$lambda-2, reason: not valid java name */
    public static final void m248ktxRunOnBgSingleBle$lambda2(Function1 block, Object obj) {
        Intrinsics.checkNotNullParameter(block, "$block");
        block.invoke(obj);
    }

    public static final <T> void ktxRunOnBgSingleNetWork(T t, Function1<? super T, Unit> block) throws InterruptedException {
        Intrinsics.checkNotNullParameter(block, "block");
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        BuildersKt__BuildersKt.runBlocking$default(null, new C02861(BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executorServiceNewSingleThreadExecutor)), null, null, new ThreadExtKt$ktxRunOnBgSingleNetWork$job$1(block, t, null), 3, null), null), 1, null);
    }

    /* compiled from: ThreadExt.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.base.ktx.ThreadExtKt$ktxRunOnBgSingleNetWork$1", f = "ThreadExt.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$ktxRunOnBgSingleNetWork$1, reason: invalid class name and case insensitive filesystem */
    static final class C02861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $job;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02861(Job job, Continuation<? super C02861> continuation) {
            super(2, continuation);
            this.$job = job;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02861(this.$job, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.$job.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final <T> void ktxRunOnBgFix(final T t, final Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        fix.execute(new Runnable() { // from class: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ThreadExtKt.m247ktxRunOnBgFix$lambda3(block, t);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ktxRunOnBgFix$lambda-3, reason: not valid java name */
    public static final void m247ktxRunOnBgFix$lambda3(Function1 block, Object obj) {
        Intrinsics.checkNotNullParameter(block, "$block");
        block.invoke(obj);
    }

    public static final <T> void ktxRunOnBgCache(final T t, final Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        cache.execute(new Runnable() { // from class: com.qcwireless.qcwatch.base.ktx.ThreadExtKt$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ThreadExtKt.m246ktxRunOnBgCache$lambda4(block, t);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ktxRunOnBgCache$lambda-4, reason: not valid java name */
    public static final void m246ktxRunOnBgCache$lambda4(Function1 block, Object obj) {
        Intrinsics.checkNotNullParameter(block, "$block");
        block.invoke(obj);
    }
}
