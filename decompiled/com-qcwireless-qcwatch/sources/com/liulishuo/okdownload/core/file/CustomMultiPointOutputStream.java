package com.liulishuo.okdownload.core.file;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import java.io.IOException;

/* loaded from: classes3.dex */
public class CustomMultiPointOutputStream extends MultiPointOutputStream {
    private static final String TAG = "CustomMultiPointOutputStream";
    private final DownloadTask task;

    CustomMultiPointOutputStream(final DownloadTask task, BreakpointInfo info, DownloadStore store, Runnable syncRunnable) {
        super(task, info, store, syncRunnable);
        this.task = task;
    }

    public CustomMultiPointOutputStream(DownloadTask task, BreakpointInfo info, DownloadStore store) {
        this(task, info, store, null);
    }

    @Override // com.liulishuo.okdownload.core.file.MultiPointOutputStream
    synchronized void close(int blockIndex) throws IOException {
        DownloadOutputStream downloadOutputStream = this.outputStreamMap.get(blockIndex);
        if (downloadOutputStream != null) {
            downloadOutputStream.close();
            synchronized (this.noSyncLengthMap) {
                this.outputStreamMap.remove(blockIndex);
                this.noSyncLengthMap.remove(blockIndex);
            }
            Util.d(TAG, "OutputStream close task[" + this.task.getId() + "] block[" + blockIndex + "]");
        }
    }
}
