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
public final class ActivityThirdPathSyncBinding implements ViewBinding {
    public final TextView btnAuthor;
    public final View line1;
    public final QSettingItemWithClickSystem qcGoogleFitSync;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvGoogleFit;
    public final TextView userPrivacy;

    private ActivityThirdPathSyncBinding(ConstraintLayout rootView, TextView btnAuthor, View line1, QSettingItemWithClickSystem qcGoogleFitSync, LayoutTitleBarBinding titleBar, TextView tvGoogleFit, TextView userPrivacy) {
        this.rootView = rootView;
        this.btnAuthor = btnAuthor;
        this.line1 = line1;
        this.qcGoogleFitSync = qcGoogleFitSync;
        this.titleBar = titleBar;
        this.tvGoogleFit = tvGoogleFit;
        this.userPrivacy = userPrivacy;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityThirdPathSyncBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityThirdPathSyncBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_third_path_sync, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityThirdPathSyncBinding bind(View rootView) {
        int i = R.id.btn_author;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_author);
        if (textView != null) {
            i = R.id.line_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
            if (viewFindChildViewById != null) {
                i = R.id.qc_google_fit_sync;
                QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_google_fit_sync);
                if (qSettingItemWithClickSystem != null) {
                    i = R.id.title_bar;
                    View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                    if (viewFindChildViewById2 != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                        i = R.id.tv_google_fit;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_google_fit);
                        if (textView2 != null) {
                            i = R.id.user_privacy;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_privacy);
                            if (textView3 != null) {
                                return new ActivityThirdPathSyncBinding((ConstraintLayout) rootView, textView, viewFindChildViewById, qSettingItemWithClickSystem, layoutTitleBarBindingBind, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
