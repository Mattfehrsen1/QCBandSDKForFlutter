package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleMsgItemBinding implements ViewBinding {
    public final LinearLayout leftLayout;
    public final TextView receivedMsg;
    public final LinearLayout rightLayout;
    private final LinearLayout rootView;
    public final TextView sendMsg;

    private RecycleMsgItemBinding(LinearLayout rootView, LinearLayout leftLayout, TextView receivedMsg, LinearLayout rightLayout, TextView sendMsg) {
        this.rootView = rootView;
        this.leftLayout = leftLayout;
        this.receivedMsg = receivedMsg;
        this.rightLayout = rightLayout;
        this.sendMsg = sendMsg;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static RecycleMsgItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleMsgItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycle_msg_item, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleMsgItemBinding bind(View rootView) {
        int i = R.id.left_layout;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.left_layout);
        if (linearLayout != null) {
            i = R.id.received_msg;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.received_msg);
            if (textView != null) {
                i = R.id.right_layout;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.right_layout);
                if (linearLayout2 != null) {
                    i = R.id.send_msg;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.send_msg);
                    if (textView2 != null) {
                        return new RecycleMsgItemBinding((LinearLayout) rootView, linearLayout, textView, linearLayout2, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
