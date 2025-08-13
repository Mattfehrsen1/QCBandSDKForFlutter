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
public final class ActivityImageRoomInBinding implements ViewBinding {
    public final ImageView imageRoomIn;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityImageRoomInBinding(ConstraintLayout rootView, ImageView imageRoomIn, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.imageRoomIn = imageRoomIn;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityImageRoomInBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityImageRoomInBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_image_room_in, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityImageRoomInBinding bind(View rootView) {
        int i = R.id.image_room_in;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_room_in);
        if (imageView != null) {
            i = R.id.titleBar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
            if (viewFindChildViewById != null) {
                return new ActivityImageRoomInBinding((ConstraintLayout) rootView, imageView, LayoutTitleBarBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
