package com.qcwireless.qcwatch.ui.home.gps.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.event.DeviceGpsDataEvent;
import com.qcwireless.qcwatch.base.event.DeviceGpsTimeEvent;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.home.gps.bean.Gps;
import com.qcwireless.qcwatch.ui.home.gps.bean.QcLatLon;
import com.qcwireless.qcwatch.ui.home.gps.util.GpsUtilsKt;
import com.qcwireless.qcwatch.ui.home.gps.util.MyWakeLock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import org.greenrobot.eventbus.EventBus;

/* compiled from: TrackingService.kt */
@Metadata(d1 = {"\u0000s\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001\u0018\u0018\u0000 ;2\u00020\u0001:\u0002;<B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0015H\u0002J\u0010\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020,H\u0003J\b\u0010-\u001a\u00020(H\u0002J\b\u0010.\u001a\u00020(H\u0017J\"\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002002\u0006\u00104\u001a\u000200H\u0016J\b\u00105\u001a\u00020(H\u0002J\b\u00106\u001a\u00020(H\u0002J\b\u00107\u001a\u00020(H\u0002J\b\u00108\u001a\u00020(H\u0002J\u0010\u00109\u001a\u00020(2\u0006\u0010:\u001a\u00020\fH\u0003R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010\u000fR\u000e\u0010!\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00120#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/service/TrackingService;", "Landroidx/lifecycle/LifecycleService;", "()V", "am", "Landroid/app/AlarmManager;", "fusedLocationProviderClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "getFusedLocationProviderClient", "()Lcom/google/android/gms/location/FusedLocationProviderClient;", "setFusedLocationProviderClient", "(Lcom/google/android/gms/location/FusedLocationProviderClient;)V", "isFirstRun", "", "()Z", "setFirstRun", "(Z)V", "isTimeEnabled", "lapTime", "", "lastLocationTime", "lastPosition", "Landroid/location/Location;", "lastSeconTimeStamp", "locationCallback", "com/qcwireless/qcwatch/ui/home/gps/service/TrackingService$locationCallback$1", "Lcom/qcwireless/qcwatch/ui/home/gps/service/TrackingService$locationCallback$1;", "myWakeLock", "Lcom/qcwireless/qcwatch/ui/home/gps/util/MyWakeLock;", "pi", "Landroid/app/PendingIntent;", "serviceKilled", "getServiceKilled", "setServiceKilled", "timeRun", "timeRunInSeconds", "Landroidx/lifecycle/MutableLiveData;", "timeStarted", "addEmptyPolylines", "", "addPathPoints", "", "location", "createNotificationChannel", "notificationManager", "Landroid/app/NotificationManager;", "killService", "onCreate", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "pauseService", "postInitialValue", "startForegroundService", "startTimer", "updateLocationTracking", "isTracking", "Companion", "Constant", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TrackingService extends LifecycleService {
    private AlarmManager am;
    public FusedLocationProviderClient fusedLocationProviderClient;
    private boolean isTimeEnabled;
    private long lapTime;
    private long lastLocationTime;
    private Location lastPosition;
    private long lastSeconTimeStamp;
    private MyWakeLock myWakeLock;
    private PendingIntent pi;
    private boolean serviceKilled;
    private long timeRun;
    private long timeStarted;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MutableLiveData<Long> timeRunInMillis = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> isTracking = new MutableLiveData<>();
    private static final MutableLiveData<List<QcLatLon>> pathPoints = new MutableLiveData<>();
    private boolean isFirstRun = true;
    private final MutableLiveData<Long> timeRunInSeconds = new MutableLiveData<>();
    private final TrackingService$locationCallback$1 locationCallback = new LocationCallback() { // from class: com.qcwireless.qcwatch.ui.home.gps.service.TrackingService$locationCallback$1
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            super.onLocationResult(result);
            Boolean value = TrackingService.INSTANCE.isTracking().getValue();
            Intrinsics.checkNotNull(value);
            if (value.booleanValue()) {
                List<Location> locations = result.getLocations();
                TrackingService trackingService = this.this$0;
                for (Location location : locations) {
                    Intrinsics.checkNotNullExpressionValue(location, "location");
                    trackingService.addPathPoints(location);
                    XLog.i("NEW LOCATION " + location.getLatitude() + ',' + location.getLongitude());
                }
            }
        }
    };

    /* renamed from: isFirstRun, reason: from getter */
    public final boolean getIsFirstRun() {
        return this.isFirstRun;
    }

    public final void setFirstRun(boolean z) {
        this.isFirstRun = z;
    }

    public final boolean getServiceKilled() {
        return this.serviceKilled;
    }

    public final void setServiceKilled(boolean z) {
        this.serviceKilled = z;
    }

    public final FusedLocationProviderClient getFusedLocationProviderClient() {
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationProviderClient;
        if (fusedLocationProviderClient != null) {
            return fusedLocationProviderClient;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fusedLocationProviderClient");
        return null;
    }

    public final void setFusedLocationProviderClient(FusedLocationProviderClient fusedLocationProviderClient) {
        Intrinsics.checkNotNullParameter(fusedLocationProviderClient, "<set-?>");
        this.fusedLocationProviderClient = fusedLocationProviderClient;
    }

    /* compiled from: TrackingService.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0006R!\u0010\u0007\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\t0\bj\u0002`\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0006R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/service/TrackingService$Companion;", "", "()V", "isTracking", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "pathPoints", "", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/QcLatLon;", "Lcom/qcwireless/qcwatch/ui/home/gps/service/polyline;", "getPathPoints", "timeRunInMillis", "", "getTimeRunInMillis", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableLiveData<Long> getTimeRunInMillis() {
            return TrackingService.timeRunInMillis;
        }

        public final MutableLiveData<Boolean> isTracking() {
            return TrackingService.isTracking;
        }

        public final MutableLiveData<List<QcLatLon>> getPathPoints() {
            return TrackingService.pathPoints;
        }
    }

    private final void postInitialValue() {
        isTracking.postValue(false);
        pathPoints.postValue(new ArrayList());
        timeRunInMillis.postValue(0L);
        this.timeRunInSeconds.postValue(0L);
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            Notification notificationInitBandNotification = new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            if (notificationInitBandNotification != null) {
                startForeground(NotificationUtils.QCNotification, notificationInitBandNotification);
            } else {
                startForeground(NotificationUtils.QCNotification, new Notification());
            }
        }
        postInitialValue();
        TrackingService trackingService = this;
        setFusedLocationProviderClient(new FusedLocationProviderClient(trackingService));
        isTracking.observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.service.TrackingService$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TrackingService.m695onCreate$lambda0(this.f$0, (Boolean) obj);
            }
        });
        if (this.myWakeLock == null) {
            MyWakeLock myWakeLock = new MyWakeLock(trackingService);
            this.myWakeLock = myWakeLock;
            Intrinsics.checkNotNull(myWakeLock);
            myWakeLock.acquireWakeLock();
        }
        new IntentFilter().addAction("com.kunket.locSDK.timer1");
        this.pi = PendingIntent.getBroadcast(trackingService, 0, new Intent(), 335544320);
        Object systemService = getSystemService(NotificationCompat.CATEGORY_ALARM);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        this.am = (AlarmManager) systemService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m695onCreate$lambda0(TrackingService this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.updateLocationTracking(it.booleanValue());
    }

    private final void killService() {
        PendingIntent pendingIntent;
        MyWakeLock myWakeLock = this.myWakeLock;
        if (myWakeLock != null) {
            Intrinsics.checkNotNull(myWakeLock);
            myWakeLock.releaseWakeLock();
        }
        AlarmManager alarmManager = this.am;
        if (alarmManager != null && (pendingIntent = this.pi) != null) {
            Intrinsics.checkNotNull(alarmManager);
            alarmManager.cancel(pendingIntent);
        }
        this.serviceKilled = true;
        this.isFirstRun = true;
        pauseService();
        postInitialValue();
        stopForeground(true);
        stopSelf();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action;
        if (Build.VERSION.SDK_INT >= 26) {
            Notification notificationInitBandNotification = new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            if (notificationInitBandNotification != null) {
                startForeground(NotificationUtils.QCNotification, notificationInitBandNotification);
            } else {
                startForeground(NotificationUtils.QCNotification, new Notification());
            }
        }
        if (intent != null && (action = intent.getAction()) != null) {
            int iHashCode = action.hashCode();
            if (iHashCode != -1023568191) {
                if (iHashCode != 923148003) {
                    if (iHashCode == 1729812633 && action.equals(Constant.ACTION_START_OR_RESUME_SERVICE)) {
                        if (this.isFirstRun) {
                            startForegroundService();
                            this.isFirstRun = false;
                        } else {
                            XLog.i("service  resume");
                            startTimer();
                        }
                    }
                } else if (action.equals(Constant.ACTION_PAUSE_SERVICE)) {
                    XLog.i("service pause");
                    pauseService();
                }
            } else if (action.equals(Constant.ACTION_STOP_SERVICE)) {
                XLog.i("service stop");
                killService();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private final void startTimer() {
        addEmptyPolylines();
        isTracking.postValue(true);
        this.timeStarted = System.currentTimeMillis();
        this.isTimeEnabled = true;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new AnonymousClass1(null), 3, null);
    }

    /* compiled from: TrackingService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.gps.service.TrackingService$startTimer$1", f = "TrackingService.kt", i = {}, l = {179}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.gps.service.TrackingService$startTimer$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TrackingService.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0 || i == 1) {
                ResultKt.throwOnFailure(obj);
                do {
                    Boolean value = TrackingService.INSTANCE.isTracking().getValue();
                    Intrinsics.checkNotNull(value);
                    if (value.booleanValue()) {
                        TrackingService.this.lapTime = System.currentTimeMillis() - TrackingService.this.timeStarted;
                        TrackingService.INSTANCE.getTimeRunInMillis().postValue(Boxing.boxLong(TrackingService.this.timeRun + TrackingService.this.lapTime));
                        EventBus.getDefault().post(new DeviceGpsTimeEvent(TrackingService.this.timeRun + TrackingService.this.lapTime));
                        Long value2 = TrackingService.INSTANCE.getTimeRunInMillis().getValue();
                        Intrinsics.checkNotNull(value2);
                        if (value2.longValue() >= TrackingService.this.lastSeconTimeStamp + 1000) {
                            MutableLiveData mutableLiveData = TrackingService.this.timeRunInSeconds;
                            T value3 = TrackingService.this.timeRunInSeconds.getValue();
                            Intrinsics.checkNotNull(value3);
                            mutableLiveData.postValue(Boxing.boxLong(((Number) value3).longValue() + 1));
                            TrackingService.this.lastSeconTimeStamp += 1000;
                        }
                        this.label = 1;
                    } else {
                        TrackingService.this.timeRun += TrackingService.this.lapTime;
                        return Unit.INSTANCE;
                    }
                } while (DelayKt.delay(1000L, this) != coroutine_suspended);
                return coroutine_suspended;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void pauseService() {
        isTracking.postValue(false);
        this.isTimeEnabled = false;
    }

    private final void updateLocationTracking(boolean isTracking2) {
        if (isTracking2) {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(5000L);
            locationRequest.setFastestInterval(Constant.FASTEST_UPDATE_INTERVAL);
            locationRequest.setPriority(100);
            getFusedLocationProviderClient().requestLocationUpdates(locationRequest, this.locationCallback, Looper.getMainLooper());
            return;
        }
        getFusedLocationProviderClient().removeLocationUpdates(this.locationCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addPathPoints(Location location) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.lastLocationTime;
        long j2 = jCurrentTimeMillis - j;
        Location location2 = null;
        if (j > 0) {
            Location location3 = this.lastPosition;
            if (location3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lastPosition");
                location3 = null;
            }
            float f = j2 / 1000.0f;
            if (location3.distanceTo(location) / f > 5.5d) {
                StringBuilder sb = new StringBuilder();
                sb.append("-------漂移");
                Location location4 = this.lastPosition;
                if (location4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lastPosition");
                } else {
                    location2 = location4;
                }
                sb.append(location2.distanceTo(location) / f);
                XLog.i(sb.toString());
                return;
            }
        }
        if (this.lastLocationTime > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("-------没有漂移");
            Location location5 = this.lastPosition;
            if (location5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lastPosition");
            } else {
                location2 = location5;
            }
            sb2.append(location2.distanceTo(location) / (j2 / 1000.0f));
            XLog.i(sb2.toString());
        }
        this.lastLocationTime = System.currentTimeMillis();
        this.lastPosition = location;
        Gps gpsTransform2 = GpsUtilsKt.transform2(location.getLatitude(), location.getLongitude());
        QcLatLon qcLatLon = new QcLatLon(gpsTransform2.getWgLat(), gpsTransform2.getWgLon(), 99.0d, location.getSpeed());
        MutableLiveData<List<QcLatLon>> mutableLiveData = pathPoints;
        List<QcLatLon> value = mutableLiveData.getValue();
        if (value != null) {
            value.add(qcLatLon);
            mutableLiveData.postValue(value);
        }
        EventBus.getDefault().post(new DeviceGpsDataEvent(mutableLiveData));
    }

    private final Object addEmptyPolylines() {
        MutableLiveData<List<QcLatLon>> mutableLiveData = pathPoints;
        List<QcLatLon> value = mutableLiveData.getValue();
        if (value != null) {
            mutableLiveData.postValue(value);
        } else {
            value = null;
        }
        if (value != null) {
            return value;
        }
        mutableLiveData.postValue(new ArrayList());
        return Unit.INSTANCE;
    }

    private final void startForegroundService() {
        startTimer();
        isTracking.postValue(true);
        Object systemService = getSystemService("notification");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel(notificationManager);
        }
        this.timeRunInSeconds.observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.service.TrackingService$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TrackingService.m696startForegroundService$lambda7(this.f$0, (Long) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startForegroundService$lambda-7, reason: not valid java name */
    public static final void m696startForegroundService$lambda7(TrackingService this$0, Long l) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.serviceKilled) {
            return;
        }
        new NotificationUtils(this$0).initBandNotification();
    }

    private final void createNotificationChannel(NotificationManager notificationManager) {
        notificationManager.createNotificationChannel(new NotificationChannel(Constant.NOTIFICATION_CHANNEL_ID, Constant.NOTIFICATION_CHANNEL_NAME, 2));
    }

    /* compiled from: TrackingService.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/service/TrackingService$Constant;", "", "()V", Constant.ACTION_PAUSE_SERVICE, "", Constant.ACTION_SHOW_TRACKING_FRAGMENT, Constant.ACTION_START_OR_RESUME_SERVICE, Constant.ACTION_STOP_SERVICE, "FASTEST_UPDATE_INTERVAL", "", "LOCATION_UPDATE_INTERVAL", "MAP_ZOOM", "", "MAP_ZOOM_SMALL", "NOTIFICATION_CHANNEL_ID", "NOTIFICATION_CHANNEL_NAME", "POLYLINE_WIDTH", "REQUEST_CODE_LOCATION", "", "RUNNING_DATABSE_NAME", "UPDATE_TIME_INTERVAL", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Constant {
        public static final String ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE";
        public static final String ACTION_SHOW_TRACKING_FRAGMENT = "ACTION_SHOW_TRACKING_FRAGMENT";
        public static final String ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE";
        public static final String ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE";
        public static final long FASTEST_UPDATE_INTERVAL = 2000;
        public static final Constant INSTANCE = new Constant();
        public static final long LOCATION_UPDATE_INTERVAL = 5000;
        public static final float MAP_ZOOM = 18.0f;
        public static final float MAP_ZOOM_SMALL = 16.0f;
        public static final String NOTIFICATION_CHANNEL_ID = "tracking_channel";
        public static final String NOTIFICATION_CHANNEL_NAME = "Tracking";
        public static final float POLYLINE_WIDTH = 10.0f;
        public static final int REQUEST_CODE_LOCATION = 0;
        public static final String RUNNING_DATABSE_NAME = "running_db";
        public static final long UPDATE_TIME_INTERVAL = 1000;

        private Constant() {
        }
    }
}
