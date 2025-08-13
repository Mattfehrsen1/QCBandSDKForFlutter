package com.realsil.sdk.core.bluetooth.utils;

import android.os.ParcelUuid;
import android.support.v4.media.session.PlaybackStateCompat;
import com.realsil.sdk.core.a.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes3.dex */
public final class BluetoothUuid {
    public static final ParcelUuid[] A2DP_SINK_PROFILE_UUIDS;
    public static final ParcelUuid[] A2DP_SRC_PROFILE_UUIDS;
    public static final ParcelUuid ADV_AUDIO_DIST;
    public static final ParcelUuid AUDIO_SINK;
    public static final ParcelUuid AUDIO_SOURCE;
    public static final ParcelUuid AVRCP_CONTROLLER;
    public static final ParcelUuid AVRCP_TARGET;
    public static final ParcelUuid BASE_UUID;
    public static final ParcelUuid BNEP;
    public static final ParcelUuid HANDSFREE;
    public static final ParcelUuid HANDSFREE_AG;
    public static final ParcelUuid[] HEADSET_PROFILE_UUIDS;
    public static final ParcelUuid HID;
    public static final ParcelUuid[] HID_PROFILE_UUIDS;
    public static final ParcelUuid HOGP;
    public static final ParcelUuid HSP;
    public static final ParcelUuid HSP_AG;
    public static final ParcelUuid MAP;
    public static final ParcelUuid MAS;
    public static final ParcelUuid MNS;
    public static final ParcelUuid NAP;
    public static final ParcelUuid[] NAP_PROFILE_UUIDS;
    public static final ParcelUuid OBEX_OBJECT_PUSH;
    public static final ParcelUuid[] OPP_PROFILE_UUIDS;
    public static final ParcelUuid PANU;
    public static final ParcelUuid[] PANU_PROFILE_UUIDS;
    public static final ParcelUuid PBAP_PCE;
    public static final ParcelUuid PBAP_PSE;
    public static final ParcelUuid[] RESERVED_UUIDS;
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;

    static {
        ParcelUuid parcelUuidFromString = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");
        AUDIO_SINK = parcelUuidFromString;
        ParcelUuid parcelUuidFromString2 = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");
        AUDIO_SOURCE = parcelUuidFromString2;
        ParcelUuid parcelUuidFromString3 = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");
        ADV_AUDIO_DIST = parcelUuidFromString3;
        ParcelUuid parcelUuidFromString4 = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");
        HSP = parcelUuidFromString4;
        HSP_AG = ParcelUuid.fromString("00001112-0000-1000-8000-00805F9B34FB");
        ParcelUuid parcelUuidFromString5 = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");
        HANDSFREE = parcelUuidFromString5;
        HANDSFREE_AG = ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");
        ParcelUuid parcelUuidFromString6 = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");
        AVRCP_CONTROLLER = parcelUuidFromString6;
        ParcelUuid parcelUuidFromString7 = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");
        AVRCP_TARGET = parcelUuidFromString7;
        ParcelUuid parcelUuidFromString8 = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");
        OBEX_OBJECT_PUSH = parcelUuidFromString8;
        ParcelUuid parcelUuidFromString9 = ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");
        HID = parcelUuidFromString9;
        HOGP = ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");
        ParcelUuid parcelUuidFromString10 = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");
        PANU = parcelUuidFromString10;
        ParcelUuid parcelUuidFromString11 = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");
        NAP = parcelUuidFromString11;
        BNEP = ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");
        PBAP_PCE = ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");
        PBAP_PSE = ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");
        ParcelUuid parcelUuidFromString12 = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
        MAP = parcelUuidFromString12;
        ParcelUuid parcelUuidFromString13 = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
        MNS = parcelUuidFromString13;
        ParcelUuid parcelUuidFromString14 = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
        MAS = parcelUuidFromString14;
        BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
        HEADSET_PROFILE_UUIDS = new ParcelUuid[]{parcelUuidFromString4, parcelUuidFromString5};
        A2DP_SINK_PROFILE_UUIDS = new ParcelUuid[]{parcelUuidFromString, parcelUuidFromString3};
        A2DP_SRC_PROFILE_UUIDS = new ParcelUuid[]{parcelUuidFromString2};
        OPP_PROFILE_UUIDS = new ParcelUuid[]{parcelUuidFromString8};
        HID_PROFILE_UUIDS = new ParcelUuid[]{parcelUuidFromString9};
        PANU_PROFILE_UUIDS = new ParcelUuid[]{parcelUuidFromString10};
        NAP_PROFILE_UUIDS = new ParcelUuid[]{parcelUuidFromString11};
        RESERVED_UUIDS = new ParcelUuid[]{parcelUuidFromString, parcelUuidFromString2, parcelUuidFromString3, parcelUuidFromString4, parcelUuidFromString5, parcelUuidFromString6, parcelUuidFromString7, parcelUuidFromString8, parcelUuidFromString10, parcelUuidFromString11, parcelUuidFromString12, parcelUuidFromString13, parcelUuidFromString14};
    }

    public static boolean a(UUID uuid) {
        return (uuid.getMostSignificantBits() & (-281470681743361L)) == 0 && uuid.getLeastSignificantBits() == 0;
    }

