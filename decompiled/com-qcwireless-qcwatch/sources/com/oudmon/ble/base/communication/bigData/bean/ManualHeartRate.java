package com.oudmon.ble.base.communication.bigData.bean;

import java.util.List;

/* loaded from: classes3.dex */
public class ManualHeartRate {
    private List<DetailBean> data;
    private int index;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public List<DetailBean> getData() {
        return this.data;
    }

    public void setData(List<DetailBean> list) {
        this.data = list;
    }

    public static class DetailBean {
        private int m;
        private int v;

        public int getM() {
            return this.m;
        }

        public void setM(int i) {
            this.m = i;
        }

        public int getV() {
            return this.v;
        }

        public void setV(int i) {
            this.v = i;
        }
    }
}
