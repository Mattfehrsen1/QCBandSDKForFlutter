package com.baidu.location;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/* loaded from: classes.dex */
class c implements ServiceConnection {
    final /* synthetic */ LocationClient a;

    c(LocationClient locationClient) {
        this.a = locationClient;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws RemoteException {
        this.a.g = new Messenger(iBinder);
        if (this.a.g == null) {
            return;
        }
        this.a.e = true;
        if (this.a.D) {
            this.a.h.obtainMessage(2).sendToTarget();
            return;
        }
        try {
            Message messageObtain = Message.obtain((Handler) null, 11);
            messageObtain.replyTo = this.a.i;
            messageObtain.setData(this.a.f());
            this.a.g.send(messageObtain);
            this.a.e = true;
            if (this.a.c != null) {
                this.a.G.booleanValue();
                this.a.h.obtainMessage(4).sendToTarget();
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.a.g = null;
        this.a.e = false;
    }
}
