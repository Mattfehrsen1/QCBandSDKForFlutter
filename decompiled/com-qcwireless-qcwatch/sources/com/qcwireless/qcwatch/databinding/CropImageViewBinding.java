package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropOverlayView;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CropImageViewBinding implements ViewBinding {
    public final CropOverlayView CropOverlayView;
    public final ProgressBar CropProgressBar;
    public final ImageView ImageViewImage;
    private final View rootView;

    private CropImageViewBinding(View rootView, CropOverlayView CropOverlayView, ProgressBar CropProgressBar, ImageView ImageViewImage) {
        this.rootView = rootView;
        this.CropOverlayView = CropOverlayView;
        this.CropProgressBar = CropProgressBar;
        this.ImageViewImage = ImageViewImage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static CropImageViewBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        Objects.requireNonNull(parent, "parent");
        inflater.inflate(R.layout.crop_image_view, parent);
        return bind(parent);
    }

    public static CropImageViewBinding bind(View rootView) {
        int i = R.id.CropOverlayView;
        CropOverlayView cropOverlayView = (CropOverlayView) ViewBindings.findChildViewById(rootView, R.id.CropOverlayView);
        if (cropOverlayView != null) {
            i = R.id.CropProgressBar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.CropProgressBar);
            if (progressBar != null) {
                i = R.id.ImageView_image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.ImageView_image);
                if (imageView != null) {
                    return new CropImageViewBinding(rootView, cropOverlayView, progressBar, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
