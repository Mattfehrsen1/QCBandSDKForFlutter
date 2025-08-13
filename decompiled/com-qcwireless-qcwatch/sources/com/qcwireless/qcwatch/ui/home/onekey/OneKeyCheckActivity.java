package com.qcwireless.qcwatch.ui.home.onekey;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.rsp.StartHeartRateRsp;
import com.oudmon.ble.base.util.AppUtil;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityOneKeyCheckBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.OneKeySupport;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastOneKeyBean;
import com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel;
import com.squareup.moshi.JsonAdapter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: OneKeyCheckActivity.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001b\u001a\u00020\fJ\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u001dH\u0014J\u0010\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#H\u0017J\b\u0010$\u001a\u00020\u001dH\u0014J\b\u0010%\u001a\u00020\u001dH\u0002J\b\u0010&\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00060\u0012R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "animatorSet", "Landroid/animation/AnimatorSet;", "bindBinding", "Lcom/qcwireless/qcwatch/databinding/ActivityOneKeyCheckBinding;", "lastBean", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;", "lastData", "Lcom/oudmon/ble/base/communication/rsp/StartHeartRateRsp;", "mCountDown", "", "mHandler", "Landroid/os/Handler;", "mOxygenArray", "", "runnable", "Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckActivity$CountDownRunnable;", "startOneKey", "", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "createFatigueValue", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "startAnimator", "stopAnimator", "CountDownRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OneKeyCheckActivity extends BaseActivity {
    private AnimatorSet animatorSet;
    private ActivityOneKeyCheckBinding bindBinding;
    private LastOneKeyBean lastBean;
    private StartHeartRateRsp lastData;
    private int mCountDown;
    private final Handler mHandler;
    private final int[] mOxygenArray;
    private final CountDownRunnable runnable;
    private boolean startOneKey;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public OneKeyCheckActivity() {
        final OneKeyCheckActivity oneKeyCheckActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<OneKeyCheckViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final OneKeyCheckViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(oneKeyCheckActivity, Reflection.getOrCreateKotlinClass(OneKeyCheckViewModel.class), qualifier, objArr);
            }
        });
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mCountDown = 30;
        this.lastData = new StartHeartRateRsp();
        this.animatorSet = new AnimatorSet();
        this.mOxygenArray = new int[]{96, 97, 98, 99};
        this.lastBean = new LastOneKeyBean();
        this.runnable = new CountDownRunnable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OneKeyCheckViewModel getViewModel() {
        return (OneKeyCheckViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOneKeyCheckBinding activityOneKeyCheckBindingInflate = ActivityOneKeyCheckBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityOneKeyCheckBindingInflate, "inflate(layoutInflater)");
        this.bindBinding = activityOneKeyCheckBindingInflate;
        if (activityOneKeyCheckBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
            activityOneKeyCheckBindingInflate = null;
        }
        ConstraintLayout root = activityOneKeyCheckBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "bindBinding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.one_key_bg);
        ActivityOneKeyCheckBinding activityOneKeyCheckBinding = this.bindBinding;
        ActivityOneKeyCheckBinding activityOneKeyCheckBinding2 = null;
        if (activityOneKeyCheckBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
            activityOneKeyCheckBinding = null;
        }
        activityOneKeyCheckBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_309));
        ViewKt.gone(activityOneKeyCheckBinding.titleBar.divider);
        try {
            String oneKeySupport = UserConfig.INSTANCE.getInstance().getOneKeySupport();
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<OneKeySupport>() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity$setupViews$lambda-0$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            OneKeySupport oneKeySupport2 = (OneKeySupport) jsonAdapterAdapter.fromJson(oneKeySupport);
            if (oneKeySupport2 != null) {
                XLog.i(oneKeySupport2);
                if (oneKeySupport2.getSupportBloodOxygen()) {
                    ViewKt.visible(activityOneKeyCheckBinding.csl2);
                }
                if (oneKeySupport2.getSupportBloodPressure()) {
                    ViewKt.visible(activityOneKeyCheckBinding.csl4);
                }
                if (oneKeySupport2.getSupportFeature()) {
                    ViewKt.visible(activityOneKeyCheckBinding.csl3);
                }
                if (!UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
                    ViewKt.visible(activityOneKeyCheckBinding.csl2);
                    ViewKt.visible(activityOneKeyCheckBinding.csl4);
                    ViewKt.visible(activityOneKeyCheckBinding.csl3);
                }
            }
        } catch (Exception unused) {
        }
        getViewModel().getLastOneKeyCheck(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        View[] viewArr = new View[1];
        ActivityOneKeyCheckBinding activityOneKeyCheckBinding3 = this.bindBinding;
        if (activityOneKeyCheckBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
        } else {
            activityOneKeyCheckBinding2 = activityOneKeyCheckBinding3;
        }
        viewArr[0] = activityOneKeyCheckBinding2.btnOnekey;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity.setupViews.2
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
                ActivityOneKeyCheckBinding activityOneKeyCheckBinding4 = null;
                if (BleOperateManager.getInstance().isConnected()) {
                    ActivityOneKeyCheckBinding activityOneKeyCheckBinding5 = OneKeyCheckActivity.this.bindBinding;
                    if (activityOneKeyCheckBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                        activityOneKeyCheckBinding5 = null;
                    }
                    ViewKt.visible(activityOneKeyCheckBinding5.tvTestTime);
                    OneKeyCheckActivity.this.mCountDown = 30;
                    OneKeyCheckActivity.this.mHandler.removeCallbacks(OneKeyCheckActivity.this.runnable);
                    OneKeyCheckActivity.this.mHandler.post(OneKeyCheckActivity.this.runnable);
                    OneKeyCheckActivity.this.getViewModel().startOnKey();
                    OneKeyCheckActivity.this.startOneKey = true;
                    OneKeyCheckActivity.this.startAnimator();
                    ActivityOneKeyCheckBinding activityOneKeyCheckBinding6 = OneKeyCheckActivity.this.bindBinding;
                    if (activityOneKeyCheckBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                        activityOneKeyCheckBinding6 = null;
                    }
                    ViewKt.visible(activityOneKeyCheckBinding6.imageLine);
                    ActivityOneKeyCheckBinding activityOneKeyCheckBinding7 = OneKeyCheckActivity.this.bindBinding;
                    if (activityOneKeyCheckBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                    } else {
                        activityOneKeyCheckBinding4 = activityOneKeyCheckBinding7;
                    }
                    ViewKt.gone(activityOneKeyCheckBinding4.btnOnekey);
                    return;
                }
                String string = OneKeyCheckActivity.this.getString(R.string.qc_text_75);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
                GlobalKt.showToast$default(string, 0, 1, null);
                ActivityOneKeyCheckBinding activityOneKeyCheckBinding8 = OneKeyCheckActivity.this.bindBinding;
                if (activityOneKeyCheckBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                } else {
                    activityOneKeyCheckBinding4 = activityOneKeyCheckBinding8;
                }
                ViewKt.visible(activityOneKeyCheckBinding4.btnOnekey);
                OneKeyCheckActivity.this.startOneKey = false;
            }
        });
        OneKeyCheckActivity oneKeyCheckActivity = this;
        getViewModel().getUiState().observe(oneKeyCheckActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                OneKeyCheckActivity.m798setupViews$lambda1(this.f$0, (OneKeyCheckViewModel.OneKeyUI) obj);
            }
        });
        getViewModel().getLastBean().observe(oneKeyCheckActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                OneKeyCheckActivity.m799setupViews$lambda3(this.f$0, (OneKeyCheckViewModel.OneKeyLastBean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m798setupViews$lambda1(OneKeyCheckActivity this$0, OneKeyCheckViewModel.OneKeyUI oneKeyUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (oneKeyUI.getError() > 0) {
            String string = this$0.getString(R.string.qc_text_312);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_312)");
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding = null;
            GlobalKt.showToast$default(string, 0, 1, null);
            this$0.mHandler.removeCallbacks(this$0.runnable);
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding2 = this$0.bindBinding;
            if (activityOneKeyCheckBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                activityOneKeyCheckBinding2 = null;
            }
            activityOneKeyCheckBinding2.tvTestTime.setText("");
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding3 = this$0.bindBinding;
            if (activityOneKeyCheckBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                activityOneKeyCheckBinding3 = null;
            }
            ViewKt.invisible(activityOneKeyCheckBinding3.tvTestTime);
            this$0.stopAnimator();
            this$0.mCountDown = 30;
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding4 = this$0.bindBinding;
            if (activityOneKeyCheckBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                activityOneKeyCheckBinding4 = null;
            }
            ViewKt.gone(activityOneKeyCheckBinding4.imageLine);
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding5 = this$0.bindBinding;
            if (activityOneKeyCheckBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
            } else {
                activityOneKeyCheckBinding = activityOneKeyCheckBinding5;
            }
            ViewKt.visible(activityOneKeyCheckBinding.btnOnekey);
            this$0.startOneKey = false;
            return;
        }
        this$0.lastData = oneKeyUI.getResp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m799setupViews$lambda3(final OneKeyCheckActivity this$0, final OneKeyCheckViewModel.OneKeyLastBean oneKeyLastBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.lastBean = oneKeyLastBean.getLast();
        ActivityOneKeyCheckBinding activityOneKeyCheckBinding = this$0.bindBinding;
        if (activityOneKeyCheckBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
            activityOneKeyCheckBinding = null;
        }
        ThreadExtKt.ktxRunOnUi(activityOneKeyCheckBinding, new Function1<ActivityOneKeyCheckBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity$setupViews$4$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActivityOneKeyCheckBinding activityOneKeyCheckBinding2) {
                invoke2(activityOneKeyCheckBinding2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ActivityOneKeyCheckBinding ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                if (oneKeyLastBean.getLast().getHr() > 0) {
                    ktxRunOnUi.tvHrValue.setText(String.valueOf(oneKeyLastBean.getLast().getHr()));
                    TextView textView = ktxRunOnUi.tvBpValue;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Math.abs(oneKeyLastBean.getLast().getSbp()));
                    sb.append('/');
                    sb.append(Math.abs(oneKeyLastBean.getLast().getDbp()));
                    textView.setText(sb.toString());
                    ktxRunOnUi.tvBoValue.setText(String.valueOf(oneKeyLastBean.getLast().getBo()));
                    if (oneKeyLastBean.getLast().getFatigue() >= 80) {
                        ktxRunOnUi.tvFatigueValue.setText(this$0.getString(R.string.qc_text_314));
                    } else {
                        ktxRunOnUi.tvFatigueValue.setText(this$0.getString(R.string.qc_text_315));
                    }
                    ActivityOneKeyCheckBinding activityOneKeyCheckBinding2 = this$0.bindBinding;
                    if (activityOneKeyCheckBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                        activityOneKeyCheckBinding2 = null;
                    }
                    TextView textView2 = activityOneKeyCheckBinding2.tvTestTime;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this$0.getString(R.string.qc_text_335);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_335)");
                    String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(oneKeyLastBean.getLast().getScore())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                    textView2.setText(str);
                    return;
                }
                ktxRunOnUi.tvHrValue.setText("--");
                ktxRunOnUi.tvBpValue.setText("--");
                ktxRunOnUi.tvBoValue.setText("--");
                ktxRunOnUi.tvFatigueValue.setText("--");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: OneKeyCheckActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckActivity$CountDownRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/onekey/OneKeyCheckActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class CountDownRunnable implements Runnable {
        public CountDownRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding = null;
            if (OneKeyCheckActivity.this.mCountDown <= 0) {
                ActivityOneKeyCheckBinding activityOneKeyCheckBinding2 = OneKeyCheckActivity.this.bindBinding;
                if (activityOneKeyCheckBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                } else {
                    activityOneKeyCheckBinding = activityOneKeyCheckBinding2;
                }
                final OneKeyCheckActivity oneKeyCheckActivity = OneKeyCheckActivity.this;
                ThreadExtKt.ktxRunOnUi(activityOneKeyCheckBinding, new Function1<ActivityOneKeyCheckBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.onekey.OneKeyCheckActivity$CountDownRunnable$run$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ActivityOneKeyCheckBinding activityOneKeyCheckBinding3) {
                        invoke2(activityOneKeyCheckBinding3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ActivityOneKeyCheckBinding ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ActivityOneKeyCheckBinding activityOneKeyCheckBinding3 = oneKeyCheckActivity.bindBinding;
                        ActivityOneKeyCheckBinding activityOneKeyCheckBinding4 = null;
                        if (activityOneKeyCheckBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                            activityOneKeyCheckBinding3 = null;
                        }
                        ViewKt.visible(activityOneKeyCheckBinding3.btnOnekey);
                        oneKeyCheckActivity.mHandler.removeCallbacks(oneKeyCheckActivity.runnable);
                        ActivityOneKeyCheckBinding activityOneKeyCheckBinding5 = oneKeyCheckActivity.bindBinding;
                        if (activityOneKeyCheckBinding5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                            activityOneKeyCheckBinding5 = null;
                        }
                        ViewKt.invisible(activityOneKeyCheckBinding5.tvTestTime);
                        oneKeyCheckActivity.stopAnimator();
                        oneKeyCheckActivity.mCountDown = 30;
                        ActivityOneKeyCheckBinding activityOneKeyCheckBinding6 = oneKeyCheckActivity.bindBinding;
                        if (activityOneKeyCheckBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                            activityOneKeyCheckBinding6 = null;
                        }
                        ViewKt.gone(activityOneKeyCheckBinding6.imageLine);
                        oneKeyCheckActivity.getViewModel().stopOnKey();
                        if (oneKeyCheckActivity.lastData.getValue() > 0) {
                            ActivityOneKeyCheckBinding activityOneKeyCheckBinding7 = oneKeyCheckActivity.bindBinding;
                            if (activityOneKeyCheckBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                                activityOneKeyCheckBinding7 = null;
                            }
                            activityOneKeyCheckBinding7.tvTestTime.setText("");
                            ActivityOneKeyCheckBinding activityOneKeyCheckBinding8 = oneKeyCheckActivity.bindBinding;
                            if (activityOneKeyCheckBinding8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                                activityOneKeyCheckBinding8 = null;
                            }
                            ViewKt.gone(activityOneKeyCheckBinding8.imageLine);
                            int i = oneKeyCheckActivity.mOxygenArray[new Random().nextInt(4)];
                            int iCreateFatigueValue = oneKeyCheckActivity.createFatigueValue();
                            if (iCreateFatigueValue >= 80) {
                                ktxRunOnUi.tvFatigueValue.setText(oneKeyCheckActivity.getString(R.string.qc_text_314));
                            } else {
                                ktxRunOnUi.tvFatigueValue.setText(oneKeyCheckActivity.getString(R.string.qc_text_315));
                            }
                            ktxRunOnUi.tvBoValue.setText(String.valueOf(i));
                            oneKeyCheckActivity.lastBean.setLastTime(new DateUtil().getUnixTimestamp());
                            oneKeyCheckActivity.lastBean.setHr(oneKeyCheckActivity.lastData.getValue());
                            oneKeyCheckActivity.lastBean.setSbp(Math.abs(oneKeyCheckActivity.lastData.getSbp()));
                            oneKeyCheckActivity.lastBean.setDbp(Math.abs(oneKeyCheckActivity.lastData.getDbp()));
                            oneKeyCheckActivity.lastBean.setBo(i);
                            oneKeyCheckActivity.lastBean.setFatigue(iCreateFatigueValue);
                            int iNextInt = new Random().nextInt(4) + 96;
                            oneKeyCheckActivity.lastBean.setScore(iNextInt);
                            oneKeyCheckActivity.getViewModel().saveLastOneKeyCheck(oneKeyCheckActivity.lastBean);
                            ActivityOneKeyCheckBinding activityOneKeyCheckBinding9 = oneKeyCheckActivity.bindBinding;
                            if (activityOneKeyCheckBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                                activityOneKeyCheckBinding9 = null;
                            }
                            TextView textView = activityOneKeyCheckBinding9.tvTestTime;
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            String string = oneKeyCheckActivity.getString(R.string.qc_text_335);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_335)");
                            String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(iNextInt)}, 1));
                            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                            textView.setText(str);
                            ActivityOneKeyCheckBinding activityOneKeyCheckBinding10 = oneKeyCheckActivity.bindBinding;
                            if (activityOneKeyCheckBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                                activityOneKeyCheckBinding10 = null;
                            }
                            ViewKt.visible(activityOneKeyCheckBinding10.tvTestTime);
                            ActivityOneKeyCheckBinding activityOneKeyCheckBinding11 = oneKeyCheckActivity.bindBinding;
                            if (activityOneKeyCheckBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                            } else {
                                activityOneKeyCheckBinding4 = activityOneKeyCheckBinding11;
                            }
                            TextView textView2 = activityOneKeyCheckBinding4.tvTestTime;
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            String string2 = oneKeyCheckActivity.getString(R.string.qc_text_335);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_335)");
                            String str2 = String.format(string2, Arrays.copyOf(new Object[]{String.valueOf(iNextInt)}, 1));
                            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                            textView2.setText(str2);
                            ktxRunOnUi.tvHrValue.setText(String.valueOf((int) oneKeyCheckActivity.lastData.getValue()));
                            TextView textView3 = ktxRunOnUi.tvBpValue;
                            StringBuilder sb = new StringBuilder();
                            sb.append(oneKeyCheckActivity.lastData.getSbp());
                            sb.append('/');
                            sb.append(oneKeyCheckActivity.lastData.getDbp());
                            textView3.setText(sb.toString());
                            return;
                        }
                        ktxRunOnUi.tvHrValue.setText("--");
                        ktxRunOnUi.tvBpValue.setText("--");
                        ktxRunOnUi.tvBoValue.setText("--");
                        ktxRunOnUi.tvFatigueValue.setText("--");
                    }
                });
                return;
            }
            if (AppUtil.isBackground(QCApplication.INSTANCE.getCONTEXT()) || AppUtil.isApplicationBroughtToBackground(QCApplication.INSTANCE.getCONTEXT())) {
                ActivityOneKeyCheckBinding activityOneKeyCheckBinding3 = OneKeyCheckActivity.this.bindBinding;
                if (activityOneKeyCheckBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                    activityOneKeyCheckBinding3 = null;
                }
                ViewKt.visible(activityOneKeyCheckBinding3.btnOnekey);
                String string = OneKeyCheckActivity.this.getString(R.string.qc_text_312);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_312)");
                GlobalKt.showToast$default(string, 0, 1, null);
                OneKeyCheckActivity.this.mHandler.removeCallbacks(OneKeyCheckActivity.this.runnable);
                ActivityOneKeyCheckBinding activityOneKeyCheckBinding4 = OneKeyCheckActivity.this.bindBinding;
                if (activityOneKeyCheckBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                    activityOneKeyCheckBinding4 = null;
                }
                ViewKt.invisible(activityOneKeyCheckBinding4.tvTestTime);
                OneKeyCheckActivity.this.stopAnimator();
                OneKeyCheckActivity.this.mCountDown = 30;
                ActivityOneKeyCheckBinding activityOneKeyCheckBinding5 = OneKeyCheckActivity.this.bindBinding;
                if (activityOneKeyCheckBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                } else {
                    activityOneKeyCheckBinding = activityOneKeyCheckBinding5;
                }
                ViewKt.gone(activityOneKeyCheckBinding.imageLine);
                OneKeyCheckActivity.this.getViewModel().stopOnKey();
                return;
            }
            OneKeyCheckActivity.this.mHandler.postDelayed(this, 1000L);
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding6 = OneKeyCheckActivity.this.bindBinding;
            if (activityOneKeyCheckBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
            } else {
                activityOneKeyCheckBinding = activityOneKeyCheckBinding6;
            }
            TextView textView = activityOneKeyCheckBinding.tvTestTime;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = OneKeyCheckActivity.this.getString(R.string.qc_text_311);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_311)");
            StringBuilder sb = new StringBuilder();
            sb.append(OneKeyCheckActivity.this.mCountDown);
            sb.append('s');
            String str = String.format(string2, Arrays.copyOf(new Object[]{sb.toString()}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            textView.setText(str);
            XLog.i(Integer.valueOf(OneKeyCheckActivity.this.mCountDown));
            OneKeyCheckActivity oneKeyCheckActivity2 = OneKeyCheckActivity.this;
            oneKeyCheckActivity2.mCountDown--;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAnimator() {
        try {
            stopAnimator();
            ActivityOneKeyCheckBinding activityOneKeyCheckBinding = this.bindBinding;
            if (activityOneKeyCheckBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bindBinding");
                activityOneKeyCheckBinding = null;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(activityOneKeyCheckBinding.imageLine, "translationY", 0.0f, GlobalKt.dp2px(this, 140.0f), 0.0f);
            objectAnimatorOfFloat.setRepeatCount(10);
            this.animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat);
            this.animatorSet.setDuration(5000L);
            this.animatorSet.start();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopAnimator() {
        this.animatorSet.end();
    }

    public final int createFatigueValue() {
        int iNextInt;
        int i;
        int iNextInt2;
        int i2 = Calendar.getInstance().get(11);
        int fatigue = this.lastBean.getFatigue();
        if (new DateUtil().getUnixTimestamp() - this.lastBean.getLastTime() > 1800) {
            if (5 <= i2 && i2 < 11) {
                iNextInt2 = new Random().nextInt(20);
            } else {
                if (i2 >= 22 || i2 < 5) {
                    iNextInt = new Random().nextInt(20);
                } else {
                    if (12 <= i2 && i2 < 14) {
                        if (new Random().nextInt(10) < 7) {
                            iNextInt = new Random().nextInt(20);
                        } else {
                            iNextInt2 = new Random().nextInt(20);
                        }
                    } else if (new Random().nextBoolean()) {
                        iNextInt = new Random().nextInt(20);
                    } else {
                        iNextInt2 = new Random().nextInt(20);
                    }
                    fatigue = i;
                    this.lastBean.setLastTime(System.currentTimeMillis());
                    this.lastBean.setFatigue(fatigue);
                }
                i = iNextInt + 40;
                fatigue = i;
                this.lastBean.setLastTime(System.currentTimeMillis());
                this.lastBean.setFatigue(fatigue);
            }
            i = iNextInt2 + 80;
            fatigue = i;
            this.lastBean.setLastTime(System.currentTimeMillis());
            this.lastBean.setFatigue(fatigue);
        }
        return fatigue;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        this.startOneKey = false;
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacks(this.runnable);
        if (this.startOneKey) {
            getViewModel().stopOnKey();
        }
    }
}
