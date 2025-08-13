package com.oudmon.ble.base.communication;

import android.util.Log;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.DataTransferUtils;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/DfuHandle.class */
public class DfuHandle {
    public static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    public static final UUID SERIAL_PORT_CHAREACTER_NOTIRY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    public static final UUID SERIAL_PORT_CHAREACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private static final String TAG = "DfuHandle";
    public static final int RSP_OK = 0;
    public static final int RSP_DATA_SIZE = 1;
    public static final int RSP_DATA_CONTENT = 2;
    public static final int RSP_CMD_STATUS = 3;
    public static final int RSP_CMD_FORMAT = 4;
    public static final int RSP_INNER = 5;
    public static final int RSP_LOW_BATTERY = 6;
    private byte[] dfuData;
    private IOpResult iOpResult;
    private static DfuHandle odmHandle;
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHAREACTER_NOTIRY);
    private OnGattEventCallback localEventCallback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.DfuHandle.1
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String uuid, byte[] data) {
            if (data != null && DfuHandle.this.checkTheData(data)) {
                if (data[6] == 0 && data[1] == 3) {
                    if (!DfuHandle.this.readNextBigPocket()) {
                        DfuHandle.this.iOpResult.onProgress(100);
                    } else {
                        DfuHandle.this.iOpResult.onProgress(((DfuHandle.this.bigPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2) * 100) / DfuHandle.this.dfuData.length);
                        return;
                    }
                }
                if (DfuHandle.this.iOpResult != null) {
                    DfuHandle.this.iOpResult.onActionResult(data[1], data[6]);
                }
            }
        }
    };
    private short dfuFileChecksum = 0;
    private short dfuFileCrc16 = 0;
    private short bigPocketIndex = 0;
    private int mPackageLength = JPackageManager.getInstance().getLength();

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/DfuHandle$IOpResult.class */
    public interface IOpResult {
        void onActionResult(int i, int i2);

        void onProgress(int i);
    }

    public static DfuHandle getInstance() {
        if (odmHandle == null) {
            synchronized (DfuHandle.class) {
                if (odmHandle == null) {
                    odmHandle = new DfuHandle();
                }
            }
        }
        return odmHandle;
    }

    public DfuHandle() {
        Log.i(TAG, this.mPackageLength + "");
    }

    public void initCallback() {
        BleOperateManager.getInstance().setCallback(this.localEventCallback);
    }

    private void openNotify() {
        this.enableNotifyRequest.setEnable(true);
        BleOperateManager.getInstance().execute(this.enableNotifyRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkTheData(byte[] data) {
        Log.i(TAG, "checkTheData: data=" + DataTransferUtils.getHexString(data));
        if (data.length >= 6) {
            if ((data[0] & 255) == 188 && DataTransferUtils.bytesToShort(data, 2) == data.length - 6) {
                int crc = CRC16.calcCrc16(Arrays.copyOfRange(data, 6, data.length));
                if ((DataTransferUtils.bytesToShort(data, 4) & 65535) == crc) {
                    return true;
                }
                Log.e(TAG, "checkTheData: CRC 校验失败");
                return false;
            }
            Log.e(TAG, "checkTheData: 数据长度不一致");
            return false;
        }
        Log.e(TAG, "checkTheData: 协议长度有问题");
        return false;
    }

    public boolean checkFile(String filePath) throws IOException {
        Log.i("OTA升级调试--", "选择升级文件：" + filePath);
        File dfuNrfFile = new File(filePath);
        if (!dfuNrfFile.exists()) {
            Log.i("OTA升级调试--", "文件不存在！");
            return false;
        }
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                this.dfuFileChecksum = (short) 0;
                this.dfuFileCrc16 = (short) 0;
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(filePath, "r");
                if (randomAccessFile2.length() > 12288000) {
                    Log.i("OTA升级调试--", "文件大小溢出！");
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
                this.dfuData = new byte[(int) randomAccessFile2.length()];
                int size = randomAccessFile2.read(this.dfuData, 0, this.dfuData.length);
                Log.i("OTA升级调试--", "start cal check sum.. dataSize=" + this.dfuData.length + "  readSize=" + size);
                this.dfuFileChecksum = (short) 0;
                for (int i = 0; i < this.dfuData.length; i++) {
                    this.dfuFileChecksum = (short) (this.dfuFileChecksum + (this.dfuData[i] & 255));
                }
                this.dfuFileChecksum = (short) (this.dfuFileChecksum & (-1));
                this.dfuFileCrc16 = (short) CRC16.calcCrc16(this.dfuData);
                Log.i("OTA升级调试--", "dfuFileChecksum: " + ((int) this.dfuFileChecksum) + ", dfuFileCrc16: " + ((int) this.dfuFileCrc16));
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e4) {
            e4.printStackTrace();
            if (0 == 0) {
                return false;
            }
            try {
                randomAccessFile.close();
                return false;
            } catch (IOException e5) {
                e5.printStackTrace();
                return false;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
            if (0 == 0) {
                return false;
            }
            try {
                randomAccessFile.close();
                return false;
            } catch (IOException e7) {
                e7.printStackTrace();
                return false;
            }
        }
    }

    public void start(IOpResult iOpResult) {
        openNotify();
        this.iOpResult = iOpResult;
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(1, null)));
    }

    public void init() {
        byte[] data = new byte[9];
        data[0] = 1;
        System.arraycopy(DataTransferUtils.intToBytes(this.dfuData.length), 0, data, 1, 4);
        System.arraycopy(DataTransferUtils.shortToBytes(this.dfuFileCrc16), 0, data, 5, 2);
        System.arraycopy(DataTransferUtils.shortToBytes(this.dfuFileChecksum), 0, data, 7, 2);
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(2, data)));
    }

    public void sendPacket() {
        this.bigPocketIndex = (short) 0;
        readNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readNextBigPocket() {
        if (this.bigPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2 < this.dfuData.length) {
            byte[] bufferNext = new byte[Math.min(EqConstants.CodeIndex.REALTIME_EQ_2, this.dfuData.length - (this.bigPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2)) + 2];
            System.arraycopy(DataTransferUtils.shortToBytes((short) (this.bigPocketIndex + 1)), 0, bufferNext, 0, 2);
            System.arraycopy(this.dfuData, this.bigPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2, bufferNext, 2, bufferNext.length - 2);
            sendPocketToBle(addHeader(3, bufferNext));
            this.bigPocketIndex = (short) (this.bigPocketIndex + 1);
            return true;
        }
        Log.i(TAG, "升级包发送完毕");
        return false;
    }

    private void sendPocketToBle(byte[] bigPocket) {
        Log.i(TAG, "sendPocketToBle: bigPocket=" + DataTransferUtils.getHexString(bigPocket));
        for (int index = 0; index * this.mPackageLength < bigPocket.length; index++) {
            BleOperateManager.getInstance().execute(getWriteRequest(Arrays.copyOfRange(bigPocket, index * this.mPackageLength, (index * this.mPackageLength) + Math.min(this.mPackageLength, bigPocket.length - (index * this.mPackageLength)))));
        }
    }

    public void check() {
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(4, null)));
    }

    public void endAndRelease() {
        this.iOpResult = null;
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(5, null)));
        this.enableNotifyRequest.setEnable(false);
        BleOperateManager.getInstance().execute(this.enableNotifyRequest);
        BleOperateManager.getInstance().setCallback(null);
    }

    private byte[] addHeader(int cmdid, byte[] data) {
        byte[] pocket = new byte[(data == null ? 0 : data.length) + 6];
        pocket[0] = -68;
        pocket[1] = (byte) cmdid;
        if (data != null && data.length > 0) {
            System.arraycopy(DataTransferUtils.shortToBytes((short) data.length), 0, pocket, 2, 2);
            System.arraycopy(DataTransferUtils.shortToBytes((short) CRC16.calcCrc16(data)), 0, pocket, 4, 2);
            System.arraycopy(data, 0, pocket, 6, data.length);
        } else {
            pocket[4] = -1;
            pocket[5] = -1;
        }
        return pocket;
    }

    private WriteRequest getWriteRequest(byte[] data) {
        Log.i(TAG, "getWriteRequest: data=" + DataTransferUtils.getHexString(data));
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(SERIAL_PORT_SERVICE, SERIAL_PORT_CHAREACTER_WRITE);
        noRspInstance.setValue(data);
        return noRspInstance;
    }
}
