package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;

/* loaded from: classes3.dex */
public final class ActivityAiReminderBinding implements ViewBinding {
    public final ImageView addAlarm;
    public final View dot2;
    public final QSettingItem qcDrink;
    public final QSettingItem qcLongSit;
    public final RecyclerView rcvAlarm;
    private final ConstraintLayout rootView;
    public final TextView subTitle1;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvReminderTitle;

    private ActivityAiReminderBinding(ConstraintLayout rootView, ImageView addAlarm, View dot2, QSettingItem qcDrink, QSettingItem qcLongSit, RecyclerView rcvAlarm, TextView subTitle1, LayoutTitleBarBinding titleBar, TextView tvReminderTitle) {
        this.rootView = rootView;
        this.addAlarm = addAlarm;
        this.dot2 = dot2;
        this.qcDrink = qcDrink;
        this.qcLongSit = qcLongSit;
        this.rcvAlarm = rcvAlarm;
        this.subTitle1 = subTitle1;
        this.titleBar = titleBar;
        this.tvReminderTitle = tvReminderTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAiReminderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAiReminderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ai_reminder, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAiReminderBinding bind(View rootView) {
        int i = R.id.add_alarm;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.add_alarm);
        if (imageView != null) {
            i = R.id.dot_2;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.dot_2);
            if (viewFindChildViewById != null) {
                i = R.id.qc_drink;
                QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_drink);
                if (qSettingItem != null) {
                    i = R.id.qc_long_sit;
                    QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_long_sit);
                    if (qSettingItem2 != null) {
                        i = R.id.rcv_alarm;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_alarm);
                        if (recyclerView != null) {
                            i = R.id.sub_title_1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_title_1);
                            if (textView != null) {
                                i = R.id.titleBar;
                                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                if (viewFindChildViewById2 != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                                    i = R.id.tv_reminder_title;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_reminder_title);
                                    if (textView2 != null) {
                                        return new ActivityAiReminderBinding((ConstraintLayout) rootView, imageView, viewFindChildViewById, qSettingItem, qSettingItem2, recyclerView, textView, layoutTitleBarBindingBind, textView2);
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
