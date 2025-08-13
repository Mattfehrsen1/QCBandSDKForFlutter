package com.baidu.android.bbalbs.common.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.android.bbalbs.common.security.Base64;
import com.king.zxing.util.LogUtils;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.realsil.sdk.bbpro.params.Mmi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class b {
    private static final String e = a(new byte[]{81, 72, 116, 79, 75, 72, 69, Mmi.AU_MMAU_MMI_AV_FWD, LargeDataHandler.ACTION_SMS_QUICK, 51, Mmi.AU_MMI_OUTPUT_INDICATION_1, 61}, new byte[]{82, 51, Mmi.AU_MMI_OUTPUT_INDICATION_2, Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, Mmi.AU_MMI_DEV_LINK_LAST_DEVICE, Mmi.AU_MMI_ENTER_PAIRING_MODE_LONG_PRESS, 65, 105, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 49, Mmi.AU_MMI_AUDIO_EQ_SWITCH, 61});
    private static final String f = a(new byte[]{LargeDataHandler.ACTION_SMS_QUICK, Constants.CMD_GET_STEP_SOMEDAY_DETAIL, 77, Mmi.AU_MMI_AV_BWD, 77, Constants.CMD_QUERY_DATA_DISTRIBUTION, Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, 73, 81, Mmi.AU_MMI_AUDIO_EQ_SWITCH, Mmi.AU_MMI_AUDIO_EQ_SWITCH, 61}, new byte[]{Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, 105, 108, Mmi.AU_MMI_RWS_SYNC_RINGTONE, 79, Constants.CMD_GET_SLEEP, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, 81, 86, Mmi.AU_MMI_RWS_SYNC_RINGTONE, Mmi.AU_MMI_DEV_DISCONNECT_ALL_LINK, 61});
    private String a;
    private String b;
    private int c = 3;
    private int d;

    b() {
    }

    static b a(Context context, String str) {
        return b(context, str);
    }

    static b a(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("arg non-nullable is expected");
        }
        b bVar = new b();
        bVar.a(aVar.a());
        bVar.b(aVar.b());
        return bVar;
    }

    private static String a(byte[]... bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte[] bArr2 : bArr) {
            sb.append(new String(Base64.decode(bArr2)));
        }
        return sb.toString();
    }

    static b b(Context context) {
        File fileD = d(context);
        if (fileD.exists()) {
            return d(d.a(fileD));
        }
        return null;
    }

    private static b b(Context context, String str) {
        StringBuilder sb;
        b bVar = new b();
        boolean z = Build.VERSION.SDK_INT < 23;
        String strA = d.a(context);
        if (z) {
            String strE = e(context);
            if (TextUtils.isEmpty(strE)) {
                strE = UUID.randomUUID().toString();
                c(context, strE);
            }
            sb = new StringBuilder();
            sb.append(strA);
            sb.append(strE);
        } else {
            sb = new StringBuilder();
            sb.append("com.baidu");
            sb.append(strA);
        }
        bVar.a(com.baidu.android.bbalbs.common.security.b.a(sb.toString().getBytes(), true));
        bVar.b(str);
        bVar.a(Build.VERSION.SDK_INT);
        return bVar;
    }

    private String b() throws JSONException {
        try {
            JSONObject jSONObjectPut = new JSONObject().put(i("ZGV2aWNlaWQ="), this.a);
            String strI = i("ZmxhZw==");
            String str = this.b;
            if (str == null) {
                str = "0";
            }
            return jSONObjectPut.put(strI, str).put(i("dmVy"), this.c).put(i("c2Rr"), this.d).toString();
        } catch (JSONException e2) {
            d.a(e2);
            return null;
        }
    }

    static b c(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(i("ZmxhZw=="), "0");
            String string = jSONObject.getString(i("ZGV2aWNlaWQ="));
            int iOptInt = jSONObject.optInt(i("c2Rr"), 0);
            if (!TextUtils.isEmpty(string)) {
                b bVar = new b();
                bVar.a(string);
                bVar.b(strOptString);
                bVar.a(iOptInt);
                return bVar;
            }
        } catch (JSONException e2) {
            d.a(e2);
        }
        return null;
    }

    private static void c(Context context, String str) {
        if (TextUtils.isEmpty(d.a(context, "XL5g0WZAHpIaKspIHIHYg5k")) && d.b(context)) {
            d.a(context, "XL5g0WZAHpIaKspIHIHYg5k", g(str));
        }
    }

    private boolean c(Context context) throws IOException {
        String strE = e(b());
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            try {
                fileOutputStreamOpenFileOutput = context.openFileOutput("libcuid_v3.so", 0);
                fileOutputStreamOpenFileOutput.write(strE.getBytes());
                fileOutputStreamOpenFileOutput.flush();
                if (fileOutputStreamOpenFileOutput == null) {
                    return true;
                }
                try {
                    fileOutputStreamOpenFileOutput.close();
                    return true;
                } catch (Exception e2) {
                    d.a(e2);
                    return true;
                }
            } catch (Exception e3) {
                d.a(e3);
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e4) {
                        d.a(e4);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception e5) {
                    d.a(e5);
                }
            }
            throw th;
        }
    }

    static b d(String str) {
        return c(f(str));
    }

    private static File d(Context context) {
        return new File(context.getFilesDir(), "libcuid_v3.so");
    }

    private static String e(Context context) {
        return h(d.a(context, "XL5g0WZAHpIaKspIHIHYg5k"));
    }

    static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Base64.encode(com.baidu.android.bbalbs.common.security.a.a(e, f, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException | Exception e2) {
            d.a(e2);
            return "";
        }
    }

    static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(com.baidu.android.bbalbs.common.security.a.b(e, f, Base64.decode(str.getBytes())));
        } catch (Exception e2) {
            d.a(e2);
            return "";
        }
    }

    static String g(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Base64.encode(com.baidu.android.bbalbs.common.security.a.a(f, e, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException | Exception e2) {
            d.a(e2);
            return "";
        }
    }

    static String h(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(com.baidu.android.bbalbs.common.security.a.b(f, e, Base64.decode(str.getBytes())));
        } catch (Exception e2) {
            d.a(e2);
            return "";
        }
    }

    static String i(String str) {
        return new String(Base64.decode(str.getBytes()));
    }

    public String a() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = "0";
        }
        return this.a + LogUtils.VERTICAL + this.b;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(String str) {
        this.a = str;
    }

    boolean a(Context context) {
        return c(context);
    }

    public void b(String str) {
        this.b = str;
    }
}
