package com.elvishew.xlog.internal;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.elvishew.xlog.formatter.message.object.BundleFormatter;
import com.elvishew.xlog.formatter.message.object.IntentFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class Platform {
    private static final Platform PLATFORM = findPlatform();

    public static Platform get() {
        return PLATFORM;
    }

    String lineSeparator() {
        return System.lineSeparator();
    }

    Printer defaultPrinter() {
        return new ConsolePrinter();
    }

    Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
        return Collections.emptyMap();
    }

    public void warn(String str) {
        System.out.println(str);
    }

    public void error(String str) {
        System.out.println(str);
    }

    private static Platform findPlatform() throws ClassNotFoundException {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException unused) {
        }
        return new Platform();
    }

    static class Android extends Platform {
        private static final Map<Class<?>, ObjectFormatter<?>> BUILTIN_OBJECT_FORMATTERS;

        Android() {
        }

        static {
            HashMap map = new HashMap();
            map.put(Bundle.class, new BundleFormatter());
            map.put(Intent.class, new IntentFormatter());
            BUILTIN_OBJECT_FORMATTERS = Collections.unmodifiableMap(map);
        }

        @Override // com.elvishew.xlog.internal.Platform
        String lineSeparator() {
            return Build.VERSION.SDK_INT < 19 ? "\n" : System.lineSeparator();
        }

        @Override // com.elvishew.xlog.internal.Platform
        Printer defaultPrinter() {
            return new AndroidPrinter();
        }

        @Override // com.elvishew.xlog.internal.Platform
        Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
            return BUILTIN_OBJECT_FORMATTERS;
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void warn(String str) {
            Log.w("XLog", str);
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void error(String str) {
            Log.e("XLog", str);
        }
    }
}
