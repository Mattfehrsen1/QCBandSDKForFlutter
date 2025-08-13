package com.qcwireless.qcwatch.ui.base.util;

import android.text.TextUtils;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* compiled from: SameDeviceUtil.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"isSameDevice", "", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SameDeviceUtilKt {
    public static final boolean isSameDevice() {
        String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
        String lastDeviceAddress = UserConfig.INSTANCE.getInstance().getLastDeviceAddress();
        if (TextUtils.isEmpty(deviceAddress)) {
            return false;
        }
        return StringsKt.equals$default(deviceAddress, lastDeviceAddress, false, 2, null);
    }
}
