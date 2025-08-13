package com.qcwireless.qcwatch.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.work.WorkRequest;
import com.qcwireless.qcwatch.base.dialog.loading.ShapeLoadingDialog;
import com.qcwireless.qcwatch.ui.base.util.AppToolsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseFragment.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001:\u00018B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0018J\b\u0010\u001f\u001a\u00020\u001aH\u0016J\u0012\u0010 \u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%H\u0016J\u0012\u0010&\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J&\u0010'\u001a\u0004\u0018\u00010\u00162\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010,\u001a\u00020\u001aH\u0016J\b\u0010-\u001a\u00020\u001aH\u0016J\b\u0010.\u001a\u00020\u001aH\u0016J\b\u0010/\u001a\u00020\u001aH\u0016J\b\u00100\u001a\u00020\u001aH\u0016J\b\u00101\u001a\u00020\u001aH\u0016J\b\u00102\u001a\u00020\u001aH\u0016J\u001a\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u00162\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0006\u00105\u001a\u00020\u001aJ\u000e\u00106\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u001dR\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\u00060\u0010R\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "dialog", "Lcom/qcwireless/qcwatch/base/dialog/loading/ShapeLoadingDialog;", "dialogCallback", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment$DialogCallback;", "getDialogCallback", "()Lcom/qcwireless/qcwatch/ui/base/BaseFragment$DialogCallback;", "handler", "Landroid/os/Handler;", "loadErrorView", "Landroid/view/View;", "mHasLoadedData", "", "dismissLoadingDialog", "", "dismissLoadingDialogDelay", "delay", "", "isDialogShowing", "loadDataOnce", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreate", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onDetach", "onPause", "onResume", "onStart", "onStop", "onViewCreated", "view", "showLoadingDialog", "showLoadingDialogTimeout", "timeout", "DialogCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class BaseFragment extends Fragment {
    private final String TAG;
    public Activity activity;
    private ShapeLoadingDialog dialog;
    private final DialogCallback dialogCallback;
    private final Handler handler;
    private View loadErrorView;
    private boolean mHasLoadedData;

    public void loadDataOnce() {
    }

    public BaseFragment() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.base.BaseFragment$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this.javaClass.simpleName");
        this.TAG = simpleName;
        this.dialogCallback = new DialogCallback();
    }

    public final Activity getActivity() {
        Activity activity = this.activity;
        if (activity != null) {
            return activity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("activity");
        return null;
    }

    public final void setActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.activity = activity;
    }

    protected final String getTAG() {
        return this.TAG;
    }

    public final DialogCallback getDialogCallback() {
        return this.dialogCallback;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        setActivity(fragmentActivityRequireActivity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mHasLoadedData) {
            return;
        }
        loadDataOnce();
        this.mHasLoadedData = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    public final void showLoadingDialog() {
        try {
            if (this.dialog == null) {
                WindowManager windowManager = getActivity().getWindowManager();
                Intrinsics.checkNotNullExpressionValue(windowManager, "activity.windowManager");
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Intrinsics.checkNotNullExpressionValue(defaultDisplay, "winManage.defaultDisplay");
                this.dialog = new ShapeLoadingDialog.Builder(getContext()).setScreenAndStatus(defaultDisplay.getHeight(), AppToolsKt.getStatusBarHeight(getActivity())).build();
            }
            ShapeLoadingDialog shapeLoadingDialog = this.dialog;
            Intrinsics.checkNotNull(shapeLoadingDialog);
            shapeLoadingDialog.show();
            this.handler.removeCallbacks(this.dialogCallback);
            this.handler.postDelayed(this.dialogCallback, WorkRequest.MIN_BACKOFF_MILLIS);
        } catch (Exception unused) {
        }
    }

    public final void showLoadingDialogTimeout(int timeout) {
        try {
            if (this.dialog == null) {
                WindowManager windowManager = getActivity().getWindowManager();
                Intrinsics.checkNotNullExpressionValue(windowManager, "activity.windowManager");
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Intrinsics.checkNotNullExpressionValue(defaultDisplay, "winManage.defaultDisplay");
                this.dialog = new ShapeLoadingDialog.Builder(getContext()).setScreenAndStatus(defaultDisplay.getHeight(), AppToolsKt.getStatusBarHeight(getActivity())).build();
            }
            ShapeLoadingDialog shapeLoadingDialog = this.dialog;
            Intrinsics.checkNotNull(shapeLoadingDialog);
            shapeLoadingDialog.show();
            this.handler.removeCallbacks(this.dialogCallback);
            this.handler.postDelayed(this.dialogCallback, timeout);
        } catch (Exception unused) {
        }
    }

    /* compiled from: BaseFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/BaseFragment$DialogCallback;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/base/BaseFragment;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class DialogCallback implements Runnable {
        public DialogCallback() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseFragment.this.dismissLoadingDialog();
        }
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
        if (getActivity() == null || (shapeLoadingDialog = this.dialog) == null) {
            return;
        }
        Intrinsics.checkNotNull(shapeLoadingDialog);
        if (shapeLoadingDialog.isShowing()) {
            ShapeLoadingDialog shapeLoadingDialog2 = this.dialog;
            Intrinsics.checkNotNull(shapeLoadingDialog2);
            shapeLoadingDialog2.dismiss();
        }
    }

    public final void dismissLoadingDialogDelay(int delay) {
        if (getActivity() == null) {
            return;
        }
        this.handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.BaseFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BaseFragment.m277dismissLoadingDialogDelay$lambda0(this.f$0);
            }
        }, delay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dismissLoadingDialogDelay$lambda-0, reason: not valid java name */
    public static final void m277dismissLoadingDialogDelay$lambda0(BaseFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShapeLoadingDialog shapeLoadingDialog = this$0.dialog;
        if (shapeLoadingDialog != null) {
            Intrinsics.checkNotNull(shapeLoadingDialog);
            if (shapeLoadingDialog.isShowing()) {
                ShapeLoadingDialog shapeLoadingDialog2 = this$0.dialog;
                Intrinsics.checkNotNull(shapeLoadingDialog2);
                shapeLoadingDialog2.dismiss();
            }
        }
    }
}
