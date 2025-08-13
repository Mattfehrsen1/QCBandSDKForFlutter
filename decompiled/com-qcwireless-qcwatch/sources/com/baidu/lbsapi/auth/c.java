package com.baidu.lbsapi.auth;

import androidx.core.view.MotionEventCompat;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.realsil.sdk.bbpro.params.Mmi;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public final class c {
    private static final byte[] a = {65, 66, Constants.CMD_GET_STEP_SOMEDAY_DETAIL, Constants.CMD_GET_SLEEP, 69, Constants.CMD_QUERY_DATA_DISTRIBUTION, LargeDataHandler.ACTION_Blood_Sugar, 72, 73, LargeDataHandler.ACTION_AVATAR_Device, 75, LargeDataHandler.ACTION_SMS_QUICK, 77, 78, 79, 80, 81, 82, Mmi.AU_MMI_DEV_LINK_LAST_DEVICE, 84, Mmi.AU_MMI_DEV_POWER_ON_BUTTON_RELEASE, 86, Mmi.AU_MMI_DEV_POWER_OFF_BUTTON_RELEASE, Mmi.AU_MMI_DEV_FACTORY_RESET_TO_DEFAULT, Mmi.AU_MMI_DEV_DISCONNECT_ALL_LINK, Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, 97, Mmi.AU_MMI_NFC_DETECT, Mmi.AU_MMI_AUDIO_EFFECT_NEXT, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, Mmi.AU_MMI_AUDIO_PASS_THROUGH, Mmi.AU_MMI_SWITCH_NEXT_VOICE_PROMPT_LANGUAGE, Mmi.AU_MMI_OUTPUT_INDICATION_1, Mmi.AU_MMI_OUTPUT_INDICATION_2, 105, 106, Mmi.AU_MMI_AUDIO_EQ_SWITCH, 108, 109, Constants.CMD_HEALTH_PPG_DATA, 111, 112, Mmi.AU_MMI_RWS_LINKBACK, 114, 115, 116, Mmi.AU_MMI_RWS_BUNDLE_PAIRING, Mmi.AU_MMI_RWS_RESET_TO_DEFAULT, 119, 120, Mmi.AU_MMI_RWS_SYNC_RINGTONE, Mmi.AU_MMI_ENTER_PAIRING_MODE_LONG_PRESS, 48, 49, 50, 51, Mmi.AU_MMAU_MMI_AV_FWD, Mmi.AU_MMI_AV_BWD, 54, 55, 56, 57, Constants.CMD_MENSTRUATION, 47};

    public static String a(byte[] bArr, String str) throws UnsupportedEncodingException {
        int length = (bArr.length * 4) / 3;
        byte[] bArr2 = new byte[length + (length / 76) + 3];
        int length2 = bArr.length - (bArr.length % 3);
        int i = 0;
        for (int i2 = 0; i2 < length2; i2 += 3) {
            int i3 = i + 1;
            byte[] bArr3 = a;
            bArr2[i] = bArr3[(bArr[i2] & 255) >> 2];
            int i4 = i3 + 1;
            int i5 = i2 + 1;
            bArr2[i3] = bArr3[((bArr[i2] & 3) << 4) | ((bArr[i5] & 255) >> 4)];
            int i6 = i4 + 1;
            int i7 = i2 + 2;
            bArr2[i4] = bArr3[((bArr[i5] & 15) << 2) | ((bArr[i7] & 255) >> 6)];
            i = i6 + 1;
            bArr2[i6] = bArr3[bArr[i7] & 63];
        }
        int length3 = bArr.length % 3;
        if (length3 == 1) {
            int i8 = i + 1;
            byte[] bArr4 = a;
            bArr2[i] = bArr4[(bArr[length2] & 255) >> 2];
            int i9 = i8 + 1;
            bArr2[i8] = bArr4[(bArr[length2] & 3) << 4];
            int i10 = i9 + 1;
            bArr2[i9] = 61;
            i = i10 + 1;
            bArr2[i10] = 61;
        } else if (length3 == 2) {
            int i11 = i + 1;
            byte[] bArr5 = a;
            bArr2[i] = bArr5[(bArr[length2] & 255) >> 2];
            int i12 = i11 + 1;
            int i13 = (bArr[length2] & 3) << 4;
            int i14 = length2 + 1;
            bArr2[i11] = bArr5[((bArr[i14] & 255) >> 4) | i13];
            int i15 = i12 + 1;
            bArr2[i12] = bArr5[(bArr[i14] & 15) << 2];
            i = i15 + 1;
            bArr2[i15] = 61;
        }
        return new String(bArr2, 0, i, str);
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static byte[] a(byte[] bArr, int i) {
        byte b;
        int i2;
        int i3 = (i / 4) * 3;
        if (i3 == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i3];
        int i4 = i;
        int i5 = 0;
        while (true) {
            byte b2 = bArr[i4 - 1];
            b = 10;
            if (b2 != 10 && b2 != 13 && b2 != 32 && b2 != 9) {
                if (b2 != 61) {
                    break;
                }
                i5++;
            }
            i4--;
        }
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i6 < i4) {
            byte b3 = bArr[i6];
            if (b3 != b && b3 != 13 && b3 != 32 && b3 != 9) {
                if (b3 >= 65 && b3 <= 90) {
                    i2 = b3 - 65;
                } else if (b3 >= 97 && b3 <= 122) {
                    i2 = b3 - 71;
                } else if (b3 >= 48 && b3 <= 57) {
                    i2 = b3 + 4;
                } else if (b3 == 43) {
                    i2 = 62;
                } else {
                    if (b3 != 47) {
                        return null;
                    }
                    i2 = 63;
                }
                i7 = ((byte) i2) | (i7 << 6);
                if (i9 % 4 == 3) {
                    int i10 = i8 + 1;
                    bArr2[i8] = (byte) ((16711680 & i7) >> 16);
                    int i11 = i10 + 1;
                    bArr2[i10] = (byte) ((65280 & i7) >> 8);
                    bArr2[i11] = (byte) (i7 & 255);
                    i8 = i11 + 1;
                }
                i9++;
            }
            i6++;
            b = 10;
        }
        if (i5 > 0) {
            int i12 = i7 << (i5 * 6);
            int i13 = i8 + 1;
            bArr2[i8] = (byte) ((i12 & 16711680) >> 16);
            if (i5 == 1) {
                i8 = i13 + 1;
                bArr2[i13] = (byte) ((i12 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            } else {
                i8 = i13;
            }
        }
        byte[] bArr3 = new byte[i8];
        System.arraycopy(bArr2, 0, bArr3, 0, i8);
        return bArr3;
    }
}
