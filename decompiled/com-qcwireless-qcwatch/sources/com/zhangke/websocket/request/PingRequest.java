package com.zhangke.websocket.request;

import org.java_websocket.client.WebSocketClient;

/* loaded from: classes4.dex */
public class PingRequest implements Request<Object> {
    @Override // com.zhangke.websocket.request.Request
    public Object getRequestData() {
        return null;
    }

    @Override // com.zhangke.websocket.request.Request
    public void setRequestData(Object obj) {
    }

    PingRequest() {
    }

    @Override // com.zhangke.websocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.sendPing();
    }

    @Override // com.zhangke.websocket.request.Request
    public void release() {
        RequestFactory.releasePingRequest(this);
    }
}
