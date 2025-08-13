package io.fotoapparat.concurrent;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.mine.ai.bean.ChatEnumAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraExecutor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\fH\u0002J \u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0011R\u0018\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006\u0013"}, d2 = {"Lio/fotoapparat/concurrent/CameraExecutor;", "", "executor", "Ljava/util/concurrent/ExecutorService;", "(Ljava/util/concurrent/ExecutorService;)V", "cancellableTasksQueue", "Ljava/util/LinkedList;", "Ljava/util/concurrent/Future;", "isPending", "", "(Ljava/util/concurrent/Future;)Z", "cancelTasks", "", "cleanUpCancelledTasks", "execute", ExifInterface.GPS_DIRECTION_TRUE, "operation", "Lio/fotoapparat/concurrent/CameraExecutor$Operation;", "Operation", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CameraExecutor {
    private final LinkedList<Future<?>> cancellableTasksQueue;
    private final ExecutorService executor;

    /* JADX WARN: Multi-variable type inference failed */
    public CameraExecutor() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public CameraExecutor(ExecutorService executor) {
        Intrinsics.checkParameterIsNotNull(executor, "executor");
        this.executor = executor;
        this.cancellableTasksQueue = new LinkedList<>();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CameraExecutor(ExecutorService executorService, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            executorService = Executors.newSingleThreadExecutor();
            Intrinsics.checkExpressionValueIsNotNull(executorService, "Executors.newSingleThreadExecutor()");
        }
        this(executorService);
    }

    public final <T> Future<T> execute(final Operation<? extends T> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Future<T> future = this.executor.submit(new Callable<T>() { // from class: io.fotoapparat.concurrent.CameraExecutor$execute$future$1
            @Override // java.util.concurrent.Callable
            public final T call() {
                return operation.getFunction().invoke();
            }
        });
        if (operation.getCancellable()) {
            this.cancellableTasksQueue.add(future);
        }
        cleanUpCancelledTasks();
        Intrinsics.checkExpressionValueIsNotNull(future, "future");
        return future;
    }

    private final void cleanUpCancelledTasks() {
        CollectionsKt.removeAll((List) this.cancellableTasksQueue, (Function1) new Function1<Future<?>, Boolean>() { // from class: io.fotoapparat.concurrent.CameraExecutor.cleanUpCancelledTasks.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Future<?> future) {
                return Boolean.valueOf(invoke2(future));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(Future<?> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return !CameraExecutor.this.isPending(it);
            }
        });
    }

    public final void cancelTasks() {
        LinkedList<Future<?>> linkedList = this.cancellableTasksQueue;
        ArrayList arrayList = new ArrayList();
        for (Object obj : linkedList) {
            if (isPending((Future) obj)) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Future) it.next()).cancel(true);
        }
        this.cancellableTasksQueue.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPending(Future<?> future) {
        return (future.isCancelled() || future.isDone()) ? false : true;
    }

    /* compiled from: CameraExecutor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006HÆ\u0003J)\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lio/fotoapparat/concurrent/CameraExecutor$Operation;", ExifInterface.GPS_DIRECTION_TRUE, "", "cancellable", "", ChatEnumAction.FUNCTION, "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;)V", "getCancellable", "()Z", "getFunction", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final /* data */ class Operation<T> {
        private final boolean cancellable;
        private final Function0<T> function;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Operation copy$default(Operation operation, boolean z, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                z = operation.cancellable;
            }
            if ((i & 2) != 0) {
                function0 = operation.function;
            }
            return operation.copy(z, function0);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getCancellable() {
            return this.cancellable;
        }

        public final Function0<T> component2() {
            return this.function;
        }

        public final Operation<T> copy(boolean cancellable, Function0<? extends T> function) {
            Intrinsics.checkParameterIsNotNull(function, "function");
            return new Operation<>(cancellable, function);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof Operation) {
                    Operation operation = (Operation) other;
                    if (!(this.cancellable == operation.cancellable) || !Intrinsics.areEqual(this.function, operation.function)) {
                    }
                }
                return false;
            }
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.cancellable;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            Function0<T> function0 = this.function;
            return i + (function0 != null ? function0.hashCode() : 0);
        }

        public String toString() {
            return "Operation(cancellable=" + this.cancellable + ", function=" + this.function + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Operation(boolean z, Function0<? extends T> function) {
            Intrinsics.checkParameterIsNotNull(function, "function");
            this.cancellable = z;
            this.function = function;
        }

        public /* synthetic */ Operation(boolean z, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, function0);
        }

        public final boolean getCancellable() {
            return this.cancellable;
        }

        public final Function0<T> getFunction() {
            return this.function;
        }
    }
}
