package com.oudmon.ble.base.bluetooth;

import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/LargeDataParser.class */
public class LargeDataParser {
    private static LargeDataParser instance;
    public static String uuid_notify = "de5bf729-d711-4e47-af26-65e3012a5dc7";
    public byte[] tempData;
    public int dataLength = 0;

    private LargeDataParser() {
    }

    public static LargeDataParser getInstance() {
        if (instance == null) {
            synchronized (LargeDataParser.class) {
                if (instance == null) {
                    instance = new LargeDataParser();
                }
            }
        }
        return instance;
    }

    public void parseBigLargeData(String uuid, byte[] value) {
        if (uuid_notify.equals(uuid)) {
            if (value.length >= 6 && (value[0] & 255) == 188) {
                this.dataLength = ByteUtil.bytesToInt(Arrays.copyOfRange(value, 2, 4));
                if (value.length - 6 >= this.dataLength) {
                    this.tempData = Arrays.copyOfRange(value, 0, value.length);
                    parseData(this.tempData);
                    this.tempData = new byte[0];
                    return;
                }
                this.tempData = Arrays.copyOfRange(value, 0, value.length);
                return;
            }
            this.tempData = ByteUtil.concat(this.tempData, value);
            if (this.tempData.length - 6 == this.dataLength) {
                parseData(this.tempData);
                this.tempData = new byte[0];
            }
        }
    }

    public void parseData(byte[] data) {
        ILargeDataResponse response;
        byte b = data[1];
        if ((data[0] & 255) == 188 && (response = LargeDataHandler.getInstance().getRespMap().get(Integer.valueOf(b))) != null) {
            response.parseData(b, data);
        }
    }
}
