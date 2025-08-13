package com.baidu.location.c;

import android.content.SharedPreferences;
import com.baidu.location.Jni;
import com.baidu.location.b.al;
import com.baidu.location.b.t;
import com.baidu.location.h.r;
import com.baidu.location.h.s;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e {
    private static e i;
    private static final String l = r.a + "/conlts.dat";
    private static int m = -1;
    private static int n = -1;
    private static int o = 0;
    private a j = null;
    private long k = 604800;
    public boolean a = true;
    public boolean b = true;
    public boolean c = false;
    public boolean d = true;
    public boolean e = true;
    public boolean f = true;
    public boolean g = true;
    public boolean h = false;

    class a extends com.baidu.location.h.h {
        String a = null;
        boolean b = false;
        boolean c = false;

        public a() {
            this.ei = new HashMap();
        }

        public void a(String str, boolean z) {
            if (this.c) {
                return;
            }
            this.c = true;
            this.a = str;
            this.b = z;
            ExecutorService executorServiceC = al.a().c();
            if (z) {
                a(executorServiceC, true, "loc.map.baidu.com");
            } else if (executorServiceC != null) {
                a(executorServiceC, com.baidu.location.h.e.f);
            } else {
                e(com.baidu.location.h.e.f);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
        @Override // com.baidu.location.h.h
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(boolean r2) {
            /*
                r1 = this;
                if (r2 == 0) goto L17
                java.lang.String r2 = r1.eg
                if (r2 == 0) goto L17
                boolean r2 = r1.b
                if (r2 == 0) goto L12
                com.baidu.location.c.e r2 = com.baidu.location.c.e.this
                byte[] r0 = r1.ek
                com.baidu.location.c.e.a(r2, r0)
                goto L1d
            L12:
                com.baidu.location.c.e r2 = com.baidu.location.c.e.this
                java.lang.String r0 = r1.eg
                goto L1a
            L17:
                com.baidu.location.c.e r2 = com.baidu.location.c.e.this
                r0 = 0
            L1a:
                com.baidu.location.c.e.a(r2, r0)
            L1d:
                java.util.Map<java.lang.String, java.lang.Object> r2 = r1.ei
                if (r2 == 0) goto L26
                java.util.Map<java.lang.String, java.lang.Object> r2 = r1.ei
                r2.clear()
            L26:
                r2 = 0
                r1.c = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.e.a.a(boolean):void");
        }

        @Override // com.baidu.location.h.h
        public void b() {
            Map<String, Object> map;
            String str;
            this.ef = 2;
            String strEncode = Jni.encode(this.a);
            this.a = null;
            if (this.b) {
                map = this.ei;
                str = "grid";
            } else {
                map = this.ei;
                str = "conf";
            }
            map.put("qt", str);
            this.ei.put("req", strEncode);
        }
    }

    private e() {
    }

    public static e a() {
        if (i == null) {
            i = new e();
        }
        return i;
    }

    private void a(int i2) {
        this.a = (i2 & 1) == 1;
        this.b = (i2 & 2) == 2;
        this.c = (i2 & 4) == 4;
        this.d = (i2 & 8) == 8;
        this.f = (i2 & 65536) == 65536;
        this.g = (i2 & 131072) == 131072;
        if ((i2 & 16) == 16) {
            this.e = false;
        }
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        boolean z = true;
        try {
            if (jSONObject.has("ipen") && jSONObject.getInt("ipen") == 0) {
                z = false;
            }
            int i2 = jSONObject.has("ipvt") ? jSONObject.getInt("ipvt") : 14400000;
            int i3 = jSONObject.has("ipvn") ? jSONObject.getInt("ipvn") : 10;
            SharedPreferences.Editor editorEdit = com.baidu.location.f.getServiceContext().getSharedPreferences("MapCoreServicePre", 0).edit();
            editorEdit.putBoolean("ipLocInfoUpload", z);
            editorEdit.putInt("ipValidTime", i2);
            editorEdit.putInt("ipLocInfoUploadTimesPerDay", i3);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr) {
        int i2 = 0;
        if (bArr != null) {
            try {
                if (bArr.length < 640) {
                    s.w = false;
                    s.t = s.r + 0.025d;
                    s.s = s.q - 0.025d;
                } else {
                    s.w = true;
                    s.s = Double.longBitsToDouble(((bArr[7] & 255) << 56) | ((bArr[6] & 255) << 48) | ((bArr[5] & 255) << 40) | ((bArr[4] & 255) << 32) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[0] & 255));
                    s.t = Double.longBitsToDouble(((bArr[15] & 255) << 56) | ((bArr[14] & 255) << 48) | ((bArr[13] & 255) << 40) | ((bArr[12] & 255) << 32) | ((bArr[11] & 255) << 24) | ((bArr[10] & 255) << 16) | ((bArr[9] & 255) << 8) | (255 & bArr[8]));
                    s.v = new byte[625];
                    while (i2 < 625) {
                        s.v[i2] = bArr[i2 + 16];
                        i2++;
                    }
                }
                i2 = 1;
            } catch (Exception unused) {
                return;
            }
        }
        if (i2 != 0) {
            g();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0054 A[Catch: Exception -> 0x040f, TRY_LEAVE, TryCatch #0 {Exception -> 0x040f, blocks: (B:11:0x0037, B:16:0x0045, B:18:0x0054, B:21:0x0061, B:23:0x006e, B:25:0x0072, B:27:0x007a, B:28:0x0082, B:30:0x0086, B:32:0x008e, B:33:0x0096, B:35:0x009a, B:37:0x00a2, B:38:0x00aa, B:40:0x00af, B:42:0x00b7, B:43:0x00bf, B:45:0x00c4, B:47:0x00cc, B:48:0x00d4, B:50:0x00d9, B:52:0x00e1, B:53:0x00e9, B:55:0x00ee, B:57:0x00f6, B:58:0x00fe, B:60:0x0103, B:62:0x010b, B:63:0x0113, B:65:0x0119, B:67:0x0121, B:68:0x0129, B:70:0x012f, B:72:0x0137, B:73:0x013f, B:75:0x0143, B:77:0x014b, B:78:0x0153, B:80:0x0159, B:82:0x0165, B:84:0x0169, B:86:0x0171, B:87:0x0179, B:89:0x017d, B:91:0x0185, B:92:0x018d, B:94:0x0192, B:96:0x019a, B:97:0x01a2, B:99:0x01a7, B:101:0x01af, B:102:0x01b7, B:104:0x01bd, B:106:0x01c9, B:108:0x01cd, B:110:0x01d5, B:111:0x01dd, B:113:0x01e1, B:115:0x01e9, B:116:0x01f1, B:118:0x01f6, B:120:0x01fe, B:121:0x0206, B:123:0x020b, B:125:0x0213, B:126:0x021b, B:128:0x0221, B:130:0x022d, B:132:0x0231, B:134:0x0239, B:135:0x0241, B:137:0x0245, B:139:0x024d, B:140:0x0255, B:142:0x025a, B:144:0x0262, B:145:0x026a, B:147:0x026f, B:149:0x0277, B:150:0x027f, B:152:0x0285, B:154:0x0291, B:156:0x0295, B:158:0x029d, B:159:0x02a5, B:161:0x02a9, B:163:0x02b1, B:164:0x02b9, B:166:0x02be, B:168:0x02c6, B:169:0x02ce, B:171:0x02d3, B:173:0x02db, B:174:0x02e3, B:176:0x02e8, B:178:0x02f0, B:179:0x02f8, B:181:0x02fe, B:183:0x030a, B:185:0x030e, B:187:0x0316, B:189:0x031e, B:190:0x0321, B:191:0x0323, B:193:0x0327, B:195:0x032f, B:197:0x0337, B:198:0x033a, B:199:0x033c, B:201:0x0341, B:203:0x0349, B:204:0x0351, B:206:0x0356, B:208:0x035e, B:209:0x0366, B:211:0x036b, B:213:0x0373, B:215:0x037b, B:216:0x0391, B:217:0x0393, B:219:0x0398, B:221:0x03a0, B:222:0x03a8, B:224:0x03ae, B:226:0x03ba, B:228:0x03be, B:230:0x03c6, B:231:0x03ce, B:233:0x03d2, B:235:0x03da, B:236:0x03e2, B:238:0x03e7, B:240:0x03ef, B:241:0x03f7, B:243:0x03ff), top: B:254:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r18) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 1040
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.e.a(java.lang.String):boolean");
    }

    private void b(int i2) throws IOException {
        File file = new File(l);
        if (!file.exists()) {
            i();
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(4L);
            int i3 = randomAccessFile.readInt();
            int i4 = randomAccessFile.readInt();
            randomAccessFile.seek((i3 * o) + 128);
            byte[] bytes = (com.baidu.location.h.b.e + (char) 0).getBytes();
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes, 0, bytes.length);
            randomAccessFile.writeInt(i2);
            if (i4 == o) {
                randomAccessFile.seek(8L);
                randomAccessFile.writeInt(i4 + 1);
            }
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        n = -1;
        if (str != null) {
            try {
                if (a(str)) {
                    f();
                }
            } catch (Exception unused) {
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("ctr")) {
                    n = Integer.parseInt(jSONObject.getString("ctr"));
                }
            } catch (Exception unused2) {
            }
            try {
                j();
                int i2 = n;
                if (i2 != -1) {
                    b(i2);
                } else {
                    i2 = m;
                    if (i2 == -1) {
                        i2 = -1;
                    }
                }
                if (i2 != -1) {
                    a(i2);
                }
            } catch (Exception unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String str = "&ver=" + s.x + "&usr=" + com.baidu.location.h.b.a().c() + "&app=" + com.baidu.location.h.b.e + "&prod=" + com.baidu.location.h.b.f + "&cnloc=" + t.a().b();
        if (this.j == null) {
            this.j = new a();
        }
        if (s.b()) {
            return;
        }
        this.j.a(str, false);
    }

    private void f() throws IOException {
        String str = r.a + "/config.dat";
        byte[] bytes = String.format(Locale.CHINA, "{\"ver\":\"%d\",\"gps\":\"%.1f|%.1f|%.1f|%.1f|%d|%d|%d|%d|%d|%d|%d\",\"up\":\"%.1f|%.1f|%.1f|%.1f\",\"wf\":\"%d|%.1f|%d|%.1f\",\"ab\":\"%.2f|%.2f|%d|%d\",\"gpc\":\"%d|%d|%d|%d|%d|%d\",\"zxd\":\"%.1f|%.1f|%d|%d|%d\",\"shak\":\"%d|%d|%.1f\",\"dmx\":%d}", Integer.valueOf(s.x), Float.valueOf(s.y), Float.valueOf(s.z), Float.valueOf(s.A), Float.valueOf(s.B), Integer.valueOf(s.C), Integer.valueOf(s.D), Integer.valueOf(s.E), Integer.valueOf(s.F), Integer.valueOf(s.G), Integer.valueOf(s.H), Integer.valueOf(s.I), Float.valueOf(s.J), Float.valueOf(s.K), Float.valueOf(s.L), Float.valueOf(s.M), Integer.valueOf(s.N), Float.valueOf(s.P), Integer.valueOf(s.Q), Float.valueOf(s.R), Float.valueOf(s.S), Float.valueOf(s.T), Integer.valueOf(s.U), Integer.valueOf(s.V), Integer.valueOf(s.aa ? 1 : 0), Integer.valueOf(s.ab ? 1 : 0), Integer.valueOf(s.ac), Integer.valueOf(s.ae), Long.valueOf(s.ak), Integer.valueOf(s.an), Float.valueOf(s.ar), Float.valueOf(s.as), Integer.valueOf(s.at), Integer.valueOf(s.au), Integer.valueOf(s.av), Integer.valueOf(s.ao), Integer.valueOf(s.ap), Float.valueOf(s.aq), Integer.valueOf(s.am)).getBytes();
        try {
            File file = new File(str);
            if (!file.exists()) {
                File file2 = new File(r.a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(0L);
            randomAccessFile2.writeBoolean(true);
            randomAccessFile2.seek(2L);
            randomAccessFile2.writeInt(bytes.length);
            randomAccessFile2.write(bytes);
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    private void g() throws IOException {
        try {
            File file = new File(r.a + "/config.dat");
            if (!file.exists()) {
                File file2 = new File(r.a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(1L);
            randomAccessFile2.writeBoolean(true);
            randomAccessFile2.seek(1024L);
            randomAccessFile2.writeDouble(s.s);
            randomAccessFile2.writeDouble(s.t);
            randomAccessFile2.writeBoolean(s.w);
            if (s.w && s.v != null) {
                randomAccessFile2.write(s.v);
            }
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    private void h() throws IOException {
        try {
            File file = new File(r.a + "/config.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(2L);
                    int i2 = randomAccessFile.readInt();
                    byte[] bArr = new byte[i2];
                    randomAccessFile.read(bArr, 0, i2);
                    a(new String(bArr));
                }
                randomAccessFile.seek(1L);
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(1024L);
                    s.s = randomAccessFile.readDouble();
                    s.t = randomAccessFile.readDouble();
                    s.w = randomAccessFile.readBoolean();
                    if (s.w) {
                        s.v = new byte[625];
                        randomAccessFile.read(s.v, 0, 625);
                    }
                }
                randomAccessFile.close();
            }
        } catch (Exception unused) {
        }
        if (s.o) {
            boolean z = com.baidu.location.f.isServing;
        }
    }

    private void i() throws IOException {
        try {
            File file = new File(l);
            if (file.exists()) {
                return;
            }
            File file2 = new File(r.a);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file.createNewFile()) {
                file = null;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(128);
            randomAccessFile.writeInt(0);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    private void j() throws IOException {
        try {
            File file = new File(l);
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(4L);
                int i2 = randomAccessFile.readInt();
                if (i2 > 3000) {
                    randomAccessFile.close();
                    o = 0;
                    i();
                    return;
                }
                int i3 = randomAccessFile.readInt();
                randomAccessFile.seek(128L);
                byte[] bArr = new byte[i2];
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        break;
                    }
                    randomAccessFile.seek((i2 * i4) + 128);
                    int i5 = randomAccessFile.readInt();
                    if (i5 > 0 && i5 < i2) {
                        randomAccessFile.read(bArr, 0, i5);
                        int i6 = i5 - 1;
                        if (bArr[i6] == 0) {
                            String str = new String(bArr, 0, i6);
                            com.baidu.location.h.b.a();
                            if (str.equals(com.baidu.location.h.b.e)) {
                                m = randomAccessFile.readInt();
                                o = i4;
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    i4++;
                }
                if (i4 == i3) {
                    o = i3;
                }
                randomAccessFile.close();
            }
        } catch (Exception unused) {
        }
    }

    public void b() {
        h();
    }

    public void c() {
    }

    public void d() {
        if (System.currentTimeMillis() - com.baidu.location.h.f.a().c() > this.k * 1000) {
            com.baidu.location.h.f.a().b(System.currentTimeMillis());
            com.baidu.location.g.a.a().postDelayed(new f(this), 1000L);
        }
    }
}
