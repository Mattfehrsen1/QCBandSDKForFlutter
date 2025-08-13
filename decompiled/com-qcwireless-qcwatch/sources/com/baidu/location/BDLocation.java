package com.baidu.location;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.location.Address;
import com.baidu.location.h.s;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class BDLocation implements Parcelable {
    public static final String BDLOCATION_BD09LL_TO_GCJ02 = "bd09ll2gcj";
    public static final String BDLOCATION_BD09_TO_GCJ02 = "bd092gcj";
    public static final String BDLOCATION_COOR_TYPE_BD09LL = "bd09";
    public static final String BDLOCATION_COOR_TYPE_BD09MC = "bd09mc";
    public static final String BDLOCATION_COOR_TYPE_GCJ02 = "gcj02";
    public static final String BDLOCATION_COOR_TYPE_GCJ03 = "gcj03";
    public static final String BDLOCATION_COOR_TYPE_WGS84 = "wgs84";
    public static final String BDLOCATION_GCJ02_TO_BD09 = "bd09";
    public static final String BDLOCATION_GCJ02_TO_BD09LL = "bd09ll";
    public static final String BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU = "bd_beidou";
    public static final String BDLOCATION_GNSS_PROVIDER_FROM_SYSTEM = "system";
    public static final String BDLOCATION_WGS84_TO_GCJ02 = "gps2gcj";
    public static final Parcelable.Creator<BDLocation> CREATOR = new b();
    public static final int GNSS_ACCURACY_BAD = 3;
    public static final int GNSS_ACCURACY_GOOD = 1;
    public static final int GNSS_ACCURACY_MID = 2;
    public static final int GNSS_ACCURACY_UNKNOWN = 0;
    public static final int GPS_ACCURACY_BAD = 3;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_MID = 2;
    public static final int GPS_ACCURACY_UNKNOWN = 0;
    public static final int GPS_RECTIFY_INDOOR = 1;
    public static final int GPS_RECTIFY_NONE = 0;
    public static final int GPS_RECTIFY_OUTDOOR = 2;
    public static final int INDOOR_LOCATION_NEARBY_SURPPORT_TRUE = 2;
    public static final int INDOOR_LOCATION_SOURCE_BLUETOOTH = 4;
    public static final int INDOOR_LOCATION_SOURCE_MAGNETIC = 2;
    public static final int INDOOR_LOCATION_SOURCE_SMALLCELLSTATION = 8;
    public static final int INDOOR_LOCATION_SOURCE_UNKNOWN = 0;
    public static final int INDOOR_LOCATION_SOURCE_WIFI = 1;
    public static final int INDOOR_LOCATION_SURPPORT_FALSE = 0;
    public static final int INDOOR_LOCATION_SURPPORT_TRUE = 1;
    public static final int INDOOR_NETWORK_STATE_HIGH = 2;
    public static final int INDOOR_NETWORK_STATE_LOW = 0;
    public static final int INDOOR_NETWORK_STATE_MIDDLE = 1;
    public static final int LOCATION_WHERE_IN_CN = 1;
    public static final int LOCATION_WHERE_OUT_CN = 0;
    public static final int LOCATION_WHERE_UNKNOW = 2;
    public static final int MOCK_GNSS_PROBABILITY_HIGH = 3;
    public static final int MOCK_GNSS_PROBABILITY_LOW = 1;
    public static final int MOCK_GNSS_PROBABILITY_MIDDLE = 2;
    public static final int MOCK_GNSS_PROBABILITY_UNKNOW = -1;
    public static final int MOCK_GNSS_PROBABILITY_ZERO = 0;
    public static final int MOCK_GPS_PROBABILITY_HIGH = 3;
    public static final int MOCK_GPS_PROBABILITY_LOW = 1;
    public static final int MOCK_GPS_PROBABILITY_MIDDLE = 2;
    public static final int MOCK_GPS_PROBABILITY_UNKNOW = -1;
    public static final int MOCK_GPS_PROBABILITY_ZERO = 0;
    public static final int OPERATORS_TYPE_MOBILE = 1;
    public static final int OPERATORS_TYPE_TELECOMU = 3;
    public static final int OPERATORS_TYPE_UNICOM = 2;
    public static final int OPERATORS_TYPE_UNKONW = 0;
    public static final int TYPE_BMS_HD_LOCATION = 602;
    public static final int TYPE_CLOSE_LOCATION_SERVICE_SWITCH_FAIL = 69;
    public static final int TYPE_HD_LOCATION = 601;
    public static final int TYPE_LANE_HD_LOCATION = 603;
    public static final int TYPE_NO_PERMISSION_AND_CLOSE_SWITCH_FAIL = 71;
    public static final int TYPE_NO_PERMISSION_LOCATION_FAIL = 70;
    public static final int TypeCacheLocation = 65;
    public static final int TypeCoarseLocation = 160;
    public static final int TypeCriteriaException = 62;
    public static final int TypeGnssLocation = 61;
    public static final int TypeGpsLocation = 61;
    public static final int TypeNetWorkException = 63;
    public static final int TypeNetWorkLocation = 161;
    public static final int TypeNone = 0;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeServerCheckFlowError = 506;
    public static final int TypeServerCheckKeyError = 505;
    public static final int TypeServerDecryptError = 162;
    public static final int TypeServerError = 167;
    public static final int USER_INDDOR_TRUE = 1;
    public static final int USER_INDOOR_FALSE = 0;
    public static final int USER_INDOOR_UNKNOW = -1;
    private String A;
    private String B;
    private double C;
    private boolean D;
    private int E;
    private int F;
    private String G;
    private int H;
    private String I;
    private int J;
    private int K;
    private int L;
    private int M;
    private String N;
    private String O;
    private String P;
    private int Q;
    private List<Poi> R;
    private String S;
    private String T;
    private String U;
    private Bundle V;
    private int W;
    private int X;
    private long Y;
    private String Z;
    private int a;
    private String aa;
    private double ab;
    private double ac;
    private boolean ad;
    private PoiRegion ae;
    private float af;
    private double ag;
    private int ah;
    private int ai;
    private BDLocation aj;
    private Bundle ak;
    private String al;
    private long am;
    private String b;
    private double c;
    private double d;
    private boolean e;
    private double f;
    private boolean g;
    private float h;
    private boolean i;
    private float j;
    private String k;
    private float l;
    private int m;
    private float n;
    private boolean o;
    private int p;
    private float q;
    private String r;
    private boolean s;
    private String t;
    private String u;
    private String v;
    private String w;
    private boolean x;
    private Address y;
    private String z;

    public BDLocation() {
        this.a = 0;
        this.b = null;
        this.c = Double.MIN_VALUE;
        this.d = Double.MIN_VALUE;
        this.e = false;
        this.f = Double.MIN_VALUE;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.l = 0.0f;
        this.m = -1;
        this.n = 0.0f;
        this.o = false;
        this.p = -1;
        this.q = -1.0f;
        this.r = null;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.y = new Address.Builder().build();
        this.z = null;
        this.A = null;
        this.B = null;
        this.D = false;
        this.E = 0;
        this.F = 1;
        this.G = null;
        this.I = "";
        this.J = -1;
        this.K = 0;
        this.L = 2;
        this.M = 0;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = -1;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = new Bundle();
        this.W = 0;
        this.X = 0;
        this.Y = 0L;
        this.Z = null;
        this.aa = null;
        this.ab = Double.MIN_VALUE;
        this.ac = Double.MIN_VALUE;
        this.ad = false;
        this.ae = null;
        this.af = -1.0f;
        this.ag = -1.0d;
        this.ah = 0;
        this.ai = -1;
        this.ak = null;
        this.al = null;
        this.am = -1L;
    }

    private BDLocation(Parcel parcel) {
        this.a = 0;
        this.b = null;
        this.c = Double.MIN_VALUE;
        this.d = Double.MIN_VALUE;
        this.e = false;
        this.f = Double.MIN_VALUE;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.l = 0.0f;
        this.m = -1;
        this.n = 0.0f;
        this.o = false;
        this.p = -1;
        this.q = -1.0f;
        this.r = null;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.y = new Address.Builder().build();
        this.z = null;
        this.A = null;
        this.B = null;
        this.D = false;
        this.E = 0;
        this.F = 1;
        this.G = null;
        this.I = "";
        this.J = -1;
        this.K = 0;
        this.L = 2;
        this.M = 0;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = -1;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = new Bundle();
        this.W = 0;
        this.X = 0;
        this.Y = 0L;
        this.Z = null;
        this.aa = null;
        this.ab = Double.MIN_VALUE;
        this.ac = Double.MIN_VALUE;
        this.ad = false;
        this.ae = null;
        this.af = -1.0f;
        this.ag = -1.0d;
        this.ah = 0;
        this.ai = -1;
        this.ak = null;
        this.al = null;
        this.am = -1L;
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.am = parcel.readLong();
        this.c = parcel.readDouble();
        this.d = parcel.readDouble();
        this.f = parcel.readDouble();
        this.h = parcel.readFloat();
        this.j = parcel.readFloat();
        this.k = parcel.readString();
        this.l = parcel.readFloat();
        this.m = parcel.readInt();
        this.n = parcel.readFloat();
        this.p = parcel.readInt();
        this.q = parcel.readFloat();
        this.z = parcel.readString();
        this.E = parcel.readInt();
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readDouble();
        this.G = parcel.readString();
        String string = parcel.readString();
        String string2 = parcel.readString();
        String string3 = parcel.readString();
        String string4 = parcel.readString();
        String string5 = parcel.readString();
        String string6 = parcel.readString();
        parcel.readString();
        String string7 = parcel.readString();
        String string8 = parcel.readString();
        String string9 = parcel.readString();
        this.y = new Address.Builder().country(string7).countryCode(string8).province(string).city(string2).cityCode(string6).district(string3).street(string4).streetNumber(string5).adcode(string9).town(parcel.readString()).build();
        boolean[] zArr = new boolean[8];
        this.H = parcel.readInt();
        this.I = parcel.readString();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.F = parcel.readInt();
        this.S = parcel.readString();
        this.J = parcel.readInt();
        this.K = parcel.readInt();
        this.L = parcel.readInt();
        this.M = parcel.readInt();
        this.N = parcel.readString();
        this.O = parcel.readString();
        this.P = parcel.readString();
        this.Q = parcel.readInt();
        this.W = parcel.readInt();
        this.T = parcel.readString();
        this.X = parcel.readInt();
        this.U = parcel.readString();
        this.Z = parcel.readString();
        this.aa = parcel.readString();
        this.Y = parcel.readLong();
        this.ab = parcel.readDouble();
        this.ac = parcel.readDouble();
        this.af = parcel.readFloat();
        this.ag = parcel.readDouble();
        this.ah = parcel.readInt();
        this.ai = parcel.readInt();
        this.r = parcel.readString();
        this.al = parcel.readString();
        try {
            this.aj = (BDLocation) parcel.readParcelable(BDLocation.class.getClassLoader());
        } catch (Exception e) {
            this.aj = null;
            e.printStackTrace();
        }
        try {
            parcel.readBooleanArray(zArr);
            this.e = zArr[0];
            this.g = zArr[1];
            this.i = zArr[2];
            this.o = zArr[3];
            this.s = zArr[4];
            this.x = zArr[5];
            this.D = zArr[6];
            this.ad = zArr[7];
        } catch (Exception unused) {
        }
        ArrayList arrayList = new ArrayList();
        try {
            parcel.readList(arrayList, Poi.class.getClassLoader());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (arrayList.size() == 0) {
            this.R = null;
        } else {
            this.R = arrayList;
        }
        try {
            this.V = parcel.readBundle();
        } catch (Exception e3) {
            e3.printStackTrace();
            this.V = new Bundle();
        }
        try {
            this.ak = parcel.readBundle();
        } catch (Exception e4) {
            e4.printStackTrace();
            this.ak = new Bundle();
        }
        try {
            this.ae = (PoiRegion) parcel.readParcelable(PoiRegion.class.getClassLoader());
        } catch (Exception e5) {
            this.ae = null;
            e5.printStackTrace();
        }
    }

    /* synthetic */ BDLocation(Parcel parcel, b bVar) {
        this(parcel);
    }

    public BDLocation(BDLocation bDLocation) {
        this.a = 0;
        ArrayList arrayList = null;
        this.b = null;
        this.c = Double.MIN_VALUE;
        this.d = Double.MIN_VALUE;
        this.e = false;
        this.f = Double.MIN_VALUE;
        this.g = false;
        this.h = 0.0f;
        this.i = false;
        this.j = 0.0f;
        this.l = 0.0f;
        this.m = -1;
        this.n = 0.0f;
        this.o = false;
        this.p = -1;
        this.q = -1.0f;
        this.r = null;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.y = new Address.Builder().build();
        this.z = null;
        this.A = null;
        this.B = null;
        this.D = false;
        this.E = 0;
        this.F = 1;
        this.G = null;
        this.I = "";
        this.J = -1;
        this.K = 0;
        this.L = 2;
        this.M = 0;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = -1;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = new Bundle();
        this.W = 0;
        this.X = 0;
        this.Y = 0L;
        this.Z = null;
        this.aa = null;
        this.ab = Double.MIN_VALUE;
        this.ac = Double.MIN_VALUE;
        this.ad = false;
        this.ae = null;
        this.af = -1.0f;
        this.ag = -1.0d;
        this.ah = 0;
        this.ai = -1;
        this.ak = null;
        this.al = null;
        this.am = -1L;
        this.a = bDLocation.a;
        this.b = bDLocation.b;
        this.am = bDLocation.am;
        this.c = bDLocation.c;
        this.d = bDLocation.d;
        this.e = bDLocation.e;
        this.f = bDLocation.f;
        this.g = bDLocation.g;
        this.h = bDLocation.h;
        this.i = bDLocation.i;
        this.j = bDLocation.j;
        this.k = bDLocation.k;
        this.l = bDLocation.l;
        this.m = bDLocation.m;
        this.n = bDLocation.n;
        this.o = bDLocation.o;
        this.p = bDLocation.p;
        this.q = bDLocation.q;
        this.r = bDLocation.r;
        this.s = bDLocation.s;
        this.t = bDLocation.t;
        this.x = bDLocation.x;
        this.y = new Address.Builder().country(bDLocation.y.country).countryCode(bDLocation.y.countryCode).province(bDLocation.y.province).city(bDLocation.y.city).cityCode(bDLocation.y.cityCode).district(bDLocation.y.district).street(bDLocation.y.street).streetNumber(bDLocation.y.streetNumber).adcode(bDLocation.y.adcode).town(bDLocation.y.town).build();
        this.z = bDLocation.z;
        this.A = bDLocation.A;
        this.B = bDLocation.B;
        this.C = bDLocation.C;
        this.F = bDLocation.F;
        this.E = bDLocation.E;
        this.D = bDLocation.D;
        this.G = bDLocation.G;
        this.H = bDLocation.H;
        this.I = bDLocation.I;
        this.u = bDLocation.u;
        this.v = bDLocation.v;
        this.w = bDLocation.w;
        this.J = bDLocation.J;
        this.K = bDLocation.K;
        this.L = bDLocation.K;
        this.M = bDLocation.M;
        this.N = bDLocation.N;
        this.O = bDLocation.O;
        this.P = bDLocation.P;
        this.Q = bDLocation.Q;
        this.W = bDLocation.W;
        this.U = bDLocation.U;
        this.Z = bDLocation.Z;
        this.aa = bDLocation.aa;
        this.ab = bDLocation.ab;
        this.ac = bDLocation.ac;
        this.Y = bDLocation.Y;
        this.ag = bDLocation.ag;
        this.ah = bDLocation.ah;
        this.ai = bDLocation.ai;
        this.aj = bDLocation.aj;
        this.T = bDLocation.T;
        if (bDLocation.R != null) {
            arrayList = new ArrayList();
            for (int i = 0; i < bDLocation.R.size(); i++) {
                Poi poi = bDLocation.R.get(i);
                arrayList.add(new Poi(poi.getId(), poi.getName(), poi.getRank(), poi.getTags(), poi.getAddr()));
            }
        }
        this.R = arrayList;
        this.S = bDLocation.S;
        this.V = bDLocation.V;
        this.X = bDLocation.X;
        this.ad = bDLocation.ad;
        this.ae = bDLocation.ae;
        this.af = bDLocation.af;
        this.ak = bDLocation.ak;
        this.al = bDLocation.al;
    }

    /* JADX WARN: Removed duplicated region for block: B:225:0x04e6 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0537 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0551 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0572 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x058c A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x05a5 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x05be A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:258:0x05cf A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:310:0x06e0 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x06eb A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TRY_LEAVE, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x06f5 A[Catch: Exception -> 0x0705, Error -> 0x07f6, TryCatch #14 {Error -> 0x07f6, blocks: (B:7:0x00bb, B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:16:0x0165, B:17:0x016f, B:19:0x0175, B:20:0x0181, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:102:0x0356, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:267:0x05fc, B:269:0x060b, B:271:0x061b, B:274:0x0623, B:275:0x0626, B:277:0x062f, B:278:0x0641, B:280:0x064a, B:281:0x0653, B:283:0x065c, B:284:0x0665, B:286:0x066e, B:287:0x0678, B:289:0x0681, B:291:0x0692, B:293:0x069c, B:295:0x06a0, B:297:0x06ae, B:299:0x06b7, B:301:0x06c4, B:302:0x06c8, B:303:0x06cd, B:308:0x06d7, B:310:0x06e0, B:312:0x06ef, B:314:0x06f5, B:315:0x0701, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:148:0x0435, B:150:0x0440, B:154:0x0449, B:160:0x0456, B:168:0x0463, B:176:0x0470, B:184:0x047c, B:192:0x048b, B:200:0x049b, B:208:0x04ad, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:389:0x00bb }] */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0701 A[Catch: Exception -> 0x0705, Error -> 0x07f6, TRY_LEAVE, TryCatch #14 {Error -> 0x07f6, blocks: (B:7:0x00bb, B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:16:0x0165, B:17:0x016f, B:19:0x0175, B:20:0x0181, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:102:0x0356, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:267:0x05fc, B:269:0x060b, B:271:0x061b, B:274:0x0623, B:275:0x0626, B:277:0x062f, B:278:0x0641, B:280:0x064a, B:281:0x0653, B:283:0x065c, B:284:0x0665, B:286:0x066e, B:287:0x0678, B:289:0x0681, B:291:0x0692, B:293:0x069c, B:295:0x06a0, B:297:0x06ae, B:299:0x06b7, B:301:0x06c4, B:302:0x06c8, B:303:0x06cd, B:308:0x06d7, B:310:0x06e0, B:312:0x06ef, B:314:0x06f5, B:315:0x0701, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:148:0x0435, B:150:0x0440, B:154:0x0449, B:160:0x0456, B:168:0x0463, B:176:0x0470, B:184:0x047c, B:192:0x048b, B:200:0x049b, B:208:0x04ad, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:389:0x00bb }] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0709  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0718 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x072a A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TRY_LEAVE, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:335:0x075c A[Catch: all -> 0x075f, TRY_LEAVE, TryCatch #19 {all -> 0x075f, blocks: (B:327:0x0735, B:329:0x073b, B:331:0x0741, B:333:0x0745, B:335:0x075c), top: B:405:0x0735 }] */
    /* JADX WARN: Removed duplicated region for block: B:385:0x05fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:397:0x076d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:417:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0238 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a6 A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02be A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x033c A[Catch: Exception -> 0x07f2, Error -> 0x07f6, TryCatch #7 {Exception -> 0x07f2, blocks: (B:8:0x00bd, B:11:0x00f7, B:13:0x0152, B:14:0x015c, B:21:0x0185, B:24:0x018c, B:25:0x0191, B:28:0x019b, B:30:0x01ca, B:31:0x01d1, B:33:0x01d7, B:34:0x01e2, B:36:0x01e8, B:37:0x01ef, B:39:0x01f5, B:40:0x0200, B:43:0x020b, B:45:0x021a, B:47:0x0226, B:48:0x0229, B:50:0x0230, B:52:0x0238, B:53:0x024b, B:55:0x0251, B:57:0x0273, B:59:0x027f, B:61:0x0285, B:63:0x028e, B:64:0x029b, B:65:0x029d, B:67:0x02a6, B:69:0x02b3, B:70:0x02b5, B:72:0x02be, B:74:0x02cd, B:76:0x02d8, B:78:0x02e1, B:80:0x02ed, B:82:0x02f6, B:84:0x0302, B:86:0x030a, B:88:0x0315, B:90:0x031e, B:92:0x032a, B:93:0x0333, B:95:0x033c, B:97:0x0349, B:99:0x034e, B:106:0x0360, B:108:0x0368, B:110:0x0370, B:112:0x0378, B:114:0x0380, B:116:0x0388, B:118:0x0390, B:120:0x0398, B:122:0x03a0, B:124:0x03a9, B:126:0x03b6, B:128:0x03be, B:130:0x03c9, B:132:0x03d2, B:134:0x03de, B:136:0x03e7, B:138:0x03f3, B:140:0x03fb, B:142:0x0403, B:144:0x040c, B:225:0x04e6, B:228:0x052f, B:230:0x0537, B:232:0x0545, B:233:0x0548, B:235:0x0551, B:237:0x055e, B:238:0x0569, B:240:0x0572, B:242:0x0581, B:243:0x0584, B:245:0x058c, B:247:0x059a, B:248:0x059d, B:250:0x05a5, B:252:0x05b3, B:253:0x05b6, B:255:0x05be, B:256:0x05c6, B:258:0x05cf, B:260:0x05dc, B:261:0x05e0, B:264:0x05e9, B:265:0x05f3, B:308:0x06d7, B:310:0x06e0, B:316:0x0705, B:319:0x070c, B:321:0x0718, B:322:0x0721, B:324:0x072a, B:339:0x0761, B:340:0x0764, B:349:0x079c, B:311:0x06eb, B:307:0x06d4, B:222:0x04cf, B:227:0x0522, B:357:0x07b0, B:358:0x07b5), top: B:386:0x00bd }] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v42 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BDLocation(java.lang.String r26) throws org.json.JSONException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 2056
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.BDLocation.<init>(java.lang.String):void");
    }

    private void a(Boolean bool) {
        this.x = bool.booleanValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getAcc() {
        return this.C;
    }

    public String getAdCode() {
        return this.y.adcode;
    }

    public String getAddrStr() {
        return this.y.address;
    }

    public Address getAddress() {
        return this.y;
    }

    public double getAltitude() {
        return this.f;
    }

    public String getBuildingID() {
        return this.A;
    }

    public String getBuildingName() {
        return this.B;
    }

    public String getCity() {
        return this.y.city;
    }

    public String getCityCode() {
        return this.y.cityCode;
    }

    public String getCoorType() {
        return this.r;
    }

    public String getCountry() {
        return this.y.country;
    }

    public String getCountryCode() {
        return this.y.countryCode;
    }

    public long getDelayTime() {
        return this.Y;
    }

    @Deprecated
    public float getDerect() {
        return this.q;
    }

    public float getDirection() {
        return this.q;
    }

    public double getDisToRealLocation() {
        return this.ag;
    }

    public String getDistrict() {
        return this.y.district;
    }

    public Bundle getExtraInfo() {
        return this.ak;
    }

    public Location getExtraLocation(String str) {
        Bundle bundle = this.V;
        if (bundle == null) {
            return null;
        }
        Parcelable parcelable = bundle.getParcelable(str);
        if (parcelable instanceof Location) {
            return (Location) parcelable;
        }
        return null;
    }

    public String getFloor() {
        return this.z;
    }

    public double[] getFusionLocInfo(String str) {
        return this.V.getDoubleArray(str);
    }

    public int getGnssAccuracyStatus() {
        return this.W;
    }

    public float getGnssBiasProb() {
        return this.af;
    }

    public int getGnssCheckStatus() {
        return this.X;
    }

    public String getGnssProvider() {
        return this.al;
    }

    @Deprecated
    public int getGpsAccuracyStatus() {
        return this.W;
    }

    @Deprecated
    public float getGpsBiasProb() {
        return this.af;
    }

    @Deprecated
    public int getGpsCheckStatus() {
        return this.X;
    }

    public int getInOutStatus() {
        return this.Q;
    }

    public int getIndoorLocationSource() {
        return this.M;
    }

    public int getIndoorLocationSurpport() {
        return this.K;
    }

    public String getIndoorLocationSurpportBuidlingID() {
        return this.O;
    }

    public String getIndoorLocationSurpportBuidlingName() {
        return this.N;
    }

    public int getIndoorNetworkState() {
        return this.L;
    }

    public String getIndoorSurpportPolygon() {
        return this.P;
    }

    public double getLatitude() {
        return this.c;
    }

    public int getLocType() {
        return this.a;
    }

    public String getLocTypeDescription() {
        return this.S;
    }

    public String getLocationDescribe() {
        return this.u;
    }

    public String getLocationID() {
        return this.T;
    }

    public int getLocationWhere() {
        return this.F;
    }

    public double getLongitude() {
        return this.d;
    }

    public int getMockGnssProbability() {
        return this.ai;
    }

    public int getMockGnssStrategy() {
        return this.ah;
    }

    @Deprecated
    public int getMockGpsProbability() {
        return this.ai;
    }

    @Deprecated
    public int getMockGpsStrategy() {
        return this.ah;
    }

    public String getNetworkLocationType() {
        return this.G;
    }

    public double getNrlLat() {
        return this.ab;
    }

    public double getNrlLon() {
        return this.ac;
    }

    public String getNrlResult() {
        return this.Z;
    }

    @Deprecated
    public int getOperators() {
        return this.H;
    }

    public List<Poi> getPoiList() {
        return this.R;
    }

    public PoiRegion getPoiRegion() {
        return this.ae;
    }

    public String getProvince() {
        return this.y.province;
    }

    public float getRadius() {
        return this.j;
    }

    public BDLocation getReallLocation() {
        if (getMockGpsStrategy() > 0) {
            return this.aj;
        }
        return null;
    }

    public String getRetFields(String str) {
        return this.V.getString(str);
    }

    public String getRoadLocString() {
        return this.U;
    }

    public int getSatelliteNumber() {
        this.o = true;
        return this.p;
    }

    @Deprecated
    public String getSemaAptag() {
        return this.u;
    }

    public float getSpeed() {
        return this.h;
    }

    public String getStreet() {
        return this.y.street;
    }

    public String getStreetNumber() {
        return this.y.streetNumber;
    }

    public String getTime() {
        return this.b;
    }

    public long getTimeStamp() {
        return this.am;
    }

    public String getTown() {
        return this.y.town;
    }

    public String getTraffic() {
        return this.k;
    }

    public float getTrafficConfidence() {
        return this.l;
    }

    public float getTrafficSkipProb() {
        return this.n;
    }

    public int getUserIndoorState() {
        return this.J;
    }

    public String getVdrJsonString() {
        Bundle bundle = this.V;
        if (bundle == null || !bundle.containsKey("vdr")) {
            return null;
        }
        return this.V.getString("vdr");
    }

    public String getViaductResult() {
        return this.aa;
    }

    public boolean hasAddr() {
        return this.s;
    }

    public boolean hasAltitude() {
        return this.e;
    }

    public boolean hasRadius() {
        return this.i;
    }

    public boolean hasSateNumber() {
        return this.o;
    }

    public boolean hasSpeed() {
        return this.g;
    }

    public boolean isCellChangeFlag() {
        return this.x;
    }

    public boolean isInIndoorPark() {
        return this.ad;
    }

    public boolean isIndoorLocMode() {
        return this.D;
    }

    public boolean isNrlAvailable() {
        return (this.ac == Double.MIN_VALUE || this.ab == Double.MIN_VALUE) ? false : true;
    }

    public int isParkAvailable() {
        return this.E;
    }

    public int isTrafficStation() {
        return this.m;
    }

    public void setAcc(double d) {
        this.C = d;
    }

    public void setAddr(Address address) {
        if (address != null) {
            this.y = address;
            this.s = true;
        }
    }

    public void setAddrStr(String str) {
        this.t = str;
        this.s = str != null;
    }

    public void setAltitude(double d) {
        if (d < 9999.0d) {
            this.f = d;
            this.e = true;
        }
    }

    public void setBuildingID(String str) {
        this.A = str;
    }

    public void setBuildingName(String str) {
        this.B = str;
    }

    public void setCoorType(String str) {
        this.r = str;
    }

    public void setDelayTime(long j) {
        this.Y = j;
    }

    public void setDirection(float f) {
        this.q = f;
    }

    public void setDisToRealLocation(double d) {
        this.ag = d;
    }

    public void setExtraLocation(String str, Location location) {
        if (this.V == null) {
            this.V = new Bundle();
        }
        this.V.putParcelable(str, location);
    }

    public void setExtrainfo(Bundle bundle) {
        this.ak = bundle == null ? null : new Bundle(bundle);
    }

    public void setFloor(String str) {
        this.z = str;
    }

    public void setFusionLocInfo(String str, double[] dArr) {
        if (this.V == null) {
            this.V = new Bundle();
        }
        this.V.putDoubleArray(str, dArr);
    }

    public void setGnssAccuracyStatus(int i) {
        this.W = i;
    }

    public void setGnssBiasProb(float f) {
        this.af = f;
    }

    public void setGnssCheckStatus(int i) {
        this.X = i;
    }

    public void setGnssProvider(String str) {
        this.al = str;
    }

    @Deprecated
    public void setGpsAccuracyStatus(int i) {
        this.W = i;
    }

    @Deprecated
    public void setGpsBiasProb(float f) {
        this.af = f;
    }

    @Deprecated
    public void setGpsCheckStatus(int i) {
        this.X = i;
    }

    public void setInOutStatus(int i) {
        this.Q = i;
    }

    public void setIndoorLocMode(boolean z) {
        this.D = z;
    }

    public void setIndoorLocationSource(int i) {
        this.M = i;
    }

    public void setIndoorLocationSurpport(int i) {
        this.K = i;
    }

    public void setIndoorNetworkState(int i) {
        this.L = i;
    }

    public void setIndoorSurpportPolygon(String str) {
        this.P = str;
    }

    public void setIsInIndoorPark(boolean z) {
        this.ad = z;
    }

    public void setIsTrafficStation(int i) {
        this.m = i;
    }

    public void setLatitude(double d) {
        this.c = d;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setLocType(int r2) {
        /*
            r1 = this;
            r1.a = r2
            r0 = 66
            if (r2 == r0) goto L4f
            r0 = 67
            if (r2 == r0) goto L4c
            r0 = 167(0xa7, float:2.34E-43)
            if (r2 == r0) goto L49
            r0 = 505(0x1f9, float:7.08E-43)
            if (r2 == r0) goto L46
            switch(r2) {
                case 61: goto L36;
                case 62: goto L30;
                case 63: goto L4c;
                default: goto L15;
            }
        L15:
            switch(r2) {
                case 69: goto L2d;
                case 70: goto L2a;
                case 71: goto L27;
                default: goto L18;
            }
        L18:
            switch(r2) {
                case 160: goto L24;
                case 161: goto L21;
                case 162: goto L1e;
                default: goto L1b;
            }
        L1b:
            java.lang.String r2 = "UnKnown!"
            goto L32
        L1e:
            java.lang.String r2 = "NetWork location failed because baidu location service can not decrypt the request query, please check the so file !"
            goto L32
        L21:
            java.lang.String r2 = "NetWork location successful!"
            goto L32
        L24:
            java.lang.String r2 = "Coarse location successful"
            goto L32
        L27:
            java.lang.String r2 = "Location failed because the location service switch is not on and the location permission is not enabled"
            goto L32
        L2a:
            java.lang.String r2 = "Location failed because the location permission is not enabled"
            goto L32
        L2d:
            java.lang.String r2 = "Location failed because the location service switch is not on"
            goto L32
        L30:
            java.lang.String r2 = "Location failed beacuse we can not get any loc information!"
        L32:
            r1.setLocTypeDescription(r2)
            goto L52
        L36:
            java.lang.String r2 = "GPS location successful!"
            r1.setLocTypeDescription(r2)
            r2 = 0
            r1.setUserIndoorState(r2)
            java.lang.String r2 = "system"
            r1.setGnssProvider(r2)
            goto L52
        L46:
            java.lang.String r2 = "NetWork location failed because baidu location service check the key is unlegal, please check the key in AndroidManifest.xml !"
            goto L32
        L49:
            java.lang.String r2 = "NetWork location failed because baidu location service can not caculate the location!"
            goto L32
        L4c:
            java.lang.String r2 = "Offline location failed, please check the net (wifi/cell)!"
            goto L32
        L4f:
            java.lang.String r2 = "Offline location successful!"
            goto L32
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.BDLocation.setLocType(int):void");
    }

    public void setLocTypeDescription(String str) {
        this.S = str;
    }

    public void setLocationDescribe(String str) {
        this.u = str;
    }

    public void setLocationID(String str) {
        this.T = str;
    }

    public void setLocationWhere(int i) {
        this.F = i;
    }

    public void setLongitude(double d) {
        this.d = d;
    }

    public void setMockGnssProbability(int i) {
        this.ai = i;
    }

    public void setMockGnssStrategy(int i) {
        this.ah = i;
    }

    @Deprecated
    public void setMockGpsProbability(int i) {
        this.ai = i;
    }

    @Deprecated
    public void setMockGpsStrategy(int i) {
        this.ah = i;
    }

    public void setNetworkLocationType(String str) {
        this.G = str;
    }

    public void setNrlData(String str) {
        this.Z = str;
    }

    public void setOperators(int i) {
        this.H = i;
    }

    public void setParkAvailable(int i) {
        this.E = i;
    }

    public void setPoiList(List<Poi> list) {
        this.R = list;
    }

    public void setPoiRegion(PoiRegion poiRegion) {
        this.ae = poiRegion;
    }

    public void setRadius(float f) {
        this.j = f;
        this.i = true;
    }

    public void setReallLocation(BDLocation bDLocation) {
        if (getMockGpsStrategy() > 0) {
            this.aj = bDLocation;
        }
    }

    public void setRetFields(String str, String str2) {
        if (this.V == null) {
            this.V = new Bundle();
        }
        this.V.putString(str, str2);
    }

    public void setRoadLocString(float f, float f2, String str, String str2) {
        String str3 = ((double) f) > 0.001d ? String.format("%.2f", Float.valueOf(f)) : "";
        String str4 = ((double) f2) > 0.001d ? String.format("%.2f", Float.valueOf(f2)) : "";
        String str5 = null;
        if (this.Z != null) {
            str5 = String.format(Locale.US, "%s|%s,%s", this.Z, str3, str4);
            if (this.aa != null) {
                str5 = String.format(Locale.US, "%s|%s", str5, this.aa);
            }
        }
        if (str == null) {
            str = str5;
        } else if (str5 != null) {
            str = String.format(Locale.US, "%s|%s", str5, str);
        }
        if (str2 == null) {
            str2 = str;
        } else if (str != null) {
            str2 = String.format(Locale.US, "%s|%s", str, str2);
        }
        this.U = str2;
    }

    public void setSatelliteNumber(int i) {
        this.p = i;
    }

    public void setSpeed(float f) {
        this.h = f;
        this.g = true;
    }

    public void setTime(String str) {
        this.b = str;
        setLocationID(s.a(str));
    }

    public void setTimeStamp(long j) {
        this.am = j;
    }

    public void setTraffic(String str) {
        this.k = str;
    }

    public void setTrafficConfidence(float f) {
        this.l = f;
    }

    public void setTrafficSkipProb(float f) {
        this.n = f;
    }

    public void setUserIndoorState(int i) {
        this.J = i;
    }

    public void setVdrJsonValue(String str) {
        try {
            if (this.V == null) {
                this.V = new Bundle();
            }
            this.V.putString("vdr", str);
        } catch (Exception unused) {
        }
    }

    public void setViaductData(String str) {
        this.aa = str;
    }

    public String toString() {
        return "&loctype=" + getLocType() + "&lat=" + getLatitude() + "&lon=" + getLongitude() + "&radius=" + getRadius() + "&biasprob=" + getGpsBiasProb() + "&altitude=" + getAltitude() + "&speed=" + getSpeed() + "&time=" + getTimeStamp() + "&extrainfo=" + getExtraInfo();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.am);
        parcel.writeDouble(this.c);
        parcel.writeDouble(this.d);
        parcel.writeDouble(this.f);
        parcel.writeFloat(this.h);
        parcel.writeFloat(this.j);
        parcel.writeString(this.k);
        parcel.writeFloat(this.l);
        parcel.writeInt(this.m);
        parcel.writeFloat(this.n);
        parcel.writeInt(this.p);
        parcel.writeFloat(this.q);
        parcel.writeString(this.z);
        parcel.writeInt(this.E);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeDouble(this.C);
        parcel.writeString(this.G);
        parcel.writeString(this.y.province);
        parcel.writeString(this.y.city);
        parcel.writeString(this.y.district);
        parcel.writeString(this.y.street);
        parcel.writeString(this.y.streetNumber);
        parcel.writeString(this.y.cityCode);
        parcel.writeString(this.y.address);
        parcel.writeString(this.y.country);
        parcel.writeString(this.y.countryCode);
        parcel.writeString(this.y.adcode);
        parcel.writeString(this.y.town);
        parcel.writeInt(this.H);
        parcel.writeString(this.I);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeInt(this.F);
        parcel.writeString(this.S);
        parcel.writeInt(this.J);
        parcel.writeInt(this.K);
        parcel.writeInt(this.L);
        parcel.writeInt(this.M);
        parcel.writeString(this.N);
        parcel.writeString(this.O);
        parcel.writeString(this.P);
        parcel.writeInt(this.Q);
        parcel.writeInt(this.W);
        parcel.writeString(this.T);
        parcel.writeInt(this.X);
        parcel.writeString(this.U);
        parcel.writeString(this.Z);
        parcel.writeString(this.aa);
        parcel.writeLong(this.Y);
        parcel.writeDouble(this.ab);
        parcel.writeDouble(this.ac);
        parcel.writeFloat(this.af);
        parcel.writeDouble(this.ag);
        parcel.writeInt(this.ah);
        parcel.writeInt(this.ai);
        parcel.writeString(this.r);
        parcel.writeString(this.al);
        parcel.writeParcelable(this.aj, i);
        parcel.writeBooleanArray(new boolean[]{this.e, this.g, this.i, this.o, this.s, this.x, this.D, this.ad});
        parcel.writeList(this.R);
        parcel.writeBundle(this.V);
        parcel.writeBundle(this.ak);
        parcel.writeParcelable(this.ae, i);
    }
}
