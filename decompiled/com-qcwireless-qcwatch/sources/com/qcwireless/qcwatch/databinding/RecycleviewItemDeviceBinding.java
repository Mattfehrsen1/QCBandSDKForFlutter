package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemDeviceBinding implements ViewBinding {
    public final View itemLine;
    public final TextView rcvDeviceAddress;
    public final TextView rcvDeviceName;
    private final ConstraintLayout rootView;

    private RecycleviewItemDeviceBinding(ConstraintLayout rootView, View itemLine, TextView rcvDeviceAddress, TextView rcvDeviceName) {
        this.rootView = rootView;
        this.itemLine = itemLine;
        this.rcvDeviceAddress = rcvDeviceAddress;
        this.rcvDeviceName = rcvDeviceName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemDeviceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_device, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemDeviceBinding bind(View rootView) {
        int i = R.id.item_line;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.item_line);
        if (viewFindChildViewById != null) {
            i = R.id.rcv_device_address;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_address);
            if (textView != null) {
                i = R.id.rcv_device_name;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_name);
                if (textView2 != null) {
                    return new RecycleviewItemDeviceBinding((ConstraintLayout) rootView, viewFindChildViewById, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
