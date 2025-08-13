package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QCirclePieView;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QPressureMonthHistoryBarChart;
import com.qcwireless.qcwatch.ui.base.view.QStepComponentView;

/* loaded from: classes3.dex */
public final class FragmentMonthPressureBinding implements ViewBinding {
    public final ConstraintLayout cslHeartPie;
    public final View cstLine1;
    public final View cstLine2;
    public final ConstraintLayout ctlData;
    public final QCirclePieView heartCircleView;
    public final QStepComponentView pressureAvg;
    public final QStepComponentView pressureTotal;
    public final QDateMonthSwitchView qcDateChange;
    public final QPressureMonthHistoryBarChart qcPressureChart;
    private final ConstraintLayout rootView;
    public final TextView topBg1;
    public final TextView tv1;
    public final TextView tv11;
    public final TextView tv2;
    public final TextView tv22;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv42;
    public final TextView tv5;
    public final TextView tv51;
    public final TextView tvAvg;
    public final TextView tvAvgValue;
    public final TextView tvDayPressure;
    public final TextView tvDayPressureUnit;
    public final TextView tvMax;
    public final TextView tvMaxValue;
    public final TextView tvMin;
    public final TextView tvMinValue;
    public final TextView tvPressureRange;
    public final TextView tvValue1;
    public final TextView tvValue2;
    public final TextView tvValue3;
    public final TextView tvValue4;
    public final TextView tvValue5;

