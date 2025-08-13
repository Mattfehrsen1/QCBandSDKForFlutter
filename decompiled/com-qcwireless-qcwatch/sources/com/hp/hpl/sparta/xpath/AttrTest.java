package com.hp.hpl.sparta.xpath;

/* loaded from: classes2.dex */
public class AttrTest extends NodeTest {
    private final String attrName_;

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return true;
    }

    AttrTest(String str) {
        this.attrName_ = str;
    }

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getAttrName() {
        return this.attrName_;
    }

    public String toString() {
        return "@" + this.attrName_;
    }
}
