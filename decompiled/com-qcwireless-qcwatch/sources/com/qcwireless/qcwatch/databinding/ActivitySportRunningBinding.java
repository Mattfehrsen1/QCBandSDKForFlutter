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
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;
import com.qcwireless.qcwatch.ui.base.view.gps.GpsEndView;
import com.qcwireless.qcwatch.ui.base.view.gps.GpsStartView;
import com.qcwireless.qcwatch.ui.base.view.gps.LockView;

/* loaded from: classes3.dex */
public final class ActivitySportRunningBinding implements ViewBinding {
    public final QSportItemView gpsCalorie;
    public final QSportItemView gpsDistance;
    public final QSportItemView gpsHeart;
    public final QSportItemView gpsPace;
    public final QSportItemView gpsStep;
    public final QSportItemView gpsTimes;
    public final ImageView imageBleStatus;
    public final GpsStartView imageContinue;
    public final GpsEndView imageEnd;
    public final ImageView imageScreenLock;
    public final LockView imageStop;
    private final ConstraintLayout rootView;
    public final TextView timeDuration;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvLabelEnd;
    public final TextView tvLabelUnlock;
    public final TextView viewTopOfLayout;

    private ActivitySportRunningBinding(ConstraintLayout rootView, QSportItemView gpsCalorie, QSportItemView gpsDistance, QSportItemView gpsHeart, QSportItemView gpsPace, QSportItemView gpsStep, QSportItemView gpsTimes, ImageView imageBleStatus, GpsStartView imageContinue, GpsEndView imageEnd, ImageView imageScreenLock, LockView imageStop, TextView timeDuration, LayoutTitleBarBinding titleBar, TextView tvLabelEnd, TextView tvLabelUnlock, TextView viewTopOfLayout) {
        this.rootView = rootView;
        this.gpsCalorie = gpsCalorie;
        this.gpsDistance = gpsDistance;
        this.gpsHeart = gpsHeart;
        this.gpsPace = gpsPace;
        this.gpsStep = gpsStep;
        this.gpsTimes = gpsTimes;
        this.imageBleStatus = imageBleStatus;
        this.imageContinue = imageContinue;
        this.imageEnd = imageEnd;
        this.imageScreenLock = imageScreenLock;
        this.imageStop = imageStop;
        this.timeDuration = timeDuration;
        this.titleBar = titleBar;
        this.tvLabelEnd = tvLabelEnd;
        this.tvLabelUnlock = tvLabelUnlock;
        this.viewTopOfLayout = viewTopOfLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySportRunningBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySportRunningBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_sport_running, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySportRunningBinding bind(View rootView) {
        int i = R.id.gps_calorie;
        QSportItemView qSportItemView = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_calorie);
        if (qSportItemView != null) {
            i = R.id.gps_distance;
            QSportItemView qSportItemView2 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_distance);
            if (qSportItemView2 != null) {
                i = R.id.gps_heart;
                QSportItemView qSportItemView3 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_heart);
                if (qSportItemView3 != null) {
                    i = R.id.gps_pace;
                    QSportItemView qSportItemView4 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_pace);
                    if (qSportItemView4 != null) {
                        i = R.id.gps_step;
                        QSportItemView qSportItemView5 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_step);
                        if (qSportItemView5 != null) {
                            i = R.id.gps_times;
                            QSportItemView qSportItemView6 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_times);
                            if (qSportItemView6 != null) {
                                i = R.id.image_ble_status;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_ble_status);
                                if (imageView != null) {
                                    i = R.id.image_continue;
                                    GpsStartView gpsStartView = (GpsStartView) ViewBindings.findChildViewById(rootView, R.id.image_continue);
                                    if (gpsStartView != null) {
                                        i = R.id.image_end;
                                        GpsEndView gpsEndView = (GpsEndView) ViewBindings.findChildViewById(rootView, R.id.image_end);
                                        if (gpsEndView != null) {
                                            i = R.id.image_screen_lock;
                                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_screen_lock);
                                            if (imageView2 != null) {
                                                i = R.id.image_stop;
                                                LockView lockView = (LockView) ViewBindings.findChildViewById(rootView, R.id.image_stop);
                                                if (lockView != null) {
                                                    i = R.id.time_duration;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.time_duration);
                                                    if (textView != null) {
                                                        i = R.id.title_bar;
                                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                                        if (viewFindChildViewById != null) {
                                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                                            i = R.id.tv_label_end;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_label_end);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_label_unlock;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_label_unlock);
                                                                if (textView3 != null) {
                                                                    i = R.id.view_top_of_layout;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.view_top_of_layout);
                                                                    if (textView4 != null) {
                                                                        return new ActivitySportRunningBinding((ConstraintLayout) rootView, qSportItemView, qSportItemView2, qSportItemView3, qSportItemView4, qSportItemView5, qSportItemView6, imageView, gpsStartView, gpsEndView, imageView2, lockView, textView, layoutTitleBarBindingBind, textView2, textView3, textView4);
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
