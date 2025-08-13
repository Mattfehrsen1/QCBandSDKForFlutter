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
import com.qcwireless.qcwatch.ui.base.view.QDateWeekSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSleepAnalysisView;
import com.qcwireless.qcwatch.ui.base.view.QSleepWeekBarView;

/* loaded from: classes3.dex */
public final class FragmentWeekSleepBinding implements ViewBinding {
    public final ConstraintLayout cslInfoView;
    public final QDateWeekSwitchView qcDateChange;
    public final QSleepAnalysisView qcSleep1;
    public final QSleepAnalysisView qcSleep2;
    public final QSleepAnalysisView qcSleep3;
    public final QSleepAnalysisView qcSleep4;
    private final NestedScrollView rootView;
    public final TextView topBg1;
    public final TextView tvInfo1;
    public final TextView tvSleepAwake;
    public final TextView tvSleepDeep;
    public final TextView tvSleepH;
    public final TextView tvSleepLight;
    public final TextView tvSleepMin;
    public final TextView tvSleepRange;
    public final TextView tvSleepRapid;
    public final TextView tvSleepType;
    public final TextView tvSleepUnit;
    public final QSleepWeekBarView weekSleepBarView;

    private FragmentWeekSleepBinding(NestedScrollView rootView, ConstraintLayout cslInfoView, QDateWeekSwitchView qcDateChange, QSleepAnalysisView qcSleep1, QSleepAnalysisView qcSleep2, QSleepAnalysisView qcSleep3, QSleepAnalysisView qcSleep4, TextView topBg1, TextView tvInfo1, TextView tvSleepAwake, TextView tvSleepDeep, TextView tvSleepH, TextView tvSleepLight, TextView tvSleepMin, TextView tvSleepRange, TextView tvSleepRapid, TextView tvSleepType, TextView tvSleepUnit, QSleepWeekBarView weekSleepBarView) {
        this.rootView = rootView;
        this.cslInfoView = cslInfoView;
        this.qcDateChange = qcDateChange;
        this.qcSleep1 = qcSleep1;
        this.qcSleep2 = qcSleep2;
        this.qcSleep3 = qcSleep3;
        this.qcSleep4 = qcSleep4;
        this.topBg1 = topBg1;
        this.tvInfo1 = tvInfo1;
        this.tvSleepAwake = tvSleepAwake;
        this.tvSleepDeep = tvSleepDeep;
        this.tvSleepH = tvSleepH;
        this.tvSleepLight = tvSleepLight;
        this.tvSleepMin = tvSleepMin;
        this.tvSleepRange = tvSleepRange;
        this.tvSleepRapid = tvSleepRapid;
        this.tvSleepType = tvSleepType;
        this.tvSleepUnit = tvSleepUnit;
        this.weekSleepBarView = weekSleepBarView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentWeekSleepBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentWeekSleepBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_week_sleep, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWeekSleepBinding bind(View rootView) {
        int i = R.id.csl_info_view;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_info_view);
        if (constraintLayout != null) {
            i = R.id.qc_date_change;
            QDateWeekSwitchView qDateWeekSwitchView = (QDateWeekSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
            if (qDateWeekSwitchView != null) {
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
                                i = R.id.top_bg_1;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.top_bg_1);
                                if (textView != null) {
                                    i = R.id.tv_info_1;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                    if (textView2 != null) {
                                        i = R.id.tv_sleep_awake;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_awake);
                                        if (textView3 != null) {
                                            i = R.id.tv_sleep_deep;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_deep);
                                            if (textView4 != null) {
                                                i = R.id.tv_sleep_h;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_h);
                                                if (textView5 != null) {
                                                    i = R.id.tv_sleep_light;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_light);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_sleep_min;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_min);
                                                        if (textView7 != null) {
                                                            i = R.id.tv_sleep_range;
                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_range);
                                                            if (textView8 != null) {
                                                                i = R.id.tv_sleep_rapid;
                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_rapid);
                                                                if (textView9 != null) {
                                                                    i = R.id.tv_sleep_type;
                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_type);
                                                                    if (textView10 != null) {
                                                                        i = R.id.tv_sleep_unit;
                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_unit);
                                                                        if (textView11 != null) {
                                                                            i = R.id.week_sleep_bar_view;
                                                                            QSleepWeekBarView qSleepWeekBarView = (QSleepWeekBarView) ViewBindings.findChildViewById(rootView, R.id.week_sleep_bar_view);
                                                                            if (qSleepWeekBarView != null) {
                                                                                return new FragmentWeekSleepBinding((NestedScrollView) rootView, constraintLayout, qDateWeekSwitchView, qSleepAnalysisView, qSleepAnalysisView2, qSleepAnalysisView3, qSleepAnalysisView4, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, qSleepWeekBarView);
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
