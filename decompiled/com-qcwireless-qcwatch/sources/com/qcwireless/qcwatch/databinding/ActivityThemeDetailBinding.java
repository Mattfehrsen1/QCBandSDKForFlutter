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
public final class ActivityThemeDetailBinding implements ViewBinding {
    public final ProgressButton btnDialSave;
    public final TextView errorText;
    public final ImageView imageBg;
    public final ImageView imageWatchFace;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvDownload;
    public final TextView tvPrice;
    public final TextView tvWarming;

    private ActivityThemeDetailBinding(ConstraintLayout rootView, ProgressButton btnDialSave, TextView errorText, ImageView imageBg, ImageView imageWatchFace, LayoutTitleBarBinding titleBar, TextView tvDownload, TextView tvPrice, TextView tvWarming) {
        this.rootView = rootView;
        this.btnDialSave = btnDialSave;
        this.errorText = errorText;
        this.imageBg = imageBg;
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

    public static ActivityThemeDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityThemeDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_theme_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityThemeDetailBinding bind(View rootView) {
        int i = R.id.btn_dial_save;
        ProgressButton progressButton = (ProgressButton) ViewBindings.findChildViewById(rootView, R.id.btn_dial_save);
        if (progressButton != null) {
            i = R.id.error_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.error_text);
            if (textView != null) {
                i = R.id.image_bg;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_bg);
                if (imageView != null) {
                    i = R.id.image_watch_face;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_watch_face);
                    if (imageView2 != null) {
                        i = R.id.title_bar;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                        if (viewFindChildViewById != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                            i = R.id.tv_download;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_download);
                            if (textView2 != null) {
                                i = R.id.tv_price;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_price);
                                if (textView3 != null) {
                                    i = R.id.tv_warming;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_warming);
                                    if (textView4 != null) {
                                        return new ActivityThemeDetailBinding((ConstraintLayout) rootView, progressButton, textView, imageView, imageView2, layoutTitleBarBindingBind, textView2, textView3, textView4);
                                    }
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
