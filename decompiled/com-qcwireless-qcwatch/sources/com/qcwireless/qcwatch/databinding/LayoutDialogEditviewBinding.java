package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutDialogEditviewBinding implements ViewBinding {
    public final TextView btnSave;
    public final EditText nameEdt;
    private final LinearLayout rootView;

    private LayoutDialogEditviewBinding(LinearLayout rootView, TextView btnSave, EditText nameEdt) {
        this.rootView = rootView;
        this.btnSave = btnSave;
        this.nameEdt = nameEdt;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogEditviewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogEditviewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_editview, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogEditviewBinding bind(View rootView) {
        int i = R.id.btn_save;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_save);
        if (textView != null) {
            i = R.id.name_edt;
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.name_edt);
            if (editText != null) {
                return new LayoutDialogEditviewBinding((LinearLayout) rootView, textView, editText);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
