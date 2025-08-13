package com.qcwireless.qcwatch.ui.mine.privacy;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* compiled from: LanguagePPMURL.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"getLanguagePPMUrl", "", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LanguagePPMURLKt {
    public static final String getLanguagePPMUrl() {
        try {
            return StringsKt.equals(Locale.getDefault().getLanguage(), "it", true) ? WebUrl.PPM_URL_IT : "https://api1.qcwxkjvip.com/pro_privacy.html";
        } catch (Exception unused) {
            return "https://api1.qcwxkjvip.com/pro_privacy.html";
        }
    }
}
