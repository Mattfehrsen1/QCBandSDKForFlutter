package com.zhangke.websocket.response;

import com.zhangke.websocket.dispatcher.IResponseDispatcher;
import com.zhangke.websocket.dispatcher.ResponseDelivery;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public class ByteBufferResponse implements Response<ByteBuffer> {
    private ByteBuffer data;

    ByteBufferResponse() {
    }

    @Override // com.zhangke.websocket.response.Response
    public ByteBuffer getResponseData() {
        return this.data;
    }

    @Override // com.zhangke.websocket.response.Response
    public void setResponseData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    @Override // com.zhangke.websocket.response.Response
    public void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        iResponseDispatcher.onMessage(this.data, responseDelivery);
        release();
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        ByteBuffer byteBuffer = this.data;
        objArr[1] = byteBuffer == null ? "null" : byteBuffer.toString();
        return String.format("[@ByteBufferResponse%s->ByteBuffer:%s]", objArr);
    }

    @Override // com.zhangke.websocket.response.Response
    public void release() {
        ResponseFactory.releaseByteBufferResponse(this);
    }
}
