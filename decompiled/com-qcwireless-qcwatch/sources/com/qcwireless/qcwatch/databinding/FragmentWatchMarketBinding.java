package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;

/* loaded from: classes3.dex */
public final class FragmentWatchMarketBinding implements ViewBinding {
    public final MyRecycleView rcvNetworkView;
    private final ConstraintLayout rootView;

    private FragmentWatchMarketBinding(ConstraintLayout rootView, MyRecycleView rcvNetworkView) {
        this.rootView = rootView;
        this.rcvNetworkView = rcvNetworkView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWatchMarketBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentWatchMarketBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_watch_market, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWatchMarketBinding bind(View rootView) {
        MyRecycleView myRecycleView = (MyRecycleView) ViewBindings.findChildViewById(rootView, R.id.rcv_network_view);
        if (myRecycleView != null) {
            return new FragmentWatchMarketBinding((ConstraintLayout) rootView, myRecycleView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.rcv_network_view)));
    }
}
