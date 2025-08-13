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
public final class RecycleviewItemWallpaperMarketBinding implements ViewBinding {
    public final ImageView imageBg;
    public final ImageView imageWatchFace;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;
    public final TextView tvPrice;

    private RecycleviewItemWallpaperMarketBinding(ConstraintLayout rootView, ImageView imageBg, ImageView imageWatchFace, TextView tvDesc, TextView tvPrice) {
        this.rootView = rootView;
        this.imageBg = imageBg;
        this.imageWatchFace = imageWatchFace;
        this.tvDesc = tvDesc;
        this.tvPrice = tvPrice;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemWallpaperMarketBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemWallpaperMarketBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_wallpaper_market, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemWallpaperMarketBinding bind(View rootView) {
        int i = R.id.image_bg;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_bg);
        if (imageView != null) {
            i = R.id.image_watch_face;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_face);
            if (imageView2 != null) {
                i = R.id.tv_desc;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_desc);
                if (textView != null) {
                    i = R.id.tv_price;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_price);
                    if (textView2 != null) {
                        return new RecycleviewItemWallpaperMarketBinding((ConstraintLayout) rootView, imageView, imageView2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
