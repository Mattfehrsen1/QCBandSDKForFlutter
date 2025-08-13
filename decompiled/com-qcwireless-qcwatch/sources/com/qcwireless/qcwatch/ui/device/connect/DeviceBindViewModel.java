package com.qcwireless.qcwatch.ui.device.connect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.scan.ScanRecord;
import com.oudmon.ble.base.scan.ScanWrapperCallback;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceBindRepository;
import com.qcwireless.qcwatch.ui.device.connect.bean.SmartWatch;
import com.realsil.sdk.core.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeviceBindViewModel.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0002R\u0015\u0010\u0005\u001a\u00060\u0006R\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindViewModel;", "Landroidx/lifecycle/ViewModel;", "deviceBindRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceBindRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceBindRepository;)V", "bleScanCallback", "Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindViewModel$BleCallback;", "getBleScanCallback", "()Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindViewModel$BleCallback;", "deviceList", "", "Lcom/qcwireless/qcwatch/ui/device/connect/bean/SmartWatch;", "getDeviceList", "()Ljava/util/List;", "deviceLiveData", "Landroidx/lifecycle/MutableLiveData;", "getDeviceLiveData", "()Landroidx/lifecycle/MutableLiveData;", "setDeviceLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "sFILTER_PREFIX", "", "", "getSFILTER_PREFIX", "()[Ljava/lang/String;", "[Ljava/lang/String;", "addSystemBondedDevice", "filterResult", "", "name", "BleCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceBindViewModel extends ViewModel {
    private final BleCallback bleScanCallback;
    private final List<SmartWatch> deviceList;
    private MutableLiveData<SmartWatch> deviceLiveData;
    private final String[] sFILTER_PREFIX;

    public DeviceBindViewModel(DeviceBindRepository deviceBindRepository) {
        Intrinsics.checkNotNullParameter(deviceBindRepository, "deviceBindRepository");
        this.deviceList = new ArrayList();
        this.bleScanCallback = new BleCallback();
        this.deviceLiveData = new MutableLiveData<>();
        this.sFILTER_PREFIX = new String[]{"T80", "T90", "T91", "T93", "T95", "TW68", "S9", "C60", "C66", "C67", "C68", "C80", "C86", "C88", "C96", "wxb_w4"};
    }

    public final List<SmartWatch> getDeviceList() {
        return this.deviceList;
    }

    public final BleCallback getBleScanCallback() {
        return this.bleScanCallback;
    }

    public final MutableLiveData<SmartWatch> getDeviceLiveData() {
        return this.deviceLiveData;
    }

    public final void setDeviceLiveData(MutableLiveData<SmartWatch> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.deviceLiveData = mutableLiveData;
    }

    public final List<SmartWatch> addSystemBondedDevice() {
        ArrayList arrayList = new ArrayList();
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (bondedDevices.size() > 0) {
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null) {
                    String name = bluetoothDevice.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "bluetoothDevice.name");
                    if (filterResult(name) > 0) {
                        String name2 = bluetoothDevice.getName();
                        Intrinsics.checkNotNullExpressionValue(name2, "bluetoothDevice.name");
                        arrayList.add(new SmartWatch(name2, bluetoothDevice.getAddress(), 90));
                    }
                }
            }
        }
        return arrayList;
    }

    private final int filterResult(String name) {
        if (TextUtils.isEmpty(name)) {
            return -1;
        }
        if (StringsKt.startsWith$default(name, "O_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "o_", false, 2, (Object) null)) {
            return 2;
        }
        for (String pre : QJavaApplication.getInstance().getKeys()) {
            Intrinsics.checkNotNullExpressionValue(pre, "pre");
            if (StringsKt.startsWith$default(name, pre, false, 2, (Object) null)) {
                return 2;
            }
        }
        StringBuilder sb = new StringBuilder();
        int length = name.length();
        for (int i = 0; i < length; i++) {
            sb.append(name.charAt(i));
            if (QJavaApplication.getInstance().getKeysMap().get(sb.toString()) != null) {
                return 2;
            }
        }
        if (StringsKt.startsWith$default(name, "Q_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "q_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "R3L", false, 2, (Object) null) || StringsKt.startsWith$default(name, "QC", false, 2, (Object) null) || StringsKt.startsWith$default(name, Constants.FLAVOR_QC, false, 2, (Object) null) || StringsKt.startsWith$default(name, "Wa", false, 2, (Object) null)) {
            return 3;
        }
        for (String str : this.sFILTER_PREFIX) {
            if (StringsKt.startsWith$default(name, str, false, 2, (Object) null)) {
                return 1;
            }
        }
        return -1;
    }

    public final String[] getSFILTER_PREFIX() {
        return this.sFILTER_PREFIX;
    }

    /* compiled from: DeviceBindViewModel.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016J$\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindViewModel$BleCallback;", "Lcom/oudmon/ble/base/scan/ScanWrapperCallback;", "(Lcom/qcwireless/qcwatch/ui/device/connect/DeviceBindViewModel;)V", "onBatchScanResults", "", "results", "", "Landroid/bluetooth/le/ScanResult;", "onLeScan", "device", "Landroid/bluetooth/BluetoothDevice;", "rssi", "", "scanRecord", "", "onParsedData", "Lcom/oudmon/ble/base/scan/ScanRecord;", "onScanFailed", "errorCode", "onStart", "onStop", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class BleCallback implements ScanWrapperCallback {
        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onBatchScanResults(List<ScanResult> results) {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onParsedData(BluetoothDevice device, ScanRecord scanRecord) {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onScanFailed(int errorCode) {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onStart() {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onStop() {
        }

        public BleCallback() {
        }

        @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            if (device != null) {
                try {
                    String name = device.getName();
                    if (name == null || name.length() == 0) {
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    String name2 = device.getName();
                    int length = name2.length();
                    for (int i = 0; i < length; i++) {
                        sb.append(name2.charAt(i));
                        if (QJavaApplication.getInstance().getKeysMap().get(sb.toString()) != null) {
                            MutableLiveData<SmartWatch> deviceLiveData = DeviceBindViewModel.this.getDeviceLiveData();
                            String name3 = device.getName();
                            Intrinsics.checkNotNullExpressionValue(name3, "device.name");
                            deviceLiveData.setValue(new SmartWatch(name3, device.getAddress(), rssi));
                            XLog.i(device.getName() + "---" + device.getAddress());
                            return;
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
    }
}
