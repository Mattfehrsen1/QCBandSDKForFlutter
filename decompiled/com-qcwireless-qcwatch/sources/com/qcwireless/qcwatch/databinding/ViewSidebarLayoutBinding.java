package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView;

/* loaded from: classes3.dex */
public final class ViewSidebarLayoutBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final SideBarSortView sortView;
    public final TextView tvTips;

    private ViewSidebarLayoutBinding(RelativeLayout rootView, SideBarSortView sortView, TextView tvTips) {
        this.rootView = rootView;
        this.sortView = sortView;
        this.tvTips = tvTips;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ViewSidebarLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewSidebarLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.view_sidebar_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewSidebarLayoutBinding bind(View rootView) {
        int i = R.id.sortView;
        SideBarSortView sideBarSortView = (SideBarSortView) ViewBindings.findChildViewById(rootView, R.id.sortView);
        if (sideBarSortView != null) {
            i = R.id.tvTips;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tvTips);
            if (textView != null) {
                return new ViewSidebarLayoutBinding((RelativeLayout) rootView, sideBarSortView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
