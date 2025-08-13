package com.realsil.sdk.bbpro.vendor;

/* loaded from: classes3.dex */
public final class VendorConstants {
    public static final byte APT_NR_DISABLED = 0;
    public static final byte APT_NR_ENABLED = 1;
    public static final byte LISTENING_MODE_1 = 0;
    public static final byte LISTENING_MODE_2 = 1;
    public static final byte LISTENING_MODE_3 = 2;
    public static final byte LISTENING_MODE_CYCLE_1 = 0;
    public static final byte LISTENING_MODE_CYCLE_2 = 1;
    public static final byte LISTENING_MODE_CYCLE_3 = 2;
    public static final byte LISTENING_MODE_CYCLE_4 = 3;
    public static final byte LISTENING_MODE_CYCLE_5 = 4;
    public static final byte MFB_SETTINGS_FAVORITE_CALL = 1;
    public static final byte MFB_SETTINGS_VOICE_DIAL = 0;
    public static final byte RWS_BUD_SIDE_LEFT = 0;
    public static final byte RWS_BUD_SIDE_RIGHT = 1;
    public static final byte RWS_CHANNEL_LEFT = 1;
    public static final byte RWS_CHANNEL_MIX = 3;
    public static final byte RWS_CHANNEL_NA = 0;
    public static final byte RWS_CHANNEL_RIGHT = 2;
    public static final byte RWS_STATE_CONNECTED = 1;
    public static final byte RWS_STATE_DISCONNECTED = 0;

    public interface AptType {
        public static final int DSP = 1;
        public static final int LL = 2;
        public static final int NA = 0;
    }

    public interface BtConnectStatus {
        public static final byte A2DP = 1;
        public static final byte AVRCP = 2;
        public static final byte HFHS = 4;
        public static final byte IAP = 32;
        public static final byte MASK = 1;
        public static final byte PBAP = 64;
        public static final byte SPP = 16;
        public static final byte VENDOR = 8;
    }

    public static final class BudRole {
        public static final byte LEFT = 0;
        public static final byte RIGHT = 1;
    }

    public interface CreateConnectionProfile {
        public static final byte A2DP = 1;
        public static final byte AVRCP = 2;
        public static final byte HF = 4;
        public static final byte HS = 4;
        public static final byte IAP = 16;
        public static final byte MASK = 1;
        public static final byte MULTI_SPEAKER = 8;
        public static final byte PBAP = 64;
        public static final byte SPP = 16;
    }

    public interface CreateConnectionType {
        public static final byte A2DP_DEVICE = 2;
        public static final byte DEDICATE_DEVICE_AND_PROFILE = 4;
        public static final byte HF_HS_DEVICE = 1;
        public static final byte LAST_DEVICE = 0;
    }

    public interface DisConnectionProfile {
        public static final byte A2DP = 1;
        public static final byte AVRCP = 2;
        public static final byte HFHS = 4;
        public static final byte IAP = 32;
        public static final byte MASK = 1;
        public static final byte PBAP = 64;
        public static final byte SPP = 16;
        public static final byte VENDOR = 8;
    }

    public interface Operation {
        public static final int CHECK_VIBRATOR = 11;
        public static final int DISABLE_VIBRATOR = 7;
        public static final int ENABLE_VIBRATOR = 6;
        public static final int GET_ANC_BASIC_INFO = 22;
        public static final int GET_ANC_SCENARIO_CHOOSE_INFO = 57;
        public static final int GET_ANC_STATE = 21;
        public static final int GET_APT_NR_ON_OFF = 48;
        public static final int GET_APT_POWER_ON_DELAY_TIME = 52;
        public static final int GET_APT_VOLUME = 39;
        public static final int GET_APT_VOLUME_STATUS = 41;
        public static final int GET_BUD_INFO = 54;
        public static final int GET_CFG_SETTINGS = 23;
        public static final int GET_EAR_DETECTION_STATUS = 35;
        public static final int GET_FW_VERSION = 63;
        public static final int GET_GAMING_MODE_STATE = 45;
        public static final int GET_INFO_REQ = 30;
        public static final int GET_KEY_MMI_MAP = 19;
        public static final int GET_LE_ADDR = 24;
        public static final int GET_LISTENING_MODE_CYCLE = 37;
        public static final int GET_LLAPT_BRIGHTNESS_INFO = 44;
        public static final int GET_LLAPT_BRIGHTNESS_STATUS = 43;
        public static final int GET_LLAPT_SCENARIO_CHOOSE_INFO = 49;
        public static final int GET_MULTI_LINK_CONN_NUM = 55;
        public static final int GET_NAME = 23;
        public static final int GET_OTA_DEVICE_INFO = 25;
        public static final int GET_PACKAGE_ID = 34;
        public static final int GET_RWS_KEY_MMI_MAP = 60;
        public static final int GET_STATUS = 31;
        public static final int GET_SUPPORTED_CALL_STATUS = 18;
        public static final int GET_SUPPORTED_CLICK_TYPE = 17;
        public static final int GET_SUPPORTED_MMI_LIST = 16;
        public static final int GET_VIBRATOR_MODE_PARAMETERS = 12;
        public static final int GET_VIBRATOR_STATUS = 10;
        public static final int QUERY_LLAPT = 32;
        public static final int RESET_KEY_MMI_MAP = 56;
        public static final int RESET_RWS_KEY_MMI_MAP = 62;
        public static final int SET_ANC_SCENARIO_CHOOSE_RESULT = 59;
        public static final int SET_ANC_SCENARIO_CHOOSE_TRY = 58;
        public static final int SET_ANC_STATE = 14;
        public static final int SET_APT_NR_ON_OFF = 47;
        public static final int SET_APT_POWER_ON_DELAY_TIME = 53;
        public static final int SET_APT_VOLUME = 40;
        public static final int SET_CONFIGURATION = 26;
        public static final int SET_FIND_ME_STATE = 1;
        public static final int SET_KEY_MMI_MAP = 20;
        public static final int SET_LISTENING_MODE_CYCLE = 36;
        public static final int SET_LISTENING_MODE_STATE = 38;
        public static final int SET_LLAPT_BRIGHTNESS = 42;
        public static final int SET_LLAPT_SCENARIO_CHOOSE_RESULT = 51;
        public static final int SET_LLAPT_SCENARIO_CHOOSE_TRY = 50;
        public static final int SET_LOW_LATENCY_LEVEL = 46;
        public static final int SET_MMI = 29;
        public static final int SET_RWS_KEY_MMI_MAP = 61;
        public static final int SET_SPATIAL_AUDIO_MODE = 64;
        public static final int SET_VIBRATOR_MODE_PARAMETERS = 13;
        public static final int SET_VOLUME = 4;
        public static final int STOP_VIBRATOR = 9;
        public static final int TOGGLE_LLAPT = 33;
        public static final int TOGGLE_VIBRATOR_AND_VP = 8;
    }

    public static final class RwsDefaultRole {
        public static final byte PRIMARY = 1;
        public static final byte SECONDARY = 2;
        public static final byte SINGLE = 0;
    }

    public interface SoundPressCalibrationResult {
        public static final int FAIL = 2;
        public static final int INVALID = 1;
        public static final int SUCCESS = 0;
    }
}
