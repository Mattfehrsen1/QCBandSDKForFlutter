package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.contrarywind.view.WheelView;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutDialogTimeSelectorBinding implements ViewBinding {
    public final TextView centerText;
    private final ConstraintLayout rootView;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;
    public final WheelView wheelViewHour;
    public final WheelView wheelViewMin;

    private LayoutDialogTimeSelectorBinding(ConstraintLayout rootView, TextView centerText, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle, WheelView wheelViewHour, WheelView wheelViewMin) {
        this.rootView = rootView;
        this.centerText = centerText;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
        this.wheelViewHour = wheelViewHour;
        this.wheelViewMin = wheelViewMin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogTimeSelectorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogTimeSelectorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_time_selector, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogTimeSelectorBinding bind(View rootView) {
        int i = R.id.center_text;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.center_text);
        if (textView != null) {
            i = R.id.tv_dialog_cancel;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_cancel);
            if (textView2 != null) {
                i = R.id.tv_dialog_confirm;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_confirm);
                if (textView3 != null) {
                    i = R.id.tv_dialog_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                    if (textView4 != null) {
                        i = R.id.wheel_view_hour;
                        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_hour);
                        if (wheelView != null) {
                            i = R.id.wheel_view_min;
                            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_min);
                            if (wheelView2 != null) {
                                return new LayoutDialogTimeSelectorBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, wheelView, wheelView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
