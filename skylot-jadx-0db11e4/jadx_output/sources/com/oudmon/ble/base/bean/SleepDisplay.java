package com.oudmon.ble.base.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bean/SleepDisplay.class */
public class SleepDisplay implements Serializable {
    private int totalSleepDuration;
    private int deepDuration;
    private int shallowDuration;
    private int awakeDuration;
    private int rapidDuration;
    private int sleepTime;
    private int wakeTime;
    private int id;
    private String address;
    private List<SleepDataBean> list;
    private int wakingCount;
    private int totalDays;

    public int getTotalDays() {
        return this.totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public SleepDisplay(int id) {
        this.id = id;
    }

    public SleepDisplay() {
    }

    public int getId() {
        return this.id;
    }

    public int getWakeTime() {
        return this.wakeTime;
    }

    public void setWakeTime(int wakeTime) {
        this.wakeTime = wakeTime;
    }

    public int getTotalSleepDuration() {
        return this.totalSleepDuration;
    }

    public void setTotalSleepDuration(int totalSleepDuration) {
        this.totalSleepDuration = totalSleepDuration;
    }

    public int getDeepSleepDuration() {
        return this.deepDuration;
    }

    public void setDeepSleepDuration(int deep) {
        this.deepDuration = deep;
    }

    public int getShallowSleepDuration() {
        return this.shallowDuration;
    }

    public void setShallowSleepDuration(int shallow) {
        this.shallowDuration = shallow;
    }

    public int getWakingCount() {
        return this.wakingCount;
    }

    public void setWakingCount(int wakingCount) {
        this.wakingCount = wakingCount;
    }

    public int getSleepTime() {
        return this.sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getAddress() {
        return this.address;
    }

    public int getAwakeDuration() {
        return this.awakeDuration;
    }

    public void setAwakeDuration(int awakeDuration) {
        this.awakeDuration = awakeDuration;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SleepDataBean> getList() {
        return this.list;
    }

    public void setList(List<SleepDataBean> list) {
        this.list = list;
    }

    public int getRapidDuration() {
        return this.rapidDuration;
    }

    public void setRapidDuration(int rapidDuration) {
        this.rapidDuration = rapidDuration;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bean/SleepDisplay$SleepDataBean.class */
    public static class SleepDataBean {
        private long sleepStart;
        private long sleepEnd;
        private int type;

        public SleepDataBean() {
        }

        public SleepDataBean(long sleepStart, long sleepEnd, int type) {
            this.sleepStart = sleepStart;
            this.sleepEnd = sleepEnd;
            this.type = type;
        }

        public long getSleepStart() {
            return this.sleepStart;
        }

        public void setSleepStart(long sleepStart) {
            this.sleepStart = sleepStart;
        }

        public long getSleepEnd() {
            return this.sleepEnd;
        }

        public void setSleepEnd(long sleepEnd) {
            this.sleepEnd = sleepEnd;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public String toString() {
        return "SleepDisplay{totalSleepDuration=" + this.totalSleepDuration + ", deepDuration=" + this.deepDuration + ", shallowDuration=" + this.shallowDuration + ", awakeDuration=" + this.awakeDuration + ", rapidDuration=" + this.rapidDuration + ", sleepTime=" + this.sleepTime + ", wakeTime=" + this.wakeTime + ", id=" + this.id + ", address='" + this.address + "', list=" + this.list + ", wakingCount=" + this.wakingCount + ", totalDays=" + this.totalDays + '}';
    }
}
