package com.realsil.customer.core.base;

import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/base/BaseThread.class */
public abstract class BaseThread<T> extends Thread {
    public volatile boolean a = false;
    public final LinkedBlockingDeque<T> b = new LinkedBlockingDeque<>();

    public boolean isCanceled() {
        return this.a;
    }

    public void cancel(boolean z) {
        this.a = z;
    }

    public void addQueue(T t) {
        synchronized (this.b) {
            this.b.add(t);
        }
    }

    public void push(T t) {
        synchronized (this.b) {
            this.b.push(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.core.base.BaseThread] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
    public T poll() {
        T tPoll = (T) this;
        synchronized (tPoll.b) {
            tPoll = tPoll.b.poll();
        }
        return tPoll;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.core.base.BaseThread] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
    public T peek() {
        T tPeek = (T) this;
        synchronized (tPeek.b) {
            tPeek = tPeek.b.peek();
        }
        return tPeek;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object, java.lang.Throwable] */
    public T take() throws InterruptedException {
        T t;
        try {
            T tTake = this.b.take();
            t = tTake;
            return t;
        } catch (InterruptedException unused) {
            t.printStackTrace();
            return null;
        }
    }

    public void clearQueue() {
        synchronized (this.b) {
            this.b.clear();
        }
    }
}
