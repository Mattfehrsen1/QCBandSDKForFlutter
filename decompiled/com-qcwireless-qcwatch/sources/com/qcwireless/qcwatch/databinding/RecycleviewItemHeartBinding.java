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
import com.qcwireless.qcwatch.ui.base.view.QHomeHeartLineChartView;

/* loaded from: classes3.dex */
public final class RecycleviewItemHeartBinding implements ViewBinding {
    public final TextView homeHeartDate;
    public final TextView homeHeartTitle;
    public final TextView homeHeartUnit;
    public final TextView homeHeartValue;
    public final QHomeHeartLineChartView homeHeartView;
    public final ImageView imageNoData;
    private final ConstraintLayout rootView;

    private RecycleviewItemHeartBinding(ConstraintLayout rootView, TextView homeHeartDate, TextView homeHeartTitle, TextView homeHeartUnit, TextView homeHeartValue, QHomeHeartLineChartView homeHeartView, ImageView imageNoData) {
        this.rootView = rootView;
        this.homeHeartDate = homeHeartDate;
        this.homeHeartTitle = homeHeartTitle;
        this.homeHeartUnit = homeHeartUnit;
        this.homeHeartValue = homeHeartValue;
        this.homeHeartView = homeHeartView;
        this.imageNoData = imageNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemHeartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemHeartBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_heart, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemHeartBinding bind(View rootView) {
        int i = R.id.home_heart_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_heart_date);
        if (textView != null) {
            i = R.id.home_heart_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_heart_title);
            if (textView2 != null) {
                i = R.id.home_heart_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_heart_unit);
                if (textView3 != null) {
                    i = R.id.home_heart_value;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_heart_value);
                    if (textView4 != null) {
                        i = R.id.home_heart_view;
                        QHomeHeartLineChartView qHomeHeartLineChartView = (QHomeHeartLineChartView) ViewBindings.findChildViewById(rootView, R.id.home_heart_view);
                        if (qHomeHeartLineChartView != null) {
                            i = R.id.image_no_data;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                            if (imageView != null) {
                                return new RecycleviewItemHeartBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, qHomeHeartLineChartView, imageView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
