package com.oudmon.ble.base.communication.dfu_temperature;

import android.util.Log;
import com.oudmon.ble.base.bluetooth.IBleListener;
import com.oudmon.ble.base.communication.CompressUtils;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.DataTransferUtils;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/dfu_temperature/TemperatureHandle.class */
public class TemperatureHandle {
    private static final String TAG = "TemperatureHandle";
    private static final byte ACTION_SERIES = 37;
    private static final byte ACTION_ONCE = 38;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private IBleListener mBleManager;
    private HandlerCallback mCallback;
    private byte[] mReceivedData;
    private byte[] mFileSend;
    private boolean mReceiving;
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY);
    private short mPocketIndex = 0;
    private int mTotalCount = 0;
    private int mReceivedCount = 0;
    private List<String> mFileNames = new ArrayList();
    private TemperatureEntity mTempEntity = new TemperatureEntity();
    private int mPackageLength = JPackageManager.getInstance().getLength();

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/dfu_temperature/TemperatureHandle$HandlerCallback.class */
    public interface HandlerCallback {
        void onProgress(int i);

        void onComplete();

        void onNext(TemperatureEntity temperatureEntity);

        void onActionResult(int i, int i2);
    }

    public TemperatureHandle(IBleListener iBleManagerSrv) {
        this.mBleManager = iBleManagerSrv;
    }

    private void parseReceivedData(byte[] data) {
        try {
            Log.i(TAG, "===========================ParseReceivedData开始============================");
            this.mTempEntity.clear();
            this.mTempEntity.mIndex = data[0];
            this.mTempEntity.mTimeSpan = data[1];
            this.mTempEntity.mValues = new float[data[1] == 0 ? 1 : 1440 / this.mTempEntity.mTimeSpan];
            int i = 0;
            for (int index = 2; index < data.length; index++) {
                int value = data[index] & 255;
                if (value > 128) {
                    int length = value - EqConstants.CodeIndex.BUILD_IN_EQ_4;
                    for (int temp = 0; temp < length; temp++) {
                        int i2 = i;
                        i++;
                        this.mTempEntity.mValues[i2] = 0.0f;
                    }
                } else {
                    float val = (data[index] * 1.0f) / 10.0f;
                    int i3 = i;
                    i++;
                    this.mTempEntity.mValues[i3] = val + 32.0f;
                }
            }
            Log.i(TAG, "mTempEntity: " + this.mTempEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "===========================ParseReceivedData结束============================");
    }

    public void init(HandlerCallback callback) {
        this.mCallback = callback;
        this.enableNotifyRequest.setEnable(true);
        this.mBleManager.execute(this.enableNotifyRequest);
    }

    public void startObtainSeries(int index) {
        Log.i(TAG, "startObtainSeries... ");
        byte[] data = {(byte) index};
        this.mBleManager.execute(getWriteRequest(addHeader(37, data)));
    }

    @Deprecated
    public void testSend() {
        Log.i(TAG, "testSend... ");
        this.mBleManager.execute(getWriteRequest(addHeader(84, null)));
    }

    private boolean sendNextBigPocket() {
        try {
            if (this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2 < this.mFileSend.length) {
                byte[] bufferSend = new byte[Math.min(EqConstants.CodeIndex.REALTIME_EQ_2, this.mFileSend.length - (this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2))];
                System.arraycopy(this.mFileSend, this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2, bufferSend, 0, bufferSend.length);
                byte[] compressBuffer = CompressUtils.compress(bufferSend);
                byte[] bufferNext = new byte[compressBuffer.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bufferNext, 0, 2);
                System.arraycopy(compressBuffer, 0, bufferNext, 2, compressBuffer.length);
                sendPocketToBle(addHeader(50, bufferNext));
                this.mPocketIndex = (short) (this.mPocketIndex + 1);
                return true;
            }
            Log.i(TAG, "文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "文件发送异常: " + e.getMessage());
            return false;
        }
    }

    public void cmdSendPacket() {
        this.mPocketIndex = (short) 0;
        readNextBigPocket();
    }

    private boolean readNextBigPocket() {
        try {
            if (this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2 < this.mFileSend.length) {
                byte[] bufferSend = new byte[Math.min(EqConstants.CodeIndex.REALTIME_EQ_2, this.mFileSend.length - (this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2))];
                System.arraycopy(this.mFileSend, this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2, bufferSend, 0, bufferSend.length);
                byte[] compressBuffer = CompressUtils.compress(bufferSend);
                byte[] bufferNext = new byte[compressBuffer.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bufferNext, 0, 2);
                System.arraycopy(compressBuffer, 0, bufferNext, 2, compressBuffer.length);
                sendPocketToBle(addHeader(50, bufferNext));
                this.mPocketIndex = (short) (this.mPocketIndex + 1);
                return true;
            }
            Log.i(TAG, "文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "文件发送异常: " + e.getMessage());
            return false;
        }
    }

    private void sendPocketToBle(byte[] bigPocket) {
        Log.i(TAG, "sendPocketToBle: bigPocket=" + DataTransferUtils.getHexString(bigPocket));
        for (int index = 0; index * this.mPackageLength < bigPocket.length; index++) {
            this.mBleManager.execute(getWriteRequest(Arrays.copyOfRange(bigPocket, index * this.mPackageLength, (index * this.mPackageLength) + Math.min(this.mPackageLength, bigPocket.length - (index * this.mPackageLength)))));
        }
    }

    private void cmdCheck() {
        this.mBleManager.execute(getWriteRequest(addHeader(51, null)));
    }

    public void endAndRelease() {
        this.enableNotifyRequest.setEnable(false);
        this.mBleManager.execute(this.enableNotifyRequest);
    }

    private byte[] addHeader(int cmdId, byte[] data) {
        byte[] pocket = new byte[(data == null ? 0 : data.length) + 6];
        pocket[0] = -68;
        pocket[1] = (byte) cmdId;
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
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_WRITE);
        noRspInstance.setValue(data);
        return noRspInstance;
    }
}
