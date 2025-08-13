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
public final class LayoutDialogWheelViewBinding implements ViewBinding {
    public final TextView dialogUnit;
    private final ConstraintLayout rootView;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;
    public final WheelView wheelView;

    private LayoutDialogWheelViewBinding(ConstraintLayout rootView, TextView dialogUnit, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle, WheelView wheelView) {
        this.rootView = rootView;
        this.dialogUnit = dialogUnit;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
        this.wheelView = wheelView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogWheelViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogWheelViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_wheel_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogWheelViewBinding bind(View rootView) {
        int i = R.id.dialog_unit;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.dialog_unit);
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
                        i = R.id.wheel_view;
                        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view);
                        if (wheelView != null) {
                            return new LayoutDialogWheelViewBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, wheelView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
