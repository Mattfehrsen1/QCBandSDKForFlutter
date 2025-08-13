package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CropImageActivityBinding implements ViewBinding {
    public final CropImageView cropImageView;
    private final CropImageView rootView;

    private CropImageActivityBinding(CropImageView rootView, CropImageView cropImageView) {
        this.rootView = rootView;
        this.cropImageView = cropImageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CropImageView getRoot() {
        return this.rootView;
    }

    public static CropImageActivityBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CropImageActivityBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.crop_image_activity, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CropImageActivityBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        CropImageView cropImageView = (CropImageView) rootView;
        return new CropImageActivityBinding(cropImageView, cropImageView);
    }
}
