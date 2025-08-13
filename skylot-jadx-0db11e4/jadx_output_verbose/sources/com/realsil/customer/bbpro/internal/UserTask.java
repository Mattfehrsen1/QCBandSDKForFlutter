package com.realsil.customer.bbpro.internal;

import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.logger.ZLogger;
import java.util.Locale;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/UserTask.class */
public class UserTask implements Runnable {
    public boolean a;
    public boolean b;
    public int c;
    public UUID d;
    public boolean e;
    public boolean f;
    public boolean g;
    public final Object h;
    public byte i;

    public UserTask(int i) {
        this(i, UUID.randomUUID());
    }

    @Override // java.lang.Runnable
    public void run() {
        ZLogger.v(this.b, String.format("task:%s is running", getName()));
        this.e = false;
    }

    public int getOpcode() {
        return this.c;
    }

    public UUID getUuid() {
        return this.d;
    }

    public String getName() {
        return this.d.toString();
    }

    public boolean isLastAction() {
        return this.g;
    }

    public byte getTaskStatus() {
        return this.i;
    }

    public void startSubTask(boolean z) {
        this.f = true;
        this.g = z;
        ZLogger.v(this.b, String.format("task %s start", getName()));
    }

    public void stopSubTask() {
        this.f = false;
        ZLogger.v(this.b, String.format("task %s stop", getName()));
    }

    public void cancel() {
        this.e = true;
    }

    public void waitTaskComplete() {
        waitTaskComplete(5000L);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Throwable] */
    public void notiyTaskUpdate(byte b) {
        synchronized (this.h) {
            this.f = false;
            this.i = b;
            this.h.notifyAll();
            boolean z = this.b;
            Object[] objArr = new Object[1];
            objArr[0] = getName();
            ZLogger.v(z, String.format("task %s update", objArr));
        }
    }

    public boolean isProcessing() {
        return this.f;
    }

    public UserTask(int i, UUID uuid) {
        this.a = RtkCore.DEBUG;
        this.b = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = new Object();
        this.i = (byte) 0;
        this.c = i;
        this.d = uuid;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.realsil.customer.bbpro.internal.UserTask] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public void waitTaskComplete(long j) {
        ?? r0;
        try {
            r0 = this;
            Object obj = r0.h;
            synchronized (obj) {
                if (r0.f) {
                    boolean z = this.b;
                    Locale locale = Locale.US;
                    Object[] objArr = new Object[2];
                    objArr[0] = getName();
                    objArr[1] = Long.valueOf(j);
                    ZLogger.v(z, String.format(locale, "task %s wait %d ms", objArr));
                    this.h.wait(j);
                }
                r0 = obj;
            }
        } catch (InterruptedException unused) {
            ZLogger.w(r0.getMessage());
        }
    }
}
