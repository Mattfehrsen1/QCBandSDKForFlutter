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
public final class ActivityHeartHrvBinding implements ViewBinding {
    public final ConstraintLayout cslInfoView;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvInfo1;

    private ActivityHeartHrvBinding(ConstraintLayout rootView, ConstraintLayout cslInfoView, LayoutTitleBarBinding titleBar, TextView tvInfo1) {
        this.rootView = rootView;
        this.cslInfoView = cslInfoView;
        this.titleBar = titleBar;
        this.tvInfo1 = tvInfo1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityHeartHrvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHeartHrvBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_heart_hrv, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityHeartHrvBinding bind(View rootView) {
        int i = R.id.csl_info_view;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_info_view);
        if (constraintLayout != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                if (textView != null) {
                    return new ActivityHeartHrvBinding((ConstraintLayout) rootView, constraintLayout, layoutTitleBarBindingBind, textView);
                }
                i = R.id.tv_info_1;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
