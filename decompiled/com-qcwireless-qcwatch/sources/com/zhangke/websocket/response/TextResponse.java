package com.zhangke.websocket.response;

import android.text.TextUtils;
import com.zhangke.websocket.dispatcher.IResponseDispatcher;
import com.zhangke.websocket.dispatcher.ResponseDelivery;

/* loaded from: classes4.dex */
public class TextResponse implements Response<String> {
    private String responseText;

    TextResponse() {
    }

    @Override // com.zhangke.websocket.response.Response
    public String getResponseData() {
        return this.responseText;
    }

    @Override // com.zhangke.websocket.response.Response
    public void setResponseData(String str) {
        this.responseText = str;
    }

    @Override // com.zhangke.websocket.response.Response
    public void onResponse(IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        iResponseDispatcher.onMessage(this.responseText, responseDelivery);
        release();
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        objArr[1] = TextUtils.isEmpty(this.responseText) ? "null" : this.responseText;
        return String.format("[@TextResponse%s->responseText:%s]", objArr);
    }

    @Override // com.zhangke.websocket.response.Response
    public void release() {
        ResponseFactory.releaseTextResponse(this);
    }
}
