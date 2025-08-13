package com.realsil.customer.bbpro.internal;

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
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.realsil.customer.bbpro.BeeProParams;
import com.realsil.customer.bbpro.BumblebeeCallback;
import com.realsil.customer.bbpro.ConnectionParameters;
import com.realsil.customer.bbpro.RtkBbpro;
import com.realsil.customer.bbpro.core.BeeError;
import com.realsil.customer.bbpro.core.Utils;
import com.realsil.customer.bbpro.core.protocol.CommandContract;
import com.realsil.customer.bbpro.core.transportlayer.AckPacket;
import com.realsil.customer.bbpro.core.transportlayer.Command;
import com.realsil.customer.bbpro.core.transportlayer.SppTransportLayer;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayerCallback;
import com.realsil.customer.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.customer.bbpro.equalizer.EqModelClient;
import com.realsil.customer.bbpro.i.e;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.bbpro.profile.AppReq;
import com.realsil.customer.bbpro.profile.GetCfgSettingsReq;
import com.realsil.customer.bbpro.profile.GetStatusReq;
import com.realsil.customer.bbpro.profile.MmiReq;
import com.realsil.customer.bbpro.profile.SetCfgSettingsReq;
import com.realsil.customer.bbpro.vendor.VendorModelCallback;
import com.realsil.customer.bbpro.vendor.VendorModelClient;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.bluetooth.BluetoothProfileCallback;
import com.realsil.customer.core.bluetooth.BluetoothProfileManager;
import com.realsil.customer.core.bluetooth.RtkBluetoothManager;
import com.realsil.customer.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.customer.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
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

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager.class */
public class BaseBeeProManager {
    public static boolean D = true;
    public static boolean E = false;
    public static BeeProParams F;
    public static final int STATE_IDLE_MASK = 256;
    public static final int STATE_INIT = 257;
    public static final int STATE_DEVICE_DISCONNECTED = 260;
    public static final int STATE_DEVICE_CONNECTING = 261;
    public static final int STATE_DEVICE_DISCONNECTING = 262;
    public static final int STATE_DEVICE_CONNECTED = 263;
    public static final int STATE_DATA_PREPARED = 264;
    public static final int STATE_DATA_SYNC_FAILED = 265;
    public static final int STATE_DATA_SYNC_MASK = 512;
    public static final int STATE_DATA_SYNC_PROCESSING = 513;

    @Keep
    protected Context mContext;
    public BluetoothAdapter a;
    public SppTransportLayer e;
    public volatile boolean g;
    public boolean j;
    public ThreadPoolExecutor l;
    public Object n;
    public VendorModelClient q;
    public final RtkBluetoothManagerCallback s;
    public final BluetoothProfileCallback t;
    public VendorModelCallback u;
    public j v;
    public boolean w;
    public com.realsil.customer.bbpro.e.b x;
    public final Object y;
    public final Object z;
    public final Object A;
    public final Object B;
    public i C;
    public boolean b = false;
    public BluetoothDevice c = null;
    public BluetoothDevice d = null;
    public com.realsil.customer.bbpro.e.a f = null;
    public int h = 0;
    public int i = 0;
    public int k = 257;
    public final TransportLayerCallback m = new a();
    public boolean o = false;
    public List<BumblebeeCallback> p = new CopyOnWriteArrayList();
    public boolean r = true;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$a.class */
    public class a extends TransportLayerCallback {
        public a() {
        }

