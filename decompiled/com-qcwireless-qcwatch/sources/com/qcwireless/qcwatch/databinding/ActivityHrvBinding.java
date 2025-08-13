package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QCirclePieView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QHrvLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;

/* loaded from: classes3.dex */
public final class ActivityHrvBinding implements ViewBinding {
    public final Button btnMeasure;
    public final ConstraintLayout cslHeartPie;
    public final View cstLine1;
    public final View cstLine2;
    public final ConstraintLayout ctlData;
    public final ConstraintLayout currHeartCtl;
    public final LinearLayout detailLayout;
    public final QHrvLineChartView heartChatView;
    public final QCirclePieView heartCircleView;
    public final QSettingItem heartValueDetail;
    public final ImageView imageHeart;
    public final LinearLayout layout1;
    public final View line1;
    public final QDateSwitchView qcDateChange;
    public final QSettingItemTitleSubTitleSystem qcSettingHrv;
    public final LinearLayout rcvDetailLayout;
    public final RecyclerView rcvHrvDetail;
    private final NestedScrollView rootView;
    public final LayoutTitleBarBinding titleBar;
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
    public final TextView tvChartTitle;
    public final TextView tvCurrHeart;
    public final TextView tvCurrUnit;
    public final TextView tvHM;
    public final TextView tvHeart1;
    public final TextView tvHeartTime;
    public final TextView tvHeartValue;
    public final TextView tvListDetail;
    public final TextView tvMax;
    public final TextView tvMaxValue;
    public final TextView tvMin;
    public final TextView tvMinValue;
    public final TextView tvNoData;
    public final TextView tvValue1;
    public final TextView tvValue2;
    public final TextView tvValue3;
    public final TextView tvValue4;
    public final TextView tvValue5;

