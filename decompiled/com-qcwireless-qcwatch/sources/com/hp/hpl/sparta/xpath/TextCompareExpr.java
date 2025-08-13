package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public abstract class TextCompareExpr extends BooleanExpr {
    private final String value_;

    TextCompareExpr(String str) {
        this.value_ = str;
    }

    public String getValue() {
        return this.value_;
    }

    protected String toString(String str) {
        return "[text()" + str + "'" + this.value_ + "']";
    }
}
