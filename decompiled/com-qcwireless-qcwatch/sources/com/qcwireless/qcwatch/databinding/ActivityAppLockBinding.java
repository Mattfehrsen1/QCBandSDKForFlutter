package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.qcwireless.qcwatch.R;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ActivityAppLockBinding implements ViewBinding {
    private final ConstraintLayout rootView;

    private ActivityAppLockBinding(ConstraintLayout rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAppLockBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAppLockBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_app_lock, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAppLockBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        return new ActivityAppLockBinding((ConstraintLayout) rootView);
    }
}
