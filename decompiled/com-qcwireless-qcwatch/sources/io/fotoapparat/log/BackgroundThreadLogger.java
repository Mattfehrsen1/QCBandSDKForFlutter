package io.fotoapparat.log;

import io.fotoapparat.hardware.ExecutorKt;
import io.fotoapparat.log.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackgroundThreadLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/fotoapparat/log/BackgroundThreadLogger;", "Lio/fotoapparat/log/Logger;", "logger", "(Lio/fotoapparat/log/Logger;)V", "log", "", "message", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class BackgroundThreadLogger implements Logger {
    private final Logger logger;

    public BackgroundThreadLogger(Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
    }

    @Override // io.fotoapparat.log.Logger
    public void recordMethod() {
        Logger.DefaultImpls.recordMethod(this);
    }

    @Override // io.fotoapparat.log.Logger
    public void log(final String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        ExecutorKt.executeLoggingThread(new Function0<Unit>() { // from class: io.fotoapparat.log.BackgroundThreadLogger.log.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BackgroundThreadLogger.this.logger.log(message);
            }
        });
    }
}
