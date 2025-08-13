package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QCirclePieView;
import com.qcwireless.qcwatch.ui.base.view.QHeartSportDetailLineChartView;
import com.qcwireless.qcwatch.ui.base.view.sport.QSportDetailItem;

/* loaded from: classes3.dex */
public final class ActivitySportDetailDistanceBinding implements ViewBinding {
    public final ConstraintLayout detailCtl1;
    public final Guideline guideLine;
    public final QCirclePieView heartCircleView;
    public final ConstraintLayout heartSupport;
    public final View line1;
    private final ConstraintLayout rootView;
    public final QHeartSportDetailLineChartView sportDetailHeartLine;
    public final QSportDetailItem sportDurationTime;
    public final QSportDetailItem sportHeart;
    public final QSportDetailItem sportHourBufu;
    public final QSportDetailItem sportHourSpeed;
    public final QSportDetailItem sportKcal;
    public final QSportDetailItem sportSpeed;
    public final QSportDetailItem sportStepBuping;
    public final QSportDetailItem sportStepCount;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tvDesc;
    public final TextView tvHeartMaxInfo;
    public final TextView tvHeartMaxValue;
    public final TextView tvHeartMinInfo;
    public final TextView tvHeartMinValue;
    public final TextView tvHeartTitle;
    public final TextView tvKcal;
    public final TextView tvKcalUnit;
    public final TextView tvSportName;
    public final TextView tvSportStartTime;
    public final TextView tvValue1;
    public final TextView tvValue2;
    public final TextView tvValue3;
    public final TextView tvValue4;
    public final TextView tvValue5;

