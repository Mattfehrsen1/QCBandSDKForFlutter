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
public final class LayoutDialogAppUpgradeBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvConfirm;
    public final TextView tvContent;
    public final TextView tvDialogCancel;
    public final TextView tvTitle;

    private LayoutDialogAppUpgradeBinding(ConstraintLayout rootView, TextView tvConfirm, TextView tvContent, TextView tvDialogCancel, TextView tvTitle) {
        this.rootView = rootView;
        this.tvConfirm = tvConfirm;
        this.tvContent = tvContent;
        this.tvDialogCancel = tvDialogCancel;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogAppUpgradeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogAppUpgradeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_app_upgrade, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogAppUpgradeBinding bind(View rootView) {
        int i = R.id.tv_confirm;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_confirm);
        if (textView != null) {
            i = R.id.tv_content;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
            if (textView2 != null) {
                i = R.id.tv_dialog_cancel;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_cancel);
                if (textView3 != null) {
                    i = R.id.tv_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                    if (textView4 != null) {
                        return new LayoutDialogAppUpgradeBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
