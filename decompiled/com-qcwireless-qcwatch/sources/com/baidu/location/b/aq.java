package com.baidu.location.b;

import android.location.GnssNavigationMessage;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
class aq extends Handler {
    final /* synthetic */ ap a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aq(ap apVar, Looper looper) {
        super(looper);
        this.a = apVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        com.baidu.location.f.a aVarC;
        com.baidu.location.f.p pVarU;
        Location locationD;
        String strA;
        switch (message.what) {
            case 1:
                Bundle data = message.getData();
                try {
                    Location location = (Location) data.getParcelable("loc");
                    data.getInt("satnum");
                    if (location != null) {
                        o.a().a(location);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 2:
                aVarC = aj.c();
                pVarU = com.baidu.location.f.h.a().u();
                locationD = aj.d();
                strA = aj.a();
                break;
            case 3:
                aVarC = aj.c();
                pVarU = null;
                locationD = aj.d();
                strA = c.a().d();
                break;
            case 4:
                boolean zM = com.baidu.location.f.h.a().m();
                boolean z = false;
                if (com.baidu.location.h.s.b()) {
                    zM = false;
                }
                if (!zM) {
                    z = zM;
                } else if (s.a().d() != 1) {
                    z = true;
                }
                if (z) {
                    if (com.baidu.location.c.d.a().e()) {
                        com.baidu.location.e.i.a().m();
                        com.baidu.location.e.i.a().i();
                    }
                    com.baidu.location.c.h.a().c();
                    if (com.baidu.location.c.d.a().e()) {
                        ao.a().c();
                    }
                }
                try {
                    if (this.a.d != null) {
                        this.a.d.sendEmptyMessageDelayed(4, com.baidu.location.h.s.Q);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                an.a().b();
                return;
            case 5:
                com.baidu.location.c.h.a().b();
                return;
            case 6:
            case 10:
            default:
                return;
            case 7:
                ao.a().c();
                com.baidu.location.c.h.a().c();
                return;
            case 8:
            case 9:
                message.getData();
                return;
            case 11:
                Bundle data2 = message.getData();
                try {
                    an.a().a((GnssNavigationMessage) data2.getParcelable("gnss_navigation_message"), data2.getLong("gps_time"));
                    return;
                } catch (Exception unused) {
                    return;
                }
        }
        ao.a(aVarC, pVarU, locationD, strA, aj.e());
    }
}
