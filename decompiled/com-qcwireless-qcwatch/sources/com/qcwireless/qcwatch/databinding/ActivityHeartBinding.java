package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QCirclePieView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QHeartLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemTitleSubTitleSystem;

/* loaded from: classes3.dex */
public final class ActivityHeartBinding implements ViewBinding {
    public final ConstraintLayout cslHeartPie;
    public final ConstraintLayout cslInfoView;
    public final ConstraintLayout currHeartCtl;
    public final LinearLayout detailLayout;
    public final QHeartLineChartView heartChatView;
    public final QCirclePieView heartCircleView;
    public final QSettingItem heartValueDetail;
    public final ImageView iamgeHeart;
    public final LinearLayout layout1;
    public final QDateSwitchView qcDateChange;
    public final QSettingItemTitleSubTitleSystem qcSettingHeart;
    private final NestedScrollView rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView topBg1;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tvCurrHeart;
    public final TextView tvDesc;
    public final TextView tvHM;
    public final TextView tvHeartValue;
    public final TextView tvInfo1;
    public final TextView tvValue1;
    public final TextView tvValue2;
    public final TextView tvValue3;
    public final TextView tvValue4;
    public final TextView tvValue5;

    private ActivityHeartBinding(NestedScrollView rootView, ConstraintLayout cslHeartPie, ConstraintLayout cslInfoView, ConstraintLayout currHeartCtl, LinearLayout detailLayout, QHeartLineChartView heartChatView, QCirclePieView heartCircleView, QSettingItem heartValueDetail, ImageView iamgeHeart, LinearLayout layout1, QDateSwitchView qcDateChange, QSettingItemTitleSubTitleSystem qcSettingHeart, LayoutTitleBarBinding titleBar, TextView topBg1, TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5, TextView tvCurrHeart, TextView tvDesc, TextView tvHM, TextView tvHeartValue, TextView tvInfo1, TextView tvValue1, TextView tvValue2, TextView tvValue3, TextView tvValue4, TextView tvValue5) {
        this.rootView = rootView;
        this.cslHeartPie = cslHeartPie;
        this.cslInfoView = cslInfoView;
        this.currHeartCtl = currHeartCtl;
        this.detailLayout = detailLayout;
        this.heartChatView = heartChatView;
        this.heartCircleView = heartCircleView;
        this.heartValueDetail = heartValueDetail;
        this.iamgeHeart = iamgeHeart;
        this.layout1 = layout1;
        this.qcDateChange = qcDateChange;
        this.qcSettingHeart = qcSettingHeart;
        this.titleBar = titleBar;
        this.topBg1 = topBg1;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tvCurrHeart = tvCurrHeart;
        this.tvDesc = tvDesc;
        this.tvHM = tvHM;
        this.tvHeartValue = tvHeartValue;
        this.tvInfo1 = tvInfo1;
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

    public static ActivityHeartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityHeartBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_heart, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityHeartBinding bind(View rootView) {
        int i = R.id.csl_heart_pie;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_heart_pie);
        if (constraintLayout != null) {
            i = R.id.csl_info_view;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_info_view);
            if (constraintLayout2 != null) {
                i = R.id.curr_heart_ctl;
                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.curr_heart_ctl);
                if (constraintLayout3 != null) {
                    i = R.id.detail_layout;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.detail_layout);
                    if (linearLayout != null) {
                        i = R.id.heart_chat_view;
                        QHeartLineChartView qHeartLineChartView = (QHeartLineChartView) ViewBindings.findChildViewById(rootView, R.id.heart_chat_view);
                        if (qHeartLineChartView != null) {
                            i = R.id.heart_circle_view;
                            QCirclePieView qCirclePieView = (QCirclePieView) ViewBindings.findChildViewById(rootView, R.id.heart_circle_view);
                            if (qCirclePieView != null) {
                                i = R.id.heart_value_detail;
                                QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.heart_value_detail);
                                if (qSettingItem != null) {
                                    i = R.id.iamge_heart;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iamge_heart);
                                    if (imageView != null) {
                                        i = R.id.layout_1;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_1);
                                        if (linearLayout2 != null) {
                                            i = R.id.qc_date_change;
                                            QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                                            if (qDateSwitchView != null) {
                                                i = R.id.qc_setting_heart;
                                                QSettingItemTitleSubTitleSystem qSettingItemTitleSubTitleSystem = (QSettingItemTitleSubTitleSystem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_heart);
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
                                                                i = R.id.tv_2;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_3;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_3);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_4;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_4);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_5;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_5);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_curr_heart;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_curr_heart);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_desc;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_desc);
                                                                                    if (textView8 != null) {
                                                                                        i = R.id.tv_h_m;
                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_h_m);
                                                                                        if (textView9 != null) {
                                                                                            i = R.id.tv_heart_value;
                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_value);
                                                                                            if (textView10 != null) {
                                                                                                i = R.id.tv_info_1;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                                                                                if (textView11 != null) {
                                                                                                    i = R.id.tv_value_1;
                                                                                                    TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_1);
                                                                                                    if (textView12 != null) {
                                                                                                        i = R.id.tv_value_2;
                                                                                                        TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_2);
                                                                                                        if (textView13 != null) {
                                                                                                            i = R.id.tv_value_3;
                                                                                                            TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_3);
                                                                                                            if (textView14 != null) {
                                                                                                                i = R.id.tv_value_4;
                                                                                                                TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_4);
                                                                                                                if (textView15 != null) {
                                                                                                                    i = R.id.tv_value_5;
                                                                                                                    TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_5);
                                                                                                                    if (textView16 != null) {
                                                                                                                        return new ActivityHeartBinding((NestedScrollView) rootView, constraintLayout, constraintLayout2, constraintLayout3, linearLayout, qHeartLineChartView, qCirclePieView, qSettingItem, imageView, linearLayout2, qDateSwitchView, qSettingItemTitleSubTitleSystem, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16);
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
