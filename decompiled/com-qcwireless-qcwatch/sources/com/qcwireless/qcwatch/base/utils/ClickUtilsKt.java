package com.qcwireless.qcwatch.base.utils;

import kotlin.Metadata;

/* compiled from: ClickUtils.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0006\u0010\u0004\u001a\u00020\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"MIN_CLICK_DELAY_TIME", "", "lastClickTime", "", "isNotFastClick", "", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ClickUtilsKt {
    private static final int MIN_CLICK_DELAY_TIME = 3000;
    private static long lastClickTime;

    public static final boolean isNotFastClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastClickTime < 3000) {
            return false;
        }
        lastClickTime = jCurrentTimeMillis;
        return true;
    }
}
