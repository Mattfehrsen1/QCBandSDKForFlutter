package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;

/* loaded from: classes3.dex */
public final class FragmentDayGpsBinding implements ViewBinding {
    public final RecyclerView gpsDetailRcv;
    public final QDateSwitchView qcDateChange;
    private final NestedScrollView rootView;
    public final TextView todayTotalDuring;
    public final QSportItemView totalCal;
    public final QSportItemView totalDistance;
    public final QSportItemView totalTimes;
    public final TextView tvRecent;

    private FragmentDayGpsBinding(NestedScrollView rootView, RecyclerView gpsDetailRcv, QDateSwitchView qcDateChange, TextView todayTotalDuring, QSportItemView totalCal, QSportItemView totalDistance, QSportItemView totalTimes, TextView tvRecent) {
        this.rootView = rootView;
        this.gpsDetailRcv = gpsDetailRcv;
        this.qcDateChange = qcDateChange;
        this.todayTotalDuring = todayTotalDuring;
        this.totalCal = totalCal;
        this.totalDistance = totalDistance;
        this.totalTimes = totalTimes;
        this.tvRecent = tvRecent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static FragmentDayGpsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDayGpsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_day_gps, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDayGpsBinding bind(View rootView) {
        int i = R.id.gps_detail_rcv;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.gps_detail_rcv);
        if (recyclerView != null) {
            i = R.id.qc_date_change;
            QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
            if (qDateSwitchView != null) {
                i = R.id.today_total_during;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.today_total_during);
                if (textView != null) {
                    i = R.id.total_cal;
                    QSportItemView qSportItemView = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_cal);
                    if (qSportItemView != null) {
                        i = R.id.total_distance;
                        QSportItemView qSportItemView2 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_distance);
                        if (qSportItemView2 != null) {
                            i = R.id.total_times;
                            QSportItemView qSportItemView3 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.total_times);
                            if (qSportItemView3 != null) {
                                i = R.id.tv_recent;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_recent);
                                if (textView2 != null) {
                                    return new FragmentDayGpsBinding((NestedScrollView) rootView, recyclerView, qDateSwitchView, textView, qSportItemView, qSportItemView2, qSportItemView3, textView2);
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
