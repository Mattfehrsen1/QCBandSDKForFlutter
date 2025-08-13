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
public final class FragmentBirthdayBinding implements ViewBinding {
    public final Button btnNext;
    public final ConstraintLayout ctlBirthday;
    private final ConstraintLayout rootView;
    public final TextView tvCenter;
    public final TextView tvTitle;
    public final WheelView wheelViewMonth;
    public final WheelView wheelViewYear;

    private FragmentBirthdayBinding(ConstraintLayout rootView, Button btnNext, ConstraintLayout ctlBirthday, TextView tvCenter, TextView tvTitle, WheelView wheelViewMonth, WheelView wheelViewYear) {
        this.rootView = rootView;
        this.btnNext = btnNext;
        this.ctlBirthday = ctlBirthday;
        this.tvCenter = tvCenter;
        this.tvTitle = tvTitle;
        this.wheelViewMonth = wheelViewMonth;
        this.wheelViewYear = wheelViewYear;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBirthdayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentBirthdayBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_birthday, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentBirthdayBinding bind(View rootView) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_next);
        if (button != null) {
            i = R.id.ctl_birthday;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_birthday);
            if (constraintLayout != null) {
                i = R.id.tv_center;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_center);
                if (textView != null) {
                    i = R.id.tv_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                    if (textView2 != null) {
                        i = R.id.wheel_view_month;
                        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_month);
                        if (wheelView != null) {
                            i = R.id.wheel_view_year;
                            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_year);
                            if (wheelView2 != null) {
                                return new FragmentBirthdayBinding((ConstraintLayout) rootView, button, constraintLayout, textView, textView2, wheelView, wheelView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
