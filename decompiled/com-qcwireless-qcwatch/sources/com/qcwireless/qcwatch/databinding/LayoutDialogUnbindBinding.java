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
public final class LayoutDialogUnbindBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvContent;

    private LayoutDialogUnbindBinding(ConstraintLayout rootView, TextView tvCancel, TextView tvConfirm, TextView tvContent) {
        this.rootView = rootView;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
        this.tvContent = tvContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogUnbindBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogUnbindBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_unbind, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogUnbindBinding bind(View rootView) {
        int i = R.id.tv_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
        if (textView != null) {
            i = R.id.tv_confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_confirm);
            if (textView2 != null) {
                i = R.id.tv_content;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
                if (textView3 != null) {
                    return new LayoutDialogUnbindBinding((ConstraintLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
