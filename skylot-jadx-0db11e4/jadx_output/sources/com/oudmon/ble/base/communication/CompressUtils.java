package com.oudmon.ble.base.communication;

import android.util.Log;
import java.util.Arrays;
import org.jvcompress.lzo.MiniLZO;
import org.jvcompress.util.MInt;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/CompressUtils.class */
public class CompressUtils {
    private static final String TAG = "Jxr35";

    public static byte[] compress(byte[] input) {
        try {
            byte[] output = new byte[input.length + (input.length / 16) + 64 + 3];
            int[] dict = new int[65536];
            MInt mInt = new MInt();
            MiniLZO.lzo1x_1_compress(input, input.length, output, mInt, dict);
            byte[] compress = Arrays.copyOfRange(output, 0, mInt.v);
            Log.i(TAG, "compress.. mInt: " + mInt.v);
            return compress;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "compress.. FileNotFoundException");
            return new byte[0];
        }
    }
}
