package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Space;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ViewMessageInputBinding implements ViewBinding {
    public final ImageButton attachmentButton;
    public final Space attachmentButtonSpace;
    public final EditText messageInput;
    public final ImageButton messageSendButton;
    private final View rootView;
    public final Space sendButtonSpace;

    private ViewMessageInputBinding(View rootView, ImageButton attachmentButton, Space attachmentButtonSpace, EditText messageInput, ImageButton messageSendButton, Space sendButtonSpace) {
        this.rootView = rootView;
        this.attachmentButton = attachmentButton;
        this.attachmentButtonSpace = attachmentButtonSpace;
        this.messageInput = messageInput;
        this.messageSendButton = messageSendButton;
        this.sendButtonSpace = sendButtonSpace;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static ViewMessageInputBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        Objects.requireNonNull(parent, "parent");
        inflater.inflate(R.layout.view_message_input, parent);
        return bind(parent);
    }

    public static ViewMessageInputBinding bind(View rootView) {
        int i = R.id.attachmentButton;
        ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(rootView, R.id.attachmentButton);
        if (imageButton != null) {
            i = R.id.attachmentButtonSpace;
            Space space = (Space) ViewBindings.findChildViewById(rootView, R.id.attachmentButtonSpace);
            if (space != null) {
                i = R.id.messageInput;
                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.messageInput);
                if (editText != null) {
                    i = R.id.messageSendButton;
                    ImageButton imageButton2 = (ImageButton) ViewBindings.findChildViewById(rootView, R.id.messageSendButton);
                    if (imageButton2 != null) {
                        i = R.id.sendButtonSpace;
                        Space space2 = (Space) ViewBindings.findChildViewById(rootView, R.id.sendButtonSpace);
                        if (space2 != null) {
                            return new ViewMessageInputBinding(rootView, imageButton, space, editText, imageButton2, space2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
