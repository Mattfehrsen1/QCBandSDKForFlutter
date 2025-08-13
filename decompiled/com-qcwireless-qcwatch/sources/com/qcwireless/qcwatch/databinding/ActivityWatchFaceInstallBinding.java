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
import com.qcwireless.qcwatch.ui.base.view.ProgressButton;

/* loaded from: classes3.dex */
public final class ActivityWatchFaceInstallBinding implements ViewBinding {
    public final ProgressButton btnDialSave;
    public final ImageView imageWatchFace;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvDownload;
    public final TextView tvPrice;
    public final TextView tvWarming;

    private ActivityWatchFaceInstallBinding(ConstraintLayout rootView, ProgressButton btnDialSave, ImageView imageWatchFace, LayoutTitleBarBinding titleBar, TextView tvDownload, TextView tvPrice, TextView tvWarming) {
        this.rootView = rootView;
        this.btnDialSave = btnDialSave;
        this.imageWatchFace = imageWatchFace;
        this.titleBar = titleBar;
        this.tvDownload = tvDownload;
        this.tvPrice = tvPrice;
        this.tvWarming = tvWarming;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWatchFaceInstallBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityWatchFaceInstallBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_watch_face_install, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWatchFaceInstallBinding bind(View rootView) {
        int i = R.id.btn_dial_save;
        ProgressButton progressButton = (ProgressButton) ViewBindings.findChildViewById(rootView, R.id.btn_dial_save);
        if (progressButton != null) {
            i = R.id.image_watch_face;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_face);
            if (imageView != null) {
                i = R.id.title_bar;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                if (viewFindChildViewById != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = R.id.tv_download;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_download);
                    if (textView != null) {
                        i = R.id.tv_price;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_price);
                        if (textView2 != null) {
                            i = R.id.tv_warming;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_warming);
                            if (textView3 != null) {
                                return new ActivityWatchFaceInstallBinding((ConstraintLayout) rootView, progressButton, imageView, layoutTitleBarBindingBind, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
