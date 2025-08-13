package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;
import com.qcwireless.qcwatch.ui.base.view.pullrefresh.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* loaded from: classes3.dex */
public final class FragmentHealthyBinding implements ViewBinding {
    public final ClassicsHeader classHeader;
    public final View healthyBottom1;
    public final MyRecycleView healthyCardRcv;
    public final TextView healthyTop1;
    private final ConstraintLayout rootView;
    public final SmartRefreshLayout syncRefresh;

    private FragmentHealthyBinding(ConstraintLayout rootView, ClassicsHeader classHeader, View healthyBottom1, MyRecycleView healthyCardRcv, TextView healthyTop1, SmartRefreshLayout syncRefresh) {
        this.rootView = rootView;
        this.classHeader = classHeader;
        this.healthyBottom1 = healthyBottom1;
        this.healthyCardRcv = healthyCardRcv;
        this.healthyTop1 = healthyTop1;
        this.syncRefresh = syncRefresh;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentHealthyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentHealthyBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_healthy, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentHealthyBinding bind(View rootView) {
        int i = R.id.class_header;
        ClassicsHeader classicsHeader = (ClassicsHeader) ViewBindings.findChildViewById(rootView, R.id.class_header);
        if (classicsHeader != null) {
            i = R.id.healthy_bottom_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.healthy_bottom_1);
            if (viewFindChildViewById != null) {
                i = R.id.healthy_card_rcv;
                MyRecycleView myRecycleView = (MyRecycleView) ViewBindings.findChildViewById(rootView, R.id.healthy_card_rcv);
                if (myRecycleView != null) {
                    i = R.id.healthy_top_1;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.healthy_top_1);
                    if (textView != null) {
                        i = R.id.sync_refresh;
                        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, R.id.sync_refresh);
                        if (smartRefreshLayout != null) {
                            return new FragmentHealthyBinding((ConstraintLayout) rootView, classicsHeader, viewFindChildViewById, myRecycleView, textView, smartRefreshLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
