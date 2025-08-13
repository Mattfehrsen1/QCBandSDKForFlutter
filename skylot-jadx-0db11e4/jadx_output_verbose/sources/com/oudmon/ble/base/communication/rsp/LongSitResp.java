package com.oudmon.ble.base.communication.rsp;

import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/LongSitResp.class */
public class LongSitResp {
    private int index;
    private List<DetailBean> list;

    public List<DetailBean> getList() {
        return this.list;
    }

    public void setList(List<DetailBean> list) {
        this.list = list;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/LongSitResp$DetailBean.class */
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
    }
}
