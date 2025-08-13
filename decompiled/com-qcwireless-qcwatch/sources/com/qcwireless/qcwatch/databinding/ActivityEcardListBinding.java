package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityEcardListBinding implements ViewBinding {
    public final RecyclerView eCardRev;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityEcardListBinding(ConstraintLayout rootView, RecyclerView eCardRev, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.eCardRev = eCardRev;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEcardListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEcardListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ecard_list, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEcardListBinding bind(View rootView) {
        int i = R.id.e_card_rev;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.e_card_rev);
        if (recyclerView != null) {
            i = R.id.title_bar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
            if (viewFindChildViewById != null) {
                return new ActivityEcardListBinding((ConstraintLayout) rootView, recyclerView, LayoutTitleBarBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
