package com.baidu.location.h;

import com.baidu.location.b.ae;

/* loaded from: classes.dex */
class j implements ae.a {
    final /* synthetic */ long a;
    final /* synthetic */ h b;

    j(h hVar, long j) {
        this.b = hVar;
        this.a = j;
    }

    @Override // com.baidu.location.b.ae.a
    public void a(int i, String str) {
        this.b.eg = null;
        this.b.a(false);
    }

    @Override // com.baidu.location.b.ae.a
    public void a(int i, String str, byte[] bArr) {
        h hVar;
        boolean z;
        if (i == 200) {
            this.b.ep = System.currentTimeMillis() - this.a;
            this.b.eg = str;
            this.b.eh = bArr;
            hVar = this.b;
            z = true;
        } else {
            this.b.eg = null;
            hVar = this.b;
            z = false;
        }
        hVar.a(z);
    }
}
