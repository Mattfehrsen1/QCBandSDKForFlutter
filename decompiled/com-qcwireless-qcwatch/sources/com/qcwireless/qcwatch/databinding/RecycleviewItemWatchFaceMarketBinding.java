package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.LoadSendView;

/* loaded from: classes3.dex */
public final class RecycleviewItemWatchFaceMarketBinding implements ViewBinding {
    public final ImageView imageWatchFace;
    public final LoadSendView lsvLoading;
    private final ConstraintLayout rootView;

    private RecycleviewItemWatchFaceMarketBinding(ConstraintLayout rootView, ImageView imageWatchFace, LoadSendView lsvLoading) {
        this.rootView = rootView;
        this.imageWatchFace = imageWatchFace;
        this.lsvLoading = lsvLoading;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemWatchFaceMarketBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemWatchFaceMarketBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_watch_face_market, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemWatchFaceMarketBinding bind(View rootView) {
        int i = R.id.image_watch_face;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_face);
        if (imageView != null) {
            i = R.id.lsv_loading;
            LoadSendView loadSendView = (LoadSendView) ViewBindings.findChildViewById(rootView, R.id.lsv_loading);
            if (loadSendView != null) {
                return new RecycleviewItemWatchFaceMarketBinding((ConstraintLayout) rootView, imageView, loadSendView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
