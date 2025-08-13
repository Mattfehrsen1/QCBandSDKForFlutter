package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QStepBarChart;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;

/* loaded from: classes3.dex */
public final class FragmentDayStepBinding implements ViewBinding {
    public final QDateSwitchView qcDateChange;
    public final QStepBarChart qcStepChart;
    private final ConstraintLayout rootView;
    public final QStepComponentView stepAvg;
    public final QStepComponentView stepCalorie;
    public final QStepComponentView stepDistance;
    public final QStepComponentView stepTotal;
    public final TextView topBg1;
    public final TextView tvDayStep;
    public final TextView tvDayStepUnit;
    public final TextView tvStepRange;

    private FragmentDayStepBinding(ConstraintLayout rootView, QDateSwitchView qcDateChange, QStepBarChart qcStepChart, QStepComponentView stepAvg, QStepComponentView stepCalorie, QStepComponentView stepDistance, QStepComponentView stepTotal, TextView topBg1, TextView tvDayStep, TextView tvDayStepUnit, TextView tvStepRange) {
        this.rootView = rootView;
        this.qcDateChange = qcDateChange;
        this.qcStepChart = qcStepChart;
        this.stepAvg = stepAvg;
        this.stepCalorie = stepCalorie;
        this.stepDistance = stepDistance;
        this.stepTotal = stepTotal;
        this.topBg1 = topBg1;
        this.tvDayStep = tvDayStep;
        this.tvDayStepUnit = tvDayStepUnit;
        this.tvStepRange = tvStepRange;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDayStepBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDayStepBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_day_step, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDayStepBinding bind(View rootView) {
        int i = R.id.qc_date_change;
        QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
        if (qDateSwitchView != null) {
            i = R.id.qc_step_chart;
            QStepBarChart qStepBarChart = (QStepBarChart) ViewBindings.findChildViewById(rootView, R.id.qc_step_chart);
            if (qStepBarChart != null) {
                i = R.id.step_avg;
                QStepComponentView qStepComponentView = (QStepComponentView) ViewBindings.findChildViewById(rootView, R.id.step_avg);
                if (qStepComponentView != null) {
                    i = R.id.step_calorie;
                    QStepComponentView qStepComponentView2 = (QStepComponentView) ViewBindings.findChildViewById(rootView, R.id.step_calorie);
                    if (qStepComponentView2 != null) {
                        i = R.id.step_distance;
                        QStepComponentView qStepComponentView3 = (QStepComponentView) ViewBindings.findChildViewById(rootView, R.id.step_distance);
                        if (qStepComponentView3 != null) {
                            i = R.id.step_total;
                            QStepComponentView qStepComponentView4 = (QStepComponentView) ViewBindings.findChildViewById(rootView, R.id.step_total);
                            if (qStepComponentView4 != null) {
                                i = R.id.top_bg_1;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.top_bg_1);
                                if (textView != null) {
                                    i = R.id.tv_day_step;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_day_step);
                                    if (textView2 != null) {
                                        i = R.id.tv_day_step_unit;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_day_step_unit);
                                        if (textView3 != null) {
                                            i = R.id.tv_step_range;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_step_range);
                                            if (textView4 != null) {
                                                return new FragmentDayStepBinding((ConstraintLayout) rootView, qDateSwitchView, qStepBarChart, qStepComponentView, qStepComponentView2, qStepComponentView3, qStepComponentView4, textView, textView2, textView3, textView4);
                                            }
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
