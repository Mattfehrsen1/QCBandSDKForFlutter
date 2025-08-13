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
public final class RecycleviewItemMsgPushBinding implements ViewBinding {
    public final ImageView imgSoft;
    public final TextView rcvSoftwareName;
    public final ImageView rightSwitch;
    private final ConstraintLayout rootView;

    private RecycleviewItemMsgPushBinding(ConstraintLayout rootView, ImageView imgSoft, TextView rcvSoftwareName, ImageView rightSwitch) {
        this.rootView = rootView;
        this.imgSoft = imgSoft;
        this.rcvSoftwareName = rcvSoftwareName;
        this.rightSwitch = rightSwitch;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemMsgPushBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemMsgPushBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_msg_push, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemMsgPushBinding bind(View rootView) {
        int i = R.id.img_soft;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.img_soft);
        if (imageView != null) {
            i = R.id.rcv_software_name;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_software_name);
            if (textView != null) {
                i = R.id.right_switch;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.right_switch);
                if (imageView2 != null) {
                    return new RecycleviewItemMsgPushBinding((ConstraintLayout) rootView, imageView, textView, imageView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
