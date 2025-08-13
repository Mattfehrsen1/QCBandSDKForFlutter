package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick;

/* loaded from: classes3.dex */
public final class ActivityMenstruationBinding implements ViewBinding {
    public final ConstraintLayout calendarLayout;
    public final CalendarLayout calendarLayout1;
    public final CalendarView calendarView;
    public final QDateMonthSwitchView qcDateChange;
    public final QSettingItemWithClick qcMenstruationStatus;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvNotDo;
    public final TextView tvNumberDay;
    public final TextView tvStatus;
    public final TextView tvTitle1;
    public final TextView tvTitle2;
    public final TextView tvTitle3;
    public final TextView tvTitle4;

    private ActivityMenstruationBinding(ConstraintLayout rootView, ConstraintLayout calendarLayout, CalendarLayout calendarLayout1, CalendarView calendarView, QDateMonthSwitchView qcDateChange, QSettingItemWithClick qcMenstruationStatus, LayoutTitleBarBinding titleBar, TextView tvNotDo, TextView tvNumberDay, TextView tvStatus, TextView tvTitle1, TextView tvTitle2, TextView tvTitle3, TextView tvTitle4) {
        this.rootView = rootView;
        this.calendarLayout = calendarLayout;
        this.calendarLayout1 = calendarLayout1;
        this.calendarView = calendarView;
        this.qcDateChange = qcDateChange;
        this.qcMenstruationStatus = qcMenstruationStatus;
        this.titleBar = titleBar;
        this.tvNotDo = tvNotDo;
        this.tvNumberDay = tvNumberDay;
        this.tvStatus = tvStatus;
        this.tvTitle1 = tvTitle1;
        this.tvTitle2 = tvTitle2;
        this.tvTitle3 = tvTitle3;
        this.tvTitle4 = tvTitle4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMenstruationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMenstruationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_menstruation, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMenstruationBinding bind(View rootView) {
        int i = R.id.calendar_layout;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.calendar_layout);
        if (constraintLayout != null) {
            i = R.id.calendarLayout;
            CalendarLayout calendarLayout = (CalendarLayout) ViewBindings.findChildViewById(rootView, R.id.calendarLayout);
            if (calendarLayout != null) {
                i = R.id.calendarView;
                CalendarView calendarView = (CalendarView) ViewBindings.findChildViewById(rootView, R.id.calendarView);
                if (calendarView != null) {
                    i = R.id.qc_date_change;
                    QDateMonthSwitchView qDateMonthSwitchView = (QDateMonthSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                    if (qDateMonthSwitchView != null) {
                        i = R.id.qc_menstruation_status;
                        QSettingItemWithClick qSettingItemWithClick = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.qc_menstruation_status);
                        if (qSettingItemWithClick != null) {
                            i = R.id.title_bar;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                            if (viewFindChildViewById != null) {
                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                i = R.id.tv_not_do;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_not_do);
                                if (textView != null) {
                                    i = R.id.tv_number_day;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_number_day);
                                    if (textView2 != null) {
                                        i = R.id.tv_status;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_status);
                                        if (textView3 != null) {
                                            i = R.id.tv_title_1;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_1);
                                            if (textView4 != null) {
                                                i = R.id.tv_title_2;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_2);
                                                if (textView5 != null) {
                                                    i = R.id.tv_title_3;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_3);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_title_4;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_4);
                                                        if (textView7 != null) {
                                                            return new ActivityMenstruationBinding((ConstraintLayout) rootView, constraintLayout, calendarLayout, calendarView, qDateMonthSwitchView, qSettingItemWithClick, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
