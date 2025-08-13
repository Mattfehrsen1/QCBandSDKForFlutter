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
import com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartHomeView;

/* loaded from: classes3.dex */
public final class RecycleviewItemBloodSugarBinding implements ViewBinding {
    public final TextView homeSugarDate;
    public final TextView homeSugarTitle;
    public final TextView homeSugarUnit;
    public final TextView homeSugarValue;
    public final QBloodSugarLineChartHomeView homeSugarView;
    public final ImageView imageNoData;
    private final ConstraintLayout rootView;

    private RecycleviewItemBloodSugarBinding(ConstraintLayout rootView, TextView homeSugarDate, TextView homeSugarTitle, TextView homeSugarUnit, TextView homeSugarValue, QBloodSugarLineChartHomeView homeSugarView, ImageView imageNoData) {
        this.rootView = rootView;
        this.homeSugarDate = homeSugarDate;
        this.homeSugarTitle = homeSugarTitle;
        this.homeSugarUnit = homeSugarUnit;
        this.homeSugarValue = homeSugarValue;
        this.homeSugarView = homeSugarView;
        this.imageNoData = imageNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemBloodSugarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemBloodSugarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_blood_sugar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemBloodSugarBinding bind(View rootView) {
        int i = R.id.home_sugar_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sugar_date);
        if (textView != null) {
            i = R.id.home_sugar_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sugar_title);
            if (textView2 != null) {
                i = R.id.home_sugar_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sugar_unit);
                if (textView3 != null) {
                    i = R.id.home_sugar_value;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sugar_value);
                    if (textView4 != null) {
                        i = R.id.home_sugar_view;
                        QBloodSugarLineChartHomeView qBloodSugarLineChartHomeView = (QBloodSugarLineChartHomeView) ViewBindings.findChildViewById(rootView, R.id.home_sugar_view);
                        if (qBloodSugarLineChartHomeView != null) {
                            i = R.id.image_no_data;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                            if (imageView != null) {
                                return new RecycleviewItemBloodSugarBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, qBloodSugarLineChartHomeView, imageView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
