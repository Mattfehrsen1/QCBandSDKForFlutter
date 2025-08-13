package com.realsil.sdk.bbpro.equalizer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes3.dex */
public final class EqConstants {
    public static final byte DSP_STATUS_ACTION_A2DP_DECODE = 4;
    public static final int EQ_GAMING_MODE = 1;
    public static final int EQ_INVALID_MODE = 255;
    public static final int EQ_NORMAL_MODE = 0;
    public static final int EQ_STATE_OFF = 0;
    public static final int EQ_STATE_ON = 1;
    public static final int FLOW_MECHANISM_BASE = 0;
    public static final int FLOW_MECHANISM_V1 = 1;
    public static final int FLOW_MECHANISM_V2 = 2;
    public static final int STATE_GAMING_EQ_OFF = 0;
    public static final int STATE_GAMING_EQ_ON = 1;

    public interface AptEqIndexReportScene {
        public static final int APT_ENABLE = 0;
        public static final int GET_APT_EQ_INDEX = 3;
        public static final int RWS_CONNECTED = 1;
        public static final int SWITCH_APT_EQ_INDEX_BY_KEY = 2;
        public static final int SYNC_UNSAVED_EQ = 5;
        public static final int UNKNOWN = 254;
        public static final int UPDATE_EQ_INDEX = 4;
    }

    public interface CodeIndex {
        public static final int BUILD_IN_EQ_1 = 16;
        public static final int BUILD_IN_EQ_2 = 32;
        public static final int BUILD_IN_EQ_3 = 64;
        public static final int BUILD_IN_EQ_4 = 128;
        public static final int BUILD_IN_EQ_5 = 256;
        public static final int CUSTOMER_EQ_1 = 2;
        public static final int CUSTOMER_EQ_2 = 4;
        public static final int CUSTOMER_EQ_3 = 8;
        public static final int EQ_OFF = 1;
        public static final int GAMING_MODE_EQ = 2048;
        public static final int REALTIME_EQ = 512;
        public static final int REALTIME_EQ_1 = 512;
        public static final int REALTIME_EQ_2 = 1024;
    }

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EqBud {
        public static final int LEFT = 0;
        public static final int LEFT_AND_RIGHT = 2;
        public static final int RIGHT = 1;
    }

    public interface EqIndexReportScene {
        public static final int CHANGE_EQ_MODE = 1;
        public static final int MUSIC_PLAYING = 0;
        public static final int QUERY_EQ_INDEX = 3;
        public static final int SWITCH_EQ_INDEX = 2;
        public static final int SYNC_UNSAVED_EQ = 5;
        public static final int UNKNOWN = 254;
        public static final int UPDATE_EQ_INDEX = 4;
    }

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EqType {
        public static final int MIC_SW_EQ = 1;
        public static final int SPK_SW_EQ = 0;
    }

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MicEqMode {
        public static final int APT_MODE_EQ = 0;
    }

    public interface SampleRate {
        public static final byte SR_16K = 1;
        public static final byte SR_32K = 2;
        public static final byte SR_44P1K = 3;
        public static final byte SR_48K = 4;
        public static final byte SR_88P2K = 5;
        public static final byte SR_8K = 0;
        public static final byte SR_96K = 6;
    }

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SpkEqMode {
        public static final int ANC_MODE_EQ = 2;
        public static final int GAMING_MODE_EQ = 1;
        public static final int NORMAL_MODE_EQ = 0;
    }
}
