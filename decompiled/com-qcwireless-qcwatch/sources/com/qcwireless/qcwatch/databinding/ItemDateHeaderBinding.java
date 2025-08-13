package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.qcwireless.qcwatch.R;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ItemDateHeaderBinding implements ViewBinding {
    public final TextView messageText;
    private final TextView rootView;

    private ItemDateHeaderBinding(TextView rootView, TextView messageText) {
        this.rootView = rootView;
        this.messageText = messageText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextView getRoot() {
        return this.rootView;
    }

    public static ItemDateHeaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemDateHeaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_date_header, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemDateHeaderBinding bind(View rootView) {
        Objects.requireNonNull(rootView, "rootView");
        TextView textView = (TextView) rootView;
        return new ItemDateHeaderBinding(textView, textView);
    }
}
