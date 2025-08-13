package com.baidu.lbsapi.auth;

import android.text.TextUtils;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

/* loaded from: classes.dex */
class j extends Authenticator {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    @Override // java.net.Authenticator
    protected PasswordAuthentication getPasswordAuthentication() {
        if (!TextUtils.isEmpty(this.a.g) && !TextUtils.isEmpty(this.a.h)) {
            return new PasswordAuthentication(this.a.g, this.a.h.toCharArray());
        }
        b.a("Proxy Username or Password is null");
        return null;
    }
}
