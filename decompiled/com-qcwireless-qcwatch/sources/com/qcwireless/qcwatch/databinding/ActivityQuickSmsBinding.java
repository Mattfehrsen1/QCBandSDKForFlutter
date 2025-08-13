package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityQuickSmsBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final QSettingItemWithClickSystem sms1;
    public final QSettingItemWithClickSystem sms2;
    public final QSettingItemWithClickSystem sms3;
    public final QSettingItemWithClickSystem sms4;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvNote;

    private ActivityQuickSmsBinding(ConstraintLayout rootView, QSettingItemWithClickSystem sms1, QSettingItemWithClickSystem sms2, QSettingItemWithClickSystem sms3, QSettingItemWithClickSystem sms4, LayoutTitleBarBinding titleBar, TextView tvNote) {
        this.rootView = rootView;
        this.sms1 = sms1;
        this.sms2 = sms2;
        this.sms3 = sms3;
        this.sms4 = sms4;
        this.titleBar = titleBar;
        this.tvNote = tvNote;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityQuickSmsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityQuickSmsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_quick_sms, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityQuickSmsBinding bind(View rootView) {
        int i = R.id.sms_1;
        QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.sms_1);
        if (qSettingItemWithClickSystem != null) {
            i = R.id.sms_2;
            QSettingItemWithClickSystem qSettingItemWithClickSystem2 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.sms_2);
            if (qSettingItemWithClickSystem2 != null) {
                i = R.id.sms_3;
                QSettingItemWithClickSystem qSettingItemWithClickSystem3 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.sms_3);
                if (qSettingItemWithClickSystem3 != null) {
                    i = R.id.sms_4;
                    QSettingItemWithClickSystem qSettingItemWithClickSystem4 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.sms_4);
                    if (qSettingItemWithClickSystem4 != null) {
                        i = R.id.titleBar;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                        if (viewFindChildViewById != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                            i = R.id.tv_note;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_note);
                            if (textView != null) {
                                return new ActivityQuickSmsBinding((ConstraintLayout) rootView, qSettingItemWithClickSystem, qSettingItemWithClickSystem2, qSettingItemWithClickSystem3, qSettingItemWithClickSystem4, layoutTitleBarBindingBind, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
