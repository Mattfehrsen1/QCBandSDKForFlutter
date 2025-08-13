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
public final class ActivityMusicManagerBinding implements ViewBinding {
    public final TextView btnAddMusic;
    public final ConstraintLayout cs1;
    public final ConstraintLayout cs2;
    public final ConstraintLayout cs3;
    public final ConstraintLayout cs4;
    public final ConstraintLayout ctlProgress;
    public final ConstraintLayout noMusic;
    public final RecyclerView rcvDeviceMusicList;
    public final RecyclerView rcvMyMusicList;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvAddMusic;
    public final TextView tvAddMyMusic;
    public final TextView tvMusicAll;
    public final TextView tvMusicDelete;
    public final TextView tvMusicLyrics;
    public final TextView tvText1;
    public final TextView tvTextIndex;
    public final TextView tvTextProgress;
    public final TextView tvTitle1;
    public final TextView tvTitle2;

    private ActivityMusicManagerBinding(ConstraintLayout rootView, TextView btnAddMusic, ConstraintLayout cs1, ConstraintLayout cs2, ConstraintLayout cs3, ConstraintLayout cs4, ConstraintLayout ctlProgress, ConstraintLayout noMusic, RecyclerView rcvDeviceMusicList, RecyclerView rcvMyMusicList, LayoutTitleBarBinding titleBar, TextView tvAddMusic, TextView tvAddMyMusic, TextView tvMusicAll, TextView tvMusicDelete, TextView tvMusicLyrics, TextView tvText1, TextView tvTextIndex, TextView tvTextProgress, TextView tvTitle1, TextView tvTitle2) {
        this.rootView = rootView;
        this.btnAddMusic = btnAddMusic;
        this.cs1 = cs1;
        this.cs2 = cs2;
        this.cs3 = cs3;
        this.cs4 = cs4;
        this.ctlProgress = ctlProgress;
        this.noMusic = noMusic;
        this.rcvDeviceMusicList = rcvDeviceMusicList;
        this.rcvMyMusicList = rcvMyMusicList;
        this.titleBar = titleBar;
        this.tvAddMusic = tvAddMusic;
        this.tvAddMyMusic = tvAddMyMusic;
        this.tvMusicAll = tvMusicAll;
        this.tvMusicDelete = tvMusicDelete;
        this.tvMusicLyrics = tvMusicLyrics;
        this.tvText1 = tvText1;
        this.tvTextIndex = tvTextIndex;
        this.tvTextProgress = tvTextProgress;
        this.tvTitle1 = tvTitle1;
        this.tvTitle2 = tvTitle2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMusicManagerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMusicManagerBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_music_manager, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMusicManagerBinding bind(View rootView) {
        int i = R.id.btn_add_music;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.btn_add_music);
        if (textView != null) {
            i = R.id.cs_1;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_1);
            if (constraintLayout != null) {
                i = R.id.cs_2;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_2);
                if (constraintLayout2 != null) {
                    i = R.id.cs_3;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_3);
                    if (constraintLayout3 != null) {
                        i = R.id.cs_4;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.cs_4);
                        if (constraintLayout4 != null) {
                            i = R.id.ctl_progress;
                            ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.ctl_progress);
                            if (constraintLayout5 != null) {
                                i = R.id.no_music;
                                ConstraintLayout constraintLayout6 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.no_music);
                                if (constraintLayout6 != null) {
                                    i = R.id.rcv_device_music_list;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_device_music_list);
                                    if (recyclerView != null) {
                                        i = R.id.rcv_my_music_list;
                                        RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.rcv_my_music_list);
                                        if (recyclerView2 != null) {
                                            i = R.id.titleBar;
                                            View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                            if (viewFindChildViewById != null) {
                                                LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                                i = R.id.tv_add_music;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_add_music);
                                                if (textView2 != null) {
                                                    i = R.id.tv_add_my_music;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_add_my_music);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_music_all;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_all);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_music_delete;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_delete);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_music_lyrics;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_music_lyrics);
                                                                if (textView6 != null) {
                                                                    i = R.id.tv_text_1;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_1);
                                                                    if (textView7 != null) {
                                                                        i = R.id.tv_text_index;
                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_index);
                                                                        if (textView8 != null) {
                                                                            i = R.id.tv_text_progress;
                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_text_progress);
                                                                            if (textView9 != null) {
                                                                                i = R.id.tv_title_1;
                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_1);
                                                                                if (textView10 != null) {
                                                                                    i = R.id.tv_title_2;
                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_2);
                                                                                    if (textView11 != null) {
                                                                                        return new ActivityMusicManagerBinding((ConstraintLayout) rootView, textView, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, constraintLayout6, recyclerView, recyclerView2, layoutTitleBarBindingBind, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
