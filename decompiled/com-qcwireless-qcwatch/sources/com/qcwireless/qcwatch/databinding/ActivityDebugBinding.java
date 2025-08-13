package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityDebugBinding implements ViewBinding {
    public final Button btnCopy;
    public final QSettingItemWithClickSystem qcDebug;
    public final QSettingItemWithClickSystem qcTest;
    private final ConstraintLayout rootView;
    public final TextView text3;
    public final TextView text4;
    public final TextView tvShow;

    private ActivityDebugBinding(ConstraintLayout rootView, Button btnCopy, QSettingItemWithClickSystem qcDebug, QSettingItemWithClickSystem qcTest, TextView text3, TextView text4, TextView tvShow) {
        this.rootView = rootView;
        this.btnCopy = btnCopy;
        this.qcDebug = qcDebug;
        this.qcTest = qcTest;
        this.text3 = text3;
        this.text4 = text4;
        this.tvShow = tvShow;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDebugBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDebugBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_debug, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDebugBinding bind(View rootView) {
        int i = R.id.btn_copy;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_copy);
        if (button != null) {
            i = R.id.qc_debug;
            QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_debug);
            if (qSettingItemWithClickSystem != null) {
                i = R.id.qc_test;
                QSettingItemWithClickSystem qSettingItemWithClickSystem2 = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_test);
                if (qSettingItemWithClickSystem2 != null) {
                    i = R.id.text_3;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_3);
                    if (textView != null) {
                        i = R.id.text_4;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.text_4);
                        if (textView2 != null) {
                            i = R.id.tv_show;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_show);
                            if (textView3 != null) {
                                return new ActivityDebugBinding((ConstraintLayout) rootView, button, qSettingItemWithClickSystem, qSettingItemWithClickSystem2, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
