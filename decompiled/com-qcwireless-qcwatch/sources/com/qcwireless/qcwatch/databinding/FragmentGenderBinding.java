package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class FragmentGenderBinding implements ViewBinding {
    public final Button btnNext;
    public final ImageView imageBoy;
    public final ImageView imageBoySelect;
    public final ImageView imageGirl;
    public final ImageView imageGirlSelect;
    private final ConstraintLayout rootView;
    public final TextView tvFemale;
    public final TextView tvMale;
    public final TextView tvTitle;

    private FragmentGenderBinding(ConstraintLayout rootView, Button btnNext, ImageView imageBoy, ImageView imageBoySelect, ImageView imageGirl, ImageView imageGirlSelect, TextView tvFemale, TextView tvMale, TextView tvTitle) {
        this.rootView = rootView;
        this.btnNext = btnNext;
        this.imageBoy = imageBoy;
        this.imageBoySelect = imageBoySelect;
        this.imageGirl = imageGirl;
        this.imageGirlSelect = imageGirlSelect;
        this.tvFemale = tvFemale;
        this.tvMale = tvMale;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentGenderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentGenderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_gender, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentGenderBinding bind(View rootView) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_next);
        if (button != null) {
            i = R.id.image_boy;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_boy);
            if (imageView != null) {
                i = R.id.image_boy_select;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_boy_select);
                if (imageView2 != null) {
                    i = R.id.image_girl;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_girl);
                    if (imageView3 != null) {
                        i = R.id.image_girl_select;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_girl_select);
                        if (imageView4 != null) {
                            i = R.id.tv_female;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_female);
                            if (textView != null) {
                                i = R.id.tv_male;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_male);
                                if (textView2 != null) {
                                    i = R.id.tv_title;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                                    if (textView3 != null) {
                                        return new FragmentGenderBinding((ConstraintLayout) rootView, button, imageView, imageView2, imageView3, imageView4, textView, textView2, textView3);
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
