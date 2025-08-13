package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.gms.maps.MapView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;

/* loaded from: classes3.dex */
public final class ActivityGpsMapBinding implements ViewBinding {
    public final QSportItemView gpsCalorie;
    public final QSportItemView gpsDistance;
    public final QSportItemView gpsTimes;
    public final ImageView imageBack;
    public final MapView mapView;
    private final ConstraintLayout rootView;

    private ActivityGpsMapBinding(ConstraintLayout rootView, QSportItemView gpsCalorie, QSportItemView gpsDistance, QSportItemView gpsTimes, ImageView imageBack, MapView mapView) {
        this.rootView = rootView;
        this.gpsCalorie = gpsCalorie;
        this.gpsDistance = gpsDistance;
        this.gpsTimes = gpsTimes;
        this.imageBack = imageBack;
        this.mapView = mapView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGpsMapBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGpsMapBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_gps_map, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGpsMapBinding bind(View rootView) {
        int i = R.id.gps_calorie;
        QSportItemView qSportItemView = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_calorie);
        if (qSportItemView != null) {
            i = R.id.gps_distance;
            QSportItemView qSportItemView2 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_distance);
            if (qSportItemView2 != null) {
                i = R.id.gps_times;
                QSportItemView qSportItemView3 = (QSportItemView) ViewBindings.findChildViewById(rootView, R.id.gps_times);
                if (qSportItemView3 != null) {
                    i = R.id.image_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_back);
                    if (imageView != null) {
                        i = R.id.map_view;
                        MapView mapView = (MapView) ViewBindings.findChildViewById(rootView, R.id.map_view);
                        if (mapView != null) {
                            return new ActivityGpsMapBinding((ConstraintLayout) rootView, qSportItemView, qSportItemView2, qSportItemView3, imageView, mapView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
