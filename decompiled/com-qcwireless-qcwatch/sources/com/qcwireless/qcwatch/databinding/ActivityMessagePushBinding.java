package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityMessagePushBinding implements ViewBinding {
    public final View line1;
    public final View line2;
    public final Group messagePushGroup;
    public final RecyclerView pushRcv;
    public final QSettingItemWithClickSystem qcMessageSwitch;
    public final QSettingItemWithClickSystem qcSmsSwitch;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvInfo1;

    private ActivityMessagePushBinding(ConstraintLayout rootView, View line1, View line2, Group messagePushGroup, RecyclerView pushRcv, QSettingItemWithClickSystem qcMessageSwitch, QSettingItemWithClickSystem qcSmsSwitch, LayoutTitleBarBinding titleBar, TextView tvInfo1) {
        this.rootView = rootView;
        this.line1 = line1;
        this.line2 = line2;
        this.messagePushGroup = messagePushGroup;
        this.pushRcv = pushRcv;
        this.qcMessageSwitch = qcMessageSwitch;
        this.qcSmsSwitch = qcSmsSwitch;
        this.titleBar = titleBar;
        this.tvInfo1 = tvInfo1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMessagePushBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMessagePushBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_message_push, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMessagePushBinding bind(View rootView) {
        int i = R.id.line_1;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
        if (viewFindChildViewById != null) {
            i = R.id.line_2;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.line_2);
            if (viewFindChildViewById2 != null) {
                i = R.id.message_push_group;
                Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.message_push_group);
                if (group != null) {
                    i = R.id.push_rcv;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.push_rcv);
                    if (recyclerView != null) {
                        i = R.id.qc_message_switch;
                        QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_message_switch);
                        if (qSettingItemWithClickSystem != null) {
                            i = R.id.qc_sms_switch;
                            QSettingItemWithClickSystem qSettingItemWithClickSystem2 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_sms_switch);
                            if (qSettingItemWithClickSystem2 != null) {
                                i = R.id.titleBar;
                                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                if (viewFindChildViewById3 != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById3);
                                    i = R.id.tv_info_1;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                    if (textView != null) {
                                        return new ActivityMessagePushBinding((ConstraintLayout) rootView, viewFindChildViewById, viewFindChildViewById2, group, recyclerView, qSettingItemWithClickSystem, qSettingItemWithClickSystem2, layoutTitleBarBindingBind, textView);
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
