package com.zhangke.websocket;

import android.content.Context;
import android.content.IntentFilter;
import com.zhangke.websocket.dispatcher.ResponseProcessEngine;
import com.zhangke.websocket.util.LogImpl;
import com.zhangke.websocket.util.LogUtil;
import com.zhangke.websocket.util.Logable;
import com.zhangke.websocket.util.PermissionUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class WebSocketHandler {
    private static final String TAG = "WebSocketHandler";
    private static final Object WS_MAP_BLOCK = new HashMap();
    private static WebSocketManager defaultWebSocket;
    private static Logable mLog;
    private static Map<String, WebSocketManager> mWebSocketMap;
    private static ResponseProcessEngine responseProcessEngine;
    private static WebSocketEngine webSocketEngine;

    public static WebSocketManager init(WebSocketSetting webSocketSetting) {
        if (defaultWebSocket == null) {
            synchronized (WebSocketHandler.class) {
                if (webSocketEngine == null) {
                    webSocketEngine = new WebSocketEngine();
                }
                if (responseProcessEngine == null) {
                    responseProcessEngine = new ResponseProcessEngine();
                }
                if (defaultWebSocket == null) {
                    defaultWebSocket = new WebSocketManager(webSocketSetting, webSocketEngine, responseProcessEngine);
                }
            }
        } else {
            LogUtil.e(TAG, "Default WebSocketManager exists!do not start again!");
        }
        return defaultWebSocket;
    }

    public static WebSocketManager initGeneralWebSocket(String str, WebSocketSetting webSocketSetting) {
        checkEngineNullAndInit();
        checkWebSocketMapNullAndInit();
        synchronized (WS_MAP_BLOCK) {
            if (mWebSocketMap.containsKey(str)) {
                LogUtil.e(TAG, "WebSocketManager exists!do not start again!");
                return mWebSocketMap.get(str);
            }
            WebSocketManager webSocketManager = new WebSocketManager(webSocketSetting, webSocketEngine, responseProcessEngine);
            mWebSocketMap.put(str, webSocketManager);
            return webSocketManager;
        }
    }

    public static WebSocketManager getDefault() {
        return defaultWebSocket;
    }

    public static WebSocketManager getWebSocket(String str) {
        checkWebSocketMapNullAndInit();
        if (mWebSocketMap.containsKey(str)) {
            return mWebSocketMap.get(str);
        }
        return null;
    }

    public static Map<String, WebSocketManager> getAllWebSocket() {
        checkWebSocketMapNullAndInit();
        return mWebSocketMap;
    }

    public static WebSocketManager removeWebSocket(String str) {
        checkWebSocketMapNullAndInit();
        if (!mWebSocketMap.containsKey(str)) {
            return null;
        }
        WebSocketManager webSocketManager = mWebSocketMap.get(str);
        synchronized (WS_MAP_BLOCK) {
            mWebSocketMap.remove(str);
        }
        return webSocketManager;
    }

    public static void registerNetworkChangedReceiver(Context context) {
        if (PermissionUtil.checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                context.registerReceiver(new NetworkChangedReceiver(), intentFilter);
                return;
            } catch (Exception e) {
                LogUtil.e(TAG, "网络监听广播注册失败：", e);
                return;
            }
        }
        LogUtil.e(TAG, "未获取到网络状态权限，广播监听器无法注册");
    }

    private static void checkEngineNullAndInit() {
        if (webSocketEngine == null || responseProcessEngine == null) {
            synchronized (WebSocketHandler.class) {
                if (webSocketEngine == null) {
                    webSocketEngine = new WebSocketEngine();
                }
                if (responseProcessEngine == null) {
                    responseProcessEngine = new ResponseProcessEngine();
                }
            }
        }
    }

    private static void checkWebSocketMapNullAndInit() {
        if (mWebSocketMap == null) {
            synchronized (WS_MAP_BLOCK) {
                if (mWebSocketMap == null) {
                    mWebSocketMap = new HashMap();
                }
            }
        }
    }

    public static void setLogable(Logable logable) {
        mLog = logable;
    }

    public static Logable getLogable() {
        if (mLog == null) {
            mLog = new LogImpl();
        }
        return mLog;
    }
}
