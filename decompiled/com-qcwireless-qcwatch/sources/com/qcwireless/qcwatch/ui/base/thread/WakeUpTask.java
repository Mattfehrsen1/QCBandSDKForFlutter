package com.qcwireless.qcwatch.ui.base.thread;

/* loaded from: classes3.dex */
public class WakeUpTask implements IDo {
    private boolean noWait;
    private WorkThread workThread;

    public WakeUpTask(WorkThread workThread) {
        this.workThread = workThread;
    }

    @Override // com.qcwireless.qcwatch.ui.base.thread.IDo
    public void iDo() {
        if (this.noWait) {
            this.workThread.wakeUpNoSleep();
        } else {
            this.workThread.wakeUp();
        }
    }

    public boolean isNoWait() {
        return this.noWait;
    }

    public void setNoWait(boolean noWait) {
        this.noWait = noWait;
    }
}
