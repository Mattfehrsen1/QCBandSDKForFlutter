package com.oudmon.ble.base.util;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/ThreadUtils.class */
public class ThreadUtils {
    private static final ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();
    private static final HashMap<TimeTask, ScheduledFuture> mRunnableCache = new HashMap<>();
    private static final Object mLock = new Object();

    public static void postDelay(TimeTask runnable, long delay) {
        synchronized (mLock) {
            cancel(runnable);
            ScheduledFuture<?> future = mExecutor.schedule(runnable, delay, TimeUnit.MILLISECONDS);
            mRunnableCache.put(runnable, future);
        }
    }

    public static void cancel(TimeTask runnable) {
        cancel(runnable, false);
    }

    public static void cancel(TimeTask runnable, boolean mayInterruptIfRunning) {
        synchronized (mLock) {
            ScheduledFuture future = mRunnableCache.get(runnable);
            if (future != null) {
                future.cancel(mayInterruptIfRunning);
                mRunnableCache.remove(runnable);
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/ThreadUtils$TimeTask.class */
    public static abstract class TimeTask implements Runnable {
        protected abstract void task();

        @Override // java.lang.Runnable
        public void run() {
            task();
            ThreadUtils.mRunnableCache.remove(this);
        }
    }
}
