package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemWatchFaceDeviceBinding implements ViewBinding {
    public final ImageView imageDelete;
    public final ImageView imageWatchFace;
    private final ConstraintLayout rootView;

    private RecycleviewItemWatchFaceDeviceBinding(ConstraintLayout rootView, ImageView imageDelete, ImageView imageWatchFace) {
        this.rootView = rootView;
        this.imageDelete = imageDelete;
        this.imageWatchFace = imageWatchFace;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemWatchFaceDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemWatchFaceDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_watch_face_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemWatchFaceDeviceBinding bind(View rootView) {
        int i = R.id.image_delete;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_delete);
        if (imageView != null) {
            i = R.id.image_watch_face;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_face);
            if (imageView2 != null) {
                return new RecycleviewItemWatchFaceDeviceBinding((ConstraintLayout) rootView, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
