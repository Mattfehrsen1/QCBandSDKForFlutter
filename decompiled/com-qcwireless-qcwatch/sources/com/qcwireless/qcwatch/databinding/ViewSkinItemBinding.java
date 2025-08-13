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
public final class ViewSkinItemBinding implements ViewBinding {
    public final ImageView imageSkinCheck;
    private final ConstraintLayout rootView;
    public final TextView tvSkinBg;
    public final TextView tvSkinName;

    private ViewSkinItemBinding(ConstraintLayout rootView, ImageView imageSkinCheck, TextView tvSkinBg, TextView tvSkinName) {
        this.rootView = rootView;
        this.imageSkinCheck = imageSkinCheck;
        this.tvSkinBg = tvSkinBg;
        this.tvSkinName = tvSkinName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewSkinItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewSkinItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.view_skin_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewSkinItemBinding bind(View rootView) {
        int i = R.id.image_skin_check;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_skin_check);
        if (imageView != null) {
            i = R.id.tv_skin_bg;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_skin_bg);
            if (textView != null) {
                i = R.id.tv_skin_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_skin_name);
                if (textView2 != null) {
                    return new ViewSkinItemBinding((ConstraintLayout) rootView, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
