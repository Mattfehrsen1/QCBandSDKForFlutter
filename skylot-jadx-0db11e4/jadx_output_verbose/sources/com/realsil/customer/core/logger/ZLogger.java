package com.realsil.customer.core.logger;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.realsil.customer.core.f.a;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/logger/ZLogger.class */
public final class ZLogger {
    public static String a = "Realtek";
    public static boolean LOG_ENABLED = false;
    public static final int NA = 0;
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int ASSET = 6;
    public static int GLOBAL_LOG_LEVEL = 1;
    public static int b = 0;
    public static Logger c = new a();

    public static void initialize(String str, boolean z) {
        initialize(str, z, 1);
    }

    public static void setLogger(@NonNull Logger logger) {
        c = logger;
    }

    public static void setConsoleLogLevel(int i) {
        GLOBAL_LOG_LEVEL = i;
    }

    public static void setFilterLogLevel(int i) {
        b = i;
    }

    public static void v(String str) {
        a(LOG_ENABLED, 1, a, str);
    }

    public static void d(String str) {
        a(LOG_ENABLED, 2, a, str);
    }

    public static void i(String str) {
        a(LOG_ENABLED, 3, a, str);
    }

    public static void w(String str) {
        a(LOG_ENABLED, 4, a, str);
    }

    public static void e(String str) {
        a(LOG_ENABLED, 5, a, str);
    }

    public static void a(boolean z, int i, String str, Object obj) {
        String[] strArr;
        if (z) {
            Object[] objArr = {obj};
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 6) {
                strArr = null;
            } else {
                String className = stackTrace[5].getClassName();
                String str2 = className;
                String[] strArrSplit = className.split("\\.");
                if (strArrSplit.length > 0) {
                    str2 = strArrSplit[strArrSplit.length - 1] + ".java";
                }
                if (str2.contains("$")) {
                    str2 = str2.split("\\$")[0] + ".java";
                }
                String methodName = stackTrace[5].getMethodName();
                int lineNumber = stackTrace[5].getLineNumber();
                int i2 = lineNumber;
                if (lineNumber < 0) {
                    i2 = 0;
                }
                String str3 = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                String str4 = str == null ? str2 : str;
                if (TextUtils.isEmpty(str4)) {
                    str4 = a;
                }
                strArr = new String[]{str4, objArr[0].toString(), String.format(Locale.US, "[ (%s:%d)#%s ]", str2, Integer.valueOf(i2), str3)};
            }
            if (strArr == null || strArr.length < 3) {
                a(i, str, (String) obj);
            } else {
                String[] strArr2 = strArr;
                a(i, strArr2[0], strArr2[2] + strArr2[1]);
            }
        }
    }

    public static void initialize(String str, boolean z, int i) {
        a = str;
        LOG_ENABLED = z;
        GLOBAL_LOG_LEVEL = i;
    }

    public static void v(boolean z, String str) {
        a(z, 1, a, str);
    }

    public static void d(boolean z, String str) {
        a(z, 2, a, str);
    }

    public static void i(boolean z, String str) {
        a(z, 3, a, str);
    }

    public static void w(boolean z, String str) {
        a(z, 4, a, str);
    }

    public static void e(boolean z, String str) {
        a(z, 5, a, str);
    }

    public static void v(boolean z, String str, String str2) {
        a(z, 1, str, str2);
    }

    public static void d(boolean z, String str, String str2) {
        a(z, 2, str, str2);
    }

    public static void i(boolean z, String str, String str2) {
        a(z, 3, str, str2);
    }

    public static void w(boolean z, String str, String str2) {
        a(z, 4, str, str2);
    }

    public static void e(boolean z, String str, String str2) {
        a(z, 5, str, str2);
    }

    public static void a(int i, String str, String str2) {
        if (c == null) {
            return;
        }
        int iMax = Math.max(i, GLOBAL_LOG_LEVEL);
        if ((iMax & 6) == 6 && b <= 6) {
            c.log(6, str, str2);
            return;
        }
        if ((iMax & 5) == 5 && b <= 5) {
            c.log(5, str, str2);
            return;
        }
        if ((iMax & 4) == 4 && b <= 4) {
            c.log(4, str, str2);
            return;
        }
        if ((iMax & 3) == 3 && b <= 3) {
            c.log(3, str, str2);
            return;
        }
        if ((iMax & 2) == 2 && b <= 2) {
            c.log(2, str, str2);
        } else {
            if ((iMax & 1) != 1 || b > 1) {
                return;
            }
            c.log(1, str, str2);
        }
    }
}
