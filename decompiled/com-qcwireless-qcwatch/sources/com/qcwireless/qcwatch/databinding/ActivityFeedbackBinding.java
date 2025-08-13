package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityFeedbackBinding implements ViewBinding {
    public final ConstraintLayout feedbackSuggest;
    public final ConstraintLayout networkLayout;
    public final RecyclerView rcvFeatures;
    public final RecyclerView rcvFw;
    public final RecyclerView rcvHw;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityFeedbackBinding(ConstraintLayout rootView, ConstraintLayout feedbackSuggest, ConstraintLayout networkLayout, RecyclerView rcvFeatures, RecyclerView rcvFw, RecyclerView rcvHw, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.feedbackSuggest = feedbackSuggest;
        this.networkLayout = networkLayout;
        this.rcvFeatures = rcvFeatures;
        this.rcvFw = rcvFw;
        this.rcvHw = rcvHw;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_feedback, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityFeedbackBinding bind(View rootView) {
        int i = R.id.feedback_suggest;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.feedback_suggest);
        if (constraintLayout != null) {
            i = R.id.network_layout;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.network_layout);
            if (constraintLayout2 != null) {
                i = R.id.rcv_features;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_features);
                if (recyclerView != null) {
                    i = R.id.rcv_fw;
                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_fw);
                    if (recyclerView2 != null) {
                        i = R.id.rcv_hw;
                        RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_hw);
                        if (recyclerView3 != null) {
                            i = R.id.title_bar;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                            if (viewFindChildViewById != null) {
                                return new ActivityFeedbackBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, recyclerView, recyclerView2, recyclerView3, LayoutTitleBarBinding.bind(viewFindChildViewById));
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
