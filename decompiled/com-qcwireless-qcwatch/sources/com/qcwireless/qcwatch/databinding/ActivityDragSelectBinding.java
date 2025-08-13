package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityDragSelectBinding implements ViewBinding {
    public final RecyclerView dragRcv;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvBottom;

    private ActivityDragSelectBinding(ConstraintLayout rootView, RecyclerView dragRcv, LayoutTitleBarBinding titleBar, TextView tvBottom) {
        this.rootView = rootView;
        this.dragRcv = dragRcv;
        this.titleBar = titleBar;
        this.tvBottom = tvBottom;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDragSelectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDragSelectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_drag_select, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDragSelectBinding bind(View rootView) {
        int i = R.id.drag_rcv;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.drag_rcv);
        if (recyclerView != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bottom);
                if (textView != null) {
                    return new ActivityDragSelectBinding((ConstraintLayout) rootView, recyclerView, layoutTitleBarBindingBind, textView);
                }
                i = R.id.tv_bottom;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
