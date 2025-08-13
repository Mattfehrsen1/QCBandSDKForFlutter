package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityMenstruationSettingBinding implements ViewBinding {
    public final Group alarmGroup;
    public final View line1;
    public final View line2;
    public final ConstraintLayout menstruationGroup;
    public final QSettingItem qcMsAlarm1;
    public final QSettingItem qcMsAlarm2;
    public final QSettingItem qcMsAlarm3;
    public final QSettingItemWithClickSystem qcMsAlarmSwitch;
    public final QSettingItem qcMsCycle;
    public final QSettingItem qcMsDuring;
    public final QSettingItem qcMsLast;
    public final QSettingItemWithClickSystem qcMsSwitch;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvTitle1;
    public final TextView tvTitle2;
    public final TextView tvTitle3;

    private ActivityMenstruationSettingBinding(ConstraintLayout rootView, Group alarmGroup, View line1, View line2, ConstraintLayout menstruationGroup, QSettingItem qcMsAlarm1, QSettingItem qcMsAlarm2, QSettingItem qcMsAlarm3, QSettingItemWithClickSystem qcMsAlarmSwitch, QSettingItem qcMsCycle, QSettingItem qcMsDuring, QSettingItem qcMsLast, QSettingItemWithClickSystem qcMsSwitch, LayoutTitleBarBinding titleBar, TextView tvTitle1, TextView tvTitle2, TextView tvTitle3) {
        this.rootView = rootView;
        this.alarmGroup = alarmGroup;
        this.line1 = line1;
        this.line2 = line2;
        this.menstruationGroup = menstruationGroup;
        this.qcMsAlarm1 = qcMsAlarm1;
        this.qcMsAlarm2 = qcMsAlarm2;
        this.qcMsAlarm3 = qcMsAlarm3;
        this.qcMsAlarmSwitch = qcMsAlarmSwitch;
        this.qcMsCycle = qcMsCycle;
        this.qcMsDuring = qcMsDuring;
        this.qcMsLast = qcMsLast;
        this.qcMsSwitch = qcMsSwitch;
        this.titleBar = titleBar;
        this.tvTitle1 = tvTitle1;
        this.tvTitle2 = tvTitle2;
        this.tvTitle3 = tvTitle3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMenstruationSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMenstruationSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_menstruation_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMenstruationSettingBinding bind(View rootView) {
        int i = R.id.alarm_group;
        Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.alarm_group);
        if (group != null) {
            i = R.id.line_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
            if (viewFindChildViewById != null) {
                i = R.id.line_2;
                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.line_2);
                if (viewFindChildViewById2 != null) {
                    i = R.id.menstruation_group;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.menstruation_group);
                    if (constraintLayout != null) {
                        i = R.id.qc_ms_alarm_1;
                        QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_alarm_1);
                        if (qSettingItem != null) {
                            i = R.id.qc_ms_alarm_2;
                            QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_alarm_2);
                            if (qSettingItem2 != null) {
                                i = R.id.qc_ms_alarm_3;
                                QSettingItem qSettingItem3 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_alarm_3);
                                if (qSettingItem3 != null) {
                                    i = R.id.qc_ms_alarm_switch;
                                    QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_alarm_switch);
                                    if (qSettingItemWithClickSystem != null) {
                                        i = R.id.qc_ms_cycle;
                                        QSettingItem qSettingItem4 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_cycle);
                                        if (qSettingItem4 != null) {
                                            i = R.id.qc_ms_during;
                                            QSettingItem qSettingItem5 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_during);
                                            if (qSettingItem5 != null) {
                                                i = R.id.qc_ms_last;
                                                QSettingItem qSettingItem6 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_last);
                                                if (qSettingItem6 != null) {
                                                    i = R.id.qc_ms_switch;
                                                    QSettingItemWithClickSystem qSettingItemWithClickSystem2 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_ms_switch);
                                                    if (qSettingItemWithClickSystem2 != null) {
                                                        i = R.id.title_bar;
                                                        View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                                        if (viewFindChildViewById3 != null) {
                                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById3);
                                                            i = R.id.tv_title_1;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_1);
                                                            if (textView != null) {
                                                                i = R.id.tv_title_2;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_2);
                                                                if (textView2 != null) {
                                                                    i = R.id.tv_title_3;
                                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_3);
                                                                    if (textView3 != null) {
                                                                        return new ActivityMenstruationSettingBinding((ConstraintLayout) rootView, group, viewFindChildViewById, viewFindChildViewById2, constraintLayout, qSettingItem, qSettingItem2, qSettingItem3, qSettingItemWithClickSystem, qSettingItem4, qSettingItem5, qSettingItem6, qSettingItemWithClickSystem2, layoutTitleBarBindingBind, textView, textView2, textView3);
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
