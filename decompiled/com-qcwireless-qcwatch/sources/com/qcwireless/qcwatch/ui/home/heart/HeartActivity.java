package com.qcwireless.qcwatch.ui.home.heart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import com.baidu.location.LocationConst;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.RealTimeHeartRate;
import com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import com.oudmon.ble.base.communication.rsp.RealTimeHeartRateRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.ManualRefreshEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityHeartBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QHeartLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivity;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel;
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

/* compiled from: HeartActivity.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002%&B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001bH\u0014J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!H\u0017J\b\u0010\"\u001a\u00020\u001bH\u0014J\b\u0010#\u001a\u00020\u001bH\u0014J\b\u0010$\u001a\u00020\u001bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "animation", "Landroid/view/animation/ScaleAnimation;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityHeartBinding;", "callback", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivity$MyRunnable;", "dateUtil", "Lcom/qcwireless/qc_utils/date/DateUtil;", "deviceNotifyListener", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivity$MyDeviceNotifyListener;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "handler", "Landroid/os/Handler;", "maxHeart", "", "userAge", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onPause", "onResume", "setupViews", "MyDeviceNotifyListener", "MyRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartActivity extends BaseActivity {
    private ScaleAnimation animation;
    private ActivityHeartBinding binding;
    private final MyRunnable callback;
    private DateUtil dateUtil;
    private MyDeviceNotifyListener deviceNotifyListener;
    private DeviceSetting deviceSetting;
    private final Handler handler;
    private int maxHeart;
    private int userAge;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public HeartActivity() {
        final HeartActivity heartActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<HeartActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HeartActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(heartActivity, Reflection.getOrCreateKotlinClass(HeartActivityViewModel.class), qualifier, objArr);
            }
        });
        this.userAge = 27;
        this.maxHeart = 200;
        this.callback = new MyRunnable();
        this.dateUtil = new DateUtil();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HeartActivityViewModel getViewModel() {
        return (HeartActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHeartBinding activityHeartBindingInflate = ActivityHeartBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityHeartBindingInflate, "inflate(layoutInflater)");
        this.binding = activityHeartBindingInflate;
        if (activityHeartBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBindingInflate = null;
        }
        NestedScrollView root = activityHeartBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getViewModel().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        getViewModel().m761getAge();
        final ActivityHeartBinding activityHeartBinding = this.binding;
        ActivityHeartBinding activityHeartBinding2 = null;
        if (activityHeartBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding = null;
        }
        setStatusBarBackground(R.color.heart_bg);
        ViewKt.gone(activityHeartBinding.titleBar.divider);
        activityHeartBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_85));
        ViewKt.visible(activityHeartBinding.titleBar.tvRightText);
        activityHeartBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.app_warming);
        activityHeartBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartActivity.m752setupViews$lambda4$lambda0(this.f$0, view);
            }
        });
        final QDateSwitchView qDateSwitchView = activityHeartBinding.qcDateChange;
        qDateSwitchView.setDateListener(new QDateSwitchView.QDateBefore() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$setupViews$1$2$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QDateSwitchView.QDateBefore
            public void clickBefore(boolean z, DateUtil date) {
                Intrinsics.checkNotNullParameter(date, "date");
                qDateSwitchView.setDateUtil(date);
                this.dateUtil = date;
                this.getViewModel().showHeartRateDetail(date);
            }
        });
        activityHeartBinding.heartChatView.setSelectedListener(new QHeartLineChartView.OnSelectedListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.QHeartLineChartView.OnSelectedListener
            public final void onSelected(QHeartLineChartView.HeartDataBean heartDataBean) {
                HeartActivity.m753setupViews$lambda4$lambda2(activityHeartBinding, heartDataBean);
            }
        });
        TextView textView = activityHeartBinding.tvValue1;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_100)");
        String str = String.format(string, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        TextView textView2 = activityHeartBinding.tvValue2;
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String string2 = getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_100)");
        String str2 = String.format(string2, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        textView2.setText(str2);
        TextView textView3 = activityHeartBinding.tvValue3;
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String string3 = getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_100)");
        String str3 = String.format(string3, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
        textView3.setText(str3);
        TextView textView4 = activityHeartBinding.tvValue4;
        StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
        String string4 = getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_100)");
        String str4 = String.format(string4, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
        textView4.setText(str4);
        TextView textView5 = activityHeartBinding.tvValue5;
        StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
        String string5 = getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_100)");
        String str5 = String.format(string5, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str5, "format(format, *args)");
        textView5.setText(str5);
        activityHeartBinding.tvDesc.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartActivity.m754setupViews$lambda4$lambda3(this.f$0, view);
            }
        });
        HeartActivity heartActivity = this;
        getViewModel().getLastDate().observe(heartActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HeartActivity.m755setupViews$lambda5(this.f$0, (DateUtil) obj);
            }
        });
        getViewModel().getUiState().observe(heartActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HeartActivity.m756setupViews$lambda7(this.f$0, (HeartActivityViewModel.HeartDetailUI) obj);
            }
        });
        getViewModel().getAge().observe(heartActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HeartActivity.m757setupViews$lambda8(this.f$0, (HeartActivityViewModel.UserAge) obj);
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
        if (BleOperateManager.getInstance().isConnected()) {
            ActivityHeartBinding activityHeartBinding3 = this.binding;
            if (activityHeartBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHeartBinding3 = null;
            }
            ImageView imageView = activityHeartBinding3.iamgeHeart;
            ScaleAnimation scaleAnimation4 = this.animation;
            if (scaleAnimation4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animation");
                scaleAnimation4 = null;
            }
            imageView.startAnimation(scaleAnimation4);
        }
        ActivityHeartBinding activityHeartBinding4 = this.binding;
        if (activityHeartBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding4 = null;
        }
        activityHeartBinding4.heartValueDetail.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity.setupViews.5
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt(LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, (int) HeartActivity.this.dateUtil.getUnixTimestamp());
                HeartActivity heartActivity2 = HeartActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(heartActivity2, (Class<?>) HeartRateDataDetailActivity.class);
                intent.setFlags(1);
                intent.putExtras(bundle);
                for (Pair pair : arrayList) {
                    if (pair != null) {
                        String str6 = (String) pair.getFirst();
                        Object second = pair.getSecond();
                        if (second instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Number) second).intValue()), "putExtra(name, value)");
                        } else if (second instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Number) second).byteValue()), "putExtra(name, value)");
                        } else if (second instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Character) second).charValue()), "putExtra(name, value)");
                        } else if (second instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Number) second).shortValue()), "putExtra(name, value)");
                        } else if (second instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                        } else if (second instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Number) second).longValue()), "putExtra(name, value)");
                        } else if (second instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Number) second).floatValue()), "putExtra(name, value)");
                        } else if (second instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, ((Number) second).doubleValue()), "putExtra(name, value)");
                        } else if (second instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (String) second), "putExtra(name, value)");
                        } else if (second instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (CharSequence) second), "putExtra(name, value)");
                        } else if (second instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (Parcelable) second), "putExtra(name, value)");
                        } else if (second instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (Serializable) second), "putExtra(name, value)");
                        } else if (second instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (boolean[]) second), "putExtra(name, value)");
                        } else if (second instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (byte[]) second), "putExtra(name, value)");
                        } else if (second instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (short[]) second), "putExtra(name, value)");
                        } else if (second instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (char[]) second), "putExtra(name, value)");
                        } else if (second instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (int[]) second), "putExtra(name, value)");
                        } else if (second instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (long[]) second), "putExtra(name, value)");
                        } else if (second instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (float[]) second), "putExtra(name, value)");
                        } else if (second instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (double[]) second), "putExtra(name, value)");
                        } else if (second instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (Bundle) second), "putExtra(name, value)");
                        } else if (second instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str6, (Parcelable) second), "putExtra(name, value)");
                        } else {
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                }
                heartActivity2.startActivity(intent);
            }
        });
        getViewModel().getDeviceSetting().observe(heartActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HeartActivity.m758setupViews$lambda9(this.f$0, (DeviceSetting) obj);
            }
        });
        ActivityHeartBinding activityHeartBinding5 = this.binding;
        if (activityHeartBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding5 = null;
        }
        activityHeartBinding5.qcSettingHeart.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                HeartActivity.m750setupViews$lambda10(this.f$0, compoundButton, z);
            }
        });
        ActivityHeartBinding activityHeartBinding6 = this.binding;
        if (activityHeartBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHeartBinding2 = activityHeartBinding6;
        }
        activityHeartBinding2.qcSettingHeart.setSubTitleClick(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartActivity.m751setupViews$lambda11(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-0, reason: not valid java name */
    public static final void m752setupViews$lambda4$lambda0(HeartActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HeartActivity heartActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(heartActivity, (Class<?>) HeartRateGuideActivity.class);
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
        heartActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-2, reason: not valid java name */
    public static final void m753setupViews$lambda4$lambda2(ActivityHeartBinding this_run, final QHeartLineChartView.HeartDataBean heartDataBean) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        if (heartDataBean != null) {
            ThreadExtKt.ktxRunOnUi(this_run, new Function1<ActivityHeartBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$setupViews$1$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ActivityHeartBinding activityHeartBinding) {
                    invoke2(activityHeartBinding);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ActivityHeartBinding ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    if (heartDataBean.getValue() > 0) {
                        ktxRunOnUi.tvHeartValue.setText(String.valueOf(heartDataBean.getValue()));
                    } else {
                        ktxRunOnUi.tvHeartValue.setText("--");
                    }
                    ktxRunOnUi.tvHM.setText(DateUtil.dayMinToStr(heartDataBean.getMin()));
                }
            });
        } else {
            this_run.tvHeartValue.setText("--");
            this_run.tvHM.setText("12:00");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-3, reason: not valid java name */
    public static final void m754setupViews$lambda4$lambda3(HeartActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HeartActivity heartActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(heartActivity, (Class<?>) ExerciseHeartGuideActivity.class);
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
        heartActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m755setupViews$lambda5(HeartActivity this$0, DateUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.dateUtil = it;
        ActivityHeartBinding activityHeartBinding = this$0.binding;
        if (activityHeartBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding = null;
        }
        activityHeartBinding.qcDateChange.setDateUtil(it);
        this$0.getViewModel().showHeartRateDetail(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m756setupViews$lambda7(HeartActivity this$0, HeartActivityViewModel.HeartDetailUI heartDetailUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityHeartBinding activityHeartBinding = this$0.binding;
        ActivityHeartBinding activityHeartBinding2 = null;
        if (activityHeartBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding = null;
        }
        if (!heartDetailUI.getListData().isEmpty()) {
            activityHeartBinding.heartChatView.setData(heartDetailUI.getListData(), heartDetailUI.getToday());
            int min = 0;
            for (QHeartLineChartView.HeartDataBean heartDataBean : heartDetailUI.getListData()) {
                if (heartDataBean.getValue() > 0) {
                    min = heartDataBean.getMin();
                }
            }
            String strDayMinToStr = DateUtil.dayMinToStr(min);
            ActivityHeartBinding activityHeartBinding3 = this$0.binding;
            if (activityHeartBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHeartBinding2 = activityHeartBinding3;
            }
            activityHeartBinding2.heartValueDetail.setRightText(strDayMinToStr);
            int[] iArr = {0, 0, 0, 0, 0};
            for (QHeartLineChartView.HeartDataBean heartDataBean2 : heartDetailUI.getListData()) {
                float value = heartDataBean2.getValue();
                int i = this$0.maxHeart;
                if (value >= i * 0.9f) {
                    iArr[0] = iArr[0] + heartDataBean2.getRange();
                } else if (value >= i * 0.8f) {
                    iArr[1] = iArr[1] + heartDataBean2.getRange();
                } else if (value >= i * 0.7f) {
                    iArr[2] = iArr[2] + heartDataBean2.getRange();
                } else if (value >= i * 0.6f) {
                    iArr[3] = iArr[3] + heartDataBean2.getRange();
                } else if (value >= i * 0.5f) {
                    iArr[4] = iArr[4] + heartDataBean2.getRange();
                }
            }
            activityHeartBinding.heartCircleView.dataInit(iArr);
            if (iArr[0] > 0) {
                TextView textView = activityHeartBinding.tvValue1;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_100)");
                String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(iArr[0])}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                textView.setText(str);
            } else {
                TextView textView2 = activityHeartBinding.tvValue1;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String string2 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_100)");
                String str2 = String.format(string2, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                textView2.setText(str2);
            }
            if (iArr[1] > 0) {
                TextView textView3 = activityHeartBinding.tvValue2;
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String string3 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_100)");
                String str3 = String.format(string3, Arrays.copyOf(new Object[]{String.valueOf(iArr[1])}, 1));
                Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
                textView3.setText(str3);
            } else {
                TextView textView4 = activityHeartBinding.tvValue2;
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String string4 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_100)");
                String str4 = String.format(string4, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
                textView4.setText(str4);
            }
            if (iArr[2] > 0) {
                TextView textView5 = activityHeartBinding.tvValue3;
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                String string5 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_100)");
                String str5 = String.format(string5, Arrays.copyOf(new Object[]{String.valueOf(iArr[2])}, 1));
                Intrinsics.checkNotNullExpressionValue(str5, "format(format, *args)");
                textView5.setText(str5);
            } else {
                TextView textView6 = activityHeartBinding.tvValue3;
                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                String string6 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_100)");
                String str6 = String.format(string6, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str6, "format(format, *args)");
                textView6.setText(str6);
            }
            if (iArr[3] > 0) {
                TextView textView7 = activityHeartBinding.tvValue4;
                StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                String string7 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_100)");
                String str7 = String.format(string7, Arrays.copyOf(new Object[]{String.valueOf(iArr[3])}, 1));
                Intrinsics.checkNotNullExpressionValue(str7, "format(format, *args)");
                textView7.setText(str7);
            } else {
                TextView textView8 = activityHeartBinding.tvValue4;
                StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                String string8 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_100)");
                String str8 = String.format(string8, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str8, "format(format, *args)");
                textView8.setText(str8);
            }
            if (iArr[4] > 0) {
                TextView textView9 = activityHeartBinding.tvValue5;
                StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                String string9 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.qc_text_100)");
                String str9 = String.format(string9, Arrays.copyOf(new Object[]{String.valueOf(iArr[4])}, 1));
                Intrinsics.checkNotNullExpressionValue(str9, "format(format, *args)");
                textView9.setText(str9);
                return;
            }
            TextView textView10 = activityHeartBinding.tvValue5;
            StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
            String string10 = this$0.getString(R.string.qc_text_100);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.qc_text_100)");
            String str10 = String.format(string10, Arrays.copyOf(new Object[]{"--"}, 1));
            Intrinsics.checkNotNullExpressionValue(str10, "format(format, *args)");
            textView10.setText(str10);
            return;
        }
        activityHeartBinding.heartCircleView.dataInit(new int[]{0, 0, 0, 0, 0});
        activityHeartBinding.heartChatView.setData(new ArrayList(), heartDetailUI.getToday());
        TextView textView11 = activityHeartBinding.tvValue1;
        StringCompanionObject stringCompanionObject11 = StringCompanionObject.INSTANCE;
        String string11 = this$0.getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.qc_text_100)");
        String str11 = String.format(string11, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str11, "format(format, *args)");
        textView11.setText(str11);
        TextView textView12 = activityHeartBinding.tvValue2;
        StringCompanionObject stringCompanionObject12 = StringCompanionObject.INSTANCE;
        String string12 = this$0.getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.qc_text_100)");
        String str12 = String.format(string12, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str12, "format(format, *args)");
        textView12.setText(str12);
        TextView textView13 = activityHeartBinding.tvValue3;
        StringCompanionObject stringCompanionObject13 = StringCompanionObject.INSTANCE;
        String string13 = this$0.getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string13, "getString(R.string.qc_text_100)");
        String str13 = String.format(string13, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str13, "format(format, *args)");
        textView13.setText(str13);
        TextView textView14 = activityHeartBinding.tvValue4;
        StringCompanionObject stringCompanionObject14 = StringCompanionObject.INSTANCE;
        String string14 = this$0.getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string14, "getString(R.string.qc_text_100)");
        String str14 = String.format(string14, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str14, "format(format, *args)");
        textView14.setText(str14);
        TextView textView15 = activityHeartBinding.tvValue5;
        StringCompanionObject stringCompanionObject15 = StringCompanionObject.INSTANCE;
        String string15 = this$0.getString(R.string.qc_text_100);
        Intrinsics.checkNotNullExpressionValue(string15, "getString(R.string.qc_text_100)");
        String str15 = String.format(string15, Arrays.copyOf(new Object[]{"--"}, 1));
        Intrinsics.checkNotNullExpressionValue(str15, "format(format, *args)");
        textView15.setText(str15);
        activityHeartBinding.tvHeartValue.setText("--");
        ActivityHeartBinding activityHeartBinding4 = this$0.binding;
        if (activityHeartBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHeartBinding2 = activityHeartBinding4;
        }
        activityHeartBinding2.heartValueDetail.setRightText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m757setupViews$lambda8(HeartActivity this$0, HeartActivityViewModel.UserAge userAge) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int age = userAge.getAge();
        this$0.userAge = age;
        this$0.maxHeart = 220 - age;
        this$0.getViewModel().queryLastData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-9, reason: not valid java name */
    public static final void m758setupViews$lambda9(HeartActivity this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        ActivityHeartBinding activityHeartBinding = this$0.binding;
        if (activityHeartBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding = null;
        }
        activityHeartBinding.qcSettingHeart.setChecked(it.getHrDetection());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-10, reason: not valid java name */
    public static final void m750setupViews$lambda10(HeartActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (BleOperateManager.getInstance().isConnected()) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 != null) {
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setHrDetection(z);
                HeartActivityViewModel viewModel = this$0.getViewModel();
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
        ActivityHeartBinding activityHeartBinding = this$0.binding;
        if (activityHeartBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding = null;
        }
        QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = activityHeartBinding.qcSettingHeart;
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        qSettingItemTitleSubTitleSystem.setChecked(deviceSetting4.getHrDetection());
        String string = this$0.getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-11, reason: not valid java name */
    public static final void m751setupViews$lambda11(HeartActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityHeartBinding activityHeartBinding = this$0.binding;
        ActivityHeartBinding activityHeartBinding2 = null;
        if (activityHeartBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding = null;
        }
        if (activityHeartBinding.qcSettingHeart.getTextLines() == 2) {
            ActivityHeartBinding activityHeartBinding3 = this$0.binding;
            if (activityHeartBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityHeartBinding3 = null;
            }
            activityHeartBinding3.qcSettingHeart.startAnim(180.0f);
            ActivityHeartBinding activityHeartBinding4 = this$0.binding;
            if (activityHeartBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHeartBinding2 = activityHeartBinding4;
            }
            activityHeartBinding2.qcSettingHeart.setTextLines(20);
            return;
        }
        ActivityHeartBinding activityHeartBinding5 = this$0.binding;
        if (activityHeartBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityHeartBinding5 = null;
        }
        activityHeartBinding5.qcSettingHeart.startAnim(360.0f);
        ActivityHeartBinding activityHeartBinding6 = this$0.binding;
        if (activityHeartBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityHeartBinding2 = activityHeartBinding6;
        }
        activityHeartBinding2.qcSettingHeart.setTextLines(2);
    }

    /* compiled from: HeartActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivity$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            final HeartActivity heartActivity = HeartActivity.this;
            ThreadExtKt.ktxRunOnUi(this, new Function1<MyRunnable, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$MyRunnable$run$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HeartActivity.MyRunnable myRunnable) {
                    invoke2(myRunnable);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HeartActivity.MyRunnable ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    CommandHandle.getInstance().executeReqCmdNoCallback(new RealTimeHeartRate(3));
                    heartActivity.handler.postDelayed(heartActivity.callback, 20000L);
                }
            });
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
        bleOperateManager.addOutDeviceListener(1, myDeviceNotifyListener);
        this.handler.postDelayed(this.callback, 1000L);
        CommandHandle.getInstance().executeReqCmd(new RealTimeHeartRate(1), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda9
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                HeartActivity.m749onResume$lambda12(this.f$0, (RealTimeHeartRateRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onResume$lambda-12, reason: not valid java name */
    public static final void m749onResume$lambda12(HeartActivity this$0, final RealTimeHeartRateRsp realTimeHeartRateRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (realTimeHeartRateRsp == null || realTimeHeartRateRsp.getHeart() <= 0) {
            return;
        }
        ThreadExtKt.ktxRunOnUi(this$0, new Function1<HeartActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$onResume$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeartActivity heartActivity) {
                invoke2(heartActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeartActivity ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                ActivityHeartBinding activityHeartBinding = ktxRunOnUi.binding;
                if (activityHeartBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHeartBinding = null;
                }
                activityHeartBinding.tvCurrHeart.setText(String.valueOf(realTimeHeartRateRsp.getHeart()));
            }
        });
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        CommandHandle.getInstance().executeReqCmdNoCallback(new RealTimeHeartRate(2));
        this.handler.removeCallbacks(this.callback);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (messageEvent instanceof ManualRefreshEvent) {
            if (this.dateUtil.isToday()) {
                getViewModel().showHeartRateDetail(this.dateUtil);
                return;
            }
            return;
        }
        if (messageEvent instanceof BluetoothEvent) {
            ActivityHeartBinding activityHeartBinding = null;
            ScaleAnimation scaleAnimation = null;
            if (((BluetoothEvent) messageEvent).getConnect()) {
                ActivityHeartBinding activityHeartBinding2 = this.binding;
                if (activityHeartBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHeartBinding2 = null;
                }
                ImageView imageView = activityHeartBinding2.iamgeHeart;
                ScaleAnimation scaleAnimation2 = this.animation;
                if (scaleAnimation2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("animation");
                } else {
                    scaleAnimation = scaleAnimation2;
                }
                imageView.startAnimation(scaleAnimation);
                CommandHandle.getInstance().executeReqCmd(new RealTimeHeartRate(1), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$$ExternalSyntheticLambda10
                    @Override // com.oudmon.ble.base.communication.ICommandResponse
                    public final void onDataResponse(BaseRspCmd baseRspCmd) {
                        HeartActivity.m748onMessageEvent$lambda13(this.f$0, (RealTimeHeartRateRsp) baseRspCmd);
                    }
                });
                return;
            }
            ActivityHeartBinding activityHeartBinding3 = this.binding;
            if (activityHeartBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityHeartBinding = activityHeartBinding3;
            }
            activityHeartBinding.iamgeHeart.clearAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onMessageEvent$lambda-13, reason: not valid java name */
    public static final void m748onMessageEvent$lambda13(HeartActivity this$0, final RealTimeHeartRateRsp realTimeHeartRateRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (realTimeHeartRateRsp == null || realTimeHeartRateRsp.getHeart() <= 0) {
            return;
        }
        ThreadExtKt.ktxRunOnUi(this$0, new Function1<HeartActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.heart.HeartActivity$onMessageEvent$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeartActivity heartActivity) {
                invoke2(heartActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeartActivity ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                ActivityHeartBinding activityHeartBinding = ktxRunOnUi.binding;
                if (activityHeartBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityHeartBinding = null;
                }
                activityHeartBinding.tvCurrHeart.setText(String.valueOf(realTimeHeartRateRsp.getHeart()));
            }
        });
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeOutDeviceListener(1);
    }

    /* compiled from: HeartActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivity$MyDeviceNotifyListener;", "Lcom/oudmon/ble/base/communication/responseImpl/DeviceNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivity;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/DeviceNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyDeviceNotifyListener extends DeviceNotifyListener {
        public MyDeviceNotifyListener() {
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(DeviceNotifyRsp resultEntity) throws InterruptedException {
            super.onDataResponse(resultEntity);
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0 && resultEntity.getDataType() == 1) {
                HeartActivity.this.getViewModel().syncTodayData();
            }
        }
    }
}
