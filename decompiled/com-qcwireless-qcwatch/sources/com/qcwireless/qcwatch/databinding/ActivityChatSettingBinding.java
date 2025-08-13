package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityChatSettingBinding implements ViewBinding {
    public final Button btnConfirm;
    public final LinearLayout ll1;
    public final ConstraintLayout main;
    public final QSettingItemWithClickSystem qcMessageDisturb;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityChatSettingBinding(ConstraintLayout rootView, Button btnConfirm, LinearLayout ll1, ConstraintLayout main, QSettingItemWithClickSystem qcMessageDisturb, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.btnConfirm = btnConfirm;
        this.ll1 = ll1;
        this.main = main;
        this.qcMessageDisturb = qcMessageDisturb;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityChatSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityChatSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_chat_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityChatSettingBinding bind(View rootView) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_confirm);
        if (button != null) {
            i = R.id.ll_1;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.ll_1);
            if (linearLayout != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                i = R.id.qc_message_disturb;
                QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_message_disturb);
                if (qSettingItemWithClickSystem != null) {
                    i = R.id.title_bar;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                    if (viewFindChildViewById != null) {
                        return new ActivityChatSettingBinding(constraintLayout, button, linearLayout, constraintLayout, qSettingItemWithClickSystem, LayoutTitleBarBinding.bind(viewFindChildViewById));
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
