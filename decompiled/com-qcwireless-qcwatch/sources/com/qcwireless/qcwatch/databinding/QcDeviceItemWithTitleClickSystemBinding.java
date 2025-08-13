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
import com.qcwireless.qcwatch.ui.base.view.QSwitchCompat;

/* loaded from: classes3.dex */
public final class QcDeviceItemWithTitleClickSystemBinding implements ViewBinding {
    public final ImageView imageTextDown;
    public final QSwitchCompat rightswitch;
    public final ConstraintLayout rootLayout;
    private final ConstraintLayout rootView;
    public final TextView tvLeftSubText;
    public final TextView tvLefttext;

    private QcDeviceItemWithTitleClickSystemBinding(ConstraintLayout rootView, ImageView imageTextDown, QSwitchCompat rightswitch, ConstraintLayout rootLayout, TextView tvLeftSubText, TextView tvLefttext) {
        this.rootView = rootView;
        this.imageTextDown = imageTextDown;
        this.rightswitch = rightswitch;
        this.rootLayout = rootLayout;
        this.tvLeftSubText = tvLeftSubText;
        this.tvLefttext = tvLefttext;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static QcDeviceItemWithTitleClickSystemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static QcDeviceItemWithTitleClickSystemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.qc_device_item_with_title_click_system, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static QcDeviceItemWithTitleClickSystemBinding bind(View rootView) {
        int i = R.id.image_text_down;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_text_down);
        if (imageView != null) {
            i = R.id.rightswitch;
            QSwitchCompat qSwitchCompat = (QSwitchCompat) ViewBindings.findChildViewById(rootView, R.id.rightswitch);
            if (qSwitchCompat != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                i = R.id.tv_left_sub_text;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_left_sub_text);
                if (textView != null) {
                    i = R.id.tv_lefttext;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_lefttext);
                    if (textView2 != null) {
                        return new QcDeviceItemWithTitleClickSystemBinding(constraintLayout, imageView, qSwitchCompat, constraintLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
