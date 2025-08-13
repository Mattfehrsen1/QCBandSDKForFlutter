package com.hp.hpl.sparta;

/* loaded from: classes2.dex */
public class DefaultParseHandler implements ParseHandler {
    private ParseSource parseSource_ = null;

    @Override // com.hp.hpl.sparta.ParseHandler
    public void characters(char[] cArr, int i, int i2) throws ParseException {
    }

    @Override // com.hp.hpl.sparta.ParseHandler
    public void endDocument() throws ParseException {
    }

    @Override // com.hp.hpl.sparta.ParseHandler
    public void endElement(Element element) throws ParseException {
    }

    @Override // com.hp.hpl.sparta.ParseHandler
    public void startDocument() throws ParseException {
    }

    @Override // com.hp.hpl.sparta.ParseHandler
    public void startElement(Element element) throws ParseException {
    }

    @Override // com.hp.hpl.sparta.ParseHandler
    public void setParseSource(ParseSource parseSource) {
        this.parseSource_ = parseSource;
    }

    @Override // com.hp.hpl.sparta.ParseHandler
    public ParseSource getParseSource() {
        return this.parseSource_;
    }
}
