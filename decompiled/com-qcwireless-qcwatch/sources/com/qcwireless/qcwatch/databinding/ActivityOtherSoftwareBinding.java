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
public final class ActivityOtherSoftwareBinding implements ViewBinding {
    public final RecyclerView otherPushRcv;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityOtherSoftwareBinding(ConstraintLayout rootView, RecyclerView otherPushRcv, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.otherPushRcv = otherPushRcv;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityOtherSoftwareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityOtherSoftwareBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_other_software, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityOtherSoftwareBinding bind(View rootView) {
        int i = R.id.other_push_rcv;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.other_push_rcv);
        if (recyclerView != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                return new ActivityOtherSoftwareBinding((ConstraintLayout) rootView, recyclerView, LayoutTitleBarBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
