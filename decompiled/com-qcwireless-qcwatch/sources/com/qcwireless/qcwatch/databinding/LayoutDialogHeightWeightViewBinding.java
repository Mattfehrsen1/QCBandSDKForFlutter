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
public final class LayoutDialogHeightWeightViewBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvCenter;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;
    public final WheelView wheelView;
    public final WheelView wheelViewUnit;

    private LayoutDialogHeightWeightViewBinding(ConstraintLayout rootView, TextView tvCenter, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle, WheelView wheelView, WheelView wheelViewUnit) {
        this.rootView = rootView;
        this.tvCenter = tvCenter;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
        this.wheelView = wheelView;
        this.wheelViewUnit = wheelViewUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogHeightWeightViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogHeightWeightViewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_height_weight_view, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogHeightWeightViewBinding bind(View rootView) {
        int i = R.id.tv_center;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_center);
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
                            i = R.id.wheel_view_unit;
                            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_unit);
                            if (wheelView2 != null) {
                                return new LayoutDialogHeightWeightViewBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, wheelView, wheelView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
