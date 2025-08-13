package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutDialogEditviewMusicBinding implements ViewBinding {
    public final ImageView btnCancel;
    public final ImageView btnSave;
    public final View line;
    public final EditText nameEdt;
    private final ConstraintLayout rootView;

    private LayoutDialogEditviewMusicBinding(ConstraintLayout rootView, ImageView btnCancel, ImageView btnSave, View line, EditText nameEdt) {
        this.rootView = rootView;
        this.btnCancel = btnCancel;
        this.btnSave = btnSave;
        this.line = line;
        this.nameEdt = nameEdt;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogEditviewMusicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogEditviewMusicBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_editview_music, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogEditviewMusicBinding bind(View rootView) {
        int i = R.id.btn_cancel;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_cancel);
        if (imageView != null) {
            i = R.id.btn_save;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_save);
            if (imageView2 != null) {
                i = R.id.line;
                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line);
                if (viewFindChildViewById != null) {
                    i = R.id.name_edt;
                    EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.name_edt);
                    if (editText != null) {
                        return new LayoutDialogEditviewMusicBinding((ConstraintLayout) rootView, imageView, imageView2, viewFindChildViewById, editText);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
