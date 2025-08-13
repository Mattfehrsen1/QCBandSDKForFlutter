package com.qcwireless.qcwatch.ui.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;
import androidx.work.WorkRequest;
import com.elvishew.xlog.XLog;
import com.gyf.immersionbar.ImmersionBar;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.loading.ShapeLoadingDialog;
import com.qcwireless.qcwatch.ui.base.util.AppToolsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseActivity.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\fJ\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u000eH\u0014J\b\u0010\u0016\u001a\u00020\u000eH\u0014J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u000eH\u0014J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0019H\u0014J\b\u0010\u001d\u001a\u00020\u000eH\u0014J\b\u0010\u001e\u001a\u00020\u000eH\u0014J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u000eH\u0016J\b\u0010%\u001a\u00020\u000eH\u0014J\u0006\u0010&\u001a\u00020\u000eJ\u000e\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020#J\u000e\u0010)\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020#R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseThemeActivity;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "dialog", "Lcom/qcwireless/qcwatch/base/dialog/loading/ShapeLoadingDialog;", "handler", "Landroid/os/Handler;", "isActive", "", "dismissLoadingDialog", "", "getDelegate", "Landroidx/appcompat/app/AppCompatDelegate;", "isDialogShowing", "onNewIntent", "intent", "Landroid/content/Intent;", "onPause", "onRestart", "onRestoreInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "setContentView", "layoutView", "Landroid/view/View;", "layoutResID", "", "setStatusBarNoBackground", "setupViews", "showLoadingDialog", "showLoadingDialogTimeout", "timeout", "showLoadingDialogTimeoutNotCancel", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class BaseActivity extends BaseThemeActivity {
    private final String TAG;
    private ShapeLoadingDialog dialog;
    private final Handler handler;
    private boolean isActive;

    public BaseActivity() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        this.TAG = simpleName;
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.base.BaseActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    protected final String getTAG() {
        return this.TAG;
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public AppCompatDelegate getDelegate() {
        AppCompatDelegate appCompatDelegate = SkinAppCompatDelegateImpl.get(this, this);
        Intrinsics.checkNotNullExpressionValue(appCompatDelegate, "get(this, this)");
        return appCompatDelegate;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.isActive = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isActive = false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupViews();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View layoutView) {
        Intrinsics.checkNotNullParameter(layoutView, "layoutView");
        super.setContentView(layoutView);
        setupViews();
    }

    protected void setupViews() {
        ImageView imageView = (ImageView) findViewById(R.id.ivNavigateBefore);
        TextView textView = (TextView) findViewById(R.id.tvTitle);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.base.BaseActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseActivity.m273setupViews$lambda0(this.f$0, view);
                }
            });
        }
        if (textView == null) {
            return;
        }
        textView.setSelected(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m273setupViews$lambda0(BaseActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public void setStatusBarNoBackground() {
        ImmersionBar.with(this).transparentStatusBar().transparentNavigationBar().fitsSystemWindows(false).init();
    }

    public final void showLoadingDialog() {
        try {
            if (getActivity() != null) {
                Activity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                if (activity.isDestroyed()) {
                    return;
                }
                if (this.dialog == null) {
                    WindowManager windowManager = getWindowManager();
                    Intrinsics.checkNotNullExpressionValue(windowManager, "windowManager");
                    Display defaultDisplay = windowManager.getDefaultDisplay();
                    Intrinsics.checkNotNullExpressionValue(defaultDisplay, "winManage.defaultDisplay");
                    this.dialog = new ShapeLoadingDialog.Builder(this).setScreenAndStatus(defaultDisplay.getHeight(), AppToolsKt.getStatusBarHeight(this)).build();
                }
                ShapeLoadingDialog shapeLoadingDialog = this.dialog;
                Intrinsics.checkNotNull(shapeLoadingDialog);
                shapeLoadingDialog.show();
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.BaseActivity$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseActivity.m274showLoadingDialog$lambda1(this.f$0);
                    }
                }, WorkRequest.MIN_BACKOFF_MILLIS);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showLoadingDialog$lambda-1, reason: not valid java name */
    public static final void m274showLoadingDialog$lambda1(BaseActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
    }

    public final void showLoadingDialogTimeoutNotCancel(int timeout) {
        try {
            if (getActivity() != null) {
                Activity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                if (activity.isDestroyed()) {
                    return;
                }
                if (this.dialog == null) {
                    WindowManager windowManager = getWindowManager();
                    Intrinsics.checkNotNullExpressionValue(windowManager, "windowManager");
                    Display defaultDisplay = windowManager.getDefaultDisplay();
                    Intrinsics.checkNotNullExpressionValue(defaultDisplay, "winManage.defaultDisplay");
                    this.dialog = new ShapeLoadingDialog.Builder(this).cancelable(false).setScreenAndStatus(defaultDisplay.getHeight(), AppToolsKt.getStatusBarHeight(this)).build();
                }
                ShapeLoadingDialog shapeLoadingDialog = this.dialog;
                Intrinsics.checkNotNull(shapeLoadingDialog);
                shapeLoadingDialog.show();
                ShapeLoadingDialog shapeLoadingDialog2 = this.dialog;
                Intrinsics.checkNotNull(shapeLoadingDialog2);
                shapeLoadingDialog2.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.qcwireless.qcwatch.ui.base.BaseActivity.showLoadingDialogTimeoutNotCancel.1
                    @Override // android.content.DialogInterface.OnKeyListener
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode != 4) {
                            return false;
                        }
                        XLog.i("------------");
                        return true;
                    }
                });
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.BaseActivity$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseActivity.m276showLoadingDialogTimeoutNotCancel$lambda2(this.f$0);
                    }
                }, timeout);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showLoadingDialogTimeoutNotCancel$lambda-2, reason: not valid java name */
    public static final void m276showLoadingDialogTimeoutNotCancel$lambda2(BaseActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
    }

    public final void showLoadingDialogTimeout(int timeout) {
        try {
            if (getActivity() != null) {
                Activity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                if (activity.isDestroyed()) {
                    return;
                }
                if (this.dialog == null) {
                    WindowManager windowManager = getWindowManager();
                    Intrinsics.checkNotNullExpressionValue(windowManager, "windowManager");
                    Display defaultDisplay = windowManager.getDefaultDisplay();
                    Intrinsics.checkNotNullExpressionValue(defaultDisplay, "winManage.defaultDisplay");
                    this.dialog = new ShapeLoadingDialog.Builder(this).setScreenAndStatus(defaultDisplay.getHeight(), AppToolsKt.getStatusBarHeight(this)).build();
                }
                ShapeLoadingDialog shapeLoadingDialog = this.dialog;
                Intrinsics.checkNotNull(shapeLoadingDialog);
                shapeLoadingDialog.show();
                this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.BaseActivity$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseActivity.m275showLoadingDialogTimeout$lambda3(this.f$0);
                    }
                }, timeout);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showLoadingDialogTimeout$lambda-3, reason: not valid java name */
    public static final void m275showLoadingDialogTimeout$lambda3(BaseActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
    }

    public final boolean isDialogShowing() {
        ShapeLoadingDialog shapeLoadingDialog;
        if (getActivity() == null || (shapeLoadingDialog = this.dialog) == null) {
            return false;
        }
        Intrinsics.checkNotNull(shapeLoadingDialog);
        return shapeLoadingDialog.isShowing();
    }

    public final void dismissLoadingDialog() {
        ShapeLoadingDialog shapeLoadingDialog;
        try {
            if (getActivity() != null) {
                Activity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                if (activity.isDestroyed() || (shapeLoadingDialog = this.dialog) == null) {
                    return;
                }
                Intrinsics.checkNotNull(shapeLoadingDialog);
                if (shapeLoadingDialog.isShowing()) {
                    ShapeLoadingDialog shapeLoadingDialog2 = this.dialog;
                    Intrinsics.checkNotNull(shapeLoadingDialog2);
                    shapeLoadingDialog2.dismiss();
                }
            }
        } catch (Exception unused) {
        }
    }
}
