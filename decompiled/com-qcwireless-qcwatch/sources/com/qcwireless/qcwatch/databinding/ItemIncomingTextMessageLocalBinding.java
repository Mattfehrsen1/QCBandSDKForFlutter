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
public final class ItemIncomingTextMessageLocalBinding implements ViewBinding {
    public final FlexboxLayout bubble1;
    public final TextView messageText;
    public final TextView messageText1;
    public final TextView messageText2;
    public final TextView messageText3;
    public final TextView messageText4;
    public final TextView messageTime;
    public final ShapeImageView messageUserAvatar;
    private final RelativeLayout rootView;
    public final TextView toCs;

    private ItemIncomingTextMessageLocalBinding(RelativeLayout rootView, FlexboxLayout bubble1, TextView messageText, TextView messageText1, TextView messageText2, TextView messageText3, TextView messageText4, TextView messageTime, ShapeImageView messageUserAvatar, TextView toCs) {
        this.rootView = rootView;
        this.bubble1 = bubble1;
        this.messageText = messageText;
        this.messageText1 = messageText1;
        this.messageText2 = messageText2;
        this.messageText3 = messageText3;
        this.messageText4 = messageText4;
        this.messageTime = messageTime;
        this.messageUserAvatar = messageUserAvatar;
        this.toCs = toCs;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemIncomingTextMessageLocalBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemIncomingTextMessageLocalBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_incoming_text_message_local, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemIncomingTextMessageLocalBinding bind(View rootView) {
        int i = R.id.bubble_1;
        FlexboxLayout flexboxLayout = (FlexboxLayout) ViewBindings.findChildViewById(rootView, R.id.bubble_1);
        if (flexboxLayout != null) {
            i = R.id.messageText;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageText);
            if (textView != null) {
                i = R.id.messageText_1;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageText_1);
                if (textView2 != null) {
                    i = R.id.messageText_2;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageText_2);
                    if (textView3 != null) {
                        i = R.id.messageText_3;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageText_3);
                        if (textView4 != null) {
                            i = R.id.messageText_4;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageText_4);
                            if (textView5 != null) {
                                i = R.id.messageTime;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageTime);
                                if (textView6 != null) {
                                    i = R.id.messageUserAvatar;
                                    ShapeImageView shapeImageView = (ShapeImageView) ViewBindings.findChildViewById(rootView, R.id.messageUserAvatar);
                                    if (shapeImageView != null) {
                                        i = R.id.toCs;
                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.toCs);
                                        if (textView7 != null) {
                                            return new ItemIncomingTextMessageLocalBinding((RelativeLayout) rootView, flexboxLayout, textView, textView2, textView3, textView4, textView5, textView6, shapeImageView, textView7);
                                        }
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
