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
public final class RecycleviewExpListGroupBinding implements ViewBinding {
    public final ImageView imageClick;
    private final ConstraintLayout rootView;
    public final TextView tvItemGroupTitle;

    private RecycleviewExpListGroupBinding(ConstraintLayout rootView, ImageView imageClick, TextView tvItemGroupTitle) {
        this.rootView = rootView;
        this.imageClick = imageClick;
        this.tvItemGroupTitle = tvItemGroupTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewExpListGroupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewExpListGroupBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_exp_list_group, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewExpListGroupBinding bind(View rootView) {
        int i = R.id.image_click;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_click);
        if (imageView != null) {
            i = R.id.tv_item_group_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_item_group_title);
            if (textView != null) {
                return new RecycleviewExpListGroupBinding((ConstraintLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
