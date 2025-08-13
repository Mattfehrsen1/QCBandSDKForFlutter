package com.realsil.sdk.audioconnect.localplayback;

import android.content.Context;
import android.os.Bundle;
import com.realsil.sdk.bbpro.core.BeeError;
import com.realsil.sdk.bbpro.core.transportlayer.AckPacket;
import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.core.transportlayer.TransportLayerPacket;
import com.realsil.sdk.bbpro.internal.ModelClient;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class LocalPlaybackModelClient extends ModelClient<LocalPlaybackModelCallback> {
    public static volatile LocalPlaybackModelClient a;

    public LocalPlaybackModelClient(Context context) {
        super(context);
    }

    public static LocalPlaybackModelClient getInstance() {
        if (a == null) {
            ZLogger.w("please call setup(Context, BeeProManager) first");
        }
        return a;
    }

    public static void initialize(Context context) {
        if (a == null) {
            synchronized (LocalPlaybackModelClient.class) {
                if (a == null) {
                    a = new LocalPlaybackModelClient(context.getApplicationContext());
                }
            }
        }
    }

    public BeeError addDeleteSongToPlaylist(int i, int i2, byte[] bArr) {
        if (bArr == null) {
            ZLogger.e(ModelClient.DBG, "song name cannot be empty");
            return new BeeError(48);
        }
        if (bArr.length == 0) {
            ZLogger.e(ModelClient.DBG, "song name length cannot be 0");
            return new BeeError(48);
        }
        byte[] bArr2 = new byte[bArr.length + 6];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferWrap.putShort(0, (short) i);
        byteBufferWrap.putShort(2, (short) i2);
        byteBufferWrap.putShort(4, (short) bArr.length);
        System.arraycopy(bArr, 0, bArr2, 6, bArr.length);
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 1674, bArr2).eventId((short) 1673).build());
    }

    public BeeError cancelTransfer() {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 1671, new byte[0]).eventId((short) 1670).build());
    }

    public BeeError deleteAllSongFromDevice() {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 1676, new byte[0]).eventId((short) 1675).build());
    }

    public BeeError deleteSingleSongFromDevice(int i, byte[] bArr) {
        if (bArr == null) {
            ZLogger.e(ModelClient.DBG, "song name cannot be empty");
            return new BeeError(48);
        }
        if (bArr.length == 0) {
            ZLogger.e(ModelClient.DBG, "song name length cannot be 0");
            return new BeeError(48);
        }
        byte[] bArr2 = new byte[bArr.length + 4];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferWrap.putShort(0, (short) i);
        byteBufferWrap.putShort(2, (short) bArr.length);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 1673, bArr2).eventId((short) 1672).build());
    }

    public BeeError enterSongTransferMode(File file) {
        if (file == null) {
            ZLogger.e(ModelClient.DBG, "song file can not be null");
            return new BeeError(48);
        }
        if (!file.exists()) {
            ZLogger.e(ModelClient.DBG, "song file not exists");
            return new BeeError(48);
        }
        if (file.length() == 0) {
            ZLogger.e(ModelClient.DBG, "file content cannot be empty");
            return new BeeError(48);
        }
        byte[] bytes = file.getName().getBytes(StandardCharsets.UTF_16LE);
        int length = bytes.length + 2;
        int length2 = (int) file.length();
        ZLogger.w(ModelClient.DBG, "enter transfer mode: [songNameLen: " + length + ", songLen: " + length2 + "]");
        int i = length + 2;
        byte[] bArr = new byte[i + 4];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferWrap.putShort(0, (short) length);
        System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        byteBufferWrap.putInt(i, length2);
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 1666, bArr).eventId((short) 1666).build());
    }

    public BeeError exitSongTransferMode() {
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 1672, new byte[0]).eventId((short) 1671).build());
    }

    public BeeError getFileListData(byte b) {
        return sendVendorData(new Command.Builder().writeType(1).packet((short) 1665, new byte[]{b}).build());
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public int getVendorCmd() {
        return 0;
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public int getVendorEvent() {
        return 0;
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public boolean processAckPacket(AckPacket ackPacket) {
        short toAckId = ackPacket.getToAckId();
        byte status = ackPacket.getStatus();
        switch (toAckId) {
            case 1664:
                if (status == 2 || status == 1) {
                    ZLogger.w("CMD_QUERY_INFO not supported");
                }
                notifyOperationComplete(1, status);
                break;
            case 1666:
                if (status == 2 || status == 1) {
                    ZLogger.w("CMD_TRANS_START not supported");
                }
                notifyOperationComplete(2, status);
                break;
            case 1667:
                if (status == 2 || status == 1) {
                    ZLogger.w("CMD_TRANS_CONTINUE not supported");
                }
                notifyOperationComplete(3, status);
                break;
            case 1669:
                if (status == 2 || status == 1) {
                    ZLogger.w("CMD_VALID_SONG not supported");
                }
                notifyOperationComplete(4, status);
                break;
            case 1671:
                if (status == 2 || status == 1) {
                    ZLogger.w("CMD_TRANS_CANCEL not supported");
                }
                notifyOperationComplete(5, status);
                break;
            case 1672:
                if (status == 2 || status == 1) {
                    ZLogger.w("CMD_TRANS_EXIT not supported");
                }
                notifyOperationComplete(6, status);
                break;
        }
        return true;
    }

    @Override // com.realsil.sdk.bbpro.internal.ModelClient
    public boolean processEventPacket(TransportLayerPacket transportLayerPacket) {
        short opcode = transportLayerPacket.getOpcode();
        byte[] parameters = transportLayerPacket.getParameters();
        transportLayerPacket.getPayload();
        switch (opcode) {
            case 1664:
                if (parameters == null || parameters.length < 32) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    ByteBuffer byteBufferWrap = ByteBuffer.wrap(parameters);
                    byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i = byteBufferWrap.getShort(0) & 65535;
                    int i2 = 65535 & byteBufferWrap.getShort(2);
                    int i3 = byteBufferWrap.get(4) & 255;
                    byte b = byteBufferWrap.get(5);
                    int i4 = b & 1;
                    int i5 = (2 & b) >> 1;
                    byteBufferWrap.get(6);
                    int i6 = byteBufferWrap.get(7) & 255;
                    Bundle bundle = new Bundle();
                    bundle.putInt("com.realsil.android.extra.SEND_PACKET_SIZE", i);
                    bundle.putInt("com.realsil.android.extra.BUFFER_CHECK_SIZE", i2);
                    bundle.putInt("com.realsil.android.extra.PROTOCOL_VERSION", i3);
                    bundle.putInt("com.realsil.android.extra.RWS_STATUS", i4);
                    bundle.putInt("com.realsil.android.extra.BUD_ROLE", i5);
                    bundle.putInt("com.realsil.android.extra.SUPPORT_FORMATS", i6);
                    List<MCB> list = this.mCallbacks;
                    if (list == 0 || list.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it = this.mCallbacks.iterator();
                        while (it.hasNext()) {
                            ((LocalPlaybackModelCallback) it.next()).onGetDeviceInfoReport(bundle);
                        }
                    }
                }
                return true;
            case 1665:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    byte b2 = parameters[0];
                    if (b2 == 0) {
                        ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(parameters);
                        byteBufferWrap2.order(ByteOrder.LITTLE_ENDIAN);
                        int i7 = byteBufferWrap2.getShort(1) & 65535;
                        long j = byteBufferWrap2.getInt(3) & 4294967295L;
                        ZLogger.w(ModelClient.VDBG, "current file crc: " + i7 + ", total length: " + j);
                        List<MCB> list2 = this.mCallbacks;
                        if (list2 != 0 && list2.size() > 0) {
                            Iterator it2 = this.mCallbacks.iterator();
                            while (it2.hasNext()) {
                                ((LocalPlaybackModelCallback) it2.next()).onGetFileHeaderReport(i7, j);
                            }
                        }
                    } else if (b2 == 1 || b2 == 2) {
                        ByteBuffer byteBufferWrap3 = ByteBuffer.wrap(parameters);
                        byteBufferWrap3.order(ByteOrder.LITTLE_ENDIAN);
                        int i8 = byteBufferWrap3.getShort(1) & 65535;
                        ZLogger.w(ModelClient.VDBG, "current file length: " + i8);
                        byte[] bArr = new byte[i8];
                        System.arraycopy(parameters, 3, bArr, 0, i8);
                        List<MCB> list3 = this.mCallbacks;
                        if (list3 != 0 && list3.size() > 0) {
                            for (MCB mcb : this.mCallbacks) {
                                if (b2 == 1) {
                                    mcb.onGetFileContentReport(i8, bArr);
                                } else {
                                    mcb.onGetFileFooterReport(i8, bArr);
                                }
                            }
                        }
                    }
                }
                return true;
            case 1666:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i9 = parameters[0] & 255;
                    List<MCB> list4 = this.mCallbacks;
                    if (list4 == 0 || list4.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it3 = this.mCallbacks.iterator();
                        while (it3.hasNext()) {
                            ((LocalPlaybackModelCallback) it3.next()).onEnterSongTransferModeReport(i9 == 1);
                        }
                    }
                }
                return true;
            case 1667:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i10 = parameters[0] & 255;
                    List<MCB> list5 = this.mCallbacks;
                    if (list5 == 0 || list5.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        for (MCB mcb2 : this.mCallbacks) {
                            if (i10 == 241 || i10 == 242) {
                                mcb2.onWriteSuccessReport(i10);
                            } else {
                                mcb2.onWriteFailedReport();
                            }
                        }
                    }
                }
                return true;
            case 1668:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i11 = parameters[0] & 255;
                    List<MCB> list6 = this.mCallbacks;
                    if (list6 == 0 || list6.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it4 = this.mCallbacks.iterator();
                        while (it4.hasNext()) {
                            ((LocalPlaybackModelCallback) it4.next()).onTransferWasValidReport(i11);
                        }
                    }
                }
                return true;
            case 1669:
            case 1674:
            default:
                return false;
            case 1670:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i12 = parameters[0] & 255;
                    List<MCB> list7 = this.mCallbacks;
                    if (list7 == 0 || list7.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it5 = this.mCallbacks.iterator();
                        while (it5.hasNext()) {
                            ((LocalPlaybackModelCallback) it5.next()).onCancelTransferReport(i12 == 1);
                        }
                    }
                }
                return true;
            case 1671:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i13 = parameters[0] & 255;
                    List<MCB> list8 = this.mCallbacks;
                    if (list8 == 0 || list8.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it6 = this.mCallbacks.iterator();
                        while (it6.hasNext()) {
                            ((LocalPlaybackModelCallback) it6.next()).onExitSongTransferModeReport(i13 == 1);
                        }
                    }
                }
                return true;
            case 1672:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i14 = parameters[0] & 255;
                    List<MCB> list9 = this.mCallbacks;
                    if (list9 == 0 || list9.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it7 = this.mCallbacks.iterator();
                        while (it7.hasNext()) {
                            ((LocalPlaybackModelCallback) it7.next()).onDeleteSingleSongReport(i14);
                        }
                    }
                }
                return true;
            case 1673:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i15 = parameters[0] & 255;
                    List<MCB> list10 = this.mCallbacks;
                    if (list10 == 0 || list10.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it8 = this.mCallbacks.iterator();
                        while (it8.hasNext()) {
                            ((LocalPlaybackModelCallback) it8.next()).onAddOrDeleteSongToPlaylistReport(i15);
                        }
                    }
                }
                return true;
            case 1675:
                if (parameters == null || parameters.length <= 0) {
                    ZLogger.e(ModelClient.DBG, "return parameter error");
                } else {
                    int i16 = parameters[0] & 255;
                    List<MCB> list11 = this.mCallbacks;
                    if (list11 == 0 || list11.size() <= 0) {
                        ZLogger.v(ModelClient.VDBG, "no callback registered");
                    } else {
                        Iterator it9 = this.mCallbacks.iterator();
                        while (it9.hasNext()) {
                            ((LocalPlaybackModelCallback) it9.next()).onDeleteAllSongReport(i16);
                        }
                    }
                }
                return true;
        }
    }

    public BeeError queryDeviceInfo() {
        Command commandBuild = new Command.Builder().writeType(2).packet((short) 1664, new byte[0]).eventId((short) 1664).build();
        ZLogger.i("send queryDeviceInfo..: " + DataConverter.bytes2HexWithSeparate(commandBuild.encode(28)));
        return sendVendorData(commandBuild);
    }

    public BeeError transfer(short s, short s2, int i, short s3, byte[] bArr) {
        if (s < 0) {
            ZLogger.e(ModelClient.DBG, "seq number is invalid");
            return new BeeError(48);
        }
        if (s3 <= 0) {
            ZLogger.e(ModelClient.DBG, "data length is invalid");
            return new BeeError(48);
        }
        if (bArr == null) {
            ZLogger.e(ModelClient.DBG, "song data can not be null");
            return new BeeError(48);
        }
        if (bArr.length == 0) {
            ZLogger.e(ModelClient.DBG, "song data length can not be 0");
            return new BeeError(48);
        }
        byte[] bArr2 = new byte[bArr.length + 10];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferWrap.putShort(0, s);
        byteBufferWrap.putShort(2, s2);
        byteBufferWrap.putInt(4, i);
        byteBufferWrap.putShort(8, s3);
        System.arraycopy(bArr, 0, bArr2, 10, s3);
        return sendVendorData(new Command.Builder().writeType(1).packet((short) 1667, bArr2).build());
    }

    public BeeError validTransfer(int i) {
        if (i <= 0) {
            ZLogger.e(ModelClient.DBG, "song length is invalid");
            return new BeeError(48);
        }
        byte[] bArr = new byte[6];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferWrap.putInt(0, i);
        byteBufferWrap.putShort(4, (short) 4660);
        return sendVendorData(new Command.Builder().writeType(2).packet((short) 1669, bArr).eventId((short) 1668).build());
    }
}
