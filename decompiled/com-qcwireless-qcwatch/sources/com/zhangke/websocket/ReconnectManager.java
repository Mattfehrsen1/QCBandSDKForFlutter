package com.zhangke.websocket;

/* loaded from: classes4.dex */
public interface ReconnectManager {

    public interface OnConnectListener {
        void onConnected();

        void onDisconnect();
    }

    void destroy();

    void onConnectError(Throwable th);

    void onConnected();

    boolean reconnecting();

    void startReconnect();

    void stopReconnect();
}
