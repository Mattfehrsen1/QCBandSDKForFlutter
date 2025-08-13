package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class l extends Handler {
    final /* synthetic */ LBSAuthManager a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    l(LBSAuthManager lBSAuthManager, Looper looper) {
        super(looper);
        this.a = lBSAuthManager;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) throws JSONException {
        b.a("handleMessage !!");
        if (message.what == 0) {
            this.a.a((JSONObject) message.obj);
        }
        LBSAuthManagerListener lBSAuthManagerListener = (LBSAuthManagerListener) LBSAuthManager.j.get(message.getData().getString("listenerKey"));
        b.a("handleMessage listener = " + lBSAuthManagerListener);
        if (lBSAuthManagerListener != null) {
            lBSAuthManagerListener.onAuthResult(message.what, message.obj.toString());
        }
    }
}
