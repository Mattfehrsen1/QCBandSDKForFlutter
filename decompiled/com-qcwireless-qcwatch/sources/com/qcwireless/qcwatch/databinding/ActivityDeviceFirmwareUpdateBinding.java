package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QProgressCircle;

/* loaded from: classes3.dex */
public final class ActivityDeviceFirmwareUpdateBinding implements ViewBinding {
    public final QProgressCircle progressValue;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvError;
    public final TextView tvProgressUnit;
    public final TextView tvProgressValue;
    public final TextView tvWarming;

    private ActivityDeviceFirmwareUpdateBinding(ConstraintLayout rootView, QProgressCircle progressValue, LayoutTitleBarBinding titleBar, TextView tvError, TextView tvProgressUnit, TextView tvProgressValue, TextView tvWarming) {
        this.rootView = rootView;
        this.progressValue = progressValue;
        this.titleBar = titleBar;
        this.tvError = tvError;
        this.tvProgressUnit = tvProgressUnit;
        this.tvProgressValue = tvProgressValue;
        this.tvWarming = tvWarming;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceFirmwareUpdateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeviceFirmwareUpdateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_device_firmware_update, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeviceFirmwareUpdateBinding bind(View rootView) {
        int i = R.id.progress_value;
        QProgressCircle qProgressCircle = (QProgressCircle) ViewBindings.findChildViewById(rootView, R.id.progress_value);
        if (qProgressCircle != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                i = R.id.tv_error;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_error);
                if (textView != null) {
                    i = R.id.tv_progress_unit;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_progress_unit);
                    if (textView2 != null) {
                        i = R.id.tv_progress_value;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_progress_value);
                        if (textView3 != null) {
                            i = R.id.tv_warming;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_warming);
                            if (textView4 != null) {
                                return new ActivityDeviceFirmwareUpdateBinding((ConstraintLayout) rootView, qProgressCircle, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
