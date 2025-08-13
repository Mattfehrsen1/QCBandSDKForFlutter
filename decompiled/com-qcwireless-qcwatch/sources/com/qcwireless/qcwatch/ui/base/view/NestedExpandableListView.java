package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

/* loaded from: classes3.dex */
public class NestedExpandableListView extends ExpandableListView {
    public NestedExpandableListView(Context context) {
        super(context);
    }

    public NestedExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
