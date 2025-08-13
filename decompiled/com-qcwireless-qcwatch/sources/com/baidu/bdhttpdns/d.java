package com.baidu.bdhttpdns;

import com.baidu.bdhttpdns.BDHttpDns;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import com.baidu.bdhttpdns.h;

/* loaded from: classes.dex */
class d implements Runnable {
    final /* synthetic */ BDHttpDns.CompletionHandler a;
    final /* synthetic */ BDHttpDnsResult.ResolveType b;
    final /* synthetic */ h.a c;
    final /* synthetic */ BDHttpDns d;

    d(BDHttpDns bDHttpDns, BDHttpDns.CompletionHandler completionHandler, BDHttpDnsResult.ResolveType resolveType, h.a aVar) {
        this.d = bDHttpDns;
        this.a = completionHandler;
        this.b = resolveType;
        this.c = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.completionHandler(new BDHttpDnsResult(this.b, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, this.c.b(), this.c.c()));
    }
}
