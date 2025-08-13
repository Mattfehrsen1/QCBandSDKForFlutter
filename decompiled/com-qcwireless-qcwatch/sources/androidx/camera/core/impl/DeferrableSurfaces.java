package androidx.camera.core.impl;

import android.view.Surface;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class DeferrableSurfaces {
    private DeferrableSurfaces() {
    }

    public static ListenableFuture<List<Surface>> surfaceListWithTimeout(Collection<DeferrableSurface> deferrableSurfaces, final boolean removeNullSurfaces, final long timeout, final Executor executor, final ScheduledExecutorService scheduledExecutorService) {
        final ArrayList arrayList = new ArrayList();
        Iterator<DeferrableSurface> it = deferrableSurfaces.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSurface());
        }
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.DeferrableSurfaces$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return DeferrableSurfaces.lambda$surfaceListWithTimeout$3(arrayList, scheduledExecutorService, executor, timeout, removeNullSurfaces, completer);
            }
        });
    }

    static /* synthetic */ Object lambda$surfaceListWithTimeout$3(List list, ScheduledExecutorService scheduledExecutorService, final Executor executor, final long j, final boolean z, final CallbackToFutureAdapter.Completer completer) throws Exception {
        final ListenableFuture listenableFutureSuccessfulAsList = Futures.successfulAsList(list);
        final ScheduledFuture<?> scheduledFutureSchedule = scheduledExecutorService.schedule(new Runnable() { // from class: androidx.camera.core.impl.DeferrableSurfaces$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                executor.execute(new Runnable() { // from class: androidx.camera.core.impl.DeferrableSurfaces$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeferrableSurfaces.lambda$surfaceListWithTimeout$0(listenableFuture, completer, j);
                    }
                });
            }
        }, j, TimeUnit.MILLISECONDS);
        completer.addCancellationListener(new Runnable() { // from class: androidx.camera.core.impl.DeferrableSurfaces$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                listenableFutureSuccessfulAsList.cancel(true);
            }
        }, executor);
        Futures.addCallback(listenableFutureSuccessfulAsList, new FutureCallback<List<Surface>>() { // from class: androidx.camera.core.impl.DeferrableSurfaces.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(List<Surface> result) {
                ArrayList arrayList = new ArrayList(result);
                if (z) {
                    arrayList.removeAll(Collections.singleton(null));
                }
                completer.set(arrayList);
                scheduledFutureSchedule.cancel(true);
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable t) {
                completer.set(Collections.unmodifiableList(Collections.emptyList()));
                scheduledFutureSchedule.cancel(true);
            }
        }, executor);
        return "surfaceList";
    }

    static /* synthetic */ void lambda$surfaceListWithTimeout$0(ListenableFuture listenableFuture, CallbackToFutureAdapter.Completer completer, long j) {
        if (listenableFuture.isDone()) {
            return;
        }
        completer.setException(new TimeoutException("Cannot complete surfaceList within " + j));
        listenableFuture.cancel(true);
    }

    public static boolean tryIncrementAll(List<DeferrableSurface> surfaceList) {
        try {
            incrementAll(surfaceList);
            return true;
        } catch (DeferrableSurface.SurfaceClosedException unused) {
            return false;
        }
    }

    public static void incrementAll(List<DeferrableSurface> surfaceList) throws DeferrableSurface.SurfaceClosedException {
        if (surfaceList.isEmpty()) {
            return;
        }
        int i = 0;
        do {
            try {
                surfaceList.get(i).incrementUseCount();
                i++;
            } catch (DeferrableSurface.SurfaceClosedException e) {
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    surfaceList.get(i2).decrementUseCount();
                }
                throw e;
            }
        } while (i < surfaceList.size());
    }

    public static void decrementAll(List<DeferrableSurface> surfaceList) {
        Iterator<DeferrableSurface> it = surfaceList.iterator();
        while (it.hasNext()) {
            it.next().decrementUseCount();
        }
    }
}
