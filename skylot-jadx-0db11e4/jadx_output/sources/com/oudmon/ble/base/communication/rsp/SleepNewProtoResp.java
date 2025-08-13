package com.oudmon.ble.base.communication.rsp;

import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/SleepNewProtoResp.class */
public class SleepNewProtoResp {
    private int st;
    private int et;
    private int lunchSt;
    private int lunchEt;
    private boolean lunchBreak;
    private List<DetailBean> list;

    public int getSt() {
        return this.st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public int getEt() {
        return this.et;
    }

    public void setEt(int et) {
        this.et = et;
    }

    public List<DetailBean> getList() {
        return this.list;
    }

    public void setList(List<DetailBean> list) {
        this.list = list;
    }

    public boolean isLunchBreak() {
        return this.lunchBreak;
    }

    public void setLunchBreak(boolean lunchBreak) {
        this.lunchBreak = lunchBreak;
    }

    public int getLunchSt() {
        return this.lunchSt;
    }

    public void setLunchSt(int lunchSt) {
        this.lunchSt = lunchSt;
    }

    public int getLunchEt() {
        return this.lunchEt;
    }

    public void setLunchEt(int lunchEt) {
        this.lunchEt = lunchEt;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/SleepNewProtoResp$DetailBean.class */
    public static class DetailBean {
        private int d;
        private int t;

        public int getD() {
            return this.d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getT() {
            return this.t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public String toString() {
            return "DetailBean{d=" + this.d + ", t=" + this.t + '}';
        }
    }

    public String toString() {
        return "SleepNewProtoResp{st=" + this.st + ", et=" + this.et + ", list=" + this.list + '}';
    }
}
