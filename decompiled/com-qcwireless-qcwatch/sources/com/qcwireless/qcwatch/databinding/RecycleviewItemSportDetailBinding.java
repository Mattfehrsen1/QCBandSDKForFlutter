package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemSportDetailBinding implements ViewBinding {
    public final Group groupSpeed;
    public final ImageView imageClick;
    public final ImageView imageDuration;
    public final ImageView imageSpeed;
    public final ConstraintLayout itemLayout;
    private final ConstraintLayout rootView;
    public final ImageView sportTypeImage;
    public final TextView tvSportDistance;
    public final TextView tvSportDuration;
    public final TextView tvSportSpeed;

    private RecycleviewItemSportDetailBinding(ConstraintLayout rootView, Group groupSpeed, ImageView imageClick, ImageView imageDuration, ImageView imageSpeed, ConstraintLayout itemLayout, ImageView sportTypeImage, TextView tvSportDistance, TextView tvSportDuration, TextView tvSportSpeed) {
        this.rootView = rootView;
        this.groupSpeed = groupSpeed;
        this.imageClick = imageClick;
        this.imageDuration = imageDuration;
        this.imageSpeed = imageSpeed;
        this.itemLayout = itemLayout;
        this.sportTypeImage = sportTypeImage;
        this.tvSportDistance = tvSportDistance;
        this.tvSportDuration = tvSportDuration;
        this.tvSportSpeed = tvSportSpeed;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemSportDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemSportDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_sport_detail, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemSportDetailBinding bind(View rootView) {
        int i = R.id.group_speed;
        Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.group_speed);
        if (group != null) {
            i = R.id.image_click;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_click);
            if (imageView != null) {
                i = R.id.image_duration;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_duration);
                if (imageView2 != null) {
                    i = R.id.image_speed;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_speed);
                    if (imageView3 != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                        i = R.id.sport_type_image;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.sport_type_image);
                        if (imageView4 != null) {
                            i = R.id.tv_sport_distance;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_distance);
                            if (textView != null) {
                                i = R.id.tv_sport_duration;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_duration);
                                if (textView2 != null) {
                                    i = R.id.tv_sport_speed;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sport_speed);
                                    if (textView3 != null) {
                                        return new RecycleviewItemSportDetailBinding(constraintLayout, group, imageView, imageView2, imageView3, constraintLayout, imageView4, textView, textView2, textView3);
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
