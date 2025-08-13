package com.zhangke.websocket.response;

import com.zhangke.websocket.dispatcher.IResponseDispatcher;
import com.zhangke.websocket.dispatcher.ResponseDelivery;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public class PongResponse implements Response<Framedata> {
    private Framedata framedata;

    PongResponse() {
    }

    @Override // com.zhangke.websocket.response.Response
    public Framedata getResponseData() {
        return this.framedata;
    }

    @Override // com.zhangke.websocket.response.Response
    public void setResponseData(Framedata framedata) {
        this.framedata = framedata;
    }

    @Override // com.zhangke.websocket.response.Response
    public void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        iResponseDispatcher.onPong(this.framedata, responseDelivery);
    }

    @Override // com.zhangke.websocket.response.Response
    public void release() {
        this.framedata = null;
        ResponseFactory.releasePongResponse(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        Framedata framedata = this.framedata;
        objArr[1] = framedata == null ? "null" : framedata.toString();
        return String.format("[@PongResponse%s->Framedata:%s]", objArr);
    }
}
