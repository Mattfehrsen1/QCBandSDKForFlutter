package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutDialogLogOffBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;

    private LayoutDialogLogOffBinding(ConstraintLayout rootView, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle) {
        this.rootView = rootView;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogLogOffBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogLogOffBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_log_off, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogLogOffBinding bind(View rootView) {
        int i = R.id.tv_dialog_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_cancel);
        if (textView != null) {
            i = R.id.tv_dialog_confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_confirm);
            if (textView2 != null) {
                i = R.id.tv_dialog_title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                if (textView3 != null) {
                    return new LayoutDialogLogOffBinding((ConstraintLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
