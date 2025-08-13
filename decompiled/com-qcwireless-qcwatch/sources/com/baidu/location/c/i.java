package com.baidu.location.c;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import org.json.JSONException;

/* loaded from: classes.dex */
class i extends Handler {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) throws JSONException, IOException {
        if (message.what != 1) {
            return;
        }
        this.a.d();
    }
}
