package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public class TextTest extends NodeTest {
    static final TextTest INSTANCE = new TextTest();

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return true;
    }

    public String toString() {
        return "text()";
    }

    private TextTest() {
    }

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) throws XPathException {
        visitor.visit(this);
    }
}
