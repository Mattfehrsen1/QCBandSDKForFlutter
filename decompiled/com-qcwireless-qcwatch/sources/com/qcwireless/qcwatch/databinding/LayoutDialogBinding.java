package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.LoadingView;

/* loaded from: classes3.dex */
public final class LayoutDialogBinding implements ViewBinding {
    public final LoadingView loadView;
    private final FrameLayout rootView;

    private LayoutDialogBinding(FrameLayout rootView, LoadingView loadView) {
        this.rootView = rootView;
        this.loadView = loadView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogBinding bind(View rootView) {
        LoadingView loadingView = (LoadingView) ViewBindings.findChildViewById(rootView, R.id.loadView);
        if (loadingView != null) {
            return new LayoutDialogBinding((FrameLayout) rootView, loadingView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.loadView)));
    }
}
