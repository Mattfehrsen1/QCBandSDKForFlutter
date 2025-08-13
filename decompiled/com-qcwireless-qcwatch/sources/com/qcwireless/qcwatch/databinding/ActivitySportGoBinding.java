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
public final class ActivitySportGoBinding implements ViewBinding {
    public final RecyclerView rcvSportType;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivitySportGoBinding(ConstraintLayout rootView, RecyclerView rcvSportType, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.rcvSportType = rcvSportType;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportGoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportGoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sport_go, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportGoBinding bind(View rootView) {
        int i = R.id.rcv_sport_type;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_sport_type);
        if (recyclerView != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                return new ActivitySportGoBinding((ConstraintLayout) rootView, recyclerView, LayoutTitleBarBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
