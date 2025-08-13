package com.hp.hpl.sparta.xpath;

import java.io.IOException;

/* loaded from: classes2.dex */
public class Step {
    public static Step DOT = new Step(ThisNodeTest.INSTANCE, TrueExpr.INSTANCE);
    private final boolean multiLevel_;
    private final NodeTest nodeTest_;
    private final BooleanExpr predicate_;

    Step(NodeTest nodeTest, BooleanExpr booleanExpr) {
        this.nodeTest_ = nodeTest;
        this.predicate_ = booleanExpr;
        this.multiLevel_ = false;
    }

    Step(XPath xPath, boolean z, SimpleStreamTokenizer simpleStreamTokenizer) throws XPathException, IOException {
        this.multiLevel_ = z;
        int i = simpleStreamTokenizer.ttype;
        if (i != -3) {
            if (i == 42) {
                this.nodeTest_ = AllElementTest.INSTANCE;
            } else if (i != 46) {
                if (i == 64) {
                    if (simpleStreamTokenizer.nextToken() != -3) {
                        throw new XPathException(xPath, "after @ in node test", simpleStreamTokenizer, "name");
                    }
                    this.nodeTest_ = new AttrTest(simpleStreamTokenizer.sval);
                } else {
                    throw new XPathException(xPath, "at begininning of step", simpleStreamTokenizer, "'.' or '*' or name");
                }
            } else if (simpleStreamTokenizer.nextToken() == 46) {
                this.nodeTest_ = ParentNodeTest.INSTANCE;
            } else {
                simpleStreamTokenizer.pushBack();
                this.nodeTest_ = ThisNodeTest.INSTANCE;
            }
        } else if (simpleStreamTokenizer.sval.equals("text")) {
            if (simpleStreamTokenizer.nextToken() != 40 || simpleStreamTokenizer.nextToken() != 41) {
                throw new XPathException(xPath, "after text", simpleStreamTokenizer, "()");
            }
            this.nodeTest_ = TextTest.INSTANCE;
        } else {
            this.nodeTest_ = new ElementTest(simpleStreamTokenizer.sval);
        }
        if (simpleStreamTokenizer.nextToken() == 91) {
            simpleStreamTokenizer.nextToken();
            this.predicate_ = ExprFactory.createExpr(xPath, simpleStreamTokenizer);
            if (simpleStreamTokenizer.ttype != 93) {
                throw new XPathException(xPath, "after predicate expression", simpleStreamTokenizer, "]");
            }
            simpleStreamTokenizer.nextToken();
            return;
        }
        this.predicate_ = TrueExpr.INSTANCE;
    }

    public String toString() {
        return this.nodeTest_.toString() + this.predicate_.toString();
    }

    public boolean isMultiLevel() {
        return this.multiLevel_;
    }

    public boolean isStringValue() {
        return this.nodeTest_.isStringValue();
    }

    public NodeTest getNodeTest() {
        return this.nodeTest_;
    }

    public BooleanExpr getPredicate() {
        return this.predicate_;
    }
}
