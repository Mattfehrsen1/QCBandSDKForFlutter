package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QProgressCircle;

/* loaded from: classes3.dex */
public final class ActivityDeviceFirmwareUpdateChinaBinding implements ViewBinding {
    public final Button btnGoTo;
    public final QProgressCircle progressValue;
    private final NestedScrollView rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvError;
    public final TextView tvProgressUnit;
    public final TextView tvProgressValue;
    public final TextView tvUpdateDesc;
    public final TextView tvUpdateDescTitle;
    public final TextView tvWarming;

    private ActivityDeviceFirmwareUpdateChinaBinding(NestedScrollView rootView, Button btnGoTo, QProgressCircle progressValue, LayoutTitleBarBinding titleBar, TextView tvError, TextView tvProgressUnit, TextView tvProgressValue, TextView tvUpdateDesc, TextView tvUpdateDescTitle, TextView tvWarming) {
        this.rootView = rootView;
        this.btnGoTo = btnGoTo;
        this.progressValue = progressValue;
        this.titleBar = titleBar;
        this.tvError = tvError;
        this.tvProgressUnit = tvProgressUnit;
        this.tvProgressValue = tvProgressValue;
        this.tvUpdateDesc = tvUpdateDesc;
        this.tvUpdateDescTitle = tvUpdateDescTitle;
        this.tvWarming = tvWarming;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceFirmwareUpdateChinaBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeviceFirmwareUpdateChinaBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_device_firmware_update_china, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeviceFirmwareUpdateChinaBinding bind(View rootView) {
        int i = R.id.btn_go_to;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_go_to);
        if (button != null) {
            i = R.id.progress_value;
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
                                i = R.id.tv_update_desc;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_update_desc);
                                if (textView4 != null) {
                                    i = R.id.tv_update_desc_title;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_update_desc_title);
                                    if (textView5 != null) {
                                        i = R.id.tv_warming;
                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_warming);
                                        if (textView6 != null) {
                                            return new ActivityDeviceFirmwareUpdateChinaBinding((NestedScrollView) rootView, button, qProgressCircle, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6);
                                        }
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
