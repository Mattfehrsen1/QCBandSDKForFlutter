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
public final class RecycleViewBloodPressureDetailBinding implements ViewBinding {
    public final TextView bloodPressureTime;
    public final TextView bloodPressureValue;
    public final TextView bloodPressureValueUnit;
    public final ImageView imageSpo2;
    private final ConstraintLayout rootView;

    private RecycleViewBloodPressureDetailBinding(ConstraintLayout rootView, TextView bloodPressureTime, TextView bloodPressureValue, TextView bloodPressureValueUnit, ImageView imageSpo2) {
        this.rootView = rootView;
        this.bloodPressureTime = bloodPressureTime;
        this.bloodPressureValue = bloodPressureValue;
        this.bloodPressureValueUnit = bloodPressureValueUnit;
        this.imageSpo2 = imageSpo2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewBloodPressureDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewBloodPressureDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycle_view_blood_pressure_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewBloodPressureDetailBinding bind(View rootView) {
        int i = R.id.blood_pressure_time;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.blood_pressure_time);
        if (textView != null) {
            i = R.id.blood_pressure_value;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.blood_pressure_value);
            if (textView2 != null) {
                i = R.id.blood_pressure_value_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.blood_pressure_value_unit);
                if (textView3 != null) {
                    i = R.id.image_spo2;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_spo2);
                    if (imageView != null) {
                        return new RecycleViewBloodPressureDetailBinding((ConstraintLayout) rootView, textView, textView2, textView3, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
