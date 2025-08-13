package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityEbookManagerBinding implements ViewBinding {
    public final TextView btnAddMusic;
    public final TextView btnEbook;
    public final ConstraintLayout cs2;
    public final ConstraintLayout cs3;
    public final ConstraintLayout cs4;
    public final ConstraintLayout ctlProgress;
    public final ConstraintLayout noEbook;
    public final RecyclerView rcvDeviceEbookList;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvEbookDelete;
    public final TextView tvText1;
    public final TextView tvTextIndex;
    public final TextView tvTextProgress;
    public final TextView tvTitle2;

    private ActivityEbookManagerBinding(ConstraintLayout rootView, TextView btnAddMusic, TextView btnEbook, ConstraintLayout cs2, ConstraintLayout cs3, ConstraintLayout cs4, ConstraintLayout ctlProgress, ConstraintLayout noEbook, RecyclerView rcvDeviceEbookList, LayoutTitleBarBinding titleBar, TextView tvEbookDelete, TextView tvText1, TextView tvTextIndex, TextView tvTextProgress, TextView tvTitle2) {
        this.rootView = rootView;
        this.btnAddMusic = btnAddMusic;
        this.btnEbook = btnEbook;
        this.cs2 = cs2;
        this.cs3 = cs3;
        this.cs4 = cs4;
        this.ctlProgress = ctlProgress;
        this.noEbook = noEbook;
        this.rcvDeviceEbookList = rcvDeviceEbookList;
        this.titleBar = titleBar;
        this.tvEbookDelete = tvEbookDelete;
        this.tvText1 = tvText1;
        this.tvTextIndex = tvTextIndex;
        this.tvTextProgress = tvTextProgress;
        this.tvTitle2 = tvTitle2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEbookManagerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEbookManagerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_ebook_manager, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityEbookManagerBinding bind(View rootView) {
        int i = R.id.btn_add_music;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_add_music);
        if (textView != null) {
            i = R.id.btn_ebook;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_ebook);
            if (textView2 != null) {
                i = R.id.cs_2;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_2);
                if (constraintLayout != null) {
                    i = R.id.cs_3;
                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_3);
                    if (constraintLayout2 != null) {
                        i = R.id.cs_4;
                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_4);
                        if (constraintLayout3 != null) {
                            i = R.id.ctl_progress;
                            ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_progress);
                            if (constraintLayout4 != null) {
                                i = R.id.no_ebook;
                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.no_ebook);
                                if (constraintLayout5 != null) {
                                    i = R.id.rcv_device_ebook_list;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_ebook_list);
                                    if (recyclerView != null) {
                                        i = R.id.titleBar;
                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                        if (viewFindChildViewById != null) {
                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                            i = R.id.tv_ebook_delete;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_ebook_delete);
                                            if (textView3 != null) {
                                                i = R.id.tv_text_1;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_1);
                                                if (textView4 != null) {
                                                    i = R.id.tv_text_index;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_index);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_text_progress;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_progress);
                                                        if (textView6 != null) {
                                                            i = R.id.tv_title_2;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_2);
                                                            if (textView7 != null) {
                                                                return new ActivityEbookManagerBinding((ConstraintLayout) rootView, textView, textView2, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, recyclerView, layoutTitleBarBindingBind, textView3, textView4, textView5, textView6, textView7);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
