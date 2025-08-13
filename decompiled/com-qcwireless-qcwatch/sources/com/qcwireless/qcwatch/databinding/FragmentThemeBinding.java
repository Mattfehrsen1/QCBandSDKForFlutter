package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class FragmentThemeBinding implements ViewBinding {
    public final Button btnTryAgain;
    public final ConstraintLayout ctlNoNetwork;
    public final RecyclerView rcvTypeList;
    private final ConstraintLayout rootView;
    public final TextView tvNoNetwork;

    private FragmentThemeBinding(ConstraintLayout rootView, Button btnTryAgain, ConstraintLayout ctlNoNetwork, RecyclerView rcvTypeList, TextView tvNoNetwork) {
        this.rootView = rootView;
        this.btnTryAgain = btnTryAgain;
        this.ctlNoNetwork = ctlNoNetwork;
        this.rcvTypeList = rcvTypeList;
        this.tvNoNetwork = tvNoNetwork;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentThemeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentThemeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_theme, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentThemeBinding bind(View rootView) {
        int i = R.id.btn_try_again;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_try_again);
        if (button != null) {
            i = R.id.ctl_no_network;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_no_network);
            if (constraintLayout != null) {
                i = R.id.rcv_type_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_type_list);
                if (recyclerView != null) {
                    i = R.id.tv_no_network;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_no_network);
                    if (textView != null) {
                        return new FragmentThemeBinding((ConstraintLayout) rootView, button, constraintLayout, recyclerView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
