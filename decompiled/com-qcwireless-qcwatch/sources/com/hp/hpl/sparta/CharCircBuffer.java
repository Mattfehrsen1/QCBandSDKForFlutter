package com.hp.hpl.sparta;

/* loaded from: classes2.dex */
class CharCircBuffer {
    private final int[] buf_;
    private int next_ = 0;
    private int total_ = 0;
    private boolean enabled_ = true;

    CharCircBuffer(int i) {
        this.buf_ = new int[i];
    }

    void enable() {
        this.enabled_ = true;
    }

    void disable() {
        this.enabled_ = false;
    }

    void addInt(int i) {
        addRaw(i + 65536);
    }

    void addChar(char c) {
        addRaw(c);
    }

    private void addRaw(int i) {
        if (this.enabled_) {
            int[] iArr = this.buf_;
            int i2 = this.next_;
            iArr[i2] = i;
            this.next_ = (i2 + 1) % iArr.length;
            this.total_++;
        }
    }

    void addString(String str) {
        for (char c : str.toCharArray()) {
            addChar(c);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer((this.buf_.length * 11) / 10);
        int i = this.total_;
        int[] iArr = this.buf_;
        int length = i < iArr.length ? iArr.length - i : 0;
        while (true) {
            int[] iArr2 = this.buf_;
            if (length < iArr2.length) {
                int i2 = iArr2[(this.next_ + length) % iArr2.length];
                if (i2 < 65536) {
                    stringBuffer.append((char) i2);
                } else {
                    stringBuffer.append(Integer.toString(i2 - 65536));
                }
                length++;
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
