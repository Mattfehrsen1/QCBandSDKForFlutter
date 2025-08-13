package com.qcwireless.qcwatch.ui.device.push.call;

import android.telephony.PhoneStateListener;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.baidu.location.LocationConst;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* compiled from: MyPhoneStateListener.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/call/MyPhoneStateListener;", "Landroid/telephony/PhoneStateListener;", "()V", "onCallStateChanged", "", LocationConst.HDYawConst.KEY_HD_YAW_STATE, "", "phoneNumber", "", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyPhoneStateListener extends PhoneStateListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<MyPhoneStateListener> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MyPhoneStateListener>() { // from class: com.qcwireless.qcwatch.ui.device.push.call.MyPhoneStateListener$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MyPhoneStateListener invoke() {
            return new MyPhoneStateListener();
        }
    });

    @Override // android.telephony.PhoneStateListener
    public void onCallStateChanged(int state, String phoneNumber) {
        super.onCallStateChanged(state, phoneNumber);
        if (phoneNumber != null) {
            String str = phoneNumber;
            if (StringsKt.isBlank(str)) {
                return;
            }
            if (state == 0) {
                Log.d(NotificationCompat.CATEGORY_CALL, "incoming IDLE, number:" + phoneNumber);
                if (!TextUtils.isEmpty(str)) {
                    if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                        MessagePushUtil.pushCallMsg(4, XPhoneStateReceiver.INSTANCE.getDisplayNameByPhone(QCApplication.INSTANCE.getCONTEXT(), phoneNumber));
                        return;
                    }
                    return;
                } else {
                    if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                        MessagePushUtil.pushCallMsg(4, "Dialout");
                        return;
                    }
                    return;
                }
            }
            if (state != 1) {
                if (state != 2) {
                    return;
                }
                Log.d(NotificationCompat.CATEGORY_CALL, "incoming ACCEPT :" + phoneNumber);
                if (!TextUtils.isEmpty(str)) {
                    if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                        MessagePushUtil.pushCallMsg(4, XPhoneStateReceiver.INSTANCE.getDisplayNameByPhone(QCApplication.INSTANCE.getCONTEXT(), phoneNumber));
                        return;
                    }
                    return;
                } else {
                    if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                        MessagePushUtil.pushCallMsg(4, "Dialout");
                        return;
                    }
                    return;
                }
            }
            if (!BleOperateManager.getInstance().isConnected()) {
                new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
                ThreadManager.getInstance().wakeUp();
            }
            XLog.e("RINGING number:" + phoneNumber);
            if (!TextUtils.isEmpty(str)) {
                String displayNameByPhone = XPhoneStateReceiver.INSTANCE.getDisplayNameByPhone(QCApplication.INSTANCE.getCONTEXT(), phoneNumber);
                if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                    MessagePushUtil.pushCallMsg(0, displayNameByPhone);
                    return;
                }
                return;
            }
            if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                MessagePushUtil.pushCallMsg(0, phoneNumber);
            }
        }
    }

    /* compiled from: MyPhoneStateListener.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/call/MyPhoneStateListener$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/device/push/call/MyPhoneStateListener;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/device/push/call/MyPhoneStateListener;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MyPhoneStateListener getGetInstance() {
            return (MyPhoneStateListener) MyPhoneStateListener.getInstance$delegate.getValue();
        }
    }
}
