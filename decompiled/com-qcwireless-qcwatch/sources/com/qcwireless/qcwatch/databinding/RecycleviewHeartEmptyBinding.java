package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewHeartEmptyBinding implements ViewBinding {
    public final ImageView imageSportNoData;
    private final ConstraintLayout rootView;

    private RecycleviewHeartEmptyBinding(ConstraintLayout rootView, ImageView imageSportNoData) {
        this.rootView = rootView;
        this.imageSportNoData = imageSportNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewHeartEmptyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewHeartEmptyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_heart_empty, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewHeartEmptyBinding bind(View rootView) {
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_sport_no_data);
        if (imageView != null) {
            return new RecycleviewHeartEmptyBinding((ConstraintLayout) rootView, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.image_sport_no_data)));
    }
}
