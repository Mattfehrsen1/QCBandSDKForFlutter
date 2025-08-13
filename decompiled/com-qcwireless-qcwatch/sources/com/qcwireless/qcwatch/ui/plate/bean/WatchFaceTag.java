package com.qcwireless.qcwatch.ui.plate.bean;

/* loaded from: classes3.dex */
public class WatchFaceTag {
    private boolean check;
    private String tag;

    public WatchFaceTag(String tag, boolean check) {
        this.tag = tag;
        this.check = check;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
