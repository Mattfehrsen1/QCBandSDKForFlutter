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
public final class ActivityGpsBinding implements ViewBinding {
    public final ImageView btnGpsGo;
    public final ImageView imageSignal;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvGpsUnit;
    public final TextView tvSportHistory;
    public final TextView tvTotalDistance;

    private ActivityGpsBinding(ConstraintLayout rootView, ImageView btnGpsGo, ImageView imageSignal, LayoutTitleBarBinding titleBar, TextView tvGpsUnit, TextView tvSportHistory, TextView tvTotalDistance) {
        this.rootView = rootView;
        this.btnGpsGo = btnGpsGo;
        this.imageSignal = imageSignal;
        this.titleBar = titleBar;
        this.tvGpsUnit = tvGpsUnit;
        this.tvSportHistory = tvSportHistory;
        this.tvTotalDistance = tvTotalDistance;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGpsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGpsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_gps, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGpsBinding bind(View rootView) {
        int i = R.id.btn_gps_go;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_gps_go);
        if (imageView != null) {
            i = R.id.image_signal;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_signal);
            if (imageView2 != null) {
                i = R.id.title_bar;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                if (viewFindChildViewById != null) {
                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                    i = R.id.tv_gps_unit;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_gps_unit);
                    if (textView != null) {
                        i = R.id.tv_sport_history;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_history);
                        if (textView2 != null) {
                            i = R.id.tv_total_distance;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_total_distance);
                            if (textView3 != null) {
                                return new ActivityGpsBinding((ConstraintLayout) rootView, imageView, imageView2, layoutTitleBarBindingBind, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
