package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public class TrueExpr extends BooleanExpr {
    static final TrueExpr INSTANCE = new TrueExpr();

    public String toString() {
        return "";
    }

    private TrueExpr() {
    }

    @Override // com.hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) {
        booleanExprVisitor.visit(this);
    }
}
