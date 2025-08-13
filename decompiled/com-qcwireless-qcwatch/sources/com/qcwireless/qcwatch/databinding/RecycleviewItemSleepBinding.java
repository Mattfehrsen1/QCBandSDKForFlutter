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
import com.qcwireless.qcwatch.ui.base.view.QSleepHomeBarChart;

/* loaded from: classes3.dex */
public final class RecycleviewItemSleepBinding implements ViewBinding {
    public final TextView homeSleepDate;
    public final TextView homeSleepH;
    public final TextView homeSleepMin;
    public final TextView homeSleepTitle;
    public final TextView homeSleepValue;
    public final TextView homeSleepValueMin;
    public final ImageView imageNoData;
    public final QSleepHomeBarChart lastSleepView;
    private final ConstraintLayout rootView;

    private RecycleviewItemSleepBinding(ConstraintLayout rootView, TextView homeSleepDate, TextView homeSleepH, TextView homeSleepMin, TextView homeSleepTitle, TextView homeSleepValue, TextView homeSleepValueMin, ImageView imageNoData, QSleepHomeBarChart lastSleepView) {
        this.rootView = rootView;
        this.homeSleepDate = homeSleepDate;
        this.homeSleepH = homeSleepH;
        this.homeSleepMin = homeSleepMin;
        this.homeSleepTitle = homeSleepTitle;
        this.homeSleepValue = homeSleepValue;
        this.homeSleepValueMin = homeSleepValueMin;
        this.imageNoData = imageNoData;
        this.lastSleepView = lastSleepView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemSleepBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemSleepBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_sleep, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemSleepBinding bind(View rootView) {
        int i = R.id.home_sleep_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sleep_date);
        if (textView != null) {
            i = R.id.home_sleep_h;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sleep_h);
            if (textView2 != null) {
                i = R.id.home_sleep_min;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sleep_min);
                if (textView3 != null) {
                    i = R.id.home_sleep_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sleep_title);
                    if (textView4 != null) {
                        i = R.id.home_sleep_value;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sleep_value);
                        if (textView5 != null) {
                            i = R.id.home_sleep_value_min;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_sleep_value_min);
                            if (textView6 != null) {
                                i = R.id.image_no_data;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                                if (imageView != null) {
                                    i = R.id.last_sleep_view;
                                    QSleepHomeBarChart qSleepHomeBarChart = (QSleepHomeBarChart) ViewBindings.findChildViewById(rootView, R.id.last_sleep_view);
                                    if (qSleepHomeBarChart != null) {
                                        return new RecycleviewItemSleepBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, imageView, qSleepHomeBarChart);
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
