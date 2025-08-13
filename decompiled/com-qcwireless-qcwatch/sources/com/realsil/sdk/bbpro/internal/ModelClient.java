package com.realsil.sdk.bbpro.internal;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.realsil.sdk.bbpro.BumblebeeCallback;
import com.realsil.sdk.bbpro.core.BeeError;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.bbpro.internal.ModelClientCallback;
import com.realsil.sdk.bbpro.model.DeviceInfo;
import com.realsil.sdk.bbpro.profile.AppReq;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp;
import com.realsil.sdk.core.bluetooth.utils.BluetoothUuid;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public abstract class ModelClient<MCB extends ModelClientCallback> {
    public static boolean DBG = true;
    public static final int STATUS_SUCCESS = 0;
    public static boolean VDBG = false;
    public BaseBeeProManager a;
    public SppTransportLayer b;
    public ThreadPoolExecutor c;
    public ConcurrentMap<Integer, CopyOnWriteArrayList<UserTask>> d;
    public Context mContext;
    public static final UUID UUID_QUERY_LISTENING_MODE_INFO = BluetoothUuid.fromShortValue(256);
    public static final UUID UUID_TTS_REGISTER_MODEL = BluetoothUuid.fromShortValue(512);
    public static final UUID UUID_EQ_QUERY_BASIC_INFO = BluetoothUuid.fromShortValue(BluetoothSpp.STATE_DISCONNECTING);
    public static final UUID UUID_QUERY_BUD_INFO = BluetoothUuid.fromShortValue(1024);
    public List<MCB> mCallbacks = new CopyOnWriteArrayList();
    public final TransportLayerCallback e = new a();
    public final BumblebeeCallback f = new b();

    public class a extends TransportLayerCallback {
        public a() {
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onAckReceive(AckPacket ackPacket) {
            super.onAckReceive(ackPacket);
            ModelClient.this.processAckPacket(ackPacket);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            ModelClient.this.processConnectionStateChanged(bluetoothDevice, i);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onDataReceive(TransportLayerPacket transportLayerPacket) {
            super.onDataReceive(transportLayerPacket);
            ModelClient.this.processEventPacket(transportLayerPacket);
        }
    }

    public class b extends BumblebeeCallback {
        public b() {
        }

        @Override // com.realsil.sdk.bbpro.BumblebeeCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
            super.onConnectionStateChanged(bluetoothDevice, i, i2);
        }

        @Override // com.realsil.sdk.bbpro.BumblebeeCallback
        public void onDeviceInfoChanged(DeviceInfo deviceInfo, int i) {
            super.onDeviceInfoChanged(deviceInfo, i);
            ModelClient.this.processDeviceInfoChanged(deviceInfo, i);
        }

        @Override // com.realsil.sdk.bbpro.BumblebeeCallback
        public void onStateChanged(int i) {
            super.onStateChanged(i);
            if (i == 264) {
                ModelClient.this.registerModel();
            }
            ModelClient.this.notifyStateChanged(i);
        }
    }

    public ModelClient(Context context) {
        this.d = new ConcurrentHashMap();
        this.mContext = context;
        DBG = RtkCore.DEBUG;
        VDBG = RtkCore.VDBG;
        this.d = new ConcurrentHashMap();
        this.c = new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.AbortPolicy());
        a();
    }

    public SppTransportLayer a() {
        if (this.b == null) {
            SppTransportLayer sppTransportLayer = SppTransportLayer.getInstance();
            this.b = sppTransportLayer;
            sppTransportLayer.register(this.e);
        }
        return this.b;
    }

    public void close() {
        ZLogger.v("clese model");
        List<MCB> list = this.mCallbacks;
        if (list != null) {
            list.clear();
            this.mCallbacks = null;
        }
        BaseBeeProManager baseBeeProManager = this.a;
        if (baseBeeProManager != null) {
            baseBeeProManager.removeManagerCallback(this.f);
        }
    }

    public void destroy() {
        close();
        ThreadPoolExecutor threadPoolExecutor = this.c;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
        SppTransportLayer sppTransportLayer = this.b;
        if (sppTransportLayer != null) {
            sppTransportLayer.unregister(this.e);
            this.b = null;
        }
    }

    public BeeError execute(UserTask userTask) {
        if (this.c == null) {
            return new BeeError(48, "Executor has not neen initialized");
        }
        if (userTask == null) {
            return new BeeError(48, "task can not be null");
        }
        a(userTask);
        this.c.execute(userTask);
        return new BeeError(0);
    }

    public int getCmdSetVersion() {
        DeviceInfo deviceInfo;
        BaseBeeProManager baseBeeProManager = this.a;
        if (baseBeeProManager == null || (deviceInfo = baseBeeProManager.getDeviceInfo()) == null) {
            return 0;
        }
        return deviceInfo.getCmdSetVersion();
    }

    public UserTask getUserTask(int i, UUID uuid) {
        CopyOnWriteArrayList<UserTask> copyOnWriteArrayList = this.d.get(Integer.valueOf(i));
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
            for (UserTask userTask : copyOnWriteArrayList) {
                if (uuid.equals(userTask.getUuid())) {
                    return userTask;
                }
            }
        }
        return null;
    }

    public List<UserTask> getUserTasks(int i) {
        return this.d.get(Integer.valueOf(i));
    }

    public BaseBeeProManager getVendorClient() {
        return this.a;
    }

    public abstract int getVendorCmd();

    public DeviceInfo getVendorDeviceInfo() {
        BaseBeeProManager baseBeeProManager = this.a;
        if (baseBeeProManager == null) {
            return null;
        }
        return baseBeeProManager.getDeviceInfo();
    }

    public abstract int getVendorEvent();

    public void notifyOperationComplete(int i, byte b2) {
        List<MCB> list = this.mCallbacks;
        if (list == null || list.size() <= 0) {
            if (VDBG) {
                ZLogger.v("no callback registered");
            }
        } else {
            Iterator<MCB> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onOperationComplete(i, b2);
            }
        }
    }

    public void notifyStateChanged(int i) {
        List<MCB> list = this.mCallbacks;
        if (list == null || list.size() <= 0) {
            if (VDBG) {
                ZLogger.v("no callback registered");
            }
        } else {
            Iterator<MCB> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onStateChanged(i);
            }
        }
    }

    public boolean processAckPacket(AckPacket ackPacket) {
        return false;
    }

    public void processConnectionStateChanged(BluetoothDevice bluetoothDevice, int i) {
    }

    public void processDeviceInfoChanged(DeviceInfo deviceInfo, int i) {
    }

    public boolean processEvent(byte[] bArr) {
        return false;
    }

    public boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        return false;
    }

    public boolean processStatusReport(byte b2, byte[] bArr) {
        return false;
    }

    public void registerCallback(MCB mcb) {
        if (mcb == null) {
            return;
        }
        if (this.mCallbacks == null) {
            this.mCallbacks = new CopyOnWriteArrayList();
        }
        if (!this.mCallbacks.contains(mcb)) {
            this.mCallbacks.add(mcb);
        }
        ZLogger.v(VDBG, "mCallbacks.size=" + this.mCallbacks.size());
    }

    public BeeError registerModel() {
        return new BeeError(0);
    }

    public void removeTask(UserTask userTask) {
        if (this.d == null) {
            this.d = new ConcurrentHashMap();
        }
        userTask.stopSubTask();
        CopyOnWriteArrayList<UserTask> copyOnWriteArrayList = this.d.get(Integer.valueOf(userTask.getOpcode()));
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(userTask);
        }
        this.d.put(Integer.valueOf(userTask.getOpcode()), copyOnWriteArrayList);
    }

    public BeeError sendAppReq(AppReq appReq) {
        if (VDBG) {
            ZLogger.v(appReq.toString());
        }
        return this.b.sendCommand(appReq.encode(getCmdSetVersion())) ? new BeeError(0) : new BeeError(1, "sendCommand failed");
    }

    public BeeError sendVendorData(byte[] bArr) {
        if (bArr == null) {
            return new BeeError(48);
        }
        BaseBeeProManager baseBeeProManager = this.a;
        return baseBeeProManager == null ? new BeeError(16) : baseBeeProManager.sendVendorCommand(bArr);
    }

    public void setup(BaseBeeProManager baseBeeProManager) {
        this.a = baseBeeProManager;
        if (baseBeeProManager != null) {
            baseBeeProManager.addManagerCallback(this.f);
        }
    }

    public void unregisterCallback(MCB mcb) {
        List<MCB> list = this.mCallbacks;
        if (list != null) {
            list.remove(mcb);
        }
    }

    public void updateUserTasks(UUID uuid, byte b2) {
        UserTask userTask;
        if (this.d == null || (userTask = getUserTask(uuid)) == null) {
            return;
        }
        userTask.notiyTaskUpdate(b2);
        if (userTask.isLastAction()) {
            removeTask(userTask);
        }
    }

    public final void a(UserTask userTask) {
        if (this.d == null) {
            this.d = new ConcurrentHashMap();
        }
        CopyOnWriteArrayList<UserTask> copyOnWriteArrayList = this.d.get(Integer.valueOf(userTask.getOpcode()));
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        }
        if (!copyOnWriteArrayList.contains(userTask)) {
            copyOnWriteArrayList.add(userTask);
        }
        ZLogger.v(VDBG, String.format(Locale.US, "contains %d sub tasks", Integer.valueOf(copyOnWriteArrayList.size())));
        this.d.put(Integer.valueOf(userTask.getOpcode()), copyOnWriteArrayList);
    }

    public UserTask getUserTask(UUID uuid) {
        Iterator<Integer> it = this.d.keySet().iterator();
        while (it.hasNext()) {
            UserTask userTask = getUserTask(it.next().intValue(), uuid);
            if (userTask != null) {
                return userTask;
            }
        }
        return null;
    }

    public BeeError sendVendorData(Command command) {
        BaseBeeProManager baseBeeProManager = this.a;
        if (baseBeeProManager == null) {
            return new BeeError(16);
        }
        return baseBeeProManager.sendVendorCommand(command);
    }
}
