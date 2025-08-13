package com.qcwireless.qcwatch.base.dialog.bean;

/* loaded from: classes3.dex */
public class WeekRepeat {
    private boolean check;
    private String day;
    private int repeat;

    public WeekRepeat(String day, boolean check) {
        this.day = day;
        this.check = check;
    }

    public WeekRepeat(String day, boolean check, int repeat) {
        this.day = day;
        this.check = check;
        this.repeat = repeat;
    }

    public int getRepeat() {
        return this.repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
