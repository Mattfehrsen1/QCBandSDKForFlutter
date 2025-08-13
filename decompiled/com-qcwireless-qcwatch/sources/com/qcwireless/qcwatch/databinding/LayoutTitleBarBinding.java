package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutTitleBarBinding implements ViewBinding {
    public final View divider;
    public final ImageView ivNavigateBefore;
    private final RelativeLayout rootView;
    public final RelativeLayout titleBar;
    public final ImageView tvRightImage;
    public final TextView tvRightText;
    public final TextView tvTitle;

    private LayoutTitleBarBinding(RelativeLayout rootView, View divider, ImageView ivNavigateBefore, RelativeLayout titleBar, ImageView tvRightImage, TextView tvRightText, TextView tvTitle) {
        this.rootView = rootView;
        this.divider = divider;
        this.ivNavigateBefore = ivNavigateBefore;
        this.titleBar = titleBar;
        this.tvRightImage = tvRightImage;
        this.tvRightText = tvRightText;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LayoutTitleBarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutTitleBarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_title_bar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutTitleBarBinding bind(View rootView) {
        int i = R.id.divider;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.divider);
        if (viewFindChildViewById != null) {
            i = R.id.ivNavigateBefore;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.ivNavigateBefore);
            if (imageView != null) {
                RelativeLayout relativeLayout = (RelativeLayout) rootView;
                i = R.id.tvRightImage;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.tvRightImage);
                if (imageView2 != null) {
                    i = R.id.tvRightText;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvRightText);
                    if (textView != null) {
                        i = R.id.tvTitle;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvTitle);
                        if (textView2 != null) {
                            return new LayoutTitleBarBinding(relativeLayout, viewFindChildViewById, imageView, relativeLayout, imageView2, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
