package com.hp.hpl.sparta.xpath;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

/* loaded from: classes2.dex */
public class XPath {
    private static final int ASSERTION = 0;
    private static Hashtable cache_ = new Hashtable();
    private boolean absolute_;
    private Stack steps_;
    private String string_;

    private XPath(boolean z, Step[] stepArr) {
        this.steps_ = new Stack();
        for (Step step : stepArr) {
            this.steps_.addElement(step);
        }
        this.absolute_ = z;
        this.string_ = null;
    }

    private XPath(String str) throws XPathException {
        this(str, new InputStreamReader(new ByteArrayInputStream(str.getBytes())));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004b A[Catch: IOException -> 0x0074, TryCatch #0 {IOException -> 0x0074, blocks: (B:3:0x000a, B:5:0x002d, B:7:0x0035, B:10:0x003d, B:11:0x0047, B:13:0x004b, B:15:0x0051, B:17:0x0057, B:18:0x0062, B:21:0x0068, B:22:0x0073, B:8:0x003a), top: B:26:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068 A[Catch: IOException -> 0x0074, TryCatch #0 {IOException -> 0x0074, blocks: (B:3:0x000a, B:5:0x002d, B:7:0x0035, B:10:0x003d, B:11:0x0047, B:13:0x004b, B:15:0x0051, B:17:0x0057, B:18:0x0062, B:21:0x0068, B:22:0x0073, B:8:0x003a), top: B:26:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private XPath(java.lang.String r6, java.io.Reader r7) throws com.hp.hpl.sparta.xpath.XPathException {
        /*
            r5 = this;
            r5.<init>()
            java.util.Stack r0 = new java.util.Stack
            r0.<init>()
            r5.steps_ = r0
            r5.string_ = r6     // Catch: java.io.IOException -> L74
            com.hp.hpl.sparta.xpath.SimpleStreamTokenizer r6 = new com.hp.hpl.sparta.xpath.SimpleStreamTokenizer     // Catch: java.io.IOException -> L74
            r6.<init>(r7)     // Catch: java.io.IOException -> L74
            r7 = 47
            r6.ordinaryChar(r7)     // Catch: java.io.IOException -> L74
            r0 = 46
            r6.ordinaryChar(r0)     // Catch: java.io.IOException -> L74
            r0 = 58
            r6.wordChars(r0, r0)     // Catch: java.io.IOException -> L74
            r0 = 95
            r6.wordChars(r0, r0)     // Catch: java.io.IOException -> L74
            int r0 = r6.nextToken()     // Catch: java.io.IOException -> L74
            r1 = 1
            r2 = 0
            if (r0 != r7) goto L3a
            r5.absolute_ = r1     // Catch: java.io.IOException -> L74
            int r0 = r6.nextToken()     // Catch: java.io.IOException -> L74
            if (r0 != r7) goto L3c
            r6.nextToken()     // Catch: java.io.IOException -> L74
            r0 = 1
            goto L3d
        L3a:
            r5.absolute_ = r2     // Catch: java.io.IOException -> L74
        L3c:
            r0 = 0
        L3d:
            java.util.Stack r3 = r5.steps_     // Catch: java.io.IOException -> L74
            com.hp.hpl.sparta.xpath.Step r4 = new com.hp.hpl.sparta.xpath.Step     // Catch: java.io.IOException -> L74
            r4.<init>(r5, r0, r6)     // Catch: java.io.IOException -> L74
            r3.push(r4)     // Catch: java.io.IOException -> L74
        L47:
            int r0 = r6.ttype     // Catch: java.io.IOException -> L74
            if (r0 != r7) goto L62
            int r0 = r6.nextToken()     // Catch: java.io.IOException -> L74
            if (r0 != r7) goto L56
            r6.nextToken()     // Catch: java.io.IOException -> L74
            r0 = 1
            goto L57
        L56:
            r0 = 0
        L57:
            java.util.Stack r3 = r5.steps_     // Catch: java.io.IOException -> L74
            com.hp.hpl.sparta.xpath.Step r4 = new com.hp.hpl.sparta.xpath.Step     // Catch: java.io.IOException -> L74
            r4.<init>(r5, r0, r6)     // Catch: java.io.IOException -> L74
            r3.push(r4)     // Catch: java.io.IOException -> L74
            goto L47
        L62:
            int r7 = r6.ttype     // Catch: java.io.IOException -> L74
            r0 = -1
            if (r7 != r0) goto L68
            return
        L68:
            com.hp.hpl.sparta.xpath.XPathException r7 = new com.hp.hpl.sparta.xpath.XPathException     // Catch: java.io.IOException -> L74
            java.lang.String r0 = "at end of XPATH expression"
            java.lang.String r1 = "end of expression"
            r7.<init>(r5, r0, r6, r1)     // Catch: java.io.IOException -> L74
            throw r7     // Catch: java.io.IOException -> L74
        L74:
            r6 = move-exception
            com.hp.hpl.sparta.xpath.XPathException r7 = new com.hp.hpl.sparta.xpath.XPathException
            r7.<init>(r5, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.xpath.XPath.<init>(java.lang.String, java.io.Reader):void");
    }

    public String toString() {
        if (this.string_ == null) {
            this.string_ = generateString();
        }
        return this.string_;
    }

    private String generateString() {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration enumerationElements = this.steps_.elements();
        boolean z = true;
        while (enumerationElements.hasMoreElements()) {
            Step step = (Step) enumerationElements.nextElement();
            if (!z || this.absolute_) {
                stringBuffer.append('/');
                if (step.isMultiLevel()) {
                    stringBuffer.append('/');
                }
            }
            stringBuffer.append(step.toString());
            z = false;
        }
        return stringBuffer.toString();
    }

    public boolean isAbsolute() {
        return this.absolute_;
    }

    public boolean isStringValue() {
        return ((Step) this.steps_.peek()).isStringValue();
    }

    public Enumeration getSteps() {
        return this.steps_.elements();
    }

    public String getIndexingAttrName() throws XPathException {
        BooleanExpr predicate = ((Step) this.steps_.peek()).getPredicate();
        if (!(predicate instanceof AttrExistsExpr)) {
            throw new XPathException(this, "has no indexing attribute name (must end with predicate of the form [@attrName]");
        }
        return ((AttrExistsExpr) predicate).getAttrName();
    }

    public String getIndexingAttrNameOfEquals() throws XPathException {
        BooleanExpr predicate = ((Step) this.steps_.peek()).getPredicate();
        if (predicate instanceof AttrEqualsExpr) {
            return ((AttrEqualsExpr) predicate).getAttrName();
        }
        return null;
    }

    public Object clone() {
        int size = this.steps_.size();
        Step[] stepArr = new Step[size];
        Enumeration enumerationElements = this.steps_.elements();
        for (int i = 0; i < size; i++) {
            stepArr[i] = (Step) enumerationElements.nextElement();
        }
        return new XPath(this.absolute_, stepArr);
    }

    public static XPath get(String str) throws XPathException {
        XPath xPath;
        synchronized (cache_) {
            xPath = (XPath) cache_.get(str);
            if (xPath == null) {
                xPath = new XPath(str);
                cache_.put(str, xPath);
            }
        }
        return xPath;
    }

    public static XPath get(boolean z, Step[] stepArr) {
        XPath xPath = new XPath(z, stepArr);
        String string = xPath.toString();
        synchronized (cache_) {
            XPath xPath2 = (XPath) cache_.get(string);
            if (xPath2 != null) {
                return xPath2;
            }
            cache_.put(string, xPath);
            return xPath;
        }
    }

    public static boolean isStringValue(String str) throws XPathException, IOException {
        return get(str).isStringValue();
    }
}
