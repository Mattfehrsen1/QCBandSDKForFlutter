package com.app.watch.keeping.exception;

import android.os.Process;
import java.lang.Thread;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CactusUncaughtExceptionHandler.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0016\u0010\u0003\u001a\n \u0004*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/app/watch/keeping/exception/CactusUncaughtExceptionHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "()V", "mDefault", "kotlin.jvm.PlatformType", "uncaughtException", "", "t", "Ljava/lang/Thread;", "e", "", "Companion", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CactusUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<CactusUncaughtExceptionHandler> instance$delegate = LazyKt.lazy(new Function0<CactusUncaughtExceptionHandler>() { // from class: com.app.watch.keeping.exception.CactusUncaughtExceptionHandler$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CactusUncaughtExceptionHandler invoke() {
            return new CactusUncaughtExceptionHandler(null);
        }
    });
    private final Thread.UncaughtExceptionHandler mDefault;

    public /* synthetic */ CactusUncaughtExceptionHandler(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final CactusUncaughtExceptionHandler getInstance() {
        return INSTANCE.getInstance();
    }

    private CactusUncaughtExceptionHandler() {
        this.mDefault = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* compiled from: CactusUncaughtExceptionHandler.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/app/watch/keeping/exception/CactusUncaughtExceptionHandler$Companion;", "", "()V", "instance", "Lcom/app/watch/keeping/exception/CactusUncaughtExceptionHandler;", "getInstance$annotations", "getInstance", "()Lcom/app/watch/keeping/exception/CactusUncaughtExceptionHandler;", "instance$delegate", "Lkotlin/Lazy;", "keeping_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final CactusUncaughtExceptionHandler getInstance() {
            return (CactusUncaughtExceptionHandler) CactusUncaughtExceptionHandler.instance$delegate.getValue();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread t, Throwable e) {
        Intrinsics.checkNotNullParameter(t, "t");
        Intrinsics.checkNotNullParameter(e, "e");
        String message = e.getMessage();
        if (message != null) {
            String str = message;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "Bad notification for startForeground: java.lang.RuntimeException: invalid channel for service notification", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "Context.startForegroundService() did not then call Service.startForeground()", false, 2, (Object) null)) {
                Process.killProcess(Process.myPid());
                System.exit(10);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            }
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefault;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(t, e);
        }
    }
}
