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
public final class ActivityExerciseHeartGuideBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tv1;
    public final TextView tv10;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tv6;
    public final TextView tv7;
    public final TextView tv8;
    public final TextView tv9;
    public final TextView tvContent1;
    public final TextView tvContent10;
    public final TextView tvContent11;
    public final TextView tvContent12;
    public final TextView tvContent2;
    public final TextView tvContent3;
    public final TextView tvContent4;
    public final TextView tvContent5;
    public final TextView tvContent6;
    public final TextView tvContent7;
    public final TextView tvContent8;
    public final TextView tvContent9;
    public final TextView tvTitle2;
    public final TextView tvTitleWarming;

    private ActivityExerciseHeartGuideBinding(ConstraintLayout rootView, LayoutTitleBarBinding titleBar, TextView tv1, TextView tv10, TextView tv2, TextView tv3, TextView tv4, TextView tv5, TextView tv6, TextView tv7, TextView tv8, TextView tv9, TextView tvContent1, TextView tvContent10, TextView tvContent11, TextView tvContent12, TextView tvContent2, TextView tvContent3, TextView tvContent4, TextView tvContent5, TextView tvContent6, TextView tvContent7, TextView tvContent8, TextView tvContent9, TextView tvTitle2, TextView tvTitleWarming) {
        this.rootView = rootView;
        this.titleBar = titleBar;
        this.tv1 = tv1;
        this.tv10 = tv10;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tv6 = tv6;
        this.tv7 = tv7;
        this.tv8 = tv8;
        this.tv9 = tv9;
        this.tvContent1 = tvContent1;
        this.tvContent10 = tvContent10;
        this.tvContent11 = tvContent11;
        this.tvContent12 = tvContent12;
        this.tvContent2 = tvContent2;
        this.tvContent3 = tvContent3;
        this.tvContent4 = tvContent4;
        this.tvContent5 = tvContent5;
        this.tvContent6 = tvContent6;
        this.tvContent7 = tvContent7;
        this.tvContent8 = tvContent8;
        this.tvContent9 = tvContent9;
        this.tvTitle2 = tvTitle2;
        this.tvTitleWarming = tvTitleWarming;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityExerciseHeartGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityExerciseHeartGuideBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_exercise_heart_guide, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityExerciseHeartGuideBinding bind(View rootView) {
        int i = R.id.title_bar;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
        if (viewFindChildViewById != null) {
            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
            i = R.id.tv_1;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_1);
            if (textView != null) {
                i = R.id.tv_10;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_10);
                if (textView2 != null) {
                    i = R.id.tv_2;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_2);
                    if (textView3 != null) {
                        i = R.id.tv_3;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_3);
                        if (textView4 != null) {
                            i = R.id.tv_4;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_4);
                            if (textView5 != null) {
                                i = R.id.tv_5;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_5);
                                if (textView6 != null) {
                                    i = R.id.tv_6;
                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_6);
                                    if (textView7 != null) {
                                        i = R.id.tv_7;
                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_7);
                                        if (textView8 != null) {
                                            i = R.id.tv_8;
                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_8);
                                            if (textView9 != null) {
                                                i = R.id.tv_9;
                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_9);
                                                if (textView10 != null) {
                                                    i = R.id.tv_content_1;
                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_1);
                                                    if (textView11 != null) {
                                                        i = R.id.tv_content_10;
                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_10);
                                                        if (textView12 != null) {
                                                            i = R.id.tv_content_11;
                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_11);
                                                            if (textView13 != null) {
                                                                i = R.id.tv_content_12;
                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_12);
                                                                if (textView14 != null) {
                                                                    i = R.id.tv_content_2;
                                                                    TextView textView15 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_2);
                                                                    if (textView15 != null) {
                                                                        i = R.id.tv_content_3;
                                                                        TextView textView16 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_3);
                                                                        if (textView16 != null) {
                                                                            i = R.id.tv_content_4;
                                                                            TextView textView17 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_4);
                                                                            if (textView17 != null) {
                                                                                i = R.id.tv_content_5;
                                                                                TextView textView18 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_5);
                                                                                if (textView18 != null) {
                                                                                    i = R.id.tv_content_6;
                                                                                    TextView textView19 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_6);
                                                                                    if (textView19 != null) {
                                                                                        i = R.id.tv_content_7;
                                                                                        TextView textView20 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_7);
                                                                                        if (textView20 != null) {
                                                                                            i = R.id.tv_content_8;
                                                                                            TextView textView21 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_8);
                                                                                            if (textView21 != null) {
                                                                                                i = R.id.tv_content_9;
                                                                                                TextView textView22 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content_9);
                                                                                                if (textView22 != null) {
                                                                                                    i = R.id.tv_title_2;
                                                                                                    TextView textView23 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_2);
                                                                                                    if (textView23 != null) {
                                                                                                        i = R.id.tv_title_warming;
                                                                                                        TextView textView24 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title_warming);
                                                                                                        if (textView24 != null) {
                                                                                                            return new ActivityExerciseHeartGuideBinding((ConstraintLayout) rootView, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20, textView21, textView22, textView23, textView24);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
