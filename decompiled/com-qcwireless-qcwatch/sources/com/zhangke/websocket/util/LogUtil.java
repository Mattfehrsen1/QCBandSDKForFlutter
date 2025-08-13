package com.zhangke.websocket.util;

import com.zhangke.websocket.WebSocketHandler;

/* loaded from: classes4.dex */
public class LogUtil {
    public static void v(String str, String str2) {
        WebSocketHandler.getLogable().v(str, str2);
    }

    public static void v(String str, String str2, Throwable th) {
        WebSocketHandler.getLogable().v(str, str2, th);
    }

    public static void d(String str, String str2) {
        WebSocketHandler.getLogable().d(str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        WebSocketHandler.getLogable().d(str, str2, th);
    }

    public static void i(String str, String str2) {
        WebSocketHandler.getLogable().i(str, str2);
    }

    public static void i(String str, String str2, Throwable th) {
        WebSocketHandler.getLogable().i(str, str2, th);
    }

    public static void e(String str, String str2) {
        WebSocketHandler.getLogable().e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        WebSocketHandler.getLogable().e(str, str2, th);
    }

    public static void w(String str, Throwable th) {
        WebSocketHandler.getLogable().w(str, th);
    }

    public static void wtf(String str, String str2) {
        WebSocketHandler.getLogable().wtf(str, str2);
    }

    public static void wtf(String str, Throwable th) {
        WebSocketHandler.getLogable().wtf(str, th);
    }

    public static void wtf(String str, String str2, Throwable th) {
        WebSocketHandler.getLogable().wtf(str, str2, th);
    }
}
