package com.baidu.bdhttpdns;

import android.content.Context;
import android.content.IntentFilter;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import com.baidu.bdhttpdns.h;
import com.baidu.bdhttpdns.i;
import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: classes.dex */
public final class BDHttpDns {
    private static volatile BDHttpDns a;
    private BDNetworkStateChangeReceiver f;
    private final Context g;
    private long i;
    private long j;
    private int k;
    private final i b = i.a();
    private final f c = f.a();
    private final h d = new h("DNS", true);
    private final h e = new h("HTTPDNS", false);
    private CachePolicy h = CachePolicy.POLICY_TOLERANT;

    public enum CachePolicy {
        POLICY_AGGRESSIVE,
        POLICY_TOLERANT,
        POLICY_STRICT
    }

    public interface CompletionHandler {
        void completionHandler(BDHttpDnsResult bDHttpDnsResult);
    }

    private BDHttpDns(Context context) {
        this.g = context;
        f();
        this.f.refreshIpReachable();
        this.j = System.currentTimeMillis();
    }

    private boolean a(long j) {
        return this.b.f() || j - this.i > 1000;
    }

    private boolean b(long j) {
        if (j - this.j <= 60000) {
            return false;
        }
        this.j = j;
        return true;
    }

    private void f() {
        this.f = new BDNetworkStateChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.g.registerReceiver(this.f, intentFilter);
    }

    public static BDHttpDns getService(Context context) {
        if (a == null) {
            synchronized (BDHttpDns.class) {
                if (a == null) {
                    a = new BDHttpDns(context);
                }
            }
        }
        return a;
    }

    h a() {
        return this.e;
    }

