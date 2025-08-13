package com.elvishew.xlog.formatter.thread;

/* loaded from: classes2.dex */
public class DefaultThreadFormatter implements ThreadFormatter {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(Thread thread) {
        return "Thread: " + thread.getName();
    }
}
