package com.zhangke.websocket;

import android.text.TextUtils;
import com.zhangke.websocket.request.Request;
import com.zhangke.websocket.response.Response;
import com.zhangke.websocket.response.ResponseFactory;
import com.zhangke.websocket.util.LogUtil;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

/* loaded from: classes4.dex */
public class WebSocketWrapper {
    private static final String TAG = "WSWrapper";
    private WebSocketSetting mSetting;
    private SocketWrapperListener mSocketListener;
    private WebSocketClient mWebSocket;
    private int connectStatus = 0;
    private boolean needClose = false;
    private boolean destroyed = false;

    WebSocketWrapper(WebSocketSetting webSocketSetting, SocketWrapperListener socketWrapperListener) {
        this.mSetting = webSocketSetting;
        this.mSocketListener = socketWrapperListener;
    }

    void connect() {
        if (this.destroyed) {
            return;
        }
        this.needClose = false;
        if (this.connectStatus == 0) {
            this.connectStatus = 1;
            try {
                if (this.mWebSocket == null) {
                    if (TextUtils.isEmpty(this.mSetting.getConnectUrl())) {
                        throw new RuntimeException("WebSocket connect url is empty!");
                    }
                    Draft draft = this.mSetting.getDraft();
                    if (draft == null) {
                        draft = new Draft_6455();
                    }
                    Draft draft2 = draft;
                    int connectTimeout = this.mSetting.getConnectTimeout();
                    this.mWebSocket = new MyWebSocketClient(new URI(this.mSetting.getConnectUrl()), draft2, this.mSetting.getHttpHeaders(), connectTimeout <= 0 ? 0 : connectTimeout);
                    LogUtil.i(TAG, "WebSocket start connect...");
                    if (this.mSetting.getProxy() != null) {
                        this.mWebSocket.setProxy(this.mSetting.getProxy());
                    }
                    this.mWebSocket.connect();
                    this.mWebSocket.setConnectionLostTimeout(this.mSetting.getConnectionLostTimeout());
                    if (this.needClose) {
                        disConnect();
                    }
                    checkDestroy();
                    return;
                }
                LogUtil.i(TAG, "WebSocket reconnecting...");
                this.mWebSocket.reconnect();
                if (this.needClose) {
                    disConnect();
                }
                checkDestroy();
            } catch (Throwable th) {
                this.connectStatus = 0;
                LogUtil.e(TAG, "WebSocket connect failed:", th);
                SocketWrapperListener socketWrapperListener = this.mSocketListener;
                if (socketWrapperListener != null) {
                    socketWrapperListener.onConnectFailed(th);
                }
            }
        }
    }

    void reconnect() {
        this.needClose = false;
        if (this.connectStatus == 0) {
            connect();
        }
    }

    void disConnect() {
        this.needClose = true;
        if (this.connectStatus == 2) {
            LogUtil.i(TAG, "WebSocket disconnecting...");
            WebSocketClient webSocketClient = this.mWebSocket;
            if (webSocketClient != null) {
                webSocketClient.close();
            }
            LogUtil.i(TAG, "WebSocket disconnected");
        }
    }

    void send(Request request) {
        WebSocketClient webSocketClient = this.mWebSocket;
        if (webSocketClient == null) {
            return;
        }
        if (request == null) {
            LogUtil.e(TAG, "send data is null!");
            return;
        }
        try {
            if (this.connectStatus == 2) {
                try {
                    request.send(webSocketClient);
                    LogUtil.i(TAG, "send success:" + request.toString());
                } catch (WebsocketNotConnectedException e) {
                    this.connectStatus = 0;
                    LogUtil.e(TAG, "ws is disconnected, send failed:" + request.toString(), e);
                    SocketWrapperListener socketWrapperListener = this.mSocketListener;
                    if (socketWrapperListener != null) {
                        socketWrapperListener.onSendDataError(request, 0, e);
                        this.mSocketListener.onDisconnect();
                    }
                    return;
                } catch (Throwable th) {
                    this.connectStatus = 0;
                    LogUtil.e(TAG, "Exception,send failed:" + request.toString(), th);
                    SocketWrapperListener socketWrapperListener2 = this.mSocketListener;
                    if (socketWrapperListener2 != null) {
                        socketWrapperListener2.onSendDataError(request, 1, th);
                    }
                    return;
                }
                return;
            }
            LogUtil.e(TAG, "WebSocket not connect,send failed:" + request.toString());
            SocketWrapperListener socketWrapperListener3 = this.mSocketListener;
            if (socketWrapperListener3 != null) {
                socketWrapperListener3.onSendDataError(request, 0, null);
            }
        } finally {
            request.release();
        }
    }

    int getConnectState() {
        return this.connectStatus;
    }

    void destroy() {
        this.destroyed = true;
        disConnect();
        if (this.connectStatus == 0) {
            this.mWebSocket = null;
        }
        releaseResource();
    }

