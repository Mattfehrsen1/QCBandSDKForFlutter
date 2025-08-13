package com.baidu.location.f;

import android.location.OnNmeaMessageListener;

/* loaded from: classes.dex */
class f implements OnNmeaMessageListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    @Override // android.location.OnNmeaMessageListener
    public void onNmeaMessage(String str, long j) {
        if (this.a.S != null) {
            this.a.S.sendMessage(this.a.S.obtainMessage(5, str));
        }
    }
}
