package com.zhangke.websocket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.zhangke.websocket.util.LogUtil;
import com.zhangke.websocket.util.PermissionUtil;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class NetworkChangedReceiver extends BroadcastReceiver {
    private static final String TAG = "WSNetworkReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return;
        }
        try {
            if (!PermissionUtil.checkPermission(context, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return;
            }
            if (activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 1) {
                    LogUtil.i(TAG, "网络连接发生变化，当前WiFi连接可用，正在尝试重连。");
                } else if (activeNetworkInfo.getType() == 0) {
                    LogUtil.i(TAG, "网络连接发生变化，当前移动连接可用，正在尝试重连。");
                }
                if (WebSocketHandler.getDefault() != null && WebSocketHandler.getDefault().getSetting().reconnectWithNetworkChanged()) {
                    WebSocketHandler.getDefault().reconnect();
                }
                if (WebSocketHandler.getAllWebSocket().isEmpty()) {
                    return;
                }
                Map<String, WebSocketManager> allWebSocket = WebSocketHandler.getAllWebSocket();
                Iterator<String> it = allWebSocket.keySet().iterator();
                while (it.hasNext()) {
                    WebSocketManager webSocketManager = allWebSocket.get(it.next());
                    if (webSocketManager != null && webSocketManager.getSetting().reconnectWithNetworkChanged()) {
                        webSocketManager.reconnect();
                    }
                }
                return;
            }
            LogUtil.i(TAG, "当前没有可用网络");
        } catch (Exception e) {
            LogUtil.e(TAG, "网络状态获取错误", e);
        }
    }
}
