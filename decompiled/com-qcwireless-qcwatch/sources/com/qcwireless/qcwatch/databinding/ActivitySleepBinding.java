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
public final class ActivitySleepBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final FrameLayout sleepContainer;
    public final QSwitchButtonView sleepQsvView;
    public final LayoutTitleBarBinding titleBar;

    private ActivitySleepBinding(ConstraintLayout rootView, FrameLayout sleepContainer, QSwitchButtonView sleepQsvView, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.sleepContainer = sleepContainer;
        this.sleepQsvView = sleepQsvView;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySleepBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySleepBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sleep, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySleepBinding bind(View rootView) {
        int i = R.id.sleepContainer;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.sleepContainer);
        if (frameLayout != null) {
            i = R.id.sleep_qsv_view;
            QSwitchButtonView qSwitchButtonView = (QSwitchButtonView) ViewBindings.findChildViewById(rootView, R.id.sleep_qsv_view);
            if (qSwitchButtonView != null) {
                i = R.id.title_bar;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                if (viewFindChildViewById != null) {
                    return new ActivitySleepBinding((ConstraintLayout) rootView, frameLayout, qSwitchButtonView, LayoutTitleBarBinding.bind(viewFindChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
