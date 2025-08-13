package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ViewSleepAnalysisBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tv1;
    public final Group tvDetailGroup;
    public final TextView tvSleepHour;
    public final TextView tvSleepMin;
    public final TextView tvSleepStatus;
    public final TextView tvSleepTotalTime;
    public final TextView tvSleepUnitH;
    public final TextView tvSleepUnitMin;

    private ViewSleepAnalysisBinding(ConstraintLayout rootView, TextView tv1, Group tvDetailGroup, TextView tvSleepHour, TextView tvSleepMin, TextView tvSleepStatus, TextView tvSleepTotalTime, TextView tvSleepUnitH, TextView tvSleepUnitMin) {
        this.rootView = rootView;
        this.tv1 = tv1;
        this.tvDetailGroup = tvDetailGroup;
        this.tvSleepHour = tvSleepHour;
        this.tvSleepMin = tvSleepMin;
        this.tvSleepStatus = tvSleepStatus;
        this.tvSleepTotalTime = tvSleepTotalTime;
        this.tvSleepUnitH = tvSleepUnitH;
        this.tvSleepUnitMin = tvSleepUnitMin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewSleepAnalysisBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewSleepAnalysisBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.view_sleep_analysis, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewSleepAnalysisBinding bind(View rootView) {
        int i = R.id.tv_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
        if (textView != null) {
            i = R.id.tv_detail_group;
            Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.tv_detail_group);
            if (group != null) {
                i = R.id.tv_sleep_hour;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_hour);
                if (textView2 != null) {
                    i = R.id.tv_sleep_min;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_min);
                    if (textView3 != null) {
                        i = R.id.tv_sleep_status;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_status);
                        if (textView4 != null) {
                            i = R.id.tv_sleep_total_time;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_total_time);
                            if (textView5 != null) {
                                i = R.id.tv_sleep_unit_h;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_unit_h);
                                if (textView6 != null) {
                                    i = R.id.tv_sleep_unit_min;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_unit_min);
                                    if (textView7 != null) {
                                        return new ViewSleepAnalysisBinding((ConstraintLayout) rootView, textView, group, textView2, textView3, textView4, textView5, textView6, textView7);
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
