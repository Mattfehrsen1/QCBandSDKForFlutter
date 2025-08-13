package com.zhangke.websocket;

import com.zhangke.websocket.ReconnectManager;
import com.zhangke.websocket.util.LogUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes4.dex */
public class DefaultReconnectManager implements ReconnectManager {
    private static final String TAG = "WSDefaultRM";
    private ReconnectManager.OnConnectListener mOnDisconnectListener;
    private WebSocketManager mWebSocketManager;
    private final Object BLOCK = new Object();
    private volatile boolean needStopReconnect = false;
    private volatile boolean connected = false;
    private final ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
    private int reconnectCount = 1;
    private int finishCount = 1;
    private volatile boolean reconnecting = false;
    private volatile boolean destroyed = false;

    static /* synthetic */ int access$308(DefaultReconnectManager defaultReconnectManager) {
        int i = defaultReconnectManager.reconnectCount;
        defaultReconnectManager.reconnectCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(DefaultReconnectManager defaultReconnectManager) {
        int i = defaultReconnectManager.finishCount;
        defaultReconnectManager.finishCount = i + 1;
        return i;
    }

    public DefaultReconnectManager(WebSocketManager webSocketManager, ReconnectManager.OnConnectListener onConnectListener) {
        this.mWebSocketManager = webSocketManager;
        this.mOnDisconnectListener = onConnectListener;
    }

    @Override // com.zhangke.websocket.ReconnectManager
    public boolean reconnecting() {
        return this.reconnecting;
    }

    @Override // com.zhangke.websocket.ReconnectManager
    public void startReconnect() {
        if (this.reconnecting) {
            LogUtil.i(TAG, "Reconnecting, do not call again.");
            return;
        }
        if (this.destroyed) {
            LogUtil.e(TAG, "ReconnectManager is destroyed!!!");
            return;
        }
        this.needStopReconnect = false;
        this.reconnecting = true;
        try {
            this.singleThreadPool.execute(getReconnectRunnable());
        } catch (RejectedExecutionException e) {
            LogUtil.e(TAG, "线程队列已满，无法执行此次任务。", e);
            this.reconnecting = false;
        }
    }

    private Runnable getReconnectRunnable() {
        return new Runnable() { // from class: com.zhangke.websocket.DefaultReconnectManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (DefaultReconnectManager.this.destroyed || DefaultReconnectManager.this.needStopReconnect) {
                    DefaultReconnectManager.this.reconnecting = false;
                    return;
                }
                LogUtil.d(DefaultReconnectManager.TAG, "开始重连:" + DefaultReconnectManager.this.reconnectCount);
                DefaultReconnectManager.access$308(DefaultReconnectManager.this);
                DefaultReconnectManager.this.reconnecting = true;
                DefaultReconnectManager.this.connected = false;
                try {
                    int reconnectFrequency = DefaultReconnectManager.this.mWebSocketManager.getSetting().getReconnectFrequency();
                    int i = 0;
                    while (true) {
                        if (i >= reconnectFrequency) {
                            break;
                        }
                        i++;
                        LogUtil.i(DefaultReconnectManager.TAG, String.format("第%s次重连", Integer.valueOf(i)));
                        DefaultReconnectManager.this.mWebSocketManager.reconnectOnce();
                        synchronized (DefaultReconnectManager.this.BLOCK) {
                            try {
                                DefaultReconnectManager.this.BLOCK.wait(DefaultReconnectManager.this.mWebSocketManager.getSetting().getConnectTimeout());
                                if (!DefaultReconnectManager.this.connected) {
                                    if (DefaultReconnectManager.this.needStopReconnect) {
                                    }
                                } else {
                                    LogUtil.i(DefaultReconnectManager.TAG, "reconnectOnce success!");
                                    DefaultReconnectManager.this.mOnDisconnectListener.onConnected();
                                    return;
                                }
                            } catch (InterruptedException unused) {
                            } finally {
                            }
                        }
                    }
                } finally {
                    LogUtil.d(DefaultReconnectManager.TAG, "重连结束:" + DefaultReconnectManager.this.finishCount);
                    DefaultReconnectManager.access$808(DefaultReconnectManager.this);
                    DefaultReconnectManager.this.reconnecting = false;
                    LogUtil.i(DefaultReconnectManager.TAG, "reconnecting = false");
                }
                LogUtil.i(DefaultReconnectManager.TAG, "reconnectOnce failed!");
                DefaultReconnectManager.this.mOnDisconnectListener.onDisconnect();
            }
        };
    }

    @Override // com.zhangke.websocket.ReconnectManager
    public void stopReconnect() {
        this.needStopReconnect = true;
        ExecutorService executorService = this.singleThreadPool;
        if (executorService != null) {
            executorService.shutdownNow();
        }
    }

    @Override // com.zhangke.websocket.ReconnectManager
    public void onConnected() {
        this.connected = true;
        synchronized (this.BLOCK) {
            LogUtil.i(TAG, "onConnected()->BLOCK.notifyAll()");
            this.BLOCK.notifyAll();
        }
    }

    @Override // com.zhangke.websocket.ReconnectManager
    public void onConnectError(Throwable th) {
        this.connected = false;
        synchronized (this.BLOCK) {
            LogUtil.i(TAG, "onConnectError(Throwable)->BLOCK.notifyAll()");
            this.BLOCK.notifyAll();
        }
    }

    @Override // com.zhangke.websocket.ReconnectManager
    public void destroy() {
        this.destroyed = true;
        stopReconnect();
        this.mWebSocketManager = null;
    }
}
