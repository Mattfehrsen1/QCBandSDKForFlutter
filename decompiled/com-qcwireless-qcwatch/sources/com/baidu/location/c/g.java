package com.baidu.location.c;

import android.os.Environment;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.Thread;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes.dex */
public class g implements Thread.UncaughtExceptionHandler {
    private static g a;
    private int b = 0;

    private g() {
    }

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    private String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    private void a(File file, String str, String str2) throws IOException {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(280L);
            randomAccessFile.writeInt(12346);
            randomAccessFile.seek(300L);
            randomAccessFile.writeLong(System.currentTimeMillis());
            byte[] bytes = str.getBytes();
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes, 0, bytes.length);
            randomAccessFile.seek(600L);
            byte[] bytes2 = str2.getBytes();
            randomAccessFile.writeInt(bytes2.length);
            randomAccessFile.write(bytes2, 0, bytes2.length);
            if (!a(str, str2)) {
                randomAccessFile.seek(280L);
                randomAccessFile.writeInt(1326);
            }
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    private boolean a(String str, String str2) throws IOException {
        HttpsURLConnection httpsURLConnection;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !com.baidu.location.f.h.a().m()) {
            return false;
        }
        try {
            URL url = new URL(com.baidu.location.h.e.c);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("e0");
            stringBuffer.append("=");
            stringBuffer.append(str);
            stringBuffer.append("&");
            stringBuffer.append("e1");
            stringBuffer.append("=");
            stringBuffer.append(str2);
            stringBuffer.append("&");
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setConnectTimeout(com.baidu.location.h.a.a);
            httpsURLConnection.setReadTimeout(com.baidu.location.h.a.a);
            httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpsURLConnection.setRequestProperty("Accept-Charset", Key.STRING_CHARSET_NAME);
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            outputStream.write(stringBuffer.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception unused) {
        }
        return httpsURLConnection.getResponseCode() == 200;
    }

    public void b() {
        String str;
        try {
            File file = new File((Environment.getExternalStorageDirectory().getPath() + "/traces") + "/error_fs2.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(280L);
                if (1326 == randomAccessFile.readInt()) {
                    randomAccessFile.seek(308L);
                    int i = randomAccessFile.readInt();
                    String str2 = null;
                    if (i <= 0 || i >= 2048) {
                        str = null;
                    } else {
                        byte[] bArr = new byte[i];
                        randomAccessFile.read(bArr, 0, i);
                        str = new String(bArr, 0, i);
                    }
                    randomAccessFile.seek(600L);
                    int i2 = randomAccessFile.readInt();
                    if (i2 > 0 && i2 < 2048) {
                        byte[] bArr2 = new byte[i2];
                        randomAccessFile.read(bArr2, 0, i2);
                        str2 = new String(bArr2, 0, i2);
                    }
                    if (a(str, str2)) {
                        randomAccessFile.seek(280L);
                        randomAccessFile.writeInt(12346);
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void uncaughtException(java.lang.Thread r9, java.lang.Throwable r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.g.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
