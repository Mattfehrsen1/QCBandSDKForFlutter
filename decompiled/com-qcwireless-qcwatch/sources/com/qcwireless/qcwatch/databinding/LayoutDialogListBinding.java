package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutDialogListBinding implements ViewBinding {
    public final TextView dialogCancel;
    public final RecyclerView dialogRcv;
    public final TextView dialogTitle;
    private final ConstraintLayout rootView;

    private LayoutDialogListBinding(ConstraintLayout rootView, TextView dialogCancel, RecyclerView dialogRcv, TextView dialogTitle) {
        this.rootView = rootView;
        this.dialogCancel = dialogCancel;
        this.dialogRcv = dialogRcv;
        this.dialogTitle = dialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogListBinding bind(View rootView) {
        int i = R.id.dialog_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.dialog_cancel);
        if (textView != null) {
            i = R.id.dialog_rcv;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.dialog_rcv);
            if (recyclerView != null) {
                i = R.id.dialog_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.dialog_title);
                if (textView2 != null) {
                    return new LayoutDialogListBinding((ConstraintLayout) rootView, textView, recyclerView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
