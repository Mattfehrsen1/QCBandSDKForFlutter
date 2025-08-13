package com.oudmon.ble.base.util;

import android.app.Application;
import android.text.TextUtils;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

/* loaded from: classes3.dex */
public class LogToFile {
    private static LogToFile mInstance;
    public boolean debug = true;
    private String logPath;
    private String notifyPath;
    private String statusPath;
    private String writePath;

    public static LogToFile getInstance() {
        if (mInstance == null) {
            synchronized (LogToFile.class) {
                if (mInstance == null) {
                    mInstance = new LogToFile();
                }
            }
        }
        return mInstance;
    }

    public void initPath(Application application) {
        this.logPath = application.getExternalFilesDir("") + "/log/" + new DateLogUtil().getY_M_D() + "debug_log.txt";
        this.statusPath = application.getExternalFilesDir("") + "/log/" + new DateLogUtil().getY_M_D() + "ble_status.txt";
        this.notifyPath = application.getExternalFilesDir("") + "/log/" + new DateLogUtil().getY_M_D() + "ble_notify.txt";
        this.writePath = application.getExternalFilesDir("") + "/log/" + new DateLogUtil().getY_M_D() + "ble_write.txt";
        StringBuilder sb = new StringBuilder();
        sb.append(application.getExternalFilesDir(""));
        sb.append("/log/");
        createDirs(sb.toString());
    }

    public boolean createDirs(String str) {
        File file = new File(str);
        if (file.exists()) {
            return false;
        }
        file.mkdirs();
        return true;
    }

    public void wtf(String str) throws Throwable {
        if (this.logPath != null && this.debug) {
            writeToFile(new DateLogUtil().getY_M_D_H_M_S() + ":" + str + "\n", this.logPath);
        }
    }

    public void wtfStatus(String str) throws Throwable {
        if (this.statusPath != null && this.debug) {
            writeToFile(new DateLogUtil().getY_M_D_H_M_S() + ":" + str + "\n", this.statusPath);
        }
    }

    public void wtfWrite(String str) throws Throwable {
        if (this.writePath != null && this.debug) {
            writeToFile(new DateLogUtil().getY_M_D_H_M_S() + ":" + str + "\n", this.writePath);
        }
    }

    public void wtfNotify(String str) throws Throwable {
        if (this.notifyPath != null && this.debug) {
            writeToFile(new DateLogUtil().getY_M_D_H_M_S() + ":" + str + "\n", this.notifyPath);
        }
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }

    private void writeToFile(String str, String str2) throws Throwable {
        if (TextUtils.isEmpty(str2)) {
            throw new RuntimeException("Log File Path must not be empty");
        }
        File file = new File(str2);
        File parentFile = file.getParentFile();
        Objects.requireNonNull(parentFile);
        if (!parentFile.exists()) {
            file.getParentFile().mkdirs();
        }
        writeToFile(file, str);
    }

    public void writeToFile(File file, String str) throws Throwable {
        FileWriter fileWriter;
        FileWriter fileWriter2 = null;
        try {
            try {
                try {
                    fileWriter = new FileWriter(file, true);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileWriter.write(str);
                fileWriter.write("\r\n");
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e2) {
                e = e2;
                fileWriter2 = fileWriter;
                e.printStackTrace();
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileWriter2 = fileWriter;
                if (fileWriter2 != null) {
                    try {
                        fileWriter2.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
}
