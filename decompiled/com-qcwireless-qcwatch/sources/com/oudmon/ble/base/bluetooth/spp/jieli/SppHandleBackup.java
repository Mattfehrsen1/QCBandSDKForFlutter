package com.oudmon.ble.base.bluetooth.spp.jieli;

import android.bluetooth.BluetoothDevice;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.bluetooth.spp.SerialListener;
import com.oudmon.ble.base.bluetooth.spp.SerialSocket;
import com.oudmon.ble.base.communication.CompressUtils;
import com.oudmon.ble.base.communication.file.ICallback;
import com.oudmon.ble.base.communication.file.IEbookCallback;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes3.dex */
public class SppHandleBackup {
    public static int FileTypeMuSic = 3;
    public static int PACKAGE_LENGTH = 512;
    private static final String TAG = "SppHandle";
    private static SppHandleBackup mInstance;
    private byte[] mFileSend;
    private byte[] mFileSendA;
    private byte[] mFileSendB;
    private int mPackageLength;
    private byte[] mReceivedData;
    private boolean mReceiving;
    private SerialListener outSerialListener;
    private ArrayList<String> fileNames = new ArrayList<>();
    private CopyOnWriteArraySet<IEbookCallback> mCallbackArray = new CopyOnWriteArraySet<>();
    private int currFileType = 0;
    private int mTotalCount = 0;
    private int mReceivedCount = 0;
    private short mPocketIndex = 0;
    private short mPocketIndexA = 0;
    private short mPocketIndexB = 0;
    private int totalSize = 1;
    private int sizeA = 1;
    private int sizeB = 1;
    private SerialListener serialListener = new SerialListener() { // from class: com.oudmon.ble.base.bluetooth.spp.jieli.SppHandleBackup.1
        @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
        public void onSerialRead(ArrayDeque<byte[]> arrayDeque) {
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
        public void onSerialConnect() {
            if (SppHandleBackup.this.outSerialListener != null) {
                SppHandleBackup.this.outSerialListener.onSerialConnect();
            }
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
        public void onSerialConnectError(Exception exc) {
            if (SppHandleBackup.this.outSerialListener != null) {
                SppHandleBackup.this.outSerialListener.onSerialConnectError(exc);
            }
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
        public void onSerialRead(byte[] bArr) throws NumberFormatException, IOException {
            if (bArr != null) {
                if ((bArr[0] & 255) == 188 && bArr[1] == 49) {
                    XLog.i("初始化完成，开始向手环发送实际文件");
                    SppHandleBackup.this.cmdSendPacket();
                    return;
                }
                if ((bArr[0] & 255) == 188 && bArr[1] == 50) {
                    XLog.i(ByteUtil.byteArrayToString(bArr));
                    int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7));
                    if (iBytesToInt == 0) {
                        if (SppHandleBackup.this.readNextBigPocket()) {
                            float f = Float.parseFloat(new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US)).format((((SppHandleBackup.this.mPocketIndex * 1024) * 1.0f) * 100.0f) / SppHandleBackup.this.totalSize));
                            XLog.i("向手环发送数据进度: " + f + ", 包序: " + ((int) SppHandleBackup.this.mPocketIndex) + "总包:" + SppHandleBackup.this.totalSize);
                            SppHandleBackup.this.mCallback.onProgress(Math.min(f, 100.0f));
                            return;
                        }
                        XLog.i("向手环发送数据完毕, 包序: " + ((int) SppHandleBackup.this.mPocketIndex));
                        SppHandleBackup.this.cmdCheck();
                        return;
                    }
                    SppHandleBackup.this.mCallback.onDeleteSuccess(iBytesToInt);
                    return;
                }
                if ((bArr[0] & 255) == 188 && bArr[1] == 51) {
                    XLog.i("===============回调 onComplete");
                    SppHandleBackup.this.mFileSend = new byte[0];
                    SppHandleBackup.this.mCallback.onComplete();
                    return;
                }
                if ((bArr[0] & 255) != 188 || bArr[1] != Byte.MIN_VALUE) {
                    if (SppHandleBackup.this.mReceiving) {
                        System.arraycopy(bArr, 0, SppHandleBackup.this.mReceivedData, SppHandleBackup.this.mReceivedCount, bArr.length);
                        SppHandleBackup.access$812(SppHandleBackup.this, bArr.length);
                        SppHandleBackup sppHandleBackup = SppHandleBackup.this;
                        sppHandleBackup.mReceiving = sppHandleBackup.mReceivedCount < SppHandleBackup.this.mTotalCount;
                        if (SppHandleBackup.this.mReceiving) {
                            return;
                        }
                        int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 1, 2));
                        if (ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 2, 3)) == 0) {
                            SppHandleBackup.this.fileNames = new ArrayList();
                        }
                        XLog.i(Integer.valueOf(iBytesToInt2));
                        if (iBytesToInt2 > 0) {
                            int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 4, 5));
                            SppHandleBackup sppHandleBackup2 = SppHandleBackup.this;
                            sppHandleBackup2.parseEbookData(sppHandleBackup2.mReceivedData, 5, iBytesToInt3, iBytesToInt2, 0);
                            return;
                        }
                        SppHandleBackup.this.mCallback.onFileNames(SppHandleBackup.this.fileNames);
                        return;
                    }
                    if ((bArr[0] & 255) == 188 && bArr[1] == -127) {
                        SppHandleBackup.this.mCallback.onDeleteSuccess(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)));
                        return;
                    }
                    return;
                }
                SppHandleBackup.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                SppHandleBackup.this.mReceivedCount = bArr.length - 6;
                SppHandleBackup sppHandleBackup3 = SppHandleBackup.this;
                sppHandleBackup3.mReceivedData = new byte[sppHandleBackup3.mTotalCount];
                System.arraycopy(bArr, 6, SppHandleBackup.this.mReceivedData, 0, SppHandleBackup.this.mReceivedCount);
                SppHandleBackup sppHandleBackup4 = SppHandleBackup.this;
                sppHandleBackup4.mReceiving = sppHandleBackup4.mReceivedCount < SppHandleBackup.this.mTotalCount;
                if (SppHandleBackup.this.mReceiving) {
                    return;
                }
                int iBytesToInt4 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 1, 2));
                if (ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 2, 3)) == 0) {
                    SppHandleBackup.this.fileNames = new ArrayList();
                }
                XLog.i(Integer.valueOf(iBytesToInt4));
                if (iBytesToInt4 > 0) {
                    int iBytesToInt5 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 4, 5));
                    SppHandleBackup sppHandleBackup5 = SppHandleBackup.this;
                    sppHandleBackup5.parseEbookData(sppHandleBackup5.mReceivedData, 5, iBytesToInt5, iBytesToInt4, 0);
                    return;
                }
                SppHandleBackup.this.mCallback.onFileNames(SppHandleBackup.this.fileNames);
            }
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
        public void onSerialIoError(Exception exc) {
            if (SppHandleBackup.this.outSerialListener != null) {
                SppHandleBackup.this.outSerialListener.onSerialIoError(exc);
            }
        }
    };
    private IEbookCallback mCallback = new IEbookCallback() { // from class: com.oudmon.ble.base.bluetooth.spp.jieli.SppHandleBackup.2
        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onFileNames(ArrayList<String> arrayList) {
            Iterator it = SppHandleBackup.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onFileNames(arrayList);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onProgress(float f) {
            Iterator it = SppHandleBackup.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onProgress(f);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onComplete() {
            Iterator it = SppHandleBackup.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onComplete();
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onDeleteSuccess(int i) {
            Iterator it = SppHandleBackup.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onDeleteSuccess(i);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.IEbookCallback
        public void onActionResult(int i) {
            Iterator it = SppHandleBackup.this.mCallbackArray.iterator();
            while (it.hasNext()) {
                ((IEbookCallback) it.next()).onActionResult(i);
            }
        }
    };
    private OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.bluetooth.spp.jieli.SppHandleBackup.3
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String str, byte[] bArr) throws NumberFormatException, IOException {
            if (bArr != null) {
                if ((bArr[0] & 255) == 188 && bArr[1] == 49) {
                    XLog.i("初始化完成，开始向手环发送实际文件");
                    SppHandleBackup.this.cmdSendPacket();
                    return;
                }
                if ((bArr[0] & 255) == 188 && bArr[1] == 50) {
                    int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7));
                    if (iBytesToInt == 0) {
                        if (SppHandleBackup.this.readNextBigPocket()) {
                            float f = Float.parseFloat(new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US)).format((((SppHandleBackup.this.mPocketIndex * 1024) * 1.0f) * 100.0f) / SppHandleBackup.this.totalSize));
                            XLog.i("向手环发送数据进度: " + f + ", 包序: " + ((int) SppHandleBackup.this.mPocketIndex) + "总包:" + SppHandleBackup.this.totalSize);
                            SppHandleBackup.this.mCallback.onProgress(Math.min(f, 100.0f));
                            return;
                        }
                        XLog.i("向手环发送数据完毕, 包序: " + ((int) SppHandleBackup.this.mPocketIndex));
                        SppHandleBackup.this.cmdCheck();
                        return;
                    }
                    SppHandleBackup.this.mCallback.onDeleteSuccess(iBytesToInt);
                    return;
                }
                if ((bArr[0] & 255) == 188 && bArr[1] == 51) {
                    XLog.i("===============回调 onComplete");
                    SppHandleBackup.this.mFileSend = new byte[0];
                    SppHandleBackup.this.mCallback.onComplete();
                    return;
                }
                if ((bArr[0] & 255) != 188 || bArr[1] != Byte.MIN_VALUE) {
                    if (SppHandleBackup.this.mReceiving) {
                        System.arraycopy(bArr, 0, SppHandleBackup.this.mReceivedData, SppHandleBackup.this.mReceivedCount, bArr.length);
                        SppHandleBackup.access$812(SppHandleBackup.this, bArr.length);
                        SppHandleBackup sppHandleBackup = SppHandleBackup.this;
                        sppHandleBackup.mReceiving = sppHandleBackup.mReceivedCount < SppHandleBackup.this.mTotalCount;
                        XLog.i("文件：->3 mTotalCount: " + SppHandleBackup.this.mTotalCount + ", mReceivedCount: " + SppHandleBackup.this.mReceivedCount + ", mReceiving: " + SppHandleBackup.this.mReceiving);
                        if (SppHandleBackup.this.mReceiving) {
                            return;
                        }
                        XLog.i("文件：->4 ->" + DataTransferUtils.getHexString(SppHandleBackup.this.mReceivedData));
                        int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 1, 2));
                        if (ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 2, 3)) == 0) {
                            SppHandleBackup.this.fileNames = new ArrayList();
                        }
                        XLog.i(Integer.valueOf(iBytesToInt2));
                        if (iBytesToInt2 > 0) {
                            int iBytesToInt3 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 4, 5));
                            SppHandleBackup sppHandleBackup2 = SppHandleBackup.this;
                            sppHandleBackup2.parseEbookData(sppHandleBackup2.mReceivedData, 5, iBytesToInt3, iBytesToInt2, 0);
                            return;
                        }
                        SppHandleBackup.this.mCallback.onFileNames(SppHandleBackup.this.fileNames);
                        return;
                    }
                    if ((bArr[0] & 255) == 188 && bArr[1] == -127) {
                        SppHandleBackup.this.mCallback.onDeleteSuccess(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)));
                        return;
                    }
                    return;
                }
                SppHandleBackup.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                SppHandleBackup.this.mReceivedCount = bArr.length - 6;
                SppHandleBackup sppHandleBackup3 = SppHandleBackup.this;
                sppHandleBackup3.mReceivedData = new byte[sppHandleBackup3.mTotalCount];
                System.arraycopy(bArr, 6, SppHandleBackup.this.mReceivedData, 0, SppHandleBackup.this.mReceivedCount);
                SppHandleBackup sppHandleBackup4 = SppHandleBackup.this;
                sppHandleBackup4.mReceiving = sppHandleBackup4.mReceivedCount < SppHandleBackup.this.mTotalCount;
                XLog.i("文件：-> 1mTotalCount: " + SppHandleBackup.this.mTotalCount + ", mReceivedCount: " + SppHandleBackup.this.mReceivedCount + ", mReceiving: " + SppHandleBackup.this.mReceiving);
                if (SppHandleBackup.this.mReceiving) {
                    return;
                }
                XLog.i("文件：->2->" + DataTransferUtils.getHexString(SppHandleBackup.this.mReceivedData));
                int iBytesToInt4 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 1, 2));
                if (ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 2, 3)) == 0) {
                    SppHandleBackup.this.fileNames = new ArrayList();
                }
                XLog.i(Integer.valueOf(iBytesToInt4));
                if (iBytesToInt4 > 0) {
                    int iBytesToInt5 = ByteUtil.bytesToInt(Arrays.copyOfRange(SppHandleBackup.this.mReceivedData, 4, 5));
                    SppHandleBackup sppHandleBackup5 = SppHandleBackup.this;
                    sppHandleBackup5.parseEbookData(sppHandleBackup5.mReceivedData, 5, iBytesToInt5, iBytesToInt4, 0);
                    return;
                }
                SppHandleBackup.this.mCallback.onFileNames(SppHandleBackup.this.fileNames);
            }
        }
    };
    boolean mPocketIndexAFlag = false;

    public void endAndRelease() {
    }

    static /* synthetic */ int access$812(SppHandleBackup sppHandleBackup, int i) {
        int i2 = sppHandleBackup.mReceivedCount + i;
        sppHandleBackup.mReceivedCount = i2;
        return i2;
    }

    public int getCurrFileType() {
        return this.currFileType;
    }

    public void setCurrFileType(int i) {
        this.currFileType = i;
    }

    public void setOutSerialListener(SerialListener serialListener) {
        this.outSerialListener = serialListener;
    }

    public static SppHandleBackup getInstance() {
        if (mInstance == null) {
            synchronized (SppHandleBackup.class) {
                if (mInstance == null) {
                    mInstance = new SppHandleBackup();
                }
            }
        }
        return mInstance;
    }

    private SppHandleBackup() {
        this.mPackageLength = 512;
        this.mPackageLength = PACKAGE_LENGTH;
        XLog.i("create FileHandle.. mPackageLength: " + this.mPackageLength);
    }

    public void initRegister() {
        registerMusicBleCallback();
        SerialSocket.getInstance().setListener(this.serialListener);
    }

    public void registerMusicBleCallback() {
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public void connect(BluetoothDevice bluetoothDevice) {
        SerialSocket.getInstance().setDevice(bluetoothDevice);
        SerialSocket.getInstance().connect(this.serialListener);
    }

    public void disconnect() throws IOException {
        SerialSocket.getInstance().disconnect();
    }

    public boolean isConnected() {
        return SerialSocket.getInstance().isConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseEbookData(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i4 + 1;
        int i6 = i2 + i;
        try {
            this.fileNames.add(unicodeByteToStr(Arrays.copyOfRange(bArr, i, i6)));
            if (i5 < i3) {
                parseEbookData(bArr, i6 + 1, bArr[i6], i3, i5);
            } else {
                this.mCallback.onFileNames(this.fileNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void start(int i) throws IOException {
        SerialSocket.getInstance().write(addHeader(128, new byte[]{1, (byte) i}));
    }

    public void deleteMusic(int i, String str) {
        try {
            byte[] bytes = str.getBytes(Charset.forName("unicode"));
            if (!ByteUtil.byteArrayToString(bytes).startsWith("fffe")) {
                int i2 = 0;
                while (i2 < bytes.length) {
                    int i3 = i2 + 2;
                    int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bytes, i2, i3));
                    bytes[i2] = (byte) ByteUtil.hiword(iBytesToInt);
                    bytes[i2 + 1] = (byte) ByteUtil.loword(iBytesToInt);
                    i2 = i3;
                }
            }
            SerialSocket.getInstance().write(addHeader(129, ByteUtil.concat(new byte[]{1, (byte) i, (byte) bytes.length}, bytes)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x01b8 A[Catch: IOException -> 0x01d9, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x01d9, blocks: (B:59:0x01b8, B:76:0x01d5), top: B:89:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean executeFilePrepare(java.lang.String r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oudmon.ble.base.bluetooth.spp.jieli.SppHandleBackup.executeFilePrepare(java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0053 A[Catch: Exception -> 0x0095, LOOP:0: B:28:0x0053->B:72:0x0053, LOOP_START, TryCatch #0 {Exception -> 0x0095, blocks: (B:3:0x0005, B:5:0x001a, B:7:0x001e, B:10:0x0026, B:26:0x004e, B:28:0x0053, B:41:0x006f, B:49:0x0080, B:52:0x0088, B:56:0x0091, B:14:0x002e, B:16:0x0032, B:19:0x0039, B:21:0x003f, B:23:0x0045), top: B:61:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String charset(java.lang.String r8) throws java.io.IOException {
        /*
            java.lang.String r0 = "GBK"
            r1 = 3
            byte[] r2 = new byte[r1]
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Exception -> L95
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L95
            r4.<init>(r8)     // Catch: java.lang.Exception -> L95
            r3.<init>(r4)     // Catch: java.lang.Exception -> L95
            r8 = 0
            r3.mark(r8)     // Catch: java.lang.Exception -> L95
            int r1 = r3.read(r2, r8, r1)     // Catch: java.lang.Exception -> L95
            r4 = -1
            if (r1 != r4) goto L1e
            r3.close()     // Catch: java.lang.Exception -> L95
            return r0
        L1e:
            r1 = r2[r8]     // Catch: java.lang.Exception -> L95
            java.lang.String r5 = "UTF-8"
            r6 = -2
            r7 = 1
            if (r1 != r4) goto L2e
            r1 = r2[r7]     // Catch: java.lang.Exception -> L95
            if (r1 != r6) goto L2e
            java.lang.String r0 = "UTF-16LE"
        L2c:
            r8 = 1
            goto L4e
        L2e:
            r1 = r2[r8]     // Catch: java.lang.Exception -> L95
            if (r1 != r6) goto L39
            r1 = r2[r7]     // Catch: java.lang.Exception -> L95
            if (r1 != r4) goto L39
            java.lang.String r0 = "UTF-16BE"
            goto L2c
        L39:
            r1 = r2[r8]     // Catch: java.lang.Exception -> L95
            r6 = -17
            if (r1 != r6) goto L4e
            r1 = r2[r7]     // Catch: java.lang.Exception -> L95
            r6 = -69
            if (r1 != r6) goto L4e
            r1 = 2
            r1 = r2[r1]     // Catch: java.lang.Exception -> L95
            r2 = -65
            if (r1 != r2) goto L4e
            r0 = r5
            goto L2c
        L4e:
            r3.reset()     // Catch: java.lang.Exception -> L95
            if (r8 != 0) goto L91
        L53:
            int r8 = r3.read()     // Catch: java.lang.Exception -> L95
            if (r8 == r4) goto L91
            r1 = 240(0xf0, float:3.36E-43)
            if (r8 < r1) goto L5e
            goto L91
        L5e:
            r1 = 191(0xbf, float:2.68E-43)
            r2 = 128(0x80, float:1.794E-43)
            if (r2 > r8) goto L67
            if (r8 > r1) goto L67
            goto L91
        L67:
            r6 = 192(0xc0, float:2.69E-43)
            if (r6 > r8) goto L78
            r6 = 223(0xdf, float:3.12E-43)
            if (r8 > r6) goto L78
            int r8 = r3.read()     // Catch: java.lang.Exception -> L95
            if (r2 > r8) goto L91
            if (r8 > r1) goto L91
            goto L53
        L78:
            r6 = 224(0xe0, float:3.14E-43)
            if (r6 > r8) goto L53
            r6 = 239(0xef, float:3.35E-43)
            if (r8 > r6) goto L53
            int r8 = r3.read()     // Catch: java.lang.Exception -> L95
            if (r2 > r8) goto L91
            if (r8 > r1) goto L91
            int r8 = r3.read()     // Catch: java.lang.Exception -> L95
            if (r2 > r8) goto L91
            if (r8 > r1) goto L91
            r0 = r5
        L91:
            r3.close()     // Catch: java.lang.Exception -> L95
            goto L99
        L95:
            r8 = move-exception
            r8.printStackTrace()
        L99:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oudmon.ble.base.bluetooth.spp.jieli.SppHandleBackup.charset(java.lang.String):java.lang.String");
    }

    public void cmdFileInit(String str) {
        try {
            byte[] bytes = str.getBytes(Charset.forName("unicode"));
            if (!ByteUtil.byteArrayToString(bytes).startsWith("fffe")) {
                int i = 0;
                while (i < bytes.length) {
                    int i2 = i + 2;
                    int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bytes, i, i2));
                    bytes[i] = (byte) ByteUtil.hiword(iBytesToInt);
                    bytes[i + 1] = (byte) ByteUtil.loword(iBytesToInt);
                    i = i2;
                }
            }
            XLog.i(ByteUtil.byteArrayToString(bytes));
            byte[] bArr = new byte[bytes.length + 10];
            bArr[0] = (byte) this.currFileType;
            System.arraycopy(DataTransferUtils.intToBytes(this.totalSize), 0, bArr, 1, 4);
            bArr[9] = (byte) bytes.length;
            System.arraycopy(bytes, 0, bArr, 10, bytes.length);
            sendPocketToBle(addHeader(49, bArr));
            XLog.i("cmdFileInit.. 完成");
        } catch (Exception e) {
            XLog.i("cmdFileInit.. Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cmdSendPacket() {
        if (this.mFileSend == null) {
            return;
        }
        XLog.i("cmdSendPacket.. 开始发送数据，数据长度: " + this.totalSize);
        readNextBigPocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readNextBigPocket() {
        try {
            if (this.mPocketIndex * 1024 < this.totalSize) {
                if (this.mFileSend.length > 0) {
                    XLog.i("next pkg");
                    int iMin = Math.min(1024, this.totalSize - (this.mPocketIndex * 1024));
                    byte[] bArr = new byte[iMin];
                    System.arraycopy(this.mFileSend, this.mPocketIndex * 1024, bArr, 0, iMin);
                    byte[] bArrCompress = CompressUtils.compress(bArr);
                    byte[] bArr2 = new byte[bArrCompress.length + 2];
                    System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndex + 1)), 0, bArr2, 0, 2);
                    System.arraycopy(bArrCompress, 0, bArr2, 2, bArrCompress.length);
                    sendPocketToBle(addHeader(50, bArr2));
                    this.mPocketIndex = (short) (this.mPocketIndex + 1);
                    return true;
                }
                short s = this.mPocketIndexA;
                int i = s * 1024;
                int i2 = this.sizeA;
                if (i < i2) {
                    int iMin2 = Math.min(1024, i2 - (s * 1024));
                    byte[] bArr3 = new byte[iMin2];
                    System.arraycopy(this.mFileSendA, this.mPocketIndexA * 1024, bArr3, 0, iMin2);
                    byte[] bArrCompress2 = CompressUtils.compress(bArr3);
                    byte[] bArr4 = new byte[bArrCompress2.length + 2];
                    System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndexA + 1)), 0, bArr4, 0, 2);
                    System.arraycopy(bArrCompress2, 0, bArr4, 2, bArrCompress2.length);
                    sendPocketToBle(addHeader(50, bArr4));
                    short s2 = (short) (this.mPocketIndexA + 1);
                    this.mPocketIndexA = s2;
                    this.mPocketIndex = (short) (s2 + this.mPocketIndexB);
                    return true;
                }
                int iMin3 = Math.min(1024, this.sizeB - (this.mPocketIndexB * 1024));
                byte[] bArr5 = new byte[iMin3];
                System.arraycopy(this.mFileSendB, this.mPocketIndexB * 1024, bArr5, 0, iMin3);
                byte[] bArrCompress3 = CompressUtils.compress(bArr5);
                byte[] bArr6 = new byte[bArrCompress3.length + 2];
                System.arraycopy(DataTransferUtils.shortToBytes((short) (this.mPocketIndexA + this.mPocketIndexB + 1)), 0, bArr6, 0, 2);
                System.arraycopy(bArrCompress3, 0, bArr6, 2, bArrCompress3.length);
                sendPocketToBle(addHeader(50, bArr6));
                short s3 = (short) (this.mPocketIndexB + 1);
                this.mPocketIndexB = s3;
                this.mPocketIndex = (short) (this.mPocketIndexA + s3);
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

    private void sendPocketToBle(byte[] bArr) throws IOException {
        resetPackageLength();
        for (int i = 0; this.mPackageLength * i < bArr.length; i++) {
            SerialSocket serialSocket = SerialSocket.getInstance();
            int i2 = this.mPackageLength;
            serialSocket.write(Arrays.copyOfRange(bArr, i * i2, (i * i2) + Math.min(i2, bArr.length - (i * i2))));
        }
    }

    private void resetPackageLength() {
        this.mPackageLength = PACKAGE_LENGTH;
        XLog.i("resetPackageLength.. mPackageLength: " + this.mPackageLength);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cmdCheck() throws IOException {
        SerialSocket.getInstance().write(addHeader(51, null));
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

    public List<List<byte[]>> subListBySegment(List<byte[]> list, int i) {
        List<byte[]> listSubList;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size > 0 && i > 0) {
            int i2 = size / i;
            for (int i3 = 0; i3 < i; i3++) {
                if (i3 == i - 1) {
                    listSubList = list.subList(i2 * i3, size);
                } else {
                    listSubList = list.subList(i2 * i3, (i3 + 1) * i2);
                }
                arrayList.add(listSubList);
            }
        } else {
            arrayList.add(list);
        }
        return arrayList;
    }
}
