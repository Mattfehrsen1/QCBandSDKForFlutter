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
public final class ActivityWatchFileDismissBinding implements ViewBinding {
    public final QProgressCircle progressValue;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvError;
    public final TextView tvFileStatus;
    public final TextView tvProgressUnit;
    public final TextView tvProgressValue;
    public final TextView tvWarming;

    private ActivityWatchFileDismissBinding(ConstraintLayout rootView, QProgressCircle progressValue, LayoutTitleBarBinding titleBar, TextView tvError, TextView tvFileStatus, TextView tvProgressUnit, TextView tvProgressValue, TextView tvWarming) {
        this.rootView = rootView;
        this.progressValue = progressValue;
        this.titleBar = titleBar;
        this.tvError = tvError;
        this.tvFileStatus = tvFileStatus;
        this.tvProgressUnit = tvProgressUnit;
        this.tvProgressValue = tvProgressValue;
        this.tvWarming = tvWarming;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWatchFileDismissBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityWatchFileDismissBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_watch_file_dismiss, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWatchFileDismissBinding bind(View rootView) {
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
                    i = R.id.tv_file_status;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_file_status);
                    if (textView2 != null) {
                        i = R.id.tv_progress_unit;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_progress_unit);
                        if (textView3 != null) {
                            i = R.id.tv_progress_value;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_progress_value);
                            if (textView4 != null) {
                                i = R.id.tv_warming;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_warming);
                                if (textView5 != null) {
                                    return new ActivityWatchFileDismissBinding((ConstraintLayout) rootView, qProgressCircle, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5);
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
