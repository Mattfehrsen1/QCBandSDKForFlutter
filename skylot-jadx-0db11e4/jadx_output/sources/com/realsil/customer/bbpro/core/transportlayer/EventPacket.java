package com.realsil.customer.bbpro.core.transportlayer;

import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;
import com.realsil.customer.core.utility.DataConverter;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/transportlayer/EventPacket.class */
public class EventPacket {
    public int a;
    public int paramsLen = 0;
    public byte[] mEventParams;

    public static EventPacket builderPacket(byte[] bArr) {
        EventPacket eventPacket = new EventPacket();
        if (eventPacket.parse(bArr)) {
            return eventPacket;
        }
        return null;
    }

    public boolean parse(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return false;
        }
        this.a = ((bArr[1] << 8) | (bArr[0] & 255)) & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
        int length = bArr.length - 2;
        this.paramsLen = length;
        if (length <= 0) {
            this.mEventParams = null;
            return true;
        }
        byte[] bArr2 = new byte[length];
        this.mEventParams = bArr2;
        System.arraycopy(bArr, 2, bArr2, 0, length);
        return true;
    }

    public int getEventId() {
        return this.a;
    }

    public byte[] getEventParams() {
        return this.mEventParams;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Event{");
        sb.append(String.format(Locale.US, "eventId=0x%04X", Integer.valueOf(this.a)));
        sb.append("\n\tparams: " + DataConverter.bytes2Hex(this.mEventParams));
        sb.append("\n}");
        return sb.toString();
    }
}
