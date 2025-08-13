package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSwitchView;

/* loaded from: classes3.dex */
public final class QcDeviceItemWithClickBinding implements ViewBinding {
    public final ImageView ivLefticon;
    public final ImageView ivRighticon;
    public final AppCompatCheckBox rightcheck;
    public final FrameLayout rightlayout;
    public final QSwitchView rightswitch;
    public final ConstraintLayout rootLayout;
    private final ConstraintLayout rootView;
    public final TextView tvLeftSubText;
    public final TextView tvLefttext;
    public final TextView tvRighttext;

    private QcDeviceItemWithClickBinding(ConstraintLayout rootView, ImageView ivLefticon, ImageView ivRighticon, AppCompatCheckBox rightcheck, FrameLayout rightlayout, QSwitchView rightswitch, ConstraintLayout rootLayout, TextView tvLeftSubText, TextView tvLefttext, TextView tvRighttext) {
        this.rootView = rootView;
        this.ivLefticon = ivLefticon;
        this.ivRighticon = ivRighticon;
        this.rightcheck = rightcheck;
        this.rightlayout = rightlayout;
        this.rightswitch = rightswitch;
        this.rootLayout = rootLayout;
        this.tvLeftSubText = tvLeftSubText;
        this.tvLefttext = tvLefttext;
        this.tvRighttext = tvRighttext;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static QcDeviceItemWithClickBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static QcDeviceItemWithClickBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.qc_device_item_with_click, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static QcDeviceItemWithClickBinding bind(View rootView) {
        int i = R.id.iv_lefticon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_lefticon);
        if (imageView != null) {
            i = R.id.iv_righticon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_righticon);
            if (imageView2 != null) {
                i = R.id.rightcheck;
                AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) ViewBindings.findChildViewById(rootView, R.id.rightcheck);
                if (appCompatCheckBox != null) {
                    i = R.id.rightlayout;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.rightlayout);
                    if (frameLayout != null) {
                        i = R.id.rightswitch;
                        QSwitchView qSwitchView = (QSwitchView) ViewBindings.findChildViewById(rootView, R.id.rightswitch);
                        if (qSwitchView != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                            i = R.id.tv_left_sub_text;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_left_sub_text);
                            if (textView != null) {
                                i = R.id.tv_lefttext;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_lefttext);
                                if (textView2 != null) {
                                    i = R.id.tv_righttext;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_righttext);
                                    if (textView3 != null) {
                                        return new QcDeviceItemWithClickBinding(constraintLayout, imageView, imageView2, appCompatCheckBox, frameLayout, qSwitchView, constraintLayout, textView, textView2, textView3);
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
