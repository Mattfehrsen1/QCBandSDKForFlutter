package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;

/* loaded from: classes3.dex */
public final class ActivityGoalSettingBinding implements ViewBinding {
    public final QSettingItem goalSettingCalorie;
    public final QSettingItem goalSettingDistance;
    public final QSettingItem goalSettingSleepTime;
    public final QSettingItem goalSettingSportTime;
    public final QSettingItem goalSettingStep;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityGoalSettingBinding(ConstraintLayout rootView, QSettingItem goalSettingCalorie, QSettingItem goalSettingDistance, QSettingItem goalSettingSleepTime, QSettingItem goalSettingSportTime, QSettingItem goalSettingStep, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.goalSettingCalorie = goalSettingCalorie;
        this.goalSettingDistance = goalSettingDistance;
        this.goalSettingSleepTime = goalSettingSleepTime;
        this.goalSettingSportTime = goalSettingSportTime;
        this.goalSettingStep = goalSettingStep;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGoalSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGoalSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_goal_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGoalSettingBinding bind(View rootView) {
        int i = R.id.goal_setting_calorie;
        QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.goal_setting_calorie);
        if (qSettingItem != null) {
            i = R.id.goal_setting_distance;
            QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.goal_setting_distance);
            if (qSettingItem2 != null) {
                i = R.id.goal_setting_sleep_time;
                QSettingItem qSettingItem3 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.goal_setting_sleep_time);
                if (qSettingItem3 != null) {
                    i = R.id.goal_setting_sport_time;
                    QSettingItem qSettingItem4 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.goal_setting_sport_time);
                    if (qSettingItem4 != null) {
                        i = R.id.goal_setting_step;
                        QSettingItem qSettingItem5 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.goal_setting_step);
                        if (qSettingItem5 != null) {
                            i = R.id.title_bar;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                            if (viewFindChildViewById != null) {
                                return new ActivityGoalSettingBinding((ConstraintLayout) rootView, qSettingItem, qSettingItem2, qSettingItem3, qSettingItem4, qSettingItem5, LayoutTitleBarBinding.bind(viewFindChildViewById));
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
