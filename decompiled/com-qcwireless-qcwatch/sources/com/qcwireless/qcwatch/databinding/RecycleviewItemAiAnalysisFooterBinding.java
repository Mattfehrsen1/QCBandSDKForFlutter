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
public final class RecycleviewItemAiAnalysisFooterBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvContentRisk;
    public final TextView tvContentSuggestion;
    public final TextView tvStatement;
    public final TextView tvSummarize;
    public final TextView tvTitleRisk;
    public final TextView tvTitleStatement;
    public final TextView tvTitleSuggestion;
    public final TextView tvTitleSummarize;

    private RecycleviewItemAiAnalysisFooterBinding(ConstraintLayout rootView, TextView tvContentRisk, TextView tvContentSuggestion, TextView tvStatement, TextView tvSummarize, TextView tvTitleRisk, TextView tvTitleStatement, TextView tvTitleSuggestion, TextView tvTitleSummarize) {
        this.rootView = rootView;
        this.tvContentRisk = tvContentRisk;
        this.tvContentSuggestion = tvContentSuggestion;
        this.tvStatement = tvStatement;
        this.tvSummarize = tvSummarize;
        this.tvTitleRisk = tvTitleRisk;
        this.tvTitleStatement = tvTitleStatement;
        this.tvTitleSuggestion = tvTitleSuggestion;
        this.tvTitleSummarize = tvTitleSummarize;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static RecycleviewItemAiAnalysisFooterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RecycleviewItemAiAnalysisFooterBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.recycleview_item_ai_analysis_footer, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static RecycleviewItemAiAnalysisFooterBinding bind(View rootView) {
        int i = R.id.tv_content_risk;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_risk);
        if (textView != null) {
            i = R.id.tv_content_suggestion;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_suggestion);
            if (textView2 != null) {
                i = R.id.tv_statement;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_statement);
                if (textView3 != null) {
                    i = R.id.tv_summarize;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_summarize);
                    if (textView4 != null) {
                        i = R.id.tv_title_risk;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_risk);
                        if (textView5 != null) {
                            i = R.id.tv_title_statement;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_statement);
                            if (textView6 != null) {
                                i = R.id.tv_title_suggestion;
                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_suggestion);
                                if (textView7 != null) {
                                    i = R.id.tv_title_summarize;
                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_summarize);
                                    if (textView8 != null) {
                                        return new RecycleviewItemAiAnalysisFooterBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
