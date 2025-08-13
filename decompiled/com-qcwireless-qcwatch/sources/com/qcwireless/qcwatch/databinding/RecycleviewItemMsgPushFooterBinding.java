package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class RecycleviewItemMsgPushFooterBinding implements ViewBinding {
    public final QSettingItemWithClickSystem qcOtherSwitch;
    private final ConstraintLayout rootView;

    private RecycleviewItemMsgPushFooterBinding(ConstraintLayout rootView, QSettingItemWithClickSystem qcOtherSwitch) {
        this.rootView = rootView;
        this.qcOtherSwitch = qcOtherSwitch;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemMsgPushFooterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemMsgPushFooterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_msg_push_footer, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemMsgPushFooterBinding bind(View rootView) {
        QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_other_switch);
        if (qSettingItemWithClickSystem != null) {
            return new RecycleviewItemMsgPushFooterBinding((ConstraintLayout) rootView, qSettingItemWithClickSystem);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.qc_other_switch)));
    }
}
