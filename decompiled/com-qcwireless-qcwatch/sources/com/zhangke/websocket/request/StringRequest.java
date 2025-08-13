package com.zhangke.websocket.request;

import android.text.TextUtils;
import org.java_websocket.client.WebSocketClient;

/* loaded from: classes4.dex */
public class StringRequest implements Request<String> {
    private String requestText;

    StringRequest() {
    }

    @Override // com.zhangke.websocket.request.Request
    public void setRequestData(String str) {
        this.requestText = str;
    }

    @Override // com.zhangke.websocket.request.Request
    public String getRequestData() {
        return this.requestText;
    }

    @Override // com.zhangke.websocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.send(this.requestText);
    }

    @Override // com.zhangke.websocket.request.Request
    public void release() {
        RequestFactory.releaseStringRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        objArr[1] = TextUtils.isEmpty(this.requestText) ? "null" : this.requestText;
        return String.format("@StringRequest%s,requestText:%s", objArr);
    }
}
