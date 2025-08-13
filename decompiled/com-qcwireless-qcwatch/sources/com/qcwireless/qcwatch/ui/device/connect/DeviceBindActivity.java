package com.qcwireless.qcwatch.ui.device.connect;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleAction;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityDeviceBindBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.connect.adapter.DeviceListAdapter;
import com.qcwireless.qcwatch.ui.device.connect.bean.SmartWatch;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DeviceBindActivity.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0004()*+B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001bH\u0014J\b\u0010\u001f\u001a\u00020\u001bH\u0014J\b\u0010 \u001a\u00020\u001bH\u0014J\b\u0010!\u001a\u00020\u001bH\u0002J\b\u0010\"\u001a\u00020\u001bH\u0002J\b\u0010#\u001a\u00020\u001bH\u0014J\b\u0010$\u001a\u00020\u001bH\u0002J\u0010\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u0011\u001a\u00060\u0012R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00060\u0018R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/connect/adapter/DeviceListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityDeviceBindBinding;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "connectFailNumber", "", "deviceViewModel", "Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindViewModel;", "getDeviceViewModel", "()Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindViewModel;", "deviceViewModel$delegate", "Lkotlin/Lazy;", "myBleReceiver", "Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity$MyReceiver;", "myHandler", "Landroid/os/Handler;", "popupWindow", "Landroid/widget/PopupWindow;", "runnable", "Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity$MyRunnable;", "scanSize", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "setAnim1", "setAnim2", "setupViews", "showBluetoothRestartDialog", "showPopMenu", "view", "Landroid/view/View;", "BluetoothPermissionCallback", "MyReceiver", "MyRunnable", "PermissionCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceBindActivity extends BaseActivity {
    private DeviceListAdapter adapter;
    private ActivityDeviceBindBinding binding;
    private BluetoothAdapter bluetoothAdapter;
    private int connectFailNumber;

    /* renamed from: deviceViewModel$delegate, reason: from kotlin metadata */
    private final Lazy deviceViewModel;
    private final MyReceiver myBleReceiver;
    private final Handler myHandler;
    private PopupWindow popupWindow;
    private final MyRunnable runnable;
    private int scanSize;

    /* JADX WARN: Multi-variable type inference failed */
    public DeviceBindActivity() {
        final DeviceBindActivity deviceBindActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.deviceViewModel = LazyKt.lazy(new Function0<DeviceBindViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.connect.DeviceBindViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceBindViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(deviceBindActivity, Reflection.getOrCreateKotlinClass(DeviceBindViewModel.class), qualifier, objArr);
            }
        });
        this.runnable = new MyRunnable();
        this.myBleReceiver = new MyReceiver();
        final Looper mainLooper = Looper.getMainLooper();
        this.myHandler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$myHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeviceBindViewModel getDeviceViewModel() {
        return (DeviceBindViewModel) this.deviceViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDeviceBindBinding activityDeviceBindBindingInflate = ActivityDeviceBindBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDeviceBindBindingInflate, "inflate(layoutInflater)");
        this.binding = activityDeviceBindBindingInflate;
        if (activityDeviceBindBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBindingInflate = null;
        }
        ConstraintLayout root = activityDeviceBindBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() throws NoSuchMethodException, SecurityException {
        super.onResume();
        try {
            if (!BluetoothUtils.isEnabledBluetooth(this)) {
                Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(this, Permission.BLUETOOTH_CONNECT) != 0) {
                    return;
                } else {
                    startActivityForResult(intent, 300);
                }
            }
        } catch (Exception unused) {
        }
        DeviceBindActivity deviceBindActivity = this;
        XLog.i(Boolean.valueOf(PermissionUtilKt.hasBluetooth(deviceBindActivity)));
        if (!PermissionUtilKt.hasBluetooth(deviceBindActivity)) {
            PermissionUtilKt.requestBluetoothPermission(deviceBindActivity, new BluetoothPermissionCallback());
        } else {
            PermissionUtilKt.requestLocationPermission(deviceBindActivity, new PermissionCallback());
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Intrinsics.checkNotNullExpressionValue(defaultAdapter, "getDefaultAdapter()");
        this.bluetoothAdapter = defaultAdapter;
        IntentFilter intentFilter = BleAction.getIntentFilter();
        Intrinsics.checkNotNullExpressionValue(intentFilter, "getIntentFilter()");
        LocalBroadcastManager.getInstance(QCApplication.INSTANCE.getCONTEXT()).registerReceiver(this.myBleReceiver, intentFilter);
        setAnim1();
        setAnim2();
        DeviceBindActivity deviceBindActivity = this;
        this.adapter = new DeviceListAdapter(deviceBindActivity, getDeviceViewModel().getDeviceList());
        ActivityDeviceBindBinding activityDeviceBindBinding = this.binding;
        ActivityDeviceBindBinding activityDeviceBindBinding2 = null;
        if (activityDeviceBindBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBinding = null;
        }
        activityDeviceBindBinding.deviceRcv.setLayoutManager(new LinearLayoutManager(deviceBindActivity));
        RecyclerView recyclerView = activityDeviceBindBinding.deviceRcv;
        DeviceListAdapter deviceListAdapter = this.adapter;
        if (deviceListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            deviceListAdapter = null;
        }
        recyclerView.setAdapter(deviceListAdapter);
        activityDeviceBindBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_9));
        ViewKt.visible(activityDeviceBindBinding.titleBar.tvRightText);
        activityDeviceBindBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.device_warming);
        activityDeviceBindBinding.titleBar.ivNavigateBefore.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindActivity.m395setupViews$lambda2$lambda0(this.f$0, view);
            }
        });
        activityDeviceBindBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindActivity.m396setupViews$lambda2$lambda1(this.f$0, view);
            }
        });
        DeviceListAdapter deviceListAdapter2 = this.adapter;
        if (deviceListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            deviceListAdapter2 = null;
        }
        deviceListAdapter2.notifyDataSetChanged();
        final DeviceBindViewModel deviceViewModel = getDeviceViewModel();
        deviceViewModel.getDeviceLiveData().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DeviceBindActivity.m397setupViews$lambda5$lambda4(deviceViewModel, this, (SmartWatch) obj);
            }
        });
        DeviceListAdapter deviceListAdapter3 = this.adapter;
        if (deviceListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            deviceListAdapter3 = null;
        }
        deviceListAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DeviceBindActivity.m398setupViews$lambda7$lambda6(this.f$0, baseQuickAdapter, view, i);
            }
        });
        View[] viewArr = new View[1];
        ActivityDeviceBindBinding activityDeviceBindBinding3 = this.binding;
        if (activityDeviceBindBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceBindBinding2 = activityDeviceBindBinding3;
        }
        viewArr[0] = activityDeviceBindBinding2.startScan;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity.setupViews.4
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
                DeviceBindActivity.this.getDeviceViewModel().getDeviceList().clear();
                DeviceListAdapter deviceListAdapter4 = DeviceBindActivity.this.adapter;
                if (deviceListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    deviceListAdapter4 = null;
                }
                deviceListAdapter4.notifyDataSetChanged();
                BleScannerHelper.getInstance().reSetCallback();
                List<SmartWatch> listAddSystemBondedDevice = DeviceBindActivity.this.getDeviceViewModel().addSystemBondedDevice();
                if (listAddSystemBondedDevice.size() > 0) {
                    DeviceBindActivity.this.getDeviceViewModel().getDeviceList().addAll(listAddSystemBondedDevice);
                }
                if (BluetoothUtils.isEnabledBluetooth(DeviceBindActivity.this)) {
                    DeviceBindActivity.this.scanSize = 0;
                    ActivityDeviceBindBinding activityDeviceBindBinding4 = DeviceBindActivity.this.binding;
                    if (activityDeviceBindBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityDeviceBindBinding4 = null;
                    }
                    activityDeviceBindBinding4.tvSearchStatus.setText(DeviceBindActivity.this.getString(R.string.qc_text_31));
                    BleScannerHelper.getInstance().setScanFilter(QJavaApplication.getInstance().getKeys());
                    BleScannerHelper.getInstance().scanDevice(QCApplication.INSTANCE.getCONTEXT(), null, DeviceBindActivity.this.getDeviceViewModel().getBleScanCallback());
                    DeviceBindActivity.this.myHandler.removeCallbacks(DeviceBindActivity.this.runnable);
                    DeviceBindActivity.this.myHandler.postDelayed(DeviceBindActivity.this.runnable, 15000L);
                    DeviceBindActivity.this.setAnim1();
                    DeviceBindActivity.this.setAnim2();
                    return;
                }
                if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(DeviceBindActivity.this, Permission.BLUETOOTH_CONNECT) == 0) {
                    Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                    Activity activity = DeviceBindActivity.this.getActivity();
                    Intrinsics.checkNotNull(activity);
                    activity.startActivityForResult(intent, 300);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-0, reason: not valid java name */
    public static final void m395setupViews$lambda2$lambda0(DeviceBindActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m396setupViews$lambda2$lambda1(DeviceBindActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceBindActivity deviceBindActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(deviceBindActivity, (Class<?>) DeviceConnectWarmingActivity.class);
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
        deviceBindActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5$lambda-4, reason: not valid java name */
    public static final void m397setupViews$lambda5$lambda4(DeviceBindViewModel this_apply, DeviceBindActivity this$0, SmartWatch it) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_apply.getDeviceList().contains(it)) {
            return;
        }
        this$0.scanSize++;
        List<SmartWatch> deviceList = this_apply.getDeviceList();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        deviceList.add(0, it);
        if (this_apply.getDeviceList().size() > 5 && this_apply.getDeviceList().size() % 5 == 0) {
            List<SmartWatch> deviceList2 = this_apply.getDeviceList();
            if (deviceList2.size() > 1) {
                CollectionsKt.sortWith(deviceList2, new Comparator() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$setupViews$lambda-5$lambda-4$$inlined$sortByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((SmartWatch) t2).getRssi()), Integer.valueOf(((SmartWatch) t).getRssi()));
                    }
                });
            }
        }
        DeviceListAdapter deviceListAdapter = this$0.adapter;
        ActivityDeviceBindBinding activityDeviceBindBinding = null;
        if (deviceListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            deviceListAdapter = null;
        }
        deviceListAdapter.notifyDataSetChanged();
        if (this$0.scanSize > 20) {
            ActivityDeviceBindBinding activityDeviceBindBinding2 = this$0.binding;
            if (activityDeviceBindBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceBindBinding2 = null;
            }
            activityDeviceBindBinding2.tvSearchStatus.setText(this$0.getString(R.string.qc_text_32));
            ActivityDeviceBindBinding activityDeviceBindBinding3 = this$0.binding;
            if (activityDeviceBindBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceBindBinding3 = null;
            }
            activityDeviceBindBinding3.ivWave1.clearAnimation();
            ActivityDeviceBindBinding activityDeviceBindBinding4 = this$0.binding;
            if (activityDeviceBindBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDeviceBindBinding = activityDeviceBindBinding4;
            }
            activityDeviceBindBinding.ivWave2.clearAnimation();
            BleScannerHelper.getInstance().stopScan(QCApplication.INSTANCE.getCONTEXT());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7$lambda-6, reason: not valid java name */
    public static final void m398setupViews$lambda7$lambda6(DeviceBindActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.myHandler.removeCallbacks(this$0.runnable);
        SmartWatch smartWatch = this$0.getDeviceViewModel().getDeviceList().get(i);
        XLog.i(smartWatch);
        this$0.showLoadingDialog();
        UserConfig.INSTANCE.getInstance().setGpsPermission(new DateUtil().getUnixTimestamp() + 300);
        UserConfig.INSTANCE.getInstance().save();
        BleOperateManager.getInstance().connectDirectly(smartWatch.getDeviceAddress());
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActivityDeviceBindBinding activityDeviceBindBinding = DeviceBindActivity.this.binding;
            ActivityDeviceBindBinding activityDeviceBindBinding2 = null;
            if (activityDeviceBindBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceBindBinding = null;
            }
            activityDeviceBindBinding.tvSearchStatus.setText(DeviceBindActivity.this.getString(R.string.qc_text_32));
            ActivityDeviceBindBinding activityDeviceBindBinding3 = DeviceBindActivity.this.binding;
            if (activityDeviceBindBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceBindBinding3 = null;
            }
            activityDeviceBindBinding3.ivWave1.clearAnimation();
            ActivityDeviceBindBinding activityDeviceBindBinding4 = DeviceBindActivity.this.binding;
            if (activityDeviceBindBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDeviceBindBinding4 = null;
            }
            activityDeviceBindBinding4.ivWave2.clearAnimation();
            BleScannerHelper.getInstance().stopScan(QCApplication.INSTANCE.getCONTEXT());
            if (DeviceBindActivity.this.scanSize <= 0) {
                try {
                    if (DeviceBindActivity.this.isFinishing() || !(ActivityUtils.getTopActivity() instanceof DeviceBindActivity)) {
                        return;
                    }
                    DeviceBindActivity deviceBindActivity = DeviceBindActivity.this;
                    ActivityDeviceBindBinding activityDeviceBindBinding5 = deviceBindActivity.binding;
                    if (activityDeviceBindBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityDeviceBindBinding2 = activityDeviceBindBinding5;
                    }
                    TextView textView = activityDeviceBindBinding2.titleBar.tvRightText;
                    Intrinsics.checkNotNullExpressionValue(textView, "binding.titleBar.tvRightText");
                    deviceBindActivity.showPopMenu(textView);
                } catch (Exception unused) {
                }
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            PopupWindow popupWindow2 = null;
            if (popupWindow == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
                popupWindow = null;
            }
            if (popupWindow.isShowing()) {
                PopupWindow popupWindow3 = this.popupWindow;
                if (popupWindow3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
                } else {
                    popupWindow2 = popupWindow3;
                }
                popupWindow2.dismiss();
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.myHandler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                DeviceBindActivity.m394onDestroy$lambda8();
            }
        }, 1000L);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.myBleReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onDestroy$lambda-8, reason: not valid java name */
    public static final void m394onDestroy$lambda8() {
        CommandHandle.getInstance().executeReqCmdNoCallback(new SimpleKeyReq((byte) 16));
        BleScannerHelper.getInstance().stopScan(QCApplication.INSTANCE.getCONTEXT());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnim1() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f, 1, 0.5f, 1, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
        scaleAnimation.setDuration(800L);
        scaleAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatCount(-1);
        animationSet.setDuration(800L);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        ActivityDeviceBindBinding activityDeviceBindBinding = this.binding;
        if (activityDeviceBindBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBinding = null;
        }
        activityDeviceBindBinding.ivWave1.startAnimation(animationSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnim2() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.3f, 1.5f, 1.3f, 1.5f, 1, 0.5f, 1, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 0.1f);
        scaleAnimation.setDuration(800L);
        scaleAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatCount(-1);
        animationSet.setDuration(800L);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        ActivityDeviceBindBinding activityDeviceBindBinding = this.binding;
        if (activityDeviceBindBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDeviceBindBinding = null;
        }
        activityDeviceBindBinding.ivWave2.startAnimation(animationSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showBluetoothRestartDialog() {
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_bluetooth_warming);
        final CenterDialog centerDialogCreate = builder.create();
        centerDialogCreate.show();
        View contentView = centerDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        ((TextView) contentView.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceBindActivity.m399showBluetoothRestartDialog$lambda9(centerDialogCreate, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showBluetoothRestartDialog$lambda-9, reason: not valid java name */
    public static final void m399showBluetoothRestartDialog$lambda9(CenterDialog centerDialog, DeviceBindActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        centerDialog.dismiss();
        if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(this$0, Permission.BLUETOOTH_CONNECT) == 0) {
            BluetoothAdapter bluetoothAdapter = this$0.bluetoothAdapter;
            if (bluetoothAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bluetoothAdapter");
                bluetoothAdapter = null;
            }
            bluetoothAdapter.disable();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPopMenu(View view) {
        DeviceBindActivity deviceBindActivity = this;
        ActivityDeviceBindBinding activityDeviceBindBinding = null;
        View viewInflate = LayoutInflater.from(deviceBindActivity).inflate(R.layout.popwindow_layout_scan, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "from(this).inflate(\n    …yout_scan, null\n        )");
        this.popupWindow = new PopupWindow(viewInflate, (int) GlobalKt.dp2px(deviceBindActivity, 120.0f), -2, true);
        ((TextView) viewInflate.findViewById(R.id.tv_add)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DeviceBindActivity.m400showPopMenu$lambda10(this.f$0, view2);
            }
        });
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow = null;
        }
        popupWindow.setTouchable(true);
        int width = view.getWidth();
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow2 = null;
        }
        popupWindow2.dismiss();
        PopupWindow popupWindow3 = this.popupWindow;
        if (popupWindow3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow3 = null;
        }
        ActivityDeviceBindBinding activityDeviceBindBinding2 = this.binding;
        if (activityDeviceBindBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDeviceBindBinding = activityDeviceBindBinding2;
        }
        popupWindow3.showAsDropDown(activityDeviceBindBinding.titleBar.tvRightText, (-((int) GlobalKt.dp2px(deviceBindActivity, 120.0f))) + width, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPopMenu$lambda-10, reason: not valid java name */
    public static final void m400showPopMenu$lambda10(DeviceBindActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PopupWindow popupWindow = this$0.popupWindow;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow = null;
        }
        popupWindow.dismiss();
        DeviceBindActivity deviceBindActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(deviceBindActivity, (Class<?>) DeviceConnectWarmingActivity.class);
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
        deviceBindActivity.startActivity(intent);
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity$MyReceiver;", "Lcom/oudmon/ble/base/bluetooth/QCBluetoothCallbackCloneReceiver;", "(Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity;)V", "bleStatus", "", NotificationCompat.CATEGORY_STATUS, "", "newState", "connectStatue", "device", "Landroid/bluetooth/BluetoothDevice;", "connected", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyReceiver extends QCBluetoothCallbackCloneReceiver {
        public MyReceiver() {
        }

        @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
        public void connectStatue(BluetoothDevice device, boolean connected) {
            if (connected) {
                DeviceBindActivity.this.connectFailNumber = 0;
                DeviceBindActivity.this.dismissLoadingDialog();
                DeviceBindActivity.this.finish();
            }
        }

        @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
        public void bleStatus(int status, int newState) {
            super.bleStatus(status, newState);
            if (status > 0) {
                DeviceBindActivity.this.connectFailNumber++;
                if (DeviceBindActivity.this.connectFailNumber > 4) {
                    DeviceBindActivity.this.showBluetoothRestartDialog();
                }
            }
        }
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity$PermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PermissionCallback implements OnPermissionCallback {
        public PermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            ActivityDeviceBindBinding activityDeviceBindBinding = null;
            if (all) {
                ActivityDeviceBindBinding activityDeviceBindBinding2 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDeviceBindBinding = activityDeviceBindBinding2;
                }
                activityDeviceBindBinding.startScan.performClick();
                return;
            }
            String string = DeviceBindActivity.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                String string = DeviceBindActivity.this.getString(R.string.qc_text_386);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_386)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity((Activity) DeviceBindActivity.this, permissions);
            }
        }
    }

    /* compiled from: DeviceBindActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity$BluetoothPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class BluetoothPermissionCallback implements OnPermissionCallback {
        public BluetoothPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            ActivityDeviceBindBinding activityDeviceBindBinding = null;
            if (all) {
                ActivityDeviceBindBinding activityDeviceBindBinding2 = DeviceBindActivity.this.binding;
                if (activityDeviceBindBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDeviceBindBinding = activityDeviceBindBinding2;
                }
                activityDeviceBindBinding.startScan.performClick();
                return;
            }
            String string = DeviceBindActivity.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                DeviceBindActivity.this.finish();
                XXPermissions.startPermissionActivity((Activity) DeviceBindActivity.this, permissions);
                String string = DeviceBindActivity.this.getString(R.string.qc_text_458);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_458)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
        }
    }
}
