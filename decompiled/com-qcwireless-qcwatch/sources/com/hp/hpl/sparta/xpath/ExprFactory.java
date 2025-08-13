package com.hp.hpl.sparta.xpath;

import java.io.IOException;

/* loaded from: classes2.dex */
public class ExprFactory {
    static BooleanExpr createExpr(XPath xPath, SimpleStreamTokenizer simpleStreamTokenizer) throws XPathException, IOException, NumberFormatException {
        int i;
        int i2;
        int i3 = simpleStreamTokenizer.ttype;
        if (i3 == -3) {
            if (!simpleStreamTokenizer.sval.equals("text")) {
                throw new XPathException(xPath, "at beginning of expression", simpleStreamTokenizer, "text()");
            }
            if (simpleStreamTokenizer.nextToken() != 40) {
                throw new XPathException(xPath, "after text", simpleStreamTokenizer, "(");
            }
            if (simpleStreamTokenizer.nextToken() != 41) {
                throw new XPathException(xPath, "after text(", simpleStreamTokenizer, ")");
            }
            int iNextToken = simpleStreamTokenizer.nextToken();
            if (iNextToken != 33) {
                if (iNextToken == 61) {
                    simpleStreamTokenizer.nextToken();
                    if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
                        throw new XPathException(xPath, "right hand side of equals", simpleStreamTokenizer, "quoted string");
                    }
                    String str = simpleStreamTokenizer.sval;
                    simpleStreamTokenizer.nextToken();
                    return new TextEqualsExpr(str);
                }
                return TextExistsExpr.INSTANCE;
            }
            simpleStreamTokenizer.nextToken();
            if (simpleStreamTokenizer.ttype != 61) {
                throw new XPathException(xPath, "after !", simpleStreamTokenizer, "=");
            }
            simpleStreamTokenizer.nextToken();
            if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
                throw new XPathException(xPath, "right hand side of !=", simpleStreamTokenizer, "quoted string");
            }
            String str2 = simpleStreamTokenizer.sval;
            simpleStreamTokenizer.nextToken();
            return new TextNotEqualsExpr(str2);
        }
        if (i3 == -2) {
            int i4 = simpleStreamTokenizer.nval;
            simpleStreamTokenizer.nextToken();
            return new PositionEqualsExpr(i4);
        }
        if (i3 != 64) {
            throw new XPathException(xPath, "at beginning of expression", simpleStreamTokenizer, "@, number, or text()");
        }
        if (simpleStreamTokenizer.nextToken() != -3) {
            throw new XPathException(xPath, "after @", simpleStreamTokenizer, "name");
        }
        String str3 = simpleStreamTokenizer.sval;
        int iNextToken2 = simpleStreamTokenizer.nextToken();
        if (iNextToken2 != 33) {
            switch (iNextToken2) {
                case 60:
                    simpleStreamTokenizer.nextToken();
                    if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                        i = Integer.parseInt(simpleStreamTokenizer.sval);
                    } else if (simpleStreamTokenizer.ttype == -2) {
                        i = simpleStreamTokenizer.nval;
                    } else {
                        throw new XPathException(xPath, "right hand side of less-than", simpleStreamTokenizer, "quoted string or number");
                    }
                    simpleStreamTokenizer.nextToken();
                    return new AttrLessExpr(str3, i);
                case 61:
                    simpleStreamTokenizer.nextToken();
                    if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
                        throw new XPathException(xPath, "right hand side of equals", simpleStreamTokenizer, "quoted string");
                    }
                    String str4 = simpleStreamTokenizer.sval;
                    simpleStreamTokenizer.nextToken();
                    return new AttrEqualsExpr(str3, str4);
                case 62:
                    simpleStreamTokenizer.nextToken();
                    if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                        i2 = Integer.parseInt(simpleStreamTokenizer.sval);
                    } else if (simpleStreamTokenizer.ttype == -2) {
                        i2 = simpleStreamTokenizer.nval;
                    } else {
                        throw new XPathException(xPath, "right hand side of greater-than", simpleStreamTokenizer, "quoted string or number");
                    }
                    simpleStreamTokenizer.nextToken();
                    return new AttrGreaterExpr(str3, i2);
                default:
                    return new AttrExistsExpr(str3);
            }
        }
        simpleStreamTokenizer.nextToken();
        if (simpleStreamTokenizer.ttype != 61) {
            throw new XPathException(xPath, "after !", simpleStreamTokenizer, "=");
        }
        simpleStreamTokenizer.nextToken();
        if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
            throw new XPathException(xPath, "right hand side of !=", simpleStreamTokenizer, "quoted string");
        }
        String str5 = simpleStreamTokenizer.sval;
        simpleStreamTokenizer.nextToken();
        return new AttrNotEqualsExpr(str3, str5);
    }
}
