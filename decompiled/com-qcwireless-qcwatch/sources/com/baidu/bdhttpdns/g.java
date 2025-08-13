package com.baidu.bdhttpdns;

import android.content.Context;
import com.baidu.bdhttpdns.BDHttpDns;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import com.baidu.bdhttpdns.f;
import com.baidu.bdhttpdns.h;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class g implements f.a {
    private final BDHttpDns.CompletionHandler a;
    private final BDHttpDns b;
    private final h c;

    public g(Context context, BDHttpDns.CompletionHandler completionHandler) {
        this.a = completionHandler;
        BDHttpDns service = BDHttpDns.getService(context);
        this.b = service;
        this.c = service.b();
    }

    @Override // com.baidu.bdhttpdns.f.a
    public void a(int i, ArrayList<String> arrayList, ArrayList<String> arrayList2, long j, String str) {
        BDHttpDns.CompletionHandler completionHandler;
        BDHttpDnsResult bDHttpDnsResult;
        if (i == -1) {
            l.a("Async resolve failed, host(%s), dns resolve failed", str);
            completionHandler = this.a;
            if (completionHandler == null) {
                return;
            } else {
                bDHttpDnsResult = new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_NONE, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveErrorDnsResolve, arrayList, arrayList2);
            }
        } else {
            if (i != 0) {
                l.a("Internal error: async dns resolve completion get error ret(%d)", Integer.valueOf(i));
                return;
            }
            Object[] objArr = new Object[4];
            objArr[0] = str;
            objArr[1] = arrayList != null ? arrayList.toString() : null;
            objArr[2] = arrayList2 != null ? arrayList2.toString() : null;
            objArr[3] = BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS.toString();
            l.a("Async resolve successful, host(%s) ipv4List(%s) ipv6List(%s) resolveType(%s)", objArr);
            h.a aVar = new h.a();
            aVar.a(60L);
            aVar.b(System.currentTimeMillis() / 1000);
            aVar.a(arrayList);
            aVar.b(arrayList2);
            this.c.a(str, aVar);
            completionHandler = this.a;
            if (completionHandler == null) {
                return;
            } else {
                bDHttpDnsResult = new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, arrayList, arrayList2);
            }
        }
        completionHandler.completionHandler(bDHttpDnsResult);
    }
}
