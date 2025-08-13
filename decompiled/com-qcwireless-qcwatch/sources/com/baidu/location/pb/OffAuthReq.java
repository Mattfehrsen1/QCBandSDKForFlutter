package com.baidu.location.pb;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class OffAuthReq extends MessageMicro {
    public static final int AK_FIELD_NUMBER = 2;
    public static final int CLIENT_INFO_FIELD_NUMBER = 7;
    public static final int CU_FIELD_NUMBER = 1;
    public static final int GTL_FIELD_NUMBER = 8;
    public static final int GT_FIELD_NUMBER = 6;
    public static final int LOC_FIELD_NUMBER = 3;
    public static final int SRC_FIELD_NUMBER = 5;
    public static final int TS_FIELD_NUMBER = 4;
    private boolean hasAk;
    private boolean hasClientInfo;
    private boolean hasCu;
    private boolean hasLoc;
    private boolean hasSrc;
    private boolean hasTs;
    private ByteStringMicro cu_ = ByteStringMicro.EMPTY;
    private ByteStringMicro ak_ = ByteStringMicro.EMPTY;
    private Loc loc_ = null;
    private long ts_ = 0;
    private ByteStringMicro src_ = ByteStringMicro.EMPTY;
    private List<Integer> gt_ = Collections.emptyList();
    private ByteStringMicro clientInfo_ = ByteStringMicro.EMPTY;
    private List<GridTypeLen> gtl_ = Collections.emptyList();
    private int cachedSize = -1;

    public static OffAuthReq parseFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        return new OffAuthReq().mergeFrom(codedInputStreamMicro);
    }

    public static OffAuthReq parseFrom(byte[] bArr) throws InvalidProtocolBufferMicroException {
        return (OffAuthReq) new OffAuthReq().mergeFrom(bArr);
    }

    public OffAuthReq addGt(int i) {
        if (this.gt_.isEmpty()) {
            this.gt_ = new ArrayList();
        }
        this.gt_.add(Integer.valueOf(i));
        return this;
    }

    public OffAuthReq addGtl(GridTypeLen gridTypeLen) {
        if (gridTypeLen == null) {
            return this;
        }
        if (this.gtl_.isEmpty()) {
            this.gtl_ = new ArrayList();
        }
        this.gtl_.add(gridTypeLen);
        return this;
    }

    public final OffAuthReq clear() {
        clearCu();
        clearAk();
        clearLoc();
        clearTs();
        clearSrc();
        clearGt();
        clearClientInfo();
        clearGtl();
        this.cachedSize = -1;
        return this;
    }

    public OffAuthReq clearAk() {
        this.hasAk = false;
        this.ak_ = ByteStringMicro.EMPTY;
        return this;
    }

    public OffAuthReq clearClientInfo() {
        this.hasClientInfo = false;
        this.clientInfo_ = ByteStringMicro.EMPTY;
        return this;
    }

    public OffAuthReq clearCu() {
        this.hasCu = false;
        this.cu_ = ByteStringMicro.EMPTY;
        return this;
    }

    public OffAuthReq clearGt() {
        this.gt_ = Collections.emptyList();
        return this;
    }

    public OffAuthReq clearGtl() {
        this.gtl_ = Collections.emptyList();
        return this;
    }

    public OffAuthReq clearLoc() {
        this.hasLoc = false;
        this.loc_ = null;
        return this;
    }

    public OffAuthReq clearSrc() {
        this.hasSrc = false;
        this.src_ = ByteStringMicro.EMPTY;
        return this;
    }

    public OffAuthReq clearTs() {
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

    public int getGt(int i) {
        return this.gt_.get(i).intValue();
    }

    public int getGtCount() {
        return this.gt_.size();
    }

    public List<Integer> getGtList() {
        return this.gt_;
    }

    public GridTypeLen getGtl(int i) {
        return this.gtl_.get(i);
    }

    public int getGtlCount() {
        return this.gtl_.size();
    }

    public List<GridTypeLen> getGtlList() {
        return this.gtl_;
    }

    public Loc getLoc() {
        return this.loc_;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public int getSerializedSize() {
        int iComputeInt32SizeNoTag = 0;
        int iComputeBytesSize = hasCu() ? CodedOutputStreamMicro.computeBytesSize(1, getCu()) + 0 : 0;
        if (hasAk()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeBytesSize(2, getAk());
        }
        if (hasLoc()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeMessageSize(3, getLoc());
        }
        if (hasTs()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeInt64Size(4, getTs());
        }
        if (hasSrc()) {
            iComputeBytesSize += CodedOutputStreamMicro.computeBytesSize(5, getSrc());
        }
        Iterator<Integer> it = getGtList().iterator();
        while (it.hasNext()) {
            iComputeInt32SizeNoTag += CodedOutputStreamMicro.computeInt32SizeNoTag(it.next().intValue());
        }
        int size = iComputeBytesSize + iComputeInt32SizeNoTag + (getGtList().size() * 1);
        if (hasClientInfo()) {
            size += CodedOutputStreamMicro.computeBytesSize(7, getClientInfo());
        }
        Iterator<GridTypeLen> it2 = getGtlList().iterator();
        while (it2.hasNext()) {
            size += CodedOutputStreamMicro.computeMessageSize(8, it2.next());
        }
        this.cachedSize = size;
        return size;
    }

    public ByteStringMicro getSrc() {
        return this.src_;
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

    public boolean hasLoc() {
        return this.hasLoc;
    }

    public boolean hasSrc() {
        return this.hasSrc;
    }

    public boolean hasTs() {
        return this.hasTs;
    }

    public final boolean isInitialized() {
        return true;
    }

    @Override // com.google.protobuf.micro.MessageMicro
    public OffAuthReq mergeFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
        while (true) {
            int tag = codedInputStreamMicro.readTag();
            if (tag == 0) {
                return this;
            }
            if (tag == 10) {
                setCu(codedInputStreamMicro.readBytes());
            } else if (tag == 18) {
                setAk(codedInputStreamMicro.readBytes());
            } else if (tag == 26) {
                Loc loc = new Loc();
                codedInputStreamMicro.readMessage(loc);
                setLoc(loc);
            } else if (tag == 32) {
                setTs(codedInputStreamMicro.readInt64());
            } else if (tag == 42) {
                setSrc(codedInputStreamMicro.readBytes());
            } else if (tag == 48) {
                addGt(codedInputStreamMicro.readInt32());
            } else if (tag == 58) {
                setClientInfo(codedInputStreamMicro.readBytes());
            } else if (tag == 66) {
                GridTypeLen gridTypeLen = new GridTypeLen();
                codedInputStreamMicro.readMessage(gridTypeLen);
                addGtl(gridTypeLen);
            } else if (!parseUnknownField(codedInputStreamMicro, tag)) {
                return this;
            }
        }
    }

    public OffAuthReq setAk(ByteStringMicro byteStringMicro) {
        this.hasAk = true;
        this.ak_ = byteStringMicro;
        return this;
    }

    public OffAuthReq setClientInfo(ByteStringMicro byteStringMicro) {
        this.hasClientInfo = true;
        this.clientInfo_ = byteStringMicro;
        return this;
    }

    public OffAuthReq setCu(ByteStringMicro byteStringMicro) {
        this.hasCu = true;
        this.cu_ = byteStringMicro;
        return this;
    }

    public OffAuthReq setGt(int i, int i2) {
        this.gt_.set(i, Integer.valueOf(i2));
        return this;
    }

    public OffAuthReq setGtl(int i, GridTypeLen gridTypeLen) {
        if (gridTypeLen == null) {
            return this;
        }
        this.gtl_.set(i, gridTypeLen);
        return this;
    }

    public OffAuthReq setLoc(Loc loc) {
        if (loc == null) {
            return clearLoc();
        }
        this.hasLoc = true;
        this.loc_ = loc;
        return this;
    }

    public OffAuthReq setSrc(ByteStringMicro byteStringMicro) {
        this.hasSrc = true;
        this.src_ = byteStringMicro;
        return this;
    }

    public OffAuthReq setTs(long j) {
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
        if (hasLoc()) {
            codedOutputStreamMicro.writeMessage(3, getLoc());
        }
        if (hasTs()) {
            codedOutputStreamMicro.writeInt64(4, getTs());
        }
        if (hasSrc()) {
            codedOutputStreamMicro.writeBytes(5, getSrc());
        }
        Iterator<Integer> it = getGtList().iterator();
        while (it.hasNext()) {
            codedOutputStreamMicro.writeInt32(6, it.next().intValue());
        }
        if (hasClientInfo()) {
            codedOutputStreamMicro.writeBytes(7, getClientInfo());
        }
        Iterator<GridTypeLen> it2 = getGtlList().iterator();
        while (it2.hasNext()) {
            codedOutputStreamMicro.writeMessage(8, it2.next());
        }
    }
}
