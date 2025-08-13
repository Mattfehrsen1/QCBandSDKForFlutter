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
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;

/* loaded from: classes3.dex */
public final class FragmentDaySportBinding implements ViewBinding {
    public final ImageView imageSportNoData;
    public final ConstraintLayout noDataLayout;
    public final QDateSwitchView qcDateChange;
    private final NestedScrollView rootView;
    public final RecyclerView sportDetailRcv;
    public final QSportItemView totalCal;
    public final QSportItemView totalDays;
    public final QSportItemView totalTimes;
    public final TextView tvInfo;
    public final TextView tvRecent;
    public final TextView weekTotal;

    private FragmentDaySportBinding(NestedScrollView rootView, ImageView imageSportNoData, ConstraintLayout noDataLayout, QDateSwitchView qcDateChange, RecyclerView sportDetailRcv, QSportItemView totalCal, QSportItemView totalDays, QSportItemView totalTimes, TextView tvInfo, TextView tvRecent, TextView weekTotal) {
        this.rootView = rootView;
        this.imageSportNoData = imageSportNoData;
        this.noDataLayout = noDataLayout;
        this.qcDateChange = qcDateChange;
        this.sportDetailRcv = sportDetailRcv;
        this.totalCal = totalCal;
        this.totalDays = totalDays;
        this.totalTimes = totalTimes;
        this.tvInfo = tvInfo;
        this.tvRecent = tvRecent;
        this.weekTotal = weekTotal;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentDaySportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDaySportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_day_sport, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDaySportBinding bind(View rootView) {
        int i = R.id.image_sport_no_data;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_sport_no_data);
        if (imageView != null) {
            i = R.id.no_data_layout;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.no_data_layout);
            if (constraintLayout != null) {
                i = R.id.qc_date_change;
                QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                if (qDateSwitchView != null) {
                    i = R.id.sport_detail_rcv;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.sport_detail_rcv);
                    if (recyclerView != null) {
                        i = R.id.total_cal;
                        QSportItemView qSportItemView = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_cal);
                        if (qSportItemView != null) {
                            i = R.id.total_days;
                            QSportItemView qSportItemView2 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_days);
                            if (qSportItemView2 != null) {
                                i = R.id.total_times;
                                QSportItemView qSportItemView3 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_times);
                                if (qSportItemView3 != null) {
                                    i = R.id.tv_info;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info);
                                    if (textView != null) {
                                        i = R.id.tv_recent;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_recent);
                                        if (textView2 != null) {
                                            i = R.id.week_total;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.week_total);
                                            if (textView3 != null) {
                                                return new FragmentDaySportBinding((NestedScrollView) rootView, imageView, constraintLayout, qDateSwitchView, recyclerView, qSportItemView, qSportItemView2, qSportItemView3, textView, textView2, textView3);
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
