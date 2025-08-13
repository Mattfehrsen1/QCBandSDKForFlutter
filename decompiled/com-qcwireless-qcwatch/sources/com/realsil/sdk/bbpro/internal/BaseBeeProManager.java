package com.realsil.sdk.bbpro.internal;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.realsil.sdk.bbpro.BeeProParams;
import com.realsil.sdk.bbpro.BumblebeeCallback;
import com.realsil.sdk.bbpro.ConnectionParameters;
import com.realsil.sdk.bbpro.RtkBbpro;
import com.realsil.sdk.bbpro.core.BeeError;
import com.realsil.sdk.bbpro.core.Utils;
import com.realsil.sdk.bbpro.core.protocol.CommandContract;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.bbpro.equalizer.EqModelClient;
import com.realsil.sdk.bbpro.i.e;
import com.realsil.sdk.bbpro.model.DeviceInfo;
import com.realsil.sdk.bbpro.params.Mmi;
import com.realsil.sdk.bbpro.profile.AppReq;
import com.realsil.sdk.bbpro.profile.GetCfgSettingsReq;
import com.realsil.sdk.bbpro.profile.GetStatusReq;
import com.realsil.sdk.bbpro.profile.MmiReq;
import com.realsil.sdk.bbpro.profile.SetCfgSettingsReq;
import com.realsil.sdk.bbpro.vendor.VendorModelCallback;
import com.realsil.sdk.bbpro.vendor.VendorModelClient;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.BluetoothProfileCallback;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.sdk.core.bluetooth.connection.legacy.BluetoothSpp;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class BaseBeeProManager {
    public static boolean D = true;
    public static boolean E = false;
    public static BeeProParams F = null;
    public static final int STATE_DATA_PREPARED = 264;
    public static final int STATE_DATA_SYNC_FAILED = 265;
    public static final int STATE_DATA_SYNC_MASK = 512;
    public static final int STATE_DATA_SYNC_PROCESSING = 513;
    public static final int STATE_DEVICE_CONNECTED = 263;
    public static final int STATE_DEVICE_CONNECTING = 261;
    public static final int STATE_DEVICE_DISCONNECTED = 260;
    public static final int STATE_DEVICE_DISCONNECTING = 262;
    public static final int STATE_IDLE_MASK = 256;
    public static final int STATE_INIT = 257;
    public final Object A;
    public final Object B;
    public i C;
    public BluetoothAdapter a;
    public SppTransportLayer e;
    public volatile boolean g;
    public boolean j;
    public ThreadPoolExecutor l;
    public Context mContext;
    public Object n;
    public VendorModelClient q;
    public final RtkBluetoothManagerCallback s;
    public final BluetoothProfileCallback t;
    public VendorModelCallback u;
    public j v;
    public boolean w;
    public com.realsil.sdk.bbpro.e.b x;
    public final Object y;
    public final Object z;
    public boolean b = false;
    public BluetoothDevice c = null;
    public BluetoothDevice d = null;
    public com.realsil.sdk.bbpro.e.a f = null;
    public int h = 0;
    public int i = 0;
    public int k = 257;
    public final TransportLayerCallback m = new a();
    public boolean o = false;
    public List<BumblebeeCallback> p = new CopyOnWriteArrayList();
    public boolean r = true;

    public class a extends TransportLayerCallback {
        public a() {
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onAckReceive(AckPacket ackPacket) {
            super.onAckReceive(ackPacket);
            BaseBeeProManager.this.processAckPacket(ackPacket);
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) throws IOException {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            if (BaseBeeProManager.this.isDeviceChanged(bluetoothDevice)) {
                ZLogger.d("device changed, just ignore here");
                return;
            }
            if (i == 512) {
                if (TextUtils.isEmpty(BaseBeeProManager.this.getDeviceInfo().getBrEdrName())) {
                    BaseBeeProManager.this.getDeviceInfo().setBrEdrName(bluetoothDevice.getName());
                }
                BaseBeeProManager.this.a(i);
                BaseBeeProManager.this.b(bluetoothDevice);
                ZLogger.v(BaseBeeProManager.this.getDeviceInfo().toString());
                BaseBeeProManager.this.stopSppServerThread();
                BaseBeeProManager.this.j = false;
                BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
                baseBeeProManager.a(baseBeeProManager.c, baseBeeProManager.h);
                return;
            }
            if (i != 0) {
                BaseBeeProManager.this.a(i);
                BaseBeeProManager baseBeeProManager2 = BaseBeeProManager.this;
                baseBeeProManager2.a(baseBeeProManager2.c, baseBeeProManager2.h);
                return;
            }
            BaseBeeProManager.this.clearDeviceInfo();
            BaseBeeProManager.this.a(0);
            BaseBeeProManager.this.b((BluetoothDevice) null);
            if (BaseBeeProManager.this.e != null) {
                BaseBeeProManager.this.e.unregister(BaseBeeProManager.this.m);
                BaseBeeProManager.this.e.disconnect();
                BaseBeeProManager.this.e = null;
            }
            if (BaseBeeProManager.this.j) {
                BaseBeeProManager.this.j = false;
                BaseBeeProManager.this.startConnect(bluetoothDevice);
            } else {
                BaseBeeProManager baseBeeProManager3 = BaseBeeProManager.this;
                baseBeeProManager3.a(baseBeeProManager3.c, baseBeeProManager3.h);
                BaseBeeProManager.this.startSppServerThread();
            }
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.TransportLayerCallback
        public void onDataReceive(TransportLayerPacket transportLayerPacket) {
            super.onDataReceive(transportLayerPacket);
            BaseBeeProManager.this.processEventPacket(transportLayerPacket);
        }
    }

    public class b extends RtkBluetoothManagerCallback {
        public b() {
        }

        @Override // com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback
        public void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onBondStateChanged(bluetoothDevice, i);
            BluetoothDevice bluetoothDevice2 = BaseBeeProManager.this.c;
            if (bluetoothDevice2 == null || !bluetoothDevice2.equals(bluetoothDevice)) {
                return;
            }
            int connectionState = BluetoothProfileManager.getInstance().getConnectionState(2, BaseBeeProManager.this.c);
            int bondState = bluetoothDevice.getBondState();
            ZLogger.v(String.format(Locale.US, "a2dpState=0x%02X, bondState=%d", Integer.valueOf(connectionState), Integer.valueOf(bondState)));
            if (bondState != 12) {
                if (bondState == 10) {
                    synchronized (BaseBeeProManager.this.y) {
                        if (BaseBeeProManager.this.h == 256) {
                            BaseBeeProManager.this.y.notifyAll();
                        }
                    }
                    return;
                }
                return;
            }
            synchronized (BaseBeeProManager.this.y) {
                BaseBeeProManager.this.y.notifyAll();
            }
            if (!BaseBeeProManager.this.r || connectionState == 2) {
                return;
            }
            ZLogger.d("auto connect a2dp when paired");
            BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
            baseBeeProManager.a(baseBeeProManager.c);
        }
    }

    public class c extends BluetoothProfileCallback {
        public c() {
        }

        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onA2dpStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onA2dpStateChanged(bluetoothDevice, i);
            if (BaseBeeProManager.this.isDeviceChanged(bluetoothDevice)) {
                return;
            }
            if (i == 2) {
                synchronized (BaseBeeProManager.this.z) {
                    BaseBeeProManager.this.z.notifyAll();
                }
            } else if (i == 0) {
                synchronized (BaseBeeProManager.this.z) {
                    BaseBeeProManager.this.z.notifyAll();
                }
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onHfpConnectionStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onHfpConnectionStateChanged(bluetoothDevice, i);
            if (BaseBeeProManager.this.isDeviceChanged(bluetoothDevice)) {
                if (BaseBeeProManager.E) {
                    ZLogger.v("ignore inactive device's hfp conn state update");
                    return;
                }
                return;
            }
            if (i != 2) {
                if (i == 0) {
                    synchronized (BaseBeeProManager.this.A) {
                        BaseBeeProManager.this.A.notifyAll();
                    }
                    if (BaseBeeProManager.getBeeProParams().c() && BaseBeeProManager.this.isConnected()) {
                        ZLogger.d(BaseBeeProManager.D, "auto disconect spp when hfp disconnected");
                        BaseBeeProManager.this.disconnect();
                        return;
                    }
                    return;
                }
                return;
            }
            synchronized (BaseBeeProManager.this.A) {
                BaseBeeProManager.this.A.notifyAll();
            }
            if (BaseBeeProManager.getBeeProParams().e()) {
                if (!BaseBeeProManager.this.isDisconnected()) {
                    if (BaseBeeProManager.E) {
                        ZLogger.v(String.format("ignore when spp is not in disconnected state: 0x%04X", Integer.valueOf(BaseBeeProManager.this.getConnectState())));
                    }
                } else {
                    ZLogger.d(BaseBeeProManager.D, "auto connect spp when hfp connected");
                    if (BaseBeeProManager.E) {
                        Locale locale = Locale.US;
                        BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
                        ZLogger.v(String.format(locale, "processInfo: %d,%s", Integer.valueOf(Process.myPid()), baseBeeProManager.a(baseBeeProManager.mContext, Process.myPid())));
                    }
                    BaseBeeProManager.this.connect(bluetoothDevice);
                }
            }
        }
    }

    public class d extends VendorModelCallback {
        public d() {
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x000e  */
        @Override // com.realsil.sdk.bbpro.vendor.VendorModelCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onDeviceInfoChanged(int r2, com.realsil.sdk.bbpro.model.DeviceInfo r3) {
            /*
                r1 = this;
                super.onDeviceInfoChanged(r2, r3)
                r0 = 2
                if (r2 == r0) goto Le
                r0 = 58
                if (r2 == r0) goto Le
                switch(r2) {
                    case 11: goto Le;
                    case 12: goto Le;
                    case 13: goto Le;
                    default: goto Ld;
                }
            Ld:
                goto L13
            Le:
                com.realsil.sdk.bbpro.internal.BaseBeeProManager r0 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.this
                r0.d()
            L13:
                com.realsil.sdk.bbpro.internal.BaseBeeProManager r0 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.this
                r0.notifyDeviceInfoChanged(r3, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.bbpro.internal.BaseBeeProManager.d.onDeviceInfoChanged(int, com.realsil.sdk.bbpro.model.DeviceInfo):void");
        }

        @Override // com.realsil.sdk.bbpro.internal.ModelClientCallback
        public void onOperationComplete(int i, byte b) {
            super.onOperationComplete(i, b);
            if (i == 23) {
                if (b != 0) {
                    BaseBeeProManager.this.d();
                }
                return;
            }
            if (i == 34) {
                if (b != 0) {
                    BaseBeeProManager.this.d();
                    return;
                }
                return;
            }
            switch (i) {
                case 29:
                    if (b != 0) {
                        BaseBeeProManager.this.d();
                        break;
                    }
                    break;
                case 30:
                    if (b != 0) {
                        BaseBeeProManager.this.d();
                        break;
                    }
                    break;
                case 31:
                    if (b != 0) {
                        BaseBeeProManager.this.d();
                        break;
                    }
                    break;
            }
        }
    }

    public class e extends com.realsil.sdk.bbpro.e.b {
        public e() {
        }

        @Override // com.realsil.sdk.bbpro.e.b
        public void a(BluetoothDevice bluetoothDevice, boolean z, BluetoothSocket bluetoothSocket) throws IOException {
            if (!z) {
                if (bluetoothSocket != null) {
                    try {
                        bluetoothSocket.close();
                    } catch (IOException e) {
                        ZLogger.w(e.getMessage());
                    }
                }
                BaseBeeProManager.this.startSppServerThread();
                return;
            }
            if (bluetoothSocket != null && bluetoothDevice != null) {
                ZLogger.d("auto connect as server");
                BaseBeeProManager.this.startConnect(bluetoothDevice, bluetoothSocket);
            } else if (BaseBeeProManager.D) {
                ZLogger.v("something error");
            }
        }
    }

    public class f implements Runnable {
        public final /* synthetic */ BluetoothDevice a;

        public f(BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZLogger.v(BaseBeeProManager.E, "connect a2dp");
            boolean zConnectA2dpSource = BluetoothProfileManager.getInstance().connectA2dpSource(this.a.getAddress());
            ZLogger.d(BaseBeeProManager.D, "connectA2dpSource:" + zConnectA2dpSource);
        }
    }

    public class g implements Runnable {
        public final /* synthetic */ BluetoothDevice a;
        public final /* synthetic */ ConnectionParameters b;

        public g(BluetoothDevice bluetoothDevice, ConnectionParameters connectionParameters) {
            this.a = bluetoothDevice;
            this.b = connectionParameters;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                synchronized (BaseBeeProManager.this.y) {
                    if (this.a.getBondState() != 12) {
                        BaseBeeProManager.this.y.wait(15000L);
                    }
                }
            } catch (InterruptedException e) {
                ZLogger.w(e.getMessage());
            }
            if (this.a.getBondState() != 12) {
                BaseBeeProManager.this.a(0);
                BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
                baseBeeProManager.a(baseBeeProManager.c, baseBeeProManager.h);
                return;
            }
            BaseBeeProManager.this.a();
            BaseBeeProManager.this.b();
            BaseBeeProManager.this.a(256);
            if (BaseBeeProManager.this.c().connect(this.a, null, this.b.d())) {
                return;
            }
            ZLogger.w("connect failed");
            BaseBeeProManager.this.a(0);
            BaseBeeProManager baseBeeProManager2 = BaseBeeProManager.this;
            baseBeeProManager2.a(baseBeeProManager2.c, baseBeeProManager2.h);
        }
    }

    public class h implements Runnable {
        public final /* synthetic */ ConnectionParameters a;

        public h(ConnectionParameters connectionParameters) {
            this.a = connectionParameters;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                synchronized (BaseBeeProManager.this.B) {
                    BaseBeeProManager.this.B.wait(5000L);
                }
            } catch (InterruptedException e) {
                ZLogger.w(e.getMessage());
            }
            BaseBeeProManager.this.a(256);
            if (BaseBeeProManager.this.c().connect(this.a.c())) {
                return;
            }
            ZLogger.w("connect failed");
            BaseBeeProManager.this.a(0);
            BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
            baseBeeProManager.a(baseBeeProManager.c, baseBeeProManager.h);
        }
    }

    public class i extends BroadcastReceiver {
        public i() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.device.action.UUID".equals(intent.getAction())) {
                if (BaseBeeProManager.this.isDeviceChanged((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"))) {
                    return;
                }
                ZLogger.v(BaseBeeProManager.D, "uuid update.");
                synchronized (BaseBeeProManager.this.B) {
                    BaseBeeProManager.this.B.notifyAll();
                }
            }
        }

        public /* synthetic */ i(BaseBeeProManager baseBeeProManager, a aVar) {
            this();
        }
    }

    public class j extends Thread {
        public j() {
        }

        public final boolean a() {
            if (BaseBeeProManager.this.isConnected()) {
                return true;
            }
            BaseBeeProManager.this.a(260, false);
            ZLogger.v("sync interrupted, because of connection disconnected");
            return false;
        }

        public final boolean b() {
            if (!a()) {
                BaseBeeProManager.this.w = false;
                return false;
            }
            ZLogger.v(BaseBeeProManager.E, ">> LOAD_BREDR_NAME");
            BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
            baseBeeProManager.o = true;
            BeeError beeErrorReqDeviceName = baseBeeProManager.reqDeviceName((byte) 1);
            if (beeErrorReqDeviceName.code == 0) {
                BaseBeeProManager.this.h();
                return true;
            }
            ZLogger.w("loadBredrName failed: " + beeErrorReqDeviceName.message);
            BaseBeeProManager baseBeeProManager2 = BaseBeeProManager.this;
            baseBeeProManager2.w = false;
            baseBeeProManager2.a(265, true);
            return false;
        }

        public final boolean c() {
            if (!a()) {
                BaseBeeProManager.this.w = false;
                return false;
            }
            ZLogger.v(BaseBeeProManager.E, ">> LOAD_CAPABILITY");
            BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
            baseBeeProManager.o = true;
            BeeError beeErrorReqCapability = baseBeeProManager.reqCapability();
            if (beeErrorReqCapability.code == 0) {
                BaseBeeProManager.this.h();
                return true;
            }
            ZLogger.d("loadCapability failed: " + beeErrorReqCapability.message);
            BaseBeeProManager baseBeeProManager2 = BaseBeeProManager.this;
            baseBeeProManager2.w = false;
            baseBeeProManager2.a(265, true);
            return false;
        }

        public final boolean d() {
            if (!a()) {
                BaseBeeProManager.this.w = false;
                return false;
            }
            ZLogger.v(BaseBeeProManager.E, ">> LOAD_CHIP_INFO");
            BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
            baseBeeProManager.o = true;
            BeeError beeErrorReqPackageId = baseBeeProManager.reqPackageId();
            if (beeErrorReqPackageId.code == 0) {
                BaseBeeProManager.this.h();
                return true;
            }
            ZLogger.w("reqPackageId failed: " + beeErrorReqPackageId.message);
            BaseBeeProManager baseBeeProManager2 = BaseBeeProManager.this;
            baseBeeProManager2.w = false;
            baseBeeProManager2.a(265, true);
            return false;
        }

        public final boolean e() {
            if (!a()) {
                BaseBeeProManager.this.w = false;
                return false;
            }
            ZLogger.v(BaseBeeProManager.E, ">> LOAD_CMD_SET_VERSION");
            BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
            baseBeeProManager.o = true;
            BeeError beeErrorReqCmdSetVersion = baseBeeProManager.reqCmdSetVersion();
            if (beeErrorReqCmdSetVersion.code == 0) {
                BaseBeeProManager.this.h();
                return true;
            }
            ZLogger.w("loadCmdSetVersion failed: " + beeErrorReqCmdSetVersion.message);
            BaseBeeProManager baseBeeProManager2 = BaseBeeProManager.this;
            baseBeeProManager2.w = false;
            baseBeeProManager2.a(265, true);
            return false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BaseBeeProManager.this.a(513, true);
            BaseBeeProManager.this.w = true;
            if (d() && e() && c() && b()) {
                ZLogger.v(BaseBeeProManager.E, ">> no more data to sync");
                BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
                baseBeeProManager.w = false;
                baseBeeProManager.a(264, true);
                if (BaseBeeProManager.D) {
                    ZLogger.v(BaseBeeProManager.this.getDeviceInfo().toString());
                }
            }
        }

        public /* synthetic */ j(BaseBeeProManager baseBeeProManager, a aVar) {
            this();
        }
    }

    public BaseBeeProManager(Context context) {
        this.g = false;
        this.n = new Object();
        b bVar = new b();
        this.s = bVar;
        c cVar = new c();
        this.t = cVar;
        this.u = new d();
        this.w = false;
        this.x = new e();
        this.y = new Object();
        this.z = new Object();
        this.A = new Object();
        this.B = new Object();
        ZLogger.v(D, "create BaseBeeProManager");
        this.mContext = context;
        this.a = BluetoothAdapter.getDefaultAdapter();
        this.n = new Object();
        D = RtkCore.DEBUG;
        E = RtkCore.VDBG;
        RtkBbpro.initialize(context);
        if (RtkBluetoothManager.getInstance() == null) {
            RtkBluetoothManager.initial(context);
        }
        if (RtkBluetoothManager.getInstance() != null) {
            if (this.b) {
                ZLogger.v(E, "already been initialized");
            } else {
                RtkBluetoothManager.getInstance().addManagerCallback(bVar);
            }
        }
        if (BluetoothProfileManager.getInstance() == null) {
            BluetoothProfileManager.initial(context);
        }
        if (BluetoothProfileManager.getInstance() != null) {
            if (this.b) {
                ZLogger.v(E, "already been initialized");
            } else {
                BluetoothProfileManager.getInstance().addManagerCallback(cVar);
            }
        }
        this.g = false;
        startSppServerThread();
        this.l = new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static BeeProParams getBeeProParams() {
        if (F == null) {
            F = new BeeProParams();
        }
        return F;
    }

    public void addManagerCallback(BumblebeeCallback bumblebeeCallback) {
        if (this.p == null) {
            this.p = new CopyOnWriteArrayList();
        }
        if (!this.p.contains(bumblebeeCallback)) {
            this.p.add(bumblebeeCallback);
        }
        ZLogger.v(E, "mManagerCallback.size=" + this.p.size());
    }

    public BeeError changeDeviceName(byte b2, String str) {
        return sendVendorCommand(new SetCfgSettingsReq.Builder(b2).cfgData(str.getBytes()).assembleDataLength(true).build().encode());
    }

    public BeeError changeRwsChannelSetting() {
        return sendVendorCommand(new MmiReq.Builder((byte) 116).build().encode());
    }

    public void clearDeviceInfo() {
        VendorModelClient vendorModelClient = this.q;
        if (vendorModelClient != null) {
            vendorModelClient.clearDeviceInfo();
        }
    }

    public void clearManagerCallback() {
        List<BumblebeeCallback> list = this.p;
        if (list != null) {
            list.clear();
            this.p = null;
        }
    }

    public BeeError connect(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return new BeeError(1, "BluetoothDevice is invalid");
        }
        if (isConnected()) {
            ZLogger.d("already connected");
            a(bluetoothDevice, 512);
            return new BeeError(0);
        }
        ZLogger.v(D, "connectionstate = " + getConnectState());
        return new BeeError(startConnect(new ConnectionParameters.Builder(bluetoothDevice).uuid(getBeeProParams().b()).transport(getBeeProParams().a()).build()));
    }

    public void destroy() throws IOException {
        ZLogger.v(E, "destroy...");
        disconnect();
        VendorModelClient vendorModelClient = this.q;
        if (vendorModelClient != null) {
            vendorModelClient.destroy();
            this.q = null;
        }
        ThreadPoolExecutor threadPoolExecutor = this.l;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
        this.o = false;
        g();
        stopSppServerThread();
        f();
        if (RtkBluetoothManager.getInstance() != null) {
            RtkBluetoothManager.getInstance().removeManagerCallback(this.s);
        }
        if (BluetoothProfileManager.getInstance() != null) {
            BluetoothProfileManager.getInstance().removeManagerCallback(this.t);
        }
        SppTransportLayer sppTransportLayer = this.e;
        if (sppTransportLayer != null) {
            sppTransportLayer.unregister(this.m);
            this.e = null;
        }
        clearManagerCallback();
        a(257, false);
        this.b = false;
    }

    public BeeError disconnect() {
        if (this.h == 0) {
            ZLogger.d("connection has already disconnected");
            a(this.c, this.h);
        } else {
            SppTransportLayer sppTransportLayer = this.e;
            if (sppTransportLayer == null) {
                ZLogger.d("SppTransportLayer has already been released");
                a(this.c, 0);
            } else if (sppTransportLayer.getConnectionState() == 512 || this.e.getConnectionState() == 256) {
                a(BluetoothSpp.STATE_DISCONNECTING);
                this.e.disconnect();
            } else if (this.e.getConnectionState() == 768) {
                a(BluetoothSpp.STATE_DISCONNECTING);
                a(this.c, this.h);
            } else {
                a(this.c, 0);
            }
        }
        return new BeeError(0);
    }

    public BeeError enterPairingMode() {
        return sendVendorCommand(new MmiReq.Builder((byte) 81).build().encode());
    }

    public BeeError exitPairingMode() {
        return sendVendorCommand(new MmiReq.Builder((byte) 82).build().encode());
    }

    public int getConnState() {
        return this.h;
    }

    public int getConnectState() {
        return c().getConnectionState();
    }

    public BluetoothDevice getCurDevice() {
        return this.c;
    }

    public DeviceInfo getDeviceInfo() {
        VendorModelClient vendorModelClient = this.q;
        return vendorModelClient != null ? vendorModelClient.getDeviceInfo() : new DeviceInfo();
    }

    public EqModelClient getEqModelClient() {
        EqModelClient eqModelClient = EqModelClient.getInstance();
        if (eqModelClient == null) {
            EqModelClient.initialize(this.mContext);
            eqModelClient = EqModelClient.getInstance();
            if (eqModelClient != null) {
                eqModelClient.setup(this);
            }
        }
        return eqModelClient;
    }

    public BeeError getLeAddr() {
        return sendVendorCommand(CommandContract.buildPacket((short) 261));
    }

    public BeeError getName(byte b2) {
        return reqDeviceName(b2);
    }

    public BeeError getOtaDeviceInfo() {
        return sendVendorCommand(CommandContract.buildPacket((short) 1536));
    }

    public void getRwsInfo() {
        getVendorClient().getBudInfo();
    }

    public int getState() {
        return this.k;
    }

    public BeeError getStatus(byte b2) {
        return sendVendorCommand(new GetStatusReq.Builder(b2).build().encode());
    }

    public VendorModelClient getVendorClient() {
        if (this.q == null) {
            VendorModelClient vendorModelClient = VendorModelClient.getInstance();
            this.q = vendorModelClient;
            if (vendorModelClient != null) {
                vendorModelClient.setup(this);
                this.q.registerCallback(this.u);
            }
        }
        return this.q;
    }

    public boolean initialize(BeeProParams beeProParams) {
        F = beeProParams;
        ZLogger.d("initialize:" + getBeeProParams().toString());
        VendorModelClient.initialize(this.mContext);
        VendorModelClient vendorModelClient = VendorModelClient.getInstance();
        this.q = vendorModelClient;
        if (vendorModelClient != null) {
            vendorModelClient.setup(this);
            this.q.registerCallback(this.u);
        }
        EqModelClient.initialize(this.mContext);
        EqModelClient.getInstance().setup(this);
        e();
        a(this.k, true);
        this.b = true;
        ZLogger.v(D, "initialize success");
        return true;
    }

    public boolean isActiveDevice(BluetoothDevice bluetoothDevice) {
        BluetoothDevice bluetoothDevice2;
        if (bluetoothDevice == null || (bluetoothDevice2 = this.c) == null) {
            return false;
        }
        return bluetoothDevice2.equals(bluetoothDevice);
    }

    public boolean isConnected() {
        return c().getConnectionState() == 512;
    }

    public boolean isConnectionStateChanged() {
        return this.h != this.i;
    }

    public boolean isDeviceChanged(BluetoothDevice bluetoothDevice) {
        boolean zEquals;
        if (bluetoothDevice == null) {
            return false;
        }
        BluetoothDevice bluetoothDevice2 = this.c;
        if (bluetoothDevice2 != null) {
            zEquals = bluetoothDevice2.equals(bluetoothDevice);
        } else {
            BluetoothDevice bluetoothDevice3 = this.d;
            if (bluetoothDevice3 == null) {
                return false;
            }
            zEquals = bluetoothDevice3.equals(bluetoothDevice);
        }
        return !zEquals;
    }

    public boolean isDisconnected() {
        return c().getConnectionState() == 0;
    }

    public void notifyDeviceInfoChanged(DeviceInfo deviceInfo, int i2) {
        List<BumblebeeCallback> list = this.p;
        if (list == null || list.size() <= 0) {
            ZLogger.v(E, "no callback registered");
            return;
        }
        if (E) {
            ZLogger.v(String.format(Locale.US, "indicator=%d, %s", Integer.valueOf(i2), deviceInfo.toString()));
        }
        Iterator<BumblebeeCallback> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().onDeviceInfoChanged(deviceInfo, i2);
        }
    }

    public BeeError powerOff() {
        return sendVendorCommand(new MmiReq.Builder((byte) 86).build().encode());
    }

    public boolean processAckPacket(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        List<BumblebeeCallback> list = this.p;
        if (list == null || list.size() <= 0) {
            ZLogger.v(E, "no callback registered");
            return false;
        }
        Iterator<BumblebeeCallback> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().onAckReceived(toAckId, status);
        }
        return false;
    }

    public boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        short opcode = transportLayerPacket.getOpcode();
        byte[] parameters = transportLayerPacket.getParameters();
        transportLayerPacket.getPayload();
        if (opcode == 25) {
            if (E) {
                ZLogger.v(">> EVENT_REPORT_STATUS");
            }
            processStatusReport(parameters);
            return true;
        }
        List<BumblebeeCallback> list = this.p;
        if (list == null || list.size() <= 0) {
            if (!E) {
                return false;
            }
            ZLogger.v("no callback registered");
            return false;
        }
        Iterator<BumblebeeCallback> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().onEventReported(opcode, parameters);
        }
        return false;
    }

    public boolean processStatusReport(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            byte b2 = bArr[0];
            VendorModelClient vendorModelClient = this.q;
            if (vendorModelClient != null && vendorModelClient.processStatusReport(b2, bArr)) {
                return true;
            }
        }
        return false;
    }

    public boolean registerModel(ModelClient modelClient) {
        if (modelClient == null) {
            ZLogger.w("model can not be null");
            return false;
        }
        modelClient.setup(this);
        return true;
    }

    public void registerVendorModelCallback(VendorModelCallback vendorModelCallback) {
        VendorModelClient vendorModelClient = this.q;
        if (vendorModelClient != null) {
            vendorModelClient.registerCallback(vendorModelCallback);
        }
    }

    public void removeManagerCallback(BumblebeeCallback bumblebeeCallback) {
        List<BumblebeeCallback> list = this.p;
        if (list != null) {
            list.remove(bumblebeeCallback);
            ZLogger.v(E, "mManagerCallback.size=" + this.p.size());
        }
    }

    public BeeError reqBrEdrName(byte b2) {
        return reqDeviceName((byte) 1);
    }

    public BeeError reqCapability() {
        return sendVendorCommand(CommandContract.reqCmdInfo((byte) 1));
    }

    public BeeError reqCmdSetVersion() {
        return sendVendorCommand(new e.b().a(262).b(com.realsil.sdk.bbpro.c.c.a()).a().encode());
    }

    public BeeError reqDeviceName(byte b2) {
        return sendVendorCommand(new GetCfgSettingsReq.Builder(b2).build().encode());
    }

    public BeeError reqLeName() {
        return reqDeviceName((byte) 0);
    }

    public BeeError reqPackageId() {
        return sendVendorCommand(CommandContract.buildPacket(CommandContract.CMD_GET_PACKAGE_ID));
    }

    public BeeError sendATone(int i2) {
        return sendVendorCommand(CommandContract.buildPacket((short) 8, new byte[]{(byte) i2}));
    }

    public BeeError sendAppReq(AppReq appReq) {
        return getVendorClient().sendAppReq(appReq);
    }

    public BeeError sendUserCommand(byte[] bArr) {
        return sendVendorCommand(bArr);
    }

    public BeeError sendVendorCommand(byte[] bArr) {
        return sendVendorCommand(new Command.Builder().writeType(2).payload(bArr).build());
    }

    public BeeError setVolumeDown() {
        return sendVendorCommand(new MmiReq.Builder((byte) 49).build().encode());
    }

    public BeeError setVolumeUp() {
        return sendVendorCommand(new MmiReq.Builder((byte) 48).build().encode());
    }

    public int startConnect(BluetoothDevice bluetoothDevice) {
        return startConnect(new ConnectionParameters.Builder(bluetoothDevice).uuid(getBeeProParams().b()).transport(getBeeProParams().a()).build());
    }

    public void startSppServerThread() {
        if (this.g || !getBeeProParams().f()) {
            return;
        }
        try {
            com.realsil.sdk.bbpro.e.a aVar = new com.realsil.sdk.bbpro.e.a(this.mContext, getBeeProParams().b(), this.x);
            this.f = aVar;
            aVar.start();
        } catch (Exception e2) {
            ZLogger.w(e2.toString());
        }
    }

    public void stopSppServerThread() throws IOException {
        this.g = true;
        com.realsil.sdk.bbpro.e.a aVar = this.f;
        if (aVar != null) {
            aVar.a();
        }
    }

    public synchronized boolean syncData() {
        if (this.k == 513 || this.w) {
            ZLogger.v("already in syncing data");
            return true;
        }
        a aVar = null;
        try {
            if (this.v == null) {
                j jVar = new j(this, aVar);
                this.v = jVar;
                jVar.start();
            } else {
                ZLogger.v(E, "sync thread isAlive:" + this.v.isAlive());
                if (this.v.isAlive()) {
                    ZLogger.v(D, "sync thread is already started");
                } else {
                    ZLogger.v(E, "restart sync thread when it's dead");
                    this.v.start();
                }
            }
        } catch (Exception e2) {
            ZLogger.w(e2.toString());
            j jVar2 = new j(this, aVar);
            this.v = jVar2;
            jVar2.start();
        }
        return true;
    }

    public BeeError toggleDspPassthrough() {
        return sendVendorCommand(new MmiReq.Builder(Mmi.AU_MMI_AUDIO_PASS_THROUGH).build().encode());
    }

    public BeeError triggerBleAdvertising() {
        return sendVendorCommand(new MmiReq.Builder((byte) -96).build().encode());
    }

    public void unregisterVendorModelCallback(VendorModelCallback vendorModelCallback) {
        VendorModelClient vendorModelClient = this.q;
        if (vendorModelClient != null) {
            vendorModelClient.unregisterCallback(vendorModelCallback);
        }
    }

    public synchronized SppTransportLayer c() {
        if (this.e == null) {
            ZLogger.d(D, "create SppTransportLayer");
            SppTransportLayer sppTransportLayer = SppTransportLayer.getInstance();
            this.e = sppTransportLayer;
            sppTransportLayer.register(this.m);
        }
        return this.e;
    }

    public void d() {
        synchronized (this.n) {
            this.o = false;
            this.n.notifyAll();
        }
    }

    public final void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.UUID");
        i iVar = new i(this, null);
        this.C = iVar;
        this.mContext.registerReceiver(iVar, intentFilter);
    }

    public final void f() {
        j jVar = this.v;
        if (jVar != null) {
            jVar.interrupt();
            this.v = null;
        }
        this.w = false;
    }

    public final void g() {
        try {
            this.mContext.unregisterReceiver(this.C);
        } catch (Exception e2) {
            ZLogger.w(e2.getMessage());
        }
    }

    public void h() {
        synchronized (this.n) {
            if (this.o) {
                try {
                    this.n.wait(5000L);
                } catch (InterruptedException e2) {
                    ZLogger.w(e2.toString());
                }
            }
        }
    }

    public final boolean b(ConnectionParameters connectionParameters) {
        BluetoothDevice bluetoothDeviceA = connectionParameters.a();
        connectionParameters.b();
        if (E) {
            ZLogger.v("processSdpConnect:" + connectionParameters.toString());
        }
        if (!bluetoothDeviceA.fetchUuidsWithSdp()) {
            ZLogger.d("fetchUuidsWithSdp failed");
            a(256);
            if (c().connect(connectionParameters.c())) {
                return true;
            }
            ZLogger.w("connect failed");
            a(0);
            a(this.c, this.h);
            return false;
        }
        ThreadPoolExecutor threadPoolExecutor = this.l;
        if (threadPoolExecutor == null) {
            ZLogger.d("syncExecutor == null");
            return false;
        }
        threadPoolExecutor.execute(new h(connectionParameters));
        return true;
    }

    public BeeError sendVendorCommand(Command command) {
        SppTransportLayer sppTransportLayer = this.e;
        if (sppTransportLayer == null) {
            return new BeeError(32);
        }
        if (sppTransportLayer.sendCommand(command)) {
            return new BeeError(0);
        }
        return new BeeError(1, "send Cmd failed");
    }

    public int startConnect(BluetoothDevice bluetoothDevice, UUID uuid) {
        return startConnect(new ConnectionParameters.Builder(bluetoothDevice).uuid(uuid).build());
    }

    public void a(int i2, boolean z) {
        int i3 = this.k;
        if (i2 != i3) {
            ZLogger.d(String.format("state 0x%04X > 0x%04X", Integer.valueOf(i3), Integer.valueOf(i2)));
            this.k = i2;
        }
        if (z) {
            List<BumblebeeCallback> list = this.p;
            if (list != null && list.size() > 0) {
                Iterator<BumblebeeCallback> it = this.p.iterator();
                while (it.hasNext()) {
                    it.next().onStateChanged(this.k);
                }
                return;
            }
            ZLogger.v(E, "no callback registered");
        }
    }

    public int startConnect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        return startConnect(new ConnectionParameters.Builder(bluetoothDevice).uuid(getBeeProParams().b()).transport(getBeeProParams().a()).bluetoothSocket(bluetoothSocket).build());
    }

    public int startConnect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket, UUID uuid) {
        return startConnect(new ConnectionParameters.Builder(bluetoothDevice).uuid(uuid).bluetoothSocket(bluetoothSocket).build());
    }

    public final String a(Context context, int i2) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> arrayList = new ArrayList<>();
        if (activityManager != null) {
            arrayList = activityManager.getRunningAppProcesses();
        }
        if (arrayList == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : arrayList) {
            if (runningAppProcessInfo.pid == i2) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    public int startConnect(ConnectionParameters connectionParameters) {
        BluetoothDevice bluetoothDeviceA = connectionParameters.a();
        connectionParameters.b();
        if (isActiveDevice(bluetoothDeviceA)) {
            if (isConnected()) {
                if (D) {
                    ZLogger.d("device " + BluetoothHelper.formatAddress(bluetoothDeviceA.getAddress(), true) + " already connected");
                }
                a(512);
                b(bluetoothDeviceA);
                a(this.c, this.h);
                return 0;
            }
            if (this.h == 256) {
                if (D) {
                    ZLogger.d("device " + BluetoothHelper.formatAddress(bluetoothDeviceA.getAddress(), true) + " is already in connecting");
                }
                return 0;
            }
        } else if (this.h == 256) {
            if (D) {
                ZLogger.d("old device " + BluetoothHelper.formatAddress(bluetoothDeviceA.getAddress(), true) + " is already in connecting");
            }
            disconnect();
            return 1;
        }
        b(bluetoothDeviceA);
        if (bluetoothDeviceA.getBondState() == 12) {
            if (!Utils.checkUuid(bluetoothDeviceA.getUuids(), connectionParameters.d(), true) && connectionParameters.e()) {
                if (!b(connectionParameters)) {
                    return 1;
                }
            } else {
                a(256);
                if (!c().connect(connectionParameters.c())) {
                    ZLogger.d("connect failed");
                    a(0);
                    a(this.c, this.h);
                    return 1;
                }
            }
        } else if (!a(connectionParameters)) {
            return 1;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b() {
        /*
            r7 = this;
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r1 = r7.c
            r2 = 1
            int r0 = r0.getConnectionState(r2, r1)
            r1 = 2
            r3 = 0
            if (r1 != r0) goto L16
            java.lang.String r4 = "HFP already connected"
            com.realsil.sdk.core.logger.ZLogger.v(r4)
        L14:
            r4 = 0
            goto L5d
        L16:
            if (r2 != r0) goto L5c
            boolean r0 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.D
            java.lang.String r4 = "HFP already connecting..., wait create hfp result"
            com.realsil.sdk.core.logger.ZLogger.d(r0, r4)
            java.lang.Object r4 = r7.A
            monitor-enter(r4)
            java.lang.Object r0 = r7.A     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            r5 = 15000(0x3a98, double:7.411E-320)
            r0.wait(r5)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L2c
            goto L34
        L2a:
            r0 = move-exception
            goto L5a
        L2c:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L2a
            com.realsil.sdk.core.logger.ZLogger.w(r0)     // Catch: java.lang.Throwable -> L2a
        L34:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2a
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r4 = r7.c
            int r0 = r0.getConnectionState(r2, r4)
            boolean r4 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r5[r3] = r6
            java.lang.String r6 = "hfpState = 0x%02X"
            java.lang.String r5 = java.lang.String.format(r6, r5)
            com.realsil.sdk.core.logger.ZLogger.v(r4, r5)
            if (r1 == r0) goto L14
            java.lang.String r4 = "hfp back connect failed"
            com.realsil.sdk.core.logger.ZLogger.d(r4)
            goto L5c
        L5a:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2a
            throw r0
        L5c:
            r4 = 1
        L5d:
            if (r4 == 0) goto Lb4
            boolean r4 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r3] = r0
            java.lang.String r0 = "hfpState = 0x%02X"
            java.lang.String r0 = java.lang.String.format(r0, r5)
            com.realsil.sdk.core.logger.ZLogger.v(r4, r0)
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r4 = r7.c
            java.lang.String r4 = r4.getAddress()
            boolean r0 = r0.connectHfpAg(r4)
            if (r0 != 0) goto L88
            java.lang.String r0 = "connect Hfp failed"
            com.realsil.sdk.core.logger.ZLogger.w(r0)
            goto Lb4
        L88:
            boolean r0 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.String r4 = "wait create hfp result"
            com.realsil.sdk.core.logger.ZLogger.v(r0, r4)
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r4 = r7.c
            int r0 = r0.getConnectionState(r2, r4)
            boolean r4 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r2[r3] = r5
            java.lang.String r3 = "hfpState = 0x%02X"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            com.realsil.sdk.core.logger.ZLogger.v(r4, r2)
            if (r1 == r0) goto Lb4
            java.lang.String r0 = "hfp connect failed"
            com.realsil.sdk.core.logger.ZLogger.d(r0)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.bbpro.internal.BaseBeeProManager.b():void");
    }

    public final void a(BluetoothDevice bluetoothDevice) {
        this.r = false;
        if (bluetoothDevice == null) {
            return;
        }
        try {
            new Handler(Looper.getMainLooper()).postDelayed(new f(bluetoothDevice), 1000L);
        } catch (Exception e2) {
            ZLogger.w(e2.toString());
        }
    }

    public final void a(int i2) {
        int i3 = this.h;
        if (i2 != i3) {
            ZLogger.v(D, String.format(Locale.US, "connect state 0x%04X -> 0x%04X", Integer.valueOf(i3), Integer.valueOf(i2)));
        }
        this.i = this.h;
        this.h = i2;
    }

    public final void a(BluetoothDevice bluetoothDevice, int i2) {
        if (i2 == 512) {
            if (this.c != null && getBeeProParams().d()) {
                int connectionState = BluetoothProfileManager.getInstance().getConnectionState(2, this.c);
                int bondState = bluetoothDevice.getBondState();
                ZLogger.v(D, String.format(Locale.US, "a2dpState=0x%02X, bondState=%d", Integer.valueOf(connectionState), Integer.valueOf(bondState)));
                if (connectionState == 0) {
                    if (bondState == 12) {
                        ZLogger.v(D, "wait to connect a2dp");
                        a(this.c);
                    } else {
                        ZLogger.d(D, "wait paired and then to connect a2dp");
                        this.r = true;
                    }
                }
            }
            a(263, true);
            if (isConnectionStateChanged()) {
                ZLogger.v(String.format("connection state change from 0x%04X to 0x%04X", Integer.valueOf(this.i), Integer.valueOf(this.h)));
                if (getBeeProParams().g()) {
                    syncData();
                } else {
                    if (E) {
                        ZLogger.v(getDeviceInfo().toString());
                    }
                    a(264, true);
                }
            } else {
                ZLogger.v("connection state no changed");
                if (E) {
                    ZLogger.v(getDeviceInfo().toString());
                }
                a(264, false);
            }
        } else if (i2 == 0) {
            a(260, true);
            this.r = false;
            f();
        } else if (i2 == 768) {
            a(262, true);
        } else {
            a(261, true);
        }
        List<BumblebeeCallback> list = this.p;
        if (list != null && list.size() > 0) {
            Iterator<BumblebeeCallback> it = this.p.iterator();
            while (it.hasNext()) {
                it.next().onConnectionStateChanged(bluetoothDevice, 0, i2);
            }
            return;
        }
        ZLogger.v("no callback registered");
    }

    public final void b(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.v(D, "clear device info");
        } else {
            if (E) {
                ZLogger.d("update device:" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
            }
            this.d = this.c;
        }
        this.c = bluetoothDevice;
    }

    public final boolean a(ConnectionParameters connectionParameters) throws NoSuchMethodException, SecurityException {
        boolean zCreateBond;
        BluetoothDevice bluetoothDeviceA = connectionParameters.a();
        connectionParameters.b();
        if (E) {
            ZLogger.v("processBondConnect:" + connectionParameters.toString());
        }
        if (Build.VERSION.SDK_INT >= 23) {
            zCreateBond = BluetoothDeviceImpl.createBond(bluetoothDeviceA, 1);
        } else {
            zCreateBond = BluetoothDeviceImpl.createBond(bluetoothDeviceA, 1);
        }
        if (!zCreateBond && D) {
            ZLogger.d("createBond failed, maybe need to confirm pair dialog");
        }
        ThreadPoolExecutor threadPoolExecutor = this.l;
        if (threadPoolExecutor == null) {
            ZLogger.d("syncExecutor == null");
            return false;
        }
        threadPoolExecutor.execute(new g(bluetoothDeviceA, connectionParameters));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c8 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r9 = this;
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r1 = r9.c
            r2 = 2
            int r0 = r0.getConnectionState(r2, r1)
            r3 = 15000(0x3a98, double:7.411E-320)
            r1 = 0
            r5 = 1
            if (r2 != r0) goto L17
            java.lang.String r0 = "A2DP already connected"
            com.realsil.sdk.core.logger.ZLogger.v(r0)
            goto L59
        L17:
            if (r5 != r0) goto L5d
            boolean r0 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.D
            java.lang.String r6 = "A2DP already connecting..., wait create A2DP result"
            com.realsil.sdk.core.logger.ZLogger.d(r0, r6)
            java.lang.Object r6 = r9.z
            monitor-enter(r6)
            java.lang.Object r0 = r9.z     // Catch: java.lang.Throwable -> L29 java.lang.InterruptedException -> L2b
            r0.wait(r3)     // Catch: java.lang.Throwable -> L29 java.lang.InterruptedException -> L2b
            goto L33
        L29:
            r0 = move-exception
            goto L5b
        L2b:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L29
            com.realsil.sdk.core.logger.ZLogger.w(r0)     // Catch: java.lang.Throwable -> L29
        L33:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L29
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r6 = r9.c
            int r0 = r0.getConnectionState(r2, r6)
            boolean r6 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.Object[] r7 = new java.lang.Object[r5]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            r7[r1] = r8
            java.lang.String r8 = "a2dpState = 0x%02X"
            java.lang.String r7 = java.lang.String.format(r8, r7)
            com.realsil.sdk.core.logger.ZLogger.v(r6, r7)
            if (r2 == r0) goto L59
            java.lang.String r0 = "A2DP back connect failed"
            com.realsil.sdk.core.logger.ZLogger.d(r0)
            goto L70
        L59:
            r0 = 0
            goto L71
        L5b:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L29
            throw r0
        L5d:
            boolean r6 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.Object[] r7 = new java.lang.Object[r5]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7[r1] = r0
            java.lang.String r0 = "a2dpState = 0x%02X"
            java.lang.String r0 = java.lang.String.format(r0, r7)
            com.realsil.sdk.core.logger.ZLogger.v(r6, r0)
        L70:
            r0 = 1
        L71:
            if (r0 == 0) goto Lc8
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r6 = r9.c
            boolean r0 = r0.connectA2dpSource(r6)
            if (r0 != 0) goto L85
            java.lang.String r0 = "connect A2DP failed"
            com.realsil.sdk.core.logger.ZLogger.w(r0)
            goto Lc8
        L85:
            boolean r0 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.String r6 = "wait create A2DP result"
            com.realsil.sdk.core.logger.ZLogger.v(r0, r6)
            java.lang.Object r0 = r9.z
            monitor-enter(r0)
            java.lang.Object r6 = r9.z     // Catch: java.lang.Throwable -> L96 java.lang.InterruptedException -> L98
            r6.wait(r3)     // Catch: java.lang.Throwable -> L96 java.lang.InterruptedException -> L98
            goto La0
        L96:
            r1 = move-exception
            goto Lc6
        L98:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L96
            com.realsil.sdk.core.logger.ZLogger.w(r3)     // Catch: java.lang.Throwable -> L96
        La0:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L96
            com.realsil.sdk.core.bluetooth.BluetoothProfileManager r0 = com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getInstance()
            android.bluetooth.BluetoothDevice r3 = r9.c
            int r0 = r0.getConnectionState(r2, r3)
            boolean r3 = com.realsil.sdk.bbpro.internal.BaseBeeProManager.E
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r4[r1] = r5
            java.lang.String r1 = "a2dpState = 0x%02X"
            java.lang.String r1 = java.lang.String.format(r1, r4)
            com.realsil.sdk.core.logger.ZLogger.v(r3, r1)
            if (r2 == r0) goto Lc8
            java.lang.String r0 = "A2DP connect failed"
            com.realsil.sdk.core.logger.ZLogger.d(r0)
            goto Lc8
        Lc6:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L96
            throw r1
        Lc8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.bbpro.internal.BaseBeeProManager.a():void");
    }
}
