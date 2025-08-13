package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.bbpro.core.transportlayer.Command;
import com.realsil.sdk.bbpro.profile.AppReq;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class f extends AppReq {
    public int a;

    public static class b {
        public int a;

        public b a(int i) {
            this.a = i;
            return this;
        }

        public f a() {
            return new f(this.a);
        }
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode(int i) {
        return encode();
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getCommandId() {
        return (short) 528;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public short getEventId() {
        return (short) 520;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GetEqBasicInfo {");
        int i = this.a;
        if ((i & 255) != 255) {
            sb.append(String.format(Locale.US, "\n\tmode=0x%02X", Integer.valueOf(i)));
        }
        sb.append("\n}");
        return sb.toString();
    }

    public f(int i) {
        this.a = i;
    }

    @Override // com.realsil.sdk.bbpro.profile.AppReq
    public Command encode() {
        int i = this.a & 255;
        return new Command.Builder().writeType(2).packet(getCommandId(), i != 255 ? new byte[]{(byte) i} : null).eventId(getEventId()).build();
    }
}
