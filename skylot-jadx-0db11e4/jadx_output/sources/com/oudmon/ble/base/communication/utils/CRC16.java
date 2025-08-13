package com.oudmon.ble.base.communication.utils;

import com.realsil.customer.audioconnect.localplayback.LocalPlaybackTransferEngine;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/utils/CRC16.class */
public class CRC16 {
    public static int calcCrc16(byte[] bufData) {
        int i;
        int CRC = 65535;
        if (bufData.length == 0) {
            return LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
        }
        for (byte b : bufData) {
            CRC ^= b & 255;
            for (int j = 0; j < 8; j++) {
                if ((CRC & 1) != 0) {
                    i = (CRC >> 1) ^ 40961;
                } else {
                    i = CRC >> 1;
                }
                CRC = i;
            }
        }
        return CRC & LocalPlaybackTransferEngine.MAX_SEQUENCE_NUMBER;
    }
}
