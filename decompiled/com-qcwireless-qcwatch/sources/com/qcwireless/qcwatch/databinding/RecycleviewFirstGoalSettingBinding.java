package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.ucenter.MoneySelectRuleView;

/* loaded from: classes3.dex */
public final class RecycleviewFirstGoalSettingBinding implements ViewBinding {
    public final Button btnOver;
    public final MoneySelectRuleView distanceGoalSelect;
    public final MoneySelectRuleView kcalGoalSelect;
    public final View line1;
    public final View line2;
    public final View line3;
    public final View line4;
    public final View line5;
    private final ConstraintLayout rootView;
    public final MoneySelectRuleView sleepGoalSelect;
    public final MoneySelectRuleView sportGoalSelect;
    public final MoneySelectRuleView stepGoalSelect;
    public final TextView tvDistanceGoalValue;
    public final TextView tvDistanceTitle;
    public final TextView tvDistanceValueUnit;
    public final TextView tvKcalGoalValue;
    public final TextView tvKcalTitle;
    public final TextView tvKcalValueUnit;
    public final TextView tvSleepGoalValue;
    public final TextView tvSleepTitle;
    public final TextView tvSleepValueUnit;
    public final TextView tvSportGoalValue;
    public final TextView tvSportTitle;
    public final TextView tvSportValueUnit;
    public final TextView tvStepGoal;
    public final TextView tvStepGoalValue;
    public final TextView tvStepValueUnit;

