package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.recycleview.SwipeItemLayout;

/* loaded from: classes3.dex */
public final class ContactItemRcvBinding implements ViewBinding {
    public final Button btnDelete;
    public final ImageView itemListMenuImageView;
    public final TextView itemListTextTextView;
    public final View line1;
    private final SwipeItemLayout rootView;

    private ContactItemRcvBinding(SwipeItemLayout rootView, Button btnDelete, ImageView itemListMenuImageView, TextView itemListTextTextView, View line1) {
        this.rootView = rootView;
        this.btnDelete = btnDelete;
        this.itemListMenuImageView = itemListMenuImageView;
        this.itemListTextTextView = itemListTextTextView;
        this.line1 = line1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public SwipeItemLayout getRoot() {
        return this.rootView;
    }

    public static ContactItemRcvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ContactItemRcvBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.contact_item_rcv, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ContactItemRcvBinding bind(View rootView) {
        int i = R.id.btn_delete;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_delete);
        if (button != null) {
            i = R.id.item_list_menu_imageView;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.item_list_menu_imageView);
            if (imageView != null) {
                i = R.id.item_list_text_textView;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.item_list_text_textView);
                if (textView != null) {
                    i = R.id.line_1;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                    if (viewFindChildViewById != null) {
                        return new ContactItemRcvBinding((SwipeItemLayout) rootView, button, imageView, textView, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
