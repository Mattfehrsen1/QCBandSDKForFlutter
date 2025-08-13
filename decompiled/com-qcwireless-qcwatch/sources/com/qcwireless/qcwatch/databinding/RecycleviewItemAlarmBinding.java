package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;
import com.qcwireless.qcwatch.ui.base.view.recycleview.SwipeItemLayout;

/* loaded from: classes3.dex */
public final class RecycleviewItemAlarmBinding implements ViewBinding {
    public final QSettingItemWithClickSystem alarmDetail;
    public final Button btnDelete;
    public final RelativeLayout main;
    private final SwipeItemLayout rootView;

    private RecycleviewItemAlarmBinding(SwipeItemLayout rootView, QSettingItemWithClickSystem alarmDetail, Button btnDelete, RelativeLayout main) {
        this.rootView = rootView;
        this.alarmDetail = alarmDetail;
        this.btnDelete = btnDelete;
        this.main = main;
    }

    @Override // androidx.viewbinding.ViewBinding
    public SwipeItemLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemAlarmBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemAlarmBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_alarm, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemAlarmBinding bind(View rootView) {
        int i = R.id.alarm_detail;
        QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.alarm_detail);
        if (qSettingItemWithClickSystem != null) {
            i = R.id.btn_delete;
            Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_delete);
            if (button != null) {
                i = R.id.main;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.main);
                if (relativeLayout != null) {
                    return new RecycleviewItemAlarmBinding((SwipeItemLayout) rootView, qSettingItemWithClickSystem, button, relativeLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
