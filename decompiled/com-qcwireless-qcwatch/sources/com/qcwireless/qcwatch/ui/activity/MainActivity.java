package com.qcwireless.qcwatch.ui.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.elvishew.xlog.XLog;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.util.ThreadUtils;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.event.CameraToastEvent;
import com.qcwireless.qcwatch.base.event.DeviceConfigEvent;
import com.qcwireless.qcwatch.base.event.DeviceNoScreenEvent;
import com.qcwireless.qcwatch.base.event.FirmCheckEvent;
import com.qcwireless.qcwatch.base.event.LoginSuccessEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.OnThemeEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.event.UnbindDeviceEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.lifecycle.QcLifeCycle;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMainBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository;
import com.qcwireless.qcwatch.ui.base.service.NetService;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.device.DeviceFragment;
import com.qcwireless.qcwatch.ui.device.camera.CameraActivity;
import com.qcwireless.qcwatch.ui.device.dfu.ChinaDeviceFirmwareUpdateActivity;
import com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity;
import com.qcwireless.qcwatch.ui.device.dfu.WatchFileDismissActivity;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import com.qcwireless.qcwatch.ui.home.healthy.HealthyFragment;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.qcwireless.qcwatch.ui.mine.skin.util.SkinType;
import com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.GoogleFitSync;
import com.qcwireless.qcwatch.ui.plate.PlateFragment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 G2\u00020\u0001:\u0006DEFGHIB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020#H\u0002J\b\u0010%\u001a\u00020#H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020#H\u0002J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020#H\u0016J\u0012\u0010.\u001a\u00020#2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\u0010\u00101\u001a\u00020#2\u0006\u00102\u001a\u000203H\u0017J\b\u00104\u001a\u00020#H\u0014J\u0010\u00105\u001a\u00020#2\u0006\u0010/\u001a\u000200H\u0014J\b\u00106\u001a\u00020#H\u0014J\b\u00107\u001a\u00020#H\u0002J\b\u00108\u001a\u00020#H\u0002J\u0010\u00109\u001a\u00020#2\u0006\u0010:\u001a\u00020,H\u0002J\b\u0010;\u001a\u00020#H\u0014J\u0010\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u00020\fH\u0002J\b\u0010>\u001a\u00020#H\u0002J\u0016\u0010?\u001a\u00020#2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020B0AH\u0002J\b\u0010C\u001a\u00020#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u00060 R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/MainActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "backPressTime", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMainBinding;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "centerDialog", "Lcom/qcwireless/qcwatch/base/dialog/CenterDialog;", "connectFailNumber", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "fragmentManager$delegate", "Lkotlin/Lazy;", "handler", "Landroid/os/Handler;", "homeDevice", "Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;", "homeHealth", "Lcom/qcwireless/qcwatch/ui/home/healthy/HealthyFragment;", "homePlate", "Lcom/qcwireless/qcwatch/ui/plate/PlateFragment;", "mineFragment", "Lcom/qcwireless/qcwatch/ui/mine/MineFragment;", "refuseDialog", "Lcom/qcwireless/qcwatch/base/dialog/BottomDialog;", "runnable", "Lcom/qcwireless/qcwatch/ui/activity/MainActivity$ReconnectRunnable;", "tabIndex", "clearAllSelected", "", "deviceScanConfig", "doServer", "hideFragments", "transaction", "Landroidx/fragment/app/FragmentTransaction;", "initDeviceRequestLocation", "notificationUiRefresh", "selectionIndex", "Landroid/view/View;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onPause", "onRestoreInstanceState", "onResume", "processBackPressed", "requestPermissions", "setTabSelection", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "setupViews", "showAppUpgradeDialog", "appUpgradeCode", "showPermissionInformationDialog", "showPermissionSettingDialog", "permissions", "", "", "upDataCollection", "AllPermissionCallback", "BluetoothPermissionCallback", "CameraPermissionCallback", "Companion", "PermissionCallback", "ReconnectRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MainActivity extends BaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private long backPressTime;
    private ActivityMainBinding binding;
    private BluetoothAdapter bluetoothAdapter;
    private CenterDialog centerDialog;
    private int connectFailNumber;
    private final Handler handler;
    private DeviceFragment homeDevice;
    private HealthyFragment homeHealth;
    private PlateFragment homePlate;
    private MineFragment mineFragment;
    private BottomDialog refuseDialog;
    private int tabIndex;

    /* renamed from: fragmentManager$delegate, reason: from kotlin metadata */
    private final Lazy fragmentManager = LazyKt.lazy(new Function0<FragmentManager>() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$fragmentManager$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FragmentManager invoke() {
            FragmentManager supportFragmentManager = this.this$0.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
            return supportFragmentManager;
        }
    });
    private final ReconnectRunnable runnable = new ReconnectRunnable();

    public MainActivity() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    private final FragmentManager getFragmentManager() {
        return (FragmentManager) this.fragmentManager.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Exception unused) {
        }
        ActivityMainBinding activityMainBindingInflate = ActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMainBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMainBindingInflate;
        if (activityMainBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBindingInflate = null;
        }
        LinearLayout root = activityMainBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
        if (PermissionUtilKt.hasLocationPermission(this)) {
            QCApplication.INSTANCE.getGetInstance().getLocationOnce(QCApplication.INSTANCE.getApplication());
        }
        setStatusBarBackground(R.color.healthy_bg);
        UserConfig companion = UserConfig.INSTANCE.getInstance();
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getDefault().language");
        companion.setLanguageCurr(language);
        UserConfig.INSTANCE.getInstance().save();
        QCApplication.INSTANCE.getGetInstance().trySetupNotifyService(this);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Intrinsics.checkNotNullExpressionValue(defaultAdapter, "getDefaultAdapter()");
        this.bluetoothAdapter = defaultAdapter;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        try {
            super.onRestoreInstanceState(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(this, (Class<?>) MainActivity.class);
            intent.addFlags(67108864);
            intent.addFlags(536870912);
            intent.addFlags(268435456);
            startActivity(intent);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws InterruptedException, NoSuchMethodException, SecurityException {
        super.setupViews();
        View[] viewArr = new View[4];
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        viewArr[0] = activityMainBinding.btnHealth;
        ActivityMainBinding activityMainBinding2 = this.binding;
        if (activityMainBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding2 = null;
        }
        viewArr[1] = activityMainBinding2.btnDevice;
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        viewArr[2] = activityMainBinding3.btnPlate;
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding4 = null;
        }
        viewArr[3] = activityMainBinding4.btnMine;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity.setupViews.1
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
                ActivityMainBinding activityMainBinding5 = MainActivity.this.binding;
                ActivityMainBinding activityMainBinding6 = null;
                if (activityMainBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding5 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityMainBinding5.btnHealth)) {
                    ActivityMainBinding activityMainBinding7 = MainActivity.this.binding;
                    if (activityMainBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding7 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, activityMainBinding7.btnDevice)) {
                        ActivityMainBinding activityMainBinding8 = MainActivity.this.binding;
                        if (activityMainBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding8 = null;
                        }
                        if (!Intrinsics.areEqual(setOnClickListener, activityMainBinding8.btnPlate)) {
                            ActivityMainBinding activityMainBinding9 = MainActivity.this.binding;
                            if (activityMainBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMainBinding9 = null;
                            }
                            if (Intrinsics.areEqual(setOnClickListener, activityMainBinding9.btnMine)) {
                                MainActivity mainActivity = MainActivity.this;
                                ActivityMainBinding activityMainBinding10 = mainActivity.binding;
                                if (activityMainBinding10 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMainBinding10 = null;
                                }
                                View view = activityMainBinding10.btnMine;
                                Intrinsics.checkNotNullExpressionValue(view, "binding.btnMine");
                                mainActivity.setTabSelection(view);
                                MainActivity mainActivity2 = MainActivity.this;
                                ActivityMainBinding activityMainBinding11 = mainActivity2.binding;
                                if (activityMainBinding11 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityMainBinding6 = activityMainBinding11;
                                }
                                View view2 = activityMainBinding6.btnMine;
                                Intrinsics.checkNotNullExpressionValue(view2, "binding.btnMine");
                                mainActivity2.notificationUiRefresh(view2);
                                return;
                            }
                            return;
                        }
                        MainActivity mainActivity3 = MainActivity.this;
                        ActivityMainBinding activityMainBinding12 = mainActivity3.binding;
                        if (activityMainBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding12 = null;
                        }
                        View view3 = activityMainBinding12.btnPlate;
                        Intrinsics.checkNotNullExpressionValue(view3, "binding.btnPlate");
                        mainActivity3.setTabSelection(view3);
                        MainActivity mainActivity4 = MainActivity.this;
                        ActivityMainBinding activityMainBinding13 = mainActivity4.binding;
                        if (activityMainBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMainBinding6 = activityMainBinding13;
                        }
                        View view4 = activityMainBinding6.btnPlate;
                        Intrinsics.checkNotNullExpressionValue(view4, "binding.btnPlate");
                        mainActivity4.notificationUiRefresh(view4);
                        return;
                    }
                    MainActivity mainActivity5 = MainActivity.this;
                    ActivityMainBinding activityMainBinding14 = mainActivity5.binding;
                    if (activityMainBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding14 = null;
                    }
                    View view5 = activityMainBinding14.btnDevice;
                    Intrinsics.checkNotNullExpressionValue(view5, "binding.btnDevice");
                    mainActivity5.setTabSelection(view5);
                    MainActivity mainActivity6 = MainActivity.this;
                    ActivityMainBinding activityMainBinding15 = mainActivity6.binding;
                    if (activityMainBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding6 = activityMainBinding15;
                    }
                    View view6 = activityMainBinding6.btnDevice;
                    Intrinsics.checkNotNullExpressionValue(view6, "binding.btnDevice");
                    mainActivity6.notificationUiRefresh(view6);
                    return;
                }
                MainActivity mainActivity7 = MainActivity.this;
                ActivityMainBinding activityMainBinding16 = mainActivity7.binding;
                if (activityMainBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding16 = null;
                }
                View view7 = activityMainBinding16.btnHealth;
                Intrinsics.checkNotNullExpressionValue(view7, "binding.btnHealth");
                mainActivity7.setTabSelection(view7);
                MainActivity mainActivity8 = MainActivity.this;
                ActivityMainBinding activityMainBinding17 = mainActivity8.binding;
                if (activityMainBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding6 = activityMainBinding17;
                }
                View view8 = activityMainBinding6.btnHealth;
                Intrinsics.checkNotNullExpressionValue(view8, "binding.btnHealth");
                mainActivity8.notificationUiRefresh(view8);
            }
        });
        ActivityMainBinding activityMainBinding5 = this.binding;
        if (activityMainBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding5 = null;
        }
        View view = activityMainBinding5.btnHealth;
        Intrinsics.checkNotNullExpressionValue(view, "binding.btnHealth");
        setTabSelection(view);
        if (UserConfig.INSTANCE.getInstance().getPermissionDescFlag()) {
            showPermissionInformationDialog();
        } else {
            requestPermissions();
        }
        doServer();
        String language = Locale.getDefault().getLanguage();
        if (UserConfig.INSTANCE.getInstance().getGoogleFit()) {
            Intrinsics.checkNotNullExpressionValue(language, "language");
            if (!StringsKt.contains$default((CharSequence) language, (CharSequence) "zh", false, 2, (Object) null)) {
                GoogleFitSync.INSTANCE.getGetInstance().connectGoogleFit(this);
            }
        }
        initDeviceRequestLocation();
        ThreadExtKt.ktxRunOnBgSingleNetWork(this, new Function1<MainActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity.setupViews.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MainActivity mainActivity) {
                invoke2(mainActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MainActivity ktxRunOnBgSingleNetWork) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleNetWork, "$this$ktxRunOnBgSingleNetWork");
                ktxRunOnBgSingleNetWork.deviceScanConfig();
            }
        });
        if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() > 0) {
            BleOperateManager.getInstance().setReconnectStartOrNot(true);
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1", f = "MainActivity.kt", i = {0, 1, 2}, l = {207, 207, 228, 228, 246, 246}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = MainActivity.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x006d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0082 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x009d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00b0 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00c3 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                switch(r1) {
                    case 0: goto L38;
                    case 1: goto L30;
                    case 2: goto L28;
                    case 3: goto L20;
                    case 4: goto L1b;
                    case 5: goto L16;
                    case 6: goto L11;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L11:
                kotlin.ResultKt.throwOnFailure(r6)
                goto Lc4
            L16:
                kotlin.ResultKt.throwOnFailure(r6)
                goto Lb1
            L1b:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L9e
            L20:
                java.lang.Object r1 = r5.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r6)
                goto L83
            L28:
                java.lang.Object r1 = r5.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r6)
                goto L6e
            L30:
                java.lang.Object r1 = r5.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r6)
                goto L59
            L38:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                r1 = r6
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$Companion r6 = com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r6 = r6.getGetInstance()
                java.lang.String r2 = com.qcwireless.qcwatch.base.view.GlobalKt.getAppName()
                r3 = r5
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r5.L$0 = r1
                r4 = 1
                r5.label = r4
                java.lang.Object r6 = r6.getDeviceScanConfigFromServer(r2, r3)
                if (r6 != r0) goto L59
                return r0
            L59:
                kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
                com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$1<T> r2 = new kotlinx.coroutines.flow.FlowCollector() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity.deviceScanConfig.1.1
                    static {
                        /*
                            com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$1 r0 = new com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$1
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$1<T>) com.qcwireless.qcwatch.ui.activity.MainActivity.deviceScanConfig.1.1.INSTANCE com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$1
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.C00391.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.C00391.<init>():void");
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ java.lang.Object emit(java.lang.Object r1, kotlin.coroutines.Continuation r2) {
                        /*
                            r0 = this;
                            com.qcwireless.qcwatch.ui.base.repository.mine.NetState r1 = (com.qcwireless.qcwatch.ui.base.repository.mine.NetState) r1
                            java.lang.Object r1 = r0.emit(r1, r2)
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.C00391.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }

                    public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState<java.lang.String> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
                        /*
                            r6 = this;
                            java.lang.Object r8 = r7.isSuccess()     // Catch: java.lang.Exception -> L6e
                            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch: java.lang.Exception -> L6e
                            com.elvishew.xlog.XLog.i(r8)     // Catch: java.lang.Exception -> L6e
                            r0 = r8
                            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch: java.lang.Exception -> L6e
                            int r0 = r0.length()     // Catch: java.lang.Exception -> L6e
                            if (r0 <= 0) goto L16
                            r0 = 1
                            goto L17
                        L16:
                            r0 = 0
                        L17:
                            if (r0 == 0) goto L64
                            int r7 = r7.getRetCode()     // Catch: java.lang.Exception -> L6e
                            if (r7 != 0) goto L64
                            java.lang.String r7 = "com.qc.app_scan_config"
                            com.qcwireless.qcwatch.base.pref.PreUtil.putString(r7, r8)     // Catch: java.lang.Exception -> L6e
                            r0 = r8
                            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch: java.lang.Exception -> L6e
                            java.lang.String r7 = ","
                            java.lang.String[] r1 = new java.lang.String[]{r7}     // Catch: java.lang.Exception -> L6e
                            r2 = 0
                            r3 = 0
                            r4 = 6
                            r5 = 0
                            java.util.List r7 = kotlin.text.StringsKt.split$default(r0, r1, r2, r3, r4, r5)     // Catch: java.lang.Exception -> L6e
                            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Exception -> L6e
                        L39:
                            boolean r8 = r7.hasNext()     // Catch: java.lang.Exception -> L6e
                            if (r8 == 0) goto L54
                            java.lang.Object r8 = r7.next()     // Catch: java.lang.Exception -> L6e
                            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Exception -> L6e
                            com.qcwireless.qcwatch.QJavaApplication r0 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L6e
                            r0.putScanKeys(r8)     // Catch: java.lang.Exception -> L6e
                            com.qcwireless.qcwatch.QJavaApplication r0 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L6e
                            r0.putScanKeysMap(r8)     // Catch: java.lang.Exception -> L6e
                            goto L39
                        L54:
                            com.oudmon.ble.base.scan.BleScannerHelper r7 = com.oudmon.ble.base.scan.BleScannerHelper.getInstance()     // Catch: java.lang.Exception -> L6e
                            com.qcwireless.qcwatch.QJavaApplication r8 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L6e
                            java.util.List r8 = r8.getKeys()     // Catch: java.lang.Exception -> L6e
                            r7.setScanFilter(r8)     // Catch: java.lang.Exception -> L6e
                            goto L72
                        L64:
                            com.qcwireless.qcwatch.QCApplication$Companion r7 = com.qcwireless.qcwatch.QCApplication.INSTANCE     // Catch: java.lang.Exception -> L6e
                            com.qcwireless.qcwatch.QCApplication r7 = r7.getGetInstance()     // Catch: java.lang.Exception -> L6e
                            r7.initScanFilter()     // Catch: java.lang.Exception -> L6e
                            goto L72
                        L6e:
                            r7 = move-exception
                            r7.printStackTrace()
                        L72:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.C00391.emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                r3 = r5
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r5.L$0 = r1
                r4 = 2
                r5.label = r4
                java.lang.Object r6 = r6.collect(r2, r3)
                if (r6 != r0) goto L6e
                return r0
            L6e:
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$Companion r6 = com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r6 = r6.getGetInstance()
                r2 = r5
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r5.L$0 = r1
                r3 = 3
                r5.label = r3
                java.lang.Object r6 = r6.getAppUpgrade(r2)
                if (r6 != r0) goto L83
                return r0
            L83:
                kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
                com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$2 r2 = new com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$2
                com.qcwireless.qcwatch.ui.activity.MainActivity r3 = com.qcwireless.qcwatch.ui.activity.MainActivity.this
                r2.<init>()
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                r1 = r5
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r3 = 0
                r5.L$0 = r3
                r3 = 4
                r5.label = r3
                java.lang.Object r6 = r6.collect(r2, r1)
                if (r6 != r0) goto L9e
                return r0
            L9e:
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository$Companion r6 = com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository r6 = r6.getGetInstance()
                r1 = r5
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r2 = 5
                r5.label = r2
                java.lang.Object r6 = r6.getCsSupport(r1)
                if (r6 != r0) goto Lb1
                return r0
            Lb1:
                kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
                com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$3<T> r1 = new kotlinx.coroutines.flow.FlowCollector() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity.deviceScanConfig.1.3
                    static {
                        /*
                            com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$3 r0 = new com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$3
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$3<T>) com.qcwireless.qcwatch.ui.activity.MainActivity.deviceScanConfig.1.3.INSTANCE com.qcwireless.qcwatch.ui.activity.MainActivity$deviceScanConfig$1$3
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.AnonymousClass3.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.AnonymousClass3.<init>():void");
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ java.lang.Object emit(java.lang.Object r1, kotlin.coroutines.Continuation r2) {
                        /*
                            r0 = this;
                            com.qcwireless.qcwatch.ui.base.repository.mine.NetState r1 = (com.qcwireless.qcwatch.ui.base.repository.mine.NetState) r1
                            java.lang.Object r1 = r0.emit(r1, r2)
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.AnonymousClass3.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }

                    public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState<java.util.List<com.qcwireless.qcwatch.ui.base.bean.response.cs.SupportCsResp>> r2, kotlin.coroutines.Continuation<? super kotlin.Unit> r3) {
                        /*
                            r1 = this;
                            java.lang.Object r3 = r2.isSuccess()
                            com.elvishew.xlog.XLog.i(r3)
                            com.qcwireless.qcwatch.base.pref.UserConfig$Companion r3 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                            com.qcwireless.qcwatch.base.pref.UserConfig r3 = r3.getInstance()
                            com.google.gson.Gson r0 = new com.google.gson.Gson
                            r0.<init>()
                            java.lang.Object r2 = r2.isSuccess()
                            java.lang.String r2 = r0.toJson(r2)
                            java.lang.String r0 = "Gson().toJson(it1.isSuccess)"
                            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
                            r3.setSupportCs(r2)
                            com.qcwireless.qcwatch.base.pref.UserConfig$Companion r2 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                            com.qcwireless.qcwatch.base.pref.UserConfig r2 = r2.getInstance()
                            r2.save()
                            kotlin.Unit r2 = kotlin.Unit.INSTANCE
                            return r2
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.AnonymousClass3.emit(com.qcwireless.qcwatch.ui.base.repository.mine.NetState, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                r2 = r5
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 6
                r5.label = r3
                java.lang.Object r6 = r6.collect(r1, r2)
                if (r6 != r0) goto Lc4
                return r0
            Lc4:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.activity.MainActivity.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deviceScanConfig() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAppUpgradeDialog(int appUpgradeCode) {
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_app_upgrade);
        final CenterDialog centerDialogCreate = builder.create();
        centerDialogCreate.show();
        View contentView = centerDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        ((TextView) contentView.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.m265showAppUpgradeDialog$lambda0(centerDialogCreate, this, view);
            }
        });
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        if (appUpgradeCode == 1 || appUpgradeCode == 2) {
            ViewKt.visible(textView);
        } else if (appUpgradeCode == 3) {
            ViewKt.gone(textView);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                centerDialogCreate.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAppUpgradeDialog$lambda-0, reason: not valid java name */
    public static final void m265showAppUpgradeDialog$lambda0(CenterDialog centerDialog, MainActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        centerDialog.dismiss();
        this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.qcwireless.qcwatch")));
    }

    private final void initDeviceRequestLocation() {
        LargeDataHandler.getInstance().deviceRequestLocation(new ILargeDataResponse() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$$ExternalSyntheticLambda6
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, byte[] bArr) {
                MainActivity.m264initDeviceRequestLocation$lambda2(this.f$0, i, bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initDeviceRequestLocation$lambda-2, reason: not valid java name */
    public static final void m264initDeviceRequestLocation$lambda2(MainActivity this$0, int i, byte[] bArr) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 32) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MainActivity$initDeviceRequestLocation$1$1(this$0, null), 3, null);
        }
    }

    private final void upDataCollection() {
        if (UserConfig.INSTANCE.getInstance().getLastCollectionTime() < new DateUtil().getUnixTimestamp()) {
            if (UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear().length() > 0) {
                if (UserConfig.INSTANCE.getInstance().getFmVersion().length() > 0) {
                    if (UserConfig.INSTANCE.getInstance().getHwVersion().length() > 0) {
                        NetService.INSTANCE.getGetInstance().upCollectionData();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doServer() {
        if (UserConfig.INSTANCE.getInstance().getLastCollectionTime() < new DateUtil().getUnixTimestamp()) {
            if (UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear().length() > 0) {
                if (UserConfig.INSTANCE.getInstance().getFmVersion().length() > 0) {
                    NetService.INSTANCE.getGetInstance().upCollectionData();
                }
            }
        }
        ThreadUtils.postDelay(new ThreadUtils.TimeTask() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity.doServer.1
            @Override // com.oudmon.ble.base.util.ThreadUtils.TimeTask
            protected void task() {
                MessagePushRepository.INSTANCE.getGetInstance().initData();
            }
        }, 0L);
        if (UserConfig.INSTANCE.getInstance().getLoginStatus() && UserConfig.INSTANCE.getInstance().getLastSyncFromServerTime() < new DateUtil().getUnixTimestamp()) {
            NetService.INSTANCE.getGetInstance().downAll();
            NetService.INSTANCE.getGetInstance().downUserProfile();
            NetService.INSTANCE.getGetInstance().downGoalSetting();
            if (BleOperateManager.getInstance().isConnected()) {
                if (UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear().length() > 0) {
                    NetService.INSTANCE.getGetInstance().upAll();
                    UserConfig.INSTANCE.getInstance().setLastSyncFromServerTime(new DateUtil().getUnixTimestamp() + 43200);
                    UserConfig.INSTANCE.getInstance().save();
                }
            }
        }
        Integer num = QJavaApplication.getInstance().getScreenMap().get(UserConfig.INSTANCE.getInstance().getDeviceAddress());
        if (num != null && num.intValue() == 2) {
            ActivityMainBinding activityMainBinding = this.binding;
            if (activityMainBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding = null;
            }
            ViewKt.visible(activityMainBinding.showDial);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (messageEvent instanceof OnThemeEvent) {
            if (UserConfig.INSTANCE.getInstance().getSkinType() == SkinType.INSTANCE.getSkin_Black()) {
                ImmersionBar.with(this).statusBarDarkFont(false).transparentStatusBar().init();
                return;
            } else {
                ImmersionBar.with(this).statusBarDarkFont(true).transparentStatusBar().init();
                return;
            }
        }
        if (messageEvent instanceof LoginSuccessEvent) {
            ThreadExtKt.ktxRunOnUiDelay(this, TrackingService.Constant.FASTEST_UPDATE_INTERVAL, new Function1<MainActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity.onMessageEvent.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MainActivity mainActivity) {
                    invoke2(mainActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MainActivity ktxRunOnUiDelay) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                    ktxRunOnUiDelay.doServer();
                }
            });
            return;
        }
        if (messageEvent instanceof FirmCheckEvent) {
            upDataCollection();
            return;
        }
        if (messageEvent instanceof CameraToastEvent) {
            Activity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            PermissionUtilKt.requestCameraPermission((FragmentActivity) activity, new CameraPermissionCallback());
            return;
        }
        ActivityMainBinding activityMainBinding = null;
        if (messageEvent instanceof DeviceConfigEvent) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass2(messageEvent, null), 3, null);
            return;
        }
        if (messageEvent instanceof UnbindDeviceEvent) {
            try {
                Integer num = QJavaApplication.getInstance().getScreenMap().get(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                if (num == null || num.intValue() != 2) {
                    if (!UserConfig.INSTANCE.getInstance().getShowPlate()) {
                        ActivityMainBinding activityMainBinding2 = this.binding;
                        if (activityMainBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMainBinding = activityMainBinding2;
                        }
                        ViewKt.gone(activityMainBinding.showDial);
                        return;
                    }
                }
                ActivityMainBinding activityMainBinding3 = this.binding;
                if (activityMainBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding = activityMainBinding3;
                }
                ViewKt.visible(activityMainBinding.showDial);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (messageEvent instanceof DeviceNoScreenEvent) {
            if (UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
                ActivityMainBinding activityMainBinding4 = this.binding;
                if (activityMainBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding = activityMainBinding4;
                }
                ViewKt.gone(activityMainBinding.showDial);
                return;
            }
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (activityMainBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding5;
            }
            ViewKt.visible(activityMainBinding.showDial);
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.activity.MainActivity$onMessageEvent$2", f = "MainActivity.kt", i = {}, l = {382, 383}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.activity.MainActivity$onMessageEvent$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MessageEvent $messageEvent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(MessageEvent messageEvent, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$messageEvent = messageEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$messageEvent, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceDisplay(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final MessageEvent messageEvent = this.$messageEvent;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity.onMessageEvent.2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Integer) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(Integer num, Continuation<? super Unit> continuation) {
                    if (num == null) {
                        if (num == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            return num;
                        }
                    } else if (num.intValue() == 0) {
                        Object deviceConfig = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceConfig(((DeviceConfigEvent) messageEvent).getHdVersion(), continuation);
                        return deviceConfig == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? deviceConfig : Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/MainActivity$CameraPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/activity/MainActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CameraPermissionCallback implements OnPermissionCallback {
        public CameraPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (!all) {
                String string = MainActivity.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return;
            }
            MainActivity mainActivity = MainActivity.this;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(mainActivity, (Class<?>) CameraActivity.class);
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
            mainActivity.startActivity(intent);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                String string = MainActivity.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity((Activity) MainActivity.this, permissions);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            processBackPressed();
        }
    }

    private final void processBackPressed() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.backPressTime > TrackingService.Constant.FASTEST_UPDATE_INTERVAL) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(R.string.qc_text_5);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_5)");
            String str = String.format(string, Arrays.copyOf(new Object[]{GlobalKt.getAppName()}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            GlobalKt.showToast$default(str, 0, 1, null);
            this.backPressTime = jCurrentTimeMillis;
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTabSelection(View index) {
        clearAllSelected();
        FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "this");
        hideFragments(fragmentTransactionBeginTransaction);
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        if (Intrinsics.areEqual(index, activityMainBinding.btnHealth)) {
            this.tabIndex = 0;
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding3 = null;
            }
            activityMainBinding3.ivHealth.setSelected(true);
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding4;
            }
            activityMainBinding2.tvHealth.setSelected(true);
            HealthyFragment healthyFragment = this.homeHealth;
            if (healthyFragment == null) {
                HealthyFragment healthyFragmentNewInstance = HealthyFragment.INSTANCE.newInstance();
                this.homeHealth = healthyFragmentNewInstance;
                Intrinsics.checkNotNull(healthyFragmentNewInstance);
                fragmentTransactionBeginTransaction.add(R.id.homeContainer, healthyFragmentNewInstance);
            } else {
                Intrinsics.checkNotNull(healthyFragment);
                fragmentTransactionBeginTransaction.show(healthyFragment);
            }
        } else {
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (activityMainBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding5 = null;
            }
            if (Intrinsics.areEqual(index, activityMainBinding5.btnDevice)) {
                this.tabIndex = 1;
                ActivityMainBinding activityMainBinding6 = this.binding;
                if (activityMainBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding6 = null;
                }
                activityMainBinding6.ivDevice.setSelected(true);
                ActivityMainBinding activityMainBinding7 = this.binding;
                if (activityMainBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityMainBinding2 = activityMainBinding7;
                }
                activityMainBinding2.tvDevice.setSelected(true);
                DeviceFragment deviceFragment = this.homeDevice;
                if (deviceFragment == null) {
                    DeviceFragment deviceFragmentNewInstance = DeviceFragment.INSTANCE.newInstance();
                    this.homeDevice = deviceFragmentNewInstance;
                    Intrinsics.checkNotNull(deviceFragmentNewInstance);
                    fragmentTransactionBeginTransaction.add(R.id.homeContainer, deviceFragmentNewInstance);
                } else {
                    Intrinsics.checkNotNull(deviceFragment);
                    fragmentTransactionBeginTransaction.show(deviceFragment);
                }
            } else {
                ActivityMainBinding activityMainBinding8 = this.binding;
                if (activityMainBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding8 = null;
                }
                if (Intrinsics.areEqual(index, activityMainBinding8.btnPlate)) {
                    this.tabIndex = 2;
                    ActivityMainBinding activityMainBinding9 = this.binding;
                    if (activityMainBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding9 = null;
                    }
                    activityMainBinding9.ivPlate.setSelected(true);
                    ActivityMainBinding activityMainBinding10 = this.binding;
                    if (activityMainBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding2 = activityMainBinding10;
                    }
                    activityMainBinding2.tvPlate.setSelected(true);
                    PlateFragment plateFragment = this.homePlate;
                    if (plateFragment == null) {
                        PlateFragment plateFragmentNewInstance = PlateFragment.INSTANCE.newInstance();
                        this.homePlate = plateFragmentNewInstance;
                        Intrinsics.checkNotNull(plateFragmentNewInstance);
                        fragmentTransactionBeginTransaction.add(R.id.homeContainer, plateFragmentNewInstance);
                    } else {
                        Intrinsics.checkNotNull(plateFragment);
                        fragmentTransactionBeginTransaction.show(plateFragment);
                    }
                } else {
                    ActivityMainBinding activityMainBinding11 = this.binding;
                    if (activityMainBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding11 = null;
                    }
                    if (Intrinsics.areEqual(index, activityMainBinding11.btnMine)) {
                        this.tabIndex = 3;
                        ActivityMainBinding activityMainBinding12 = this.binding;
                        if (activityMainBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding12 = null;
                        }
                        activityMainBinding12.ivMine.setSelected(true);
                        ActivityMainBinding activityMainBinding13 = this.binding;
                        if (activityMainBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMainBinding2 = activityMainBinding13;
                        }
                        activityMainBinding2.tvMine.setSelected(true);
                        MineFragment mineFragment = this.mineFragment;
                        if (mineFragment == null) {
                            MineFragment mineFragmentNewInstance = MineFragment.INSTANCE.newInstance();
                            this.mineFragment = mineFragmentNewInstance;
                            Intrinsics.checkNotNull(mineFragmentNewInstance);
                            fragmentTransactionBeginTransaction.add(R.id.homeContainer, mineFragmentNewInstance);
                        } else {
                            Intrinsics.checkNotNull(mineFragment);
                            fragmentTransactionBeginTransaction.show(mineFragment);
                        }
                    } else {
                        this.tabIndex = 0;
                        ActivityMainBinding activityMainBinding14 = this.binding;
                        if (activityMainBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMainBinding14 = null;
                        }
                        activityMainBinding14.ivHealth.setSelected(true);
                        ActivityMainBinding activityMainBinding15 = this.binding;
                        if (activityMainBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMainBinding2 = activityMainBinding15;
                        }
                        activityMainBinding2.tvHealth.setSelected(true);
                        HealthyFragment healthyFragment2 = this.homeHealth;
                        if (healthyFragment2 == null) {
                            HealthyFragment healthyFragmentNewInstance2 = HealthyFragment.INSTANCE.newInstance();
                            this.homeHealth = healthyFragmentNewInstance2;
                            Intrinsics.checkNotNull(healthyFragmentNewInstance2);
                            fragmentTransactionBeginTransaction.add(R.id.homeContainer, healthyFragmentNewInstance2);
                        } else {
                            Intrinsics.checkNotNull(healthyFragment2);
                            fragmentTransactionBeginTransaction.show(healthyFragment2);
                        }
                    }
                }
            }
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void clearAllSelected() {
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        activityMainBinding.ivHealth.setSelected(false);
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        activityMainBinding3.tvHealth.setSelected(false);
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding4 = null;
        }
        activityMainBinding4.ivDevice.setSelected(false);
        ActivityMainBinding activityMainBinding5 = this.binding;
        if (activityMainBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding5 = null;
        }
        activityMainBinding5.tvDevice.setSelected(false);
        ActivityMainBinding activityMainBinding6 = this.binding;
        if (activityMainBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding6 = null;
        }
        activityMainBinding6.ivPlate.setSelected(false);
        ActivityMainBinding activityMainBinding7 = this.binding;
        if (activityMainBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding7 = null;
        }
        activityMainBinding7.tvPlate.setSelected(false);
        ActivityMainBinding activityMainBinding8 = this.binding;
        if (activityMainBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding8 = null;
        }
        activityMainBinding8.ivMine.setSelected(false);
        ActivityMainBinding activityMainBinding9 = this.binding;
        if (activityMainBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding9;
        }
        activityMainBinding2.tvMine.setSelected(false);
    }

    private final void hideFragments(FragmentTransaction transaction) {
        HealthyFragment healthyFragment = this.homeHealth;
        if (healthyFragment != null) {
            Intrinsics.checkNotNull(healthyFragment);
            transaction.hide(healthyFragment);
        }
        DeviceFragment deviceFragment = this.homeDevice;
        if (deviceFragment != null) {
            Intrinsics.checkNotNull(deviceFragment);
            transaction.hide(deviceFragment);
        }
        PlateFragment plateFragment = this.homePlate;
        if (plateFragment != null) {
            Intrinsics.checkNotNull(plateFragment);
            transaction.hide(plateFragment);
        }
        MineFragment mineFragment = this.mineFragment;
        if (mineFragment != null) {
            Intrinsics.checkNotNull(mineFragment);
            transaction.hide(mineFragment);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (!BluetoothUtils.isEnabledBluetooth(this)) {
                XLog.i("-----蓝牙关");
                Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(this, Permission.BLUETOOTH_CONNECT) != 0) {
                    return;
                } else {
                    startActivityForResult(intent, 0);
                }
            }
        } catch (Exception unused) {
        }
        ActivityMainBinding activityMainBinding = null;
        if (BleOperateManager.getInstance().isConnected()) {
            int updating = QCApplication.INSTANCE.getGetInstance().getUpdating();
            if (updating == 1) {
                String language = Locale.getDefault().getLanguage();
                Intrinsics.checkNotNullExpressionValue(language, "language");
                if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null)) {
                    MainActivity mainActivity = this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent2 = new Intent(mainActivity, (Class<?>) ChinaDeviceFirmwareUpdateActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    Unit unit3 = Unit.INSTANCE;
                    Unit unit4 = Unit.INSTANCE;
                    mainActivity.startActivity(intent2);
                } else {
                    MainActivity mainActivity2 = this;
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intent intent3 = new Intent(mainActivity2, (Class<?>) DeviceFirmwareUpdateActivity.class);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit5 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit6 = Unit.INSTANCE;
                    Unit unit7 = Unit.INSTANCE;
                    Unit unit8 = Unit.INSTANCE;
                    mainActivity2.startActivity(intent3);
                }
            } else if (updating == 2) {
                Bundle bundle = new Bundle();
                bundle.putInt("background", 1);
                Unit unit9 = Unit.INSTANCE;
                MainActivity mainActivity3 = this;
                ArrayList<Pair> arrayList3 = new ArrayList();
                Intent intent4 = new Intent(mainActivity3, (Class<?>) WatchFileDismissActivity.class);
                intent4.setFlags(1);
                intent4.putExtras(bundle);
                for (Pair pair3 : arrayList3) {
                    if (pair3 != null) {
                        String str3 = (String) pair3.getFirst();
                        Object second3 = pair3.getSecond();
                        if (second3 instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Number) second3).intValue()), "putExtra(name, value)");
                        } else if (second3 instanceof Byte) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Number) second3).byteValue()), "putExtra(name, value)");
                        } else if (second3 instanceof Character) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Character) second3).charValue()), "putExtra(name, value)");
                        } else if (second3 instanceof Short) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Number) second3).shortValue()), "putExtra(name, value)");
                        } else if (second3 instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Boolean) second3).booleanValue()), "putExtra(name, value)");
                        } else if (second3 instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Number) second3).longValue()), "putExtra(name, value)");
                        } else if (second3 instanceof Float) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Number) second3).floatValue()), "putExtra(name, value)");
                        } else if (second3 instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, ((Number) second3).doubleValue()), "putExtra(name, value)");
                        } else if (second3 instanceof String) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (String) second3), "putExtra(name, value)");
                        } else if (second3 instanceof CharSequence) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (CharSequence) second3), "putExtra(name, value)");
                        } else if (second3 instanceof Parcelable) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (Parcelable) second3), "putExtra(name, value)");
                        } else if (second3 instanceof Object[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                        } else if (second3 instanceof ArrayList) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                        } else if (second3 instanceof Serializable) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                        } else if (second3 instanceof boolean[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (boolean[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof byte[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (byte[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof short[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (short[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof char[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (char[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof int[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (int[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof long[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (long[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof float[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (float[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof double[]) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (double[]) second3), "putExtra(name, value)");
                        } else if (second3 instanceof Bundle) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (Bundle) second3), "putExtra(name, value)");
                        } else if (second3 instanceof Intent) {
                            Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str3, (Parcelable) second3), "putExtra(name, value)");
                        } else {
                            Unit unit10 = Unit.INSTANCE;
                        }
                    }
                }
                Unit unit11 = Unit.INSTANCE;
                Unit unit12 = Unit.INSTANCE;
                Unit unit13 = Unit.INSTANCE;
                mainActivity3.startActivity(intent4);
            }
        } else {
            this.handler.removeCallbacks(this.runnable);
            this.handler.postDelayed(this.runnable, 1000L);
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        }
        if (UserConfig.INSTANCE.getInstance().getLastLocationTime() < new DateUtil().getUnixTimestamp() && PermissionUtilKt.hasLocationPermission(this)) {
            QCApplication.INSTANCE.getGetInstance().getLocationOnce(QCApplication.INSTANCE.getApplication());
            UserConfig.INSTANCE.getInstance().setLastLocationTime((int) (new DateUtil().getUnixTimestamp() + TypedValues.Custom.TYPE_INT));
            UserConfig.INSTANCE.getInstance().save();
        }
        try {
            Integer num = QJavaApplication.getInstance().getScreenMap().get(UserConfig.INSTANCE.getInstance().getDeviceAddress());
            if (num == null || num.intValue() != 2) {
                if (!UserConfig.INSTANCE.getInstance().getShowPlate()) {
                    return;
                }
            }
            ActivityMainBinding activityMainBinding2 = this.binding;
            if (activityMainBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding = activityMainBinding2;
            }
            ViewKt.visible(activityMainBinding.showDial);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (UserConfig.INSTANCE.getInstance().getLastLocationTime() >= new DateUtil().getUnixTimestamp() || !PermissionUtilKt.hasLocationPermission(this)) {
            return;
        }
        QCApplication.INSTANCE.getGetInstance().getLocationOnce(QCApplication.INSTANCE.getApplication());
        UserConfig.INSTANCE.getInstance().setLastLocationTime((int) (new DateUtil().getUnixTimestamp() + TypedValues.Custom.TYPE_INT));
        UserConfig.INSTANCE.getInstance().save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notificationUiRefresh(View selectionIndex) {
        this.handler.removeCallbacks(this.runnable);
        this.handler.postDelayed(this.runnable, 1000L);
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        if (Intrinsics.areEqual(selectionIndex, activityMainBinding.btnHealth)) {
            setStatusBarBackground(R.color.healthy_bg);
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding3;
            }
            if (activityMainBinding2.ivHealth.isSelected()) {
                EventBus.getDefault().post(new RefreshEvent(HealthyFragment.class));
                return;
            }
            return;
        }
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding4 = null;
        }
        if (Intrinsics.areEqual(selectionIndex, activityMainBinding4.btnDevice)) {
            setStatusBarBackground(R.color.color_F9F9F9);
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (activityMainBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding5;
            }
            if (activityMainBinding2.ivDevice.isSelected()) {
                EventBus.getDefault().post(new RefreshEvent(DeviceFragment.class));
                return;
            }
            return;
        }
        ActivityMainBinding activityMainBinding6 = this.binding;
        if (activityMainBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding6 = null;
        }
        if (Intrinsics.areEqual(selectionIndex, activityMainBinding6.btnPlate)) {
            setStatusBarBackground(R.color.color_F9F9F9);
            ActivityMainBinding activityMainBinding7 = this.binding;
            if (activityMainBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding7;
            }
            if (activityMainBinding2.ivPlate.isSelected()) {
                EventBus.getDefault().post(new RefreshEvent(PlateFragment.class));
                return;
            }
            return;
        }
        ActivityMainBinding activityMainBinding8 = this.binding;
        if (activityMainBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding8 = null;
        }
        if (Intrinsics.areEqual(selectionIndex, activityMainBinding8.btnMine)) {
            setStatusBarBackground(R.color.healthy_bg);
            ActivityMainBinding activityMainBinding9 = this.binding;
            if (activityMainBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding9;
            }
            if (activityMainBinding2.ivMine.isSelected()) {
                EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
            }
        }
    }

    private final void requestPermissions() throws NoSuchMethodException, SecurityException {
        PermissionUtilKt.requestBluetoothPermission(this, new BluetoothPermissionCallback());
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/MainActivity$BluetoothPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/activity/MainActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class BluetoothPermissionCallback implements OnPermissionCallback {
        public BluetoothPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                return;
            }
            String string = MainActivity.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                XXPermissions.startPermissionActivity((Activity) MainActivity.this, permissions);
                String string = MainActivity.this.getString(R.string.qc_text_458);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_458)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/MainActivity$AllPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/activity/MainActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class AllPermissionCallback implements OnPermissionCallback {
        public AllPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                return;
            }
            String string = MainActivity.this.getString(R.string.qc_text_44);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_44)");
            GlobalKt.showToast(string, 1);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                XXPermissions.startPermissionActivity((Activity) MainActivity.this, permissions);
                String string = MainActivity.this.getString(R.string.qc_text_387);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_387)");
                GlobalKt.showToast(string, 1);
            }
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/MainActivity$ReconnectRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/activity/MainActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ReconnectRunnable implements Runnable {
        public ReconnectRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BleOperateManager.getInstance().isConnected()) {
                MainActivity.this.handler.removeCallbacks(MainActivity.this.runnable);
            } else if (QcLifeCycle.INSTANCE.isForeground()) {
                ThreadManager.getInstance().wakeUpNotWait();
                MainActivity.this.handler.postDelayed(MainActivity.this.runnable, 5000L);
            } else {
                ThreadManager.getInstance().wakeUp();
                MainActivity.this.handler.postDelayed(MainActivity.this.runnable, 5000L);
            }
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/MainActivity$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, (Class<?>) MainActivity.class));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    private final void showPermissionInformationDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_permission_information);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                MainActivity.m267showPermissionInformationDialog$lambda6(this.f$0, objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                MainActivity.m268showPermissionInformationDialog$lambda7(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPermissionInformationDialog$lambda-6, reason: not valid java name */
    public static final void m267showPermissionInformationDialog$lambda6(MainActivity this$0, Ref.ObjectRef bottomDialog, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.requestPermissions();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPermissionInformationDialog$lambda-7, reason: not valid java name */
    public static final void m268showPermissionInformationDialog$lambda7(MainActivity this$0, Ref.ObjectRef bottomDialog, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.requestPermissions();
        UserConfig.INSTANCE.getInstance().setPermissionDescFlag(false);
        UserConfig.INSTANCE.getInstance().save();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/activity/MainActivity$PermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/activity/MainActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PermissionCallback implements OnPermissionCallback {
        public PermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                return;
            }
            try {
                MainActivity.this.showPermissionSettingDialog(permissions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                try {
                    MainActivity.this.showPermissionSettingDialog(permissions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPermissionSettingDialog(final List<String> permissions) {
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_permission_warming_refuse);
        if (this.refuseDialog == null) {
            BottomDialog bottomDialogCreate = builder.create();
            this.refuseDialog = bottomDialogCreate;
            Intrinsics.checkNotNull(bottomDialogCreate);
            bottomDialogCreate.show();
            BottomDialog bottomDialog = this.refuseDialog;
            Intrinsics.checkNotNull(bottomDialog);
            View contentView = bottomDialog.getContentView();
            Intrinsics.checkNotNullExpressionValue(contentView, "refuseDialog!!.contentView");
            TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
            TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.m269showPermissionSettingDialog$lambda8(this.f$0, permissions, view);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.activity.MainActivity$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.m270showPermissionSettingDialog$lambda9(this.f$0, permissions, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionSettingDialog$lambda-8, reason: not valid java name */
    public static final void m269showPermissionSettingDialog$lambda8(MainActivity this$0, List permissions, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        BottomDialog bottomDialog = this$0.refuseDialog;
        Intrinsics.checkNotNull(bottomDialog);
        bottomDialog.dismiss();
        XXPermissions.startPermissionActivity((Activity) this$0, (List<String>) permissions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionSettingDialog$lambda-9, reason: not valid java name */
    public static final void m270showPermissionSettingDialog$lambda9(MainActivity this$0, List permissions, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        BottomDialog bottomDialog = this$0.refuseDialog;
        Intrinsics.checkNotNull(bottomDialog);
        bottomDialog.dismiss();
        XXPermissions.startPermissionActivity((Activity) this$0, (List<String>) permissions);
    }
}
