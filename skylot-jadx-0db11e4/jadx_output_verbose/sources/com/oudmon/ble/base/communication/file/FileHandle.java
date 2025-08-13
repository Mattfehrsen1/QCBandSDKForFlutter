package com.oudmon.ble.base.communication.file;

import android.graphics.Bitmap;
import android.util.Log;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.bluetooth.queue.BleDataBean;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.CompressUtils;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.communication.utils.ImageUtils;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.DataTransferUtils;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/file/FileHandle.class */
public class FileHandle {
    private static final String TAG = "FileHandle";
    public static final int TypeMarketWatchFace = 1;
    public static final int TypeDiyWatchFace = 2;
    public static final int TypeDismissFile = 3;
    public static final int TypeOtaFile = 4;
    private static final byte ACTION_SERIES = 37;
    private static final byte ACTION_ONCE = 38;
    private static final byte ACTION_A_GPS = 84;
    private static final byte ACTION_PLATE = 53;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private byte[] mReceivedData;
    private byte[] mFileSend;
    private boolean mReceiving;
    private static FileHandle mInstance;
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY);
    private Set<ICallback> mCallbackArray = new HashSet();
    private int currFileType = 0;
    private ICallback mCallback = new ICallback() { // from class: com.oudmon.ble.base.communication.file.FileHandle.1
        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onRequestAGPS() {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onRequestAGPS();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlate(List<PlateEntity> array) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onUpdatePlate(array);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlateError(int code) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onUpdatePlateError(code);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onDeletePlate() {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onDeletePlate();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onDeletePlateError(int code) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onDeletePlateError(code);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdateTemperature(TemperatureEntity data) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onUpdateTemperature(data);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdateTemperatureList(List<TemperatureOnceEntity> array) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onUpdateTemperatureList(array);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onFileNames(ArrayList<String> fileNames) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onFileNames(fileNames);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onProgress(int percent) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onProgress(percent);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onComplete() {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onComplete();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onActionResult(int type, int errCode) {
            for (ICallback callback : FileHandle.this.mCallbackArray) {
                callback.onActionResult(type, errCode);
            }
        }
    };
    private short mPocketIndex = 0;
    private boolean mPlateReceivedFinished = true;
    private boolean mTemperatureReceivedFinished = true;
    private boolean mTemperatureOnceReceivedFinished = true;
    private int mTotalCount = 0;
    private int mReceivedCount = 0;
    private List<String> mFileNames = new ArrayList();
    private OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.file.FileHandle.2
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String uuid, byte[] data) throws InterruptedException {
            if (data != null) {
                if ((data[0] & 255) != 188 || data[1] != 53) {
                    if (!FileHandle.this.mPlateReceivedFinished) {
                        try {
                            System.arraycopy(data, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, data.length);
                            FileHandle.access$212(FileHandle.this, data.length);
                            FileHandle.this.mPlateReceivedFinished = FileHandle.this.mReceivedCount >= FileHandle.this.mTotalCount;
                            if (FileHandle.this.mPlateReceivedFinished) {
                                if (FileHandle.this.mReceivedData.length > 2) {
                                    FileHandle.this.mCallback.onUpdatePlate(DataHelper.parsePlate(FileHandle.this.mReceivedData));
                                } else {
                                    List<PlateEntity> mPlateArray = new ArrayList<>();
                                    FileHandle.this.mCallback.onUpdatePlate(mPlateArray);
                                }
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    if ((data[0] & 255) == 188 && data[1] == 84) {
                        if (data[2] == 0) {
                            FileHandle.this.mCallback.onRequestAGPS();
                            return;
                        } else if (FileHandle.this.sendNextBigPocket()) {
                            Log.i(Constants.TAG, "向手环发送数据进度: " + (((FileHandle.this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2) * 100) / FileHandle.this.mFileSend.length) + ", 包序: " + ((int) FileHandle.this.mPocketIndex));
                            return;
                        } else {
                            Log.i(Constants.TAG, "向手环发送数据完毕, 包序: " + ((int) FileHandle.this.mPocketIndex));
                            FileHandle.this.cmdCheck();
                            return;
                        }
                    }
                    if ((data[0] & 255) != 188 || data[1] != 37) {
                        if (!FileHandle.this.mTemperatureReceivedFinished) {
                            System.arraycopy(data, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, data.length);
                            FileHandle.access$212(FileHandle.this, data.length);
                            FileHandle.this.mTemperatureReceivedFinished = FileHandle.this.mReceivedCount >= FileHandle.this.mTotalCount;
                            Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureReceivedFinished: " + FileHandle.this.mTemperatureReceivedFinished);
                            if (FileHandle.this.mTemperatureReceivedFinished) {
                                Log.i(Constants.TAG, "onReceiver All Temperature data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                if (FileHandle.this.mReceivedData.length > 2) {
                                    Log.i(Constants.TAG, "mCallback: " + FileHandle.this.mCallback + ", class: " + FileHandle.this.mCallback.getClass());
                                    FileHandle.this.mCallback.onUpdateTemperature(DataHelper.parseTemperature(FileHandle.this.mReceivedData));
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        if ((data[0] & 255) != 188 || data[1] != 38) {
                            if (!FileHandle.this.mTemperatureOnceReceivedFinished) {
                                System.arraycopy(data, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, data.length);
                                FileHandle.access$212(FileHandle.this, data.length);
                                FileHandle.this.mTemperatureOnceReceivedFinished = FileHandle.this.mReceivedCount >= FileHandle.this.mTotalCount;
                                Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureOnceReceivedFinished: " + FileHandle.this.mTemperatureOnceReceivedFinished);
                                if (FileHandle.this.mTemperatureOnceReceivedFinished) {
                                    Log.i(Constants.TAG, "onReceiver All Temperature once data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                    if (FileHandle.this.mReceivedData.length > 2) {
                                        Log.i(Constants.TAG, "mCallback: " + FileHandle.this.mCallback + ", class: " + FileHandle.this.mCallback.getClass());
                                        FileHandle.this.mCallback.onUpdateTemperatureList(DataHelper.parseTemperatureOnce(FileHandle.this.mReceivedData));
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            if ((data[0] & 255) != 188 || data[1] != 48) {
                                if (FileHandle.this.mReceiving) {
                                    System.arraycopy(data, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, data.length);
                                    FileHandle.access$212(FileHandle.this, data.length);
                                    FileHandle.this.mReceiving = FileHandle.this.mReceivedCount < FileHandle.this.mTotalCount;
                                    Log.i(Constants.TAG, "文件：->3 mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mReceiving: " + FileHandle.this.mReceiving);
                                    if (!FileHandle.this.mReceiving) {
                                        Log.i(Constants.TAG, "文件：->4 ->" + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                        FileHandle.this.parseFileInfo(FileHandle.this.mReceivedData);
                                        FileHandle.this.mCallback.onFileNames((ArrayList) FileHandle.this.mFileNames);
                                        return;
                                    }
                                    return;
                                }
                                if ((data[0] & 255) == 188 && data[1] == 49) {
                                    Log.i(Constants.TAG, "初始化完成，开始向手环发送实际文件");
                                    FileHandle.this.cmdSendPacket();
                                    return;
                                }
                                if ((data[0] & 255) == 188 && data[1] == 50) {
                                    if (FileHandle.this.readNextBigPocket()) {
                                        int percent = ((FileHandle.this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2) * 100) / FileHandle.this.mFileSend.length;
                                        Log.i(Constants.TAG, "向手环发送数据进度: " + percent + ", 包序: " + ((int) FileHandle.this.mPocketIndex));
                                        FileHandle.this.mCallback.onProgress(Math.min(percent, 100));
                                        return;
                                    } else {
                                        Log.i(Constants.TAG, "向手环发送数据完毕, 包序: " + ((int) FileHandle.this.mPocketIndex));
                                        FileHandle.this.cmdCheck();
                                        return;
                                    }
                                }
                                if ((data[0] & 255) == 188 && data[1] == 51) {
                                    Log.i(Constants.TAG, "===============回调 onComplete");
                                    FileHandle.this.mCallback.onComplete();
                                    return;
                                }
                                if ((data[0] & 255) == 188 && data[1] == 54) {
                                    Log.i(Constants.TAG, "初始化完成，开始向手环发送实际文件");
                                    if (data.length <= 6 || data[6] <= 0) {
                                        FileHandle.this.executeFileSend(55);
                                        return;
                                    } else {
                                        FileHandle.this.mCallback.onUpdatePlateError(data[6]);
                                        return;
                                    }
                                }
                                if ((data[0] & 255) == 188 && data[1] == 55) {
                                    if (FileHandle.this.executeNextSend(55)) {
                                        int percent2 = ((FileHandle.this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2) * 100) / FileHandle.this.mFileSend.length;
                                        Log.i(Constants.TAG, "向手环发送数据进度: " + percent2 + ", 包序: " + ((int) FileHandle.this.mPocketIndex));
                                        FileHandle.this.mCallback.onProgress(Math.min(percent2, 100));
                                        return;
                                    } else {
                                        Log.i(Constants.TAG, "向手环发送数据完毕, 包序: " + ((int) FileHandle.this.mPocketIndex));
                                        FileHandle.this.executeFileFinished(56);
                                        return;
                                    }
                                }
                                if ((data[0] & 255) == 188 && data[1] == 56) {
                                    Log.i(Constants.TAG, "===============回调 onComplete");
                                    FileHandle.this.mCallback.onComplete();
                                    return;
                                } else {
                                    if ((data[0] & 255) == 188 && data[1] == 57) {
                                        if (data.length <= 6 || data[6] <= 0) {
                                            FileHandle.this.mCallback.onDeletePlate();
                                            return;
                                        } else {
                                            FileHandle.this.mCallback.onDeletePlateError(data[6]);
                                            return;
                                        }
                                    }
                                    return;
                                }
                            }
                            FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(data, 2);
                            FileHandle.this.mReceivedCount = data.length - 6;
                            FileHandle.this.mReceivedData = new byte[FileHandle.this.mTotalCount];
                            System.arraycopy(data, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                            FileHandle.this.mReceiving = FileHandle.this.mReceivedCount < FileHandle.this.mTotalCount;
                            Log.i(Constants.TAG, "文件：-> 1mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mReceiving: " + FileHandle.this.mReceiving);
                            if (!FileHandle.this.mReceiving) {
                                Log.i(Constants.TAG, "文件：->2->" + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                FileHandle.this.parseFileInfo(FileHandle.this.mReceivedData);
                                FileHandle.this.mCallback.onFileNames((ArrayList) FileHandle.this.mFileNames);
                                return;
                            }
                            return;
                        }
                        FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(data, 2);
                        if (FileHandle.this.mTotalCount != 0) {
                            FileHandle.this.mReceivedCount = data.length - 6;
                            FileHandle.this.mReceivedData = new byte[FileHandle.this.mTotalCount];
                            System.arraycopy(data, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                            FileHandle.this.mTemperatureOnceReceivedFinished = FileHandle.this.mReceivedCount >= FileHandle.this.mTotalCount;
                            Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureOnceReceivedFinished: " + FileHandle.this.mTemperatureOnceReceivedFinished);
                            if (FileHandle.this.mTemperatureOnceReceivedFinished) {
                                Log.i(Constants.TAG, "onReceiver All Temperature once data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                if (FileHandle.this.mReceivedData.length > 2) {
                                    Log.i(Constants.TAG, "mCallback: " + FileHandle.this.mCallback + ", class: " + FileHandle.this.mCallback.getClass());
                                    FileHandle.this.mCallback.onUpdateTemperatureList(DataHelper.parseTemperatureOnce(FileHandle.this.mReceivedData));
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(data, 2);
                    if (FileHandle.this.mTotalCount != 0) {
                        FileHandle.this.mReceivedCount = data.length - 6;
                        FileHandle.this.mReceivedData = new byte[FileHandle.this.mTotalCount];
                        System.arraycopy(data, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                        FileHandle.this.mTemperatureReceivedFinished = FileHandle.this.mReceivedCount >= FileHandle.this.mTotalCount;
                        Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureReceivedFinished: " + FileHandle.this.mTemperatureReceivedFinished);
                        if (FileHandle.this.mTemperatureReceivedFinished) {
                            Log.i(Constants.TAG, "onReceiver All Temperature data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                            if (FileHandle.this.mReceivedData.length > 2) {
                                Log.i(Constants.TAG, "mCallback: " + FileHandle.this.mCallback + ", class: " + FileHandle.this.mCallback.getClass());
                                FileHandle.this.mCallback.onUpdateTemperature(DataHelper.parseTemperature(FileHandle.this.mReceivedData));
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(data, 2);
                if (FileHandle.this.mTotalCount != 0) {
                    FileHandle.this.mReceivedCount = data.length - 6;
                    FileHandle.this.mReceivedData = new byte[FileHandle.this.mTotalCount];
                    System.arraycopy(data, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                    FileHandle.this.mPlateReceivedFinished = FileHandle.this.mReceivedCount >= FileHandle.this.mTotalCount;
                    Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mPlateReceivedFinished: " + FileHandle.this.mPlateReceivedFinished);
                    if (FileHandle.this.mPlateReceivedFinished) {
                        Log.i(Constants.TAG, "onReceiver All Temperature data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                        if (FileHandle.this.mReceivedData.length > 2) {
                            FileHandle.this.mCallback.onUpdatePlate(DataHelper.parsePlate(FileHandle.this.mReceivedData));
                        } else {
                            List<PlateEntity> mPlateArray2 = new ArrayList<>();
                            FileHandle.this.mCallback.onUpdatePlate(mPlateArray2);
                        }
                    }
                }
            }
        }
    };
    private int mPackageLength = JPackageManager.getInstance().getLength();

    static /* synthetic */ int access$212(FileHandle x0, int x1) {
        int i = x0.mReceivedCount + x1;
        x0.mReceivedCount = i;
        return i;
    }

    public int getCurrFileType() {
        return this.currFileType;
    }

    public void setCurrFileType(int currFileType) {
        this.currFileType = currFileType;
    }

    public static FileHandle getInstance() {
        if (mInstance == null) {
            synchronized (FileHandle.class) {
                if (mInstance == null) {
                    mInstance = new FileHandle();
                }
            }
        }
        return mInstance;
    }

    private FileHandle() {
        Log.i(Constants.TAG, "create FileHandle.. mPackageLength: " + this.mPackageLength);
    }

    public void initRegister() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public void clearCallback() {
        try {
            this.mCallbackArray.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseFileInfo(byte[] bArr) {
        this.mFileNames.clear();
        try {
            if (bArr[0] > 0) {
                int start = 0;
                while (start < bArr.length - 1) {
                    int i = bArr[start + 1];
                    byte[] byteName = new byte[i];
                    System.arraycopy(bArr, start + 2, byteName, 0, i);
                    start += i + 1;
                    String fileName = new String(byteName);
                    this.mFileNames.add(fileName);
                    Log.i(Constants.TAG, "fileLength: " + i + ", fileName: " + fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerCallback(ICallback callback) {
        try {
            this.mCallbackArray.add(callback);
            this.enableNotifyRequest.setEnable(true);
            BleOperateManager.getInstance().execute(this.enableNotifyRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startObtainTemperatureSeries(int index) throws InterruptedException {
        Log.i(Constants.TAG, "startObtainSeries... ");
        byte[] data = {(byte) index};
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(37, data), this.mPackageLength));
    }

    public void startObtainTemperatureOnce(int index) throws InterruptedException {
        Log.i(Constants.TAG, "startObtainOnce... ");
        byte[] data = {(byte) index};
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(38, data), this.mPackageLength));
    }

    public void startObtainPlate() throws InterruptedException {
        Log.i(Constants.TAG, "startObtainPlate... ");
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(53, null), this.mPackageLength));
    }

    public void start() throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(48, null), this.mPackageLength));
    }

    @Deprecated
    public void testSend() {
        Log.i(Constants.TAG, "testSend... ");
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(84, null)));
    }

    public boolean checkFile(String filePath) throws IOException {
        Log.i(Constants.TAG, "准备发送的文件路径：" + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            Log.i(Constants.TAG, "准备发送的文件不存在！");
            return false;
        }
        RandomAccessFile accessFile = null;
        try {
            try {
                this.mFileSend = fileToByteStr(filePath);
                Log.i(Constants.TAG, "准备发送的文件.. dataSize=" + this.mFileSend.length + "  readSize=" + this.mFileSend.length);
                if (0 != 0) {
                    try {
                        accessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (0 == 0) {
                    return false;
                }
                try {
                    accessFile.close();
                    return false;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return false;
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    accessFile.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public boolean checkData(byte[] data) {
        Log.i(Constants.TAG, "checkData... dataSize: " + data.length);
        this.mFileSend = data;
        return true;
    }

    public static byte[] fileToByteStr(String path) throws IOException {
        File file;
        byte[] data = null;
        try {
            file = new File(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        InputStream in = new FileInputStream(path);
        data = new byte[in.available()];
        in.read(data);
        in.close();
        return data;
    }

    public boolean executeFilePrepare(String path) throws IOException {
        Log.i(Constants.TAG, "准备发送的文件路径：" + path);
        File file = new File(path);
        if (!file.exists()) {
            Log.i(Constants.TAG, "准备发送的文件不存在！");
            return false;
        }
        RandomAccessFile accessFile = null;
        try {
            try {
                accessFile = new RandomAccessFile(path, "r");
                this.mFileSend = new byte[(int) accessFile.length()];
                int size = accessFile.read(this.mFileSend, 0, this.mFileSend.length);
                Log.i(Constants.TAG, "准备发送的文件.. dataSize = " + this.mFileSend.length + ", readSize = " + size);
                if (accessFile != null) {
                    try {
                        accessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (accessFile == null) {
                    return false;
                }
                try {
                    accessFile.close();
                    return false;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return false;
                }
            }
        } catch (Throwable th) {
            if (accessFile != null) {
                try {
                    accessFile.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public void executeFileInit(String fileName, int cmd) {
        try {
            Log.i(Constants.TAG, "executeFileInit.. 开始");
            byte[] fileNames = fileName.getBytes(StandardCharsets.UTF_8);
            byte[] data = new byte[fileNames.length + 10];
            data[0] = 1;
            System.arraycopy(DataTransferUtils.intToBytes(this.mFileSend.length), 0, data, 1, 4);
            data[9] = (byte) fileNames.length;
            System.arraycopy(fileNames, 0, data, 10, fileNames.length);
            sendPocketToBle(addHeader(cmd, data));
            Log.i(Constants.TAG, "executeFileInit.. 完成");
        } catch (Exception e) {
            Log.i(Constants.TAG, "executeFileInit.. Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeFileSend(int cmd) {
        this.mPocketIndex = (short) 0;
        Log.i(Constants.TAG, "executeFileSend.. 开始发送数据，数据长度: " + this.mFileSend.length);
        Log.i(Constants.TAG, "executeFileSend.. 开始发送数据，数据内容: " + DataTransferUtils.getHexString(this.mFileSend));
        executeNextSend(cmd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean executeNextSend(int cmd) {
        try {
            if (this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2 < this.mFileSend.length) {
                byte[] bufferSend = new byte[Math.min(EqConstants.CodeIndex.REALTIME_EQ_2, this.mFileSend.length - (this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2))];
                System.arraycopy(this.mFileSend, this.mPocketIndex * EqConstants.CodeIndex.REALTIME_EQ_2, bufferSend, 0, bufferSend.length);
                byte[] compressBuffer = CompressUtils.compress(bufferSend);
                byte[] bufferNext = new byte[compressBuffer.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bufferNext, 0, 2);
                System.arraycopy(compressBuffer, 0, bufferNext, 2, compressBuffer.length);
                sendPocketToBle(addHeader(cmd, bufferNext));
                this.mPocketIndex = (short) (this.mPocketIndex + 1);
                return true;
            }
            Log.i(Constants.TAG, "文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(Constants.TAG, "文件发送异常: " + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeFileFinished(int cmd) throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(cmd, null), this.mPackageLength));
    }

    public void executeFileDelete(String name) throws InterruptedException {
        Log.i(Constants.TAG, "executeFileDelete.. name: " + name);
        byte[] fileNames = name.getBytes(StandardCharsets.UTF_8);
        Log.i(Constants.TAG, "executeFileDelete.. fileNames: " + DataTransferUtils.getHexString(fileNames));
        byte[] data = new byte[fileNames.length + 1];
        data[0] = 1;
        System.arraycopy(fileNames, 0, data, 1, fileNames.length);
        Log.i(Constants.TAG, "executeFileDelete.. data: " + DataTransferUtils.getHexString(data));
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(57, data), this.mPackageLength));
    }

    public void executeMusicSend(boolean playing, int progress, int volume, String name) throws InterruptedException {
        Log.i(Constants.TAG, "executeMusicSend.. playing: " + playing + ", progress: " + progress + ", volume: " + volume + ", name: " + name);
        byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);
        Log.i(Constants.TAG, "executeMusicSend.. nameBytes: " + DataTransferUtils.getHexString(nameBytes));
        byte[] data = new byte[nameBytes.length + 3];
        data[0] = (byte) (playing ? 0 : 1);
        data[1] = (byte) progress;
        data[2] = (byte) volume;
        System.arraycopy(nameBytes, 0, data, 3, nameBytes.length);
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(6, data), this.mPackageLength));
    }

    public void cmdFileInit(String fileName) throws UnsupportedEncodingException {
        try {
            byte[] fileNames = fileName.getBytes("UTF-8");
            byte[] data = new byte[fileNames.length + 10];
            data[0] = 1;
            System.arraycopy(DataTransferUtils.intToBytes(this.mFileSend.length), 0, data, 1, 4);
            data[9] = (byte) fileNames.length;
            System.arraycopy(fileNames, 0, data, 10, fileNames.length);
            sendPocketToBle(addHeader(49, data));
            Log.i(Constants.TAG, "cmdFileInit.. 完成");
        } catch (Exception e) {
            Log.i(Constants.TAG, "cmdFileInit.. Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void startGpsOnline() {
        this.mPocketIndex = (short) 0;
        sendNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendNextBigPocket() {
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
            Log.i(Constants.TAG, "文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(Constants.TAG, "文件发送异常: " + e.getMessage());
            return false;
        }
    }

    public void cmdSendPacket() {
        this.mPocketIndex = (short) 0;
        if (this.mFileSend == null) {
            return;
        }
        Log.i(Constants.TAG, "cmdSendPacket.. 开始发送数据，数据长度: " + this.mFileSend.length);
        Log.i(Constants.TAG, "cmdSendPacket.. 开始发送数据，数据内容: " + DataTransferUtils.getHexString(this.mFileSend));
        readNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readNextBigPocket() {
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
            Log.i(Constants.TAG, "文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(Constants.TAG, "文件发送异常: " + e.getMessage());
            return false;
        }
    }

    private void sendPocketToBle(byte[] bigPocket) throws InterruptedException {
        resetPackageLength();
        Log.i(Constants.TAG, "sendPocketToBle: mPackageLength: " + this.mPackageLength + ", bigPocket=" + DataTransferUtils.getHexString(bigPocket));
        BleThreadManager.getInstance().addData(new BleDataBean(bigPocket, this.mPackageLength));
    }

    private void resetPackageLength() {
        this.mPackageLength = JPackageManager.getInstance().getLength();
        Log.i(Constants.TAG, "resetPackageLength.. mPackageLength: " + this.mPackageLength);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cmdCheck() throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(51, null), this.mPackageLength));
    }

    public void endAndRelease() {
        this.enableNotifyRequest.setEnable(false);
        BleOperateManager.getInstance().execute(this.enableNotifyRequest);
        BleOperateManager.getInstance().setCallback(null);
    }

    public void customizeWatchFace(Bitmap bitmap, int width, int height, SimpleCallback callback) throws UnsupportedEncodingException {
        byte[] data = ImageUtils.getRgb565ByteArray(bitmap, width, height);
        getInstance().registerCallback(callback);
        getInstance().currFileType = 2;
        if (getInstance().checkData(data)) {
            getInstance().cmdFileInit("time2_bg_img.ui");
        }
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

    public WriteRequest getWriteRequest(byte[] data) {
        Log.i(Constants.TAG, "getWriteRequest: data=" + DataTransferUtils.getHexString(data));
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_WRITE);
        noRspInstance.setValue(data);
        return noRspInstance;
    }
}
