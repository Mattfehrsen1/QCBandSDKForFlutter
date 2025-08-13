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
import com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartHomeView;

/* loaded from: classes3.dex */
public final class RecycleviewItemBloodPressureBinding implements ViewBinding {
    public final TextView homeBpDate;
    public final TextView homeBpTitle;
    public final TextView homeBpUnit;
    public final TextView homeBpValue;
    public final QBloodPressureChartHomeView homeBpView;
    public final ImageView imageNoData;
    private final ConstraintLayout rootView;

    private RecycleviewItemBloodPressureBinding(ConstraintLayout rootView, TextView homeBpDate, TextView homeBpTitle, TextView homeBpUnit, TextView homeBpValue, QBloodPressureChartHomeView homeBpView, ImageView imageNoData) {
        this.rootView = rootView;
        this.homeBpDate = homeBpDate;
        this.homeBpTitle = homeBpTitle;
        this.homeBpUnit = homeBpUnit;
        this.homeBpValue = homeBpValue;
        this.homeBpView = homeBpView;
        this.imageNoData = imageNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemBloodPressureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemBloodPressureBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_blood_pressure, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemBloodPressureBinding bind(View rootView) {
        int i = R.id.home_bp_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bp_date);
        if (textView != null) {
            i = R.id.home_bp_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bp_title);
            if (textView2 != null) {
                i = R.id.home_bp_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bp_unit);
                if (textView3 != null) {
                    i = R.id.home_bp_value;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bp_value);
                    if (textView4 != null) {
                        i = R.id.home_bp_view;
                        QBloodPressureChartHomeView qBloodPressureChartHomeView = (QBloodPressureChartHomeView) ViewBindings.findChildViewById(rootView, R.id.home_bp_view);
                        if (qBloodPressureChartHomeView != null) {
                            i = R.id.image_no_data;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                            if (imageView != null) {
                                return new RecycleviewItemBloodPressureBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, qBloodPressureChartHomeView, imageView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
