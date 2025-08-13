package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import skin.support.widget.SkinCompatCheckBox;

/* loaded from: classes3.dex */
public final class DragItemRcvBinding implements ViewBinding {
    public final ImageView itemListMenuImageView;
    public final SkinCompatCheckBox itemListSwitchCompat;
    public final TextView itemListTextTextView;
    public final View line1;
    private final ConstraintLayout rootView;

    private DragItemRcvBinding(ConstraintLayout rootView, ImageView itemListMenuImageView, SkinCompatCheckBox itemListSwitchCompat, TextView itemListTextTextView, View line1) {
        this.rootView = rootView;
        this.itemListMenuImageView = itemListMenuImageView;
        this.itemListSwitchCompat = itemListSwitchCompat;
        this.itemListTextTextView = itemListTextTextView;
        this.line1 = line1;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DragItemRcvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DragItemRcvBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.drag_item_rcv, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DragItemRcvBinding bind(View rootView) {
        int i = R.id.item_list_menu_imageView;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.item_list_menu_imageView);
        if (imageView != null) {
            i = R.id.item_list_switchCompat;
            SkinCompatCheckBox skinCompatCheckBox = (SkinCompatCheckBox) ViewBindings.findChildViewById(rootView, R.id.item_list_switchCompat);
            if (skinCompatCheckBox != null) {
                i = R.id.item_list_text_textView;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.item_list_text_textView);
                if (textView != null) {
                    i = R.id.line_1;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                    if (viewFindChildViewById != null) {
                        return new DragItemRcvBinding((ConstraintLayout) rootView, imageView, skinCompatCheckBox, textView, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
