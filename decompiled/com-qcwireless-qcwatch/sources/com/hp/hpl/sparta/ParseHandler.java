package com.hp.hpl.sparta;

/* loaded from: classes2.dex */
public interface ParseHandler {
    void characters(char[] cArr, int i, int i2) throws ParseException;

    void endDocument() throws ParseException;

    void endElement(Element element) throws ParseException;

    ParseSource getParseSource();

    void setParseSource(ParseSource parseSource);

    void startDocument() throws ParseException;

    void startElement(Element element) throws ParseException;
}
