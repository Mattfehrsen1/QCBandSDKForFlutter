package com.qcwireless.qcwatch.ui.home.bloodoxgen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
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
import com.qcwireless.qcwatch.databinding.ActivityBloodOxygenBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.view.QBloodOxygenLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;
import com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel;
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

/* compiled from: BloodOxygenActivity.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0014J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\b\u0010\u0019\u001a\u00020\u0012H\u0014J\b\u0010\u001a\u001a\u00020\u0012H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityBloodOxygenBinding;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "deviceNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenActivity$MyDeviceNotifyListener;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "MyDeviceNotifyListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodOxygenActivity extends BaseActivity {
    private ActivityBloodOxygenBinding binding;
    private DateUtil dateUtil;
    private MyDeviceNotifyListener deviceNotifyListener;
    private DeviceSetting deviceSetting;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public BloodOxygenActivity() {
        final BloodOxygenActivity bloodOxygenActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<BloodOxygenViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BloodOxygenViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(bloodOxygenActivity, Reflection.getOrCreateKotlinClass(BloodOxygenViewModel.class), qualifier, objArr);
            }
        });
        this.dateUtil = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BloodOxygenViewModel getViewModel() {
        return (BloodOxygenViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBloodOxygenBinding activityBloodOxygenBindingInflate = ActivityBloodOxygenBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityBloodOxygenBindingInflate, "inflate(layoutInflater)");
        this.binding = activityBloodOxygenBindingInflate;
        if (activityBloodOxygenBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBindingInflate = null;
        }
        ConstraintLayout root = activityBloodOxygenBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getViewModel().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        getViewModel().queryLastData();
        final ActivityBloodOxygenBinding activityBloodOxygenBinding = this.binding;
        ActivityBloodOxygenBinding activityBloodOxygenBinding2 = null;
        if (activityBloodOxygenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding = null;
        }
        setStatusBarBackground(R.color.blood_oxygen_bg);
        ViewKt.gone(activityBloodOxygenBinding.titleBar.divider);
        activityBloodOxygenBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_87));
        ViewKt.visible(activityBloodOxygenBinding.titleBar.tvRightText);
        activityBloodOxygenBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.app_warming);
        activityBloodOxygenBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodOxygenActivity.m637setupViews$lambda3$lambda0(this.f$0, view);
            }
        });
        final QDateSwitchView qDateSwitchView = activityBloodOxygenBinding.qcDateChange;
        qDateSwitchView.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$setupViews$1$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil date) {
                Intrinsics.checkNotNullParameter(date, "date");
                this.this$0.dateUtil = date;
                qDateSwitchView.setDateUtil(date);
                this.this$0.getViewModel().queryBloodOxygenByDate(date);
            }
        });
        activityBloodOxygenBinding.bloodOxygenChatView.setSelectedListener(new QBloodOxygenLineChartView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.ui.base.view.QBloodOxygenLineChartView.OnSelectedListener
            public final void onSelected(QBloodOxygenLineChartView.DataBean dataBean) {
                BloodOxygenActivity.m638setupViews$lambda3$lambda2(activityBloodOxygenBinding, dataBean);
            }
        });
        BloodOxygenActivity bloodOxygenActivity = this;
        getViewModel().getDeviceSetting().observe(bloodOxygenActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodOxygenActivity.m639setupViews$lambda4(this.f$0, (DeviceSetting) obj);
            }
        });
        ActivityBloodOxygenBinding activityBloodOxygenBinding3 = this.binding;
        if (activityBloodOxygenBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding3 = null;
        }
        activityBloodOxygenBinding3.qcSettingBloodOxygen.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BloodOxygenActivity.m640setupViews$lambda5(this.f$0, compoundButton, z);
            }
        });
        getViewModel().getUiState().observe(bloodOxygenActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodOxygenActivity.m641setupViews$lambda6(this.f$0, (BloodOxygenViewModel.BloodOxygenUI) obj);
            }
        });
        getViewModel().getLastDate().observe(bloodOxygenActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BloodOxygenActivity.m642setupViews$lambda7(this.f$0, (DateUtil) obj);
            }
        });
        ActivityBloodOxygenBinding activityBloodOxygenBinding4 = this.binding;
        if (activityBloodOxygenBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding4 = null;
        }
        activityBloodOxygenBinding4.boValueDetail.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity.setupViews.6
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) BloodOxygenActivity.this.dateUtil.getUnixTimestamp());
                BloodOxygenActivity bloodOxygenActivity2 = BloodOxygenActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(bloodOxygenActivity2, (Class<?>) BloodOxygenDataDetailActivity.class);
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
                bloodOxygenActivity2.startActivity(intent);
            }
        });
        ActivityBloodOxygenBinding activityBloodOxygenBinding5 = this.binding;
        if (activityBloodOxygenBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodOxygenBinding2 = activityBloodOxygenBinding5;
        }
        activityBloodOxygenBinding2.qcSettingBloodOxygen.setSubTitleClick(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodOxygenActivity.m643setupViews$lambda8(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-0, reason: not valid java name */
    public static final void m637setupViews$lambda3$lambda0(BloodOxygenActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BloodOxygenActivity bloodOxygenActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(bloodOxygenActivity, (Class<?>) BloodOxygenGuideActivity.class);
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
        bloodOxygenActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-2, reason: not valid java name */
    public static final void m638setupViews$lambda3$lambda2(ActivityBloodOxygenBinding this_run, final QBloodOxygenLineChartView.DataBean dataBean) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        ThreadExtKt.ktxRunOnUi(this_run, new Function1<ActivityBloodOxygenBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.bloodoxgen.BloodOxygenActivity$setupViews$1$3$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActivityBloodOxygenBinding activityBloodOxygenBinding) {
                invoke2(activityBloodOxygenBinding);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ActivityBloodOxygenBinding ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                if (dataBean.getMinValue() == 0) {
                    ktxRunOnUi.tvBloodOxygenValue.setText("--");
                } else {
                    TextView textView = ktxRunOnUi.tvBloodOxygenValue;
                    StringBuilder sb = new StringBuilder();
                    sb.append(dataBean.getMinValue());
                    sb.append('-');
                    sb.append(dataBean.getMaxValue());
                    textView.setText(sb.toString());
                }
                ktxRunOnUi.tvHM.setText(DateUtil.dayMinToStr(dataBean.getSeconds() / 60));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m639setupViews$lambda4(BloodOxygenActivity this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        ActivityBloodOxygenBinding activityBloodOxygenBinding = this$0.binding;
        if (activityBloodOxygenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding = null;
        }
        activityBloodOxygenBinding.qcSettingBloodOxygen.setChecked(it.getBo2Detection());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m640setupViews$lambda5(BloodOxygenActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (BleOperateManager.getInstance().isConnected()) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 != null) {
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setBo2Detection(z);
                BloodOxygenViewModel viewModel = this$0.getViewModel();
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
        ActivityBloodOxygenBinding activityBloodOxygenBinding = this$0.binding;
        if (activityBloodOxygenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding = null;
        }
        QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = activityBloodOxygenBinding.qcSettingBloodOxygen;
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        qSettingItemTitleSubTitleSystem.setChecked(deviceSetting4.getBo2Detection());
        String string = this$0.getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m641setupViews$lambda6(BloodOxygenActivity this$0, BloodOxygenViewModel.BloodOxygenUI bloodOxygenUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityBloodOxygenBinding activityBloodOxygenBinding = null;
        if (!bloodOxygenUI.getListData().isEmpty()) {
            ActivityBloodOxygenBinding activityBloodOxygenBinding2 = this$0.binding;
            if (activityBloodOxygenBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenBinding2 = null;
            }
            activityBloodOxygenBinding2.bloodOxygenChatView.setData(bloodOxygenUI.getListData(), bloodOxygenUI.getToday());
            int seconds = -1;
            for (QBloodOxygenLineChartView.DataBean dataBean : bloodOxygenUI.getListData()) {
                if (dataBean.getMaxValue() > 0) {
                    seconds = dataBean.getSeconds();
                }
            }
            String strDayMinToStr = DateUtil.dayMinToStr(seconds / 60);
            if (seconds == -1) {
                ActivityBloodOxygenBinding activityBloodOxygenBinding3 = this$0.binding;
                if (activityBloodOxygenBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodOxygenBinding3 = null;
                }
                activityBloodOxygenBinding3.boValueDetail.setRightText("");
            } else {
                ActivityBloodOxygenBinding activityBloodOxygenBinding4 = this$0.binding;
                if (activityBloodOxygenBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodOxygenBinding4 = null;
                }
                activityBloodOxygenBinding4.boValueDetail.setRightText(strDayMinToStr);
            }
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (QBloodOxygenLineChartView.DataBean dataBean2 : bloodOxygenUI.getListData()) {
                if (dataBean2.getMaxValue() >= 98) {
                    i++;
                } else {
                    int maxValue = dataBean2.getMaxValue();
                    if (95 <= maxValue && maxValue < 98) {
                        i2++;
                    } else {
                        int maxValue2 = dataBean2.getMaxValue();
                        if (1 <= maxValue2 && maxValue2 < 96) {
                            i3++;
                        }
                    }
                }
            }
            for (QBloodOxygenLineChartView.DataBean dataBean3 : bloodOxygenUI.getListData()) {
                if (dataBean3.getMinValue() >= 98) {
                    i++;
                } else {
                    int minValue = dataBean3.getMinValue();
                    if (95 <= minValue && minValue < 98) {
                        i2++;
                    } else {
                        int minValue2 = dataBean3.getMinValue();
                        if (1 <= minValue2 && minValue2 < 96) {
                            i3++;
                        }
                    }
                }
            }
            int[] iArr = {0, 0, 0, 0, 0};
            iArr[0] = i3;
            iArr[3] = i;
            iArr[4] = i2;
            int i4 = i + i3 + i2;
            if (i4 > 0) {
                ActivityBloodOxygenBinding activityBloodOxygenBinding5 = this$0.binding;
                if (activityBloodOxygenBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodOxygenBinding5 = null;
                }
                activityBloodOxygenBinding5.bloodOxygenCircleView.dataInit(iArr);
                ActivityBloodOxygenBinding activityBloodOxygenBinding6 = this$0.binding;
                if (activityBloodOxygenBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodOxygenBinding6 = null;
                }
                TextView textView = activityBloodOxygenBinding6.tvValue1;
                StringBuilder sb = new StringBuilder();
                float f = i4;
                float f2 = (i * 1.0f) / f;
                float f3 = 100;
                sb.append(Math.round(f2 * f3));
                sb.append('%');
                textView.setText(sb.toString());
                ActivityBloodOxygenBinding activityBloodOxygenBinding7 = this$0.binding;
                if (activityBloodOxygenBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBloodOxygenBinding7 = null;
                }
                TextView textView2 = activityBloodOxygenBinding7.tvValue2;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Math.round(((i3 * 1.0f) / f) * f3));
                sb2.append('%');
                textView2.setText(sb2.toString());
                ActivityBloodOxygenBinding activityBloodOxygenBinding8 = this$0.binding;
                if (activityBloodOxygenBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityBloodOxygenBinding = activityBloodOxygenBinding8;
                }
                TextView textView3 = activityBloodOxygenBinding.tvValue3;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(Math.round(((i2 * 1.0f) / f) * f3));
                sb3.append('%');
                textView3.setText(sb3.toString());
                return;
            }
            int[] iArr2 = {0, 0, 0, 0, 0};
            ActivityBloodOxygenBinding activityBloodOxygenBinding9 = this$0.binding;
            if (activityBloodOxygenBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenBinding9 = null;
            }
            activityBloodOxygenBinding9.bloodOxygenCircleView.dataInit(iArr2);
            ActivityBloodOxygenBinding activityBloodOxygenBinding10 = this$0.binding;
            if (activityBloodOxygenBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenBinding10 = null;
            }
            activityBloodOxygenBinding10.bloodOxygenChatView.setData(bloodOxygenUI.getListData(), bloodOxygenUI.getToday());
            ActivityBloodOxygenBinding activityBloodOxygenBinding11 = this$0.binding;
            if (activityBloodOxygenBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenBinding11 = null;
            }
            activityBloodOxygenBinding11.tvValue1.setText("--");
            ActivityBloodOxygenBinding activityBloodOxygenBinding12 = this$0.binding;
            if (activityBloodOxygenBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenBinding12 = null;
            }
            activityBloodOxygenBinding12.tvValue2.setText("--");
            ActivityBloodOxygenBinding activityBloodOxygenBinding13 = this$0.binding;
            if (activityBloodOxygenBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBloodOxygenBinding = activityBloodOxygenBinding13;
            }
            activityBloodOxygenBinding.tvValue3.setText("--");
            return;
        }
        int[] iArr3 = {0, 0, 0, 0, 0};
        ActivityBloodOxygenBinding activityBloodOxygenBinding14 = this$0.binding;
        if (activityBloodOxygenBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding14 = null;
        }
        activityBloodOxygenBinding14.bloodOxygenCircleView.dataInit(iArr3);
        ActivityBloodOxygenBinding activityBloodOxygenBinding15 = this$0.binding;
        if (activityBloodOxygenBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding15 = null;
        }
        activityBloodOxygenBinding15.bloodOxygenChatView.setData(bloodOxygenUI.getListData(), bloodOxygenUI.getToday());
        ActivityBloodOxygenBinding activityBloodOxygenBinding16 = this$0.binding;
        if (activityBloodOxygenBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding16 = null;
        }
        activityBloodOxygenBinding16.tvValue1.setText("--");
        ActivityBloodOxygenBinding activityBloodOxygenBinding17 = this$0.binding;
        if (activityBloodOxygenBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding17 = null;
        }
        activityBloodOxygenBinding17.tvValue2.setText("--");
        ActivityBloodOxygenBinding activityBloodOxygenBinding18 = this$0.binding;
        if (activityBloodOxygenBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding18 = null;
        }
        activityBloodOxygenBinding18.tvValue3.setText("--");
        ActivityBloodOxygenBinding activityBloodOxygenBinding19 = this$0.binding;
        if (activityBloodOxygenBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodOxygenBinding = activityBloodOxygenBinding19;
        }
        activityBloodOxygenBinding.boValueDetail.setRightText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m642setupViews$lambda7(BloodOxygenActivity this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.dateUtil = it;
        ActivityBloodOxygenBinding activityBloodOxygenBinding = this$0.binding;
        if (activityBloodOxygenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding = null;
        }
        activityBloodOxygenBinding.qcDateChange.setDateUtil(it);
        this$0.getViewModel().queryBloodOxygenByDate(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m643setupViews$lambda8(BloodOxygenActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityBloodOxygenBinding activityBloodOxygenBinding = this$0.binding;
        ActivityBloodOxygenBinding activityBloodOxygenBinding2 = null;
        if (activityBloodOxygenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding = null;
        }
        if (activityBloodOxygenBinding.qcSettingBloodOxygen.getTextLines() == 2) {
            ActivityBloodOxygenBinding activityBloodOxygenBinding3 = this$0.binding;
            if (activityBloodOxygenBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBloodOxygenBinding3 = null;
            }
            activityBloodOxygenBinding3.qcSettingBloodOxygen.startAnim(180.0f);
            ActivityBloodOxygenBinding activityBloodOxygenBinding4 = this$0.binding;
            if (activityBloodOxygenBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBloodOxygenBinding2 = activityBloodOxygenBinding4;
            }
            activityBloodOxygenBinding2.qcSettingBloodOxygen.setTextLines(20);
            return;
        }
        ActivityBloodOxygenBinding activityBloodOxygenBinding5 = this$0.binding;
        if (activityBloodOxygenBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBloodOxygenBinding5 = null;
        }
        activityBloodOxygenBinding5.qcSettingBloodOxygen.startAnim(360.0f);
        ActivityBloodOxygenBinding activityBloodOxygenBinding6 = this$0.binding;
        if (activityBloodOxygenBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBloodOxygenBinding2 = activityBloodOxygenBinding6;
        }
        activityBloodOxygenBinding2.qcSettingBloodOxygen.setTextLines(2);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.dateUtil.isToday()) {
            getViewModel().queryBloodOxygenByDate(this.dateUtil);
        }
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
        bleOperateManager.addOutDeviceListener(3, myDeviceNotifyListener);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeOutDeviceListener(3);
    }

    /* compiled from: BloodOxygenActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenActivity$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/BloodOxygenActivity;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyDeviceNotifyListener extends DeviceNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) throws InterruptedException {
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0 && resultEntity.getDataType() == 3) {
                BloodOxygenActivity.this.getViewModel().syncTodayData();
            }
        }
    }
}
