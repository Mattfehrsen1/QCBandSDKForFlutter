package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public class ParentNodeTest extends NodeTest {
    static final ParentNodeTest INSTANCE = new ParentNodeTest();

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return false;
    }

    public String toString() {
        return "..";
    }

    private ParentNodeTest() {
    }

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) throws XPathException {
        visitor.visit(this);
    }
}
