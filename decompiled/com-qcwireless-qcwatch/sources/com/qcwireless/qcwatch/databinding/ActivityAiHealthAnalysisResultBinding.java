package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityAiHealthAnalysisResultBinding implements ViewBinding {
    public final View divider;
    public final TextView healthyTop1;
    public final ImageView imageShare;
    public final ImageView ivAiBg;
    public final ImageView ivNavigateBefore;
    public final LinearLayout llFooter;
    public final ScrollView nestScrollView;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final RelativeLayout titleBar;
    public final TextView tvAnalTitle;
    public final TextView tvDate;
    public final TextView tvHint;
    public final TextView tvResultTitle;
    public final TextView tvScore;
    public final TextView tvTimeNotification;
    public final TextView tvTitle;

    private ActivityAiHealthAnalysisResultBinding(ConstraintLayout rootView, View divider, TextView healthyTop1, ImageView imageShare, ImageView ivAiBg, ImageView ivNavigateBefore, LinearLayout llFooter, ScrollView nestScrollView, RecyclerView recyclerView, RelativeLayout titleBar, TextView tvAnalTitle, TextView tvDate, TextView tvHint, TextView tvResultTitle, TextView tvScore, TextView tvTimeNotification, TextView tvTitle) {
        this.rootView = rootView;
        this.divider = divider;
        this.healthyTop1 = healthyTop1;
        this.imageShare = imageShare;
        this.ivAiBg = ivAiBg;
        this.ivNavigateBefore = ivNavigateBefore;
        this.llFooter = llFooter;
        this.nestScrollView = nestScrollView;
        this.recyclerView = recyclerView;
        this.titleBar = titleBar;
        this.tvAnalTitle = tvAnalTitle;
        this.tvDate = tvDate;
        this.tvHint = tvHint;
        this.tvResultTitle = tvResultTitle;
        this.tvScore = tvScore;
        this.tvTimeNotification = tvTimeNotification;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiHealthAnalysisResultBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiHealthAnalysisResultBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_health_analysis_result, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiHealthAnalysisResultBinding bind(View rootView) {
        int i = R.id.divider;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.divider);
        if (viewFindChildViewById != null) {
            i = R.id.healthy_top_1;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.healthy_top_1);
            if (textView != null) {
                i = R.id.image_share;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_share);
                if (imageView != null) {
                    i = R.id.iv_ai_bg;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_ai_bg);
                    if (imageView2 != null) {
                        i = R.id.ivNavigateBefore;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.ivNavigateBefore);
                        if (imageView3 != null) {
                            i = R.id.ll_footer;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_footer);
                            if (linearLayout != null) {
                                i = R.id.nestScrollView;
                                ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(rootView, R.id.nestScrollView);
                                if (scrollView != null) {
                                    i = R.id.recyclerView;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
                                    if (recyclerView != null) {
                                        i = R.id.titleBar;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                        if (relativeLayout != null) {
                                            i = R.id.tv_anal_title;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_anal_title);
                                            if (textView2 != null) {
                                                i = R.id.tv_date;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_date);
                                                if (textView3 != null) {
                                                    i = R.id.tv_hint;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_hint);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_result_title;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_result_title);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_score;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_score);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_time_notification;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_time_notification);
                                                                if (textView7 != null) {
                                                                    i = R.id.tvTitle;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvTitle);
                                                                    if (textView8 != null) {
                                                                        return new ActivityAiHealthAnalysisResultBinding((ConstraintLayout) rootView, viewFindChildViewById, textView, imageView, imageView2, imageView3, linearLayout, scrollView, recyclerView, relativeLayout, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
