package com.oudmon.ble.base.communication.responseImpl;

import android.content.Context;
import android.telecom.TelecomManager;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.PhoneNotifyRsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/responseImpl/PhoneNotifyListener.class */
public class PhoneNotifyListener implements ICommandResponse<PhoneNotifyRsp> {
    private Context mContext;

    public PhoneNotifyListener(Context context) {
        this.mContext = context;
    }

    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(PhoneNotifyRsp resultEntity) {
    }

    @RequiresApi(api = Constants.CMD_GET_MUSIC_SWITCH)
    private void endCall() {
        try {
            TelecomManager telManager = (TelecomManager) this.mContext.getApplicationContext().getSystemService("telecom");
            if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ANSWER_PHONE_CALLS") == 0) {
                boolean bool = telManager.endCall();
                Log.i(Constants.TAG, "endCall.. bool: " + bool);
            } else {
                Log.i(Constants.TAG, "endCall.. noPermission..");
            }
        } catch (Error | Exception e) {
            Log.i(Constants.TAG, "endCall.. exception or error..");
            e.printStackTrace();
        }
    }
}
