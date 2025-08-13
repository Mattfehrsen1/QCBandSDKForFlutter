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
public final class ActivityTest1Binding implements ViewBinding {
    public final TextView bleConnect;
    public final TextView bleDisconnect;
    public final TextView bleScan;
    public final TextView info;
    private final ConstraintLayout rootView;

    private ActivityTest1Binding(ConstraintLayout rootView, TextView bleConnect, TextView bleDisconnect, TextView bleScan, TextView info) {
        this.rootView = rootView;
        this.bleConnect = bleConnect;
        this.bleDisconnect = bleDisconnect;
        this.bleScan = bleScan;
        this.info = info;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTest1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTest1Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_test1, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTest1Binding bind(View rootView) {
        int i = R.id.ble_connect;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.ble_connect);
        if (textView != null) {
            i = R.id.ble_disconnect;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.ble_disconnect);
            if (textView2 != null) {
                i = R.id.ble_scan;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.ble_scan);
                if (textView3 != null) {
                    i = R.id.info;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.info);
                    if (textView4 != null) {
                        return new ActivityTest1Binding((ConstraintLayout) rootView, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
