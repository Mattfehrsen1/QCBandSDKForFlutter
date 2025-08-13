package com.hp.hpl.sparta;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
class ParseCharStream implements ParseSource {
    private static final char[] BEGIN_CDATA;
    private static final char[] BEGIN_ETAG;
    private static final char[] CHARREF_BEGIN;
    private static final char[] COMMENT_BEGIN;
    private static final char[] COMMENT_END;
    private static final boolean DEBUG = true;
    private static final char[] DOCTYPE_BEGIN;
    private static final char[] ENCODING;
    private static final char[] END_CDATA;
    private static final char[] END_EMPTYTAG;
    private static final char[] ENTITY_BEGIN;
    public static final int HISTORY_LENGTH = 100;
    private static final boolean H_DEBUG = false;
    private static final char[] MARKUPDECL_BEGIN;
    private static final int MAX_COMMON_CHAR = 128;
    private static final char[] NDATA;
    private static final char[] PI_BEGIN;
    private static final char[] PUBLIC;
    private static final char[] QU_END;
    private static final char[] SYSTEM;
    private static final int TMP_BUF_SIZE = 255;
    private static final char[] VERSION;
    private static final char[] VERSIONNUM_PUNC_CHARS;
    private static final char[] XML_BEGIN;
    private final int CBUF_SIZE;
    private final char[] cbuf_;
    private int ch_;
    private int curPos_;
    private String docTypeName_;
    private final String encoding_;
    private int endPos_;
    private final Hashtable entities_;
    private boolean eos_;
    private final ParseHandler handler_;
    private final CharCircBuffer history_;
    private boolean isExternalDtd_;
    private int lineNumber_;
    private final ParseLog log_;
    private final Hashtable pes_;
    private final Reader reader_;
    private String systemId_;
    private final char[] tmpBuf_;
    private static final char[] NAME_PUNCT_CHARS = {'.', '-', '_', ':'};
    private static final boolean[] IS_NAME_CHAR = new boolean[128];

    private static boolean isExtender(char c) {
        if (c == 183 || c == 903 || c == 1600 || c == 3654 || c == 3782 || c == 12293 || c == 720 || c == 721 || c == 12445 || c == 12446) {
            return true;
        }
        switch (c) {
            case 12337:
            case 12338:
            case 12339:
            case 12340:
            case 12341:
                return true;
            default:
                switch (c) {
                    case 12540:
                    case 12541:
                    case 12542:
                        return true;
                    default:
                        return false;
                }
        }
    }

    final String getHistory() {
        return "";
    }

