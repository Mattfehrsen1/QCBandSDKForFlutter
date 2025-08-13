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
public final class ActivityEbookSelectBinding implements ViewBinding {
    public final ConstraintLayout cs4;
    public final ImageView deleteText;
    public final EditText edtSearch;
    public final LinearLayout musicLayout;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;
    public final SideBarLayout sidebar;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvAddMyBook;
    public final TextView tvBookDelete;

    private ActivityEbookSelectBinding(ConstraintLayout rootView, ConstraintLayout cs4, ImageView deleteText, EditText edtSearch, LinearLayout musicLayout, RecyclerView recyclerView, SideBarLayout sidebar, LayoutTitleBarBinding titleBar, TextView tvAddMyBook, TextView tvBookDelete) {
        this.rootView = rootView;
        this.cs4 = cs4;
        this.deleteText = deleteText;
        this.edtSearch = edtSearch;
        this.musicLayout = musicLayout;
        this.recyclerView = recyclerView;
        this.sidebar = sidebar;
        this.titleBar = titleBar;
        this.tvAddMyBook = tvAddMyBook;
        this.tvBookDelete = tvBookDelete;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEbookSelectBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEbookSelectBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ebook_select, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEbookSelectBinding bind(View rootView) {
        int i = R.id.cs_4;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_4);
        if (constraintLayout != null) {
            i = R.id.delete_text;
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
                            i = R.id.sidebar;
                            SideBarLayout sideBarLayout = (SideBarLayout) ViewBindings.findChildViewById(rootView, R.id.sidebar);
                            if (sideBarLayout != null) {
                                i = R.id.titleBar;
                                View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                if (viewFindChildViewById != null) {
                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                    i = R.id.tv_add_my_book;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_add_my_book);
                                    if (textView != null) {
                                        i = R.id.tv_book_delete;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_book_delete);
                                        if (textView2 != null) {
                                            return new ActivityEbookSelectBinding((ConstraintLayout) rootView, constraintLayout, imageView, editText, linearLayout, recyclerView, sideBarLayout, layoutTitleBarBindingBind, textView, textView2);
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
