package com.baidu.bdhttpdns;

import com.baidu.bdhttpdns.BDHttpDns;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import java.util.ArrayList;

/* loaded from: classes.dex */
class a implements Runnable {
    final /* synthetic */ BDHttpDns.CompletionHandler a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ BDHttpDns c;

    a(BDHttpDns bDHttpDns, BDHttpDns.CompletionHandler completionHandler, ArrayList arrayList) {
        this.c = bDHttpDns;
        this.a = completionHandler;
        this.b = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.completionHandler(new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_NONEED, BDHttpDnsResult.ResolveStatus.BDHttpDnsResolveOK, this.b, null));
    }
}
