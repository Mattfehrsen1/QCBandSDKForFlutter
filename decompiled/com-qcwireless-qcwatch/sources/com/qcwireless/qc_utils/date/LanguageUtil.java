package com.qcwireless.qc_utils.date;

import java.util.Locale;

/* loaded from: classes3.dex */
public class LanguageUtil {
    public static String localLanguage = "fr";

    public static boolean isChina() {
        return false;
    }

    public static boolean changeDateFormat() {
        return localLanguage.contains(Locale.getDefault().getLanguage());
    }
}
