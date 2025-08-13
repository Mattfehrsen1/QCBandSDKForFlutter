package com.elvishew.xlog.printer.file.naming;

/* loaded from: classes2.dex */
public class ChangelessFileNameGenerator implements FileNameGenerator {
    private final String fileName;

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public boolean isFileNameChangeable() {
        return false;
    }

    public ChangelessFileNameGenerator(String str) {
        this.fileName = str;
    }

    @Override // com.elvishew.xlog.printer.file.naming.FileNameGenerator
    public String generateFileName(int i, long j) {
        return this.fileName;
    }
}
