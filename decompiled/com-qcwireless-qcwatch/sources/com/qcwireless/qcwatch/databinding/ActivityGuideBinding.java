package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class ActivityGuideBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final WebView webView;

    private ActivityGuideBinding(ConstraintLayout rootView, WebView webView) {
        this.rootView = rootView;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityGuideBinding bind(View rootView) {
        WebView webView = (WebView) ViewBindings.findChildViewById(rootView, R.id.webView);
        if (webView != null) {
            return new ActivityGuideBinding((ConstraintLayout) rootView, webView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.webView)));
    }
}
