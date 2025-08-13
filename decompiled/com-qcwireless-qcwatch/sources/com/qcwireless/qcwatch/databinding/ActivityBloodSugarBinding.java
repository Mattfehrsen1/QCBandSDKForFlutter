package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QDateSwitchView;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;

/* loaded from: classes3.dex */
public final class ActivityBloodSugarBinding implements ViewBinding {
    public final QSettingItem bloodSugarValueDetail;
    public final QBloodSugarLineChartView bloodSugarView;
    public final LinearLayout detailLayout;
    public final QDateSwitchView qcDateChange;
    private final NestedScrollView rootView;
    public final LayoutTitleBarBinding titleBar;
    public final TextView topBg1;
    public final TextView tvHM;
    public final TextView tvInfo1;
    public final TextView tvSugarValue;

    private ActivityBloodSugarBinding(NestedScrollView rootView, QSettingItem bloodSugarValueDetail, QBloodSugarLineChartView bloodSugarView, LinearLayout detailLayout, QDateSwitchView qcDateChange, LayoutTitleBarBinding titleBar, TextView topBg1, TextView tvHM, TextView tvInfo1, TextView tvSugarValue) {
        this.rootView = rootView;
        this.bloodSugarValueDetail = bloodSugarValueDetail;
        this.bloodSugarView = bloodSugarView;
        this.detailLayout = detailLayout;
        this.qcDateChange = qcDateChange;
        this.titleBar = titleBar;
        this.topBg1 = topBg1;
        this.tvHM = tvHM;
        this.tvInfo1 = tvInfo1;
        this.tvSugarValue = tvSugarValue;
    }

    @Override // androidx.viewbinding.ViewBinding
    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityBloodSugarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityBloodSugarBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_blood_sugar, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityBloodSugarBinding bind(View rootView) {
        int i = R.id.blood_sugar_value_detail;
        QSettingItem qSettingItem = (QSettingItem) ViewBindings.findChildViewById(rootView, R.id.blood_sugar_value_detail);
        if (qSettingItem != null) {
            i = R.id.blood_sugar_view;
            QBloodSugarLineChartView qBloodSugarLineChartView = (QBloodSugarLineChartView) ViewBindings.findChildViewById(rootView, R.id.blood_sugar_view);
            if (qBloodSugarLineChartView != null) {
                i = R.id.detail_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.detail_layout);
                if (linearLayout != null) {
                    i = R.id.qc_date_change;
                    QDateSwitchView qDateSwitchView = (QDateSwitchView) ViewBindings.findChildViewById(rootView, R.id.qc_date_change);
                    if (qDateSwitchView != null) {
                        i = R.id.title_bar;
                        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.title_bar);
                        if (viewFindChildViewById != null) {
                            LayoutTitleBarBinding layoutTitleBarBindingBind = LayoutTitleBarBinding.bind(viewFindChildViewById);
                            i = R.id.top_bg_1;
                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.top_bg_1);
                            if (textView != null) {
                                i = R.id.tv_h_m;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_h_m);
                                if (textView2 != null) {
                                    i = R.id.tv_info_1;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_info_1);
                                    if (textView3 != null) {
                                        i = R.id.tv_sugar_value;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_sugar_value);
                                        if (textView4 != null) {
                                            return new ActivityBloodSugarBinding((NestedScrollView) rootView, qSettingItem, qBloodSugarLineChartView, linearLayout, qDateSwitchView, layoutTitleBarBindingBind, textView, textView2, textView3, textView4);
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
