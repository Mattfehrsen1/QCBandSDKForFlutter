package com.oudmon.ble.base.communication.bigData;

import java.util.List;

/* loaded from: classes3.dex */
public class AlarmNewEntity {
    private List<AlarmBean> data;
    private int total;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public List<AlarmBean> getData() {
        return this.data;
    }

    public void setData(List<AlarmBean> list) {
        this.data = list;
    }

    public static class AlarmBean {
        private int alarmLength;
        private String content;
        private int min;
        private int repeatAndEnable;

        public int getAlarmLength() {
            return this.alarmLength;
        }

        public void setAlarmLength(int i) {
            this.alarmLength = i;
        }

        public int getRepeatAndEnable() {
            return this.repeatAndEnable;
        }

        public void setRepeatAndEnable(int i) {
            this.repeatAndEnable = i;
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int i) {
            this.min = i;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }
    }
}
