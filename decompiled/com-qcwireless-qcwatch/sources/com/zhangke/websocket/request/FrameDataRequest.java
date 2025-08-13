package com.zhangke.websocket.request;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public class FrameDataRequest implements Request<Framedata> {
    private Framedata framedata;

    FrameDataRequest() {
    }

    @Override // com.zhangke.websocket.request.Request
    public void setRequestData(Framedata framedata) {
        this.framedata = framedata;
    }

    @Override // com.zhangke.websocket.request.Request
    public Framedata getRequestData() {
        return this.framedata;
    }

    @Override // com.zhangke.websocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.sendFrame(this.framedata);
    }

    @Override // com.zhangke.websocket.request.Request
    public void release() {
        RequestFactory.releaseFrameDataRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        Framedata framedata = this.framedata;
        objArr[1] = framedata == null ? "null" : framedata.toString();
        return String.format("[@FrameDataRequest%s,Framedata:%s]", objArr);
    }
}
