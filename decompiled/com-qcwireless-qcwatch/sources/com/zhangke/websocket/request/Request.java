package com.zhangke.websocket.request;

import org.java_websocket.client.WebSocketClient;

/* loaded from: classes4.dex */
public interface Request<T> {
    T getRequestData();

    void release();

    void send(WebSocketClient webSocketClient);

    void setRequestData(T t);
}
