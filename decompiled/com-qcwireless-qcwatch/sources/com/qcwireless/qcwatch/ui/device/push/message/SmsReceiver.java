package com.qcwireless.qcwatch.ui.device.push.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SmsReceiver.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0005\u0014\u0015\u0016\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "setMHandler", "(Landroid/os/Handler;)V", "addMessage", "", NotificationCompat.CATEGORY_MESSAGE, "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "CallMessage", "Companion", "Contact", "Sms", "SmsInfo", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SmsReceiver extends BroadcastReceiver {
    public static final boolean D = true;
    private static final String PHONE_360_RECEIVED = "com.qiku.android.action.NEW_MESSAGE";
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String SMS_RECEIVED_NEW = "android.provider.Telephony.SMS_DELIVER";
    private final String TAG = getClass().getSimpleName();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static CallMessage smsMessage = new CallMessage();

    public final Handler getMHandler() {
        return this.mHandler;
    }

    public final void setMHandler(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        this.mHandler = handler;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Sms sms;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if ((Intrinsics.areEqual(intent.getAction(), SMS_RECEIVED) || Intrinsics.areEqual(intent.getAction(), SMS_RECEIVED_NEW)) && (sms = INSTANCE.getSms(context, intent)) != null) {
            Contact contact = sms.getContact();
            Intrinsics.checkNotNull(contact);
            String strReplace$default = StringsKt.replace$default(contact.getDisplayName(), " ", "", false, 4, (Object) null);
            String body = sms.getBody();
            if (sms.getBody() != null) {
                strReplace$default = strReplace$default + ':' + body;
            }
            if (strReplace$default == null) {
                strReplace$default = sms.getNumber() + body;
            }
            addMessage(strReplace$default);
            XLog.i("短信广播收到的短信内容为：" + strReplace$default);
        }
    }

    private final synchronized void addMessage(String msg) {
        XLog.i(msg);
        if (UserConfig.INSTANCE.getInstance().getSmsPushEnable()) {
            synchronized (smsMessage) {
                System.currentTimeMillis();
                smsMessage.getLast_receive_time();
                smsMessage.setLast_msg(msg);
                smsMessage.setLast_receive_time(System.currentTimeMillis());
                MessagePushUtil.pushSmsMsg(1, msg);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* compiled from: SmsReceiver.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B%\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Sms;", "", "()V", "number", "", "body", "contact", "Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Contact;", "(Ljava/lang/String;Ljava/lang/String;Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Contact;)V", "getBody", "()Ljava/lang/String;", "setBody", "(Ljava/lang/String;)V", "getContact", "()Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Contact;", "setContact", "(Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Contact;)V", "getNumber", "setNumber", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Sms {
        private String body;
        private Contact contact;
        private String number;

        public Sms() {
        }

        public Sms(String str, String str2, Contact contact) {
            this.number = str;
            this.body = str2;
            this.contact = contact;
        }

        public final String getNumber() {
            return this.number;
        }

        public final void setNumber(String str) {
            this.number = str;
        }

        public final String getBody() {
            return this.body;
        }

        public final void setBody(String str) {
            this.body = str;
        }

        public final Contact getContact() {
            return this.contact;
        }

        public final void setContact(Contact contact) {
            this.contact = contact;
        }
    }

    /* compiled from: SmsReceiver.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0007\"\u0004\b\n\u0010\u0004¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Contact;", "", "number", "", "(Ljava/lang/String;)V", "displayName", "getDisplayName", "()Ljava/lang/String;", "setDisplayName", "getNumber", "setNumber", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Contact {
        private String displayName;
        private String number;

        public Contact(String number) {
            Intrinsics.checkNotNullParameter(number, "number");
            this.number = number;
            this.displayName = number;
        }

        public final String getNumber() {
            return this.number;
        }

        public final void setNumber(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.number = str;
        }

        public final String getDisplayName() {
            return this.displayName;
        }

        public final void setDisplayName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.displayName = str;
        }
    }

    /* compiled from: SmsReceiver.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0018\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\b¨\u0006\""}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$SmsInfo;", "", "(Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver;)V", "_id", "", "get_id", "()Ljava/lang/String;", "set_id", "(Ljava/lang/String;)V", "action", "", "getAction", "()I", "setAction", "(I)V", "date", "getDate", "setDate", "person", "getPerson", "setPerson", "read", "getRead", "setRead", "smsAddress", "getSmsAddress", "setSmsAddress", "smsBody", "getSmsBody", "setSmsBody", "thread_id", "getThread_id", "setThread_id", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class SmsInfo {
        private int action;
        private String _id = "";
        private String thread_id = "";
        private String smsAddress = "";
        private String smsBody = "";
        private String read = "";
        private String date = "";
        private String person = "";

        public SmsInfo() {
        }

        public final String get_id() {
            return this._id;
        }

        public final void set_id(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this._id = str;
        }

        public final String getThread_id() {
            return this.thread_id;
        }

        public final void setThread_id(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.thread_id = str;
        }

        public final String getSmsAddress() {
            return this.smsAddress;
        }

        public final void setSmsAddress(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.smsAddress = str;
        }

        public final String getSmsBody() {
            return this.smsBody;
        }

        public final void setSmsBody(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.smsBody = str;
        }

        public final String getRead() {
            return this.read;
        }

        public final void setRead(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.read = str;
        }

        public final String getDate() {
            return this.date;
        }

        public final void setDate(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.date = str;
        }

        public final String getPerson() {
            return this.person;
        }

        public final void setPerson(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.person = str;
        }

        public final int getAction() {
            return this.action;
        }

        public final void setAction(int i) {
            this.action = i;
        }

        public String toString() {
            return "SmsInfo{_id='" + this._id + "', thread_id='" + this.thread_id + "', smsAddress='" + this.smsAddress + "', smsBody='" + this.smsBody + "', read='" + this.read + "', date='" + this.date + "', person='" + this.person + "', action=" + this.action + '}';
        }
    }

    /* compiled from: SmsReceiver.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$CallMessage;", "", "()V", "last_msg", "", "getLast_msg", "()Ljava/lang/String;", "setLast_msg", "(Ljava/lang/String;)V", "last_receive_time", "", "getLast_receive_time", "()J", "setLast_receive_time", "(J)V", "type", "", "getType", "()I", "setType", "(I)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class CallMessage {
        private String last_msg = "";
        private long last_receive_time;
        private int type;

        public final long getLast_receive_time() {
            return this.last_receive_time;
        }

        public final void setLast_receive_time(long j) {
            this.last_receive_time = j;
        }

        public final String getLast_msg() {
            return this.last_msg;
        }

        public final void setLast_msg(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.last_msg = str;
        }

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }
    }

    /* compiled from: SmsReceiver.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Companion;", "", "()V", "D", "", "PHONE_360_RECEIVED", "", "SMS_RECEIVED", "SMS_RECEIVED_NEW", "smsMessage", "Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$CallMessage;", "getSmsMessage", "()Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$CallMessage;", "setSmsMessage", "(Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$CallMessage;)V", "getContact", "Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Contact;", "context", "Landroid/content/Context;", "phoneNumber", "getSms", "Lcom/qcwireless/qcwatch/ui/device/push/message/SmsReceiver$Sms;", "intent", "Landroid/content/Intent;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CallMessage getSmsMessage() {
            return SmsReceiver.smsMessage;
        }

        public final void setSmsMessage(CallMessage callMessage) {
            Intrinsics.checkNotNullParameter(callMessage, "<set-?>");
            SmsReceiver.smsMessage = callMessage;
        }

        public final Sms getSms(Context context, Intent intent) {
            Object[] objArr;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            StringBuilder sb = new StringBuilder("");
            StringBuilder sb2 = new StringBuilder("");
            Bundle extras = intent.getExtras();
            if (extras == null || (objArr = (Object[]) extras.get("pdus")) == null || objArr.length == 0) {
                return null;
            }
            int length = objArr.length;
            SmsMessage[] smsMessageArr = new SmsMessage[length];
            int length2 = objArr.length;
            for (int i = 0; i < length2; i++) {
                smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
            }
            for (int i2 = 0; i2 < length; i2++) {
                SmsMessage smsMessage = smsMessageArr[i2];
                String string = sb.toString();
                Intrinsics.checkNotNull(smsMessage);
                if (!Intrinsics.areEqual(string, smsMessage.getDisplayOriginatingAddress())) {
                    sb.append(smsMessage.getDisplayOriginatingAddress());
                }
                sb2.append(smsMessage.getDisplayMessageBody());
            }
            String string2 = sb.toString();
            String string3 = sb2.toString();
            String string4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string4, "number.toString()");
            return new Sms(string2, string3, getContact(context, string4));
        }

        public final Contact getContact(Context context, String phoneNumber) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
            Contact contact = new Contact(phoneNumber);
            if (TextUtils.isEmpty(phoneNumber)) {
                contact.setDisplayName("Unknown Number");
            }
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber)), new String[]{"display_name", "type", "label"}, null, null, "display_name LIMIT 1");
                    Intrinsics.checkNotNull(cursorQuery);
                    if (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("display_name"));
                        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…honeLookup.DISPLAY_NAME))");
                        contact.setDisplayName(string);
                    }
                } catch (Exception unused) {
                    contact.setDisplayName(phoneNumber);
                    if (cursorQuery != null) {
                    }
                }
                cursorQuery.close();
                return contact;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
    }
}
