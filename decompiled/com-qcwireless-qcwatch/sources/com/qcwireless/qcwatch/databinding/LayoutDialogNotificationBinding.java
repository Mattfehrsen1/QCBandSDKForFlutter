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
public final class LayoutDialogNotificationBinding implements ViewBinding {
    public final ImageView imageNotification;
    private final ConstraintLayout rootView;
    public final TextView tvConfirm;
    public final TextView tvContent;
    public final TextView tvTitle;

    private LayoutDialogNotificationBinding(ConstraintLayout rootView, ImageView imageNotification, TextView tvConfirm, TextView tvContent, TextView tvTitle) {
        this.rootView = rootView;
        this.imageNotification = imageNotification;
        this.tvConfirm = tvConfirm;
        this.tvContent = tvContent;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogNotificationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogNotificationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_notification, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogNotificationBinding bind(View rootView) {
        int i = R.id.image_notification;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_notification);
        if (imageView != null) {
            i = R.id.tv_confirm;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_confirm);
            if (textView != null) {
                i = R.id.tv_content;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
                if (textView2 != null) {
                    i = R.id.tv_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                    if (textView3 != null) {
                        return new LayoutDialogNotificationBinding((ConstraintLayout) rootView, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
