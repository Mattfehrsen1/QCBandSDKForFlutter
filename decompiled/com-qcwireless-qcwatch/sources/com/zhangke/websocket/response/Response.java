package com.zhangke.websocket.response;

import com.zhangke.websocket.dispatcher.IResponseDispatcher;
import com.zhangke.websocket.dispatcher.ResponseDelivery;

/* loaded from: classes4.dex */
public interface Response<T> {
    T getResponseData();

    void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery);

    void release();

    void setResponseData(T t);
}
