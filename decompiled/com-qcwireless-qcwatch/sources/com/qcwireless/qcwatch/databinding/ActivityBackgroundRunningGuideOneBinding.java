package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityBackgroundRunningGuideOneBinding implements ViewBinding {
    public final ImageView icon1;
    public final ImageView icon2;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvGoSetting;
    public final TextView tvText1;
    public final TextView tvText2;
    public final TextView tvText3;
    public final TextView tvText4;

    private ActivityBackgroundRunningGuideOneBinding(ConstraintLayout rootView, ImageView icon1, ImageView icon2, LayoutTitleBarBinding titleBar, TextView tvGoSetting, TextView tvText1, TextView tvText2, TextView tvText3, TextView tvText4) {
        this.rootView = rootView;
        this.icon1 = icon1;
        this.icon2 = icon2;
        this.titleBar = titleBar;
        this.tvGoSetting = tvGoSetting;
        this.tvText1 = tvText1;
        this.tvText2 = tvText2;
        this.tvText3 = tvText3;
        this.tvText4 = tvText4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBackgroundRunningGuideOneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBackgroundRunningGuideOneBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_background_running_guide_one, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBackgroundRunningGuideOneBinding bind(View rootView) {
        int i = R.id.icon_1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.icon_1);
        if (imageView != null) {
            i = R.id.icon_2;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.icon_2);
            if (imageView2 != null) {
                i = R.id.title_bar;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                if (viewFindChildViewById != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = R.id.tv_go_setting;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_go_setting);
                    if (textView != null) {
                        i = R.id.tv_text_1;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_1);
                        if (textView2 != null) {
                            i = R.id.tv_text_2;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_2);
                            if (textView3 != null) {
                                i = R.id.tv_text_3;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_3);
                                if (textView4 != null) {
                                    i = R.id.tv_text_4;
                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_4);
                                    if (textView5 != null) {
                                        return new ActivityBackgroundRunningGuideOneBinding((ConstraintLayout) rootView, imageView, imageView2, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5);
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
