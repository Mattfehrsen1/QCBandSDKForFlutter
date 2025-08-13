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
import com.qcwireless.qcwatch.ui.base.view.QHomeMenstruationView;

/* loaded from: classes3.dex */
public final class RecycleviewItemMenstruationBinding implements ViewBinding {
    public final TextView homeBoDate;
    public final TextView homeBoTitle;
    public final TextView homeBoUnit;
    public final TextView homeBoValue;
    public final ImageView imageNoData;
    public final QHomeMenstruationView qmsView;
    private final ConstraintLayout rootView;

    private RecycleviewItemMenstruationBinding(ConstraintLayout rootView, TextView homeBoDate, TextView homeBoTitle, TextView homeBoUnit, TextView homeBoValue, ImageView imageNoData, QHomeMenstruationView qmsView) {
        this.rootView = rootView;
        this.homeBoDate = homeBoDate;
        this.homeBoTitle = homeBoTitle;
        this.homeBoUnit = homeBoUnit;
        this.homeBoValue = homeBoValue;
        this.imageNoData = imageNoData;
        this.qmsView = qmsView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemMenstruationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemMenstruationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_menstruation, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemMenstruationBinding bind(View rootView) {
        int i = R.id.home_bo_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bo_date);
        if (textView != null) {
            i = R.id.home_bo_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bo_title);
            if (textView2 != null) {
                i = R.id.home_bo_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bo_unit);
                if (textView3 != null) {
                    i = R.id.home_bo_value;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_bo_value);
                    if (textView4 != null) {
                        i = R.id.image_no_data;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                        if (imageView != null) {
                            i = R.id.qms_view;
                            QHomeMenstruationView qHomeMenstruationView = (QHomeMenstruationView) ViewBindings.findChildViewById(rootView, R.id.qms_view);
                            if (qHomeMenstruationView != null) {
                                return new RecycleviewItemMenstruationBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, imageView, qHomeMenstruationView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
