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
public final class RecycleviewItemECardBinding implements ViewBinding {
    public final ImageView imgSoft;
    public final View line1;
    public final TextView rcvSoftwareName;
    public final ImageView rightSwitch;
    private final ConstraintLayout rootView;

    private RecycleviewItemECardBinding(ConstraintLayout rootView, ImageView imgSoft, View line1, TextView rcvSoftwareName, ImageView rightSwitch) {
        this.rootView = rootView;
        this.imgSoft = imgSoft;
        this.line1 = line1;
        this.rcvSoftwareName = rcvSoftwareName;
        this.rightSwitch = rightSwitch;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemECardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemECardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_e_card, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemECardBinding bind(View rootView) {
        int i = R.id.img_soft;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.img_soft);
        if (imageView != null) {
            i = R.id.line_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
            if (viewFindChildViewById != null) {
                i = R.id.rcv_software_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_software_name);
                if (textView != null) {
                    i = R.id.right_switch;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.right_switch);
                    if (imageView2 != null) {
                        return new RecycleviewItemECardBinding((ConstraintLayout) rootView, imageView, viewFindChildViewById, textView, imageView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
