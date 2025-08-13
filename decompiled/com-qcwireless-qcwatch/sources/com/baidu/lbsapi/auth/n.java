package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.e;

/* loaded from: classes.dex */
class n implements e.a<String> {
    final /* synthetic */ String a;
    final /* synthetic */ LBSAuthManager b;

    n(LBSAuthManager lBSAuthManager, String str) {
        this.b = lBSAuthManager;
        this.a = str;
    }

    @Override // com.baidu.lbsapi.auth.e.a
    public void a(String str) {
        this.b.a(str, this.a);
    }
}
