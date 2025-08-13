package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.stfalcon.chatkit.messages.MessagesList;

/* loaded from: classes3.dex */
public final class ActivityQcChatBinding implements ViewBinding {
    public final ConstraintLayout chatLayout;
    public final ConstraintLayout csvPicture;
    public final ImageView imageAdd;
    public final TextView imageAdd1;
    public final ImageView imageAlbum;
    public final ImageView imageCamera;
    public final ImageView imageSend;
    public final ConstraintLayout input;
    public final View line1;
    public final EditText messageInput;
    public final MessagesList messagesList;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityQcChatBinding(ConstraintLayout rootView, ConstraintLayout chatLayout, ConstraintLayout csvPicture, ImageView imageAdd, TextView imageAdd1, ImageView imageAlbum, ImageView imageCamera, ImageView imageSend, ConstraintLayout input, View line1, EditText messageInput, MessagesList messagesList, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.chatLayout = chatLayout;
        this.csvPicture = csvPicture;
        this.imageAdd = imageAdd;
        this.imageAdd1 = imageAdd1;
        this.imageAlbum = imageAlbum;
        this.imageCamera = imageCamera;
        this.imageSend = imageSend;
        this.input = input;
        this.line1 = line1;
        this.messageInput = messageInput;
        this.messagesList = messagesList;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityQcChatBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityQcChatBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_qc_chat, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityQcChatBinding bind(View rootView) {
        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
        int i = R.id.csv_picture;
        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csv_picture);
        if (constraintLayout2 != null) {
            i = R.id.image_add;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_add);
            if (imageView != null) {
                i = R.id.image_add_1;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.image_add_1);
                if (textView != null) {
                    i = R.id.image_album;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_album);
                    if (imageView2 != null) {
                        i = R.id.image_camera;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_camera);
                        if (imageView3 != null) {
                            i = R.id.image_send;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_send);
                            if (imageView4 != null) {
                                i = R.id.input;
                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.input);
                                if (constraintLayout3 != null) {
                                    i = R.id.line_1;
                                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                                    if (viewFindChildViewById != null) {
                                        i = R.id.messageInput;
                                        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.messageInput);
                                        if (editText != null) {
                                            i = R.id.messagesList;
                                            MessagesList messagesList = (MessagesList) ViewBindings.findChildViewById(rootView, R.id.messagesList);
                                            if (messagesList != null) {
                                                i = R.id.title_bar;
                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                                if (viewFindChildViewById2 != null) {
                                                    return new ActivityQcChatBinding(constraintLayout, constraintLayout, constraintLayout2, imageView, textView, imageView2, imageView3, imageView4, constraintLayout3, viewFindChildViewById, editText, messagesList, LayoutTitleBarBinding.bind(viewFindChildViewById2));
                                                }
                                            }
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
