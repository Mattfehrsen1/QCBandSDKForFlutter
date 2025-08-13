package com.oudmon.ble.base.bluetooth.queue;

import android.util.Log;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.Constants;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/queue/BleThreadManager.class */
public class BleThreadManager {
    private static BleThreadManager instance;
    private BlockingDeque<BleDataBean> queue = new LinkedBlockingDeque(20);
    private BleConsumer bleConsumer;

    private BleThreadManager() {
        if (this.bleConsumer == null) {
            this.bleConsumer = new BleConsumer("bleConsumer-" + new Random().nextInt(), this.queue);
            this.bleConsumer.start();
        }
    }

    public static BleThreadManager getInstance() {
        BleThreadManager bleThreadManager;
        if (instance == null) {
            synchronized (BleThreadManager.class) {
                if (instance == null) {
                    instance = new BleThreadManager();
                }
                bleThreadManager = instance;
            }
            return bleThreadManager;
        }
        return instance;
    }

    public void addData(BleDataBean bean) throws InterruptedException {
        try {
            if (!BleOperateManager.getInstance().isConnected()) {
                Log.i(Constants.TAG, "设备断开");
            } else {
                this.queue.putLast(bean);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clean() {
        this.queue.clear();
    }
}
