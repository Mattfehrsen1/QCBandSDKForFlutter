package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QDateMonthSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;

/* loaded from: classes3.dex */
public final class FragmentMonthSportBinding implements ViewBinding {
    public final RecyclerView expList;
    public final ImageView imageSportNoData;
    public final ConstraintLayout noDataLayout;
    public final QDateMonthSwitchView qcDateChange;
    private final NestedScrollView rootView;
    public final QSportItemView totalCal;
    public final QSportItemView totalDays;
    public final QSportItemView totalTimes;
    public final TextView weekTotal;

    private FragmentMonthSportBinding(NestedScrollView rootView, RecyclerView expList, ImageView imageSportNoData, ConstraintLayout noDataLayout, QDateMonthSwitchView qcDateChange, QSportItemView totalCal, QSportItemView totalDays, QSportItemView totalTimes, TextView weekTotal) {
        this.rootView = rootView;
        this.expList = expList;
        this.imageSportNoData = imageSportNoData;
        this.noDataLayout = noDataLayout;
        this.qcDateChange = qcDateChange;
        this.totalCal = totalCal;
        this.totalDays = totalDays;
        this.totalTimes = totalTimes;
        this.weekTotal = weekTotal;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentMonthSportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentMonthSportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_month_sport, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMonthSportBinding bind(View rootView) {
        int i = R.id.exp_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.exp_list);
        if (recyclerView != null) {
            i = R.id.image_sport_no_data;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_sport_no_data);
            if (imageView != null) {
                i = R.id.no_data_layout;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.no_data_layout);
                if (constraintLayout != null) {
                    i = R.id.qc_date_change;
                    QDateMonthSwitchView qDateMonthSwitchView = (QDateMonthSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                    if (qDateMonthSwitchView != null) {
                        i = R.id.total_cal;
                        QSportItemView qSportItemView = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_cal);
                        if (qSportItemView != null) {
                            i = R.id.total_days;
                            QSportItemView qSportItemView2 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_days);
                            if (qSportItemView2 != null) {
                                i = R.id.total_times;
                                QSportItemView qSportItemView3 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_times);
                                if (qSportItemView3 != null) {
                                    i = R.id.week_total;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.week_total);
                                    if (textView != null) {
                                        return new FragmentMonthSportBinding((NestedScrollView) rootView, recyclerView, imageView, constraintLayout, qDateMonthSwitchView, qSportItemView, qSportItemView2, qSportItemView3, textView);
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
