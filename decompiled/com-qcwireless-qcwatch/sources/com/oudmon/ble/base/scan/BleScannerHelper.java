package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.os.HandlerCompat;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import com.realsil.sdk.core.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes3.dex */
public class BleScannerHelper {
    private static final String HANDLER_TOKEN = "stop_token";
    private static final String TAG = "BleScannerHelper";
    private static BleScannerHelper bleScannerHelper;
    public static final String[] sFILTER_PREFIX = {"T80", "T90", "T91", "T93", "T95", "TW68", "S9", "C60", "C66", "C67", "C68", "C80", "C86", "C88", "C96", "wxb_w4"};
    private Handler handler = new Handler(Looper.getMainLooper());
    private int timeOut = 12000;
    private List<String> filters = new ArrayList();

    private BleScannerHelper() {
    }

    public static BleScannerHelper getInstance() {
        if (bleScannerHelper == null) {
            synchronized (BleScannerHelper.class) {
                if (bleScannerHelper == null) {
                    bleScannerHelper = new BleScannerHelper();
                }
            }
        }
        return bleScannerHelper;
    }

    public void reSetCallback() {
        bleScannerHelper = null;
    }

    public void setScanFilter(List<String> list) {
        this.filters = list;
    }

    public void scanDevice(final Context context, UUID uuid, final ScanWrapperCallback scanWrapperCallback) {
        this.handler.removeCallbacksAndMessages(HANDLER_TOKEN);
        if (!BluetoothUtils.isEnabledBluetooth(context)) {
            BleScannerCompat.getScanner(context).scanning = false;
            return;
        }
        if (BleScannerCompat.getScanner(context).isScanning()) {
            stopScan(context);
        }
        HandlerCompat.postDelayed(this.handler, new Runnable() { // from class: com.oudmon.ble.base.scan.BleScannerHelper.1
            @Override // java.lang.Runnable
            public void run() {
                BleScannerHelper.this.stopScan(context);
                ScanWrapperCallback scanWrapperCallback2 = scanWrapperCallback;
                if (scanWrapperCallback2 != null) {
                    scanWrapperCallback2.onScanFailed(0);
                }
            }
        }, HANDLER_TOKEN, 12000L);
        BleScannerCompat.getScanner(context).startScan(scanWrapperCallback);
    }

    public void stopScan(Context context) {
        this.handler.removeCallbacksAndMessages(HANDLER_TOKEN);
        if (!BluetoothUtils.isEnabledBluetooth(context)) {
            BleScannerCompat.getScanner(context).scanning = false;
        } else {
            BleScannerCompat.getScanner(context).stopScan();
        }
    }

    public boolean scanTheDevice(final Context context, final String str, final OnTheScanResult onTheScanResult) {
        this.handler.removeCallbacksAndMessages(HANDLER_TOKEN);
        if (!BluetoothUtils.isEnabledBluetooth(context)) {
            return false;
        }
        HandlerCompat.postDelayed(this.handler, new Runnable() { // from class: com.oudmon.ble.base.scan.BleScannerHelper.2
            @Override // java.lang.Runnable
            public void run() {
                BleScannerHelper.this.stopScan(context);
                OnTheScanResult onTheScanResult2 = onTheScanResult;
                if (onTheScanResult2 != null) {
                    onTheScanResult2.onScanFailed(0);
                }
            }
        }, HANDLER_TOKEN, this.timeOut);
        BleScannerCompat.getScanner(context).startScan(new ScanWrapperCallback() { // from class: com.oudmon.ble.base.scan.BleScannerHelper.3
            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onParsedData(BluetoothDevice bluetoothDevice, ScanRecord scanRecord) {
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onStart() {
                XLog.i("start");
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onStop() {
                XLog.i("stop");
                try {
                    Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
                    if (bondedDevices.size() > 0) {
                        for (BluetoothDevice bluetoothDevice : bondedDevices) {
                            if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null && bluetoothDevice.getAddress().equalsIgnoreCase(str)) {
                                onTheScanResult.onResult(bluetoothDevice);
                                XLog.i("系统绑定了手环");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                if (bluetoothDevice.getAddress().equalsIgnoreCase(str)) {
                    onTheScanResult.onResult(bluetoothDevice);
                }
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onScanFailed(int i) {
                onTheScanResult.onScanFailed(i);
                XLog.e("------------" + i);
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onBatchScanResults(List<ScanResult> list) {
                Iterator<ScanResult> it = list.iterator();
                while (it.hasNext()) {
                    BluetoothDevice device = it.next().getDevice();
                    if (device.getAddress().equalsIgnoreCase(str)) {
                        onTheScanResult.onResult(device);
                    }
                }
            }
        });
        return true;
    }

    public void removeSystemBle() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
            if (bondedDevices.size() > 0) {
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    XLog.i(bluetoothDevice.getName());
                    XLog.i(bluetoothDevice.getAddress());
                    if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null) {
                        String deviceAddress = DeviceManager.getInstance().getDeviceAddress();
                        if (!TextUtils.isEmpty(deviceAddress) && deviceAddress.equalsIgnoreCase(bluetoothDevice.getAddress())) {
                            removeBondDevice(defaultAdapter, bluetoothDevice.getAddress());
                        }
                        if (filterResult(bluetoothDevice.getName()) > 0) {
                            removeBondDevice(defaultAdapter, bluetoothDevice.getAddress());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeMacSystemBond(String str) {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
            if (bondedDevices.size() > 0) {
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null && !TextUtils.isEmpty(str) && str.equalsIgnoreCase(bluetoothDevice.getAddress())) {
                        removeBondDevice(defaultAdapter, bluetoothDevice.getAddress());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int filterResult(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (str.startsWith("O_") || str.startsWith("o_")) {
            return 2;
        }
        Iterator<String> it = this.filters.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return 2;
            }
        }
        if (str.startsWith("Q_") || str.startsWith("q_") || str.startsWith("R3L") || str.startsWith("QC") || str.startsWith(Constants.FLAVOR_QC) || str.startsWith("Wa")) {
            return 3;
        }
        for (String str2 : sFILTER_PREFIX) {
            if (str.startsWith(str2)) {
                return 1;
            }
        }
        return -1;
    }

    private void removeBondDevice(BluetoothAdapter bluetoothAdapter, String str) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
        try {
            Method method = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
            method.setAccessible(true);
            method.invoke(remoteDevice, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(int i) {
        this.timeOut = i;
    }
}
