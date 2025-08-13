package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class QcAppbackgroundTextBinding implements ViewBinding {
    public final TextView appDes;
    public final TextView appTitle;
    public final TextView btnAppSetting;
    private final ConstraintLayout rootView;

    private QcAppbackgroundTextBinding(ConstraintLayout rootView, TextView appDes, TextView appTitle, TextView btnAppSetting) {
        this.rootView = rootView;
        this.appDes = appDes;
        this.appTitle = appTitle;
        this.btnAppSetting = btnAppSetting;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static QcAppbackgroundTextBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static QcAppbackgroundTextBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.qc_appbackground_text, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static QcAppbackgroundTextBinding bind(View rootView) {
        int i = R.id.app_des;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_des);
        if (textView != null) {
            i = R.id.app_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.app_title);
            if (textView2 != null) {
                i = R.id.btn_app_setting;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_app_setting);
                if (textView3 != null) {
                    return new QcAppbackgroundTextBinding((ConstraintLayout) rootView, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
