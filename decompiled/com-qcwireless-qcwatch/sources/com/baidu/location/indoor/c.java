package com.baidu.location.indoor;

import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.king.zxing.util.LogUtils;
import java.util.Arrays;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c {
    private double A;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private String K;
    private String L;
    private int O;
    private String P;
    public String a;
    public String b;
    public double c;
    public double d;
    public double e;
    public double f;
    public String g;
    public String[] h;
    public String i;
    public String j;
    public int k;
    public String l;
    public String m;
    public b n;
    private String[] o;
    private String[] p;
    private double q;
    private double r;
    private int s;
    private int t;
    private String u;
    private String v;
    private String w;
    private double y;
    private boolean z;
    private int x = 8;
    private double[] M = null;
    private double[] N = null;

    public enum a {
        UNKNOWN(0),
        NORMAL(1),
        AUTH(2),
        BIG_RECT(4),
        RING_RECT(8),
        AOA_RECT(16);

        private int g;

        a(int i) {
            this.g = 0;
            this.g = i;
        }

        public int a() {
            return this.g;
        }
    }

    public class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;

        public b() {
        }

        public void a(String str) {
            this.a = str;
        }

        public void b(String str) {
            this.b = str;
        }

        public void c(String str) {
            this.c = str;
        }

        public void d(String str) {
            this.d = str;
        }

        public void e(String str) {
            this.e = str;
        }

        public String toString() {
            return "OfflineFileInfo{fileUrl='" + this.a + "', zipMd5='" + this.b + "', modleMd5='" + this.c + "', dataMd5='" + this.d + "', dictMd5='" + this.e + "'}";
        }
    }

    public c(JSONObject jSONObject) throws JSONException {
        String string;
        String[] strArrSplit;
        JSONObject jSONObjectOptJSONObject;
        this.s = 100;
        this.t = 0;
        this.u = "default";
        this.v = "default";
        this.w = "0|0";
        this.y = 1.0d;
        this.z = true;
        this.A = 0.0d;
        this.B = false;
        this.C = false;
        this.D = 2;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
        this.K = "";
        this.L = "";
        this.O = 0;
        if (jSONObject != null) {
            try {
                if (jSONObject.has("rect_type")) {
                    this.F = jSONObject.optInt("rect_type");
                    if (jSONObject.has("aoa_device_path")) {
                        this.P = jSONObject.getString("aoa_device_path");
                    }
                    if ((this.F & a.AUTH.a()) == a.AUTH.a()) {
                        this.G = 1;
                    } else {
                        this.G = 0;
                    }
                    if ((this.F & a.BIG_RECT.a()) == a.BIG_RECT.a()) {
                        this.H = 1;
                    } else {
                        this.H = 0;
                    }
                    if ((this.F & a.RING_RECT.a()) == a.RING_RECT.a()) {
                        this.I = 1;
                    } else {
                        this.I = 0;
                    }
                    a.NORMAL.a();
                    a.NORMAL.a();
                }
                if ((this.F & a.AOA_RECT.a()) == a.AOA_RECT.a()) {
                    this.t = 1;
                } else {
                    this.t = 0;
                }
                if (jSONObject.has("bldg")) {
                    this.a = jSONObject.optString("bldg");
                }
                if (jSONObject.has("bid")) {
                    this.b = jSONObject.optString("bid");
                }
                if (jSONObject.has("inout_points")) {
                    this.l = jSONObject.optString("inout_points");
                }
                if (jSONObject.has("off_ble_ver")) {
                    this.m = jSONObject.optString("off_ble_ver");
                }
                if (jSONObject.has("max_scan_num")) {
                    this.s = jSONObject.optInt("max_scan_num");
                }
                if (jSONObject.has("scenario_detector")) {
                    this.u = jSONObject.optString("scenario_detector");
                }
                if (jSONObject.has("passageway_info")) {
                    this.v = jSONObject.optString("passageway_info");
                }
                if (jSONObject.has("offloc_parameter")) {
                    this.w = jSONObject.optString("offloc_parameter");
                }
                if (jSONObject.has("uuid")) {
                    this.g = jSONObject.getString("uuid").replace("-", "");
                }
                if (jSONObject.has("uuids")) {
                    String string2 = jSONObject.getString("uuids");
                    if (!"".equals(string2)) {
                        if (string2.contains(LogUtils.VERTICAL)) {
                            String[] strArrSplit2 = string2.split("\\|");
                            for (int i = 0; i < strArrSplit2.length; i++) {
                                strArrSplit2[i] = strArrSplit2[i].replace("-", "").toUpperCase();
                            }
                            this.h = strArrSplit2;
                        } else {
                            this.h = new String[]{string2.replace("-", "").toUpperCase()};
                        }
                    }
                }
                if (jSONObject.has("encrypt_uuids")) {
                    String strOptString = jSONObject.optString("encrypt_uuids");
                    if (!"".equals(strOptString)) {
                        if (strOptString.contains(LogUtils.VERTICAL)) {
                            String[] strArrSplit3 = strOptString.split("\\|");
                            for (int i2 = 0; i2 < strArrSplit3.length; i2++) {
                                strArrSplit3[i2] = strArrSplit3[i2].replace("-", "").toUpperCase();
                            }
                            this.o = strArrSplit3;
                        } else {
                            this.o = new String[]{strOptString.replace("-", "").toUpperCase()};
                        }
                    }
                }
                a(this.h, this.o);
                if (jSONObject.has("support_types")) {
                    this.i = jSONObject.optString("support_types");
                }
                if (jSONObject.has("conf_type")) {
                    this.j = jSONObject.optString("conf_type");
                }
                if (jSONObject.has("offline_data_mode")) {
                    this.D = jSONObject.optInt("offline_data_mode");
                }
                if (jSONObject.has("algo_rects")) {
                    this.L = jSONObject.optString("algo_rects");
                    if ("zqxfbsd".equals(this.a)) {
                        a(this.L);
                    }
                }
                if (jSONObject.has("is_need_walk_navi_link")) {
                    this.O = jSONObject.optInt("is_need_walk_navi_link");
                }
                String str = "indoor_file_info_v2";
                String str2 = "is_support_off_ble_v2";
                int i3 = this.D;
                if (i3 == 2) {
                    str = "indoor_file_info_v2";
                    str2 = "is_support_off_ble_v2";
                } else if (i3 == 3) {
                    str = "indoor_file_info_v3";
                    str2 = "is_support_off_ble_v3";
                }
                if (jSONObject.has(str2)) {
                    this.k = jSONObject.optInt(str2);
                }
                if (!jSONObject.has(str) || (jSONObjectOptJSONObject = jSONObject.optJSONArray(str).optJSONObject(0)) == null) {
                    this.C = false;
                } else {
                    this.n = new b();
                    this.C = true;
                    if (jSONObjectOptJSONObject.has("file_path")) {
                        this.n.a(jSONObjectOptJSONObject.optString("file_path"));
                    } else {
                        this.C = false;
                    }
                    if (jSONObjectOptJSONObject.has("zip_md5")) {
                        this.n.b(jSONObjectOptJSONObject.optString("zip_md5"));
                    } else {
                        this.C = false;
                    }
                    if (jSONObjectOptJSONObject.has("model_md5")) {
                        this.n.c(jSONObjectOptJSONObject.optString("model_md5"));
                    } else {
                        this.C = false;
                    }
                    if (jSONObjectOptJSONObject.has("data_md5")) {
                        this.n.d(jSONObjectOptJSONObject.optString("data_md5"));
                    } else {
                        this.C = false;
                    }
                    if (jSONObjectOptJSONObject.has("dict_md5")) {
                        this.n.e(jSONObjectOptJSONObject.optString("dict_md5"));
                    } else {
                        this.C = false;
                    }
                }
                if (jSONObject.has("rect") && (string = jSONObject.getString("rect")) != null && string.contains(",") && (strArrSplit = string.split(",")) != null && strArrSplit.length >= 4) {
                    this.c = Double.valueOf(strArrSplit[1]).doubleValue();
                    this.e = Double.valueOf(strArrSplit[0]).doubleValue();
                    this.d = Double.valueOf(strArrSplit[3]).doubleValue();
                    double dDoubleValue = Double.valueOf(strArrSplit[2]).doubleValue();
                    this.f = dDoubleValue;
                    double d = this.c;
                    this.q = (this.e + d) / 2.0d;
                    double d2 = this.d;
                    this.r = (dDoubleValue + d2) / 2.0d;
                    double[] dArrCoorEncrypt = Jni.coorEncrypt(d, d2, BDLocation.BDLOCATION_WGS84_TO_GCJ02);
                    this.c = dArrCoorEncrypt[0];
                    this.d = dArrCoorEncrypt[1];
                    double[] dArrCoorEncrypt2 = Jni.coorEncrypt(this.e, this.f, BDLocation.BDLOCATION_WGS84_TO_GCJ02);
                    this.e = dArrCoorEncrypt2[0];
                    this.f = dArrCoorEncrypt2[1];
                }
                if (jSONObject.has("indoor_log_rate")) {
                    this.y = jSONObject.optDouble("indoor_log_rate");
                    if (new Random().nextDouble() <= this.y) {
                        this.z = true;
                    } else {
                        this.z = false;
                    }
                }
                if (jSONObject.has("sensor_log_rate")) {
                    this.A = jSONObject.optDouble("sensor_log_rate");
                    if (new Random().nextDouble() <= this.A) {
                        this.B = true;
                    } else {
                        this.B = false;
                    }
                }
                if (jSONObject.has("is_support_poi_data")) {
                    this.E = jSONObject.optInt("is_support_poi_data");
                }
                if (jSONObject.has("is_use_bms")) {
                    this.J = jSONObject.optInt("is_use_bms");
                }
                if (jSONObject.has("new_indoorloc_param")) {
                    this.K = jSONObject.optString("new_indoorloc_param");
                }
            } catch (Exception unused) {
            }
        }
    }

    private void a(String str) throws NumberFormatException {
        if (str != null) {
            try {
                String[] strArrSplit = str.split(";");
                if (strArrSplit.length >= 3) {
                    int i = Integer.parseInt(strArrSplit[0]);
                    String str2 = strArrSplit[2];
                    if (i != 3 || str2 == null) {
                        return;
                    }
                    String[] strArrSplit2 = str2.split(",");
                    if (strArrSplit2.length >= 8) {
                        double d = Double.parseDouble(strArrSplit2[0]);
                        double d2 = Double.parseDouble(strArrSplit2[1]);
                        double d3 = Double.parseDouble(strArrSplit2[2]);
                        double d4 = Double.parseDouble(strArrSplit2[3]);
                        double d5 = Double.parseDouble(strArrSplit2[4]);
                        double d6 = Double.parseDouble(strArrSplit2[5]);
                        double d7 = Double.parseDouble(strArrSplit2[6]);
                        double d8 = Double.parseDouble(strArrSplit2[7]);
                        this.M = new double[]{d, d3, d5, d7};
                        this.N = new double[]{d2, d4, d6, d8};
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    private void a(String[] strArr, String[] strArr2) {
        int length = (strArr == null || strArr.length <= 0) ? 0 : strArr.length;
        int length2 = (strArr2 == null || strArr2.length <= 0) ? 0 : strArr2.length;
        String[] strArr3 = new String[length + length2];
        this.p = strArr3;
        if (strArr != null) {
            System.arraycopy(strArr, 0, strArr3, 0, length);
        }
        if (strArr2 != null) {
            System.arraycopy(strArr2, 0, this.p, length, length2);
        }
    }

    public int a() {
        return this.s;
    }

    public String[] b() {
        return this.o;
    }

    public String[] c() {
        return this.p;
    }

    public int d() {
        return this.H;
    }

    public String toString() {
        return "BlePdrEffectArea{bldg='" + this.a + "', bid='" + this.b + "', lon0=" + this.c + ", lat0=" + this.d + ", lon1=" + this.e + ", lat1=" + this.f + ", uuid='" + this.g + "', uuids=" + Arrays.toString(this.h) + ", encryptUuids=" + Arrays.toString(this.o) + ", allUuids=" + Arrays.toString(this.p) + ", supportTypes='" + this.i + "', confType='" + this.j + "', isSupportOffBle=" + this.k + ", inoutPoints='" + this.l + "', offBleVer='" + this.m + "', offline_data_mode=" + this.D + ", offlineFileInfo=" + this.n + ", middlelon=" + this.q + ", middlelat=" + this.r + ", bleNumlimit=" + this.s + ", conf='" + this.u + "', passageWayInfo='" + this.v + "', offLocParameter='" + this.w + "', mThrYaw=" + this.x + ", mIndoorLogRate=" + this.y + ", isIndoorLogReport=" + this.z + ", mIndoorSensorLogRate=" + this.A + ", isIndoorSensorLogReport=" + this.B + ", isOfflineServerDataValid=" + this.C + ", isNeedCompanyAuth=" + this.G + ", rectType=" + this.F + ", isBigRect=" + this.H + ", isRingRect=" + this.I + ", isUseBMS=" + this.J + ", isSupportPoiData=" + this.E + ", algoRects=" + this.L + ", isNeedWalkNaviLink=" + this.O + ", newIndoorLocParams=" + this.K + '}';
    }
}
