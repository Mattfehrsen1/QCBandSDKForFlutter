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
public final class RecycleViewHrvManualDetailAppBinding implements ViewBinding {
    public final ImageView imageSpo2;
    public final TextView pressureTime;
    public final TextView pressureValue;
    public final TextView pressureValueStatus;
    private final ConstraintLayout rootView;

    private RecycleViewHrvManualDetailAppBinding(ConstraintLayout rootView, ImageView imageSpo2, TextView pressureTime, TextView pressureValue, TextView pressureValueStatus) {
        this.rootView = rootView;
        this.imageSpo2 = imageSpo2;
        this.pressureTime = pressureTime;
        this.pressureValue = pressureValue;
        this.pressureValueStatus = pressureValueStatus;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewHrvManualDetailAppBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewHrvManualDetailAppBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycle_view_hrv_manual_detail_app, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewHrvManualDetailAppBinding bind(View rootView) {
        int i = R.id.image_spo2;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_spo2);
        if (imageView != null) {
            i = R.id.pressure_time;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.pressure_time);
            if (textView != null) {
                i = R.id.pressure_value;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pressure_value);
                if (textView2 != null) {
                    i = R.id.pressure_value_status;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.pressure_value_status);
                    if (textView3 != null) {
                        return new RecycleViewHrvManualDetailAppBinding((ConstraintLayout) rootView, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
