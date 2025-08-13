package com.baidu.location.b;

import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
class q extends Thread {
    final /* synthetic */ o a;

    q(o oVar) {
        this.a = oVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws IOException {
        this.a.a(new File(com.baidu.location.h.s.h() + "/baidu/tempdata", "intime.dat"), "https://itsdata.map.baidu.com/long-conn-gps/sdk.php");
    }
}
