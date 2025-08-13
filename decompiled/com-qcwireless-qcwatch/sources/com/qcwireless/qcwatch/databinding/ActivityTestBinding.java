package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityTestBinding implements ViewBinding {
    public final Button btn1;
    public final Button btn2;
    public final TextView myTextView1;
    private final ConstraintLayout rootView;

    private ActivityTestBinding(ConstraintLayout rootView, Button btn1, Button btn2, TextView myTextView1) {
        this.rootView = rootView;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.myTextView1 = myTextView1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTestBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityTestBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_test, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityTestBinding bind(View rootView) {
        int i = R.id.btn_1;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_1);
        if (button != null) {
            i = R.id.btn_2;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_2);
            if (button2 != null) {
                i = R.id.my_textView_1;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.my_textView_1);
                if (textView != null) {
                    return new ActivityTestBinding((ConstraintLayout) rootView, button, button2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
