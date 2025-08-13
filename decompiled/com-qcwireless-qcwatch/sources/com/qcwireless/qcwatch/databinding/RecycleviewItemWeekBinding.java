package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemWeekBinding implements ViewBinding {
    public final ImageView imageWeek;
    private final ConstraintLayout rootView;
    public final TextView tvWeekName;

    private RecycleviewItemWeekBinding(ConstraintLayout rootView, ImageView imageWeek, TextView tvWeekName) {
        this.rootView = rootView;
        this.imageWeek = imageWeek;
        this.tvWeekName = tvWeekName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemWeekBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemWeekBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_week, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemWeekBinding bind(View rootView) {
        int i = R.id.image_week;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_week);
        if (imageView != null) {
            i = R.id.tv_week_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_week_name);
            if (textView != null) {
                return new RecycleviewItemWeekBinding((ConstraintLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
