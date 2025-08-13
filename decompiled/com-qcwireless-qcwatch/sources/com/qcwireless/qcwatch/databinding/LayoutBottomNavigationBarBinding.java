package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutBottomNavigationBarBinding implements ViewBinding {
    public final Group btnDevice;
    public final Group btnHealth;
    public final Group btnMine;
    public final Group btnPlate;
    public final ImageView ivDevice;
    public final ImageView ivHealth;
    public final ImageView ivMine;
    public final ImageView ivPlate;
    private final ConstraintLayout rootView;
    public final TextView tvDevice;
    public final TextView tvHealth;
    public final TextView tvMine;
    public final TextView tvPlate;

    private LayoutBottomNavigationBarBinding(ConstraintLayout rootView, Group btnDevice, Group btnHealth, Group btnMine, Group btnPlate, ImageView ivDevice, ImageView ivHealth, ImageView ivMine, ImageView ivPlate, TextView tvDevice, TextView tvHealth, TextView tvMine, TextView tvPlate) {
        this.rootView = rootView;
        this.btnDevice = btnDevice;
        this.btnHealth = btnHealth;
        this.btnMine = btnMine;
        this.btnPlate = btnPlate;
        this.ivDevice = ivDevice;
        this.ivHealth = ivHealth;
        this.ivMine = ivMine;
        this.ivPlate = ivPlate;
        this.tvDevice = tvDevice;
        this.tvHealth = tvHealth;
        this.tvMine = tvMine;
        this.tvPlate = tvPlate;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutBottomNavigationBarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutBottomNavigationBarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_bottom_navigation_bar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutBottomNavigationBarBinding bind(View rootView) {
        int i = R.id.btn_device;
        Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.btn_device);
        if (group != null) {
            i = R.id.btn_health;
            Group group2 = (Group) ViewBindings.findChildViewById(rootView, R.id.btn_health);
            if (group2 != null) {
                i = R.id.btn_mine;
                Group group3 = (Group) ViewBindings.findChildViewById(rootView, R.id.btn_mine);
                if (group3 != null) {
                    i = R.id.btn_plate;
                    Group group4 = (Group) ViewBindings.findChildViewById(rootView, R.id.btn_plate);
                    if (group4 != null) {
                        i = R.id.iv_device;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_device);
                        if (imageView != null) {
                            i = R.id.iv_health;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_health);
                            if (imageView2 != null) {
                                i = R.id.iv_mine;
                                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_mine);
                                if (imageView3 != null) {
                                    i = R.id.iv_plate;
                                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_plate);
                                    if (imageView4 != null) {
                                        i = R.id.tv_device;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_device);
                                        if (textView != null) {
                                            i = R.id.tv_health;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_health);
                                            if (textView2 != null) {
                                                i = R.id.tv_mine;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_mine);
                                                if (textView3 != null) {
                                                    i = R.id.tv_plate;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_plate);
                                                    if (textView4 != null) {
                                                        return new LayoutBottomNavigationBarBinding((ConstraintLayout) rootView, group, group2, group3, group4, imageView, imageView2, imageView3, imageView4, textView, textView2, textView3, textView4);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
