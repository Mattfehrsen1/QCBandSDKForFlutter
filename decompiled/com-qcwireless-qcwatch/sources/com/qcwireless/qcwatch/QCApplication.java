package com.qcwireless.qcwatch;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.TelephonyManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import androidx.work.WorkRequest;
import com.app.watch.keeping.Cactus;
import com.app.watch.keeping.ext.CactusExtKt;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.blankj.utilcode.constant.CacheConstants;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.OkDownloadProvider;
import com.liulishuo.okdownload.core.dispatcher.DownloadDispatcher;
import com.liulishuo.okdownload.core.file.CustomProcessFileStrategy;
import com.oudmon.ble.base.bluetooth.BleAction;
import com.oudmon.ble.base.bluetooth.BleBaseControl;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.req.WeatherForecastReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.FindPhoneRsp;
import com.oudmon.ble.base.communication.rsp.WeatherForecastRsp;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.di.KoinModuleKt;
import com.qcwireless.qcwatch.base.lifecycle.QcLifeCycle;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.ui.base.bean.device.OneKeySupport;
import com.qcwireless.qcwatch.ui.base.bean.request.weather.WeatherRequest;
import com.qcwireless.qcwatch.ui.base.bean.response.weather.WeatherResp;
import com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean;
import com.qcwireless.qcwatch.ui.base.receiver.BluetoothReceiver;
import com.qcwireless.qcwatch.ui.base.receiver.MyBluetoothReceiver;
import com.qcwireless.qcwatch.ui.base.receiver.SystemLocaleChangeReceiver;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.weather.WeatherRepository;
import com.qcwireless.qcwatch.ui.base.service.XService;
import com.qcwireless.qcwatch.ui.base.service.YService;
import com.qcwireless.qcwatch.ui.base.util.MediaUtil;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.device.push.call.MyPhoneStateListener;
import com.qcwireless.qcwatch.ui.device.push.service.MyNotificationService;
import com.qcwireless.qcwatch.ui.home.gps.util.GpsUtilsKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.koin.android.ext.koin.KoinExtKt;
import org.koin.core.KoinApplication;
import org.koin.core.context.GlobalContextKt;
import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.app.SkinMaterialViewInflater;

