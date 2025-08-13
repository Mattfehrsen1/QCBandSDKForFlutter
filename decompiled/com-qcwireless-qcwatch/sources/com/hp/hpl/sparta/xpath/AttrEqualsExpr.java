package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public class AttrEqualsExpr extends AttrCompareExpr {
    AttrEqualsExpr(String str, String str2) {
        super(str, str2);
    }

    @Override // com.hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    @Override // com.hp.hpl.sparta.xpath.AttrExpr
    public String toString() {
        return toString("=");
    }
}
