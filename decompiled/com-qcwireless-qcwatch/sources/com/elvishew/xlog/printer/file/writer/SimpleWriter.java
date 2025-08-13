package com.elvishew.xlog.printer.file.writer;

import com.elvishew.xlog.internal.Platform;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes2.dex */
public class SimpleWriter extends Writer {
    private BufferedWriter bufferedWriter;
    private File logFile;
    private String logFileName;

    public void onNewFileCreated(File file) {
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public boolean open(File file) throws IOException {
        boolean z;
        this.logFileName = file.getName();
        this.logFile = file;
        if (file.exists()) {
            z = false;
        } else {
            try {
                File parentFile = this.logFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.logFile.createNewFile();
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                close();
                return false;
            }
        }
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.logFile, true));
            if (z) {
                onNewFileCreated(this.logFile);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            close();
            return false;
        }
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public boolean isOpened() {
        return this.bufferedWriter != null && this.logFile.exists();
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public File getOpenedFile() {
        return this.logFile;
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public String getOpenedFileName() {
        return this.logFileName;
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public void appendLog(String str) throws IOException {
        try {
            this.bufferedWriter.write(str);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (Exception e) {
            Platform.get().warn("append log failed: " + e.getMessage());
        }
    }

    @Override // com.elvishew.xlog.printer.file.writer.Writer
    public boolean close() throws IOException {
        BufferedWriter bufferedWriter = this.bufferedWriter;
        if (bufferedWriter != null) {
            try {
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.bufferedWriter = null;
        this.logFileName = null;
        this.logFile = null;
        return true;
    }
}