        @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayerCallback
        public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) throws IOException {
            super.onConnectionStateChanged(bluetoothDevice, z, i);
            if (BaseBeeProManager.this.isDeviceChanged(bluetoothDevice)) {
                ZLogger.d("device changed, just ignore here");
                return;
            }
            if (i == 2) {
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

        @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayerCallback
        public void onAckReceive(AckPacket ackPacket) {
            super.onAckReceive(ackPacket);
            BaseBeeProManager.this.processAckPacket(ackPacket);
        }

        @Override // com.realsil.customer.bbpro.core.transportlayer.TransportLayerCallback
        public void onDataReceive(TransportLayerPacket transportLayerPacket) {
            super.onDataReceive(transportLayerPacket);
            BaseBeeProManager.this.processEventPacket(transportLayerPacket);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$b.class */
    public class b extends RtkBluetoothManagerCallback {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v8, types: [com.realsil.customer.bbpro.internal.BaseBeeProManager$b] */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r8v0, types: [com.realsil.customer.bbpro.internal.BaseBeeProManager$b, com.realsil.customer.core.bluetooth.RtkBluetoothManagerCallback, java.lang.Throwable] */
        @Override // com.realsil.customer.core.bluetooth.RtkBluetoothManagerCallback
        public void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onBondStateChanged(bluetoothDevice, i);
            BluetoothDevice bluetoothDevice2 = BaseBeeProManager.this.c;
            if (bluetoothDevice2 == null || !bluetoothDevice2.equals(bluetoothDevice)) {
                return;
            }
            int connectionState = BluetoothProfileManager.getInstance().getConnectionState(2, BaseBeeProManager.this.c);
            int bondState = bluetoothDevice.getBondState();
            ZLogger.v(String.format(Locale.US, "a2dpState=0x%02X, bondState=%d", Integer.valueOf(connectionState), Integer.valueOf(bondState)));
            if (bondState == 12) {
                synchronized (BaseBeeProManager.this.y) {
                    BaseBeeProManager.this.y.notifyAll();
                }
                if (!BaseBeeProManager.this.r || connectionState == 2) {
                    return;
                }
                ZLogger.d("auto connect a2dp when paired");
                BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
                baseBeeProManager.a(baseBeeProManager.c);
                return;
            }
            if (bondState == 10) {
                ?? r0 = this;
                Object obj = BaseBeeProManager.this.y;
                synchronized (obj) {
                    if (BaseBeeProManager.this.h == 1) {
                        BaseBeeProManager.this.y.notifyAll();
                    }
                    r0 = obj;
                }
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$c.class */
    public class c extends BluetoothProfileCallback {
        public c() {
        }

        /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object, java.lang.Throwable] */
        @Override // com.realsil.customer.core.bluetooth.BluetoothProfileCallback
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

        /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Object, java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object, java.lang.Throwable] */
        @Override // com.realsil.customer.core.bluetooth.BluetoothProfileCallback
        public void onHfpConnectionStateChanged(BluetoothDevice bluetoothDevice, int i) throws IOException {
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
                    ?? r0 = BaseBeeProManager.this.A;
                    synchronized (r0) {
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
            ?? r02 = BaseBeeProManager.this.A;
            synchronized (r02) {
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

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$d.class */
    public class d extends VendorModelCallback {
        public d() {
        }

        @Override // com.realsil.customer.bbpro.internal.ModelClientCallback
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

        /* JADX WARN: Removed duplicated region for block: B:9:0x002f  */
        @Override // com.realsil.customer.bbpro.vendor.VendorModelCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onDeviceInfoChanged(int r6, @androidx.annotation.NonNull com.realsil.customer.bbpro.model.DeviceInfo r7) {
            /*
                r5 = this;
                r0 = r6
                r1 = r5
                r2 = r6
                r3 = r7
                super.onDeviceInfoChanged(r2, r3)
                r1 = 2
                if (r0 == r1) goto L2f
                r0 = r6
                r1 = 58
                if (r0 == r1) goto L2f
                r0 = r6
                switch(r0) {
                    case 11: goto L2f;
                    case 12: goto L2f;
                    case 13: goto L2f;
                    default: goto L2c;
                }
            L2c:
                goto L36
            L2f:
                r0 = r5
                com.realsil.customer.bbpro.internal.BaseBeeProManager r0 = com.realsil.customer.bbpro.internal.BaseBeeProManager.this
                r0.d()
            L36:
                r0 = r5
                com.realsil.customer.bbpro.internal.BaseBeeProManager r0 = com.realsil.customer.bbpro.internal.BaseBeeProManager.this
                r1 = r7
                r2 = r6
                r0.notifyDeviceInfoChanged(r1, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.bbpro.internal.BaseBeeProManager.d.onDeviceInfoChanged(int, com.realsil.customer.bbpro.model.DeviceInfo):void");
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$e.class */
    public class e extends com.realsil.customer.bbpro.e.b {
        public e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v11, types: [android.bluetooth.BluetoothSocket] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        @Override // com.realsil.customer.bbpro.e.b
        public void a(BluetoothDevice bluetoothDevice, boolean z, BluetoothSocket bluetoothSocket) throws IOException {
            if (!z) {
                ?? r0 = bluetoothSocket;
                if (r0 != 0) {
                    try {
                        r0 = bluetoothSocket;
                        r0.close();
                    } catch (IOException unused) {
                        ZLogger.w(r0.getMessage());
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

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$f.class */
    public class f implements Runnable {
        public final /* synthetic */ BluetoothDevice a;

        public f(BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZLogger.v(BaseBeeProManager.E, "connect a2dp");
            ZLogger.d(BaseBeeProManager.D, "connectA2dpSource:" + BluetoothProfileManager.getInstance().connectA2dpSource(this.a.getAddress()));
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$g.class */
    public class g implements Runnable {
        public final /* synthetic */ BluetoothDevice a;
        public final /* synthetic */ ConnectionParameters b;

        public g(BluetoothDevice bluetoothDevice, ConnectionParameters connectionParameters) {
            this.a = bluetoothDevice;
            this.b = connectionParameters;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v13, types: [com.realsil.customer.bbpro.internal.BaseBeeProManager$g] */
        /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v17 */
        @Override // java.lang.Runnable
        public void run() {
            ?? r0;
            Object obj;
            try {
                r0 = this;
                obj = BaseBeeProManager.this.y;
            } catch (InterruptedException unused) {
                ZLogger.w(r0.getMessage());
            }
            synchronized (obj) {
                if (r0.a.getBondState() != 12) {
                    BaseBeeProManager.this.y.wait(15000L);
                }
                r0 = obj;
                if (this.a.getBondState() != 12) {
                    BaseBeeProManager.this.a(0);
                    BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
                    baseBeeProManager.a(baseBeeProManager.c, baseBeeProManager.h);
                    return;
                }
                BaseBeeProManager.this.a();
                BaseBeeProManager.this.b();
                BaseBeeProManager.this.a(1);
                if (BaseBeeProManager.this.c().connect(this.a, null, this.b.d())) {
                    return;
                }
                ZLogger.w("connect failed");
                BaseBeeProManager.this.a(0);
                BaseBeeProManager baseBeeProManager2 = BaseBeeProManager.this;
                baseBeeProManager2.a(baseBeeProManager2.c, baseBeeProManager2.h);
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$h.class */
    public class h implements Runnable {
        public final /* synthetic */ ConnectionParameters a;

        public h(ConnectionParameters connectionParameters) {
            this.a = connectionParameters;
        }

        /* JADX WARN: Not initialized variable reg: 0, insn: 0x001f: INVOKE (r0 I:java.lang.String) = (r0 I:java.lang.Throwable) VIRTUAL call: java.lang.Throwable.getMessage():java.lang.String A[Catch: InterruptedException -> 0x001f, MD:():java.lang.String (c), TRY_LEAVE] (LINE:5), block:B:13:0x001f */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, java.lang.Throwable] */
        @Override // java.lang.Runnable
        public void run() {
            ?? message;
            try {
                synchronized (BaseBeeProManager.this.B) {
                    BaseBeeProManager.this.B.wait(5000L);
                }
            } catch (InterruptedException unused) {
                ZLogger.w(message.getMessage());
            }
            BaseBeeProManager.this.a(1);
            if (BaseBeeProManager.this.c().connect(this.a.c())) {
                return;
            }
            ZLogger.w("connect failed");
            BaseBeeProManager.this.a(0);
            BaseBeeProManager baseBeeProManager = BaseBeeProManager.this;
            baseBeeProManager.a(baseBeeProManager.c, baseBeeProManager.h);
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$i.class */
    public class i extends BroadcastReceiver {
        public i() {
        }

        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object, java.lang.Throwable] */
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

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/internal/BaseBeeProManager$j.class */
    public class j extends Thread {
        public j() {
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

        public final boolean a() {
            if (BaseBeeProManager.this.isConnected()) {
                return true;
            }
            BaseBeeProManager.this.a(260, false);
            ZLogger.v("sync interrupted, because of connection disconnected");
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

        public /* synthetic */ j(BaseBeeProManager baseBeeProManager, a aVar) {
            this();
        }
    }

    public int getState() {
        return this.k;
    }

    public boolean registerModel(ModelClient modelClient) {
        if (modelClient == null) {
            ZLogger.w("model can not be null");
            return false;
        }
        modelClient.setup(this);
        return true;
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
        if (bArr == null || bArr.length <= 0) {
            return false;
        }
        byte b2 = bArr[0];
        VendorModelClient vendorModelClient = this.q;
        return vendorModelClient != null && vendorModelClient.processStatusReport(b2, bArr);
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

    public EqModelClient getEqModelClient() {
        EqModelClient eqModelClient = EqModelClient.getInstance();
        EqModelClient eqModelClient2 = eqModelClient;
        if (eqModelClient == null) {
            EqModelClient.initialize(this.mContext);
            EqModelClient eqModelClient3 = EqModelClient.getInstance();
            eqModelClient2 = eqModelClient3;
            if (eqModelClient3 != null) {
                eqModelClient2.setup(this);
            }
        }
        return eqModelClient2;
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

    public void registerVendorModelCallback(VendorModelCallback vendorModelCallback) {
        VendorModelClient vendorModelClient = this.q;
        if (vendorModelClient != null) {
            vendorModelClient.registerCallback(vendorModelCallback);
        }
    }

    public void unregisterVendorModelCallback(VendorModelCallback vendorModelCallback) {
        VendorModelClient vendorModelClient = this.q;
        if (vendorModelClient != null) {
            vendorModelClient.unregisterCallback(vendorModelCallback);
        }
    }

    public void getRwsInfo() {
        getVendorClient().getBudInfo();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v5, types: [boolean] */
    public synchronized boolean syncData() {
        Object obj;
        if (this.k == 513 || (obj = this.w) != 0) {
            ZLogger.v("already in syncing data");
            return true;
        }
        try {
            if (this.v == null) {
                j jVar = new j(this, null);
                this.v = jVar;
                jVar.start();
                obj = jVar;
            } else {
                ZLogger.v(E, "sync thread isAlive:" + this.v.isAlive());
                if (this.v.isAlive()) {
                    boolean z = D;
                    ZLogger.v(z, "sync thread is already started");
                    obj = z;
                } else {
                    ZLogger.v(E, "restart sync thread when it's dead");
                    j jVar2 = this.v;
                    jVar2.start();
                    obj = jVar2;
                }
            }
            return true;
        } catch (Exception unused) {
            ZLogger.w(obj.toString());
            j jVar3 = new j(this, null);
            this.v = jVar3;
            jVar3.start();
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.realsil.customer.bbpro.e.a, java.lang.Thread] */
    public void startSppServerThread() {
        Object objF;
        if (this.g || (objF = getBeeProParams().f()) == 0) {
            return;
        }
        try {
            objF = new com.realsil.customer.bbpro.e.a(this.mContext, getBeeProParams().b(), this.x);
            this.f = objF;
            objF.start();
        } catch (Exception unused) {
            ZLogger.w(objF.toString());
        }
    }

    public void stopSppServerThread() throws IOException {
        this.g = true;
        com.realsil.customer.bbpro.e.a aVar = this.f;
        if (aVar != null) {
            aVar.a();
        }
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

    public boolean isConnected() {
        return c().getConnectionState() == 2;
    }

    public boolean isDisconnected() {
        return c().getConnectionState() == 0;
    }

    public int getConnectState() {
        return c().getConnectionState();
    }

    public BeeError connect(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return new BeeError(1, "BluetoothDevice is invalid");
        }
        if (!isConnected()) {
            ZLogger.v(D, "connectionstate = " + getConnectState());
            return new BeeError(startConnect(new ConnectionParameters.Builder(bluetoothDevice).uuid(getBeeProParams().b()).transport(getBeeProParams().a()).build()));
        }
        ZLogger.d("already connected");
        a(bluetoothDevice, 2);
        return new BeeError(0);
    }

    public int startConnect(BluetoothDevice bluetoothDevice) {
        return startConnect(new ConnectionParameters.Builder(bluetoothDevice).uuid(getBeeProParams().b()).transport(getBeeProParams().a()).build());
    }

    public BeeError disconnect() throws IOException {
        if (this.h == 0) {
            ZLogger.d("connection has already disconnected");
            a(this.c, this.h);
        } else {
            SppTransportLayer sppTransportLayer = this.e;
            if (sppTransportLayer == null) {
                ZLogger.d("SppTransportLayer has already been released");
                a(this.c, 0);
            } else if (sppTransportLayer.getConnectionState() == 2 || this.e.getConnectionState() == 1) {
                a(3);
                this.e.disconnect();
            } else if (this.e.getConnectionState() == 3) {
                a(3);
                a(this.c, this.h);
            } else {
                a(this.c, 0);
            }
        }
        return new BeeError(0);
    }

    public BluetoothDevice getCurDevice() {
        return this.c;
    }

    public int getConnState() {
        return this.h;
    }

    public DeviceInfo getDeviceInfo() {
        VendorModelClient vendorModelClient = this.q;
        return vendorModelClient != null ? vendorModelClient.getDeviceInfo() : new DeviceInfo();
    }

    public void clearDeviceInfo() {
        VendorModelClient vendorModelClient = this.q;
        if (vendorModelClient != null) {
            vendorModelClient.clearDeviceInfo();
        }
    }

    public boolean isConnectionStateChanged() {
        return this.h != this.i;
    }

    public boolean isDeviceChanged(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (this.c != null) {
            return !r0.equals(bluetoothDevice);
        }
        if (this.d == null) {
            return true;
        }
        return !r0.equals(bluetoothDevice);
    }

    public boolean isActiveDevice(BluetoothDevice bluetoothDevice) {
        BluetoothDevice bluetoothDevice2;
        if (bluetoothDevice == null || (bluetoothDevice2 = this.c) == null) {
            return false;
        }
        return bluetoothDevice2.equals(bluetoothDevice);
    }

    public BeeError changeDeviceName(byte b2, @NonNull String str) {
        return sendVendorCommand(new SetCfgSettingsReq.Builder(b2).cfgData(str.getBytes()).assembleDataLength(true).build().encode());
    }

    public BeeError reqPackageId() {
        return sendVendorCommand(CommandContract.buildPacket((short) 783));
    }

    public BeeError reqCmdSetVersion() {
        return sendVendorCommand(new e.b().a(262).b(com.realsil.customer.bbpro.c.c.a()).a().encode());
    }

    public BeeError reqCapability() {
        return sendVendorCommand(CommandContract.reqCmdInfo((byte) 1));
    }

    public BeeError getName(byte b2) {
        return reqDeviceName(b2);
    }

    public BeeError reqLeName() {
        return reqDeviceName((byte) 0);
    }

    public BeeError reqBrEdrName(byte b2) {
        return reqDeviceName((byte) 1);
    }

    public BeeError reqDeviceName(byte b2) {
        return sendVendorCommand(new GetCfgSettingsReq.Builder(b2).build().encode());
    }

    public BeeError getLeAddr() {
        return sendVendorCommand(CommandContract.buildPacket((short) 261));
    }

    public BeeError triggerBleAdvertising() {
        return sendVendorCommand(new MmiReq.Builder((byte) -96).build().encode());
    }

    public BeeError toggleDspPassthrough() {
        return sendVendorCommand(new MmiReq.Builder((byte) 101).build().encode());
    }

    public BeeError changeRwsChannelSetting() {
        return sendVendorCommand(new MmiReq.Builder((byte) 116).build().encode());
    }

    public BeeError setVolumeUp() {
        return sendVendorCommand(new MmiReq.Builder((byte) 48).build().encode());
    }

    public BeeError setVolumeDown() {
        return sendVendorCommand(new MmiReq.Builder((byte) 49).build().encode());
    }

    public BeeError enterPairingMode() {
        return sendVendorCommand(new MmiReq.Builder((byte) 81).build().encode());
    }

    public BeeError exitPairingMode() {
        return sendVendorCommand(new MmiReq.Builder((byte) 82).build().encode());
    }

    public BeeError powerOff() {
        return sendVendorCommand(new MmiReq.Builder((byte) 86).build().encode());
    }

    public BeeError sendATone(int i2) {
        return sendVendorCommand(CommandContract.buildPacket((short) 8, new byte[]{(byte) i2}));
    }

    public BeeError getStatus(byte b2) {
        return sendVendorCommand(new GetStatusReq.Builder(b2).build().encode());
    }

    public BeeError getOtaDeviceInfo() {
        return sendVendorCommand(CommandContract.buildPacket((short) 1536));
    }

    public BeeError sendAppReq(@NonNull AppReq appReq) {
        return getVendorClient().sendAppReq(appReq);
    }

    public BeeError sendUserCommand(byte[] bArr) {
        return sendVendorCommand(bArr);
    }

    public BeeError sendVendorCommand(byte[] bArr) {
        return sendVendorCommand(new Command.Builder().writeType(2).payload(bArr).build());
    }

    public void addManagerCallback(@NonNull BumblebeeCallback bumblebeeCallback) {
        if (this.p == null) {
            this.p = new CopyOnWriteArrayList();
        }
        if (!this.p.contains(bumblebeeCallback)) {
            this.p.add(bumblebeeCallback);
        }
        ZLogger.v(E, "mManagerCallback.size=" + this.p.size());
    }

    public void removeManagerCallback(BumblebeeCallback bumblebeeCallback) {
        List<BumblebeeCallback> list = this.p;
        if (list != null) {
            list.remove(bumblebeeCallback);
            ZLogger.v(E, "mManagerCallback.size=" + this.p.size());
        }
    }

    public void clearManagerCallback() {
        List<BumblebeeCallback> list = this.p;
        if (list != null) {
            list.clear();
            this.p = null;
        }
    }

    public static BeeProParams getBeeProParams() {
        if (F == null) {
            F = new BeeProParams();
        }
        return F;
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

    public synchronized SppTransportLayer c() {
        if (this.e == null) {
            ZLogger.d(D, "create SppTransportLayer");
            SppTransportLayer sppTransportLayer = SppTransportLayer.getInstance();
            this.e = sppTransportLayer;
            sppTransportLayer.register(this.m);
        }
        return this.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.realsil.customer.bbpro.internal.BaseBeeProManager] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public void h() {
        ?? r0 = this;
        Object obj = r0.n;
        synchronized (obj) {
            r0 = r0.o;
            if (r0 == 0) {
                r0 = obj;
                return;
            }
            try {
                r0 = this.n;
                r0.wait(5000L);
            } catch (InterruptedException unused) {
                ZLogger.w(r0.toString());
            }
            r0 = obj;
            return;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Throwable] */
    public void d() {
        synchronized (this.n) {
            this.o = false;
            this.n.notifyAll();
        }
    }

    public final void f() {
        j jVar = this.v;
        if (jVar != null) {
            jVar.interrupt();
            this.v = null;
        }
        this.w = false;
    }

    public final void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.UUID");
        i iVar = new i(this, null);
        this.C = iVar;
        this.mContext.registerReceiver(iVar, intentFilter);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.content.Context, java.lang.Throwable] */
    public final void g() {
        ?? r0;
        try {
            r0 = this.mContext;
            r0.unregisterReceiver(this.C);
        } catch (Exception unused) {
            ZLogger.w(r0.getMessage());
        }
    }

    public final boolean b(@NonNull ConnectionParameters connectionParameters) {
        BluetoothDevice bluetoothDeviceA = connectionParameters.a();
        connectionParameters.b();
        if (E) {
            ZLogger.v("processSdpConnect:" + connectionParameters.toString());
        }
        if (!bluetoothDeviceA.fetchUuidsWithSdp()) {
            ZLogger.d("fetchUuidsWithSdp failed");
            a(1);
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
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = arrayList;
        ArrayList arrayList = new ArrayList();
        if (activityManager != null) {
            runningAppProcesses = activityManager.getRunningAppProcesses();
        }
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == i2) {
                    return runningAppProcessInfo.processName;
                }
            }
            return "";
        }
        return "";
    }

    public int startConnect(@NonNull ConnectionParameters connectionParameters) throws IOException {
        BluetoothDevice bluetoothDeviceA = connectionParameters.a();
        connectionParameters.b();
        if (isActiveDevice(bluetoothDeviceA)) {
            if (isConnected()) {
                if (D) {
                    ZLogger.d("device " + BluetoothHelper.formatAddress(bluetoothDeviceA.getAddress(), true) + " already connected");
                }
                a(2);
                b(bluetoothDeviceA);
                a(this.c, this.h);
                return 0;
            }
            if (this.h == 1) {
                if (!D) {
                    return 0;
                }
                ZLogger.d("device " + BluetoothHelper.formatAddress(bluetoothDeviceA.getAddress(), true) + " is already in connecting");
                return 0;
            }
        } else if (this.h == 1) {
            if (D) {
                ZLogger.d("old device " + BluetoothHelper.formatAddress(bluetoothDeviceA.getAddress(), true) + " is already in connecting");
            }
            disconnect();
            return 1;
        }
        b(bluetoothDeviceA);
        if (bluetoothDeviceA.getBondState() != 12) {
            return !a(connectionParameters) ? 1 : 0;
        }
        if (!Utils.checkUuid(bluetoothDeviceA.getUuids(), connectionParameters.d(), true) && connectionParameters.e()) {
            return !b(connectionParameters) ? 1 : 0;
        }
        a(1);
        if (c().connect(connectionParameters.c())) {
            return 0;
        }
        ZLogger.d("connect failed");
        a(0);
        a(this.c, this.h);
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.realsil.customer.bbpro.internal.BaseBeeProManager] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    public final void b() {
        boolean z = false;
        int connectionState = BluetoothProfileManager.getInstance().getConnectionState(1, this.c);
        if (2 == connectionState) {
            ZLogger.v("HFP already connected");
        } else if (1 == connectionState) {
            ?? r0 = this;
            ZLogger.d(D, "HFP already connecting..., wait create hfp result");
            synchronized (r0.A) {
                try {
                    r0 = r0.A;
                    r0.wait(15000L);
                } catch (InterruptedException unused) {
                    ZLogger.w(r0.getMessage());
                }
            }
            connectionState = BluetoothProfileManager.getInstance().getConnectionState(1, this.c);
            ZLogger.v(E, String.format("hfpState = 0x%02X", Integer.valueOf(connectionState)));
            if (2 != connectionState) {
                ZLogger.d("hfp back connect failed");
                z = true;
            }
        } else {
            z = true;
        }
        if (z) {
            ZLogger.v(E, String.format("hfpState = 0x%02X", Integer.valueOf(connectionState)));
            if (!BluetoothProfileManager.getInstance().connectHfpAg(this.c.getAddress())) {
                ZLogger.w("connect Hfp failed");
                return;
            }
            ZLogger.v(E, "wait create hfp result");
            int connectionState2 = BluetoothProfileManager.getInstance().getConnectionState(1, this.c);
            ZLogger.v(E, String.format("hfpState = 0x%02X", Integer.valueOf(connectionState2)));
            if (2 != connectionState2) {
                ZLogger.d("hfp connect failed");
            }
        }
    }

    public final void a(BluetoothDevice bluetoothDevice) {
        this.r = false;
        if (bluetoothDevice == null) {
            return;
        }
        try {
            new Handler(Looper.getMainLooper()).postDelayed(new f(bluetoothDevice), 1000L);
        } catch (Exception unused) {
            ZLogger.w(bluetoothDevice.toString());
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
        if (i2 == 2) {
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
        } else if (i2 == 3) {
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
        ZLogger.v(E, "no callback registered");
    }

    public final void b(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.v(D, "clear device info");
        } else {
            if (E) {
                ZLogger.d("update device:" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
            }
            BluetoothDevice bluetoothDevice2 = this.c;
            if (bluetoothDevice2 == null) {
                this.d = bluetoothDevice;
            } else {
                this.d = bluetoothDevice2;
            }
        }
        this.c = bluetoothDevice;
    }

    public final boolean a(@NonNull ConnectionParameters connectionParameters) throws NoSuchMethodException, SecurityException {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v22, types: [com.realsil.customer.bbpro.internal.BaseBeeProManager] */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.realsil.customer.bbpro.internal.BaseBeeProManager] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    public final void a() {
        boolean z = false;
        int connectionState = BluetoothProfileManager.getInstance().getConnectionState(2, this.c);
        if (2 == connectionState) {
            ZLogger.v("A2DP already connected");
        } else if (1 == connectionState) {
            ?? r0 = this;
            ZLogger.d(D, "A2DP already connecting..., wait create A2DP result");
            synchronized (r0.z) {
                try {
                    r0 = r0.z;
                    r0.wait(15000L);
                } catch (InterruptedException unused) {
                    ZLogger.w(r0.getMessage());
                }
            }
            int connectionState2 = BluetoothProfileManager.getInstance().getConnectionState(2, this.c);
            ZLogger.v(E, String.format("a2dpState = 0x%02X", Integer.valueOf(connectionState2)));
            if (2 != connectionState2) {
                ZLogger.d("A2DP back connect failed");
                z = true;
            }
        } else {
            ZLogger.v(E, String.format("a2dpState = 0x%02X", Integer.valueOf(connectionState)));
            z = true;
        }
        if (z) {
            if (!BluetoothProfileManager.getInstance().connectA2dpSource(this.c)) {
                ZLogger.w("connect A2DP failed");
                return;
            }
            ?? r02 = this;
            ZLogger.v(E, "wait create A2DP result");
            synchronized (r02.z) {
                try {
                    r02 = r02.z;
                    r02.wait(15000L);
                } catch (InterruptedException unused2) {
                    ZLogger.w(r02.getMessage());
                }
            }
            int connectionState3 = BluetoothProfileManager.getInstance().getConnectionState(2, this.c);
            ZLogger.v(E, String.format("a2dpState = 0x%02X", Integer.valueOf(connectionState3)));
            if (2 != connectionState3) {
                ZLogger.d("A2DP connect failed");
            }
        }
    }
}
