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
public final class RecycleViewBloodSugarDetailBinding implements ViewBinding {
    public final TextView bloodSugarTime;
    public final TextView bloodSugarValue;
    public final ImageView imageSugar;
    private final ConstraintLayout rootView;

    private RecycleViewBloodSugarDetailBinding(ConstraintLayout rootView, TextView bloodSugarTime, TextView bloodSugarValue, ImageView imageSugar) {
        this.rootView = rootView;
        this.bloodSugarTime = bloodSugarTime;
        this.bloodSugarValue = bloodSugarValue;
        this.imageSugar = imageSugar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleViewBloodSugarDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleViewBloodSugarDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycle_view_blood_sugar_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleViewBloodSugarDetailBinding bind(View rootView) {
        int i = R.id.blood_sugar_time;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.blood_sugar_time);
        if (textView != null) {
            i = R.id.blood_sugar_value;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.blood_sugar_value);
            if (textView2 != null) {
                i = R.id.image_sugar;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_sugar);
                if (imageView != null) {
                    return new RecycleViewBloodSugarDetailBinding((ConstraintLayout) rootView, textView, textView2, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
