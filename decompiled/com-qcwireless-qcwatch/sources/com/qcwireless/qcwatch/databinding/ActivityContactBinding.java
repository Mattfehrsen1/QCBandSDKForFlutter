package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;
import com.qcwireless.qcwatch.ui.base.view.ProgressButton;

/* loaded from: classes3.dex */
public final class ActivityContactBinding implements ViewBinding {
    public final Button btnAddContact;
    public final ProgressButton btnToDevice;
    public final ConstraintLayout cslToDevice;
    public final Group group1;
    public final ImageView imageContact1;
    public final MyRecycleView rcvContact;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvDesc;
    public final TextView tvTitle;
    public final TextView tvTotalContacts;

    private ActivityContactBinding(ConstraintLayout rootView, Button btnAddContact, ProgressButton btnToDevice, ConstraintLayout cslToDevice, Group group1, ImageView imageContact1, MyRecycleView rcvContact, LayoutTitleBarBinding titleBar, TextView tvDesc, TextView tvTitle, TextView tvTotalContacts) {
        this.rootView = rootView;
        this.btnAddContact = btnAddContact;
        this.btnToDevice = btnToDevice;
        this.cslToDevice = cslToDevice;
        this.group1 = group1;
        this.imageContact1 = imageContact1;
        this.rcvContact = rcvContact;
        this.titleBar = titleBar;
        this.tvDesc = tvDesc;
        this.tvTitle = tvTitle;
        this.tvTotalContacts = tvTotalContacts;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityContactBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityContactBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_contact, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityContactBinding bind(View rootView) {
        int i = R.id.btn_add_contact;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_add_contact);
        if (button != null) {
            i = R.id.btn_to_device;
            ProgressButton progressButton = (ProgressButton) ViewBindings.findChildViewById(rootView, R.id.btn_to_device);
            if (progressButton != null) {
                i = R.id.csl_to_device;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_to_device);
                if (constraintLayout != null) {
                    i = R.id.group_1;
                    Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.group_1);
                    if (group != null) {
                        i = R.id.image_contact_1;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_contact_1);
                        if (imageView != null) {
                            i = R.id.rcv_contact;
                            MyRecycleView myRecycleView = (MyRecycleView) ViewBindings.findChildViewById(rootView, R.id.rcv_contact);
                            if (myRecycleView != null) {
                                i = R.id.titleBar;
                                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                if (viewFindChildViewById != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                    i = R.id.tv_desc;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_desc);
                                    if (textView != null) {
                                        i = R.id.tv_title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                        if (textView2 != null) {
                                            i = R.id.tv_total_contacts;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_total_contacts);
                                            if (textView3 != null) {
                                                return new ActivityContactBinding((ConstraintLayout) rootView, button, progressButton, constraintLayout, group, imageView, myRecycleView, layoutTitleBarBindingBind, textView, textView2, textView3);
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
