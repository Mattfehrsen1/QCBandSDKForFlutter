package androidx.camera.core.impl;

import android.util.Log;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class DeferrableSurface {
    private CallbackToFutureAdapter.Completer<Void> mTerminationCompleter;
    private final ListenableFuture<Void> mTerminationFuture;
    private static final String TAG = "DeferrableSurface";
    private static final boolean DEBUG = Logger.isDebugEnabled(TAG);
    private static final AtomicInteger USED_COUNT = new AtomicInteger(0);
    private static final AtomicInteger TOTAL_COUNT = new AtomicInteger(0);
    private final Object mLock = new Object();
    private int mUseCount = 0;
    private boolean mClosed = false;

    protected abstract ListenableFuture<Surface> provideSurface();

    public static final class SurfaceUnavailableException extends Exception {
        public SurfaceUnavailableException(String message) {
            super(message);
        }
    }

    public static final class SurfaceClosedException extends Exception {
        DeferrableSurface mDeferrableSurface;

        public SurfaceClosedException(String s, DeferrableSurface surface) {
            super(s);
            this.mDeferrableSurface = surface;
        }

        public DeferrableSurface getDeferrableSurface() {
            return this.mDeferrableSurface;
        }
    }

    public DeferrableSurface() {
        ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.DeferrableSurface$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m110lambda$new$0$androidxcameracoreimplDeferrableSurface(completer);
            }
        });
        this.mTerminationFuture = future;
        if (Logger.isDebugEnabled(TAG)) {
            printGlobalDebugCounts("Surface created", TOTAL_COUNT.incrementAndGet(), USED_COUNT.get());
            final String stackTraceString = Log.getStackTraceString(new Exception());
            future.addListener(new Runnable() { // from class: androidx.camera.core.impl.DeferrableSurface$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m111lambda$new$1$androidxcameracoreimplDeferrableSurface(stackTraceString);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    /* renamed from: lambda$new$0$androidx-camera-core-impl-DeferrableSurface, reason: not valid java name */
    public /* synthetic */ Object m110lambda$new$0$androidxcameracoreimplDeferrableSurface(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            this.mTerminationCompleter = completer;
        }
        return "DeferrableSurface-termination(" + this + ")";
    }

    /* renamed from: lambda$new$1$androidx-camera-core-impl-DeferrableSurface, reason: not valid java name */
    public /* synthetic */ void m111lambda$new$1$androidxcameracoreimplDeferrableSurface(String str) {
        try {
            this.mTerminationFuture.get();
            printGlobalDebugCounts("Surface terminated", TOTAL_COUNT.decrementAndGet(), USED_COUNT.get());
        } catch (Exception e) {
            Logger.e(TAG, "Unexpected surface termination for " + this + "\nStack Trace:\n" + str);
            synchronized (this.mLock) {
                throw new IllegalArgumentException(String.format("DeferrableSurface %s [closed: %b, use_count: %s] terminated with unexpected exception.", this, Boolean.valueOf(this.mClosed), Integer.valueOf(this.mUseCount)), e);
            }
        }
    }

    private void printGlobalDebugCounts(String prefix, int totalCount, int useCount) {
        if (!DEBUG && Logger.isDebugEnabled(TAG)) {
            Logger.d(TAG, "DeferrableSurface usage statistics may be inaccurate since debug logging was not enabled at static initialization time. App restart may be required to enable accurate usage statistics.");
        }
        Logger.d(TAG, prefix + "[total_surfaces=" + totalCount + ", used_surfaces=" + useCount + "](" + this + "}");
    }

    public final ListenableFuture<Surface> getSurface() {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return Futures.immediateFailedFuture(new SurfaceClosedException("DeferrableSurface already closed.", this));
            }
            return provideSurface();
        }
    }

    public ListenableFuture<Void> getTerminationFuture() {
        return Futures.nonCancellationPropagating(this.mTerminationFuture);
    }

    public void incrementUseCount() throws SurfaceClosedException {
        synchronized (this.mLock) {
            int i = this.mUseCount;
            if (i == 0 && this.mClosed) {
                throw new SurfaceClosedException("Cannot begin use on a closed surface.", this);
            }
            this.mUseCount = i + 1;
            if (Logger.isDebugEnabled(TAG)) {
                if (this.mUseCount == 1) {
                    printGlobalDebugCounts("New surface in use", TOTAL_COUNT.get(), USED_COUNT.incrementAndGet());
                }
                Logger.d(TAG, "use count+1, useCount=" + this.mUseCount + " " + this);
            }
        }
    }

    public final void close() {
        CallbackToFutureAdapter.Completer<Void> completer;
        synchronized (this.mLock) {
            if (this.mClosed) {
                completer = null;
            } else {
                this.mClosed = true;
                if (this.mUseCount == 0) {
                    completer = this.mTerminationCompleter;
                    this.mTerminationCompleter = null;
                } else {
                    completer = null;
                }
                if (Logger.isDebugEnabled(TAG)) {
                    Logger.d(TAG, "surface closed,  useCount=" + this.mUseCount + " closed=true " + this);
                }
            }
        }
        if (completer != null) {
            completer.set(null);
        }
    }

    public void decrementUseCount() {
        CallbackToFutureAdapter.Completer<Void> completer;
        synchronized (this.mLock) {
            int i = this.mUseCount;
            if (i == 0) {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
            int i2 = i - 1;
            this.mUseCount = i2;
            if (i2 == 0 && this.mClosed) {
                completer = this.mTerminationCompleter;
                this.mTerminationCompleter = null;
            } else {
                completer = null;
            }
            if (Logger.isDebugEnabled(TAG)) {
                Logger.d(TAG, "use count-1,  useCount=" + this.mUseCount + " closed=" + this.mClosed + " " + this);
                if (this.mUseCount == 0) {
                    printGlobalDebugCounts("Surface no longer in use", TOTAL_COUNT.get(), USED_COUNT.decrementAndGet());
                }
            }
        }
        if (completer != null) {
            completer.set(null);
        }
    }

    public int getUseCount() {
        int i;
        synchronized (this.mLock) {
            i = this.mUseCount;
        }
        return i;
    }
}
