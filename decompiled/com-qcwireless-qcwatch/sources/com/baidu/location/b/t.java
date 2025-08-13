package com.baidu.location.b;

import android.content.Context;
import android.content.SharedPreferences;
import com.baidu.bdhttpdns.BDHttpDns;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.Dns;

/* loaded from: classes.dex */
public class t {
    private boolean a;
    private long b;
    private Context c;
    private BDHttpDns d;

    private static class a {
        private static final t a = new t();
    }

    public static class b implements Dns {
        private static b a;
        private BDHttpDns b;

        private b(BDHttpDns bDHttpDns) {
            this.b = bDHttpDns;
        }

        public static b a(BDHttpDns bDHttpDns) {
            if (a == null) {
                a = new b(bDHttpDns);
            }
            return a;
        }

        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (!com.baidu.location.h.e.a.equals(str)) {
                return Dns.SYSTEM.lookup(str);
            }
            BDHttpDnsResult bDHttpDnsResultSyncResolve = this.b.syncResolve(str, true);
            ArrayList<String> ipv6List = bDHttpDnsResultSyncResolve.getIpv6List();
            ArrayList<String> ipv4List = bDHttpDnsResultSyncResolve.getIpv4List();
            String str2 = null;
            if (ipv6List != null && !ipv6List.isEmpty()) {
                str2 = "[" + ipv6List.get(0) + "]";
            } else if (ipv4List != null && !ipv4List.isEmpty()) {
                str2 = ipv4List.get(0);
            }
            return str2 != null ? Arrays.asList(InetAddress.getAllByName(str2)) : Dns.SYSTEM.lookup(str);
        }
    }

    private t() {
        this.a = true;
        this.b = 0L;
    }

    public static t a() {
        return a.a;
    }

    private void a(BDLocation bDLocation, Context context) {
        double longitude;
        double latitude;
        String str;
        double[] dArrCoorEncrypt = new double[2];
        String coorType = bDLocation.getCoorType();
        if ("wgs84".equals(coorType)) {
            dArrCoorEncrypt[0] = bDLocation.getLongitude();
            dArrCoorEncrypt[1] = bDLocation.getLatitude();
        } else {
            double[] dArrCoorEncrypt2 = new double[2];
            if ("bd09ll".equals(coorType)) {
                longitude = bDLocation.getLongitude();
                latitude = bDLocation.getLatitude();
                str = BDLocation.BDLOCATION_BD09LL_TO_GCJ02;
            } else if ("bd09".equals(coorType)) {
                longitude = bDLocation.getLongitude();
                latitude = bDLocation.getLatitude();
                str = BDLocation.BDLOCATION_BD09_TO_GCJ02;
            } else {
                dArrCoorEncrypt2[0] = bDLocation.getLongitude();
                dArrCoorEncrypt2[1] = bDLocation.getLatitude();
                dArrCoorEncrypt = Jni.coorEncrypt(dArrCoorEncrypt2[0], dArrCoorEncrypt2[1], "gcj2wgs");
            }
            dArrCoorEncrypt2 = Jni.coorEncrypt(longitude, latitude, str);
            dArrCoorEncrypt = Jni.coorEncrypt(dArrCoorEncrypt2[0], dArrCoorEncrypt2[1], "gcj2wgs");
        }
        boolean zA = com.baidu.location.h.g.a().a(dArrCoorEncrypt[0], dArrCoorEncrypt[1]);
        try {
            this.b = System.currentTimeMillis();
            SharedPreferences sharedPreferencesA = af.a(context);
            if (sharedPreferencesA != null) {
                SharedPreferences.Editor editorEdit = sharedPreferencesA.edit();
                editorEdit.putBoolean("IsDomestic", zA);
                editorEdit.putLong("update_result_time", this.b);
                editorEdit.apply();
            }
        } catch (Exception unused) {
        }
    }

    public void a(Context context) {
        if (e.a().eb == 0) {
            return;
        }
        this.c = context;
        this.a = af.a(context).getBoolean("IsDomestic", true);
        this.b = af.a(context).getLong("update_result_time", 0L);
        if (this.a) {
            com.baidu.location.h.e.a();
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("loc.map.baidu.com"));
        BDHttpDns service = BDHttpDns.getService(context);
        this.d = service;
        try {
            service.setAccountID("110001");
            this.d.setSecret("nHpsFU98hcqhzFWY17Ht");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        this.d.setHttpsRequestEnable(true);
        this.d.setNetworkSwitchPolicy(true, true);
        this.d.setCachePolicy(BDHttpDns.CachePolicy.POLICY_TOLERANT);
        this.d.setPreResolveHosts(arrayList);
    }

    public void a(BDLocation bDLocation) {
        if (e.a().eb != 1 || bDLocation.getLongitude() == Double.MIN_VALUE || bDLocation.getLatitude() == Double.MIN_VALUE || System.currentTimeMillis() - this.b <= e.a().ec) {
            return;
        }
        a(bDLocation, this.c);
    }

    public boolean a(String str) {
        try {
            String host = new URL(str).getHost();
            if (this.a) {
                return false;
            }
            return com.baidu.location.h.e.a.equals(host);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int b() {
        return this.a ? 1 : 0;
    }

    public String b(String str) {
        if (this.a) {
            return str;
        }
        try {
            String host = new URL(str).getHost();
            if (host != null && !host.isEmpty()) {
                BDHttpDnsResult bDHttpDnsResultSyncResolve = this.d.syncResolve(host, true);
                ArrayList<String> ipv6List = bDHttpDnsResultSyncResolve.getIpv6List();
                ArrayList<String> ipv4List = bDHttpDnsResultSyncResolve.getIpv4List();
                String str2 = null;
                if (ipv6List != null && !ipv6List.isEmpty()) {
                    str2 = "[" + ipv6List.get(0) + "]";
                } else if (ipv4List != null && !ipv4List.isEmpty()) {
                    str2 = ipv4List.get(0);
                }
                return str2 != null ? str.replaceFirst(host, str2) : str;
            }
            return str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return str;
        }
    }

    public Dns c() {
        return this.a ? Dns.SYSTEM : b.a(this.d);
    }
}
