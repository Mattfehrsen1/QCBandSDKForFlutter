package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView;

/* loaded from: classes3.dex */
public final class ActivityRingPressureBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final View statusBarView;
    public final FrameLayout stepContainer;
    public final QSwitchButtonView stepQsvView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityRingPressureBinding(ConstraintLayout rootView, View statusBarView, FrameLayout stepContainer, QSwitchButtonView stepQsvView, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.statusBarView = statusBarView;
        this.stepContainer = stepContainer;
        this.stepQsvView = stepQsvView;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRingPressureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRingPressureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ring_pressure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRingPressureBinding bind(View rootView) {
        int i = R.id.status_bar_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.status_bar_view);
        if (viewFindChildViewById != null) {
            i = R.id.stepContainer;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.stepContainer);
            if (frameLayout != null) {
                i = R.id.step_qsv_view;
                QSwitchButtonView qSwitchButtonView = (QSwitchButtonView) ViewBindings.findChildViewById(rootView, R.id.step_qsv_view);
                if (qSwitchButtonView != null) {
                    i = R.id.title_bar;
                    View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                    if (viewFindChildViewById2 != null) {
                        return new ActivityRingPressureBinding((ConstraintLayout) rootView, viewFindChildViewById, frameLayout, qSwitchButtonView, LayoutTitleBarBinding.bind(viewFindChildViewById2));
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
