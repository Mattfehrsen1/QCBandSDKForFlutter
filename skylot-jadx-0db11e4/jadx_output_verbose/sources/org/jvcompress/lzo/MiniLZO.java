package org.jvcompress.lzo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.jvcompress.util.MInt;

/* loaded from: qc_sdk_20250409.aar:classes.jar:org/jvcompress/lzo/MiniLZO.class */
public final class MiniLZO implements LZOConstants {
    public static final int c_top_loop = 1;
    public static final int c_first_literal_run = 2;
    public static final int c_match = 3;
    public static final int c_copy_match = 4;
    public static final int c_match_done = 5;
    public static final int c_match_next = 6;
    public static final int c_eof_found = 7;
    public static final int c_input_overrun = 8;
    public static final int c_output_overrun = 9;
    public static final int c_lookbehind_overrun = 10;
    public static final int c0_top = 1;
    public static final int c0_try_match = 2;
    public static final int c0_literal = 3;
    public static final int c0_match = 4;
    public static final int c0_m3_m4_len = 5;
    public static final int c0_m3_m4_offset = 6;
    public static final int c0_last = 7;
    private static final boolean debug = false;

    private static final int U(byte b) {
        return b & 255;
    }

    public static void main(String[] args) throws IOException {
        int ZERO_FILL = Integer.getInteger("ZERO_FILL", 0).intValue();
        String IFILE = System.getProperty("IFILE", "IFILE");
        String OFILE = System.getProperty("OFILE", "IFILE");
        try {
            File fComp_ = new File(OFILE);
            long len = fComp_.length();
            byte[] buf = new byte[(int) len];
            File fUnComp_ = new File(IFILE);
            byte[] out = new byte[(int) fUnComp_.length()];
            byte[] out_ori = new byte[(int) fUnComp_.length()];
            FileInputStream fComp = new FileInputStream(fComp_);
            FileInputStream fUnComp = new FileInputStream(IFILE);
            int ret = fComp.read(buf);
            if (ret > 0) {
                MInt out_len = new MInt();
                System.out.println("Decompressing byte.length=" + len);
                lzo1x_decompress(buf, (int) len, out, out_len);
                System.out.println("Got decompressed length:" + out_len.v);
                if (ZERO_FILL > 0) {
                    System.out.println("Doing zero fill check");
                    for (int i = 0; i < out_len.v; i++) {
                        if (out[i] != 0) {
                            throw new AssertionError("Decompreesed values not matching to Zero @:" + i);
                        }
                    }
                } else {
                    fUnComp.read(out_ori);
                    for (int i2 = 0; i2 < out_len.v; i2++) {
                        if (out[i2] != out_ori[i2]) {
                            throw new AssertionError("Decompreesed values not matching to Zero @:" + i2);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x003f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:106:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0455 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0455 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0199  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int _lzo1x_1_do_compress(byte[] r6, int r7, byte[] r8, org.jvcompress.util.MInt r9, int[] r10) {
        /*
            Method dump skipped, instructions count: 1124
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jvcompress.lzo.MiniLZO._lzo1x_1_do_compress(byte[], int, byte[], org.jvcompress.util.MInt, int[]):int");
    }

    public static final int lzo1x_1_compress(byte[] in, int in_len, byte[] out, MInt out_len, int[] dict) {
        int t;
        int op = 0;
        if (in_len <= 13) {
            t = in_len;
        } else {
            t = _lzo1x_1_do_compress(in, in_len, out, out_len, dict);
            op = 0 + out_len.v;
        }
        if (t > 0) {
            int ii = (0 + in_len) - t;
            if (op == 0 && t <= 238) {
                int i = op;
                op++;
                out[i] = (byte) (17 + t);
            } else if (t <= 3) {
                int i2 = op - 2;
                out[i2] = (byte) (out[i2] | ((byte) t));
            } else if (t <= 18) {
                int i3 = op;
                op++;
                out[i3] = (byte) (t - 3);
            } else {
                int tt = t - 18;
                int i4 = op;
                int op2 = op + 1;
                out[i4] = 0;
                while (tt > 255) {
                    tt -= 255;
                    int i5 = op2;
                    op2++;
                    out[i5] = 0;
                }
                int i6 = op2;
                op = op2 + 1;
                out[i6] = (byte) tt;
            }
            do {
                int i7 = op;
                op++;
                int i8 = ii;
                ii++;
                out[i7] = in[i8];
                t--;
            } while (t > 0);
        }
        int i9 = op;
        int op3 = op + 1;
        out[i9] = 17;
        int op4 = op3 + 1;
        out[op3] = 0;
        out[op4] = 0;
        out_len.v = (op4 + 1) - 0;
        return 0;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x005f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:112:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x051e  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0192 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x018c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x050a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0504 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int lzo1x_decompress(byte[] r6, int r7, byte[] r8, org.jvcompress.util.MInt r9) {
        /*
            Method dump skipped, instructions count: 1398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jvcompress.lzo.MiniLZO.lzo1x_decompress(byte[], int, byte[], org.jvcompress.util.MInt):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x01fd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x01f7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x055b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0555 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:229:0x056c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0565 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int lzo1x_decompress_safe(byte[] r6, int r7, byte[] r8, org.jvcompress.util.MInt r9) {
        /*
            Method dump skipped, instructions count: 1570
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jvcompress.lzo.MiniLZO.lzo1x_decompress_safe(byte[], int, byte[], org.jvcompress.util.MInt):int");
    }
}
