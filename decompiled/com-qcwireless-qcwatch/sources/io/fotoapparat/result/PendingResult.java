package io.fotoapparat.result;

import androidx.exifinterface.media.ExifInterface;
import io.fotoapparat.concurrent.EnsureBgThreadKt;
import io.fotoapparat.exception.UnableToDecodeBitmapException;
import io.fotoapparat.hardware.ExecutorKt;
import io.fotoapparat.log.Logger;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: PendingResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u001a*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001aB%\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ+\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0001\u0010\u000e2\u0018\u0010\u000f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u0002H\u000e0\u0010¢\u0006\u0002\u0010\u0011J\u000b\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\fJ&\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0000\"\u0004\b\u0001\u0010\u000e2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u000e0\u0010J\u001c\u0010\u0015\u001a\u00020\u00162\u0014\u0010\u0017\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\u00160\u0010J\u0014\u0010\u0018\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001b"}, d2 = {"Lio/fotoapparat/result/PendingResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "future", "Ljava/util/concurrent/Future;", "logger", "Lio/fotoapparat/log/Logger;", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Future;Lio/fotoapparat/log/Logger;Ljava/util/concurrent/Executor;)V", "resultUnsafe", "getResultUnsafe", "()Ljava/lang/Object;", "adapt", "R", "adapter", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "await", "transform", "transformer", "whenAvailable", "", "callback", "whenDone", "Lio/fotoapparat/result/WhenDoneListener;", "Companion", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public class PendingResult<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Executor executor;
    private final Future<T> future;
    private final Logger logger;

    /* compiled from: PendingResult.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0017\u0010\u0003\u001a\u0013\u0018\u0001H\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "p1", "Lkotlin/ParameterName;", "name", "it", "invoke", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 1, 13})
    /* renamed from: io.fotoapparat.result.PendingResult$whenDone$1, reason: invalid class name and case insensitive filesystem */
    static final class C06951 extends FunctionReference implements Function1<T, Unit> {
        C06951(WhenDoneListener whenDoneListener) {
            super(1, whenDoneListener);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "whenDone";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(WhenDoneListener.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "whenDone(Ljava/lang/Object;)V";
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
            invoke2((C06951) obj);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(T t) {
            ((WhenDoneListener) this.receiver).whenDone(t);
        }
    }

    public PendingResult(Future<T> future, Logger logger, Executor executor) {
        Intrinsics.checkParameterIsNotNull(future, "future");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(executor, "executor");
        this.future = future;
        this.logger = logger;
        this.executor = executor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T getResultUnsafe() {
        EnsureBgThreadKt.ensureBackgroundThread();
        return this.future.get();
    }

    public final <R> PendingResult<R> transform(final Function1<? super T, ? extends R> transformer) {
        Intrinsics.checkParameterIsNotNull(transformer, "transformer");
        FutureTask futureTask = new FutureTask(new Callable<V>() { // from class: io.fotoapparat.result.PendingResult$transform$transformTask$1
            /* JADX WARN: Type inference failed for: r0v1, types: [R, java.lang.Object] */
            @Override // java.util.concurrent.Callable
            public final R call() {
                return transformer.invoke(this.this$0.future.get());
            }
        });
        this.executor.execute(futureTask);
        return new PendingResult<>(futureTask, this.logger, this.executor);
    }

    public final T await() throws ExecutionException, InterruptedException {
        return this.future.get();
    }

    public final <R> R adapt(Function1<? super Future<T>, ? extends R> adapter) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        return adapter.invoke(this.future);
    }

    public final void whenAvailable(final Function1<? super T, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.executor.execute(new Runnable() { // from class: io.fotoapparat.result.PendingResult.whenAvailable.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    final Object resultUnsafe = PendingResult.this.getResultUnsafe();
                    PendingResultKt.notifyOnMainThread(new Function0<Unit>() { // from class: io.fotoapparat.result.PendingResult.whenAvailable.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            callback.invoke(resultUnsafe);
                        }
                    });
                } catch (UnableToDecodeBitmapException unused) {
                    PendingResult.this.logger.log("Couldn't decode bitmap from byte array");
                    PendingResultKt.notifyOnMainThread(new Function0<Unit>() { // from class: io.fotoapparat.result.PendingResult.whenAvailable.1.2
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
                            callback.invoke(null);
                        }
                    });
                } catch (InterruptedException unused2) {
                    PendingResult.this.logger.log("Couldn't deliver pending result: Camera stopped before delivering result.");
                } catch (CancellationException unused3) {
                    PendingResult.this.logger.log("Couldn't deliver pending result: Camera operation was cancelled.");
                } catch (ExecutionException unused4) {
                    PendingResult.this.logger.log("Couldn't deliver pending result: Operation failed internally.");
                    PendingResultKt.notifyOnMainThread(new Function0<Unit>() { // from class: io.fotoapparat.result.PendingResult.whenAvailable.1.3
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
                            callback.invoke(null);
                        }
                    });
                }
            }
        });
    }

    public final void whenDone(WhenDoneListener<? super T> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        whenAvailable(new C06951(callback));
    }

    /* compiled from: PendingResult.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Lio/fotoapparat/result/PendingResult$Companion;", "", "()V", "fromFuture", "Lio/fotoapparat/result/PendingResult;", ExifInterface.GPS_DIRECTION_TRUE, "future", "Ljava/util/concurrent/Future;", "logger", "Lio/fotoapparat/log/Logger;", "fromFuture$fotoapparat_release", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T> PendingResult<T> fromFuture$fotoapparat_release(Future<T> future, Logger logger) {
            Intrinsics.checkParameterIsNotNull(future, "future");
            Intrinsics.checkParameterIsNotNull(logger, "logger");
            ExecutorService pendingResultExecutor = ExecutorKt.getPendingResultExecutor();
            Intrinsics.checkExpressionValueIsNotNull(pendingResultExecutor, "pendingResultExecutor");
            return new PendingResult<>(future, logger, pendingResultExecutor);
        }
    }
}
