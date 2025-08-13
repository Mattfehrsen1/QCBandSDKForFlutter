package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QCirclePieView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineChartView;

/* loaded from: classes3.dex */
public final class ActivityTemperatureBinding implements ViewBinding {
    public final ConstraintLayout cslInfoView;
    public final ConstraintLayout cslPieView;
    public final LinearLayout detailLayout;
    public final QDateSwitchView qcDateChange;
    private final ConstraintLayout rootView;
    public final QTemperatureLineChartView temperatureChatView;
    public final QCirclePieView temperatureCircleView;
    public final QSettingItem temperatureValueDetail;
    public final LayoutTitleBarBinding titleBar;
    public final TextView topBg1;
    public final TextView tv1;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tvHM;
    public final TextView tvInfo1;
    public final TextView tvInfo2;
    public final TextView tvTemperatureUnit;
    public final TextView tvTemperatureValue;
    public final TextView tvValue1;
    public final TextView tvValue2;
    public final TextView tvValue3;

    private ActivityTemperatureBinding(ConstraintLayout rootView, ConstraintLayout cslInfoView, ConstraintLayout cslPieView, LinearLayout detailLayout, QDateSwitchView qcDateChange, QTemperatureLineChartView temperatureChatView, QCirclePieView temperatureCircleView, QSettingItem temperatureValueDetail, LayoutTitleBarBinding titleBar, TextView topBg1, TextView tv1, TextView tv4, TextView tv5, TextView tvHM, TextView tvInfo1, TextView tvInfo2, TextView tvTemperatureUnit, TextView tvTemperatureValue, TextView tvValue1, TextView tvValue2, TextView tvValue3) {
        this.rootView = rootView;
        this.cslInfoView = cslInfoView;
        this.cslPieView = cslPieView;
        this.detailLayout = detailLayout;
        this.qcDateChange = qcDateChange;
        this.temperatureChatView = temperatureChatView;
        this.temperatureCircleView = temperatureCircleView;
        this.temperatureValueDetail = temperatureValueDetail;
        this.titleBar = titleBar;
        this.topBg1 = topBg1;
        this.tv1 = tv1;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tvHM = tvHM;
        this.tvInfo1 = tvInfo1;
        this.tvInfo2 = tvInfo2;
        this.tvTemperatureUnit = tvTemperatureUnit;
        this.tvTemperatureValue = tvTemperatureValue;
        this.tvValue1 = tvValue1;
        this.tvValue2 = tvValue2;
        this.tvValue3 = tvValue3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTemperatureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTemperatureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_temperature, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTemperatureBinding bind(View rootView) {
        int i = R.id.csl_info_view;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_info_view);
        if (constraintLayout != null) {
            i = R.id.csl_pie_view;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_pie_view);
            if (constraintLayout2 != null) {
                i = R.id.detail_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.detail_layout);
                if (linearLayout != null) {
                    i = R.id.qc_date_change;
                    QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                    if (qDateSwitchView != null) {
                        i = R.id.temperature_chat_view;
                        QTemperatureLineChartView qTemperatureLineChartView = (QTemperatureLineChartView) ViewBindings.findChildViewById(rootView, R.id.temperature_chat_view);
                        if (qTemperatureLineChartView != null) {
                            i = R.id.temperature_circle_view;
                            QCirclePieView qCirclePieView = (QCirclePieView) ViewBindings.findChildViewById(rootView, R.id.temperature_circle_view);
                            if (qCirclePieView != null) {
                                i = R.id.temperature_value_detail;
                                QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.temperature_value_detail);
                                if (qSettingItem != null) {
                                    i = R.id.title_bar;
                                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                    if (viewFindChildViewById != null) {
                                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                        i = R.id.top_bg_1;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.top_bg_1);
                                        if (textView != null) {
                                            i = R.id.tv_1;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
                                            if (textView2 != null) {
                                                i = R.id.tv_4;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_4);
                                                if (textView3 != null) {
                                                    i = R.id.tv_5;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_5);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_h_m;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_h_m);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_info_1;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_info_2;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_2);
                                                                if (textView7 != null) {
                                                                    i = R.id.tv_temperature_unit;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_temperature_unit);
                                                                    if (textView8 != null) {
                                                                        i = R.id.tv_temperature_value;
                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_temperature_value);
                                                                        if (textView9 != null) {
                                                                            i = R.id.tv_value_1;
                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_1);
                                                                            if (textView10 != null) {
                                                                                i = R.id.tv_value_2;
                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_2);
                                                                                if (textView11 != null) {
                                                                                    i = R.id.tv_value_3;
                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_3);
                                                                                    if (textView12 != null) {
                                                                                        return new ActivityTemperatureBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, linearLayout, qDateSwitchView, qTemperatureLineChartView, qCirclePieView, qSettingItem, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
