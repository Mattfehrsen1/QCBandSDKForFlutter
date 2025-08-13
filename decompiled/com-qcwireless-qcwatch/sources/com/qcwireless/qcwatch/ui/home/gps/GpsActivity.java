package com.qcwireless.qcwatch.ui.home.gps;

import android.app.Activity;
import android.content.Intent;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.hjq.permissions.Permission;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.SystemServiceExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityGpsBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.gps.bean.Gps;
import com.qcwireless.qcwatch.ui.home.gps.util.GpsUtilsKt;
import com.qcwireless.qcwatch.ui.home.gps.vm.GPSActivityViewModel;
import com.qcwireless.qcwatch.ui.home.healthy.vm.HealthyViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: GpsActivity.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\f\u0018\u0000 92\u00020\u00012\u00020\u00022\u00020\u0003:\u000289B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\u0012\u0010\u001f\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u001dH\u0015J\u0010\u0010#\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&H\u0016J+\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010-\u001a\u00020.H\u0016¢\u0006\u0002\u0010/J\b\u00100\u001a\u00020\u001dH\u0014J\u0010\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u00020!H\u0014J\b\u00103\u001a\u00020\u001dH\u0002J\u000e\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u00020)J\b\u00106\u001a\u00020\u001dH\u0015J\b\u00107\u001a\u00020\u001dH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000e8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006:"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/GpsActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "Lcom/google/android/gms/maps/GoogleMap$OnMarkerClickListener;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityGpsBinding;", "cameraPosition", "Lcom/google/android/gms/maps/model/CameraPosition;", "defaultLocation", "Lcom/google/android/gms/maps/model/LatLng;", "fusedLocationProviderClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "gnss", "Landroid/location/GnssStatus$Callback;", "lastKnownLocation", "Landroid/location/Location;", "locationPermissionGranted", "", "map", "Lcom/google/android/gms/maps/GoogleMap;", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkPermission", "getDeviceLocation", "", "getLocationPermission", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMapReady", "onMarkerClick", "p0", "Lcom/google/android/gms/maps/model/Marker;", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onSaveInstanceState", "outState", "openBatteryWhiteList", "setGpsStatues", "number", "setupViews", "updateLocationUI", "Callback", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private static final int DEFAULT_ZOOM = 15;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final int M_MAX_ENTRIES = 5;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private ActivityGpsBinding binding;
    private CameraPosition cameraPosition;
    private final LatLng defaultLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GnssStatus.Callback gnss = new Callback();
    private Location lastKnownLocation;
    private boolean locationPermissionGranted;
    private GoogleMap map;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GpsActivity() {
        final GpsActivity gpsActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<GPSActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.gps.vm.GPSActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final GPSActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(gpsActivity, Reflection.getOrCreateKotlinClass(GPSActivityViewModel.class), qualifier, objArr);
            }
        });
        this.defaultLocation = new LatLng(-33.8523341d, 151.2106085d);
    }

    private final GPSActivityViewModel getViewModel() {
        return (GPSActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGpsBinding activityGpsBindingInflate = ActivityGpsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityGpsBindingInflate, "inflate(layoutInflater)");
        this.binding = activityGpsBindingInflate;
        if (activityGpsBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsBindingInflate = null;
        }
        ConstraintLayout root = activityGpsBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
        if (savedInstanceState != null) {
            this.lastKnownLocation = (Location) savedInstanceState.getParcelable(KEY_LOCATION);
            this.cameraPosition = (CameraPosition) savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(this)");
        this.fusedLocationProviderClient = fusedLocationProviderClient;
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
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

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.gps_bg);
        ActivityGpsBinding activityGpsBinding = this.binding;
        if (activityGpsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsBinding = null;
        }
        activityGpsBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_92));
        ViewKt.gone(activityGpsBinding.titleBar.divider);
        View[] viewArr = new View[2];
        ActivityGpsBinding activityGpsBinding2 = this.binding;
        if (activityGpsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsBinding2 = null;
        }
        viewArr[0] = activityGpsBinding2.btnGpsGo;
        ActivityGpsBinding activityGpsBinding3 = this.binding;
        if (activityGpsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsBinding3 = null;
        }
        viewArr[1] = activityGpsBinding3.tvSportHistory;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsActivity.setupViews.2
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
                ActivityGpsBinding activityGpsBinding4 = GpsActivity.this.binding;
                ActivityGpsBinding activityGpsBinding5 = null;
                if (activityGpsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityGpsBinding4.btnGpsGo)) {
                    if (!GpsActivity.this.checkPermission()) {
                        GpsActivity.this.openBatteryWhiteList();
                        String string = GpsActivity.this.getString(R.string.qc_text_390);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_390)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        return;
                    }
                    GpsActivity gpsActivity = GpsActivity.this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(gpsActivity, (Class<?>) GpsPrepareActivity.class);
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
                    gpsActivity.startActivity(intent);
                    return;
                }
                ActivityGpsBinding activityGpsBinding6 = GpsActivity.this.binding;
                if (activityGpsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityGpsBinding5 = activityGpsBinding6;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityGpsBinding5.tvSportHistory)) {
                    GpsActivity gpsActivity2 = GpsActivity.this;
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intent intent2 = new Intent(gpsActivity2, (Class<?>) GpsHistoryActivity.class);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    gpsActivity2.startActivity(intent2);
                }
            }
        });
        GpsActivity gpsActivity = this;
        if (ActivityCompat.checkSelfPermission(gpsActivity, Permission.ACCESS_FINE_LOCATION) != 0) {
            return;
        }
        LocationManager locationManager = SystemServiceExtKt.getLocationManager(gpsActivity);
        if (locationManager != null) {
            locationManager.registerGnssStatusCallback(this.gnss, (Handler) null);
        }
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                GpsActivity.m672setupViews$lambda2(this.f$0, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m672setupViews$lambda2(GpsActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(str);
        ActivityGpsBinding activityGpsBinding = this$0.binding;
        ActivityGpsBinding activityGpsBinding2 = null;
        if (activityGpsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsBinding = null;
        }
        activityGpsBinding.tvTotalDistance.setText(str);
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            ActivityGpsBinding activityGpsBinding3 = this$0.binding;
            if (activityGpsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsBinding2 = activityGpsBinding3;
            }
            activityGpsBinding2.tvGpsUnit.setText(this$0.getString(R.string.qc_text_88));
            return;
        }
        ActivityGpsBinding activityGpsBinding4 = this$0.binding;
        if (activityGpsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsBinding2 = activityGpsBinding4;
        }
        activityGpsBinding2.tvGpsUnit.setText(this$0.getString(R.string.qc_text_358));
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getViewModel().queryTotalDistance();
        updateLocationUI();
        getDeviceLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        Object systemService = getSystemService("power");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return ((PowerManager) systemService).isIgnoringBatteryOptimizations(getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openBatteryWhiteList() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, HealthyViewModel.Type_Sync_TimeOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: GpsActivity.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/GpsActivity$Callback;", "Landroid/location/GnssStatus$Callback;", "(Lcom/qcwireless/qcwatch/ui/home/gps/GpsActivity;)V", "onFirstFix", "", "ttffMillis", "", "onSatelliteStatusChanged", NotificationCompat.CATEGORY_STATUS, "Landroid/location/GnssStatus;", "onStarted", "onStopped", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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
            GpsActivity.this.setGpsStatues(status.getSatelliteCount());
        }
    }

    public final void setGpsStatues(int number) {
        ActivityGpsBinding activityGpsBinding = null;
        if (number <= 0) {
            ActivityGpsBinding activityGpsBinding2 = this.binding;
            if (activityGpsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsBinding = activityGpsBinding2;
            }
            activityGpsBinding.imageSignal.setImageResource(R.mipmap.signal_1);
            return;
        }
        if (number <= 2) {
            ActivityGpsBinding activityGpsBinding3 = this.binding;
            if (activityGpsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsBinding = activityGpsBinding3;
            }
            activityGpsBinding.imageSignal.setImageResource(R.mipmap.signal_2);
            return;
        }
        if (number <= 5) {
            ActivityGpsBinding activityGpsBinding4 = this.binding;
            if (activityGpsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsBinding = activityGpsBinding4;
            }
            activityGpsBinding.imageSignal.setImageResource(R.mipmap.signal_3);
            return;
        }
        ActivityGpsBinding activityGpsBinding5 = this.binding;
        if (activityGpsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsBinding = activityGpsBinding5;
        }
        activityGpsBinding.imageSignal.setImageResource(R.mipmap.signal_4);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            this.gnss.onStopped();
            LocationManager locationManager = SystemServiceExtKt.getLocationManager(this);
            Intrinsics.checkNotNull(locationManager);
            locationManager.unregisterGnssStatusCallback(this.gnss);
        } catch (Exception unused) {
        }
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
                lastLocation.addOnCompleteListener(this, new OnCompleteListener() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsActivity$$ExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        GpsActivity.m671getDeviceLocation$lambda3(this.f$0, task);
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getDeviceLocation$lambda-3, reason: not valid java name */
    public static final void m671getDeviceLocation$lambda3(GpsActivity this$0, Task task) {
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
                }
                GoogleMap googleMap2 = this$0.map;
                if (googleMap2 != null) {
                    googleMap2.addMarker(new MarkerOptions().position(new LatLng(gpsTransform2.getWgLat(), gpsTransform2.getWgLon())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_mark)).anchor(0.5f, 0.5f));
                    return;
                }
                return;
            }
            return;
        }
        Log.d(this$0.getTAG(), "Current location is null. Using defaults.");
        Log.e(this$0.getTAG(), "Exception: %s", task.getException());
        GoogleMap googleMap3 = this$0.map;
        if (googleMap3 != null) {
            googleMap3.moveCamera(CameraUpdateFactory.newLatLngZoom(this$0.defaultLocation, 15.0f));
        }
        GoogleMap googleMap4 = this$0.map;
        UiSettings uiSettings = googleMap4 != null ? googleMap4.getUiSettings() : null;
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
        try {
            if (this.locationPermissionGranted) {
                if (googleMap != null) {
                    googleMap.setMyLocationEnabled(false);
                }
                GoogleMap googleMap2 = this.map;
                UiSettings uiSettings = googleMap2 != null ? googleMap2.getUiSettings() : null;
                if (uiSettings == null) {
                    return;
                }
                uiSettings.setMyLocationButtonEnabled(false);
                return;
            }
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(false);
            }
            GoogleMap googleMap3 = this.map;
            UiSettings uiSettings2 = googleMap3 != null ? googleMap3.getUiSettings() : null;
            if (uiSettings2 != null) {
                uiSettings2.setMyLocationButtonEnabled(false);
            }
            this.lastKnownLocation = null;
            getLocationPermission();
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }
}
