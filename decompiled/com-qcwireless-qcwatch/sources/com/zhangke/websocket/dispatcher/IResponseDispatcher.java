package com.zhangke.websocket.dispatcher;

import com.zhangke.websocket.response.ErrorResponse;
import java.nio.ByteBuffer;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public interface IResponseDispatcher {
    void onConnectFailed(Throwable th, ResponseDelivery responseDelivery);

    void onConnected(ResponseDelivery responseDelivery);

    void onDisconnect(ResponseDelivery responseDelivery);

    void onMessage(String str, ResponseDelivery responseDelivery);

    void onMessage(ByteBuffer byteBuffer, ResponseDelivery responseDelivery);

    void onPing(Framedata framedata, ResponseDelivery responseDelivery);

    void onPong(Framedata framedata, ResponseDelivery responseDelivery);

    void onSendDataError(ErrorResponse errorResponse, ResponseDelivery responseDelivery);
}
