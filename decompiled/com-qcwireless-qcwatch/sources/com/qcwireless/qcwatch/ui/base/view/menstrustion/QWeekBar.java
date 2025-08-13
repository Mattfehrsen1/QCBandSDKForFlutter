package com.qcwireless.qcwatch.ui.base.view.menstrustion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.haibin.calendarview.WeekBar;
import com.qcwireless.qcwatch.R;

/* loaded from: classes3.dex */
public class QWeekBar extends WeekBar {
    public QWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.menstruation_week_bar, (ViewGroup) this, true);
    }
}
