package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityBloodPressureDataDetailBinding implements ViewBinding {
    public final ImageView imageSportNoData;
    public final ConstraintLayout noDataView;
    public final RecyclerView rcvBloodPressureDetail;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvDataDate;
    public final TextView tvErrorText;

    private ActivityBloodPressureDataDetailBinding(ConstraintLayout rootView, ImageView imageSportNoData, ConstraintLayout noDataView, RecyclerView rcvBloodPressureDetail, LayoutTitleBarBinding titleBar, TextView tvDataDate, TextView tvErrorText) {
        this.rootView = rootView;
        this.imageSportNoData = imageSportNoData;
        this.noDataView = noDataView;
        this.rcvBloodPressureDetail = rcvBloodPressureDetail;
        this.titleBar = titleBar;
        this.tvDataDate = tvDataDate;
        this.tvErrorText = tvErrorText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBloodPressureDataDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBloodPressureDataDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_blood_pressure_data_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBloodPressureDataDetailBinding bind(View rootView) {
        int i = R.id.image_sport_no_data;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_sport_no_data);
        if (imageView != null) {
            i = R.id.no_data_view;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.no_data_view);
            if (constraintLayout != null) {
                i = R.id.rcv_blood_pressure_detail;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_blood_pressure_detail);
                if (recyclerView != null) {
                    i = R.id.title_bar;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                    if (viewFindChildViewById != null) {
                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                        i = R.id.tv_data_date;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_data_date);
                        if (textView != null) {
                            i = R.id.tv_error_text;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_error_text);
                            if (textView2 != null) {
                                return new ActivityBloodPressureDataDetailBinding((ConstraintLayout) rootView, imageView, constraintLayout, recyclerView, layoutTitleBarBindingBind, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
