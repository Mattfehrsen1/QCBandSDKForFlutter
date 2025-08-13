package com.realsil.sdk.core.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.impl.BluetoothA2dpSinkImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothHeadsetImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothHidHostImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothInputDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public final class BluetoothProfileManager {
    public static final int INDICATOR_A2DP = 1;
    public static final int INDICATOR_A2DP_SINK = 2;
    public static final int INDICATOR_FULL = 255;
    public static final int INDICATOR_HEADSET = 4;
    public static final int INDICATOR_HID = 8;
    public static BluetoothProfileManager m;
    public boolean a;
    public boolean b;
    public Context c;
    public List<BluetoothProfileCallback> d;
    public BluetoothAdapter e;
    public BluetoothHealth h;
    public BluetoothHeadset f = null;
    public BluetoothA2dp g = null;
    public BluetoothProfile i = null;
    public BluetoothProfile j = null;
    public ProfileBroadcastReceiver k = null;
    public a l = new a();

    public class ProfileBroadcastReceiver extends BroadcastReceiver {
        public ProfileBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0017  */
        /* JADX WARN: Type inference failed for: r13v11, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v17, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v22, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v28, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v33, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v42, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v47, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v53, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v58, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v6, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r13v63, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        /* JADX WARN: Type inference failed for: r2v7, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onReceive(android.content.Context r13, android.content.Intent r14) {
            /*
                Method dump skipped, instructions count: 746
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.ProfileBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public class a implements BluetoothProfile.ServiceListener {
        public a() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public final void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            ZLogger.d(String.format("%s(%s) profile connected", BluetoothHelper.parseProfile(i), bluetoothProfile.getClass().getName()));
            if (i == 11) {
                BluetoothProfileManager.this.j = bluetoothProfile;
                return;
            }
            if (i == 1) {
                BluetoothProfileManager.this.f = (BluetoothHeadset) bluetoothProfile;
                return;
            }
            if (i == 2) {
                BluetoothProfileManager.this.g = (BluetoothA2dp) bluetoothProfile;
            } else if (i == 3) {
                BluetoothProfileManager.this.h = (BluetoothHealth) bluetoothProfile;
            } else {
                if (i != 4) {
                    return;
                }
                BluetoothProfileManager.this.i = bluetoothProfile;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public final void onServiceDisconnected(int i) {
            ZLogger.d(BluetoothHelper.parseProfile(i) + " profile disconnected");
            if (i == 1) {
                BluetoothProfileManager.this.f = null;
                return;
            }
            if (i == 2) {
                BluetoothProfileManager.this.g = null;
                return;
            }
            if (i == 3) {
                BluetoothProfileManager.this.h = null;
            } else if (i == 4) {
                BluetoothProfileManager.this.i = null;
            } else if (i == 11) {
                BluetoothProfileManager.this.j = null;
            }
        }
    }

    public BluetoothProfileManager(Context context) {
        this.a = false;
        this.b = false;
        this.c = context.getApplicationContext();
        this.a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        a();
    }

    public static BluetoothProfileManager getInstance() {
        return m;
    }

    public static void initial(Context context) {
        if (m == null) {
            synchronized (BluetoothProfileManager.class) {
                if (m == null) {
                    m = new BluetoothProfileManager(context);
                }
            }
        }
    }

    public final boolean a() {
        Context context = this.c;
        if (context == null) {
            ZLogger.w("not intialized");
            return false;
        }
        if (this.e == null) {
            if (Build.VERSION.SDK_INT >= 18) {
                BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
                if (bluetoothManager == null) {
                    ZLogger.w("Unable to initialize BluetoothManager.");
                    return false;
                }
                this.e = bluetoothManager.getAdapter();
            } else {
                this.e = BluetoothAdapter.getDefaultAdapter();
            }
            if (this.e == null) {
                ZLogger.w("Unable to obtain a BluetoothAdapter.");
                return false;
            }
        }
        registerProfiles();
        this.k = new ProfileBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_PLAYING_STATE_CHANGED);
        this.c.registerReceiver(this.k, intentFilter);
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void addManagerCallback(BluetoothProfileCallback bluetoothProfileCallback) {
        if (bluetoothProfileCallback == null) {
            return;
        }
        if (this.d == null) {
            this.d = new CopyOnWriteArrayList();
        }
        if (!this.d.contains(bluetoothProfileCallback)) {
            this.d.add(bluetoothProfileCallback);
        }
        boolean z = this.b;
        StringBuilder sbA = com.realsil.sdk.core.a.a.a("mManagerCallbacks.size=");
        sbA.append(this.d.size());
        ZLogger.v(z, sbA.toString());
    }

    public void close() {
        if (this.a) {
            ZLogger.v("close()");
        }
        Context context = this.c;
        if (context != null) {
            try {
                context.unregisterReceiver(this.k);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        }
    }

    public void closeProfileProxy(int i) {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            return;
        }
        try {
            ZLogger.v(String.format(Locale.US, "profile=%d", Integer.valueOf(i)));
            if (i == 11) {
                this.e.closeProfileProxy(i, this.j);
            } else if (i == 1) {
                this.e.closeProfileProxy(i, this.f);
            } else if (i == 2) {
                this.e.closeProfileProxy(i, this.g);
            } else if (i == 3) {
                this.e.closeProfileProxy(i, this.h);
            } else if (i == 4) {
                this.e.closeProfileProxy(i, this.i);
            }
        } catch (Exception e) {
            ZLogger.e(e.toString());
        }
    }

    public boolean connectA2dpSink(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectA2dpSource(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectHfpAg(byte[] bArr) {
        return connectHfpAg(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectHid(BluetoothDevice bluetoothDevice) {
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.connectProfile(this.i, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice) : BluetoothProfileImpl.connectProfile(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean connectProfile(int i, BluetoothDevice bluetoothDevice) {
        if (i != 4) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.connectProfile(this.i, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice) : BluetoothProfileImpl.connectProfile(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean disConnectHid(BluetoothDevice bluetoothDevice) {
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.disconnect(this.i, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice) : BluetoothProfileImpl.disconnect(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean disconnectA2dpSink(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("device is null");
            return false;
        }
        BluetoothProfile bluetoothProfile = this.j;
        if (bluetoothProfile == null) {
            ZLogger.w("A2DP Sink not initialized");
            getProfileProxy(11);
            return false;
        }
        if (bluetoothProfile.getConnectionState(bluetoothDevice) == 2) {
            return BluetoothProfileImpl.disconnect(this.j, BluetoothA2dpSinkImpl.CLASS_NAME, bluetoothDevice);
        }
        ZLogger.w("A2DP Sink already disconnected");
        return false;
    }

    public boolean disconnectA2dpSource(byte[] bArr) {
        return disconnectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean disconnectHfp(byte[] bArr) {
        return disconnectHfp(BluetoothHelper.convertMac(bArr));
    }

    public boolean disconnectProfile(int i, BluetoothDevice bluetoothDevice) {
        if (i != 4) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.disconnect(this.i, BluetoothHidHostImpl.CLASS_NAME, bluetoothDevice) : BluetoothProfileImpl.disconnect(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<android.bluetooth.BluetoothDevice> getConnectedDevices(int r5) {
        /*
            r4 = this;
            android.bluetooth.BluetoothAdapter r0 = r4.e
            if (r0 == 0) goto L74
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto Lb
            goto L74
        Lb:
            r0 = 11
            r1 = 4
            r2 = 2
            r3 = 1
            if (r5 == r3) goto L19
            if (r5 == r2) goto L2a
            if (r5 == r1) goto L3b
            if (r5 == r0) goto L5b
            goto L6e
        L19:
            android.bluetooth.BluetoothHeadset r5 = r4.f
            if (r5 == 0) goto L22
            java.util.List r5 = r5.getConnectedDevices()
            return r5
        L22:
            java.lang.String r5 = "HEADSET profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r3)
        L2a:
            android.bluetooth.BluetoothA2dp r5 = r4.g
            if (r5 == 0) goto L33
            java.util.List r5 = r5.getConnectedDevices()
            return r5
        L33:
            java.lang.String r5 = "A2DP profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r2)
        L3b:
            android.bluetooth.BluetoothProfile r5 = r4.i
            if (r5 == 0) goto L53
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L4c
            java.lang.String r0 = "android.bluetooth.BluetoothHidHost"
            java.util.List r5 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectedDevices(r5, r0)
            return r5
        L4c:
            java.lang.String r0 = "android.bluetooth.BluetoothInputDevice"
            java.util.List r5 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectedDevices(r5, r0)
            return r5
        L53:
            java.lang.String r5 = "HID_HOST profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r1)
        L5b:
            android.bluetooth.BluetoothProfile r5 = r4.j
            if (r5 == 0) goto L66
            java.lang.String r0 = "android.bluetooth.BluetoothA2dpSink"
            java.util.List r5 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectedDevices(r5, r0)
            return r5
        L66:
            java.lang.String r5 = "A2DP_SINK profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r0)
        L6e:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            return r5
        L74:
            java.lang.String r5 = "BT not enabled"
            com.realsil.sdk.core.logger.ZLogger.w(r5)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getConnectedDevices(int):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getConnectionState(int r5, android.bluetooth.BluetoothDevice r6) {
        /*
            r4 = this;
            android.bluetooth.BluetoothAdapter r0 = r4.e
            if (r0 == 0) goto L70
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto Lb
            goto L70
        Lb:
            r0 = 11
            r1 = 4
            r2 = 2
            r3 = 1
            if (r5 == r3) goto L19
            if (r5 == r2) goto L2a
            if (r5 == r1) goto L3b
            if (r5 == r0) goto L5b
            goto L6e
        L19:
            android.bluetooth.BluetoothHeadset r5 = r4.f
            if (r5 == 0) goto L22
            int r5 = r5.getConnectionState(r6)
            return r5
        L22:
            java.lang.String r5 = "HEADSET profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r3)
        L2a:
            android.bluetooth.BluetoothA2dp r5 = r4.g
            if (r5 == 0) goto L33
            int r5 = r5.getConnectionState(r6)
            return r5
        L33:
            java.lang.String r5 = "A2DP profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r2)
        L3b:
            android.bluetooth.BluetoothProfile r5 = r4.i
            if (r5 == 0) goto L53
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L4c
            java.lang.String r0 = "android.bluetooth.BluetoothHidHost"
            int r5 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r5, r0, r6)
            return r5
        L4c:
            java.lang.String r0 = "android.bluetooth.BluetoothInputDevice"
            int r5 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r5, r0, r6)
            return r5
        L53:
            java.lang.String r5 = "HID_HOST profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r1)
        L5b:
            android.bluetooth.BluetoothProfile r5 = r4.j
            if (r5 == 0) goto L66
            java.lang.String r0 = "android.bluetooth.BluetoothA2dpSink"
            int r5 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r5, r0, r6)
            return r5
        L66:
            java.lang.String r5 = "A2DP_SINK profile not connected"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r4.getProfileProxy(r0)
        L6e:
            r5 = 0
            return r5
        L70:
            java.lang.String r5 = "BT not enabled"
            com.realsil.sdk.core.logger.ZLogger.d(r5)
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getConnectionState(int, android.bluetooth.BluetoothDevice):int");
    }

    public BluetoothProfile getProfile(int i) {
        if (i == 1) {
            return this.f;
        }
        if (i == 2) {
            return this.g;
        }
        if (i == 4) {
            return this.i;
        }
        if (i != 11) {
            return null;
        }
        return this.j;
    }

    public boolean getProfileProxy(int i) {
        try {
            if (!this.e.getProfileProxy(this.c, this.l, i)) {
                ZLogger.w(String.format(Locale.US, "getProfileProxy %d failed", Integer.valueOf(i)));
                return false;
            }
            if (this.b) {
                ZLogger.v(String.format(Locale.US, "getProfileProxy %d success", Integer.valueOf(i)));
            }
            return true;
        } catch (Exception e) {
            ZLogger.w(String.format(Locale.US, "getProfileProxy %d exception: %s", Integer.valueOf(i), e.toString()));
            return false;
        }
    }

    public int getProfileState(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            return -1;
        }
        if (bluetoothProfile != null) {
            return bluetoothProfile.getConnectionState(bluetoothDevice);
        }
        ZLogger.d("profile is not supported");
        return -1;
    }

    public boolean isConnectionFeatureSupported(int i) {
        if (i == 1) {
            return BluetoothProfileImpl.isConnectMethodSupported(this.f, BluetoothHeadsetImpl.CLASS_NAME) && BluetoothProfileImpl.isDisConnectMethodSupported(this.f, BluetoothHeadsetImpl.CLASS_NAME);
        }
        if (i == 2) {
            return BluetoothProfileImpl.isConnectMethodSupported(this.g, BluetoothProfileImpl.CLASS_NAME_BLUETOOTH_A2DP) && BluetoothProfileImpl.isDisConnectMethodSupported(this.g, BluetoothProfileImpl.CLASS_NAME_BLUETOOTH_A2DP);
        }
        if (i != 4) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.isConnectMethodSupported(this.i, BluetoothHidHostImpl.CLASS_NAME) && BluetoothProfileImpl.isDisConnectMethodSupported(this.i, BluetoothHidHostImpl.CLASS_NAME) : BluetoothProfileImpl.isConnectMethodSupported(this.i, BluetoothInputDeviceImpl.CLASS_NAME) && BluetoothProfileImpl.isDisConnectMethodSupported(this.i, BluetoothInputDeviceImpl.CLASS_NAME);
    }

    public boolean isDeviceConnected(BluetoothDevice bluetoothDevice) throws NoSuchMethodException, SecurityException {
        if (bluetoothDevice == null) {
            ZLogger.d("no activeBluetoothDevice exist");
            return false;
        }
        int connectionState = getInstance().getConnectionState(1, bluetoothDevice);
        int connectionState2 = getInstance().getConnectionState(2, bluetoothDevice);
        boolean zIsConnected = BluetoothDeviceImpl.isConnected(bluetoothDevice);
        ZLogger.v(String.format(Locale.US, "%s, connected=%b, hfpState= %d,a2dpState= %d", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), Boolean.valueOf(zIsConnected), Integer.valueOf(connectionState), Integer.valueOf(connectionState2)));
        return zIsConnected || 2 == connectionState || 2 == connectionState2;
    }

    public boolean isProfileSupported(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i == 11 && this.j != null : this.i != null : this.g != null : this.f != null;
    }

    public void registerProfiles() {
        if (this.e == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return;
        }
        this.k = new ProfileBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        getProfileProxy(2);
        getProfileProxy(1);
        getProfileProxy(4);
        getProfileProxy(11);
        this.c.registerReceiver(this.k, intentFilter);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.realsil.sdk.core.bluetooth.BluetoothProfileCallback>, java.util.concurrent.CopyOnWriteArrayList] */
    public void removeManagerCallback(BluetoothProfileCallback bluetoothProfileCallback) {
        ?? r0 = this.d;
        if (r0 != 0) {
            r0.remove(bluetoothProfileCallback);
        }
    }

    public boolean startScoUsingVirtualVoiceCall(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.f;
        if (bluetoothHeadset != null) {
            return Build.VERSION.SDK_INT >= 28 ? BluetoothHeadsetImpl.startScoUsingVirtualVoiceCall(bluetoothHeadset) : BluetoothHeadsetImpl.startScoUsingVirtualVoiceCall(bluetoothHeadset, bluetoothDevice);
        }
        ZLogger.w("BluetoothHeadset service is not connected");
        getProfileProxy(1);
        return false;
    }

    public boolean stopScoUsingVirtualVoiceCall(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.f;
        if (bluetoothHeadset != null) {
            return Build.VERSION.SDK_INT >= 28 ? BluetoothHeadsetImpl.stopScoUsingVirtualVoiceCall(bluetoothHeadset) : BluetoothHeadsetImpl.stopScoUsingVirtualVoiceCall(bluetoothHeadset, bluetoothDevice);
        }
        ZLogger.w("BluetoothHeadset service is not connected");
        getProfileProxy(1);
        return false;
    }

    public boolean connectA2dpSink(String str) {
        return connectA2dpSink(a(str));
    }

    public boolean connectA2dpSource(String str) {
        return connectA2dpSource(a(str));
    }

    public boolean connectHfpAg(String str) {
        BluetoothDevice remoteDevice = this.e.getRemoteDevice(str);
        if (remoteDevice == null) {
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.f;
        if (bluetoothHeadset == null) {
            ZLogger.w("BluetoothHeadset service is not connected");
            getProfileProxy(1);
            return false;
        }
        if (bluetoothHeadset.getConnectionState(remoteDevice) != 2) {
            return BluetoothProfileImpl.connectProfile(this.f, remoteDevice);
        }
        ZLogger.w("BluetoothHeadset profile is already connected");
        return true;
    }

    public boolean disconnectA2dpSource(String str) {
        return disconnectA2dpSource(a(str));
    }

    public boolean disconnectHfp(String str) {
        BluetoothDevice remoteDevice = this.e.getRemoteDevice(str);
        if (remoteDevice == null) {
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.f;
        if (bluetoothHeadset == null) {
            ZLogger.w("BluetoothHeadset service is not connected");
            getProfileProxy(1);
            return false;
        }
        if (bluetoothHeadset.getConnectionState(remoteDevice) == 2) {
            return BluetoothProfileImpl.disconnect(this.f, remoteDevice);
        }
        ZLogger.w("BluetoothHeadset profile is not connected");
        return false;
    }

    public boolean connectA2dpSink(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothProfile bluetoothProfile = this.j;
        if (bluetoothProfile == null) {
            ZLogger.w("A2DP Sink not initialized");
            getProfileProxy(11);
            return false;
        }
        if (bluetoothProfile.getConnectionState(bluetoothDevice) == 2) {
            ZLogger.w("A2DP Sink already connected");
            return true;
        }
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            return BluetoothProfileImpl.connectProfile(this.j, BluetoothA2dpSinkImpl.CLASS_NAME, bluetoothDevice);
        }
        ZLogger.d("BT not enabled");
        return false;
    }

    public boolean connectA2dpSource(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothA2dp bluetoothA2dp = this.g;
        if (bluetoothA2dp == null) {
            ZLogger.w("A2DP not initialized");
            getProfileProxy(2);
            return false;
        }
        if (bluetoothA2dp.getConnectionState(bluetoothDevice) == 2) {
            ZLogger.w("a2dp already connected");
            return true;
        }
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            if (!BluetoothProfileImpl.setPriority(this.g, bluetoothDevice, 100)) {
                ZLogger.v(this.a, "setPriority failed");
            }
            return BluetoothProfileImpl.connectProfile(this.g, bluetoothDevice);
        }
        ZLogger.w("BT not enabled");
        return false;
    }

    public boolean disconnectA2dpSource(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("device is null");
            return false;
        }
        BluetoothA2dp bluetoothA2dp = this.g;
        if (bluetoothA2dp == null) {
            ZLogger.w("A2DP not initialized");
            getProfileProxy(2);
            return false;
        }
        if (bluetoothA2dp.getConnectionState(bluetoothDevice) != 2) {
            ZLogger.w("A2DP already disconnected");
            return false;
        }
        if (!BluetoothProfileImpl.setPriority(this.g, bluetoothDevice, 100)) {
            ZLogger.v(this.a, "setPriority failed");
        }
        return BluetoothProfileImpl.disconnect(this.g, bluetoothDevice);
    }

    public final BluetoothDevice a(String str) {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            return this.e.getRemoteDevice(str);
        }
        ZLogger.w("BT not enabled");
        return null;
    }
}
