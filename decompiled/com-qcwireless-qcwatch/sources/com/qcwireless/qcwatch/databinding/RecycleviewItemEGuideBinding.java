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
public final class RecycleviewItemEGuideBinding implements ViewBinding {
    public final ImageView image1;
    private final ConstraintLayout rootView;

    private RecycleviewItemEGuideBinding(ConstraintLayout rootView, ImageView image1) {
        this.rootView = rootView;
        this.image1 = image1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemEGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemEGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_e_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemEGuideBinding bind(View rootView) {
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_1);
        if (imageView != null) {
            return new RecycleviewItemEGuideBinding((ConstraintLayout) rootView, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.image_1)));
    }
}
