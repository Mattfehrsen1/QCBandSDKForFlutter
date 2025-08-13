package com.zhangke.websocket;

import android.text.TextUtils;
import com.zhangke.websocket.ReconnectManager;
import com.zhangke.websocket.dispatcher.MainThreadResponseDelivery;
import com.zhangke.websocket.dispatcher.ResponseDelivery;
import com.zhangke.websocket.dispatcher.ResponseProcessEngine;
import com.zhangke.websocket.request.Request;
import com.zhangke.websocket.request.RequestFactory;
import com.zhangke.websocket.response.ErrorResponse;
import com.zhangke.websocket.response.Response;
import com.zhangke.websocket.response.ResponseFactory;
import com.zhangke.websocket.util.LogUtil;
import java.nio.ByteBuffer;
import java.util.Collection;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;

/* loaded from: classes4.dex */
public class WebSocketManager {
    private static final String TAG = "WSManager";
    private boolean destroyed = false;
    private boolean disconnect = false;
    private ResponseDelivery mDelivery;
    private ReconnectManager mReconnectManager;
    private ResponseProcessEngine mResponseProcessEngine;
    private WebSocketSetting mSetting;
    private SocketWrapperListener mSocketWrapperListener;
    private WebSocketWrapper mWebSocket;
    private WebSocketEngine mWebSocketEngine;

    WebSocketManager(WebSocketSetting webSocketSetting, WebSocketEngine webSocketEngine, ResponseProcessEngine responseProcessEngine) {
        this.mSetting = webSocketSetting;
        this.mWebSocketEngine = webSocketEngine;
        this.mResponseProcessEngine = responseProcessEngine;
        ResponseDelivery responseDelivery = webSocketSetting.getResponseDelivery();
        this.mDelivery = responseDelivery;
        if (responseDelivery == null) {
            this.mDelivery = new MainThreadResponseDelivery();
        }
        SocketWrapperListener socketWrapperListener = getSocketWrapperListener();
        this.mSocketWrapperListener = socketWrapperListener;
        if (this.mWebSocket == null) {
            this.mWebSocket = new WebSocketWrapper(this.mSetting, socketWrapperListener);
        }
        start();
    }

    public WebSocketManager start() {
        if (this.mWebSocket == null) {
            this.mWebSocket = new WebSocketWrapper(this.mSetting, this.mSocketWrapperListener);
        }
        if (this.mWebSocket.getConnectState() == 0) {
            reconnect();
        }
        return this;
    }

    public boolean isConnect() {
        WebSocketWrapper webSocketWrapper = this.mWebSocket;
        return webSocketWrapper != null && webSocketWrapper.getConnectState() == 2;
    }

    public void setReconnectManager(ReconnectManager reconnectManager) {
        this.mReconnectManager = reconnectManager;
    }

    public WebSocketManager reconnect() {
        this.disconnect = false;
        if (this.mReconnectManager == null) {
            this.mReconnectManager = getDefaultReconnectManager();
        }
        if (!this.mReconnectManager.reconnecting()) {
            this.mReconnectManager.startReconnect();
        }
        return this;
    }

