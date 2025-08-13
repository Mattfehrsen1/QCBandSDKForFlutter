package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemDialKeyBinding implements ViewBinding {
    public final ConstraintLayout clickCtl;
    public final RecyclerView rcvItem;
    private final ConstraintLayout rootView;
    public final TextView tvRightMore;
    public final TextView tvTitle;

    private RecycleviewItemDialKeyBinding(ConstraintLayout rootView, ConstraintLayout clickCtl, RecyclerView rcvItem, TextView tvRightMore, TextView tvTitle) {
        this.rootView = rootView;
        this.clickCtl = clickCtl;
        this.rcvItem = rcvItem;
        this.tvRightMore = tvRightMore;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemDialKeyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemDialKeyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_dial_key, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemDialKeyBinding bind(View rootView) {
        int i = R.id.click_ctl;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.click_ctl);
        if (constraintLayout != null) {
            i = R.id.rcv_item;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_item);
            if (recyclerView != null) {
                i = R.id.tv_right_more;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_right_more);
                if (textView != null) {
                    i = R.id.tv_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                    if (textView2 != null) {
                        return new RecycleviewItemDialKeyBinding((ConstraintLayout) rootView, constraintLayout, recyclerView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
