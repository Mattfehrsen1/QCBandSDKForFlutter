package com.oudmon.ble.base.util;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class ThreadUtils {
    private static final ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();
    private static final HashMap<TimeTask, ScheduledFuture> mRunnableCache = new HashMap<>();
    private static final Object mLock = new Object();

    public static void postDelay(TimeTask timeTask, long j) {
        synchronized (mLock) {
            cancel(timeTask);
            mRunnableCache.put(timeTask, mExecutor.schedule(timeTask, j, TimeUnit.MILLISECONDS));
        }
    }

    public static void cancel(TimeTask timeTask) {
        cancel(timeTask, false);
    }

    public static void cancel(TimeTask timeTask, boolean z) {
        synchronized (mLock) {
            HashMap<TimeTask, ScheduledFuture> map = mRunnableCache;
            ScheduledFuture scheduledFuture = map.get(timeTask);
            if (scheduledFuture != null) {
                scheduledFuture.cancel(z);
                map.remove(timeTask);
            }
        }
    }

    public static abstract class TimeTask implements Runnable {
        protected abstract void task();

        @Override // java.lang.Runnable
        public void run() {
            task();
            ThreadUtils.mRunnableCache.remove(this);
        }
    }
}
