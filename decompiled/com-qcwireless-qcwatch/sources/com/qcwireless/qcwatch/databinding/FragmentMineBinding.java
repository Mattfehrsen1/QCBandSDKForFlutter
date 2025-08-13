package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.CircleImageView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick;

/* loaded from: classes3.dex */
public final class FragmentMineBinding implements ViewBinding {
    public final Group loginStatusGroup;
    public final QSettingItem qcAboutUs;
    public final QSettingItemWithClick qcBgRunning;
    public final QSettingItem qcCs;
    public final QSettingItem qcFeedback;
    public final QSettingItem qcGoalSetting;
    public final QSettingItem qcHelp;
    public final QSettingItem qcSettingHealthAnalysis;
    public final QSettingItem qcSkin;
    public final QSettingItem qcThird;
    public final QSettingItem qcUserProfile;
    private final ConstraintLayout rootView;
    public final TextView tvNoLogin;
    public final ConstraintLayout userCenter;
    public final ConstraintLayout userCenterAnother;
    public final ImageView userIcon;
    public final CircleImageView userIconCenter;
    public final TextView userId;
    public final TextView userIdCenter;
    public final TextView userNickName;
    public final TextView userNickNameCenter;
    public final TextView viewCenter;

    private FragmentMineBinding(ConstraintLayout rootView, Group loginStatusGroup, QSettingItem qcAboutUs, QSettingItemWithClick qcBgRunning, QSettingItem qcCs, QSettingItem qcFeedback, QSettingItem qcGoalSetting, QSettingItem qcHelp, QSettingItem qcSettingHealthAnalysis, QSettingItem qcSkin, QSettingItem qcThird, QSettingItem qcUserProfile, TextView tvNoLogin, ConstraintLayout userCenter, ConstraintLayout userCenterAnother, ImageView userIcon, CircleImageView userIconCenter, TextView userId, TextView userIdCenter, TextView userNickName, TextView userNickNameCenter, TextView viewCenter) {
        this.rootView = rootView;
        this.loginStatusGroup = loginStatusGroup;
        this.qcAboutUs = qcAboutUs;
        this.qcBgRunning = qcBgRunning;
        this.qcCs = qcCs;
        this.qcFeedback = qcFeedback;
        this.qcGoalSetting = qcGoalSetting;
        this.qcHelp = qcHelp;
        this.qcSettingHealthAnalysis = qcSettingHealthAnalysis;
        this.qcSkin = qcSkin;
        this.qcThird = qcThird;
        this.qcUserProfile = qcUserProfile;
        this.tvNoLogin = tvNoLogin;
        this.userCenter = userCenter;
        this.userCenterAnother = userCenterAnother;
        this.userIcon = userIcon;
        this.userIconCenter = userIconCenter;
        this.userId = userId;
        this.userIdCenter = userIdCenter;
        this.userNickName = userNickName;
        this.userNickNameCenter = userNickNameCenter;
        this.viewCenter = viewCenter;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMineBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentMineBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_mine, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMineBinding bind(View rootView) {
        int i = R.id.login_status_group;
        Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.login_status_group);
        if (group != null) {
            i = R.id.qc_about_us;
            QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_about_us);
            if (qSettingItem != null) {
                i = R.id.qc_bg_running;
                QSettingItemWithClick qSettingItemWithClick = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.qc_bg_running);
                if (qSettingItemWithClick != null) {
                    i = R.id.qc_cs;
                    QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_cs);
                    if (qSettingItem2 != null) {
                        i = R.id.qc_feedback;
                        QSettingItem qSettingItem3 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_feedback);
                        if (qSettingItem3 != null) {
                            i = R.id.qc_goal_setting;
                            QSettingItem qSettingItem4 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_goal_setting);
                            if (qSettingItem4 != null) {
                                i = R.id.qc_help;
                                QSettingItem qSettingItem5 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_help);
                                if (qSettingItem5 != null) {
                                    i = R.id.qc_setting_health_analysis;
                                    QSettingItem qSettingItem6 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_setting_health_analysis);
                                    if (qSettingItem6 != null) {
                                        i = R.id.qc_skin;
                                        QSettingItem qSettingItem7 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_skin);
                                        if (qSettingItem7 != null) {
                                            i = R.id.qc_third;
                                            QSettingItem qSettingItem8 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_third);
                                            if (qSettingItem8 != null) {
                                                i = R.id.qc_user_profile;
                                                QSettingItem qSettingItem9 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_user_profile);
                                                if (qSettingItem9 != null) {
                                                    i = R.id.tv_no_login;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_no_login);
                                                    if (textView != null) {
                                                        i = R.id.user_center;
                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.user_center);
                                                        if (constraintLayout != null) {
                                                            i = R.id.user_center_another;
                                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.user_center_another);
                                                            if (constraintLayout2 != null) {
                                                                i = R.id.user_icon;
                                                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.user_icon);
                                                                if (imageView != null) {
                                                                    i = R.id.user_icon_center;
                                                                    CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, R.id.user_icon_center);
                                                                    if (circleImageView != null) {
                                                                        i = R.id.user_id;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_id);
                                                                        if (textView2 != null) {
                                                                            i = R.id.user_id_center;
                                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_id_center);
                                                                            if (textView3 != null) {
                                                                                i = R.id.user_nick_name;
                                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_nick_name);
                                                                                if (textView4 != null) {
                                                                                    i = R.id.user_nick_name_center;
                                                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.user_nick_name_center);
                                                                                    if (textView5 != null) {
                                                                                        i = R.id.view_center;
                                                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.view_center);
                                                                                        if (textView6 != null) {
                                                                                            return new FragmentMineBinding((ConstraintLayout) rootView, group, qSettingItem, qSettingItemWithClick, qSettingItem2, qSettingItem3, qSettingItem4, qSettingItem5, qSettingItem6, qSettingItem7, qSettingItem8, qSettingItem9, textView, constraintLayout, constraintLayout2, imageView, circleImageView, textView2, textView3, textView4, textView5, textView6);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
