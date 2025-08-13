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
import com.qcwireless.qcwatch.ui.base.view.QBloodOxygenLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QCirclePieView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;

/* loaded from: classes3.dex */
public final class ActivityBloodOxygenBinding implements ViewBinding {
    public final QBloodOxygenLineChartView bloodOxygenChatView;
    public final QCirclePieView bloodOxygenCircleView;
    public final QSettingItem boValueDetail;
    public final ConstraintLayout cslInfoView;
    public final ConstraintLayout cslPieView;
    public final LinearLayout detailLayout;
    public final LinearLayout layout1;
    public final QDateSwitchView qcDateChange;
    public final QSettingItemTitleSubTitleSystem qcSettingBloodOxygen;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView topBg1;
    public final TextView tv1;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tvBloodOxygenValue;
    public final TextView tvHM;
    public final TextView tvInfo1;
    public final TextView tvInfo2;
    public final TextView tvValue1;
    public final TextView tvValue2;
    public final TextView tvValue3;

    private ActivityBloodOxygenBinding(ConstraintLayout rootView, QBloodOxygenLineChartView bloodOxygenChatView, QCirclePieView bloodOxygenCircleView, QSettingItem boValueDetail, ConstraintLayout cslInfoView, ConstraintLayout cslPieView, LinearLayout detailLayout, LinearLayout layout1, QDateSwitchView qcDateChange, QSettingItemTitleSubTitleSystem qcSettingBloodOxygen, LayoutTitleBarBinding titleBar, TextView topBg1, TextView tv1, TextView tv4, TextView tv5, TextView tvBloodOxygenValue, TextView tvHM, TextView tvInfo1, TextView tvInfo2, TextView tvValue1, TextView tvValue2, TextView tvValue3) {
        this.rootView = rootView;
        this.bloodOxygenChatView = bloodOxygenChatView;
        this.bloodOxygenCircleView = bloodOxygenCircleView;
        this.boValueDetail = boValueDetail;
        this.cslInfoView = cslInfoView;
        this.cslPieView = cslPieView;
        this.detailLayout = detailLayout;
        this.layout1 = layout1;
        this.qcDateChange = qcDateChange;
        this.qcSettingBloodOxygen = qcSettingBloodOxygen;
        this.titleBar = titleBar;
        this.topBg1 = topBg1;
        this.tv1 = tv1;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tvBloodOxygenValue = tvBloodOxygenValue;
        this.tvHM = tvHM;
        this.tvInfo1 = tvInfo1;
        this.tvInfo2 = tvInfo2;
        this.tvValue1 = tvValue1;
        this.tvValue2 = tvValue2;
        this.tvValue3 = tvValue3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBloodOxygenBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBloodOxygenBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_blood_oxygen, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBloodOxygenBinding bind(View rootView) {
        int i = R.id.blood_oxygen_chat_view;
        QBloodOxygenLineChartView qBloodOxygenLineChartView = (QBloodOxygenLineChartView) ViewBindings.findChildViewById(rootView, R.id.blood_oxygen_chat_view);
        if (qBloodOxygenLineChartView != null) {
            i = R.id.blood_oxygen_circle_view;
            QCirclePieView qCirclePieView = (QCirclePieView) ViewBindings.findChildViewById(rootView, R.id.blood_oxygen_circle_view);
            if (qCirclePieView != null) {
                i = R.id.bo_value_detail;
                QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.bo_value_detail);
                if (qSettingItem != null) {
                    i = R.id.csl_info_view;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_info_view);
                    if (constraintLayout != null) {
                        i = R.id.csl_pie_view;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_pie_view);
                        if (constraintLayout2 != null) {
                            i = R.id.detail_layout;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.detail_layout);
                            if (linearLayout != null) {
                                i = R.id.layout_1;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_1);
                                if (linearLayout2 != null) {
                                    i = R.id.qc_date_change;
                                    QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                                    if (qDateSwitchView != null) {
                                        i = R.id.qc_setting_blood_oxygen;
                                        QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = (QSettingItemTitleSubTitleSystem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_blood_oxygen);
                                        if (qSettingItemTitleSubTitleSystem != null) {
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
                                                                i = R.id.tv_blood_oxygen_value;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_blood_oxygen_value);
                                                                if (textView5 != null) {
                                                                    i = R.id.tv_h_m;
                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_h_m);
                                                                    if (textView6 != null) {
                                                                        i = R.id.tv_info_1;
                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                                                        if (textView7 != null) {
                                                                            i = R.id.tv_info_2;
                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_2);
                                                                            if (textView8 != null) {
                                                                                i = R.id.tv_value_1;
                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_1);
                                                                                if (textView9 != null) {
                                                                                    i = R.id.tv_value_2;
                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_2);
                                                                                    if (textView10 != null) {
                                                                                        i = R.id.tv_value_3;
                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_3);
                                                                                        if (textView11 != null) {
                                                                                            return new ActivityBloodOxygenBinding((ConstraintLayout) rootView, qBloodOxygenLineChartView, qCirclePieView, qSettingItem, constraintLayout, constraintLayout2, linearLayout, linearLayout2, qDateSwitchView, qSettingItemTitleSubTitleSystem, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
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
