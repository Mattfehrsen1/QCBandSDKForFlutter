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
public final class ItemIncomingTextMessageLocalQudaoBinding implements ViewBinding {
    public final FlexboxLayout bubble2;
    public final TextView messageTime;
    public final ShapeImageView messageUserAvatar;
    public final TextView qudaoMessageText;
    public final TextView qudaoMessageText1;
    public final TextView qudaoMessageText2;
    public final TextView qudaoMessageText3;
    public final TextView qudaoToCs;
    private final RelativeLayout rootView;

    private ItemIncomingTextMessageLocalQudaoBinding(RelativeLayout rootView, FlexboxLayout bubble2, TextView messageTime, ShapeImageView messageUserAvatar, TextView qudaoMessageText, TextView qudaoMessageText1, TextView qudaoMessageText2, TextView qudaoMessageText3, TextView qudaoToCs) {
        this.rootView = rootView;
        this.bubble2 = bubble2;
        this.messageTime = messageTime;
        this.messageUserAvatar = messageUserAvatar;
        this.qudaoMessageText = qudaoMessageText;
        this.qudaoMessageText1 = qudaoMessageText1;
        this.qudaoMessageText2 = qudaoMessageText2;
        this.qudaoMessageText3 = qudaoMessageText3;
        this.qudaoToCs = qudaoToCs;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemIncomingTextMessageLocalQudaoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemIncomingTextMessageLocalQudaoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_incoming_text_message_local_qudao, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemIncomingTextMessageLocalQudaoBinding bind(View rootView) {
        int i = R.id.bubble_2;
        FlexboxLayout flexboxLayout = (FlexboxLayout) ViewBindings.findChildViewById(rootView, R.id.bubble_2);
        if (flexboxLayout != null) {
            i = R.id.messageTime;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageTime);
            if (textView != null) {
                i = R.id.messageUserAvatar;
                ShapeImageView shapeImageView = (ShapeImageView) ViewBindings.findChildViewById(rootView, R.id.messageUserAvatar);
                if (shapeImageView != null) {
                    i = R.id.qudao_messageText;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.qudao_messageText);
                    if (textView2 != null) {
                        i = R.id.qudao_messageText_1;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.qudao_messageText_1);
                        if (textView3 != null) {
                            i = R.id.qudao_messageText_2;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.qudao_messageText_2);
                            if (textView4 != null) {
                                i = R.id.qudao_messageText_3;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.qudao_messageText_3);
                                if (textView5 != null) {
                                    i = R.id.qudao_toCs;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.qudao_toCs);
                                    if (textView6 != null) {
                                        return new ItemIncomingTextMessageLocalQudaoBinding((RelativeLayout) rootView, flexboxLayout, textView, shapeImageView, textView2, textView3, textView4, textView5, textView6);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
