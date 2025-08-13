package com.qcwireless.qcwatch.ui.base.bean.response.healthy;

import java.util.List;

/* loaded from: classes3.dex */
public class SleepDetailNewProtocolResp {
    public List<SleepDetail> datas;
    public String date;
    public String deviceAddress;
    public String deviceName;
    public int et;
    public int st;
    public long uid;
    public long updateTime;

    public static class SleepDetail {
        public int d;
        public int t;
    }
}
