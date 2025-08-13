package com.oudmon.ble.base.communication.file;

import com.bumptech.glide.load.Key;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.bluetooth.queue.BleDataBean;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.CompressUtils;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.LogToFile;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes3.dex */
public class FileHandle {
    private static final byte ACTION_A_GPS = 84;
    private static final byte ACTION_ONCE = 38;
    private static final byte ACTION_PLATE = 53;
    private static final byte ACTION_SERIES = 37;
    private static final String TAG = "FileHandle";
    public static final int TypeDismissFile = 3;
    public static final int TypeDiyWatchFace = 2;
    public static final int TypeMarketWatchFace = 1;
    public static final int TypeOtaFile = 4;
    private static FileHandle mInstance;
    private byte[] mFileSend;
    private byte[] mReceivedData;
    private boolean mReceiving;
    private int noDataCount;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY, new EnableNotifyRequest.ListenerCallback() { // from class: com.oudmon.ble.base.communication.file.FileHandle.1
        @Override // com.oudmon.ble.base.request.EnableNotifyRequest.ListenerCallback
        public void enable(boolean z) {
        }
    });
    private CopyOnWriteArraySet<ICallback> mCallbackArray = new CopyOnWriteArraySet<>();
    private int currFileType = 0;
    private String notDataString = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    private ICallback mCallback = new ICallback() { // from class: com.oudmon.ble.base.communication.file.FileHandle.2
        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onRequestAGPS() {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onRequestAGPS();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlate(List<PlateEntity> list) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onUpdatePlate(list);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdatePlateError(int i) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onUpdatePlateError(i);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onDeletePlate() {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onDeletePlate();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onDeletePlateError(int i) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onDeletePlateError(i);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdateTemperature(TemperatureEntity temperatureEntity) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onUpdateTemperature(temperatureEntity);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onUpdateTemperatureList(List<TemperatureOnceEntity> list) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onUpdateTemperatureList(list);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onFileNames(ArrayList<String> arrayList) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onFileNames(arrayList);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onProgress(int i) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onProgress(i);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onComplete() {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onComplete();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.ICallback
        public void onActionResult(int i, int i2) {
            Iterator it = FileHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((ICallback) it.next()).onActionResult(i, i2);
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
    private OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.file.FileHandle.3
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String str, byte[] bArr) throws Throwable {
            if (bArr != null) {
                if ((bArr[0] & 255) != 188 || bArr[1] != 53) {
                    if (!FileHandle.this.mPlateReceivedFinished) {
                        try {
                            System.arraycopy(bArr, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, bArr.length);
                            FileHandle.access$212(FileHandle.this, bArr.length);
                            FileHandle fileHandle = FileHandle.this;
                            fileHandle.mPlateReceivedFinished = fileHandle.mReceivedCount >= FileHandle.this.mTotalCount;
                            if (FileHandle.this.mPlateReceivedFinished) {
                                if (FileHandle.this.mReceivedData.length > 2) {
                                    FileHandle.this.mCallback.onUpdatePlate(DataHelper.parsePlate(FileHandle.this.mReceivedData));
                                } else {
                                    FileHandle.this.mCallback.onUpdatePlate(new ArrayList());
                                }
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    if ((bArr[0] & 255) == 188 && bArr[1] == 84) {
                        if (bArr[2] == 0) {
                            FileHandle.this.mCallback.onRequestAGPS();
                            return;
                        }
                        if (FileHandle.this.sendNextBigPocket()) {
                            XLog.i("向手环发送数据进度: " + (((FileHandle.this.mPocketIndex * 1024) * 100) / FileHandle.this.mFileSend.length) + ", 包序: " + ((int) FileHandle.this.mPocketIndex));
                            return;
                        }
                        XLog.i("向手环发送数据完毕, 包序: " + ((int) FileHandle.this.mPocketIndex));
                        FileHandle.this.cmdCheck();
                        return;
                    }
                    if ((bArr[0] & 255) != 188 || bArr[1] != 37) {
                        if (!FileHandle.this.mTemperatureReceivedFinished) {
                            System.arraycopy(bArr, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, bArr.length);
                            FileHandle.access$212(FileHandle.this, bArr.length);
                            FileHandle fileHandle2 = FileHandle.this;
                            fileHandle2.mTemperatureReceivedFinished = fileHandle2.mReceivedCount >= FileHandle.this.mTotalCount;
                            XLog.i("onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureReceivedFinished: " + FileHandle.this.mTemperatureReceivedFinished);
                            if (FileHandle.this.mTemperatureReceivedFinished) {
                                XLog.i("onReceiver All Temperature data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                if (FileHandle.this.mReceivedData.length > 2) {
                                    XLog.i("mCallback: " + FileHandle.this.mCallback + ", class: " + FileHandle.this.mCallback.getClass());
                                    FileHandle.this.mCallback.onUpdateTemperature(DataHelper.parseTemperature(FileHandle.this.mReceivedData));
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        if ((bArr[0] & 255) != 188 || bArr[1] != 38) {
                            if (!FileHandle.this.mTemperatureOnceReceivedFinished) {
                                System.arraycopy(bArr, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, bArr.length);
                                FileHandle.access$212(FileHandle.this, bArr.length);
                                FileHandle fileHandle3 = FileHandle.this;
                                fileHandle3.mTemperatureOnceReceivedFinished = fileHandle3.mReceivedCount >= FileHandle.this.mTotalCount;
                                XLog.i("onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureOnceReceivedFinished: " + FileHandle.this.mTemperatureOnceReceivedFinished);
                                if (FileHandle.this.mTemperatureOnceReceivedFinished) {
                                    XLog.i("onReceiver All Temperature once data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                    if (FileHandle.this.mReceivedData.length > 2) {
                                        XLog.i("mCallback: " + FileHandle.this.mCallback + ", class: " + FileHandle.this.mCallback.getClass());
                                        FileHandle.this.mCallback.onUpdateTemperatureList(DataHelper.parseTemperatureOnce(FileHandle.this.mReceivedData));
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            if ((bArr[0] & 255) != 188 || bArr[1] != 48) {
                                if (FileHandle.this.mReceiving) {
                                    System.arraycopy(bArr, 0, FileHandle.this.mReceivedData, FileHandle.this.mReceivedCount, bArr.length);
                                    FileHandle.access$212(FileHandle.this, bArr.length);
                                    FileHandle fileHandle4 = FileHandle.this;
                                    fileHandle4.mReceiving = fileHandle4.mReceivedCount < FileHandle.this.mTotalCount;
                                    XLog.i("文件：->3 mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mReceiving: " + FileHandle.this.mReceiving);
                                    if (FileHandle.this.mReceiving) {
                                        return;
                                    }
                                    XLog.i("文件：->4 ->" + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                                    FileHandle fileHandle5 = FileHandle.this;
                                    fileHandle5.parseFileInfo(fileHandle5.mReceivedData);
                                    FileHandle.this.mCallback.onFileNames((ArrayList) FileHandle.this.mFileNames);
                                    return;
                                }
                                if ((bArr[0] & 255) == 188 && bArr[1] == 49) {
                                    XLog.i("初始化完成，开始向手环发送实际文件");
                                    FileHandle.this.cmdSendPacket();
                                    return;
                                }
                                if ((bArr[0] & 255) == 188 && bArr[1] == 50) {
                                    if (FileHandle.this.readNextBigPocket()) {
                                        int length = ((FileHandle.this.mPocketIndex * 1024) * 100) / FileHandle.this.mFileSend.length;
                                        XLog.i("向手环发送数据进度: " + length + ", 包序: " + ((int) FileHandle.this.mPocketIndex));
                                        FileHandle.this.mCallback.onProgress(Math.min(length, 100));
                                        return;
                                    }
                                    XLog.i("向手环发送数据完毕, 包序: " + ((int) FileHandle.this.mPocketIndex));
                                    FileHandle.this.cmdCheck();
                                    return;
                                }
                                if ((bArr[0] & 255) == 188 && bArr[1] == 51) {
                                    XLog.i("===============回调 onComplete");
                                    FileHandle.this.mCallback.onComplete();
                                    return;
                                }
                                if ((bArr[0] & 255) == 188 && bArr[1] == 54) {
                                    XLog.i("初始化完成，开始向手环发送实际文件");
                                    if (bArr.length <= 6 || bArr[6] <= 0) {
                                        FileHandle.this.executeFileSend(55);
                                        return;
                                    } else {
                                        FileHandle.this.mCallback.onUpdatePlateError(bArr[6]);
                                        return;
                                    }
                                }
                                if ((bArr[0] & 255) == 188 && bArr[1] == 55) {
                                    if (FileHandle.this.executeNextSend(55)) {
                                        int length2 = ((FileHandle.this.mPocketIndex * 1024) * 100) / FileHandle.this.mFileSend.length;
                                        XLog.i("向手环发送数据进度: " + length2 + ", 包序: " + ((int) FileHandle.this.mPocketIndex));
                                        LogToFile.getInstance().wtf("向手环发送数据进度: " + length2 + ", 包序: " + ((int) FileHandle.this.mPocketIndex));
                                        FileHandle.this.mCallback.onProgress(Math.min(length2, 100));
                                        return;
                                    }
                                    XLog.i("向手环发送数据完毕, 包序: " + ((int) FileHandle.this.mPocketIndex));
                                    FileHandle.this.executeFileFinished(56);
                                    return;
                                }
                                if ((bArr[0] & 255) == 188 && bArr[1] == 56) {
                                    if (bArr[6] > 0) {
                                        FileHandle.this.mCallback.onUpdatePlateError(666);
                                        return;
                                    } else {
                                        FileHandle.this.mCallback.onComplete();
                                        return;
                                    }
                                }
                                if ((bArr[0] & 255) == 188 && bArr[1] == 57) {
                                    if (bArr.length <= 6 || bArr[6] <= 0) {
                                        FileHandle.this.mCallback.onDeletePlate();
                                        return;
                                    } else {
                                        FileHandle.this.mCallback.onDeletePlateError(bArr[6]);
                                        return;
                                    }
                                }
                                return;
                            }
                            FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                            FileHandle.this.mReceivedCount = bArr.length - 6;
                            FileHandle fileHandle6 = FileHandle.this;
                            fileHandle6.mReceivedData = new byte[fileHandle6.mTotalCount];
                            System.arraycopy(bArr, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                            FileHandle fileHandle7 = FileHandle.this;
                            fileHandle7.mReceiving = fileHandle7.mReceivedCount < FileHandle.this.mTotalCount;
                            XLog.i("文件：-> 1mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mReceiving: " + FileHandle.this.mReceiving);
                            if (FileHandle.this.mReceiving) {
                                return;
                            }
                            XLog.i("文件：->2->" + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                            FileHandle fileHandle8 = FileHandle.this;
                            fileHandle8.parseFileInfo(fileHandle8.mReceivedData);
                            FileHandle.this.mCallback.onFileNames((ArrayList) FileHandle.this.mFileNames);
                            return;
                        }
                        FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                        if (FileHandle.this.mTotalCount == 0) {
                            return;
                        }
                        FileHandle.this.mReceivedCount = bArr.length - 6;
                        FileHandle fileHandle9 = FileHandle.this;
                        fileHandle9.mReceivedData = new byte[fileHandle9.mTotalCount];
                        System.arraycopy(bArr, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                        FileHandle fileHandle10 = FileHandle.this;
                        fileHandle10.mTemperatureOnceReceivedFinished = fileHandle10.mReceivedCount >= FileHandle.this.mTotalCount;
                        XLog.i("onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureOnceReceivedFinished: " + FileHandle.this.mTemperatureOnceReceivedFinished);
                        if (FileHandle.this.mTemperatureOnceReceivedFinished) {
                            XLog.i("onReceiver All Temperature once data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                            if (FileHandle.this.mReceivedData.length <= 2) {
                                FileHandle.this.mCallback.onUpdateTemperatureList(null);
                                return;
                            }
                            XLog.i("mCallback: " + FileHandle.this.mCallback + ", class: " + FileHandle.this.mCallback.getClass());
                            FileHandle.this.mCallback.onUpdateTemperatureList(DataHelper.parseTemperatureOnce(FileHandle.this.mReceivedData));
                            return;
                        }
                        return;
                    }
                    FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                    if (FileHandle.this.mTotalCount == 0) {
                        return;
                    }
                    FileHandle.this.mReceivedCount = bArr.length - 6;
                    FileHandle fileHandle11 = FileHandle.this;
                    fileHandle11.mReceivedData = new byte[fileHandle11.mTotalCount];
                    System.arraycopy(bArr, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                    FileHandle fileHandle12 = FileHandle.this;
                    fileHandle12.mTemperatureReceivedFinished = fileHandle12.mReceivedCount >= FileHandle.this.mTotalCount;
                    XLog.i("onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mTemperatureReceivedFinished: " + FileHandle.this.mTemperatureReceivedFinished);
                    if (FileHandle.this.mTemperatureReceivedFinished) {
                        XLog.i("onReceiver All Temperature data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                        if (FileHandle.this.mReceivedData.length > 2) {
                            FileHandle.this.mCallback.onUpdateTemperature(DataHelper.parseTemperature(FileHandle.this.mReceivedData));
                            return;
                        } else {
                            FileHandle.this.mCallback.onUpdateTemperature(null);
                            return;
                        }
                    }
                    return;
                }
                FileHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                if (FileHandle.this.mTotalCount == 0) {
                    return;
                }
                FileHandle.this.mReceivedCount = bArr.length - 6;
                FileHandle fileHandle13 = FileHandle.this;
                fileHandle13.mReceivedData = new byte[fileHandle13.mTotalCount];
                System.arraycopy(bArr, 6, FileHandle.this.mReceivedData, 0, FileHandle.this.mReceivedCount);
                FileHandle fileHandle14 = FileHandle.this;
                fileHandle14.mPlateReceivedFinished = fileHandle14.mReceivedCount >= FileHandle.this.mTotalCount;
                XLog.i("onReceivedData.. mTotalCount: " + FileHandle.this.mTotalCount + ", mReceivedCount: " + FileHandle.this.mReceivedCount + ", mPlateReceivedFinished: " + FileHandle.this.mPlateReceivedFinished);
                if (FileHandle.this.mPlateReceivedFinished) {
                    XLog.i("onReceiver All Temperature data: " + DataTransferUtils.getHexString(FileHandle.this.mReceivedData));
                    if (FileHandle.this.mReceivedData.length > 2) {
                        XLog.i(DataHelper.parsePlate(FileHandle.this.mReceivedData));
                        FileHandle.this.mCallback.onUpdatePlate(DataHelper.parsePlate(FileHandle.this.mReceivedData));
                    } else {
                        FileHandle.this.mCallback.onUpdatePlate(new ArrayList());
                    }
                }
            }
        }
    };
    private int mPackageLength = JPackageManager.getInstance().getLength();

    static /* synthetic */ int access$212(FileHandle fileHandle, int i) {
        int i2 = fileHandle.mReceivedCount + i;
        fileHandle.mReceivedCount = i2;
        return i2;
    }

    public int getCurrFileType() {
        return this.currFileType;
    }

    public void setCurrFileType(int i) {
        this.currFileType = i;
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
        XLog.i("create FileHandle.. mPackageLength: " + this.mPackageLength);
    }

    public void initRegister() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public void setBleOperateManagerCallback() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseFileInfo(byte[] bArr) {
        this.mFileNames.clear();
        try {
            if (bArr[0] > 0) {
                int i = 0;
                while (i < bArr.length - 1) {
                    int i2 = bArr[i + 1];
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, i + 2, bArr2, 0, i2);
                    i += i2 + 1;
                    String str = new String(bArr2);
                    this.mFileNames.add(str);
                    XLog.i("fileLength: " + i2 + ", fileName: " + str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerCallback(ICallback iCallback) {
        try {
            this.mCallbackArray.remove(iCallback);
            this.mCallbackArray.add(iCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCallback(ICallback iCallback) {
        try {
            this.mCallbackArray.remove(iCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCallback() {
        try {
            this.mCallbackArray.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startObtainTemperatureSeries(int i) throws InterruptedException {
        XLog.i("startObtainSeries... ");
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(37, new byte[]{(byte) i}), this.mPackageLength));
    }

    public void startObtainTemperatureOnce(int i) throws InterruptedException {
        XLog.i("startObtainOnce... ");
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(38, new byte[]{(byte) i}), this.mPackageLength));
    }

    public void startObtainPlate() throws InterruptedException {
        XLog.i("startObtainPlate... ");
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(53, null), this.mPackageLength));
    }

    public void start() throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(48, null), this.mPackageLength));
    }

    @Deprecated
    public void testSend() {
        XLog.i("testSend... ");
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(84, null)));
    }

    public boolean checkFile(String str) {
        XLog.i("准备发送的文件路径：" + str);
        if (!new File(str).exists()) {
            XLog.i("准备发送的文件不存在！");
            return false;
        }
        try {
            this.mFileSend = fileToByteStr(str);
            XLog.i("准备发送的文件.. dataSize=" + this.mFileSend.length + "  readSize=" + this.mFileSend.length);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkData(byte[] bArr) {
        XLog.i("checkData... dataSize: " + bArr.length);
        this.mFileSend = bArr;
        return true;
    }

    public static byte[] fileToByteStr(String str) throws IOException {
        byte[] bArr = null;
        try {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(str);
                bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                fileInputStream.close();
                return bArr;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public boolean executeFilePrepare(String str) throws Throwable {
        RandomAccessFile randomAccessFile;
        this.noDataCount = 0;
        if (!new File(str).exists()) {
            XLog.i("准备发送的文件不存在！");
            return false;
        }
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(str, "r");
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            byte[] bArr = new byte[(int) randomAccessFile.length()];
            this.mFileSend = bArr;
            int i = randomAccessFile.read(bArr, 0, bArr.length);
            XLog.e("准备发送的文件.. dataSize = " + this.mFileSend.length + ", readSize = " + i);
            LogToFile logToFile = LogToFile.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append("文件本地路径:");
            sb.append(str);
            logToFile.wtf(sb.toString());
            LogToFile.getInstance().wtf("准备发送的文件.. dataSize = " + this.mFileSend.length + ", readSize = " + i);
            try {
                randomAccessFile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return true;
        } catch (Exception e3) {
            e = e3;
            randomAccessFile2 = randomAccessFile;
            e.printStackTrace();
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public void executeFileInit(String str, int i) {
        try {
            setBleOperateManagerCallback();
            XLog.i("executeFileInit.. 开始");
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            byte[] bArr = new byte[bytes.length + 10];
            bArr[0] = 1;
            System.arraycopy(DataTransferUtils.intToBytes(this.mFileSend.length), 0, bArr, 1, 4);
            bArr[9] = (byte) bytes.length;
            System.arraycopy(bytes, 0, bArr, 10, bytes.length);
            sendPocketToBle(addHeader(i, bArr));
            XLog.i("executeFileInit.. 完成");
        } catch (Exception e) {
            XLog.i("executeFileInit.. Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeFileSend(int i) throws Throwable {
        setBleOperateManagerCallback();
        this.mPocketIndex = (short) 0;
        XLog.i("executeFileSend.. 开始发送数据，数据长度: " + this.mFileSend.length);
        executeNextSend(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean executeNextSend(int i) throws Throwable {
        try {
            setBleOperateManagerCallback();
            short s = this.mPocketIndex;
            int i2 = s * 1024;
            byte[] bArr = this.mFileSend;
            if (i2 < bArr.length) {
                int iMin = Math.min(1024, bArr.length - (s * 1024));
                byte[] bArr2 = new byte[iMin];
                System.arraycopy(this.mFileSend, this.mPocketIndex * 1024, bArr2, 0, iMin);
                byte[] bArrCompress = CompressUtils.compress(bArr2);
                byte[] bArr3 = new byte[bArrCompress.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bArr3, 0, 2);
                System.arraycopy(bArrCompress, 0, bArr3, 2, bArrCompress.length);
                sendPocketToBle(addHeader(i, bArr3));
                this.mPocketIndex = (short) (this.mPocketIndex + 1);
                if (this.notDataString.equalsIgnoreCase(ByteUtil.byteArrayToString(bArr2)) && i == 55) {
                    this.noDataCount++;
                    LogToFile.getInstance().wtf("表盘下发异常，全为0的数据" + this.noDataCount);
                    if (this.noDataCount > 30) {
                        XLog.i("表盘下发异常，全为0的数据");
                        return false;
                    }
                } else {
                    this.noDataCount = 0;
                }
                return true;
            }
            XLog.i("文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            XLog.i("文件发送异常: " + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeFileFinished(int i) throws InterruptedException {
        setBleOperateManagerCallback();
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(i, null), this.mPackageLength));
    }

    public void executeFileDelete(String str) throws InterruptedException {
        setBleOperateManagerCallback();
        XLog.i("executeFileDelete.. name: " + str);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        XLog.i("executeFileDelete.. fileNames: " + DataTransferUtils.getHexString(bytes));
        byte[] bArr = new byte[bytes.length + 1];
        bArr[0] = 1;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        XLog.i("executeFileDelete.. data: " + DataTransferUtils.getHexString(bArr));
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(57, bArr), this.mPackageLength));
    }

    public void executeMusicSend(boolean z, int i, int i2, String str) throws InterruptedException {
        XLog.i("executeMusicSend.. playing: " + z + ", progress: " + i + ", volume: " + i2 + ", name: " + str);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        sb.append("executeMusicSend.. nameBytes: ");
        sb.append(DataTransferUtils.getHexString(bytes));
        XLog.i(sb.toString());
        byte[] bArr = new byte[bytes.length + 3];
        bArr[0] = (byte) (!z ? 1 : 0);
        bArr[1] = (byte) i;
        bArr[2] = (byte) i2;
        System.arraycopy(bytes, 0, bArr, 3, bytes.length);
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(6, bArr), this.mPackageLength));
    }

    public void cmdFileInit(String str) throws UnsupportedEncodingException {
        try {
            setBleOperateManagerCallback();
            byte[] bytes = str.getBytes(Key.STRING_CHARSET_NAME);
            byte[] bArr = new byte[bytes.length + 10];
            bArr[0] = 1;
            System.arraycopy(DataTransferUtils.intToBytes(this.mFileSend.length), 0, bArr, 1, 4);
            bArr[9] = (byte) bytes.length;
            System.arraycopy(bytes, 0, bArr, 10, bytes.length);
            sendPocketToBle(addHeader(49, bArr));
            XLog.i("cmdFileInit.. 完成");
        } catch (Exception e) {
            XLog.i("cmdFileInit.. Exception: " + e.getMessage());
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
            setBleOperateManagerCallback();
            short s = this.mPocketIndex;
            int i = s * 1024;
            byte[] bArr = this.mFileSend;
            if (i < bArr.length) {
                int iMin = Math.min(1024, bArr.length - (s * 1024));
                byte[] bArr2 = new byte[iMin];
                System.arraycopy(this.mFileSend, this.mPocketIndex * 1024, bArr2, 0, iMin);
                byte[] bArrCompress = CompressUtils.compress(bArr2);
                byte[] bArr3 = new byte[bArrCompress.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bArr3, 0, 2);
                System.arraycopy(bArrCompress, 0, bArr3, 2, bArrCompress.length);
                sendPocketToBle(addHeader(50, bArr3));
                this.mPocketIndex = (short) (this.mPocketIndex + 1);
                return true;
            }
            XLog.i("文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            XLog.i("文件发送异常: " + e.getMessage());
            return false;
        }
    }

    public void cmdSendPacket() {
        setBleOperateManagerCallback();
        this.mPocketIndex = (short) 0;
        if (this.mFileSend == null) {
            return;
        }
        XLog.i("cmdSendPacket.. 开始发送数据，数据长度: " + this.mFileSend.length);
        readNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readNextBigPocket() {
        try {
            setBleOperateManagerCallback();
            short s = this.mPocketIndex;
            int i = s * 1024;
            byte[] bArr = this.mFileSend;
            if (i < bArr.length) {
                int iMin = Math.min(1024, bArr.length - (s * 1024));
                byte[] bArr2 = new byte[iMin];
                System.arraycopy(this.mFileSend, this.mPocketIndex * 1024, bArr2, 0, iMin);
                byte[] bArrCompress = CompressUtils.compress(bArr2);
                byte[] bArr3 = new byte[bArrCompress.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bArr3, 0, 2);
                System.arraycopy(bArrCompress, 0, bArr3, 2, bArrCompress.length);
                sendPocketToBle(addHeader(50, bArr3));
                this.mPocketIndex = (short) (this.mPocketIndex + 1);
                return true;
            }
            XLog.i("文件发送完毕");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            XLog.i("文件发送异常: " + e.getMessage());
            return false;
        }
    }

    private void sendPocketToBle(byte[] bArr) throws InterruptedException {
        setBleOperateManagerCallback();
        resetPackageLength();
        BleThreadManager.getInstance().addData(new BleDataBean(bArr, this.mPackageLength));
    }

    private void resetPackageLength() {
        this.mPackageLength = JPackageManager.getInstance().getLength();
        XLog.i("resetPackageLength.. mPackageLength: " + this.mPackageLength);
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

    public WriteRequest getWriteRequest(byte[] bArr) {
        XLog.i("getWriteRequest: data=" + DataTransferUtils.getHexString(bArr));
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_WRITE);
        noRspInstance.setValue(bArr);
        return noRspInstance;
    }

    public static String getMD5Three(String str) throws NoSuchAlgorithmException, IOException {
        BigInteger bigInteger;
        try {
            byte[] bArr = new byte[8192];
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i);
            }
            fileInputStream.close();
            bigInteger = new BigInteger(1, messageDigest.digest());
        } catch (IOException e) {
            e.printStackTrace();
            bigInteger = null;
            return bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            bigInteger = null;
            return bigInteger.toString(16);
        }
        return bigInteger.toString(16);
    }
}
