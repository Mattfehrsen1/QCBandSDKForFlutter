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
public final class RecycleViewBloodOxygenDetailBinding implements ViewBinding {
    public final TextView bloodOxygenTime;
    public final TextView bloodOxygenValue;
    public final ImageView imageSpo2;
    private final ConstraintLayout rootView;

    private RecycleViewBloodOxygenDetailBinding(ConstraintLayout rootView, TextView bloodOxygenTime, TextView bloodOxygenValue, ImageView imageSpo2) {
        this.rootView = rootView;
        this.bloodOxygenTime = bloodOxygenTime;
        this.bloodOxygenValue = bloodOxygenValue;
        this.imageSpo2 = imageSpo2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewBloodOxygenDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewBloodOxygenDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycle_view_blood_oxygen_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewBloodOxygenDetailBinding bind(View rootView) {
        int i = R.id.blood_oxygen_time;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.blood_oxygen_time);
        if (textView != null) {
            i = R.id.blood_oxygen_value;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.blood_oxygen_value);
            if (textView2 != null) {
                i = R.id.image_spo2;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_spo2);
                if (imageView != null) {
                    return new RecycleViewBloodOxygenDetailBinding((ConstraintLayout) rootView, textView, textView2, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
