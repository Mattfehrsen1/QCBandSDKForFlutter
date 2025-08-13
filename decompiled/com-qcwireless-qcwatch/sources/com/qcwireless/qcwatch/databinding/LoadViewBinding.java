package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.ShapeLoadingView;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class LoadViewBinding implements ViewBinding {
    public final ImageView indication;
    public final TextView promptTV;
    private final View rootView;
    public final ShapeLoadingView shapeLoadingView;

    private LoadViewBinding(View rootView, ImageView indication, TextView promptTV, ShapeLoadingView shapeLoadingView) {
        this.rootView = rootView;
        this.indication = indication;
        this.promptTV = promptTV;
        this.shapeLoadingView = shapeLoadingView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static LoadViewBinding inflate(LayoutInflater inflater, ViewGroup parent) {
        Objects.requireNonNull(parent, "parent");
        inflater.inflate(R.layout.load_view, parent);
        return bind(parent);
    }

    public static LoadViewBinding bind(View rootView) {
        int i = R.id.indication;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.indication);
        if (imageView != null) {
            i = R.id.promptTV;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.promptTV);
            if (textView != null) {
                i = R.id.shapeLoadingView;
                ShapeLoadingView shapeLoadingView = (ShapeLoadingView) ViewBindings.findChildViewById(rootView, R.id.shapeLoadingView);
                if (shapeLoadingView != null) {
                    return new LoadViewBinding(rootView, imageView, textView, shapeLoadingView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
