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
public final class ViewDateSwitchBinding implements ViewBinding {
    public final ImageView imageBefore;
    public final ImageView imageNext;
    private final ConstraintLayout rootView;
    public final TextView tvDateTitle;

    private ViewDateSwitchBinding(ConstraintLayout rootView, ImageView imageBefore, ImageView imageNext, TextView tvDateTitle) {
        this.rootView = rootView;
        this.imageBefore = imageBefore;
        this.imageNext = imageNext;
        this.tvDateTitle = tvDateTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewDateSwitchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewDateSwitchBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.view_date_switch, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewDateSwitchBinding bind(View rootView) {
        int i = R.id.image_before;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_before);
        if (imageView != null) {
            i = R.id.image_next;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_next);
            if (imageView2 != null) {
                i = R.id.tv_date_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_date_title);
                if (textView != null) {
                    return new ViewDateSwitchBinding((ConstraintLayout) rootView, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
