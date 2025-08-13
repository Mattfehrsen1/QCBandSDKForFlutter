package com.zhangke.websocket.request;

import java.util.Collection;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public class CollectionFrameDataRequest implements Request<Collection<Framedata>> {
    private Collection<Framedata> data;

    CollectionFrameDataRequest() {
    }

    @Override // com.zhangke.websocket.request.Request
    public void setRequestData(Collection<Framedata> collection) {
        this.data = collection;
    }

    @Override // com.zhangke.websocket.request.Request
    public Collection<Framedata> getRequestData() {
        return this.data;
    }

    @Override // com.zhangke.websocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        webSocketClient.sendFrame(this.data);
    }

    @Override // com.zhangke.websocket.request.Request
    public void release() {
        RequestFactory.releaseCollectionFrameRequest(this);
    }

    public String toString() {
        String str;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        if (this.data == null) {
            str = "null";
        } else {
            str = this.data.size() + " length";
        }
        objArr[1] = str;
        return String.format("[@CollectionFrameDataRequest%s,Collection<Framedata>:%s]", objArr);
    }
}
