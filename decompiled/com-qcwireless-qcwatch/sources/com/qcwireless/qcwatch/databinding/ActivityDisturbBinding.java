package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick;

/* loaded from: classes3.dex */
public final class ActivityDisturbBinding implements ViewBinding {
    public final Group disturbGroup;
    public final View line1;
    public final QSettingItem qcDisturbEnd;
    public final QSettingItem qcDisturbStart;
    public final QSettingItemWithClick qcDisturbSwitch;
    public final QSettingItem qcSettingDisturbManual;
    private final LinearLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvDisturb;

    private ActivityDisturbBinding(LinearLayout rootView, Group disturbGroup, View line1, QSettingItem qcDisturbEnd, QSettingItem qcDisturbStart, QSettingItemWithClick qcDisturbSwitch, QSettingItem qcSettingDisturbManual, LayoutTitleBarBinding titleBar, TextView tvDisturb) {
        this.rootView = rootView;
        this.disturbGroup = disturbGroup;
        this.line1 = line1;
        this.qcDisturbEnd = qcDisturbEnd;
        this.qcDisturbStart = qcDisturbStart;
        this.qcDisturbSwitch = qcDisturbSwitch;
        this.qcSettingDisturbManual = qcSettingDisturbManual;
        this.titleBar = titleBar;
        this.tvDisturb = tvDisturb;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDisturbBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDisturbBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_disturb, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDisturbBinding bind(View rootView) {
        int i = R.id.disturb_group;
        Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.disturb_group);
        if (group != null) {
            i = R.id.line_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
            if (viewFindChildViewById != null) {
                i = R.id.qc_disturb_end;
                QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_disturb_end);
                if (qSettingItem != null) {
                    i = R.id.qc_disturb_start;
                    QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_disturb_start);
                    if (qSettingItem2 != null) {
                        i = R.id.qc_disturb_switch;
                        QSettingItemWithClick qSettingItemWithClick = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.qc_disturb_switch);
                        if (qSettingItemWithClick != null) {
                            i = R.id.qc_setting_disturb_manual;
                            QSettingItem qSettingItem3 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_disturb_manual);
                            if (qSettingItem3 != null) {
                                i = R.id.titleBar;
                                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                if (viewFindChildViewById2 != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                                    i = R.id.tv_disturb;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_disturb);
                                    if (textView != null) {
                                        return new ActivityDisturbBinding((LinearLayout) rootView, group, viewFindChildViewById, qSettingItem, qSettingItem2, qSettingItemWithClick, qSettingItem3, layoutTitleBarBindingBind, textView);
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
