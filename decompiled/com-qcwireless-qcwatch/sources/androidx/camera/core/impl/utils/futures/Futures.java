package androidx.camera.core.impl.utils.futures;

import androidx.arch.core.util.Function;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.ImmediateFuture;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/* loaded from: classes.dex */
public final class Futures {
    private static final Function<?, ?> IDENTITY_FUNCTION = new Function<Object, Object>() { // from class: androidx.camera.core.impl.utils.futures.Futures.2
        @Override // androidx.arch.core.util.Function
        public Object apply(Object input) {
            return input;
        }
    };

    public static <V> ListenableFuture<V> immediateFuture(V value) {
        if (value == null) {
            return ImmediateFuture.nullFuture();
        }
        return new ImmediateFuture.ImmediateSuccessfulFuture(value);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable cause) {
        return new ImmediateFuture.ImmediateFailedFuture(cause);
    }

    public static <V> ScheduledFuture<V> immediateFailedScheduledFuture(Throwable cause) {
        return new ImmediateFuture.ImmediateFailedScheduledFuture(cause);
    }

    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function, Executor executor) {
        ChainingListenableFuture chainingListenableFuture = new ChainingListenableFuture(function, input);
        input.addListener(chainingListenableFuture, executor);
        return chainingListenableFuture;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, final Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        return transformAsync(input, new AsyncFunction<I, O>() { // from class: androidx.camera.core.impl.utils.futures.Futures.1
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public ListenableFuture<O> apply(I input2) {
                return Futures.immediateFuture(function.apply(input2));
            }
        }, executor);
    }

    public static <V> void propagate(ListenableFuture<V> input, final CallbackToFutureAdapter.Completer<V> completer) {
        propagateTransform(input, IDENTITY_FUNCTION, completer, CameraXExecutors.directExecutor());
    }

    public static <I, O> void propagateTransform(final ListenableFuture<I> input, final Function<? super I, ? extends O> function, final CallbackToFutureAdapter.Completer<O> completer, Executor executor) {
        propagateTransform(true, input, function, completer, executor);
    }

    private static <I, O> void propagateTransform(boolean propagateCancellation, final ListenableFuture<I> input, final Function<? super I, ? extends O> function, final CallbackToFutureAdapter.Completer<O> completer, Executor executor) {
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(completer);
        Preconditions.checkNotNull(executor);
        addCallback(input, new FutureCallback<I>() { // from class: androidx.camera.core.impl.utils.futures.Futures.3
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(I result) {
                try {
                    completer.set(function.apply(result));
                } catch (Throwable th) {
                    completer.setException(th);
                }
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable t) {
                completer.setException(t);
            }
        }, executor);
        if (propagateCancellation) {
            completer.addCancellationListener(new Runnable() { // from class: androidx.camera.core.impl.utils.futures.Futures.4
                @Override // java.lang.Runnable
                public void run() {
                    input.cancel(true);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(final ListenableFuture<V> future) {
        Preconditions.checkNotNull(future);
        return future.isDone() ? future : CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.utils.futures.Futures$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return Futures.lambda$nonCancellationPropagating$0(future, completer);
            }
        });
    }

    static /* synthetic */ Object lambda$nonCancellationPropagating$0(ListenableFuture listenableFuture, CallbackToFutureAdapter.Completer completer) throws Exception {
        propagateTransform(false, listenableFuture, IDENTITY_FUNCTION, completer, CameraXExecutors.directExecutor());
        return "nonCancellationPropagating[" + listenableFuture + "]";
    }

    public static <V> ListenableFuture<List<V>> successfulAsList(Collection<? extends ListenableFuture<? extends V>> futures) {
        return new ListFuture(new ArrayList(futures), false, CameraXExecutors.directExecutor());
    }

    public static <V> ListenableFuture<List<V>> allAsList(Collection<? extends ListenableFuture<? extends V>> futures) {
        return new ListFuture(new ArrayList(futures), true, CameraXExecutors.directExecutor());
    }

    public static <V> void addCallback(final ListenableFuture<V> future, final FutureCallback<? super V> callback, Executor executor) {
        Preconditions.checkNotNull(callback);
        future.addListener(new CallbackListener(future, callback), executor);
    }

    private static final class CallbackListener<V> implements Runnable {
        final FutureCallback<? super V> mCallback;
        final Future<V> mFuture;

        CallbackListener(Future<V> future, FutureCallback<? super V> callback) {
            this.mFuture = future;
            this.mCallback = callback;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.mCallback.onSuccess(Futures.getDone(this.mFuture));
            } catch (Error e) {
                e = e;
                this.mCallback.onFailure(e);
            } catch (RuntimeException e2) {
                e = e2;
                this.mCallback.onFailure(e);
            } catch (ExecutionException e3) {
                this.mCallback.onFailure(e3.getCause());
            }
        }

        public String toString() {
            return getClass().getSimpleName() + "," + this.mCallback;
        }
    }

    public static <V> V getDone(Future<V> future) throws ExecutionException {
        Preconditions.checkState(future.isDone(), "Future was expected to be done, " + future);
        return (V) getUninterruptibly(future);
    }

    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    private Futures() {
    }
}
