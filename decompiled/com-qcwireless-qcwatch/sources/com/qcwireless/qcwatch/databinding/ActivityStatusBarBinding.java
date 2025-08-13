package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityStatusBarBinding implements ViewBinding {
    public final FrameLayout frameLayoutContentPlace;
    private final LinearLayout rootView;
    public final View viewStatusBarPlace;

    private ActivityStatusBarBinding(LinearLayout rootView, FrameLayout frameLayoutContentPlace, View viewStatusBarPlace) {
        this.rootView = rootView;
        this.frameLayoutContentPlace = frameLayoutContentPlace;
        this.viewStatusBarPlace = viewStatusBarPlace;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityStatusBarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityStatusBarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_status_bar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityStatusBarBinding bind(View rootView) {
        int i = R.id.frame_layout_content_place;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.frame_layout_content_place);
        if (frameLayout != null) {
            i = R.id.view_status_bar_place;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.view_status_bar_place);
            if (viewFindChildViewById != null) {
                return new ActivityStatusBarBinding((LinearLayout) rootView, frameLayout, viewFindChildViewById);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
