package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import java.util.List;

/* loaded from: classes3.dex */
public class CommitSleepNewProtocolParam {
    public List<SleepList> data;
    public String deviceAddress;
    public String deviceName;
    public long uid;

    public static class SleepList {
        public String date;
        public List<SleepDetail> detail;
        public int et;
        public int st;

        public static class SleepDetail {
            public int d;
            public int t;
        }
    }
}
