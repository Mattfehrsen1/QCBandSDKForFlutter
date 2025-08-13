package com.oudmon.ble.base.communication.file;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.bluetooth.queue.BleDataBean;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.CompressUtils;
import com.oudmon.ble.base.communication.JPackageManager;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes3.dex */
public class AvatarHandle {
    private static final String TAG = "EbookHandle";
    private static AvatarHandle mInstance;
    private byte[] mFileSend;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private ArrayList<String> fileNames = new ArrayList<>();
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY, new EnableNotifyRequest.ListenerCallback() { // from class: com.oudmon.ble.base.communication.file.AvatarHandle.1
        @Override // com.oudmon.ble.base.request.EnableNotifyRequest.ListenerCallback
        public void enable(boolean z) {
        }
    });
    private CopyOnWriteArraySet<IEbookCallback> mCallbackArray = new CopyOnWriteArraySet<>();
    private int currFileType = 0;
    private IEbookCallback mCallback = new IEbookCallback() { // from class: com.oudmon.ble.base.communication.file.AvatarHandle.2
        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onFileNames(ArrayList<String> arrayList) {
            Iterator it = AvatarHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onFileNames(arrayList);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onProgress(float f) {
            Iterator it = AvatarHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onProgress(f);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onComplete() {
            Iterator it = AvatarHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onComplete();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onDeleteSuccess(int i) {
            Iterator it = AvatarHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onDeleteSuccess(i);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onActionResult(int i) {
            Iterator it = AvatarHandle.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onActionResult(i);
            }
        }
    };
    private short mPocketIndex = 0;
    private int totalCount = 1;
    private int totalSize = 1;
    private OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.file.AvatarHandle.3
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String str, byte[] bArr) throws NumberFormatException {
            if (bArr != null) {
                XLog.i(ByteUtil.byteArrayToString(bArr));
                if ((bArr[0] & 255) == 188 && bArr[1] == 74) {
                    if (AvatarHandle.this.readNextBigPocket()) {
                        float f = Float.parseFloat(new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US)).format((((AvatarHandle.this.mPocketIndex * 1024) * 1.0f) * 100.0f) / AvatarHandle.this.totalSize));
                        XLog.i("向手环发送数据进度: " + f + ", 包序: " + ((int) AvatarHandle.this.mPocketIndex) + "总包:" + AvatarHandle.this.totalSize);
                        AvatarHandle.this.mCallback.onProgress(Math.min(f, 100.0f));
                        return;
                    }
                    AvatarHandle.this.mCallback.onComplete();
                    XLog.i("向手环发送数据完毕, 包序: " + ((int) AvatarHandle.this.mPocketIndex));
                }
            }
        }
    };
    private int mPackageLength = JPackageManager.getInstance().getLength();

    public int getCurrFileType() {
        return this.currFileType;
    }

    public void setCurrFileType(int i) {
        this.currFileType = i;
    }

    public static AvatarHandle getInstance() {
        if (mInstance == null) {
            synchronized (AvatarHandle.class) {
                if (mInstance == null) {
                    mInstance = new AvatarHandle();
                }
            }
        }
        return mInstance;
    }

    private AvatarHandle() {
        XLog.i("create FileHandle.. mPackageLength: " + this.mPackageLength);
    }

    public void initRegister() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public void setBleOperateManagerCallback() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public String unicodeByteToStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 2;
            sb.append((char) ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, i, i2)));
            i = i2;
        }
        return sb.toString();
    }

    public void registerCallback(IEbookCallback iEbookCallback) {
        try {
            this.mCallbackArray.remove(iEbookCallback);
            this.mCallbackArray.add(iEbookCallback);
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

    public void cmdSendPacket() {
        setBleOperateManagerCallback();
        this.mPocketIndex = (short) 0;
        if (this.mFileSend == null) {
            return;
        }
        XLog.i("cmdSendPacket.. 开始发送数据，数据长度: " + this.totalSize);
        readNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readNextBigPocket() {
        try {
            setBleOperateManagerCallback();
            short s = this.mPocketIndex;
            int i = s * 1024;
            int i2 = this.totalSize;
            if (i < i2) {
                if (this.mFileSend.length > 0) {
                    int iMin = Math.min(1024, i2 - (s * 1024));
                    byte[] bArr = new byte[iMin];
                    System.arraycopy(this.mFileSend, this.mPocketIndex * 1024, bArr, 0, iMin);
                    byte[] bArrCompress = CompressUtils.compress(bArr);
                    byte[] bArr2 = new byte[bArrCompress.length + 3];
                    System.arraycopy(new byte[]{(byte) this.totalCount, (byte) (this.mPocketIndex + 1), 1}, 0, bArr2, 0, 3);
                    System.arraycopy(bArrCompress, 0, bArr2, 3, bArrCompress.length);
                    sendPocketToBle(addHeader(74, bArr2));
                    this.mPocketIndex = (short) (this.mPocketIndex + 1);
                    return true;
                }
            } else {
                XLog.i("文件发送完毕");
            }
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

    public boolean checkData(byte[] bArr) {
        this.mFileSend = bArr;
        this.totalSize = bArr.length;
        int length = bArr.length;
        int i = length / 1024;
        this.totalCount = i;
        if (length % 1024 != 0) {
            this.totalCount = i + 1;
        }
        setBleOperateManagerCallback();
        XLog.i("总包数: " + this.totalCount);
        return true;
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
}
