package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;

/* loaded from: classes3.dex */
public final class ActivityCameraBinding implements ViewBinding {
    public final View bottomLayout;
    public final CameraView cameraView;
    public final FocusView focusView;
    public final ImageView imagePre;
    public final ImageView imageSwitch;
    public final ImageView imageTakePicture;
    private final ConstraintLayout rootView;

    private ActivityCameraBinding(ConstraintLayout rootView, View bottomLayout, CameraView cameraView, FocusView focusView, ImageView imagePre, ImageView imageSwitch, ImageView imageTakePicture) {
        this.rootView = rootView;
        this.bottomLayout = bottomLayout;
        this.cameraView = cameraView;
        this.focusView = focusView;
        this.imagePre = imagePre;
        this.imageSwitch = imageSwitch;
        this.imageTakePicture = imageTakePicture;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCameraBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCameraBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_camera, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCameraBinding bind(View rootView) {
        int i = R.id.bottom_layout;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.bottom_layout);
        if (viewFindChildViewById != null) {
            i = R.id.camera_view;
            CameraView cameraView = (CameraView) ViewBindings.findChildViewById(rootView, R.id.camera_view);
            if (cameraView != null) {
                i = R.id.focusView;
                FocusView focusView = (FocusView) ViewBindings.findChildViewById(rootView, R.id.focusView);
                if (focusView != null) {
                    i = R.id.image_pre;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_pre);
                    if (imageView != null) {
                        i = R.id.image_switch;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_switch);
                        if (imageView2 != null) {
                            i = R.id.image_take_picture;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_take_picture);
                            if (imageView3 != null) {
                                return new ActivityCameraBinding((ConstraintLayout) rootView, viewFindChildViewById, cameraView, focusView, imageView, imageView2, imageView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
