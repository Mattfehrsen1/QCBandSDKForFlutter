package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ItemDialogBinding implements ViewBinding {
    public final TextView address;
    public final TextView name;
    private final LinearLayout rootView;
    public final LinearLayout tv;

    private ItemDialogBinding(LinearLayout rootView, TextView address, TextView name, LinearLayout tv) {
        this.rootView = rootView;
        this.address = address;
        this.name = name;
        this.tv = tv;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemDialogBinding bind(View rootView) {
        int i = R.id.address;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.address);
        if (textView != null) {
            i = R.id.name;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.name);
            if (textView2 != null) {
                LinearLayout linearLayout = (LinearLayout) rootView;
                return new ItemDialogBinding(linearLayout, textView, textView2, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
