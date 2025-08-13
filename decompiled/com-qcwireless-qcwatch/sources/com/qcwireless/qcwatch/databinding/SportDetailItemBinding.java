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
public final class SportDetailItemBinding implements ViewBinding {
    public final ImageView imageItem;
    private final ConstraintLayout rootView;
    public final TextView tvItemTitle;
    public final TextView tvItemValue;
    public final TextView tvItemValueUnit;

    private SportDetailItemBinding(ConstraintLayout rootView, ImageView imageItem, TextView tvItemTitle, TextView tvItemValue, TextView tvItemValueUnit) {
        this.rootView = rootView;
        this.imageItem = imageItem;
        this.tvItemTitle = tvItemTitle;
        this.tvItemValue = tvItemValue;
        this.tvItemValueUnit = tvItemValueUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SportDetailItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SportDetailItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.sport_detail_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SportDetailItemBinding bind(View rootView) {
        int i = R.id.image_item;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_item);
        if (imageView != null) {
            i = R.id.tv_item_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_item_title);
            if (textView != null) {
                i = R.id.tv_item_value;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_item_value);
                if (textView2 != null) {
                    i = R.id.tv_item_value_unit;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_item_value_unit);
                    if (textView3 != null) {
                        return new SportDetailItemBinding((ConstraintLayout) rootView, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
