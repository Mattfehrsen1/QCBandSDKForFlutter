package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;

/* loaded from: classes3.dex */
public final class FragmentDialMarketBinding implements ViewBinding {
    public final Button btnTryAgain;
    public final ConstraintLayout ctlNoDevice;
    public final ConstraintLayout ctlNoNetwork;
    public final MyRecycleView dialCardRcv;
    private final ConstraintLayout rootView;
    public final TextView tvNoDeviceTitle;
    public final TextView tvNoNetwork;

    private FragmentDialMarketBinding(ConstraintLayout rootView, Button btnTryAgain, ConstraintLayout ctlNoDevice, ConstraintLayout ctlNoNetwork, MyRecycleView dialCardRcv, TextView tvNoDeviceTitle, TextView tvNoNetwork) {
        this.rootView = rootView;
        this.btnTryAgain = btnTryAgain;
        this.ctlNoDevice = ctlNoDevice;
        this.ctlNoNetwork = ctlNoNetwork;
        this.dialCardRcv = dialCardRcv;
        this.tvNoDeviceTitle = tvNoDeviceTitle;
        this.tvNoNetwork = tvNoNetwork;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDialMarketBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentDialMarketBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_dial_market, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentDialMarketBinding bind(View rootView) {
        int i = R.id.btn_try_again;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_try_again);
        if (button != null) {
            i = R.id.ctl_no_device;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_no_device);
            if (constraintLayout != null) {
                i = R.id.ctl_no_network;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_no_network);
                if (constraintLayout2 != null) {
                    i = R.id.dial_card_rcv;
                    MyRecycleView myRecycleView = (MyRecycleView) ViewBindings.findChildViewById(rootView, R.id.dial_card_rcv);
                    if (myRecycleView != null) {
                        i = R.id.tv_no_device_title;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_no_device_title);
                        if (textView != null) {
                            i = R.id.tv_no_network;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_no_network);
                            if (textView2 != null) {
                                return new FragmentDialMarketBinding((ConstraintLayout) rootView, button, constraintLayout, constraintLayout2, myRecycleView, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
