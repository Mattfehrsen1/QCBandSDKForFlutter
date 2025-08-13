package com.oudmon.ble.base.bluetooth;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback;
import com.oudmon.ble.base.bluetooth.spp.RtkMusicSpp;
import com.oudmon.ble.base.bluetooth.spp.RtkSppConstants;
import com.oudmon.ble.base.bluetooth.spp.bean.MyDeviceInfo;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.AppRevisionReq;
import com.oudmon.ble.base.communication.req.AppRevisionResp;
import com.oudmon.ble.base.communication.req.BindAncsReq;
import com.oudmon.ble.base.communication.req.SetTimeReq;
import com.oudmon.ble.base.communication.req.StartHeartRateReq;
import com.oudmon.ble.base.communication.req.StopHeartRateReq;
import com.oudmon.ble.base.communication.responseImpl.DeviceNotifyListener;
import com.oudmon.ble.base.communication.responseImpl.DeviceSportNotifyListener;
import com.oudmon.ble.base.communication.responseImpl.InnerCameraNotifyListener;
import com.oudmon.ble.base.communication.responseImpl.PackageLengthListener;
import com.oudmon.ble.base.communication.rsp.CameraNotifyRsp;
import com.oudmon.ble.base.communication.rsp.StartCalcDataRsp;
import com.oudmon.ble.base.communication.rsp.StartHeartRateRsp;
import com.oudmon.ble.base.communication.rsp.StopHeartRateRsp;
import com.oudmon.ble.base.request.BaseRequest;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.LocalWriteRequest;
import com.oudmon.ble.base.scan.BleScannerHelper;
import com.oudmon.ble.base.util.BluetoothUtils;
import com.realsil.customer.audioconnect.localplayback.LocalPlaybackModelClient;
import com.realsil.customer.bbpro.BeeProManager;
import com.realsil.customer.bbpro.BeeProParams;
import com.realsil.customer.bbpro.BumblebeeCallback;
import com.realsil.customer.bbpro.ConnectionParameters;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.core.RtkConfigure;
import com.realsil.customer.core.RtkCore;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/BleOperateManager.class */
public class BleOperateManager extends HandlerThread implements IBleListener {
    private static final String TAG = "BleOperateManager";
    private final Handler mainThreadHandler;
    private final Handler myHandler;
    private Handler workThreadHandler;
    private final Object mLock;
    private boolean mRequestCompleted;
    private final Context mContext;
    private ConcurrentHashMap<Integer, LocalWriteRequest> localWriteRequestConcurrentHashMap;
    private SparseArray<ICommandResponse> notifySparseArray;
    private InnerCameraNotifyListener innerCameraNotifyListener;
    private OnGattEventCallback callback;
    private String reConnectMac;
    private DeviceNotifyListener deviceNotifyListener;
    private DeviceSportNotifyListener deviceSportNotifyListener;
    private MyBumblebeeCallback mySppCallback;
    private Application application;
    ICommandResponse<StartHeartRateRsp> response;
    ICommandResponse<StartCalcDataRsp> calcResponse;
    Runnable runnable;
    Runnable runnableEnable;
    private BumblebeeCallback sppCallback;
    public byte heartValue;
    public byte sbp;
    public byte dbp;
    public byte spo2;
    public byte pressure;
    public int hrv;
    public int temperature;
    public int rri;
    Runnable heartRunnable;
    Runnable bpRunnable;
    Runnable spo2Runnable;
    Runnable temperatureRunnable;
    Runnable pressureRunnable;
    Runnable hrvRunnable;
    ICommandResponse<StopHeartRateRsp> ringCallback;
    private OneKeyResp oneKeyCallback;
    private int lastFatigueValue;
    private int lastFatigueTime;
    private int[] spo2Array;
    ICommandResponse<AppRevisionResp> ringAppRevisionCallback;
    Runnable timeout;
    private static BleOperateManager bleOperateManager = null;
    private static Random mRandom = new Random();

