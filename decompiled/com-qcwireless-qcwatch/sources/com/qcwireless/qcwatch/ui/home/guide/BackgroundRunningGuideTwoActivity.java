package com.qcwireless.qcwatch.ui.home.guide;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityBackgroundRunningGuideTwoBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel;
import com.qcwireless.qcwatch.ui.mine.guide.GuideActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;

/* compiled from: BackgroundRunningGuideTwoActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\"\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\fH\u0014J\b\u0010\u0018\u001a\u00020\fH\u0002J\b\u0010\u0019\u001a\u00020\fH\u0014J\b\u0010\u001a\u001a\u00020\fH\u0002J\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/guide/BackgroundRunningGuideTwoActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBackgroundRunningGuideTwoBinding;", "handler", "Landroid/os/Handler;", "myRunnable", "Lcom/qcwireless/qcwatch/ui/home/guide/BackgroundRunningGuideTwoActivity$MyRunnable;", "checkPermission", "", "doRefresh", "", "isNotificationListenerEnabled", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "openBatteryWhiteList", "setupViews", "showNotificationDialog", "showSelectDialog", "type", "MyRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BackgroundRunningGuideTwoActivity extends BaseActivity {
    private ActivityBackgroundRunningGuideTwoBinding binding;
    private final Handler handler;
    private final MyRunnable myRunnable = new MyRunnable();

    public BackgroundRunningGuideTwoActivity() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideTwoActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBindingInflate = ActivityBackgroundRunningGuideTwoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBackgroundRunningGuideTwoBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBackgroundRunningGuideTwoBindingInflate;
        if (activityBackgroundRunningGuideTwoBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundRunningGuideTwoBindingInflate = null;
        }
        ConstraintLayout root = activityBackgroundRunningGuideTwoBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        final ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding = this.binding;
        if (activityBackgroundRunningGuideTwoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundRunningGuideTwoBinding = null;
        }
        activityBackgroundRunningGuideTwoBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_247));
        GlobalKt.setOnClickListener(new View[]{activityBackgroundRunningGuideTwoBinding.tvStatusText1, activityBackgroundRunningGuideTwoBinding.tvStatusText2, activityBackgroundRunningGuideTwoBinding.tvStatusText3, activityBackgroundRunningGuideTwoBinding.tvStatusText4, activityBackgroundRunningGuideTwoBinding.tvStatusText5, activityBackgroundRunningGuideTwoBinding.image1Right, activityBackgroundRunningGuideTwoBinding.imageBatteryRight, activityBackgroundRunningGuideTwoBinding.image2Right, activityBackgroundRunningGuideTwoBinding.image3Right, activityBackgroundRunningGuideTwoBinding.image4Right}, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideTwoActivity$setupViews$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                if (Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.image1Right) ? true : Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.tvStatusText1)) {
                    this.showNotificationDialog();
                    return;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.tvStatusText2)) {
                    this.showSelectDialog(2);
                    return;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.tvStatusText3)) {
                    this.showSelectDialog(3);
                    return;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.tvStatusText4)) {
                    this.showSelectDialog(4);
                    return;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.tvStatusText5) ? true : Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.imageBatteryRight)) {
                    if (this.checkPermission()) {
                        return;
                    }
                    this.openBatteryWhiteList();
                    return;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.image2Right) ? true : Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.image3Right) ? true : Intrinsics.areEqual(setOnClickListener, activityBackgroundRunningGuideTwoBinding.image4Right)) {
                    BackgroundRunningGuideTwoActivity backgroundRunningGuideTwoActivity = this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(backgroundRunningGuideTwoActivity, (Class<?>) GuideActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    backgroundRunningGuideTwoActivity.startActivity(intent);
                }
            }
        });
        doRefresh();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding = null;
        if (isNotificationListenerEnabled()) {
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding2 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundRunningGuideTwoBinding2 = null;
            }
            activityBackgroundRunningGuideTwoBinding2.tvStatusText1.setText(R.string.qc_text_411);
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding3 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundRunningGuideTwoBinding3 = null;
            }
            activityBackgroundRunningGuideTwoBinding3.tvStatusText1.setBackgroundResource(R.drawable.bg_corners_bg_text_1);
        } else {
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding4 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundRunningGuideTwoBinding4 = null;
            }
            activityBackgroundRunningGuideTwoBinding4.tvStatusText1.setText(R.string.qc_text_410);
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding5 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundRunningGuideTwoBinding5 = null;
            }
            activityBackgroundRunningGuideTwoBinding5.tvStatusText1.setBackgroundResource(R.drawable.bg_corners_bg_text_2);
        }
        if (!checkPermission()) {
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding6 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundRunningGuideTwoBinding6 = null;
            }
            activityBackgroundRunningGuideTwoBinding6.tvStatusText5.setText(R.string.qc_text_410);
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding7 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBackgroundRunningGuideTwoBinding = activityBackgroundRunningGuideTwoBinding7;
            }
            activityBackgroundRunningGuideTwoBinding.tvStatusText5.setBackgroundResource(R.drawable.bg_corners_bg_text_2);
        } else {
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding8 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundRunningGuideTwoBinding8 = null;
            }
            activityBackgroundRunningGuideTwoBinding8.tvStatusText5.setText(R.string.qc_text_411);
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding9 = this.binding;
            if (activityBackgroundRunningGuideTwoBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBackgroundRunningGuideTwoBinding = activityBackgroundRunningGuideTwoBinding9;
            }
            activityBackgroundRunningGuideTwoBinding.tvStatusText5.setBackgroundResource(R.drawable.bg_corners_bg_text_1);
        }
        this.handler.removeCallbacks(this.myRunnable);
        this.handler.postDelayed(this.myRunnable, 5000L);
    }

    /* compiled from: BackgroundRunningGuideTwoActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/guide/BackgroundRunningGuideTwoActivity$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/guide/BackgroundRunningGuideTwoActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding = null;
            if (!BackgroundRunningGuideTwoActivity.this.checkPermission()) {
                ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding2 = BackgroundRunningGuideTwoActivity.this.binding;
                if (activityBackgroundRunningGuideTwoBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBackgroundRunningGuideTwoBinding2 = null;
                }
                activityBackgroundRunningGuideTwoBinding2.tvStatusText5.setText(R.string.qc_text_410);
                ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding3 = BackgroundRunningGuideTwoActivity.this.binding;
                if (activityBackgroundRunningGuideTwoBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityBackgroundRunningGuideTwoBinding = activityBackgroundRunningGuideTwoBinding3;
                }
                activityBackgroundRunningGuideTwoBinding.tvStatusText5.setBackgroundResource(R.drawable.bg_corners_bg_text_2);
                return;
            }
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding4 = BackgroundRunningGuideTwoActivity.this.binding;
            if (activityBackgroundRunningGuideTwoBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundRunningGuideTwoBinding4 = null;
            }
            activityBackgroundRunningGuideTwoBinding4.tvStatusText5.setText(R.string.qc_text_411);
            ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding5 = BackgroundRunningGuideTwoActivity.this.binding;
            if (activityBackgroundRunningGuideTwoBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBackgroundRunningGuideTwoBinding = activityBackgroundRunningGuideTwoBinding5;
            }
            activityBackgroundRunningGuideTwoBinding.tvStatusText5.setBackgroundResource(R.drawable.bg_corners_bg_text_1);
        }
    }

    private final void doRefresh() {
        ActivityBackgroundRunningGuideTwoBinding activityBackgroundRunningGuideTwoBinding = this.binding;
        if (activityBackgroundRunningGuideTwoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundRunningGuideTwoBinding = null;
        }
        if (UserConfig.INSTANCE.getInstance().getWarmingAutoStart()) {
            activityBackgroundRunningGuideTwoBinding.tvStatusText2.setText(R.string.qc_text_411);
            activityBackgroundRunningGuideTwoBinding.tvStatusText2.setBackgroundResource(R.drawable.bg_corners_bg_text_1);
        } else {
            activityBackgroundRunningGuideTwoBinding.tvStatusText2.setText(R.string.qc_text_410);
            activityBackgroundRunningGuideTwoBinding.tvStatusText2.setBackgroundResource(R.drawable.bg_corners_bg_text_2);
        }
        if (UserConfig.INSTANCE.getInstance().getWarmingLock()) {
            activityBackgroundRunningGuideTwoBinding.tvStatusText3.setText(R.string.qc_text_411);
            activityBackgroundRunningGuideTwoBinding.tvStatusText3.setBackgroundResource(R.drawable.bg_corners_bg_text_1);
        } else {
            activityBackgroundRunningGuideTwoBinding.tvStatusText3.setText(R.string.qc_text_410);
            activityBackgroundRunningGuideTwoBinding.tvStatusText3.setBackgroundResource(R.drawable.bg_corners_bg_text_2);
        }
        if (UserConfig.INSTANCE.getInstance().getWarmingBatteryAllow()) {
            activityBackgroundRunningGuideTwoBinding.tvStatusText4.setText(R.string.qc_text_411);
            activityBackgroundRunningGuideTwoBinding.tvStatusText4.setBackgroundResource(R.drawable.bg_corners_bg_text_1);
        } else {
            activityBackgroundRunningGuideTwoBinding.tvStatusText4.setText(R.string.qc_text_410);
            activityBackgroundRunningGuideTwoBinding.tvStatusText4.setBackgroundResource(R.drawable.bg_corners_bg_text_2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showSelectDialog(final int type) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_guide);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_take_photo);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_take_picture);
        ((TextView) contentView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideTwoActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BackgroundRunningGuideTwoActivity.m699showSelectDialog$lambda2(objectRef, view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideTwoActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BackgroundRunningGuideTwoActivity.m700showSelectDialog$lambda3(objectRef, type, this, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideTwoActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BackgroundRunningGuideTwoActivity.m701showSelectDialog$lambda4(type, objectRef, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showSelectDialog$lambda-2, reason: not valid java name */
    public static final void m699showSelectDialog$lambda2(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showSelectDialog$lambda-3, reason: not valid java name */
    public static final void m700showSelectDialog$lambda3(Ref.ObjectRef bottomDialog, int i, BackgroundRunningGuideTwoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((BottomDialog) bottomDialog.element).dismiss();
        if (i == 2) {
            UserConfig.INSTANCE.getInstance().setWarmingAutoStart(false);
        } else if (i == 3) {
            UserConfig.INSTANCE.getInstance().setWarmingLock(false);
        } else if (i == 4) {
            UserConfig.INSTANCE.getInstance().setWarmingBatteryAllow(false);
        }
        UserConfig.INSTANCE.getInstance().save();
        this$0.doRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showSelectDialog$lambda-4, reason: not valid java name */
    public static final void m701showSelectDialog$lambda4(int i, Ref.ObjectRef bottomDialog, BackgroundRunningGuideTwoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 2) {
            UserConfig.INSTANCE.getInstance().setWarmingAutoStart(true);
        } else if (i == 3) {
            UserConfig.INSTANCE.getInstance().setWarmingLock(true);
        } else if (i == 4) {
            UserConfig.INSTANCE.getInstance().setWarmingBatteryAllow(true);
        }
        UserConfig.INSTANCE.getInstance().save();
        ((BottomDialog) bottomDialog.element).dismiss();
        this$0.doRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.CenterDialog] */
    public final void showNotificationDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_notification);
        objectRef.element = builder.create();
        ((CenterDialog) objectRef.element).show();
        View contentView = ((CenterDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        ((TextView) contentView.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideTwoActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BackgroundRunningGuideTwoActivity.m698showNotificationDialog$lambda5(objectRef, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showNotificationDialog$lambda-5, reason: not valid java name */
    public static final void m698showNotificationDialog$lambda5(Ref.ObjectRef bottomDialog, BackgroundRunningGuideTwoActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((CenterDialog) bottomDialog.element).dismiss();
        try {
            this$0.startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 100);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final boolean isNotificationListenerEnabled() {
        String packageName = QCApplication.INSTANCE.getCONTEXT().getPackageName();
        String flat = Settings.Secure.getString(QCApplication.INSTANCE.getCONTEXT().getContentResolver(), "enabled_notification_listeners");
        String str = flat;
        if (!TextUtils.isEmpty(str)) {
            Intrinsics.checkNotNullExpressionValue(flat, "flat");
            Object[] array = new Regex(":").split(str, 0).toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            for (String str2 : (String[]) array) {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str2);
                if (componentNameUnflattenFromString != null && TextUtils.equals(packageName, componentNameUnflattenFromString.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openBatteryWhiteList() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, HealthyViewModel.Type_Sync_TimeOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.handler.removeCallbacks(this.myRunnable);
        this.handler.postDelayed(this.myRunnable, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        Object systemService = getSystemService("power");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return ((PowerManager) systemService).isIgnoringBatteryOptimizations(getPackageName());
    }
}
