package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class SrlClassicsHeaderBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ImageView srlClassicsArrow;
    public final ImageView srlClassicsProgress;
    public final TextView srlClassicsTitle;
    public final TextView srlClassicsUpdate;

    private SrlClassicsHeaderBinding(ConstraintLayout rootView, ImageView srlClassicsArrow, ImageView srlClassicsProgress, TextView srlClassicsTitle, TextView srlClassicsUpdate) {
        this.rootView = rootView;
        this.srlClassicsArrow = srlClassicsArrow;
        this.srlClassicsProgress = srlClassicsProgress;
        this.srlClassicsTitle = srlClassicsTitle;
        this.srlClassicsUpdate = srlClassicsUpdate;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SrlClassicsHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static SrlClassicsHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.srl_classics_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static SrlClassicsHeaderBinding bind(View rootView) {
        int i = R.id.srl_classics_arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.srl_classics_arrow);
        if (imageView != null) {
            i = R.id.srl_classics_progress;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.srl_classics_progress);
            if (imageView2 != null) {
                i = R.id.srl_classics_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.srl_classics_title);
                if (textView != null) {
                    i = R.id.srl_classics_update;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.srl_classics_update);
                    if (textView2 != null) {
                        return new SrlClassicsHeaderBinding((ConstraintLayout) rootView, imageView, imageView2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
