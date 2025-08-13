package androidx.camera.core;

import android.os.Build;
import android.util.Log;

/* loaded from: classes.dex */
public final class Logger {
    static final int DEFAULT_MIN_LOG_LEVEL = 3;
    private static final int MAX_TAG_LENGTH = 23;
    private static int sMinLogLevel = 3;

    private Logger() {
    }

    static void setMinLogLevel(int logLevel) {
        sMinLogLevel = logLevel;
    }

    static void resetMinLogLevel() {
        sMinLogLevel = 3;
    }

    public static boolean isDebugEnabled(String tag) {
        return sMinLogLevel <= 3 || Log.isLoggable(truncateTag(tag), 3);
    }

    public static boolean isInfoEnabled(String tag) {
        return sMinLogLevel <= 4 || Log.isLoggable(truncateTag(tag), 4);
    }

    public static boolean isWarnEnabled(String tag) {
        return sMinLogLevel <= 5 || Log.isLoggable(truncateTag(tag), 5);
    }

    public static boolean isErrorEnabled(String tag) {
        return sMinLogLevel <= 6 || Log.isLoggable(truncateTag(tag), 6);
    }

    public static void d(String tag, String message) {
        d(tag, message, null);
    }

    public static void d(String tag, String message, final Throwable throwable) {
        if (isDebugEnabled(tag)) {
            Log.d(truncateTag(tag), message, throwable);
        }
    }

    public static void i(String tag, String message) {
        i(tag, message, null);
    }

    public static void i(String tag, String message, final Throwable throwable) {
        if (isInfoEnabled(tag)) {
            Log.i(truncateTag(tag), message, throwable);
        }
    }

    public static void w(String tag, String message) {
        w(tag, message, null);
    }

    public static void w(String tag, String message, final Throwable throwable) {
        if (isWarnEnabled(tag)) {
            Log.w(truncateTag(tag), message, throwable);
        }
    }

    public static void e(String tag, String message) {
        e(tag, message, null);
    }

    public static void e(String tag, String message, final Throwable throwable) {
        if (isErrorEnabled(tag)) {
            Log.e(truncateTag(tag), message, throwable);
        }
    }

    private static String truncateTag(String tag) {
        return (23 >= tag.length() || Build.VERSION.SDK_INT >= 24) ? tag : tag.substring(0, 23);
    }
}
