package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutDialogPermissionInformationBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvContent;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;

    private LayoutDialogPermissionInformationBinding(LinearLayout rootView, TextView tvContent, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle) {
        this.rootView = rootView;
        this.tvContent = tvContent;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogPermissionInformationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogPermissionInformationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_permission_information, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogPermissionInformationBinding bind(View rootView) {
        int i = R.id.tv_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
        if (textView != null) {
            i = R.id.tv_dialog_cancel;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_cancel);
            if (textView2 != null) {
                i = R.id.tv_dialog_confirm;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_confirm);
                if (textView3 != null) {
                    i = R.id.tv_dialog_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                    if (textView4 != null) {
                        return new LayoutDialogPermissionInformationBinding((LinearLayout) rootView, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
