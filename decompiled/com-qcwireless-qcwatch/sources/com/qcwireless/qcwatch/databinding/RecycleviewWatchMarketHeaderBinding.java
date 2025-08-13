package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewWatchMarketHeaderBinding implements ViewBinding {
    public final RecyclerView rcvLocalView;
    private final ConstraintLayout rootView;

    private RecycleviewWatchMarketHeaderBinding(ConstraintLayout rootView, RecyclerView rcvLocalView) {
        this.rootView = rootView;
        this.rcvLocalView = rcvLocalView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewWatchMarketHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewWatchMarketHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_watch_market_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewWatchMarketHeaderBinding bind(View rootView) {
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_local_view);
        if (recyclerView != null) {
            return new RecycleviewWatchMarketHeaderBinding((ConstraintLayout) rootView, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.rcv_local_view)));
    }
}
