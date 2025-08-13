package com.qcwireless.qcwatch.ui.device.disturb;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.Observer;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomTimeDialog;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityDisturbBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSwitchView;
import com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DisturbActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0014J\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/disturb/DisturbActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityDisturbBinding;", "disturb", "Lcom/qcwireless/qcwatch/ui/device/disturb/DisturbViewModel$DisturbUI;", "disturbViewModel", "Lcom/qcwireless/qcwatch/ui/device/disturb/DisturbViewModel;", "getDisturbViewModel", "()Lcom/qcwireless/qcwatch/ui/device/disturb/DisturbViewModel;", "disturbViewModel$delegate", "Lkotlin/Lazy;", "manualDND", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "readDisturbFromDevice", "setupViews", "showTimeDialog", "title", "", "time", "", "type", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DisturbActivity extends BaseActivity {
    private ActivityDisturbBinding binding;
    private DisturbViewModel.DisturbUI disturb;

    /* renamed from: disturbViewModel$delegate, reason: from kotlin metadata */
    private final Lazy disturbViewModel;
    private boolean manualDND;

    /* JADX WARN: Multi-variable type inference failed */
    public DisturbActivity() {
        final DisturbActivity disturbActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.disturbViewModel = LazyKt.lazy(new Function0<DisturbViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.disturb.DisturbViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DisturbViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(disturbActivity, Reflection.getOrCreateKotlinClass(DisturbViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DisturbViewModel getDisturbViewModel() {
        return (DisturbViewModel) this.disturbViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDisturbBinding activityDisturbBindingInflate = ActivityDisturbBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDisturbBindingInflate, "inflate(layoutInflater)");
        this.binding = activityDisturbBindingInflate;
        if (activityDisturbBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDisturbBindingInflate = null;
        }
        LinearLayout root = activityDisturbBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        DisturbViewModel disturbViewModel = getDisturbViewModel();
        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
        Intrinsics.checkNotNull(deviceAddress);
        disturbViewModel.getDisturbSwitch(deviceAddress);
        ActivityDisturbBinding activityDisturbBinding = this.binding;
        ActivityDisturbBinding activityDisturbBinding2 = null;
        if (activityDisturbBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDisturbBinding = null;
        }
        activityDisturbBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_13));
        ViewKt.visible(activityDisturbBinding.titleBar.tvRightText);
        activityDisturbBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_79));
        activityDisturbBinding.qcDisturbSwitch.setQSettingItemCheckListener(new QSwitchView.OnSwitchStateChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity$$ExternalSyntheticLambda2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchView.OnSwitchStateChangeListener
            public final void onSwitchStateChange(boolean z) {
                DisturbActivity.m442setupViews$lambda1$lambda0(this.f$0, z);
            }
        });
        activityDisturbBinding.qcDisturbStart.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity$setupViews$1$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DisturbActivity disturbActivity = this.this$0;
                String string = disturbActivity.getString(R.string.qc_text_39);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_39)");
                DisturbViewModel.DisturbUI disturbUI = this.this$0.disturb;
                if (disturbUI == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("disturb");
                    disturbUI = null;
                }
                disturbActivity.showTimeDialog(string, disturbUI.getStart(), 1);
            }
        });
        activityDisturbBinding.qcDisturbEnd.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity$setupViews$1$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DisturbActivity disturbActivity = this.this$0;
                String string = disturbActivity.getString(R.string.qc_text_40);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_40)");
                DisturbViewModel.DisturbUI disturbUI = this.this$0.disturb;
                if (disturbUI == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("disturb");
                    disturbUI = null;
                }
                disturbActivity.showTimeDialog(string, disturbUI.getEnd(), 2);
            }
        });
        getDisturbViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DisturbActivity.m443setupViews$lambda3$lambda2(this.f$0, (DisturbViewModel.DisturbUI) obj);
            }
        });
        View[] viewArr = new View[1];
        ActivityDisturbBinding activityDisturbBinding3 = this.binding;
        if (activityDisturbBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDisturbBinding2 = activityDisturbBinding3;
        }
        viewArr[0] = activityDisturbBinding2.titleBar.tvRightText;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity.setupViews.3
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
                ActivityDisturbBinding activityDisturbBinding4 = DisturbActivity.this.binding;
                DisturbViewModel.DisturbUI disturbUI = null;
                if (activityDisturbBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDisturbBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityDisturbBinding4.titleBar.tvRightText)) {
                    DisturbViewModel disturbViewModel2 = DisturbActivity.this.getDisturbViewModel();
                    String deviceAddress2 = UserConfig.INSTANCE.getInstance().getDeviceAddress();
                    Intrinsics.checkNotNull(deviceAddress2);
                    DisturbViewModel.DisturbUI disturbUI2 = DisturbActivity.this.disturb;
                    if (disturbUI2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("disturb");
                    } else {
                        disturbUI = disturbUI2;
                    }
                    disturbViewModel2.saveDisturbSwitch(deviceAddress2, disturbUI);
                    DisturbActivity.this.finish();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m442setupViews$lambda1$lambda0(DisturbActivity this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DisturbViewModel.DisturbUI disturbUI = this$0.disturb;
        ActivityDisturbBinding activityDisturbBinding = null;
        if (disturbUI == null) {
            Intrinsics.throwUninitializedPropertyAccessException("disturb");
            disturbUI = null;
        }
        disturbUI.setSwitch(z);
        if (z) {
            ActivityDisturbBinding activityDisturbBinding2 = this$0.binding;
            if (activityDisturbBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDisturbBinding = activityDisturbBinding2;
            }
            ViewKt.visible(activityDisturbBinding.disturbGroup);
            return;
        }
        ActivityDisturbBinding activityDisturbBinding3 = this$0.binding;
        if (activityDisturbBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDisturbBinding = activityDisturbBinding3;
        }
        ViewKt.gone(activityDisturbBinding.disturbGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-2, reason: not valid java name */
    public static final void m443setupViews$lambda3$lambda2(DisturbActivity this$0, DisturbViewModel.DisturbUI it) {
        DisturbViewModel.DisturbUI disturbUI;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.disturb = it;
        ActivityDisturbBinding activityDisturbBinding = null;
        if (it == null) {
            Intrinsics.throwUninitializedPropertyAccessException("disturb");
            disturbUI = null;
        } else {
            disturbUI = it;
        }
        if (disturbUI.getSwitch()) {
            ActivityDisturbBinding activityDisturbBinding2 = this$0.binding;
            if (activityDisturbBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDisturbBinding2 = null;
            }
            ViewKt.visible(activityDisturbBinding2.disturbGroup);
        } else {
            ActivityDisturbBinding activityDisturbBinding3 = this$0.binding;
            if (activityDisturbBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDisturbBinding3 = null;
            }
            ViewKt.gone(activityDisturbBinding3.disturbGroup);
        }
        ActivityDisturbBinding activityDisturbBinding4 = this$0.binding;
        if (activityDisturbBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDisturbBinding4 = null;
        }
        activityDisturbBinding4.qcDisturbSwitch.setChecked(it.getSwitch());
        ActivityDisturbBinding activityDisturbBinding5 = this$0.binding;
        if (activityDisturbBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDisturbBinding5 = null;
        }
        activityDisturbBinding5.qcDisturbStart.setRightText(DateUtil.dayMinToStr(it.getStart()));
        ActivityDisturbBinding activityDisturbBinding6 = this$0.binding;
        if (activityDisturbBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDisturbBinding6 = null;
        }
        activityDisturbBinding6.qcDisturbEnd.setRightText(DateUtil.dayMinToStr(it.getEnd()));
        if (this$0.manualDND) {
            ActivityDisturbBinding activityDisturbBinding7 = this$0.binding;
            if (activityDisturbBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDisturbBinding7 = null;
            }
            ViewKt.visible(activityDisturbBinding7.qcSettingDisturbManual);
            ActivityDisturbBinding activityDisturbBinding8 = this$0.binding;
            if (activityDisturbBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDisturbBinding8 = null;
            }
            activityDisturbBinding8.qcSettingDisturbManual.setRightIconGone();
            if (it.getManualSwitch()) {
                ActivityDisturbBinding activityDisturbBinding9 = this$0.binding;
                if (activityDisturbBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDisturbBinding = activityDisturbBinding9;
                }
                activityDisturbBinding.qcSettingDisturbManual.setRightText(this$0.getString(R.string.qc_text_567));
                return;
            }
            ActivityDisturbBinding activityDisturbBinding10 = this$0.binding;
            if (activityDisturbBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDisturbBinding = activityDisturbBinding10;
            }
            activityDisturbBinding.qcSettingDisturbManual.setRightText(this$0.getString(R.string.qc_text_21));
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (messageEvent instanceof BluetoothEvent) {
            if (((BluetoothEvent) messageEvent).getConnect()) {
                return;
            }
            String string = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
            return;
        }
        if (messageEvent instanceof ManualRefreshEvent) {
            this.manualDND = true;
            readDisturbFromDevice();
        }
    }

    private final void readDisturbFromDevice() {
        DisturbViewModel disturbViewModel = getDisturbViewModel();
        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
        Intrinsics.checkNotNull(deviceAddress);
        disturbViewModel.getDisturbSwitch(deviceAddress);
    }

    public final void showTimeDialog(String title, int time, final int type) {
        Intrinsics.checkNotNullParameter(title, "title");
        BottomTimeDialog.Builder builder = new BottomTimeDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_time_selector);
        BottomTimeDialog bottomTimeDialogCreate = builder.create();
        bottomTimeDialogCreate.setListener(new BottomTimeDialog.TimeListener() { // from class: com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.TimeListener
            public final void min(int i) {
                DisturbActivity.m444showTimeDialog$lambda4(type, this, i);
            }
        });
        bottomTimeDialogCreate.initData(this);
        bottomTimeDialogCreate.setTitle(title);
        bottomTimeDialogCreate.setCurrTime(time / 60, time % 60);
        bottomTimeDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showTimeDialog$lambda-4, reason: not valid java name */
    public static final void m444showTimeDialog$lambda4(int i, DisturbActivity this$0, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDisturbBinding activityDisturbBinding = null;
        if (i == 1) {
            DisturbViewModel.DisturbUI disturbUI = this$0.disturb;
            if (disturbUI == null) {
                Intrinsics.throwUninitializedPropertyAccessException("disturb");
                disturbUI = null;
            }
            disturbUI.setStart(i2);
            ActivityDisturbBinding activityDisturbBinding2 = this$0.binding;
            if (activityDisturbBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDisturbBinding = activityDisturbBinding2;
            }
            activityDisturbBinding.qcDisturbStart.setRightText(DateUtil.dayMinToStr(i2));
            return;
        }
        DisturbViewModel.DisturbUI disturbUI2 = this$0.disturb;
        if (disturbUI2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("disturb");
            disturbUI2 = null;
        }
        disturbUI2.setEnd(i2);
        ActivityDisturbBinding activityDisturbBinding3 = this$0.binding;
        if (activityDisturbBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDisturbBinding = activityDisturbBinding3;
        }
        activityDisturbBinding.qcDisturbEnd.setRightText(DateUtil.dayMinToStr(i2));
    }
}
