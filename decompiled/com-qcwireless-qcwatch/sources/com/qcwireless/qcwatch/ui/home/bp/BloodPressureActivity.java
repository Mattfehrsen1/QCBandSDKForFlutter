package com.qcwireless.qcwatch.ui.home.bp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.baidu.location.LocationConst;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityBloodPressureBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;
import com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: BloodPressureActivity.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0014J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\b\u0010\u0019\u001a\u00020\u0012H\u0014J\b\u0010\u001a\u001a\u00020\u0012H\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodPressureBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "deviceNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureActivity$MyDeviceNotifyListener;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "MyDeviceNotifyListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureActivity extends BaseActivity {
    private ActivityBloodPressureBinding binding;
    private DateUtil date;
    private MyDeviceNotifyListener deviceNotifyListener;
    private DeviceSetting deviceSetting;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public BloodPressureActivity() {
        final BloodPressureActivity bloodPressureActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<BloodPressureViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.bp.BloodPressureViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BloodPressureViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(bloodPressureActivity, Reflection.getOrCreateKotlinClass(BloodPressureViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BloodPressureViewModel getViewModel() {
        return (BloodPressureViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodPressureBinding activityBloodPressureBindingInflate = ActivityBloodPressureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodPressureBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodPressureBindingInflate;
        if (activityBloodPressureBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBindingInflate = null;
        }
        ConstraintLayout root = activityBloodPressureBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getViewModel().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        getViewModel().queryLastData();
        ActivityBloodPressureBinding activityBloodPressureBinding = this.binding;
        ActivityBloodPressureBinding activityBloodPressureBinding2 = null;
        if (activityBloodPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding = null;
        }
        setStatusBarBackground(R.color.blood_pressure_bg);
        ViewKt.gone(activityBloodPressureBinding.titleBar.divider);
        activityBloodPressureBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_86));
        final QDateSwitchView qDateSwitchView = activityBloodPressureBinding.qcDateChange;
        qDateSwitchView.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$setupViews$1$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                this.this$0.date = dateUtil;
                qDateSwitchView.setDateUtil(dateUtil);
                this.this$0.getViewModel().queryBloodPressureByDate(dateUtil);
            }
        });
        ViewKt.visible(activityBloodPressureBinding.titleBar.tvRightText);
        activityBloodPressureBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.app_warming);
        activityBloodPressureBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureActivity.m656setupViews$lambda2$lambda1(this.f$0, view);
            }
        });
        BloodPressureActivity bloodPressureActivity = this;
        getViewModel().getUiState().observe(bloodPressureActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodPressureActivity.m657setupViews$lambda3(this.f$0, (BloodPressureViewModel.BloodPressureUI) obj);
            }
        });
        getViewModel().getLastDate().observe(bloodPressureActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodPressureActivity.m658setupViews$lambda4(this.f$0, (DateUtil) obj);
            }
        });
        ActivityBloodPressureBinding activityBloodPressureBinding3 = this.binding;
        if (activityBloodPressureBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding3 = null;
        }
        activityBloodPressureBinding3.bloodPressureChatView.setSelectedListener(new QBloodPressureChartView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartView.OnSelectedListener
            public final void onSelected(QBloodPressureChartView.BpDataBean bpDataBean) {
                BloodPressureActivity.m659setupViews$lambda5(this.f$0, bpDataBean);
            }
        });
        getViewModel().getDeviceSetting().observe(bloodPressureActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodPressureActivity.m660setupViews$lambda6(this.f$0, (DeviceSetting) obj);
            }
        });
        ActivityBloodPressureBinding activityBloodPressureBinding4 = this.binding;
        if (activityBloodPressureBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding4 = null;
        }
        activityBloodPressureBinding4.qcSettingBloodPressure.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BloodPressureActivity.m661setupViews$lambda7(this.f$0, compoundButton, z);
            }
        });
        ActivityBloodPressureBinding activityBloodPressureBinding5 = this.binding;
        if (activityBloodPressureBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding5 = null;
        }
        activityBloodPressureBinding5.bpValueDetail.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity.setupViews.7
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) BloodPressureActivity.this.date.getUnixTimestamp());
                BloodPressureActivity bloodPressureActivity2 = BloodPressureActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(bloodPressureActivity2, (Class<?>) BloodPressureDataDetailActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
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
                bloodPressureActivity2.startActivity(intent);
            }
        });
        ActivityBloodPressureBinding activityBloodPressureBinding6 = this.binding;
        if (activityBloodPressureBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodPressureBinding2 = activityBloodPressureBinding6;
        }
        activityBloodPressureBinding2.qcSettingBloodPressure.setSubTitleClick(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureActivity.m662setupViews$lambda8(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m656setupViews$lambda2$lambda1(BloodPressureActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BloodPressureActivity bloodPressureActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(bloodPressureActivity, (Class<?>) BloodPressureGuideActivity.class);
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
        bloodPressureActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m657setupViews$lambda3(BloodPressureActivity this$0, BloodPressureViewModel.BloodPressureUI bloodPressureUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (bloodPressureUI != null) {
            ActivityBloodPressureBinding activityBloodPressureBinding = this$0.binding;
            ActivityBloodPressureBinding activityBloodPressureBinding2 = null;
            if (activityBloodPressureBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodPressureBinding = null;
            }
            activityBloodPressureBinding.bloodPressureChatView.setData(bloodPressureUI.getListData(), bloodPressureUI.getToday());
            if (bloodPressureUI.getListData().isEmpty()) {
                ActivityBloodPressureBinding activityBloodPressureBinding3 = this$0.binding;
                if (activityBloodPressureBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityBloodPressureBinding2 = activityBloodPressureBinding3;
                }
                activityBloodPressureBinding2.bpValueDetail.setRightText("");
                return;
            }
            int min = bloodPressureUI.getListData().get(0).getMin();
            ActivityBloodPressureBinding activityBloodPressureBinding4 = this$0.binding;
            if (activityBloodPressureBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBloodPressureBinding2 = activityBloodPressureBinding4;
            }
            activityBloodPressureBinding2.bpValueDetail.setRightText(DateUtil.dayMinToStr(min));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m658setupViews$lambda4(BloodPressureActivity this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.date = it;
        ActivityBloodPressureBinding activityBloodPressureBinding = this$0.binding;
        if (activityBloodPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding = null;
        }
        activityBloodPressureBinding.qcDateChange.setDateUtil(it);
        this$0.getViewModel().queryBloodPressureByDate(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m659setupViews$lambda5(BloodPressureActivity this$0, final QBloodPressureChartView.BpDataBean bpDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (bpDataBean != null) {
            if (bpDataBean.getSbp() == 0) {
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<BloodPressureActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$setupViews$4$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodPressureActivity bloodPressureActivity) {
                        invoke2(bloodPressureActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodPressureActivity ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ActivityBloodPressureBinding activityBloodPressureBinding = ktxRunOnUi.binding;
                        if (activityBloodPressureBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityBloodPressureBinding = null;
                        }
                        activityBloodPressureBinding.bpValue.setText("--");
                    }
                });
            } else {
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<BloodPressureActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bp.BloodPressureActivity$setupViews$4$2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BloodPressureActivity bloodPressureActivity) {
                        invoke2(bloodPressureActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BloodPressureActivity ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        StringBuilder sb = new StringBuilder();
                        sb.append(bpDataBean.getSbp());
                        sb.append('/');
                        sb.append(bpDataBean.getDbp());
                        String string = sb.toString();
                        ActivityBloodPressureBinding activityBloodPressureBinding = ktxRunOnUi.binding;
                        if (activityBloodPressureBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityBloodPressureBinding = null;
                        }
                        activityBloodPressureBinding.bpValue.setText(string);
                    }
                });
            }
            ActivityBloodPressureBinding activityBloodPressureBinding = this$0.binding;
            if (activityBloodPressureBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodPressureBinding = null;
            }
            activityBloodPressureBinding.tvHM.setText(DateUtil.dayMinToStr(bpDataBean.getMin()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m660setupViews$lambda6(BloodPressureActivity this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        ActivityBloodPressureBinding activityBloodPressureBinding = this$0.binding;
        if (activityBloodPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding = null;
        }
        activityBloodPressureBinding.qcSettingBloodPressure.setChecked(it.getBpSwitch());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m661setupViews$lambda7(BloodPressureActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (BleOperateManager.getInstance().isConnected()) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 != null) {
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setBpSwitch(z);
                BloodPressureViewModel viewModel = this$0.getViewModel();
                String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
                DeviceSetting deviceSetting3 = this$0.deviceSetting;
                if (deviceSetting3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                } else {
                    deviceSetting = deviceSetting3;
                }
                viewModel.saveDeviceSetting(deviceAddressNoClear, deviceSetting);
                return;
            }
            return;
        }
        ActivityBloodPressureBinding activityBloodPressureBinding = this$0.binding;
        if (activityBloodPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding = null;
        }
        QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = activityBloodPressureBinding.qcSettingBloodPressure;
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        qSettingItemTitleSubTitleSystem.setChecked(deviceSetting4.getBpSwitch());
        String string = this$0.getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m662setupViews$lambda8(BloodPressureActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityBloodPressureBinding activityBloodPressureBinding = this$0.binding;
        ActivityBloodPressureBinding activityBloodPressureBinding2 = null;
        if (activityBloodPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding = null;
        }
        if (activityBloodPressureBinding.qcSettingBloodPressure.getTextLines() == 2) {
            ActivityBloodPressureBinding activityBloodPressureBinding3 = this$0.binding;
            if (activityBloodPressureBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodPressureBinding3 = null;
            }
            activityBloodPressureBinding3.qcSettingBloodPressure.startAnim(180.0f);
            ActivityBloodPressureBinding activityBloodPressureBinding4 = this$0.binding;
            if (activityBloodPressureBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBloodPressureBinding2 = activityBloodPressureBinding4;
            }
            activityBloodPressureBinding2.qcSettingBloodPressure.setTextLines(20);
            return;
        }
        ActivityBloodPressureBinding activityBloodPressureBinding5 = this$0.binding;
        if (activityBloodPressureBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodPressureBinding5 = null;
        }
        activityBloodPressureBinding5.qcSettingBloodPressure.startAnim(360.0f);
        ActivityBloodPressureBinding activityBloodPressureBinding6 = this$0.binding;
        if (activityBloodPressureBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodPressureBinding2 = activityBloodPressureBinding6;
        }
        activityBloodPressureBinding2.qcSettingBloodPressure.setTextLines(2);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.deviceNotifyListener = new MyDeviceNotifyListener();
        BleOperateManager bleOperateManager = BleOperateManager.getInstance();
        MyDeviceNotifyListener myDeviceNotifyListener = this.deviceNotifyListener;
        if (myDeviceNotifyListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceNotifyListener");
            myDeviceNotifyListener = null;
        }
        bleOperateManager.addOutDeviceListener(2, myDeviceNotifyListener);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryBloodPressureByDate(this.date);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeOutDeviceListener(2);
    }

    /* compiled from: BloodPressureActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureActivity$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/bp/BloodPressureActivity;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyDeviceNotifyListener extends DeviceNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) {
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0 && resultEntity.getDataType() == 2) {
                BloodPressureActivity.this.getViewModel().syncBp();
            }
        }
    }
}
