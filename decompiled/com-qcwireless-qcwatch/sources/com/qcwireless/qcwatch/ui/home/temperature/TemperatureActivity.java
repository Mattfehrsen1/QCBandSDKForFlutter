package com.qcwireless.qcwatch.ui.home.temperature;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
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
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityTemperatureBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineChartView;
import com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
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

/* compiled from: TemperatureActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0017J\b\u0010\u0017\u001a\u00020\u0010H\u0014J\b\u0010\u0018\u001a\u00020\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/temperature/TemperatureActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityTemperatureBinding;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "deviceNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/temperature/TemperatureActivity$MyDeviceNotifyListener;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/temperature/vm/TemperatureViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/temperature/vm/TemperatureViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "MyDeviceNotifyListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemperatureActivity extends BaseActivity {
    private ActivityTemperatureBinding binding;
    private DateUtil date;
    private MyDeviceNotifyListener deviceNotifyListener;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public TemperatureActivity() {
        final TemperatureActivity temperatureActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<TemperatureViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.temperature.vm.TemperatureViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final TemperatureViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(temperatureActivity, Reflection.getOrCreateKotlinClass(TemperatureViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TemperatureViewModel getViewModel() {
        return (TemperatureViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTemperatureBinding activityTemperatureBindingInflate = ActivityTemperatureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTemperatureBindingInflate, "inflate(layoutInflater)");
        this.binding = activityTemperatureBindingInflate;
        if (activityTemperatureBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureBindingInflate = null;
        }
        ConstraintLayout root = activityTemperatureBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.temperature_bg);
        getViewModel().queryLastDate();
        final ActivityTemperatureBinding activityTemperatureBinding = this.binding;
        ActivityTemperatureBinding activityTemperatureBinding2 = null;
        if (activityTemperatureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureBinding = null;
        }
        activityTemperatureBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_93));
        ViewKt.visible(activityTemperatureBinding.titleBar.tvRightText);
        activityTemperatureBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.app_warming);
        activityTemperatureBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureActivity.m870setupViews$lambda3$lambda0(this.f$0, view);
            }
        });
        final QDateSwitchView qDateSwitchView = activityTemperatureBinding.qcDateChange;
        qDateSwitchView.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$setupViews$1$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                qDateSwitchView.setDateUtil(dateUtil);
                this.date = dateUtil;
                this.getViewModel().getTemperatureByDate(dateUtil);
            }
        });
        activityTemperatureBinding.temperatureChatView.setSelectedListener(new QTemperatureLineChartView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$$ExternalSyntheticLambda3
            @Override // com.qcwireless.qcwatch.ui.base.view.QTemperatureLineChartView.OnSelectedListener
            public final void onSelected(QTemperatureLineChartView.TemperatureDataBean temperatureDataBean) {
                TemperatureActivity.m871setupViews$lambda3$lambda2(activityTemperatureBinding, this, temperatureDataBean);
            }
        });
        if (UserConfig.INSTANCE.getInstance().getTemperature()) {
            ActivityTemperatureBinding activityTemperatureBinding3 = this.binding;
            if (activityTemperatureBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding3 = null;
            }
            TextView textView = activityTemperatureBinding3.tvInfo1;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(R.string.qc_text_334);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_334)");
            String str = String.format(string, Arrays.copyOf(new Object[]{"96.8~98.6°F", "99.14°F", "99.14-100.4℉", "100.58-104℉", "104°F"}, 5));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            textView.setText(str);
            ActivityTemperatureBinding activityTemperatureBinding4 = this.binding;
            if (activityTemperatureBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTemperatureBinding2 = activityTemperatureBinding4;
            }
            TextView textView2 = activityTemperatureBinding2.tvInfo2;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String string2 = getString(R.string.qc_text_333);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_333)");
            String str2 = String.format(string2, Arrays.copyOf(new Object[]{"97.3~99.0°F", "32.4~33.0°F", "33.4°F", "32.6°F"}, 4));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            textView2.setText(str2);
        } else {
            ActivityTemperatureBinding activityTemperatureBinding5 = this.binding;
            if (activityTemperatureBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding5 = null;
            }
            TextView textView3 = activityTemperatureBinding5.tvInfo1;
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String string3 = getString(R.string.qc_text_334);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_334)");
            String str3 = String.format(string3, Arrays.copyOf(new Object[]{"36～37℃", "37.3℃", "37.3～38℃", "38.1～40℃", "40°C"}, 5));
            Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
            textView3.setText(str3);
            ActivityTemperatureBinding activityTemperatureBinding6 = this.binding;
            if (activityTemperatureBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTemperatureBinding2 = activityTemperatureBinding6;
            }
            TextView textView4 = activityTemperatureBinding2.tvInfo2;
            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
            String string4 = getString(R.string.qc_text_333);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_333)");
            String str4 = String.format(string4, Arrays.copyOf(new Object[]{"36.3℃～37.2℃", "0.2℃～0.5℃", "0.8°C", "0.4°C"}, 4));
            Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
            textView4.setText(str4);
        }
        activityTemperatureBinding.temperatureValueDetail.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$setupViews$1$4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) this.this$0.date.getUnixTimestamp());
                TemperatureActivity temperatureActivity = this.this$0;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(temperatureActivity, (Class<?>) TemperatureDataDetailActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str5 = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).intValue()), "putExtra(name, value)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).byteValue()), "putExtra(name, value)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Character) second).charValue()), "putExtra(name, value)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).shortValue()), "putExtra(name, value)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).longValue()), "putExtra(name, value)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).floatValue()), "putExtra(name, value)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).doubleValue()), "putExtra(name, value)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (String) second), "putExtra(name, value)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (CharSequence) second), "putExtra(name, value)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Parcelable) second), "putExtra(name, value)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (boolean[]) second), "putExtra(name, value)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (byte[]) second), "putExtra(name, value)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (short[]) second), "putExtra(name, value)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (char[]) second), "putExtra(name, value)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (int[]) second), "putExtra(name, value)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (long[]) second), "putExtra(name, value)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (float[]) second), "putExtra(name, value)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (double[]) second), "putExtra(name, value)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Bundle) second), "putExtra(name, value)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Parcelable) second), "putExtra(name, value)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                temperatureActivity.startActivity(intent);
            }
        });
        TemperatureActivity temperatureActivity = this;
        getViewModel().getLastDate().observe(temperatureActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TemperatureActivity.m872setupViews$lambda4(this.f$0, (DateUtil) obj);
            }
        });
        getViewModel().getUiState().observe(temperatureActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TemperatureActivity.m873setupViews$lambda5(this.f$0, (TemperatureViewModel.TemperatureUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-0, reason: not valid java name */
    public static final void m870setupViews$lambda3$lambda0(TemperatureActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TemperatureActivity temperatureActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(temperatureActivity, (Class<?>) TemperatureGuideActivity.class);
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
        temperatureActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-2, reason: not valid java name */
    public static final void m871setupViews$lambda3$lambda2(ActivityTemperatureBinding this_run, TemperatureActivity this$0, final QTemperatureLineChartView.TemperatureDataBean temperatureDataBean) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (temperatureDataBean.getValue() > 0.0f) {
            ThreadExtKt.ktxRunOnUi(this_run, new Function1<ActivityTemperatureBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.temperature.TemperatureActivity$setupViews$1$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ActivityTemperatureBinding activityTemperatureBinding) {
                    invoke2(activityTemperatureBinding);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ActivityTemperatureBinding ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    if (UserConfig.INSTANCE.getInstance().getTemperature()) {
                        TextView textView = ktxRunOnUi.tvTemperatureValue;
                        String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((temperatureDataBean.getValue() * 1.8f) + 32)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
                        textView.setText(str);
                    } else {
                        ktxRunOnUi.tvTemperatureValue.setText(String.valueOf(temperatureDataBean.getValue()));
                    }
                    ktxRunOnUi.tvHM.setText(DateUtil.dayMinToStr(temperatureDataBean.getMin()));
                }
            });
        } else {
            this_run.tvTemperatureValue.setText("--");
            this_run.tvHM.setText(DateUtil.dayMinToStr(temperatureDataBean.getMin()));
        }
        if (UserConfig.INSTANCE.getInstance().getTemperature()) {
            this_run.tvTemperatureUnit.setText(this$0.getString(R.string.qc_text_28));
        } else {
            this_run.tvTemperatureUnit.setText(this$0.getString(R.string.qc_text_27));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m872setupViews$lambda4(TemperatureActivity this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.date = it;
        ActivityTemperatureBinding activityTemperatureBinding = this$0.binding;
        if (activityTemperatureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureBinding = null;
        }
        activityTemperatureBinding.qcDateChange.setDateUtil(it);
        this$0.getViewModel().getTemperatureByDate(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m873setupViews$lambda5(TemperatureActivity this$0, TemperatureViewModel.TemperatureUI temperatureUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityTemperatureBinding activityTemperatureBinding = this$0.binding;
        ActivityTemperatureBinding activityTemperatureBinding2 = null;
        if (activityTemperatureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureBinding = null;
        }
        activityTemperatureBinding.temperatureChatView.setData(temperatureUI.getData(), this$0.date.isToday(), UserConfig.INSTANCE.getInstance().getTemperature());
        if (!temperatureUI.getData().isEmpty()) {
            int min = 0;
            for (QTemperatureLineChartView.TemperatureDataBean temperatureDataBean : temperatureUI.getData()) {
                if (temperatureDataBean.getValue() > 0.0f) {
                    min = temperatureDataBean.getMin();
                }
            }
            String strDayMinToStr = DateUtil.dayMinToStr(min);
            ActivityTemperatureBinding activityTemperatureBinding3 = this$0.binding;
            if (activityTemperatureBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding3 = null;
            }
            activityTemperatureBinding3.temperatureValueDetail.setRightText(strDayMinToStr);
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (QTemperatureLineChartView.TemperatureDataBean temperatureDataBean2 : temperatureUI.getData()) {
                if (temperatureDataBean2.getValue() >= 37.299d) {
                    i++;
                } else {
                    double value = temperatureDataBean2.getValue();
                    if (35.9999999d <= value && value <= 37.298d) {
                        i2++;
                    } else if (temperatureDataBean2.getValue() <= 35.9999998d) {
                        i3++;
                    }
                }
            }
            int[] iArr = {0, 0, 0, 0, 0};
            iArr[0] = i;
            iArr[3] = i2;
            iArr[4] = i3;
            int i4 = i + i3 + i2;
            ActivityTemperatureBinding activityTemperatureBinding4 = this$0.binding;
            if (activityTemperatureBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding4 = null;
            }
            activityTemperatureBinding4.temperatureCircleView.dataInit(iArr);
            ActivityTemperatureBinding activityTemperatureBinding5 = this$0.binding;
            if (activityTemperatureBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding5 = null;
            }
            TextView textView = activityTemperatureBinding5.tvValue1;
            StringBuilder sb = new StringBuilder();
            float f = 100;
            float f2 = i4;
            sb.append(Math.round(((i3 * 1.0f) * f) / f2));
            sb.append('%');
            textView.setText(sb.toString());
            ActivityTemperatureBinding activityTemperatureBinding6 = this$0.binding;
            if (activityTemperatureBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding6 = null;
            }
            TextView textView2 = activityTemperatureBinding6.tvValue2;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Math.round(((i * 1.0f) * f) / f2));
            sb2.append('%');
            textView2.setText(sb2.toString());
            ActivityTemperatureBinding activityTemperatureBinding7 = this$0.binding;
            if (activityTemperatureBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding7 = null;
            }
            TextView textView3 = activityTemperatureBinding7.tvValue3;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(Math.round(((i2 * 1.0f) * f) / f2));
            sb3.append('%');
            textView3.setText(sb3.toString());
        } else {
            ActivityTemperatureBinding activityTemperatureBinding8 = this$0.binding;
            if (activityTemperatureBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding8 = null;
            }
            activityTemperatureBinding8.temperatureValueDetail.setRightText("");
            int[] iArr2 = {0, 0, 0, 0, 0};
            ActivityTemperatureBinding activityTemperatureBinding9 = this$0.binding;
            if (activityTemperatureBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding9 = null;
            }
            activityTemperatureBinding9.temperatureCircleView.dataInit(iArr2);
            ActivityTemperatureBinding activityTemperatureBinding10 = this$0.binding;
            if (activityTemperatureBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding10 = null;
            }
            activityTemperatureBinding10.tvValue1.setText("--");
            ActivityTemperatureBinding activityTemperatureBinding11 = this$0.binding;
            if (activityTemperatureBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding11 = null;
            }
            activityTemperatureBinding11.tvValue2.setText("--");
            ActivityTemperatureBinding activityTemperatureBinding12 = this$0.binding;
            if (activityTemperatureBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding12 = null;
            }
            activityTemperatureBinding12.tvValue3.setText("--");
            ActivityTemperatureBinding activityTemperatureBinding13 = this$0.binding;
            if (activityTemperatureBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding13 = null;
            }
            activityTemperatureBinding13.tvTemperatureValue.setText("--");
        }
        if (UserConfig.INSTANCE.getInstance().getTemperature()) {
            ActivityTemperatureBinding activityTemperatureBinding14 = this$0.binding;
            if (activityTemperatureBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding14 = null;
            }
            activityTemperatureBinding14.tv1.setText(">=99.1°F");
            ActivityTemperatureBinding activityTemperatureBinding15 = this$0.binding;
            if (activityTemperatureBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityTemperatureBinding15 = null;
            }
            activityTemperatureBinding15.tv4.setText("96.8~99.0°F");
            ActivityTemperatureBinding activityTemperatureBinding16 = this$0.binding;
            if (activityTemperatureBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityTemperatureBinding2 = activityTemperatureBinding16;
            }
            activityTemperatureBinding2.tv5.setText("≤96.6°F");
            return;
        }
        ActivityTemperatureBinding activityTemperatureBinding17 = this$0.binding;
        if (activityTemperatureBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureBinding17 = null;
        }
        activityTemperatureBinding17.tv1.setText(">=37.3℃");
        ActivityTemperatureBinding activityTemperatureBinding18 = this$0.binding;
        if (activityTemperatureBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityTemperatureBinding18 = null;
        }
        activityTemperatureBinding18.tv4.setText("36~37.2℃");
        ActivityTemperatureBinding activityTemperatureBinding19 = this$0.binding;
        if (activityTemperatureBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityTemperatureBinding2 = activityTemperatureBinding19;
        }
        activityTemperatureBinding2.tv5.setText("≤35.9℃");
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
        bleOperateManager.addOutDeviceListener(5, myDeviceNotifyListener);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if ((messageEvent instanceof ManualRefreshEvent) && this.date.isToday()) {
            getViewModel().queryLastDate();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeNotifyListener(5);
    }

    /* compiled from: TemperatureActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/temperature/TemperatureActivity$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/temperature/TemperatureActivity;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyDeviceNotifyListener extends DeviceNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) {
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0 && resultEntity.getDataType() == 5) {
                TemperatureActivity.this.getViewModel().syncTemperatureSingleCheck();
            }
        }
    }
}
