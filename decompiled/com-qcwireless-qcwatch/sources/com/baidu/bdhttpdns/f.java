package com.baidu.bdhttpdns;

import com.baidu.bdhttpdns.BDHttpDnsResult;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class f {
    private static volatile f a;

    interface a {
        void a(int i, ArrayList<String> arrayList, ArrayList<String> arrayList2, long j, String str);
    }

    private class b implements Runnable {
        private String b;
        private a c;

        public b(String str, a aVar) {
            this.b = str;
            this.c = aVar;
        }

        @Override // java.lang.Runnable
        public void run() throws UnknownHostException {
            ArrayList<String> arrayList;
            ArrayList<String> arrayList2;
            Map mapB = f.this.b(this.b);
            if (mapB != null) {
                ArrayList<String> arrayList3 = (ArrayList) mapB.get("ipv4");
                arrayList2 = (ArrayList) mapB.get("ipv6");
                arrayList = arrayList3;
            } else {
                arrayList = null;
                arrayList2 = null;
            }
            this.c.a(((arrayList == null || arrayList.isEmpty()) && (arrayList2 == null || arrayList2.isEmpty())) ? -1 : 0, arrayList, arrayList2, 60L, this.b);
        }
    }

    private f() {
    }

    static f a() {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f();
                }
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, ArrayList> b(String str) throws UnknownHostException {
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            if (allByName == null || allByName.length == 0) {
                l.a("Dns resolve failed, host(%s), get empty resolve result", str);
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            HashMap map = new HashMap();
            for (InetAddress inetAddress : allByName) {
                if (inetAddress instanceof Inet4Address) {
                    String hostAddress = inetAddress.getHostAddress();
                    if (e.a(hostAddress)) {
                        arrayList.add(hostAddress);
                    }
                } else if (inetAddress instanceof Inet6Address) {
                    String hostAddress2 = inetAddress.getHostAddress();
                    if (e.b(hostAddress2)) {
                        arrayList2.add(hostAddress2);
                    }
                }
            }
            map.put("ipv4", arrayList);
            map.put("ipv6", arrayList2);
            if (arrayList.isEmpty() && arrayList2.isEmpty()) {
                l.a("Dns resolve failed, host(%s), get no valid resolve result", str);
                return null;
            }
            l.a("Dns resolve successful, host(%s), ipv4List(%s), ipv6List(%s)", str, arrayList.toString(), arrayList2.toString());
            return map;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            l.a("Dns resolve failed, host(%s), caught UnknownHostException", str);
            return null;
        }
    }

    BDHttpDnsResult a(String str) throws UnknownHostException {
        ArrayList arrayList;
        Map<String, ArrayList> mapB = b(str);
        ArrayList arrayList2 = null;
        if (mapB != null) {
            arrayList2 = mapB.get("ipv4");
            arrayList = mapB.get("ipv6");
        } else {
            arrayList = null;
        }
        return ((arrayList2 == null || arrayList2.isEmpty()) && (arrayList == null || arrayList.isEmpty())) ? new BDHttpDnsResult(BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveErrorDnsResolve) : new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, arrayList2, arrayList);
    }

    void a(String str, a aVar) {
        if (str == null || str.isEmpty()) {
            return;
        }
        m.a().b().execute(new b(str, aVar));
    }
}
