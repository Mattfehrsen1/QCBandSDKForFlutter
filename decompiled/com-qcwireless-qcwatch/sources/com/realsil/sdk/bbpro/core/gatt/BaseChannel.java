package com.realsil.sdk.bbpro.core.gatt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: classes3.dex */
public abstract class BaseChannel {
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_DISCONNECTED = 1;
    public static final int STATE_DISCONNECTING = 4;
    public static final int STATE_INITIAL = 0;
    public Context a;
    public int b;
    public ChannelCallback c;

    public BaseChannel(Context context, ChannelCallback channelCallback) {
        this.a = context;
        this.c = channelCallback;
        a();
    }

    public final void a() {
        BluetoothAdapter.getDefaultAdapter();
        updateConnectionState(0);
    }

    public int getConnectState() {
        return this.b;
    }

    public BluetoothDevice getDevice() {
        return null;
    }

    public boolean isFlowCtrlEnabled() {
        return false;
    }

    public boolean isRxSupported() {
        return true;
    }

    public boolean isRxValidateEnabled() {
        return false;
    }

    public boolean isTxSupported() {
        return true;
    }

    public boolean isTxValidateEnabled() {
        return false;
    }

    public void updateConnectionState(int i) {
        ZLogger.v(String.format(Locale.US, "%d -> %d", Integer.valueOf(this.b), Integer.valueOf(i)));
        this.b = i;
        ChannelCallback channelCallback = this.c;
        if (channelCallback != null) {
            channelCallback.onConnectionStateChange(null, true, i);
        } else {
            ZLogger.w("no channel callback");
        }
    }
}
