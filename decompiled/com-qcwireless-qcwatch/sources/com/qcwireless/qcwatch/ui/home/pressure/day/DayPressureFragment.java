package com.qcwireless.qcwatch.ui.home.pressure.day;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.LocationConst;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.FragmentDayPressureBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QPressureBarChart;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;
import com.qcwireless.qcwatch.ui.home.pressure.RingPressureDetailActivity;
import com.qcwireless.qcwatch.ui.home.pressure.adapter.ManualPressureDetailAdapter;
import com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment;
import com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel;
import com.yalantis.ucrop.view.CropImageView;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DayPressureFragment.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 52\u00020\u0001:\u00015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0016J&\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020&H\u0016J\u0010\u00100\u001a\u00020&2\u0006\u00101\u001a\u000202H\u0007J\b\u00103\u001a\u00020&H\u0016J\b\u00104\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"¨\u00066"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/pressure/adapter/ManualPressureDetailAdapter;", "alphaAnimation", "Landroid/view/animation/AlphaAnimation;", "animation", "Landroid/view/animation/ScaleAnimation;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentDayPressureBinding;", "countDownTimer", "Landroid/os/CountDownTimer;", "date", "Lcom/qcwireless/qc_utils/date/DateUtil;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "handler", "Landroid/os/Handler;", "maxPressure", "", "minPressure", "pressureValue", "getPressureValue", "()I", "setPressureValue", "(I)V", "startMeasure", "", "totalIndex", "totalPressure", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragmentViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragmentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "secondsDown", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DayPressureFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ManualPressureDetailAdapter adapter;
    private AlphaAnimation alphaAnimation;
    private ScaleAnimation animation;
    private FragmentDayPressureBinding binding;
    private CountDownTimer countDownTimer;
    private DateUtil date;
    private DeviceSetting deviceSetting;
    private final Handler handler;
    private int maxPressure;
    private int minPressure;
    private int pressureValue;
    private boolean startMeasure;
    private int totalIndex;
    private int totalPressure;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DayPressureFragment() {
        final DayPressureFragment dayPressureFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<DayPressureFragmentViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragmentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DayPressureFragmentViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(dayPressureFragment, Reflection.getOrCreateKotlinClass(DayPressureFragmentViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
        this.minPressure = 100;
        this.pressureValue = 42;
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DayPressureFragmentViewModel getViewModel() {
        return (DayPressureFragmentViewModel) this.viewModel.getValue();
    }

    public final int getPressureValue() {
        return this.pressureValue;
    }

    public final void setPressureValue(int i) {
        this.pressureValue = i;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDayPressureBinding fragmentDayPressureBindingInflate = FragmentDayPressureBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentDayPressureBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentDayPressureBindingInflate;
        if (fragmentDayPressureBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBindingInflate = null;
        }
        return fragmentDayPressureBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() throws SecurityException {
        super.loadDataOnce();
        EventBus.getDefault().register(this);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f, 1, 0.5f, 1, 0.5f);
        this.animation = scaleAnimation;
        scaleAnimation.setDuration(1500L);
        ScaleAnimation scaleAnimation2 = this.animation;
        FragmentDayPressureBinding fragmentDayPressureBinding = null;
        if (scaleAnimation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animation");
            scaleAnimation2 = null;
        }
        scaleAnimation2.setRepeatCount(-1);
        ScaleAnimation scaleAnimation3 = this.animation;
        if (scaleAnimation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animation");
            scaleAnimation3 = null;
        }
        scaleAnimation3.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        this.alphaAnimation = alphaAnimation;
        alphaAnimation.setDuration(500L);
        AlphaAnimation alphaAnimation2 = this.alphaAnimation;
        if (alphaAnimation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alphaAnimation");
            alphaAnimation2 = null;
        }
        alphaAnimation2.setRepeatMode(2);
        AlphaAnimation alphaAnimation3 = this.alphaAnimation;
        if (alphaAnimation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alphaAnimation");
            alphaAnimation3 = null;
        }
        alphaAnimation3.setRepeatCount(60);
        getViewModel().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        this.adapter = new ManualPressureDetailAdapter(getActivity(), getViewModel().getManualList());
        FragmentDayPressureBinding fragmentDayPressureBinding2 = this.binding;
        if (fragmentDayPressureBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding2 = null;
        }
        fragmentDayPressureBinding2.rcvPressureDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
        FragmentDayPressureBinding fragmentDayPressureBinding3 = this.binding;
        if (fragmentDayPressureBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding3 = null;
        }
        RecyclerView recyclerView = fragmentDayPressureBinding3.rcvPressureDetail;
        ManualPressureDetailAdapter manualPressureDetailAdapter = this.adapter;
        if (manualPressureDetailAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            manualPressureDetailAdapter = null;
        }
        recyclerView.setAdapter(manualPressureDetailAdapter);
        DayPressureFragment dayPressureFragment = this;
        getViewModel().getUiState().observe(dayPressureFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DayPressureFragment.m804loadDataOnce$lambda1(this.f$0, (DayPressureFragmentViewModel.DayPressureUI) obj);
            }
        });
        getViewModel().queryLastData();
        final FragmentDayPressureBinding fragmentDayPressureBinding4 = this.binding;
        if (fragmentDayPressureBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding4 = null;
        }
        fragmentDayPressureBinding4.qcDateChange.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$loadDataOnce$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                fragmentDayPressureBinding4.qcDateChange.setDateUtil(dateUtil);
                this.getViewModel().queryPressureByDate(dateUtil);
                this.date = dateUtil;
            }
        });
        FragmentDayPressureBinding fragmentDayPressureBinding5 = this.binding;
        if (fragmentDayPressureBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding5 = null;
        }
        fragmentDayPressureBinding5.qcPressureChart.setListener(new QPressureBarChart.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.ui.base.view.QPressureBarChart.OnSelectedListener
            public final void onSelected(QPressureBarChart.PressureDataBean pressureDataBean) {
                DayPressureFragment.m805loadDataOnce$lambda3$lambda2(this.f$0, pressureDataBean);
            }
        });
        getViewModel().getDeviceSetting().observe(dayPressureFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DayPressureFragment.m806loadDataOnce$lambda4(this.f$0, (DeviceSetting) obj);
            }
        });
        FragmentDayPressureBinding fragmentDayPressureBinding6 = this.binding;
        if (fragmentDayPressureBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding6 = null;
        }
        fragmentDayPressureBinding6.qcSettingPressure.setSubTitleClick(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DayPressureFragment.m807loadDataOnce$lambda5(this.f$0, view);
            }
        });
        getViewModel().getLastDate().observe(dayPressureFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DayPressureFragment.m808loadDataOnce$lambda6(this.f$0, (DateUtil) obj);
            }
        });
        FragmentDayPressureBinding fragmentDayPressureBinding7 = this.binding;
        if (fragmentDayPressureBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding7 = null;
        }
        fragmentDayPressureBinding7.qcSettingPressure.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DayPressureFragment.m809loadDataOnce$lambda7(this.f$0, compoundButton, z);
            }
        });
        FragmentDayPressureBinding fragmentDayPressureBinding8 = this.binding;
        if (fragmentDayPressureBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding8 = null;
        }
        fragmentDayPressureBinding8.tvListDetail.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DayPressureFragment.m810loadDataOnce$lambda9(this.f$0, view);
            }
        });
        FragmentDayPressureBinding fragmentDayPressureBinding9 = this.binding;
        if (fragmentDayPressureBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDayPressureBinding = fragmentDayPressureBinding9;
        }
        fragmentDayPressureBinding.heartValueDetail.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment.loadDataOnce.8
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) DayPressureFragment.this.date.getUnixTimestamp());
                bundle.putInt("type", 1);
                DayPressureFragment dayPressureFragment2 = DayPressureFragment.this;
                FragmentActivity it = dayPressureFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) RingPressureDetailActivity.class);
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
                    dayPressureFragment2.startActivity(intent);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1, reason: not valid java name */
    public static final void m804loadDataOnce$lambda1(DayPressureFragment this$0, DayPressureFragmentViewModel.DayPressureUI dayPressureUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDayPressureBinding fragmentDayPressureBinding = this$0.binding;
        FragmentDayPressureBinding fragmentDayPressureBinding2 = null;
        if (fragmentDayPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding = null;
        }
        fragmentDayPressureBinding.qcPressureChart.setData(dayPressureUI.getData());
        this$0.totalIndex = 0;
        this$0.totalPressure = 0;
        this$0.minPressure = 100;
        this$0.maxPressure = 0;
        int timeStamp = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (QPressureBarChart.PressureDataBean pressureDataBean : dayPressureUI.getData()) {
            if (pressureDataBean.getSteps() > 0) {
                timeStamp = (int) pressureDataBean.getTimeStamp();
            }
            if (pressureDataBean.getSteps() < this$0.minPressure) {
                this$0.minPressure = pressureDataBean.getSteps();
            }
            if (pressureDataBean.getSteps() > this$0.maxPressure) {
                this$0.maxPressure = pressureDataBean.getSteps();
            }
            this$0.totalPressure += pressureDataBean.getSteps();
            if (pressureDataBean.getSteps() >= 80) {
                i++;
            } else {
                int steps = pressureDataBean.getSteps();
                if (60 <= steps && steps < 80) {
                    i2++;
                } else {
                    int steps2 = pressureDataBean.getSteps();
                    if (30 <= steps2 && steps2 < 60) {
                        i3++;
                    } else {
                        int steps3 = pressureDataBean.getSteps();
                        if (steps3 >= 0 && steps3 < 30) {
                            i4++;
                        }
                    }
                }
            }
            if (pressureDataBean.getSteps() > 0) {
                this$0.totalIndex++;
            }
        }
        if (timeStamp > 0) {
            String strDayMinToStr = DateUtil.dayMinToStr(timeStamp / 60);
            FragmentDayPressureBinding fragmentDayPressureBinding3 = this$0.binding;
            if (fragmentDayPressureBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayPressureBinding3 = null;
            }
            fragmentDayPressureBinding3.heartValueDetail.setRightText(strDayMinToStr);
        } else {
            FragmentDayPressureBinding fragmentDayPressureBinding4 = this$0.binding;
            if (fragmentDayPressureBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayPressureBinding4 = null;
            }
            fragmentDayPressureBinding4.heartValueDetail.setRightText("--:--");
        }
        if (!dayPressureUI.getData().isEmpty()) {
            if (this$0.totalIndex > 0) {
                FragmentDayPressureBinding fragmentDayPressureBinding5 = this$0.binding;
                if (fragmentDayPressureBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding5 = null;
                }
                fragmentDayPressureBinding5.tvAvgValue.setText(String.valueOf(MathKt.roundToInt((this$0.totalPressure * 1.0f) / this$0.totalIndex)));
                FragmentDayPressureBinding fragmentDayPressureBinding6 = this$0.binding;
                if (fragmentDayPressureBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding6 = null;
                }
                fragmentDayPressureBinding6.tvMinValue.setText(String.valueOf(this$0.minPressure));
                FragmentDayPressureBinding fragmentDayPressureBinding7 = this$0.binding;
                if (fragmentDayPressureBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding7 = null;
                }
                fragmentDayPressureBinding7.tvMaxValue.setText(String.valueOf(this$0.maxPressure));
            } else {
                FragmentDayPressureBinding fragmentDayPressureBinding8 = this$0.binding;
                if (fragmentDayPressureBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding8 = null;
                }
                fragmentDayPressureBinding8.tvAvgValue.setText("--");
                FragmentDayPressureBinding fragmentDayPressureBinding9 = this$0.binding;
                if (fragmentDayPressureBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding9 = null;
                }
                fragmentDayPressureBinding9.tvMinValue.setText("--");
                FragmentDayPressureBinding fragmentDayPressureBinding10 = this$0.binding;
                if (fragmentDayPressureBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding10 = null;
                }
                fragmentDayPressureBinding10.tvMaxValue.setText("--");
            }
            FragmentDayPressureBinding fragmentDayPressureBinding11 = this$0.binding;
            if (fragmentDayPressureBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayPressureBinding11 = null;
            }
            QStepComponentView qStepComponentView = fragmentDayPressureBinding11.pressureTotal;
            String string = this$0.getString(R.string.qc_text_6666562);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_6666562)");
            qStepComponentView.setTitleUnit(string);
            FragmentDayPressureBinding fragmentDayPressureBinding12 = this$0.binding;
            if (fragmentDayPressureBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayPressureBinding12 = null;
            }
            QStepComponentView qStepComponentView2 = fragmentDayPressureBinding12.pressureAvg;
            String string2 = this$0.getString(R.string.qc_text_6666562);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_6666562)");
            qStepComponentView2.setTitleUnit(string2);
            int[] iArr = {0, 0, 0, 0, 0};
            iArr[0] = i4;
            iArr[1] = i3;
            iArr[2] = i2;
            iArr[3] = i;
            int i5 = i3 + i4 + i2 + i;
            if (i5 > 0) {
                FragmentDayPressureBinding fragmentDayPressureBinding13 = this$0.binding;
                if (fragmentDayPressureBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding13 = null;
                }
                fragmentDayPressureBinding13.heartCircleView.dataPressureInit(iArr);
                float f = i5;
                float f2 = 100;
                int iRound = Math.round(((i4 * 1.0f) / f) * f2);
                int iRound2 = Math.round(((i2 * 1.0f) / f) * f2);
                int iRound3 = Math.round(((i * 1.0f) / f) * f2);
                int i6 = ((100 - iRound) - iRound2) - iRound3;
                FragmentDayPressureBinding fragmentDayPressureBinding14 = this$0.binding;
                if (fragmentDayPressureBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding14 = null;
                }
                TextView textView = fragmentDayPressureBinding14.tvValue1;
                StringBuilder sb = new StringBuilder();
                sb.append(iRound);
                sb.append('%');
                textView.setText(sb.toString());
                FragmentDayPressureBinding fragmentDayPressureBinding15 = this$0.binding;
                if (fragmentDayPressureBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding15 = null;
                }
                TextView textView2 = fragmentDayPressureBinding15.tvValue2;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(i6);
                sb2.append('%');
                textView2.setText(sb2.toString());
                FragmentDayPressureBinding fragmentDayPressureBinding16 = this$0.binding;
                if (fragmentDayPressureBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding16 = null;
                }
                TextView textView3 = fragmentDayPressureBinding16.tvValue4;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(iRound2);
                sb3.append('%');
                textView3.setText(sb3.toString());
                FragmentDayPressureBinding fragmentDayPressureBinding17 = this$0.binding;
                if (fragmentDayPressureBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDayPressureBinding2 = fragmentDayPressureBinding17;
                }
                TextView textView4 = fragmentDayPressureBinding2.tvValue5;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(iRound3);
                sb4.append('%');
                textView4.setText(sb4.toString());
                return;
            }
            return;
        }
        int[] iArr2 = {0, 0, 0, 0, 0};
        FragmentDayPressureBinding fragmentDayPressureBinding18 = this$0.binding;
        if (fragmentDayPressureBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding18 = null;
        }
        fragmentDayPressureBinding18.heartCircleView.dataPressureInit(iArr2);
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < 48; i7++) {
            arrayList.add(new QPressureBarChart.PressureDataBean(i7 * 1800, 0));
        }
        FragmentDayPressureBinding fragmentDayPressureBinding19 = this$0.binding;
        if (fragmentDayPressureBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding19 = null;
        }
        fragmentDayPressureBinding19.qcPressureChart.setData(arrayList);
        FragmentDayPressureBinding fragmentDayPressureBinding20 = this$0.binding;
        if (fragmentDayPressureBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding20 = null;
        }
        fragmentDayPressureBinding20.tvValue1.setText("0%");
        FragmentDayPressureBinding fragmentDayPressureBinding21 = this$0.binding;
        if (fragmentDayPressureBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding21 = null;
        }
        fragmentDayPressureBinding21.tvValue2.setText("0%");
        FragmentDayPressureBinding fragmentDayPressureBinding22 = this$0.binding;
        if (fragmentDayPressureBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding22 = null;
        }
        fragmentDayPressureBinding22.tvValue4.setText("0%");
        FragmentDayPressureBinding fragmentDayPressureBinding23 = this$0.binding;
        if (fragmentDayPressureBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding23 = null;
        }
        fragmentDayPressureBinding23.tvValue5.setText("0%");
        FragmentDayPressureBinding fragmentDayPressureBinding24 = this$0.binding;
        if (fragmentDayPressureBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding24 = null;
        }
        fragmentDayPressureBinding24.pressureTotal.setTitleValue("--");
        FragmentDayPressureBinding fragmentDayPressureBinding25 = this$0.binding;
        if (fragmentDayPressureBinding25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding25 = null;
        }
        fragmentDayPressureBinding25.pressureTotal.setTitleUnit("--");
        FragmentDayPressureBinding fragmentDayPressureBinding26 = this$0.binding;
        if (fragmentDayPressureBinding26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding26 = null;
        }
        fragmentDayPressureBinding26.pressureAvg.setTitleValue("--");
        FragmentDayPressureBinding fragmentDayPressureBinding27 = this$0.binding;
        if (fragmentDayPressureBinding27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding27 = null;
        }
        fragmentDayPressureBinding27.pressureAvg.setTitleUnit("--");
        FragmentDayPressureBinding fragmentDayPressureBinding28 = this$0.binding;
        if (fragmentDayPressureBinding28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding28 = null;
        }
        fragmentDayPressureBinding28.tvAvgValue.setText("--");
        FragmentDayPressureBinding fragmentDayPressureBinding29 = this$0.binding;
        if (fragmentDayPressureBinding29 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding29 = null;
        }
        fragmentDayPressureBinding29.tvMinValue.setText("--");
        FragmentDayPressureBinding fragmentDayPressureBinding30 = this$0.binding;
        if (fragmentDayPressureBinding30 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDayPressureBinding2 = fragmentDayPressureBinding30;
        }
        fragmentDayPressureBinding2.tvMaxValue.setText("--");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3$lambda-2, reason: not valid java name */
    public static final void m805loadDataOnce$lambda3$lambda2(DayPressureFragment this$0, QPressureBarChart.PressureDataBean pressureDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (pressureDataBean != null) {
            FragmentDayPressureBinding fragmentDayPressureBinding = null;
            if (pressureDataBean.getSteps() > 0) {
                FragmentDayPressureBinding fragmentDayPressureBinding2 = this$0.binding;
                if (fragmentDayPressureBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding2 = null;
                }
                fragmentDayPressureBinding2.tvPressureRange.setText(DateUtil.dayMinToStr((int) (pressureDataBean.getTimeStamp() / 60)));
                FragmentDayPressureBinding fragmentDayPressureBinding3 = this$0.binding;
                if (fragmentDayPressureBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDayPressureBinding3 = null;
                }
                fragmentDayPressureBinding3.tvDayPressure.setText(String.valueOf(pressureDataBean.getSteps()));
                if (pressureDataBean.getSteps() <= 29) {
                    FragmentDayPressureBinding fragmentDayPressureBinding4 = this$0.binding;
                    if (fragmentDayPressureBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentDayPressureBinding = fragmentDayPressureBinding4;
                    }
                    fragmentDayPressureBinding.tvDayPressureUnit.setText(this$0.getString(R.string.qc_text_6666563));
                    return;
                }
                int steps = pressureDataBean.getSteps();
                if (30 <= steps && steps < 60) {
                    FragmentDayPressureBinding fragmentDayPressureBinding5 = this$0.binding;
                    if (fragmentDayPressureBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentDayPressureBinding = fragmentDayPressureBinding5;
                    }
                    fragmentDayPressureBinding.tvDayPressureUnit.setText(this$0.getString(R.string.qc_text_6666562));
                    return;
                }
                int steps2 = pressureDataBean.getSteps();
                if (60 <= steps2 && steps2 < 80) {
                    FragmentDayPressureBinding fragmentDayPressureBinding6 = this$0.binding;
                    if (fragmentDayPressureBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentDayPressureBinding = fragmentDayPressureBinding6;
                    }
                    fragmentDayPressureBinding.tvDayPressureUnit.setText(this$0.getString(R.string.qc_text_6666564));
                    return;
                }
                FragmentDayPressureBinding fragmentDayPressureBinding7 = this$0.binding;
                if (fragmentDayPressureBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDayPressureBinding = fragmentDayPressureBinding7;
                }
                fragmentDayPressureBinding.tvDayPressureUnit.setText(this$0.getString(R.string.qc_text_6666565));
                return;
            }
            FragmentDayPressureBinding fragmentDayPressureBinding8 = this$0.binding;
            if (fragmentDayPressureBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayPressureBinding8 = null;
            }
            fragmentDayPressureBinding8.tvDayPressure.setText("--");
            FragmentDayPressureBinding fragmentDayPressureBinding9 = this$0.binding;
            if (fragmentDayPressureBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayPressureBinding9 = null;
            }
            fragmentDayPressureBinding9.tvDayPressureUnit.setText("");
            FragmentDayPressureBinding fragmentDayPressureBinding10 = this$0.binding;
            if (fragmentDayPressureBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDayPressureBinding = fragmentDayPressureBinding10;
            }
            fragmentDayPressureBinding.tvPressureRange.setText(DateUtil.dayMinToStr((int) (pressureDataBean.getTimeStamp() / 60)));
            return;
        }
        XLog.i("-----");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-4, reason: not valid java name */
    public static final void m806loadDataOnce$lambda4(DayPressureFragment this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        FragmentDayPressureBinding fragmentDayPressureBinding = this$0.binding;
        if (fragmentDayPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding = null;
        }
        fragmentDayPressureBinding.qcSettingPressure.setChecked(it.getPressureDetection());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-5, reason: not valid java name */
    public static final void m807loadDataOnce$lambda5(DayPressureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDayPressureBinding fragmentDayPressureBinding = this$0.binding;
        FragmentDayPressureBinding fragmentDayPressureBinding2 = null;
        if (fragmentDayPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding = null;
        }
        if (fragmentDayPressureBinding.qcSettingPressure.getTextLines() == 2) {
            FragmentDayPressureBinding fragmentDayPressureBinding3 = this$0.binding;
            if (fragmentDayPressureBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDayPressureBinding3 = null;
            }
            fragmentDayPressureBinding3.qcSettingPressure.startAnim(180.0f);
            FragmentDayPressureBinding fragmentDayPressureBinding4 = this$0.binding;
            if (fragmentDayPressureBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDayPressureBinding2 = fragmentDayPressureBinding4;
            }
            fragmentDayPressureBinding2.qcSettingPressure.setTextLines(20);
            return;
        }
        FragmentDayPressureBinding fragmentDayPressureBinding5 = this$0.binding;
        if (fragmentDayPressureBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding5 = null;
        }
        fragmentDayPressureBinding5.qcSettingPressure.startAnim(360.0f);
        FragmentDayPressureBinding fragmentDayPressureBinding6 = this$0.binding;
        if (fragmentDayPressureBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDayPressureBinding2 = fragmentDayPressureBinding6;
        }
        fragmentDayPressureBinding2.qcSettingPressure.setTextLines(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-6, reason: not valid java name */
    public static final void m808loadDataOnce$lambda6(DayPressureFragment this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentDayPressureBinding fragmentDayPressureBinding = this$0.binding;
        if (fragmentDayPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding = null;
        }
        QDateSwitchView qDateSwitchView = fragmentDayPressureBinding.qcDateChange;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        qDateSwitchView.setDateUtil(it);
        XLog.i(it.getY_M_D());
        this$0.getViewModel().queryPressureByDate(it);
        this$0.date = it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7, reason: not valid java name */
    public static final void m809loadDataOnce$lambda7(DayPressureFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (BleOperateManager.getInstance().isConnected()) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 != null) {
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setPressureDetection(z);
                DayPressureFragmentViewModel viewModel = this$0.getViewModel();
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
        FragmentDayPressureBinding fragmentDayPressureBinding = this$0.binding;
        if (fragmentDayPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding = null;
        }
        QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = fragmentDayPressureBinding.qcSettingPressure;
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        qSettingItemTitleSubTitleSystem.setChecked(deviceSetting4.getPressureDetection());
        String string = this$0.getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-9, reason: not valid java name */
    public static final void m810loadDataOnce$lambda9(DayPressureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundle = new Bundle();
        bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) this$0.date.getUnixTimestamp());
        bundle.putInt("type", 2);
        DayPressureFragment dayPressureFragment = this$0;
        FragmentActivity it = dayPressureFragment.getActivity();
        if (it != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) RingPressureDetailActivity.class);
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
            dayPressureFragment.startActivity(intent);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentDayPressureBinding fragmentDayPressureBinding = this.binding;
        if (fragmentDayPressureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDayPressureBinding = null;
        }
        fragmentDayPressureBinding.btnMeasure.setText(getString(R.string.qc_text_76660010));
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$secondsDown$1] */
    private final void secondsDown() {
        CountDownTimer countDownTimerStart = new CountDownTimer(25000L) { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment.secondsDown.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long p0) {
                if (((int) (p0 / CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION)) % 2 == 0) {
                    final DayPressureFragment dayPressureFragment = DayPressureFragment.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$secondsDown$1$onTick$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DayPressureFragment.AnonymousClass1 anonymousClass1) {
                            invoke2(anonymousClass1);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DayPressureFragment.AnonymousClass1 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            FragmentDayPressureBinding fragmentDayPressureBinding = dayPressureFragment.binding;
                            if (fragmentDayPressureBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentDayPressureBinding = null;
                            }
                            fragmentDayPressureBinding.tvCurrHeart.setText("--");
                        }
                    });
                } else {
                    final DayPressureFragment dayPressureFragment2 = DayPressureFragment.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.pressure.day.DayPressureFragment$secondsDown$1$onTick$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DayPressureFragment.AnonymousClass1 anonymousClass1) {
                            invoke2(anonymousClass1);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DayPressureFragment.AnonymousClass1 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            FragmentDayPressureBinding fragmentDayPressureBinding = dayPressureFragment2.binding;
                            if (fragmentDayPressureBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentDayPressureBinding = null;
                            }
                            fragmentDayPressureBinding.tvCurrHeart.setText("-");
                        }
                    });
                }
            }
        }.start();
        Intrinsics.checkNotNullExpressionValue(countDownTimerStart, "private fun secondsDown(…\n\n        }.start()\n    }");
        this.countDownTimer = countDownTimerStart;
    }

    /* compiled from: DayPressureFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/home/pressure/day/DayPressureFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DayPressureFragment newInstance() {
            return new DayPressureFragment();
        }
    }
}
