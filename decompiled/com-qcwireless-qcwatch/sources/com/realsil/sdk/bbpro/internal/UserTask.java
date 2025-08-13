package com.realsil.sdk.bbpro.internal;

import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes3.dex */
public class UserTask implements Runnable {
    public boolean a;
    public boolean b;
    public int c;
    public UUID d;
    public boolean e;
    public boolean f;
    public Object g;
    public byte h;

    public UserTask(int i) {
        this(i, UUID.randomUUID());
    }

    public String getName() {
        return this.d.toString();
    }

    public int getOpcode() {
        return this.c;
    }

    public byte getTaskStatus() {
        return this.h;
    }

    public UUID getUuid() {
        return this.d;
    }

    public boolean isLastAction() {
        return this.f;
    }

    public boolean isProcessing() {
        return this.e;
    }

    public void notiyTaskUpdate(byte b) {
        synchronized (this.g) {
            this.e = false;
            this.h = b;
            this.g.notifyAll();
            ZLogger.v(this.b, String.format("task %s update", getName()));
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        ZLogger.v(this.b, String.format("task:%s is running", getName()));
    }

    public void startSubTask(boolean z) {
        this.e = true;
        this.f = z;
        ZLogger.v(this.b, String.format("task %s start", getName()));
    }

    public void stopSubTask() {
        this.e = false;
        ZLogger.v(this.b, String.format("task %s stop", getName()));
    }

    public void waitTaskComplete() {
        waitTaskComplete(5000L);
    }

    public UserTask(int i, UUID uuid) {
        this.a = RtkCore.DEBUG;
        this.b = false;
        this.e = false;
        this.f = false;
        this.g = new Object();
        this.h = (byte) 0;
        this.c = i;
        this.d = uuid;
    }

    public void waitTaskComplete(long j) {
        try {
            synchronized (this.g) {
                if (this.e) {
                    ZLogger.v(this.b, String.format(Locale.US, "task %s wait %d ms", getName(), Long.valueOf(j)));
                    this.g.wait(j);
                }
            }
        } catch (InterruptedException e) {
            ZLogger.w(e.getMessage());
        }
    }
}
