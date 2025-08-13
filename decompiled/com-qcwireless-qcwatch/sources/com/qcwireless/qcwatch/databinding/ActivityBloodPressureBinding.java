package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;

/* loaded from: classes3.dex */
public final class ActivityBloodPressureBinding implements ViewBinding {
    public final QBloodPressureChartView bloodPressureChatView;
    public final TextView bpValue;
    public final QSettingItem bpValueDetail;
    public final LinearLayout detailLayout;
    public final Guideline guide1;
    public final LinearLayout layout1;
    public final QDateSwitchView qcDateChange;
    public final QSettingItemTitleSubTitleSystem qcSettingBloodPressure;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView topBg1;
    public final TextView tvHM;
    public final TextView tvInfo1;
    public final TextView tvInfo2;
    public final TextView tvText1;
    public final TextView tvText2;

    private ActivityBloodPressureBinding(ConstraintLayout rootView, QBloodPressureChartView bloodPressureChatView, TextView bpValue, QSettingItem bpValueDetail, LinearLayout detailLayout, Guideline guide1, LinearLayout layout1, QDateSwitchView qcDateChange, QSettingItemTitleSubTitleSystem qcSettingBloodPressure, LayoutTitleBarBinding titleBar, TextView topBg1, TextView tvHM, TextView tvInfo1, TextView tvInfo2, TextView tvText1, TextView tvText2) {
        this.rootView = rootView;
        this.bloodPressureChatView = bloodPressureChatView;
        this.bpValue = bpValue;
        this.bpValueDetail = bpValueDetail;
        this.detailLayout = detailLayout;
        this.guide1 = guide1;
        this.layout1 = layout1;
        this.qcDateChange = qcDateChange;
        this.qcSettingBloodPressure = qcSettingBloodPressure;
        this.titleBar = titleBar;
        this.topBg1 = topBg1;
        this.tvHM = tvHM;
        this.tvInfo1 = tvInfo1;
        this.tvInfo2 = tvInfo2;
        this.tvText1 = tvText1;
        this.tvText2 = tvText2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBloodPressureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBloodPressureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_blood_pressure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBloodPressureBinding bind(View rootView) {
        int i = R.id.blood_pressure_chat_view;
        QBloodPressureChartView qBloodPressureChartView = (QBloodPressureChartView) ViewBindings.findChildViewById(rootView, R.id.blood_pressure_chat_view);
        if (qBloodPressureChartView != null) {
            i = R.id.bp_value;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.bp_value);
            if (textView != null) {
                i = R.id.bp_value_detail;
                QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.bp_value_detail);
                if (qSettingItem != null) {
                    i = R.id.detail_layout;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.detail_layout);
                    if (linearLayout != null) {
                        i = R.id.guide_1;
                        Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guide_1);
                        if (guideline != null) {
                            i = R.id.layout_1;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_1);
                            if (linearLayout2 != null) {
                                i = R.id.qc_date_change;
                                QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                                if (qDateSwitchView != null) {
                                    i = R.id.qc_setting_blood_pressure;
                                    QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = (QSettingItemTitleSubTitleSystem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_blood_pressure);
                                    if (qSettingItemTitleSubTitleSystem != null) {
                                        i = R.id.title_bar;
                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                        if (viewFindChildViewById != null) {
                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                            i = R.id.top_bg_1;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.top_bg_1);
                                            if (textView2 != null) {
                                                i = R.id.tv_h_m;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_h_m);
                                                if (textView3 != null) {
                                                    i = R.id.tv_info_1;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_info_2;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_2);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_text_1;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_1);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_text_2;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_2);
                                                                if (textView7 != null) {
                                                                    return new ActivityBloodPressureBinding((ConstraintLayout) rootView, qBloodPressureChartView, textView, qSettingItem, linearLayout, guideline, linearLayout2, qDateSwitchView, qSettingItemTitleSubTitleSystem, layoutTitleBarBindingBind, textView2, textView3, textView4, textView5, textView6, textView7);
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
