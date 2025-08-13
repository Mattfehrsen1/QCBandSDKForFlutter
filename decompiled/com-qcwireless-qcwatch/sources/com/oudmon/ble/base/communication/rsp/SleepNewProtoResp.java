package com.oudmon.ble.base.communication.rsp;

import java.util.List;

/* loaded from: classes3.dex */
public class SleepNewProtoResp {
    private int et;
    private List<DetailBean> list;
    private boolean lunchBreak;
    private int lunchEt;
    private int lunchSt;
    private int st;

    public int getSt() {
        return this.st;
    }

    public void setSt(int i) {
        this.st = i;
    }

    public int getEt() {
        return this.et;
    }

    public void setEt(int i) {
        this.et = i;
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

    public void setLunchBreak(boolean z) {
        this.lunchBreak = z;
    }

    public int getLunchSt() {
        return this.lunchSt;
    }

    public void setLunchSt(int i) {
        this.lunchSt = i;
    }

    public int getLunchEt() {
        return this.lunchEt;
    }

    public void setLunchEt(int i) {
        this.lunchEt = i;
    }

    public static class DetailBean {
        private int d;
        private int t;

        public int getD() {
            return this.d;
        }

        public void setD(int i) {
            this.d = i;
        }

        public int getT() {
            return this.t;
        }

        public void setT(int i) {
            this.t = i;
        }

        public String toString() {
            return "DetailBean{d=" + this.d + ", t=" + this.t + '}';
        }
    }

    public String toString() {
        return "SleepNewProtoResp{st=" + this.st + ", et=" + this.et + ", list=" + this.list + '}';
    }
}
