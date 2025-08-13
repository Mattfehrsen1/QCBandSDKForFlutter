package com.oudmon.ble.base.communication;

import android.util.Log;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.WriteRequest;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.UUID;

/* loaded from: classes3.dex */
public class DfuHandle {
    public static final int RSP_CMD_FORMAT = 4;
    public static final int RSP_CMD_STATUS = 3;
    public static final int RSP_DATA_CONTENT = 2;
    public static final int RSP_DATA_SIZE = 1;
    public static final int RSP_INNER = 5;
    public static final int RSP_LOW_BATTERY = 6;
    public static final int RSP_OK = 0;
    private static final String TAG = "DfuHandle";
    private static DfuHandle odmHandle;
    private byte[] dfuData;
    private IOpResult iOpResult;
    public static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    public static final UUID SERIAL_PORT_CHAREACTER_NOTIRY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    public static final UUID SERIAL_PORT_CHAREACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private OnGattEventCallback localEventCallback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.DfuHandle.1
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String str, byte[] bArr) {
            if (bArr != null && DfuHandle.this.checkTheData(bArr)) {
                if (bArr[6] == 0 && bArr[1] == 3) {
                    if (!DfuHandle.this.readNextBigPocket()) {
                        DfuHandle.this.iOpResult.onProgress(100);
                    } else {
                        DfuHandle.this.iOpResult.onProgress(((DfuHandle.this.bigPocketIndex * 1024) * 100) / DfuHandle.this.dfuData.length);
                        return;
                    }
                }
                if (DfuHandle.this.iOpResult != null) {
                    DfuHandle.this.iOpResult.onActionResult(bArr[1], bArr[6]);
                }
            }
        }
    };
    private short dfuFileChecksum = 0;
    private short dfuFileCrc16 = 0;
    private short bigPocketIndex = 0;
    private int mPackageLength = JPackageManager.getInstance().getLength();

    public interface IOpResult {
        void onActionResult(int i, int i2);

        void onProgress(int i);
    }

    private void openNotify() {
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

    public void initCallback() {
        this.mPackageLength = JPackageManager.getInstance().getLength();
        BleOperateManager.getInstance().setCallback(this.localEventCallback);
    }

    public void setBleOperateManagerCallback() {
        BleOperateManager.getInstance().setCallback(this.localEventCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkTheData(byte[] bArr) {
        Log.i(TAG, "checkTheData: data=" + DataTransferUtils.getHexString(bArr));
        if (bArr.length >= 6) {
            if ((bArr[0] & 255) == 188 && DataTransferUtils.bytesToShort(bArr, 2) == bArr.length - 6) {
                if ((DataTransferUtils.bytesToShort(bArr, 4) & 65535) == CRC16.calcCrc16(Arrays.copyOfRange(bArr, 6, bArr.length))) {
                    return true;
                }
                Log.e(TAG, "checkTheData: CRC 校验失败");
            } else {
                Log.e(TAG, "checkTheData: 数据长度不一致");
            }
        } else {
            Log.e(TAG, "checkTheData: 协议长度有问题");
        }
        return false;
    }

    public boolean checkFile(String str) throws Throwable {
        RandomAccessFile randomAccessFile;
        Log.i("OTA升级调试--", "选择升级文件：" + str);
        if (!new File(str).exists()) {
            Log.i("OTA升级调试--", "文件不存在！");
            return false;
        }
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                try {
                    this.dfuFileChecksum = (short) 0;
                    this.dfuFileCrc16 = (short) 0;
                    randomAccessFile = new RandomAccessFile(str, "r");
                } catch (Throwable th) {
                    th = th;
                }
            } catch (FileNotFoundException e) {
                e = e;
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            if (randomAccessFile.length() > 12288000) {
                Log.i("OTA升级调试--", "文件大小溢出！");
                try {
                    randomAccessFile.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return false;
            }
            byte[] bArr = new byte[(int) randomAccessFile.length()];
            this.dfuData = bArr;
            Log.i("OTA升级调试--", "start cal check sum.. dataSize=" + this.dfuData.length + "  readSize=" + randomAccessFile.read(bArr, 0, bArr.length));
            this.dfuFileChecksum = (short) 0;
            int i = 0;
            while (true) {
                byte[] bArr2 = this.dfuData;
                if (i >= bArr2.length) {
                    this.dfuFileChecksum = (short) (this.dfuFileChecksum & (-1));
                    this.dfuFileCrc16 = (short) CRC16.calcCrc16(bArr2);
                    Log.i("OTA升级调试--", "dfuFileChecksum: " + ((int) this.dfuFileChecksum) + ", dfuFileCrc16: " + ((int) this.dfuFileCrc16));
                    try {
                        randomAccessFile.close();
                        return true;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return true;
                    }
                }
                this.dfuFileChecksum = (short) (this.dfuFileChecksum + (bArr2[i] & 255));
                i++;
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            randomAccessFile2 = randomAccessFile;
            e.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            return false;
        } catch (IOException e7) {
            e = e7;
            randomAccessFile2 = randomAccessFile;
            e.printStackTrace();
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            throw th;
        }
    }

    public void start(IOpResult iOpResult) {
        openNotify();
        this.iOpResult = iOpResult;
        setBleOperateManagerCallback();
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(1, null)));
    }

    public void init() {
        setBleOperateManagerCallback();
        byte[] bArr = new byte[9];
        bArr[0] = 1;
        System.arraycopy(DataTransferUtils.intToBytes(this.dfuData.length), 0, bArr, 1, 4);
        System.arraycopy(DataTransferUtils.shortToBytes(this.dfuFileCrc16), 0, bArr, 5, 2);
        System.arraycopy(DataTransferUtils.shortToBytes(this.dfuFileChecksum), 0, bArr, 7, 2);
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(2, bArr)));
    }

    public void sendPacket() {
        this.bigPocketIndex = (short) 0;
        readNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readNextBigPocket() {
        setBleOperateManagerCallback();
        short s = this.bigPocketIndex;
        int i = s * 1024;
        byte[] bArr = this.dfuData;
        if (i < bArr.length) {
            int iMin = Math.min(1024, bArr.length - (s * 1024)) + 2;
            byte[] bArr2 = new byte[iMin];
            System.arraycopy(DataTransferUtils.shortToBytes((short) (this.bigPocketIndex + 1)), 0, bArr2, 0, 2);
            System.arraycopy(this.dfuData, this.bigPocketIndex * 1024, bArr2, 2, iMin - 2);
            sendPocketToBle(addHeader(3, bArr2));
            this.bigPocketIndex = (short) (this.bigPocketIndex + 1);
            return true;
        }
        Log.i(TAG, "升级包发送完毕");
        return false;
    }

    private void sendPocketToBle(byte[] bArr) {
        Log.i(TAG, "sendPocketToBle: bigPocket=" + DataTransferUtils.getHexString(bArr));
        setBleOperateManagerCallback();
        for (int i = 0; this.mPackageLength * i < bArr.length; i++) {
            BleOperateManager bleOperateManager = BleOperateManager.getInstance();
            int i2 = this.mPackageLength;
            bleOperateManager.execute(getWriteRequest(Arrays.copyOfRange(bArr, i * i2, (i * i2) + Math.min(i2, bArr.length - (i * i2)))));
        }
    }

    public void check() {
        setBleOperateManagerCallback();
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(4, null)));
    }

    public void endAndRelease() {
        this.iOpResult = null;
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(5, null)));
        BleOperateManager.getInstance().setCallback(null);
    }

    private byte[] addHeader(int i, byte[] bArr) {
        byte[] bArr2 = new byte[(bArr == null ? 0 : bArr.length) + 6];
        bArr2[0] = -68;
        bArr2[1] = (byte) i;
        if (bArr != null && bArr.length > 0) {
            System.arraycopy(DataTransferUtils.shortToBytes((short) bArr.length), 0, bArr2, 2, 2);
            System.arraycopy(DataTransferUtils.shortToBytes((short) CRC16.calcCrc16(bArr)), 0, bArr2, 4, 2);
            System.arraycopy(bArr, 0, bArr2, 6, bArr.length);
        } else {
            bArr2[4] = -1;
            bArr2[5] = -1;
        }
        return bArr2;
    }

    private WriteRequest getWriteRequest(byte[] bArr) {
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(SERIAL_PORT_SERVICE, SERIAL_PORT_CHAREACTER_WRITE);
        noRspInstance.setValue(bArr);
        return noRspInstance;
    }
}
