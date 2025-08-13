package com.liulishuo.okdownload.core.file;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;

/* loaded from: classes3.dex */
public class CustomProcessFileStrategy extends ProcessFileStrategy {
    @Override // com.liulishuo.okdownload.core.file.ProcessFileStrategy
    public MultiPointOutputStream createProcessStream(DownloadTask task, BreakpointInfo info, DownloadStore store) {
        return new CustomMultiPointOutputStream(task, info, store);
    }
}
