package org.jvcompress.lzo;

import java.util.Arrays;
import java.util.Random;

/* loaded from: qc_sdk_20250409.aar:classes.jar:org/jvcompress/lzo/Min1Comp.class */
public class Min1Comp {
    private static final int IN_LEN = 1048576;
    private static final int OUT_LEN = 2097152;

    private static void clearDict(int[] dict) {
        Arrays.fill(dict, 0);
    }

    private static String R(long millis, long iBytes, long oBytes) {
        long millis2 = millis + 1;
        return ", millis:" + millis2 + ", MB/sec:" + (((iBytes * 1000) / millis2) / 1000000) + ", ratio:" + (((oBytes + 1) * 100) / iBytes);
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x045d, code lost:
    
        throw new java.lang.AssertionError(r20 + ". Dict-File Data(" + r0 + ")  Decompreesed values not matching to Zero @:" + r29);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jvcompress.lzo.Min1Comp.main(java.lang.String[]):void");
    }

    private static void fillPartillyRandom(int lim, byte[] in, Random ran, boolean rpt) {
        int i = 0;
        do {
            int repeat = rpt ? ran.nextInt(10) : 1;
            byte b = (byte) ran.nextInt(lim);
            for (int j = 0; j < repeat && i < in.length; j++) {
                int i2 = i;
                i++;
                in[i2] = b;
            }
        } while (i < in.length);
    }
}
