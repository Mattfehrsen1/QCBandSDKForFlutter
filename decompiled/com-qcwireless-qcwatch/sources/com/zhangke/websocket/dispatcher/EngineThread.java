package com.zhangke.websocket.dispatcher;

import android.os.Process;
import com.zhangke.websocket.dispatcher.ResponseProcessEngine;
import com.zhangke.websocket.util.LogUtil;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class EngineThread extends Thread {
    private ExecutorService executorService;
    private boolean stop;
    private String TAG = "WSEngineThread";
    private ArrayBlockingQueue<ResponseProcessEngine.EngineEntity> jobQueue = new ArrayBlockingQueue<>(10);

    @Override // java.lang.Thread
    public synchronized void start() {
        this.stop = false;
        super.start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws InterruptedException, SecurityException, IllegalArgumentException {
        super.run();
        Process.setThreadPriority(10);
        while (!this.stop) {
            try {
                ResponseProcessEngine.EngineEntity engineEntityTake = this.jobQueue.take();
                if (engineEntityTake.isError) {
                    engineEntityTake.dispatcher.onSendDataError(engineEntityTake.errorResponse, engineEntityTake.delivery);
                } else {
                    engineEntityTake.response.onResponse(engineEntityTake.dispatcher, engineEntityTake.delivery);
                }
                ResponseProcessEngine.EngineEntity.release(engineEntityTake);
            } catch (InterruptedException unused) {
                if (this.stop) {
                    return;
                }
            } catch (Exception e) {
                LogUtil.e(this.TAG, "run()->Exception", e);
            }
        }
    }

    void add(final ResponseProcessEngine.EngineEntity engineEntity) {
        if (this.jobQueue.offer(engineEntity)) {
            return;
        }
        LogUtil.e(this.TAG, "Offer response to Engine failed!start an thread to put.");
        if (this.executorService == null) {
            this.executorService = Executors.newCachedThreadPool();
        }
        this.executorService.execute(new Runnable() { // from class: com.zhangke.websocket.dispatcher.EngineThread.1
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                if (EngineThread.this.stop) {
                    return;
                }
                try {
                    EngineThread.this.jobQueue.put(engineEntity);
                } catch (Exception e) {
                    if (EngineThread.this.stop) {
                        LogUtil.e(EngineThread.this.TAG, "put response failed!", e);
                    } else {
                        EngineThread.this.interrupt();
                    }
                }
            }
        });
    }

    void quit() {
        this.stop = true;
        this.jobQueue.clear();
        interrupt();
    }
}
