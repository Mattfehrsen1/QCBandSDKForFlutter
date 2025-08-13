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
public final class ItemIncomingTextMessageLocalTocsBinding implements ViewBinding {
    public final FlexboxLayout bubble3;
    public final TextView messageTime;
    public final ShapeImageView messageUserAvatar;
    public final TextView qMessageText;
    public final TextView qMessageText1;
    public final TextView qToCs;
    private final RelativeLayout rootView;

    private ItemIncomingTextMessageLocalTocsBinding(RelativeLayout rootView, FlexboxLayout bubble3, TextView messageTime, ShapeImageView messageUserAvatar, TextView qMessageText, TextView qMessageText1, TextView qToCs) {
        this.rootView = rootView;
        this.bubble3 = bubble3;
        this.messageTime = messageTime;
        this.messageUserAvatar = messageUserAvatar;
        this.qMessageText = qMessageText;
        this.qMessageText1 = qMessageText1;
        this.qToCs = qToCs;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemIncomingTextMessageLocalTocsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemIncomingTextMessageLocalTocsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_incoming_text_message_local_tocs, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemIncomingTextMessageLocalTocsBinding bind(View rootView) {
        int i = R.id.bubble_3;
        FlexboxLayout flexboxLayout = (FlexboxLayout) ViewBindings.findChildViewById(rootView, R.id.bubble_3);
        if (flexboxLayout != null) {
            i = R.id.messageTime;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageTime);
            if (textView != null) {
                i = R.id.messageUserAvatar;
                ShapeImageView shapeImageView = (ShapeImageView) ViewBindings.findChildViewById(rootView, R.id.messageUserAvatar);
                if (shapeImageView != null) {
                    i = R.id.q_messageText;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.q_messageText);
                    if (textView2 != null) {
                        i = R.id.q_messageText_1;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.q_messageText_1);
                        if (textView3 != null) {
                            i = R.id.q_toCs;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.q_toCs);
                            if (textView4 != null) {
                                return new ItemIncomingTextMessageLocalTocsBinding((RelativeLayout) rootView, flexboxLayout, textView, shapeImageView, textView2, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
