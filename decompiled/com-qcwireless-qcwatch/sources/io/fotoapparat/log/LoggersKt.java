package io.fotoapparat.log;

import android.content.Context;
import io.reactivex.annotations.SchedulerSupport;
import java.io.File;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Loggers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u001f\u0010\u0007\u001a\u00020\u00012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\b\"\u00020\u0001¢\u0006\u0002\u0010\t\u001a\u0006\u0010\n\u001a\u00020\u0001¨\u0006\u000b"}, d2 = {"fileLogger", "Lio/fotoapparat/log/Logger;", "context", "Landroid/content/Context;", "file", "Ljava/io/File;", "logcat", "loggers", "", "([Lio/fotoapparat/log/Logger;)Lio/fotoapparat/log/Logger;", SchedulerSupport.NONE, "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class LoggersKt {
    public static final Logger none() {
        return new DummyLogger();
    }

    public static final Logger logcat() {
        return new LogcatLogger();
    }

    public static final Logger fileLogger(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return new BackgroundThreadLogger(new FileLogger(file));
    }

    public static final Logger fileLogger(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return fileLogger(new File(context.getExternalFilesDir("logs"), "log.txt"));
    }

    public static final Logger loggers(Logger... loggers) {
        Intrinsics.checkParameterIsNotNull(loggers, "loggers");
        return new CompositeLogger(ArraysKt.toList(loggers));
    }
}
