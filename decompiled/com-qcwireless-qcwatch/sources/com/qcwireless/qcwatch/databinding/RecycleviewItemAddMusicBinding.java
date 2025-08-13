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
public final class RecycleviewItemAddMusicBinding implements ViewBinding {
    public final ImageView imageSelect;
    public final View itemLine;
    public final TextView keyWord;
    public final TextView rcvDeviceAddress;
    public final TextView rcvDeviceName;
    private final ConstraintLayout rootView;

    private RecycleviewItemAddMusicBinding(ConstraintLayout rootView, ImageView imageSelect, View itemLine, TextView keyWord, TextView rcvDeviceAddress, TextView rcvDeviceName) {
        this.rootView = rootView;
        this.imageSelect = imageSelect;
        this.itemLine = itemLine;
        this.keyWord = keyWord;
        this.rcvDeviceAddress = rcvDeviceAddress;
        this.rcvDeviceName = rcvDeviceName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemAddMusicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemAddMusicBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_add_music, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemAddMusicBinding bind(View rootView) {
        int i = R.id.image_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_select);
        if (imageView != null) {
            i = R.id.item_line;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.item_line);
            if (viewFindChildViewById != null) {
                i = R.id.key_word;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.key_word);
                if (textView != null) {
                    i = R.id.rcv_device_address;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_address);
                    if (textView2 != null) {
                        i = R.id.rcv_device_name;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_name);
                        if (textView3 != null) {
                            return new RecycleviewItemAddMusicBinding((ConstraintLayout) rootView, imageView, viewFindChildViewById, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
