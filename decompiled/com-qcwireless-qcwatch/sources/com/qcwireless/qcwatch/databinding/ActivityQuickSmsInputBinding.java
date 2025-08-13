package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityQuickSmsInputBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final EditText userSmsQuick;

    private ActivityQuickSmsInputBinding(ConstraintLayout rootView, LayoutTitleBarBinding titleBar, EditText userSmsQuick) {
        this.rootView = rootView;
        this.titleBar = titleBar;
        this.userSmsQuick = userSmsQuick;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityQuickSmsInputBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityQuickSmsInputBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_quick_sms_input, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityQuickSmsInputBinding bind(View rootView) {
        int i = R.id.titleBar;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
        if (viewFindChildViewById != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_sms_quick);
            if (editText != null) {
                return new ActivityQuickSmsInputBinding((ConstraintLayout) rootView, layoutTitleBarBindingBind, editText);
            }
            i = R.id.user_sms_quick;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
