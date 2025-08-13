package com.realsil.customer.core.f;

import android.util.Log;
import com.realsil.customer.core.logger.Logger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/f/a.class */
public final class a implements Logger {
    @Override // com.realsil.customer.core.logger.Logger
    public final void log(int i, String str, String str2) {
        switch (i) {
            case 1:
                Log.v(str, str2);
                break;
            case 2:
                Log.d(str, str2);
                break;
            case 3:
                Log.i(str, str2);
                break;
            case 4:
                Log.w(str, str2);
                break;
            case 5:
                Log.e(str, str2);
                break;
            case 6:
                Log.wtf(str, str2);
                break;
        }
    }
}
