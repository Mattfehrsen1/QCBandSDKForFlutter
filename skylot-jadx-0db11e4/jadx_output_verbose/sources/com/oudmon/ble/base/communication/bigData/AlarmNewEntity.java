package com.oudmon.ble.base.communication.bigData;

import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/bigData/AlarmNewEntity.class */
public class AlarmNewEntity {
    private int total;
    private List<AlarmBean> data;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<AlarmBean> getData() {
        return this.data;
    }

    public void setData(List<AlarmBean> data) {
        this.data = data;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/bigData/AlarmNewEntity$AlarmBean.class */
    public static class AlarmBean {
        private int alarmLength;
        private int repeatAndEnable;
        private int min;
        private String content;

        public int getAlarmLength() {
            return this.alarmLength;
        }

        public void setAlarmLength(int alarmLength) {
            this.alarmLength = alarmLength;
        }

        public int getRepeatAndEnable() {
            return this.repeatAndEnable;
        }

        public void setRepeatAndEnable(int repeatAndEnable) {
            this.repeatAndEnable = repeatAndEnable;
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String toString() {
            return "AlarmBean{alarmLength=" + this.alarmLength + ", repeatAndEnable=" + this.repeatAndEnable + ", min=" + this.min + ", content='" + this.content + "'}";
        }
    }

    public String toString() {
        return "AlarmNewEntity{total=" + this.total + ", data=" + this.data + '}';
    }
}
