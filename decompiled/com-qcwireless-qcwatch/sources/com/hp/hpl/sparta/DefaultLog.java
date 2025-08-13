package com.hp.hpl.sparta;

/* compiled from: ParseSource.java */
/* loaded from: classes2.dex */
class DefaultLog implements ParseLog {
    DefaultLog() {
    }

    @Override // com.hp.hpl.sparta.ParseLog
    public void error(String str, String str2, int i) {
        System.err.println(str2 + "(" + i + "): " + str + " (ERROR)");
    }

    @Override // com.hp.hpl.sparta.ParseLog
    public void warning(String str, String str2, int i) {
        System.out.println(str2 + "(" + i + "): " + str + " (WARNING)");
    }

    @Override // com.hp.hpl.sparta.ParseLog
    public void note(String str, String str2, int i) {
        System.out.println(str2 + "(" + i + "): " + str + " (NOTE)");
    }
}
