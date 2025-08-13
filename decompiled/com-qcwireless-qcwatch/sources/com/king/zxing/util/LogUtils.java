package com.king.zxing.util;

import android.util.Log;

/* loaded from: classes3.dex */
public class LogUtils {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int PRINTLN = 1;
    public static final String TAG = "ZXingLite";
    public static final String TAG_FORMAT = "%s.%s(%s:%d)";
    public static final int VERBOSE = 2;
    public static final String VERTICAL = "|";
    public static final int WARN = 5;
    private static boolean isShowLog = true;
    private static int priority = 1;

    private LogUtils() {
        throw new AssertionError();
    }

    public static void setShowLog(boolean z) {
        isShowLog = z;
    }

    public static boolean isShowLog() {
        return isShowLog;
    }

    public static int getPriority() {
        return priority;
    }

    public static void setPriority(int i) {
        priority = i;
    }

    private static String generateTag(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        return TAG + VERTICAL + String.format(TAG_FORMAT, className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()));
    }

    public static StackTraceElement getStackTraceElement(int i) {
        return Thread.currentThread().getStackTrace()[i];
    }

    private static String getCallerStackLogTag() {
        return generateTag(getStackTraceElement(5));
    }

    private static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static void v(String str) {
        if (!isShowLog || priority > 2) {
            return;
        }
        Log.v(getCallerStackLogTag(), String.valueOf(str));
    }

    public static void v(Throwable th) {
        if (!isShowLog || priority > 2) {
            return;
        }
        Log.v(getCallerStackLogTag(), getStackTraceString(th));
    }

    public static void v(String str, Throwable th) {
        if (!isShowLog || priority > 2) {
            return;
        }
        Log.v(getCallerStackLogTag(), String.valueOf(str), th);
    }

    public static void d(String str) {
        if (!isShowLog || priority > 3) {
            return;
        }
        Log.d(getCallerStackLogTag(), String.valueOf(str));
    }

    public static void d(Throwable th) {
        if (!isShowLog || priority > 3) {
            return;
        }
        Log.d(getCallerStackLogTag(), getStackTraceString(th));
    }

    public static void d(String str, Throwable th) {
        if (!isShowLog || priority > 3) {
            return;
        }
        Log.d(getCallerStackLogTag(), String.valueOf(str), th);
    }

    public static void i(String str) {
        if (!isShowLog || priority > 4) {
            return;
        }
        Log.i(getCallerStackLogTag(), String.valueOf(str));
    }

    public static void i(Throwable th) {
        if (!isShowLog || priority > 4) {
            return;
        }
        Log.i(getCallerStackLogTag(), getStackTraceString(th));
    }

    public static void i(String str, Throwable th) {
        if (!isShowLog || priority > 4) {
            return;
        }
        Log.i(getCallerStackLogTag(), String.valueOf(str), th);
    }

    public static void w(String str) {
        if (!isShowLog || priority > 5) {
            return;
        }
        Log.w(getCallerStackLogTag(), String.valueOf(str));
    }

    public static void w(Throwable th) {
        if (!isShowLog || priority > 5) {
            return;
        }
        Log.w(getCallerStackLogTag(), getStackTraceString(th));
    }

    public static void w(String str, Throwable th) {
        if (!isShowLog || priority > 5) {
            return;
        }
        Log.w(getCallerStackLogTag(), String.valueOf(str), th);
    }

    public static void e(String str) {
        if (!isShowLog || priority > 6) {
            return;
        }
        Log.e(getCallerStackLogTag(), String.valueOf(str));
    }

    public static void e(Throwable th) {
        if (!isShowLog || priority > 6) {
            return;
        }
        Log.e(getCallerStackLogTag(), getStackTraceString(th));
    }

    public static void e(String str, Throwable th) {
        if (!isShowLog || priority > 6) {
            return;
        }
        Log.e(getCallerStackLogTag(), String.valueOf(str), th);
    }

    public static void wtf(String str) {
        if (!isShowLog || priority > 7) {
            return;
        }
        Log.wtf(getCallerStackLogTag(), String.valueOf(str));
    }

    public static void wtf(Throwable th) {
        if (!isShowLog || priority > 7) {
            return;
        }
        Log.wtf(getCallerStackLogTag(), getStackTraceString(th));
    }

    public static void wtf(String str, Throwable th) {
        if (!isShowLog || priority > 7) {
            return;
        }
        Log.wtf(getCallerStackLogTag(), String.valueOf(str), th);
    }

    public static void print(String str) {
        if (!isShowLog || priority > 1) {
            return;
        }
        System.out.print(str);
    }

    public static void print(Object obj) {
        if (!isShowLog || priority > 1) {
            return;
        }
        System.out.print(obj);
    }

    public static void printf(String str) {
        if (!isShowLog || priority > 1) {
            return;
        }
        System.out.printf(str, new Object[0]);
    }

    public static void println(String str) {
        if (!isShowLog || priority > 1) {
            return;
        }
        System.out.println(str);
    }

    public static void println(Object obj) {
        if (!isShowLog || priority > 1) {
            return;
        }
        System.out.println(obj);
    }
}