    public ParseCharStream(String str, char[] cArr, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, IOException {
        this(str, null, cArr, parseLog, str2, parseHandler);
    }

    public ParseCharStream(String str, Reader reader, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, IOException {
        this(str, reader, null, parseLog, str2, parseHandler);
    }

    public ParseCharStream(String str, Reader reader, char[] cArr, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, IOException {
        this.docTypeName_ = null;
        Hashtable hashtable = new Hashtable();
        this.entities_ = hashtable;
        this.pes_ = new Hashtable();
        this.ch_ = -2;
        this.isExternalDtd_ = false;
        this.CBUF_SIZE = 1024;
        this.curPos_ = 0;
        this.endPos_ = 0;
        this.eos_ = false;
        this.tmpBuf_ = new char[255];
        this.lineNumber_ = -1;
        this.lineNumber_ = 1;
        this.history_ = null;
        parseLog = parseLog == null ? DEFAULT_LOG : parseLog;
        this.log_ = parseLog;
        this.encoding_ = str2 == null ? null : str2.toLowerCase();
        hashtable.put("lt", "<");
        hashtable.put("gt", ">");
        hashtable.put("amp", "&");
        hashtable.put("apos", "'");
        hashtable.put("quot", "\"");
        if (cArr != null) {
            this.cbuf_ = cArr;
            this.curPos_ = 0;
            this.endPos_ = cArr.length;
            this.eos_ = true;
            this.reader_ = null;
        } else {
            this.reader_ = reader;
            this.cbuf_ = new char[1024];
            fillBuf();
        }
        this.systemId_ = str;
        this.handler_ = parseHandler;
        parseHandler.setParseSource(this);
        readProlog();
        parseHandler.startDocument();
        Element element = readElement();
        String str3 = this.docTypeName_;
        if (str3 != null && !str3.equals(element.getTagName())) {
            parseLog.warning("DOCTYPE name \"" + this.docTypeName_ + "\" not same as tag name, \"" + element.getTagName() + "\" of root element", this.systemId_, getLineNumber());
        }
        while (isMisc()) {
            readMisc();
        }
        Reader reader2 = this.reader_;
        if (reader2 != null) {
            reader2.close();
        }
        this.handler_.endDocument();
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public String toString() {
        return this.systemId_;
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public String getSystemId() {
        return this.systemId_;
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public int getLineNumber() {
        return this.lineNumber_;
    }

    int getLastCharRead() {
        return this.ch_;
    }

    private int fillBuf() throws IOException {
        if (this.eos_) {
            return -1;
        }
        int i = this.endPos_;
        char[] cArr = this.cbuf_;
        if (i == cArr.length) {
            this.endPos_ = 0;
            this.curPos_ = 0;
        }
        Reader reader = this.reader_;
        int i2 = this.endPos_;
        int i3 = reader.read(cArr, i2, cArr.length - i2);
        if (i3 <= 0) {
            this.eos_ = true;
            return -1;
        }
        this.endPos_ += i3;
        return i3;
    }

    private int fillBuf(int i) throws IOException {
        int i2;
        int i3;
        if (this.eos_) {
            return -1;
        }
        int i4 = 0;
        if (this.cbuf_.length - this.curPos_ < i) {
            int i5 = 0;
            while (true) {
                i2 = this.curPos_;
                int i6 = i2 + i5;
                i3 = this.endPos_;
                if (i6 >= i3) {
                    break;
                }
                char[] cArr = this.cbuf_;
                cArr[i5] = cArr[i2 + i5];
                i5++;
            }
            int i7 = i3 - i2;
            this.endPos_ = i7;
            this.curPos_ = 0;
            i4 = i7;
        }
        int iFillBuf = fillBuf();
        if (iFillBuf != -1) {
            return i4 + iFillBuf;
        }
        if (i4 == 0) {
            return -1;
        }
        return i4;
    }

    private final char readChar() throws ParseException, IOException {
        if (this.curPos_ >= this.endPos_ && fillBuf() == -1) {
            throw new ParseException(this, "unexpected end of expression.");
        }
        char[] cArr = this.cbuf_;
        int i = this.curPos_;
        if (cArr[i] == '\n') {
            this.lineNumber_++;
        }
        this.curPos_ = i + 1;
        return cArr[i];
    }

    private final char peekChar() throws ParseException, IOException {
        if (this.curPos_ < this.endPos_ || fillBuf() != -1) {
            return this.cbuf_[this.curPos_];
        }
        throw new ParseException(this, "unexpected end of expression.");
    }

    private final void readChar(char c) throws ParseException, IOException {
        char c2 = readChar();
        if (c2 != c) {
            throw new ParseException(this, c2, c);
        }
    }

    private final boolean isChar(char c) throws ParseException, IOException {
        if (this.curPos_ < this.endPos_ || fillBuf() != -1) {
            return this.cbuf_[this.curPos_] == c;
        }
        throw new ParseException(this, "unexpected end of expression.");
    }

    private final char readChar(char c, char c2) throws ParseException, IOException {
        char c3 = readChar();
        if (c3 == c || c3 == c2) {
            return c3;
        }
        throw new ParseException(this, c3, new char[]{c, c2});
    }

    private final char readChar(char c, char c2, char c3, char c4) throws ParseException, IOException {
        char c5 = readChar();
        if (c5 == c || c5 == c2 || c5 == c3 || c5 == c4) {
            return c5;
        }
        throw new ParseException(this, c5, new char[]{c, c2, c3, c4});
    }

    private final boolean isChar(char c, char c2) throws ParseException, IOException {
        if (this.curPos_ >= this.endPos_ && fillBuf() == -1) {
            return false;
        }
        char c3 = this.cbuf_[this.curPos_];
        return c3 == c || c3 == c2;
    }

    private final boolean isChar(char c, char c2, char c3, char c4) throws ParseException, IOException {
        if (this.curPos_ >= this.endPos_ && fillBuf() == -1) {
            return false;
        }
        char c5 = this.cbuf_[this.curPos_];
        return c5 == c || c5 == c2 || c5 == c3 || c5 == c4;
    }

    private static final boolean isIn(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private final void readS() throws ParseException, IOException {
        readChar(' ', '\t', '\r', '\n');
        while (isChar(' ', '\t', '\r', '\n')) {
            readChar();
        }
    }

    private final boolean isS() throws ParseException, IOException {
        return isChar(' ', '\t', '\r', '\n');
    }

    static {
        for (char c = 0; c < 128; c = (char) (c + 1)) {
            IS_NAME_CHAR[c] = isNameChar(c);
        }
        COMMENT_BEGIN = "<!--".toCharArray();
        COMMENT_END = "-->".toCharArray();
        PI_BEGIN = "<?".toCharArray();
        QU_END = "?>".toCharArray();
        DOCTYPE_BEGIN = "<!DOCTYPE".toCharArray();
        XML_BEGIN = "<?xml".toCharArray();
        ENCODING = "encoding".toCharArray();
        VERSION = "version".toCharArray();
        VERSIONNUM_PUNC_CHARS = new char[]{'_', '.', ':', '-'};
        MARKUPDECL_BEGIN = "<!".toCharArray();
        CHARREF_BEGIN = "&#".toCharArray();
        ENTITY_BEGIN = "<!ENTITY".toCharArray();
        NDATA = "NDATA".toCharArray();
        SYSTEM = "SYSTEM".toCharArray();
        PUBLIC = "PUBLIC".toCharArray();
        BEGIN_CDATA = "<![CDATA[".toCharArray();
        END_CDATA = "]]>".toCharArray();
        END_EMPTYTAG = "/>".toCharArray();
        BEGIN_ETAG = "</".toCharArray();
    }

    private boolean isNameChar() throws ParseException, IOException {
        char cPeekChar = peekChar();
        return cPeekChar < 128 ? IS_NAME_CHAR[cPeekChar] : isNameChar(cPeekChar);
    }

    private static boolean isLetter(char c) {
        return "abcdefghijklmnopqrstuvwxyz".indexOf(Character.toLowerCase(c)) != -1;
    }

    private static boolean isNameChar(char c) {
        return Character.isDigit(c) || isLetter(c) || isIn(c, NAME_PUNCT_CHARS) || isExtender(c);
    }

    private final String readName() throws ParseException, IOException {
        this.tmpBuf_[0] = readNameStartChar();
        int i = 1;
        StringBuffer stringBuffer = null;
        while (isNameChar()) {
            if (i >= 255) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(i);
                    stringBuffer.append(this.tmpBuf_, 0, i);
                } else {
                    stringBuffer.append(this.tmpBuf_, 0, i);
                }
                i = 0;
            }
            this.tmpBuf_[i] = readChar();
            i++;
        }
        if (stringBuffer == null) {
            return Sparta.intern(new String(this.tmpBuf_, 0, i));
        }
        stringBuffer.append(this.tmpBuf_, 0, i);
        return stringBuffer.toString();
    }

    private char readNameStartChar() throws ParseException, IOException {
        char c = readChar();
        if (isLetter(c) || c == '_' || c == ':') {
            return c;
        }
        throw new ParseException(this, c, "letter, underscore, colon");
    }

    private final String readEntityValue() throws ParseException, IOException {
        char c = readChar('\'', Typography.quote);
        StringBuffer stringBuffer = new StringBuffer();
        while (!isChar(c)) {
            if (isPeReference()) {
                stringBuffer.append(readPeReference());
            } else if (isReference()) {
                stringBuffer.append(readReference());
            } else {
                stringBuffer.append(readChar());
            }
        }
        readChar(c);
        return stringBuffer.toString();
    }

    private final boolean isEntityValue() throws ParseException, IOException {
        return isChar('\'', Typography.quote);
    }

    private final void readSystemLiteral() throws ParseException, IOException {
        char c = readChar();
        while (peekChar() != c) {
            readChar();
        }
        readChar(c);
    }

    private final void readPubidLiteral() throws ParseException, IOException {
        readSystemLiteral();
    }

    private boolean isMisc() throws ParseException, IOException {
        return isComment() || isPi() || isS();
    }

    private void readMisc() throws ParseException, IOException {
        if (isComment()) {
            readComment();
        } else if (isPi()) {
            readPi();
        } else {
            if (isS()) {
                readS();
                return;
            }
            throw new ParseException(this, "expecting comment or processing instruction or space");
        }
    }

    private final void readComment() throws ParseException, IOException {
        readSymbol(COMMENT_BEGIN);
        while (true) {
            char[] cArr = COMMENT_END;
            if (!isSymbol(cArr)) {
                readChar();
            } else {
                readSymbol(cArr);
                return;
            }
        }
    }

    private final boolean isComment() throws ParseException, IOException {
        return isSymbol(COMMENT_BEGIN);
    }

    private final void readPi() throws ParseException, IOException {
        readSymbol(PI_BEGIN);
        while (true) {
            char[] cArr = QU_END;
            if (!isSymbol(cArr)) {
                readChar();
            } else {
                readSymbol(cArr);
                return;
            }
        }
    }

    private final boolean isPi() throws ParseException, IOException {
        return isSymbol(PI_BEGIN);
    }

    private void readProlog() throws ParseException, IOException {
        if (isXmlDecl()) {
            readXmlDecl();
        }
        while (isMisc()) {
            readMisc();
        }
        if (isDocTypeDecl()) {
            readDocTypeDecl();
            while (isMisc()) {
                readMisc();
            }
        }
    }

    private boolean isDocTypeDecl() throws ParseException, IOException {
        return isSymbol(DOCTYPE_BEGIN);
    }

    private void readXmlDecl() throws ParseException, IOException {
        readSymbol(XML_BEGIN);
        readVersionInfo();
        if (isS()) {
            readS();
        }
        if (isEncodingDecl()) {
            String encodingDecl = readEncodingDecl();
            if (this.encoding_ != null && !encodingDecl.toLowerCase().equals(this.encoding_)) {
                throw new EncodingMismatchException(this.systemId_, encodingDecl, this.encoding_);
            }
        }
        while (true) {
            char[] cArr = QU_END;
            if (!isSymbol(cArr)) {
                readChar();
            } else {
                readSymbol(cArr);
                return;
            }
        }
    }

    private boolean isXmlDecl() throws ParseException, IOException {
        return isSymbol(XML_BEGIN);
    }

    private boolean isEncodingDecl() throws ParseException, IOException {
        return isSymbol(ENCODING);
    }

    private String readEncodingDecl() throws ParseException, IOException {
        readSymbol(ENCODING);
        readEq();
        char c = readChar('\'', Typography.quote);
        StringBuffer stringBuffer = new StringBuffer();
        while (!isChar(c)) {
            stringBuffer.append(readChar());
        }
        readChar(c);
        return stringBuffer.toString();
    }

    private void readVersionInfo() throws ParseException, IOException {
        readS();
        readSymbol(VERSION);
        readEq();
        char c = readChar('\'', Typography.quote);
        readVersionNum();
        readChar(c);
    }

    private final void readEq() throws ParseException, IOException {
        if (isS()) {
            readS();
        }
        readChar('=');
        if (isS()) {
            readS();
        }
    }

    private boolean isVersionNumChar() throws ParseException, IOException {
        char cPeekChar = peekChar();
        return Character.isDigit(cPeekChar) || ('a' <= cPeekChar && cPeekChar <= 'z') || (('Z' <= cPeekChar && cPeekChar <= 'Z') || isIn(cPeekChar, VERSIONNUM_PUNC_CHARS));
    }

    private void readVersionNum() throws ParseException, IOException {
        readChar();
        while (isVersionNumChar()) {
            readChar();
        }
    }

    private void readDocTypeDecl() throws ParseException, IOException {
        readSymbol(DOCTYPE_BEGIN);
        readS();
        this.docTypeName_ = readName();
        if (isS()) {
            readS();
            if (!isChar(Typography.greater) && !isChar('[')) {
                this.isExternalDtd_ = true;
                readExternalId();
                if (isS()) {
                    readS();
                }
            }
        }
        if (isChar('[')) {
            readChar();
            while (!isChar(']')) {
                if (isDeclSep()) {
                    readDeclSep();
                } else {
                    readMarkupDecl();
                }
            }
            readChar(']');
            if (isS()) {
                readS();
            }
        }
        readChar(Typography.greater);
    }

    private void readDeclSep() throws ParseException, IOException {
        if (isPeReference()) {
            readPeReference();
        } else {
            readS();
        }
    }

    private boolean isDeclSep() throws ParseException, IOException {
        return isPeReference() || isS();
    }

    private void readMarkupDecl() throws ParseException, IOException {
        if (isPi()) {
            readPi();
            return;
        }
        if (isComment()) {
            readComment();
            return;
        }
        if (isEntityDecl()) {
            readEntityDecl();
            return;
        }
        if (isSymbol(MARKUPDECL_BEGIN)) {
            while (!isChar(Typography.greater)) {
                if (isChar('\'', Typography.quote)) {
                    char c = readChar();
                    while (!isChar(c)) {
                        readChar();
                    }
                    readChar(c);
                } else {
                    readChar();
                }
            }
            readChar(Typography.greater);
            return;
        }
        throw new ParseException(this, "expecting processing instruction, comment, or \"<!\"");
    }

    private char readCharRef() throws ParseException, IOException {
        int i;
        readSymbol(CHARREF_BEGIN);
        if (isChar('x')) {
            readChar();
            i = 16;
        } else {
            i = 10;
        }
        int i2 = 0;
        while (!isChar(';')) {
            int i3 = i2 + 1;
            this.tmpBuf_[i2] = readChar();
            if (i3 >= 255) {
                this.log_.warning("Tmp buffer overflow on readCharRef", this.systemId_, getLineNumber());
                return ' ';
            }
            i2 = i3;
        }
        readChar(';');
        String str = new String(this.tmpBuf_, 0, i2);
        try {
            return (char) Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            ParseLog parseLog = this.log_;
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(str);
            sb.append("\" is not a valid ");
            sb.append(i == 16 ? "hexadecimal" : "decimal");
            sb.append(" number");
            parseLog.warning(sb.toString(), this.systemId_, getLineNumber());
            return ' ';
        }
    }

    private final char[] readReference() throws ParseException, IOException {
        return isSymbol(CHARREF_BEGIN) ? new char[]{readCharRef()} : readEntityRef().toCharArray();
    }

    private final boolean isReference() throws ParseException, IOException {
        return isChar(Typography.amp);
    }

    private String readEntityRef() throws ParseException, IOException {
        readChar(Typography.amp);
        String name = readName();
        String str = (String) this.entities_.get(name);
        if (str == null) {
            if (this.isExternalDtd_) {
                this.log_.warning("&" + name + "; not found -- possibly defined in external DTD)", this.systemId_, getLineNumber());
            } else {
                this.log_.warning("No declaration of &" + name + ";", this.systemId_, getLineNumber());
            }
            str = "";
        }
        readChar(';');
        return str;
    }

    private String readPeReference() throws ParseException, IOException {
        readChar('%');
        String name = readName();
        String str = (String) this.pes_.get(name);
        if (str == null) {
            this.log_.warning("No declaration of %" + name + ";", this.systemId_, getLineNumber());
            str = "";
        }
        readChar(';');
        return str;
    }

    private boolean isPeReference() throws ParseException, IOException {
        return isChar('%');
    }

    private void readEntityDecl() throws ParseException, IOException {
        String externalId;
        String externalId2;
        readSymbol(ENTITY_BEGIN);
        readS();
        if (isChar('%')) {
            readChar('%');
            readS();
            String name = readName();
            readS();
            if (isEntityValue()) {
                externalId2 = readEntityValue();
            } else {
                externalId2 = readExternalId();
            }
            this.pes_.put(name, externalId2);
        } else {
            String name2 = readName();
            readS();
            if (isEntityValue()) {
                externalId = readEntityValue();
            } else if (isExternalId()) {
                externalId = readExternalId();
                if (isS()) {
                    readS();
                }
                char[] cArr = NDATA;
                if (isSymbol(cArr)) {
                    readSymbol(cArr);
                    readS();
                    readName();
                }
            } else {
                throw new ParseException(this, "expecting double-quote, \"PUBLIC\" or \"SYSTEM\" while reading entity declaration");
            }
            this.entities_.put(name2, externalId);
        }
        if (isS()) {
            readS();
        }
        readChar(Typography.greater);
    }

    private boolean isEntityDecl() throws ParseException, IOException {
        return isSymbol(ENTITY_BEGIN);
    }

    private String readExternalId() throws ParseException, IOException {
        char[] cArr = SYSTEM;
        if (isSymbol(cArr)) {
            readSymbol(cArr);
        } else {
            char[] cArr2 = PUBLIC;
            if (isSymbol(cArr2)) {
                readSymbol(cArr2);
                readS();
                readPubidLiteral();
            } else {
                throw new ParseException(this, "expecting \"SYSTEM\" or \"PUBLIC\" while reading external ID");
            }
        }
        readS();
        readSystemLiteral();
        return "(WARNING: external ID not read)";
    }

    private boolean isExternalId() throws ParseException, IOException {
        return isSymbol(SYSTEM) || isSymbol(PUBLIC);
    }

    private final void readSymbol(char[] cArr) throws ParseException, IOException {
        int length = cArr.length;
        if (this.endPos_ - this.curPos_ < length && fillBuf(length) <= 0) {
            this.ch_ = -1;
            throw new ParseException(this, "end of XML file", cArr);
        }
        char[] cArr2 = this.cbuf_;
        int i = this.endPos_;
        this.ch_ = cArr2[i - 1];
        if (i - this.curPos_ < length) {
            throw new ParseException(this, "end of XML file", cArr);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (this.cbuf_[this.curPos_ + i2] != cArr[i2]) {
                throw new ParseException(this, new String(this.cbuf_, this.curPos_, length), cArr);
            }
        }
        this.curPos_ += length;
    }

    private final boolean isSymbol(char[] cArr) throws ParseException, IOException {
        int length = cArr.length;
        if (this.endPos_ - this.curPos_ < length && fillBuf(length) <= 0) {
            this.ch_ = -1;
            return false;
        }
        char[] cArr2 = this.cbuf_;
        int i = this.endPos_;
        this.ch_ = cArr2[i - 1];
        if (i - this.curPos_ < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (this.cbuf_[this.curPos_ + i2] != cArr[i2]) {
                return false;
            }
        }
        return true;
    }

    private String readAttValue() throws ParseException, IOException {
        char c = readChar('\'', Typography.quote);
        StringBuffer stringBuffer = new StringBuffer();
        while (!isChar(c)) {
            if (isReference()) {
                stringBuffer.append(readReference());
            } else {
                stringBuffer.append(readChar());
            }
        }
        readChar(c);
        return stringBuffer.toString();
    }

    private void readPossibleCharData() throws ParseException, IOException {
        int i;
        loop0: while (true) {
            i = 0;
            while (!isChar(Typography.less) && !isChar(Typography.amp) && !isSymbol(END_CDATA)) {
                this.tmpBuf_[i] = readChar();
                if (this.tmpBuf_[i] == '\r' && peekChar() == '\n') {
                    this.tmpBuf_[i] = readChar();
                }
                i++;
                if (i == 255) {
                    break;
                }
            }
            this.handler_.characters(this.tmpBuf_, 0, 255);
        }
        if (i > 0) {
            this.handler_.characters(this.tmpBuf_, 0, i);
        }
    }

    private void readCdSect() throws ParseException, IOException {
        char[] cArr;
        readSymbol(BEGIN_CDATA);
        StringBuffer stringBuffer = null;
        int i = 0;
        while (true) {
            cArr = END_CDATA;
            if (isSymbol(cArr)) {
                break;
            }
            if (i >= 255) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(i);
                    stringBuffer.append(this.tmpBuf_, 0, i);
                } else {
                    stringBuffer.append(this.tmpBuf_, 0, i);
                }
                i = 0;
            }
            this.tmpBuf_[i] = readChar();
            i++;
        }
        readSymbol(cArr);
        if (stringBuffer != null) {
            stringBuffer.append(this.tmpBuf_, 0, i);
            char[] charArray = stringBuffer.toString().toCharArray();
            this.handler_.characters(charArray, 0, charArray.length);
            return;
        }
        this.handler_.characters(this.tmpBuf_, 0, i);
    }

    private boolean isCdSect() throws ParseException, IOException {
        return isSymbol(BEGIN_CDATA);
    }

    private final Element readElement() throws ParseException, IOException {
        Element element = new Element();
        boolean emptyElementTagOrSTag = readEmptyElementTagOrSTag(element);
        this.handler_.startElement(element);
        if (emptyElementTagOrSTag) {
            readContent();
            readETag(element);
        }
        this.handler_.endElement(element);
        return element;
    }

    ParseLog getLog() {
        return this.log_;
    }

    private boolean readEmptyElementTagOrSTag(Element element) throws ParseException, IOException {
        readChar(Typography.less);
        element.setTagName(readName());
        while (isS()) {
            readS();
            if (!isChar('/', Typography.greater)) {
                readAttribute(element);
            }
        }
        if (isS()) {
            readS();
        }
        boolean zIsChar = isChar(Typography.greater);
        if (zIsChar) {
            readChar(Typography.greater);
        } else {
            readSymbol(END_EMPTYTAG);
        }
        return zIsChar;
    }

    private void readAttribute(Element element) throws ParseException, IOException {
        String name = readName();
        readEq();
        String attValue = readAttValue();
        if (element.getAttribute(name) != null) {
            this.log_.warning("Element " + this + " contains attribute " + name + "more than once", this.systemId_, getLineNumber());
        }
        element.setAttribute(name, attValue);
    }

    private void readETag(Element element) throws ParseException, IOException {
        readSymbol(BEGIN_ETAG);
        String name = readName();
        if (!name.equals(element.getTagName())) {
            this.log_.warning("end tag (" + name + ") does not match begin tag (" + element.getTagName() + ")", this.systemId_, getLineNumber());
        }
        if (isS()) {
            readS();
        }
        readChar(Typography.greater);
    }

    private boolean isETag() throws ParseException, IOException {
        return isSymbol(BEGIN_ETAG);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readContent() throws com.hp.hpl.sparta.ParseException, java.io.IOException {
        /*
            r5 = this;
            r5.readPossibleCharData()
            r0 = 1
        L4:
            if (r0 == 0) goto L4d
            boolean r1 = r5.isETag()
            r2 = 0
            if (r1 == 0) goto Lf
        Ld:
            r0 = 0
            goto L49
        Lf:
            boolean r1 = r5.isReference()
            if (r1 == 0) goto L20
            char[] r1 = r5.readReference()
            com.hp.hpl.sparta.ParseHandler r3 = r5.handler_
            int r4 = r1.length
            r3.characters(r1, r2, r4)
            goto L49
        L20:
            boolean r1 = r5.isCdSect()
            if (r1 == 0) goto L2a
            r5.readCdSect()
            goto L49
        L2a:
            boolean r1 = r5.isPi()
            if (r1 == 0) goto L34
            r5.readPi()
            goto L49
        L34:
            boolean r1 = r5.isComment()
            if (r1 == 0) goto L3e
            r5.readComment()
            goto L49
        L3e:
            r1 = 60
            boolean r1 = r5.isChar(r1)
            if (r1 == 0) goto Ld
            r5.readElement()
        L49:
            r5.readPossibleCharData()
            goto L4
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.ParseCharStream.readContent():void");
    }
}
