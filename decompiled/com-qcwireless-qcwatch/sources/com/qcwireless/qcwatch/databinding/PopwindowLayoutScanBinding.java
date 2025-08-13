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
public final class PopwindowLayoutScanBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvAdd;

    private PopwindowLayoutScanBinding(ConstraintLayout rootView, TextView tvAdd) {
        this.rootView = rootView;
        this.tvAdd = tvAdd;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static PopwindowLayoutScanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PopwindowLayoutScanBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.popwindow_layout_scan, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopwindowLayoutScanBinding bind(View rootView) {
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_add);
        if (textView != null) {
            return new PopwindowLayoutScanBinding((ConstraintLayout) rootView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.tv_add)));
    }
}
