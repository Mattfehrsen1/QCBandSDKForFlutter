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
public final class RecycleviewItemMapKeyBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvAddress;
    public final TextView tvCity;

    private RecycleviewItemMapKeyBinding(ConstraintLayout rootView, TextView tvAddress, TextView tvCity) {
        this.rootView = rootView;
        this.tvAddress = tvAddress;
        this.tvCity = tvCity;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemMapKeyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemMapKeyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_map_key, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemMapKeyBinding bind(View rootView) {
        int i = R.id.tv_address;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_address);
        if (textView != null) {
            i = R.id.tv_city;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_city);
            if (textView2 != null) {
                return new RecycleviewItemMapKeyBinding((ConstraintLayout) rootView, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
