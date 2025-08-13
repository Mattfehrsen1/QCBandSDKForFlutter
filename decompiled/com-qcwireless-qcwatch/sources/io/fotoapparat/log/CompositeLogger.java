package io.fotoapparat.log;

import io.fotoapparat.log.Logger;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CompositeLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/fotoapparat/log/CompositeLogger;", "Lio/fotoapparat/log/Logger;", "loggers", "", "(Ljava/util/List;)V", "log", "", "message", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CompositeLogger implements Logger {
    private final List<Logger> loggers;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositeLogger(List<? extends Logger> loggers) {
        Intrinsics.checkParameterIsNotNull(loggers, "loggers");
        this.loggers = loggers;
    }

    @Override // io.fotoapparat.log.Logger
    public void recordMethod() {
        Logger.DefaultImpls.recordMethod(this);
    }

    @Override // io.fotoapparat.log.Logger
    public void log(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Iterator<T> it = this.loggers.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).log(message);
        }
    }
}
