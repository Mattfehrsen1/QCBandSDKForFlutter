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
public final class RecycleviewItemGuideBinding implements ViewBinding {
    public final ImageView itemImage;
    public final TextView itemTitle;
    private final ConstraintLayout rootView;

    private RecycleviewItemGuideBinding(ConstraintLayout rootView, ImageView itemImage, TextView itemTitle) {
        this.rootView = rootView;
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemGuideBinding bind(View rootView) {
        int i = R.id.item_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.item_image);
        if (imageView != null) {
            i = R.id.item_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.item_title);
            if (textView != null) {
                return new RecycleviewItemGuideBinding((ConstraintLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
