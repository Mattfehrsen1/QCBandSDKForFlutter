package com.realsil.sdk.audioconnect.localplayback;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes3.dex */
public class LocalPlaybackConstants {
    public static final int BUD_ROLE_PRIMARY = 1;
    public static final int BUD_ROLE_SECONDARY = 0;
    public static final int BUFFER_A_CAN_USE_FLAG = 241;
    public static final int BUFFER_B_CAN_USE_FLAG = 242;
    public static final String EXTRA_BUD_ROLE = "com.realsil.android.extra.BUD_ROLE";
    public static final String EXTRA_BUFFER_CHECK_SIZE = "com.realsil.android.extra.BUFFER_CHECK_SIZE";
    public static final String EXTRA_PROTOCOL_VERSION = "com.realsil.android.extra.PROTOCOL_VERSION";
    public static final String EXTRA_RWS_STATUS = "com.realsil.android.extra.RWS_STATUS";
    public static final String EXTRA_SEND_PACKET_SIZE = "com.realsil.android.extra.SEND_PACKET_SIZE";
    public static final String EXTRA_SUPPORT_FORMATS = "com.realsil.android.extra.SUPPORT_FORMATS";
    public static final byte FILE_TYPE_HEADER_BIN = 0;
    public static final byte FILE_TYPE_NAME_BIN = 1;
    public static final int FORMAT_TYPE_AAC = 1;
    public static final int FORMAT_TYPE_FLAC = 4;
    public static final int FORMAT_TYPE_MP3 = 2;
    public static final int FORMAT_TYPE_RTK = 0;
    public static final int FORMAT_TYPE_WAV = 8;
    public static final byte GET_DATA_FLAG_CONTINUE = 1;
    public static final byte GET_DATA_FLAG_END = 2;
    public static final byte GET_DATA_FLAG_START = 0;
    public static final byte RESULT_SUCCESS = 1;
    public static final int RWS_STATUS_COUPLE = 1;
    public static final int RWS_STATUS_SINGLE = 0;
    public static final int VALID_RESULT_CRC_ERROR = 5;
    public static final int VALID_RESULT_LENGTH_ERROR = 6;
    public static final int VALID_RESULT_SUCCESS = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BufferFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FormatType {
    }

    public interface Operation {
        public static final int QUERY_INFO = 1;
        public static final int TRANS_CANCEL = 5;
        public static final int TRANS_CONTINUE = 3;
        public static final int TRANS_EXIT = 6;
        public static final int TRANS_START = 2;
        public static final int VALID_SONG = 4;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ValidResult {
    }
}
