package com.oudmon.ble.base.bluetooth.spp;

import java.util.UUID;

/* loaded from: classes3.dex */
public class RtkSppConstants {
    public static int ERR_DEVICE_CONNECT_FAILED = 33;
    public static int ERR_DEVICE_DISCONNECTED = 32;
    public static final String EXTRA_BUD_ROLE = "com.realsil.android.extra.BUD_ROLE";
    public static final String EXTRA_BUFFER_CHECK_SIZE = "com.realsil.android.extra.BUFFER_CHECK_SIZE";
    public static final String EXTRA_PROTOCOL_VERSION = "com.realsil.android.extra.PROTOCOL_VERSION";
    public static final String EXTRA_RWS_STATUS = "com.realsil.android.extra.RWS_STATUS";
    public static final String EXTRA_SEND_PACKET_SIZE = "com.realsil.android.extra.SEND_PACKET_SIZE";
    public static final String EXTRA_SUPPORT_FORMATS = "com.realsil.android.extra.SUPPORT_FORMATS";
    public static int FILE_TYPE_HEADER_BIN = 0;
    public static int FILE_TYPE_NAME_BIN = 1;
    public static final String HEADER_BIN_NAME = "header.bin";
    public static final String NAME_BIN_NAME = "name.bin";
    public static int RESULT_SUCCESS = 1;
    public static final int RWS_STATUS_SINGLE = 0;
    public static final int STATE_DATA_PREPARED = 264;
    public static final int STATE_DATA_SYNC_FAILED = 265;
    public static final int STATE_DATA_SYNC_MASK = 512;
    public static final int STATE_DATA_SYNC_PROCESSING = 513;
    public static final int STATE_DEVICE_CONNECTED = 263;
    public static final int STATE_DEVICE_CONNECTING = 261;
    public static final int STATE_DEVICE_DISCONNECTED = 260;
    public static final int STATE_DEVICE_DISCONNECTING = 262;
    public static final int STATE_IDLE_MASK = 256;
    public static final int STATE_INIT = 257;
    public static UUID sppUUID = UUID.fromString("6A24EEAB-4B65-4693-986B-3C26C352264F");
}
