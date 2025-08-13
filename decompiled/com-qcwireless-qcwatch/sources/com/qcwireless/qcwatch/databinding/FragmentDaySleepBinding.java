package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSleepAnalysisView;
import com.qcwireless.qcwatch.ui.base.view.QSleepBarChart;

/* loaded from: classes3.dex */
public final class FragmentDaySleepBinding implements ViewBinding {
    public final ConstraintLayout clsLunchSleep;
    public final ConstraintLayout cslInfoView;
    public final QSleepBarChart daySleepBarView;
    public final QDateSwitchView qcDateChange;
    public final QSleepAnalysisView qcSleep1;
    public final QSleepAnalysisView qcSleep2;
    public final QSleepAnalysisView qcSleep3;
    public final QSleepAnalysisView qcSleep4;
    public final QSleepAnalysisView qcSleepAwake;
    public final QSleepAnalysisView qcSleepDeep;
    public final QSleepAnalysisView qcSleepLight;
    public final QSleepAnalysisView qcSleepRem;
    private final NestedScrollView rootView;
    public final TextView topBg1;
    public final TextView tvInfo1;
    public final TextView tvLunchRange;
    public final TextView tvLunchTitle;
    public final TextView tvLunchValue;
    public final TextView tvSleepAwake;
    public final TextView tvSleepDeep;
    public final TextView tvSleepLight;
    public final TextView tvSleepMin;
    public final TextView tvSleepRange;
    public final TextView tvSleepRapid;
    public final TextView tvSleepType;
    public final TextView tvSleepUnit;

    private FragmentDaySleepBinding(NestedScrollView rootView, ConstraintLayout clsLunchSleep, ConstraintLayout cslInfoView, QSleepBarChart daySleepBarView, QDateSwitchView qcDateChange, QSleepAnalysisView qcSleep1, QSleepAnalysisView qcSleep2, QSleepAnalysisView qcSleep3, QSleepAnalysisView qcSleep4, QSleepAnalysisView qcSleepAwake, QSleepAnalysisView qcSleepDeep, QSleepAnalysisView qcSleepLight, QSleepAnalysisView qcSleepRem, TextView topBg1, TextView tvInfo1, TextView tvLunchRange, TextView tvLunchTitle, TextView tvLunchValue, TextView tvSleepAwake, TextView tvSleepDeep, TextView tvSleepLight, TextView tvSleepMin, TextView tvSleepRange, TextView tvSleepRapid, TextView tvSleepType, TextView tvSleepUnit) {
        this.rootView = rootView;
        this.clsLunchSleep = clsLunchSleep;
        this.cslInfoView = cslInfoView;
        this.daySleepBarView = daySleepBarView;
        this.qcDateChange = qcDateChange;
        this.qcSleep1 = qcSleep1;
        this.qcSleep2 = qcSleep2;
        this.qcSleep3 = qcSleep3;
        this.qcSleep4 = qcSleep4;
        this.qcSleepAwake = qcSleepAwake;
        this.qcSleepDeep = qcSleepDeep;
        this.qcSleepLight = qcSleepLight;
        this.qcSleepRem = qcSleepRem;
        this.topBg1 = topBg1;
        this.tvInfo1 = tvInfo1;
        this.tvLunchRange = tvLunchRange;
        this.tvLunchTitle = tvLunchTitle;
        this.tvLunchValue = tvLunchValue;
        this.tvSleepAwake = tvSleepAwake;
        this.tvSleepDeep = tvSleepDeep;
        this.tvSleepLight = tvSleepLight;
        this.tvSleepMin = tvSleepMin;
        this.tvSleepRange = tvSleepRange;
        this.tvSleepRapid = tvSleepRapid;
        this.tvSleepType = tvSleepType;
        this.tvSleepUnit = tvSleepUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentDaySleepBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDaySleepBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_day_sleep, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDaySleepBinding bind(View rootView) {
        int i = R.id.cls_lunch_sleep;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cls_lunch_sleep);
        if (constraintLayout != null) {
            i = R.id.csl_info_view;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_info_view);
            if (constraintLayout2 != null) {
                i = R.id.day_sleep_bar_view;
                QSleepBarChart qSleepBarChart = (QSleepBarChart) ViewBindings.findChildViewById(rootView, R.id.day_sleep_bar_view);
                if (qSleepBarChart != null) {
                    i = R.id.qc_date_change;
                    QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                    if (qDateSwitchView != null) {
                        i = R.id.qc_sleep_1;
                        QSleepAnalysisView qSleepAnalysisView = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep_1);
                        if (qSleepAnalysisView != null) {
                            i = R.id.qc_sleep_2;
                            QSleepAnalysisView qSleepAnalysisView2 = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep_2);
                            if (qSleepAnalysisView2 != null) {
                                i = R.id.qc_sleep3;
                                QSleepAnalysisView qSleepAnalysisView3 = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep3);
                                if (qSleepAnalysisView3 != null) {
                                    i = R.id.qc_sleep_4;
                                    QSleepAnalysisView qSleepAnalysisView4 = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep_4);
                                    if (qSleepAnalysisView4 != null) {
                                        i = R.id.qc_sleep_awake;
                                        QSleepAnalysisView qSleepAnalysisView5 = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep_awake);
                                        if (qSleepAnalysisView5 != null) {
                                            i = R.id.qc_sleep_deep;
                                            QSleepAnalysisView qSleepAnalysisView6 = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep_deep);
                                            if (qSleepAnalysisView6 != null) {
                                                i = R.id.qc_sleep_light;
                                                QSleepAnalysisView qSleepAnalysisView7 = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep_light);
                                                if (qSleepAnalysisView7 != null) {
                                                    i = R.id.qc_sleep_rem;
                                                    QSleepAnalysisView qSleepAnalysisView8 = (QSleepAnalysisView) ViewBindings.findChildViewById(rootView, R.id.qc_sleep_rem);
                                                    if (qSleepAnalysisView8 != null) {
                                                        i = R.id.top_bg_1;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.top_bg_1);
                                                        if (textView != null) {
                                                            i = R.id.tv_info_1;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_lunch_range;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_lunch_range);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_lunch_title;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_lunch_title);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_lunch_value;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_lunch_value);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_sleep_awake;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_awake);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_sleep_deep;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_deep);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_sleep_light;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_light);
                                                                                    if (textView8 != null) {
                                                                                        i = R.id.tv_sleep_min;
                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_min);
                                                                                        if (textView9 != null) {
                                                                                            i = R.id.tv_sleep_range;
                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_range);
                                                                                            if (textView10 != null) {
                                                                                                i = R.id.tv_sleep_rapid;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_rapid);
                                                                                                if (textView11 != null) {
                                                                                                    i = R.id.tv_sleep_type;
                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_type);
                                                                                                    if (textView12 != null) {
                                                                                                        i = R.id.tv_sleep_unit;
                                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_unit);
                                                                                                        if (textView13 != null) {
                                                                                                            return new FragmentDaySleepBinding((NestedScrollView) rootView, constraintLayout, constraintLayout2, qSleepBarChart, qDateSwitchView, qSleepAnalysisView, qSleepAnalysisView2, qSleepAnalysisView3, qSleepAnalysisView4, qSleepAnalysisView5, qSleepAnalysisView6, qSleepAnalysisView7, qSleepAnalysisView8, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13);
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
