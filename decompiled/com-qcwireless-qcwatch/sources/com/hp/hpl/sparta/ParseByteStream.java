package com.hp.hpl.sparta;

import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
class ParseByteStream implements ParseSource {
    private ParseCharStream parseSource_;

    public ParseByteStream(String str, InputStream inputStream, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, IOException {
        ParseLog parseLog2 = parseLog == null ? DEFAULT_LOG : parseLog;
        if (!inputStream.markSupported()) {
            throw new Error("Precondition violation: the InputStream passed to ParseByteStream must support mark");
        }
        inputStream.mark(MAXLOOKAHEAD);
        byte[] bArr = new byte[4];
        String strGuessEncoding = str2 == null ? guessEncoding(str, bArr, inputStream.read(bArr), parseLog2) : str2;
        try {
            inputStream.reset();
            try {
                this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding(strGuessEncoding)), parseLog2, strGuessEncoding, parseHandler);
            } catch (IOException unused) {
                parseLog2.note("Problem reading with assumed encoding of " + strGuessEncoding + " so restarting with euc-jp", str, 1);
                inputStream.reset();
                try {
                    this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding("euc-jp")), parseLog2, (String) null, parseHandler);
                } catch (UnsupportedEncodingException unused2) {
                    throw new ParseException(parseLog2, str, 1, 0, "euc-jp", "\"euc-jp\" is not a supported encoding");
                }
            }
        } catch (EncodingMismatchException e) {
            String declaredEncoding = e.getDeclaredEncoding();
            parseLog2.note("Encoding declaration of " + declaredEncoding + " is different that assumed " + strGuessEncoding + " so restarting the parsing with the new encoding", str, 1);
            inputStream.reset();
            try {
                this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding(declaredEncoding)), parseLog2, (String) null, parseHandler);
            } catch (UnsupportedEncodingException unused3) {
                throw new ParseException(parseLog2, str, 1, 0, declaredEncoding, "\"" + declaredEncoding + "\" is not a supported encoding");
            }
        }
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public String toString() {
        return this.parseSource_.toString();
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public String getSystemId() {
        return this.parseSource_.getSystemId();
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public int getLineNumber() {
        return this.parseSource_.getLineNumber();
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String guessEncoding(java.lang.String r5, byte[] r6, int r7, com.hp.hpl.sparta.ParseLog r8) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.ParseByteStream.guessEncoding(java.lang.String, byte[], int, com.hp.hpl.sparta.ParseLog):java.lang.String");
    }

    private static String hex(byte b) {
        String hexString = Integer.toHexString(b);
        int length = hexString.length();
        if (length != 1) {
            return length != 2 ? hexString.substring(hexString.length() - 2) : hexString;
        }
        return "0" + hexString;
    }

    private static boolean equals(byte[] bArr, int i) {
        return bArr[0] == ((byte) (i >>> 24)) && bArr[1] == ((byte) ((i >>> 16) & 255)) && bArr[2] == ((byte) ((i >>> 8) & 255)) && bArr[3] == ((byte) (i & 255));
    }

    private static boolean equals(byte[] bArr, short s) {
        return bArr[0] == ((byte) (s >>> 8)) && bArr[1] == ((byte) (s & 255));
    }

    private static String fixEncoding(String str) {
        return str.toLowerCase().equals("utf8") ? Key.STRING_CHARSET_NAME : str;
    }
}