    private RecycleviewFirstGoalSettingBinding(ConstraintLayout rootView, Button btnOver, MoneySelectRuleView distanceGoalSelect, MoneySelectRuleView kcalGoalSelect, View line1, View line2, View line3, View line4, View line5, MoneySelectRuleView sleepGoalSelect, MoneySelectRuleView sportGoalSelect, MoneySelectRuleView stepGoalSelect, TextView tvDistanceGoalValue, TextView tvDistanceTitle, TextView tvDistanceValueUnit, TextView tvKcalGoalValue, TextView tvKcalTitle, TextView tvKcalValueUnit, TextView tvSleepGoalValue, TextView tvSleepTitle, TextView tvSleepValueUnit, TextView tvSportGoalValue, TextView tvSportTitle, TextView tvSportValueUnit, TextView tvStepGoal, TextView tvStepGoalValue, TextView tvStepValueUnit) {
        this.rootView = rootView;
        this.btnOver = btnOver;
        this.distanceGoalSelect = distanceGoalSelect;
        this.kcalGoalSelect = kcalGoalSelect;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.line5 = line5;
        this.sleepGoalSelect = sleepGoalSelect;
        this.sportGoalSelect = sportGoalSelect;
        this.stepGoalSelect = stepGoalSelect;
        this.tvDistanceGoalValue = tvDistanceGoalValue;
        this.tvDistanceTitle = tvDistanceTitle;
        this.tvDistanceValueUnit = tvDistanceValueUnit;
        this.tvKcalGoalValue = tvKcalGoalValue;
        this.tvKcalTitle = tvKcalTitle;
        this.tvKcalValueUnit = tvKcalValueUnit;
        this.tvSleepGoalValue = tvSleepGoalValue;
        this.tvSleepTitle = tvSleepTitle;
        this.tvSleepValueUnit = tvSleepValueUnit;
        this.tvSportGoalValue = tvSportGoalValue;
        this.tvSportTitle = tvSportTitle;
        this.tvSportValueUnit = tvSportValueUnit;
        this.tvStepGoal = tvStepGoal;
        this.tvStepGoalValue = tvStepGoalValue;
        this.tvStepValueUnit = tvStepValueUnit;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewFirstGoalSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewFirstGoalSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_first_goal_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewFirstGoalSettingBinding bind(View rootView) {
        int i = R.id.btn_over;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_over);
        if (button != null) {
            i = R.id.distance_goal_select;
            MoneySelectRuleView moneySelectRuleView = (MoneySelectRuleView) ViewBindings.findChildViewById(rootView, R.id.distance_goal_select);
            if (moneySelectRuleView != null) {
                i = R.id.kcal_goal_select;
                MoneySelectRuleView moneySelectRuleView2 = (MoneySelectRuleView) ViewBindings.findChildViewById(rootView, R.id.kcal_goal_select);
                if (moneySelectRuleView2 != null) {
                    i = R.id.line_1;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                    if (viewFindChildViewById != null) {
                        i = R.id.line_2;
                        View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.line_2);
                        if (viewFindChildViewById2 != null) {
                            i = R.id.line_3;
                            View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.line_3);
                            if (viewFindChildViewById3 != null) {
                                i = R.id.line_4;
                                View viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, R.id.line_4);
                                if (viewFindChildViewById4 != null) {
                                    i = R.id.line_5;
                                    View viewFindChildViewById5 = ViewBindings.findChildViewById(rootView, R.id.line_5);
                                    if (viewFindChildViewById5 != null) {
                                        i = R.id.sleep_goal_select;
                                        MoneySelectRuleView moneySelectRuleView3 = (MoneySelectRuleView) ViewBindings.findChildViewById(rootView, R.id.sleep_goal_select);
                                        if (moneySelectRuleView3 != null) {
                                            i = R.id.sport_goal_select;
                                            MoneySelectRuleView moneySelectRuleView4 = (MoneySelectRuleView) ViewBindings.findChildViewById(rootView, R.id.sport_goal_select);
                                            if (moneySelectRuleView4 != null) {
                                                i = R.id.step_goal_select;
                                                MoneySelectRuleView moneySelectRuleView5 = (MoneySelectRuleView) ViewBindings.findChildViewById(rootView, R.id.step_goal_select);
                                                if (moneySelectRuleView5 != null) {
                                                    i = R.id.tv_distance_goal_value;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_distance_goal_value);
                                                    if (textView != null) {
                                                        i = R.id.tv_distance_title;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_distance_title);
                                                        if (textView2 != null) {
                                                            i = R.id.tv_distance_value_unit;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_distance_value_unit);
                                                            if (textView3 != null) {
                                                                i = R.id.tv_kcal_goal_value;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_kcal_goal_value);
                                                                if (textView4 != null) {
                                                                    i = R.id.tv_kcal_title;
                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_kcal_title);
                                                                    if (textView5 != null) {
                                                                        i = R.id.tv_kcal_value_unit;
                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_kcal_value_unit);
                                                                        if (textView6 != null) {
                                                                            i = R.id.tv_sleep_goal_value;
                                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_goal_value);
                                                                            if (textView7 != null) {
                                                                                i = R.id.tv_sleep_title;
                                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_title);
                                                                                if (textView8 != null) {
                                                                                    i = R.id.tv_sleep_value_unit;
                                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sleep_value_unit);
                                                                                    if (textView9 != null) {
                                                                                        i = R.id.tv_sport_goal_value;
                                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_goal_value);
                                                                                        if (textView10 != null) {
                                                                                            i = R.id.tv_sport_title;
                                                                                            TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_title);
                                                                                            if (textView11 != null) {
                                                                                                i = R.id.tv_sport_value_unit;
                                                                                                TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_value_unit);
                                                                                                if (textView12 != null) {
                                                                                                    i = R.id.tv_step_goal;
                                                                                                    TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_step_goal);
                                                                                                    if (textView13 != null) {
                                                                                                        i = R.id.tv_step_goal_value;
                                                                                                        TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_step_goal_value);
                                                                                                        if (textView14 != null) {
                                                                                                            i = R.id.tv_step_value_unit;
                                                                                                            TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_step_value_unit);
                                                                                                            if (textView15 != null) {
                                                                                                                return new RecycleviewFirstGoalSettingBinding((ConstraintLayout) rootView, button, moneySelectRuleView, moneySelectRuleView2, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, viewFindChildViewById5, moneySelectRuleView3, moneySelectRuleView4, moneySelectRuleView5, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15);
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
