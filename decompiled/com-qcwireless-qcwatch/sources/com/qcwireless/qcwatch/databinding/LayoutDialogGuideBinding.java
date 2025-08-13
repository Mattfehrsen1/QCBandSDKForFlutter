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
public final class LayoutDialogGuideBinding implements ViewBinding {
    public final View line1;
    private final ConstraintLayout rootView;
    public final TextView tvCancel;
    public final TextView tvTakePhoto;
    public final TextView tvTakePicture;

    private LayoutDialogGuideBinding(ConstraintLayout rootView, View line1, TextView tvCancel, TextView tvTakePhoto, TextView tvTakePicture) {
        this.rootView = rootView;
        this.line1 = line1;
        this.tvCancel = tvCancel;
        this.tvTakePhoto = tvTakePhoto;
        this.tvTakePicture = tvTakePicture;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogGuideBinding bind(View rootView) {
        int i = R.id.line_1;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
        if (viewFindChildViewById != null) {
            i = R.id.tv_cancel;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_cancel);
            if (textView != null) {
                i = R.id.tv_take_photo;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_take_photo);
                if (textView2 != null) {
                    i = R.id.tv_take_picture;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_take_picture);
                    if (textView3 != null) {
                        return new LayoutDialogGuideBinding((ConstraintLayout) rootView, viewFindChildViewById, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
