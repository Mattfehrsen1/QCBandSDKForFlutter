package com.oudmon.ble.base.communication.bigData.bean;

import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/bigData/bean/ManualHeartRate.class */
public class ManualHeartRate {
    private int index;
    private List<DetailBean> data;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<DetailBean> getData() {
        return this.data;
    }

    public void setData(List<DetailBean> data) {
        this.data = data;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/bigData/bean/ManualHeartRate$DetailBean.class */
    public static class DetailBean {
        private int m;
        private int v;

        public int getM() {
            return this.m;
        }

        public void setM(int m) {
            this.m = m;
        }

        public int getV() {
            return this.v;
        }

        public void setV(int v) {
            this.v = v;
        }
    }

    public String toString() {
        return "ManualHeartRate{index=" + this.index + ", data=" + this.data + '}';
    }
}
