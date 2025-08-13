package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityRecordListBinding implements ViewBinding {
    public final TextView btnAddMusic;
    public final ConstraintLayout cs2;
    public final ImageView imageNoData;
    public final ConstraintLayout noMusic;
    public final RecyclerView rcvDeviceRecordList;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvTitle2;

    private ActivityRecordListBinding(ConstraintLayout rootView, TextView btnAddMusic, ConstraintLayout cs2, ImageView imageNoData, ConstraintLayout noMusic, RecyclerView rcvDeviceRecordList, LayoutTitleBarBinding titleBar, TextView tvTitle2) {
        this.rootView = rootView;
        this.btnAddMusic = btnAddMusic;
        this.cs2 = cs2;
        this.imageNoData = imageNoData;
        this.noMusic = noMusic;
        this.rcvDeviceRecordList = rcvDeviceRecordList;
        this.titleBar = titleBar;
        this.tvTitle2 = tvTitle2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRecordListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityRecordListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_record_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityRecordListBinding bind(View rootView) {
        int i = R.id.btn_add_music;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_add_music);
        if (textView != null) {
            i = R.id.cs_2;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_2);
            if (constraintLayout != null) {
                i = R.id.image_no_data;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_no_data);
                if (imageView != null) {
                    i = R.id.no_music;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.no_music);
                    if (constraintLayout2 != null) {
                        i = R.id.rcv_device_record_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_record_list);
                        if (recyclerView != null) {
                            i = R.id.titleBar;
                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                            if (viewFindChildViewById != null) {
                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                i = R.id.tv_title_2;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_2);
                                if (textView2 != null) {
                                    return new ActivityRecordListBinding((ConstraintLayout) rootView, textView, constraintLayout, imageView, constraintLayout2, recyclerView, layoutTitleBarBindingBind, textView2);
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
