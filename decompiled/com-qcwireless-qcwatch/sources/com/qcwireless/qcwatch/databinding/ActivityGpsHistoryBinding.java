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
public final class ActivityGpsHistoryBinding implements ViewBinding {
    public final FrameLayout gpsContainer;
    public final QSwitchButtonView qsvView;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityGpsHistoryBinding(ConstraintLayout rootView, FrameLayout gpsContainer, QSwitchButtonView qsvView, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.gpsContainer = gpsContainer;
        this.qsvView = qsvView;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGpsHistoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGpsHistoryBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_gps_history, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGpsHistoryBinding bind(View rootView) {
        int i = R.id.gpsContainer;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.gpsContainer);
        if (frameLayout != null) {
            i = R.id.qsv_view;
            QSwitchButtonView qSwitchButtonView = (QSwitchButtonView) ViewBindings.findChildViewById(rootView, R.id.qsv_view);
            if (qSwitchButtonView != null) {
                i = R.id.title_bar;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                if (viewFindChildViewById != null) {
                    return new ActivityGpsHistoryBinding((ConstraintLayout) rootView, frameLayout, qSwitchButtonView, LayoutTitleBarBinding.bind(viewFindChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
