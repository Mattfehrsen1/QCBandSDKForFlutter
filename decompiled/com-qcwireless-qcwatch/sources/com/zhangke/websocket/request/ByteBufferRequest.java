package com.zhangke.websocket.request;

import java.nio.ByteBuffer;
import org.java_websocket.client.WebSocketClient;

/* loaded from: classes4.dex */
public class ByteBufferRequest implements Request<ByteBuffer> {
    private ByteBuffer data;

    ByteBufferRequest() {
    }

    @Override // com.zhangke.websocket.request.Request
    public void setRequestData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    @Override // com.zhangke.websocket.request.Request
    public ByteBuffer getRequestData() {
        return this.data;
    }

    @Override // com.zhangke.websocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.send(this.data);
    }

    @Override // com.zhangke.websocket.request.Request
    public void release() {
        RequestFactory.releaseByteBufferRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        ByteBuffer byteBuffer = this.data;
        objArr[1] = byteBuffer == null ? "null" : byteBuffer.toString();
        return String.format("[@ByteBufferRequest%s,ByteBuffer:%s]", objArr);
    }
}
