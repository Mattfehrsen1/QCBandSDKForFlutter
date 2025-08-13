package com.zhangke.websocket.dispatcher;

import com.zhangke.websocket.response.ErrorResponse;
import java.nio.ByteBuffer;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public class DefaultResponseDispatcher implements IResponseDispatcher {
    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onConnected(ResponseDelivery responseDelivery) {
        responseDelivery.onConnected();
    }

    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onConnectFailed(Throwable th, ResponseDelivery responseDelivery) {
        responseDelivery.onConnectFailed(th);
    }

    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onDisconnect(ResponseDelivery responseDelivery) {
        responseDelivery.onDisconnect();
    }

    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onMessage(String str, ResponseDelivery responseDelivery) {
        responseDelivery.onMessage(str, (String) null);
    }

    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onMessage(ByteBuffer byteBuffer, ResponseDelivery responseDelivery) {
        responseDelivery.onMessage(byteBuffer, (ByteBuffer) null);
    }

    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onPing(Framedata framedata, ResponseDelivery responseDelivery) {
        responseDelivery.onPing(framedata);
    }

    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onPong(Framedata framedata, ResponseDelivery responseDelivery) {
        responseDelivery.onPong(framedata);
    }

    @Override // com.zhangke.websocket.dispatcher.IResponseDispatcher
    public void onSendDataError(ErrorResponse errorResponse, ResponseDelivery responseDelivery) {
        responseDelivery.onSendDataError(errorResponse);
    }
}
