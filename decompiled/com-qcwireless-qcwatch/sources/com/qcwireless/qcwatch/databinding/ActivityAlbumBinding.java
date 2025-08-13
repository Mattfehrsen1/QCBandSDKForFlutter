package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityAlbumBinding implements ViewBinding {
    public final Button btnSelectPic;
    public final ImageView imageNoData;
    public final RecyclerView rcvImage;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityAlbumBinding(ConstraintLayout rootView, Button btnSelectPic, ImageView imageNoData, RecyclerView rcvImage, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.btnSelectPic = btnSelectPic;
        this.imageNoData = imageNoData;
        this.rcvImage = rcvImage;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAlbumBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityAlbumBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_album, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityAlbumBinding bind(View rootView) {
        int i = R.id.btn_select_pic;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_select_pic);
        if (button != null) {
            i = R.id.image_no_data;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
            if (imageView != null) {
                i = R.id.rcv_image;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_image);
                if (recyclerView != null) {
                    i = R.id.titleBar;
                    View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                    if (viewFindChildViewById != null) {
                        return new ActivityAlbumBinding((ConstraintLayout) rootView, button, imageView, recyclerView, LayoutTitleBarBinding.bind(viewFindChildViewById));
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
