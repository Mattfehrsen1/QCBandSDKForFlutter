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
public final class RecycleviewItemOnekeyBinding implements ViewBinding {
    public final TextView homeOneKeyDate;
    public final TextView homeOneKeyTitle;
    public final TextView homeOneKeyUnit;
    public final TextView homeOneKeyValue;
    public final ImageView homeOneKeyView;
    public final ImageView imageNoData;
    private final ConstraintLayout rootView;

    private RecycleviewItemOnekeyBinding(ConstraintLayout rootView, TextView homeOneKeyDate, TextView homeOneKeyTitle, TextView homeOneKeyUnit, TextView homeOneKeyValue, ImageView homeOneKeyView, ImageView imageNoData) {
        this.rootView = rootView;
        this.homeOneKeyDate = homeOneKeyDate;
        this.homeOneKeyTitle = homeOneKeyTitle;
        this.homeOneKeyUnit = homeOneKeyUnit;
        this.homeOneKeyValue = homeOneKeyValue;
        this.homeOneKeyView = homeOneKeyView;
        this.imageNoData = imageNoData;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemOnekeyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemOnekeyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_onekey, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemOnekeyBinding bind(View rootView) {
        int i = R.id.home_one_key_date;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_one_key_date);
        if (textView != null) {
            i = R.id.home_one_key_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_one_key_title);
            if (textView2 != null) {
                i = R.id.home_one_key_unit;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_one_key_unit);
                if (textView3 != null) {
                    i = R.id.home_one_key_value;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.home_one_key_value);
                    if (textView4 != null) {
                        i = R.id.home_one_key_view;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.home_one_key_view);
                        if (imageView != null) {
                            i = R.id.image_no_data;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                            if (imageView2 != null) {
                                return new RecycleviewItemOnekeyBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, imageView, imageView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
