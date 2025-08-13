package com.baidu.bdhttpdns;

import android.content.Context;
import com.baidu.bdhttpdns.BDHttpDns;
import com.baidu.bdhttpdns.h;
import com.baidu.bdhttpdns.i;
import java.util.Map;

/* loaded from: classes.dex */
public class k implements i.a {
    private final h a;
    private final BDHttpDns b;
    private final BDHttpDns.CachePolicy c;
    private final i d;

    public k(Context context) {
        BDHttpDns service = BDHttpDns.getService(context);
        this.b = service;
        this.a = service.a();
        this.c = service.c();
        this.d = service.d();
    }

    @Override // com.baidu.bdhttpdns.i.a
    public void a(int i, i.d dVar, Map<String, i.e> map, String str) {
        if (i != -1) {
            if (i != 0) {
                l.a("Internal error: async httpdns resolve completion get error ret(%d)", Integer.valueOf(i));
            } else {
                for (Map.Entry<String, i.e> entry : map.entrySet()) {
                    String key = entry.getKey();
                    i.e value = entry.getValue();
                    if (value != null) {
                        h.a aVar = new h.a();
                        aVar.a(value.c());
                        aVar.b(System.currentTimeMillis() / 1000);
                        aVar.a(value.a());
                        aVar.b(value.b());
                        this.a.a(key, aVar);
                    } else if (this.c == BDHttpDns.CachePolicy.POLICY_TOLERANT) {
                        this.a.b(key);
                    }
                }
            }
        } else if (dVar.equals(i.d.DNLIST_HOSTS) && this.c == BDHttpDns.CachePolicy.POLICY_TOLERANT) {
            for (String str2 : str.split(",")) {
                this.a.b(str2);
            }
        }
        if (this.b.e() <= 0 || this.d.f()) {
            return;
        }
        this.d.b(true);
        l.a("preResolve has finished", new Object[0]);
    }
}
