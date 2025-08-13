package com.qcwireless.qcwatch.ui.base.receiver;

import android.bluetooth.BluetoothDevice;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.PreUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: MyBluetoothReceiver.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0002J&\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0004H\u0016¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/receiver/MyBluetoothReceiver;", "Lcom/oudmon/ble/base/bluetooth/QCBluetoothCallbackCloneReceiver;", "()V", "bleStatus", "", NotificationCompat.CATEGORY_STATUS, "", "newState", "connectStatue", "device", "Landroid/bluetooth/BluetoothDevice;", "connected", "", "initCmd", "onCharacteristicChange", "address", "", "uuid", "data", "", "onCharacteristicRead", "onServiceDiscovered", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyBluetoothReceiver extends QCBluetoothCallbackCloneReceiver {
    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void connectStatue(BluetoothDevice device, boolean connected) {
        if (device != null && connected) {
            if (device.getName() != null) {
                UserConfig companion = UserConfig.INSTANCE.getInstance();
                String name = device.getName();
                Intrinsics.checkNotNullExpressionValue(name, "device.name");
                companion.setDeviceName(name);
                UserConfig companion2 = UserConfig.INSTANCE.getInstance();
                String name2 = device.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "device.name");
                companion2.setDeviceNameNoClear(name2);
            }
            PreUtil.putString(PreUtil.Action_Device_Address, device.getAddress());
            UserConfig companion3 = UserConfig.INSTANCE.getInstance();
            String address = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address, "device.address");
            companion3.setDeviceAddress(address);
            if (!StringsKt.equals(device.getAddress(), UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), true)) {
                UserConfig.INSTANCE.getInstance().setGoogleFitLastInfo("");
                UserConfig.INSTANCE.getInstance().save();
            }
            UserConfig companion4 = UserConfig.INSTANCE.getInstance();
            String address2 = device.getAddress();
            Intrinsics.checkNotNullExpressionValue(address2, "device.address");
            companion4.setDeviceAddressNoClear(address2);
            UserConfig.INSTANCE.getInstance().setWeatherToDeviceLastTime(0);
            UserConfig.INSTANCE.getInstance().setLastTenMinSyncTime(600 + new DateUtil().getUnixTimestamp());
            UserConfig.INSTANCE.getInstance().save();
            return;
        }
        EventBus.getDefault().post(new BluetoothEvent(false));
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void onServiceDiscovered() {
        ThreadExtKt.ktxRunOnBgSingleBle(this, new Function1<MyBluetoothReceiver, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.receiver.MyBluetoothReceiver.onServiceDiscovered.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyBluetoothReceiver myBluetoothReceiver) {
                invoke2(myBluetoothReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyBluetoothReceiver ktxRunOnBgSingleBle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleBle, "$this$ktxRunOnBgSingleBle");
                LargeDataHandler.getInstance().initEnable();
                EventBus.getDefault().post(new BluetoothEvent(true));
                ktxRunOnBgSingleBle.initCmd();
            }
        });
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void onCharacteristicChange(String address, String uuid, final byte[] data) {
        if (data != null) {
            ThreadExtKt.ktxRunOnBgSingleBle(this, new Function1<MyBluetoothReceiver, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.receiver.MyBluetoothReceiver.onCharacteristicChange.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MyBluetoothReceiver myBluetoothReceiver) {
                    invoke2(myBluetoothReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MyBluetoothReceiver ktxRunOnBgSingleBle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingleBle, "$this$ktxRunOnBgSingleBle");
                    BleCommonDataParseKt.parseBleData(data);
                }
            });
        }
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void onCharacteristicRead(final String uuid, final byte[] data) {
        if (uuid == null || data == null) {
            return;
        }
        ThreadExtKt.ktxRunOnBgSingleBle(this, new Function1<MyBluetoothReceiver, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.receiver.MyBluetoothReceiver.onCharacteristicRead.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyBluetoothReceiver myBluetoothReceiver) {
                invoke2(myBluetoothReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyBluetoothReceiver ktxRunOnBgSingleBle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingleBle, "$this$ktxRunOnBgSingleBle");
                BleCommonDataParseKt.parseDeviceInfoData(uuid, data);
            }
        });
    }

    @Override // com.oudmon.ble.base.bluetooth.QCBluetoothCallbackCloneReceiver
    public void bleStatus(int status, int newState) {
        super.bleStatus(status, newState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initCmd() {
        XLog.i("initCmd");
        CommandHandle.getInstance().execReadCmd(CommandHandle.getInstance().getReadHwRequest());
        CommandHandle.getInstance().execReadCmd(CommandHandle.getInstance().getReadFmRequest());
        DeviceCmdInit.INSTANCE.getGetInstance().initDeviceSetting();
    }
}
