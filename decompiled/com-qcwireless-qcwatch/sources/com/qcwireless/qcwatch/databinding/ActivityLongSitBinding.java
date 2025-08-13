package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;

/* loaded from: classes3.dex */
public final class ActivityLongSitBinding implements ViewBinding {
    public final View line1;
    public final View line2;
    public final RelativeLayout linearRcv;
    public final Group longSitGroup;
    public final ConstraintRadioGroup longSitRadioGroup;
    public final QSettingItem qcLongSitEnd;
    public final QSettingItem qcLongSitStart;
    public final QSettingItemWithClickSystem qcLongSwitch;
    public final RadioButton rbText1;
    public final RadioButton rbText2;
    public final RadioButton rbText3;
    private final ConstraintLayout rootView;
    public final TextView subTitle1;
    public final TextView subTitle2;
    public final TextView subTitle3;
    public final LayoutTitleBarBinding titleBar;
    public final RecyclerView weekDayRecycler;

    private ActivityLongSitBinding(ConstraintLayout rootView, View line1, View line2, RelativeLayout linearRcv, Group longSitGroup, ConstraintRadioGroup longSitRadioGroup, QSettingItem qcLongSitEnd, QSettingItem qcLongSitStart, QSettingItemWithClickSystem qcLongSwitch, RadioButton rbText1, RadioButton rbText2, RadioButton rbText3, TextView subTitle1, TextView subTitle2, TextView subTitle3, LayoutTitleBarBinding titleBar, RecyclerView weekDayRecycler) {
        this.rootView = rootView;
        this.line1 = line1;
        this.line2 = line2;
        this.linearRcv = linearRcv;
        this.longSitGroup = longSitGroup;
        this.longSitRadioGroup = longSitRadioGroup;
        this.qcLongSitEnd = qcLongSitEnd;
        this.qcLongSitStart = qcLongSitStart;
        this.qcLongSwitch = qcLongSwitch;
        this.rbText1 = rbText1;
        this.rbText2 = rbText2;
        this.rbText3 = rbText3;
        this.subTitle1 = subTitle1;
        this.subTitle2 = subTitle2;
        this.subTitle3 = subTitle3;
        this.titleBar = titleBar;
        this.weekDayRecycler = weekDayRecycler;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLongSitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLongSitBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_long_sit, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityLongSitBinding bind(View rootView) {
        int i = R.id.line_1;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.line_1);
        if (viewFindChildViewById != null) {
            i = R.id.line_2;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.line_2);
            if (viewFindChildViewById2 != null) {
                i = R.id.linear_rcv;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, R.id.linear_rcv);
                if (relativeLayout != null) {
                    i = R.id.long_sit_group;
                    Group group = (Group) ViewBindings.findChildViewById(rootView, R.id.long_sit_group);
                    if (group != null) {
                        i = R.id.long_sit_radio_group;
                        ConstraintRadioGroup constraintRadioGroup = (ConstraintRadioGroup) ViewBindings.findChildViewById(rootView, R.id.long_sit_radio_group);
                        if (constraintRadioGroup != null) {
                            i = R.id.qc_long_sit_end;
                            QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_long_sit_end);
                            if (qSettingItem != null) {
                                i = R.id.qc_long_sit_start;
                                QSettingItem qSettingItem2 = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.qc_long_sit_start);
                                if (qSettingItem2 != null) {
                                    i = R.id.qc_long_switch;
                                    QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) ViewBindings.findChildViewById(rootView, R.id.qc_long_switch);
                                    if (qSettingItemWithClickSystem != null) {
                                        i = R.id.rb_text1;
                                        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text1);
                                        if (radioButton != null) {
                                            i = R.id.rb_text2;
                                            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text2);
                                            if (radioButton2 != null) {
                                                i = R.id.rb_text3;
                                                RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, R.id.rb_text3);
                                                if (radioButton3 != null) {
                                                    i = R.id.sub_title_1;
                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_title_1);
                                                    if (textView != null) {
                                                        i = R.id.sub_title_2;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_title_2);
                                                        if (textView2 != null) {
                                                            i = R.id.sub_title_3;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.sub_title_3);
                                                            if (textView3 != null) {
                                                                i = R.id.title_bar;
                                                                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                                                                if (viewFindChildViewById3 != null) {
                                                                    LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById3);
                                                                    i = R.id.week_day_recycler;
                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.week_day_recycler);
                                                                    if (recyclerView != null) {
                                                                        return new ActivityLongSitBinding((ConstraintLayout) rootView, viewFindChildViewById, viewFindChildViewById2, relativeLayout, group, constraintRadioGroup, qSettingItem, qSettingItem2, qSettingItemWithClickSystem, radioButton, radioButton2, radioButton3, textView, textView2, textView3, layoutTitleBarBindingBind, recyclerView);
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
