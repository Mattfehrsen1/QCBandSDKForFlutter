package com.qcwireless.qcwatch.ui.home.gps;

import android.app.Activity;
import android.content.Intent;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.StyleSpan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.PhoneGpsReq;
import com.oudmon.ble.base.communication.rsp.AppGpsRsp;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.RealTimeHeartRateRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.DeviceGpsDataEvent;
import com.qcwireless.qcwatch.base.event.DeviceGpsEvent;
import com.qcwireless.qcwatch.base.event.DeviceGpsTimeEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.SystemServiceExtKt;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityGpsRunBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.event.HealthItemRefreshEvent;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;
import com.qcwireless.qcwatch.ui.base.view.gps.GpsEndView;
import com.qcwireless.qcwatch.ui.base.view.gps.LockView;
import com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity;
import com.qcwireless.qcwatch.ui.home.gps.bean.Gps;
import com.qcwireless.qcwatch.ui.home.gps.bean.QcLatLon;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import com.qcwireless.qcwatch.ui.home.gps.util.GpsUtilsKt;
import com.qcwireless.qcwatch.ui.home.gps.vm.GPSRunActivityViewModel;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: GpsRunActivity.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0016\u0018\u0000 b2\u00020\u00012\u00020\u0002:\u0002abB\u0005¢\u0006\u0002\u0010\u0003J\b\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u000208H\u0002J\b\u0010:\u001a\u000208H\u0002J\b\u0010;\u001a\u000208H\u0002J\b\u0010<\u001a\u000208H\u0002J\b\u0010=\u001a\u000208H\u0016J\u0012\u0010>\u001a\u0002082\b\u0010?\u001a\u0004\u0018\u00010@H\u0014J\b\u0010A\u001a\u000208H\u0015J\u0010\u0010B\u001a\u0002082\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010C\u001a\u0002082\u0006\u0010D\u001a\u00020EH\u0016J+\u0010F\u001a\u0002082\u0006\u0010G\u001a\u00020H2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020K0J2\u0006\u0010L\u001a\u00020MH\u0016¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u000208H\u0014J\u0010\u0010P\u001a\u0002082\u0006\u0010Q\u001a\u00020@H\u0014J\u0010\u0010R\u001a\u0002082\u0006\u0010S\u001a\u00020KH\u0002J\u000e\u0010T\u001a\u0002082\u0006\u0010U\u001a\u00020HJ\b\u0010V\u001a\u000208H\u0015J\u0006\u0010W\u001a\u000208J\b\u0010X\u001a\u000208H\u0002J\b\u0010Y\u001a\u000208H\u0002J\b\u0010Z\u001a\u000208H\u0002J\b\u0010[\u001a\u000208H\u0002J\b\u0010\\\u001a\u000208H\u0002J\b\u0010]\u001a\u000208H\u0002J\b\u0010^\u001a\u000208H\u0002J\u0010\u0010_\u001a\u0002082\u0006\u0010!\u001a\u00020\u0018H\u0002J\b\u0010`\u001a\u000208H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\u000bR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00020\u001c8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b3\u00104¨\u0006c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/GpsRunActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityGpsRunBinding;", "calorieTotal", "", "getCalorieTotal", "()F", "setCalorieTotal", "(F)V", "cameraPosition", "Lcom/google/android/gms/maps/model/CameraPosition;", "currentTimeInMiliis", "", "defaultLocation", "Lcom/google/android/gms/maps/model/LatLng;", "df", "Ljava/text/DecimalFormat;", "distanceInMeters", "getDistanceInMeters", "setDistanceInMeters", "firstLocation", "", "fusedLocationProviderClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "gnss", "Landroid/location/GnssStatus$Callback;", "gpsResponse", "Lcom/oudmon/ble/base/communication/ICommandResponse;", "Lcom/oudmon/ble/base/communication/rsp/AppGpsRsp;", "gpsStart", "isTracking", "lastKnownLocation", "Landroid/location/Location;", "locationPermissionGranted", "map", "Lcom/google/android/gms/maps/GoogleMap;", "marker", "Lcom/google/android/gms/maps/model/Marker;", "pathPoint", "", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/QcLatLon;", "startAnim", "Landroid/view/animation/TranslateAnimation;", "startTime", "timer", "Ljava/util/Timer;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSRunActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSRunActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "endViewAnim", "", "getDeviceLocation", "getLocationPermission", "initGPSListener", "moveCameraToUser", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMapReady", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onSaveInstanceState", "outState", "sendCommandToService", "action", "setGpsStatues", "number", "setupViews", "showGpsEnd", "showGpsExitDialog", "startViewAnim", "stopGps", "stopRun", "subscribeToObservers", "syncDistance", "toggleRun", "upDateTracking", "updateLocationUI", "Callback", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsRunActivity extends BaseActivity implements OnMapReadyCallback {
    private static final int DEFAULT_ZOOM = 15;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final int M_MAX_ENTRIES = 5;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private ActivityGpsRunBinding binding;
    private float calorieTotal;
    private CameraPosition cameraPosition;
    private long currentTimeInMiliis;
    private DecimalFormat df;
    private float distanceInMeters;
    private boolean firstLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private ICommandResponse<AppGpsRsp> gpsResponse;
    private boolean isTracking;
    private Location lastKnownLocation;
    private boolean locationPermissionGranted;
    private GoogleMap map;
    private Marker marker;
    private TranslateAnimation startAnim;
    private long startTime;
    private Timer timer;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private GnssStatus.Callback gnss = new Callback();
    private boolean gpsStart = true;
    private final LatLng defaultLocation = new LatLng(-33.8523341d, 151.2106085d);
    private List<QcLatLon> pathPoint = new ArrayList();

    /* JADX WARN: Multi-variable type inference failed */
    public GpsRunActivity() {
        final GpsRunActivity gpsRunActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<GPSRunActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.gps.vm.GPSRunActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final GPSRunActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(gpsRunActivity, Reflection.getOrCreateKotlinClass(GPSRunActivityViewModel.class), qualifier, objArr);
            }
        });
        this.gpsResponse = new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda8
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                GpsRunActivity.m681gpsResponse$lambda14(this.f$0, (AppGpsRsp) baseRspCmd);
            }
        };
    }

    public final float getDistanceInMeters() {
        return this.distanceInMeters;
    }

    public final void setDistanceInMeters(float f) {
        this.distanceInMeters = f;
    }

    public final float getCalorieTotal() {
        return this.calorieTotal;
    }

    public final void setCalorieTotal(float f) {
        this.calorieTotal = f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GPSRunActivityViewModel getViewModel() {
        return (GPSRunActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGpsRunBinding activityGpsRunBindingInflate = ActivityGpsRunBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityGpsRunBindingInflate, "inflate(layoutInflater)");
        this.binding = activityGpsRunBindingInflate;
        if (savedInstanceState != null) {
            this.lastKnownLocation = (Location) savedInstanceState.getParcelable(KEY_LOCATION);
            this.cameraPosition = (CameraPosition) savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        ActivityGpsRunBinding activityGpsRunBinding = this.binding;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        ConstraintLayout root = activityGpsRunBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(this)");
        this.fusedLocationProviderClient = fusedLocationProviderClient;
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        initGPSListener();
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        setStatusBarNoBackground();
        ActivityGpsRunBinding activityGpsRunBinding = this.binding;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        activityGpsRunBinding.imageStop.setListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GpsRunActivity.m683setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        ThreadExtKt.ktxRunOnUiDelay(this, TrackingService.Constant.FASTEST_UPDATE_INTERVAL, new Function1<GpsRunActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.setupViews.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GpsRunActivity gpsRunActivity) {
                invoke2(gpsRunActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GpsRunActivity ktxRunOnUiDelay) {
                Intrinsics.checkNotNullParameter(ktxRunOnUiDelay, "$this$ktxRunOnUiDelay");
                CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 6), ktxRunOnUiDelay.gpsResponse);
            }
        });
        ActivityGpsRunBinding activityGpsRunBinding2 = this.binding;
        if (activityGpsRunBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding2 = null;
        }
        activityGpsRunBinding2.imageEnd.setListener(new GpsEndView.GpsEndListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda10
            @Override // com.qcwireless.qcwatch.ui.base.view.gps.GpsEndView.GpsEndListener
            public final void gpsEnd() {
                GpsRunActivity.m684setupViews$lambda2(this.f$0);
            }
        });
        ActivityGpsRunBinding activityGpsRunBinding3 = this.binding;
        if (activityGpsRunBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding3 = null;
        }
        activityGpsRunBinding3.imageStop.setEndListener(new LockView.EndListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.ui.base.view.gps.LockView.EndListener
            public final void onEnd() {
                GpsRunActivity.m685setupViews$lambda3(this.f$0);
            }
        });
        View[] viewArr = new View[4];
        ActivityGpsRunBinding activityGpsRunBinding4 = this.binding;
        if (activityGpsRunBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding4 = null;
        }
        viewArr[0] = activityGpsRunBinding4.imageBack;
        ActivityGpsRunBinding activityGpsRunBinding5 = this.binding;
        if (activityGpsRunBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding5 = null;
        }
        viewArr[1] = activityGpsRunBinding5.imageContinue;
        ActivityGpsRunBinding activityGpsRunBinding6 = this.binding;
        if (activityGpsRunBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding6 = null;
        }
        viewArr[2] = activityGpsRunBinding6.imageScreenLock;
        ActivityGpsRunBinding activityGpsRunBinding7 = this.binding;
        if (activityGpsRunBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding7 = null;
        }
        viewArr[3] = activityGpsRunBinding7.viewTopOfLayout;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.setupViews.5
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
                ActivityGpsRunBinding activityGpsRunBinding8 = GpsRunActivity.this.binding;
                ActivityGpsRunBinding activityGpsRunBinding9 = null;
                if (activityGpsRunBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding8 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityGpsRunBinding8.imageBack)) {
                    GpsRunActivity.this.showGpsExitDialog();
                    return;
                }
                ActivityGpsRunBinding activityGpsRunBinding10 = GpsRunActivity.this.binding;
                if (activityGpsRunBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding10 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityGpsRunBinding10.imageContinue)) {
                    ActivityGpsRunBinding activityGpsRunBinding11 = GpsRunActivity.this.binding;
                    if (activityGpsRunBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityGpsRunBinding11 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityGpsRunBinding11.imageScreenLock)) {
                        ActivityGpsRunBinding activityGpsRunBinding12 = GpsRunActivity.this.binding;
                        if (activityGpsRunBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityGpsRunBinding12 = null;
                        }
                        ViewKt.visible(activityGpsRunBinding12.tvLabelUnlock);
                        ActivityGpsRunBinding activityGpsRunBinding13 = GpsRunActivity.this.binding;
                        if (activityGpsRunBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityGpsRunBinding13 = null;
                        }
                        activityGpsRunBinding13.imageStop.flipAnimator(1);
                        ActivityGpsRunBinding activityGpsRunBinding14 = GpsRunActivity.this.binding;
                        if (activityGpsRunBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityGpsRunBinding14 = null;
                        }
                        ViewKt.gone(activityGpsRunBinding14.imageScreenLock);
                        ActivityGpsRunBinding activityGpsRunBinding15 = GpsRunActivity.this.binding;
                        if (activityGpsRunBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityGpsRunBinding9 = activityGpsRunBinding15;
                        }
                        ViewKt.visible(activityGpsRunBinding9.viewTopOfLayout);
                        return;
                    }
                    ActivityGpsRunBinding activityGpsRunBinding16 = GpsRunActivity.this.binding;
                    if (activityGpsRunBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityGpsRunBinding9 = activityGpsRunBinding16;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityGpsRunBinding9.viewTopOfLayout)) {
                        XLog.i("---click--");
                        return;
                    }
                    return;
                }
                CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 3), GpsRunActivity.this.gpsResponse);
                ActivityGpsRunBinding activityGpsRunBinding17 = GpsRunActivity.this.binding;
                if (activityGpsRunBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding17 = null;
                }
                ViewKt.gone(activityGpsRunBinding17.tvLabelEnd);
                ActivityGpsRunBinding activityGpsRunBinding18 = GpsRunActivity.this.binding;
                if (activityGpsRunBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityGpsRunBinding9 = activityGpsRunBinding18;
                }
                ViewKt.visible(activityGpsRunBinding9.imageScreenLock);
                GpsRunActivity.this.startViewAnim();
                GpsRunActivity.this.isTracking = false;
                GpsRunActivity.this.toggleRun();
            }
        });
        GpsRunActivity gpsRunActivity = this;
        if (ActivityCompat.checkSelfPermission(gpsRunActivity, Permission.ACCESS_FINE_LOCATION) != 0) {
            return;
        }
        LocationManager locationManager = SystemServiceExtKt.getLocationManager(gpsRunActivity);
        if (locationManager != null) {
            locationManager.registerGnssStatusCallback(this.gnss, (Handler) null);
        }
        toggleRun();
        subscribeToObservers();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m683setupViews$lambda1$lambda0(GpsRunActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityGpsRunBinding activityGpsRunBinding = this$0.binding;
        ActivityGpsRunBinding activityGpsRunBinding2 = null;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        activityGpsRunBinding.imageStop.setVisibility(4);
        ActivityGpsRunBinding activityGpsRunBinding3 = this$0.binding;
        if (activityGpsRunBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding3 = null;
        }
        ViewKt.visible(activityGpsRunBinding3.imageContinue);
        ActivityGpsRunBinding activityGpsRunBinding4 = this$0.binding;
        if (activityGpsRunBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding4 = null;
        }
        ViewKt.visible(activityGpsRunBinding4.imageEnd);
        ActivityGpsRunBinding activityGpsRunBinding5 = this$0.binding;
        if (activityGpsRunBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding5 = null;
        }
        ViewKt.gone(activityGpsRunBinding5.imageScreenLock);
        ActivityGpsRunBinding activityGpsRunBinding6 = this$0.binding;
        if (activityGpsRunBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsRunBinding2 = activityGpsRunBinding6;
        }
        activityGpsRunBinding2.imageStop.setEnabled(false);
        this$0.isTracking = true;
        this$0.toggleRun();
        this$0.endViewAnim();
        CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 2), this$0.gpsResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m684setupViews$lambda2(GpsRunActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long j = 1000;
        if ((this$0.currentTimeInMiliis / j) / 60 < 3) {
            this$0.showGpsExitDialog();
        } else {
            if (this$0.startTime == 0) {
                this$0.startTime = (System.currentTimeMillis() - this$0.currentTimeInMiliis) / j;
            }
            try {
                if (((int) this$0.distanceInMeters) == 0) {
                    float fCalculatePolyLineLength = GpsUtilsKt.calculatePolyLineLength(this$0.pathPoint);
                    this$0.distanceInMeters = fCalculatePolyLineLength;
                    this$0.calorieTotal = (30 * fCalculatePolyLineLength) / 1000;
                }
            } catch (Exception unused) {
            }
            long j2 = this$0.startTime;
            int i = (int) this$0.currentTimeInMiliis;
            float f = this$0.distanceInMeters;
            float f2 = this$0.calorieTotal;
            String json = MoshiUtilsKt.toJson(this$0.pathPoint);
            String y_m_d = new DateUtil().getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
            this$0.getViewModel().saveGpsDetail(new GpsDetail(j2, i, f, f2, json, y_m_d));
            this$0.stopRun();
        }
        CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 4), this$0.gpsResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m685setupViews$lambda3(GpsRunActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityGpsRunBinding activityGpsRunBinding = this$0.binding;
        ActivityGpsRunBinding activityGpsRunBinding2 = null;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        ViewKt.gone(activityGpsRunBinding.tvLabelUnlock);
        ActivityGpsRunBinding activityGpsRunBinding3 = this$0.binding;
        if (activityGpsRunBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding3 = null;
        }
        ViewKt.visible(activityGpsRunBinding3.imageScreenLock);
        ActivityGpsRunBinding activityGpsRunBinding4 = this$0.binding;
        if (activityGpsRunBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsRunBinding2 = activityGpsRunBinding4;
        }
        ViewKt.gone(activityGpsRunBinding2.viewTopOfLayout);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!BleOperateManager.getInstance().isConnected()) {
            ActivityGpsRunBinding activityGpsRunBinding = this.binding;
            if (activityGpsRunBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityGpsRunBinding = null;
            }
            activityGpsRunBinding.gpsHeart.setItemTitle("00");
        }
        BleOperateManager.getInstance().addNotifyListener(30, new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda9
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                GpsRunActivity.m682onResume$lambda4(this.f$0, (RealTimeHeartRateRsp) baseRspCmd);
            }
        });
        try {
            if (((int) this.distanceInMeters) == 0) {
                float fCalculatePolyLineLength = GpsUtilsKt.calculatePolyLineLength(this.pathPoint);
                this.distanceInMeters = fCalculatePolyLineLength;
                this.calorieTotal = (30 * fCalculatePolyLineLength) / 1000;
            }
        } catch (Exception unused) {
        }
        try {
            CommandHandle.getInstance().executeReqCmdNoCallback(PhoneGpsReq.setPhoneDataReq((int) (this.distanceInMeters * 100), (int) (this.calorieTotal * 1000)));
        } catch (Exception unused2) {
        }
        if (BleOperateManager.getInstance().isConnected()) {
            syncDistance();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onResume$lambda-4, reason: not valid java name */
    public static final void m682onResume$lambda4(GpsRunActivity this$0, final RealTimeHeartRateRsp realTimeHeartRateRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (realTimeHeartRateRsp == null || realTimeHeartRateRsp.getHeart() <= 0) {
            return;
        }
        ThreadExtKt.ktxRunOnUi(this$0, new Function1<GpsRunActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$onResume$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GpsRunActivity gpsRunActivity) {
                invoke2(gpsRunActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GpsRunActivity ktxRunOnUi) {
                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                ActivityGpsRunBinding activityGpsRunBinding = ktxRunOnUi.binding;
                if (activityGpsRunBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding = null;
                }
                activityGpsRunBinding.gpsHeart.setItemTitle(String.valueOf(realTimeHeartRateRsp.getHeart()));
            }
        });
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        GoogleMap googleMap = this.map;
        if (googleMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, googleMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, this.lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.map = map;
        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();
    }

    private final void getDeviceLocation() {
        try {
            if (this.locationPermissionGranted) {
                FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationProviderClient;
                if (fusedLocationProviderClient == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fusedLocationProviderClient");
                    fusedLocationProviderClient = null;
                }
                Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
                Intrinsics.checkNotNullExpressionValue(lastLocation, "fusedLocationProviderClient.lastLocation");
                lastLocation.addOnCompleteListener(this, new OnCompleteListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda7
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        GpsRunActivity.m680getDeviceLocation$lambda6(this.f$0, task);
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getDeviceLocation$lambda-6, reason: not valid java name */
    public static final void m680getDeviceLocation$lambda6(GpsRunActivity this$0, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Location location = (Location) task.getResult();
            this$0.lastKnownLocation = location;
            if (location != null) {
                Intrinsics.checkNotNull(location);
                double latitude = location.getLatitude();
                Location location2 = this$0.lastKnownLocation;
                Intrinsics.checkNotNull(location2);
                Gps gpsTransform2 = GpsUtilsKt.transform2(latitude, location2.getLongitude());
                GoogleMap googleMap = this$0.map;
                if (googleMap != null) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsTransform2.getWgLat(), gpsTransform2.getWgLon()), 15.0f));
                    return;
                }
                return;
            }
            return;
        }
        Log.d(this$0.getTAG(), "Current location is null. Using defaults.");
        Log.e(this$0.getTAG(), "Exception: %s", task.getException());
        GoogleMap googleMap2 = this$0.map;
        if (googleMap2 != null) {
            googleMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(this$0.defaultLocation, 15.0f));
        }
        GoogleMap googleMap3 = this$0.map;
        UiSettings uiSettings = googleMap3 != null ? googleMap3.getUiSettings() : null;
        if (uiSettings == null) {
            return;
        }
        uiSettings.setMyLocationButtonEnabled(false);
    }

    private final void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Permission.ACCESS_FINE_LOCATION) == 0) {
            this.locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        this.locationPermissionGranted = false;
        if (requestCode == 1) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                this.locationPermissionGranted = true;
            }
        }
        updateLocationUI();
    }

    private final void updateLocationUI() {
        GoogleMap googleMap = this.map;
        if (googleMap == null) {
            return;
        }
        if (googleMap != null) {
            try {
                googleMap.setMyLocationEnabled(false);
            } catch (SecurityException e) {
                Log.e("Exception: %s", e.getMessage(), e);
                return;
            }
        }
        GoogleMap googleMap2 = this.map;
        UiSettings uiSettings = googleMap2 != null ? googleMap2.getUiSettings() : null;
        if (uiSettings != null) {
            uiSettings.setMyLocationButtonEnabled(false);
        }
        if (this.locationPermissionGranted) {
            return;
        }
        this.lastKnownLocation = null;
        getLocationPermission();
    }

    /* compiled from: GpsRunActivity.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/GpsRunActivity$Callback;", "Landroid/location/GnssStatus$Callback;", "(Lcom/qcwireless/qcwatch/ui/home/gps/GpsRunActivity;)V", "onFirstFix", "", "ttffMillis", "", "onSatelliteStatusChanged", NotificationCompat.CATEGORY_STATUS, "Landroid/location/GnssStatus;", "onStarted", "onStopped", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends GnssStatus.Callback {
        public Callback() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            super.onStarted();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            super.onStopped();
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int ttffMillis) {
            super.onFirstFix(ttffMillis);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus status) {
            Intrinsics.checkNotNullParameter(status, "status");
            super.onSatelliteStatusChanged(status);
            GpsRunActivity.this.setGpsStatues(status.getSatelliteCount());
        }
    }

    public final void setGpsStatues(int number) {
        ActivityGpsRunBinding activityGpsRunBinding = null;
        if (number <= 0) {
            ActivityGpsRunBinding activityGpsRunBinding2 = this.binding;
            if (activityGpsRunBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsRunBinding = activityGpsRunBinding2;
            }
            activityGpsRunBinding.imageSignal.setImageResource(R.mipmap.signal_1);
            return;
        }
        if (number <= 2) {
            ActivityGpsRunBinding activityGpsRunBinding3 = this.binding;
            if (activityGpsRunBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsRunBinding = activityGpsRunBinding3;
            }
            activityGpsRunBinding.imageSignal.setImageResource(R.mipmap.signal_2);
            return;
        }
        if (number <= 5) {
            ActivityGpsRunBinding activityGpsRunBinding4 = this.binding;
            if (activityGpsRunBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsRunBinding = activityGpsRunBinding4;
            }
            activityGpsRunBinding.imageSignal.setImageResource(R.mipmap.signal_3);
            return;
        }
        ActivityGpsRunBinding activityGpsRunBinding5 = this.binding;
        if (activityGpsRunBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsRunBinding = activityGpsRunBinding5;
        }
        activityGpsRunBinding.imageSignal.setImageResource(R.mipmap.signal_4);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            Timer timer = this.timer;
            if (timer != null) {
                Intrinsics.checkNotNull(timer);
                timer.cancel();
            }
            BleOperateManager.getInstance().removeNotifyListener(30);
            this.gnss.onStopped();
            LocationManager locationManager = SystemServiceExtKt.getLocationManager(this);
            Intrinsics.checkNotNull(locationManager);
            locationManager.unregisterGnssStatusCallback(this.gnss);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endViewAnim() {
        ActivityGpsRunBinding activityGpsRunBinding = this.binding;
        ActivityGpsRunBinding activityGpsRunBinding2 = null;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(activityGpsRunBinding.imageStop.getMeasuredWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        ActivityGpsRunBinding activityGpsRunBinding3 = this.binding;
        if (activityGpsRunBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding3 = null;
        }
        activityGpsRunBinding3.imageEnd.startAnimation(translateAnimation);
        ActivityGpsRunBinding activityGpsRunBinding4 = this.binding;
        if (activityGpsRunBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding4 = null;
        }
        TranslateAnimation translateAnimation2 = new TranslateAnimation(-activityGpsRunBinding4.imageStop.getMeasuredWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.endViewAnim.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ActivityGpsRunBinding activityGpsRunBinding5 = GpsRunActivity.this.binding;
                if (activityGpsRunBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding5 = null;
                }
                ViewKt.visible(activityGpsRunBinding5.tvLabelEnd);
            }
        });
        translateAnimation2.setDuration(300L);
        ActivityGpsRunBinding activityGpsRunBinding5 = this.binding;
        if (activityGpsRunBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsRunBinding2 = activityGpsRunBinding5;
        }
        activityGpsRunBinding2.imageContinue.startAnimation(translateAnimation2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startViewAnim() {
        ActivityGpsRunBinding activityGpsRunBinding = this.binding;
        ActivityGpsRunBinding activityGpsRunBinding2 = null;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, activityGpsRunBinding.imageStop.getMeasuredWidth(), 0.0f, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.startViewAnim.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }
        });
        if (this.startAnim == null) {
            ActivityGpsRunBinding activityGpsRunBinding3 = this.binding;
            if (activityGpsRunBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityGpsRunBinding3 = null;
            }
            TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, -activityGpsRunBinding3.imageStop.getMeasuredWidth(), 0.0f, 0.0f);
            this.startAnim = translateAnimation2;
            Intrinsics.checkNotNull(translateAnimation2);
            translateAnimation2.setDuration(300L);
            TranslateAnimation translateAnimation3 = this.startAnim;
            Intrinsics.checkNotNull(translateAnimation3);
            translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.startViewAnim.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    GpsRunActivity.this.showGpsEnd();
                }
            });
        }
        ActivityGpsRunBinding activityGpsRunBinding4 = this.binding;
        if (activityGpsRunBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding4 = null;
        }
        activityGpsRunBinding4.imageEnd.startAnimation(translateAnimation);
        ActivityGpsRunBinding activityGpsRunBinding5 = this.binding;
        if (activityGpsRunBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsRunBinding2 = activityGpsRunBinding5;
        }
        activityGpsRunBinding2.imageContinue.startAnimation(this.startAnim);
    }

    public final void showGpsEnd() {
        XLog.i("showGpsEnd");
        ActivityGpsRunBinding activityGpsRunBinding = this.binding;
        ActivityGpsRunBinding activityGpsRunBinding2 = null;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        ViewKt.gone(activityGpsRunBinding.imageEnd);
        ActivityGpsRunBinding activityGpsRunBinding3 = this.binding;
        if (activityGpsRunBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding3 = null;
        }
        ViewKt.gone(activityGpsRunBinding3.imageContinue);
        ActivityGpsRunBinding activityGpsRunBinding4 = this.binding;
        if (activityGpsRunBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding4 = null;
        }
        ViewKt.visible(activityGpsRunBinding4.imageStop);
        ActivityGpsRunBinding activityGpsRunBinding5 = this.binding;
        if (activityGpsRunBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsRunBinding2 = activityGpsRunBinding5;
        }
        activityGpsRunBinding2.imageStop.setEnabled(true);
    }

    private final void subscribeToObservers() {
        GpsRunActivity gpsRunActivity = this;
        TrackingService.INSTANCE.isTracking().observe(gpsRunActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                GpsRunActivity.m689subscribeToObservers$lambda7(this.f$0, (Boolean) obj);
            }
        });
        TrackingService.INSTANCE.getPathPoints().observe(gpsRunActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                GpsRunActivity.m690subscribeToObservers$lambda9(this.f$0, (List) obj);
            }
        });
        TrackingService.INSTANCE.getTimeRunInMillis().observe(gpsRunActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                GpsRunActivity.m688subscribeToObservers$lambda10(this.f$0, (Long) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: subscribeToObservers$lambda-7, reason: not valid java name */
    public static final void m689subscribeToObservers$lambda7(GpsRunActivity this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.upDateTracking(it.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: subscribeToObservers$lambda-9, reason: not valid java name */
    public static final void m690subscribeToObservers$lambda9(GpsRunActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.pathPoint = it;
        this$0.moveCameraToUser();
        this$0.distanceInMeters = GpsUtilsKt.calculatePolyLineLength(it);
        if (!this$0.firstLocation && this$0.pathPoint.size() > 0) {
            LatLng latLng = new LatLng(((QcLatLon) CollectionsKt.first((List) this$0.pathPoint)).getLatitude(), ((QcLatLon) CollectionsKt.first((List) this$0.pathPoint)).getLongitude());
            GoogleMap googleMap = this$0.map;
            if (googleMap != null) {
                googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_start_mark)).anchor(0.5f, 0.5f));
            }
            this$0.firstLocation = true;
        }
        float f = 30;
        float f2 = 1000;
        float f3 = (this$0.distanceInMeters * f) / f2;
        this$0.calorieTotal = f3;
        try {
            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                ActivityGpsRunBinding activityGpsRunBinding = this$0.binding;
                if (activityGpsRunBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding = null;
                }
                QSportItemView qSportItemView = activityGpsRunBinding.gpsDistance;
                DecimalFormat decimalFormat = this$0.df;
                if (decimalFormat == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("df");
                    decimalFormat = null;
                }
                String str = decimalFormat.format(Float.valueOf((this$0.distanceInMeters * 1.0f) / f2));
                Intrinsics.checkNotNullExpressionValue(str, "df.format(distanceInMeters * 1.0f / 1000)");
                qSportItemView.setItemTitle(str);
                ActivityGpsRunBinding activityGpsRunBinding2 = this$0.binding;
                if (activityGpsRunBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding2 = null;
                }
                QSportItemView qSportItemView2 = activityGpsRunBinding2.gpsDistance;
                String string = this$0.getString(R.string.qc_text_88);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_88)");
                qSportItemView2.setItemUnit(string);
            } else {
                ActivityGpsRunBinding activityGpsRunBinding3 = this$0.binding;
                if (activityGpsRunBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding3 = null;
                }
                QSportItemView qSportItemView3 = activityGpsRunBinding3.gpsDistance;
                DecimalFormat decimalFormat2 = this$0.df;
                if (decimalFormat2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("df");
                    decimalFormat2 = null;
                }
                String str2 = decimalFormat2.format(Float.valueOf(MetricUtilsKt.kmToIn((this$0.distanceInMeters * 1.0f) / f2)));
                Intrinsics.checkNotNullExpressionValue(str2, "df.format(kmToIn(distanceInMeters * 1.0f / 1000))");
                qSportItemView3.setItemTitle(str2);
                ActivityGpsRunBinding activityGpsRunBinding4 = this$0.binding;
                if (activityGpsRunBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding4 = null;
                }
                QSportItemView qSportItemView4 = activityGpsRunBinding4.gpsDistance;
                String string2 = this$0.getString(R.string.qc_text_358);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_358)");
                qSportItemView4.setItemUnit(string2);
            }
        } catch (Exception unused) {
        }
        ActivityGpsRunBinding activityGpsRunBinding5 = this$0.binding;
        if (activityGpsRunBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding5 = null;
        }
        activityGpsRunBinding5.gpsCalorie.setItemTitle(String.valueOf((int) f3));
        if (((int) this$0.distanceInMeters) == 0) {
            float fCalculatePolyLineLength = GpsUtilsKt.calculatePolyLineLength(this$0.pathPoint);
            this$0.distanceInMeters = fCalculatePolyLineLength;
            this$0.calorieTotal = (f * fCalculatePolyLineLength) / f2;
        }
        CommandHandle.getInstance().executeReqCmdNoCallback(PhoneGpsReq.setPhoneDataReq((int) (this$0.distanceInMeters * 100), (int) (this$0.calorieTotal * f2)));
        XLog.i(this$0.currentTimeInMiliis + "----" + this$0.distanceInMeters);
        float f4 = ((float) this$0.currentTimeInMiliis) / this$0.distanceInMeters;
        if (f4 > 5999.0f) {
            ActivityGpsRunBinding activityGpsRunBinding6 = this$0.binding;
            if (activityGpsRunBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityGpsRunBinding6 = null;
            }
            activityGpsRunBinding6.gpsPace.setItemTitle("--");
            f4 = 5999.0f;
        } else {
            ActivityGpsRunBinding activityGpsRunBinding7 = this$0.binding;
            if (activityGpsRunBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityGpsRunBinding7 = null;
            }
            QSportItemView qSportItemView5 = activityGpsRunBinding7.gpsPace;
            String strDayMinToStrSymbol = DateUtil.dayMinToStrSymbol((int) f4);
            Intrinsics.checkNotNullExpressionValue(strDayMinToStrSymbol, "dayMinToStrSymbol(fStr.toInt())");
            qSportItemView5.setItemTitle(strDayMinToStrSymbol);
        }
        XLog.i(Integer.valueOf(this$0.pathPoint.size()));
        if (this$0.pathPoint.size() > 0) {
            double d = f4;
            XLog.i(Double.valueOf(d));
            ((QcLatLon) CollectionsKt.last((List) this$0.pathPoint)).setPace(d);
        }
        if (!(!this$0.pathPoint.isEmpty()) || this$0.pathPoint.size() <= 2) {
            return;
        }
        PolylineOptions polylineOptionsJointType = new PolylineOptions().width(10.0f).jointType(2);
        Intrinsics.checkNotNullExpressionValue(polylineOptionsJointType, "PolylineOptions()\n      …ointType(JointType.ROUND)");
        for (QcLatLon qcLatLon : this$0.pathPoint) {
            polylineOptionsJointType.add(new LatLng(qcLatLon.getLatitude(), qcLatLon.getLongitude()));
            polylineOptionsJointType.addSpan(new StyleSpan(GpsUtilsKt.getPaceColor(qcLatLon.getSpeed())));
        }
        GoogleMap googleMap2 = this$0.map;
        if (googleMap2 != null) {
            googleMap2.addPolyline(polylineOptionsJointType);
        }
        Marker marker = this$0.marker;
        if (marker != null && marker != null) {
            marker.remove();
        }
        GoogleMap googleMap3 = this$0.map;
        this$0.marker = googleMap3 != null ? googleMap3.addMarker(new MarkerOptions().position(new LatLng(((QcLatLon) CollectionsKt.last((List) this$0.pathPoint)).getLatitude(), ((QcLatLon) CollectionsKt.last((List) this$0.pathPoint)).getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_mark)).anchor(0.5f, 0.5f)) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: subscribeToObservers$lambda-10, reason: not valid java name */
    public static final void m688subscribeToObservers$lambda10(GpsRunActivity this$0, Long it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        long jLongValue = it.longValue();
        this$0.currentTimeInMiliis = jLongValue;
        if (jLongValue == 0 && this$0.gpsStart) {
            this$0.gpsStart = false;
        }
        String formattedStopWatchTIme = GpsUtilsKt.getFormattedStopWatchTIme(jLongValue, false);
        ActivityGpsRunBinding activityGpsRunBinding = this$0.binding;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        activityGpsRunBinding.gpsTimes.setItemTitle(formattedStopWatchTIme);
    }

    private final void upDateTracking(boolean isTracking) {
        this.isTracking = isTracking;
        if (!isTracking) {
            XLog.i("start");
        } else {
            XLog.i("stop");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toggleRun() {
        if (this.isTracking) {
            sendCommandToService(TrackingService.Constant.ACTION_PAUSE_SERVICE);
        } else {
            sendCommandToService(TrackingService.Constant.ACTION_START_OR_RESUME_SERVICE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopRun() {
        sendCommandToService(TrackingService.Constant.ACTION_STOP_SERVICE);
        finish();
        GpsRunActivity gpsRunActivity = this;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(gpsRunActivity, (Class<?>) GpsHistoryActivity.class);
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
        gpsRunActivity.startActivity(intent);
    }

    private final void moveCameraToUser() {
        GoogleMap googleMap;
        if ((!this.pathPoint.isEmpty()) && (!this.pathPoint.isEmpty()) && (googleMap = this.map) != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(((QcLatLon) CollectionsKt.last((List) this.pathPoint)).getLatitude(), ((QcLatLon) CollectionsKt.last((List) this.pathPoint)).getLongitude()), 18.0f));
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
            ActivityGpsRunBinding activityGpsRunBinding = this.binding;
            if (activityGpsRunBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityGpsRunBinding = null;
            }
            activityGpsRunBinding.gpsHeart.setItemTitle("00");
            return;
        }
        if (messageEvent instanceof DeviceGpsEvent) {
            if (BleOperateManager.getInstance().isConnected()) {
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GpsRunActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.onMessageEvent.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(GpsRunActivity gpsRunActivity) {
                        invoke2(gpsRunActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(GpsRunActivity ktxRunOnBgSingle) {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        try {
                            if (((int) ktxRunOnBgSingle.getDistanceInMeters()) == 0) {
                                ktxRunOnBgSingle.setDistanceInMeters(GpsUtilsKt.calculatePolyLineLength(ktxRunOnBgSingle.pathPoint));
                                ktxRunOnBgSingle.setCalorieTotal((30 * ktxRunOnBgSingle.getDistanceInMeters()) / 1000);
                            }
                        } catch (Exception unused) {
                        }
                        try {
                            CommandHandle.getInstance().executeReqCmdNoCallback(PhoneGpsReq.setPhoneDataReq((int) (ktxRunOnBgSingle.getDistanceInMeters() * 100), (int) (ktxRunOnBgSingle.getCalorieTotal() * 1000)));
                        } catch (Exception unused2) {
                        }
                    }
                });
            }
        } else {
            if (messageEvent instanceof DeviceGpsDataEvent) {
                try {
                    List<QcLatLon> value = ((DeviceGpsDataEvent) messageEvent).getData().getValue();
                    Intrinsics.checkNotNull(value);
                    this.pathPoint = value;
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GpsRunActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.onMessageEvent.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GpsRunActivity gpsRunActivity) {
                            invoke2(gpsRunActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(GpsRunActivity ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            if (BleOperateManager.getInstance().isConnected()) {
                                try {
                                    ktxRunOnBgSingle.setDistanceInMeters(GpsUtilsKt.calculatePolyLineLength(ktxRunOnBgSingle.pathPoint));
                                    ktxRunOnBgSingle.setCalorieTotal((30 * ktxRunOnBgSingle.getDistanceInMeters()) / 1000);
                                } catch (Exception unused) {
                                }
                                try {
                                    CommandHandle.getInstance().executeReqCmdNoCallback(PhoneGpsReq.setPhoneDataReq((int) (ktxRunOnBgSingle.getDistanceInMeters() * 100), (int) (ktxRunOnBgSingle.getCalorieTotal() * 1000)));
                                } catch (Exception unused2) {
                                }
                            }
                        }
                    });
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            if (messageEvent instanceof DeviceGpsTimeEvent) {
                this.currentTimeInMiliis = ((DeviceGpsTimeEvent) messageEvent).getTime();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ActivityGpsRunBinding activityGpsRunBinding = this.binding;
        if (activityGpsRunBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding = null;
        }
        XLog.i(Boolean.valueOf(activityGpsRunBinding.imageStop.isLock()));
        ActivityGpsRunBinding activityGpsRunBinding2 = this.binding;
        if (activityGpsRunBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsRunBinding2 = null;
        }
        ImageView imageView = activityGpsRunBinding2.imageScreenLock;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.imageScreenLock");
        if (!(imageView.getVisibility() == 0)) {
            String string = getString(R.string.qc_text_277);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_277)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        showGpsExitDialog();
    }

    private final void sendCommandToService(String action) {
        Intent intent = new Intent(this, (Class<?>) TrackingService.class);
        intent.setAction(action);
        startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showGpsExitDialog() {
        String string;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if ((this.currentTimeInMiliis / 1000) / 60 < 3) {
            booleanRef.element = false;
            string = getString(R.string.qc_text_278);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_278)");
        } else {
            booleanRef.element = true;
            string = getString(R.string.qc_text_279);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_279)");
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_gps_exit);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        ((TextView) contentView.findViewById(R.id.tv_content)).setText(string);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GpsRunActivity.m686showGpsExitDialog$lambda12(objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GpsRunActivity.m687showGpsExitDialog$lambda13(booleanRef, this, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showGpsExitDialog$lambda-12, reason: not valid java name */
    public static final void m686showGpsExitDialog$lambda12(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showGpsExitDialog$lambda-13, reason: not valid java name */
    public static final void m687showGpsExitDialog$lambda13(Ref.BooleanRef exitSave, GpsRunActivity this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(exitSave, "$exitSave");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        if (exitSave.element) {
            if (this$0.startTime == 0) {
                this$0.startTime = (System.currentTimeMillis() - this$0.currentTimeInMiliis) / 1000;
            }
            try {
                if (((int) this$0.distanceInMeters) == 0) {
                    float fCalculatePolyLineLength = GpsUtilsKt.calculatePolyLineLength(this$0.pathPoint);
                    this$0.distanceInMeters = fCalculatePolyLineLength;
                    this$0.calorieTotal = (30 * fCalculatePolyLineLength) / 1000;
                }
            } catch (Exception unused) {
            }
            long j = this$0.startTime;
            int i = (int) this$0.currentTimeInMiliis;
            float f = this$0.distanceInMeters;
            float f2 = this$0.calorieTotal;
            String json = MoshiUtilsKt.toJson(this$0.pathPoint);
            String y_m_d = new DateUtil().getY_M_D();
            Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
            this$0.getViewModel().saveGpsDetail(new GpsDetail(j, i, f, f2, json, y_m_d));
        }
        CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 4), this$0.gpsResponse);
        this$0.stopRun();
        EventBus.getDefault().post(new HealthItemRefreshEvent());
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    private final void initGPSListener() {
        CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 3), this.gpsResponse);
        BleOperateManager.getInstance().addNotifyListener(116, this.gpsResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: gpsResponse$lambda-14, reason: not valid java name */
    public static final void m681gpsResponse$lambda14(GpsRunActivity this$0, AppGpsRsp appGpsRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(appGpsRsp);
        if (appGpsRsp != null) {
            try {
                int gpsStatus = appGpsRsp.getGpsStatus();
                if (gpsStatus == 2) {
                    ThreadExtKt.ktxRunOnUi(this$0, new Function1<GpsRunActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$gpsResponse$1$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GpsRunActivity gpsRunActivity) {
                            invoke2(gpsRunActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(GpsRunActivity ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivityGpsRunBinding activityGpsRunBinding = ktxRunOnUi.binding;
                            ActivityGpsRunBinding activityGpsRunBinding2 = null;
                            if (activityGpsRunBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityGpsRunBinding = null;
                            }
                            activityGpsRunBinding.imageStop.setVisibility(4);
                            ActivityGpsRunBinding activityGpsRunBinding3 = ktxRunOnUi.binding;
                            if (activityGpsRunBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityGpsRunBinding3 = null;
                            }
                            ViewKt.visible(activityGpsRunBinding3.imageContinue);
                            ActivityGpsRunBinding activityGpsRunBinding4 = ktxRunOnUi.binding;
                            if (activityGpsRunBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityGpsRunBinding4 = null;
                            }
                            ViewKt.visible(activityGpsRunBinding4.imageEnd);
                            ActivityGpsRunBinding activityGpsRunBinding5 = ktxRunOnUi.binding;
                            if (activityGpsRunBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityGpsRunBinding5 = null;
                            }
                            ViewKt.gone(activityGpsRunBinding5.imageScreenLock);
                            ActivityGpsRunBinding activityGpsRunBinding6 = ktxRunOnUi.binding;
                            if (activityGpsRunBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityGpsRunBinding2 = activityGpsRunBinding6;
                            }
                            activityGpsRunBinding2.imageStop.setEnabled(false);
                            ktxRunOnUi.isTracking = true;
                            ktxRunOnUi.toggleRun();
                            ktxRunOnUi.endViewAnim();
                        }
                    });
                    return;
                }
                if (gpsStatus != 3) {
                    if (gpsStatus != 4) {
                        if (gpsStatus != 6) {
                            return;
                        }
                        this$0.startTime = appGpsRsp.getTimeStamp();
                        XLog.i(Integer.valueOf(appGpsRsp.getTimeStamp()));
                        return;
                    }
                    if ((this$0.currentTimeInMiliis / 1000) / 60 < 3) {
                        this$0.stopRun();
                        return;
                    } else {
                        ThreadExtKt.ktxRunOnUi(this$0, new Function1<GpsRunActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$gpsResponse$1$2
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(GpsRunActivity gpsRunActivity) {
                                invoke2(gpsRunActivity);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(GpsRunActivity ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                ktxRunOnUi.stopGps();
                                try {
                                    ktxRunOnUi.setDistanceInMeters(GpsUtilsKt.calculatePolyLineLength(ktxRunOnUi.pathPoint));
                                    ktxRunOnUi.setCalorieTotal((30 * ktxRunOnUi.getDistanceInMeters()) / 1000);
                                } catch (Exception unused) {
                                }
                                long j = ktxRunOnUi.startTime;
                                int i = (int) ktxRunOnUi.currentTimeInMiliis;
                                float distanceInMeters = ktxRunOnUi.getDistanceInMeters();
                                float calorieTotal = ktxRunOnUi.getCalorieTotal();
                                String json = MoshiUtilsKt.toJson(ktxRunOnUi.pathPoint);
                                String y_m_d = new DateUtil().getY_M_D();
                                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                                GpsDetail gpsDetail = new GpsDetail(j, i, distanceInMeters, calorieTotal, json, y_m_d);
                                XLog.i(MoshiUtilsKt.toJson(ktxRunOnUi.pathPoint));
                                ktxRunOnUi.getViewModel().saveGpsDetail(gpsDetail);
                                ktxRunOnUi.stopRun();
                            }
                        });
                        return;
                    }
                }
                ActivityGpsRunBinding activityGpsRunBinding = this$0.binding;
                ActivityGpsRunBinding activityGpsRunBinding2 = null;
                if (activityGpsRunBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsRunBinding = null;
                }
                ViewKt.gone(activityGpsRunBinding.tvLabelEnd);
                ActivityGpsRunBinding activityGpsRunBinding3 = this$0.binding;
                if (activityGpsRunBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityGpsRunBinding2 = activityGpsRunBinding3;
                }
                ViewKt.visible(activityGpsRunBinding2.imageScreenLock);
                this$0.startViewAnim();
                this$0.isTracking = false;
                this$0.toggleRun();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopGps() {
        CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 6), this.gpsResponse);
        CommandHandle.getInstance().executeReqCmd(PhoneGpsReq.getGpsStatus((byte) 4), this.gpsResponse);
    }

    private final void syncDistance() {
        if (this.timer == null) {
            Timer timer = new Timer();
            this.timer = timer;
            Intrinsics.checkNotNull(timer);
            timer.scheduleAtFixedRate(new TimerTask() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity.syncDistance.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    final GpsRunActivity gpsRunActivity = GpsRunActivity.this;
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<C05781, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsRunActivity$syncDistance$1$run$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GpsRunActivity.C05781 c05781) {
                            invoke2(c05781);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(GpsRunActivity.C05781 ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            try {
                                if (((int) gpsRunActivity.getDistanceInMeters()) == 0) {
                                    GpsRunActivity gpsRunActivity2 = gpsRunActivity;
                                    gpsRunActivity2.setDistanceInMeters(GpsUtilsKt.calculatePolyLineLength(gpsRunActivity2.pathPoint));
                                    GpsRunActivity gpsRunActivity3 = gpsRunActivity;
                                    gpsRunActivity3.setCalorieTotal((30 * gpsRunActivity3.getDistanceInMeters()) / 1000);
                                }
                            } catch (Exception unused) {
                            }
                            try {
                                CommandHandle.getInstance().executeReqCmdNoCallback(PhoneGpsReq.setPhoneDataReq((int) (gpsRunActivity.getDistanceInMeters() * 100), (int) (gpsRunActivity.getCalorieTotal() * 1000)));
                            } catch (Exception unused2) {
                            }
                        }
                    });
                }
            }, 0L, TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
        }
    }
}
