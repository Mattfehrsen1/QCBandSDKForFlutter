package org.java_websocket.util;

import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.realsil.sdk.bbpro.params.Mmi;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = 61;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "US-ASCII";
    public static final int URL_SAFE = 16;
    private static final byte[] _STANDARD_ALPHABET = {65, 66, Constants.CMD_GET_STEP_SOMEDAY_DETAIL, Constants.CMD_GET_SLEEP, 69, Constants.CMD_QUERY_DATA_DISTRIBUTION, LargeDataHandler.ACTION_Blood_Sugar, 72, 73, LargeDataHandler.ACTION_AVATAR_Device, 75, LargeDataHandler.ACTION_SMS_QUICK, 77, 78, 79, 80, 81, 82, Mmi.AU_MMI_DEV_LINK_LAST_DEVICE, 84, Mmi.AU_MMI_DEV_POWER_ON_BUTTON_RELEASE, 86, Mmi.AU_MMI_DEV_POWER_OFF_BUTTON_RELEASE, Mmi.AU_MMI_DEV_FACTORY_RESET_TO_DEFAULT, Mmi.AU_MMI_DEV_DISCONNECT_ALL_LINK, Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, 97, Mmi.AU_MMI_NFC_DETECT, Mmi.AU_MMI_AUDIO_EFFECT_NEXT, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, Mmi.AU_MMI_AUDIO_PASS_THROUGH, Mmi.AU_MMI_SWITCH_NEXT_VOICE_PROMPT_LANGUAGE, Mmi.AU_MMI_OUTPUT_INDICATION_1, Mmi.AU_MMI_OUTPUT_INDICATION_2, 105, 106, Mmi.AU_MMI_AUDIO_EQ_SWITCH, 108, 109, Constants.CMD_HEALTH_PPG_DATA, 111, 112, Mmi.AU_MMI_RWS_LINKBACK, 114, 115, 116, Mmi.AU_MMI_RWS_BUNDLE_PAIRING, Mmi.AU_MMI_RWS_RESET_TO_DEFAULT, 119, 120, Mmi.AU_MMI_RWS_SYNC_RINGTONE, Mmi.AU_MMI_ENTER_PAIRING_MODE_LONG_PRESS, 48, 49, 50, 51, Mmi.AU_MMAU_MMI_AV_FWD, Mmi.AU_MMI_AV_BWD, 54, 55, 56, 57, Constants.CMD_MENSTRUATION, 47};
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte[] _STANDARD_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, Mmi.AU_MMAU_MMI_AV_FWD, Mmi.AU_MMI_AV_BWD, 54, 55, 56, 57, 58, Mmi.AU_MMI_AV_FASTFORWARD_RELEASE, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, Constants.CMD_GET_PERSONALIZATION_SETTING, 24, Constants.CMD_GET_DEGREE_SWITCH, -9, -9, -9, -9, -9, -9, Constants.CMD_SEND_WEATHER_FORECAST, Constants.CMD_GET_BRIGHTNESS, Constants.CMD_GET_MUSIC_SWITCH, Constants.CMD_MUSIC_COMMAND, Constants.CMD_REAL_TIME_HEART_RATE, Constants.CMD_DISPLAY_TIME, 32, 33, Constants.CMD_FIND_THE_PHONE, Constants.CMD_SET_ALARM_CLOCK, Constants.CMD_GET_ALARM_CLOCK, Constants.CMD_SET_SIT_LONG, Constants.CMD_GET_SIT_LONG, 39, 40, 41, 42, Constants.CMD_MENSTRUATION, 44, 45, LargeDataHandler.ACTION_BT_MAC_Protocol, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] _URL_SAFE_ALPHABET = {65, 66, Constants.CMD_GET_STEP_SOMEDAY_DETAIL, Constants.CMD_GET_SLEEP, 69, Constants.CMD_QUERY_DATA_DISTRIBUTION, LargeDataHandler.ACTION_Blood_Sugar, 72, 73, LargeDataHandler.ACTION_AVATAR_Device, 75, LargeDataHandler.ACTION_SMS_QUICK, 77, 78, 79, 80, 81, 82, Mmi.AU_MMI_DEV_LINK_LAST_DEVICE, 84, Mmi.AU_MMI_DEV_POWER_ON_BUTTON_RELEASE, 86, Mmi.AU_MMI_DEV_POWER_OFF_BUTTON_RELEASE, Mmi.AU_MMI_DEV_FACTORY_RESET_TO_DEFAULT, Mmi.AU_MMI_DEV_DISCONNECT_ALL_LINK, Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, 97, Mmi.AU_MMI_NFC_DETECT, Mmi.AU_MMI_AUDIO_EFFECT_NEXT, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, Mmi.AU_MMI_AUDIO_PASS_THROUGH, Mmi.AU_MMI_SWITCH_NEXT_VOICE_PROMPT_LANGUAGE, Mmi.AU_MMI_OUTPUT_INDICATION_1, Mmi.AU_MMI_OUTPUT_INDICATION_2, 105, 106, Mmi.AU_MMI_AUDIO_EQ_SWITCH, 108, 109, Constants.CMD_HEALTH_PPG_DATA, 111, 112, Mmi.AU_MMI_RWS_LINKBACK, 114, 115, 116, Mmi.AU_MMI_RWS_BUNDLE_PAIRING, Mmi.AU_MMI_RWS_RESET_TO_DEFAULT, 119, 120, Mmi.AU_MMI_RWS_SYNC_RINGTONE, Mmi.AU_MMI_ENTER_PAIRING_MODE_LONG_PRESS, 48, 49, 50, 51, Mmi.AU_MMAU_MMI_AV_FWD, Mmi.AU_MMI_AV_BWD, 54, 55, 56, 57, 45, 95};
    private static final byte[] _URL_SAFE_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, Mmi.AU_MMAU_MMI_AV_FWD, Mmi.AU_MMI_AV_BWD, 54, 55, 56, 57, 58, Mmi.AU_MMI_AV_FASTFORWARD_RELEASE, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, Constants.CMD_GET_PERSONALIZATION_SETTING, 24, Constants.CMD_GET_DEGREE_SWITCH, -9, -9, -9, -9, 63, -9, Constants.CMD_SEND_WEATHER_FORECAST, Constants.CMD_GET_BRIGHTNESS, Constants.CMD_GET_MUSIC_SWITCH, Constants.CMD_MUSIC_COMMAND, Constants.CMD_REAL_TIME_HEART_RATE, Constants.CMD_DISPLAY_TIME, 32, 33, Constants.CMD_FIND_THE_PHONE, Constants.CMD_SET_ALARM_CLOCK, Constants.CMD_GET_ALARM_CLOCK, Constants.CMD_SET_SIT_LONG, Constants.CMD_GET_SIT_LONG, 39, 40, 41, 42, Constants.CMD_MENSTRUATION, 44, 45, LargeDataHandler.ACTION_BT_MAC_Protocol, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};
    private static final byte[] _ORDERED_ALPHABET = {45, 48, 49, 50, 51, Mmi.AU_MMAU_MMI_AV_FWD, Mmi.AU_MMI_AV_BWD, 54, 55, 56, 57, 65, 66, Constants.CMD_GET_STEP_SOMEDAY_DETAIL, Constants.CMD_GET_SLEEP, 69, Constants.CMD_QUERY_DATA_DISTRIBUTION, LargeDataHandler.ACTION_Blood_Sugar, 72, 73, LargeDataHandler.ACTION_AVATAR_Device, 75, LargeDataHandler.ACTION_SMS_QUICK, 77, 78, 79, 80, 81, 82, Mmi.AU_MMI_DEV_LINK_LAST_DEVICE, 84, Mmi.AU_MMI_DEV_POWER_ON_BUTTON_RELEASE, 86, Mmi.AU_MMI_DEV_POWER_OFF_BUTTON_RELEASE, Mmi.AU_MMI_DEV_FACTORY_RESET_TO_DEFAULT, Mmi.AU_MMI_DEV_DISCONNECT_ALL_LINK, Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, 95, 97, Mmi.AU_MMI_NFC_DETECT, Mmi.AU_MMI_AUDIO_EFFECT_NEXT, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, Mmi.AU_MMI_AUDIO_PASS_THROUGH, Mmi.AU_MMI_SWITCH_NEXT_VOICE_PROMPT_LANGUAGE, Mmi.AU_MMI_OUTPUT_INDICATION_1, Mmi.AU_MMI_OUTPUT_INDICATION_2, 105, 106, Mmi.AU_MMI_AUDIO_EQ_SWITCH, 108, 109, Constants.CMD_HEALTH_PPG_DATA, 111, 112, Mmi.AU_MMI_RWS_LINKBACK, 114, 115, 116, Mmi.AU_MMI_RWS_BUNDLE_PAIRING, Mmi.AU_MMI_RWS_RESET_TO_DEFAULT, 119, 120, Mmi.AU_MMI_RWS_SYNC_RINGTONE, Mmi.AU_MMI_ENTER_PAIRING_MODE_LONG_PRESS};
    private static final byte[] _ORDERED_DECODABET = {-9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, WHITE_SPACE_ENC, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, WHITE_SPACE_ENC, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, Constants.CMD_GET_PERSONALIZATION_SETTING, 24, Constants.CMD_GET_DEGREE_SWITCH, Constants.CMD_SEND_WEATHER_FORECAST, Constants.CMD_GET_BRIGHTNESS, Constants.CMD_GET_MUSIC_SWITCH, Constants.CMD_MUSIC_COMMAND, Constants.CMD_REAL_TIME_HEART_RATE, Constants.CMD_DISPLAY_TIME, 32, 33, Constants.CMD_FIND_THE_PHONE, Constants.CMD_SET_ALARM_CLOCK, Constants.CMD_GET_ALARM_CLOCK, -9, -9, -9, -9, Constants.CMD_SET_SIT_LONG, -9, Constants.CMD_GET_SIT_LONG, 39, 40, 41, 42, Constants.CMD_MENSTRUATION, 44, 45, LargeDataHandler.ACTION_BT_MAC_Protocol, 47, 48, 49, 50, 51, Mmi.AU_MMAU_MMI_AV_FWD, Mmi.AU_MMI_AV_BWD, 54, 55, 56, 57, 58, Mmi.AU_MMI_AV_FASTFORWARD_RELEASE, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    private static final byte[] getAlphabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_ALPHABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_ALPHABET;
        }
        return _STANDARD_ALPHABET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] getDecodabet(int i) {
        if ((i & 16) == 16) {
            return _URL_SAFE_DECODABET;
        }
        if ((i & 32) == 32) {
            return _ORDERED_DECODABET;
        }
        return _STANDARD_DECODABET;
    }

    private Base64() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] encode3to4(byte[] bArr, byte[] bArr2, int i, int i2) {
        encode3to4(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] encode3to4(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] alphabet = getAlphabet(i4);
        int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
        if (i2 == 1) {
            bArr2[i3] = alphabet[i5 >>> 18];
            bArr2[i3 + 1] = alphabet[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = 61;
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 == 2) {
            bArr2[i3] = alphabet[i5 >>> 18];
            bArr2[i3 + 1] = alphabet[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = alphabet[(i5 >>> 6) & 63];
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 != 3) {
            return bArr2;
        }
        bArr2[i3] = alphabet[i5 >>> 18];
        bArr2[i3 + 1] = alphabet[(i5 >>> 12) & 63];
        bArr2[i3 + 2] = alphabet[(i5 >>> 6) & 63];
        bArr2[i3 + 3] = alphabet[i5 & 63];
        return bArr2;
    }

    public static String encodeBytes(byte[] bArr) {
        try {
            return encodeBytes(bArr, 0, bArr.length, 0);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String encodeBytes(byte[] bArr, int i, int i2, int i3) throws Throwable {
        byte[] bArrEncodeBytesToBytes = encodeBytesToBytes(bArr, i, i2, i3);
        try {
            return new String(bArrEncodeBytesToBytes, PREFERRED_ENCODING);
        } catch (UnsupportedEncodingException unused) {
            return new String(bArrEncodeBytesToBytes);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] encodeBytesToBytes(byte[] r18, int r19, int r20, int r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.util.Base64.encodeBytesToBytes(byte[], int, int, int):byte[]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int decode4to3(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null) {
            throw new IllegalArgumentException("Source array was null.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("Destination array was null.");
        }
        if (i < 0 || (i4 = i + 3) >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i)));
        }
        if (i2 < 0 || (i5 = i2 + 2) >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i2)));
        }
        byte[] decodabet = getDecodabet(i3);
        int i6 = i + 2;
        if (bArr[i6] == 61) {
            bArr2[i2] = (byte) ((((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i]] & 255) << 18)) >>> 16);
            return 1;
        }
        if (bArr[i4] == 61) {
            int i7 = ((decodabet[bArr[i6]] & 255) << 6) | ((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i]] & 255) << 18);
            bArr2[i2] = (byte) (i7 >>> 16);
            bArr2[i2 + 1] = (byte) (i7 >>> 8);
            return 2;
        }
        int i8 = (decodabet[bArr[i4]] & 255) | ((decodabet[bArr[i + 1]] & 255) << 12) | ((decodabet[bArr[i]] & 255) << 18) | ((decodabet[bArr[i6]] & 255) << 6);
        bArr2[i2] = (byte) (i8 >> 16);
        bArr2[i2 + 1] = (byte) (i8 >> 8);
        bArr2[i5] = (byte) i8;
        return 3;
    }

    public static class OutputStream extends FilterOutputStream {
        private byte[] b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;

        public OutputStream(java.io.OutputStream outputStream) {
            this(outputStream, 1);
        }

        public OutputStream(java.io.OutputStream outputStream, int i) {
            super(outputStream);
            this.breakLines = (i & 8) != 0;
            boolean z = (i & 1) != 0;
            this.encode = z;
            int i2 = z ? 3 : 4;
            this.bufferLength = i2;
            this.buffer = new byte[i2];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.b4 = new byte[4];
            this.options = i;
            this.decodabet = Base64.getDecodabet(i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(i);
                return;
            }
            if (this.encode) {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                int i3 = i2 + 1;
                this.position = i3;
                bArr[i2] = (byte) i;
                if (i3 >= this.bufferLength) {
                    this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                    int i4 = this.lineLength + 4;
                    this.lineLength = i4;
                    if (this.breakLines && i4 >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                    return;
                }
                return;
            }
            byte[] bArr2 = this.decodabet;
            int i5 = i & 127;
            if (bArr2[i5] > -5) {
                byte[] bArr3 = this.buffer;
                int i6 = this.position;
                int i7 = i6 + 1;
                this.position = i7;
                bArr3[i6] = (byte) i;
                if (i7 >= this.bufferLength) {
                    this.out.write(this.b4, 0, Base64.decode4to3(bArr3, 0, this.b4, 0, this.options));
                    this.position = 0;
                    return;
                }
                return;
            }
            if (bArr2[i5] != -5) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void flushBase64() throws IOException {
            if (this.position > 0) {
                if (this.encode) {
                    this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
                    this.position = 0;
                    return;
                }
                throw new IOException("Base64 input not properly padded.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }
    }
}
