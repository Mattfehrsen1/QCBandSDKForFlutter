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
public final class ActivityDialTypeListBinding implements ViewBinding {
    public final RecyclerView rcvTypeList;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityDialTypeListBinding(ConstraintLayout rootView, RecyclerView rcvTypeList, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.rcvTypeList = rcvTypeList;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDialTypeListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDialTypeListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_dial_type_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDialTypeListBinding bind(View rootView) {
        int i = R.id.rcv_type_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_type_list);
        if (recyclerView != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                return new ActivityDialTypeListBinding((ConstraintLayout) rootView, recyclerView, LayoutTitleBarBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
