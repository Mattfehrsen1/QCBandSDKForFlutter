package com.oudmon.ble.base.bluetooth.queue;

import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.file.FileHandle;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/queue/BleConsumer.class */
public class BleConsumer extends Thread {
    BlockingQueue<BleDataBean> blockingQueue;

    public BleConsumer(String name, BlockingQueue<BleDataBean> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws InterruptedException {
        while (true) {
            try {
                BleDataBean bean = this.blockingQueue.take();
                int mPackageLength = bean.getSubLength();
                for (int index = 0; index * mPackageLength < bean.getData().length; index++) {
                    BleOperateManager.getInstance().execute(FileHandle.getInstance().getWriteRequest(Arrays.copyOfRange(bean.getData(), index * mPackageLength, (index * mPackageLength) + Math.min(mPackageLength, bean.getData().length - (index * mPackageLength)))));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
