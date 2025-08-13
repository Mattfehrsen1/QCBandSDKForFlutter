package com.qcwireless.qcwatch.ui.device;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.SimpleKeyReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.BatteryRsp;
import com.oudmon.ble.base.util.AppUtil;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.dialog.BottomListDialog;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.event.BatteryLowEvent;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.DataSyncEvent;
import com.qcwireless.qcwatch.base.event.DevTestEvent;
import com.qcwireless.qcwatch.base.event.DeviceToAppSyncEvent;
import com.qcwireless.qcwatch.base.event.FirmCheckEvent;
import com.qcwireless.qcwatch.base.event.FirmUpdateEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.MessagePermissionEvent;
import com.qcwireless.qcwatch.base.event.RefreshAiAnalyzeEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceDownloadEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.AndroidVersion;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentDeviceBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.base.util.LocationUtils;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSwitchView;
import com.qcwireless.qcwatch.ui.device.DeviceFragment.CameraPermissionCallback;
import com.qcwireless.qcwatch.ui.device.album.AlbumActivity;
import com.qcwireless.qcwatch.ui.device.camera.CameraActivity;
import com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity;
import com.qcwireless.qcwatch.ui.device.connect.DeviceConnectWarmingActivity;
import com.qcwireless.qcwatch.ui.device.contact.ContactActivity;
import com.qcwireless.qcwatch.ui.device.dfu.ChinaDeviceFirmwareUpdateActivity;
import com.qcwireless.qcwatch.ui.device.dfu.DeviceFirmwareUpdateActivity;
import com.qcwireless.qcwatch.ui.device.disturb.DisturbActivity;
import com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity;
import com.qcwireless.qcwatch.ui.device.forward.CallForwardingActivity;
import com.qcwireless.qcwatch.ui.device.more.MoreSettingActivity;
import com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity;
import com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity;
import com.qcwireless.qcwatch.ui.device.push.message.MessagePushActivity;
import com.qcwireless.qcwatch.ui.device.quicksms.QuickSmsActivity;
import com.qcwireless.qcwatch.ui.device.record.RecordListActivity;
import com.qcwireless.qcwatch.ui.device.remind.AiReminderActivity;
import com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel;
import com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
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
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: DeviceFragment.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u0000 A2\u00020\u0001:\u0006>?@ABCB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0002J\b\u0010\u001b\u001a\u00020\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\"\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J&\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u00020\u0019H\u0016J\u0010\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u000200H\u0007J\b\u00101\u001a\u00020\u0019H\u0016J\b\u00102\u001a\u00020\u0019H\u0003J\b\u00103\u001a\u00020\u0019H\u0003J\b\u00104\u001a\u00020\u0019H\u0002J\u0016\u00105\u001a\u00020\u00192\f\u00106\u001a\b\u0012\u0004\u0012\u00020807H\u0002J\b\u00109\u001a\u00020\u0019H\u0002J\u0006\u0010:\u001a\u00020\u0019J\b\u0010;\u001a\u00020\u0019H\u0002J\u0006\u0010<\u001a\u00020\u0019J\u0006\u0010=\u001a\u00020\u0019R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00060\u0017R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "batteryDialog", "Lcom/qcwireless/qcwatch/base/dialog/CenterDialog;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentDeviceBinding;", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "deviceSettingVm", "Lcom/qcwireless/qcwatch/ui/device/vm/DeviceSettingViewModel;", "getDeviceSettingVm", "()Lcom/qcwireless/qcwatch/ui/device/vm/DeviceSettingViewModel;", "deviceSettingVm$delegate", "Lkotlin/Lazy;", "deviceViewModel", "Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel;", "getDeviceViewModel", "()Lcom/qcwireless/qcwatch/ui/device/vm/DeviceViewModel;", "deviceViewModel$delegate", "handler", "Landroid/os/Handler;", "myRunnable", "Lcom/qcwireless/qcwatch/ui/device/DeviceFragment$MyRunnable;", "initDeviceSetting", "", "it", "initSupportFunction", "isNotificationListenerEnabled", "", "loadDataOnce", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "readBattery", "refreshUI", "showNotificationDialog", "showPermissionWarmingDialog", "permissions", "", "", "showRingBatteryLowDialog", "showTimeFormatDialog", "showUnbindDialog", "showUnitDialog", "showWeatherDialog", "BluetoothPermissionCallback", "CallPermissionCallback", "CameraPermissionCallback", "Companion", "LocationPermissionCallback", "MyRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CenterDialog batteryDialog;
    private FragmentDeviceBinding binding;
    private DeviceSetting deviceSetting;

    /* renamed from: deviceSettingVm$delegate, reason: from kotlin metadata */
    private final Lazy deviceSettingVm;

    /* renamed from: deviceViewModel$delegate, reason: from kotlin metadata */
    private final Lazy deviceViewModel;
    private final Handler handler;
    private final MyRunnable myRunnable;

    /* JADX WARN: Multi-variable type inference failed */
    public DeviceFragment() {
        final DeviceFragment deviceFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.deviceViewModel = LazyKt.lazy(new Function0<DeviceViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.vm.DeviceViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(deviceFragment, Reflection.getOrCreateKotlinClass(DeviceViewModel.class), qualifier, objArr);
            }
        });
        final Object[] objArr2 = 0 == true ? 1 : 0;
        final Object[] objArr3 = 0 == true ? 1 : 0;
        this.deviceSettingVm = LazyKt.lazy(new Function0<DeviceSettingViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.vm.DeviceSettingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DeviceSettingViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(deviceFragment, Reflection.getOrCreateKotlinClass(DeviceSettingViewModel.class), objArr2, objArr3);
            }
        });
        this.myRunnable = new MyRunnable();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeviceViewModel getDeviceViewModel() {
        return (DeviceViewModel) this.deviceViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeviceSettingViewModel getDeviceSettingVm() {
        return (DeviceSettingViewModel) this.deviceSettingVm.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDeviceBinding fragmentDeviceBindingInflate = FragmentDeviceBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentDeviceBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentDeviceBindingInflate;
        EventBus.getDefault().register(this);
        FragmentDeviceBinding fragmentDeviceBinding = this.binding;
        if (fragmentDeviceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding = null;
        }
        return fragmentDeviceBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        try {
            if (!BluetoothUtils.isEnabledBluetooth(getActivity())) {
                Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(getActivity(), Permission.BLUETOOTH_CONNECT) != 0) {
                    return;
                } else {
                    getActivity().startActivityForResult(intent, 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentDeviceBinding fragmentDeviceBinding = null;
        if (BleOperateManager.getInstance().isConnected()) {
            if (SyncStatus.INSTANCE.getGetInstance().getSync()) {
                FragmentDeviceBinding fragmentDeviceBinding2 = this.binding;
                if (fragmentDeviceBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding2 = null;
                }
                ViewKt.visible(fragmentDeviceBinding2.disconnectView);
            } else {
                FragmentDeviceBinding fragmentDeviceBinding3 = this.binding;
                if (fragmentDeviceBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding3 = null;
                }
                ViewKt.gone(fragmentDeviceBinding3.disconnectView);
            }
            FragmentDeviceBinding fragmentDeviceBinding4 = this.binding;
            if (fragmentDeviceBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding4 = null;
            }
            ViewKt.gone(fragmentDeviceBinding4.imageWarming);
        } else {
            FragmentDeviceBinding fragmentDeviceBinding5 = this.binding;
            if (fragmentDeviceBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding5 = null;
            }
            ViewKt.visible(fragmentDeviceBinding5.disconnectView);
            FragmentDeviceBinding fragmentDeviceBinding6 = this.binding;
            if (fragmentDeviceBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding6 = null;
            }
            fragmentDeviceBinding6.tvDeviceBattery.setText("--");
            ThreadManager.getInstance().wakeUp();
            FragmentDeviceBinding fragmentDeviceBinding7 = this.binding;
            if (fragmentDeviceBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding7 = null;
            }
            ViewKt.visible(fragmentDeviceBinding7.imageWarming);
        }
        try {
            boolean zIsNotificationListenerEnabled = isNotificationListenerEnabled();
            if (UserConfig.INSTANCE.getInstance().getMessagePushEnable() && zIsNotificationListenerEnabled) {
                FragmentDeviceBinding fragmentDeviceBinding8 = this.binding;
                if (fragmentDeviceBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding8 = null;
                }
                fragmentDeviceBinding8.qcSettingMessage.setRightText(getString(R.string.qc_text_22));
                FragmentDeviceBinding fragmentDeviceBinding9 = this.binding;
                if (fragmentDeviceBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding = fragmentDeviceBinding9;
                }
                fragmentDeviceBinding.qcSettingMessage.setErrorIcon(false);
                return;
            }
            FragmentDeviceBinding fragmentDeviceBinding10 = this.binding;
            if (fragmentDeviceBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding10 = null;
            }
            fragmentDeviceBinding10.qcSettingMessage.setErrorIcon(true);
            FragmentDeviceBinding fragmentDeviceBinding11 = this.binding;
            if (fragmentDeviceBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding11;
            }
            fragmentDeviceBinding.qcSettingMessage.setRightText(getString(R.string.qc_text_21));
        } catch (Exception unused) {
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() throws InterruptedException {
        super.loadDataOnce();
        refreshUI();
        FragmentDeviceBinding fragmentDeviceBinding = null;
        if (BleOperateManager.getInstance().isConnected()) {
            FragmentDeviceBinding fragmentDeviceBinding2 = this.binding;
            if (fragmentDeviceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding2 = null;
            }
            ViewKt.visible(fragmentDeviceBinding2.imageWarming);
        } else {
            FragmentDeviceBinding fragmentDeviceBinding3 = this.binding;
            if (fragmentDeviceBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding3 = null;
            }
            ViewKt.gone(fragmentDeviceBinding3.imageWarming);
        }
        FragmentDeviceBinding fragmentDeviceBinding4 = this.binding;
        if (fragmentDeviceBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding4 = null;
        }
        fragmentDeviceBinding4.syncDeviceRefresh.setOnRefreshListener(new OnRefreshListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda8
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) throws InterruptedException {
                DeviceFragment.m364loadDataOnce$lambda0(this.f$0, refreshLayout);
            }
        });
        DeviceViewModel deviceViewModel = getDeviceViewModel();
        deviceViewModel.init();
        DeviceFragment deviceFragment = this;
        deviceViewModel.getUiState().observe(deviceFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda17
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DeviceFragment.m365loadDataOnce$lambda2$lambda1(this.f$0, (DeviceViewModel.DeviceFragmentUI) obj);
            }
        });
        getDeviceSettingVm().getUiState().observe(deviceFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda16
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DeviceFragment.m366loadDataOnce$lambda3(this.f$0, (DeviceSetting) obj);
            }
        });
        View[] viewArr = new View[6];
        FragmentDeviceBinding fragmentDeviceBinding5 = this.binding;
        if (fragmentDeviceBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding5 = null;
        }
        viewArr[0] = fragmentDeviceBinding5.btnDeviceAdd;
        FragmentDeviceBinding fragmentDeviceBinding6 = this.binding;
        if (fragmentDeviceBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding6 = null;
        }
        viewArr[1] = fragmentDeviceBinding6.tvDeviceUnbind;
        FragmentDeviceBinding fragmentDeviceBinding7 = this.binding;
        if (fragmentDeviceBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding7 = null;
        }
        viewArr[2] = fragmentDeviceBinding7.disconnectView;
        FragmentDeviceBinding fragmentDeviceBinding8 = this.binding;
        if (fragmentDeviceBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding8 = null;
        }
        viewArr[3] = fragmentDeviceBinding8.btnFirmwareUpdate;
        FragmentDeviceBinding fragmentDeviceBinding9 = this.binding;
        if (fragmentDeviceBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding9 = null;
        }
        viewArr[4] = fragmentDeviceBinding9.imageWarming;
        FragmentDeviceBinding fragmentDeviceBinding10 = this.binding;
        if (fragmentDeviceBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding10 = null;
        }
        viewArr[5] = fragmentDeviceBinding10.btnAddDevice;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment.loadDataOnce.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws NoSuchMethodException, SecurityException {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) throws NoSuchMethodException, SecurityException {
                boolean zAreEqual;
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                FragmentDeviceBinding fragmentDeviceBinding11 = DeviceFragment.this.binding;
                FragmentDeviceBinding fragmentDeviceBinding12 = null;
                if (fragmentDeviceBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding11 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, fragmentDeviceBinding11.disconnectView)) {
                    FragmentDeviceBinding fragmentDeviceBinding13 = DeviceFragment.this.binding;
                    if (fragmentDeviceBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentDeviceBinding13 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, fragmentDeviceBinding13.btnDeviceAdd)) {
                        zAreEqual = true;
                    } else {
                        FragmentDeviceBinding fragmentDeviceBinding14 = DeviceFragment.this.binding;
                        if (fragmentDeviceBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentDeviceBinding14 = null;
                        }
                        zAreEqual = Intrinsics.areEqual(setOnClickListener, fragmentDeviceBinding14.btnAddDevice);
                    }
                    if (!zAreEqual) {
                        FragmentDeviceBinding fragmentDeviceBinding15 = DeviceFragment.this.binding;
                        if (fragmentDeviceBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentDeviceBinding15 = null;
                        }
                        if (!Intrinsics.areEqual(setOnClickListener, fragmentDeviceBinding15.tvDeviceUnbind)) {
                            FragmentDeviceBinding fragmentDeviceBinding16 = DeviceFragment.this.binding;
                            if (fragmentDeviceBinding16 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentDeviceBinding16 = null;
                            }
                            if (!Intrinsics.areEqual(setOnClickListener, fragmentDeviceBinding16.imageWarming)) {
                                FragmentDeviceBinding fragmentDeviceBinding17 = DeviceFragment.this.binding;
                                if (fragmentDeviceBinding17 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    fragmentDeviceBinding17 = null;
                                }
                                if (Intrinsics.areEqual(setOnClickListener, fragmentDeviceBinding17.btnFirmwareUpdate)) {
                                    String battery = UserConfig.INSTANCE.getInstance().getBattery();
                                    Integer numValueOf = battery != null ? Integer.valueOf(Integer.parseInt(battery)) : null;
                                    Intrinsics.checkNotNull(numValueOf);
                                    if (numValueOf.intValue() < 10) {
                                        String string = DeviceFragment.this.getString(R.string.qc_text_524);
                                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_524)");
                                        GlobalKt.showToast$default(string, 0, 1, null);
                                        return;
                                    }
                                    if (BleOperateManager.getInstance().isConnected()) {
                                        String language = Locale.getDefault().getLanguage();
                                        Intrinsics.checkNotNullExpressionValue(language, "language");
                                        if (StringsKt.startsWith$default(language, "zh", false, 2, (Object) null)) {
                                            if (!NetWorkUtils.INSTANCE.isNetworkAvailable(DeviceFragment.this.getActivity())) {
                                                String string2 = DeviceFragment.this.getString(R.string.qc_text_223);
                                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_223)");
                                                GlobalKt.showToast$default(string2, 0, 1, null);
                                                return;
                                            }
                                            DeviceFragment deviceFragment2 = DeviceFragment.this;
                                            FragmentActivity it = deviceFragment2.getActivity();
                                            if (it != null) {
                                                ArrayList<Pair> arrayList = new ArrayList();
                                                Intrinsics.checkNotNullExpressionValue(it, "it");
                                                Intent intent = new Intent(it, (Class<?>) ChinaDeviceFirmwareUpdateActivity.class);
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
                                                Unit unit2 = Unit.INSTANCE;
                                                Unit unit3 = Unit.INSTANCE;
                                                Unit unit4 = Unit.INSTANCE;
                                                deviceFragment2.startActivity(intent);
                                                Unit unit5 = Unit.INSTANCE;
                                                Unit unit6 = Unit.INSTANCE;
                                                return;
                                            }
                                            return;
                                        }
                                        DeviceFragment deviceFragment3 = DeviceFragment.this;
                                        FragmentActivity it2 = deviceFragment3.getActivity();
                                        if (it2 != null) {
                                            ArrayList<Pair> arrayList2 = new ArrayList();
                                            Intrinsics.checkNotNullExpressionValue(it2, "it");
                                            Intent intent2 = new Intent(it2, (Class<?>) DeviceFirmwareUpdateActivity.class);
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
                                                        Unit unit7 = Unit.INSTANCE;
                                                    }
                                                }
                                            }
                                            Unit unit8 = Unit.INSTANCE;
                                            Unit unit9 = Unit.INSTANCE;
                                            Unit unit10 = Unit.INSTANCE;
                                            deviceFragment3.startActivity(intent2);
                                            Unit unit11 = Unit.INSTANCE;
                                            Unit unit12 = Unit.INSTANCE;
                                            return;
                                        }
                                        return;
                                    }
                                    String string3 = DeviceFragment.this.getString(R.string.qc_text_75);
                                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_75)");
                                    GlobalKt.showToast$default(string3, 0, 1, null);
                                    return;
                                }
                                return;
                            }
                            DeviceFragment deviceFragment4 = DeviceFragment.this;
                            FragmentActivity it3 = deviceFragment4.getActivity();
                            if (it3 != null) {
                                ArrayList<Pair> arrayList3 = new ArrayList();
                                Intrinsics.checkNotNullExpressionValue(it3, "it");
                                Intent intent3 = new Intent(it3, (Class<?>) DeviceConnectWarmingActivity.class);
                                for (Pair pair3 : arrayList3) {
                                    if (pair3 != null) {
                                        String str3 = (String) pair3.getFirst();
                                        Object second3 = pair3.getSecond();
                                        if (second3 instanceof Integer) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).intValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof Byte) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).byteValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof Character) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Character) second3).charValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof Short) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).shortValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof Boolean) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Boolean) second3).booleanValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof Long) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).longValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof Float) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).floatValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof Double) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, ((Number) second3).doubleValue()), "putExtra(name, value)");
                                        } else if (second3 instanceof String) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (String) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof CharSequence) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (CharSequence) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof Parcelable) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof Object[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof ArrayList) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof Serializable) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Serializable) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof boolean[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (boolean[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof byte[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (byte[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof short[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (short[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof char[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (char[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof int[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (int[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof long[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (long[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof float[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (float[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof double[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (double[]) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof Bundle) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Bundle) second3), "putExtra(name, value)");
                                        } else if (second3 instanceof Intent) {
                                            Intrinsics.checkNotNullExpressionValue(intent3.putExtra(str3, (Parcelable) second3), "putExtra(name, value)");
                                        } else {
                                            Unit unit13 = Unit.INSTANCE;
                                        }
                                    }
                                }
                                Unit unit14 = Unit.INSTANCE;
                                Unit unit15 = Unit.INSTANCE;
                                Unit unit16 = Unit.INSTANCE;
                                deviceFragment4.startActivity(intent3);
                                Unit unit17 = Unit.INSTANCE;
                                Unit unit18 = Unit.INSTANCE;
                                return;
                            }
                            return;
                        }
                        if (AndroidVersion.isAndroid12()) {
                            PermissionUtilKt.requestBluetoothPermission((FragmentActivity) DeviceFragment.this.getActivity(), DeviceFragment.this.new BluetoothPermissionCallback());
                            return;
                        } else if (ActivityCompat.checkSelfPermission(DeviceFragment.this.getActivity(), Permission.BLUETOOTH_CONNECT) == 0) {
                            DeviceFragment.this.showUnbindDialog();
                            return;
                        } else {
                            PermissionUtilKt.requestBluetoothPermission((FragmentActivity) DeviceFragment.this.getActivity(), DeviceFragment.this.new BluetoothPermissionCallback());
                            return;
                        }
                    }
                    if (LocationUtils.isGPSEnable(DeviceFragment.this.getActivity())) {
                        if (StringsKt.equals(Build.BRAND, "oppo", true)) {
                            if (AndroidVersion.isAndroid12()) {
                                DeviceFragment deviceFragment5 = DeviceFragment.this;
                                FragmentActivity it4 = deviceFragment5.getActivity();
                                if (it4 != null) {
                                    ArrayList<Pair> arrayList4 = new ArrayList();
                                    Intrinsics.checkNotNullExpressionValue(it4, "it");
                                    Intent intent4 = new Intent(it4, (Class<?>) DeviceBindActivity.class);
                                    for (Pair pair4 : arrayList4) {
                                        if (pair4 != null) {
                                            String str4 = (String) pair4.getFirst();
                                            Object second4 = pair4.getSecond();
                                            if (second4 instanceof Integer) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).intValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof Byte) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).byteValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof Character) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Character) second4).charValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof Short) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).shortValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof Boolean) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Boolean) second4).booleanValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof Long) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).longValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof Float) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).floatValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof Double) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, ((Number) second4).doubleValue()), "putExtra(name, value)");
                                            } else if (second4 instanceof String) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (String) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof CharSequence) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (CharSequence) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof Parcelable) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Parcelable) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof Object[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof ArrayList) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof Serializable) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Serializable) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof boolean[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (boolean[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof byte[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (byte[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof short[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (short[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof char[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (char[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof int[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (int[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof long[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (long[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof float[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (float[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof double[]) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (double[]) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof Bundle) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Bundle) second4), "putExtra(name, value)");
                                            } else if (second4 instanceof Intent) {
                                                Intrinsics.checkNotNullExpressionValue(intent4.putExtra(str4, (Parcelable) second4), "putExtra(name, value)");
                                            } else {
                                                Unit unit19 = Unit.INSTANCE;
                                            }
                                        }
                                    }
                                    Unit unit20 = Unit.INSTANCE;
                                    Unit unit21 = Unit.INSTANCE;
                                    Unit unit22 = Unit.INSTANCE;
                                    deviceFragment5.startActivity(intent4);
                                    Unit unit23 = Unit.INSTANCE;
                                    Unit unit24 = Unit.INSTANCE;
                                    return;
                                }
                                return;
                            }
                            PermissionUtilKt.requestLocationPermission((FragmentActivity) DeviceFragment.this.getActivity(), DeviceFragment.this.new LocationPermissionCallback());
                            return;
                        }
                        PermissionUtilKt.requestLocationPermission((FragmentActivity) DeviceFragment.this.getActivity(), DeviceFragment.this.new LocationPermissionCallback());
                        return;
                    }
                    String string4 = DeviceFragment.this.getString(R.string.qc_text_291);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_291)");
                    GlobalKt.showToast$default(string4, 0, 1, null);
                    DeviceFragment.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                    return;
                }
                if (SyncStatus.INSTANCE.getGetInstance().getSync()) {
                    String string5 = DeviceFragment.this.getString(R.string.qc_text_236);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_236)");
                    GlobalKt.showToast$default(string5, 0, 1, null);
                    return;
                }
                if (BleOperateManager.getInstance().isConnected()) {
                    FragmentDeviceBinding fragmentDeviceBinding18 = DeviceFragment.this.binding;
                    if (fragmentDeviceBinding18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentDeviceBinding12 = fragmentDeviceBinding18;
                    }
                    ViewKt.gone(fragmentDeviceBinding12.disconnectView);
                    try {
                        if (BluetoothUtils.isEnabledBluetooth(DeviceFragment.this.getActivity())) {
                            return;
                        }
                        Intent intent5 = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                        if (ActivityCompat.checkSelfPermission(DeviceFragment.this.getActivity(), Permission.BLUETOOTH_CONNECT) != 0) {
                            return;
                        }
                        DeviceFragment.this.getActivity().startActivityForResult(intent5, 300);
                        return;
                    } catch (Exception unused) {
                        return;
                    }
                }
                String string6 = DeviceFragment.this.getString(R.string.qc_text_75);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_75)");
                GlobalKt.showToast$default(string6, 0, 1, null);
            }
        });
        final FragmentDeviceBinding fragmentDeviceBinding11 = this.binding;
        if (fragmentDeviceBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding11 = null;
        }
        fragmentDeviceBinding11.qcSettingCall.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda15
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) throws NoSuchMethodException, SecurityException {
                DeviceFragment.m367loadDataOnce$lambda7$lambda4(this.f$0, fragmentDeviceBinding11, compoundButton, z);
            }
        });
        fragmentDeviceBinding11.qcSettingMessage.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                if (!this.this$0.isNotificationListenerEnabled()) {
                    this.this$0.showNotificationDialog();
                    return;
                }
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) MessagePushActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingForward.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) CallForwardingActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingFastSms.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) QuickSmsActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingAiPush.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$5
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) AiReminderActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingDisturb.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$6
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) DisturbActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingContact.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$7
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) ContactActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingAlbum.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$8
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) AlbumActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingMusic.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$9
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                if (UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                    DeviceFragment deviceFragment2 = this.this$0;
                    FragmentActivity it = deviceFragment2.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) JieLiMusicManagerActivity.class);
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
                        deviceFragment2.startActivity(intent);
                        return;
                    }
                    return;
                }
                DeviceFragment deviceFragment3 = this.this$0;
                FragmentActivity it2 = deviceFragment3.getActivity();
                if (it2 != null) {
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    Intent intent2 = new Intent(it2, (Class<?>) MusicManagerActivity.class);
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
                    deviceFragment3.startActivity(intent2);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingEbook.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$10
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) EbookManagerActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        fragmentDeviceBinding11.qcSettingRecord.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$11
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) RecordListActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        FragmentDeviceBinding fragmentDeviceBinding12 = this.binding;
        if (fragmentDeviceBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDeviceBinding = fragmentDeviceBinding12;
        }
        fragmentDeviceBinding.qcSettingBatteryWarming.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda14
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DeviceFragment.m368loadDataOnce$lambda7$lambda5(this.f$0, compoundButton, z);
            }
        });
        fragmentDeviceBinding11.qcSettingGesture.setQSettingItemCheckListener(new QSwitchView.OnSwitchStateChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda7
            @Override // com.qcwireless.qcwatch.ui.base.view.QSwitchView.OnSwitchStateChangeListener
            public final void onSwitchStateChange(boolean z) {
                DeviceFragment.m369loadDataOnce$lambda7$lambda6(this.f$0, z);
            }
        });
        fragmentDeviceBinding11.qcSettingFindWatch.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$14
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.getDeviceSettingVm().findWatch();
                String string = this.this$0.getString(R.string.qc_text_270);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_270)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
        });
        fragmentDeviceBinding11.qcSettingCamera.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$15
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) throws NoSuchMethodException, SecurityException {
                PermissionUtilKt.requestCameraPermission((FragmentActivity) this.this$0.getActivity(), this.this$0.new CameraPermissionCallback());
            }
        });
        fragmentDeviceBinding11.qcSettingWeather.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$16
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showWeatherDialog();
            }
        });
        fragmentDeviceBinding11.qcSettingUnit.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$17
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showUnitDialog();
            }
        });
        fragmentDeviceBinding11.qcSettingTime.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$18
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                this.this$0.showTimeFormatDialog();
            }
        });
        fragmentDeviceBinding11.qcSettingMore.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$loadDataOnce$5$19
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                DeviceFragment deviceFragment2 = this.this$0;
                FragmentActivity it = deviceFragment2.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) MoreSettingActivity.class);
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
                    deviceFragment2.startActivity(intent);
                }
            }
        });
        getDeviceSettingVm().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
        getDeviceViewModel().getPicUiState().observe(deviceFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DeviceFragment.m370loadDataOnce$lambda8(this.f$0, (DeviceViewModel.DevicePictureUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-0, reason: not valid java name */
    public static final void m364loadDataOnce$lambda0(DeviceFragment this$0, RefreshLayout it) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        if (BleOperateManager.getInstance().isConnected()) {
            this$0.getDeviceSettingVm().syncDeviceSettings();
            if (UserConfig.INSTANCE.getInstance().getFmVersion().length() > 0) {
                this$0.getDeviceViewModel().checkOta();
            }
            this$0.handler.removeCallbacks(this$0.myRunnable);
            this$0.handler.postDelayed(this$0.myRunnable, 5000L);
            return;
        }
        FragmentDeviceBinding fragmentDeviceBinding = this$0.binding;
        if (fragmentDeviceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding = null;
        }
        fragmentDeviceBinding.syncDeviceRefresh.finishRefresh();
        String string = this$0.getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2$lambda-1, reason: not valid java name */
    public static final void m365loadDataOnce$lambda2$lambda1(DeviceFragment this$0, DeviceViewModel.DeviceFragmentUI deviceFragmentUI) {
        DeviceFragment deviceFragment;
        FragmentActivity it;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int refreshType = deviceFragmentUI.getRefreshType();
        FragmentDeviceBinding fragmentDeviceBinding = null;
        if (refreshType == 1) {
            if (deviceFragmentUI.getConnect()) {
                FragmentDeviceBinding fragmentDeviceBinding2 = this$0.binding;
                if (fragmentDeviceBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding = fragmentDeviceBinding2;
                }
                ViewKt.gone(fragmentDeviceBinding.disconnectView);
                return;
            }
            FragmentDeviceBinding fragmentDeviceBinding3 = this$0.binding;
            if (fragmentDeviceBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding3 = null;
            }
            ViewKt.visible(fragmentDeviceBinding3.disconnectView);
            FragmentDeviceBinding fragmentDeviceBinding4 = this$0.binding;
            if (fragmentDeviceBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding4;
            }
            fragmentDeviceBinding.tvDeviceBattery.setText("--");
            return;
        }
        if (refreshType != 2) {
            return;
        }
        XLog.i(deviceFragmentUI);
        if (deviceFragmentUI.getShowDfuButton()) {
            FragmentDeviceBinding fragmentDeviceBinding5 = this$0.binding;
            if (fragmentDeviceBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding5 = null;
            }
            ViewKt.visible(fragmentDeviceBinding5.appStatus4);
        } else {
            FragmentDeviceBinding fragmentDeviceBinding6 = this$0.binding;
            if (fragmentDeviceBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding6 = null;
            }
            ViewKt.gone(fragmentDeviceBinding6.appStatus4);
        }
        if (deviceFragmentUI.getRetCode() == 60001) {
            FragmentDeviceBinding fragmentDeviceBinding7 = this$0.binding;
            if (fragmentDeviceBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding7 = null;
            }
            ViewKt.visible(fragmentDeviceBinding7.appStatus5);
        } else {
            FragmentDeviceBinding fragmentDeviceBinding8 = this$0.binding;
            if (fragmentDeviceBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding8 = null;
            }
            ViewKt.gone(fragmentDeviceBinding8.appStatus5);
        }
        if (deviceFragmentUI.getShowDfuButton() && BleOperateManager.getInstance().isConnected()) {
            FragmentDeviceBinding fragmentDeviceBinding9 = this$0.binding;
            if (fragmentDeviceBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding9 = null;
            }
            ViewKt.visible(fragmentDeviceBinding9.btnFirmwareUpdate);
        } else {
            FragmentDeviceBinding fragmentDeviceBinding10 = this$0.binding;
            if (fragmentDeviceBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding10 = null;
            }
            ViewKt.gone(fragmentDeviceBinding10.btnFirmwareUpdate);
        }
        if (UserConfig.INSTANCE.getInstance().getDevelopment()) {
            FragmentDeviceBinding fragmentDeviceBinding11 = this$0.binding;
            if (fragmentDeviceBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding11;
            }
            ViewKt.visible(fragmentDeviceBinding.imageDebugBg);
        } else {
            FragmentDeviceBinding fragmentDeviceBinding12 = this$0.binding;
            if (fragmentDeviceBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding12;
            }
            ViewKt.gone(fragmentDeviceBinding.imageDebugBg);
        }
        if (BleOperateManager.getInstance().isConnected() && deviceFragmentUI.getDfuType() == 2 && (it = (deviceFragment = this$0).getActivity()) != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) DeviceFirmwareUpdateActivity.class);
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
            deviceFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m366loadDataOnce$lambda3(DeviceFragment this$0, DeviceSetting it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.deviceSetting = it;
        this$0.initSupportFunction();
        this$0.initDeviceSetting(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7$lambda-4, reason: not valid java name */
    public static final void m367loadDataOnce$lambda7$lambda4(DeviceFragment this$0, FragmentDeviceBinding this_run, CompoundButton compoundButton, boolean z) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        DeviceSetting deviceSetting = null;
        if (z) {
            PermissionUtilKt.requestCallPhonePermission((FragmentActivity) this$0.getActivity(), this$0.new CallPermissionCallback());
        } else {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setCallSwitch(false);
            this_run.qcSettingCall.setChecked(false);
            UserConfig.INSTANCE.getInstance().setCallPushEnable(false);
            UserConfig.INSTANCE.getInstance().save();
        }
        DeviceSettingViewModel deviceSettingVm = this$0.getDeviceSettingVm();
        String deviceAddressNoClear = UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear();
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting = deviceSetting3;
        }
        deviceSettingVm.saveDeviceSetting(deviceAddressNoClear, deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7$lambda-5, reason: not valid java name */
    public static final void m368loadDataOnce$lambda7$lambda5(DeviceFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        FragmentDeviceBinding fragmentDeviceBinding = null;
        if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() == 0) {
            if (compoundButton.isPressed()) {
                String string = this$0.getString(R.string.qc_text_6666040);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_6666040)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
            FragmentDeviceBinding fragmentDeviceBinding2 = this$0.binding;
            if (fragmentDeviceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding2;
            }
            fragmentDeviceBinding.qcSettingBatteryWarming.setChecked(!z);
            return;
        }
        if (compoundButton.isPressed() && z) {
            XLog.i("-----3333-1");
            this$0.showRingBatteryLowDialog();
        }
        try {
            if (this$0.deviceSetting != null) {
                UserConfig.INSTANCE.getInstance().setBatteryWarmingOpen(z);
                UserConfig.INSTANCE.getInstance().save();
                DeviceSetting deviceSetting2 = this$0.deviceSetting;
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setBatteryWarming(z);
                DeviceSettingViewModel deviceSettingVm = this$0.getDeviceSettingVm();
                String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
                DeviceSetting deviceSetting3 = this$0.deviceSetting;
                if (deviceSetting3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                } else {
                    deviceSetting = deviceSetting3;
                }
                deviceSettingVm.saveDeviceSetting(deviceAddress, deviceSetting);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7$lambda-6, reason: not valid java name */
    public static final void m369loadDataOnce$lambda7$lambda6(DeviceFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = this$0.deviceSetting;
        DeviceSetting deviceSetting2 = null;
        if (deviceSetting == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting = null;
        }
        deviceSetting.setWristSense(z);
        DeviceSettingViewModel deviceSettingVm = this$0.getDeviceSettingVm();
        DeviceSetting deviceSetting3 = this$0.deviceSetting;
        if (deviceSetting3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting2 = deviceSetting3;
        }
        deviceSettingVm.execGesture(deviceSetting2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-8, reason: not valid java name */
    public static final void m370loadDataOnce$lambda8(DeviceFragment this$0, DeviceViewModel.DevicePictureUI devicePictureUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(devicePictureUI);
        FragmentDeviceBinding fragmentDeviceBinding = null;
        if (devicePictureUI.getLocalUrl().length() > 0) {
            DeviceFragment deviceFragment = this$0;
            RequestBuilder<Drawable> requestBuilderLoad = Glide.with(deviceFragment).load("file://" + devicePictureUI.getLocalUrl());
            FragmentDeviceBinding fragmentDeviceBinding2 = this$0.binding;
            if (fragmentDeviceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding2 = null;
            }
            RequestBuilder requestBuilderDontAnimate = requestBuilderLoad.placeholder(fragmentDeviceBinding2.imageWatch.getDrawable()).dontAnimate();
            RequestBuilder<Drawable> requestBuilderLoad2 = Glide.with(deviceFragment).load(devicePictureUI.getUrl());
            FragmentDeviceBinding fragmentDeviceBinding3 = this$0.binding;
            if (fragmentDeviceBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding3 = null;
            }
            RequestBuilder requestBuilderError = requestBuilderDontAnimate.error(requestBuilderLoad2.into(fragmentDeviceBinding3.imageWatch));
            FragmentDeviceBinding fragmentDeviceBinding4 = this$0.binding;
            if (fragmentDeviceBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding4;
            }
            requestBuilderError.into(fragmentDeviceBinding.imageWatch);
            return;
        }
        if (devicePictureUI.getUrl().length() == 0) {
            UserConfig.INSTANCE.getInstance().setPictureUpdateTime(0);
            UserConfig.INSTANCE.getInstance().save();
        }
        RequestBuilder<Drawable> requestBuilderLoad3 = Glide.with(this$0).load(devicePictureUI.getUrl());
        FragmentDeviceBinding fragmentDeviceBinding5 = this$0.binding;
        if (fragmentDeviceBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding5 = null;
        }
        RequestBuilder requestBuilderError2 = requestBuilderLoad3.placeholder(fragmentDeviceBinding5.imageWatch.getDrawable()).dontAnimate().error(R.mipmap.device_icon_1);
        FragmentDeviceBinding fragmentDeviceBinding6 = this$0.binding;
        if (fragmentDeviceBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDeviceBinding = fragmentDeviceBinding6;
        }
        requestBuilderError2.into(fragmentDeviceBinding.imageWatch);
    }

    private final void showRingBatteryLowDialog() {
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_battery_note);
        CenterDialog centerDialog = this.batteryDialog;
        if (centerDialog != null) {
            Intrinsics.checkNotNull(centerDialog);
            if (centerDialog.isShowing()) {
                XLog.i("battery dialog dismiss");
                return;
            }
        }
        CenterDialog centerDialogCreate = builder.create();
        this.batteryDialog = centerDialogCreate;
        Intrinsics.checkNotNull(centerDialogCreate);
        centerDialogCreate.show();
        CenterDialog centerDialog2 = this.batteryDialog;
        Intrinsics.checkNotNull(centerDialog2);
        View contentView = centerDialog2.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "batteryDialog!!.contentView");
        ((TextView) contentView.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceFragment.m376showRingBatteryLowDialog$lambda9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showRingBatteryLowDialog$lambda-9, reason: not valid java name */
    public static final void m376showRingBatteryLowDialog$lambda9(DeviceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CenterDialog centerDialog = this$0.batteryDialog;
        Intrinsics.checkNotNull(centerDialog);
        centerDialog.dismiss();
    }

    /* compiled from: DeviceFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/DeviceFragment$BluetoothPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class BluetoothPermissionCallback implements OnPermissionCallback {
        public BluetoothPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                DeviceFragment.this.showUnbindDialog();
                return;
            }
            String string = DeviceFragment.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                XXPermissions.startPermissionActivity(DeviceFragment.this.getActivity(), permissions);
                String string = DeviceFragment.this.getString(R.string.qc_text_458);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_458)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
        }
    }

    private final void initDeviceSetting(DeviceSetting it) {
        FragmentDeviceBinding fragmentDeviceBinding = this.binding;
        FragmentDeviceBinding fragmentDeviceBinding2 = null;
        if (fragmentDeviceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding = null;
        }
        fragmentDeviceBinding.qcSettingBatteryWarming.setChecked(it.getBatteryWarming());
        if (it.getMetricUnit() == 0) {
            fragmentDeviceBinding.qcSettingUnit.setRightText(getString(R.string.qc_text_23));
        } else {
            fragmentDeviceBinding.qcSettingUnit.setRightText(getString(R.string.qc_text_24));
        }
        if (it.getTimeFormat() == 0) {
            FragmentDeviceBinding fragmentDeviceBinding3 = this.binding;
            if (fragmentDeviceBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding3 = null;
            }
            fragmentDeviceBinding3.qcSettingTime.setRightText(getString(R.string.qc_text_26));
        } else {
            FragmentDeviceBinding fragmentDeviceBinding4 = this.binding;
            if (fragmentDeviceBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding4 = null;
            }
            fragmentDeviceBinding4.qcSettingTime.setRightText(getString(R.string.qc_text_25));
        }
        if (it.getWeatherFormat() == 0) {
            fragmentDeviceBinding.qcSettingWeather.setRightText(getString(R.string.qc_text_27));
        } else {
            fragmentDeviceBinding.qcSettingWeather.setRightText(getString(R.string.qc_text_28));
        }
        if (it.getDisturbSwitch()) {
            if (it.getDisturbManualSwitch()) {
                fragmentDeviceBinding.qcSettingDisturb.setRightText(getString(R.string.qc_text_567));
            } else {
                fragmentDeviceBinding.qcSettingDisturb.setRightText(DateUtil.dayMinToStr(it.getDisturbStart()) + '~' + DateUtil.dayMinToStr(it.getDisturbEnd()));
            }
        } else if (it.getDisturbManualSwitch()) {
            fragmentDeviceBinding.qcSettingDisturb.setRightText(getString(R.string.qc_text_567));
        } else {
            fragmentDeviceBinding.qcSettingDisturb.setRightText(getString(R.string.qc_text_21));
        }
        try {
            boolean zIsNotificationListenerEnabled = isNotificationListenerEnabled();
            if (UserConfig.INSTANCE.getInstance().getMessagePushEnable() && zIsNotificationListenerEnabled) {
                FragmentDeviceBinding fragmentDeviceBinding5 = this.binding;
                if (fragmentDeviceBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding5 = null;
                }
                fragmentDeviceBinding5.qcSettingMessage.setRightText(getString(R.string.qc_text_22));
                FragmentDeviceBinding fragmentDeviceBinding6 = this.binding;
                if (fragmentDeviceBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding2 = fragmentDeviceBinding6;
                }
                fragmentDeviceBinding2.qcSettingMessage.setErrorIcon(false);
            } else {
                FragmentDeviceBinding fragmentDeviceBinding7 = this.binding;
                if (fragmentDeviceBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding7 = null;
                }
                fragmentDeviceBinding7.qcSettingMessage.setErrorIcon(true);
                FragmentDeviceBinding fragmentDeviceBinding8 = this.binding;
                if (fragmentDeviceBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding2 = fragmentDeviceBinding8;
                }
                fragmentDeviceBinding2.qcSettingMessage.setRightText(getString(R.string.qc_text_21));
            }
        } catch (Exception unused) {
        }
        if (!UserConfig.INSTANCE.getInstance().getNewSleepProtocol()) {
            ViewKt.visible(fragmentDeviceBinding.qcSettingGesture);
        } else {
            ViewKt.gone(fragmentDeviceBinding.qcSettingGesture);
        }
        fragmentDeviceBinding.qcSettingGesture.setChecked(it.getWristSense());
        if (!PermissionUtilKt.hasCallPhonePermission((FragmentActivity) getActivity())) {
            fragmentDeviceBinding.qcSettingCall.setChecked(false);
            UserConfig.INSTANCE.getInstance().setCallPushEnable(false);
        } else {
            UserConfig.INSTANCE.getInstance().setCallPushEnable(it.getCallSwitch());
            fragmentDeviceBinding.qcSettingCall.setChecked(it.getCallSwitch());
        }
        UserConfig.INSTANCE.getInstance().save();
        if (BleOperateManager.getInstance().isConnected()) {
            fragmentDeviceBinding.tvDeviceBattery.setText(QCApplication.INSTANCE.getCONTEXT().getString(R.string.qc_text_33, UserConfig.INSTANCE.getInstance().getBattery()) + '%');
            return;
        }
        fragmentDeviceBinding.tvDeviceBattery.setText("--");
    }

    private final void initSupportFunction() {
        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
        FragmentDeviceBinding fragmentDeviceBinding = null;
        if (deviceAddress == null || deviceAddress.length() == 0) {
            FragmentDeviceBinding fragmentDeviceBinding2 = this.binding;
            if (fragmentDeviceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding2;
            }
            ViewKt.gone(fragmentDeviceBinding.qcSettingContact);
            return;
        }
        try {
            if (UserConfig.INSTANCE.getInstance().getSupportContact()) {
                FragmentDeviceBinding fragmentDeviceBinding3 = this.binding;
                if (fragmentDeviceBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding3 = null;
                }
                ViewKt.visible(fragmentDeviceBinding3.qcSettingContact);
            } else {
                FragmentDeviceBinding fragmentDeviceBinding4 = this.binding;
                if (fragmentDeviceBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding4 = null;
                }
                ViewKt.gone(fragmentDeviceBinding4.qcSettingContact);
            }
            if (UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
                FragmentDeviceBinding fragmentDeviceBinding5 = this.binding;
                if (fragmentDeviceBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding5 = null;
                }
                ViewKt.gone(fragmentDeviceBinding5.qcSettingWeather);
                FragmentDeviceBinding fragmentDeviceBinding6 = this.binding;
                if (fragmentDeviceBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding6 = null;
                }
                ViewKt.gone(fragmentDeviceBinding6.qcSettingTime);
                FragmentDeviceBinding fragmentDeviceBinding7 = this.binding;
                if (fragmentDeviceBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding7 = null;
                }
                ViewKt.visible(fragmentDeviceBinding7.qcSettingBatteryWarming);
                return;
            }
            FragmentDeviceBinding fragmentDeviceBinding8 = this.binding;
            if (fragmentDeviceBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding8 = null;
            }
            ViewKt.visible(fragmentDeviceBinding8.qcSettingWeather);
            FragmentDeviceBinding fragmentDeviceBinding9 = this.binding;
            if (fragmentDeviceBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding9 = null;
            }
            ViewKt.visible(fragmentDeviceBinding9.qcSettingTime);
            FragmentDeviceBinding fragmentDeviceBinding10 = this.binding;
            if (fragmentDeviceBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding10 = null;
            }
            ViewKt.visible(fragmentDeviceBinding10.qcSettingUnit);
            FragmentDeviceBinding fragmentDeviceBinding11 = this.binding;
            if (fragmentDeviceBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding11 = null;
            }
            ViewKt.gone(fragmentDeviceBinding11.qcSettingBatteryWarming);
        } catch (Exception unused) {
            FragmentDeviceBinding fragmentDeviceBinding12 = this.binding;
            if (fragmentDeviceBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDeviceBinding = fragmentDeviceBinding12;
            }
            ViewKt.gone(fragmentDeviceBinding.qcSettingContact);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNotificationDialog() {
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_notification);
        final CenterDialog centerDialogCreate = builder.create();
        centerDialogCreate.show();
        View contentView = centerDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        ((TextView) contentView.findViewById(R.id.tv_confirm)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceFragment.m373showNotificationDialog$lambda11(centerDialogCreate, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showNotificationDialog$lambda-11, reason: not valid java name */
    public static final void m373showNotificationDialog$lambda11(CenterDialog centerDialog, DeviceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        centerDialog.dismiss();
        try {
            this$0.startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 100);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x028b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void refreshUI() throws java.lang.InterruptedException {
        /*
            Method dump skipped, instructions count: 802
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.device.DeviceFragment.refreshUI():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: refreshUI$lambda-13$lambda-12, reason: not valid java name */
    public static final void m372refreshUI$lambda13$lambda12(FragmentDeviceBinding this_run, BatteryRsp batteryRsp) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        UserConfig.INSTANCE.getInstance().setBattery(String.valueOf(batteryRsp.getBatteryValue()));
        UserConfig.INSTANCE.getInstance().save();
        this_run.tvDeviceBattery.setText(QCApplication.INSTANCE.getCONTEXT().getString(R.string.qc_text_33, String.valueOf(batteryRsp.getBatteryValue())) + '%');
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) throws InterruptedException {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof RefreshEvent) && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            readBattery();
            getDeviceSettingVm().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
            refreshUI();
            EventBus.getDefault().post(new BatteryLowEvent());
            if (UserConfig.INSTANCE.getInstance().getFmVersion().length() > 0) {
                getDeviceViewModel().checkOta();
            }
        }
        if (messageEvent instanceof WatchFaceDownloadEvent ? true : messageEvent instanceof FirmUpdateEvent ? true : messageEvent instanceof BluetoothEvent) {
            refreshUI();
            return;
        }
        FragmentDeviceBinding fragmentDeviceBinding = null;
        if (messageEvent instanceof MessagePermissionEvent) {
            try {
                boolean zIsNotificationListenerEnabled = isNotificationListenerEnabled();
                if (UserConfig.INSTANCE.getInstance().getMessagePushEnable() && zIsNotificationListenerEnabled) {
                    FragmentDeviceBinding fragmentDeviceBinding2 = this.binding;
                    if (fragmentDeviceBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentDeviceBinding2 = null;
                    }
                    fragmentDeviceBinding2.qcSettingMessage.setRightText(getString(R.string.qc_text_22));
                    FragmentDeviceBinding fragmentDeviceBinding3 = this.binding;
                    if (fragmentDeviceBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentDeviceBinding = fragmentDeviceBinding3;
                    }
                    fragmentDeviceBinding.qcSettingMessage.setErrorIcon(false);
                    return;
                }
                FragmentDeviceBinding fragmentDeviceBinding4 = this.binding;
                if (fragmentDeviceBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding4 = null;
                }
                fragmentDeviceBinding4.qcSettingMessage.setErrorIcon(true);
                FragmentDeviceBinding fragmentDeviceBinding5 = this.binding;
                if (fragmentDeviceBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding = fragmentDeviceBinding5;
                }
                fragmentDeviceBinding.qcSettingMessage.setRightText(getString(R.string.qc_text_21));
                return;
            } catch (Exception unused) {
                return;
            }
        }
        if (messageEvent instanceof FirmCheckEvent ? true : messageEvent instanceof DevTestEvent) {
            if (AppUtil.isBackground(getContext()) && AppUtil.isApplicationBroughtToBackground(getContext())) {
                return;
            }
            String fmVersion = UserConfig.INSTANCE.getInstance().getFmVersion();
            if (fmVersion.length() > 0) {
                getDeviceViewModel().checkOta();
                List listSplit$default = StringsKt.split$default((CharSequence) fmVersion, new String[]{"_"}, false, 0, 6, (Object) null);
                String str = listSplit$default.size() >= 2 ? (String) listSplit$default.get(1) : "";
                FragmentDeviceBinding fragmentDeviceBinding6 = this.binding;
                if (fragmentDeviceBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding = fragmentDeviceBinding6;
                }
                fragmentDeviceBinding.tvFirmwareVersion.setText(str);
                return;
            }
            return;
        }
        if (messageEvent instanceof DataSyncEvent) {
            if (BleOperateManager.getInstance().isConnected()) {
                if (((DataSyncEvent) messageEvent).getStatus()) {
                    FragmentDeviceBinding fragmentDeviceBinding7 = this.binding;
                    if (fragmentDeviceBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentDeviceBinding = fragmentDeviceBinding7;
                    }
                    ViewKt.visible(fragmentDeviceBinding.disconnectView);
                } else {
                    getDeviceSettingVm().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                    FragmentDeviceBinding fragmentDeviceBinding8 = this.binding;
                    if (fragmentDeviceBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentDeviceBinding = fragmentDeviceBinding8;
                    }
                    ViewKt.gone(fragmentDeviceBinding.disconnectView);
                }
            } else {
                FragmentDeviceBinding fragmentDeviceBinding9 = this.binding;
                if (fragmentDeviceBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding = fragmentDeviceBinding9;
                }
                ViewKt.visible(fragmentDeviceBinding.disconnectView);
            }
            refreshUI();
            return;
        }
        if (messageEvent instanceof DeviceToAppSyncEvent) {
            getDeviceSettingVm().syncDeviceSettingsFromDevice(((DeviceToAppSyncEvent) messageEvent).getType());
        }
    }

    private final void readBattery() {
        if (BleOperateManager.getInstance().isConnected()) {
            CommandHandle.getInstance().executeReqCmd(new SimpleKeyReq((byte) 3), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda3
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public final void onDataResponse(BaseRspCmd baseRspCmd) {
                    DeviceFragment.m371readBattery$lambda14(this.f$0, (BatteryRsp) baseRspCmd);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readBattery$lambda-14, reason: not valid java name */
    public static final void m371readBattery$lambda14(DeviceFragment this$0, BatteryRsp batteryRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            UserConfig.INSTANCE.getInstance().setBattery(String.valueOf(batteryRsp.getBatteryValue()));
            UserConfig.INSTANCE.getInstance().save();
            if (this$0.getActivity() == null) {
                return;
            }
            ThreadExtKt.ktxRunOnUi(this$0, new Function1<DeviceFragment, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$readBattery$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DeviceFragment deviceFragment) {
                    invoke2(deviceFragment);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DeviceFragment ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    FragmentDeviceBinding fragmentDeviceBinding = ktxRunOnUi.binding;
                    if (fragmentDeviceBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentDeviceBinding = null;
                    }
                    fragmentDeviceBinding.tvDeviceBattery.setText(ktxRunOnUi.getString(R.string.qc_text_33, UserConfig.INSTANCE.getInstance().getBattery()) + '%');
                }
            });
        } catch (Exception unused) {
        }
    }

    public final void showUnitDialog() {
        BottomListDialog bottomListDialogCreate = new BottomListDialog.Builder(getActivity()).create();
        bottomListDialogCreate.show();
        bottomListDialogCreate.initView();
        bottomListDialogCreate.setSubTitle(getString(R.string.qc_text_34));
        bottomListDialogCreate.setData(getDeviceSettingVm().getUnitList());
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda4
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                DeviceFragment.m380showUnitDialog$lambda16(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showUnitDialog$lambda-16, reason: not valid java name */
    public static final void m380showUnitDialog$lambda16(DeviceFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (i == 0) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setMetricUnit(0);
            FragmentDeviceBinding fragmentDeviceBinding = this$0.binding;
            if (fragmentDeviceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding = null;
            }
            fragmentDeviceBinding.qcSettingUnit.setRightText(this$0.getString(R.string.qc_text_23));
            UserConfig.INSTANCE.getInstance().setMetric(true);
            UserConfig.INSTANCE.getInstance().save();
        } else if (i == 1) {
            DeviceSetting deviceSetting3 = this$0.deviceSetting;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting3 = null;
            }
            deviceSetting3.setMetricUnit(1);
            FragmentDeviceBinding fragmentDeviceBinding2 = this$0.binding;
            if (fragmentDeviceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding2 = null;
            }
            fragmentDeviceBinding2.qcSettingUnit.setRightText(this$0.getString(R.string.qc_text_24));
            UserConfig.INSTANCE.getInstance().setMetric(false);
            UserConfig.INSTANCE.getInstance().save();
        }
        DeviceSettingViewModel deviceSettingVm = this$0.getDeviceSettingVm();
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        deviceSettingVm.execUnit(deviceSetting4);
        DeviceSetting deviceSetting5 = this$0.deviceSetting;
        if (deviceSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting5 = null;
        }
        deviceSettingVm.initDialogData(deviceSetting5);
        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
        Intrinsics.checkNotNull(deviceAddress);
        DeviceSetting deviceSetting6 = this$0.deviceSetting;
        if (deviceSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting = deviceSetting6;
        }
        deviceSettingVm.saveDeviceSetting(deviceAddress, deviceSetting);
    }

    public final void showWeatherDialog() {
        BottomListDialog bottomListDialogCreate = new BottomListDialog.Builder(getActivity()).create();
        bottomListDialogCreate.show();
        bottomListDialogCreate.initView();
        bottomListDialogCreate.setSubTitle(getString(R.string.qc_text_34));
        bottomListDialogCreate.setData(getDeviceSettingVm().getWeatherUnitList());
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                DeviceFragment.m381showWeatherDialog$lambda18(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showWeatherDialog$lambda-18, reason: not valid java name */
    public static final void m381showWeatherDialog$lambda18(DeviceFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (i == 0) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setWeatherFormat(0);
            FragmentDeviceBinding fragmentDeviceBinding = this$0.binding;
            if (fragmentDeviceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding = null;
            }
            fragmentDeviceBinding.qcSettingWeather.setRightText(this$0.getString(R.string.qc_text_27));
            UserConfig.INSTANCE.getInstance().setTemperature(false);
        } else if (i == 1) {
            DeviceSetting deviceSetting3 = this$0.deviceSetting;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting3 = null;
            }
            deviceSetting3.setWeatherFormat(1);
            FragmentDeviceBinding fragmentDeviceBinding2 = this$0.binding;
            if (fragmentDeviceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding2 = null;
            }
            fragmentDeviceBinding2.qcSettingWeather.setRightText(this$0.getString(R.string.qc_text_28));
            UserConfig.INSTANCE.getInstance().setTemperature(true);
        }
        UserConfig.INSTANCE.getInstance().save();
        DeviceSettingViewModel deviceSettingVm = this$0.getDeviceSettingVm();
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        deviceSettingVm.execWeather(deviceSetting4);
        DeviceSetting deviceSetting5 = this$0.deviceSetting;
        if (deviceSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting5 = null;
        }
        deviceSettingVm.initDialogData(deviceSetting5);
        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
        Intrinsics.checkNotNull(deviceAddress);
        DeviceSetting deviceSetting6 = this$0.deviceSetting;
        if (deviceSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting = deviceSetting6;
        }
        deviceSettingVm.saveDeviceSetting(deviceAddress, deviceSetting);
    }

    public final void showTimeFormatDialog() {
        BottomListDialog bottomListDialogCreate = new BottomListDialog.Builder(getActivity()).create();
        bottomListDialogCreate.show();
        bottomListDialogCreate.initView();
        bottomListDialogCreate.setSubTitle(getString(R.string.qc_text_38));
        bottomListDialogCreate.setData(getDeviceSettingVm().getTimeList());
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                DeviceFragment.m377showTimeFormatDialog$lambda20(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showTimeFormatDialog$lambda-20, reason: not valid java name */
    public static final void m377showTimeFormatDialog$lambda20(DeviceFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceSetting deviceSetting = null;
        if (i == 0) {
            DeviceSetting deviceSetting2 = this$0.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setTimeFormat(0);
            FragmentDeviceBinding fragmentDeviceBinding = this$0.binding;
            if (fragmentDeviceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding = null;
            }
            fragmentDeviceBinding.qcSettingTime.setRightText(this$0.getString(R.string.qc_text_26));
        } else if (i == 1) {
            DeviceSetting deviceSetting3 = this$0.deviceSetting;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting3 = null;
            }
            deviceSetting3.setTimeFormat(1);
            FragmentDeviceBinding fragmentDeviceBinding2 = this$0.binding;
            if (fragmentDeviceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding2 = null;
            }
            fragmentDeviceBinding2.qcSettingTime.setRightText(this$0.getString(R.string.qc_text_25));
        }
        DeviceSettingViewModel deviceSettingVm = this$0.getDeviceSettingVm();
        DeviceSetting deviceSetting4 = this$0.deviceSetting;
        if (deviceSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting4 = null;
        }
        deviceSettingVm.execUnit(deviceSetting4);
        DeviceSetting deviceSetting5 = this$0.deviceSetting;
        if (deviceSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            deviceSetting5 = null;
        }
        deviceSettingVm.initDialogData(deviceSetting5);
        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
        Intrinsics.checkNotNull(deviceAddress);
        DeviceSetting deviceSetting6 = this$0.deviceSetting;
        if (deviceSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
        } else {
            deviceSetting = deviceSetting6;
        }
        deviceSettingVm.saveDeviceSetting(deviceAddress, deviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showUnbindDialog() {
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_unbind);
        final BottomDialog bottomDialogCreate = builder.create();
        bottomDialogCreate.show();
        View contentView = bottomDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bottomDialogCreate.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceFragment.m379showUnbindDialog$lambda22(bottomDialogCreate, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showUnbindDialog$lambda-22, reason: not valid java name */
    public static final void m379showUnbindDialog$lambda22(BottomDialog bottomDialog, DeviceFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomDialog.dismiss();
        DeviceSettingRepository.INSTANCE.getGetInstance().saveSyncHistoryDataInfo(0L);
        this$0.getDeviceViewModel().unBindDevice();
        FragmentDeviceBinding fragmentDeviceBinding = this$0.binding;
        FragmentDeviceBinding fragmentDeviceBinding2 = null;
        if (fragmentDeviceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding = null;
        }
        ViewKt.gone(fragmentDeviceBinding.deviceBind);
        FragmentDeviceBinding fragmentDeviceBinding3 = this$0.binding;
        if (fragmentDeviceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding3 = null;
        }
        ViewKt.gone(fragmentDeviceBinding3.qcSettingBatteryWarming);
        FragmentDeviceBinding fragmentDeviceBinding4 = this$0.binding;
        if (fragmentDeviceBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding4 = null;
        }
        ViewKt.visible(fragmentDeviceBinding4.deviceNotBind);
        FragmentDeviceBinding fragmentDeviceBinding5 = this$0.binding;
        if (fragmentDeviceBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDeviceBinding5 = null;
        }
        ViewKt.gone(fragmentDeviceBinding5.btnFirmwareUpdate);
        FragmentDeviceBinding fragmentDeviceBinding6 = this$0.binding;
        if (fragmentDeviceBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDeviceBinding2 = fragmentDeviceBinding6;
        }
        ViewKt.gone(fragmentDeviceBinding2.qcSettingContact);
        EventBus.getDefault().post(new RefreshAiAnalyzeEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNotificationListenerEnabled() {
        String packageName = QCApplication.INSTANCE.getCONTEXT().getPackageName();
        String flat = Settings.Secure.getString(QCApplication.INSTANCE.getCONTEXT().getContentResolver(), "enabled_notification_listeners");
        String str = flat;
        if (!TextUtils.isEmpty(str)) {
            Intrinsics.checkNotNullExpressionValue(flat, "flat");
            Object[] array = new Regex(":").split(str, 0).toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            for (String str2 : (String[]) array) {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str2);
                if (componentNameUnflattenFromString != null && TextUtils.equals(packageName, componentNameUnflattenFromString.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        DeviceFragment deviceFragment;
        FragmentActivity it;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && isNotificationListenerEnabled() && (it = (deviceFragment = this).getActivity()) != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) MessagePushActivity.class);
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
            deviceFragment.startActivity(intent);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPermissionWarmingDialog(final List<String> permissions) {
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_permission_warming);
        final BottomDialog bottomDialogCreate = builder.create();
        Intrinsics.checkNotNull(bottomDialogCreate);
        bottomDialogCreate.show();
        View contentView = bottomDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomWarmingDialog!!.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceFragment.m374showPermissionWarmingDialog$lambda23(bottomDialogCreate, this, permissions, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.DeviceFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceFragment.m375showPermissionWarmingDialog$lambda24(bottomDialogCreate, this, permissions, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionWarmingDialog$lambda-23, reason: not valid java name */
    public static final void m374showPermissionWarmingDialog$lambda23(BottomDialog bottomDialog, DeviceFragment this$0, List permissions, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        Intrinsics.checkNotNull(bottomDialog);
        bottomDialog.dismiss();
        XXPermissions.startPermissionActivity(this$0.getActivity(), (List<String>) permissions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionWarmingDialog$lambda-24, reason: not valid java name */
    public static final void m375showPermissionWarmingDialog$lambda24(BottomDialog bottomDialog, DeviceFragment this$0, List permissions, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        Intrinsics.checkNotNull(bottomDialog);
        bottomDialog.dismiss();
        XXPermissions.startPermissionActivity(this$0.getActivity(), (List<String>) permissions);
    }

    /* compiled from: DeviceFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/DeviceFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DeviceFragment newInstance() {
            return new DeviceFragment();
        }
    }

    /* compiled from: DeviceFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/DeviceFragment$CameraPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CameraPermissionCallback implements OnPermissionCallback {
        public CameraPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (!all) {
                String string = DeviceFragment.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return;
            }
            DeviceFragment deviceFragment = DeviceFragment.this;
            FragmentActivity it = deviceFragment.getActivity();
            if (it != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intent intent = new Intent(it, (Class<?>) CameraActivity.class);
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
                deviceFragment.startActivity(intent);
            }
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                String string = DeviceFragment.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity(DeviceFragment.this.getActivity(), permissions);
            }
        }
    }

    /* compiled from: DeviceFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/DeviceFragment$LocationPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class LocationPermissionCallback implements OnPermissionCallback {
        public LocationPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            QCApplication.INSTANCE.getGetInstance().getLocationOnce(QCApplication.INSTANCE.getApplication());
            DeviceFragment deviceFragment = DeviceFragment.this;
            FragmentActivity it = deviceFragment.getActivity();
            if (it != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intent intent = new Intent(it, (Class<?>) DeviceBindActivity.class);
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
                deviceFragment.startActivity(intent);
            }
            XLog.i(permissions);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            XLog.i(permissions);
            XLog.i(Boolean.valueOf(never));
            if (never) {
                try {
                    DeviceFragment.this.showPermissionWarmingDialog(permissions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* compiled from: DeviceFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/DeviceFragment$MyRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyRunnable implements Runnable {
        public MyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentDeviceBinding fragmentDeviceBinding = DeviceFragment.this.binding;
            FragmentDeviceBinding fragmentDeviceBinding2 = null;
            if (fragmentDeviceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding = null;
            }
            if (fragmentDeviceBinding.syncDeviceRefresh.isRefreshing()) {
                FragmentDeviceBinding fragmentDeviceBinding3 = DeviceFragment.this.binding;
                if (fragmentDeviceBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDeviceBinding2 = fragmentDeviceBinding3;
                }
                fragmentDeviceBinding2.syncDeviceRefresh.finishRefresh();
                DeviceFragment.this.getDeviceSettingVm().initData(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
            }
        }
    }

    /* compiled from: DeviceFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/DeviceFragment$CallPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/DeviceFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CallPermissionCallback implements OnPermissionCallback {
        public CallPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            DeviceSetting deviceSetting = null;
            if (all) {
                FragmentDeviceBinding fragmentDeviceBinding = DeviceFragment.this.binding;
                if (fragmentDeviceBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding = null;
                }
                fragmentDeviceBinding.qcSettingCall.setChecked(true);
                DeviceSetting deviceSetting2 = DeviceFragment.this.deviceSetting;
                if (deviceSetting2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting2 = null;
                }
                deviceSetting2.setCallSwitch(true);
                UserConfig.INSTANCE.getInstance().setCallPushEnable(true);
            } else {
                FragmentDeviceBinding fragmentDeviceBinding2 = DeviceFragment.this.binding;
                if (fragmentDeviceBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDeviceBinding2 = null;
                }
                fragmentDeviceBinding2.qcSettingCall.setChecked(false);
                DeviceSetting deviceSetting3 = DeviceFragment.this.deviceSetting;
                if (deviceSetting3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                    deviceSetting3 = null;
                }
                deviceSetting3.setCallSwitch(false);
                UserConfig.INSTANCE.getInstance().setCallPushEnable(false);
                String string = DeviceFragment.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
            }
            UserConfig.INSTANCE.getInstance().save();
            DeviceSettingViewModel deviceSettingVm = DeviceFragment.this.getDeviceSettingVm();
            String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
            DeviceSetting deviceSetting4 = DeviceFragment.this.deviceSetting;
            if (deviceSetting4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            } else {
                deviceSetting = deviceSetting4;
            }
            deviceSettingVm.saveDeviceSetting(deviceAddress, deviceSetting);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            FragmentDeviceBinding fragmentDeviceBinding = DeviceFragment.this.binding;
            DeviceSetting deviceSetting = null;
            if (fragmentDeviceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDeviceBinding = null;
            }
            fragmentDeviceBinding.qcSettingCall.setChecked(false);
            DeviceSetting deviceSetting2 = DeviceFragment.this.deviceSetting;
            if (deviceSetting2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
                deviceSetting2 = null;
            }
            deviceSetting2.setCallSwitch(false);
            UserConfig.INSTANCE.getInstance().setCallPushEnable(false);
            UserConfig.INSTANCE.getInstance().save();
            String string = DeviceFragment.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
            DeviceSettingViewModel deviceSettingVm = DeviceFragment.this.getDeviceSettingVm();
            String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
            DeviceSetting deviceSetting3 = DeviceFragment.this.deviceSetting;
            if (deviceSetting3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceSetting");
            } else {
                deviceSetting = deviceSetting3;
            }
            deviceSettingVm.saveDeviceSetting(deviceAddress, deviceSetting);
            if (never) {
                XXPermissions.startPermissionActivity(DeviceFragment.this.getActivity(), permissions);
            }
        }
    }
}
