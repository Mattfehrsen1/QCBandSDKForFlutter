package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemAlbumBinding implements ViewBinding {
    public final ImageView imageResult;
    public final ImageView imageSelect;
    public final ProgressBar progressStyle;
    private final ConstraintLayout rootView;
    public final TextView tvProgressValue;
    public final TextView tvStatus;

    private RecycleviewItemAlbumBinding(ConstraintLayout rootView, ImageView imageResult, ImageView imageSelect, ProgressBar progressStyle, TextView tvProgressValue, TextView tvStatus) {
        this.rootView = rootView;
        this.imageResult = imageResult;
        this.imageSelect = imageSelect;
        this.progressStyle = progressStyle;
        this.tvProgressValue = tvProgressValue;
        this.tvStatus = tvStatus;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemAlbumBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemAlbumBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_album, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemAlbumBinding bind(View rootView) {
        int i = R.id.image_result;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_result);
        if (imageView != null) {
            i = R.id.image_select;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_select);
            if (imageView2 != null) {
                i = R.id.progress_style;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, R.id.progress_style);
                if (progressBar != null) {
                    i = R.id.tv_progress_value;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_progress_value);
                    if (textView != null) {
                        i = R.id.tv_status;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_status);
                        if (textView2 != null) {
                            return new RecycleviewItemAlbumBinding((ConstraintLayout) rootView, imageView, imageView2, progressBar, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
