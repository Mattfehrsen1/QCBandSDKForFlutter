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
public final class RecycleviewItemManagerMusicBinding implements ViewBinding {
    public final ImageView imageSelect;
    public final ImageView imageShowPop;
    public final View itemLine;
    public final TextView rcvDeviceAddress;
    public final TextView rcvDeviceName;
    private final ConstraintLayout rootView;
    public final TextView tvLyrics;

    private RecycleviewItemManagerMusicBinding(ConstraintLayout rootView, ImageView imageSelect, ImageView imageShowPop, View itemLine, TextView rcvDeviceAddress, TextView rcvDeviceName, TextView tvLyrics) {
        this.rootView = rootView;
        this.imageSelect = imageSelect;
        this.imageShowPop = imageShowPop;
        this.itemLine = itemLine;
        this.rcvDeviceAddress = rcvDeviceAddress;
        this.rcvDeviceName = rcvDeviceName;
        this.tvLyrics = tvLyrics;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemManagerMusicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemManagerMusicBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_manager_music, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemManagerMusicBinding bind(View rootView) {
        int i = R.id.image_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_select);
        if (imageView != null) {
            i = R.id.image_show_pop;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_show_pop);
            if (imageView2 != null) {
                i = R.id.item_line;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.item_line);
                if (viewFindChildViewById != null) {
                    i = R.id.rcv_device_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_address);
                    if (textView != null) {
                        i = R.id.rcv_device_name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_name);
                        if (textView2 != null) {
                            i = R.id.tv_lyrics;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_lyrics);
                            if (textView3 != null) {
                                return new RecycleviewItemManagerMusicBinding((ConstraintLayout) rootView, imageView, imageView2, viewFindChildViewById, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