    public void asyncResolve(String str, CompletionHandler completionHandler) {
        if (e.a(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            m.a().b().execute(new a(this, completionHandler, arrayList));
            return;
        }
        if (e.b(str)) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str.replaceAll("[\\[\\]]", ""));
            m.a().b().execute(new b(this, completionHandler, arrayList2));
            return;
        }
        h.a aVarA = this.e.a(str);
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList<String> arrayList3 = new ArrayList<>();
        if (a(jCurrentTimeMillis)) {
            if (aVarA == null) {
                arrayList3.add(str);
            } else if (aVarA.a()) {
                this.b.a(str);
            }
            if (b(jCurrentTimeMillis)) {
                arrayList3.addAll(this.b.c());
            }
            this.b.a(arrayList3, new k(this.g));
        } else {
            l.a("please wait a moment to send request for %s, until preResolve finished or has passed 1000ms ", str);
        }
        if (aVarA != null) {
            BDHttpDnsResult.ResolveType resolveType = aVarA.a() ? BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_EXPIRED_CACHE : BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_CACHE;
            Object[] objArr = new Object[4];
            objArr[0] = str;
            objArr[1] = aVarA.b() != null ? aVarA.b().toString() : null;
            objArr[2] = aVarA.c() != null ? aVarA.c().toString() : null;
            objArr[3] = resolveType.toString();
            l.a("Async resolve successful, host(%s) ipv4List(%s) ipv6List(%s) resolveType(%s)", objArr);
            m.a().b().execute(new c(this, completionHandler, resolveType, aVarA));
            return;
        }
        h.a aVarA2 = this.d.a(str);
        if (aVarA2 == null) {
            this.c.a(str, new g(this.g, completionHandler));
            return;
        }
        BDHttpDnsResult.ResolveType resolveType2 = BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS_CACHE;
        Object[] objArr2 = new Object[4];
        objArr2[0] = str;
        objArr2[1] = aVarA2.b() != null ? aVarA2.b().toString() : null;
        objArr2[2] = aVarA2.c() != null ? aVarA2.c().toString() : null;
        objArr2[3] = resolveType2.toString();
        l.a("Async resolve successful, host(%s) ipv4List(%s) ipv6List(%s) resolveType(%s)", objArr2);
        m.a().b().execute(new d(this, completionHandler, resolveType2, aVarA2));
    }

    h b() {
        return this.d;
    }

    CachePolicy c() {
        return this.h;
    }

    i d() {
        return this.b;
    }

    int e() {
        return this.k;
    }

    public void setAccountID(String str) {
        if (str.length() <= 64) {
            this.b.c(str);
            l.a("Set account id to %s", str);
        } else {
            throw new IllegalArgumentException("accountID length(" + str.length() + ") is bigger than 64");
        }
    }

    public void setCachePolicy(CachePolicy cachePolicy) {
        this.h = cachePolicy;
        if (cachePolicy == CachePolicy.POLICY_STRICT) {
            this.e.a(true);
        } else {
            this.e.a(false);
        }
        l.a("Set cache policy to %s", cachePolicy.name());
    }

    public void setHttpsRequestEnable(boolean z) {
        this.b.a(z);
        l.a("Set https enabled to %b", Boolean.valueOf(z));
    }

    public void setLogEnable(boolean z) {
        l.a(z);
        l.a("Set debug log enabled to %b", Boolean.valueOf(z));
    }

    public void setNetworkSwitchPolicy(boolean z, boolean z2) {
        this.f.a(z);
        this.f.b(z2);
        l.a("Set network change policy, clearCache(%b), httpDnsPrefetch(%b)", Boolean.valueOf(z), Boolean.valueOf(z2));
    }

    public void setPreResolveHosts(ArrayList<String> arrayList) {
        if (arrayList.size() <= 0) {
            l.a("Set pre resolve hosts error, get empty hosts", new Object[0]);
            return;
        }
        ArrayList arrayList2 = new ArrayList(new HashSet(arrayList));
        int iD = this.b.d();
        if (arrayList2.size() > iD) {
            l.a("The current number of hosts is %d, and the max supported size is %s.Please reduce it to %s or less.", Integer.valueOf(arrayList2.size()), Integer.valueOf(iD), Integer.valueOf(iD));
            return;
        }
        int i = this.k + 1;
        this.k = i;
        if (i > 1) {
            l.a("You have already set PreResolveHosts, it is best to set it only once.", new Object[0]);
        }
        this.i = System.currentTimeMillis();
        String str = "";
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            str = str + ((String) arrayList2.get(i2)) + ",";
        }
        if (str == null || str.isEmpty()) {
            return;
        }
        String strSubstring = str.substring(0, str.length() - 1);
        l.a("Set pre resolve hosts: %s", strSubstring);
        this.b.a(strSubstring, i.d.DNLIST_HOSTS, new k(this.g));
    }

    public void setPreResolveTag(String str) {
        if (str == null || str.isEmpty()) {
            l.a("Set pre resolve hosts error, get empty tag", new Object[0]);
            return;
        }
        int i = this.k + 1;
        this.k = i;
        if (i > 1) {
            l.a("You have already set PreResolveHosts, it is best to set it only once.", new Object[0]);
        }
        l.a(" Set preResolve tag : %s", str.toString());
        k kVar = new k(this.g);
        this.i = System.currentTimeMillis();
        this.b.a(str, i.d.TAG_OF_HOSTS, kVar);
    }

    public void setSecret(String str) {
        int length = str.length();
        if (length > 64 || length < 8) {
            throw new IllegalArgumentException("secret length(" + str.length() + ") check failed");
        }
        this.b.d(str);
        String strSubstring = str.substring(0, 3);
        for (int i = 0; i < length - 6; i++) {
            strSubstring = strSubstring + String.valueOf('*');
        }
        l.a("Set secret to %s", strSubstring + str.substring(length - 3));
    }

    public BDHttpDnsResult syncResolve(String str, boolean z) {
        if (e.a(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            return new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_NONEED, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, arrayList, null);
        }
        if (e.b(str)) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str.replaceAll("[\\[\\]]", ""));
            return new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_NONEED, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, null, arrayList2);
        }
        BDHttpDnsResult.ResolveType resolveType = BDHttpDnsResult.ResolveType.RESOLVE_NONE;
        h.a aVarA = this.e.a(str);
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList<String> arrayList3 = new ArrayList<>();
        if (a(jCurrentTimeMillis)) {
            if (aVarA == null) {
                arrayList3.add(str);
            } else if (aVarA.a()) {
                this.b.a(str);
            }
            if (b(jCurrentTimeMillis)) {
                arrayList3.addAll(this.b.c());
            }
            this.b.a(arrayList3, new k(this.g));
        } else {
            l.a("please wait a moment to send request for %s, until preResolve finished or has passed 1000ms ", str);
        }
        if (aVarA != null) {
            BDHttpDnsResult.ResolveType resolveType2 = aVarA.a() ? BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_EXPIRED_CACHE : BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_CACHE;
            Object[] objArr = new Object[4];
            objArr[0] = str;
            objArr[1] = aVarA.b() != null ? aVarA.b().toString() : null;
            objArr[2] = aVarA.c() != null ? aVarA.c().toString() : null;
            objArr[3] = resolveType2.toString();
            l.a("Sync resolve successful, host(%s) ipv4List(%s) ipv6List(%s) resolveType(%s)", objArr);
            return new BDHttpDnsResult(resolveType2, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, aVarA.b(), aVarA.c());
        }
        if (aVarA == null && z) {
            l.a("Sync resolve failed, host(%s), find no httpdns cache entry and cacheOnly is true", str);
            return new BDHttpDnsResult(resolveType, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveErrorCacheMiss, null, null);
        }
        h.a aVarA2 = this.d.a(str);
        if (aVarA2 != null) {
            BDHttpDnsResult.ResolveType resolveType3 = BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS_CACHE;
            Object[] objArr2 = new Object[4];
            objArr2[0] = str;
            objArr2[1] = aVarA2.b() != null ? aVarA2.b().toString() : null;
            objArr2[2] = aVarA2.c() != null ? aVarA2.c().toString() : null;
            objArr2[3] = resolveType3.toString();
            l.a("Sync resolve successful, host(%s) ipv4List(%s) ipv6List(%s) resolveType(%s)", objArr2);
            return new BDHttpDnsResult(resolveType3, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, aVarA2.b(), aVarA2.c());
        }
        BDHttpDnsResult bDHttpDnsResultA = this.c.a(str);
        if (bDHttpDnsResultA.getResolveStatus() == BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK) {
            h.a aVar = new h.a();
            aVar.a(60L);
            aVar.b(System.currentTimeMillis() / 1000);
            aVar.a(bDHttpDnsResultA.getIpv4List());
            aVar.b(bDHttpDnsResultA.getIpv6List());
            this.d.a(str, aVar);
            Object[] objArr3 = new Object[4];
            objArr3[0] = str;
            objArr3[1] = aVar.b() != null ? aVar.b().toString() : null;
            objArr3[2] = aVar.c() != null ? aVar.c().toString() : null;
            objArr3[3] = bDHttpDnsResultA.getResolveType().toString();
            l.a("Sync resolve successful, host(%s) ipList(%s) ipv6List(%s) resolveType(%s)", objArr3);
        } else {
            l.a("Sync resolve failed, host(%s), dns resolve failed", str);
        }
        return bDHttpDnsResultA;
    }
}