    private ActivitySportDetailDistanceBinding(ConstraintLayout rootView, ConstraintLayout detailCtl1, Guideline guideLine, QCirclePieView heartCircleView, ConstraintLayout heartSupport, View line1, QHeartSportDetailLineChartView sportDetailHeartLine, QSportDetailItem sportDurationTime, QSportDetailItem sportHeart, QSportDetailItem sportHourBufu, QSportDetailItem sportHourSpeed, QSportDetailItem sportKcal, QSportDetailItem sportSpeed, QSportDetailItem sportStepBuping, QSportDetailItem sportStepCount, LayoutTitleBarBinding titleBar, TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5, TextView tvDesc, TextView tvHeartMaxInfo, TextView tvHeartMaxValue, TextView tvHeartMinInfo, TextView tvHeartMinValue, TextView tvHeartTitle, TextView tvKcal, TextView tvKcalUnit, TextView tvSportName, TextView tvSportStartTime, TextView tvValue1, TextView tvValue2, TextView tvValue3, TextView tvValue4, TextView tvValue5) {
        this.rootView = rootView;
        this.detailCtl1 = detailCtl1;
        this.guideLine = guideLine;
        this.heartCircleView = heartCircleView;
        this.heartSupport = heartSupport;
        this.line1 = line1;
        this.sportDetailHeartLine = sportDetailHeartLine;
        this.sportDurationTime = sportDurationTime;
        this.sportHeart = sportHeart;
        this.sportHourBufu = sportHourBufu;
        this.sportHourSpeed = sportHourSpeed;
        this.sportKcal = sportKcal;
        this.sportSpeed = sportSpeed;
        this.sportStepBuping = sportStepBuping;
        this.sportStepCount = sportStepCount;
        this.titleBar = titleBar;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tvDesc = tvDesc;
        this.tvHeartMaxInfo = tvHeartMaxInfo;
        this.tvHeartMaxValue = tvHeartMaxValue;
        this.tvHeartMinInfo = tvHeartMinInfo;
        this.tvHeartMinValue = tvHeartMinValue;
        this.tvHeartTitle = tvHeartTitle;
        this.tvKcal = tvKcal;
        this.tvKcalUnit = tvKcalUnit;
        this.tvSportName = tvSportName;
        this.tvSportStartTime = tvSportStartTime;
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

    public static ActivitySportDetailDistanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportDetailDistanceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sport_detail_distance, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportDetailDistanceBinding bind(View rootView) {
        int i = R.id.detail_ctl_1;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.detail_ctl_1);
        if (constraintLayout != null) {
            i = R.id.guide_line;
            Guideline guideline = (Guideline) ViewBindings.findChildViewById(rootView, R.id.guide_line);
            if (guideline != null) {
                i = R.id.heart_circle_view;
                QCirclePieView qCirclePieView = (QCirclePieView) ViewBindings.findChildViewById(rootView, R.id.heart_circle_view);
                if (qCirclePieView != null) {
                    i = R.id.heart_support;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.heart_support);
                    if (constraintLayout2 != null) {
                        i = R.id.line_1;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                        if (viewFindChildViewById != null) {
                            i = R.id.sport_detail_heart_line;
                            QHeartSportDetailLineChartView qHeartSportDetailLineChartView = (QHeartSportDetailLineChartView) ViewBindings.findChildViewById(rootView, R.id.sport_detail_heart_line);
                            if (qHeartSportDetailLineChartView != null) {
                                i = R.id.sport_duration_time;
                                QSportDetailItem qSportDetailItem = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_duration_time);
                                if (qSportDetailItem != null) {
                                    i = R.id.sport_heart;
                                    QSportDetailItem qSportDetailItem2 = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_heart);
                                    if (qSportDetailItem2 != null) {
                                        i = R.id.sport_hour_bufu;
                                        QSportDetailItem qSportDetailItem3 = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_hour_bufu);
                                        if (qSportDetailItem3 != null) {
                                            i = R.id.sport_hour_speed;
                                            QSportDetailItem qSportDetailItem4 = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_hour_speed);
                                            if (qSportDetailItem4 != null) {
                                                i = R.id.sport_kcal;
                                                QSportDetailItem qSportDetailItem5 = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_kcal);
                                                if (qSportDetailItem5 != null) {
                                                    i = R.id.sport_speed;
                                                    QSportDetailItem qSportDetailItem6 = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_speed);
                                                    if (qSportDetailItem6 != null) {
                                                        i = R.id.sport_step_buping;
                                                        QSportDetailItem qSportDetailItem7 = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_step_buping);
                                                        if (qSportDetailItem7 != null) {
                                                            i = R.id.sport_step_count;
                                                            QSportDetailItem qSportDetailItem8 = (QSportDetailItem) ViewBindings.findChildViewById(rootView, R.id.sport_step_count);
                                                            if (qSportDetailItem8 != null) {
                                                                i = R.id.title_bar;
                                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                                                if (viewFindChildViewById2 != null) {
                                                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                                                                    i = R.id.tv_1;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
                                                                    if (textView != null) {
                                                                        i = R.id.tv_2;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
                                                                        if (textView2 != null) {
                                                                            i = R.id.tv_3;
                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_3);
                                                                            if (textView3 != null) {
                                                                                i = R.id.tv_4;
                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_4);
                                                                                if (textView4 != null) {
                                                                                    i = R.id.tv_5;
                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_5);
                                                                                    if (textView5 != null) {
                                                                                        i = R.id.tv_desc;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_desc);
                                                                                        if (textView6 != null) {
                                                                                            i = R.id.tv_heart_max_info;
                                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_max_info);
                                                                                            if (textView7 != null) {
                                                                                                i = R.id.tv_heart_max_value;
                                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_max_value);
                                                                                                if (textView8 != null) {
                                                                                                    i = R.id.tv_heart_min_info;
                                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_min_info);
                                                                                                    if (textView9 != null) {
                                                                                                        i = R.id.tv_heart_min_value;
                                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_min_value);
                                                                                                        if (textView10 != null) {
                                                                                                            i = R.id.tv_heart_title;
                                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_heart_title);
                                                                                                            if (textView11 != null) {
                                                                                                                i = R.id.tv_kcal;
                                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_kcal);
                                                                                                                if (textView12 != null) {
                                                                                                                    i = R.id.tv_kcal_unit;
                                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_kcal_unit);
                                                                                                                    if (textView13 != null) {
                                                                                                                        i = R.id.tv_sport_name;
                                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_name);
                                                                                                                        if (textView14 != null) {
                                                                                                                            i = R.id.tv_sport_start_time;
                                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_start_time);
                                                                                                                            if (textView15 != null) {
                                                                                                                                i = R.id.tv_value_1;
                                                                                                                                TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_1);
                                                                                                                                if (textView16 != null) {
                                                                                                                                    i = R.id.tv_value_2;
                                                                                                                                    TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_2);
                                                                                                                                    if (textView17 != null) {
                                                                                                                                        i = R.id.tv_value_3;
                                                                                                                                        TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_3);
                                                                                                                                        if (textView18 != null) {
                                                                                                                                            i = R.id.tv_value_4;
                                                                                                                                            TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_4);
                                                                                                                                            if (textView19 != null) {
                                                                                                                                                i = R.id.tv_value_5;
                                                                                                                                                TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_value_5);
                                                                                                                                                if (textView20 != null) {
                                                                                                                                                    return new ActivitySportDetailDistanceBinding((ConstraintLayout) rootView, constraintLayout, guideline, qCirclePieView, constraintLayout2, viewFindChildViewById, qHeartSportDetailLineChartView, qSportDetailItem, qSportDetailItem2, qSportDetailItem3, qSportDetailItem4, qSportDetailItem5, qSportDetailItem6, qSportDetailItem7, qSportDetailItem8, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20);
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
