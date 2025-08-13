package com.baidu.location.pb;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

/* loaded from: classes.dex */
public final class CellCommonValue extends MessageMicro {
    public static final int CELLCONNECTIONSTATUS_FIELD_NUMBER = 6;
    public static final int CELL_TYPE_FIELD_NUMBER = 1;
    public static final int MCC_FIELD_NUMBER = 2;
    public static final int MNC_FIELD_NUMBER = 3;
    public static final int REGISTERED_FIELD_NUMBER = 4;
    public static final int TIMESTAMP_FIELD_NUMBER = 5;
    private boolean hasCellType;
    private boolean hasCellconnectionstatus;
    private boolean hasMcc;
    private boolean hasMnc;
    private boolean hasRegistered;
    private boolean hasTimestamp;
    private int cellType_ = 0;
    private ByteStringMicro mcc_ = ByteStringMicro.EMPTY;
    private ByteStringMicro mnc_ = ByteStringMicro.EMPTY;
    private int registered_ = 0;
    private long timestamp_ = 0;
    private int cellconnectionstatus_ = 0;
    private int cachedSize = -1;

    public static CellCommonValue parseFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return new CellCommonValue().mergeFrom(codedInputStreamMicro);
    }

    public static CellCommonValue parseFrom(byte[] bArr) throws InvalidProtocolBufferMicroException {
        return (CellCommonValue) new CellCommonValue().mergeFrom(bArr);
    }

    public final CellCommonValue clear() {
        clearCellType();
        clearMcc();
        clearMnc();
        clearRegistered();
        clearTimestamp();
        clearCellconnectionstatus();
        this.cachedSize = -1;
        return this;
    }

    public CellCommonValue clearCellType() {
        this.hasCellType = false;
        this.cellType_ = 0;
        return this;
    }

    public CellCommonValue clearCellconnectionstatus() {
        this.hasCellconnectionstatus = false;
        this.cellconnectionstatus_ = 0;
        return this;
    }

    public CellCommonValue clearMcc() {
        this.hasMcc = false;
        this.mcc_ = ByteStringMicro.EMPTY;
        return this;
    }

    public CellCommonValue clearMnc() {
        this.hasMnc = false;
        this.mnc_ = ByteStringMicro.EMPTY;
        return this;
    }

    public CellCommonValue clearRegistered() {
        this.hasRegistered = false;
        this.registered_ = 0;
        return this;
    }

    public CellCommonValue clearTimestamp() {
        this.hasTimestamp = false;
        this.timestamp_ = 0L;
        return this;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public int getCachedSize() {
        if (this.cachedSize < 0) {
            getSerializedSize();
        }
        return this.cachedSize;
    }

    public int getCellType() {
        return this.cellType_;
    }

    public int getCellconnectionstatus() {
        return this.cellconnectionstatus_;
    }

    public ByteStringMicro getMcc() {
        return this.mcc_;
    }

    public ByteStringMicro getMnc() {
        return this.mnc_;
    }

    public int getRegistered() {
        return this.registered_;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public int getSerializedSize() {
        int iComputeInt32Size = hasCellType() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getCellType()) : 0;
        if (hasMcc()) {
            iComputeInt32Size += CodedOutputStreamMicro.computeBytesSize(2, getMcc());
        }
        if (hasMnc()) {
            iComputeInt32Size += CodedOutputStreamMicro.computeBytesSize(3, getMnc());
        }
        if (hasRegistered()) {
            iComputeInt32Size += CodedOutputStreamMicro.computeInt32Size(4, getRegistered());
        }
        if (hasTimestamp()) {
            iComputeInt32Size += CodedOutputStreamMicro.computeUInt64Size(5, getTimestamp());
        }
        if (hasCellconnectionstatus()) {
            iComputeInt32Size += CodedOutputStreamMicro.computeInt32Size(6, getCellconnectionstatus());
        }
        this.cachedSize = iComputeInt32Size;
        return iComputeInt32Size;
    }

    public long getTimestamp() {
        return this.timestamp_;
    }

    public boolean hasCellType() {
        return this.hasCellType;
    }

    public boolean hasCellconnectionstatus() {
        return this.hasCellconnectionstatus;
    }

    public boolean hasMcc() {
        return this.hasMcc;
    }

    public boolean hasMnc() {
        return this.hasMnc;
    }

    public boolean hasRegistered() {
        return this.hasRegistered;
    }

    public boolean hasTimestamp() {
        return this.hasTimestamp;
    }

    public final boolean isInitialized() {
        return true;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public CellCommonValue mergeFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        while (true) {
            int tag = codedInputStreamMicro.readTag();
            if (tag == 0) {
                return this;
            }
            if (tag == 8) {
                setCellType(codedInputStreamMicro.readInt32());
            } else if (tag == 18) {
                setMcc(codedInputStreamMicro.readBytes());
            } else if (tag == 26) {
                setMnc(codedInputStreamMicro.readBytes());
            } else if (tag == 32) {
                setRegistered(codedInputStreamMicro.readInt32());
            } else if (tag == 40) {
                setTimestamp(codedInputStreamMicro.readUInt64());
            } else if (tag == 48) {
                setCellconnectionstatus(codedInputStreamMicro.readInt32());
            } else if (!parseUnknownField(codedInputStreamMicro, tag)) {
                return this;
            }
        }
    }

    public CellCommonValue setCellType(int i) {
        this.hasCellType = true;
        this.cellType_ = i;
        return this;
    }

    public CellCommonValue setCellconnectionstatus(int i) {
        this.hasCellconnectionstatus = true;
        this.cellconnectionstatus_ = i;
        return this;
    }

    public CellCommonValue setMcc(ByteStringMicro byteStringMicro) {
        this.hasMcc = true;
        this.mcc_ = byteStringMicro;
        return this;
    }

    public CellCommonValue setMnc(ByteStringMicro byteStringMicro) {
        this.hasMnc = true;
        this.mnc_ = byteStringMicro;
        return this;
    }

    public CellCommonValue setRegistered(int i) {
        this.hasRegistered = true;
        this.registered_ = i;
        return this;
    }

    public CellCommonValue setTimestamp(long j) {
        this.hasTimestamp = true;
        this.timestamp_ = j;
        return this;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro) throws IOException {
        if (hasCellType()) {
            codedOutputStreamMicro.writeInt32(1, getCellType());
        }
        if (hasMcc()) {
            codedOutputStreamMicro.writeBytes(2, getMcc());
        }
        if (hasMnc()) {
            codedOutputStreamMicro.writeBytes(3, getMnc());
        }
        if (hasRegistered()) {
            codedOutputStreamMicro.writeInt32(4, getRegistered());
        }
        if (hasTimestamp()) {
            codedOutputStreamMicro.writeUInt64(5, getTimestamp());
        }
        if (hasCellconnectionstatus()) {
            codedOutputStreamMicro.writeInt32(6, getCellconnectionstatus());
        }
    }
}
