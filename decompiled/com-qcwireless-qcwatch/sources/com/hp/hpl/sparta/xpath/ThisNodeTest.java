package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public class ThisNodeTest extends NodeTest {
    static final ThisNodeTest INSTANCE = new ThisNodeTest();

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return false;
    }

    public String toString() {
        return ".";
    }

    private ThisNodeTest() {
    }

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