    private FragmentMonthPressureBinding(ConstraintLayout rootView, ConstraintLayout cslHeartPie, View cstLine1, View cstLine2, ConstraintLayout ctlData, QCirclePieView heartCircleView, QStepComponentView pressureAvg, QStepComponentView pressureTotal, QDateMonthSwitchView qcDateChange, QPressureMonthHistoryBarChart qcPressureChart, TextView topBg1, TextView tv1, TextView tv11, TextView tv2, TextView tv22, TextView tv3, TextView tv4, TextView tv42, TextView tv5, TextView tv51, TextView tvAvg, TextView tvAvgValue, TextView tvDayPressure, TextView tvDayPressureUnit, TextView tvMax, TextView tvMaxValue, TextView tvMin, TextView tvMinValue, TextView tvPressureRange, TextView tvValue1, TextView tvValue2, TextView tvValue3, TextView tvValue4, TextView tvValue5) {
        this.rootView = rootView;
        this.cslHeartPie = cslHeartPie;
        this.cstLine1 = cstLine1;
        this.cstLine2 = cstLine2;
        this.ctlData = ctlData;
        this.heartCircleView = heartCircleView;
        this.pressureAvg = pressureAvg;
        this.pressureTotal = pressureTotal;
        this.qcDateChange = qcDateChange;
        this.qcPressureChart = qcPressureChart;
        this.topBg1 = topBg1;
        this.tv1 = tv1;
        this.tv11 = tv11;
        this.tv2 = tv2;
        this.tv22 = tv22;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv42 = tv42;
        this.tv5 = tv5;
        this.tv51 = tv51;
        this.tvAvg = tvAvg;
        this.tvAvgValue = tvAvgValue;
        this.tvDayPressure = tvDayPressure;
        this.tvDayPressureUnit = tvDayPressureUnit;
        this.tvMax = tvMax;
        this.tvMaxValue = tvMaxValue;
        this.tvMin = tvMin;
        this.tvMinValue = tvMinValue;
        this.tvPressureRange = tvPressureRange;
        this.tvValue1 = tvValue1;
        this.tvValue2 = tvValue2;
        this.tvValue3 = tvValue3;
        this.tvValue4 = tvValue4;
        this.tvValue5 = tvValue5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMonthPressureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentMonthPressureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_month_pressure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMonthPressureBinding bind(View rootView) {
        int i = R.id.csl_heart_pie;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_heart_pie);
        if (constraintLayout != null) {
            i = R.id.cst_line_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.cst_line_1);
            if (viewFindChildViewById != null) {
                i = R.id.cst_line_2;
                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.cst_line_2);
                if (viewFindChildViewById2 != null) {
                    i = R.id.ctl_data;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_data);
                    if (constraintLayout2 != null) {
                        i = R.id.heart_circle_view;
                        QCirclePieView qCirclePieView = (QCirclePieView) ViewBindings.findChildViewById(rootView, R.id.heart_circle_view);
                        if (qCirclePieView != null) {
                            i = R.id.pressure_avg;
                            QStepComponentView qStepComponentView = (QStepComponentView) ViewBindings.findChildViewById(rootView, R.id.pressure_avg);
                            if (qStepComponentView != null) {
                                i = R.id.pressure_total;
                                QStepComponentView qStepComponentView2 = (QStepComponentView) ViewBindings.findChildViewById(rootView, R.id.pressure_total);
                                if (qStepComponentView2 != null) {
                                    i = R.id.qc_date_change;
                                    QDateMonthSwitchView qDateMonthSwitchView = (QDateMonthSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                                    if (qDateMonthSwitchView != null) {
                                        i = R.id.qc_pressure_chart;
                                        QPressureMonthHistoryBarChart qPressureMonthHistoryBarChart = (QPressureMonthHistoryBarChart) ViewBindings.findChildViewById(rootView, R.id.qc_pressure_chart);
                                        if (qPressureMonthHistoryBarChart != null) {
                                            i = R.id.top_bg_1;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.top_bg_1);
                                            if (textView != null) {
                                                i = R.id.tv_1;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
                                                if (textView2 != null) {
                                                    i = R.id.tv_1_1;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1_1);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_2;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_2_2;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2_2);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_3;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_3);
                                                                if (textView6 != null) {
                                                                    i = R.id.tv_4;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_4);
                                                                    if (textView7 != null) {
                                                                        i = R.id.tv_4_2;
                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_4_2);
                                                                        if (textView8 != null) {
                                                                            i = R.id.tv_5;
                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_5);
                                                                            if (textView9 != null) {
                                                                                i = R.id.tv_5_1;
                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_5_1);
                                                                                if (textView10 != null) {
                                                                                    i = R.id.tv_avg;
                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_avg);
                                                                                    if (textView11 != null) {
                                                                                        i = R.id.tv_avg_value;
                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_avg_value);
                                                                                        if (textView12 != null) {
                                                                                            i = R.id.tv_day_pressure;
                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_day_pressure);
                                                                                            if (textView13 != null) {
                                                                                                i = R.id.tv_day_pressure_unit;
                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_day_pressure_unit);
                                                                                                if (textView14 != null) {
                                                                                                    i = R.id.tv_max;
                                                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_max);
                                                                                                    if (textView15 != null) {
                                                                                                        i = R.id.tv_max_value;
                                                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_max_value);
                                                                                                        if (textView16 != null) {
                                                                                                            i = R.id.tv_min;
                                                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_min);
                                                                                                            if (textView17 != null) {
                                                                                                                i = R.id.tv_min_value;
                                                                                                                TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_min_value);
                                                                                                                if (textView18 != null) {
                                                                                                                    i = R.id.tv_pressure_range;
                                                                                                                    TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_pressure_range);
                                                                                                                    if (textView19 != null) {
                                                                                                                        i = R.id.tv_value_1;
                                                                                                                        TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_1);
                                                                                                                        if (textView20 != null) {
                                                                                                                            i = R.id.tv_value_2;
                                                                                                                            TextView textView21 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_2);
                                                                                                                            if (textView21 != null) {
                                                                                                                                i = R.id.tv_value_3;
                                                                                                                                TextView textView22 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_3);
                                                                                                                                if (textView22 != null) {
                                                                                                                                    i = R.id.tv_value_4;
                                                                                                                                    TextView textView23 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_4);
                                                                                                                                    if (textView23 != null) {
                                                                                                                                        i = R.id.tv_value_5;
                                                                                                                                        TextView textView24 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_5);
                                                                                                                                        if (textView24 != null) {
                                                                                                                                            return new FragmentMonthPressureBinding((ConstraintLayout) rootView, constraintLayout, viewFindChildViewById, viewFindChildViewById2, constraintLayout2, qCirclePieView, qStepComponentView, qStepComponentView2, qDateMonthSwitchView, qPressureMonthHistoryBarChart, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
