package com.baidu.location.indoor.mapversion.b;

import android.content.Context;
import android.location.Location;
import com.king.zxing.util.LogUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public class a {
    private static a a;
    private c b;
    private String c;
    private String e;
    private b f;
    private boolean d = false;
    private String g = "gcj02";
    private boolean h = false;
    private d k = null;
    private HashMap<String, d> i = new HashMap<>();
    private HashMap<String, double[][]> j = new HashMap<>();

    /* renamed from: com.baidu.location.indoor.mapversion.b.a$a, reason: collision with other inner class name */
    public static class C0026a {
        public double a;
        public double b;
        public double c;
        public double d;
        public double e;
        public double f;
        public double g;
        public double h;

        public C0026a(String str) {
            a(str);
        }

        public void a(String str) {
            String[] strArrSplit = str.trim().split("\\|");
            this.a = Double.valueOf(strArrSplit[0]).doubleValue();
            this.b = Double.valueOf(strArrSplit[1]).doubleValue();
            this.c = Double.valueOf(strArrSplit[2]).doubleValue();
            this.d = Double.valueOf(strArrSplit[3]).doubleValue();
            this.e = Double.valueOf(strArrSplit[4]).doubleValue();
            this.f = Double.valueOf(strArrSplit[5]).doubleValue();
            this.g = Double.valueOf(strArrSplit[6]).doubleValue();
            this.h = Double.valueOf(strArrSplit[7]).doubleValue();
        }
    }

    private class b extends Thread {
        private String b;
        private String c;

        public b(String str, String str2) {
            this.b = str;
            this.c = str2;
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x0155 A[Catch: Exception -> 0x015f, TRY_LEAVE, TryCatch #0 {Exception -> 0x015f, blocks: (B:3:0x0001, B:5:0x0012, B:8:0x001b, B:10:0x002e, B:12:0x0045, B:14:0x0059, B:15:0x0068, B:18:0x008f, B:20:0x00a6, B:21:0x00c6, B:23:0x00cc, B:24:0x00d0, B:26:0x00e0, B:35:0x014d, B:37:0x0155, B:27:0x00fa, B:30:0x0105, B:33:0x0134, B:11:0x0036, B:7:0x0018), top: B:43:0x0001 }] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 361
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.mapversion.b.a.b.run():void");
        }
    }

    public interface c {
        void a(boolean z, String str);
    }

    public static class d implements Serializable {
        public String a;
        public String b;
        public C0026a d;
        public C0026a f;
        public short[][] h;
        public String c = "0|3";
        public C0026a e;
        public C0026a g = this.e;
        private String i = "gcj02";

        public d(String str) {
            this.a = str;
        }

        public double a(double d) {
            return (d + this.g.d) * this.g.c;
        }

        public C0026a a() {
            return this.g;
        }

        public void a(String str) {
            C0026a c0026a;
            if (str != null) {
                String lowerCase = str.toLowerCase();
                this.i = lowerCase;
                if (lowerCase.startsWith("wgs84")) {
                    c0026a = this.d;
                } else if (this.i.startsWith("bd09")) {
                    c0026a = this.f;
                } else if (!this.i.startsWith("gcj02")) {
                    return;
                } else {
                    c0026a = this.e;
                }
                this.g = c0026a;
            }
        }

        public double b(double d) {
            return (d + this.g.f) * this.g.e;
        }

        public void b(String str) {
            String[] strArrSplit = str.split("\\t");
            this.b = strArrSplit[1];
            this.d = new C0026a(strArrSplit[2]);
            this.f = new C0026a(strArrSplit[3]);
            C0026a c0026a = new C0026a(strArrSplit[4]);
            this.e = c0026a;
            this.g = c0026a;
            this.h = (short[][]) Array.newInstance((Class<?>) short.class, (int) c0026a.g, (int) this.g.h);
            for (int i = 0; i < this.g.g; i++) {
                for (int i2 = 0; i2 < this.g.h; i2++) {
                    this.h[i][i2] = (short) (strArrSplit[5].charAt((((int) this.g.h) * i) + i2) - '0');
                }
            }
            if (strArrSplit.length >= 7) {
                this.c = strArrSplit[6];
            }
        }

        public double c(double d) {
            return (d / this.g.c) - this.g.d;
        }

        public double d(double d) {
            return (d / this.g.e) - this.g.f;
        }
    }

    private static class e implements HostnameVerifier {
        private HttpsURLConnection a;

        public e(HttpsURLConnection httpsURLConnection) {
            this.a = httpsURLConnection;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            String requestProperty = this.a.getRequestProperty("Host");
            if (requestProperty == null) {
                requestProperty = this.a.getURL().getHost();
            }
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(requestProperty, sSLSession);
        }
    }

    private a(Context context) {
        this.e = "rn";
        this.e = new File(context.getCacheDir(), this.e).getAbsolutePath();
    }

    public static a a() {
        return a;
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    public static String a(File file) throws NoSuchAlgorithmException, IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(map);
            String string = new BigInteger(1, messageDigest.digest()).toString(16);
            fileInputStream.close();
            for (int length = 32 - string.length(); length > 0; length--) {
                string = "0" + string;
            }
            return string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2) {
        return d(str) + "_" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2) {
        try {
            File file = new File(this.e + "/" + a(str, str2));
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str) {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() throws IOException {
        String str = this.c;
        if (str == null) {
            return false;
        }
        File fileF = f(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!com.baidu.location.indoor.mapversion.b.d.a(fileF, byteArrayOutputStream)) {
            return false;
        }
        this.i.clear();
        this.j.clear();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.split("\\t")[1].split("_")[0].equals("geo")) {
                    j(line);
                } else {
                    d dVar = new d(this.c);
                    dVar.b(line);
                    dVar.a(this.g);
                    this.i.put(dVar.b.toLowerCase(), dVar);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        bufferedReader.close();
        return true;
    }

    private String e(String str) {
        File file = new File(this.e);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles(new com.baidu.location.indoor.mapversion.b.b(this, str));
            if (fileArrListFiles != null && fileArrListFiles.length == 1) {
                String[] strArrSplit = fileArrListFiles[0].getName().split("_");
                if (strArrSplit.length < 2) {
                    return null;
                }
                return strArrSplit[1];
            }
            for (int i = 0; fileArrListFiles != null && i < fileArrListFiles.length; i++) {
                fileArrListFiles[i].delete();
            }
        }
        return null;
    }

    private File f(String str) {
        return new File(this.e + "/" + a(str, e(str)));
    }

    private boolean g(String str) {
        File fileF = f(str);
        return fileF.exists() && fileF.length() > 0;
    }

    private boolean h(String str) {
        return System.currentTimeMillis() - f(str).lastModified() > 1296000000;
    }

    private ArrayList<Double> i(String str) {
        double dDoubleValue;
        ArrayList<Double> arrayList = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == ',') {
                int i2 = i + 1;
                i += 2;
                dDoubleValue = Integer.valueOf(str.substring(i2, i)).intValue();
            } else if (str.charAt(i) == '.') {
                int i3 = i + 1;
                i += 4;
                dDoubleValue = Double.valueOf(str.substring(i3, i)).doubleValue();
            } else {
                int i4 = i + 2;
                double dIntValue = Integer.valueOf(str.substring(i, i4)).intValue();
                i = i4;
                dDoubleValue = dIntValue;
            }
            arrayList.add(Double.valueOf(dDoubleValue));
        }
        return arrayList;
    }

    private void j(String str) {
        String[] strArrSplit = str.split("\\t");
        String lowerCase = strArrSplit[1].split("_")[1].toLowerCase();
        try {
            if (this.i.containsKey(lowerCase)) {
                ArrayList<Double> arrayListI = i(strArrSplit[5]);
                int length = this.i.get(lowerCase).h.length;
                int length2 = this.i.get(lowerCase).h[0].length;
                double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    for (int i3 = 0; i3 < length2; i3++) {
                        if (this.i.get(lowerCase).h[i2][i3] <= 0 || this.i.get(lowerCase).h[i2][i3] == 9) {
                            dArr[i2][i3] = 0.0d;
                        } else {
                            dArr[i2][i3] = arrayListI.get(i).doubleValue();
                            i++;
                        }
                    }
                }
                this.j.put(lowerCase.toLowerCase(), dArr);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void k(String str) {
        if (this.d) {
            return;
        }
        this.d = true;
        b bVar = new b(str, e(str));
        this.f = bVar;
        bVar.start();
    }

    public void a(double d2, double d3) {
        if (this.k == null) {
            Location.distanceBetween(d3, d2, d3, d2 + 0.01d, new float[2]);
            Location.distanceBetween(d3, d2, d3 + 0.01d, d2, new float[2]);
            d dVar = new d("outdoor");
            this.k = dVar;
            dVar.b = "out";
            d dVar2 = this.k;
            dVar2.g = new C0026a("0|1.0|" + (r14[0] / 0.01d) + LogUtils.VERTICAL + (-d2) + LogUtils.VERTICAL + (r15[0] / 0.01d) + LogUtils.VERTICAL + (-d3) + "|0|0");
        }
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(String str, c cVar) throws IOException {
        String str2 = this.c;
        if ((str2 == null || !str.equals(str2)) && !this.h) {
            this.b = cVar;
            if (!g(str) || h(str)) {
                k(str);
                return;
            }
            this.c = str;
            d();
            c cVar2 = this.b;
            if (cVar2 != null) {
                cVar2.a(true, "OK");
            }
        }
    }

    public d b(String str) {
        return this.i.get(str.toLowerCase());
    }

    public void b() {
        this.i.clear();
        this.j.clear();
        this.c = null;
        this.d = false;
    }

    public d c() {
        return this.k;
    }

    public double[][] c(String str) {
        return this.j.get(str.toLowerCase());
    }
}
