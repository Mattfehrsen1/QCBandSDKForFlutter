package com.zhangke.websocket;

import com.zhangke.websocket.response.ErrorResponse;
import java.nio.ByteBuffer;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public abstract class SimpleListener implements SocketListener {
    private final String TAG = "SimpleListener";

    @Override // com.zhangke.websocket.SocketListener
    public void onConnectFailed(Throwable th) {
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onConnected() {
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onDisconnect() {
    }

    @Override // com.zhangke.websocket.SocketListener
    public <T> void onMessage(String str, T t) {
    }

    @Override // com.zhangke.websocket.SocketListener
    public <T> void onMessage(ByteBuffer byteBuffer, T t) {
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onPing(Framedata framedata) {
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onPong(Framedata framedata) {
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onSendDataError(ErrorResponse errorResponse) {
    }
}
