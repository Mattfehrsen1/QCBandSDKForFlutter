package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemMusicMenuBinding implements ViewBinding {
    public final ImageView imageMusicMenu;
    public final ImageView imageRight;
    private final ConstraintLayout rootView;
    public final TextView tvMenuName;
    public final TextView tvSongCount;

    private RecycleviewItemMusicMenuBinding(ConstraintLayout rootView, ImageView imageMusicMenu, ImageView imageRight, TextView tvMenuName, TextView tvSongCount) {
        this.rootView = rootView;
        this.imageMusicMenu = imageMusicMenu;
        this.imageRight = imageRight;
        this.tvMenuName = tvMenuName;
        this.tvSongCount = tvSongCount;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemMusicMenuBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemMusicMenuBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_music_menu, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemMusicMenuBinding bind(View rootView) {
        int i = R.id.image_music_menu;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_music_menu);
        if (imageView != null) {
            i = R.id.image_right;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_right);
            if (imageView2 != null) {
                i = R.id.tv_menu_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_menu_name);
                if (textView != null) {
                    i = R.id.tv_song_count;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_song_count);
                    if (textView2 != null) {
                        return new RecycleviewItemMusicMenuBinding((ConstraintLayout) rootView, imageView, imageView2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
