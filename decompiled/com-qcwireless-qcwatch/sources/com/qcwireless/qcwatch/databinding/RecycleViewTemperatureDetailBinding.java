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
public final class RecycleViewTemperatureDetailBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ImageView temperatureIcon;
    public final TextView temperatureTime;
    public final TextView temperatureValue;
    public final TextView temperatureValueUnit;

    private RecycleViewTemperatureDetailBinding(ConstraintLayout rootView, ImageView temperatureIcon, TextView temperatureTime, TextView temperatureValue, TextView temperatureValueUnit) {
        this.rootView = rootView;
        this.temperatureIcon = temperatureIcon;
        this.temperatureTime = temperatureTime;
        this.temperatureValue = temperatureValue;
        this.temperatureValueUnit = temperatureValueUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewTemperatureDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewTemperatureDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycle_view_temperature_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewTemperatureDetailBinding bind(View rootView) {
        int i = R.id.temperature_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.temperature_icon);
        if (imageView != null) {
            i = R.id.temperature_time;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.temperature_time);
            if (textView != null) {
                i = R.id.temperature_value;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.temperature_value);
                if (textView2 != null) {
                    i = R.id.temperature_value_unit;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.temperature_value_unit);
                    if (textView3 != null) {
                        return new RecycleViewTemperatureDetailBinding((ConstraintLayout) rootView, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
