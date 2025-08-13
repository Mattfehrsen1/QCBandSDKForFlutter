package com.zhangke.websocket.dispatcher;

import com.zhangke.websocket.SocketListener;

/* loaded from: classes4.dex */
public interface ResponseDelivery extends SocketListener {
    void addListener(SocketListener socketListener);

    void clear();

    boolean isEmpty();

    void removeListener(SocketListener socketListener);
}
