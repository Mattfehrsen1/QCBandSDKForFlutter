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
public final class RecycleviewItemThemeMarketBinding implements ViewBinding {
    public final ImageView imageBg;
    public final ImageView imageWatchFace;
    public final View line1;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;

    private RecycleviewItemThemeMarketBinding(ConstraintLayout rootView, ImageView imageBg, ImageView imageWatchFace, View line1, TextView tvDesc) {
        this.rootView = rootView;
        this.imageBg = imageBg;
        this.imageWatchFace = imageWatchFace;
        this.line1 = line1;
        this.tvDesc = tvDesc;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemThemeMarketBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemThemeMarketBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_theme_market, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemThemeMarketBinding bind(View rootView) {
        int i = R.id.image_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_bg);
        if (imageView != null) {
            i = R.id.image_watch_face;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_face);
            if (imageView2 != null) {
                i = R.id.line_1;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                if (viewFindChildViewById != null) {
                    i = R.id.tv_desc;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_desc);
                    if (textView != null) {
                        return new RecycleviewItemThemeMarketBinding((ConstraintLayout) rootView, imageView, imageView2, viewFindChildViewById, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