    public WebSocketManager reconnect(WebSocketSetting webSocketSetting) {
        this.disconnect = false;
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
            return this;
        }
        this.mSetting = webSocketSetting;
        WebSocketWrapper webSocketWrapper = this.mWebSocket;
        if (webSocketWrapper != null) {
            webSocketWrapper.destroy();
            this.mWebSocket = null;
        }
        start();
        return this;
    }

    public WebSocketManager disConnect() {
        this.disconnect = true;
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
            return this;
        }
        if (this.mWebSocket.getConnectState() != 0) {
            this.mWebSocketEngine.disConnect(this.mWebSocket, this.mSocketWrapperListener);
        }
        return this;
    }

    public void send(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Request<String> requestCreateStringRequest = RequestFactory.createStringRequest();
        requestCreateStringRequest.setRequestData(str);
        sendRequest(requestCreateStringRequest);
    }

    public void send(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        Request<byte[]> requestCreateByteArrayRequest = RequestFactory.createByteArrayRequest();
        requestCreateByteArrayRequest.setRequestData(bArr);
        sendRequest(requestCreateByteArrayRequest);
    }

    public void send(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return;
        }
        Request<ByteBuffer> requestCreateByteBufferRequest = RequestFactory.createByteBufferRequest();
        requestCreateByteBufferRequest.setRequestData(byteBuffer);
        sendRequest(requestCreateByteBufferRequest);
    }

    public void sendPing() {
        sendRequest(RequestFactory.createPingRequest());
    }

    public void sendPong() {
        sendRequest(RequestFactory.createPongRequest());
    }

    public void sendPong(PingFrame pingFrame) {
        if (pingFrame == null) {
            return;
        }
        Request<PingFrame> requestCreatePongRequest = RequestFactory.createPongRequest();
        requestCreatePongRequest.setRequestData(pingFrame);
        sendRequest(requestCreatePongRequest);
    }

    public void sendFrame(Framedata framedata) {
        if (framedata == null) {
            return;
        }
        Request<Framedata> requestCreateFrameDataRequest = RequestFactory.createFrameDataRequest();
        requestCreateFrameDataRequest.setRequestData(framedata);
        sendRequest(requestCreateFrameDataRequest);
    }

    public void sendFrame(Collection<Framedata> collection) {
        if (collection == null) {
            return;
        }
        Request<Collection<Framedata>> requestCreateCollectionFrameRequest = RequestFactory.createCollectionFrameRequest();
        requestCreateCollectionFrameRequest.setRequestData(collection);
        sendRequest(requestCreateCollectionFrameRequest);
    }

    public WebSocketManager addListener(SocketListener socketListener) {
        this.mDelivery.addListener(socketListener);
        return this;
    }

    public WebSocketManager removeListener(SocketListener socketListener) {
        this.mDelivery.removeListener(socketListener);
        return this;
    }

    public WebSocketSetting getSetting() {
        return this.mSetting;
    }

    public void destroy() {
        this.destroyed = true;
        WebSocketWrapper webSocketWrapper = this.mWebSocket;
        if (webSocketWrapper != null) {
            this.mWebSocketEngine.destroyWebSocket(webSocketWrapper);
            this.mWebSocketEngine = null;
            this.mWebSocket = null;
        }
        ResponseDelivery responseDelivery = this.mDelivery;
        if (responseDelivery != null) {
            if (!responseDelivery.isEmpty()) {
                this.mDelivery.clear();
            }
            this.mDelivery = null;
        }
        ReconnectManager reconnectManager = this.mReconnectManager;
        if (reconnectManager != null) {
            if (reconnectManager.reconnecting()) {
                this.mReconnectManager.stopReconnect();
            }
            this.mReconnectManager = null;
        }
    }

    void reconnectOnce() {
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
            return;
        }
        if (this.mWebSocket.getConnectState() == 0) {
            this.mWebSocketEngine.connect(this.mWebSocket, this.mSocketWrapperListener);
            return;
        }
        ReconnectManager reconnectManager = this.mReconnectManager;
        if (reconnectManager != null) {
            reconnectManager.onConnected();
        }
        LogUtil.e(TAG, "WebSocket 已连接，请勿重试。");
    }

    private void sendRequest(Request request) {
        if (this.destroyed) {
            LogUtil.e(TAG, "This WebSocketManager is destroyed!");
        } else {
            this.mWebSocketEngine.sendRequest(this.mWebSocket, request, this.mSocketWrapperListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ReconnectManager getDefaultReconnectManager() {
        return new DefaultReconnectManager(this, new ReconnectManager.OnConnectListener() { // from class: com.zhangke.websocket.WebSocketManager.1
            @Override // com.zhangke.websocket.ReconnectManager.OnConnectListener
            public void onConnected() {
                LogUtil.i(WebSocketManager.TAG, "重连成功");
            }

            @Override // com.zhangke.websocket.ReconnectManager.OnConnectListener
            public void onDisconnect() {
                LogUtil.i(WebSocketManager.TAG, "重连失败");
                WebSocketManager.this.mSetting.getResponseDispatcher().onDisconnect(WebSocketManager.this.mDelivery);
            }
        });
    }

    private SocketWrapperListener getSocketWrapperListener() {
        return new SocketWrapperListener() { // from class: com.zhangke.websocket.WebSocketManager.2
            @Override // com.zhangke.websocket.SocketWrapperListener
            public void onConnected() {
                if (WebSocketManager.this.mReconnectManager != null) {
                    WebSocketManager.this.mReconnectManager.onConnected();
                }
                WebSocketManager.this.mSetting.getResponseDispatcher().onConnected(WebSocketManager.this.mDelivery);
            }

            @Override // com.zhangke.websocket.SocketWrapperListener
            public void onConnectFailed(Throwable th) {
                if (WebSocketManager.this.mReconnectManager != null && WebSocketManager.this.mReconnectManager.reconnecting()) {
                    WebSocketManager.this.mReconnectManager.onConnectError(th);
                }
                WebSocketManager.this.mSetting.getResponseDispatcher().onConnectFailed(th, WebSocketManager.this.mDelivery);
            }

            @Override // com.zhangke.websocket.SocketWrapperListener
            public void onDisconnect() {
                WebSocketManager.this.mSetting.getResponseDispatcher().onDisconnect(WebSocketManager.this.mDelivery);
                if (WebSocketManager.this.mReconnectManager == null || !WebSocketManager.this.mReconnectManager.reconnecting()) {
                    if (WebSocketManager.this.disconnect) {
                        return;
                    }
                    if (WebSocketManager.this.mReconnectManager == null) {
                        WebSocketManager webSocketManager = WebSocketManager.this;
                        webSocketManager.mReconnectManager = webSocketManager.getDefaultReconnectManager();
                    }
                    WebSocketManager.this.mReconnectManager.onConnectError(null);
                    WebSocketManager.this.mReconnectManager.startReconnect();
                    return;
                }
                if (WebSocketManager.this.disconnect) {
                    WebSocketManager.this.mSetting.getResponseDispatcher().onDisconnect(WebSocketManager.this.mDelivery);
                } else {
                    WebSocketManager.this.mReconnectManager.onConnectError(null);
                }
            }

            @Override // com.zhangke.websocket.SocketWrapperListener
            public void onSendDataError(Request request, int i, Throwable th) {
                ErrorResponse errorResponseCreateErrorResponse = ResponseFactory.createErrorResponse();
                errorResponseCreateErrorResponse.init(request, i, th);
                if (!WebSocketManager.this.mSetting.processDataOnBackground()) {
                    WebSocketManager.this.mSetting.getResponseDispatcher().onSendDataError(errorResponseCreateErrorResponse, WebSocketManager.this.mDelivery);
                } else {
                    WebSocketManager.this.mResponseProcessEngine.onSendDataError(errorResponseCreateErrorResponse, WebSocketManager.this.mSetting.getResponseDispatcher(), WebSocketManager.this.mDelivery);
                }
                if (WebSocketManager.this.disconnect || i != 0) {
                    return;
                }
                LogUtil.e(WebSocketManager.TAG, "数据发送失败，网络未连接，开始重连。。。");
                WebSocketManager.this.reconnect();
            }

            @Override // com.zhangke.websocket.SocketWrapperListener
            public void onMessage(Response response) {
                if (!WebSocketManager.this.mSetting.processDataOnBackground()) {
                    response.onResponse(WebSocketManager.this.mSetting.getResponseDispatcher(), WebSocketManager.this.mDelivery);
                } else {
                    WebSocketManager.this.mResponseProcessEngine.onMessageReceive(response, WebSocketManager.this.mSetting.getResponseDispatcher(), WebSocketManager.this.mDelivery);
                }
            }
        };
    }
}
