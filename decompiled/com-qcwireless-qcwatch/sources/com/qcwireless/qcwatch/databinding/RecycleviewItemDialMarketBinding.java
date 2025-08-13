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
public final class RecycleviewItemDialMarketBinding implements ViewBinding {
    public final ImageView imageWatchFace;
    private final ConstraintLayout rootView;
    public final TextView tvPrice;

    private RecycleviewItemDialMarketBinding(ConstraintLayout rootView, ImageView imageWatchFace, TextView tvPrice) {
        this.rootView = rootView;
        this.imageWatchFace = imageWatchFace;
        this.tvPrice = tvPrice;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemDialMarketBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemDialMarketBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_dial_market, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemDialMarketBinding bind(View rootView) {
        int i = R.id.image_watch_face;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_face);
        if (imageView != null) {
            i = R.id.tv_price;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_price);
            if (textView != null) {
                return new RecycleviewItemDialMarketBinding((ConstraintLayout) rootView, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
