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
public final class LayoutDialogYmdViewBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;
    public final WheelView wheelViewDay;
    public final WheelView wheelViewMonth;
    public final WheelView wheelViewYear;

    private LayoutDialogYmdViewBinding(ConstraintLayout rootView, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle, WheelView wheelViewDay, WheelView wheelViewMonth, WheelView wheelViewYear) {
        this.rootView = rootView;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
        this.wheelViewDay = wheelViewDay;
        this.wheelViewMonth = wheelViewMonth;
        this.wheelViewYear = wheelViewYear;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogYmdViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogYmdViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_ymd_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogYmdViewBinding bind(View rootView) {
        int i = R.id.tv_dialog_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_cancel);
        if (textView != null) {
            i = R.id.tv_dialog_confirm;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_confirm);
            if (textView2 != null) {
                i = R.id.tv_dialog_title;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                if (textView3 != null) {
                    i = R.id.wheel_view_day;
                    WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_day);
                    if (wheelView != null) {
                        i = R.id.wheel_view_month;
                        WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_month);
                        if (wheelView2 != null) {
                            i = R.id.wheel_view_year;
                            WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_year);
                            if (wheelView3 != null) {
                                return new LayoutDialogYmdViewBinding((ConstraintLayout) rootView, textView, textView2, textView3, wheelView, wheelView2, wheelView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
