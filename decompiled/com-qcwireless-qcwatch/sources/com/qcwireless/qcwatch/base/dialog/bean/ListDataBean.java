package com.qcwireless.qcwatch.base.dialog.bean;

/* loaded from: classes3.dex */
public class ListDataBean {
    private boolean checked;
    private String leftText;

    public ListDataBean(String leftText, boolean checked) {
        this.leftText = leftText;
        this.checked = checked;
    }

    public String getLeftText() {
        return this.leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
