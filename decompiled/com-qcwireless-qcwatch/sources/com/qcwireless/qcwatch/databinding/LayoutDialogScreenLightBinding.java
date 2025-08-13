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
public final class LayoutDialogScreenLightBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView subTitle1;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final TextView tvDialogTitle;
    public final WheelView wheelViewSecond;

    private LayoutDialogScreenLightBinding(ConstraintLayout rootView, TextView subTitle1, TextView tvDialogCancel, TextView tvDialogConfirm, TextView tvDialogTitle, WheelView wheelViewSecond) {
        this.rootView = rootView;
        this.subTitle1 = subTitle1;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
        this.wheelViewSecond = wheelViewSecond;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogScreenLightBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogScreenLightBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_screen_light, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogScreenLightBinding bind(View rootView) {
        int i = R.id.sub_title_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_title_1);
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
                        i = R.id.wheel_view_second;
                        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_second);
                        if (wheelView != null) {
                            return new LayoutDialogScreenLightBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, wheelView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
