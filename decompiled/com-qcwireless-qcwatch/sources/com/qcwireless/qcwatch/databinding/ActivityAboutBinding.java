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
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;

/* loaded from: classes3.dex */
public final class ActivityAboutBinding implements ViewBinding {
    public final ImageView appIcon;
    public final View line1;
    public final QSettingItem qcAppCache;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tvAppName;
    public final TextView tvAppVersion;
    public final TextView userAgreement;
    public final TextView userPrivacy;

    private ActivityAboutBinding(ConstraintLayout rootView, ImageView appIcon, View line1, QSettingItem qcAppCache, LayoutTitleBarBinding titleBar, TextView tv1, TextView tv2, TextView tvAppName, TextView tvAppVersion, TextView userAgreement, TextView userPrivacy) {
        this.rootView = rootView;
        this.appIcon = appIcon;
        this.line1 = line1;
        this.qcAppCache = qcAppCache;
        this.titleBar = titleBar;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tvAppName = tvAppName;
        this.tvAppVersion = tvAppVersion;
        this.userAgreement = userAgreement;
        this.userPrivacy = userPrivacy;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAboutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAboutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_about, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAboutBinding bind(View rootView) {
        int i = R.id.app_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.app_icon);
        if (imageView != null) {
            i = R.id.line_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
            if (viewFindChildViewById != null) {
                i = R.id.qc_app_cache;
                QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_app_cache);
                if (qSettingItem != null) {
                    i = R.id.title_bar;
                    View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                    if (viewFindChildViewById2 != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                        i = R.id.tv_1;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
                        if (textView != null) {
                            i = R.id.tv_2;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
                            if (textView2 != null) {
                                i = R.id.tv_app_name;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_app_name);
                                if (textView3 != null) {
                                    i = R.id.tv_app_version;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_app_version);
                                    if (textView4 != null) {
                                        i = R.id.user_agreement;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_agreement);
                                        if (textView5 != null) {
                                            i = R.id.user_privacy;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_privacy);
                                            if (textView6 != null) {
                                                return new ActivityAboutBinding((ConstraintLayout) rootView, imageView, viewFindChildViewById, qSettingItem, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6);
                                            }
                                        }
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