    private ActivityHrvBinding(NestedScrollView rootView, Button btnMeasure, ConstraintLayout cslHeartPie, View cstLine1, View cstLine2, ConstraintLayout ctlData, ConstraintLayout currHeartCtl, LinearLayout detailLayout, QHrvLineChartView heartChatView, QCirclePieView heartCircleView, QSettingItem heartValueDetail, ImageView imageHeart, LinearLayout layout1, View line1, QDateSwitchView qcDateChange, QSettingItemTitleSubTitleSystem qcSettingHrv, LinearLayout rcvDetailLayout, RecyclerView rcvHrvDetail, LayoutTitleBarBinding titleBar, TextView topBg1, TextView tv1, TextView tv11, TextView tv2, TextView tv22, TextView tv3, TextView tv4, TextView tv42, TextView tv5, TextView tv51, TextView tvAvg, TextView tvAvgValue, TextView tvChartTitle, TextView tvCurrHeart, TextView tvCurrUnit, TextView tvHM, TextView tvHeart1, TextView tvHeartTime, TextView tvHeartValue, TextView tvListDetail, TextView tvMax, TextView tvMaxValue, TextView tvMin, TextView tvMinValue, TextView tvNoData, TextView tvValue1, TextView tvValue2, TextView tvValue3, TextView tvValue4, TextView tvValue5) {
        this.rootView = rootView;
        this.btnMeasure = btnMeasure;
        this.cslHeartPie = cslHeartPie;
        this.cstLine1 = cstLine1;
        this.cstLine2 = cstLine2;
        this.ctlData = ctlData;
        this.currHeartCtl = currHeartCtl;
        this.detailLayout = detailLayout;
        this.heartChatView = heartChatView;
        this.heartCircleView = heartCircleView;
        this.heartValueDetail = heartValueDetail;
        this.imageHeart = imageHeart;
        this.layout1 = layout1;
        this.line1 = line1;
        this.qcDateChange = qcDateChange;
        this.qcSettingHrv = qcSettingHrv;
        this.rcvDetailLayout = rcvDetailLayout;
        this.rcvHrvDetail = rcvHrvDetail;
        this.titleBar = titleBar;
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
        this.tvChartTitle = tvChartTitle;
        this.tvCurrHeart = tvCurrHeart;
        this.tvCurrUnit = tvCurrUnit;
        this.tvHM = tvHM;
        this.tvHeart1 = tvHeart1;
        this.tvHeartTime = tvHeartTime;
        this.tvHeartValue = tvHeartValue;
        this.tvListDetail = tvListDetail;
        this.tvMax = tvMax;
        this.tvMaxValue = tvMaxValue;
        this.tvMin = tvMin;
        this.tvMinValue = tvMinValue;
        this.tvNoData = tvNoData;
        this.tvValue1 = tvValue1;
        this.tvValue2 = tvValue2;
        this.tvValue3 = tvValue3;
        this.tvValue4 = tvValue4;
        this.tvValue5 = tvValue5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityHrvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHrvBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_hrv, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityHrvBinding bind(View rootView) {
        int i = R.id.btn_measure;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_measure);
        if (button != null) {
            i = R.id.csl_heart_pie;
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
                            i = R.id.curr_heart_ctl;
                            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.curr_heart_ctl);
                            if (constraintLayout3 != null) {
                                i = R.id.detail_layout;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.detail_layout);
                                if (linearLayout != null) {
                                    i = R.id.heart_chat_view;
                                    QHrvLineChartView qHrvLineChartView = (QHrvLineChartView) ViewBindings.findChildViewById(rootView, R.id.heart_chat_view);
                                    if (qHrvLineChartView != null) {
                                        i = R.id.heart_circle_view;
                                        QCirclePieView qCirclePieView = (QCirclePieView) ViewBindings.findChildViewById(rootView, R.id.heart_circle_view);
                                        if (qCirclePieView != null) {
                                            i = R.id.heart_value_detail;
                                            QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.heart_value_detail);
                                            if (qSettingItem != null) {
                                                i = R.id.image_heart;
                                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_heart);
                                                if (imageView != null) {
                                                    i = R.id.layout_1;
                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_1);
                                                    if (linearLayout2 != null) {
                                                        i = R.id.line_1;
                                                        View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.line_1);
                                                        if (viewFindChildViewById3 != null) {
                                                            i = R.id.qc_date_change;
                                                            QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                                                            if (qDateSwitchView != null) {
                                                                i = R.id.qc_setting_hrv;
                                                                QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = (QSettingItemTitleSubTitleSystem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_hrv);
                                                                if (qSettingItemTitleSubTitleSystem != null) {
                                                                    i = R.id.rcv_detail_layout;
                                                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.rcv_detail_layout);
                                                                    if (linearLayout3 != null) {
                                                                        i = R.id.rcv_hrv_detail;
                                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_hrv_detail);
                                                                        if (recyclerView != null) {
                                                                            i = R.id.title_bar;
                                                                            View viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                                                            if (viewFindChildViewById4 != null) {
                                                                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById4);
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
                                                                                                                                i = R.id.tv_chart_title;
                                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_chart_title);
                                                                                                                                if (textView13 != null) {
                                                                                                                                    i = R.id.tv_curr_heart;
                                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_curr_heart);
                                                                                                                                    if (textView14 != null) {
                                                                                                                                        i = R.id.tv_curr_unit;
                                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_curr_unit);
                                                                                                                                        if (textView15 != null) {
                                                                                                                                            i = R.id.tv_h_m;
                                                                                                                                            TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_h_m);
                                                                                                                                            if (textView16 != null) {
                                                                                                                                                i = R.id.tv_heart_1;
                                                                                                                                                TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_1);
                                                                                                                                                if (textView17 != null) {
                                                                                                                                                    i = R.id.tv_heart_time;
                                                                                                                                                    TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_time);
                                                                                                                                                    if (textView18 != null) {
                                                                                                                                                        i = R.id.tv_heart_value;
                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_value);
                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                            i = R.id.tv_list_detail;
                                                                                                                                                            TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_list_detail);
                                                                                                                                                            if (textView20 != null) {
                                                                                                                                                                i = R.id.tv_max;
                                                                                                                                                                TextView textView21 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_max);
                                                                                                                                                                if (textView21 != null) {
                                                                                                                                                                    i = R.id.tv_max_value;
                                                                                                                                                                    TextView textView22 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_max_value);
                                                                                                                                                                    if (textView22 != null) {
                                                                                                                                                                        i = R.id.tv_min;
                                                                                                                                                                        TextView textView23 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_min);
                                                                                                                                                                        if (textView23 != null) {
                                                                                                                                                                            i = R.id.tv_min_value;
                                                                                                                                                                            TextView textView24 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_min_value);
                                                                                                                                                                            if (textView24 != null) {
                                                                                                                                                                                i = R.id.tv_no_data;
                                                                                                                                                                                TextView textView25 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_no_data);
                                                                                                                                                                                if (textView25 != null) {
                                                                                                                                                                                    i = R.id.tv_value_1;
                                                                                                                                                                                    TextView textView26 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_1);
                                                                                                                                                                                    if (textView26 != null) {
                                                                                                                                                                                        i = R.id.tv_value_2;
                                                                                                                                                                                        TextView textView27 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_2);
                                                                                                                                                                                        if (textView27 != null) {
                                                                                                                                                                                            i = R.id.tv_value_3;
                                                                                                                                                                                            TextView textView28 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_3);
                                                                                                                                                                                            if (textView28 != null) {
                                                                                                                                                                                                i = R.id.tv_value_4;
                                                                                                                                                                                                TextView textView29 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_4);
                                                                                                                                                                                                if (textView29 != null) {
                                                                                                                                                                                                    i = R.id.tv_value_5;
                                                                                                                                                                                                    TextView textView30 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_5);
                                                                                                                                                                                                    if (textView30 != null) {
                                                                                                                                                                                                        return new ActivityHrvBinding((NestedScrollView) rootView, button, constraintLayout, viewFindChildViewById, viewFindChildViewById2, constraintLayout2, constraintLayout3, linearLayout, qHrvLineChartView, qCirclePieView, qSettingItem, imageView, linearLayout2, viewFindChildViewById3, qDateSwitchView, qSettingItemTitleSubTitleSystem, linearLayout3, recyclerView, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28, textView29, textView30);
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
