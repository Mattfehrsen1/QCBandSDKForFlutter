package com.oudmon.ble.base.communication;

import android.util.Log;
import java.util.Arrays;
import org.jvcompress.lzo.MiniLZO;
import org.jvcompress.util.MInt;

/* loaded from: classes3.dex */
public class CompressUtils {
    private static final String TAG = "Jxr35";

    public static byte[] compress(byte[] bArr) {
        try {
            byte[] bArr2 = new byte[bArr.length + (bArr.length / 16) + 64 + 3];
            MInt mInt = new MInt();
            MiniLZO.lzo1x_1_compress(bArr, bArr.length, bArr2, mInt, new int[65536]);
            return Arrays.copyOfRange(bArr2, 0, mInt.v);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "compress.. FileNotFoundException");
            return new byte[0];
        }
    }
}
