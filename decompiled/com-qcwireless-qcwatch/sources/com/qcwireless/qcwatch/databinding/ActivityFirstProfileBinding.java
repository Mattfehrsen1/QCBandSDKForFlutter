package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.ucenter.NoScrollViewPager;

/* loaded from: classes3.dex */
public final class ActivityFirstProfileBinding implements ViewBinding {
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final NoScrollViewPager vp2;

    private ActivityFirstProfileBinding(ConstraintLayout rootView, ProgressBar progressBar, LayoutTitleBarBinding titleBar, NoScrollViewPager vp2) {
        this.rootView = rootView;
        this.progressBar = progressBar;
        this.titleBar = titleBar;
        this.vp2 = vp2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFirstProfileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFirstProfileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_first_profile, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFirstProfileBinding bind(View rootView) {
        int i = R.id.progress_bar;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progress_bar);
        if (progressBar != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                NoScrollViewPager noScrollViewPager = (NoScrollViewPager) ViewBindings.findChildViewById(rootView, R.id.vp2);
                if (noScrollViewPager != null) {
                    return new ActivityFirstProfileBinding((ConstraintLayout) rootView, progressBar, layoutTitleBarBindingBind, noScrollViewPager);
                }
                i = R.id.vp2;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
