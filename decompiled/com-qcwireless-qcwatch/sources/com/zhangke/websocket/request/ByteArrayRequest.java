package com.zhangke.websocket.request;

import org.java_websocket.client.WebSocketClient;

/* loaded from: classes4.dex */
public class ByteArrayRequest implements Request<byte[]> {
    private byte[] data;

    ByteArrayRequest() {
    }

    @Override // com.zhangke.websocket.request.Request
    public void setRequestData(byte[] bArr) {
        this.data = bArr;
    }

    @Override // com.zhangke.websocket.request.Request
    public byte[] getRequestData() {
        return this.data;
    }

    @Override // com.zhangke.websocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.send(this.data);
    }

    @Override // com.zhangke.websocket.request.Request
    public void release() {
        RequestFactory.releaseByteArrayRequest(this);
    }

    public String toString() {
        String str;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        if (this.data == null) {
            str = "data:null";
        } else {
            str = "data.length:" + this.data.length;
        }
        objArr[1] = str;
        return String.format("[@ByteArrayRequest%s,%s]", objArr);
    }
}
