package com.qcwireless.qcwatch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.contrarywind.view.WheelView;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public final class LayoutDialogRemindSelectorBinding implements ViewBinding {
    public final TextView centerText;
    private final LinearLayout rootView;
    public final ConstraintLayout selectView;
    public final TextView tvDialogCancel;
    public final TextView tvDialogConfirm;
    public final EditText tvDialogTitle;
    public final RecyclerView weekDayRecycler;
    public final TextView weekDayTitle;
    public final WheelView wheelViewHour;
    public final WheelView wheelViewMin;

    private LayoutDialogRemindSelectorBinding(LinearLayout rootView, TextView centerText, ConstraintLayout selectView, TextView tvDialogCancel, TextView tvDialogConfirm, EditText tvDialogTitle, RecyclerView weekDayRecycler, TextView weekDayTitle, WheelView wheelViewHour, WheelView wheelViewMin) {
        this.rootView = rootView;
        this.centerText = centerText;
        this.selectView = selectView;
        this.tvDialogCancel = tvDialogCancel;
        this.tvDialogConfirm = tvDialogConfirm;
        this.tvDialogTitle = tvDialogTitle;
        this.weekDayRecycler = weekDayRecycler;
        this.weekDayTitle = weekDayTitle;
        this.wheelViewHour = wheelViewHour;
        this.wheelViewMin = wheelViewMin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDialogRemindSelectorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutDialogRemindSelectorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_dialog_remind_selector, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDialogRemindSelectorBinding bind(View rootView) {
        int i = R.id.center_text;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.center_text);
        if (textView != null) {
            i = R.id.select_view;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(rootView, R.id.select_view);
            if (constraintLayout != null) {
                i = R.id.tv_dialog_cancel;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_cancel);
                if (textView2 != null) {
                    i = R.id.tv_dialog_confirm;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_confirm);
                    if (textView3 != null) {
                        i = R.id.tv_dialog_title;
                        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, R.id.tv_dialog_title);
                        if (editText != null) {
                            i = R.id.week_day_recycler;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(rootView, R.id.week_day_recycler);
                            if (recyclerView != null) {
                                i = R.id.week_day_title;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.week_day_title);
                                if (textView4 != null) {
                                    i = R.id.wheel_view_hour;
                                    WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_hour);
                                    if (wheelView != null) {
                                        i = R.id.wheel_view_min;
                                        WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, R.id.wheel_view_min);
                                        if (wheelView2 != null) {
                                            return new LayoutDialogRemindSelectorBinding((LinearLayout) rootView, textView, constraintLayout, textView2, textView3, editText, recyclerView, textView4, wheelView, wheelView2);
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
