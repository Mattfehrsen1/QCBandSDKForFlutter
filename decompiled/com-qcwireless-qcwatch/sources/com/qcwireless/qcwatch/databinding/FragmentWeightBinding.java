package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.contrarywind.view.WheelView;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class FragmentWeightBinding implements ViewBinding {
    public final Button btnNext;
    public final ConstraintLayout ctsHeight;
    private final ConstraintLayout rootView;
    public final TextView tvCenter;
    public final TextView tvTitle;
    public final WheelView wheelView;
    public final WheelView wheelViewUnit;

    private FragmentWeightBinding(ConstraintLayout rootView, Button btnNext, ConstraintLayout ctsHeight, TextView tvCenter, TextView tvTitle, WheelView wheelView, WheelView wheelViewUnit) {
        this.rootView = rootView;
        this.btnNext = btnNext;
        this.ctsHeight = ctsHeight;
        this.tvCenter = tvCenter;
        this.tvTitle = tvTitle;
        this.wheelView = wheelView;
        this.wheelViewUnit = wheelViewUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWeightBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentWeightBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_weight, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWeightBinding bind(View rootView) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_next);
        if (button != null) {
            i = R.id.cts_height;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cts_height);
            if (constraintLayout != null) {
                i = R.id.tv_center;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_center);
                if (textView != null) {
                    i = R.id.tv_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                    if (textView2 != null) {
                        i = R.id.wheel_view;
                        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view);
                        if (wheelView != null) {
                            i = R.id.wheel_view_unit;
                            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_unit);
                            if (wheelView2 != null) {
                                return new FragmentWeightBinding((ConstraintLayout) rootView, button, constraintLayout, textView, textView2, wheelView, wheelView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
