package com.qcwireless.qcwatch.ui.base.thread;

import java.util.LinkedList;
import java.util.List;

/* loaded from: classes3.dex */
public class Queue<E> {
    private final Object mObject = new Object();
    private final LinkedList<E> mQueue = new LinkedList<>();

    public void addFirst(E object) {
        synchronized (this.mQueue) {
            this.mQueue.addFirst(object);
        }
        synchronized (this.mObject) {
            this.mObject.notifyAll();
        }
    }

    public void addTail(E object) {
        synchronized (this.mQueue) {
            this.mQueue.add(object);
        }
        synchronized (this.mObject) {
            this.mObject.notifyAll();
        }
    }

    public void addAllTail(List<E> list) {
        synchronized (this.mQueue) {
            this.mQueue.addAll(list);
        }
        synchronized (this.mObject) {
            this.mObject.notifyAll();
        }
    }

    public E get() {
        if (this.mQueue.size() == 0) {
            try {
                synchronized (this.mObject) {
                    this.mObject.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        synchronized (this.mQueue) {
            if (this.mQueue.size() <= 0) {
                return null;
            }
            return this.mQueue.getFirst();
        }
    }

    public E getNewNotWait() {
        synchronized (this.mQueue) {
            if (this.mQueue.size() <= 0) {
                return null;
            }
            return this.mQueue.getFirst();
        }
    }

    public boolean isEmpty() {
        boolean z;
        synchronized (this.mQueue) {
            z = this.mQueue.size() == 0;
        }
        return z;
    }

    public E getLast() {
        if (this.mQueue.size() == 0) {
            try {
                synchronized (this.mObject) {
                    this.mObject.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        synchronized (this.mQueue) {
            if (this.mQueue.size() <= 0) {
                return null;
            }
            return this.mQueue.getLast();
        }
    }

    public E remove() {
        if (this.mQueue.size() == 0) {
            return null;
        }
        synchronized (this.mQueue) {
            if (this.mQueue.size() <= 0) {
                return null;
            }
            return this.mQueue.removeFirst();
        }
    }

    public void remove(E object) {
        if (this.mQueue.size() != 0) {
            synchronized (this.mQueue) {
                if (this.mQueue.size() > 0) {
                    this.mQueue.remove(object);
                }
            }
        }
    }

    public boolean contains(E object) {
        if (this.mQueue.size() == 0) {
            return false;
        }
        synchronized (this.mQueue) {
            if (this.mQueue.size() <= 0) {
                return false;
            }
            return this.mQueue.contains(object);
        }
    }

    public void clear() {
        synchronized (this.mQueue) {
            this.mQueue.clear();
        }
    }

    public LinkedList<E> getAllTask() {
        return this.mQueue;
    }

    public int size() {
        return this.mQueue.size();
    }
}
