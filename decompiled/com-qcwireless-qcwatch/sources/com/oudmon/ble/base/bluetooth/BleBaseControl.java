package com.oudmon.ble.base.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.scan.BleScannerCompat;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.scan.OnTheScanResult;
import com.oudmon.ble.base.util.AppUtil;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qc_utils.bluetooth.BluetoothUtils;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class BleBaseControl {
    private static final int GATT_CLOSE_DELAY_MILLIS = 500;
    private static final String TAG = "BleBaseControl";
    private static BleBaseControl bleBaseControl;
    private volatile boolean connecting;
    private IBleListener listener;
    private BluetoothAdapter mBluetoothAdapter;
    private Context mContext;
    private String mDeviceAddress;
    private volatile boolean mIsConnected;
    private final Object mLock = new Object();
    protected Map<String, BluetoothGatt> mBluetoothGatt = new HashMap();
    private boolean isNeedReconnect = true;
    private int maxReconnect = 10;
    private int maxFail = 6;
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger failCount = new AtomicInteger(0);
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int failTotal = 0;
    private boolean bluetoothTurnOff = false;
    private HashMap<UUID, BluetoothGattCharacteristic> cacheGattCharacteristicHashMap = new HashMap<>();
    private SystemProxyTimeoutRunnable systemProxyTimeoutRunnable = new SystemProxyTimeoutRunnable();
    private Boolean rtkBindTag = false;
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) throws Throwable {
            XLog.e("onConnectionStateChange-->status = [" + i + "], newState = [" + i2 + "]");
            LogToFile.getInstance().wtfStatus(":status:" + i + "newState:" + i2);
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleStatus(i, i2);
            }
            String address = bluetoothGatt.getDevice().getAddress();
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mDiscoverServiceTimeoutRunnable);
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mTimeoutRunnable);
            BleThreadManager.getInstance().clean();
            if (i != 0) {
                BleBaseControl.this.notifyMyAll();
                BleBaseControl.access$608(BleBaseControl.this);
                XLog.i("失败次数：" + BleBaseControl.this.failTotal);
                BleBaseControl.this.mIsConnected = false;
                try {
                    if (!BleBaseControl.this.rtkBindTag.booleanValue()) {
                        BleBaseControl.this.unBondedDevice(address);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (BleBaseControl.this.listener != null) {
                    BleBaseControl.this.listener.bleGattDisconnect(bluetoothGatt.getDevice());
                }
                BleBaseControl.this.disconnectDevice(address);
                BleBaseControl.this.failCount.incrementAndGet();
                reconnectFromStateChangeNoAutoConnect();
                return;
            }
            if (i2 != 2) {
                if (i2 == 0) {
                    BleBaseControl.this.mIsConnected = false;
                    BleBaseControl.this.notifyMyAll();
                    BleBaseControl.this.disconnectDevice(address);
                    BleBaseControl.this.reconnectDevice();
                    if (BleBaseControl.this.listener != null) {
                        BleBaseControl.this.listener.bleGattDisconnect(bluetoothGatt.getDevice());
                        return;
                    }
                    return;
                }
                return;
            }
            BleBaseControl.this.failTotal = 0;
            BleBaseControl.this.cacheGattCharacteristicHashMap.clear();
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mReconnectRunnable);
            BleBaseControl.this.waitFor(500L);
            if (bluetoothGatt.discoverServices()) {
                BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mDiscoverServiceTimeoutRunnable);
                BleBaseControl.this.mHandler.postDelayed(BleBaseControl.this.mDiscoverServiceTimeoutRunnable, 40000L);
            } else {
                BleBaseControl.this.waitFor(1000L);
                boolean zDiscoverServices = bluetoothGatt.discoverServices();
                XLog.i("-------1---" + zDiscoverServices);
                if (!zDiscoverServices) {
                    BleBaseControl.this.disconnectDevice(address);
                    return;
                }
            }
            BleBaseControl.this.count.getAndSet(0);
            BleBaseControl.this.failCount.getAndSet(0);
            BleBaseControl.this.mIsConnected = true;
            BleBaseControl.this.bluetoothTurnOff = false;
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mReconnectRunnable);
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleGattConnected(bluetoothGatt.getDevice());
            }
        }

        public void disconnectDeviceNotClose(String str) {
            final BluetoothGatt bluetoothGatt;
            try {
                BleBaseControl.this.connecting = false;
                BleBaseControl.this.mIsConnected = false;
                XLog.i(str + "  gatt map size:" + BleBaseControl.this.mBluetoothGatt.size());
                if (!BleBaseControl.this.mBluetoothGatt.containsKey(str) || (bluetoothGatt = BleBaseControl.this.mBluetoothGatt.get(str)) == null) {
                    return;
                }
                XLog.i("gatt disconnect it");
                bluetoothGatt.disconnect();
                BleBaseControl.this.mHandler.postDelayed(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.1.1
                    @Override // java.lang.Runnable
                    public void run() throws NoSuchMethodException, SecurityException {
                        BleBaseControl.this.refreshDeviceCache(bluetoothGatt);
                    }
                }, 500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void reconnectFromStateChangeNoAutoConnect() {
            BleBaseControl.this.mHandler.postDelayed(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.1.2
                @Override // java.lang.Runnable
                public void run() {
                    BleBaseControl.this.reconnectDevice();
                }
            }, TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            String address = bluetoothGatt.getDevice().getAddress();
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mDiscoverServiceTimeoutRunnable);
            if (i != 0) {
                BleBaseControl.this.disconnectDevice(address);
                return;
            }
            Iterator<BluetoothGattService> it = bluetoothGatt.getServices().iterator();
            while (it.hasNext()) {
                XLog.i("servicesUUID:" + it.next().getUuid().toString());
            }
            BleBaseControl.this.connecting = false;
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleServiceDiscovered(i, address);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) throws Throwable {
            LogToFile.getInstance().wtfWrite(DataTransferUtils.getHexString(bluetoothGattCharacteristic.getValue()));
            XLog.i("app->device：" + DataTransferUtils.getHexString(bluetoothGattCharacteristic.getValue()));
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleCharacteristicWrite(bluetoothGatt.getDevice().getAddress(), bluetoothGattCharacteristic.getUuid().toString(), i, bluetoothGattCharacteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleCharacteristicRead(bluetoothGatt.getDevice().getAddress(), bluetoothGattCharacteristic.getUuid().toString(), i, bluetoothGattCharacteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) throws Throwable {
            LogToFile.getInstance().wtfNotify(DataTransferUtils.getHexString(bluetoothGattCharacteristic.getValue()));
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleCharacteristicChanged(bluetoothGatt.getDevice().getAddress(), bluetoothGattCharacteristic.getUuid().toString(), bluetoothGattCharacteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            }
            if (i == 0) {
                BleBaseControl.this.checkIsNotifyConfigAndRegisterCallback(bluetoothGattDescriptor, bluetoothGatt);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.onReadRemoteRssi(bluetoothGatt, i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            XLog.i(i + "");
        }
    };
    private Runnable mDiscoverServiceTimeoutRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.4
        @Override // java.lang.Runnable
        public void run() {
            BleBaseControl bleBaseControl2 = BleBaseControl.this;
            bleBaseControl2.disconnectDevice(bleBaseControl2.mDeviceAddress);
        }
    };
    private Runnable mTimeoutRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.6
        @Override // java.lang.Runnable
        public void run() {
            BleBaseControl.this.mIsConnected = false;
            BleBaseControl.this.connecting = false;
            XLog.i("没有收到系统回调，直接断开");
            BleBaseControl bleBaseControl2 = BleBaseControl.this;
            bleBaseControl2.disconnectDevice(bleBaseControl2.mDeviceAddress);
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleNoCallback();
            }
        }
    };
    private Runnable mReconnectRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.7
        @Override // java.lang.Runnable
        public void run() {
            if (BleBaseControl.this.count.get() < BleBaseControl.this.maxReconnect) {
                BleBaseControl.this.count.incrementAndGet();
                XLog.e("正在重连,重连次数：" + BleBaseControl.this.count.get());
                BleBaseControl bleBaseControl2 = BleBaseControl.this;
                bleBaseControl2.connect(bleBaseControl2.mDeviceAddress);
                return;
            }
            BleBaseControl.this.connecting = false;
            XLog.e("超出了重连次数:" + BleBaseControl.this.count.get());
        }
    };

    static /* synthetic */ int access$608(BleBaseControl bleBaseControl2) {
        int i = bleBaseControl2.failTotal;
        bleBaseControl2.failTotal = i + 1;
        return i;
    }

    public void setBluetoothTurnOff(boolean z) {
        this.bluetoothTurnOff = z;
        if (z) {
            return;
        }
        this.mIsConnected = false;
        this.connecting = false;
    }

    public boolean ismIsConnected() {
        return this.mIsConnected;
    }

    public static BleBaseControl getInstance() {
        return bleBaseControl;
    }

    public static BleBaseControl getInstance(Context context) {
        if (bleBaseControl == null) {
            synchronized (BleBaseControl.class) {
                if (bleBaseControl == null) {
                    bleBaseControl = new BleBaseControl(context);
                }
            }
        }
        return bleBaseControl;
    }

    public Context getmContext() {
        return this.mContext;
    }

    public void setmContext(Context context) {
        this.mContext = context;
    }

    private BleBaseControl(Context context) {
        this.mContext = context;
        initialize();
    }

    private void initialize() {
        BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
        if (bluetoothManager != null) {
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
        } else {
            Log.e(TAG, "Unable to initialize BluetoothManager...");
        }
    }

    private class SystemProxyTimeoutRunnable implements Runnable {
        private SystemProxyTimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() throws SecurityException {
            BleBaseControl.this.doConnectClone();
        }
    }

    private void reconnectFromStateChange(String str) {
        this.mHandler.removeCallbacks(this.systemProxyTimeoutRunnable);
        this.mHandler.postDelayed(this.systemProxyTimeoutRunnable, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doConnectClone() throws SecurityException {
        boolean zIsBackground = AppUtil.isBackground(this.mContext);
        boolean zIsApplicationBroughtToBackground = AppUtil.isApplicationBroughtToBackground(this.mContext);
        if (zIsBackground || zIsApplicationBroughtToBackground) {
            XLog.e("后台重连调用");
            doConnect();
        } else {
            XLog.e("前台重连调用");
            BleScannerHelper.getInstance().scanTheDevice(this.mContext, this.mDeviceAddress, new OnTheScanResult() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.2
                @Override // com.oudmon.ble.base.scan.OnTheScanResult
                public void onResult(BluetoothDevice bluetoothDevice) {
                    if (BleBaseControl.this.count.get() >= BleBaseControl.this.maxReconnect) {
                        BleBaseControl.this.count.getAndSet(0);
                    }
                    if (bluetoothDevice != null) {
                        BleBaseControl.this.mHandler.postDelayed(BleBaseControl.this.mReconnectRunnable, 200L);
                        return;
                    }
                    BleBaseControl.this.connecting = false;
                    BleBaseControl.this.count.incrementAndGet();
                    BleBaseControl.this.doConnect();
                }

                @Override // com.oudmon.ble.base.scan.OnTheScanResult
                public void onScanFailed(int i) {
                    BleBaseControl.this.connecting = false;
                    BleBaseControl.this.count.incrementAndGet();
                    BleBaseControl.this.doConnect();
                }
            });
        }
    }

    public void reconnectOpeningUp() {
        if (this.count.get() >= this.maxReconnect) {
            this.count.getAndSet(0);
        }
        if (this.failCount.get() >= this.maxFail) {
            this.failCount.getAndSet(0);
        }
        this.isNeedReconnect = true;
        reconnectDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectDevice() {
        synchronized (BleBaseControl.class) {
            if (!this.isNeedReconnect) {
                this.connecting = false;
                return;
            }
            if (BluetoothUtils.isEnabledBluetooth(this.mContext) && !TextUtils.isEmpty(this.mDeviceAddress)) {
                if (this.failCount.get() >= this.maxFail) {
                    this.mIsConnected = false;
                    this.connecting = false;
                    XLog.i("内部失败循环大于" + this.maxFail + "次直接返回");
                    return;
                }
                if (!isConnecting() && !ismIsConnected()) {
                    this.mHandler.removeCallbacks(this.mReconnectRunnable);
                    if (BleScannerCompat.getScanner(this.mContext).isScanning()) {
                        return;
                    }
                    doConnectClone();
                    return;
                }
                XLog.i("正在连接:" + isConnecting() + " 已经连上:" + ismIsConnected());
                return;
            }
            this.mIsConnected = false;
            this.connecting = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doConnect() {
        if (!this.isNeedReconnect) {
            this.connecting = false;
            return;
        }
        XLog.i("连接失败次数:" + this.failTotal);
        if (this.failTotal > 10) {
            XLog.i("失败超出了最大失败次数");
            return;
        }
        if (this.count.get() % 3 == 0 && !this.bluetoothTurnOff) {
            this.mHandler.postDelayed(this.mReconnectRunnable, 1000L);
            XLog.i("--直连");
        } else {
            XLog.i("--扫连");
            this.bluetoothTurnOff = false;
            BleScannerHelper.getInstance().scanTheDevice(this.mContext, this.mDeviceAddress, new OnTheScanResult() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.3
                @Override // com.oudmon.ble.base.scan.OnTheScanResult
                public void onResult(BluetoothDevice bluetoothDevice) {
                    if (BleBaseControl.this.count.get() >= BleBaseControl.this.maxReconnect) {
                        BleBaseControl.this.count.getAndSet(0);
                    }
                    XLog.i("扫描出的mac:" + bluetoothDevice.getAddress());
                    if (bluetoothDevice != null) {
                        BleBaseControl.this.mHandler.postDelayed(BleBaseControl.this.mReconnectRunnable, 200L);
                        return;
                    }
                    BleBaseControl.this.connecting = false;
                    BleBaseControl.this.count.incrementAndGet();
                    BleBaseControl.this.doConnect();
                }

                @Override // com.oudmon.ble.base.scan.OnTheScanResult
                public void onScanFailed(int i) {
                    BleBaseControl.this.connecting = false;
                    BleBaseControl.this.count.incrementAndGet();
                    BleBaseControl.this.doConnect();
                }
            });
        }
    }

    public boolean connect(String str) {
        BluetoothGatt bluetoothGattConnectGatt;
        if (!BluetoothUtils.isEnabledBluetooth(this.mContext)) {
            this.connecting = false;
            this.isNeedReconnect = false;
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            XLog.i("address 空返回");
            this.mIsConnected = false;
            this.connecting = false;
            return false;
        }
        if (isConnecting() || ismIsConnected()) {
            XLog.i("再次检查的时候返回了");
            return false;
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mHandler.postDelayed(this.mTimeoutRunnable, 40000L);
        this.connecting = true;
        this.isNeedReconnect = true;
        BleScannerHelper.getInstance().stopScan(this.mContext);
        this.mDeviceAddress = str;
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        XLog.e("---------------【开始GATT连接】--------------");
        LogToFile.getInstance().wtfStatus("正在连接手表，等系统回调");
        IBleListener iBleListener = this.listener;
        if (iBleListener != null) {
            iBleListener.startConnect();
        }
        if (Build.VERSION.SDK_INT > 23) {
            if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(this.mContext, Permission.BLUETOOTH_CONNECT) != 0) {
                return false;
            }
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.mContext, false, this.mGattCallback, 2);
        } else {
            bluetoothGattConnectGatt = remoteDevice.connectGatt(this.mContext, false, this.mGattCallback);
        }
        if (bluetoothGattConnectGatt == null) {
            this.mBluetoothGatt.remove(str);
            return false;
        }
        this.mBluetoothGatt.put(str, bluetoothGattConnectGatt);
        return true;
    }

    public void disconnectDevice(String str) {
        try {
            this.connecting = false;
            this.mIsConnected = false;
            XLog.i(str + "  gatt map size:" + this.mBluetoothGatt.size());
            for (final BluetoothGatt bluetoothGatt : this.mBluetoothGatt.values()) {
                if (bluetoothGatt != null) {
                    XLog.i("gatt disconnect it");
                    if (Build.VERSION.SDK_INT >= 31 && ActivityCompat.checkSelfPermission(this.mContext, Permission.BLUETOOTH_CONNECT) != 0) {
                        return;
                    }
                    bluetoothGatt.disconnect();
                    this.mHandler.postDelayed(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.5
                        @Override // java.lang.Runnable
                        public void run() throws NoSuchMethodException, SecurityException {
                            BleBaseControl.this.refreshDeviceCache(bluetoothGatt);
                            if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(BleBaseControl.this.mContext, Permission.BLUETOOTH_CONNECT) == 0) {
                                bluetoothGatt.close();
                            }
                        }
                    }, 500L);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BluetoothGatt getGatt(String str) {
        BluetoothGatt bluetoothGatt;
        if (!this.mBluetoothGatt.containsKey(str) || (bluetoothGatt = this.mBluetoothGatt.get(str)) == null) {
            return null;
        }
        return bluetoothGatt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMyAll() {
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitFor(long j) {
        synchronized (this.mLock) {
            try {
                this.mLock.wait(j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean refreshDeviceCache(BluetoothGatt bluetoothGatt) throws NoSuchMethodException, SecurityException {
        if (!BluetoothUtils.isEnabledBluetooth(this.mContext)) {
            return false;
        }
        try {
            Method method = BluetoothGatt.class.getMethod("refresh", new Class[0]);
            if (method != null) {
                boolean zBooleanValue = ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                XLog.e("Refreshing result: " + zBooleanValue);
                return zBooleanValue;
            }
        } catch (Exception e) {
            XLog.e("An exception occured while refreshing device " + e.toString());
        }
        return false;
    }

    public void setNeedReconnect(boolean z) {
        this.isNeedReconnect = z;
    }

    public void setmDeviceAddress(String str) {
        this.mDeviceAddress = str;
    }

    public String getmDeviceAddress() {
        return this.mDeviceAddress;
    }

    public IBleListener getListener() {
        return this.listener;
    }

    public void setListener(IBleListener iBleListener) {
        this.listener = iBleListener;
    }

    public boolean isConnecting() {
        return this.connecting;
    }

    public void setRtkBindTag(Boolean bool) {
        this.rtkBindTag = bool;
    }

    public BluetoothGattCharacteristic findTheGattCharacteristic(UUID uuid, UUID uuid2) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.cacheGattCharacteristicHashMap.get(uuid2);
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristicInitTheCharacteristic = initTheCharacteristic(uuid, uuid2);
        if (bluetoothGattCharacteristicInitTheCharacteristic != null) {
            this.cacheGattCharacteristicHashMap.put(uuid2, bluetoothGattCharacteristicInitTheCharacteristic);
        }
        return bluetoothGattCharacteristicInitTheCharacteristic;
    }

    private BluetoothGattCharacteristic initTheCharacteristic(UUID uuid, UUID uuid2) {
        BluetoothGatt gatt;
        if (!BleOperateManager.getInstance().isConnected()) {
            return null;
        }
        String str = getInstance().getmDeviceAddress();
        if (TextUtils.isEmpty(str) || (gatt = getInstance().getGatt(str)) == null) {
            return null;
        }
        BluetoothGattService service = gatt.getService(uuid);
        if (service == null) {
            Log.e(TAG, "initTheCharacteristic: can't find service uuid=" + uuid);
            return null;
        }
        return service.getCharacteristic(uuid2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsNotifyConfigAndRegisterCallback(BluetoothGattDescriptor bluetoothGattDescriptor, BluetoothGatt bluetoothGatt) {
        byte[] value;
        IBleListener iBleListener;
        if (bluetoothGattDescriptor.getUuid().compareTo(Constants.GATT_NOTIFY_CONFIG) == 0 && (value = bluetoothGattDescriptor.getValue()) != null && value.length == 2 && value[1] == 0 && value[0] == 1 && (iBleListener = this.listener) != null) {
            iBleListener.bleCharacteristicNotification();
        }
    }

    public void unBondedDevice(String str) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
            XLog.i(remoteDevice);
            Method method = null;
            try {
                try {
                    method = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
                    method.invoke(remoteDevice, new Object[0]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
            method.setAccessible(true);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public boolean classicBluetoothScan() {
        try {
            if (this.mBluetoothAdapter.isDiscovering()) {
                this.mBluetoothAdapter.cancelDiscovery();
            }
            XLog.i("------扫描");
            return this.mBluetoothAdapter.startDiscovery();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelScanBluetooth() {
        try {
            return this.mBluetoothAdapter.cancelDiscovery();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createBondBlueTooth(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        try {
            if (this.mBluetoothAdapter.isDiscovering()) {
                this.mBluetoothAdapter.cancelDiscovery();
            }
            if (bluetoothDevice.getBondState() == 10) {
                try {
                    XLog.e("是否配对成功：" + bluetoothDevice.createBond());
                } catch (Exception e) {
                    e.printStackTrace();
                    XLog.e("配对失败：");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean createBond(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            Method declaredMethod = bluetoothDevice.getClass().getDeclaredMethod("createBond", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(bluetoothDevice, Integer.valueOf(i));
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createBond(Context context, BluetoothDevice bluetoothDevice) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (bluetoothDevice == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 20) {
            return bluetoothDevice.createBond();
        }
        try {
            Object objInvoke = bluetoothDevice.getClass().getMethod("createBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
            if (objInvoke instanceof Boolean) {
                return ((Boolean) objInvoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            XLog.e("Invoke createBond : " + e.getMessage());
            return false;
        }
    }

    public void setReconnectStartOrNot(boolean z) {
        boolean z2 = false;
        if (z && this.failTotal > 10) {
            this.failTotal = 0;
        }
        if (this.failTotal < 10 && z) {
            z2 = true;
        }
        setNeedReconnect(z2);
    }
}
