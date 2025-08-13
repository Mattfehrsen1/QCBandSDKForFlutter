package com.realsil.sdk.bbpro.core.gatt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.c.b;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.UUID;

/* loaded from: classes3.dex */
public class GattLayer extends BaseChannel {
    public static final UUID j = UUID.fromString("000002fd-3C17-D293-8E48-14FE2E4DA212");
    public static final UUID k = new UUID(253, 3);
    public static final UUID l = new UUID(253, 4);
    public static final UUID m = UUID.fromString(b.CLIENT_CHARACTERISTIC_CONFIG);
    public BluetoothGatt d;
    public String e;
    public GlobalGatt f;
    public BluetoothGattCharacteristic g;
    public BluetoothGattCharacteristic h;
    public BluetoothGattCallback i;

    public GattLayer(ChannelCallback channelCallback) {
        super(null, channelCallback);
        this.e = null;
        this.i = new BluetoothGattCallback() { // from class: com.realsil.sdk.bbpro.core.gatt.GattLayer.1
            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (bluetoothGattCharacteristic.getUuid().equals(GattLayer.l)) {
                    ZLogger.d("[RX]<<(" + value.length + ") : " + DataConverter.bytes2HexWithSeparate(value));
                    ChannelCallback channelCallback2 = GattLayer.this.c;
                    if (channelCallback2 != null) {
                        channelCallback2.onDataReceive(value);
                    }
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
                BluetoothDevice device = bluetoothGatt.getDevice();
                if (i != 0) {
                    ZLogger.e("status " + i + " newState: " + i2);
                    GattLayer.this.close();
                    ChannelCallback channelCallback2 = GattLayer.this.c;
                    if (channelCallback2 != null) {
                        channelCallback2.onConnectionStateChange(device, false, i2);
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    GattLayer.this.d = bluetoothGatt;
                    ZLogger.d("Connected to GATT server.");
                    ZLogger.d("Attempting to start service discovery: " + GattLayer.this.d.discoverServices());
                    return;
                }
                if (i2 == 0) {
                    ZLogger.d("Disconnected from GATT server.");
                    GattLayer.this.close();
                    ChannelCallback channelCallback3 = GattLayer.this.c;
                    if (channelCallback3 != null) {
                        channelCallback3.onConnectionStateChange(device, true, i2);
                    }
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                bluetoothGatt.getDevice();
                if (i != 0) {
                    ZLogger.w("Descriptor write error: " + i);
                    GattLayer.this.disconnectGatt();
                    return;
                }
                if (GattLayer.m.equals(bluetoothGattDescriptor.getUuid()) && bluetoothGattDescriptor.getCharacteristic().getUuid().equals(GattLayer.this.h.getUuid())) {
                    if (bluetoothGattDescriptor.getValue()[0] == 1) {
                        ZLogger.d(" Notification enabled");
                    } else {
                        ZLogger.w("Notification  not enabled!!!");
                        GattLayer.this.disconnectGatt();
                    }
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
                if (i != 0) {
                    GattLayer.this.disconnectGatt();
                    ZLogger.w("onServicesDiscovered failed: " + i);
                    return;
                }
                ZLogger.d("onServicesDiscovered success.");
                BluetoothGattService service = bluetoothGatt.getService(GattLayer.j);
                if (service == null) {
                    GattLayer.this.disconnectGatt();
                    return;
                }
                GattLayer.this.g = service.getCharacteristic(GattLayer.k);
                GattLayer gattLayer = GattLayer.this;
                if (gattLayer.g == null) {
                    gattLayer.disconnectGatt();
                    return;
                }
                gattLayer.h = service.getCharacteristic(GattLayer.l);
                GattLayer gattLayer2 = GattLayer.this;
                BluetoothGattCharacteristic bluetoothGattCharacteristic = gattLayer2.h;
                if (bluetoothGattCharacteristic == null) {
                    gattLayer2.disconnectGatt();
                } else {
                    gattLayer2.f.setCharacteristicNotification(gattLayer2.e, bluetoothGattCharacteristic, true);
                }
            }
        };
        ZLogger.v("GattLayer initial");
        this.f = GlobalGatt.getInstance();
    }

    public void close() {
        ZLogger.d("close()");
        this.f.close(this.e);
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        String address = bluetoothDevice.getAddress();
        ZLogger.d("connect, address: " + BluetoothHelper.formatAddress(address, true));
        this.e = address;
        return this.f.connect(address, this.i);
    }

    public void disconnectGatt() {
        ZLogger.d("disconnect()");
        this.f.disconnectGatt(this.e);
    }

    public boolean sendData(byte[] bArr) {
        if (this.g == null) {
            ZLogger.w("mTXCharacteristic == null.");
            return false;
        }
        if (!this.f.isConnected(this.e)) {
            ZLogger.w("disconnect.");
            return false;
        }
        this.g.setValue(bArr);
        this.f.writeCharacteristic(this.e, this.g);
        return true;
    }
}
