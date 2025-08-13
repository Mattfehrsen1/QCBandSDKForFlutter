package com.realsil.customer.core.bluetooth.scanner;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Handler;
import com.realsil.customer.core.c.b;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/BrEdrScannerPresenter.class */
public final class BrEdrScannerPresenter extends b {
    public BrEdrScannerPresenter(Context context) {
        super(context);
    }

    @Override // com.realsil.customer.core.c.b, com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean startScan() {
        return super.startScan();
    }

    @Override // com.realsil.customer.core.c.b, com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ List getPairedDevicesByProfile(int i) {
        return super.getPairedDevicesByProfile(i);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ List getPairedDevices() {
        return super.getPairedDevices();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean isScanning() {
        return super.isScanning();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ int getState() {
        return super.getState();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean isBluetoothEnabled() {
        return super.isBluetoothEnabled();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean isBluetoothSupported() {
        return super.isBluetoothSupported();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ BluetoothAdapter getBluetoothAdapter() {
        return super.getBluetoothAdapter();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z) {
        return super.scanDevice(z);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean stopScan() {
        return super.stopScan();
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void setScannerCallback(ScannerCallback scannerCallback) {
        super.setScannerCallback(scannerCallback);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void setScanMode(int i) {
        super.setScanMode(i);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ void setScannerParams(ScannerParams scannerParams) {
        super.setScannerParams(scannerParams);
    }

    public BrEdrScannerPresenter(Context context, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        super(context, scannerParams, scannerCallback);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ List getPairedDevices(int i) {
        return super.getPairedDevices(i);
    }

    @Override // com.realsil.customer.core.c.a
    public /* bridge */ /* synthetic */ boolean scanDevice(boolean z, boolean z2) {
        return super.scanDevice(z, z2);
    }

    public BrEdrScannerPresenter(Context context, Handler handler, ScannerParams scannerParams, ScannerCallback scannerCallback) {
        super(context, handler, scannerParams, scannerCallback);
    }
}