    public static boolean containsAllUuids(ParcelUuid[] parcelUuidArr, ParcelUuid[] parcelUuidArr2) {
        if (parcelUuidArr == null && parcelUuidArr2 == null) {
            return true;
        }
        if (parcelUuidArr == null) {
            return parcelUuidArr2.length == 0;
        }
        if (parcelUuidArr2 == null) {
            return true;
        }
        HashSet hashSet = new HashSet(Arrays.asList(parcelUuidArr));
        for (ParcelUuid parcelUuid : parcelUuidArr2) {
            if (!hashSet.contains(parcelUuid)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAnyUuid(ParcelUuid[] parcelUuidArr, ParcelUuid[] parcelUuidArr2) {
        if (parcelUuidArr == null && parcelUuidArr2 == null) {
            return true;
        }
        if (parcelUuidArr == null) {
            return parcelUuidArr2.length == 0;
        }
        if (parcelUuidArr2 == null) {
            return parcelUuidArr.length == 0;
        }
        HashSet hashSet = new HashSet(Arrays.asList(parcelUuidArr));
        for (ParcelUuid parcelUuid : parcelUuidArr2) {
            if (hashSet.contains(parcelUuid)) {
                return true;
            }
        }
        return false;
    }

    public static UUID fromShortValue(int i) {
        StringBuilder sbA = a.a("0000");
        sbA.append(String.format("%04X", Integer.valueOf(i & 65535)));
        sbA.append("-0000-1000-8000-00805F9B34FB");
        return UUID.fromString(sbA.toString());
    }

    public static UUID fromString(String str) {
        try {
            return UUID.fromString(str);
        } catch (IllegalArgumentException unused) {
            return UUID.fromString("0000" + str + "-0000-1000-8000-00805F9B34FB");
        }
    }

    public static int getServiceIdentifierFromParcelUuid(ParcelUuid parcelUuid) {
        return (int) ((parcelUuid.getUuid().getMostSignificantBits() & 281470681743360L) >>> 32);
    }

    public static boolean is16BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && (uuid.getMostSignificantBits() & (-281470681743361L)) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    public static boolean is32BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && !is16BitUuid(parcelUuid) && (uuid.getMostSignificantBits() & 4294967295L) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    public static boolean isAdvAudioDist(ParcelUuid parcelUuid) {
        return parcelUuid.equals(ADV_AUDIO_DIST);
    }

    public static boolean isAudioSink(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AUDIO_SINK);
    }

    public static boolean isAudioSource(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AUDIO_SOURCE);
    }

    public static boolean isAvrcpController(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AVRCP_CONTROLLER);
    }

    public static boolean isAvrcpTarget(ParcelUuid parcelUuid) {
        return parcelUuid.equals(AVRCP_TARGET);
    }

    public static boolean isBnep(ParcelUuid parcelUuid) {
        return parcelUuid.equals(BNEP);
    }

    public static boolean isHandsfree(ParcelUuid parcelUuid) {
        return parcelUuid.equals(HANDSFREE);
    }

    public static boolean isHeadset(ParcelUuid parcelUuid) {
        return parcelUuid.equals(HSP);
    }

    public static boolean isInputDevice(ParcelUuid parcelUuid) {
        return parcelUuid.equals(HID);
    }

    public static boolean isMap(ParcelUuid parcelUuid) {
        return parcelUuid.equals(MAP);
    }

    public static boolean isMas(ParcelUuid parcelUuid) {
        return parcelUuid.equals(MAS);
    }

    public static boolean isMns(ParcelUuid parcelUuid) {
        return parcelUuid.equals(MNS);
    }

    public static boolean isNap(ParcelUuid parcelUuid) {
        return parcelUuid.equals(NAP);
    }

    public static boolean isPanu(ParcelUuid parcelUuid) {
        return parcelUuid.equals(PANU);
    }

    public static boolean isUuidPresent(ParcelUuid[] parcelUuidArr, ParcelUuid parcelUuid) {
        if ((parcelUuidArr == null || parcelUuidArr.length == 0) && parcelUuid == null) {
            return true;
        }
        if (parcelUuidArr == null) {
            return false;
        }
        for (ParcelUuid parcelUuid2 : parcelUuidArr) {
            if (parcelUuid2.equals(parcelUuid)) {
                return true;
            }
        }
        return false;
    }

    public static boolean matches(UUID uuid, UUID uuid2) {
        return (a(uuid) || a(uuid2)) ? (uuid.getMostSignificantBits() & 281470681743360L) == (uuid2.getMostSignificantBits() & 281470681743360L) : uuid.equals(uuid2);
    }

    public static ParcelUuid parcelFromShortValue(int i) {
        StringBuilder sbA = a.a("0000");
        sbA.append(String.format("%04X", Integer.valueOf(i & 65535)));
        sbA.append("-0000-1000-8000-00805F9B34FB");
        return ParcelUuid.fromString(sbA.toString());
    }

    public static ParcelUuid parseUuidFrom(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 16) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == 2) {
            j = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
        } else {
            j = ((bArr[3] & 255) << 24) + (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16);
        }
        ParcelUuid parcelUuid = BASE_UUID;
        return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j << 32), parcelUuid.getUuid().getLeastSignificantBits()));
    }

    public static ParcelUuid parseUuidReverse(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 16) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == 2) {
            j = (bArr[1] & 255) + ((bArr[0] & 255) << 8);
        } else {
            j = ((bArr[0] & 255) << 24) + (bArr[3] & 255) + ((bArr[2] & 255) << 8) + ((bArr[1] & 255) << 16);
        }
        ParcelUuid parcelUuid = BASE_UUID;
        return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j << 32), parcelUuid.getUuid().getLeastSignificantBits()));
    }

    public static int toShortValue(UUID uuid) {
        return (int) ((uuid.getMostSignificantBits() >> 32) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }
}