    private void checkDestroy() {
        if (this.destroyed) {
            try {
                WebSocketClient webSocketClient = this.mWebSocket;
                if (webSocketClient != null && !webSocketClient.isClosed()) {
                    this.mWebSocket.close();
                }
                releaseResource();
                this.connectStatus = 0;
            } catch (Throwable th) {
                LogUtil.e(TAG, "checkDestroy(WebSocketClient)", th);
            }
        }
    }

    private void releaseResource() {
        if (this.mSocketListener != null) {
            this.mSocketListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackOpen(ServerHandshake serverHandshake) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = 2;
        LogUtil.i(TAG, "WebSocket connect success");
        if (this.needClose) {
            disConnect();
            return;
        }
        SocketWrapperListener socketWrapperListener = this.mSocketListener;
        if (socketWrapperListener != null) {
            socketWrapperListener.onConnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackMessage(String str) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = 2;
        if (this.mSocketListener != null) {
            Response<String> responseCreateTextResponse = ResponseFactory.createTextResponse();
            responseCreateTextResponse.setResponseData(str);
            LogUtil.i(TAG, "WebSocket received message:" + responseCreateTextResponse.toString());
            this.mSocketListener.onMessage(responseCreateTextResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackMessage(ByteBuffer byteBuffer) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = 2;
        if (this.mSocketListener != null) {
            Response<ByteBuffer> responseCreateByteBufferResponse = ResponseFactory.createByteBufferResponse();
            responseCreateByteBufferResponse.setResponseData(byteBuffer);
            LogUtil.i(TAG, "WebSocket received message:" + responseCreateByteBufferResponse.toString());
            this.mSocketListener.onMessage(responseCreateByteBufferResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackWebsocketPing(Framedata framedata) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = 2;
        if (this.mSocketListener != null) {
            Response<Framedata> responseCreatePingResponse = ResponseFactory.createPingResponse();
            responseCreatePingResponse.setResponseData(framedata);
            LogUtil.i(TAG, "WebSocket received ping:" + responseCreatePingResponse.toString());
            this.mSocketListener.onMessage(responseCreatePingResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackWebsocketPong(Framedata framedata) {
        if (this.destroyed) {
            checkDestroy();
            return;
        }
        this.connectStatus = 2;
        if (this.mSocketListener != null) {
            Response<Framedata> responseCreatePongResponse = ResponseFactory.createPongResponse();
            responseCreatePongResponse.setResponseData(framedata);
            LogUtil.i(TAG, "WebSocket received pong:" + responseCreatePongResponse.toString());
            this.mSocketListener.onMessage(responseCreatePongResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackClose(int i, String str, boolean z) {
        this.connectStatus = 0;
        LogUtil.d(TAG, String.format("WebSocket closed!code=%s,reason:%s,remote:%s", Integer.valueOf(i), str, Boolean.valueOf(z)));
        SocketWrapperListener socketWrapperListener = this.mSocketListener;
        if (socketWrapperListener != null) {
            socketWrapperListener.onDisconnect();
        }
        checkDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWSCallbackError(Exception exc) {
        if (this.destroyed) {
            checkDestroy();
        } else {
            LogUtil.e(TAG, "WebSocketClient#onError(Exception)", exc);
        }
    }

    private class MyWebSocketClient extends WebSocketClient {
        public MyWebSocketClient(URI uri) {
            super(uri);
        }

        public MyWebSocketClient(URI uri, Draft draft) {
            super(uri, draft);
        }

        public MyWebSocketClient(URI uri, Map<String, String> map) {
            super(uri, map);
        }

        public MyWebSocketClient(URI uri, Draft draft, Map<String, String> map) {
            super(uri, draft, map);
        }

        public MyWebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
            super(uri, draft, map, i);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onOpen(ServerHandshake serverHandshake) {
            WebSocketWrapper.this.onWSCallbackOpen(serverHandshake);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onMessage(String str) {
            WebSocketWrapper.this.onWSCallbackMessage(str);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onMessage(ByteBuffer byteBuffer) {
            WebSocketWrapper.this.onWSCallbackMessage(byteBuffer);
        }

        @Override // org.java_websocket.WebSocketAdapter, org.java_websocket.WebSocketListener
        public void onWebsocketPing(WebSocket webSocket, Framedata framedata) {
            super.onWebsocketPing(webSocket, framedata);
            WebSocketWrapper.this.onWSCallbackWebsocketPing(framedata);
        }

        @Override // org.java_websocket.WebSocketAdapter, org.java_websocket.WebSocketListener
        public void onWebsocketPong(WebSocket webSocket, Framedata framedata) {
            super.onWebsocketPong(webSocket, framedata);
            WebSocketWrapper.this.onWSCallbackWebsocketPong(framedata);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onClose(int i, String str, boolean z) {
            WebSocketWrapper.this.onWSCallbackClose(i, str, z);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onError(Exception exc) {
            WebSocketWrapper.this.onWSCallbackError(exc);
        }
    }
}
