package com.oudmon.ble.base.communication.schedule;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ScheduleEntity {
    public int mDetail;
    public List<Integer> mDetails = new ArrayList();
    public int mEndTime;
    public int mRepeatType;
    public int mStartTime;
    public String mTitle;

    public ScheduleEntity() {
    }

    public ScheduleEntity(String str, int i, int i2, int i3, int i4) {
        this.mTitle = str;
        this.mStartTime = i;
        this.mEndTime = i2;
        this.mRepeatType = i3;
        this.mDetail = i4;
    }
}
