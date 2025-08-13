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
public final class LayoutDialogRestart1Binding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;

    private LayoutDialogRestart1Binding(ConstraintLayout rootView, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle) {
        this.rootView = rootView;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogRestart1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogRestart1Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_restart_1, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogRestart1Binding bind(View rootView) {
        int i = R.id.tv_dialog_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_cancel);
        if (textView != null) {
            i = R.id.tv_dialog_confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_confirm);
            if (textView2 != null) {
                i = R.id.tv_dialog_title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                if (textView3 != null) {
                    return new LayoutDialogRestart1Binding((ConstraintLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
