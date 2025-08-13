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
public final class ActivityPlayFileBinding implements ViewBinding {
    public final ImageView imageStart;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvFileName;
    public final TextView tvFileSync;
    public final TextView tvFileTime;

    private ActivityPlayFileBinding(ConstraintLayout rootView, ImageView imageStart, LayoutTitleBarBinding titleBar, TextView tvFileName, TextView tvFileSync, TextView tvFileTime) {
        this.rootView = rootView;
        this.imageStart = imageStart;
        this.titleBar = titleBar;
        this.tvFileName = tvFileName;
        this.tvFileSync = tvFileSync;
        this.tvFileTime = tvFileTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPlayFileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPlayFileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_play_file, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPlayFileBinding bind(View rootView) {
        int i = R.id.image_start;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_start);
        if (imageView != null) {
            i = R.id.titleBar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                i = R.id.tv_file_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_file_name);
                if (textView != null) {
                    i = R.id.tv_file_sync;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_file_sync);
                    if (textView2 != null) {
                        i = R.id.tv_file_time;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_file_time);
                        if (textView3 != null) {
                            return new ActivityPlayFileBinding((ConstraintLayout) rootView, imageView, layoutTitleBarBindingBind, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
