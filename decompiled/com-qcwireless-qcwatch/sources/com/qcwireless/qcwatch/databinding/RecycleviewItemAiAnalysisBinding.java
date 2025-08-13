package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class RecycleviewItemAiAnalysisBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvContent;
    public final TextView tvContentTitle;
    public final TextView tvSuggestion;
    public final TextView tvSuggestionTitle;
    public final TextView tvTitle;

    private RecycleviewItemAiAnalysisBinding(ConstraintLayout rootView, TextView tvContent, TextView tvContentTitle, TextView tvSuggestion, TextView tvSuggestionTitle, TextView tvTitle) {
        this.rootView = rootView;
        this.tvContent = tvContent;
        this.tvContentTitle = tvContentTitle;
        this.tvSuggestion = tvSuggestion;
        this.tvSuggestionTitle = tvSuggestionTitle;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemAiAnalysisBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemAiAnalysisBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_ai_analysis, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemAiAnalysisBinding bind(View rootView) {
        int i = R.id.tv_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
        if (textView != null) {
            i = R.id.tv_content_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_title);
            if (textView2 != null) {
                i = R.id.tv_suggestion;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_suggestion);
                if (textView3 != null) {
                    i = R.id.tv_suggestion_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_suggestion_title);
                    if (textView4 != null) {
                        i = R.id.tv_title;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                        if (textView5 != null) {
                            return new RecycleviewItemAiAnalysisBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, textView5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
