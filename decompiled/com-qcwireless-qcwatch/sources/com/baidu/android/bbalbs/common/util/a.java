package com.baidu.android.bbalbs.common.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.JsonReader;
import com.baidu.android.bbalbs.common.security.Base64;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.realsil.sdk.bbpro.params.Mmi;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/* loaded from: classes.dex */
final class a {
    private static final String e = new String(Base64.decode(new byte[]{77, Mmi.AU_MMI_ENTER_PAIRING_MODE_LONG_PRESS, 65, Mmi.AU_MMI_RWS_SYNC_RINGTONE, 77, 84, 73, 120, 77, Constants.CMD_GET_SLEEP, 73, 61})) + new String(Base64.decode(new byte[]{Mmi.AU_MMI_DEV_FACTORY_RESET_BY_SPP, LargeDataHandler.ACTION_Blood_Sugar, 108, 106, Mmi.AU_MMI_AUDIO_EFFECT_PREVIOUS, Mmi.AU_MMI_DEV_POWER_OFF_BUTTON_RELEASE, 82, 112, Mmi.AU_MMI_DEV_DISCONNECT_ALL_LINK, Mmi.AU_MMI_DEV_POWER_OFF_BUTTON_RELEASE, 73, 61}));
    private String a;
    private String b;
    private int c = 0;
    private int d = 2;

    a() {
    }

    static boolean a(Context context) {
        File fileC = c(context);
        if (fileC.exists()) {
            return fileC.delete();
        }
        return false;
    }

    static a b(Context context) {
        return d(d.a(c(context)));
    }

    public static boolean b(int i) {
        return i >= 14;
    }

    static a c(String str) throws Throwable {
        JsonReader jsonReader;
        JsonReader jsonReader2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            jsonReader = new JsonReader(new StringReader(str));
            try {
                jsonReader.beginObject();
                String strG = g("ZGV2aWNlaWQ=");
                String strG2 = g("dmVy");
                String strNextString = "0";
                String strNextString2 = "";
                int iNextInt = 2;
                while (jsonReader.hasNext()) {
                    String strNextName = jsonReader.nextName();
                    if (strG.equals(strNextName)) {
                        strNextString2 = jsonReader.nextString();
                    } else if (strG2.equals(strNextName)) {
                        iNextInt = jsonReader.nextInt();
                    } else {
                        strNextString = jsonReader.nextString();
                    }
                }
                jsonReader.endObject();
                int length = 0;
                if (iNextInt == 2 && !TextUtils.isEmpty(strNextString)) {
                    length = strNextString.length();
                }
                try {
                    jsonReader.close();
                } catch (Exception e2) {
                    d.a(e2);
                }
                if (TextUtils.isEmpty(strNextString2)) {
                    return null;
                }
                a aVar = new a();
                aVar.a(strNextString2);
                aVar.a(length);
                if (!aVar.d()) {
                    aVar.b(strNextString);
                }
                return aVar;
            } catch (IOException unused) {
                if (jsonReader != null) {
                    try {
                        jsonReader.close();
                    } catch (Exception e3) {
                        d.a(e3);
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                jsonReader2 = jsonReader;
                if (jsonReader2 != null) {
                    try {
                        jsonReader2.close();
                    } catch (Exception e4) {
                        d.a(e4);
                    }
                }
                throw th;
            }
        } catch (IOException unused2) {
            jsonReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static File c(Context context) {
        return new File(context.getFilesDir(), "libcuid.so");
    }

    static a d(String str) {
        return c(f(str));
    }

    public static boolean e(String str) {
        return TextUtils.isEmpty(str);
    }

    private static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String str2 = e;
            return new String(com.baidu.android.bbalbs.common.security.a.b(str2, str2, Base64.decode(str.getBytes())));
        } catch (Exception e2) {
            d.a(e2);
            return "";
        }
    }

    private static String g(String str) {
        return new String(Base64.decode(str.getBytes()));
    }

    public String a() {
        return this.a;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    boolean c() {
        String str;
        if (d()) {
            str = "O";
        } else {
            if (!e()) {
                return false;
            }
            str = "0";
        }
        this.b = str;
        return true;
    }

    public boolean d() {
        return b(this.c);
    }

    public boolean e() {
        return e(this.b);
    }
}
