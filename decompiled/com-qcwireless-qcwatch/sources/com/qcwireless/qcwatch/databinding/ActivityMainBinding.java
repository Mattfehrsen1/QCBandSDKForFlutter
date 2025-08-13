package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final View btnDevice;
    public final View btnHealth;
    public final View btnMine;
    public final View btnPlate;
    public final FrameLayout homeContainer;
    public final ImageView ivDevice;
    public final ImageView ivHealth;
    public final ImageView ivMine;
    public final ImageView ivPlate;
    private final LinearLayout rootView;
    public final Group showDial;
    public final TextView tvDevice;
    public final TextView tvHealth;
    public final TextView tvMine;
    public final TextView tvPlate;

    private ActivityMainBinding(LinearLayout rootView, View btnDevice, View btnHealth, View btnMine, View btnPlate, FrameLayout homeContainer, ImageView ivDevice, ImageView ivHealth, ImageView ivMine, ImageView ivPlate, Group showDial, TextView tvDevice, TextView tvHealth, TextView tvMine, TextView tvPlate) {
        this.rootView = rootView;
        this.btnDevice = btnDevice;
        this.btnHealth = btnHealth;
        this.btnMine = btnMine;
        this.btnPlate = btnPlate;
        this.homeContainer = homeContainer;
        this.ivDevice = ivDevice;
        this.ivHealth = ivHealth;
        this.ivMine = ivMine;
        this.ivPlate = ivPlate;
        this.showDial = showDial;
        this.tvDevice = tvDevice;
        this.tvHealth = tvHealth;
        this.tvMine = tvMine;
        this.tvPlate = tvPlate;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View rootView) {
        int i = R.id.btn_device;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.btn_device);
        if (viewFindChildViewById != null) {
            i = R.id.btn_health;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.btn_health);
            if (viewFindChildViewById2 != null) {
                i = R.id.btn_mine;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.btn_mine);
                if (viewFindChildViewById3 != null) {
                    i = R.id.btn_plate;
                    View viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, R.id.btn_plate);
                    if (viewFindChildViewById4 != null) {
                        i = R.id.homeContainer;
                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.homeContainer);
                        if (frameLayout != null) {
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
                                            i = R.id.show_dial;
                                            Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.show_dial);
                                            if (group != null) {
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
                                                                return new ActivityMainBinding((LinearLayout) rootView, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, frameLayout, imageView, imageView2, imageView3, imageView4, group, textView, textView2, textView3, textView4);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
