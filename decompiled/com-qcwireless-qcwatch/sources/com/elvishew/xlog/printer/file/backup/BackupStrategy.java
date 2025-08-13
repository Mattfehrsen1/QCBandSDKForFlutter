package com.elvishew.xlog.printer.file.backup;

import java.io.File;

/* loaded from: classes2.dex */
public interface BackupStrategy {
    boolean shouldBackup(File file);
}
