package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QStepBarChart;
import com.qcwireless.qcwatch.ui.base.view.healthy.MarqueeView;

/* loaded from: classes3.dex */
public final class RecycleviewItemHeaderBinding implements ViewBinding {
    public final ConstraintLayout clTodayStep;
    public final ConstraintLayout clsAiAnalyze;
    public final TextView healthyTextTitle;
    public final ImageView imageBleStatus;
    public final ImageView imageRight;
    public final ImageView imageView1;
    public final ImageView ivAiBg;
    public final MarqueeView marqueeView;
    public final QStepBarChart qcStepChart;
    private final ConstraintLayout rootView;
    public final ConstraintLayout todayStepLayout;
    public final TextView tvAiAnalyzeValue;
    public final TextView tvStepUnit;
    public final TextView tvTodaySteps;
    public final ConstraintLayout warmingInfo;

    private RecycleviewItemHeaderBinding(ConstraintLayout rootView, ConstraintLayout clTodayStep, ConstraintLayout clsAiAnalyze, TextView healthyTextTitle, ImageView imageBleStatus, ImageView imageRight, ImageView imageView1, ImageView ivAiBg, MarqueeView marqueeView, QStepBarChart qcStepChart, ConstraintLayout todayStepLayout, TextView tvAiAnalyzeValue, TextView tvStepUnit, TextView tvTodaySteps, ConstraintLayout warmingInfo) {
        this.rootView = rootView;
        this.clTodayStep = clTodayStep;
        this.clsAiAnalyze = clsAiAnalyze;
        this.healthyTextTitle = healthyTextTitle;
        this.imageBleStatus = imageBleStatus;
        this.imageRight = imageRight;
        this.imageView1 = imageView1;
        this.ivAiBg = ivAiBg;
        this.marqueeView = marqueeView;
        this.qcStepChart = qcStepChart;
        this.todayStepLayout = todayStepLayout;
        this.tvAiAnalyzeValue = tvAiAnalyzeValue;
        this.tvStepUnit = tvStepUnit;
        this.tvTodaySteps = tvTodaySteps;
        this.warmingInfo = warmingInfo;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemHeaderBinding bind(View rootView) {
        int i = R.id.cl_today_step;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cl_today_step);
        if (constraintLayout != null) {
            i = R.id.cls_ai_analyze;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cls_ai_analyze);
            if (constraintLayout2 != null) {
                i = R.id.healthy_text_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.healthy_text_title);
                if (textView != null) {
                    i = R.id.image_ble_status;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_ble_status);
                    if (imageView != null) {
                        i = R.id.image_right;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_right);
                        if (imageView2 != null) {
                            i = R.id.image_view_1;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_view_1);
                            if (imageView3 != null) {
                                i = R.id.iv_ai_bg;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_ai_bg);
                                if (imageView4 != null) {
                                    i = R.id.marquee_view;
                                    MarqueeView marqueeView = (MarqueeView) ViewBindings.findChildViewById(rootView, R.id.marquee_view);
                                    if (marqueeView != null) {
                                        i = R.id.qc_step_chart;
                                        QStepBarChart qStepBarChart = (QStepBarChart) ViewBindings.findChildViewById(rootView, R.id.qc_step_chart);
                                        if (qStepBarChart != null) {
                                            i = R.id.today_step_layout;
                                            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.today_step_layout);
                                            if (constraintLayout3 != null) {
                                                i = R.id.tv_ai_analyze_value;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_ai_analyze_value);
                                                if (textView2 != null) {
                                                    i = R.id.tv_step_unit;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_step_unit);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_today_steps;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_today_steps);
                                                        if (textView4 != null) {
                                                            i = R.id.warming_info;
                                                            ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.warming_info);
                                                            if (constraintLayout4 != null) {
                                                                return new RecycleviewItemHeaderBinding((ConstraintLayout) rootView, constraintLayout, constraintLayout2, textView, imageView, imageView2, imageView3, imageView4, marqueeView, qStepBarChart, constraintLayout3, textView2, textView3, textView4, constraintLayout4);
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
