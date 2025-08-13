package com.baidu.location.h;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes.dex */
public class f {
    static f c;
    String a = "firll.dat";
    int b = 3164;
    int d = 0;
    int e = 20;
    int f = 40;
    int g = 60;
    int h = 80;
    int i = 100;

    private long a(int i) throws Throwable {
        RandomAccessFile randomAccessFile;
        int i2;
        long j;
        String strG = s.g();
        if (strG == null) {
            return -1L;
        }
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(strG + File.separator + this.a, "rw");
            } catch (IOException unused) {
            }
            try {
                randomAccessFile.seek(i);
                i2 = randomAccessFile.readInt();
                j = randomAccessFile.readLong();
            } catch (Exception unused2) {
                randomAccessFile2 = randomAccessFile;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                return -1L;
            } catch (Throwable th) {
                th = th;
                randomAccessFile2 = randomAccessFile;
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (Exception unused4) {
        } catch (Throwable th2) {
            th = th2;
        }
        if (i2 == randomAccessFile.readInt()) {
            try {
                randomAccessFile.close();
            } catch (IOException unused5) {
            }
            return j;
        }
        randomAccessFile.close();
        return -1L;
    }

    public static f a() {
        if (c == null) {
            c = new f();
        }
        return c;
    }

    private void a(int i, long j) throws IOException {
        String strG = s.g();
        if (strG == null) {
            return;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(strG + File.separator + this.a, "rw");
            randomAccessFile.seek(i);
            randomAccessFile.writeInt(this.b);
            randomAccessFile.writeLong(j);
            randomAccessFile.writeInt(this.b);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    public void a(long j) throws IOException {
        a(this.e, j);
    }

    public long b() {
        return a(this.e);
    }

    public void b(long j) throws IOException {
        a(this.g, j);
    }

    public long c() {
        return a(this.g);
    }
}
