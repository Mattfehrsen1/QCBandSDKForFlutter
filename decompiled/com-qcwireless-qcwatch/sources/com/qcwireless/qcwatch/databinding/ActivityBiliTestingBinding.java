package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityBiliTestingBinding implements ViewBinding {
    public final Button button;
    public final Button button2;
    private final ConstraintLayout rootView;

    private ActivityBiliTestingBinding(ConstraintLayout rootView, Button button, Button button2) {
        this.rootView = rootView;
        this.button = button;
        this.button2 = button2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBiliTestingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBiliTestingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_bili_testing, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBiliTestingBinding bind(View rootView) {
        int i = R.id.button;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.button);
        if (button != null) {
            i = R.id.button2;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, R.id.button2);
            if (button2 != null) {
                return new ActivityBiliTestingBinding((ConstraintLayout) rootView, button, button2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
