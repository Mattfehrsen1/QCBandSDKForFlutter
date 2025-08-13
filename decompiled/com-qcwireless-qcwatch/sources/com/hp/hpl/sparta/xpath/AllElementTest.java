package com.hp.hpl.sparta.xpath;

import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class AllElementTest extends NodeTest {
    static final AllElementTest INSTANCE = new AllElementTest();

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return false;
    }

    public String toString() {
        return Marker.ANY_MARKER;
    }

    private AllElementTest() {
    }

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
