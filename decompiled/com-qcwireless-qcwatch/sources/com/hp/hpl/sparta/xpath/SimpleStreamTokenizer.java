package com.hp.hpl.sparta.xpath;

import java.io.IOException;
import java.io.Reader;

/* loaded from: classes2.dex */
public class SimpleStreamTokenizer {
    private static final int QUOTE = -6;
    public static final int TT_EOF = -1;
    public static final int TT_NUMBER = -2;
    public static final int TT_WORD = -3;
    private static final int WHITESPACE = -5;
    private int nextType_;
    private final Reader reader_;
    public int ttype = Integer.MIN_VALUE;
    public int nval = Integer.MIN_VALUE;
    public String sval = "";
    private final StringBuffer buf_ = new StringBuffer();
    private final int[] charType_ = new int[256];
    private boolean pushedBack_ = false;
    private char inQuote_ = 0;

    public String toString() {
        int i = this.ttype;
        if (i != -3) {
            if (i == -2) {
                return Integer.toString(this.nval);
            }
            if (i == -1) {
                return "(EOF)";
            }
            if (i != 34) {
                if (i == 39) {
                    return "'" + this.sval + "'";
                }
                return "'" + ((char) this.ttype) + "'";
            }
        }
        return "\"" + this.sval + "\"";
    }

    public SimpleStreamTokenizer(Reader reader) throws IOException {
        char c = 0;
        this.reader_ = reader;
        while (true) {
            int[] iArr = this.charType_;
            if (c >= iArr.length) {
                nextToken();
                return;
            }
            if (('A' <= c && c <= 'Z') || (('a' <= c && c <= 'z') || c == '-')) {
                iArr[c] = -3;
            } else if ('0' <= c && c <= '9') {
                iArr[c] = -2;
            } else if (c >= 0 && c <= ' ') {
                iArr[c] = -5;
            } else {
                iArr[c] = c;
            }
            c = (char) (c + 1);
        }
    }

    public void ordinaryChar(char c) {
        this.charType_[c] = c;
    }

    public void wordChars(char c, char c2) {
        while (c <= c2) {
            this.charType_[c] = -3;
            c = (char) (c + 1);
        }
    }

    public int nextToken() throws IOException {
        int i;
        int i2;
        char c;
        boolean z;
        boolean z2;
        int i3;
        if (this.pushedBack_) {
            this.pushedBack_ = false;
            return this.ttype;
        }
        this.ttype = this.nextType_;
        do {
            boolean z3 = false;
            do {
                i = this.reader_.read();
                if (i == -1) {
                    if (this.inQuote_ != 0) {
                        throw new IOException("Unterminated quote");
                    }
                    i2 = -1;
                } else {
                    i2 = this.charType_[i];
                }
                c = this.inQuote_;
                z = c == 0 && i2 == -5;
                z3 = z3 || z;
            } while (z);
            if (i2 == 39 || i2 == 34) {
                if (c == 0) {
                    this.inQuote_ = (char) i2;
                } else if (c == i2) {
                    this.inQuote_ = (char) 0;
                }
            }
            char c2 = this.inQuote_;
            if (c2 != 0) {
                i2 = c2;
            }
            z2 = z3 || !(((i3 = this.ttype) < -1 || i3 == 39 || i3 == 34) && i3 == i2);
            if (z2) {
                int i4 = this.ttype;
                if (i4 == -3) {
                    this.sval = this.buf_.toString();
                    this.buf_.setLength(0);
                } else if (i4 == -2) {
                    this.nval = Integer.parseInt(this.buf_.toString());
                    this.buf_.setLength(0);
                } else if (i4 == 34 || i4 == 39) {
                    this.sval = this.buf_.toString().substring(1, this.buf_.length() - 1);
                    this.buf_.setLength(0);
                }
                if (i2 != -5) {
                    this.nextType_ = i2 == -6 ? i : i2;
                }
            }
            if (i2 == -3 || i2 == -2 || i2 == 34 || i2 == 39) {
                this.buf_.append((char) i);
            }
        } while (!z2);
        return this.ttype;
    }

    public void pushBack() {
        this.pushedBack_ = true;
    }
}
