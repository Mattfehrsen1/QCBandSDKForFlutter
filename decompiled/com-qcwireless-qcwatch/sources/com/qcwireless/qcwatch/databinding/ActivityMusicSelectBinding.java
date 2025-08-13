package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.device.contact.widget.SideBarLayout;

/* loaded from: classes3.dex */
public final class ActivityMusicSelectBinding implements ViewBinding {
    public final ImageView deleteText;
    public final EditText edtSearch;
    public final LinearLayout musicLayout;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final TextView selectConfirm;
    public final SideBarLayout sidebar;
    public final LayoutTitleBarBinding titleBar;

    private ActivityMusicSelectBinding(ConstraintLayout rootView, ImageView deleteText, EditText edtSearch, LinearLayout musicLayout, RecyclerView recyclerView, TextView selectConfirm, SideBarLayout sidebar, LayoutTitleBarBinding titleBar) {
        this.rootView = rootView;
        this.deleteText = deleteText;
        this.edtSearch = edtSearch;
        this.musicLayout = musicLayout;
        this.recyclerView = recyclerView;
        this.selectConfirm = selectConfirm;
        this.sidebar = sidebar;
        this.titleBar = titleBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMusicSelectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMusicSelectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_music_select, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMusicSelectBinding bind(View rootView) {
        int i = R.id.delete_text;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.delete_text);
        if (imageView != null) {
            i = R.id.edt_search;
            EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.edt_search);
            if (editText != null) {
                i = R.id.music_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.music_layout);
                if (linearLayout != null) {
                    i = R.id.recyclerView;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.recyclerView);
                    if (recyclerView != null) {
                        i = R.id.select_confirm;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.select_confirm);
                        if (textView != null) {
                            i = R.id.sidebar;
                            SideBarLayout sideBarLayout = (SideBarLayout) ViewBindings.findChildViewById(rootView, R.id.sidebar);
                            if (sideBarLayout != null) {
                                i = R.id.titleBar;
                                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                if (viewFindChildViewById != null) {
                                    return new ActivityMusicSelectBinding((ConstraintLayout) rootView, imageView, editText, linearLayout, recyclerView, textView, sideBarLayout, LayoutTitleBarBinding.bind(viewFindChildViewById));
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
