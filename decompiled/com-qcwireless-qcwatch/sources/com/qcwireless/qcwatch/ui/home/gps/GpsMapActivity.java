package com.qcwireless.qcwatch.ui.home.gps;

import android.app.Activity;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.StyleSpan;
import com.hjq.permissions.Permission;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityGpsMapBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;
import com.qcwireless.qcwatch.ui.home.gps.bean.QcLatLon;
import com.qcwireless.qcwatch.ui.home.gps.util.GpsUtilsKt;
import com.qcwireless.qcwatch.ui.home.gps.vm.GPSActivityViewModel;
import com.squareup.moshi.Types;
import java.lang.reflect.ParameterizedType;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: GpsMapActivity.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 .2\u00020\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010*\u001a\u00020$2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020$H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\b\"\u0004\b\u0011\u0010\nR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 ¨\u0006/"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/GpsMapActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityGpsMapBinding;", "calorieTotal", "", "getCalorieTotal", "()F", "setCalorieTotal", "(F)V", "cameraPosition", "Lcom/google/android/gms/maps/model/CameraPosition;", "df", "Ljava/text/DecimalFormat;", "distanceInMeters", "getDistanceInMeters", "setDistanceInMeters", "fusedLocationProviderClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "pathPoint", "", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/QcLatLon;", "showKm", "", "getShowKm", "()I", "setShowKm", "(I)V", "viewModel", "Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/home/gps/vm/GPSActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "addLatestPolyline", "", "map", "Lcom/google/android/gms/maps/GoogleMap;", "checkPermission", "", "moveCameraToUser", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsMapActivity extends BaseActivity {
    private static final int DEFAULT_ZOOM = 15;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final int M_MAX_ENTRIES = 5;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private ActivityGpsMapBinding binding;
    private float calorieTotal;
    private CameraPosition cameraPosition;
    private DecimalFormat df;
    private float distanceInMeters;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private List<QcLatLon> pathPoint;
    private int showKm;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public GpsMapActivity() {
        final GpsMapActivity gpsMapActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<GPSActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsMapActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.gps.vm.GPSActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final GPSActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(gpsMapActivity, Reflection.getOrCreateKotlinClass(GPSActivityViewModel.class), qualifier, objArr);
            }
        });
        this.pathPoint = new ArrayList();
    }

    private final GPSActivityViewModel getViewModel() {
        return (GPSActivityViewModel) this.viewModel.getValue();
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

    public final int getShowKm() {
        return this.showKm;
    }

    public final void setShowKm(int i) {
        this.showKm = i;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGpsMapBinding activityGpsMapBindingInflate = ActivityGpsMapBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityGpsMapBindingInflate, "inflate(layoutInflater)");
        this.binding = activityGpsMapBindingInflate;
        ActivityGpsMapBinding activityGpsMapBinding = null;
        if (activityGpsMapBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsMapBindingInflate = null;
        }
        ConstraintLayout root = activityGpsMapBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(this)");
        this.fusedLocationProviderClient = fusedLocationProviderClient;
        long longExtra = getIntent().getLongExtra("start", 0L);
        XLog.i(Long.valueOf(longExtra));
        getViewModel().queryByStartTime(longExtra);
        getViewModel().getGpsDetail().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsMapActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                GpsMapActivity.m673onCreate$lambda0(this.f$0, (GpsDetail) obj);
            }
        });
        ActivityGpsMapBinding activityGpsMapBinding2 = this.binding;
        if (activityGpsMapBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsMapBinding2 = null;
        }
        activityGpsMapBinding2.mapView.onCreate(null);
        ActivityGpsMapBinding activityGpsMapBinding3 = this.binding;
        if (activityGpsMapBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsMapBinding3 = null;
        }
        activityGpsMapBinding3.mapView.onResume();
        ActivityGpsMapBinding activityGpsMapBinding4 = this.binding;
        if (activityGpsMapBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityGpsMapBinding = activityGpsMapBinding4;
        }
        activityGpsMapBinding.mapView.getMapAsync(new OnMapReadyCallback() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsMapActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.maps.OnMapReadyCallback
            public final void onMapReady(GoogleMap googleMap) {
                GpsMapActivity.m674onCreate$lambda1(this.f$0, googleMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m673onCreate$lambda0(GpsMapActivity this$0, GpsDetail gpsDetail) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (gpsDetail != null) {
            MoshiUtils moshiUtils = MoshiUtils.INSTANCE;
            String locations = gpsDetail.getLocations();
            ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, QcLatLon.class);
            Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
            ArrayList arrayList = (List) moshiUtils.fromJson(locations, parameterizedTypeNewParameterizedType);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            this$0.pathPoint = arrayList;
            ActivityGpsMapBinding activityGpsMapBinding = null;
            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                ActivityGpsMapBinding activityGpsMapBinding2 = this$0.binding;
                if (activityGpsMapBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsMapBinding2 = null;
                }
                QSportItemView qSportItemView = activityGpsMapBinding2.gpsDistance;
                DecimalFormat decimalFormat = this$0.df;
                if (decimalFormat == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("df");
                    decimalFormat = null;
                }
                String str = decimalFormat.format(Float.valueOf(gpsDetail.getDistance() / 1000));
                Intrinsics.checkNotNullExpressionValue(str, "df.format(it.distance / 1000)");
                qSportItemView.setItemTitle(str);
                ActivityGpsMapBinding activityGpsMapBinding3 = this$0.binding;
                if (activityGpsMapBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsMapBinding3 = null;
                }
                QSportItemView qSportItemView2 = activityGpsMapBinding3.gpsDistance;
                String string = this$0.getString(R.string.qc_text_88);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_88)");
                qSportItemView2.setItemUnit(string);
            } else {
                ActivityGpsMapBinding activityGpsMapBinding4 = this$0.binding;
                if (activityGpsMapBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsMapBinding4 = null;
                }
                QSportItemView qSportItemView3 = activityGpsMapBinding4.gpsDistance;
                DecimalFormat decimalFormat2 = this$0.df;
                if (decimalFormat2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("df");
                    decimalFormat2 = null;
                }
                String str2 = decimalFormat2.format(Float.valueOf(MetricUtilsKt.kmToIn(gpsDetail.getDistance() / 1000)));
                Intrinsics.checkNotNullExpressionValue(str2, "df.format(kmToIn(it.distance / 1000))");
                qSportItemView3.setItemTitle(str2);
                ActivityGpsMapBinding activityGpsMapBinding5 = this$0.binding;
                if (activityGpsMapBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsMapBinding5 = null;
                }
                QSportItemView qSportItemView4 = activityGpsMapBinding5.gpsDistance;
                String string2 = this$0.getString(R.string.qc_text_358);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_358)");
                qSportItemView4.setItemUnit(string2);
            }
            ActivityGpsMapBinding activityGpsMapBinding6 = this$0.binding;
            if (activityGpsMapBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityGpsMapBinding6 = null;
            }
            activityGpsMapBinding6.gpsCalorie.setItemTitle(String.valueOf((int) gpsDetail.getCalorie()));
            ActivityGpsMapBinding activityGpsMapBinding7 = this$0.binding;
            if (activityGpsMapBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityGpsMapBinding = activityGpsMapBinding7;
            }
            QSportItemView qSportItemView5 = activityGpsMapBinding.gpsTimes;
            String strMinsToHHmmdd = DateUtil.minsToHHmmdd(gpsDetail.getDuration() / 1000);
            Intrinsics.checkNotNullExpressionValue(strMinsToHHmmdd, "minsToHHmmdd(it.duration / 1000)");
            qSportItemView5.setItemTitle(strMinsToHHmmdd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m674onCreate$lambda1(GpsMapActivity this$0, GoogleMap it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.addLatestPolyline(it);
        this$0.moveCameraToUser(it);
    }

    private final void addLatestPolyline(GoogleMap map) {
        int i;
        XLog.i(Integer.valueOf(this.pathPoint.size()));
        if ((!this.pathPoint.isEmpty()) && this.pathPoint.size() > 2) {
            PolylineOptions polylineOptionsJointType = new PolylineOptions().width(10.0f).jointType(2);
            Intrinsics.checkNotNullExpressionValue(polylineOptionsJointType, "PolylineOptions()\n      …ointType(JointType.ROUND)");
            for (QcLatLon qcLatLon : this.pathPoint) {
                polylineOptionsJointType.add(new LatLng(qcLatLon.getLatitude(), qcLatLon.getLongitude()));
                polylineOptionsJointType.addSpan(new StyleSpan(GpsUtilsKt.getPaceColor(qcLatLon.getSpeed())));
            }
            map.addPolyline(polylineOptionsJointType);
        }
        float f = 0.0f;
        int size = this.pathPoint.size() - 2;
        char c = 0;
        if (size >= 0) {
            int i2 = 0;
            while (true) {
                QcLatLon qcLatLon2 = this.pathPoint.get(i2);
                int i3 = i2 + 1;
                QcLatLon qcLatLon3 = this.pathPoint.get(i3);
                float[] fArr = new float[1];
                Location.distanceBetween(qcLatLon2.getLatitude(), qcLatLon2.getLongitude(), qcLatLon3.getLatitude(), qcLatLon3.getLongitude(), fArr);
                f += fArr[c];
                int i4 = ((int) f) / 1000;
                if (f < 1000.0f || i4 <= this.showKm) {
                    i = i2;
                } else {
                    this.showKm = i4;
                    String strValueOf = String.valueOf(i4);
                    StringBuilder sb = new StringBuilder();
                    sb.append(strValueOf);
                    sb.append(UserConfig.INSTANCE.getInstance().getMetric() ? "km" : "mile");
                    int i5 = i2;
                    Marker markerAddMarker = map.addMarker(new MarkerOptions().position(new LatLng(qcLatLon3.getLatitude(), qcLatLon3.getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(GpsUtilsKt.fromText(this, sb.toString()))).flat(true).anchor(0.5f, 0.5f));
                    if (markerAddMarker != null) {
                        markerAddMarker.showInfoWindow();
                    }
                    i = i5;
                }
                if (i != size) {
                    i2 = i3;
                    c = 0;
                }
            }
        }
        try {
            map.addMarker(new MarkerOptions().position(new LatLng(this.pathPoint.get(0).getLatitude(), this.pathPoint.get(0).getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_start_mark)).anchor(0.4f, 0.4f));
            MarkerOptions markerOptions = new MarkerOptions();
            List<QcLatLon> list = this.pathPoint;
            double latitude = list.get(list.size() - 1).getLatitude();
            List<QcLatLon> list2 = this.pathPoint;
            map.addMarker(markerOptions.position(new LatLng(latitude, list2.get(list2.size() - 1).getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_end_mark)).anchor(0.5f, 0.5f));
        } catch (Exception unused) {
        }
    }

    private final void moveCameraToUser(GoogleMap map) {
        if ((!this.pathPoint.isEmpty()) && (!this.pathPoint.isEmpty())) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(((QcLatLon) CollectionsKt.last((List) this.pathPoint)).getLatitude(), ((QcLatLon) CollectionsKt.last((List) this.pathPoint)).getLongitude()), 18.0f));
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        if (this.binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        setStatusBarNoBackground();
        View[] viewArr = new View[1];
        ActivityGpsMapBinding activityGpsMapBinding = this.binding;
        if (activityGpsMapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityGpsMapBinding = null;
        }
        viewArr[0] = activityGpsMapBinding.imageBack;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.gps.GpsMapActivity.setupViews.2
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
                ActivityGpsMapBinding activityGpsMapBinding2 = GpsMapActivity.this.binding;
                if (activityGpsMapBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityGpsMapBinding2 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityGpsMapBinding2.imageBack)) {
                    GpsMapActivity.this.finish();
                }
            }
        });
        if (ActivityCompat.checkSelfPermission(this, Permission.ACCESS_FINE_LOCATION) != 0) {
            return;
        }
        checkPermission();
    }

    private final boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        Object systemService = getSystemService("power");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return ((PowerManager) systemService).isIgnoringBatteryOptimizations(getPackageName());
    }
}
