package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityDrinkBinding implements ViewBinding {
    public final QSettingItemWithClick drink0;
    public final QSettingItemWithClick drink1;
    public final QSettingItemWithClick drink2;
    public final QSettingItemWithClick drink3;
    public final QSettingItemWithClick drink4;
    public final QSettingItemWithClick drink5;
    public final QSettingItemWithClick drink6;
    public final QSettingItemWithClick drink7;
    public final ConstraintLayout drinkAll;
    public final Group drinkGroup;
    public final NestedScrollView drinkScroll;
    public final QSettingItemWithClickSystem drinkSwitch;
    public final View line1;
    private final ConstraintLayout rootView;
    public final TextView subTitle1;
    public final TextView subTitle2;
    public final LayoutTitleBarBinding titleBar;
    public final RecyclerView weekDayRecycler;

    private ActivityDrinkBinding(ConstraintLayout rootView, QSettingItemWithClick drink0, QSettingItemWithClick drink1, QSettingItemWithClick drink2, QSettingItemWithClick drink3, QSettingItemWithClick drink4, QSettingItemWithClick drink5, QSettingItemWithClick drink6, QSettingItemWithClick drink7, ConstraintLayout drinkAll, Group drinkGroup, NestedScrollView drinkScroll, QSettingItemWithClickSystem drinkSwitch, View line1, TextView subTitle1, TextView subTitle2, LayoutTitleBarBinding titleBar, RecyclerView weekDayRecycler) {
        this.rootView = rootView;
        this.drink0 = drink0;
        this.drink1 = drink1;
        this.drink2 = drink2;
        this.drink3 = drink3;
        this.drink4 = drink4;
        this.drink5 = drink5;
        this.drink6 = drink6;
        this.drink7 = drink7;
        this.drinkAll = drinkAll;
        this.drinkGroup = drinkGroup;
        this.drinkScroll = drinkScroll;
        this.drinkSwitch = drinkSwitch;
        this.line1 = line1;
        this.subTitle1 = subTitle1;
        this.subTitle2 = subTitle2;
        this.titleBar = titleBar;
        this.weekDayRecycler = weekDayRecycler;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDrinkBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDrinkBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_drink, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDrinkBinding bind(View rootView) {
        int i = R.id.drink_0;
        QSettingItemWithClick qSettingItemWithClick = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_0);
        if (qSettingItemWithClick != null) {
            i = R.id.drink_1;
            QSettingItemWithClick qSettingItemWithClick2 = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_1);
            if (qSettingItemWithClick2 != null) {
                i = R.id.drink_2;
                QSettingItemWithClick qSettingItemWithClick3 = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_2);
                if (qSettingItemWithClick3 != null) {
                    i = R.id.drink_3;
                    QSettingItemWithClick qSettingItemWithClick4 = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_3);
                    if (qSettingItemWithClick4 != null) {
                        i = R.id.drink_4;
                        QSettingItemWithClick qSettingItemWithClick5 = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_4);
                        if (qSettingItemWithClick5 != null) {
                            i = R.id.drink_5;
                            QSettingItemWithClick qSettingItemWithClick6 = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_5);
                            if (qSettingItemWithClick6 != null) {
                                i = R.id.drink_6;
                                QSettingItemWithClick qSettingItemWithClick7 = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_6);
                                if (qSettingItemWithClick7 != null) {
                                    i = R.id.drink_7;
                                    QSettingItemWithClick qSettingItemWithClick8 = (QSettingItemWithClick) ViewBindings.findChildViewById(rootView, R.id.drink_7);
                                    if (qSettingItemWithClick8 != null) {
                                        i = R.id.drink_all;
                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.drink_all);
                                        if (constraintLayout != null) {
                                            i = R.id.drink_group;
                                            Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.drink_group);
                                            if (group != null) {
                                                i = R.id.drink_scroll;
                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(rootView, R.id.drink_scroll);
                                                if (nestedScrollView != null) {
                                                    i = R.id.drink_switch;
                                                    QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.drink_switch);
                                                    if (qSettingItemWithClickSystem != null) {
                                                        i = R.id.line_1;
                                                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
                                                        if (viewFindChildViewById != null) {
                                                            i = R.id.sub_title_1;
                                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_title_1);
                                                            if (textView != null) {
                                                                i = R.id.sub_title_2;
                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_title_2);
                                                                if (textView2 != null) {
                                                                    i = R.id.title_bar;
                                                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                                                    if (viewFindChildViewById2 != null) {
                                                                        LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById2);
                                                                        i = R.id.week_day_recycler;
                                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.week_day_recycler);
                                                                        if (recyclerView != null) {
                                                                            return new ActivityDrinkBinding((ConstraintLayout) rootView, qSettingItemWithClick, qSettingItemWithClick2, qSettingItemWithClick3, qSettingItemWithClick4, qSettingItemWithClick5, qSettingItemWithClick6, qSettingItemWithClick7, qSettingItemWithClick8, constraintLayout, group, nestedScrollView, qSettingItemWithClickSystem, viewFindChildViewById, textView, textView2, layoutTitleBarBindingBind, recyclerView);
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
