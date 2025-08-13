package com.realsil.sdk.core.base;

import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: classes3.dex */
public abstract class BaseThread<T> extends Thread {
    public volatile boolean a = false;
    public LinkedBlockingDeque<T> b = new LinkedBlockingDeque<>();

    public void addQueue(T t) {
        synchronized (this.b) {
            this.b.add(t);
        }
    }

    public void cancel(boolean z) {
        this.a = z;
    }

    public void clearQueue() {
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public boolean isCanceled() {
        return this.a;
    }

    public T peek() {
        T tPeek;
        synchronized (this.b) {
            tPeek = this.b.peek();
        }
        return tPeek;
    }

    public T poll() {
        T tPoll;
        synchronized (this.b) {
            tPoll = this.b.poll();
        }
        return tPoll;
    }

    public void push(T t) {
        synchronized (this.b) {
            this.b.push(t);
        }
    }

    public T take() {
        try {
            return this.b.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
