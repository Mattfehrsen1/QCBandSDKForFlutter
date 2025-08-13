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
public final class ActivityBloodPressureGuideBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvInfo1;
    public final TextView tvInfo2;
    public final ImageView watchDisplay;

    private ActivityBloodPressureGuideBinding(ConstraintLayout rootView, LayoutTitleBarBinding titleBar, TextView tvInfo1, TextView tvInfo2, ImageView watchDisplay) {
        this.rootView = rootView;
        this.titleBar = titleBar;
        this.tvInfo1 = tvInfo1;
        this.tvInfo2 = tvInfo2;
        this.watchDisplay = watchDisplay;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBloodPressureGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBloodPressureGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_blood_pressure_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBloodPressureGuideBinding bind(View rootView) {
        int i = R.id.title_bar;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
        if (viewFindChildViewById != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            i = R.id.tv_info_1;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
            if (textView != null) {
                i = R.id.tv_info_2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_2);
                if (textView2 != null) {
                    i = R.id.watch_display;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.watch_display);
                    if (imageView != null) {
                        return new ActivityBloodPressureGuideBinding((ConstraintLayout) rootView, layoutTitleBarBindingBind, textView, textView2, imageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
