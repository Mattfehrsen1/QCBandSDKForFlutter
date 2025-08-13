package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;

/* loaded from: classes3.dex */
public final class ActivityUserProfileBinding implements ViewBinding {
    public final TextView btnExit;
    public final LinearLayout layoutProfile;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final QSettingItem userCenterBirthday;
    public final QSettingItem userCenterGender;
    public final QSettingItem userCenterHeight;
    public final QSettingItem userCenterName;
    public final QSettingItem userCenterWeight;
    public final QSettingItem userLogOff;

    private ActivityUserProfileBinding(ConstraintLayout rootView, TextView btnExit, LinearLayout layoutProfile, LayoutTitleBarBinding titleBar, QSettingItem userCenterBirthday, QSettingItem userCenterGender, QSettingItem userCenterHeight, QSettingItem userCenterName, QSettingItem userCenterWeight, QSettingItem userLogOff) {
        this.rootView = rootView;
        this.btnExit = btnExit;
        this.layoutProfile = layoutProfile;
        this.titleBar = titleBar;
        this.userCenterBirthday = userCenterBirthday;
        this.userCenterGender = userCenterGender;
        this.userCenterHeight = userCenterHeight;
        this.userCenterName = userCenterName;
        this.userCenterWeight = userCenterWeight;
        this.userLogOff = userLogOff;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityUserProfileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityUserProfileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_user_profile, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityUserProfileBinding bind(View rootView) {
        int i = R.id.btn_exit;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_exit);
        if (textView != null) {
            i = R.id.layout_profile;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.layout_profile);
            if (linearLayout != null) {
                i = R.id.title_bar;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                if (viewFindChildViewById != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = R.id.user_center_birthday;
                    QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.user_center_birthday);
                    if (qSettingItem != null) {
                        i = R.id.user_center_gender;
                        QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.user_center_gender);
                        if (qSettingItem2 != null) {
                            i = R.id.user_center_height;
                            QSettingItem qSettingItem3 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.user_center_height);
                            if (qSettingItem3 != null) {
                                i = R.id.user_center_name;
                                QSettingItem qSettingItem4 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.user_center_name);
                                if (qSettingItem4 != null) {
                                    i = R.id.user_center_weight;
                                    QSettingItem qSettingItem5 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.user_center_weight);
                                    if (qSettingItem5 != null) {
                                        i = R.id.user_log_off;
                                        QSettingItem qSettingItem6 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.user_log_off);
                                        if (qSettingItem6 != null) {
                                            return new ActivityUserProfileBinding((ConstraintLayout) rootView, textView, linearLayout, layoutTitleBarBindingBind, qSettingItem, qSettingItem2, qSettingItem3, qSettingItem4, qSettingItem5, qSettingItem6);
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
