package com.qcwireless.qcwatch.base.dialog;

import android.app.Dialog;
import android.content.Context;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class PrivacyDialog extends Dialog {
    public PrivacyDialog(Context context) {
        super(context, R.style.PrivacyThemeDialog);
        setContentView(R.layout.dialog_privary);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
