package com.qcwireless.qcwatch.base.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.qcwireless.qcwatch.QCApplication;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Global.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u001a\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007\u001a\u000e\u0010\u0002\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a\u000e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a\u000e\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e\u001a\u000e\u0010\u000f\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t\u001a\u0016\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0007\u001a<\u0010\u0012\u001a\u00020\u00132\u0016\u0010\u0014\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00160\u0015\"\u0004\u0018\u00010\u00162\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00130\u0018¢\u0006\u0002\b\u0019¢\u0006\u0002\u0010\u001a\u001a+\u0010\u0012\u001a\u00020\u00132\u0016\u0010\u0014\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00160\u0015\"\u0004\u0018\u00010\u00162\u0006\u0010\u001b\u001a\u00020\u001c¢\u0006\u0002\u0010\u001d\u001a\u0014\u0010\u001e\u001a\u00020\u0013*\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u000e\"\u0011\u0010\u0000\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006!"}, d2 = {"appName", "", "getAppName", "()Ljava/lang/String;", "dateLocalization", "", "dp2px", "", "context", "Landroid/content/Context;", "dp", "getPackageName", "getString", "resId", "", "getVersionName", "px2dp", "px", "setOnClickListener", "", "v", "", "Landroid/view/View;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "([Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "([Landroid/view/View;Landroid/view/View$OnClickListener;)V", "showToast", "", TypedValues.TransitionType.S_DURATION, "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GlobalKt {
    public static final void setOnClickListener(View[] v, final Function1<? super View, Unit> block) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(block, "block");
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.view.GlobalKt$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GlobalKt.m255setOnClickListener$lambda0(block, view);
            }
        };
        for (View view : v) {
            if (view != null) {
                view.setOnClickListener(onClickListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnClickListener$lambda-0, reason: not valid java name */
    public static final void m255setOnClickListener$lambda0(Function1 block, View it) {
        Intrinsics.checkNotNullParameter(block, "$block");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        block.invoke(it);
    }

    public static final String getString(int i) throws Resources.NotFoundException {
        String string = QCApplication.INSTANCE.getCONTEXT().getResources().getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "QCApplication.CONTEXT.resources.getString(resId)");
        return string;
    }

    public static final String getAppName() throws Resources.NotFoundException {
        String string = QCApplication.INSTANCE.getCONTEXT().getResources().getString(QCApplication.INSTANCE.getCONTEXT().getApplicationInfo().labelRes);
        Intrinsics.checkNotNullExpressionValue(string, "QCApplication.CONTEXT.re…applicationInfo.labelRes)");
        return string;
    }

    public static /* synthetic */ void showToast$default(CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        showToast(charSequence, i);
    }

    public static final void showToast(CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Toast.makeText(QCApplication.INSTANCE.getApplication(), charSequence, i).show();
    }

    public static final float px2dp(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (f / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static final float dp2px(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (f * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static final String getVersionName(Context context) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "pm.getPackageInfo(context.packageName, 0)");
            String str = packageInfo.versionName;
            Intrinsics.checkNotNullExpressionValue(str, "packageInfo.versionName");
            return str;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0.0";
        }
    }

    public static final boolean dateLocalization() {
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "language");
        return StringsKt.contains$default((CharSequence) Localization.language, (CharSequence) language, false, 2, (Object) null);
    }

    public static final String getPackageName(Context context) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "pm.getPackageInfo(context.packageName, 0)");
            String str = packageInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(str, "packageInfo.packageName");
            return str;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "com.qc";
        }
    }

    public static final String getAppName(Context context) throws Resources.NotFoundException, PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "pm.getPackageInfo(context.packageName, 0)");
            String string = context.getResources().getString(packageInfo.applicationInfo.labelRes);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(labelRes)");
            return string;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "others";
        }
    }

    public static final void setOnClickListener(View[] v, View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(listener, "listener");
        for (View view : v) {
            if (view != null) {
                view.setOnClickListener(listener);
            }
        }
    }
}
