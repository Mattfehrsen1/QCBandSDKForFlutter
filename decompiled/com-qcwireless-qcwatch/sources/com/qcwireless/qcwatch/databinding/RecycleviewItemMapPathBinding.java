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
public final class RecycleviewItemMapPathBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvDistance;
    public final TextView tvTime;
    public final TextView tvTitle;

    private RecycleviewItemMapPathBinding(ConstraintLayout rootView, TextView tvDistance, TextView tvTime, TextView tvTitle) {
        this.rootView = rootView;
        this.tvDistance = tvDistance;
        this.tvTime = tvTime;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemMapPathBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemMapPathBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_map_path, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemMapPathBinding bind(View rootView) {
        int i = R.id.tv_distance;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_distance);
        if (textView != null) {
            i = R.id.tv_time;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_time);
            if (textView2 != null) {
                i = R.id.tv_title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                if (textView3 != null) {
                    return new RecycleviewItemMapPathBinding((ConstraintLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
