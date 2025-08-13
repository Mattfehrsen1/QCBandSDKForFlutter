package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.g;

/* loaded from: classes.dex */
class o implements g.a<String> {
    final /* synthetic */ String a;
    final /* synthetic */ LBSAuthManager b;

    o(LBSAuthManager lBSAuthManager, String str) {
        this.b = lBSAuthManager;
        this.a = str;
    }

    @Override // com.baidu.lbsapi.auth.g.a
    public void a(String str) {
        this.b.a(str, this.a);
    }
}
