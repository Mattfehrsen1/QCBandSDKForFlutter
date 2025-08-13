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

/* loaded from: classes3.dex */
public final class ItemOutcomingTextMessageBinding implements ViewBinding {
    public final FlexboxLayout bubble4;
    public final TextView messageText;
    public final TextView messageTime;
    private final RelativeLayout rootView;

    private ItemOutcomingTextMessageBinding(RelativeLayout rootView, FlexboxLayout bubble4, TextView messageText, TextView messageTime) {
        this.rootView = rootView;
        this.bubble4 = bubble4;
        this.messageText = messageText;
        this.messageTime = messageTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemOutcomingTextMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemOutcomingTextMessageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_outcoming_text_message, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemOutcomingTextMessageBinding bind(View rootView) {
        int i = R.id.bubble_4;
        FlexboxLayout flexboxLayout = (FlexboxLayout) ViewBindings.findChildViewById(rootView, R.id.bubble_4);
        if (flexboxLayout != null) {
            i = R.id.messageText;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageText);
            if (textView != null) {
                i = R.id.messageTime;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.messageTime);
                if (textView2 != null) {
                    return new ItemOutcomingTextMessageBinding((RelativeLayout) rootView, flexboxLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
