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
public final class ActivityCallPhoneNumberInputBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final EditText userPhone;

    private ActivityCallPhoneNumberInputBinding(ConstraintLayout rootView, LayoutTitleBarBinding titleBar, EditText userPhone) {
        this.rootView = rootView;
        this.titleBar = titleBar;
        this.userPhone = userPhone;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCallPhoneNumberInputBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCallPhoneNumberInputBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_call_phone_number_input, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCallPhoneNumberInputBinding bind(View rootView) {
        int i = R.id.titleBar;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
        if (viewFindChildViewById != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_phone);
            if (editText != null) {
                return new ActivityCallPhoneNumberInputBinding((ConstraintLayout) rootView, layoutTitleBarBindingBind, editText);
            }
            i = R.id.user_phone;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
