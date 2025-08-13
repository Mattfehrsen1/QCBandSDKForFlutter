package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemSportBinding implements ViewBinding {
    public final Button btnSportGo;
    public final TextView homeStepDate;
    public final TextView homeStepDistance;
    public final TextView homeStepDistanceUnit;
    public final TextView homeStepTitle;
    public final ImageView imageNoData;
    public final ImageView imageRun;
    private final ConstraintLayout rootView;

    private RecycleviewItemSportBinding(ConstraintLayout rootView, Button btnSportGo, TextView homeStepDate, TextView homeStepDistance, TextView homeStepDistanceUnit, TextView homeStepTitle, ImageView imageNoData, ImageView imageRun) {
        this.rootView = rootView;
        this.btnSportGo = btnSportGo;
        this.homeStepDate = homeStepDate;
        this.homeStepDistance = homeStepDistance;
        this.homeStepDistanceUnit = homeStepDistanceUnit;
        this.homeStepTitle = homeStepTitle;
        this.imageNoData = imageNoData;
        this.imageRun = imageRun;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemSportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemSportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_sport, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemSportBinding bind(View rootView) {
        int i = R.id.btn_sport_go;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_sport_go);
        if (button != null) {
            i = R.id.home_step_date;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_step_date);
            if (textView != null) {
                i = R.id.home_step_distance;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_step_distance);
                if (textView2 != null) {
                    i = R.id.home_step_distance_unit;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_step_distance_unit);
                    if (textView3 != null) {
                        i = R.id.home_step_title;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_step_title);
                        if (textView4 != null) {
                            i = R.id.image_no_data;
                            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                            if (imageView != null) {
                                i = R.id.image_run;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_run);
                                if (imageView2 != null) {
                                    return new RecycleviewItemSportBinding((ConstraintLayout) rootView, button, textView, textView2, textView3, textView4, imageView, imageView2);
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
