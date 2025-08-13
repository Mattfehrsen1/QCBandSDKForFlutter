package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.flexbox.FlexboxLayout;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.utils.ShapeImageView;

/* loaded from: classes3.dex */
public final class ItemIncomingTextMessageBinding implements ViewBinding {
    public final FlexboxLayout bubble;
    public final TextView messageText;
    public final TextView messageTime;
    public final ShapeImageView messageUserAvatar;
    private final RelativeLayout rootView;

    private ItemIncomingTextMessageBinding(RelativeLayout rootView, FlexboxLayout bubble, TextView messageText, TextView messageTime, ShapeImageView messageUserAvatar) {
        this.rootView = rootView;
        this.bubble = bubble;
        this.messageText = messageText;
        this.messageTime = messageTime;
        this.messageUserAvatar = messageUserAvatar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemIncomingTextMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemIncomingTextMessageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_incoming_text_message, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemIncomingTextMessageBinding bind(View rootView) {
        int i = R.id.bubble;
        FlexboxLayout flexboxLayout = (FlexboxLayout) ViewBindings.findChildViewById(rootView, R.id.bubble);
        if (flexboxLayout != null) {
            i = R.id.messageText;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageText);
            if (textView != null) {
                i = R.id.messageTime;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageTime);
                if (textView2 != null) {
                    i = R.id.messageUserAvatar;
                    ShapeImageView shapeImageView = (ShapeImageView) ViewBindings.findChildViewById(rootView, R.id.messageUserAvatar);
                    if (shapeImageView != null) {
                        return new ItemIncomingTextMessageBinding((RelativeLayout) rootView, flexboxLayout, textView, textView2, shapeImageView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
