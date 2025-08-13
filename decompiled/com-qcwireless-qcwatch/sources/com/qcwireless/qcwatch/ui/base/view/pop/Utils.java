package com.qcwireless.qcwatch.ui.base.view.pop;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\b¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/Utils;", "", "()V", "checkDeviceHasNavigationBar", "", "context", "Landroid/content/Context;", "dp2px", "", "dipValue", "getNavigationBarHeight", "activity", "Landroid/app/Activity;", "getScreenHeight", "getScreenSize", "", "getScreenWidth", "getStatusBarHeight", "getWindowSize", "sp2px", "spValue", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Utils {
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final int getScreenWidth(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public final int getScreenHeight(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public final int[] getScreenSize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    public final int getStatusBarHeight(Context context) throws IllegalAccessException, NoSuchFieldException, InstantiationException, ClassNotFoundException, NumberFormatException {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            Intrinsics.checkNotNull(cls);
            Object objNewInstance = cls.newInstance();
            Field field = cls.getField("status_bar_height");
            Intrinsics.checkNotNull(field);
            return context.getResources().getDimensionPixelSize(Integer.parseInt(field.get(objNewInstance).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final int[] getWindowSize(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        int[] screenSize = getScreenSize(activity);
        return new int[]{screenSize[0], screenSize[1]};
    }

    private final boolean checkDeviceHasNavigationBar(Context context) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Object objInvoke;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            objInvoke = cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
        } catch (Exception unused) {
        }
        if (objInvoke == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        String str = (String) objInvoke;
        if (Intrinsics.areEqual("1", str)) {
            return false;
        }
        if (Intrinsics.areEqual("0", str)) {
            return true;
        }
        return z;
    }

    public final int getNavigationBarHeight(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Resources resources = activity.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public final int dp2px(Context context, int dipValue) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) TypedValue.applyDimension(1, dipValue, context.getResources().getDisplayMetrics());
    }

    public final int sp2px(Context context, int spValue) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) ((spValue * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
