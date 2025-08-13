package com.baidu.location.e;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.h.s;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
final class k {
    private static final String[] a = {"CoorType", "Time", "LocType", "Longitude", "Latitude", "Radius", "NetworkLocationType", "Country", "CountryCode", "Province", "City", "CityCode", "District", "Street", "StreetNumber", "PoiList", "LocationDescription"};

    static final class a {
        final String a;
        final String b;
        final boolean c;
        final boolean d;
        final boolean e;
        final int f;
        final BDLocation g;
        final boolean h;
        final LinkedHashMap<String, Integer> i;

        public a(String[] strArr) {
            boolean z;
            String str;
            String[] strArr2;
            int iIntValue = 8;
            if (strArr == null) {
                this.a = null;
                this.b = null;
                this.i = null;
                this.c = false;
                this.d = false;
                this.e = false;
                this.g = null;
                this.h = false;
                this.f = 8;
                return;
            }
            LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
            String str2 = null;
            String strSubstring = null;
            BDLocation bDLocation = null;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            for (int i = 0; i < strArr.length; i += 2) {
                try {
                    if (strArr[i].equals("-loc")) {
                        str2 = strArr[i + 1];
                        try {
                            String[] strArrSplit = str2.split("&");
                            int i2 = 0;
                            while (i2 < strArrSplit.length) {
                                try {
                                    String str3 = str2;
                                    try {
                                        if (strArrSplit[i2].startsWith("cl=")) {
                                            try {
                                                strArr2 = strArrSplit;
                                                strSubstring = strArrSplit[i2].substring(3);
                                            } catch (Exception unused) {
                                                str2 = str3;
                                                str = str2;
                                                z = false;
                                                this.a = str;
                                                this.b = strSubstring;
                                                this.i = linkedHashMap;
                                                this.c = z;
                                                this.d = z3;
                                                this.e = z4;
                                                this.f = iIntValue;
                                                this.g = bDLocation;
                                                this.h = z5;
                                            }
                                        } else {
                                            if (strArrSplit[i2].startsWith("wf=")) {
                                                String[] strArrSplit2 = strArrSplit[i2].substring(3).split("\\|");
                                                strArr2 = strArrSplit;
                                                int i3 = 0;
                                                while (i3 < strArrSplit2.length) {
                                                    String[] strArrSplit3 = strArrSplit2[i3].split(";");
                                                    String[] strArr3 = strArrSplit2;
                                                    String str4 = strSubstring;
                                                    if (strArrSplit3.length >= 2) {
                                                        try {
                                                            linkedHashMap.put(strArrSplit3[0], Integer.valueOf(strArrSplit3[1]));
                                                        } catch (Exception unused2) {
                                                            str2 = str3;
                                                            strSubstring = str4;
                                                            str = str2;
                                                            z = false;
                                                            this.a = str;
                                                            this.b = strSubstring;
                                                            this.i = linkedHashMap;
                                                            this.c = z;
                                                            this.d = z3;
                                                            this.e = z4;
                                                            this.f = iIntValue;
                                                            this.g = bDLocation;
                                                            this.h = z5;
                                                        }
                                                    }
                                                    i3++;
                                                    strArrSplit2 = strArr3;
                                                    strSubstring = str4;
                                                }
                                            } else {
                                                strArr2 = strArrSplit;
                                            }
                                            strSubstring = strSubstring;
                                        }
                                        i2++;
                                        str2 = str3;
                                        strArrSplit = strArr2;
                                    } catch (Exception unused3) {
                                    }
                                } catch (Exception unused4) {
                                }
                            }
                        } catch (Exception unused5) {
                        }
                    } else {
                        if (strArr[i].equals("-com")) {
                            String[] strArrSplit4 = strArr[i + 1].split(";");
                            if (strArrSplit4.length > 0) {
                                BDLocation bDLocation2 = new BDLocation();
                                try {
                                    bDLocation2.setLatitude(Double.valueOf(strArrSplit4[0]).doubleValue());
                                    bDLocation2.setLongitude(Double.valueOf(strArrSplit4[1]).doubleValue());
                                    bDLocation2.setLocType(Integer.valueOf(strArrSplit4[2]).intValue());
                                    bDLocation2.setNetworkLocationType(strArrSplit4[3]);
                                    bDLocation = bDLocation2;
                                } catch (Exception unused6) {
                                    bDLocation = bDLocation2;
                                    str = str2;
                                    z = false;
                                    this.a = str;
                                    this.b = strSubstring;
                                    this.i = linkedHashMap;
                                    this.c = z;
                                    this.d = z3;
                                    this.e = z4;
                                    this.f = iIntValue;
                                    this.g = bDLocation;
                                    this.h = z5;
                                }
                            } else {
                                continue;
                            }
                        } else if (strArr[i].equals("-log")) {
                            if (strArr[i + 1].equals("true")) {
                                z2 = true;
                            }
                        } else if (strArr[i].equals("-rgc")) {
                            if (strArr[i + 1].equals("true")) {
                                z4 = true;
                            }
                        } else if (strArr[i].equals("-poi")) {
                            if (strArr[i + 1].equals("true")) {
                                z3 = true;
                            }
                        } else if (strArr[i].equals("-minap")) {
                            try {
                                iIntValue = Integer.valueOf(strArr[i + 1]).intValue();
                            } catch (Exception unused7) {
                            }
                        } else if (strArr[i].equals("-des") && strArr[i + 1].equals("true")) {
                            z5 = true;
                        }
                    }
                } catch (Exception unused8) {
                }
                this.a = str;
                this.b = strSubstring;
                this.i = linkedHashMap;
                this.c = z;
                this.d = z3;
                this.e = z4;
                this.f = iIntValue;
                this.g = bDLocation;
                this.h = z5;
            }
            str = !z2 ? null : str2;
            z = true;
            this.a = str;
            this.b = strSubstring;
            this.i = linkedHashMap;
            this.c = z;
            this.d = z3;
            this.e = z4;
            this.f = iIntValue;
            this.g = bDLocation;
            this.h = z5;
        }
    }

