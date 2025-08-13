package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.skin.QSkinItemView;

/* loaded from: classes3.dex */
public final class RecycleviewItemSkinBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final QSkinItemView skinItem;

    private RecycleviewItemSkinBinding(ConstraintLayout rootView, QSkinItemView skinItem) {
        this.rootView = rootView;
        this.skinItem = skinItem;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemSkinBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemSkinBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_skin, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemSkinBinding bind(View rootView) {
        QSkinItemView qSkinItemView = (QSkinItemView) ViewBindings.findChildViewById(rootView, R.id.skin_item);
        if (qSkinItemView != null) {
            return new RecycleviewItemSkinBinding((ConstraintLayout) rootView, qSkinItemView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.skin_item)));
    }
}
