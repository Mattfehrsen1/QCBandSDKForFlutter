package com.baidu.lbsapi.auth;

import android.content.pm.PackageManager;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes.dex */
class m implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ Hashtable e;
    final /* synthetic */ LBSAuthManager f;

    m(LBSAuthManager lBSAuthManager, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f = lBSAuthManager;
        this.a = i;
        this.b = z;
        this.c = str;
        this.d = str2;
        this.e = hashtable;
    }

    @Override // java.lang.Runnable
    public void run() throws NoSuchPaddingException, PackageManager.NameNotFoundException, NoSuchAlgorithmException, InvalidKeyException {
        b.a("status = " + this.a + "; forced = " + this.b + "checkAK = " + this.f.b(this.c));
        int i = this.a;
        if (i != 601 && !this.b && i != -1 && !this.f.b(this.c)) {
            if (602 == this.a) {
                b.a("authenticate wait ");
                if (LBSAuthManager.h != null) {
                    LBSAuthManager.h.b();
                }
            } else {
                b.a("authenticate else");
            }
            this.f.a((String) null, this.c);
            return;
        }
        b.a("authenticate sendAuthRequest");
        String[] strArrB = d.b(LBSAuthManager.a);
        if (strArrB == null || strArrB.length <= 1) {
            this.f.a(this.b, this.d, this.e, this.c, LBSAuthManager.b, LBSAuthManager.c, LBSAuthManager.d, LBSAuthManager.e);
            return;
        }
        b.a("authStrings.length:" + strArrB.length);
        b.a("more sha1 auth");
        this.f.a(this.b, this.d, (Hashtable<String, String>) this.e, strArrB, this.c, LBSAuthManager.b, LBSAuthManager.c, LBSAuthManager.d, LBSAuthManager.e);
    }
}
