package com.oudmon.ble.base.bluetooth;

import android.annotation.SuppressLint;
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
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.scan.BleScannerCompat;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.scan.OnTheScanResult;
import com.oudmon.ble.base.util.BluetoothUtils;
import com.oudmon.ble.base.util.DateUtil;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/BleBaseControl.class */
public class BleBaseControl {
    private static final boolean debug = false;
    private static final String TAG = "QCWX";
    private static BleBaseControl bleBaseControl;
    private static final int GATT_CLOSE_DELAY_MILLIS = 1000;
    private Context mContext;
    private BluetoothAdapter mBluetoothAdapter;
    private String mDeviceAddress;
    private volatile boolean mIsConnected;
    private volatile boolean connecting;
    private IBleListener listener;
    BluetoothDevice bleConnectDevice;
    private final Object mLock = new Object();
    protected Map<String, BluetoothGatt> mBluetoothGatt = new HashMap();
    private boolean isNeedReconnect = true;
    private int maxReconnect = 4;
    private int maxFail = 5;
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger failCount = new AtomicInteger(0);
    private Handler mHandler = new Handler(Looper.getMainLooper());
    String logPath = Environment.getExternalStorageDirectory() + "/QC/" + new DateUtil().getY_M_D() + "ble_status.txt";
    String notifyPath = Environment.getExternalStorageDirectory() + "/QC/" + new DateUtil().getY_M_D() + "ble_notify.txt";
    String writePath = Environment.getExternalStorageDirectory() + "/QC/" + new DateUtil().getY_M_D() + "ble_write.txt";
    private boolean bluetoothTurnOff = false;
    private HashMap<UUID, BluetoothGattCharacteristic> cacheGattCharacteristicHashMap = new HashMap<>();
    private Boolean rtkBindTag = false;
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.i(Constants.TAG, "onConnectionStateChange-->status = [" + status + "], newState = [" + newState + "]");
            String address = gatt.getDevice().getAddress();
            BleBaseControl.this.bleConnectDevice = gatt.getDevice();
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mDiscoverServiceTimeoutRunnable);
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mTimeoutRunnable);
            BleThreadManager.getInstance().clean();
            if (status != 0) {
                BleBaseControl.this.notifyMyAll();
                BleBaseControl.this.mIsConnected = false;
                BleBaseControl.this.disconnectDevice(address);
                BleBaseControl.this.failCount.incrementAndGet();
                try {
                    if (!BleBaseControl.this.rtkBindTag.booleanValue()) {
                        BleBaseControl.this.unBondedDevice(address);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (BleBaseControl.this.listener != null) {
                    BleBaseControl.this.listener.bleGattDisconnect(gatt.getDevice());
                }
                BleBaseControl.this.reconnectFromStateChange();
                return;
            }
            if (newState != 2) {
                if (newState == 0) {
                    BleBaseControl.this.mIsConnected = false;
                    BleBaseControl.this.notifyMyAll();
                    BleBaseControl.this.disconnectDevice(address);
                    BleBaseControl.this.reconnectDevice();
                    if (BleBaseControl.this.listener != null) {
                        BleBaseControl.this.listener.bleGattDisconnect(gatt.getDevice());
                        return;
                    }
                    return;
                }
                return;
            }
            BleBaseControl.this.cacheGattCharacteristicHashMap.clear();
            BleBaseControl.this.notifyMyAll();
            BleBaseControl.this.count.getAndSet(0);
            BleBaseControl.this.failCount.getAndSet(0);
            BleBaseControl.this.mIsConnected = true;
            BleBaseControl.this.bluetoothTurnOff = false;
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mReconnectRunnable);
            BleBaseControl.this.waitFor(500L);
            boolean discoverServices = gatt.discoverServices();
            if (discoverServices) {
                BleBaseControl.this.mHandler.postDelayed(BleBaseControl.this.mDiscoverServiceTimeoutRunnable, 40000L);
            }
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleGattConnected(gatt.getDevice());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            String address = gatt.getDevice().getAddress();
            BleBaseControl.this.mHandler.removeCallbacks(BleBaseControl.this.mDiscoverServiceTimeoutRunnable);
            if (status == 0) {
                BleBaseControl.this.connecting = false;
                if (BleBaseControl.this.listener != null) {
                    BleBaseControl.this.listener.bleServiceDiscovered(status, address);
                    return;
                }
                return;
            }
            BleBaseControl.this.disconnectDevice(address);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleCharacteristicWrite(gatt.getDevice().getAddress(), characteristic.getUuid().toString(), status, characteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleCharacteristicRead(gatt.getDevice().getAddress(), characteristic.getUuid().toString(), status, characteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.bleCharacteristicChanged(gatt.getDevice().getAddress(), characteristic.getUuid().toString(), characteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.onDescriptorWrite(gatt, descriptor, status);
            }
            if (status == 0) {
                BleBaseControl.this.checkIsNotifyConfigAndRegisterCallback(descriptor, gatt);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.onDescriptorRead(gatt, descriptor, status);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            if (BleBaseControl.this.listener != null) {
                BleBaseControl.this.listener.onReadRemoteRssi(gatt, rssi, status);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            Log.i(Constants.TAG, mtu + "");
        }
    };
    private Runnable mDiscoverServiceTimeoutRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.4
        @Override // java.lang.Runnable
        public void run() {
            BleBaseControl.this.disconnectDevice(BleBaseControl.this.mDeviceAddress);
        }
    };
    private Runnable mTimeoutRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.6
        @Override // java.lang.Runnable
        public void run() {
            BleBaseControl.this.mIsConnected = false;
            BleBaseControl.this.connecting = false;
            Log.i(Constants.TAG, "没有收到系统回调，直接断开");
            BleBaseControl.this.disconnectDevice(BleBaseControl.this.mDeviceAddress);
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
                Log.i(Constants.TAG, "正在重连,重连次数：" + BleBaseControl.this.count.get());
                BleBaseControl.this.connect(BleBaseControl.this.mDeviceAddress);
            } else {
                BleBaseControl.this.connecting = false;
                Log.i(Constants.TAG, "超出了重连次数:" + BleBaseControl.this.count.get());
            }
        }
    };

    public void setBluetoothTurnOff(boolean bluetoothTurnOff) {
        this.bluetoothTurnOff = bluetoothTurnOff;
    }

    public void setRtkBindTag(Boolean rtkBindTag) {
        this.rtkBindTag = rtkBindTag;
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

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private BleBaseControl(Context context) {
        this.mContext = context;
        initialize();
    }

    private void initialize() {
        if (this.mContext == null) {
            Log.e(TAG, "mContext==null,请检查Application ");
            Log.e(TAG, "Unable to initialize BluetoothManager...");
            return;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
        if (bluetoothManager != null) {
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
        } else {
            Log.e(TAG, "Unable to initialize BluetoothManager...");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnectFromStateChange() {
        this.mHandler.postDelayed(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.2
            @Override // java.lang.Runnable
            public void run() {
                BleBaseControl.this.reconnectDevice();
            }
        }, 1000L);
    }

    public void reconnectOpeningUp() {
        if (this.count.get() >= this.maxReconnect) {
            this.count.getAndSet(0);
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
            if (!BluetoothUtils.isEnabledBluetooth(this.mContext) || TextUtils.isEmpty(this.mDeviceAddress)) {
                this.mIsConnected = false;
                this.connecting = false;
                return;
            }
            if (isConnecting() || ismIsConnected()) {
                Log.i(Constants.TAG, "正在连接:" + isConnecting() + " 已经连上:" + ismIsConnected());
                return;
            }
            this.mHandler.removeCallbacks(this.mReconnectRunnable);
            if (this.count.get() % 2 == 1 && this.failCount.get() <= this.maxFail && !this.bluetoothTurnOff) {
                this.mHandler.postDelayed(this.mReconnectRunnable, 200L);
            } else if (BleScannerCompat.getScanner(this.mContext).isScanning()) {
            } else {
                BleScannerHelper.getInstance().scanTheDevice(this.mContext, this.mDeviceAddress, new OnTheScanResult() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.3
                    @Override // com.oudmon.ble.base.scan.OnTheScanResult
                    public void onResult(BluetoothDevice bluetoothDevice) {
                        if (bluetoothDevice != null) {
                            if (BleBaseControl.this.count.get() >= BleBaseControl.this.maxReconnect) {
                                BleBaseControl.this.count.getAndSet(0);
                            }
                            BleBaseControl.this.mHandler.postDelayed(BleBaseControl.this.mReconnectRunnable, 200L);
                        } else {
                            BleBaseControl.this.connecting = false;
                            BleBaseControl.this.failCount.incrementAndGet();
                            if (BleBaseControl.this.count.get() < BleBaseControl.this.maxReconnect) {
                                BleBaseControl.this.count.incrementAndGet();
                            }
                        }
                    }

                    @Override // com.oudmon.ble.base.scan.OnTheScanResult
                    public void onScanFailed(int errorCode) {
                        BleBaseControl.this.connecting = false;
                        BleBaseControl.this.failCount.incrementAndGet();
                        if (BleBaseControl.this.count.get() < BleBaseControl.this.maxReconnect) {
                            BleBaseControl.this.count.incrementAndGet();
                        }
                    }
                });
            }
        }
    }

    public boolean connect(String address) {
        BluetoothGatt gatt;
        if (!BluetoothUtils.isEnabledBluetooth(this.mContext)) {
            this.connecting = false;
            this.isNeedReconnect = false;
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            this.mIsConnected = false;
            this.connecting = false;
            return false;
        }
        if (isConnecting() || ismIsConnected()) {
            Log.i(Constants.TAG, "再次检查的时候返回了");
            return false;
        }
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mHandler.postDelayed(this.mTimeoutRunnable, 40000L);
        this.connecting = true;
        this.isNeedReconnect = true;
        BleScannerHelper.getInstance().stopScan(this.mContext);
        this.mDeviceAddress = address;
        BluetoothDevice device = this.mBluetoothAdapter.getRemoteDevice(address);
        Log.i(Constants.TAG, "---------------【开始GATT连接】--------------");
        if (this.listener != null) {
            this.listener.startConnect();
        }
        if (Build.VERSION.SDK_INT > 23) {
            gatt = device.connectGatt(this.mContext, false, this.mGattCallback, 2);
        } else {
            gatt = device.connectGatt(this.mContext, false, this.mGattCallback);
        }
        if (gatt == null) {
            this.mBluetoothGatt.remove(address);
            return false;
        }
        this.mBluetoothGatt.put(address, gatt);
        return true;
    }

    public void disconnectDevice(String address) {
        final BluetoothGatt gatt;
        try {
            this.connecting = false;
            this.mIsConnected = false;
            Log.i(Constants.TAG, address + "  gatt map size:" + this.mBluetoothGatt.size());
            if (this.mBluetoothGatt.containsKey(address) && (gatt = this.mBluetoothGatt.get(address)) != null) {
                Log.i(Constants.TAG, "gatt disconnect it");
                gatt.disconnect();
                this.mHandler.postDelayed(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleBaseControl.5
                    @Override // java.lang.Runnable
                    public void run() throws NoSuchMethodException, SecurityException {
                        BleBaseControl.this.refreshDeviceCache(gatt);
                        gatt.close();
                    }
                }, 1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BluetoothGatt getGatt(String address) {
        BluetoothGatt gatt;
        if (!this.mBluetoothGatt.containsKey(address) || (gatt = this.mBluetoothGatt.get(address)) == null) {
            return null;
        }
        return gatt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMyAll() {
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitFor(long time) {
        synchronized (this.mLock) {
            try {
                this.mLock.wait(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unBondedDevice(String mac) {
    }

    protected boolean refreshDeviceCache(BluetoothGatt gatt) throws NoSuchMethodException, SecurityException {
        if (!BluetoothUtils.isEnabledBluetooth(this.mContext)) {
            return false;
        }
        try {
            Method refresh = BluetoothGatt.class.getMethod("refresh", new Class[0]);
            if (refresh != null) {
                boolean success = ((Boolean) refresh.invoke(gatt, new Object[0])).booleanValue();
                Log.i(Constants.TAG, "Refreshing result: " + success);
                return success;
            }
            return false;
        } catch (Exception e) {
            Log.i(Constants.TAG, "An exception occured while refreshing device " + e.toString());
            return false;
        }
    }

    public void setNeedReconnect(boolean needReconnect) {
        this.isNeedReconnect = needReconnect;
    }

    public void setmDeviceAddress(String mDeviceAddress) {
        this.mDeviceAddress = mDeviceAddress;
    }

    public String getmDeviceAddress() {
        return this.mDeviceAddress;
    }

    public IBleListener getListener() {
        return this.listener;
    }

    public void setListener(IBleListener listener) {
        this.listener = listener;
    }

    public boolean isConnecting() {
        return this.connecting;
    }

    public BluetoothGattCharacteristic findTheGattCharacteristic(UUID serviceUuid, UUID charUuid) {
        BluetoothGattCharacteristic charCharacteristic = this.cacheGattCharacteristicHashMap.get(charUuid);
        if (charCharacteristic == null) {
            BluetoothGattCharacteristic charCharacteristic2 = initTheCharacteristic(serviceUuid, charUuid);
            if (charCharacteristic2 != null) {
                this.cacheGattCharacteristicHashMap.put(charUuid, charCharacteristic2);
            }
            return charCharacteristic2;
        }
        return charCharacteristic;
    }

    private BluetoothGattCharacteristic initTheCharacteristic(UUID serviceUuid, UUID charUuid) {
        BluetoothGatt gatt;
        if (!BleOperateManager.getInstance().isConnected()) {
            return null;
        }
        String address = getInstance().getmDeviceAddress();
        if (TextUtils.isEmpty(address) || (gatt = getInstance().getGatt(address)) == null) {
            return null;
        }
        BluetoothGattService service = gatt.getService(serviceUuid);
        if (service == null) {
            Log.e(TAG, "initTheCharacteristic: can't find service uuid=" + serviceUuid);
            return null;
        }
        BluetoothGattCharacteristic charCharacteristic = service.getCharacteristic(charUuid);
        return charCharacteristic;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsNotifyConfigAndRegisterCallback(BluetoothGattDescriptor descriptor, BluetoothGatt gatt) {
        byte[] value;
        if (descriptor.getUuid().compareTo(Constants.GATT_NOTIFY_CONFIG) == 0 && (value = descriptor.getValue()) != null && value.length == 2 && value[1] == 0 && value[0] == 1 && this.listener != null) {
            this.listener.bleCharacteristicNotification();
        }
    }

    public boolean classicBluetoothScan() {
        try {
            if (this.mBluetoothAdapter.isDiscovering()) {
                this.mBluetoothAdapter.cancelDiscovery();
            }
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

    @SuppressLint({"DiscouragedPrivateApi"})
    public boolean createBond(BluetoothDevice device, int transport) {
        Object object;
        if (null != device) {
            boolean bRet = false;
            try {
                Class<?> bluetoothDeviceClass = device.getClass();
                Method createBondMethod = bluetoothDeviceClass.getDeclaredMethod("createBond", Integer.TYPE);
                createBondMethod.setAccessible(true);
                object = createBondMethod.invoke(device, Integer.valueOf(transport));
            } catch (Exception var7) {
                var7.printStackTrace();
            }
            if (!(object instanceof Boolean)) {
                return false;
            }
            bRet = ((Boolean) object).booleanValue();
            return bRet;
        }
        return false;
    }

    public void bleCreateBond() {
        try {
            if (this.bleConnectDevice != null) {
                BluetoothDevice device = getMacSystemBond(this.bleConnectDevice.getAddress());
                if (this.bleConnectDevice.getBondState() == 10 && device == null) {
                    this.bleConnectDevice.createBond();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BluetoothDevice getMacSystemBond(String address) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> devices1 = adapter.getBondedDevices();
        if (devices1.size() > 0) {
            for (BluetoothDevice bluetoothDevice : devices1) {
                if (bluetoothDevice != null && bluetoothDevice.getName() != null && bluetoothDevice.getAddress() != null && !TextUtils.isEmpty(address) && address.equalsIgnoreCase(bluetoothDevice.getAddress())) {
                    return bluetoothDevice;
                }
            }
            return null;
        }
        return null;
    }

    public void createBondBlueTooth(BluetoothDevice device) {
        if (device == null) {
            return;
        }
        try {
            if (this.mBluetoothAdapter.isDiscovering()) {
                this.mBluetoothAdapter.cancelDiscovery();
            }
            if (device.getBondState() == 10) {
                try {
                    device.createBond();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