    public static BleOperateManager getInstance(Application application) {
        if (bleOperateManager == null) {
            synchronized (BleOperateManager.class) {
                if (bleOperateManager == null) {
                    bleOperateManager = new BleOperateManager(application);
                }
            }
        }
        return bleOperateManager;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getReConnectMac() {
        return this.reConnectMac;
    }

    public void setReConnectMac(String reConnectMac) {
        this.reConnectMac = reConnectMac;
    }

    public void setCallback(OnGattEventCallback callback) {
        this.callback = callback;
    }

    public static BleOperateManager getInstance() {
        return bleOperateManager;
    }

    private BleOperateManager(Application context) {
        super(TAG);
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.myHandler = new Handler(Looper.getMainLooper());
        this.mLock = new Object();
        this.mRequestCompleted = false;
        this.localWriteRequestConcurrentHashMap = new ConcurrentHashMap<>();
        this.notifySparseArray = new SparseArray<>();
        this.deviceNotifyListener = new DeviceNotifyListener();
        this.deviceSportNotifyListener = new DeviceSportNotifyListener();
        this.runnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.2
            @Override // java.lang.Runnable
            public void run() {
                BleOperateManager.this.mRequestCompleted = true;
                BleOperateManager.this.notifyLock();
            }
        };
        this.runnableEnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.6
            @Override // java.lang.Runnable
            public void run() {
                BleOperateManager.this.mRequestCompleted = true;
                BleOperateManager.this.notifyLock();
                BleOperateManager.this.enableUUID();
            }
        };
        this.sppCallback = new BumblebeeCallback() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.7
            @Override // com.realsil.customer.bbpro.BumblebeeCallback
            public void onStateChanged(int i) {
                super.onStateChanged(i);
                if (BleOperateManager.this.mySppCallback != null) {
                    int state = BeeProManager.getInstance(BleOperateManager.this.application).getState();
                    BleOperateManager.this.mySppCallback.onStateChanged(state);
                }
            }

            @Override // com.realsil.customer.bbpro.BumblebeeCallback
            public void onServiceConnectionStateChanged(boolean b) {
                super.onServiceConnectionStateChanged(b);
                if (BleOperateManager.this.mySppCallback != null) {
                    BleOperateManager.this.mySppCallback.onServiceConnectionStateChanged(b);
                }
            }

