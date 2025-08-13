package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class PopCommonBinding implements ViewBinding {
    public final LinearLayout mCommonRootLayout;
    public final RecyclerView mRvCommon;
    private final LinearLayout rootView;

    private PopCommonBinding(LinearLayout rootView, LinearLayout mCommonRootLayout, RecyclerView mRvCommon) {
        this.rootView = rootView;
        this.mCommonRootLayout = mCommonRootLayout;
        this.mRvCommon = mRvCommon;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PopCommonBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static PopCommonBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.pop_common, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static PopCommonBinding bind(View rootView) {
        LinearLayout linearLayout = (LinearLayout) rootView;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.mRvCommon);
        if (recyclerView != null) {
            return new PopCommonBinding(linearLayout, linearLayout, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.mRvCommon)));
    }
}
