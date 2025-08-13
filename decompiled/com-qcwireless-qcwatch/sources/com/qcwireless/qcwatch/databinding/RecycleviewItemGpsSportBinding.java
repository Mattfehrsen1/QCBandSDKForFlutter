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

/* loaded from: classes3.dex */
public final class RecycleviewItemGpsSportBinding implements ViewBinding {
    public final TextView homeGpsDate;
    public final TextView homeGpsDistance;
    public final TextView homeGpsDistanceUnit;
    public final TextView homeGpsTitle;
    public final ImageView imageGps;
    public final ImageView imageNoData;
    private final ConstraintLayout rootView;

    private RecycleviewItemGpsSportBinding(ConstraintLayout rootView, TextView homeGpsDate, TextView homeGpsDistance, TextView homeGpsDistanceUnit, TextView homeGpsTitle, ImageView imageGps, ImageView imageNoData) {
        this.rootView = rootView;
        this.homeGpsDate = homeGpsDate;
        this.homeGpsDistance = homeGpsDistance;
        this.homeGpsDistanceUnit = homeGpsDistanceUnit;
        this.homeGpsTitle = homeGpsTitle;
        this.imageGps = imageGps;
        this.imageNoData = imageNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemGpsSportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemGpsSportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_gps_sport, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemGpsSportBinding bind(View rootView) {
        int i = R.id.home_gps_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_gps_date);
        if (textView != null) {
            i = R.id.home_gps_distance;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_gps_distance);
            if (textView2 != null) {
                i = R.id.home_gps_distance_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_gps_distance_unit);
                if (textView3 != null) {
                    i = R.id.home_gps_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_gps_title);
                    if (textView4 != null) {
                        i = R.id.image_gps;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_gps);
                        if (imageView != null) {
                            i = R.id.image_no_data;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                            if (imageView2 != null) {
                                return new RecycleviewItemGpsSportBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, imageView, imageView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