            @Override // com.realsil.customer.bbpro.BumblebeeCallback
            public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i1) {
                super.onConnectionStateChanged(bluetoothDevice, i, i1);
                if (BleOperateManager.this.mySppCallback != null) {
                    BleOperateManager.this.mySppCallback.onConnectionStateChanged(bluetoothDevice, i, i1);
                }
            }

            @Override // com.realsil.customer.bbpro.BumblebeeCallback
            public void onDeviceInfoChanged(DeviceInfo deviceInfo, int i) {
                super.onDeviceInfoChanged(deviceInfo, i);
                if (BleOperateManager.this.mySppCallback != null) {
                    BleOperateManager.this.mySppCallback.onDeviceInfoChanged((MyDeviceInfo) deviceInfo, i);
                }
            }

            @Override // com.realsil.customer.bbpro.BumblebeeCallback
            public void onAckReceived(int i, byte b) {
                super.onAckReceived(i, b);
                if (BleOperateManager.this.mySppCallback != null) {
                    BleOperateManager.this.mySppCallback.onAckReceived(i, b);
                }
            }

            @Override // com.realsil.customer.bbpro.BumblebeeCallback
            public void onEventReported(int i, byte[] bytes) {
                super.onEventReported(i, bytes);
                if (BleOperateManager.this.mySppCallback != null) {
                    BleOperateManager.this.mySppCallback.onEventReported(i, bytes);
                }
            }
        };
        this.heartValue = (byte) 85;
        this.sbp = (byte) 80;
        this.dbp = (byte) 120;
        this.spo2 = (byte) 99;
        this.pressure = (byte) 42;
        this.hrv = -104;
        this.temperature = 252;
        this.rri = 0;
        this.heartRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.9
            @Override // java.lang.Runnable
            public void run() {
                if (BleOperateManager.this.response != null) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode((byte) 0);
                    resp.setValue(BleOperateManager.this.heartValue);
                    resp.setRri(BleOperateManager.this.rri);
                    BleOperateManager.this.response.onDataResponse(resp);
                }
                CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopHeartRate(BleOperateManager.this.heartValue), null);
            }
        };
        this.bpRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.10
            @Override // java.lang.Runnable
            public void run() {
                if (BleOperateManager.this.response != null) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode((byte) 0);
                    resp.setSbp(BleOperateManager.this.sbp);
                    resp.setDbp(BleOperateManager.this.dbp);
                    BleOperateManager.this.response.onDataResponse(resp);
                }
                CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopBloodPressure(BleOperateManager.this.sbp, BleOperateManager.this.dbp), null);
            }
        };
        this.spo2Runnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.11
            @Override // java.lang.Runnable
            public void run() {
                if (BleOperateManager.this.response != null) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode((byte) 0);
                    resp.setValue(BleOperateManager.this.spo2);
                    BleOperateManager.this.response.onDataResponse(resp);
                }
                CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopBloodOxygen(BleOperateManager.this.spo2), null);
            }
        };
        this.temperatureRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.12
            @Override // java.lang.Runnable
            public void run() {
                if (BleOperateManager.this.response != null) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode((byte) 0);
                    resp.setValue(BleOperateManager.this.temperature);
                    BleOperateManager.this.response.onDataResponse(resp);
                }
                CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopTemperatureCheck(), null);
            }
        };
        this.pressureRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.13
            @Override // java.lang.Runnable
            public void run() {
                if (BleOperateManager.this.response != null) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode((byte) 0);
                    resp.setValue(BleOperateManager.this.pressure);
                    BleOperateManager.this.response.onDataResponse(resp);
                }
                CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopPressure(BleOperateManager.this.pressure), null);
            }
        };
        this.hrvRunnable = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.14
            @Override // java.lang.Runnable
            public void run() {
                if (BleOperateManager.this.response != null) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode((byte) 0);
                    resp.setValue(BleOperateManager.this.hrv);
                    resp.setRri(BleOperateManager.this.rri);
                    BleOperateManager.this.response.onDataResponse(resp);
                }
                CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopHrv((byte) BleOperateManager.this.hrv), null);
            }
        };
        this.ringCallback = new ICommandResponse<StopHeartRateRsp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.15
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(StopHeartRateRsp resultEntity) {
                if (resultEntity.getErrCode() == 0 && resultEntity.getType() == 2) {
                    if (resultEntity.getSbp() > 0) {
                        BleOperateManager.this.sbp = (byte) resultEntity.getSbp();
                        BleOperateManager.this.dbp = (byte) resultEntity.getDbp();
                        return;
                    }
                    return;
                }
                if (resultEntity.getErrCode() != 0) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode(resultEntity.getErrCode());
                    BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.bpRunnable);
                }
            }
        };
        this.oneKeyCallback = new OneKeyResp();
        this.spo2Array = new int[]{96, 97, 98, 99};
        this.timeout = new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.21
            @Override // java.lang.Runnable
            public void run() {
                CommandHandle.getInstance().executeReqCmdNoCallback(AppRevisionReq.getWriteInstance(2));
                BleOperateManager.this.ringAppRevisionCallback.onDataResponse(new AppRevisionResp(3));
            }
        };
        this.mContext = context;
        this.application = context;
        BleBaseControl.getInstance(context).setListener(this);
        this.innerCameraNotifyListener = new InnerCameraNotifyListener(context);
        this.notifySparseArray.put(47, new PackageLengthListener());
        this.notifySparseArray.put(115, this.deviceNotifyListener);
        this.notifySparseArray.put(120, this.deviceSportNotifyListener);
        start();
        this.workThreadHandler = new Handler(getLooper());
    }

    public ConcurrentHashMap<Integer, LocalWriteRequest> getLocalWriteRequestConcurrentHashMap() {
        return this.localWriteRequestConcurrentHashMap;
    }

    public void addSportDeviceListener(int type, ICommandResponse outRspIOdmOpResponse) {
        this.deviceSportNotifyListener.setOutRspIOdmOpResponse(type, outRspIOdmOpResponse);
    }

    public void removeSportDeviceListener(int key) {
        this.deviceSportNotifyListener.removeCallback(key);
    }

    public void setBluetoothTurnOff(boolean onOrOff) {
        BleBaseControl.getInstance().setBluetoothTurnOff(onOrOff);
    }

    public void addOutCameraListener(ICommandResponse<CameraNotifyRsp> outRspIOdmOpResponse) {
        this.innerCameraNotifyListener.setOutRspIOdmOpResponse(outRspIOdmOpResponse);
    }

    public void removeOutCameraListener() {
        this.innerCameraNotifyListener.setOutRspIOdmOpResponse(null);
    }

    public boolean addNotifyListener(int key, ICommandResponse iOpResponse) {
        if (iOpResponse == null) {
            return false;
        }
        this.notifySparseArray.put(key, iOpResponse);
        return true;
    }

    public void removeNotifyListener(int key) {
        this.notifySparseArray.delete(key);
    }

    public SparseArray<ICommandResponse> getNotifySparseArray() {
        return this.notifySparseArray;
    }

    public void unBindDevice() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        setNeedConnect(false);
        BleBaseControl.getInstance().unBondedDevice(BleBaseControl.getInstance().getmDeviceAddress());
        BleScannerHelper.getInstance().removeSystemBle();
        disconnect();
        setMacNull();
    }

    public void setMacNull() {
        BleBaseControl.getInstance().setmDeviceAddress(null);
    }

    public void connectDirectly(String macAddress) {
        BleBaseControl.getInstance().connect(macAddress);
    }

    public void connectWithScan(String macAddress) {
        if (TextUtils.isEmpty(macAddress)) {
            Log.i(Constants.TAG, "mac 地址为空");
        } else {
            if (TextUtils.isEmpty(this.reConnectMac)) {
                Log.i(Constants.TAG, "mac 地址为空");
                return;
            }
            BleBaseControl.getInstance().setNeedReconnect(true);
            BleBaseControl.getInstance().setmDeviceAddress(macAddress);
            BleBaseControl.getInstance().reconnectOpeningUp();
        }
    }

    public void disconnect() {
        Log.i(Constants.TAG, "disconnect...");
        BleBaseControl.getInstance().disconnectDevice(BleBaseControl.getInstance().getmDeviceAddress());
    }

    public void setNeedConnect(boolean needConnect) {
        BleBaseControl.getInstance().setNeedReconnect(needConnect);
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public boolean execute(final BaseRequest baseCharAction) {
        if (!BluetoothUtils.isEnabledBluetooth(this.mContext)) {
            Log.e(TAG, "connectDirectly: 蓝牙未打开");
            return false;
        }
        if (!BleBaseControl.getInstance().ismIsConnected()) {
            return false;
        }
        this.workThreadHandler.postDelayed(this.runnable, 5000L);
        this.workThreadHandler.post(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (BleOperateManager.this.mLock) {
                    try {
                        if (baseCharAction.needInitCharacteristic()) {
                            BluetoothGattCharacteristic charCharacteristic = BleBaseControl.getInstance().findTheGattCharacteristic(baseCharAction.getServiceUuid(), baseCharAction.getCharUuid());
                            if (charCharacteristic == null) {
                                return;
                            }
                            boolean result = baseCharAction.execute(BleBaseControl.getInstance().getGatt(BleBaseControl.getInstance().getmDeviceAddress()), charCharacteristic);
                            if (!result) {
                                BleOperateManager.this.notifyLock();
                            }
                        } else {
                            boolean result2 = baseCharAction.execute(BleBaseControl.getInstance().getGatt(BleBaseControl.getInstance().getmDeviceAddress()), null);
                            if (!result2) {
                                BleOperateManager.this.notifyLock();
                            }
                        }
                        BleOperateManager.this.mRequestCompleted = false;
                        if (BleBaseControl.getInstance().ismIsConnected()) {
                            BleOperateManager.this.waitUntilActionResponse();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitUntilActionResponse() throws InterruptedException {
        while (!this.mRequestCompleted) {
            try {
                this.mLock.wait();
            } catch (Exception e) {
                Log.e(TAG, "Sleeping interrupted", e);
                return;
            }
        }
    }

    protected void notifyLock() {
        this.workThreadHandler.removeCallbacks(this.runnable);
        synchronized (this.mLock) {
            this.mRequestCompleted = true;
            this.mLock.notifyAll();
        }
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        notifyLock();
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        notifyLock();
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public boolean isConnected() {
        return BleBaseControl.getInstance().ismIsConnected();
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
        notifyLock();
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void startConnect() {
        Intent intent = new Intent(BleAction.BLE_START_CONNECT);
        mySendBroadcast(intent);
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleGattConnected(BluetoothDevice device) {
        Intent intent = new Intent(BleAction.BLE_GATT_CONNECTED);
        intent.putExtra(BleAction.EXTRA_DEVICE, device);
        intent.putExtra(BleAction.EXTRA_ADDR, device.getAddress());
        mySendBroadcast(intent);
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleGattDisconnect(BluetoothDevice device) {
        Intent intent = new Intent(BleAction.BLE_GATT_DISCONNECTED);
        intent.putExtra(BleAction.EXTRA_DEVICE, device);
        intent.putExtra(BleAction.EXTRA_ADDR, device.getAddress());
        mySendBroadcast(intent);
        synchronized (this.mLock) {
            this.localWriteRequestConcurrentHashMap.clear();
            notifyLock();
        }
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleServiceDiscovered(int state, final String address) {
        enableUUID();
        this.mainThreadHandler.postDelayed(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.3
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent(BleAction.BLE_SERVICE_DISCOVERED);
                intent.putExtra(BleAction.EXTRA_ADDR, address);
                BleOperateManager.this.mySendBroadcast(intent);
            }
        }, 1000L);
        this.mainThreadHandler.postDelayed(new Runnable() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.4
            @Override // java.lang.Runnable
            public void run() {
                BleOperateManager.this.runCommonCmd();
            }
        }, 3000L);
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleCharacteristicRead(String address, String uuid, int status, byte[] value) {
        Intent intent = new Intent(BleAction.BLE_CHARACTERISTIC_READ);
        intent.putExtra(BleAction.EXTRA_ADDR, address);
        intent.putExtra(BleAction.EXTRA_CHARACTER_UUID, uuid);
        intent.putExtra(BleAction.EXTRA_STATUS, status);
        intent.putExtra(BleAction.EXTRA_VALUE, value);
        mySendBroadcast(intent);
        notifyLock();
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleCharacteristicNotification() {
        Intent intent = new Intent(BleAction.BLE_CHARACTERISTIC_DISCOVERED);
        mySendBroadcast(intent);
        notifyLock();
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleCharacteristicWrite(String address, String uuid, int status, byte[] data) {
        Intent intent = new Intent(BleAction.BLE_CHARACTERISTIC_WRITE);
        intent.putExtra(BleAction.EXTRA_ADDR, address);
        intent.putExtra(BleAction.EXTRA_CHARACTER_UUID, uuid);
        intent.putExtra(BleAction.EXTRA_STATUS, status);
        intent.putExtra(BleAction.EXTRA_DATA, data);
        mySendBroadcast(intent);
        notifyLock();
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleCharacteristicChanged(String address, String uuid, byte[] value) {
        Intent intent = new Intent(BleAction.BLE_CHARACTERISTIC_CHANGED);
        intent.putExtra(BleAction.EXTRA_ADDR, address);
        intent.putExtra(BleAction.EXTRA_CHARACTER_UUID, uuid);
        intent.putExtra(BleAction.EXTRA_VALUE, value);
        mySendBroadcast(intent);
        if (this.callback != null) {
            this.callback.onReceivedData(uuid, value);
        }
    }

    @Override // com.oudmon.ble.base.bluetooth.IBleListener
    public void bleNoCallback() {
        Intent intent = new Intent(BleAction.BLE_NO_CALLBACK);
        mySendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mySendBroadcast(Intent intent) {
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableUUID() {
        this.mainThreadHandler.removeCallbacks(this.runnableEnable);
        this.mainThreadHandler.postDelayed(this.runnableEnable, 4000L);
        EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(Constants.UUID_SERVICE, Constants.UUID_READ, new EnableNotifyRequest.ListenerCallback() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.5
            @Override // com.oudmon.ble.base.request.EnableNotifyRequest.ListenerCallback
            public void enable(boolean result) {
                BleOperateManager.this.mainThreadHandler.removeCallbacks(BleOperateManager.this.runnableEnable);
            }
        });
        enableNotifyRequest.setEnable(true);
        getInstance().execute(enableNotifyRequest);
    }

    public void init() {
        IntentFilter intentFilter = BleAction.getIntentFilter();
        QCBluetoothCallbackReceiver bluetoothDataParseReceiver = new QCBluetoothCallbackReceiver();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(bluetoothDataParseReceiver, intentFilter);
        QCBluetoothCallbackCloneReceiver cloneReceiver = new QCBluetoothCallbackCloneReceiver();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(cloneReceiver, intentFilter);
        QCBluetoothCallbackBigDataCloneReceiver bigDataClone = new QCBluetoothCallbackBigDataCloneReceiver();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(bigDataClone, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runCommonCmd() {
        CommandHandle.getInstance().execReadCmd(CommandHandle.getInstance().getReadHwRequest());
        CommandHandle.getInstance().execReadCmd(CommandHandle.getInstance().getReadFmRequest());
        CommandHandle.getInstance().executeReqCmd(new SetTimeReq(1), null);
        CommandHandle.getInstance().executeReqCmd(new BindAncsReq(), null);
    }

    public void classicBluetoothStartScan() {
        BleBaseControl.getInstance().classicBluetoothScan();
    }

    public void classicBluetoothStopScan() {
        BleBaseControl.getInstance().cancelScanBluetooth();
    }

    public void createBondBlueTooth(BluetoothDevice device) {
        BleBaseControl.getInstance().createBondBlueTooth(device);
    }

    public void createBondBluetoothJieLi(BluetoothDevice device) {
        BleBaseControl.getInstance().createBond(device, 1);
    }

    public void initRTKSPP(Application application) {
        RtkConfigure configure = new RtkConfigure.Builder().debugEnabled(true).printLog(true).logTag("AudioConnect").globalLogLevel(1).build();
        RtkCore.initialize(application, configure);
        RtkCore.VDBG = true;
        BeeProParams.Builder builder = new BeeProParams.Builder().syncDataWhenConnected(true).connectA2dp(true).listenHfp(true).uuid(RtkSppConstants.sppUUID).transport(1);
        BeeProManager.getInstance(application).initialize(builder.build());
        LocalPlaybackModelClient.initialize(application);
        BeeProManager.getInstance(application).registerModel(LocalPlaybackModelClient.getInstance());
    }

    public void connectRtkSPP(BluetoothDevice bluetoothDevice) {
        RtkMusicSpp.getInstance().initModelClient();
        ConnectionParameters params = new ConnectionParameters.Builder(bluetoothDevice).uuid(RtkSppConstants.sppUUID).transport(1).build();
        int beeError = BeeProManager.getInstance(this.application).startConnect(params);
        BeeProManager.getInstance(this.application).addManagerCallback(this.sppCallback);
        if (beeError != 0) {
        }
    }

    public void disconnectSpp() {
        int i = BeeProManager.getInstance(this.application).disconnect().code;
    }

    public BluetoothDevice getCurDevice() {
        return BeeProManager.getInstance(this.application).getCurDevice();
    }

    public int getConnectState() {
        return BeeProManager.getInstance(this.application).getConnectState();
    }

    public void registerSppCallback(MyBumblebeeCallback callback) {
        this.mySppCallback = callback;
    }

    public void removeSppCallback() {
        this.mySppCallback = null;
    }

    public void disconnectRtkSPP() {
        BeeProManager.getInstance(this.application).disconnect();
        BeeProManager.getInstance(this.application).destroy();
    }

    public void setRtkBindTag(Boolean rtkBindTag) {
        BleBaseControl.getInstance().setRtkBindTag(rtkBindTag);
    }

    public void manualModeHeart(final ICommandResponse<StartHeartRateRsp> response, boolean stop) {
        if (stop) {
            CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopHeartRate((byte) 0), null);
        }
        this.rri = 0;
        this.response = response;
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 1), new ICommandResponse<StopHeartRateRsp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.8
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(StopHeartRateRsp resultEntity) {
                if (resultEntity.getErrCode() != 0 || resultEntity.getType() != 1) {
                    BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.heartRunnable);
                    if (resultEntity.getErrCode() != 0) {
                        BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.heartRunnable);
                        StartHeartRateRsp resp = new StartHeartRateRsp();
                        resp.setErrCode(resultEntity.getErrCode());
                        if (response != null) {
                            response.onDataResponse(resp);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (resultEntity.getValue() > 0) {
                    BleOperateManager.this.heartValue = (byte) resultEntity.getValue();
                    if (response != null) {
                        StartHeartRateRsp resp2 = new StartHeartRateRsp();
                        resp2.setErrCode((byte) 0);
                        resp2.setValue(BleOperateManager.this.heartValue);
                        resp2.setRri(resultEntity.getRri());
                        response.onDataResponse(resp2);
                        return;
                    }
                    return;
                }
                if (BleOperateManager.this.rri > 0) {
                    BleOperateManager.this.rri = resultEntity.getRri();
                }
                if (response != null) {
                    StartHeartRateRsp resp3 = new StartHeartRateRsp();
                    resp3.setErrCode((byte) 0);
                    resp3.setValue(0);
                    resp3.setRri(BleOperateManager.this.rri);
                    response.onDataResponse(resp3);
                }
            }
        });
        this.myHandler.removeCallbacks(this.heartRunnable);
        this.myHandler.postDelayed(this.heartRunnable, 30000L);
    }

    public void manualModeBP(ICommandResponse<StartHeartRateRsp> response, boolean stop) {
        if (stop) {
            CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopBloodPressure((byte) 0, (byte) 0), null);
        }
        this.response = response;
        this.myHandler.removeCallbacks(this.bpRunnable);
        this.myHandler.postDelayed(this.bpRunnable, 30000L);
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 2), new ICommandResponse<StopHeartRateRsp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.16
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(StopHeartRateRsp resultEntity) {
                if (resultEntity.getStatus() == 0 && resultEntity.getType() == 2) {
                    if (resultEntity.getSbp() > 0) {
                        BleOperateManager.this.sbp = (byte) resultEntity.getSbp();
                        BleOperateManager.this.dbp = (byte) resultEntity.getDbp();
                        return;
                    }
                    return;
                }
                if (resultEntity.getErrCode() != 0) {
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode(resultEntity.getErrCode());
                    BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.bpRunnable);
                }
            }
        });
    }

    public void manualModeSpO2(final ICommandResponse<StartHeartRateRsp> response, boolean stop) {
        if (stop) {
            CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopBloodOxygen((byte) 0), null);
        }
        this.response = response;
        this.myHandler.removeCallbacks(this.spo2Runnable);
        this.myHandler.postDelayed(this.spo2Runnable, 30000L);
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 3), new ICommandResponse<StopHeartRateRsp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.17
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(StopHeartRateRsp resultEntity) {
                if (resultEntity.getErrCode() == 0 && resultEntity.getType() == 3) {
                    BleOperateManager.this.spo2 = (byte) resultEntity.getValue();
                } else if (resultEntity.getErrCode() != 0) {
                    BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.spo2Runnable);
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode(resultEntity.getErrCode());
                    if (response != null) {
                        response.onDataResponse(resp);
                    }
                }
            }
        });
    }

    public void manualTemperature(final ICommandResponse<StartHeartRateRsp> response, boolean stop) {
        if (stop) {
            CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopTemperatureCheck(), null);
        }
        this.response = response;
        this.myHandler.removeCallbacks(this.temperatureRunnable);
        this.myHandler.postDelayed(this.temperatureRunnable, 30000L);
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 11), new ICommandResponse<StopHeartRateRsp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.18
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(StopHeartRateRsp resultEntity) {
                if (resultEntity.getErrCode() == 0 && resultEntity.getType() == 11) {
                    BleOperateManager.this.temperature = resultEntity.getValue() + 200;
                } else if (resultEntity.getErrCode() != 0) {
                    BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.temperatureRunnable);
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode(resultEntity.getErrCode());
                    if (response != null) {
                        response.onDataResponse(resp);
                    }
                }
            }
        });
    }

    public void manualModePressure(final ICommandResponse<StartHeartRateRsp> response, boolean stop) {
        if (stop) {
            CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopPressure((byte) 0), null);
        }
        this.response = response;
        this.myHandler.removeCallbacks(this.pressureRunnable);
        this.myHandler.postDelayed(this.pressureRunnable, 30000L);
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 8), new ICommandResponse<StopHeartRateRsp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.19
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(StopHeartRateRsp resultEntity) {
                if (resultEntity.getErrCode() == 0 && resultEntity.getType() == 8) {
                    if (resultEntity.getValue() > 50) {
                        BleOperateManager.this.pressure = (byte) (new Random().nextInt(20) + 30);
                        return;
                    } else {
                        BleOperateManager.this.pressure = (byte) resultEntity.getValue();
                        return;
                    }
                }
                if (resultEntity.getErrCode() != 0) {
                    BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.pressureRunnable);
                    StartHeartRateRsp resp = new StartHeartRateRsp();
                    resp.setErrCode(resultEntity.getErrCode());
                    if (response != null) {
                        response.onDataResponse(resp);
                    }
                }
            }
        });
    }

    public void manualModeHrv(final ICommandResponse<StartHeartRateRsp> response, boolean stop) {
        if (stop) {
            CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopHrv((byte) 0), null);
        }
        this.response = response;
        this.rri = 0;
        this.myHandler.removeCallbacks(this.hrvRunnable);
        this.myHandler.postDelayed(this.hrvRunnable, 80000L);
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 10), new ICommandResponse<StopHeartRateRsp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.20
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(StopHeartRateRsp resultEntity) {
                if (resultEntity.getErrCode() == 0 && resultEntity.getType() == 10) {
                    BleOperateManager.this.hrv = resultEntity.getValue();
                    if (BleOperateManager.this.hrv > 0) {
                        if (response != null) {
                            BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.hrvRunnable);
                            StartHeartRateRsp resp = new StartHeartRateRsp();
                            resp.setErrCode((byte) 0);
                            resp.setValue(BleOperateManager.this.hrv);
                            resp.setRri(resultEntity.getRri());
                            response.onDataResponse(resp);
                        }
                        CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopHrv((byte) BleOperateManager.this.hrv), null);
                        return;
                    }
                    if (response != null) {
                        BleOperateManager.this.rri = resultEntity.getRri();
                        StartHeartRateRsp resp2 = new StartHeartRateRsp();
                        resp2.setErrCode((byte) 0);
                        resp2.setValue(BleOperateManager.this.hrv);
                        resp2.setRri(BleOperateManager.this.rri);
                        response.onDataResponse(resp2);
                        return;
                    }
                    return;
                }
                if (resultEntity.getErrCode() != 0) {
                    BleOperateManager.this.myHandler.removeCallbacks(BleOperateManager.this.hrvRunnable);
                    StartHeartRateRsp resp3 = new StartHeartRateRsp();
                    resp3.setErrCode(resultEntity.getErrCode());
                    if (response != null) {
                        response.onDataResponse(resp3);
                    }
                }
            }
        });
    }

    public ICommandResponse<StartHeartRateRsp> getResponse() {
        return this.response;
    }

    public void setResponse(ICommandResponse<StartHeartRateRsp> response) {
        this.response = response;
    }

    public void startOneKey(int lastFatigueValue, int lastFatigueTime, ICommandResponse<StartCalcDataRsp> callback) {
        this.lastFatigueTime = lastFatigueTime;
        this.lastFatigueValue = lastFatigueValue;
        this.calcResponse = callback;
        CommandHandle.getInstance().executeReqCmd(StartHeartRateReq.getSimpleReq((byte) 5), this.oneKeyCallback);
        getInstance().addNotifyListener(105, this.oneKeyCallback);
    }

    public void endOneKey() {
        CommandHandle.getInstance().executeReqCmd(StopHeartRateReq.stopHealthCheck(), this.oneKeyCallback);
        getInstance().removeNotifyListener(105);
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/BleOperateManager$OneKeyResp.class */
    private class OneKeyResp implements ICommandResponse<StartHeartRateRsp> {
        private OneKeyResp() {
        }

        @Override // com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(StartHeartRateRsp resultEntity) {
            if (BleOperateManager.this.calcResponse != null) {
                StartCalcDataRsp calcDataRsp = new StartCalcDataRsp();
                calcDataRsp.setHeart((byte) resultEntity.getValue());
                calcDataRsp.setSbp((byte) resultEntity.getSbp());
                calcDataRsp.setDbp((byte) resultEntity.getDbp());
                calcDataRsp.setSpo2(BleOperateManager.this.calcSpo2());
                calcDataRsp.setScore(BleOperateManager.this.calcScore());
                calcDataRsp.setType(resultEntity.getType());
                calcDataRsp.setErrCode(resultEntity.getErrCode());
                calcDataRsp.setFatigue(BleOperateManager.createFatigueValue(BleOperateManager.this.lastFatigueValue, BleOperateManager.this.lastFatigueTime));
                BleOperateManager.this.calcResponse.onDataResponse(calcDataRsp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calcSpo2() {
        return this.spo2Array[new Random().nextInt(4)];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calcScore() {
        return new Random().nextInt(4) + 96;
    }

    public static int createFatigueValue(int lastFatigueValue, int lastFatigueTime) {
        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(11);
        long currentTime = System.currentTimeMillis();
        long dTime = currentTime - lastFatigueTime;
        Log.i(TAG, "createFatigueValue.. hour: " + hour + ", dTime: " + dTime + ", lastFatigueValue: " + lastFatigueValue);
        if (dTime > 1800000) {
            if (hour >= 5 && hour < 11) {
                lastFatigueValue = 80 + mRandom.nextInt(20);
            } else if (hour >= 22 || hour < 5) {
                lastFatigueValue = 40 + mRandom.nextInt(20);
            } else if (hour >= 12 && hour < 14) {
                lastFatigueValue = mRandom.nextInt(10) < 7 ? 40 + mRandom.nextInt(20) : 80 + mRandom.nextInt(20);
            } else {
                lastFatigueValue = mRandom.nextBoolean() ? 40 + mRandom.nextInt(20) : 80 + mRandom.nextInt(20);
            }
        }
        return lastFatigueValue;
    }

    public void addOutDeviceListener(int type, ICommandResponse outRspIOdmOpResponse) {
        this.deviceNotifyListener.setOutRspIOdmOpResponse(type, outRspIOdmOpResponse);
    }

    public void removeOutDeviceListener(int key) {
        this.deviceNotifyListener.removeCallback(key);
    }

    public void removeOthersListener() {
        this.deviceNotifyListener.removeOtherCallbacks();
    }

    public void ringCalibration(boolean enable, final ICommandResponse<AppRevisionResp> callback) {
        this.ringAppRevisionCallback = callback;
        final boolean[] psData = {false};
        final boolean[] bioData = {false};
        this.myHandler.removeCallbacks(this.timeout);
        if (!enable) {
            CommandHandle.getInstance().executeReqCmdNoCallback(AppRevisionReq.getWriteInstance(2));
            BleOperateManager bleOperateManager2 = bleOperateManager;
            getInstance().removeNotifyListener(-223);
        } else {
            this.myHandler.postDelayed(this.timeout, 120000L);
            BleOperateManager bleOperateManager3 = bleOperateManager;
            getInstance().addNotifyListener(-223, new ICommandResponse<AppRevisionResp>() { // from class: com.oudmon.ble.base.bluetooth.BleOperateManager.22
                @Override // com.oudmon.ble.base.communication.ICommandResponse
                public void onDataResponse(AppRevisionResp resultEntity) {
                    if (resultEntity.getDataType() == 1 && resultEntity.getResult() == 1) {
                        psData[0] = true;
                    }
                    if (resultEntity.getDataType() == 2 && resultEntity.getResult() == 1) {
                        bioData[0] = true;
                    }
                    if (psData[0] && bioData[0]) {
                        callback.onDataResponse(new AppRevisionResp(1));
                    } else {
                        callback.onDataResponse(new AppRevisionResp(2));
                    }
                }
            });
            if (enable) {
                CommandHandle.getInstance().executeReqCmdNoCallback(AppRevisionReq.getWriteInstance(6));
            }
        }
    }

    public void bleCreateBond() {
        BleBaseControl.getInstance().bleCreateBond();
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
}
