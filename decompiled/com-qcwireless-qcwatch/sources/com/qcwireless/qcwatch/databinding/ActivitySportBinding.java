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
public final class ActivitySportBinding implements ViewBinding {
    public final QSwitchButtonView qsvView;
    private final ConstraintLayout rootView;
    public final FrameLayout sportContainer;
    public final LayoutTitleBarBinding titleBar;

    private ActivitySportBinding(ConstraintLayout rootView, QSwitchButtonView qsvView, FrameLayout sportContainer, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.qsvView = qsvView;
        this.sportContainer = sportContainer;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sport, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportBinding bind(View rootView) {
        int i = R.id.qsv_view;
        QSwitchButtonView qSwitchButtonView = (QSwitchButtonView) ViewBindings.findChildViewById(rootView, R.id.qsv_view);
        if (qSwitchButtonView != null) {
            i = R.id.sportContainer;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.sportContainer);
            if (frameLayout != null) {
                i = R.id.title_bar;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                if (viewFindChildViewById != null) {
                    return new ActivitySportBinding((ConstraintLayout) rootView, qSwitchButtonView, frameLayout, LayoutTitleBarBinding.bind(viewFindChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
