package com.qcwireless.qcwatch.ui.home.heart;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.LocationConst;
import com.elvishew.xlog.XLog;
import com.haibin.calendarview.Calendar;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityHrvBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.healthy.HRVRepository;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QHrvLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;
import com.qcwireless.qcwatch.ui.home.heart.HrvActivity;
import com.qcwireless.qcwatch.ui.home.heart.HrvActivityViewModel;
import com.qcwireless.qcwatch.ui.home.heart.adapter.ManualHrvDetailAdapter;
import com.yalantis.ucrop.view.CropImageView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

/* compiled from: HrvActivity.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H\u0014J\u0010\u00104\u001a\u0002012\u0006\u00105\u001a\u000206H\u0017J\b\u00107\u001a\u000201H\u0014J\u0010\u00108\u001a\u0002012\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u00109\u001a\u000201H\u0002J\b\u0010:\u001a\u000201H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b,\u0010-¨\u0006;"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HrvActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/heart/adapter/ManualHrvDetailAdapter;", "alphaAnimation", "Landroid/view/animation/AlphaAnimation;", "animation", "Landroid/view/animation/ScaleAnimation;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityHrvBinding;", "calendarDialog", "Lcom/qcwireless/qcwatch/base/dialog/CenterDialog;", "clickDate", "Lcom/qcwireless/qc_utils/date/DateUtil;", "countDownTimer", "Landroid/os/CountDownTimer;", "date", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "handler", "Landroid/os/Handler;", "hrvValue", "", "getHrvValue", "()I", "setHrvValue", "(I)V", "map", "", "", "Lcom/haibin/calendarview/Calendar;", "getMap", "()Ljava/util/Map;", "setMap", "(Ljava/util/Map;)V", "maxHrv", "minHrv", "startMeasure", "", "totalHrv", "totalIndex", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/heart/HrvActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/heart/HrvActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "queryManualPressure", "secondsDown", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HrvActivity extends BaseActivity {
    private ManualHrvDetailAdapter adapter;
    private AlphaAnimation alphaAnimation;
    private ScaleAnimation animation;
    private ActivityHrvBinding binding;
    private CenterDialog calendarDialog;
    private DateUtil clickDate;
    private CountDownTimer countDownTimer;
    private DateUtil date;
    private DeviceSetting deviceSetting;
    private final Handler handler;
    private int hrvValue;
    private Map<String, Calendar> map;
    private int maxHrv;
    private int minHrv;
    private boolean startMeasure;
    private int totalHrv;
    private int totalIndex;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public final void queryManualPressure(DateUtil date) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HrvActivity() {
        final HrvActivity hrvActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<HrvActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.heart.HrvActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HrvActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(hrvActivity, Reflection.getOrCreateKotlinClass(HrvActivityViewModel.class), qualifier, objArr);
            }
        });
        this.date = new DateUtil();
        this.minHrv = 200;
        this.hrvValue = 152;
        this.map = new HashMap();
        this.clickDate = new DateUtil();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HrvActivityViewModel getViewModel() {
        return (HrvActivityViewModel) this.viewModel.getValue();
    }

    public final int getHrvValue() {
        return this.hrvValue;
    }

    public final void setHrvValue(int i) {
        this.hrvValue = i;
    }

    public final Map<String, Calendar> getMap() {
        return this.map;
    }

    public final void setMap(Map<String, Calendar> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.map = map;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHrvBinding activityHrvBindingInflate = ActivityHrvBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityHrvBindingInflate, "inflate(layoutInflater)");
        this.binding = activityHrvBindingInflate;
        if (activityHrvBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBindingInflate = null;
        }
        NestedScrollView root = activityHrvBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivityHrvBinding activityHrvBinding = this.binding;
        ActivityHrvBinding activityHrvBinding2 = null;
        if (activityHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding = null;
        }
        setStatusBarBackground(R.color.heart_bg);
        ViewKt.gone(activityHrvBinding.titleBar.divider);
        activityHrvBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_8060));
        ViewKt.visible(activityHrvBinding.titleBar.tvRightText);
        activityHrvBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.app_desp);
        activityHrvBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HrvActivity.m769setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f, 1, 0.5f, 1, 0.5f);
        this.animation = scaleAnimation;
        scaleAnimation.setDuration(1500L);
        ScaleAnimation scaleAnimation2 = this.animation;
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
        this.adapter = new ManualHrvDetailAdapter(this, getViewModel().getManualList());
        ActivityHrvBinding activityHrvBinding3 = this.binding;
        if (activityHrvBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding3 = null;
        }
        activityHrvBinding3.rcvHrvDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
        ActivityHrvBinding activityHrvBinding4 = this.binding;
        if (activityHrvBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding4 = null;
        }
        RecyclerView recyclerView = activityHrvBinding4.rcvHrvDetail;
        ManualHrvDetailAdapter manualHrvDetailAdapter = this.adapter;
        if (manualHrvDetailAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            manualHrvDetailAdapter = null;
        }
        recyclerView.setAdapter(manualHrvDetailAdapter);
        HrvActivity hrvActivity = this;
        getViewModel().getUiState().observe(hrvActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HrvActivity.m771setupViews$lambda3(this.f$0, (HrvActivityViewModel.HrvUI) obj);
            }
        });
        getViewModel().queryLastData();
        final ActivityHrvBinding activityHrvBinding5 = this.binding;
        if (activityHrvBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding5 = null;
        }
        activityHrvBinding5.qcDateChange.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$setupViews$3$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil dateUtil) {
                Intrinsics.checkNotNullParameter(dateUtil, "dateUtil");
                activityHrvBinding5.qcDateChange.setDateUtil(dateUtil);
                this.getViewModel().queryPressureByDate(dateUtil);
                this.date = dateUtil;
                this.clickDate = dateUtil;
                this.queryManualPressure(dateUtil);
            }
        });
        ActivityHrvBinding activityHrvBinding6 = this.binding;
        if (activityHrvBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding6 = null;
        }
        activityHrvBinding6.heartChatView.setSelectedListener(new QHrvLineChartView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda7
            @Override // com.qcwireless.qcwatch.ui.base.view.QHrvLineChartView.OnSelectedListener
            public final void onSelected(QHrvLineChartView.HeartDataBean heartDataBean) {
                HrvActivity.m772setupViews$lambda5$lambda4(this.f$0, heartDataBean);
            }
        });
        getViewModel().getDeviceSetting().observe(hrvActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HrvActivity.m773setupViews$lambda6(this.f$0, (DeviceSetting) obj);
            }
        });
        ActivityHrvBinding activityHrvBinding7 = this.binding;
        if (activityHrvBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding7 = null;
        }
        activityHrvBinding7.qcSettingHrv.setSubTitleClick(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HrvActivity.m774setupViews$lambda7(this.f$0, view);
            }
        });
        getViewModel().getLastDate().observe(hrvActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HrvActivity.m775setupViews$lambda8(this.f$0, (DateUtil) obj);
            }
        });
        ActivityHrvBinding activityHrvBinding8 = this.binding;
        if (activityHrvBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding8 = null;
        }
        activityHrvBinding8.qcSettingHrv.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HrvActivity.m776setupViews$lambda9(this.f$0, compoundButton, z);
            }
        });
        ActivityHrvBinding activityHrvBinding9 = this.binding;
        if (activityHrvBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding9 = null;
        }
        activityHrvBinding9.heartValueDetail.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity.setupViews.8
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) HrvActivity.this.date.getUnixTimestamp());
                HrvActivity hrvActivity2 = HrvActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(hrvActivity2, (Class<?>) HrvDataDetailActivity.class);
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
                hrvActivity2.startActivity(intent);
            }
        });
        ActivityHrvBinding activityHrvBinding10 = this.binding;
        if (activityHrvBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHrvBinding2 = activityHrvBinding10;
        }
        activityHrvBinding2.tvListDetail.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HrvActivity.m770setupViews$lambda11(this.f$0, view);
            }
        });
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<HrvActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity.setupViews.10
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HrvActivity hrvActivity2) {
                invoke2(hrvActivity2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HrvActivity ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.setMap(HRVRepository.INSTANCE.getGetInstance().calendarPressure(new DateUtil().getYear(), new DateUtil().getMonth()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m769setupViews$lambda1$lambda0(HrvActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HrvActivity hrvActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(hrvActivity, (Class<?>) HrvGuideActivity.class);
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
        hrvActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m771setupViews$lambda3(HrvActivity this$0, HrvActivityViewModel.HrvUI hrvUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityHrvBinding activityHrvBinding = this$0.binding;
        ActivityHrvBinding activityHrvBinding2 = null;
        if (activityHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding = null;
        }
        activityHrvBinding.heartChatView.setData(hrvUI.getData(), hrvUI.getToday());
        this$0.totalIndex = 0;
        this$0.totalHrv = 0;
        this$0.minHrv = 200;
        this$0.maxHrv = 0;
        int min = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (QHrvLineChartView.HeartDataBean heartDataBean : hrvUI.getData()) {
            if (heartDataBean.getValue() > 0) {
                min = heartDataBean.getMin();
            }
            if (heartDataBean.getValue() < this$0.minHrv) {
                this$0.minHrv = heartDataBean.getValue();
            }
            if (heartDataBean.getValue() > this$0.maxHrv) {
                this$0.maxHrv = heartDataBean.getValue();
            }
            this$0.totalHrv += heartDataBean.getValue();
            if (heartDataBean.getValue() > 101) {
                i++;
            } else {
                int value = heartDataBean.getValue();
                if (61 <= value && value < 102) {
                    i2++;
                } else {
                    int value2 = heartDataBean.getValue();
                    if (31 <= value2 && value2 < 61) {
                        i4++;
                    } else {
                        int value3 = heartDataBean.getValue();
                        if (value3 >= 0 && value3 < 31) {
                            i3++;
                        }
                    }
                }
            }
            if (heartDataBean.getValue() > 0) {
                this$0.totalIndex++;
            }
        }
        String strDayMinToStr = DateUtil.dayMinToStr(min);
        ActivityHrvBinding activityHrvBinding3 = this$0.binding;
        if (activityHrvBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding3 = null;
        }
        activityHrvBinding3.heartValueDetail.setRightText(strDayMinToStr);
        if (!hrvUI.getData().isEmpty()) {
            if (this$0.totalIndex > 0) {
                ActivityHrvBinding activityHrvBinding4 = this$0.binding;
                if (activityHrvBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding4 = null;
                }
                activityHrvBinding4.tvAvgValue.setText(String.valueOf(this$0.totalHrv / this$0.totalIndex));
                ActivityHrvBinding activityHrvBinding5 = this$0.binding;
                if (activityHrvBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding5 = null;
                }
                activityHrvBinding5.tvMinValue.setText(String.valueOf(this$0.minHrv));
                ActivityHrvBinding activityHrvBinding6 = this$0.binding;
                if (activityHrvBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding6 = null;
                }
                activityHrvBinding6.tvMaxValue.setText(String.valueOf(this$0.maxHrv));
            } else {
                ActivityHrvBinding activityHrvBinding7 = this$0.binding;
                if (activityHrvBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding7 = null;
                }
                activityHrvBinding7.tvAvgValue.setText("--");
                ActivityHrvBinding activityHrvBinding8 = this$0.binding;
                if (activityHrvBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding8 = null;
                }
                activityHrvBinding8.tvMinValue.setText("--");
                ActivityHrvBinding activityHrvBinding9 = this$0.binding;
                if (activityHrvBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding9 = null;
                }
                activityHrvBinding9.tvMaxValue.setText("--");
            }
            int[] iArr = {0, 0, 0, 0, 0};
            iArr[0] = i3;
            iArr[1] = i4;
            iArr[2] = i2;
            iArr[3] = i;
            int i5 = i3 + i4 + i2 + i;
            if (i5 > 0) {
                float f = i5;
                float f2 = 100;
                int iRound = Math.round(((i3 * 1.0f) / f) * f2);
                int iRound2 = Math.round(((i4 * 1.0f) / f) * f2);
                int iRound3 = Math.round(((i2 * 1.0f) / f) * f2);
                int i6 = ((100 - iRound) - iRound2) - iRound3;
                ActivityHrvBinding activityHrvBinding10 = this$0.binding;
                if (activityHrvBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding10 = null;
                }
                activityHrvBinding10.heartCircleView.dataHrvInit(iArr);
                ActivityHrvBinding activityHrvBinding11 = this$0.binding;
                if (activityHrvBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding11 = null;
                }
                TextView textView = activityHrvBinding11.tvValue1;
                StringBuilder sb = new StringBuilder();
                sb.append(iRound);
                sb.append('%');
                textView.setText(sb.toString());
                ActivityHrvBinding activityHrvBinding12 = this$0.binding;
                if (activityHrvBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding12 = null;
                }
                TextView textView2 = activityHrvBinding12.tvValue2;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(iRound2);
                sb2.append('%');
                textView2.setText(sb2.toString());
                ActivityHrvBinding activityHrvBinding13 = this$0.binding;
                if (activityHrvBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHrvBinding13 = null;
                }
                TextView textView3 = activityHrvBinding13.tvValue4;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(iRound3);
                sb3.append('%');
                textView3.setText(sb3.toString());
                ActivityHrvBinding activityHrvBinding14 = this$0.binding;
                if (activityHrvBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityHrvBinding2 = activityHrvBinding14;
                }
                TextView textView4 = activityHrvBinding2.tvValue5;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(i6);
                sb4.append('%');
                textView4.setText(sb4.toString());
                return;
            }
            return;
        }
        int[] iArr2 = {0, 0, 0, 0, 0};
        ActivityHrvBinding activityHrvBinding15 = this$0.binding;
        if (activityHrvBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding15 = null;
        }
        activityHrvBinding15.heartCircleView.dataHrvInit(iArr2);
        ActivityHrvBinding activityHrvBinding16 = this$0.binding;
        if (activityHrvBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding16 = null;
        }
        activityHrvBinding16.heartChatView.setData(new ArrayList(), hrvUI.getToday());
        ActivityHrvBinding activityHrvBinding17 = this$0.binding;
        if (activityHrvBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding17 = null;
        }
        activityHrvBinding17.heartValueDetail.setRightText("");
        ActivityHrvBinding activityHrvBinding18 = this$0.binding;
        if (activityHrvBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding18 = null;
        }
        activityHrvBinding18.tvValue1.setText("0%");
        ActivityHrvBinding activityHrvBinding19 = this$0.binding;
        if (activityHrvBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding19 = null;
        }
        activityHrvBinding19.tvValue2.setText("0%");
        ActivityHrvBinding activityHrvBinding20 = this$0.binding;
        if (activityHrvBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding20 = null;
        }
        activityHrvBinding20.tvValue4.setText("0%");
        ActivityHrvBinding activityHrvBinding21 = this$0.binding;
        if (activityHrvBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding21 = null;
        }
        activityHrvBinding21.tvValue5.setText("0%");
        ActivityHrvBinding activityHrvBinding22 = this$0.binding;
        if (activityHrvBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding22 = null;
        }
        activityHrvBinding22.tvAvgValue.setText("--");
        ActivityHrvBinding activityHrvBinding23 = this$0.binding;
        if (activityHrvBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding23 = null;
        }
        activityHrvBinding23.tvMinValue.setText("--");
        ActivityHrvBinding activityHrvBinding24 = this$0.binding;
        if (activityHrvBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHrvBinding2 = activityHrvBinding24;
        }
        activityHrvBinding2.tvMaxValue.setText("--");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5$lambda-4, reason: not valid java name */
    public static final void m772setupViews$lambda5$lambda4(HrvActivity this$0, QHrvLineChartView.HeartDataBean heartDataBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (heartDataBean != null) {
            ActivityHrvBinding activityHrvBinding = this$0.binding;
            ActivityHrvBinding activityHrvBinding2 = null;
            if (activityHrvBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHrvBinding = null;
            }
            activityHrvBinding.tvHM.setText(DateUtil.dayMinToStr(heartDataBean.getMin()));
            if (heartDataBean.getValue() > 0) {
                ActivityHrvBinding activityHrvBinding3 = this$0.binding;
                if (activityHrvBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityHrvBinding2 = activityHrvBinding3;
                }
                activityHrvBinding2.tvHeartValue.setText(String.valueOf(heartDataBean.getValue()));
                return;
            }
            ActivityHrvBinding activityHrvBinding4 = this$0.binding;
            if (activityHrvBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHrvBinding2 = activityHrvBinding4;
            }
            activityHrvBinding2.tvHeartValue.setText("--");
            return;
        }
        XLog.i("-----");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m773setupViews$lambda6(HrvActivity this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        ActivityHrvBinding activityHrvBinding = this$0.binding;
        if (activityHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding = null;
        }
        activityHrvBinding.qcSettingHrv.setChecked(it.getHrvEnable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m774setupViews$lambda7(HrvActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityHrvBinding activityHrvBinding = this$0.binding;
        ActivityHrvBinding activityHrvBinding2 = null;
        if (activityHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding = null;
        }
        if (activityHrvBinding.qcSettingHrv.getTextLines() == 2) {
            ActivityHrvBinding activityHrvBinding3 = this$0.binding;
            if (activityHrvBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHrvBinding3 = null;
            }
            activityHrvBinding3.qcSettingHrv.startAnim(180.0f);
            ActivityHrvBinding activityHrvBinding4 = this$0.binding;
            if (activityHrvBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHrvBinding2 = activityHrvBinding4;
            }
            activityHrvBinding2.qcSettingHrv.setTextLines(20);
            return;
        }
        ActivityHrvBinding activityHrvBinding5 = this$0.binding;
        if (activityHrvBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding5 = null;
        }
        activityHrvBinding5.qcSettingHrv.startAnim(360.0f);
        ActivityHrvBinding activityHrvBinding6 = this$0.binding;
        if (activityHrvBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHrvBinding2 = activityHrvBinding6;
        }
        activityHrvBinding2.qcSettingHrv.setTextLines(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m775setupViews$lambda8(HrvActivity this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityHrvBinding activityHrvBinding = this$0.binding;
        if (activityHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding = null;
        }
        QDateSwitchView qDateSwitchView = activityHrvBinding.qcDateChange;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        qDateSwitchView.setDateUtil(it);
        XLog.i(it.getY_M_D());
        this$0.getViewModel().queryPressureByDate(it);
        this$0.queryManualPressure(it);
        this$0.date = it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-9, reason: not valid java name */
    public static final void m776setupViews$lambda9(HrvActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (BleOperateManager.getInstance().isConnected()) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 != null) {
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setHrvEnable(z);
                HrvActivityViewModel viewModel = this$0.getViewModel();
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
        ActivityHrvBinding activityHrvBinding = this$0.binding;
        if (activityHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding = null;
        }
        QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = activityHrvBinding.qcSettingHrv;
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        qSettingItemTitleSubTitleSystem.setChecked(deviceSetting4.getHrvEnable());
        String string = this$0.getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-11, reason: not valid java name */
    public static final void m770setupViews$lambda11(HrvActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundle = new Bundle();
        bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) this$0.date.getUnixTimestamp());
        HrvActivity hrvActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(hrvActivity, (Class<?>) HrvAPPDataDetailActivity.class);
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
        hrvActivity.startActivity(intent);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof ManualRefreshEvent) {
            queryManualPressure(this.date);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ActivityHrvBinding activityHrvBinding = this.binding;
        if (activityHrvBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHrvBinding = null;
        }
        activityHrvBinding.btnMeasure.setText(getString(R.string.qc_text_76660010));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.qcwireless.qcwatch.ui.home.heart.HrvActivity$secondsDown$1] */
    private final void secondsDown() {
        CountDownTimer countDownTimerStart = new CountDownTimer(75000L) { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity.secondsDown.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long p0) {
                if (((int) (p0 / CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION)) % 2 == 0) {
                    final HrvActivity hrvActivity = HrvActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$secondsDown$1$onTick$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(HrvActivity.AnonymousClass1 anonymousClass1) {
                            invoke2(anonymousClass1);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(HrvActivity.AnonymousClass1 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivityHrvBinding activityHrvBinding = hrvActivity.binding;
                            if (activityHrvBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityHrvBinding = null;
                            }
                            activityHrvBinding.tvCurrHeart.setText("--");
                        }
                    });
                } else {
                    final HrvActivity hrvActivity2 = HrvActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HrvActivity$secondsDown$1$onTick$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(HrvActivity.AnonymousClass1 anonymousClass1) {
                            invoke2(anonymousClass1);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(HrvActivity.AnonymousClass1 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivityHrvBinding activityHrvBinding = hrvActivity2.binding;
                            if (activityHrvBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityHrvBinding = null;
                            }
                            activityHrvBinding.tvCurrHeart.setText("-");
                        }
                    });
                }
            }
        }.start();
        Intrinsics.checkNotNullExpressionValue(countDownTimerStart, "private fun secondsDown(…\n        }.start()\n\n    }");
        this.countDownTimer = countDownTimerStart;
    }
}
