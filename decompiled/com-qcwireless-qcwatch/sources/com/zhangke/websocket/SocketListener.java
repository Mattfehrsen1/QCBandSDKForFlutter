package com.zhangke.websocket;

import com.zhangke.websocket.response.ErrorResponse;
import java.nio.ByteBuffer;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public interface SocketListener {
    void onConnectFailed(Throwable th);

    void onConnected();

    void onDisconnect();

    <T> void onMessage(String str, T t);

    <T> void onMessage(ByteBuffer byteBuffer, T t);

    void onPing(Framedata framedata);

    void onPong(Framedata framedata);

    void onSendDataError(ErrorResponse errorResponse);
}
