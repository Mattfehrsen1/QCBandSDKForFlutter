package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class MusicPopLayout1Binding implements ViewBinding {
    public final ConstraintLayout rootLayout;
    private final ConstraintLayout rootView;
    public final TextView tv1;
    public final TextView tv2;

    private MusicPopLayout1Binding(ConstraintLayout rootView, ConstraintLayout rootLayout, TextView tv1, TextView tv2) {
        this.rootView = rootView;
        this.rootLayout = rootLayout;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static MusicPopLayout1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static MusicPopLayout1Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.music_pop_layout_1, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static MusicPopLayout1Binding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.tv_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
        if (textView != null) {
            i = R.id.tv_2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
            if (textView2 != null) {
                return new MusicPopLayout1Binding(constraintLayout, constraintLayout, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
