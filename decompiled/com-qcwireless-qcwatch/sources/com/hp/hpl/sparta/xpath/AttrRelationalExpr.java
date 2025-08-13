package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public abstract class AttrRelationalExpr extends AttrExpr {
    private final int attrValue_;

    AttrRelationalExpr(String str, int i) {
        super(str);
        this.attrValue_ = i;
    }

    public double getAttrValue() {
        return this.attrValue_;
    }

    protected String toString(String str) {
        return "[" + super.toString() + str + "'" + this.attrValue_ + "']";
    }
}
