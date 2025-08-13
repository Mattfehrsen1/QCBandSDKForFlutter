package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.qcwireless.qcwatch.R;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class LayoutBottomdialogBinding implements ViewBinding {
    public final ConstraintLayout bottomLayout;
    private final ConstraintLayout rootView;

    private LayoutBottomdialogBinding(ConstraintLayout rootView, ConstraintLayout bottomLayout) {
        this.rootView = rootView;
        this.bottomLayout = bottomLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutBottomdialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutBottomdialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_bottomdialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutBottomdialogBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        return new LayoutBottomdialogBinding(constraintLayout, constraintLayout);
    }
}