    static Cursor a(BDLocation bDLocation) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()));
        String[] strArr = a;
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        Object[] objArr = new Object[strArr.length];
        objArr[matrixCursor.getColumnIndex("CoorType")] = "gcj02";
        objArr[matrixCursor.getColumnIndex("Time")] = str9;
        objArr[matrixCursor.getColumnIndex("LocType")] = Integer.valueOf(bDLocation.getLocType());
        objArr[matrixCursor.getColumnIndex("Longitude")] = Double.valueOf(bDLocation.getLongitude());
        objArr[matrixCursor.getColumnIndex("Latitude")] = Double.valueOf(bDLocation.getLatitude());
        objArr[matrixCursor.getColumnIndex("Radius")] = Float.valueOf(bDLocation.getRadius());
        objArr[matrixCursor.getColumnIndex("NetworkLocationType")] = bDLocation.getNetworkLocationType();
        Address address = bDLocation.getAddress();
        if (address != null) {
            str2 = address.country;
            str3 = address.countryCode;
            str4 = address.province;
            str5 = address.city;
            str6 = address.cityCode;
            str7 = address.district;
            str8 = address.street;
            str = address.streetNumber;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
            str8 = null;
        }
        objArr[matrixCursor.getColumnIndex("Country")] = str2;
        objArr[matrixCursor.getColumnIndex("CountryCode")] = str3;
        objArr[matrixCursor.getColumnIndex("Province")] = str4;
        objArr[matrixCursor.getColumnIndex("City")] = str5;
        objArr[matrixCursor.getColumnIndex("CityCode")] = str6;
        objArr[matrixCursor.getColumnIndex("District")] = str7;
        objArr[matrixCursor.getColumnIndex("Street")] = str8;
        objArr[matrixCursor.getColumnIndex("StreetNumber")] = str;
        List<Poi> poiList = bDLocation.getPoiList();
        if (poiList == null) {
            objArr[matrixCursor.getColumnIndex("PoiList")] = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < poiList.size(); i++) {
                Poi poi = poiList.get(i);
                stringBuffer.append(poi.getId());
                stringBuffer.append(";");
                stringBuffer.append(poi.getName());
                stringBuffer.append(";");
                stringBuffer.append(poi.getRank());
                stringBuffer.append(";|");
            }
            objArr[matrixCursor.getColumnIndex("PoiList")] = stringBuffer.toString();
        }
        objArr[matrixCursor.getColumnIndex("LocationDescription")] = bDLocation.getLocationDescribe();
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.baidu.location.BDLocation a(android.database.Cursor r28) {
        /*
            Method dump skipped, instructions count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.k.a(android.database.Cursor):com.baidu.location.BDLocation");
    }

    static String a(BDLocation bDLocation, int i) {
        if (bDLocation == null || bDLocation.getLocType() == 67) {
            return String.format(Locale.CHINA, "&ofl=%s|%d", "1", Integer.valueOf(i));
        }
        String str = String.format(Locale.CHINA, "&ofl=%s|%d|%f|%f|%d", "1", Integer.valueOf(i), Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Integer.valueOf((int) bDLocation.getRadius()));
        if (bDLocation.getAddress() != null) {
            str = str + "&ofaddr=" + bDLocation.getAddress().address;
        }
        if (bDLocation.getPoiList() != null && bDLocation.getPoiList().size() > 0) {
            Poi poi = bDLocation.getPoiList().get(0);
            str = str + String.format(Locale.US, "&ofpoi=%s|%s", poi.getId(), poi.getName());
        }
        if (com.baidu.location.h.b.e == null) {
            return str;
        }
        return str + String.format(Locale.US, "&pack=%s&sdk=%.3f", com.baidu.location.h.b.e, Float.valueOf(9.601f));
    }

    static String a(BDLocation bDLocation, BDLocation bDLocation2, a aVar) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bDLocation2 == null ? "&ofcl=0" : String.format(Locale.US, "&ofcl=1|%f|%f|%d", Double.valueOf(bDLocation2.getLongitude()), Double.valueOf(bDLocation2.getLatitude()), Integer.valueOf((int) bDLocation2.getRadius())));
        stringBuffer.append(bDLocation == null ? "&ofwf=0" : String.format(Locale.US, "&ofwf=1|%f|%f|%d", Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Integer.valueOf((int) bDLocation.getRadius())));
        stringBuffer.append((aVar == null || !aVar.e) ? "&rgcn=0" : "&rgcn=1");
        stringBuffer.append((aVar == null || !aVar.d) ? "&poin=0" : "&poin=1");
        stringBuffer.append((aVar == null || !aVar.h) ? "&desc=0" : "&desc=1");
        if (aVar != null) {
            stringBuffer.append(String.format(Locale.US, "&aps=%d", Integer.valueOf(aVar.f)));
        }
        return stringBuffer.toString();
    }

    static String[] a(com.baidu.location.f.a aVar, com.baidu.location.f.p pVar, BDLocation bDLocation, String str, boolean z, int i) {
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        if (aVar != null) {
            stringBuffer.append(com.baidu.location.f.h.a().b(aVar));
        }
        if (pVar != null) {
            stringBuffer.append(com.baidu.location.f.h.a().a(30, false, pVar, s.ay));
        }
        if (stringBuffer.length() > 0) {
            if (str != null) {
                stringBuffer.append(str);
            }
            arrayList.add("-loc");
            arrayList.add(stringBuffer.toString());
        }
        if (bDLocation != null) {
            String str2 = String.format(Locale.US, "%f;%f;%d;%s", Double.valueOf(bDLocation.getLatitude()), Double.valueOf(bDLocation.getLongitude()), Integer.valueOf(bDLocation.getLocType()), bDLocation.getNetworkLocationType());
            arrayList.add("-com");
            arrayList.add(str2);
        }
        if (z) {
            arrayList.add("-log");
            arrayList.add("true");
        }
        if (s.e.equals("all")) {
            arrayList.add("-rgc");
            arrayList.add("true");
        }
        if (s.i) {
            arrayList.add("-poi");
            arrayList.add("true");
        }
        if (s.g) {
            arrayList.add("-des");
            arrayList.add("true");
        }
        arrayList.add("-minap");
        arrayList.add(Integer.toString(i));
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }
}
