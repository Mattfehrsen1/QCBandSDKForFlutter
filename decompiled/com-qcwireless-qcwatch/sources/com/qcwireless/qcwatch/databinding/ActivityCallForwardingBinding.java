package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityCallForwardingBinding implements ViewBinding {
    public final View line1;
    public final QSettingItemWithClickSystem qcForward;
    public final QSettingItemWithClickSystem qcForwardNoOneHeard;
    public final QSettingItemWithClickSystem qcForwardTo;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tv1;
    public final TextView tv2;

    private ActivityCallForwardingBinding(ConstraintLayout rootView, View line1, QSettingItemWithClickSystem qcForward, QSettingItemWithClickSystem qcForwardNoOneHeard, QSettingItemWithClickSystem qcForwardTo, LayoutTitleBarBinding titleBar, TextView tv1, TextView tv2) {
        this.rootView = rootView;
        this.line1 = line1;
        this.qcForward = qcForward;
        this.qcForwardNoOneHeard = qcForwardNoOneHeard;
        this.qcForwardTo = qcForwardTo;
        this.titleBar = titleBar;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCallForwardingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCallForwardingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_call_forwarding, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCallForwardingBinding bind(View rootView) {
        int i = R.id.line_1;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
        if (viewFindChildViewById != null) {
            i = R.id.qc_forward;
            QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_forward);
            if (qSettingItemWithClickSystem != null) {
                i = R.id.qc_forward_no_one_heard;
                QSettingItemWithClickSystem qSettingItemWithClickSystem2 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_forward_no_one_heard);
                if (qSettingItemWithClickSystem2 != null) {
                    i = R.id.qc_forward_to;
                    QSettingItemWithClickSystem qSettingItemWithClickSystem3 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_forward_to);
                    if (qSettingItemWithClickSystem3 != null) {
                        i = R.id.titleBar;
                        View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                        if (viewFindChildViewById2 != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                            i = R.id.tv_1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
                            if (textView != null) {
                                i = R.id.tv_2;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
                                if (textView2 != null) {
                                    return new ActivityCallForwardingBinding((ConstraintLayout) rootView, viewFindChildViewById, qSettingItemWithClickSystem, qSettingItemWithClickSystem2, qSettingItemWithClickSystem3, layoutTitleBarBindingBind, textView, textView2);
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
