package com.realsil.customer.bbpro.core.gatt;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClient;
import com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientCallback;
import com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl;
import com.realsil.customer.core.bluetooth.utils.BluetoothUuid;
import com.realsil.customer.core.logger.ZLogger;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/gatt/GattLayer.class */
public class GattLayer extends BluetoothGattClientImpl {
    public static final UUID c = UUID.fromString("000002fd-3C17-D293-8E48-14FE2E4DA212");
    public static final UUID d = BluetoothUuid.fromShortValue(64771);
    public static final UUID e = BluetoothUuid.fromShortValue(64772);
    public BluetoothGattCharacteristic a;
    public BluetoothGattCharacteristic b;

    public GattLayer(BluetoothGattClientCallback bluetoothGattClientCallback) {
        super(null, bluetoothGattClientCallback);
        if (this.VDBG) {
            ZLogger.v("GattLayer initial");
        }
    }

    public boolean sendData(byte[] bArr) {
        if (this.a == null) {
            if (!this.DBG) {
                return false;
            }
            ZLogger.w("mTXCharacteristic == null.");
            return false;
        }
        if (this.mGlobalGatt.isConnected(this.mDeviceAddress)) {
            this.mGlobalGatt.writeCharacteristic(this.mDeviceAddress, this.a, bArr);
            return true;
        }
        if (!this.DBG) {
            return false;
        }
        ZLogger.w("disconnect.");
        return false;
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl
    public boolean processServices(BluetoothGatt bluetoothGatt) {
        UUID uuid = c;
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        if (service == null) {
            ZLogger.w("not find BBPRO_SERVICE_UUID:" + uuid);
            return false;
        }
        UUID uuid2 = d;
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
        this.a = characteristic;
        if (characteristic == null) {
            ZLogger.w("not find BBPRO_TX_CHARACTERISTIC_UUID:" + uuid2);
            return false;
        }
        UUID uuid3 = e;
        BluetoothGattCharacteristic characteristic2 = service.getCharacteristic(uuid3);
        this.b = characteristic2;
        if (characteristic2 != null) {
            return true;
        }
        ZLogger.w("not find BBPRO_RX_CHARACTERISTIC_UUID:" + uuid3);
        return false;
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl
    public boolean enableCccd() {
        super.enableCccd();
        return setCharacteristicNotification(this.mBluetoothGatt, this.b, true);
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl
    public void processCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        super.processCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic, bArr);
        if (bluetoothGattCharacteristic.getUuid().equals(e)) {
            dispatchDataReceived(bArr);
        }
    }

    @Override // com.realsil.customer.core.bluetooth.connection.le.BluetoothGattClientImpl
    public void processDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        super.processDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        if (i != 0) {
            if (this.DBG) {
                ZLogger.w("Descriptor write error: " + i);
            }
            notifyCccdEnabled(false);
        } else if (BluetoothGattClient.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID.equals(bluetoothGattDescriptor.getUuid()) && bluetoothGattDescriptor.getCharacteristic().getUuid().equals(this.b.getUuid())) {
            boolean z = bluetoothGattDescriptor.getValue()[0] == 1;
            if (z) {
                if (this.DBG) {
                    ZLogger.d(" Notification enabled");
                }
            } else if (this.DBG) {
                ZLogger.w("Notification  not enabled!!!");
            }
            notifyCccdEnabled(z);
        }
    }
}