/* compiled from: QCApplication.kt */
@Metadata(d1 = {"\u0000w\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0012\u0018\u0000 I2\u00020\u0001:\u0002IJB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0014J\b\u0010/\u001a\u00020,H\u0002J\b\u00100\u001a\u00020,H\u0002J\u0010\u00101\u001a\u00020,2\b\u00102\u001a\u0004\u0018\u000103J\b\u00104\u001a\u00020,H\u0002J\b\u00105\u001a\u00020,H\u0002J\b\u00106\u001a\u00020,H\u0002J\b\u00107\u001a\u00020,H\u0002J\b\u00108\u001a\u00020,H\u0002J\b\u00109\u001a\u00020,H\u0002J\b\u0010:\u001a\u00020,H\u0002J\u0006\u0010;\u001a\u00020,J\b\u0010<\u001a\u00020,H\u0002J\b\u0010=\u001a\u00020,H\u0002J\b\u0010>\u001a\u00020,H\u0002J\u000e\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020.J\b\u0010A\u001a\u00020,H\u0016J\u0006\u0010 \u001a\u00020,J\b\u0010B\u001a\u00020\u0004H\u0002J\u0010\u0010C\u001a\u00020,2\u0006\u0010@\u001a\u00020.H\u0002J\u000e\u0010D\u001a\u00020,2\u0006\u0010@\u001a\u00020.J\u0016\u0010E\u001a\u00020,2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020H0GH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006K"}, d2 = {"Lcom/qcwireless/qcwatch/QCApplication;", "Landroidx/multidex/MultiDexApplication;", "()V", "chinaServer", "", "getChinaServer", "()Z", "setChinaServer", "(Z)V", "downloadWatchFace", "getDownloadWatchFace", "setDownloadWatchFace", "findPhoneRspIOdmOpResponse", "Lcom/oudmon/ble/base/communication/ICommandResponse;", "Lcom/oudmon/ble/base/communication/rsp/FindPhoneRsp;", "handler", "Landroid/os/Handler;", "locationCallback", "com/qcwireless/qcwatch/QCApplication$locationCallback$1", "Lcom/qcwireless/qcwatch/QCApplication$locationCallback$1;", "mLocationClient", "Lcom/baidu/location/LocationClient;", "getMLocationClient", "()Lcom/baidu/location/LocationClient;", "setMLocationClient", "(Lcom/baidu/location/LocationClient;)V", "mediaRunnable", "Ljava/lang/Runnable;", "mediaUtil", "Lcom/qcwireless/qcwatch/ui/base/util/MediaUtil;", "myListener", "Lcom/qcwireless/qcwatch/QCApplication$MyLocationListener;", "pingHwServer", "getPingHwServer", "setPingHwServer", "qcLifeCycle", "Lcom/qcwireless/qcwatch/base/lifecycle/QcLifeCycle;", "updating", "", "getUpdating", "()I", "setUpdating", "(I)V", "attachBaseContext", "", "base", "Landroid/content/Context;", "doCacheWeatherToDevice", "getLocationFromAndroid", "getLocationOnce", "application", "Landroid/app/Application;", "init", "initFindPhone", "initKeepAlive", "initLog", "initOkDownLoad", "initRTKSPP", "initReceiver", "initScanFilter", "initService", "initSkin", "initXLog", "isNotificationServiceRunning", "context", "onCreate", "shouldInit", "toggleNotificationService", "trySetupNotifyService", "weatherToDevice", "data", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/weather/WeatherResp;", "Companion", "MyLocationListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QCApplication extends MultiDexApplication {
    private static Application application;
    private boolean downloadWatchFace;
    private ICommandResponse<FindPhoneRsp> findPhoneRspIOdmOpResponse;
    private final Handler handler;
    private final QCApplication$locationCallback$1 locationCallback;
    private LocationClient mLocationClient;
    private Runnable mediaRunnable;
    private MediaUtil mediaUtil;
    private int updating;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ReadWriteProperty<Object, Context> CONTEXT$delegate = Delegates.INSTANCE.notNull();
    private static final Lazy<QCApplication> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<QCApplication>() { // from class: com.qcwireless.qcwatch.QCApplication$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final QCApplication invoke() {
            return new QCApplication();
        }
    });
    private final QcLifeCycle qcLifeCycle = new QcLifeCycle();
    private final MyLocationListener myListener = new MyLocationListener();
    private boolean pingHwServer = true;
    private boolean chinaServer = true;

    public final void pingHwServer() {
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.qcwireless.qcwatch.QCApplication$locationCallback$1] */
    public QCApplication() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.QCApplication$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
        this.findPhoneRspIOdmOpResponse = new ICommandResponse() { // from class: com.qcwireless.qcwatch.QCApplication$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                QCApplication.m242findPhoneRspIOdmOpResponse$lambda0(this.f$0, (FindPhoneRsp) baseRspCmd);
            }
        };
        this.mediaRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.QCApplication$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                QCApplication.m243mediaRunnable$lambda1(this.f$0);
            }
        };
        this.locationCallback = new LocationCallback() { // from class: com.qcwireless.qcwatch.QCApplication$locationCallback$1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) throws IOException {
                Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                for (Location location : locationResult.getLocations()) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    XLog.i(latitude + "-----" + longitude);
                    Application application2 = QJavaApplication.getInstance().getApplication();
                    Intrinsics.checkNotNullExpressionValue(application2, "getInstance().application");
                    String[] address = GpsUtilsKt.getAddress(application2, latitude, longitude);
                    XLog.i((Object[]) address);
                    String strValueOf = "";
                    String strValueOf2 = address[1] != null ? String.valueOf(address[1]) : "";
                    if (address[2] != null) {
                        strValueOf = String.valueOf(address[2]);
                    }
                    DeviceSettingRepository.INSTANCE.getGetInstance().saveDeviceLocation(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), new MyLocationBean((float) longitude, (float) latitude, "en", strValueOf2, strValueOf2 + strValueOf));
                }
            }
        };
    }

    public final LocationClient getMLocationClient() {
        return this.mLocationClient;
    }

    public final void setMLocationClient(LocationClient locationClient) {
        this.mLocationClient = locationClient;
    }

    public final int getUpdating() {
        return this.updating;
    }

    public final void setUpdating(int i) {
        this.updating = i;
    }

    public final boolean getDownloadWatchFace() {
        return this.downloadWatchFace;
    }

    public final void setDownloadWatchFace(boolean z) {
        this.downloadWatchFace = z;
    }

    public final boolean getPingHwServer() {
        return this.pingHwServer;
    }

    public final void setPingHwServer(boolean z) {
        this.pingHwServer = z;
    }

    public final boolean getChinaServer() {
        return this.chinaServer;
    }

    public final void setChinaServer(boolean z) {
        this.chinaServer = z;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        application = this;
        initXLog();
        if (shouldInit()) {
            initSkin();
            init();
            initReceiver();
            initLog();
            initService();
            initKeepAlive();
            initOkDownLoad();
            initFindPhone();
            initRTKSPP();
            pingHwServer();
        }
    }

    private final void initRTKSPP() {
        BleOperateManager.getInstance().initRTKSPP(this);
    }

    private final void initFindPhone() {
        this.mediaUtil = new MediaUtil(this);
        BleOperateManager.getInstance().addNotifyListener(34, this.findPhoneRspIOdmOpResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: findPhoneRspIOdmOpResponse$lambda-0, reason: not valid java name */
    public static final void m242findPhoneRspIOdmOpResponse$lambda0(QCApplication this$0, FindPhoneRsp findPhoneRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(findPhoneRsp);
        if (findPhoneRsp != null) {
            try {
                if (findPhoneRsp.getSwitchStatue() == 1) {
                    try {
                        this$0.handler.removeCallbacks(this$0.mediaRunnable);
                        this$0.handler.postDelayed(this$0.mediaRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    MediaUtil mediaUtil = this$0.mediaUtil;
                    Intrinsics.checkNotNull(mediaUtil);
                    mediaUtil.vibrateAndPlayTone();
                    return;
                }
                if (findPhoneRsp.getSwitchStatue() == 2) {
                    MediaUtil mediaUtil2 = this$0.mediaUtil;
                    Intrinsics.checkNotNull(mediaUtil2);
                    mediaUtil2.stopRing();
                    this$0.handler.removeCallbacks(this$0.mediaRunnable);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mediaRunnable$lambda-1, reason: not valid java name */
    public static final void m243mediaRunnable$lambda1(QCApplication this$0) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaUtil mediaUtil = this$0.mediaUtil;
        Intrinsics.checkNotNull(mediaUtil);
        mediaUtil.stopRing();
    }

    private final void initOkDownLoad() {
        OkDownload.setSingletonInstance(new OkDownload.Builder(this).processFileStrategy(new CustomProcessFileStrategy()).build());
        OkDownloadProvider.context = INSTANCE.getCONTEXT();
        DownloadDispatcher.setMaxParallelRunningCount(10);
    }

    private final boolean shouldInit() {
        Object systemService = getSystemService("activity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        String packageName = getPackageName();
        int iMyPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid && Intrinsics.areEqual(packageName, runningAppProcessInfo.processName)) {
                XLog.e("my.pid -> " + iMyPid + ",mainProcessName -> " + packageName);
                return true;
            }
        }
        return false;
    }

    private final void initReceiver() {
        QCApplication qCApplication = this;
        BleOperateManager.getInstance(qCApplication);
        BleOperateManager.getInstance().setApplication(qCApplication);
        BleOperateManager.getInstance().init();
        IntentFilter deviceIntentFilter = BleAction.getDeviceIntentFilter();
        Intrinsics.checkNotNullExpressionValue(deviceIntentFilter, "getDeviceIntentFilter()");
        BluetoothReceiver bluetoothReceiver = new BluetoothReceiver();
        if (Build.VERSION.SDK_INT >= 26) {
            registerReceiver(bluetoothReceiver, deviceIntentFilter, 2);
        } else {
            registerReceiver(bluetoothReceiver, deviceIntentFilter);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        if (Build.VERSION.SDK_INT >= 26) {
            registerReceiver(new SystemLocaleChangeReceiver(), intentFilter, 2);
        } else {
            registerReceiver(new SystemLocaleChangeReceiver(), intentFilter);
        }
    }

    private final void init() {
        QJavaApplication.getInstance().setApplication(this);
        QCApplication qCApplication = this;
        PreUtil.init(qCApplication);
        UserConfig.INSTANCE.getInstance(qCApplication);
        LocationClient.setAgreePrivacy(true);
        registerActivityLifecycleCallbacks(this.qcLifeCycle);
        GlobalContextKt.startKoin(new Function1<KoinApplication, Unit>() { // from class: com.qcwireless.qcwatch.QCApplication.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(KoinApplication koinApplication) {
                invoke2(koinApplication);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(KoinApplication startKoin) {
                Intrinsics.checkNotNullParameter(startKoin, "$this$startKoin");
                Companion companion = QCApplication.INSTANCE;
                Context applicationContext = QCApplication.this.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                companion.setCONTEXT(applicationContext);
                KoinExtKt.androidContext(startKoin, QCApplication.this);
                startKoin.modules(KoinModuleKt.getAppModule());
            }
        });
        IntentFilter intentFilter = BleAction.getIntentFilter();
        LocalBroadcastManager.getInstance(INSTANCE.getCONTEXT()).registerReceiver(new MyBluetoothReceiver(), intentFilter);
        trySetupNotifyService(qCApplication);
        initScanFilter();
    }

    public final void initScanFilter() {
        try {
            PreUtil.putString(PreUtil.Action_Scan_Config, "G69,O_,o_,MS,Ma,Mi,H5,H1,Ha,HW,Exo,Evoke,P4,P5,P6,P7,P8,PBL,S2,S4,S5,S ,ST,SW,SE,AFTWS0423S,iHunt,T88,Moi,WT001,N5,E7,GT,FireBoltt 172,FireBoltt 188,FireBoltt 130,FireBoltt 106,FireBoltt 138,EW11,RSW007,TS105,Watch,Y15,Ul,ul,Unbound,Duke,Capri ,TR,i31,GS9,OG,Go,W2,AHA,AMD,ADM,LYCKA,Laleli,Ch,TM05,air,Hello,FT9 MINI");
            for (String str : StringsKt.split$default((CharSequence) "G69,O_,o_,MS,Ma,Mi,H5,H1,Ha,HW,Exo,Evoke,P4,P5,P6,P7,P8,PBL,S2,S4,S5,S ,ST,SW,SE,AFTWS0423S,iHunt,T88,Moi,WT001,N5,E7,GT,FireBoltt 172,FireBoltt 188,FireBoltt 130,FireBoltt 106,FireBoltt 138,EW11,RSW007,TS105,Watch,Y15,Ul,ul,Unbound,Duke,Capri ,TR,i31,GS9,OG,Go,W2,AHA,AMD,ADM,LYCKA,Laleli,Ch,TM05,air,Hello,FT9 MINI", new String[]{","}, false, 0, 6, (Object) null)) {
                QJavaApplication.getInstance().putScanKeys(str);
                QJavaApplication.getInstance().putScanKeysMap(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void initXLog() {
        XLog.init(new LogConfiguration.Builder().logLevel(Integer.MAX_VALUE).tag("QRing").enableThreadInfo().enableStackTrace(1).enableBorder().build());
    }

    private final void initLog() {
        if (UserConfig.INSTANCE.getInstance().getIsDebug()) {
            LogToFile.getInstance().debug = true;
        } else {
            LogToFile.getInstance().debug = false;
        }
        BleBaseControl.getInstance().setmContext(this);
        LogToFile.getInstance().initPath(this);
    }

    private final void initSkin() {
        QCApplication qCApplication = this;
        SkinCompatManager.withoutActivity(qCApplication).addInflater(new SkinAppCompatViewInflater()).addInflater(new SkinMaterialViewInflater()).addInflater(new SkinConstraintViewInflater()).addInflater(new SkinCardViewInflater()).loadSkin();
        SkinCompatResources.getInstance().setApplication(qCApplication);
    }

    private final void initService() {
        Object systemService;
        try {
            Intent intent = new Intent(this, (Class<?>) XService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
            try {
                systemService = getSystemService("phone");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        ((TelephonyManager) systemService).listen(MyPhoneStateListener.INSTANCE.getGetInstance(), 32);
        Intent intent2 = new Intent(this, (Class<?>) YService.class);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent2);
            } else {
                startService(intent2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private final void initKeepAlive() {
        CactusExtKt.cactus(this, new Function1<Cactus, Unit>() { // from class: com.qcwireless.qcwatch.QCApplication.initKeepAlive.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Cactus cactus) {
                invoke2(cactus);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Cactus cactus) {
                Intrinsics.checkNotNullParameter(cactus, "$this$cactus");
                Notification notificationInitBandNotification = new NotificationUtils(QCApplication.this).initBandNotification();
                Intrinsics.checkNotNullExpressionValue(notificationInitBandNotification, "NotificationUtils(this@Q…n).initBandNotification()");
                cactus.setNotification(notificationInitBandNotification);
                cactus.isDebug(false);
                cactus.setBackgroundMusicEnabled(false);
                cactus.setCrashRestartUIEnabled(false);
                cactus.hideNotification(true);
                cactus.hideNotificationAfterO(true);
                cactus.setWorkerEnabled(true);
            }
        });
    }

    public final void trySetupNotifyService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        toggleNotificationService(context);
    }

    private final void toggleNotificationService(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.setComponentEnabledSetting(new ComponentName(context, (Class<?>) MyNotificationService.class), 2, 1);
            packageManager.setComponentEnabledSetting(new ComponentName(context, (Class<?>) MyNotificationService.class), 1, 1);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public final boolean isNotificationServiceRunning(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Object systemService = context.getSystemService("activity");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
            }
            Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE).iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(MyNotificationService.class.getName(), it.next().service.getClassName())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // androidx.multidex.MultiDexApplication, android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        Intrinsics.checkNotNullParameter(base, "base");
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /* compiled from: QCApplication.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.QCApplication$getLocationOnce$1", f = "QCApplication.kt", i = {}, l = {439, 440}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.QCApplication$getLocationOnce$1, reason: invalid class name and case insensitive filesystem */
    static final class C02821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Application $application;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02821(Application application, Continuation<? super C02821> continuation) {
            super(2, continuation);
            this.$application = application;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return QCApplication.this.new C02821(this.$application, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceLocation(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            final QCApplication qCApplication = QCApplication.this;
            final Application application = this.$application;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.QCApplication.getLocationOnce.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((MyLocationBean) obj2, (Continuation<? super Unit>) continuation);
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x0110  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
                    /*
                        Method dump skipped, instructions count: 417
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.C02821.C00361.emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean, kotlin.coroutines.Continuation):java.lang.Object");
                }

                /* compiled from: QCApplication.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
                @DebugMetadata(c = "com.qcwireless.qcwatch.QCApplication$getLocationOnce$1$1$1", f = "QCApplication.kt", i = {}, l = {450, 454}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.qcwireless.qcwatch.QCApplication$getLocationOnce$1$1$1, reason: invalid class name and collision with other inner class name */
                static final class C00371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ WeatherRequest $bean;
                    int label;
                    final /* synthetic */ QCApplication this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00371(QCApplication qCApplication, WeatherRequest weatherRequest, Continuation<? super C00371> continuation) {
                        super(2, continuation);
                        this.this$0 = qCApplication;
                        this.$bean = weatherRequest;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C00371(this.this$0, this.$bean, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            obj = WeatherRepository.INSTANCE.getGetInstance().getWeatherFromServer(this.this$0.getPingHwServer(), this.$bean, this);
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
                        final QCApplication qCApplication = this.this$0;
                        this.label = 2;
                        if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.QCApplication.getLocationOnce.1.1.1.1
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                                return emit((NetState<List<WeatherResp>>) obj2, (Continuation<? super Unit>) continuation);
                            }

                            public final Object emit(NetState<List<WeatherResp>> netState, Continuation<? super Unit> continuation) {
                                if (netState.getRetCode() == 0) {
                                    try {
                                        UserConfig companion = UserConfig.INSTANCE.getInstance();
                                        List<WeatherResp> listIsSuccess = netState.isSuccess();
                                        Intrinsics.checkNotNull(listIsSuccess);
                                        companion.setWeatherInfo(MoshiUtilsKt.toJson(listIsSuccess));
                                        UserConfig.INSTANCE.getInstance().setGpsLocationTime(new DateUtil().getUnixTimestamp() + 21600);
                                        UserConfig.INSTANCE.getInstance().save();
                                        qCApplication.weatherToDevice(netState.isSuccess());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    UserConfig.INSTANCE.getInstance().setGpsLocationTime(0L);
                                    UserConfig.INSTANCE.getInstance().save();
                                    String weatherInfo = UserConfig.INSTANCE.getInstance().getWeatherInfo();
                                    if (weatherInfo.length() > 0) {
                                        MoshiUtils moshiUtils = MoshiUtils.INSTANCE;
                                        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, WeatherResp.class);
                                        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
                                        ArrayList arrayList = (List) moshiUtils.fromJson(weatherInfo, parameterizedTypeNewParameterizedType);
                                        if (arrayList == null) {
                                            arrayList = new ArrayList();
                                        }
                                        qCApplication.weatherToDevice(arrayList);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void getLocationOnce(Application application2) {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C02821(application2, null), 3, null);
    }

    /* compiled from: QCApplication.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/QCApplication$MyLocationListener;", "Lcom/baidu/location/BDAbstractLocationListener;", "()V", "onReceiveLocation", "", "location", "Lcom/baidu/location/BDLocation;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class MyLocationListener extends BDAbstractLocationListener {
        @Override // com.baidu.location.BDAbstractLocationListener
        public void onReceiveLocation(BDLocation location) {
            Intrinsics.checkNotNullParameter(location, "location");
            if (location.getCity() != null) {
                String city = location.getCity();
                Intrinsics.checkNotNullExpressionValue(city, "location.city");
                if (city.length() > 0) {
                    XLog.e("-----------" + location.getLatitude() + "---" + location.getLongitude() + "---" + location.getCity());
                    try {
                        String oneKeySupport = UserConfig.INSTANCE.getInstance().getOneKeySupport();
                        if (oneKeySupport.length() > 0) {
                            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<OneKeySupport>() { // from class: com.qcwireless.qcwatch.QCApplication$MyLocationListener$onReceiveLocation$$inlined$fromJson$1
                            }.getType());
                            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
                            OneKeySupport oneKeySupport2 = (OneKeySupport) jsonAdapterAdapter.fromJson(oneKeySupport);
                            if (oneKeySupport2 != null && oneKeySupport2.getSupportLocation()) {
                                String name = location.getAddrStr();
                                Intrinsics.checkNotNullExpressionValue(name, "name");
                                byte[] bytes = name.getBytes(Charsets.UTF_8);
                                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                                if (bytes.length > 100) {
                                    Intrinsics.checkNotNullExpressionValue(name, "name");
                                    name = MetricUtilsKt.getWholeText(name, 98);
                                }
                                String name2 = name;
                                Intrinsics.checkNotNullExpressionValue(name2, "name");
                                if (name2.length() > 0) {
                                    LargeDataHandler.getInstance().writeLocation(location.getLongitude(), location.getLatitude(), name2);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String city2 = location.getCity();
                    try {
                        String str = city2;
                        if (str == null || str.length() == 0) {
                            city2 = GpsUtilsKt.getAddress(QCApplication.INSTANCE.getCONTEXT(), location.getLatitude(), location.getLongitude())[1];
                        }
                    } catch (Exception unused) {
                    }
                    float longitude = (float) location.getLongitude();
                    float latitude = (float) location.getLatitude();
                    Intrinsics.checkNotNullExpressionValue(city2, "city");
                    String addrStr = location.getAddrStr();
                    Intrinsics.checkNotNullExpressionValue(addrStr, "location.addrStr");
                    DeviceSettingRepository.INSTANCE.getGetInstance().saveDeviceLocation(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), new MyLocationBean(longitude, latitude, "en", city2, addrStr));
                    if (UserConfig.INSTANCE.getInstance().getWeatherLastTime() >= new DateUtil().getUnixTimestamp()) {
                        QCApplication.INSTANCE.getGetInstance().doCacheWeatherToDevice();
                        return;
                    }
                    float longitude2 = (float) location.getLongitude();
                    float latitude2 = (float) location.getLatitude();
                    Intrinsics.checkNotNullExpressionValue(city2, "city");
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new QCApplication$MyLocationListener$onReceiveLocation$1(new WeatherRequest(longitude2, latitude2, "en", city2), null), 3, null);
                    return;
                }
            }
            QCApplication.INSTANCE.getGetInstance().getLocationFromAndroid();
            UserConfig.INSTANCE.getInstance().setGpsLocationTime(0L);
            UserConfig.INSTANCE.getInstance().save();
            QCApplication.INSTANCE.getGetInstance().doCacheWeatherToDevice();
            XLog.e("--------location Error, ErrCode:" + location.getLocType());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doCacheWeatherToDevice() {
        String weatherInfo = UserConfig.INSTANCE.getInstance().getWeatherInfo();
        XLog.i(weatherInfo);
        if (!(weatherInfo.length() > 0)) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(null), 3, null);
            return;
        }
        MoshiUtils moshiUtils = MoshiUtils.INSTANCE;
        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, WeatherResp.class);
        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
        ArrayList arrayList = (List) moshiUtils.fromJson(weatherInfo, parameterizedTypeNewParameterizedType);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        weatherToDevice(arrayList);
    }

    /* compiled from: QCApplication.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1", f = "QCApplication.kt", i = {}, l = {675, 676, TypedValues.TransitionType.TYPE_INTERPOLATOR, TypedValues.TransitionType.TYPE_STAGGERED}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return QCApplication.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x007e A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0090 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L2d
                if (r1 == r5) goto L29
                if (r1 == r4) goto L25
                if (r1 == r3) goto L21
                if (r1 != r2) goto L19
                kotlin.ResultKt.throwOnFailure(r8)
                goto L91
            L19:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L21:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L7f
            L25:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L63
            L29:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L4c
            L2d:
                kotlin.ResultKt.throwOnFailure(r8)
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$Companion r8 = com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r8 = r8.getGetInstance()
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r1 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r1 = r1.getInstance()
                java.lang.String r1 = r1.getDeviceAddressNoClear()
                r6 = r7
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r7.label = r5
                java.lang.Object r8 = r8.getDeviceLocation(r1, r6)
                if (r8 != r0) goto L4c
                return r0
            L4c:
                kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
                com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1 r1 = new com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1
                com.qcwireless.qcwatch.QCApplication r5 = com.qcwireless.qcwatch.QCApplication.this
                r1.<init>(r5)
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                r5 = r7
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r7.label = r4
                java.lang.Object r8 = r8.collect(r1, r5)
                if (r8 != r0) goto L63
                return r0
            L63:
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository$Companion r8 = com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository.INSTANCE
                com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository r8 = r8.getGetInstance()
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r1 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r1 = r1.getInstance()
                java.lang.String r1 = r1.getDeviceAddressNoClear()
                r4 = r7
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r7.label = r3
                java.lang.Object r8 = r8.getDeviceLocation(r1, r4)
                if (r8 != r0) goto L7f
                return r0
            L7f:
                kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
                com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$2<T> r1 = new kotlinx.coroutines.flow.FlowCollector() { // from class: com.qcwireless.qcwatch.QCApplication.doCacheWeatherToDevice.1.2
                    static {
                        /*
                            com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$2 r0 = new com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$2
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$2<T>) com.qcwireless.qcwatch.QCApplication.doCacheWeatherToDevice.1.2.INSTANCE com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$2
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.AnonymousClass1.AnonymousClass2.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.AnonymousClass1.AnonymousClass2.<init>():void");
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ java.lang.Object emit(java.lang.Object r1, kotlin.coroutines.Continuation r2) {
                        /*
                            r0 = this;
                            com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean r1 = (com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean) r1
                            java.lang.Object r1 = r0.emit(r1, r2)
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.AnonymousClass1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }

                    public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
                        /*
                            r6 = this;
                            if (r7 == 0) goto L3d
                            java.lang.String r8 = r7.getAddress()
                            java.nio.charset.Charset r0 = kotlin.text.Charsets.UTF_8
                            byte[] r0 = r8.getBytes(r0)
                            java.lang.String r1 = "this as java.lang.String).getBytes(charset)"
                            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                            int r0 = r0.length
                            r1 = 100
                            if (r0 <= r1) goto L1d
                            r0 = 98
                            java.lang.String r8 = com.qcwireless.qcwatch.base.utils.MetricUtilsKt.getWholeText(r8, r0)
                        L1d:
                            r5 = r8
                            r8 = r5
                            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                            int r8 = r8.length()
                            if (r8 <= 0) goto L29
                            r8 = 1
                            goto L2a
                        L29:
                            r8 = 0
                        L2a:
                            if (r8 == 0) goto L3d
                            com.oudmon.ble.base.communication.LargeDataHandler r0 = com.oudmon.ble.base.communication.LargeDataHandler.getInstance()
                            float r8 = r7.getLongitude()
                            double r1 = (double) r8
                            float r7 = r7.getLatitude()
                            double r3 = (double) r7
                            r0.writeLocation(r1, r3, r5)
                        L3d:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.AnonymousClass1.AnonymousClass2.emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                r3 = r7
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r7.label = r2
                java.lang.Object r8 = r8.collect(r1, r3)
                if (r8 != r0) goto L91
                return r0
            L91:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: QCApplication.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBean;", "emit", "(Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        /* renamed from: com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00331<T> implements FlowCollector {
            final /* synthetic */ QCApplication this$0;

            C00331(QCApplication qCApplication) {
                this.this$0 = qCApplication;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
                /*
                    r7 = this;
                    boolean r0 = r9 instanceof com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1$emit$1
                    if (r0 == 0) goto L14
                    r0 = r9
                    com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1$emit$1 r0 = (com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L14
                    int r9 = r0.label
                    int r9 = r9 - r2
                    r0.label = r9
                    goto L19
                L14:
                    com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1$emit$1 r0 = new com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1$emit$1
                    r0.<init>(r7, r9)
                L19:
                    java.lang.Object r9 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L3e
                    if (r2 == r4) goto L36
                    if (r2 != r3) goto L2e
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto La0
                L2e:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r9)
                    throw r8
                L36:
                    java.lang.Object r8 = r0.L$0
                    com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1 r8 = (com.qcwireless.qcwatch.QCApplication.AnonymousClass1.C00331) r8
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L89
                L3e:
                    kotlin.ResultKt.throwOnFailure(r9)
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
                    float r9 = r8.getLatitude()
                    r2 = 0
                    r5 = 0
                    int r9 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                    if (r9 != 0) goto L50
                    r9 = 1
                    goto L51
                L50:
                    r9 = 0
                L51:
                    if (r9 != 0) goto La3
                    float r9 = r8.getLongitude()
                    int r9 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                    if (r9 != 0) goto L5c
                    r2 = 1
                L5c:
                    if (r2 != 0) goto La3
                    com.qcwireless.qcwatch.ui.base.bean.request.weather.WeatherRequest r9 = new com.qcwireless.qcwatch.ui.base.bean.request.weather.WeatherRequest
                    float r2 = r8.getLongitude()
                    float r5 = r8.getLatitude()
                    java.lang.String r8 = r8.getCity()
                    java.lang.String r6 = "en"
                    r9.<init>(r2, r5, r6, r8)
                    com.qcwireless.qcwatch.ui.base.repository.weather.WeatherRepository$Companion r8 = com.qcwireless.qcwatch.ui.base.repository.weather.WeatherRepository.INSTANCE
                    com.qcwireless.qcwatch.ui.base.repository.weather.WeatherRepository r8 = r8.getGetInstance()
                    com.qcwireless.qcwatch.QCApplication r2 = r7.this$0
                    boolean r2 = r2.getPingHwServer()
                    r0.L$0 = r7
                    r0.label = r4
                    java.lang.Object r9 = r8.getWeatherFromServer(r2, r9, r0)
                    if (r9 != r1) goto L88
                    return r1
                L88:
                    r8 = r7
                L89:
                    kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
                    com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1$1 r2 = new com.qcwireless.qcwatch.QCApplication$doCacheWeatherToDevice$1$1$1
                    com.qcwireless.qcwatch.QCApplication r8 = r8.this$0
                    r2.<init>()
                    kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                    r8 = 0
                    r0.L$0 = r8
                    r0.label = r3
                    java.lang.Object r8 = r9.collect(r2, r0)
                    if (r8 != r1) goto La0
                    return r1
                La0:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                La3:
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r8 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r8 = r8.getInstance()
                    r0 = 0
                    r8.setGpsLocationTimeNew(r0)
                    com.qcwireless.qcwatch.base.pref.UserConfig$Companion r8 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                    com.qcwireless.qcwatch.base.pref.UserConfig r8 = r8.getInstance()
                    r8.save()
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.AnonymousClass1.C00331.emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean, kotlin.coroutines.Continuation):java.lang.Object");
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((MyLocationBean) obj, (Continuation<? super Unit>) continuation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void weatherToDevice(List<WeatherResp> data) {
        int i = this.updating;
        if (i == 2 || i == 1 || i == 3 || i == 4) {
            XLog.e("正在固件升级,不发天气");
            return;
        }
        if (BleOperateManager.getInstance().isConnected()) {
            if (UserConfig.INSTANCE.getInstance().getWeatherToDeviceLastTime() < new DateUtil().getUnixTimestamp()) {
                XLog.i("同步天气");
                int i2 = 0;
                for (WeatherResp weatherResp : data) {
                    int i3 = i2 + 1;
                    if (weatherResp.getDigitType() == 0) {
                        UserConfig.INSTANCE.getInstance().setWeatherLastTime(0);
                        UserConfig.INSTANCE.getInstance().setWeatherToDeviceLastTime(0);
                        UserConfig.INSTANCE.getInstance().setWeatherInfo("");
                        UserConfig.INSTANCE.getInstance().save();
                    } else {
                        WeatherForecastReq.WeatherForecastBuilder takeUmbrella = new WeatherForecastReq.WeatherForecastBuilder().setIndex(i2).setTimeStamp(weatherResp.getTimeStamp()).setWeatherType(weatherResp.getDigitType()).setMinDegree(weatherResp.getLowTemp()).setMaxDegree(weatherResp.getHighTemp()).setHumidity(weatherResp.getHumidity()).setTakeUmbrella(weatherResp.isBringUmbrella());
                        Intrinsics.checkNotNullExpressionValue(takeUmbrella, "WeatherForecastBuilder()…la(value.isBringUmbrella)");
                        CommandHandle.getInstance().executeReqCmd(WeatherForecastReq.getWriteInstance(takeUmbrella), new ICommandResponse() { // from class: com.qcwireless.qcwatch.QCApplication$$ExternalSyntheticLambda1
                            @Override // com.oudmon.ble.base.communication.ICommandResponse
                            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                                QCApplication.m244weatherToDevice$lambda2((WeatherForecastRsp) baseRspCmd);
                            }
                        });
                    }
                    i2 = i3;
                }
                return;
            }
            XLog.i("天气同步时间限制");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: weatherToDevice$lambda-2, reason: not valid java name */
    public static final void m244weatherToDevice$lambda2(WeatherForecastRsp weatherForecastRsp) {
        if (weatherForecastRsp.getStatus() == 0) {
            UserConfig.INSTANCE.getInstance().setWeatherLastTime(((int) new DateUtil().getUnixTimestamp()) + CacheConstants.HOUR);
            UserConfig.INSTANCE.getInstance().setWeatherToDeviceLastTime(((int) new DateUtil().getUnixTimestamp()) + 300);
            UserConfig.INSTANCE.getInstance().save();
        }
    }

    /* compiled from: QCApplication.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.QCApplication$getLocationFromAndroid$1", f = "QCApplication.kt", i = {}, l = {807, 808}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.QCApplication$getLocationFromAndroid$1, reason: invalid class name and case insensitive filesystem */
    static final class C02811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C02811(Continuation<? super C02811> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return QCApplication.this.new C02811(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceLocation(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            final QCApplication qCApplication = QCApplication.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.QCApplication.getLocationFromAndroid.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((MyLocationBean) obj2, (Continuation<? super Unit>) continuation);
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x006c A[Catch: Exception -> 0x0079, TRY_LEAVE, TryCatch #0 {Exception -> 0x0079, blocks: (B:2:0x0000, B:7:0x0041, B:13:0x004e, B:14:0x005a, B:16:0x006c), top: B:23:0x0000 }] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
                    /*
                        r5 = this;
                        com.qcwireless.qcwatch.QJavaApplication r7 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L79
                        android.app.Application r7 = r7.getApplication()     // Catch: java.lang.Exception -> L79
                        android.content.Context r7 = (android.content.Context) r7     // Catch: java.lang.Exception -> L79
                        com.google.android.gms.location.FusedLocationProviderClient r7 = com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(r7)     // Catch: java.lang.Exception -> L79
                        java.lang.String r0 = "getFusedLocationProvider…etInstance().application)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)     // Catch: java.lang.Exception -> L79
                        com.google.android.gms.location.LocationRequest r0 = com.google.android.gms.location.LocationRequest.create()     // Catch: java.lang.Exception -> L79
                        r1 = 100
                        com.google.android.gms.location.LocationRequest r0 = r0.setPriority(r1)     // Catch: java.lang.Exception -> L79
                        r1 = 10000(0x2710, double:4.9407E-320)
                        com.google.android.gms.location.LocationRequest r0 = r0.setInterval(r1)     // Catch: java.lang.Exception -> L79
                        r1 = 5000(0x1388, double:2.4703E-320)
                        com.google.android.gms.location.LocationRequest r0 = r0.setFastestInterval(r1)     // Catch: java.lang.Exception -> L79
                        java.lang.String r1 = "create()\n               ….setFastestInterval(5000)"
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch: java.lang.Exception -> L79
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Exception -> L79
                        float r1 = r6.getLatitude()     // Catch: java.lang.Exception -> L79
                        r2 = 1
                        r3 = 0
                        r4 = 0
                        int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                        if (r1 != 0) goto L3e
                        r1 = 1
                        goto L3f
                    L3e:
                        r1 = 0
                    L3f:
                        if (r1 != 0) goto L5a
                        float r6 = r6.getLongitude()     // Catch: java.lang.Exception -> L79
                        int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                        if (r6 != 0) goto L4a
                        goto L4b
                    L4a:
                        r2 = 0
                    L4b:
                        if (r2 == 0) goto L4e
                        goto L5a
                    L4e:
                        com.qcwireless.qcwatch.QCApplication r6 = r1     // Catch: java.lang.Exception -> L79
                        com.qcwireless.qcwatch.QCApplication$locationCallback$1 r6 = com.qcwireless.qcwatch.QCApplication.access$getLocationCallback$p(r6)     // Catch: java.lang.Exception -> L79
                        com.google.android.gms.location.LocationCallback r6 = (com.google.android.gms.location.LocationCallback) r6     // Catch: java.lang.Exception -> L79
                        r7.removeLocationUpdates(r6)     // Catch: java.lang.Exception -> L79
                        goto L7d
                    L5a:
                        com.qcwireless.qcwatch.QJavaApplication r6 = com.qcwireless.qcwatch.QJavaApplication.getInstance()     // Catch: java.lang.Exception -> L79
                        android.app.Application r6 = r6.getApplication()     // Catch: java.lang.Exception -> L79
                        android.content.Context r6 = (android.content.Context) r6     // Catch: java.lang.Exception -> L79
                        java.lang.String r1 = "android.permission.ACCESS_FINE_LOCATION"
                        int r6 = androidx.core.app.ActivityCompat.checkSelfPermission(r6, r1)     // Catch: java.lang.Exception -> L79
                        if (r6 != 0) goto L7d
                        com.qcwireless.qcwatch.QCApplication r6 = r1     // Catch: java.lang.Exception -> L79
                        com.qcwireless.qcwatch.QCApplication$locationCallback$1 r6 = com.qcwireless.qcwatch.QCApplication.access$getLocationCallback$p(r6)     // Catch: java.lang.Exception -> L79
                        com.google.android.gms.location.LocationCallback r6 = (com.google.android.gms.location.LocationCallback) r6     // Catch: java.lang.Exception -> L79
                        r1 = 0
                        r7.requestLocationUpdates(r0, r6, r1)     // Catch: java.lang.Exception -> L79
                        goto L7d
                    L79:
                        r6 = move-exception
                        r6.printStackTrace()
                    L7d:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.QCApplication.C02811.C00351.emit(com.qcwireless.qcwatch.ui.base.bean.weather.MyLocationBean, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getLocationFromAndroid() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C02811(null), 3, null);
    }

    /* compiled from: QCApplication.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u0004\u0018\u00010\rR+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/QCApplication$Companion;", "", "()V", "<set-?>", "Landroid/content/Context;", "CONTEXT", "getCONTEXT", "()Landroid/content/Context;", "setCONTEXT", "(Landroid/content/Context;)V", "CONTEXT$delegate", "Lkotlin/properties/ReadWriteProperty;", "application", "Landroid/app/Application;", "getInstance", "Lcom/qcwireless/qcwatch/QCApplication;", "getGetInstance", "()Lcom/qcwireless/qcwatch/QCApplication;", "getInstance$delegate", "Lkotlin/Lazy;", "getApplication", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Companion.class, "CONTEXT", "getCONTEXT()Landroid/content/Context;", 0))};

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Context getCONTEXT() {
            return (Context) QCApplication.CONTEXT$delegate.getValue(this, $$delegatedProperties[0]);
        }

        public final void setCONTEXT(Context context) {
            Intrinsics.checkNotNullParameter(context, "<set-?>");
            QCApplication.CONTEXT$delegate.setValue(this, $$delegatedProperties[0], context);
        }

        public final Application getApplication() {
            if (QCApplication.application != null) {
                return QCApplication.application;
            }
            throw new RuntimeException("Not support calling this, before create app or after terminate app.");
        }

        public final QCApplication getGetInstance() {
            return (QCApplication) QCApplication.getInstance$delegate.getValue();
        }
    }
}
