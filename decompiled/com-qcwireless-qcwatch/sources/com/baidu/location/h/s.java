package com.baidu.location.h;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.bumptech.glide.load.Key;
import com.hjq.permissions.Permission;
import com.king.zxing.util.LogUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class s {
    public static float A = 2.3f;
    public static float B = 3.8f;
    public static int C = 3;
    public static int D = 10;
    public static int E = 2;
    public static int F = 7;
    public static int G = 20;
    public static int H = 70;
    public static int I = 120;
    public static float J = 2.0f;
    public static float K = 10.0f;
    public static float L = 50.0f;
    public static float M = 200.0f;
    public static int N = 16;
    public static int O = 32;
    public static float P = 0.9f;
    public static int Q = 10000;
    public static float R = 0.5f;
    public static float S = 0.0f;
    public static float T = 0.1f;
    public static int U = 30;
    public static int V = 100;
    public static int W = 0;
    public static int X = 0;
    public static int Y = 0;
    public static int Z = 420000;
    public static boolean a = false;
    public static float aA = 0.75f;
    public static double aB = -0.10000000149011612d;
    public static int aC = 0;
    public static int aD = 0;
    public static int aE = 1;
    public static int aF = 1;
    public static int aG = 0;
    public static float aH = 0.8f;
    public static float aI = 0.2f;
    public static int aJ = 0;
    public static int[] aK = null;
    public static boolean aL = false;
    public static int aM = 8;
    public static int aN = 4000;
    public static int aO = 1;
    public static boolean aP = false;
    public static int aQ = -1;
    public static int aR = 10;
    public static int aS = 3;
    public static int aT = 40;
    public static double[] aU = null;
    public static int aV = 1;
    public static int aW = 1;
    public static int aX = 1;
    public static boolean aa = true;
    public static boolean ab = true;
    public static int ac = 20;
    public static int ad = 300;
    public static int ae = 1000;
    public static int af = Integer.MAX_VALUE;
    public static long ag = 900000;
    public static long ah = 420000;
    public static long ai = 180000;
    public static long aj = 0;
    public static long ak = 15;
    public static long al = 300000;
    public static int am = 1000;
    public static int an = 0;
    public static int ao = 30000;
    public static int ap = 30000;
    public static float aq = 10.0f;
    public static float ar = 6.0f;
    public static float as = 10.0f;
    public static int at = 60;
    public static int au = 70;
    public static int av = 6;
    public static String aw = null;
    public static boolean ax = false;
    public static int ay = 16;
    public static int az = 15;
    public static boolean b = false;
    public static boolean c = false;
    public static int d = 0;
    public static String e = "no";
    public static int f = 4;
    public static boolean g = false;
    public static boolean h = false;
    public static boolean i = false;
    public static boolean j = false;
    public static boolean k = false;
    public static boolean l = false;
    public static String m = "gcj02";
    public static String n = "";
    public static boolean o = true;
    public static int p = 3;
    public static double q = 0.0d;
    public static double r = 0.0d;
    public static double s = 0.0d;
    public static double t = 0.0d;
    public static int u = 0;
    public static byte[] v = null;
    public static boolean w = false;
    public static int x = 0;
    public static float y = 1.1f;
    public static float z = 2.2f;
    private static String bd = Build.MANUFACTURER;
    public static boolean aY = false;
    public static String aZ = null;
    public static int ba = -1;
    public static String bb = null;
    public static String bc = null;

    public static double a(double d2, double d3, double d4, double d5) {
        Location.distanceBetween(d2, d3, d4, d5, new float[1]);
        return r0[0];
    }

    public static int a(char c2) {
        switch (c2) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                switch (c2) {
                    case 'A':
                        return 10;
                    case 'B':
                        return 11;
                    case 'C':
                        return 12;
                    case 'D':
                        return 13;
                    case 'E':
                        return 14;
                    case 'F':
                        return 15;
                    default:
                        switch (c2) {
                            case 'a':
                                return 10;
                            case 'b':
                                return 11;
                            case 'c':
                                return 12;
                            case 'd':
                                return 13;
                            case 'e':
                                return 14;
                            case 'f':
                                return 15;
                            default:
                                return 0;
                        }
                }
        }
    }

    public static int a(Context context, String str) {
        return !(context.checkPermission(str, Process.myPid(), Process.myUid()) == 0) ? 0 : 1;
    }

    public static int a(Object obj, String str) throws Exception {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, null);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, null)).intValue();
    }

    public static int a(String str, String str2, String str3) {
        int iIndexOf;
        int length;
        int iIndexOf2;
        String strSubstring;
        if (str != null && !str.equals("") && (iIndexOf = str.indexOf(str2)) != -1 && (iIndexOf2 = str.indexOf(str3, (length = iIndexOf + str2.length()))) != -1 && (strSubstring = str.substring(length, iIndexOf2)) != null && !strSubstring.equals("")) {
            try {
                return Integer.parseInt(strSubstring);
            } catch (NumberFormatException unused) {
            }
        }
        return Integer.MIN_VALUE;
    }

    public static String a() {
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(5);
        return String.format(Locale.CHINA, "%d-%02d-%02d %02d:%02d:%02d", Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(i2), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)), Integer.valueOf(calendar.get(13)));
    }

    public static String a(com.baidu.location.f.a aVar, com.baidu.location.f.p pVar, Location location, String str, int i2) {
        return a(aVar, pVar, location, str, i2, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00de A[Catch: Exception -> 0x00e1, TRY_LEAVE, TryCatch #0 {Exception -> 0x00e1, blocks: (B:43:0x009f, B:47:0x00bd, B:50:0x00c3, B:51:0x00c6, B:56:0x00d2, B:58:0x00d6, B:60:0x00da, B:61:0x00de), top: B:65:0x009f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(com.baidu.location.f.a r4, com.baidu.location.f.p r5, android.location.Location r6, java.lang.String r7, int r8, boolean r9) {
        /*
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r0 = 2048(0x800, float:2.87E-42)
            r9.<init>(r0)
            if (r4 == 0) goto L16
            com.baidu.location.f.h r0 = com.baidu.location.f.h.a()
            java.lang.String r0 = r0.b(r4)
            if (r0 == 0) goto L16
            r9.append(r0)
        L16:
            r0 = 1
            if (r5 == 0) goto L2f
            com.baidu.location.f.h r1 = com.baidu.location.f.h.a()
            if (r8 != 0) goto L22
            int r2 = com.baidu.location.h.s.N
            goto L24
        L22:
            int r2 = com.baidu.location.h.s.az
        L24:
            int r3 = com.baidu.location.h.s.ay
            java.lang.String r1 = r1.a(r2, r0, r5, r3)
            if (r1 == 0) goto L2f
            r9.append(r1)
        L2f:
            if (r6 == 0) goto L45
            int r1 = com.baidu.location.h.s.d
            if (r1 == 0) goto L3c
            if (r8 == 0) goto L3c
            java.lang.String r1 = com.baidu.location.f.e.c(r6)
            goto L40
        L3c:
            java.lang.String r1 = com.baidu.location.f.e.b(r6)
        L40:
            if (r1 == 0) goto L45
            r9.append(r1)
        L45:
            r1 = 0
            if (r8 != 0) goto L49
            r1 = 1
        L49:
            com.baidu.location.h.b r8 = com.baidu.location.h.b.a()
            java.lang.String r8 = r8.a(r1)
            if (r8 == 0) goto L56
            r9.append(r8)
        L56:
            if (r7 == 0) goto L5b
            r9.append(r7)
        L5b:
            com.baidu.location.c.d r7 = com.baidu.location.c.d.a()
            java.lang.String r7 = r7.d()
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L71
            java.lang.String r8 = "&bc="
            r9.append(r8)
            r9.append(r7)
        L71:
            com.baidu.location.f.e r7 = com.baidu.location.f.e.a()
            java.lang.String r7 = r7.n()
            r9.append(r7)
            com.baidu.location.f.h r7 = com.baidu.location.f.h.a()
            java.lang.String r4 = r7.a(r4)
            if (r4 == 0) goto L96
            int r7 = r4.length()
            int r8 = r9.length()
            int r7 = r7 + r8
            r8 = 2000(0x7d0, float:2.803E-42)
            if (r7 >= r8) goto L96
            r9.append(r4)
        L96:
            java.lang.String r4 = r9.toString()
            r7 = 3
            if (r6 == 0) goto Lde
            if (r5 == 0) goto Lde
            float r6 = r6.getSpeed()     // Catch: java.lang.Exception -> Le1
            int r8 = com.baidu.location.h.s.d     // Catch: java.lang.Exception -> Le1
            com.baidu.location.f.h r9 = com.baidu.location.f.h.a()     // Catch: java.lang.Exception -> Le1
            int r9 = r9.a(r5)     // Catch: java.lang.Exception -> Le1
            int r1 = r5.a()     // Catch: java.lang.Exception -> Le1
            boolean r5 = r5.d     // Catch: java.lang.Exception -> Le1
            float r2 = com.baidu.location.h.s.ar     // Catch: java.lang.Exception -> Le1
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 >= 0) goto Lc6
            if (r8 == r0) goto Lbd
            if (r8 != 0) goto Lc6
        Lbd:
            int r2 = com.baidu.location.h.s.at     // Catch: java.lang.Exception -> Le1
            if (r9 < r2) goto Lc3
            if (r5 != r0) goto Lc6
        Lc3:
            com.baidu.location.h.s.p = r0     // Catch: java.lang.Exception -> Le1
            goto Le3
        Lc6:
            float r5 = com.baidu.location.h.s.as     // Catch: java.lang.Exception -> Le1
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 >= 0) goto Lde
            if (r8 == r0) goto Ld2
            if (r8 == 0) goto Ld2
            if (r8 != r7) goto Lde
        Ld2:
            int r5 = com.baidu.location.h.s.au     // Catch: java.lang.Exception -> Le1
            if (r9 < r5) goto Lda
            int r5 = com.baidu.location.h.s.av     // Catch: java.lang.Exception -> Le1
            if (r1 <= r5) goto Lde
        Lda:
            r5 = 2
            com.baidu.location.h.s.p = r5     // Catch: java.lang.Exception -> Le1
            goto Le3
        Lde:
            com.baidu.location.h.s.p = r7     // Catch: java.lang.Exception -> Le1
            goto Le3
        Le1:
            com.baidu.location.h.s.p = r7
        Le3:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.h.s.a(com.baidu.location.f.a, com.baidu.location.f.p, android.location.Location, java.lang.String, int, boolean):java.lang.String");
    }

    public static String a(String str) {
        return Jni.en1(n + ";" + str);
    }

    public static String a(byte[] bArr, String str, boolean z2) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (z2) {
                hexString = hexString.toUpperCase();
            }
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
            sb.append(str);
        }
        return sb.toString();
    }

    public static String a(byte[] bArr, boolean z2) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return a(messageDigest.digest(), "", z2);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean a(double d2, double d3) {
        return Math.abs(d2 - d3) <= 1.192092896E-7d;
    }

    public static boolean a(float f2, float f3) {
        return Math.abs(f2 - f3) <= 1.1920929E-7f;
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo[] allNetworkInfo = null;
            try {
                allNetworkInfo = connectivityManager.getAllNetworkInfo();
            } catch (Exception unused) {
            }
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean a(Location location) {
        String str;
        if (location == null || (str = bd) == null || !"huawei".equalsIgnoreCase(str)) {
            return false;
        }
        try {
            Bundle extras = location.getExtras();
            if (extras != null) {
                return (extras.getInt("SourceType") & 128) == 128;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(BDLocation bDLocation) {
        int locType = bDLocation.getLocType();
        return (locType > 100 && locType < 200) || locType == 62;
    }

    public static boolean a(int[] iArr) {
        if (iArr != null && iArr.length >= 18) {
            for (int i2 : iArr) {
                if (i2 == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static int b(Context context) {
        try {
            if (Build.VERSION.SDK_INT > 17) {
                return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0);
            }
            return 2;
        } catch (Exception unused) {
            return 2;
        }
    }

    public static Object b(Object obj, String str) throws Exception {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    public static String b(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                }
                if (TextUtils.isEmpty(line)) {
                    return null;
                }
                return line;
            } catch (Exception unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean b() {
        return false;
    }

    public static boolean b(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] b(byte[] r4) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r4 == 0) goto L50
            int r1 = r4.length
            if (r1 != 0) goto L7
            goto L50
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r4)
            r4 = 0
            java.util.zip.GZIPInputStream r3 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2f
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2f
            r4 = 2048(0x800, float:2.87E-42)
            byte[] r4 = new byte[r4]     // Catch: java.io.IOException -> L29 java.lang.Throwable -> L44
        L1b:
            int r2 = r3.read(r4)     // Catch: java.io.IOException -> L29 java.lang.Throwable -> L44
            if (r2 < 0) goto L25
            r1.write(r4, r0, r2)     // Catch: java.io.IOException -> L29 java.lang.Throwable -> L44
            goto L1b
        L25:
            r3.close()     // Catch: java.io.IOException -> L3b
            goto L3f
        L29:
            r4 = move-exception
            goto L32
        L2b:
            r0 = move-exception
            r3 = r4
            r4 = r0
            goto L45
        L2f:
            r0 = move-exception
            r3 = r4
            r4 = r0
        L32:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L44
            if (r3 == 0) goto L3f
            r3.close()     // Catch: java.io.IOException -> L3b
            goto L3f
        L3b:
            r4 = move-exception
            r4.printStackTrace()
        L3f:
            byte[] r4 = r1.toByteArray()
            return r4
        L44:
            r4 = move-exception
        L45:
            if (r3 == 0) goto L4f
            r3.close()     // Catch: java.io.IOException -> L4b
            goto L4f
        L4b:
            r0 = move-exception
            r0.printStackTrace()
        L4f:
            throw r4
        L50:
            byte[] r4 = new byte[r0]
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.h.s.b(byte[]):byte[]");
    }

    public static int c(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return -2;
        }
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode", -1);
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long c(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime() / 1000;
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet6Address) && inetAddressNextElement.getHostAddress() != null && !inetAddressNextElement.getHostAddress().startsWith("fe80:")) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d() {
        return e.k;
    }

    public static boolean d(Context context) {
        int iA;
        if (context == null) {
            return true;
        }
        try {
            iA = a(context, Permission.ACCESS_COARSE_LOCATION);
        } catch (Exception e2) {
            e2.printStackTrace();
            iA = 1;
        }
        boolean z2 = iA == 1;
        if (z2 && Build.VERSION.SDK_INT >= 23) {
            try {
                if (Settings.Secure.getInt(context.getContentResolver(), "location_mode", 1) == 0) {
                    return false;
                }
            } catch (Exception unused) {
            }
        }
        return z2;
    }

    public static byte[] d(String str) {
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) ((a(charArray[i3 + 1]) | (a(charArray[i3]) << 4)) & 255);
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String e() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            r2 = 0
            if (r0 > r1) goto L21
            java.lang.String r0 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Exception -> L1d
            java.lang.String r3 = "mounted"
            boolean r0 = r0.equals(r3)     // Catch: java.lang.Exception -> L1d
            if (r0 == 0) goto L21
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Exception -> L1d
            java.lang.String r0 = r0.getPath()     // Catch: java.lang.Exception -> L1d
            goto L22
        L1d:
            r0 = move-exception
            r0.printStackTrace()
        L21:
            r0 = r2
        L22:
            if (r0 != 0) goto L3e
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 <= r1) goto L3e
            android.content.Context r1 = com.baidu.location.f.getServiceContext()
            if (r1 == 0) goto L3e
            android.content.Context r0 = com.baidu.location.f.getServiceContext()     // Catch: java.lang.Exception -> L3d
            java.lang.String r1 = android.os.Environment.DIRECTORY_MOVIES     // Catch: java.lang.Exception -> L3d
            java.io.File r0 = r0.getExternalFilesDir(r1)     // Catch: java.lang.Exception -> L3d
            java.lang.String r0 = r0.getAbsolutePath()     // Catch: java.lang.Exception -> L3d
            goto L3e
        L3d:
            r0 = r2
        L3e:
            if (r0 == 0) goto L65
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L60
            r1.<init>()     // Catch: java.lang.Exception -> L60
            r1.append(r0)     // Catch: java.lang.Exception -> L60
            java.lang.String r3 = "/baidu/tempdata"
            r1.append(r3)     // Catch: java.lang.Exception -> L60
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L60
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> L60
            r3.<init>(r1)     // Catch: java.lang.Exception -> L60
            boolean r1 = r3.exists()     // Catch: java.lang.Exception -> L60
            if (r1 != 0) goto L65
            r3.mkdirs()     // Catch: java.lang.Exception -> L60
            goto L65
        L60:
            r0 = move-exception
            r0.printStackTrace()
            goto L66
        L65:
            r2 = r0
        L66:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.h.s.e():java.lang.String");
    }

    public static String e(Context context) {
        int iA = a(context, Permission.ACCESS_COARSE_LOCATION);
        int iA2 = a(context, Permission.ACCESS_FINE_LOCATION);
        int iA3 = a(context, Permission.READ_PHONE_STATE);
        if (Build.VERSION.SDK_INT < 29) {
            return "&per=" + iA + LogUtils.VERTICAL + iA2 + LogUtils.VERTICAL + iA3;
        }
        return "&per=" + iA + LogUtils.VERTICAL + iA2 + LogUtils.VERTICAL + iA3 + LogUtils.VERTICAL + a(context, Permission.ACCESS_BACKGROUND_LOCATION);
    }

    public static String e(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("enc3")) {
                return new String(b(Base64.decode(jSONObject.optString("enc3").getBytes(), 0)), Key.STRING_CHARSET_NAME);
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public static String f() {
        String strE = e();
        if (strE == null) {
            return null;
        }
        return strE + "/baidu/tempdata";
    }

    public static String f(Context context) {
        int type = -1;
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    type = activeNetworkInfo.getType();
                }
            } catch (Throwable unused) {
            }
        }
        return "&netc=" + type;
    }

    public static String g() {
        try {
            File file = new File(com.baidu.location.f.getServiceContext().getFilesDir() + File.separator + "lldt");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    public static int h(Context context) {
        int iA = a(context, Permission.ACCESS_FINE_LOCATION) | a(context, Permission.ACCESS_COARSE_LOCATION);
        if (c(context) != 0 && iA == 1) {
            return 1;
        }
        if (c(context) == 0 || iA == 1) {
            return (c(context) >= 1 || iA != 1) ? 0 : -1;
        }
        return -2;
    }

    public static String h() {
        try {
            File file = new File(com.baidu.location.f.getServiceContext().getFilesDir() + File.separator + "/baidu/tempdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            return com.baidu.location.f.getServiceContext().getFilesDir().getPath();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String i() {
        try {
            File file = new File(com.baidu.location.f.getServiceContext().getFilesDir() + File.separator + "/baidu/tempdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            return com.baidu.location.f.getServiceContext().getFilesDir().getPath() + File.separator + "/baidu/tempdata";
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean i(Context context) {
        NetworkCapabilities networkCapabilities;
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (Build.VERSION.SDK_INT >= 29) {
                Network activeNetwork = connectivityManager.getActiveNetwork();
                return activeNetwork != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16);
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String j() {
        return b("ro.mediatek.platform");
    }

    public static SSLSocketFactory k() throws Exception {
        TrustManager[] trustManagerArr = {new t()};
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        return sSLContext.getSocketFactory();
    }

    public static boolean l() {
        if (com.baidu.location.f.getServiceContext() == null) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 31 && a(com.baidu.location.f.getServiceContext(), Permission.ACCESS_FINE_LOCATION) == 0 && a(com.baidu.location.f.getServiceContext(), Permission.ACCESS_COARSE_LOCATION) == 1;
    }
}
