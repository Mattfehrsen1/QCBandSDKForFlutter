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
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;

/* loaded from: classes3.dex */
public final class RecycleviewItemGpsDetailBinding implements ViewBinding {
    public final QSportItemView detail0;
    public final QSportItemView detail1;
    public final QSportItemView detail2;
    public final Group groupSportDetail;
    public final ImageView imageClick;
    public final ConstraintLayout itemLayout;
    public final TextView line1;
    private final ConstraintLayout rootView;
    public final TextView sportTypeImage;
    public final QSportItemView totalDays;
    public final TextView tvGpsStartTime;
    public final TextView tvTitle1;

    private RecycleviewItemGpsDetailBinding(ConstraintLayout rootView, QSportItemView detail0, QSportItemView detail1, QSportItemView detail2, Group groupSportDetail, ImageView imageClick, ConstraintLayout itemLayout, TextView line1, TextView sportTypeImage, QSportItemView totalDays, TextView tvGpsStartTime, TextView tvTitle1) {
        this.rootView = rootView;
        this.detail0 = detail0;
        this.detail1 = detail1;
        this.detail2 = detail2;
        this.groupSportDetail = groupSportDetail;
        this.imageClick = imageClick;
        this.itemLayout = itemLayout;
        this.line1 = line1;
        this.sportTypeImage = sportTypeImage;
        this.totalDays = totalDays;
        this.tvGpsStartTime = tvGpsStartTime;
        this.tvTitle1 = tvTitle1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemGpsDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemGpsDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_gps_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemGpsDetailBinding bind(View rootView) {
        int i = R.id.detail_0;
        QSportItemView qSportItemView = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.detail_0);
        if (qSportItemView != null) {
            i = R.id.detail_1;
            QSportItemView qSportItemView2 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.detail_1);
            if (qSportItemView2 != null) {
                i = R.id.detail_2;
                QSportItemView qSportItemView3 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.detail_2);
                if (qSportItemView3 != null) {
                    i = R.id.group_sport_detail;
                    Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.group_sport_detail);
                    if (group != null) {
                        i = R.id.image_click;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_click);
                        if (imageView != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                            i = R.id.line_1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.line_1);
                            if (textView != null) {
                                i = R.id.sport_type_image;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.sport_type_image);
                                if (textView2 != null) {
                                    i = R.id.total_days;
                                    QSportItemView qSportItemView4 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_days);
                                    if (qSportItemView4 != null) {
                                        i = R.id.tv_gps_start_time;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_gps_start_time);
                                        if (textView3 != null) {
                                            i = R.id.tv_title_1;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_1);
                                            if (textView4 != null) {
                                                return new RecycleviewItemGpsDetailBinding(constraintLayout, qSportItemView, qSportItemView2, qSportItemView3, group, imageView, constraintLayout, textView, textView2, qSportItemView4, textView3, textView4);
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
