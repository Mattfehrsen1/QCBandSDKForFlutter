package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityDrivingBinding implements ViewBinding {
    public final FrameLayout fragmentContent;
    public final FrameLayout mapContainer;
    private final FrameLayout rootView;

    private ActivityDrivingBinding(FrameLayout rootView, FrameLayout fragmentContent, FrameLayout mapContainer) {
        this.rootView = rootView;
        this.fragmentContent = fragmentContent;
        this.mapContainer = mapContainer;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDrivingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDrivingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_driving, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDrivingBinding bind(View rootView) {
        int i = R.id.fragment_content;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.fragment_content);
        if (frameLayout != null) {
            i = R.id.map_container;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.map_container);
            if (frameLayout2 != null) {
                return new ActivityDrivingBinding((FrameLayout) rootView, frameLayout, frameLayout2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
