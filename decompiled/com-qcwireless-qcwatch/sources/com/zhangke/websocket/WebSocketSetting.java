package com.zhangke.websocket;

import com.zhangke.websocket.dispatcher.DefaultResponseDispatcher;
import com.zhangke.websocket.dispatcher.IResponseDispatcher;
import com.zhangke.websocket.dispatcher.ResponseDelivery;
import java.net.Proxy;
import java.util.Map;
import org.java_websocket.drafts.Draft;

/* loaded from: classes4.dex */
public class WebSocketSetting {
    private String connectUrl;
    private Draft draft;
    private Map<String, String> httpHeaders;
    private Proxy mProxy;
    private boolean processDataOnBackground;
    private ResponseDelivery responseDelivery;
    private IResponseDispatcher responseProcessDispatcher;
    private boolean reconnectWithNetworkChanged = true;
    private int connectionLostTimeout = 60;
    private int connectTimeout = 0;
    private int reconnectFrequency = 10;

    public String getConnectUrl() {
        return this.connectUrl;
    }

    public void setConnectUrl(String str) {
        this.connectUrl = str;
    }

    public IResponseDispatcher getResponseDispatcher() {
        if (this.responseProcessDispatcher == null) {
            this.responseProcessDispatcher = new DefaultResponseDispatcher();
        }
        return this.responseProcessDispatcher;
    }

    public void setResponseProcessDispatcher(IResponseDispatcher iResponseDispatcher) {
        this.responseProcessDispatcher = iResponseDispatcher;
    }

    public boolean reconnectWithNetworkChanged() {
        return this.reconnectWithNetworkChanged;
    }

    public void setReconnectWithNetworkChanged(boolean z) {
        this.reconnectWithNetworkChanged = z;
    }

    public int getConnectionLostTimeout() {
        return this.connectionLostTimeout;
    }

    public void setConnectionLostTimeout(int i) {
        this.connectionLostTimeout = i;
    }

    public Proxy getProxy() {
        return this.mProxy;
    }

    public void setProxy(Proxy proxy) {
        this.mProxy = proxy;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    public boolean processDataOnBackground() {
        return this.processDataOnBackground;
    }

    public void setProcessDataOnBackground(boolean z) {
        this.processDataOnBackground = z;
    }

    public int getReconnectFrequency() {
        return this.reconnectFrequency;
    }

    public void setReconnectFrequency(int i) {
        this.reconnectFrequency = i;
    }

    public Map<String, String> getHttpHeaders() {
        return this.httpHeaders;
    }

    public void setHttpHeaders(Map<String, String> map) {
        this.httpHeaders = map;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public ResponseDelivery getResponseDelivery() {
        return this.responseDelivery;
    }

    public void setResponseDelivery(ResponseDelivery responseDelivery) {
        this.responseDelivery = responseDelivery;
    }
}
