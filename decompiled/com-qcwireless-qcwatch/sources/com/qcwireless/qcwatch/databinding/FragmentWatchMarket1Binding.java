package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;

/* loaded from: classes3.dex */
public final class FragmentWatchMarket1Binding implements ViewBinding {
    public final RecyclerView rcvLocalView;
    public final MyRecycleView rcvNetworkView;
    private final ConstraintLayout rootView;

    private FragmentWatchMarket1Binding(ConstraintLayout rootView, RecyclerView rcvLocalView, MyRecycleView rcvNetworkView) {
        this.rootView = rootView;
        this.rcvLocalView = rcvLocalView;
        this.rcvNetworkView = rcvNetworkView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWatchMarket1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentWatchMarket1Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_watch_market_1, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWatchMarket1Binding bind(View rootView) {
        int i = R.id.rcv_local_view;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_local_view);
        if (recyclerView != null) {
            i = R.id.rcv_network_view;
            MyRecycleView myRecycleView = (MyRecycleView) ViewBindings.findChildViewById(rootView, R.id.rcv_network_view);
            if (myRecycleView != null) {
                return new FragmentWatchMarket1Binding((ConstraintLayout) rootView, recyclerView, myRecycleView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
