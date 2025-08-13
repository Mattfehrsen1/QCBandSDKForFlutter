package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.CircleImageView;

/* loaded from: classes3.dex */
public final class FragmentNickNameBinding implements ViewBinding {
    public final Button btnNext;
    public final View line1;
    private final ConstraintLayout rootView;
    public final TextView tvTitle;
    public final CircleImageView userIconCenter;
    public final EditText userNick;

    private FragmentNickNameBinding(ConstraintLayout rootView, Button btnNext, View line1, TextView tvTitle, CircleImageView userIconCenter, EditText userNick) {
        this.rootView = rootView;
        this.btnNext = btnNext;
        this.line1 = line1;
        this.tvTitle = tvTitle;
        this.userIconCenter = userIconCenter;
        this.userNick = userNick;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentNickNameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentNickNameBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_nick_name, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentNickNameBinding bind(View rootView) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_next);
        if (button != null) {
            i = R.id.line_1;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
            if (viewFindChildViewById != null) {
                i = R.id.tv_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                if (textView != null) {
                    i = R.id.user_icon_center;
                    CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, R.id.user_icon_center);
                    if (circleImageView != null) {
                        i = R.id.user_nick;
                        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.user_nick);
                        if (editText != null) {
                            return new FragmentNickNameBinding((ConstraintLayout) rootView, button, viewFindChildViewById, textView, circleImageView, editText);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
