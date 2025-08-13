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

/* loaded from: classes3.dex */
public final class RecycleviewItemManagerEbookBinding implements ViewBinding {
    public final ImageView bookImage;
    public final ImageView imageSelect;
    public final TextView rcvBookName;
    private final ConstraintLayout rootView;

    private RecycleviewItemManagerEbookBinding(ConstraintLayout rootView, ImageView bookImage, ImageView imageSelect, TextView rcvBookName) {
        this.rootView = rootView;
        this.bookImage = bookImage;
        this.imageSelect = imageSelect;
        this.rcvBookName = rcvBookName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemManagerEbookBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemManagerEbookBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_manager_ebook, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemManagerEbookBinding bind(View rootView) {
        int i = R.id.book_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.book_image);
        if (imageView != null) {
            i = R.id.image_select;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_select);
            if (imageView2 != null) {
                i = R.id.rcv_book_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_book_name);
                if (textView != null) {
                    return new RecycleviewItemManagerEbookBinding((ConstraintLayout) rootView, imageView, imageView2, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
