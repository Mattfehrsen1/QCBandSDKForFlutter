package com.oudmon.ble.base.communication.bigData.bean;

/* loaded from: classes3.dex */
public class SmsQuickBean {
    private int index;
    private String text;

    public SmsQuickBean() {
    }

    public SmsQuickBean(int i, String str) {
        this.index = i;
        this.text = str;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
