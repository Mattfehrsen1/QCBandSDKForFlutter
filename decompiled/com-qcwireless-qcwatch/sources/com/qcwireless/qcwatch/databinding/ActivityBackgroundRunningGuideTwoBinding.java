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
public final class ActivityBackgroundRunningGuideTwoBinding implements ViewBinding {
    public final ImageView image1Right;
    public final ImageView image2Right;
    public final ImageView image3Right;
    public final ImageView image4Right;
    public final ImageView imageBatteryRight;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvAutoBattery;
    public final TextView tvAutoBatteryTitle;
    public final TextView tvAutoDelete;
    public final TextView tvAutoDeleteTitle;
    public final TextView tvAutoStart;
    public final TextView tvBatteryWhite;
    public final TextView tvBatteryWhiteSub;
    public final TextView tvNotification;
    public final TextView tvStatusText1;
    public final TextView tvStatusText2;
    public final TextView tvStatusText3;
    public final TextView tvStatusText4;
    public final TextView tvStatusText5;

    private ActivityBackgroundRunningGuideTwoBinding(ConstraintLayout rootView, ImageView image1Right, ImageView image2Right, ImageView image3Right, ImageView image4Right, ImageView imageBatteryRight, LayoutTitleBarBinding titleBar, TextView tvAutoBattery, TextView tvAutoBatteryTitle, TextView tvAutoDelete, TextView tvAutoDeleteTitle, TextView tvAutoStart, TextView tvBatteryWhite, TextView tvBatteryWhiteSub, TextView tvNotification, TextView tvStatusText1, TextView tvStatusText2, TextView tvStatusText3, TextView tvStatusText4, TextView tvStatusText5) {
        this.rootView = rootView;
        this.image1Right = image1Right;
        this.image2Right = image2Right;
        this.image3Right = image3Right;
        this.image4Right = image4Right;
        this.imageBatteryRight = imageBatteryRight;
        this.titleBar = titleBar;
        this.tvAutoBattery = tvAutoBattery;
        this.tvAutoBatteryTitle = tvAutoBatteryTitle;
        this.tvAutoDelete = tvAutoDelete;
        this.tvAutoDeleteTitle = tvAutoDeleteTitle;
        this.tvAutoStart = tvAutoStart;
        this.tvBatteryWhite = tvBatteryWhite;
        this.tvBatteryWhiteSub = tvBatteryWhiteSub;
        this.tvNotification = tvNotification;
        this.tvStatusText1 = tvStatusText1;
        this.tvStatusText2 = tvStatusText2;
        this.tvStatusText3 = tvStatusText3;
        this.tvStatusText4 = tvStatusText4;
        this.tvStatusText5 = tvStatusText5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBackgroundRunningGuideTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBackgroundRunningGuideTwoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_background_running_guide_two, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBackgroundRunningGuideTwoBinding bind(View rootView) {
        int i = R.id.image_1_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_1_right);
        if (imageView != null) {
            i = R.id.image_2_right;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_2_right);
            if (imageView2 != null) {
                i = R.id.image_3_right;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_3_right);
                if (imageView3 != null) {
                    i = R.id.image_4_right;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_4_right);
                    if (imageView4 != null) {
                        i = R.id.image_battery_right;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_battery_right);
                        if (imageView5 != null) {
                            i = R.id.title_bar;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                            if (viewFindChildViewById != null) {
                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                i = R.id.tv_auto_battery;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_auto_battery);
                                if (textView != null) {
                                    i = R.id.tv_auto_battery_title;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_auto_battery_title);
                                    if (textView2 != null) {
                                        i = R.id.tv_auto_delete;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_auto_delete);
                                        if (textView3 != null) {
                                            i = R.id.tv_auto_delete_title;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_auto_delete_title);
                                            if (textView4 != null) {
                                                i = R.id.tv_auto_start;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_auto_start);
                                                if (textView5 != null) {
                                                    i = R.id.tv_battery_white;
                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_battery_white);
                                                    if (textView6 != null) {
                                                        i = R.id.tv_battery_white_sub;
                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_battery_white_sub);
                                                        if (textView7 != null) {
                                                            i = R.id.tv_notification;
                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_notification);
                                                            if (textView8 != null) {
                                                                i = R.id.tv_status_text_1;
                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_status_text_1);
                                                                if (textView9 != null) {
                                                                    i = R.id.tv_status_text_2;
                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_status_text_2);
                                                                    if (textView10 != null) {
                                                                        i = R.id.tv_status_text_3;
                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_status_text_3);
                                                                        if (textView11 != null) {
                                                                            i = R.id.tv_status_text_4;
                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_status_text_4);
                                                                            if (textView12 != null) {
                                                                                i = R.id.tv_status_text_5;
                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_status_text_5);
                                                                                if (textView13 != null) {
                                                                                    return new ActivityBackgroundRunningGuideTwoBinding((ConstraintLayout) rootView, imageView, imageView2, imageView3, imageView4, imageView5, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13);
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
