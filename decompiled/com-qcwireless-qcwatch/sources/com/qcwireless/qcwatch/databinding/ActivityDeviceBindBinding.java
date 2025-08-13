package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityDeviceBindBinding implements ViewBinding {
    public final RecyclerView deviceRcv;
    public final ImageView ivWave;
    public final ImageView ivWave1;
    public final ImageView ivWave2;
    private final ConstraintLayout rootView;
    public final FrameLayout startScan;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvSearchStatus;

    private ActivityDeviceBindBinding(ConstraintLayout rootView, RecyclerView deviceRcv, ImageView ivWave, ImageView ivWave1, ImageView ivWave2, FrameLayout startScan, LayoutTitleBarBinding titleBar, TextView tvSearchStatus) {
        this.rootView = rootView;
        this.deviceRcv = deviceRcv;
        this.ivWave = ivWave;
        this.ivWave1 = ivWave1;
        this.ivWave2 = ivWave2;
        this.startScan = startScan;
        this.titleBar = titleBar;
        this.tvSearchStatus = tvSearchStatus;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceBindBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeviceBindBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_device_bind, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeviceBindBinding bind(View rootView) {
        int i = R.id.device_rcv;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.device_rcv);
        if (recyclerView != null) {
            i = R.id.iv_wave;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_wave);
            if (imageView != null) {
                i = R.id.iv_wave_1;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_wave_1);
                if (imageView2 != null) {
                    i = R.id.iv_wave_2;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_wave_2);
                    if (imageView3 != null) {
                        i = R.id.start_scan;
                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.start_scan);
                        if (frameLayout != null) {
                            i = R.id.titleBar;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                            if (viewFindChildViewById != null) {
                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                i = R.id.tv_search_status;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_search_status);
                                if (textView != null) {
                                    return new ActivityDeviceBindBinding((ConstraintLayout) rootView, recyclerView, imageView, imageView2, imageView3, frameLayout, layoutTitleBarBindingBind, textView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
