package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityCardBinding implements ViewBinding {
    public final Button btnConfirm;
    public final Button btnConfirmCancel;
    public final View line1;
    public final ImageView qrCodeIcon;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tvQrCode;
    public final TextView userNotUse;

    private ActivityCardBinding(ConstraintLayout rootView, Button btnConfirm, Button btnConfirmCancel, View line1, ImageView qrCodeIcon, LayoutTitleBarBinding titleBar, TextView tv1, TextView tv2, TextView tvQrCode, TextView userNotUse) {
        this.rootView = rootView;
        this.btnConfirm = btnConfirm;
        this.btnConfirmCancel = btnConfirmCancel;
        this.line1 = line1;
        this.qrCodeIcon = qrCodeIcon;
        this.titleBar = titleBar;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tvQrCode = tvQrCode;
        this.userNotUse = userNotUse;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_card, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCardBinding bind(View rootView) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_confirm);
        if (button != null) {
            i = R.id.btn_confirm_cancel;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_confirm_cancel);
            if (button2 != null) {
                i = R.id.line_1;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                if (viewFindChildViewById != null) {
                    i = R.id.qr_code_icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.qr_code_icon);
                    if (imageView != null) {
                        i = R.id.title_bar;
                        View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                        if (viewFindChildViewById2 != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                            i = R.id.tv_1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
                            if (textView != null) {
                                i = R.id.tv_2;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
                                if (textView2 != null) {
                                    i = R.id.tv_qr_code;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_qr_code);
                                    if (textView3 != null) {
                                        i = R.id.user_not_use;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_not_use);
                                        if (textView4 != null) {
                                            return new ActivityCardBinding((ConstraintLayout) rootView, button, button2, viewFindChildViewById, imageView, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
