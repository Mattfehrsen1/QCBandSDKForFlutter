package org.koin.core.context;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.KoinApplication;
import org.koin.core.error.KoinAppAlreadyStartedException;

/* compiled from: GlobalContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0007J\n\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007J\b\u0010\u000e\u001a\u00020\fH\u0007R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/koin/core/context/GlobalContext;", "", "()V", "app", "Lorg/koin/core/KoinApplication;", "getApp$koin_core", "()Lorg/koin/core/KoinApplication;", "setApp$koin_core", "(Lorg/koin/core/KoinApplication;)V", "get", "getOrNull", "start", "", "koinApplication", "stop", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final class GlobalContext {
    public static final GlobalContext INSTANCE = new GlobalContext();
    private static KoinApplication app;

    private GlobalContext() {
    }

    public final KoinApplication getApp$koin_core() {
        return app;
    }

    public final void setApp$koin_core(KoinApplication koinApplication) {
        app = koinApplication;
    }

    @JvmStatic
    public static final KoinApplication get() {
        KoinApplication koinApplication = app;
        if (koinApplication != null) {
            return koinApplication;
        }
        throw new IllegalStateException("KoinApplication has not been started".toString());
    }

    @JvmStatic
    public static final KoinApplication getOrNull() {
        return app;
    }

    @JvmStatic
    public static final void start(KoinApplication koinApplication) throws KoinAppAlreadyStartedException {
        Intrinsics.checkParameterIsNotNull(koinApplication, "koinApplication");
        if (app != null) {
            throw new KoinAppAlreadyStartedException("A Koin Application has already been started");
        }
        app = koinApplication;
    }

    @JvmStatic
    public static final void stop() {
        synchronized (INSTANCE) {
            KoinApplication koinApplication = app;
            if (koinApplication != null) {
                koinApplication.close();
            }
            app = null;
            Unit unit = Unit.INSTANCE;
        }
    }
}
