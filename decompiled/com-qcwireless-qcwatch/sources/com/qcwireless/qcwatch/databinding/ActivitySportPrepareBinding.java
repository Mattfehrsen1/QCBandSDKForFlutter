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
public final class ActivitySportPrepareBinding implements ViewBinding {
    public final View coverView;
    private final ConstraintLayout rootView;
    public final TextView tvNumber1;
    public final TextView tvNumber2;

    private ActivitySportPrepareBinding(ConstraintLayout rootView, View coverView, TextView tvNumber1, TextView tvNumber2) {
        this.rootView = rootView;
        this.coverView = coverView;
        this.tvNumber1 = tvNumber1;
        this.tvNumber2 = tvNumber2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportPrepareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportPrepareBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sport_prepare, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportPrepareBinding bind(View rootView) {
        int i = R.id.cover_view;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.cover_view);
        if (viewFindChildViewById != null) {
            i = R.id.tv_number_1;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_number_1);
            if (textView != null) {
                i = R.id.tv_number_2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_number_2);
                if (textView2 != null) {
                    return new ActivitySportPrepareBinding((ConstraintLayout) rootView, viewFindChildViewById, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
