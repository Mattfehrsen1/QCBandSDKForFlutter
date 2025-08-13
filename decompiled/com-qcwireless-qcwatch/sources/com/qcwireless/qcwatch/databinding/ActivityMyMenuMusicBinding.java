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
public final class ActivityMyMenuMusicBinding implements ViewBinding {
    public final RecyclerView rcvMyMusicList;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;

    private ActivityMyMenuMusicBinding(ConstraintLayout rootView, RecyclerView rcvMyMusicList, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.rcvMyMusicList = rcvMyMusicList;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMyMenuMusicBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMyMenuMusicBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_my_menu_music, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMyMenuMusicBinding bind(View rootView) {
        int i = R.id.rcv_my_music_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_my_music_list);
        if (recyclerView != null) {
            i = R.id.titleBar;
            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
            if (viewFindChildViewById != null) {
                return new ActivityMyMenuMusicBinding((ConstraintLayout) rootView, recyclerView, LayoutTitleBarBinding.bind(viewFindChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
