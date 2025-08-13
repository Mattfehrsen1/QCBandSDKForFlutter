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
public final class ActivityAlbumToDeviceBinding implements ViewBinding {
    public final ImageView imageTest;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvProgress;
    public final TextView tvSelect;

    private ActivityAlbumToDeviceBinding(ConstraintLayout rootView, ImageView imageTest, LayoutTitleBarBinding titleBar, TextView tvProgress, TextView tvSelect) {
        this.rootView = rootView;
        this.imageTest = imageTest;
        this.titleBar = titleBar;
        this.tvProgress = tvProgress;
        this.tvSelect = tvSelect;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAlbumToDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAlbumToDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_album_to_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAlbumToDeviceBinding bind(View rootView) {
        int i = R.id.image_test;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_test);
        if (imageView != null) {
            i = R.id.titleBar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                i = R.id.tv_progress;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_progress);
                if (textView != null) {
                    i = R.id.tv_select;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_select);
                    if (textView2 != null) {
                        return new ActivityAlbumToDeviceBinding((ConstraintLayout) rootView, imageView, layoutTitleBarBindingBind, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
