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
public final class RecycleViewHeartRateDetailBinding implements ViewBinding {
    public final TextView heartRateTime;
    public final TextView heartRateValue;
    public final TextView heartRateValueUnit;
    public final ImageView imageHeartIcon;
    private final ConstraintLayout rootView;

    private RecycleViewHeartRateDetailBinding(ConstraintLayout rootView, TextView heartRateTime, TextView heartRateValue, TextView heartRateValueUnit, ImageView imageHeartIcon) {
        this.rootView = rootView;
        this.heartRateTime = heartRateTime;
        this.heartRateValue = heartRateValue;
        this.heartRateValueUnit = heartRateValueUnit;
        this.imageHeartIcon = imageHeartIcon;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewHeartRateDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewHeartRateDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycle_view_heart_rate_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewHeartRateDetailBinding bind(View rootView) {
        int i = R.id.heart_rate_time;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.heart_rate_time);
        if (textView != null) {
            i = R.id.heart_rate_value;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.heart_rate_value);
            if (textView2 != null) {
                i = R.id.heart_rate_value_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.heart_rate_value_unit);
                if (textView3 != null) {
                    i = R.id.image_heart_icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_heart_icon);
                    if (imageView != null) {
                        return new RecycleViewHeartRateDetailBinding((ConstraintLayout) rootView, textView, textView2, textView3, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
