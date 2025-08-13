package com.qcwireless.qcwatch.base.pref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.exifinterface.media.ExifInterface;
import com.bumptech.glide.load.Key;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedPreferencesExt.kt */
@Metadata(d1 = {"\u0000@\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u001a\u001d\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0002\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0002¢\u0006\u0002\u0010\u0007\u001a3\u0010\b\u001a\u00020\t*\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u000e¢\u0006\u0002\b\u0010H\u0086\bø\u0001\u0000\u001a/\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u0002H\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0003¢\u0006\u0002\u0010\u0016\u001a/\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u0002H\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0003¢\u0006\u0002\u0010\u0018\u001a/\u0010\u0019\u001a\u00020\t\"\u0004\b\u0000\u0010\u0001*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u0002H\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0003¢\u0006\u0002\u0010\u001b\u001a/\u0010\u0019\u001a\u00020\t\"\u0004\b\u0000\u0010\u0001*\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u0002H\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0003¢\u0006\u0002\u0010\u001c\u001a\u001e\u0010\u001d\u001a\u00020\n*\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u001a\u001e\u0010\u001d\u001a\u00020\n*\u00020\u00172\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, d2 = {"deSerialization", ExifInterface.GPS_DIRECTION_TRUE, "str", "", "(Ljava/lang/String;)Ljava/lang/Object;", "serialize", "obj", "(Ljava/lang/Object;)Ljava/lang/String;", "edit", "", "Landroid/content/SharedPreferences;", "commit", "", "action", "Lkotlin/Function1;", "Landroid/content/SharedPreferences$Editor;", "Lkotlin/ExtensionFunctionType;", "getSpValue", "Landroid/app/Activity;", "key", "default", "name", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "Landroid/content/Context;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "putSpValue", "value", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)V", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)V", "sp", "mode", "", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SharedPreferencesExtKt {
    public static /* synthetic */ void edit$default(SharedPreferences sharedPreferences, boolean z, Function1 action, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        action.invoke(editor);
        if (z) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static final void edit(SharedPreferences sharedPreferences, boolean z, Function1<? super SharedPreferences.Editor, Unit> action) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        action.invoke(editor);
        if (z) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    public static /* synthetic */ SharedPreferences sp$default(Context context, String packageName, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return sp(context, packageName, i);
    }

    public static final SharedPreferences sp(Context context, String name, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, i);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(name, mode)");
        return sharedPreferences;
    }

    public static /* synthetic */ SharedPreferences sp$default(Activity activity, String packageName, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            packageName = activity.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return sp(activity, packageName, i);
    }

    public static final SharedPreferences sp(Activity activity, String name, int i) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        SharedPreferences sharedPreferences = activity.getSharedPreferences(name, i);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(name, mode)");
        return sharedPreferences;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void putSpValue(Context context, String key, T t, String name) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(name, "name");
        SharedPreferences.Editor editor = sp$default(context, name, 0, 2, (Object) null).edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        if (t instanceof Long) {
            editor.putLong(key, ((Number) t).longValue());
        } else if (t instanceof String) {
            editor.putString(key, (String) t);
        } else if (t instanceof Integer) {
            editor.putInt(key, ((Number) t).intValue());
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) t).booleanValue());
        } else if (t instanceof Float) {
            editor.putFloat(key, ((Number) t).floatValue());
        } else {
            editor.putString(key, serialize(t));
        }
        editor.apply();
    }

    public static /* synthetic */ void putSpValue$default(Context context, String str, Object obj, String packageName, int i, Object obj2) {
        if ((i & 4) != 0) {
            packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        }
        putSpValue(context, str, obj, packageName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void putSpValue(Activity activity, String key, T t, String name) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(name, "name");
        SharedPreferences.Editor editor = sp$default(activity, name, 0, 2, (Object) null).edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        if (t instanceof Long) {
            editor.putLong(key, ((Number) t).longValue());
        } else if (t instanceof String) {
            editor.putString(key, (String) t);
        } else if (t instanceof Integer) {
            editor.putInt(key, ((Number) t).intValue());
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) t).booleanValue());
        } else if (t instanceof Float) {
            editor.putFloat(key, ((Number) t).floatValue());
        } else {
            editor.putString(key, serialize(t));
        }
        editor.apply();
    }

    public static /* synthetic */ void putSpValue$default(Activity activity, String str, Object obj, String packageName, int i, Object obj2) {
        if ((i & 4) != 0) {
            packageName = activity.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        }
        putSpValue(activity, str, obj, packageName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T getSpValue(Context context, String key, T t, String name) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(name, "name");
        SharedPreferences sharedPreferencesSp$default = sp$default(context, name, 0, 2, (Object) null);
        return t instanceof Long ? (T) Long.valueOf(sharedPreferencesSp$default.getLong(key, ((Number) t).longValue())) : t instanceof String ? (T) sharedPreferencesSp$default.getString(key, (String) t) : t instanceof Integer ? (T) Integer.valueOf(sharedPreferencesSp$default.getInt(key, ((Number) t).intValue())) : t instanceof Boolean ? (T) Boolean.valueOf(sharedPreferencesSp$default.getBoolean(key, ((Boolean) t).booleanValue())) : t instanceof Float ? (T) Float.valueOf(sharedPreferencesSp$default.getFloat(key, ((Number) t).floatValue())) : (T) deSerialization(sharedPreferencesSp$default.getString(key, serialize(t)));
    }

    public static /* synthetic */ Object getSpValue$default(Context context, String str, Object obj, String packageName, int i, Object obj2) {
        if ((i & 4) != 0) {
            packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        }
        return getSpValue(context, str, obj, packageName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T getSpValue(Activity activity, String key, T t, String name) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(name, "name");
        SharedPreferences sharedPreferencesSp$default = sp$default(activity, name, 0, 2, (Object) null);
        return t instanceof Long ? (T) Long.valueOf(sharedPreferencesSp$default.getLong(key, ((Number) t).longValue())) : t instanceof String ? (T) sharedPreferencesSp$default.getString(key, (String) t) : t instanceof Integer ? (T) Integer.valueOf(sharedPreferencesSp$default.getInt(key, ((Number) t).intValue())) : t instanceof Boolean ? (T) Boolean.valueOf(sharedPreferencesSp$default.getBoolean(key, ((Boolean) t).booleanValue())) : t instanceof Float ? (T) Float.valueOf(sharedPreferencesSp$default.getFloat(key, ((Number) t).floatValue())) : (T) deSerialization(sharedPreferencesSp$default.getString(key, serialize(t)));
    }

    public static /* synthetic */ Object getSpValue$default(Activity activity, String str, Object obj, String packageName, int i, Object obj2) {
        if ((i & 4) != 0) {
            packageName = activity.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        }
        return getSpValue(activity, str, obj, packageName);
    }

    private static final <T> String serialize(T t) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(t);
        String serStr = URLEncoder.encode(byteArrayOutputStream.toString("ISO-8859-1"), Key.STRING_CHARSET_NAME);
        objectOutputStream.close();
        byteArrayOutputStream.close();
        Intrinsics.checkNotNullExpressionValue(serStr, "serStr");
        return serStr;
    }

    private static final <T> T deSerialization(String str) throws IOException {
        String redStr = URLDecoder.decode(str, Key.STRING_CHARSET_NAME);
        Intrinsics.checkNotNullExpressionValue(redStr, "redStr");
        Charset charsetForName = Charset.forName("ISO-8859-1");
        Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
        byte[] bytes = redStr.getBytes(charsetForName);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        T t = (T) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return t;
    }
}
