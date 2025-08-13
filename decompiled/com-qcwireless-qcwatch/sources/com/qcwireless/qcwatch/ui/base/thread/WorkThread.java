package com.qcwireless.qcwatch.ui.base.thread;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.work.PeriodicWorkRequest;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes3.dex */
public class WorkThread extends Thread {
    private static final int SLEEP_INTERVAL_MAX = 60;
    private static final int SLEEP_INTERVAL_MIN = 30;
    private long lastConnectTime;
    private Condition mCondition;
    private Lock mLock;
    private AtomicInteger sleepTime;

    public WorkThread(String name, Lock lock, Condition condition) {
        super(name);
        this.sleepTime = new AtomicInteger(30);
        this.mLock = lock;
        this.mCondition = condition;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                if (!BluetoothUtils.isEnabledBluetooth(QJavaApplication.getInstance().getApplication()) || TextUtils.isEmpty(PreUtil.getSharedString(PreUtil.Action_Device_Address, null))) {
                    needLock();
                }
                if (BleOperateManager.getInstance().isConnected()) {
                    this.sleepTime.getAndSet(30);
                    this.lastConnectTime = 0L;
                    needLock();
                } else if (this.sleepTime.get() <= 60) {
                    SystemClock.sleep(this.sleepTime.get() * 1000);
                    if (BleOperateManager.getInstance().isConnected()) {
                        this.sleepTime.getAndSet(30);
                        this.lastConnectTime = 0L;
                        needLock();
                    }
                    this.sleepTime.incrementAndGet();
                    BleOperateManager.getInstance().setReConnectMac(PreUtil.getSharedString(PreUtil.Action_Device_Address, null));
                    BleOperateManager.getInstance().connectWithScan(PreUtil.getSharedString(PreUtil.Action_Device_Address, null));
                } else {
                    long unixTimestamp = new DateUtil().getUnixTimestamp();
                    StringBuilder sb = new StringBuilder();
                    sb.append("5分钟");
                    sb.append(this.lastConnectTime <= unixTimestamp);
                    sb.append(this.lastConnectTime);
                    sb.append("---");
                    sb.append(unixTimestamp);
                    XLog.i(sb.toString());
                    if (this.lastConnectTime <= unixTimestamp) {
                        SystemClock.sleep(PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
                        this.lastConnectTime = unixTimestamp + 300;
                        BleOperateManager.getInstance().setReConnectMac(PreUtil.getSharedString(PreUtil.Action_Device_Address, null));
                        BleOperateManager.getInstance().connectWithScan(PreUtil.getSharedString(PreUtil.Action_Device_Address, null));
                    }
                    needLock();
                }
            } catch (Exception e) {
                e.printStackTrace();
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

    private void needLock() {
        if (this.mCondition == null) {
            return;
        }
        this.mLock.lock();
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

    public void wakeUp() {
        if (BleOperateManager.getInstance().isConnected()) {
            this.sleepTime.getAndSet(30);
            return;
        }
        try {
            try {
                this.mLock.lock();
                this.mCondition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mLock.unlock();
            QJavaApplication.trySetupNotifyService(QJavaApplication.getInstance().getApplication());
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
    }

    public void wakeUpNoSleep() {
        try {
            this.sleepTime.getAndSet(0);
            this.mLock.lock();
            this.mCondition.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    public void setSleepTimeMin() {
        this.sleepTime.getAndSet(30);
    }

    public void setLastConnectTime(long lastConnectTime) {
        this.lastConnectTime = lastConnectTime;
    }
}
