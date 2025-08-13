package org.koin.core.logger;

import androidx.core.app.NotificationCompat;
import java.io.PrintStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrintLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016¨\u0006\n"}, d2 = {"Lorg/koin/core/logger/PrintLogger;", "Lorg/koin/core/logger/Logger;", "level", "Lorg/koin/core/logger/Level;", "(Lorg/koin/core/logger/Level;)V", "log", "", NotificationCompat.CATEGORY_MESSAGE, "", "Lorg/koin/core/logger/MESSAGE;", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class PrintLogger extends Logger {
    /* JADX WARN: Multi-variable type inference failed */
    public PrintLogger() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrintLogger(Level level) {
        super(level);
        Intrinsics.checkParameterIsNotNull(level, "level");
    }

    public /* synthetic */ PrintLogger(Level level, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Level.INFO : level);
    }

    @Override // org.koin.core.logger.Logger
    public void log(Level level, String msg) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        PrintStream printStream = level.compareTo(Level.ERROR) >= 0 ? System.err : System.out;
        if (getLevel().compareTo(level) <= 0) {
            printStream.println('[' + level + "] [Koin] " + msg);
        }
    }
}
