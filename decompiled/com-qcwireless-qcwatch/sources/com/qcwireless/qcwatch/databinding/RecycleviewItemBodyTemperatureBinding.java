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
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineHomeChartView;

/* loaded from: classes3.dex */
public final class RecycleviewItemBodyTemperatureBinding implements ViewBinding {
    public final TextView homeBodyTempDate;
    public final TextView homeBodyTempTitle;
    public final TextView homeBodyTempValue;
    public final TextView homeStepDistanceUnit;
    public final QTemperatureLineHomeChartView homeTemperatureView;
    public final ImageView imageNoData;
    private final ConstraintLayout rootView;

    private RecycleviewItemBodyTemperatureBinding(ConstraintLayout rootView, TextView homeBodyTempDate, TextView homeBodyTempTitle, TextView homeBodyTempValue, TextView homeStepDistanceUnit, QTemperatureLineHomeChartView homeTemperatureView, ImageView imageNoData) {
        this.rootView = rootView;
        this.homeBodyTempDate = homeBodyTempDate;
        this.homeBodyTempTitle = homeBodyTempTitle;
        this.homeBodyTempValue = homeBodyTempValue;
        this.homeStepDistanceUnit = homeStepDistanceUnit;
        this.homeTemperatureView = homeTemperatureView;
        this.imageNoData = imageNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemBodyTemperatureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemBodyTemperatureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_body_temperature, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemBodyTemperatureBinding bind(View rootView) {
        int i = R.id.home_body_temp_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_body_temp_date);
        if (textView != null) {
            i = R.id.home_body_temp_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_body_temp_title);
            if (textView2 != null) {
                i = R.id.home_body_temp_value;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_body_temp_value);
                if (textView3 != null) {
                    i = R.id.home_step_distance_unit;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_step_distance_unit);
                    if (textView4 != null) {
                        i = R.id.home_temperature_view;
                        QTemperatureLineHomeChartView qTemperatureLineHomeChartView = (QTemperatureLineHomeChartView) ViewBindings.findChildViewById(rootView, R.id.home_temperature_view);
                        if (qTemperatureLineHomeChartView != null) {
                            i = R.id.image_no_data;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                            if (imageView != null) {
                                return new RecycleviewItemBodyTemperatureBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, qTemperatureLineHomeChartView, imageView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
