package com.baidu.location.pb;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

/* loaded from: classes.dex */
public final class OffStatReq extends MessageMicro {
    public static final int AK_FIELD_NUMBER = 2;
    public static final int CLIENT_INFO_FIELD_NUMBER = 5;
    public static final int CU_FIELD_NUMBER = 1;
    public static final int SD_FIELD_NUMBER = 4;
    public static final int TS_FIELD_NUMBER = 3;
    private boolean hasAk;
    private boolean hasClientInfo;
    private boolean hasCu;
    private boolean hasSd;
    private boolean hasTs;
    private ByteStringMicro cu_ = ByteStringMicro.EMPTY;
    private ByteStringMicro ak_ = ByteStringMicro.EMPTY;
    private long ts_ = 0;
    private StatData sd_ = null;
    private ByteStringMicro clientInfo_ = ByteStringMicro.EMPTY;
    private int cachedSize = -1;

    public static OffStatReq parseFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return new OffStatReq().mergeFrom(codedInputStreamMicro);
    }

    public static OffStatReq parseFrom(byte[] bArr) throws InvalidProtocolBufferMicroException {
        return (OffStatReq) new OffStatReq().mergeFrom(bArr);
    }

    public final OffStatReq clear() {
        clearCu();
        clearAk();
        clearTs();
        clearSd();
        clearClientInfo();
        this.cachedSize = -1;
        return this;
    }

    public OffStatReq clearAk() {
        this.hasAk = false;
        this.ak_ = ByteStringMicro.EMPTY;
        return this;
    }

    public OffStatReq clearClientInfo() {
        this.hasClientInfo = false;
        this.clientInfo_ = ByteStringMicro.EMPTY;
        return this;
    }

    public OffStatReq clearCu() {
        this.hasCu = false;
        this.cu_ = ByteStringMicro.EMPTY;
        return this;
    }

    public OffStatReq clearSd() {
        this.hasSd = false;
        this.sd_ = null;
        return this;
    }

    public OffStatReq clearTs() {
        this.hasTs = false;
        this.ts_ = 0L;
        return this;
    }

    public ByteStringMicro getAk() {
        return this.ak_;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public int getCachedSize() {
        if (this.cachedSize < 0) {
            getSerializedSize();
        }
        return this.cachedSize;
    }

    public ByteStringMicro getClientInfo() {
        return this.clientInfo_;
    }

    public ByteStringMicro getCu() {
        return this.cu_;
    }

    public StatData getSd() {
        return this.sd_;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public int getSerializedSize() {
        int iComputeBytesSize = hasCu() ? 0 + CodedOutputStreamMicro.computeBytesSize(1, getCu()) : 0;
        if (hasAk()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeBytesSize(2, getAk());
        }
        if (hasTs()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeInt64Size(3, getTs());
        }
        if (hasSd()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeMessageSize(4, getSd());
        }
        if (hasClientInfo()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeBytesSize(5, getClientInfo());
        }
        this.cachedSize = iComputeBytesSize;
        return iComputeBytesSize;
    }

    public long getTs() {
        return this.ts_;
    }

    public boolean hasAk() {
        return this.hasAk;
    }

    public boolean hasClientInfo() {
        return this.hasClientInfo;
    }

    public boolean hasCu() {
        return this.hasCu;
    }

    public boolean hasSd() {
        return this.hasSd;
    }

    public boolean hasTs() {
        return this.hasTs;
    }

    public final boolean isInitialized() {
        return true;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public OffStatReq mergeFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        while (true) {
            int tag = codedInputStreamMicro.readTag();
            if (tag == 0) {
                return this;
            }
            if (tag == 10) {
                setCu(codedInputStreamMicro.readBytes());
            } else if (tag == 18) {
                setAk(codedInputStreamMicro.readBytes());
            } else if (tag == 24) {
                setTs(codedInputStreamMicro.readInt64());
            } else if (tag == 34) {
                StatData statData = new StatData();
                codedInputStreamMicro.readMessage(statData);
                setSd(statData);
            } else if (tag == 42) {
                setClientInfo(codedInputStreamMicro.readBytes());
            } else if (!parseUnknownField(codedInputStreamMicro, tag)) {
                return this;
            }
        }
    }

    public OffStatReq setAk(ByteStringMicro byteStringMicro) {
        this.hasAk = true;
        this.ak_ = byteStringMicro;
        return this;
    }

    public OffStatReq setClientInfo(ByteStringMicro byteStringMicro) {
        this.hasClientInfo = true;
        this.clientInfo_ = byteStringMicro;
        return this;
    }

    public OffStatReq setCu(ByteStringMicro byteStringMicro) {
        this.hasCu = true;
        this.cu_ = byteStringMicro;
        return this;
    }

    public OffStatReq setSd(StatData statData) {
        if (statData == null) {
            return clearSd();
        }
        this.hasSd = true;
        this.sd_ = statData;
        return this;
    }

    public OffStatReq setTs(long j) {
        this.hasTs = true;
        this.ts_ = j;
        return this;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro) throws IOException {
        if (hasCu()) {
            codedOutputStreamMicro.writeBytes(1, getCu());
        }
        if (hasAk()) {
            codedOutputStreamMicro.writeBytes(2, getAk());
        }
        if (hasTs()) {
            codedOutputStreamMicro.writeInt64(3, getTs());
        }
        if (hasSd()) {
            codedOutputStreamMicro.writeMessage(4, getSd());
        }
        if (hasClientInfo()) {
            codedOutputStreamMicro.writeBytes(5, getClientInfo());
        }
    }
}
