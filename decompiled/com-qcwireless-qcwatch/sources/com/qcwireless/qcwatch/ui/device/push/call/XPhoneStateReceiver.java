package com.qcwireless.qcwatch.ui.device.push.call;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.thread.ThreadManager;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: XPhoneStateReceiver.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/call/XPhoneStateReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "mApp", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "onReceive", "", "context", "intent", "Landroid/content/Intent;", "Companion", "Contact", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class XPhoneStateReceiver extends BroadcastReceiver {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "XPhoneStateReceiver";
    private static boolean inComingFlag;
    private WeakReference<Context> mApp;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        try {
            this.mApp = new WeakReference<>(context);
            if (Intrinsics.areEqual("android.intent.action.NEW_OUTGOING_CALL", intent.getAction())) {
                String stringExtra = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
                Log.i(TAG, "OutGoingCall.. phoneNumber: " + stringExtra);
                return;
            }
            String stringExtra2 = intent.getStringExtra("incoming_number");
            WeakReference<Context> weakReference = this.mApp;
            if (weakReference == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mApp");
                weakReference = null;
            }
            Context context2 = weakReference.get();
            Object systemService = context2 != null ? context2.getSystemService("phone") : null;
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.telephony.TelephonyManager");
            }
            int callState = ((TelephonyManager) systemService).getCallState();
            if (callState == 0) {
                if (inComingFlag) {
                    Companion companion = INSTANCE;
                    inComingFlag = false;
                    XLog.i("incoming IDLE, number:" + stringExtra2);
                    if (!TextUtils.isEmpty(stringExtra2)) {
                        if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                            Context applicationContext = context.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                            MessagePushUtil.pushCallMsg(4, companion.getDisplayNameByPhone(applicationContext, stringExtra2));
                            return;
                        }
                        return;
                    }
                    if (stringExtra2 != null) {
                        if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                            MessagePushUtil.pushCallMsg(4, "Dialout");
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
                return;
            }
            if (callState == 1) {
                if (!BleOperateManager.getInstance().isConnected()) {
                    new NotificationUtils(QJavaApplication.getInstance().getApplication()).initBandNotification();
                    ThreadManager.getInstance().wakeUp();
                }
                Companion companion2 = INSTANCE;
                inComingFlag = true;
                XLog.i("RINGING :" + stringExtra2);
                if (!TextUtils.isEmpty(stringExtra2)) {
                    Context applicationContext2 = context.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext2, "context.applicationContext");
                    String displayNameByPhone = companion2.getDisplayNameByPhone(applicationContext2, stringExtra2);
                    XLog.i(displayNameByPhone);
                    if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                        MessagePushUtil.pushCallMsg(0, displayNameByPhone);
                        return;
                    }
                    return;
                }
                if (stringExtra2 == null || !UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                    return;
                }
                MessagePushUtil.pushCallMsg(0, "inComingNumber");
                return;
            }
            if (callState == 2 && inComingFlag) {
                Companion companion3 = INSTANCE;
                inComingFlag = false;
                XLog.i("incoming ACCEPT :" + stringExtra2);
                if (!TextUtils.isEmpty(stringExtra2)) {
                    if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                        Context applicationContext3 = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext3, "context.applicationContext");
                        MessagePushUtil.pushCallMsg(4, companion3.getDisplayNameByPhone(applicationContext3, stringExtra2));
                        return;
                    }
                    return;
                }
                if (stringExtra2 != null) {
                    if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                        MessagePushUtil.pushCallMsg(4, "Dialout");
                    }
                } else if (UserConfig.INSTANCE.getInstance().getCallPushEnable()) {
                    MessagePushUtil.pushCallMsg(4, "Dialout");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: XPhoneStateReceiver.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0007\"\u0004\b\n\u0010\u0004¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/call/XPhoneStateReceiver$Contact;", "", "number", "", "(Ljava/lang/String;)V", "displayName", "getDisplayName", "()Ljava/lang/String;", "setDisplayName", "getNumber", "setNumber", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Contact {
        private String displayName;
        private String number;

        public Contact(String str) {
            this.number = str;
            this.displayName = str;
        }

        public final String getNumber() {
            return this.number;
        }

        public final void setNumber(String str) {
            this.number = str;
        }

        public final String getDisplayName() {
            return this.displayName;
        }

        public final void setDisplayName(String str) {
            this.displayName = str;
        }
    }

    /* compiled from: XPhoneStateReceiver.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/call/XPhoneStateReceiver$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "inComingFlag", "", "getDisplayNameByPhone", "context", "Landroid/content/Context;", "phoneNum", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized String getDisplayNameByPhone(Context context, String phoneNum) {
            Intrinsics.checkNotNullParameter(context, "context");
            Contact contact = new Contact(phoneNum);
            if (TextUtils.isEmpty(phoneNum)) {
                contact.setDisplayName("");
            }
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNum)), new String[]{"display_name", "type", "label"}, null, null, "display_name LIMIT 1");
                    Intrinsics.checkNotNull(cursorQuery);
                    if (cursorQuery.moveToNext()) {
                        contact.setDisplayName(cursorQuery.getString(cursorQuery.getColumnIndex("display_name")));
                    }
                } catch (Exception unused) {
                    contact.setDisplayName(phoneNum);
                    if (cursorQuery != null) {
                    }
                }
                cursorQuery.close();
                if (!Intrinsics.areEqual("", contact.getDisplayName()) && contact.getDisplayName() != null) {
                    String displayName = contact.getDisplayName();
                    Intrinsics.checkNotNull(displayName);
                    String str = displayName;
                    int length = str.length() - 1;
                    int i = 0;
                    boolean z = false;
                    while (i <= length) {
                        boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
                        if (z) {
                            if (!z2) {
                                break;
                            }
                            length--;
                        } else if (z2) {
                            i++;
                        } else {
                            z = true;
                        }
                    }
                    phoneNum = str.subSequence(i, length + 1).toString();
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
            return phoneNum;
        }
    }
}
