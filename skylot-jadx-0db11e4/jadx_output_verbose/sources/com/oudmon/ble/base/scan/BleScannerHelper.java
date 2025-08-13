package com.oudmon.ble.base.scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.HandlerCompat;
import com.oudmon.ble.base.bluetooth.DeviceManager;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.util.BluetoothUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/scan/BleScannerHelper.class */
public class BleScannerHelper {
    private static final String TAG = "BleScannerHelper";
    private static final String HANDLER_TOKEN = "stop_token";
    private static BleScannerHelper bleScannerHelper;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int timeOut = 12000;
    public static final String[] sFILTER_PREFIX = {"T80", "T90", "T91", "T93", "T95", "TW68", "S9", "C60", "C66", "C67", "C68", "C80", "C86", "C88", "C96", "wxb_w4"};

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

    public void scanDevice(final Context context, UUID mUuid, final ScanWrapperCallback scanCallBack) {
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
                if (scanCallBack != null) {
                    scanCallBack.onScanFailed(0);
                }
            }
        }, HANDLER_TOKEN, 12000L);
        BleScannerCompat.getScanner(context).startScan(scanCallBack);
        try {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> devices1 = adapter.getBondedDevices();
            if (devices1.size() > 0) {
                for (BluetoothDevice bluetoothDevice : devices1) {
                    if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null) {
                        if (filterResult(bluetoothDevice.getName()) > 0) {
                            scanCallBack.onLeScan(bluetoothDevice, 90, null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopScan(Context context) {
        this.handler.removeCallbacksAndMessages(HANDLER_TOKEN);
        if (!BluetoothUtils.isEnabledBluetooth(context)) {
            BleScannerCompat.getScanner(context).scanning = false;
        } else {
            BleScannerCompat.getScanner(context).stopScan();
        }
    }

    public boolean scanTheDevice(final Context context, final String macAddress, final OnTheScanResult scanResult) {
        this.handler.removeCallbacksAndMessages(HANDLER_TOKEN);
        if (!BluetoothUtils.isEnabledBluetooth(context)) {
            return false;
        }
        HandlerCompat.postDelayed(this.handler, new Runnable() { // from class: com.oudmon.ble.base.scan.BleScannerHelper.2
            @Override // java.lang.Runnable
            public void run() {
                BleScannerHelper.this.stopScan(context);
                if (scanResult != null) {
                    scanResult.onScanFailed(0);
                }
            }
        }, HANDLER_TOKEN, this.timeOut);
        BleScannerCompat.getScanner(context).startScan(new ScanWrapperCallback() { // from class: com.oudmon.ble.base.scan.BleScannerHelper.3
            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onStart() {
                Log.i(Constants.TAG, "start");
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onStop() {
                Log.i(Constants.TAG, "stop");
                try {
                    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                    Set<BluetoothDevice> devices1 = adapter.getBondedDevices();
                    if (devices1.size() > 0) {
                        for (BluetoothDevice bluetoothDevice : devices1) {
                            if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null) {
                                if (bluetoothDevice.getAddress().equalsIgnoreCase(macAddress)) {
                                    scanResult.onResult(bluetoothDevice);
                                    Log.i("qc", "系统绑定了手环");
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                if (device.getAddress().equalsIgnoreCase(macAddress)) {
                    scanResult.onResult(device);
                    Log.i(Constants.TAG, device.getAddress());
                }
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onScanFailed(int errorCode) {
                scanResult.onScanFailed(errorCode);
                Log.i(Constants.TAG, "------------" + errorCode);
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onParsedData(BluetoothDevice device, ScanRecord scanRecord) {
            }

            @Override // com.oudmon.ble.base.scan.ScanWrapperCallback
            public void onBatchScanResults(List<ScanResult> results) {
                for (ScanResult sr : results) {
                    BluetoothDevice device = sr.getDevice();
                    if (device.getAddress().equalsIgnoreCase(macAddress)) {
                        scanResult.onResult(device);
                    }
                }
            }
        });
        return true;
    }

    public void removeSystemBle() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> devices1 = adapter.getBondedDevices();
        if (devices1.size() > 0) {
            for (BluetoothDevice bluetoothDevice : devices1) {
                if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null) {
                    String mac = DeviceManager.getInstance().getDeviceAddress();
                    if (!TextUtils.isEmpty(mac) && mac.equalsIgnoreCase(bluetoothDevice.getAddress())) {
                        removeBondDevice(adapter, bluetoothDevice.getAddress());
                    }
                    if (filterResult(bluetoothDevice.getName()) > 0) {
                        removeBondDevice(adapter, bluetoothDevice.getAddress());
                    }
                }
            }
        }
    }

    private int filterResult(String name) {
        if (TextUtils.isEmpty(name)) {
            return -1;
        }
        if (name.startsWith("O_") || name.startsWith("o_")) {
            return 2;
        }
        if (name.startsWith("Q_") || name.startsWith("q_") || name.startsWith("R3L") || name.startsWith("QC") || name.startsWith("qc") || name.startsWith("Wa")) {
            return 3;
        }
        for (String pre : sFILTER_PREFIX) {
            if (name.startsWith(pre)) {
                return 1;
            }
        }
        return -1;
    }

    public void removeMacSystemBond(String address) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> devices1 = adapter.getBondedDevices();
        if (devices1.size() > 0) {
            for (BluetoothDevice bluetoothDevice : devices1) {
                if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null && !TextUtils.isEmpty(address) && address.equalsIgnoreCase(bluetoothDevice.getAddress())) {
                    removeBondDevice(adapter, bluetoothDevice.getAddress());
                }
            }
        }
    }

    private void removeBondDevice(BluetoothAdapter adapter, String address) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        BluetoothDevice device = adapter.getRemoteDevice(address);
        Log.i(Constants.TAG, device.getAddress());
        try {
            Method removeBond = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
            removeBond.setAccessible(true);
            removeBond.invoke(device, new Object[0]);
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

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
