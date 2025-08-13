package com.qcwireless.qcwatch.ui.base.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes3.dex */
public class WakeupThread extends Thread {
    private final Condition mCondition;
    private Lock mLock;
    private Queue<IDo> queue;

    public WakeupThread(String threadName, Queue<IDo> queue) {
        super(threadName);
        this.queue = queue;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mLock = reentrantLock;
        this.mCondition = reentrantLock.newCondition();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                if (!Thread.interrupted()) {
                    IDo iDoRemove = this.queue.remove();
                    if (iDoRemove == null) {
                        lockThread();
                    } else {
                        iDoRemove.iDo();
                        needWait(6L);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void needWait(long seconds) {
        if (seconds > 0) {
            this.mLock.lock();
            try {
                try {
                    this.mCondition.await(seconds, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                this.mLock.unlock();
            }
        }
    }

    public Lock getLock() {
        return this.mLock;
    }

    public void setLock(Lock lock) {
        this.mLock = lock;
    }

    public Condition getCondition() {
        return this.mCondition;
    }

    public void wakeUp() {
        this.mLock.lock();
        try {
            this.mCondition.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    private void lockThread() {
        Lock lock = this.mLock;
        if (lock == null || this.mCondition == null) {
            return;
        }
        lock.lock();
        try {
            try {
                this.mCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            this.mLock.unlock();
        }
    }
}
