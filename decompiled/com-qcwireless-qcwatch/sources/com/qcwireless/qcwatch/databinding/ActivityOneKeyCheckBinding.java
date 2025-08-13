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
public final class ActivityOneKeyCheckBinding implements ViewBinding {
    public final Button btnOnekey;
    public final ConstraintLayout csl1;
    public final ConstraintLayout csl2;
    public final ConstraintLayout csl3;
    public final ConstraintLayout csl4;
    public final ConstraintLayout cslTop1;
    public final ImageView imageBgOnkey;
    public final ImageView imageLine;
    private final ConstraintLayout rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView tvBo;
    public final TextView tvBoUnit;
    public final TextView tvBoValue;
    public final TextView tvBp;
    public final TextView tvBpUnit;
    public final TextView tvBpValue;
    public final TextView tvFatigue;
    public final TextView tvFatigueUnit;
    public final TextView tvFatigueValue;
    public final TextView tvHr;
    public final TextView tvHrUnit;
    public final TextView tvHrValue;
    public final TextView tvInfo;
    public final TextView tvTestTime;

    private ActivityOneKeyCheckBinding(ConstraintLayout rootView, Button btnOnekey, ConstraintLayout csl1, ConstraintLayout csl2, ConstraintLayout csl3, ConstraintLayout csl4, ConstraintLayout cslTop1, ImageView imageBgOnkey, ImageView imageLine, LayoutTitleBarBinding titleBar, TextView tvBo, TextView tvBoUnit, TextView tvBoValue, TextView tvBp, TextView tvBpUnit, TextView tvBpValue, TextView tvFatigue, TextView tvFatigueUnit, TextView tvFatigueValue, TextView tvHr, TextView tvHrUnit, TextView tvHrValue, TextView tvInfo, TextView tvTestTime) {
        this.rootView = rootView;
        this.btnOnekey = btnOnekey;
        this.csl1 = csl1;
        this.csl2 = csl2;
        this.csl3 = csl3;
        this.csl4 = csl4;
        this.cslTop1 = cslTop1;
        this.imageBgOnkey = imageBgOnkey;
        this.imageLine = imageLine;
        this.titleBar = titleBar;
        this.tvBo = tvBo;
        this.tvBoUnit = tvBoUnit;
        this.tvBoValue = tvBoValue;
        this.tvBp = tvBp;
        this.tvBpUnit = tvBpUnit;
        this.tvBpValue = tvBpValue;
        this.tvFatigue = tvFatigue;
        this.tvFatigueUnit = tvFatigueUnit;
        this.tvFatigueValue = tvFatigueValue;
        this.tvHr = tvHr;
        this.tvHrUnit = tvHrUnit;
        this.tvHrValue = tvHrValue;
        this.tvInfo = tvInfo;
        this.tvTestTime = tvTestTime;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityOneKeyCheckBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityOneKeyCheckBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_one_key_check, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityOneKeyCheckBinding bind(View rootView) {
        int i = R.id.btn_onekey;
        Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.btn_onekey);
        if (button != null) {
            i = R.id.csl_1;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_1);
            if (constraintLayout != null) {
                i = R.id.csl_2;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_2);
                if (constraintLayout2 != null) {
                    i = R.id.csl_3;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_3);
                    if (constraintLayout3 != null) {
                        i = R.id.csl_4;
                        ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_4);
                        if (constraintLayout4 != null) {
                            i = R.id.csl_top1;
                            ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.csl_top1);
                            if (constraintLayout5 != null) {
                                i = R.id.image_bg_onkey;
                                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_bg_onkey);
                                if (imageView != null) {
                                    i = R.id.image_line;
                                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image_line);
                                    if (imageView2 != null) {
                                        i = R.id.titleBar;
                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.titleBar);
                                        if (viewFindChildViewById != null) {
                                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                                            i = R.id.tv_bo;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bo);
                                            if (textView != null) {
                                                i = R.id.tv_bo_unit;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bo_unit);
                                                if (textView2 != null) {
                                                    i = R.id.tv_bo_value;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bo_value);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_bp;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bp);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_bp_unit;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bp_unit);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_bp_value;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_bp_value);
                                                                if (textView6 != null) {
                                                                    i = R.id.tv_fatigue;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_fatigue);
                                                                    if (textView7 != null) {
                                                                        i = R.id.tv_fatigue_unit;
                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_fatigue_unit);
                                                                        if (textView8 != null) {
                                                                            i = R.id.tv_fatigue_value;
                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_fatigue_value);
                                                                            if (textView9 != null) {
                                                                                i = R.id.tv_hr;
                                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_hr);
                                                                                if (textView10 != null) {
                                                                                    i = R.id.tv_hr_unit;
                                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_hr_unit);
                                                                                    if (textView11 != null) {
                                                                                        i = R.id.tv_hr_value;
                                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_hr_value);
                                                                                        if (textView12 != null) {
                                                                                            i = R.id.tv_info;
                                                                                            TextView textView13 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info);
                                                                                            if (textView13 != null) {
                                                                                                i = R.id.tv_test_time;
                                                                                                TextView textView14 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_test_time);
                                                                                                if (textView14 != null) {
                                                                                                    return new ActivityOneKeyCheckBinding((ConstraintLayout) rootView, button, constraintLayout, constraintLayout2, constraintLayout3, constraintLayout4, constraintLayout5, imageView, imageView2, layoutTitleBarBindingBind, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14);
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
