package com.qcwireless.qcwatch.ui.device.push.call;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.telecom.TelecomManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import com.android.internal.telecom.ITelecomService;
import com.android.internal.telephony.ITelephony;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.Permission;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.PhoneNotifyRsp;

/* loaded from: classes3.dex */
public class PhoneNotifyListener implements ICommandResponse<PhoneNotifyRsp> {
    private Context mContext;

    public PhoneNotifyListener(Context context) {
        this.mContext = context;
    }

    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(PhoneNotifyRsp resultEntity) {
        if (resultEntity.isReject()) {
            rejectCall();
        }
    }

    private void rejectCall() {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                endCall();
            }
            if (Build.VERSION.SDK_INT >= 29) {
                XLog.i("rejectCall.. SDK(>=29): " + Build.VERSION.SDK_INT);
                TelecomManager telecomManager = (TelecomManager) Class.forName("android.telecom.TelecomManager").getMethod(TypedValues.TransitionType.S_FROM, Context.class).invoke(null, this.mContext);
                if (ActivityCompat.checkSelfPermission(this.mContext, Permission.ANSWER_PHONE_CALLS) == 0) {
                    telecomManager.endCall();
                }
                ITelecomService.Stub.asInterface((IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "telecom")).endCall(null);
                return;
            }
            XLog.i("rejectCall.. SDK(<29): " + Build.VERSION.SDK_INT);
            ITelephony.Stub.asInterface((IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "phone")).endCall();
        } catch (ClassNotFoundException e) {
            e = e;
            e.printStackTrace();
        } catch (Error e2) {
            e = e2;
            e.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e = e3;
            e.printStackTrace();
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
        }
    }

    private void endCall() {
        try {
            TelecomManager telecomManager = (TelecomManager) this.mContext.getApplicationContext().getSystemService("telecom");
            if (ActivityCompat.checkSelfPermission(this.mContext, Permission.ANSWER_PHONE_CALLS) == 0) {
                XLog.i("endCall.. bool: " + telecomManager.endCall());
            } else {
                XLog.i("endCall.. noPermission..");
            }
        } catch (Error | Exception e) {
            XLog.i("endCall.. exception or error..");
            e.printStackTrace();
        }
    }
}
