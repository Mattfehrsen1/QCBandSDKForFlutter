package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class FragmentPlateBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvLine1;
    public final TextView tvLine2;
    public final TextView tvLine4;
    public final TextView tvTab1Title;
    public final TextView tvTab1Title1;
    public final TextView tvTab2Title;
    public final TextView tvTab3Title;
    public final TextView tvTab4Title;
    public final HorizontalScrollView vScrollview;
    public final ViewPager2 viewPager;

    private FragmentPlateBinding(ConstraintLayout rootView, TextView tvLine1, TextView tvLine2, TextView tvLine4, TextView tvTab1Title, TextView tvTab1Title1, TextView tvTab2Title, TextView tvTab3Title, TextView tvTab4Title, HorizontalScrollView vScrollview, ViewPager2 viewPager) {
        this.rootView = rootView;
        this.tvLine1 = tvLine1;
        this.tvLine2 = tvLine2;
        this.tvLine4 = tvLine4;
        this.tvTab1Title = tvTab1Title;
        this.tvTab1Title1 = tvTab1Title1;
        this.tvTab2Title = tvTab2Title;
        this.tvTab3Title = tvTab3Title;
        this.tvTab4Title = tvTab4Title;
        this.vScrollview = vScrollview;
        this.viewPager = viewPager;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPlateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentPlateBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_plate, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentPlateBinding bind(View rootView) {
        int i = R.id.tv_line_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_line_1);
        if (textView != null) {
            i = R.id.tv_line_2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_line_2);
            if (textView2 != null) {
                i = R.id.tv_line_4;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_line_4);
                if (textView3 != null) {
                    i = R.id.tv_tab1_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tab1_title);
                    if (textView4 != null) {
                        i = R.id.tv_tab1_title_1;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tab1_title_1);
                        if (textView5 != null) {
                            i = R.id.tv_tab2_title;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tab2_title);
                            if (textView6 != null) {
                                i = R.id.tv_tab3_title;
                                TextView textView7 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tab3_title);
                                if (textView7 != null) {
                                    i = R.id.tv_tab4_title;
                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_tab4_title);
                                    if (textView8 != null) {
                                        i = R.id.v_scrollview;
                                        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) ViewBindings.findChildViewById(rootView, R.id.v_scrollview);
                                        if (horizontalScrollView != null) {
                                            i = R.id.view_pager;
                                            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(rootView, R.id.view_pager);
                                            if (viewPager2 != null) {
                                                return new FragmentPlateBinding((ConstraintLayout) rootView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, horizontalScrollView, viewPager2);
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
