package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityDeviceConnectWarmingBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvWarmingDesc;
    public final TextView tvWarmingTitle;

    private ActivityDeviceConnectWarmingBinding(ConstraintLayout rootView, LayoutTitleBarBinding titleBar, TextView tvWarmingDesc, TextView tvWarmingTitle) {
        this.rootView = rootView;
        this.titleBar = titleBar;
        this.tvWarmingDesc = tvWarmingDesc;
        this.tvWarmingTitle = tvWarmingTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDeviceConnectWarmingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDeviceConnectWarmingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_device_connect_warming, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDeviceConnectWarmingBinding bind(View rootView) {
        int i = R.id.titleBar;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
        if (viewFindChildViewById != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_warming_desc);
            if (textView != null) {
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_warming_title);
                if (textView2 != null) {
                    return new ActivityDeviceConnectWarmingBinding((ConstraintLayout) rootView, layoutTitleBarBindingBind, textView, textView2);
                }
                i = R.id.tv_warming_title;
            } else {
                i = R.id.tv_warming_desc;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
