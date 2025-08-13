package com.realsil.customer.core.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.realsil.customer.core.RtkCore;
import com.realsil.customer.core.bluetooth.impl.BluetoothA2dpSinkImpl;
import com.realsil.customer.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.customer.core.bluetooth.impl.BluetoothHeadsetImpl;
import com.realsil.customer.core.bluetooth.impl.BluetoothInputDeviceImpl;
import com.realsil.customer.core.bluetooth.impl.BluetoothProfileImpl;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/BluetoothProfileManager.class */
public final class BluetoothProfileManager {
    public static BluetoothProfileManager m;
    public static final String CLASS_NAME_BLUETOOTH_HID_HOST = "android.bluetooth.BluetoothHidHost";
    public static final int INDICATOR_FULL = 255;
    public static final int INDICATOR_A2DP = 1;
    public static final int INDICATOR_A2DP_SINK = 2;
    public static final int INDICATOR_HEADSET = 4;
    public static final int INDICATOR_HID = 8;
    public final boolean a;
    public final boolean b;
    public final Context c;
    public CopyOnWriteArrayList d;
    public BluetoothAdapter e;
    public BluetoothHealth h;
    public BluetoothHeadset f = null;
    public BluetoothA2dp g = null;
    public BluetoothProfile i = null;
    public BluetoothProfile j = null;
    public ProfileBroadcastReceiver k = null;
    public final a l = new a();

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/BluetoothProfileManager$ProfileBroadcastReceiver.class */
    public class ProfileBroadcastReceiver extends BroadcastReceiver {
        public ProfileBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        @TargetApi(19)
        public final void onReceive(Context context, Intent intent) {
            String action;
            action = intent.getAction();
            action.getClass();
            switch (action) {
                case "android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED":
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice != null) {
                        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        ZLogger.d(BluetoothProfileManager.this.a, String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true), action, Integer.valueOf(intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1)), Integer.valueOf(intExtra)));
                        CopyOnWriteArrayList copyOnWriteArrayList = BluetoothProfileManager.this.d;
                        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
                            Iterator it = BluetoothProfileManager.this.d.iterator();
                            while (it.hasNext()) {
                                ((BluetoothProfileCallback) it.next()).onHfpAudioStateChanged(bluetoothDevice, intExtra);
                            }
                            break;
                        } else {
                            ZLogger.v(BluetoothProfileManager.this.b, "no callback registered");
                            break;
                        }
                    }
                    break;
                case "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED":
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice2 != null) {
                        ZLogger.d(BluetoothProfileManager.this.a, String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice2.getAddress(), true), action, Integer.valueOf(intExtra3), Integer.valueOf(intExtra2)));
                        CopyOnWriteArrayList copyOnWriteArrayList2 = BluetoothProfileManager.this.d;
                        if (copyOnWriteArrayList2 != null && copyOnWriteArrayList2.size() > 0) {
                            Iterator it2 = BluetoothProfileManager.this.d.iterator();
                            while (it2.hasNext()) {
                                ((BluetoothProfileCallback) it2.next()).onHidStateChanged(bluetoothDevice2, intExtra2);
                            }
                            break;
                        } else {
                            ZLogger.v(BluetoothProfileManager.this.b, "no callback registered");
                            break;
                        }
                    }
                    break;
                case "android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED":
                    BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra4 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra5 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice3 != null) {
                        ZLogger.d(BluetoothProfileManager.this.a, String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice3.getAddress(), true), action, Integer.valueOf(intExtra5), Integer.valueOf(intExtra4)));
                        CopyOnWriteArrayList copyOnWriteArrayList3 = BluetoothProfileManager.this.d;
                        if (copyOnWriteArrayList3 != null && copyOnWriteArrayList3.size() > 0) {
                            Iterator it3 = BluetoothProfileManager.this.d.iterator();
                            while (it3.hasNext()) {
                                ((BluetoothProfileCallback) it3.next()).onA2dpPlayingStateChanged(bluetoothDevice3, intExtra4);
                            }
                            break;
                        } else {
                            ZLogger.v(BluetoothProfileManager.this.b, "no callback registered");
                            break;
                        }
                    }
                    break;
                case "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED":
                    BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra6 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra7 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice4 != null) {
                        ZLogger.d(BluetoothProfileManager.this.a, String.format(Locale.US, "action=%s, device:%s, state: %d->%d", action, BluetoothHelper.formatAddress(bluetoothDevice4.getAddress(), true), Integer.valueOf(intExtra7), Integer.valueOf(intExtra6)));
                        CopyOnWriteArrayList copyOnWriteArrayList4 = BluetoothProfileManager.this.d;
                        if (copyOnWriteArrayList4 != null && copyOnWriteArrayList4.size() > 0) {
                            Iterator it4 = BluetoothProfileManager.this.d.iterator();
                            while (it4.hasNext()) {
                                ((BluetoothProfileCallback) it4.next()).onHfpConnectionStateChanged(bluetoothDevice4, intExtra6);
                            }
                            break;
                        } else {
                            ZLogger.v(BluetoothProfileManager.this.b, "no callback registered");
                            break;
                        }
                    }
                    break;
                case "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED":
                    BluetoothDevice bluetoothDevice5 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra8 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    int intExtra9 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    if (bluetoothDevice5 != null) {
                        ZLogger.d(BluetoothProfileManager.this.a, String.format(Locale.US, "%s: action=%s, state: %d->%d", BluetoothHelper.formatAddress(bluetoothDevice5.getAddress(), true), action, Integer.valueOf(intExtra9), Integer.valueOf(intExtra8)));
                        CopyOnWriteArrayList copyOnWriteArrayList5 = BluetoothProfileManager.this.d;
                        if (copyOnWriteArrayList5 != null && copyOnWriteArrayList5.size() > 0) {
                            Iterator it5 = BluetoothProfileManager.this.d.iterator();
                            while (it5.hasNext()) {
                                ((BluetoothProfileCallback) it5.next()).onA2dpStateChanged(bluetoothDevice5, intExtra8);
                            }
                            break;
                        } else {
                            ZLogger.v(BluetoothProfileManager.this.b, "no callback registered");
                            break;
                        }
                    }
                    break;
                case "android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT":
                    BluetoothProfileManager bluetoothProfileManager = BluetoothProfileManager.this;
                    bluetoothProfileManager.getClass();
                    BluetoothDevice bluetoothDevice6 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice6 != null) {
                        String stringExtra = intent.getStringExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD");
                        if (stringExtra != null) {
                            int intExtra10 = intent.getIntExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE", -1);
                            if (intExtra10 != 0 || intExtra10 != 1 || intExtra10 != 2 || intExtra10 != 3 || intExtra10 != 4) {
                                ZLogger.v("onVendorSpecificHeadsetEvent() unknown command");
                                break;
                            } else {
                                Object[] objArr = (Object[]) intent.getExtras().get("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS");
                                CopyOnWriteArrayList copyOnWriteArrayList6 = bluetoothProfileManager.d;
                                if (copyOnWriteArrayList6 != null && copyOnWriteArrayList6.size() > 0) {
                                    Iterator it6 = bluetoothProfileManager.d.iterator();
                                    while (it6.hasNext()) {
                                        ((BluetoothProfileCallback) it6.next()).onVendorSpecificHeadsetEvent(bluetoothDevice6, stringExtra, intExtra10, objArr);
                                    }
                                    break;
                                } else {
                                    ZLogger.v(bluetoothProfileManager.b, "no callback registered");
                                    break;
                                }
                            }
                        } else {
                            ZLogger.v("onVendorSpecificHeadsetEvent() command is null");
                            break;
                        }
                    } else {
                        ZLogger.v("onVendorSpecificHeadsetEvent() remote device is null");
                        break;
                    }
                    break;
            }
        }
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/BluetoothProfileManager$a.class */
    public class a implements BluetoothProfile.ServiceListener {
        public a() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public final void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            ZLogger.d(String.format("%s(%s) profile connected", BluetoothHelper.parseProfile(i), bluetoothProfile.getClass().getName()));
            if (i == 11) {
                BluetoothProfileManager.this.j = bluetoothProfile;
            }
            switch (i) {
                case 1:
                    BluetoothProfileManager.this.f = (BluetoothHeadset) bluetoothProfile;
                    break;
                case 2:
                    BluetoothProfileManager.this.g = (BluetoothA2dp) bluetoothProfile;
                    break;
                case 3:
                    BluetoothProfileManager.this.h = (BluetoothHealth) bluetoothProfile;
                    break;
                case 4:
                    BluetoothProfileManager.this.i = bluetoothProfile;
                    break;
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Class<com.realsil.customer.core.bluetooth.BluetoothProfileManager>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static void initial(Context context) {
        if (m == null) {
            ?? r0 = BluetoothProfileManager.class;
            synchronized (r0) {
                if (m == null) {
                    m = new BluetoothProfileManager(context);
                }
                r0 = r0;
            }
        }
    }

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
        ZLogger.v(this.b, "mManagerCallbacks.size=" + this.d.size());
    }

    public void removeManagerCallback(BluetoothProfileCallback bluetoothProfileCallback) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.d;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(bluetoothProfileCallback);
        }
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

    public void close() {
        if (this.b) {
            ZLogger.v("close()");
        }
        Context context = this.c;
        if (context != null) {
            try {
                context = context;
                context.unregisterReceiver(this.k);
            } catch (Exception unused) {
                ZLogger.e(context.toString());
            }
        }
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

    public boolean isProfileSupported(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i == 11 && this.j != null : this.i != null : this.g != null : this.f != null;
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
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.isConnectMethodSupported(this.i, CLASS_NAME_BLUETOOTH_HID_HOST) && BluetoothProfileImpl.isDisConnectMethodSupported(this.i, CLASS_NAME_BLUETOOTH_HID_HOST) : BluetoothProfileImpl.isConnectMethodSupported(this.i, BluetoothInputDeviceImpl.CLASS_NAME) && BluetoothProfileImpl.isDisConnectMethodSupported(this.i, BluetoothInputDeviceImpl.CLASS_NAME);
    }

    public boolean connectProfile(int i, BluetoothDevice bluetoothDevice) {
        if (i != 4) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.connectProfile(this.i, CLASS_NAME_BLUETOOTH_HID_HOST, bluetoothDevice) : BluetoothProfileImpl.connectProfile(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean disconnectProfile(int i, BluetoothDevice bluetoothDevice) {
        if (i != 4) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.disconnect(this.i, CLASS_NAME_BLUETOOTH_HID_HOST, bluetoothDevice) : BluetoothProfileImpl.disconnect(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean connectHid(BluetoothDevice bluetoothDevice) {
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.connectProfile(this.i, CLASS_NAME_BLUETOOTH_HID_HOST, bluetoothDevice) : BluetoothProfileImpl.connectProfile(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean disConnectHid(BluetoothDevice bluetoothDevice) {
        return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.disconnect(this.i, CLASS_NAME_BLUETOOTH_HID_HOST, bluetoothDevice) : BluetoothProfileImpl.disconnect(this.i, BluetoothInputDeviceImpl.CLASS_NAME, bluetoothDevice);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getConnectionState(int r5, android.bluetooth.BluetoothDevice r6) {
        /*
            r4 = this;
            r0 = r4
            android.bluetooth.BluetoothAdapter r0 = r0.e
            r1 = r0
            r7 = r1
            if (r0 == 0) goto Lad
            r0 = r7
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto L13
            goto Lad
        L13:
            r0 = r5
            r1 = 1
            if (r0 == r1) goto L2b
            r0 = r5
            r1 = 2
            if (r0 == r1) goto L45
            r0 = r5
            r1 = 4
            if (r0 == r1) goto L5f
            r0 = r5
            r1 = 11
            if (r0 == r1) goto L8c
            goto Lab
        L2b:
            r0 = r4
            android.bluetooth.BluetoothHeadset r0 = r0.f
            r1 = r0
            r5 = r1
            if (r0 == 0) goto L3a
            r0 = r5
            r1 = r6
            int r0 = r0.getConnectionState(r1)
            return r0
        L3a:
            r0 = r4
            java.lang.String r1 = "HEADSET profile not connected"
            com.realsil.customer.core.logger.ZLogger.d(r1)
            r1 = 1
            boolean r0 = r0.getProfileProxy(r1)
        L45:
            r0 = r4
            android.bluetooth.BluetoothA2dp r0 = r0.g
            r1 = r0
            r5 = r1
            if (r0 == 0) goto L54
            r0 = r5
            r1 = r6
            int r0 = r0.getConnectionState(r1)
            return r0
        L54:
            r0 = r4
            java.lang.String r1 = "A2DP profile not connected"
            com.realsil.customer.core.logger.ZLogger.d(r1)
            r1 = 2
            boolean r0 = r0.getProfileProxy(r1)
        L5f:
            r0 = r4
            android.bluetooth.BluetoothProfile r0 = r0.i
            r1 = r0
            r5 = r1
            if (r0 == 0) goto L80
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L78
            r0 = r5
            java.lang.String r1 = "android.bluetooth.BluetoothHidHost"
            r2 = r6
            int r0 = com.realsil.customer.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r0, r1, r2)
            return r0
        L78:
            r0 = r5
            java.lang.String r1 = "android.bluetooth.BluetoothInputDevice"
            r2 = r6
            int r0 = com.realsil.customer.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r0, r1, r2)
            return r0
        L80:
            r0 = r4
            java.lang.String r1 = "HID_HOST profile not connected"
            com.realsil.customer.core.logger.ZLogger.d(r1)
            r1 = 4
            boolean r0 = r0.getProfileProxy(r1)
        L8c:
            r0 = r4
            android.bluetooth.BluetoothProfile r0 = r0.j
            r1 = r0
            r5 = r1
            if (r0 == 0) goto L9e
            r0 = r5
            java.lang.String r1 = "android.bluetooth.BluetoothA2dpSink"
            r2 = r6
            int r0 = com.realsil.customer.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r0, r1, r2)
            return r0
        L9e:
            r0 = r4
            java.lang.String r1 = "A2DP_SINK profile not connected"
            com.realsil.customer.core.logger.ZLogger.d(r1)
            r1 = 11
            boolean r0 = r0.getProfileProxy(r1)
        Lab:
            r0 = 0
            return r0
        Lad:
            java.lang.String r0 = "BT not enabled"
            com.realsil.customer.core.logger.ZLogger.d(r0)
            r0 = -1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.customer.core.bluetooth.BluetoothProfileManager.getConnectionState(int, android.bluetooth.BluetoothDevice):int");
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

    public List<BluetoothDevice> getConnectedDevices(int i) {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            return new ArrayList();
        }
        if (i == 1) {
            BluetoothHeadset bluetoothHeadset = this.f;
            if (bluetoothHeadset != null) {
                return bluetoothHeadset.getConnectedDevices();
            }
            ZLogger.d("HEADSET profile not connected");
            getProfileProxy(1);
        } else if (i == 2) {
            BluetoothA2dp bluetoothA2dp = this.g;
            if (bluetoothA2dp != null) {
                return bluetoothA2dp.getConnectedDevices();
            }
            ZLogger.d("A2DP profile not connected");
            getProfileProxy(2);
        } else if (i == 4) {
            BluetoothProfile bluetoothProfile = this.i;
            if (bluetoothProfile != null) {
                return Build.VERSION.SDK_INT >= 28 ? BluetoothProfileImpl.getConnectedDevices(bluetoothProfile, CLASS_NAME_BLUETOOTH_HID_HOST) : BluetoothProfileImpl.getConnectedDevices(bluetoothProfile, BluetoothInputDeviceImpl.CLASS_NAME);
            }
            ZLogger.d("HID_HOST profile not connected");
            getProfileProxy(4);
        } else if (i == 11) {
            BluetoothProfile bluetoothProfile2 = this.j;
            if (bluetoothProfile2 != null) {
                return BluetoothProfileImpl.getConnectedDevices(bluetoothProfile2, BluetoothA2dpSinkImpl.CLASS_NAME);
            }
            ZLogger.d("A2DP_SINK profile not connected");
            getProfileProxy(11);
        }
        return new ArrayList();
    }

    public boolean getProfileProxy(int i) {
        try {
            if (!this.e.getProfileProxy(this.c, this.l, i)) {
                Locale locale = Locale.US;
                Object[] objArr = new Object[1];
                objArr[0] = Integer.valueOf(i);
                ZLogger.w(String.format(locale, "getProfileProxy %d failed", objArr));
                return false;
            }
            if (!this.b) {
                return true;
            }
            Locale locale2 = Locale.US;
            Object[] objArr2 = new Object[1];
            objArr2[0] = Integer.valueOf(i);
            ZLogger.v(String.format(locale2, "getProfileProxy %d success", objArr2));
            return true;
        } catch (Exception e) {
            ZLogger.w(String.format(Locale.US, "getProfileProxy %d exception: %s", Integer.valueOf(i), e.toString()));
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v4, types: [boolean] */
    public void closeProfileProxy(int i) {
        Object objIsEnabled;
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || (objIsEnabled = bluetoothAdapter.isEnabled()) == 0) {
            ZLogger.w("BT not enabled");
            return;
        }
        try {
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(i);
            ZLogger.v(String.format(locale, "profile=%d", objArr));
            if (i != 11) {
                int i2 = i;
                objIsEnabled = i2;
                switch (i2) {
                    case 1:
                        BluetoothAdapter bluetoothAdapter2 = this.e;
                        bluetoothAdapter2.closeProfileProxy(i, this.f);
                        objIsEnabled = bluetoothAdapter2;
                        break;
                    case 2:
                        BluetoothAdapter bluetoothAdapter3 = this.e;
                        bluetoothAdapter3.closeProfileProxy(i, this.g);
                        objIsEnabled = bluetoothAdapter3;
                        break;
                    case 3:
                        BluetoothAdapter bluetoothAdapter4 = this.e;
                        bluetoothAdapter4.closeProfileProxy(i, this.h);
                        objIsEnabled = bluetoothAdapter4;
                        break;
                    case 4:
                        BluetoothAdapter bluetoothAdapter5 = this.e;
                        bluetoothAdapter5.closeProfileProxy(i, this.i);
                        objIsEnabled = bluetoothAdapter5;
                        break;
                }
            } else {
                BluetoothAdapter bluetoothAdapter6 = this.e;
                bluetoothAdapter6.closeProfileProxy(i, this.j);
                objIsEnabled = bluetoothAdapter6;
            }
        } catch (Exception unused) {
            ZLogger.e(objIsEnabled.toString());
        }
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

    public boolean connectA2dpSource(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean disconnectA2dpSource(String str) {
        BluetoothDevice remoteDevice;
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            remoteDevice = null;
        } else {
            remoteDevice = this.e.getRemoteDevice(str);
        }
        return disconnectA2dpSource(remoteDevice);
    }

    public boolean connectA2dpSink(String str) {
        BluetoothDevice remoteDevice;
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            remoteDevice = null;
        } else {
            remoteDevice = this.e.getRemoteDevice(str);
        }
        return connectA2dpSink(remoteDevice);
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

    public boolean connectHfpAg(byte[] bArr) {
        return connectHfpAg(BluetoothHelper.convertMac(bArr));
    }

    public boolean disconnectHfp(byte[] bArr) {
        return disconnectHfp(BluetoothHelper.convertMac(bArr));
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

    public final void a() {
        Context context = this.c;
        if (context == null) {
            ZLogger.w("not intialized");
            return;
        }
        if (this.e == null) {
            BluetoothAdapter bluetoothAdapterA = com.realsil.customer.core.a.a.a(context);
            this.e = bluetoothAdapterA;
            if (bluetoothAdapterA == null) {
                ZLogger.w("Unable to obtain a BluetoothAdapter.");
                return;
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
        intentFilter.addAction(BluetoothInputDeviceImpl.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_PLAYING_STATE_CHANGED);
        this.c.registerReceiver(this.k, intentFilter);
    }

    public boolean connectA2dpSource(String str) {
        BluetoothDevice remoteDevice;
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            remoteDevice = null;
        } else {
            remoteDevice = this.e.getRemoteDevice(str);
        }
        return connectA2dpSource(remoteDevice);
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

    public boolean disconnectA2dpSource(byte[] bArr) {
        return disconnectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectA2dpSink(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
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
}
