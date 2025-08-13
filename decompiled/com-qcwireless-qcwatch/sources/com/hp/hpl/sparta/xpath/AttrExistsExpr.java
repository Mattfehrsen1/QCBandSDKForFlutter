package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public class AttrExistsExpr extends AttrExpr {
    AttrExistsExpr(String str) {
        super(str);
    }

    @Override // com.hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    @Override // com.hp.hpl.sparta.xpath.AttrExpr
    public String toString() {
        return "[" + super.toString() + "]";
    }
}
