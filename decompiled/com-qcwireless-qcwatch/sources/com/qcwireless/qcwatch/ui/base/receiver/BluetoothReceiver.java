package com.qcwireless.qcwatch.ui.base.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.bluetooth.spp.jieli.SppHandle;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.SetTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.oudmon.ble.base.communication.rsp.TodaySportDataRsp;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.util.AppUtil;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.BluetoothSppEvent;
import com.qcwireless.qcwatch.base.event.DeviceSyncTodayStepsEvent;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult;
import com.qcwireless.qcwatch.ui.base.repository.healthy.StepDetailRepository;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.base.watch.DeviceReconnect;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: BluetoothReceiver.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0004H\u0002J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/receiver/BluetoothReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "bleOpen", "", "btDevice", "Landroid/bluetooth/BluetoothDevice;", "btReconnect", "", "classicBluetoothRunnable", "Ljava/lang/Runnable;", "connectRunnable", "mHandler", "Landroid/os/Handler;", "numConnect", "uiRunnable", "beginConnect", "", "delayTime", "connectAgain", "disConnectDevice", "isNotificationListenerEnabled", "onOffBle", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "reConnect", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BluetoothReceiver extends BroadcastReceiver {
    private BluetoothDevice btDevice;
    private int btReconnect;
    private boolean bleOpen = true;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private int numConnect = 1;
    private Runnable classicBluetoothRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.receiver.BluetoothReceiver$$ExternalSyntheticLambda2
        @Override // java.lang.Runnable
        public final void run() {
            BluetoothReceiver.m279classicBluetoothRunnable$lambda1(this.f$0);
        }
    };
    private Runnable uiRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.receiver.BluetoothReceiver$$ExternalSyntheticLambda4
        @Override // java.lang.Runnable
        public final void run() {
            BluetoothReceiver.m283uiRunnable$lambda2();
        }
    };
    private final Runnable connectRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.receiver.BluetoothReceiver$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            BluetoothReceiver.m280connectRunnable$lambda4(this.f$0);
        }
    };

    private final void onOffBle() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onReceive$lambda-0, reason: not valid java name */
    public static final void m282onReceive$lambda0(SetTimeRsp setTimeRsp) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        BluetoothDevice bluetoothDevice;
        BluetoothDevice bluetoothDevice2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -2128145023:
                    if (action.equals("android.intent.action.SCREEN_OFF")) {
                        BleOperateManager.getInstance().setReconnectStartOrNot(false);
                        return;
                    }
                    return;
                case -1980154005:
                    if (!action.equals("android.intent.action.BATTERY_OKAY")) {
                        return;
                    }
                    break;
                case -1530327060:
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                        if (intExtra == 10) {
                            this.numConnect = 1;
                            this.bleOpen = false;
                            onOffBle();
                            BleOperateManager.getInstance().setBluetoothTurnOff(false);
                            XLog.i("蓝牙关闭了 --> ");
                            disConnectDevice();
                            EventBus.getDefault().post(new BluetoothEvent(false));
                            return;
                        }
                        if (intExtra != 12) {
                            return;
                        }
                        XLog.i("蓝牙开启了 --> ");
                        BleOperateManager.getInstance().setBluetoothTurnOff(true);
                        this.bleOpen = true;
                        BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                        DeviceReconnect.INSTANCE.getGetInstance().connectWithScanValidation(UserConfig.INSTANCE.getInstance().getDeviceAddress());
                        beginConnect(2000);
                        this.mHandler.removeCallbacks(this.uiRunnable);
                        this.mHandler.post(this.uiRunnable);
                        if ((UserConfig.INSTANCE.getInstance().getDeviceAddress().length() == 0) && ActivityCompat.checkSelfPermission(context, Permission.BLUETOOTH_CONNECT) == 0) {
                            BleScannerHelper.getInstance().removeMacSystemBond(UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
                            return;
                        }
                        return;
                    }
                    return;
                case -1454123155:
                    if (action.equals("android.intent.action.SCREEN_ON")) {
                        BleOperateManager.getInstance().setReconnectStartOrNot(true);
                        return;
                    }
                    return;
                case -1172645946:
                    if (!action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        return;
                    }
                    break;
                case -873536848:
                    if (!action.equals("android.intent.action.INPUT_METHOD_CHANGED")) {
                        return;
                    }
                    break;
                case -403228793:
                    if (!action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                        return;
                    }
                    break;
                case -301431627:
                    if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                        BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        if (bluetoothDevice3 != null) {
                            XLog.i("蓝牙连接成功--> " + bluetoothDevice3.getName() + "--" + bluetoothDevice3.getAddress());
                        }
                        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
                        Intrinsics.checkNotNull(bluetoothDevice3);
                        if (TextUtils.isEmpty(bluetoothDevice3.getAddress()) || !StringsKt.equals(bluetoothDevice3.getAddress(), deviceAddress, true)) {
                            return;
                        }
                        XLog.i("system-connect-ok");
                        this.numConnect = 1;
                        if (BleOperateManager.getInstance().isConnected()) {
                            return;
                        }
                        beginConnect(5000);
                        ThreadManager.getInstance().wakeUp();
                        return;
                    }
                    return;
                case -286614297:
                    if (!action.equals("android.intent.action.CAMERA_BUTTON")) {
                        return;
                    }
                    break;
                case 505380757:
                    if (action.equals("android.intent.action.TIME_SET")) {
                        XLog.i("手机系统改时间");
                        CommandHandle.getInstance().executeReqCmd(new SetTimeReq(0), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.base.receiver.BluetoothReceiver$$ExternalSyntheticLambda0
                            @Override // com.oudmon.ble.base.communication.ICommandResponse
                            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                                BluetoothReceiver.m282onReceive$lambda0((SetTimeRsp) baseRspCmd);
                            }
                        });
                        return;
                    }
                    return;
                case 823795052:
                    if (action.equals("android.intent.action.USER_PRESENT")) {
                        beginConnect(2000);
                        return;
                    }
                    return;
                case 1041332296:
                    if (!action.equals("android.intent.action.DATE_CHANGED")) {
                        return;
                    }
                    break;
                case 1167529923:
                    if (action.equals("android.bluetooth.device.action.FOUND") && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null && StringsKt.equals(bluetoothDevice.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true)) {
                        String deviceAddress2 = DeviceManager.getInstance().getDeviceAddress();
                        Intrinsics.checkNotNullExpressionValue(deviceAddress2, "getInstance().deviceAddress");
                        if (deviceAddress2.length() > 0) {
                            XLog.i(bluetoothDevice.getName() + "++++" + bluetoothDevice.getAddress() + "----" + UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
                            this.btDevice = bluetoothDevice;
                            if (UserConfig.INSTANCE.getInstance().getRtkMcuSupport()) {
                                XLog.i("---------connectRtkSPP");
                                BleOperateManager.getInstance().connectRtkSPP(bluetoothDevice);
                                return;
                            }
                            if (UserConfig.INSTANCE.getInstance().getMusicSupport() && !UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                                BleOperateManager.getInstance().connectRtkSPP(bluetoothDevice);
                                return;
                            }
                            if (!UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                                this.mHandler.postDelayed(this.classicBluetoothRunnable, 3000L);
                                BleOperateManager.getInstance().createBondBlueTooth(bluetoothDevice);
                                return;
                            } else {
                                XLog.i("-------杰里配对");
                                this.mHandler.postDelayed(this.classicBluetoothRunnable, 3000L);
                                BleOperateManager.getInstance().createBondBluetoothJieLi(bluetoothDevice);
                                return;
                            }
                        }
                        return;
                    }
                    return;
                case 1821585647:
                    if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") && BluetoothUtils.isEnabledBluetooth(context) && (bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null) {
                        XLog.i("蓝牙已断开11 -->地址 " + bluetoothDevice2.getAddress() + " == " + bluetoothDevice2.getName());
                        String deviceAddress3 = UserConfig.INSTANCE.getInstance().getDeviceAddress();
                        if (TextUtils.isEmpty(bluetoothDevice2.getAddress()) || !StringsKt.equals$default(deviceAddress3, bluetoothDevice2.getAddress(), false, 2, null)) {
                            return;
                        }
                        EventBus.getDefault().post(new BluetoothSppEvent(false));
                        XLog.i("system-disconnect-ok");
                        this.numConnect = 1;
                        connectAgain(22000);
                        this.mHandler.post(this.uiRunnable);
                        return;
                    }
                    return;
                case 2116862345:
                    if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                        BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        switch (intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1)) {
                            case 10:
                                StringBuilder sb = new StringBuilder();
                                sb.append("蓝牙配对失败--> ");
                                Intrinsics.checkNotNull(bluetoothDevice4);
                                sb.append(bluetoothDevice4.getAddress());
                                XLog.i(sb.toString());
                                if (StringsKt.equals(bluetoothDevice4.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true) && this.btReconnect >= 1) {
                                    if (UserConfig.INSTANCE.getInstance().getDeviceAddress().length() > 0) {
                                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                        String str = String.format(GlobalKt.getString(R.string.qc_text_512), Arrays.copyOf(new Object[]{bluetoothDevice4.getName()}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                                        GlobalKt.showToast$default(str, 0, 1, null);
                                        break;
                                    }
                                }
                                break;
                            case 11:
                                XLog.i("蓝牙正在配对--> ");
                                break;
                            case 12:
                                XLog.i("蓝牙配对成功--> ");
                                if (bluetoothDevice4 != null && StringsKt.equals(bluetoothDevice4.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true)) {
                                    if (UserConfig.INSTANCE.getInstance().getMusicSupport() && !UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                                        XLog.i("系统配对成功,连接RtkSpp");
                                        BleOperateManager.getInstance().connectRtkSPP(bluetoothDevice4);
                                    } else if (UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                                        XLog.i("系统配对成功,连接杰里Spp");
                                        SppHandle.getInstance().connect(bluetoothDevice4);
                                    }
                                    this.mHandler.removeCallbacks(this.classicBluetoothRunnable);
                                    break;
                                }
                                break;
                        }
                        this.mHandler.removeCallbacks(this.uiRunnable);
                        this.mHandler.post(this.uiRunnable);
                        return;
                    }
                    return;
                default:
                    return;
            }
            LogToFile.getInstance().wtf("notification" + isNotificationListenerEnabled());
            XLog.i(intent.getAction());
            this.mHandler.removeCallbacks(this.uiRunnable);
            this.mHandler.postDelayed(this.uiRunnable, 1000L);
        }
    }

    private final boolean isNotificationListenerEnabled() {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: classicBluetoothRunnable$lambda-1, reason: not valid java name */
    public static final void m279classicBluetoothRunnable$lambda1(BluetoothReceiver this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BluetoothDevice bluetoothDevice = this$0.btDevice;
        if (bluetoothDevice != null) {
            Intrinsics.checkNotNull(bluetoothDevice);
            if (StringsKt.equals(bluetoothDevice.getAddress(), UserConfig.INSTANCE.getInstance().getClassicBluetoothMac(), true)) {
                this$0.btReconnect++;
                if (UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
                    BleOperateManager.getInstance().createBondBluetoothJieLi(this$0.btDevice);
                } else {
                    BleOperateManager.getInstance().createBondBlueTooth(this$0.btDevice);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uiRunnable$lambda-2, reason: not valid java name */
    public static final void m283uiRunnable$lambda2() {
        if (!BleOperateManager.getInstance().isConnected()) {
            new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
            ThreadManager.getInstance().wakeUp();
            return;
        }
        if (!AppUtil.isBackground(QJavaApplication.getInstance().getApplication()) && !AppUtil.isApplicationBroughtToBackground(QJavaApplication.getInstance().getApplication())) {
            EventBus.getDefault().post(new DeviceSyncTodayStepsEvent());
            return;
        }
        try {
            if (UserConfig.INSTANCE.getInstance().getLastSyncTodaySteps() > 0 && UserConfig.INSTANCE.getInstance().getLastSyncTodaySteps() < new DateUtil().getUnixTimestamp()) {
                XLog.i("三分钟限制" + UserConfig.INSTANCE.getInstance().getLastSyncTodaySteps());
                return;
            }
            StepDetailRepository.INSTANCE.getGetInstance().syncTodayStep(new BaseDeviceResult<TodaySportDataRsp>() { // from class: com.qcwireless.qcwatch.ui.base.receiver.BluetoothReceiver$uiRunnable$1$1
                @Override // com.qcwireless.qcwatch.ui.base.repository.base.BaseDeviceResult
                public void result(int errorCode, TodaySportDataRsp t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                    try {
                        UserConfig.INSTANCE.getInstance().setLastSyncTodaySteps(new DateUtil().getUnixTimestamp() + 180);
                        UserConfig.INSTANCE.getInstance().save();
                        PreUtil.putInt(PreUtil.Action_Today_Steps, t.getSportTotal().getTotalSteps());
                        new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    private final void beginConnect(int delayTime) {
        this.mHandler.removeCallbacks(this.connectRunnable);
        if (this.bleOpen) {
            this.mHandler.postDelayed(this.connectRunnable, delayTime);
        }
    }

    private final void connectAgain(int delayTime) {
        if (!this.bleOpen || this.numConnect > 20) {
            return;
        }
        this.mHandler.postDelayed(this.connectRunnable, delayTime);
    }

    private final void disConnectDevice() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.receiver.BluetoothReceiver$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothReceiver.m281disConnectDevice$lambda3();
            }
        }, 1500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: disConnectDevice$lambda-3, reason: not valid java name */
    public static final void m281disConnectDevice$lambda3() {
        if (BleOperateManager.getInstance().isConnected()) {
            BleOperateManager.getInstance().disconnect();
        }
        try {
            new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: connectRunnable$lambda-4, reason: not valid java name */
    public static final void m280connectRunnable$lambda4(BluetoothReceiver this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!TextUtils.isEmpty(UserConfig.INSTANCE.getInstance().getDeviceAddress()) && !BleOperateManager.getInstance().isConnected()) {
            this$0.reConnect();
            int i = this$0.numConnect;
            int i2 = i + (i / 10) + 1;
            this$0.numConnect = i2;
            this$0.connectAgain(((i2 / 10) + 1) * 60 * 1000);
            XLog.i("连结计次器" + this$0.numConnect + " ,delay时间：" + (((this$0.numConnect / 10) + 1) * 60 * 1000));
            return;
        }
        this$0.numConnect = 1;
    }

    private final void reConnect() {
        BleOperateManager.getInstance().setReConnectMac(UserConfig.INSTANCE.getInstance().getDeviceAddress());
        DeviceReconnect.INSTANCE.getGetInstance().connectWithScanValidation(UserConfig.INSTANCE.getInstance().getDeviceAddress());
    }
}
