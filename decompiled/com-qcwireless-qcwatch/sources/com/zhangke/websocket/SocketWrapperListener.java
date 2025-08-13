package com.zhangke.websocket;

import com.zhangke.websocket.request.Request;
import com.zhangke.websocket.response.Response;

/* loaded from: classes4.dex */
public interface SocketWrapperListener {
    void onConnectFailed(Throwable th);

    void onConnected();

    void onDisconnect();

    void onMessage(Response response);

    void onSendDataError(Request request, int i, Throwable th);
}
