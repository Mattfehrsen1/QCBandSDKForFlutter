package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;

/* loaded from: classes3.dex */
public final class ActivityMoreSettingBinding implements ViewBinding {
    public final View line1;
    public final QSettingItem qcMoreEcard;
    public final QSettingItem qcMoreFactory;
    public final QSettingItem qcMoreRestart;
    public final QSettingItem qcMoreScreen;
    private final LinearLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityMoreSettingBinding(LinearLayout rootView, View line1, QSettingItem qcMoreEcard, QSettingItem qcMoreFactory, QSettingItem qcMoreRestart, QSettingItem qcMoreScreen, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.line1 = line1;
        this.qcMoreEcard = qcMoreEcard;
        this.qcMoreFactory = qcMoreFactory;
        this.qcMoreRestart = qcMoreRestart;
        this.qcMoreScreen = qcMoreScreen;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMoreSettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMoreSettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_more_setting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMoreSettingBinding bind(View rootView) {
        int i = R.id.line_1;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
        if (viewFindChildViewById != null) {
            i = R.id.qc_more_ecard;
            QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_more_ecard);
            if (qSettingItem != null) {
                i = R.id.qc_more_factory;
                QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_more_factory);
                if (qSettingItem2 != null) {
                    i = R.id.qc_more_restart;
                    QSettingItem qSettingItem3 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_more_restart);
                    if (qSettingItem3 != null) {
                        i = R.id.qc_more_screen;
                        QSettingItem qSettingItem4 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_more_screen);
                        if (qSettingItem4 != null) {
                            i = R.id.titleBar;
                            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                            if (viewFindChildViewById2 != null) {
                                return new ActivityMoreSettingBinding((LinearLayout) rootView, viewFindChildViewById, qSettingItem, qSettingItem2, qSettingItem3, qSettingItem4, LayoutTitleBarBinding.bind(viewFindChildViewById2));
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
