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
public final class ActivityWebBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final WebView webView;

    private ActivityWebBinding(ConstraintLayout rootView, LayoutTitleBarBinding titleBar, WebView webView) {
        this.rootView = rootView;
        this.titleBar = titleBar;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWebBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityWebBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_web, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWebBinding bind(View rootView) {
        int i = R.id.title_bar;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
        if (viewFindChildViewById != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            WebView webView = (WebView) ViewBindings.findChildViewById(rootView, R.id.webView);
            if (webView != null) {
                return new ActivityWebBinding((ConstraintLayout) rootView, layoutTitleBarBindingBind, webView);
            }
            i = R.id.webView;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
