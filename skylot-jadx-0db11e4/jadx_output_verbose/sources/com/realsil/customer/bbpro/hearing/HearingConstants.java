package com.realsil.customer.bbpro.hearing;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/hearing/HearingConstants.class */
public final class HearingConstants {

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/hearing/HearingConstants$AptType.class */
    public interface AptType {
        public static final int DSP = 0;
        public static final int LL = 1;
        public static final int NA = 255;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/hearing/HearingConstants$ListeningMode.class */
    public static final class ListeningMode {
        public static final byte ALL_OFF = 0;
        public static final byte DSP_APT = 1;
        public static final byte ANC = 2;
        public static final byte ANC_ON = 2;
        public static final byte LL_APT = 3;
        public static final byte APT_ON = 3;
        public static final byte ANC_DSP_APT = 4;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/hearing/HearingConstants$ListeningModeCycle.class */
    public static final class ListeningModeCycle {
        public static final byte LISTENING_MODE_CYCLE_1 = 0;
        public static final byte LISTENING_MODE_CYCLE_2 = 1;
        public static final byte LISTENING_MODE_CYCLE_3 = 2;
        public static final byte LISTENING_MODE_CYCLE_4 = 3;
        public static final byte LISTENING_MODE_CYCLE_5 = 4;
    }
}
