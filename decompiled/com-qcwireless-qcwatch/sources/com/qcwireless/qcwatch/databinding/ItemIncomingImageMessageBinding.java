package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.utils.RoundedImageView;
import com.stfalcon.chatkit.utils.ShapeImageView;

/* loaded from: classes3.dex */
public final class ItemIncomingImageMessageBinding implements ViewBinding {
    public final RoundedImageView image;
    public final View imageOverlay;
    public final TextView messageTime;
    public final ShapeImageView messageUserAvatar;
    private final RelativeLayout rootView;

    private ItemIncomingImageMessageBinding(RelativeLayout rootView, RoundedImageView image, View imageOverlay, TextView messageTime, ShapeImageView messageUserAvatar) {
        this.rootView = rootView;
        this.image = image;
        this.imageOverlay = imageOverlay;
        this.messageTime = messageTime;
        this.messageUserAvatar = messageUserAvatar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemIncomingImageMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemIncomingImageMessageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_incoming_image_message, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemIncomingImageMessageBinding bind(View rootView) {
        int i = R.id.image;
        RoundedImageView roundedImageView = (RoundedImageView) ViewBindings.findChildViewById(rootView, R.id.image);
        if (roundedImageView != null) {
            i = R.id.imageOverlay;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.imageOverlay);
            if (viewFindChildViewById != null) {
                i = R.id.messageTime;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageTime);
                if (textView != null) {
                    i = R.id.messageUserAvatar;
                    ShapeImageView shapeImageView = (ShapeImageView) ViewBindings.findChildViewById(rootView, R.id.messageUserAvatar);
                    if (shapeImageView != null) {
                        return new ItemIncomingImageMessageBinding((RelativeLayout) rootView, roundedImageView, viewFindChildViewById, textView, shapeImageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
